package com.sencha.gxt.widget.core.client.form;

import java.util.HashMap;
import java.util.Map;

import com.google.gwt.event.shared.GwtEvent.Type;
import com.google.gwt.user.client.ui.RootPanel;
import com.sencha.gxt.ui.client.UITestCase;
import com.sencha.gxt.widget.core.client.event.InvalidEvent;
import com.sencha.gxt.widget.core.client.event.InvalidEvent.InvalidHandler;
import com.sencha.gxt.widget.core.client.event.ValidEvent;
import com.sencha.gxt.widget.core.client.event.ValidEvent.ValidHandler;

public class GwtTestField extends UITestCase {

  public void testField() {
    TextField field = new TextField();
    field.setName("foo");

    RootPanel.get().add(field);

    assertEquals("foo", field.getName());
    assertEquals("foo", field.getElement().child("input").getAttribute("name"));
  }

  @SuppressWarnings("rawtypes")
  public void testValidInvalidEvent() {
    final Map<Type, Object> map = new HashMap<Type, Object>();

    TextField field = new TextField();

    field.addInvalidHandler(new InvalidHandler() {

      @Override
      public void onInvalid(InvalidEvent event) {
        map.put(event.getAssociatedType(), true);
      }

    });
    field.addValidHandler(new ValidHandler(){

      @Override
      public void onValid(ValidEvent event) {
        map.put(event.getAssociatedType(), true);
      }
    });

    RootPanel.get().add(field);
    field.markInvalid("foo");
    assertEquals(true, map.get(InvalidEvent.getType()));
    map.clear();
    field.clearInvalid();
    assertEquals(true, map.get(ValidEvent.getType()));
  }

}
