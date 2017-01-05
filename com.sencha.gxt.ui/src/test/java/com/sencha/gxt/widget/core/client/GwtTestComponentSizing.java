package com.sencha.gxt.widget.core.client;

import com.google.gwt.dom.client.Document;
import com.google.gwt.user.client.ui.RootPanel;

public class GwtTestComponentSizing extends CoreBaseTestCase {

  public void testSizingInteger() {
    Component component = new Component() {
      {
        setElement(Document.get().createDivElement());
      }
    };

    RootPanel.get().add(component);

    assertTrue(component.isAutoWidth());
    assertTrue(component.isAutoHeight());

    component.setWidth(100);
    assertFalse(component.isAutoWidth());
    assertTrue(component.isAutoHeight());
    assertEquals(100, component.getOffsetWidth());

    component.setHeight(100);
    assertFalse(component.isAutoWidth());
    assertFalse(component.isAutoHeight());
    assertEquals(100, component.getOffsetWidth());
    assertEquals(100, component.getOffsetHeight());

    component.setPixelSize(200, 200);
    assertFalse(component.isAutoWidth());
    assertFalse(component.isAutoHeight());
    assertEquals(200, component.getOffsetWidth());
    assertEquals(200, component.getOffsetHeight());

    component.setPixelSize(-1, 100);
    assertTrue(component.isAutoWidth());
    assertFalse(component.isAutoHeight());
    assertEquals(100, component.getOffsetHeight());

    component.setHeight(200);
    assertTrue(component.isAutoWidth());
    assertFalse(component.isAutoHeight());
    assertEquals(200, component.getOffsetHeight());

    component.setWidth(100);
    assertFalse(component.isAutoWidth());
    assertFalse(component.isAutoHeight());
    assertEquals(100, component.getOffsetWidth());
    assertEquals(200, component.getOffsetHeight());

    component.setPixelSize(-1, -1);
    assertTrue(component.isAutoWidth());
    assertTrue(component.isAutoHeight());
  }

  public void testSizingString() {
    Component component = new Component() {
      {
        setElement(Document.get().createDivElement());
      }
    };

    RootPanel.get().add(component);

    assertTrue(component.isAutoWidth());
    assertTrue(component.isAutoHeight());

    component.setWidth("100px");
    assertFalse(component.isAutoWidth());
    assertTrue(component.isAutoHeight());
    assertEquals(100, component.getOffsetWidth());

    component.setHeight("100px");
    assertFalse(component.isAutoWidth());
    assertFalse(component.isAutoHeight());
    assertEquals(100, component.getOffsetWidth());
    assertEquals(100, component.getOffsetHeight());

    component.setSize("200px", "200px");
    assertFalse(component.isAutoWidth());
    assertFalse(component.isAutoHeight());
    assertEquals(200, component.getOffsetWidth());
    assertEquals(200, component.getOffsetHeight());

    component.setSize(null, "100px");
    assertTrue(component.isAutoWidth());
    assertFalse(component.isAutoHeight());
    assertEquals(100, component.getOffsetHeight());

    component.setHeight("200px");
    assertTrue(component.isAutoWidth());
    assertFalse(component.isAutoHeight());
    assertEquals(200, component.getOffsetHeight());

    component.setWidth("100px");
    assertFalse(component.isAutoWidth());
    assertFalse(component.isAutoHeight());
    assertEquals(100, component.getOffsetWidth());
    assertEquals(200, component.getOffsetHeight());

    component.setSize(null, null);
    assertTrue(component.isAutoWidth());
    assertTrue(component.isAutoHeight());
  }

  public void testSizingMixed() {
    Component component = new Component() {
      {
        setElement(Document.get().createDivElement());
      }
    };

    RootPanel.get().add(component);

    assertTrue(component.isAutoWidth());
    assertTrue(component.isAutoHeight());

    component.setWidth(100);
    assertFalse(component.isAutoWidth());
    assertTrue(component.isAutoHeight());
    assertEquals(100, component.getOffsetWidth());

    component.setHeight("100px");
    assertFalse(component.isAutoWidth());
    assertFalse(component.isAutoHeight());
    assertEquals(100, component.getOffsetWidth());
    assertEquals(100, component.getOffsetHeight());

    component.setSize("200px", "200px");
    assertFalse(component.isAutoWidth());
    assertFalse(component.isAutoHeight());
    assertEquals(200, component.getOffsetWidth());
    assertEquals(200, component.getOffsetHeight());

    component.setPixelSize(-1, 100);
    assertTrue(component.isAutoWidth());
    assertFalse(component.isAutoHeight());
    assertEquals(100, component.getOffsetHeight());

    component.setHeight(200);
    assertTrue(component.isAutoWidth());
    assertFalse(component.isAutoHeight());
    assertEquals(200, component.getOffsetHeight());

    component.setWidth("100px");
    assertFalse(component.isAutoWidth());
    assertFalse(component.isAutoHeight());
    assertEquals(100, component.getOffsetWidth());
    assertEquals(200, component.getOffsetHeight());

    component.setSize(null, null);
    assertTrue(component.isAutoWidth());
    assertTrue(component.isAutoHeight());
  }

}
