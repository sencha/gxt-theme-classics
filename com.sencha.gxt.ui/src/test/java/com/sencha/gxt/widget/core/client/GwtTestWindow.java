package com.sencha.gxt.widget.core.client;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.google.gwt.event.shared.GwtEvent.Type;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.junit.DoNotRunWith;
import com.google.gwt.junit.Platform;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootPanel;
import com.sencha.gxt.ui.client.UITestCase;
import com.sencha.gxt.widget.core.client.container.FlowLayoutContainer;
import com.sencha.gxt.widget.core.client.event.ActivateEvent;
import com.sencha.gxt.widget.core.client.event.ActivateEvent.ActivateHandler;
import com.sencha.gxt.widget.core.client.event.DeactivateEvent;
import com.sencha.gxt.widget.core.client.event.DeactivateEvent.DeactivateHandler;
import com.sencha.gxt.widget.core.client.event.HideEvent;
import com.sencha.gxt.widget.core.client.event.HideEvent.HideHandler;
import com.sencha.gxt.widget.core.client.event.MaximizeEvent;
import com.sencha.gxt.widget.core.client.event.MaximizeEvent.MaximizeHandler;
import com.sencha.gxt.widget.core.client.event.MinimizeEvent;
import com.sencha.gxt.widget.core.client.event.MinimizeEvent.MinimizeHandler;
import com.sencha.gxt.widget.core.client.event.RestoreEvent;
import com.sencha.gxt.widget.core.client.event.RestoreEvent.RestoreHandler;
import com.sencha.gxt.widget.core.client.event.ShowEvent;
import com.sencha.gxt.widget.core.client.event.ShowEvent.ShowHandler;

public class GwtTestWindow extends UITestCase {

  @SuppressWarnings("rawtypes")
  public void testActivate() {
    final Map<Type, Object> map = new HashMap<Type, Object>();

    Window w = new Window();
    w.setActive(false);
    HandlerRegistration handler = w.addActivateHandler(new ActivateHandler<Window>() {

      @Override
      public void onActivate(ActivateEvent event) {
        map.put(event.getAssociatedType(), true);
      }
    });

    RootPanel.get().add(w);
    w.setActive(true);
    assertEquals(true, map.get(ActivateEvent.getType()));
    handler.removeHandler();
    map.clear();
    w.addDeactivateHandler(new DeactivateHandler<Window>() {

      @Override
      public void onDeactivate(DeactivateEvent event) {
        map.put(event.getAssociatedType(), true);
      }

    });
    w.setActive(false);
    assertEquals(true, map.get(DeactivateEvent.getType()));

  }

  // With HtmlUnitLayout the width and height appear to be fixed,
  // regardless of the size of the content. This is not the case
  // on Firefox, Chrome and IE 8, which work as expected.
  @DoNotRunWith({Platform.HtmlUnitLayout})
  public void testAutoSize() {
    FlowLayoutContainer flc = new FlowLayoutContainer();
    flc.add(new HTML("This is line 1"));

    Window w = new Window();
    w.setPixelSize(-1, -1);
    w.setMinWidth(0);
    w.setMinHeight(0);
    w.setWidget(flc);
    w.show();

    int initialWidth = w.getOffsetWidth();
    int initialHeight = w.getOffsetHeight();

    assert (initialWidth > 0);
    assert (initialHeight > 0);

    flc.add(new HTML("This is line 2. It is longer than line 1."));

    int finalWidth = w.getOffsetWidth();
    int finalHeight = w.getOffsetHeight();

    assert (finalWidth > initialWidth);
    assert (finalHeight > initialHeight);
  }

  @SuppressWarnings("rawtypes")
  public void testMinMax() {
    final Map<Type, Object> map = new HashMap<Type, Object>();

    Window w = new Window();
    w.setActive(false);
    w.addMinimizeHandler(new MinimizeHandler() {

      @Override
      public void onMinimize(MinimizeEvent event) {
        map.put(event.getAssociatedType(), true);
      }

    });
    w.addMaximizeHandler(new MaximizeHandler() {

      @Override
      public void onMaximize(MaximizeEvent event) {
        map.put(event.getAssociatedType(), true);
      }
    });

    RootPanel.get().add(w);
    w.minimize();
    assertEquals(true, map.get(MinimizeEvent.getType()));
    map.clear();
    w.maximize();
    assertEquals(true, map.get(MaximizeEvent.getType()));
  }

  @DoNotRunWith({Platform.HtmlUnitLayout})
  public void testNoHeaderSizing() {
    Window w = new Window();
    int initialWidth = 200, initialHeight = 150;
    w.setPixelSize(initialWidth, initialHeight);

    w.setHeaderVisible(false);
    w.show();

    assertEquals(initialWidth, w.getOffsetWidth());
    assertEquals(initialHeight, w.getOffsetHeight());

    int newHeight = 123, newWidth = 456;
    w.setPixelSize(newWidth, newHeight);

    assertEquals(newWidth, w.getOffsetWidth());
    assertEquals(newHeight, w.getOffsetHeight());
  }

  @SuppressWarnings("rawtypes")
  public void testRestore() {
    final Map<Type, Object> map = new HashMap<Type, Object>();

    Window w = new Window();
    w.setActive(false);
    w.addRestoreHandler(new RestoreHandler() {

      @Override
      public void onRestore(RestoreEvent event) {
        map.put(event.getAssociatedType(), true);
      }
    });

    RootPanel.get().add(w);
    w.maximize();
    w.restore();
    assertEquals(true, map.get(RestoreEvent.getType()));
  }

  @DoNotRunWith({Platform.HtmlUnitLayout})
  public void testSimpleSizing() {
    Window w = new Window();
    int initialWidth = 200, initialHeight = 150;
    w.setPixelSize(initialWidth, initialHeight);

    w.show();

    assertEquals(initialWidth, w.getOffsetWidth());
    assertEquals(initialHeight, w.getOffsetHeight());

    int newHeight = 123, newWidth = 456;
    w.setPixelSize(newWidth, newHeight);

    assertEquals(newWidth, w.getOffsetWidth());
    assertEquals(newHeight, w.getOffsetHeight());
  }

  public void testWindow() {
    Window w = new Window();
    w.show();
    assertTrue(w.isAttached());
    assertTrue(w.getElement() != null);
  }

  @SuppressWarnings("rawtypes")
  public void testWindowEvents() {
    final Set<Type> events = new HashSet<Type>();
    Window w = new Window();
    w.addShowHandler(new ShowHandler() {
      @Override
      public void onShow(ShowEvent event) {
        events.add(event.getAssociatedType());
      }
    });
    w.addHideHandler(new HideHandler() {
      @Override
      public void onHide(HideEvent event) {
        events.add(event.getAssociatedType());
      }
    });

    w.show();
    w.hide();

    assertTrue(events.contains(ShowEvent.getType()));
    assertTrue(events.contains(HideEvent.getType()));
  }
}
