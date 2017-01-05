package com.sencha.gxt.widget.core.client.form;

import java.text.ParseException;

import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.InputElement;
import com.google.gwt.dom.client.NodeList;
import com.google.gwt.editor.client.Editor.Path;
import com.google.gwt.user.client.ui.RootPanel;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.core.client.dom.XElement;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import com.sencha.gxt.widget.core.client.CoreBaseTestCase;
import com.sencha.gxt.widget.core.client.form.validator.MaxLengthValidator;
import com.sencha.gxt.widget.core.client.form.validator.MinLengthValidator;
import com.sencha.gxt.widget.core.client.form.validator.RegExValidator;

public class GwtTestTextField extends CoreBaseTestCase {

  public class TestModel {

    private String key;

    public TestModel(String key) {
      this.setKey(key);
    }

    public String getKey() {
      return key;
    }

    public void setKey(String key) {
      this.key = key;
    }

  }

  public interface TestModelProperties extends PropertyAccess<TestModel> {

    ValueProvider<TestModel, String> key();

    @Path("key")
    ModelKeyProvider<TestModel> keyProvider();

  }
  
  public void testAllowBlank() {
    TextField textField = null;

    textField = new TextField();
    textField.setAllowBlank(false);

    RootPanel.get().add(textField);
    assertFalse(textField.validate());
    textField.setValue("not empty");
    assertTrue(textField.validate());
  }

  public void testAttributes() {
    TextField text = new TextField();
    text.setValue("foo");
    text.setId("10");
    text.setName("address");

    InputElement input = text.getCell().getInputElement(text.getElement().<XElement>cast());
    assertEquals("address", input.getName());
    assertEquals("10-input", input.getId());

    RootPanel.get().add(text);

    text.redraw();

    input = text.getCell().getInputElement(text.getElement().<XElement>cast());
    assertEquals("address", input.getName());
    assertEquals("10-input", input.getId());


    text.setReadOnly(true);
    assertTrue(text.getElement().selectNode("input").<InputElement>cast().isReadOnly());
  }

  public void testDisabled() {
    TextField field = new TextField();

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

  public void testEmptyText() {
    TextField textField = new TextField();
    textField.setEmptyText("empty");

    assertEquals("empty", textField.getElement().selectNode("input").getPropertyString("placeholder"));

    RootPanel.get().add(textField);

    textField.redraw();

    assertEquals("empty", textField.getElement().selectNode("input").getPropertyString("placeholder"));
  }

  public void testMaxLength() {
    TextField text = new TextField();
    text.addValidator(new MaxLengthValidator(5));
    RootPanel.get().add(text);

    assertTrue(text.isValid());
    text.setValue("123456");
    assertTrue(!text.isValid());
  }

  public void testMinLength() {
    TextField text = new TextField();
    text.addValidator(new MinLengthValidator(5));
    RootPanel.get().add(text);

    assertTrue(text.isValid());
    text.setValue("1234");
    assertTrue(!text.isValid());
    text.setValue("123456");
    assertTrue(text.isValid());
  }

  public void testName() {
    TextField f = new TextField();
    f.setName("name");
    f.setValue("foo");
    RootPanel.get().add(f);

    assertEquals("name", f.getElement().selectNode("input").getPropertyString("name"));
  }

  public void testReadOnly() {
    TextField field = new TextField();
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

  public void testRedraw() {
    TextField text = new TextField();
    text.setValue("foo");

    RootPanel.get().add(text);

    text.redraw();

    assertEquals("foo", text.getCurrentValue());
  }

  public void testRegex() {
    TextField textField = new TextField();
    textField.addValidator(new RegExValidator("foo"));

    RootPanel.get().add(textField);

    assertTrue(textField.validate());
    textField.setValue("foo");
    assertTrue(textField.validate());
    textField.setValue("bar");
    assertFalse(textField.validate());
  }

  public void testSelect() {
    TextField text = new TextField();
    RootPanel.get().add(text);

    text.setValue("this is a test");
    text.select(1, 6);

    String selected = text.getSelectedText();
    assertEquals("his is", selected);
  }

  public void testSelection() {
    TextField text = new TextField();
    RootPanel.get().add(text);

    text.setValue("this is a test");
    text.setSelectionRange(2, 4);

    String selected = text.getSelectedText();
    assertEquals("is i", selected);
  }

  public void testSizing() {
    TextField text = new TextField();
    text.setWidth(150);
    
    RootPanel.get().add(text);
    
    assertEquals(150, text.getOffsetWidth());
  }

  public void testTabIndex() {
    TextField tf = new TextField();
    tf.setTabIndex(123);
    InputElement elt = tf.getElement().selectNode("input").<InputElement>cast();
    assertEquals(123, elt.getTabIndex());

    RootPanel.get().add(tf);
    elt = tf.getElement().selectNode("input").<InputElement>cast();
    assertEquals(123, elt.getTabIndex());

    tf.setValue("asdf");
    elt = tf.getElement().selectNode("input").<InputElement>cast();
    assertEquals(123, elt.getTabIndex());

    tf.setTabIndex(321);
    elt = tf.getElement().selectNode("input").<InputElement>cast();
    assertEquals(321, elt.getTabIndex());
  }

  public void testPropertyEditorRender() {
    TextField text = new TextField();
    text.setPropertyEditor(new PropertyEditor<String>() {
      @Override
      public String parse(CharSequence text) throws ParseException {
        return String.valueOf(text);
      }

      @Override
      public String render(String object) {
        return object + "bbbb";
      }
    });
    RootPanel.get().add(text);

    text.setValue("aaaa");

    assertEquals("aaaabbbb", text.getCurrentValue());
  }

}
