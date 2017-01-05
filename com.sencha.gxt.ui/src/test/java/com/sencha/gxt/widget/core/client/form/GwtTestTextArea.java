package com.sencha.gxt.widget.core.client.form;

import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.InputElement;
import com.google.gwt.dom.client.NodeList;
import com.google.gwt.dom.client.TextAreaElement;
import com.google.gwt.editor.client.Editor.Path;
import com.google.gwt.user.client.ui.RootPanel;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import com.sencha.gxt.widget.core.client.CoreBaseTestCase;

public class GwtTestTextArea extends CoreBaseTestCase {

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
    TextArea field = new TextArea();
    field.setAllowBlank(false);
    field.setName("foo");

    RootPanel.get().add(field);
    assertFalse(field.validate());
    field.setValue("not empty");
    assertTrue(field.validate());
  }

  public void testAttributes() {
    TextArea area = new TextArea();
    area.setReadOnly(true);
    area.setName("foo");
    area.setValue("some value");

    RootPanel.get().add(area);

    assertEquals("foo", area.getElement().selectNode("textarea").getPropertyString("name"));
    assertTrue(area.getElement().selectNode("textarea").<InputElement>cast().isReadOnly());

    area.setReadOnly(false);
    assertFalse(area.getElement().selectNode("textarea").<InputElement>cast().isReadOnly());
  }
  
  public void testDisabled() {
    TextArea field = new TextArea();
    
    InputElement input = field.getElement().selectNode("textarea").cast();
    assertEquals(false, input.isDisabled());
    field.disable();
    assertEquals(true, input.isDisabled());
    
    RootPanel.get().add(field);
    assertEquals(true, input.isDisabled());
    
    field.redraw();
    // input element redrawn
    input = field.getElement().selectNode("textarea").cast();
    assertEquals(true, input.isDisabled());
    
    field.enable();
    assertEquals(false, input.isDisabled());
  }

  public void testInitialValue() {
    TextArea area = new TextArea();
    area.setValue("this is a test");

    RootPanel.get().add(area);

    assertEquals("this is a test", area.getValue());
  }
  
  public void testSizing() {
    TextArea area = new TextArea();
    area.setPixelSize(200, 150);
    
    RootPanel.get().add(area);
    
    assertEquals(150, area.getOffsetHeight());
    assertEquals(200, area.getOffsetWidth());
  }

  public void testReadOnly() {
    TextArea textArea = new TextArea();
    RootPanel.get().add(textArea);

    NodeList<Element> inputs;
    inputs = textArea.getElement().select("textarea");
    assertTrue(inputs.getLength() > 0);
    for (int i = 0; i < inputs.getLength(); i++) {
      assertFalse(inputs.getItem(i).hasAttribute("readonly"));
    }

    textArea.setReadOnly(true);
    inputs = textArea.getElement().select("textarea");
    assertTrue(inputs.getLength() > 0);
    for (int i = 0; i < inputs.getLength(); i++) {
      assertTrue(inputs.getItem(i).hasAttribute("readonly"));
    }

    textArea.redraw();
    inputs = textArea.getElement().select("textarea");
    assertTrue(inputs.getLength() > 0);
    for (int i = 0; i < inputs.getLength(); i++) {
      assertTrue(inputs.getItem(i).hasAttribute("readonly"));
    }
  }

  public void testTabIndex() {
    TextArea tf = new TextArea();
    tf.setTabIndex(123);
    TextAreaElement elt = tf.getElement().selectNode("textarea").<TextAreaElement>cast();
    assertEquals(123, elt.getTabIndex());

    RootPanel.get().add(tf);
    elt = tf.getElement().selectNode("textarea").<TextAreaElement>cast();
    assertEquals(123, elt.getTabIndex());

    tf.setValue("asdf");
    elt = tf.getElement().selectNode("textarea").<TextAreaElement>cast();
    assertEquals(123, elt.getTabIndex());

    tf.setTabIndex(321);
    elt = tf.getElement().selectNode("textarea").<TextAreaElement>cast();
    assertEquals(321, elt.getTabIndex());
  }

}
