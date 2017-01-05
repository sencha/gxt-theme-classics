package com.sencha.gxt.widget.core.client.form;

import java.util.HashMap;
import java.util.Map;

import com.google.gwt.dom.client.InputElement;
import com.google.gwt.editor.client.Editor.Path;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.GwtEvent.Type;
import com.google.gwt.user.client.ui.RootPanel;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import com.sencha.gxt.widget.core.client.CoreBaseTestCase;

public class GwtTestCheckBox extends CoreBaseTestCase {

  public class TestModel {

    private String key;
    private boolean value;

    public TestModel(String key) {
      this.setKey(key);
    }

    public String getKey() {
      return key;
    }

    public boolean getValue() {
      return value;
    }

    public void setKey(String key) {
      this.key = key;
    }

    public void setValue(boolean value) {
      this.value = value;
    }

  }

  public interface TestModelProperties extends PropertyAccess<TestModel> {

    @Path("key")
    ModelKeyProvider<TestModel> keyProvider();

    ValueProvider<TestModel, Boolean> value();

  }

  public void testChangeEvent() {
    final Map<Type<?>, Object> map = new HashMap<Type<?>, Object>();

    CheckBox box = new CheckBox();

    box.addValueChangeHandler(new ValueChangeHandler<Boolean>() {

      @Override
      public void onValueChange(ValueChangeEvent<Boolean> event) {
        map.put(event.getAssociatedType(), true);
      }

    });
    RootPanel.get().add(box);

    box.setValue(true, true);

    assertEquals(true, map.get(ValueChangeEvent.getType()));
  }

  public void testCheckBox() {
    CheckBox box = new CheckBox();
    box.setName("name");

    assertFalse(box.getValue());

    box.setValue(true);
    RootPanel.get().add(box);

    assertEquals("name", box.getElement().selectNode("input").getPropertyString("name"));
  }

  public void testDisabled() {
    CheckBox field = new CheckBox();

    InputElement input = field.getElement().selectNode("input").cast();
    assertEquals(false, input.isDisabled());
    field.disable();
    assertEquals(true, input.isDisabled());

    RootPanel.get().add(field);
    assertEquals(true, input.isDisabled());

    field.redraw();
    // input element redrawn
    input = field.getElement().selectNode("input").cast();
    assertEquals(true, input.isDisabled());

    field.enable();
    assertEquals(false, input.isDisabled());
  }

  public void testTabIndex() {
    CheckBox tf = new CheckBox();
    tf.setValue(false);
    tf.setTabIndex(123);
    InputElement elt = tf.getElement().selectNode("input").<InputElement> cast();
    assertEquals(123, elt.getTabIndex());

    RootPanel.get().add(tf);
    elt = tf.getElement().selectNode("input").<InputElement> cast();
    assertEquals(123, elt.getTabIndex());

    tf.setValue(true);
    elt = tf.getElement().selectNode("input").<InputElement> cast();
    assertEquals(123, elt.getTabIndex());

    tf.setTabIndex(321);
    elt = tf.getElement().selectNode("input").<InputElement> cast();
    assertEquals(321, elt.getTabIndex());
  }

  public void testClear() {
    CheckBox checkbox = new CheckBox();
    RootPanel.get().add(checkbox);

    checkbox.setValue(true);
    assertTrue(checkbox.getValue());

    checkbox.clear();
    assertFalse(checkbox.getValue());
  }

}
