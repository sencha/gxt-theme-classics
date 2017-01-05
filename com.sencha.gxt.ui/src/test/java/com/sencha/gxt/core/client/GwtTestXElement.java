package com.sencha.gxt.core.client;

import com.google.gwt.dom.client.Document;
import com.google.gwt.junit.DoNotRunWith;
import com.google.gwt.junit.Platform;
import com.google.gwt.user.client.ui.Button;
import com.sencha.gxt.core.shared.CoreBaseTestCase;
import com.sencha.gxt.core.client.dom.XElement;
import com.sencha.gxt.core.client.dom.XElement.VisMode;

public class GwtTestXElement extends CoreBaseTestCase {

  public void testAs() {
    Button btn = new Button();
    XElement element = XElement.as(btn.getElement());
    assertNotNull(element);
  }

  public void testToggleClassName() {
    XElement elem = XElement.createElement("div");
    assertNotNull(elem);
    assertFalse(elem.hasClassName("foo"));
    elem.toggleClassName("foo");
    assertTrue(elem.hasClassName("foo"));
  }

  public void testVisMode() {
    XElement elem = XElement.createElement("div");
    assertNotNull(elem);

    assertNull(elem.getVisMode());

    elem.setVisMode(VisMode.DISPLAY);
    assertEquals(VisMode.DISPLAY, elem.getVisMode());

    elem.setVisMode(VisMode.VISIBILITY);
    elem.setVisible(false);

    assertEquals("hidden", elem.getStyle().getVisibility());
  }

  @DoNotRunWith(Platform.HtmlUnitLayout)
  public void testXY() {
    XElement elem = XElement.createElement("div");
    elem.getStyle().setProperty("border", "1px solid black");
    elem.setXY(100, 100);

    Document.get().getBody().appendChild(elem);

    assertEquals(100, elem.getX());
    assertEquals(100, elem.getY());
  }

}
