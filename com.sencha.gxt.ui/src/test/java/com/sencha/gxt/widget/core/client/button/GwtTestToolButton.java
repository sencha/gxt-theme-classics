package com.sencha.gxt.widget.core.client.button;

import java.util.HashMap;
import java.util.Map;

import com.google.gwt.event.shared.GwtEvent.Type;
import com.google.gwt.user.client.ui.RootPanel;
import com.sencha.gxt.widget.core.client.CoreBaseTestCase;
import com.sencha.gxt.widget.core.client.event.BeforeSelectEvent;
import com.sencha.gxt.widget.core.client.event.BeforeSelectEvent.BeforeSelectHandler;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;

public class GwtTestToolButton extends CoreBaseTestCase {

  @SuppressWarnings("rawtypes")
  public void testCancelEvent() {
    final Map<Type, Object> map = new HashMap<Type, Object>();

    ToolButton button = new ToolButton("foo");

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
    button.getElement().click();

    assertEquals(true, map.get(BeforeSelectEvent.getType()));
    assertNull(map.get(SelectEvent.getType()));
  }

  @SuppressWarnings("rawtypes")
  public void testDisable() {
    final Map<Type, Object> map = new HashMap<Type, Object>();

    ToolButton button = new ToolButton("foo");

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

    button.getElement().click();

    assertNull(map.get(BeforeSelectEvent.getType()));
    assertNull(map.get(SelectEvent.getType()));
  }

}
