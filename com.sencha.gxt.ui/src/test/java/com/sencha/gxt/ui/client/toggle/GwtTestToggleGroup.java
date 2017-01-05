package com.sencha.gxt.ui.client.toggle;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.RootPanel;
import com.sencha.gxt.core.client.util.ToggleGroup;
import com.sencha.gxt.ui.client.UITestCase;
import com.sencha.gxt.widget.core.client.button.ToggleButton;

public class GwtTestToggleGroup extends UITestCase {
  
  public void testToggle() {
    ToggleButton button1 = new ToggleButton();
    RootPanel.get().add(button1);
    CheckBox checkbox1 = new CheckBox();
    RootPanel.get().add(checkbox1);
    ToggleButton button2 = new ToggleButton();
    RootPanel.get().add(button2);
    assertFalse(button1.getValue());
    assertFalse(checkbox1.getValue());
    assertFalse(button2.getValue());

    final List<HasValue<Boolean>> list = new ArrayList<HasValue<Boolean>>();

    ToggleGroup group = new ToggleGroup();
    group.addValueChangeHandler(new ValueChangeHandler<HasValue<Boolean>>() {
      @Override
      public void onValueChange(ValueChangeEvent<HasValue<Boolean>> event) {
        list.add(event.getValue());
      }
    });
    assertTrue(group.add(button1));
    assertFalse(group.add(button1));
    assertTrue(group.contains(button1));
    assertTrue(group.add(checkbox1));
    assertFalse(group.add(checkbox1));
    assertTrue(group.contains(checkbox1));
    assertTrue(group.add(button2));
    assertFalse(group.add(button2));
    assertTrue(group.contains(button2));

    assertEquals(3, group.size());

    button1.setValue(true, true);
    assertEquals(button1, group.getValue());
    assertEquals(1, list.size());
    assertTrue(button1.getValue());
    assertFalse(checkbox1.getValue());
    assertFalse(button2.getValue());

    checkbox1.setValue(true, true);
    assertEquals(checkbox1, group.getValue());
    assertEquals(2, list.size());
    assertFalse(button1.getValue());
    assertTrue(checkbox1.getValue());
    assertFalse(button2.getValue());

    button2.setValue(true, true);
    assertEquals(button2, group.getValue());
    assertEquals(3, list.size());
    assertFalse(button1.getValue());
    assertFalse(checkbox1.getValue());
    assertTrue(button2.getValue());

    assertTrue(group.remove(button2));
    assertFalse(group.remove(button2));
    assertFalse(group.contains(button2));
    assertEquals(2, group.size());

    button1.setValue(true, true);
    assertEquals(button1, group.getValue());
    assertEquals(4, list.size());
    assertTrue(button1.getValue());
    assertFalse(checkbox1.getValue());
    assertTrue(button2.getValue());

    assertEquals(4, list.size());
    assertEquals(button1, list.get(0));
    assertEquals(checkbox1, list.get(1));
    assertEquals(button2, list.get(2));
    assertEquals(button1, list.get(3));

    group.setValue(null, true);
    assertEquals(5, list.size());
    assertNull(group.getValue());
    assertFalse(button1.getValue());
    assertFalse(checkbox1.getValue());

    group.setValue(button1, true);
    assertEquals(6, list.size());
    assertEquals(button1, group.getValue());
    assertTrue(button1.getValue());
    assertFalse(checkbox1.getValue());

    group.setValue(checkbox1, true);
    assertEquals(7, list.size());
    assertEquals(checkbox1, group.getValue());
    assertFalse(button1.getValue());
    assertTrue(checkbox1.getValue());

    group.setValue(null, true);
    assertEquals(8, list.size());
    assertNull(group.getValue());
    assertFalse(button1.getValue());
    assertFalse(checkbox1.getValue());

    group.setValue(button2, true);
    assertEquals(8, list.size());
    assertNull(group.getValue());
    assertFalse(button1.getValue());
    assertFalse(checkbox1.getValue());

    group.setValue(button1, true);
    assertEquals(9, list.size());
    assertEquals(button1, group.getValue());
    assertTrue(button1.getValue());
    assertFalse(checkbox1.getValue());

    group.setValue(button2, true);
    assertEquals(10, list.size());
    assertNull(group.getValue());
    assertFalse(button1.getValue());
    assertFalse(checkbox1.getValue());
  }
}
