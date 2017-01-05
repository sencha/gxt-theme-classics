package com.sencha.gxt.widget.core.client.button;

import java.util.HashMap;
import java.util.Map;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.GwtEvent.Type;
import com.google.gwt.user.client.ui.RootPanel;
import com.sencha.gxt.core.client.dom.XElement;
import com.sencha.gxt.widget.core.client.CoreBaseTestCase;
import com.sencha.gxt.widget.core.client.event.BeforeSelectEvent;
import com.sencha.gxt.widget.core.client.event.BeforeSelectEvent.BeforeSelectHandler;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.menu.Menu;

public class GwtTestToggleButton extends CoreBaseTestCase {

  @SuppressWarnings("rawtypes")
  public void testCancelEvent() {
    final Map<Type, Object> map = new HashMap<Type, Object>();

    ToggleButton button = new ToggleButton();

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

    button.addValueChangeHandler(new ValueChangeHandler<Boolean>() {
      @Override
      public void onValueChange(ValueChangeEvent<Boolean> event) {
        map.put(event.getAssociatedType(), true);
      }
    });

    RootPanel.get().add(button);

    button.getElement().getFirstChildElement().<XElement>cast().click();

    assertEquals(true, map.get(BeforeSelectEvent.getType()));

    assertNull(map.get(SelectEvent.getType()));
    assertNull(map.get(ValueChangeEvent.getType()));
  }

  @SuppressWarnings("rawtypes")
  public void testDisable() {
    final Map<Type, Object> map = new HashMap<Type, Object>();

    ToggleButton button = new ToggleButton();

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

    button.addValueChangeHandler(new ValueChangeHandler<Boolean>() {
      @Override
      public void onValueChange(ValueChangeEvent<Boolean> event) {
        map.put(event.getAssociatedType(), true);
      }
    });

    RootPanel.get().add(button);
    button.disable();

    button.getElement().getFirstChildElement().<XElement>cast().click();

    assertNull(map.get(BeforeSelectEvent.getType()));
    assertNull(map.get(SelectEvent.getType()));
    assertNull(map.get(ValueChangeEvent.getType()));
  }

  public void testMenu() {
    ToggleButton button = new ToggleButton("test");
    button.setMenu(new Menu());
    assertFalse(button.getMenu().isVisible());

    button.showMenu();
    assertTrue(button.getMenu().isVisible());

    button.hideMenu();
    assertFalse(button.getMenu().isVisible());
  }

  public void testToggle() {
    ToggleButton toggle = new ToggleButton();
    RootPanel.get().add(toggle);

    assertFalse(toggle.getValue());

    toggle.getElement().getFirstChildElement().<XElement>cast().click();

    assertTrue(toggle.getValue());

    toggle.getElement().getFirstChildElement().<XElement>cast().click();
    assertFalse(toggle.getValue());

    toggle.disable();
    toggle.getElement().getFirstChildElement().<XElement>cast().click();
    assertFalse(toggle.getValue());
  }

}
