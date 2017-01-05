package com.sencha.gxt.widget.core.client.form;

import java.util.Date;

import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.InputElement;
import com.google.gwt.dom.client.NodeList;
import com.google.gwt.editor.client.Editor.Path;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import com.sencha.gxt.widget.core.client.CoreBaseTestCase;

public class GwtTestDateField extends CoreBaseTestCase {

  public class TestModel {

    private Date key;

    public TestModel(Date key) {
      this.key = key;
    }

    public Date getKey() {
      return key;
    }

    public void setKey(Date key) {
      this.key = key;
    }

  }

  public interface TestModelProperties extends PropertyAccess<TestModel> {

    ValueProvider<TestModel, Date> key();

    @Path("key")
    ModelKeyProvider<TestModel> keyProvider();

  }

  public void testAttributes() {
    DateField field = new DateField();
    field.setName("date");
    field.setReadOnly(true);

    field.setValue(new Date());

    RootPanel.get().add(field);

    assertEquals("date", field.getElement().selectNode("input").getPropertyString("name"));
    assertTrue(field.getElement().selectNode("input").<InputElement>cast().isReadOnly());

    field.setReadOnly(false);

    assertFalse(field.getElement().selectNode("input").<InputElement>cast().isReadOnly());
  }

  public void testDateField() {
    DateField field = new DateField();
    field.setValue(new Date());

    RootPanel.get().add(field);
  }

  public void testDisabled() {
    DateField field = new DateField();

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

  @SuppressWarnings("deprecation")
  public void testMinMax() {
    DateField field = new DateField();
    RootPanel.get().add(field);
    field.setMinValue(new Date(2011, 12, 23));
    field.setValue(new Date(2005, 12, 23));
    assertFalse(field.isValid());
    field.setValue(new Date(2012, 12, 23));
    assertTrue(field.isValid());
    field.setMaxValue(new Date(2050, 8, 23));
    field.setValue(new Date(2099, 12, 23));
    assertFalse(field.isValid());
    field.setValue(new Date(2040, 12, 23));
    assertTrue(field.isValid());
  }
  
  @SuppressWarnings("deprecation")
  public void testMinMaxSetThenRemoved() {
    DateField field = new DateField();
    RootPanel.get().add(field);
    field.setMinValue(new Date(2011, 12, 23));
    field.setValue(new Date(2005, 12, 23));
    assertFalse(field.isValid());
    field.setMinValue(null);
    assertTrue(field.isValid());
    field.setMaxValue(new Date(2050, 8, 23));
    field.setValue(new Date(2099, 12, 23));
    assertFalse(field.isValid());
    field.setMaxValue(null);
    assertTrue(field.isValid());
  }

  public void testReadOnly() {
    DateField field = new DateField();
    RootPanel.get().add(field);

    NodeList<Element> inputs;
    inputs = field.getElement().select("input");
    assertTrue(inputs.getLength() > 0);
    for (int i = 0; i < inputs.getLength(); i++) {
      assertFalse(inputs.getItem(i).hasAttribute("readonly"));
    }

    field.setReadOnly(true);
    inputs = field.getElement().select("input");
    assertTrue(inputs.getLength() > 0);
    for (int i = 0; i < inputs.getLength(); i++) {
      assertTrue(inputs.getItem(i).hasAttribute("readonly"));
    }

    field.redraw();
    inputs = field.getElement().select("input");
    assertTrue(inputs.getLength() > 0);
    for (int i = 0; i < inputs.getLength(); i++) {
      assertTrue(inputs.getItem(i).hasAttribute("readonly"));
    }
  }

  public void testDetachedEmptyValidator() {
    DateField field = new DateField();
    field.setAllowBlank(false);

    try {
      // in some browsers (including HtmlUnit) this will fail
      field.validate();
    } catch (Throwable t) {
      fail("Error occured when validating + " + t.getMessage());
      t.printStackTrace();
    }

    FlowPanel parent = new FlowPanel();
    parent.add(field);

    try {
      // in some *other* browsers (IE?), this may fail - even though it is attached, it has no size
      field.validate();
    } catch (Throwable t) {
      fail("Error occured when validating + " + t.getMessage());
      t.printStackTrace();
    }
  }
}
