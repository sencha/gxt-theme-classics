package com.sencha.gxt.widget.core.client.button;

import java.util.HashMap;
import java.util.Map;

import com.google.gwt.event.shared.GwtEvent.Type;
import com.google.gwt.user.client.ui.RootPanel;
import com.sencha.gxt.core.client.dom.XElement;
import com.sencha.gxt.widget.core.client.CoreBaseTestCase;
import com.sencha.gxt.widget.core.client.event.BeforeSelectEvent;
import com.sencha.gxt.widget.core.client.event.BeforeSelectEvent.BeforeSelectHandler;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.menu.Menu;

public class GwtTestSplitButton extends CoreBaseTestCase {

  @SuppressWarnings("rawtypes")
  public void testCancelEvent() {
    final Map<Type, Object> map = new HashMap<Type, Object>();

    SplitButton button = new SplitButton();

    button.addBeforeSelectHandler(new BeforeSelectHandler() {
      @Override
      public void onBeforeSelect(BeforeSelectEvent event) {
        map.put(event.getAssociatedType(), true);
        event.setCancelled(true);
      }
    });
    button.addSelectHandler(new SelectHandler() {

      @Override
      public void onSelect(SelectEvent event) {
        map.put(event.getAssociatedType(), true);
      }
    });

    RootPanel.get().add(button);
    button.getElement().getFirstChildElement().<XElement>cast().click();

    assertEquals(true, map.get(BeforeSelectEvent.getType()));
    assertNull(map.get(SelectEvent.getType()));
  }

  @SuppressWarnings("rawtypes")
  public void testDisable() {
    final Map<Type, Object> map = new HashMap<Type, Object>();

    SplitButton button = new SplitButton();

    button.addBeforeSelectHandler(new BeforeSelectHandler() {
      @Override
      public void onBeforeSelect(BeforeSelectEvent event) {
        map.put(event.getAssociatedType(), true);
      }
    });
    button.addSelectHandler(new SelectHandler() {

      @Override
      public void onSelect(SelectEvent event) {
        map.put(event.getAssociatedType(), true);
      }
    });

    RootPanel.get().add(button);
    button.disable();

    button.getElement().getFirstChildElement().<XElement>cast().click();

    assertNull(map.get(BeforeSelectEvent.getType()));
    assertNull(map.get(SelectEvent.getType()));

  }

  public void testMenu() {
    SplitButton button = new SplitButton("test");
    button.setMenu(new Menu());
    assertFalse(button.getMenu().isVisible());

    button.showMenu();
    assertTrue(button.getMenu().isVisible());

    button.hideMenu();
    assertFalse(button.getMenu().isVisible());
  }
}
