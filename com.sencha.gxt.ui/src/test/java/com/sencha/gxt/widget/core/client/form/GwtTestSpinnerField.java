package com.sencha.gxt.widget.core.client.form;

import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.InputElement;
import com.google.gwt.dom.client.NodeList;
import com.google.gwt.editor.client.Editor.Path;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.user.client.ui.RootPanel;
import com.sencha.gxt.cell.core.client.form.SpinnerFieldCell;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import com.sencha.gxt.theme.base.client.field.TwinTriggerFieldDefaultAppearance;
import com.sencha.gxt.widget.core.client.CoreBaseTestCase;
import com.sencha.gxt.widget.core.client.form.NumberPropertyEditor.DoublePropertyEditor;
import com.sencha.gxt.widget.core.client.form.NumberPropertyEditor.IntegerPropertyEditor;

public class GwtTestSpinnerField extends CoreBaseTestCase {

  public class TestModel {

    private int key;

    public TestModel(int key) {
      this.setKey(key);
    }

    public int getKey() {
      return key;
    }

    public void setKey(int key) {
      this.key = key;
    }

  }

  public interface TestModelProperties extends PropertyAccess<TestModel> {

    ValueProvider<TestModel, Integer> key();

    @Path("key")
    ModelKeyProvider<TestModel> keyProvider();

  }
  
  public void testDisabled() {
    SpinnerField<Integer> field = new SpinnerField<Integer>(new IntegerPropertyEditor());
    
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

  public void testReadOnly() {
    SpinnerField<Integer> textArea = new SpinnerField<Integer>(new IntegerPropertyEditor());
    RootPanel.get().add(textArea);

    NodeList<Element> inputs;
    inputs = textArea.getElement().select("input");
    assertTrue(inputs.getLength() > 0);
    for (int i = 0; i < inputs.getLength(); i++) {
      assertFalse(inputs.getItem(i).hasAttribute("readonly"));
    }

    textArea.setReadOnly(true);
    inputs = textArea.getElement().select("input");
    assertTrue(inputs.getLength() > 0);
    for (int i = 0; i < inputs.getLength(); i++) {
      assertTrue(inputs.getItem(i).hasAttribute("readonly"));
    }

    textArea.redraw();
    inputs = textArea.getElement().select("input");
    assertTrue(inputs.getLength() > 0);
    for (int i = 0; i < inputs.getLength(); i++) {
      assertTrue(inputs.getItem(i).hasAttribute("readonly"));
    }
  }

  public void testSpinnerCell() {
    SpinnerFieldCell<Integer> cell = new SpinnerFieldCell<Integer>(new IntegerPropertyEditor());

    SpinnerField<Integer> field = new SpinnerField<Integer>(cell);
    field.setValue(10);
    RootPanel.get().add(field);

    assertEquals(field.getValue().doubleValue(), 10d);
  }

  public void testSpinnerField() {
    SpinnerField<Integer> field = new SpinnerField<Integer>(new IntegerPropertyEditor());
    field.setValue(10);
    RootPanel.get().add(field);

    assertEquals(field.getValue().doubleValue(), 10d);
  }

  public void testSpinnerPropertyEditor() {
    SpinnerFieldCell<Double> cell = new SpinnerFieldCell<Double>(new DoublePropertyEditor());

    SpinnerField<Double> field = new SpinnerField<Double>(cell);
    field.setValue(5d);
    field.setIncrement(.1d);
    field.getPropertyEditor().setFormat(NumberFormat.getFormat("0.0"));

    RootPanel.get().add(field);

    TwinTriggerFieldDefaultAppearance appearance = (TwinTriggerFieldDefaultAppearance) cell.getAppearance();
    String selector = "." + appearance.getStyle().trigger();

    field.getElement().selectNode(selector).click();

    assertEquals(field.getCurrentValue().doubleValue(), 5.1d);

  }

  public void testSpinnerSpin() {
    SpinnerFieldCell<Integer> cell = new SpinnerFieldCell<Integer>(new IntegerPropertyEditor());

    SpinnerField<Integer> field = new SpinnerField<Integer>(cell);
    field.setValue(10);

    RootPanel.get().add(field);

    TwinTriggerFieldDefaultAppearance appearance = (TwinTriggerFieldDefaultAppearance) cell.getAppearance();
    String selector = "." + appearance.getStyle().trigger();
    String twinSelector = "." + appearance.getStyle().twinTrigger();

    field.getElement().selectNode(selector).click();

    assertEquals(field.getCurrentValue().doubleValue(), 11d);

    field.getElement().selectNode(twinSelector).click();
    field.getElement().selectNode(twinSelector).click();

    assertEquals(field.getCurrentValue().doubleValue(), 9d);
  }

  public void testSpinnerSpinMinMax() {
    SpinnerFieldCell<Integer> cell = new SpinnerFieldCell<Integer>(new IntegerPropertyEditor());

    SpinnerField<Integer> field = new SpinnerField<Integer>(cell);
    field.setMaxValue(10);
    field.setMinValue(0);
    field.setValue(10);

    RootPanel.get().add(field);

    TwinTriggerFieldDefaultAppearance appearance = (TwinTriggerFieldDefaultAppearance) cell.getAppearance();
    String selector = "." + appearance.getStyle().trigger();
    String twinSelector = "." + appearance.getStyle().twinTrigger();

    assertEquals(field.getValue().doubleValue(), 10d);
    field.getElement().selectNode(selector).click();

    assertEquals(field.getValue().doubleValue(), 10d);

    field.setValue(0);

    field.getElement().selectNode(twinSelector).click();

    assertEquals(field.getValue().doubleValue(), 0d);
  }
}
