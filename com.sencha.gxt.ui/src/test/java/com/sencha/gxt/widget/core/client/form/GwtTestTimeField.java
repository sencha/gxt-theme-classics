package com.sencha.gxt.widget.core.client.form;

import java.util.Date;

import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.InputElement;
import com.google.gwt.dom.client.NodeList;
import com.google.gwt.editor.client.Editor.Path;
import com.google.gwt.user.client.ui.RootPanel;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import com.sencha.gxt.widget.core.client.CoreBaseTestCase;

public class GwtTestTimeField extends CoreBaseTestCase {

  public class TestModel {

    private Date key;

    public TestModel(Date key) {
      this.setKey(key);
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

  public void testContainsAll() {
    TimeField t = new TimeField();
    t.setIncrement(1);

    RootPanel.get().add(t);

    t.expand();

    assertEquals(24 * 60, t.getStore().size());
  }
  
  public void testDisabled() {
    TimeField field = new TimeField();
    
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
    TimeField field = new TimeField();
    RootPanel.get().add(field);

    NodeList<Element> inputs = field.getElement().select("input");
    for (int i = 0; i < inputs.getLength(); i++) {
      assertFalse(inputs.getItem(i).hasAttribute("readonly"));
    }

    field.setReadOnly(true);
    for (int i = 0; i < inputs.getLength(); i++) {
      assertTrue(inputs.getItem(i).hasAttribute("readonly"));
    }

    field.redraw();
  }

  public void testTimeField() {
    TimeField field = new TimeField();
    RootPanel.get().add(field);
  }

}
