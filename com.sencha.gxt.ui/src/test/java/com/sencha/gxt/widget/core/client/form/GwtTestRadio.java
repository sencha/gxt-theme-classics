package com.sencha.gxt.widget.core.client.form;

import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.InputElement;
import com.google.gwt.dom.client.NodeList;
import com.google.gwt.editor.client.Editor.Path;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.ui.RootPanel;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import com.sencha.gxt.widget.core.client.CoreBaseTestCase;

public class GwtTestRadio extends CoreBaseTestCase {

  public class TestModel {

    private String key;
    private boolean value;

    public TestModel(String key, boolean value) {
      this.key = key;
      this.value = value;
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

    ValueProvider<TestModel, String> key();

    @Path("key")
    ModelKeyProvider<TestModel> keyProvider();

    ValueProvider<TestModel, Boolean> value();

  }

  public void testAttributes() {
    Radio radio = new Radio();
    radio.setName("name");
    radio.setValue(true);
    RootPanel.get().add(radio);

    assertEquals("name", radio.getElement().selectNode("input").getPropertyString("name"));
  }

  public void testDisabled() {
    Radio field = new Radio();

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

  public void testChangeEventOnSetValue() {
    Radio box = new Radio();

    box.addValueChangeHandler(new ValueChangeHandler<Boolean>() {

      @Override
      public void onValueChange(ValueChangeEvent<Boolean> event) {
        assertTrue(event.getValue());
      }
    });

    RootPanel.get().add(box);

    assertFalse(box.getValue());

    box.setValue(true);

    assertTrue(box.getValue());
  }

  public void testClickRadio() {
    Radio box = new Radio();
    RootPanel.get().add(box);

    assertFalse(box.getValue());

    box.setValue(true);

    assertTrue(box.getValue());
  }

  public void testReadOnly() {
    Radio radio = new Radio();
    RootPanel.get().add(radio);

    NodeList<Element> inputs;
    inputs = radio.getElement().select("input");
    assertTrue(inputs.getLength() > 0);
    for (int i = 0; i < inputs.getLength(); i++) {
      assertFalse(inputs.getItem(i).hasAttribute("readonly"));
    }

    inputs = radio.getElement().select("input");
    assertTrue(inputs.getLength() > 0);

    radio.redraw();
    inputs = radio.getElement().select("input");
    assertTrue(inputs.getLength() > 0);
  }

  public void testClear() {
    Radio radio = new Radio();
    RootPanel.get().add(radio);

    radio.setValue(true);
    assertTrue(radio.getValue());

    radio.clear();
    assertFalse(radio.getValue());
  }

  public void testRadioTrueThenReset() {
    // Given a radio
    Radio radio = new Radio();
    RootPanel.get().add(radio);

    // And value is true
    radio.setValue(true);
    assertTrue(radio.getValue());

    // When the radio is reset
    radio.reset();

    // Then the value will be reset to
    assertFalse(radio.getValue());
  }

  public void testRadioFalseThenReset() {
    // Given a radio
    Radio radio = new Radio();
    RootPanel.get().add(radio);

    // And value is true
    radio.setValue(false);
    assertFalse(radio.getValue());

    // When the radio is reset
    radio.reset();

    // Then the value will be reset to
    assertFalse(radio.getValue());
  }

  public void testResetOriginalValueTrue() {
    // Given a radio
    Radio radio = new Radio();
    RootPanel.get().add(radio);

    // And the original value
    radio.setValue(true);
    radio.setOriginalValue(true);
    assertTrue(radio.getValue());
    assertTrue(radio.getOriginalValue());

    // When the radio is reset
    radio.reset();

    // Then the value will be reset to
    assertTrue(radio.getValue());
  }

  public void testResetOriginalValueFalse() {
    // Given a radio
    Radio radio = new Radio();
    RootPanel.get().add(radio);

    // And the original value is
    radio.setValue(false);
    radio.setOriginalValue(false);
    assertFalse(radio.getValue());
    assertFalse(radio.getOriginalValue());

    // When the radio is reset
    radio.reset();

    // Then the value will be reset to
    assertFalse(radio.getValue());
  }

  public void testResetOriginalValueNull() {
    // Given a radio
    Radio radio = new Radio();
    RootPanel.get().add(radio);

    // And the original value is
    radio.setValue(null);
    radio.setOriginalValue(null);
    assertFalse(radio.getValue());
    assertNull(radio.getOriginalValue());

    // When the radio is reset
    radio.reset();

    // Then the value will be reset to
    assertFalse(radio.getValue());
  }

}
