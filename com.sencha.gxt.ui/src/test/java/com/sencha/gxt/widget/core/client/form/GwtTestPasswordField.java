package com.sencha.gxt.widget.core.client.form;

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

public class GwtTestPasswordField extends CoreBaseTestCase {

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

  public void testDisabled() {
    PasswordField field = new PasswordField();
    
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

  public void testField() {
    PasswordField field = new PasswordField();
    field.setValue("test");

    RootPanel.get().add(field);

    assertEquals("test", field.getValue());
  }
  
  public void testReadOnly() {
    PasswordField field = new PasswordField();
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

  public void testReadOnlyField() {
    PasswordField field = new PasswordField();
    field.setReadOnly(true);

    RootPanel.get().add(field);

    NodeList<Element> items = XElement.as(field.getElement()).select("input");
    for (int i = 0; i < items.getLength(); i++) {
      assertTrue(items.getItem(i).hasAttribute("readonly"));
    }
  }

}
