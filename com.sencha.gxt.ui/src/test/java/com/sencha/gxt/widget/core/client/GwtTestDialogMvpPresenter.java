package com.sencha.gxt.widget.core.client;

import java.util.Iterator;

import junit.framework.TestCase;

import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.Dialog.PredefinedButton;
import com.sencha.gxt.widget.core.client.GwtTestDialogMvpPresenter.Presenter.Display;
import com.sencha.gxt.widget.core.client.GwtTestDialogMvpPresenter.Presenter.EventBus;
import com.sencha.gxt.widget.core.client.event.DialogHideEvent;
import com.sencha.gxt.widget.core.client.event.DialogHideEvent.DialogHideHandler;
import com.sencha.gxt.widget.core.client.event.DialogHideEvent.HasDialogHideHandlers;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.HasSelectHandlers;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;

public class GwtTestDialogMvpPresenter extends TestCase {

  public static class Presenter {

    private Display display;
    private EventBus eventBus;

    public interface Display extends IsWidget {
      HasSelectHandlers getYesButton();

      HasDialogHideHandlers getDialog();
    }

    public interface EventBus {
      void fireOk();
      void fireHide(PredefinedButton hideButton);
    }
    
    public Presenter(EventBus eventBus, Display display) {
      this.eventBus = eventBus;
      this.display = display;
    }

    public void go(HasWidgets container) {
      display.getYesButton().addSelectHandler(new SelectHandler() {
        @Override
        public void onSelect(SelectEvent event) {
          eventBus.fireOk();
        }
      });
      display.getDialog().addDialogHideHandler(new DialogHideHandler() {
        @Override
        public void onDialogHide(DialogHideEvent event) {
          eventBus.fireHide(event.getHideButton());
        }
      });
      container.add(display.asWidget());
    }
  }
  
  private final class MockDisplay implements Display {

    @Override
    public Widget asWidget() {
      return null;
    }

    @Override
    public HasDialogHideHandlers getDialog() {
      return new MockHasDialogHideHandlers();
    }

    @Override
    public HasSelectHandlers getYesButton() {
      return new MockHasSelectHandlers();
    }

  }

  private final class MockEventBus implements EventBus {

    @Override
    public void fireHide(PredefinedButton button) {
      hideButton = button;
    }

    @Override
    public void fireOk() {
      okFired = true;
    }

  }

  private final class MockHasDialogHideHandlers implements HasDialogHideHandlers {
    @Override
    public HandlerRegistration addDialogHideHandler(DialogHideHandler handler) {
      dialogHideHandler = handler;
      return null;
    }
  }

  private final class MockHasSelectHandlers implements HasSelectHandlers {

    @Override
    public HandlerRegistration addSelectHandler(SelectHandler handler) {
      selectHandler = handler;
      return null;
    }

  }

  private final class MockHasWidgets implements HasWidgets {

    @Override
    public void add(Widget w) {
    }

    @Override
    public void clear() {
    }

    @Override
    public Iterator<Widget> iterator() {
      return null;
    }

    @Override
    public boolean remove(Widget w) {
      return false;
    }

  }

  private Presenter contactsPresenter;
  private DialogHideHandler dialogHideHandler;
  private SelectHandler selectHandler;
  private PredefinedButton hideButton;
  private boolean okFired;

  public void testDialogHideHandler() {
    dialogHideHandler.onDialogHide(new DialogHideEvent(PredefinedButton.YES));
    assertTrue(hideButton == PredefinedButton.YES);
  }

  public void testSelectHandler() {
    selectHandler.onSelect(new SelectEvent());
    assertTrue(okFired);
  }

  protected void setUp() {
    contactsPresenter = new Presenter(new MockEventBus(), new MockDisplay());
    contactsPresenter.go(new MockHasWidgets());
  }
}
