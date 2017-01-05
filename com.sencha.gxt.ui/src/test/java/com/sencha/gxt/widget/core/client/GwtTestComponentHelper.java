package com.sencha.gxt.widget.core.client;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.NodeList;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.core.client.dom.XElement;
import com.sencha.gxt.widget.core.client.button.TextButton;

public class GwtTestComponentHelper extends CoreBaseTestCase {

  public void testAttachDetach() {
    Component component = createEmptyComponent();

    ComponentHelper.doAttach(component);
    assertTrue(component.isAttached());

    ComponentHelper.doDetach(component);
    assertFalse(component.isAttached());
  }

  public void testGetWidgetIdFromComponent() {
    Component component = createEmptyComponent();
    String id = component.getId();
    assertEquals(ComponentHelper.getWidgetId(component), id);
  }

  public void testGetWidgetIdFromNull() {
    try {
      ComponentHelper.getWidgetId(null);
      fail("Should not be possible to get an ID for a null widget");
    } catch (Throwable t) {
      // Expected; test succeeded
    }
  }

  public void testGetWidgetIdFromWidgetWithId() {
    Widget widget = createEmptyWidget();
    widget.getElement().setId("foo");
    assertEquals(ComponentHelper.getWidgetId(widget), "foo");
  }

  public void testGetWidgetIdFromWidgetWithoutId() {
    Widget widget = createEmptyWidget();
    assertEquals(widget.getElement().getId(), "");
    String widgetId = ComponentHelper.getWidgetId(widget);
    assertNotNull(widgetId);
    assertFalse(widgetId.equals(""));
  }

  public void testSetParent() {
    Component parent = createEmptyComponent();
    Component child = createEmptyComponent();

    ComponentHelper.setParent(parent, child);

    assertEquals(parent, child.getParent());
  }

  private Component createEmptyComponent() {
    return new Component() {
      {
        setElement(Document.get().createDivElement());
      }
    };
  }

  private Widget createEmptyWidget() {
    return new Widget() {
      {
        setElement(Document.get().createDivElement());
      }
    };
  }
  
  public void testGetWidgetWithElement() {
    TextButton btn = new TextButton();
    FlowPanel panel = new FlowPanel();
    panel.add(btn);
    RootPanel.get().add(btn);
    
    NodeList<XElement> children = btn.getElement().select("").cast();
    for (int i = 0; i < children.getLength(); i++){
      assertEquals(btn, ComponentHelper.getWidgetWithElement(children.getItem(i)));
    }
    
    RootPanel.get().clear();
  }

}
