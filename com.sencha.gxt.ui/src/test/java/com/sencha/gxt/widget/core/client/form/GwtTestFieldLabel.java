package com.sencha.gxt.widget.core.client.form;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.ui.client.UITestCase;
import com.sencha.gxt.widget.core.client.button.TextButton;

public class GwtTestFieldLabel extends UITestCase {

  @UiTemplate("FieldLabelTestWidget.ui.xml")
  public interface FieldLabelTestWidgetUiBinder extends UiBinder<Widget, FieldLabelTestWidget> {}
  
  class FieldLabelTestWidget extends Composite {
    FieldLabelTestWidgetUiBinder uiBinder = GWT.create(FieldLabelTestWidgetUiBinder.class);
    @UiField
    FieldLabel fieldlabel;
    @UiField
    TextField input;
    public FieldLabelTestWidget() {
      initWidget(uiBinder.createAndBindUi(this));
    }
    public FieldLabel getFieldLabel() {
      return fieldlabel;
    }
    public TextField getInput() {
      return input;
    }
  }
  
  public void testBlankFieldLabel() {
    FieldLabel fl = new FieldLabel();
    RootPanel.get().add(fl);
  }

  public void testFieldLabel() {
    FieldLabel fl = new FieldLabel(new Label("test"), "Label");

    RootPanel.get().add(fl);
    assertTrue(fl.getElement().getInnerText().contains("Label"));
  }

  public void testSeparator() {
    FieldLabel fl = new FieldLabel(new Label("test"), "Label");
    fl.setLabelSeparator("--");

    RootPanel.get().add(fl);
    assertTrue(fl.getElement().getInnerText().contains("--"));
  }

  public void testSeparatorWithSetText() {
    FieldLabel fl = new FieldLabel(new Label("test"), "Label");
    fl.setLabelSeparator("--");
    fl.setText("test2");

    RootPanel.get().add(fl);
    assertTrue(fl.getElement().getInnerText().contains("--"));
  }

  public void testSetWidget() {
    FieldLabel fl = new FieldLabel();

    TextButton button1 = new TextButton("test");
    TextButton button2 = new TextButton("test");

    assertNull(button1.getParent());
    assertNull(button2.getParent());
    assertEquals(0, fl.getWidgetCount());

    fl.setWidget(button1);
    assertEquals(1, fl.getWidgetCount());
    assertEquals(fl, button1.getParent());
    assertNull(button2.getParent());

    fl.setWidget(button2);
    assertEquals(1, fl.getWidgetCount());
    assertEquals(fl, button2.getParent());
    assertNull(button1.getParent());

    fl.clear();
    assertNull(button1.getParent());
    assertNull(button2.getParent());
    assertEquals(0, fl.getWidgetCount());
  }
  
  public void testSetWidgetUiBinder() {
    FieldLabelTestWidget w = new FieldLabelTestWidget();
    
    assertNotNull(w);
    assertNotNull(w.getFieldLabel());
    
    TextField tf = (TextField) w.getFieldLabel().getWidget();
    assertNotNull(tf);
    
    assertNotNull(w.getInput());
  }
  
}
