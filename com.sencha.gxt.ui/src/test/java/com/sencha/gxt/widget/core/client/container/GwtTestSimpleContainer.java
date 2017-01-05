package com.sencha.gxt.widget.core.client.container;

import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.CoreBaseTestCase;
import com.sencha.gxt.widget.core.client.button.TextButton;

public class GwtTestSimpleContainer extends CoreBaseTestCase {
  public void testAdd() {
    SimpleContainer c = new SimpleContainer();

    TextButton button1 = new TextButton("test");
    TextButton button2 = new TextButton("test");

    assertNull(button1.getParent());
    assertNull(button2.getParent());
    assertEquals(0, c.getWidgetCount());

    c.add(button1);
    assertEquals(1, c.getWidgetCount());
    assertEquals(c, button1.getParent());
    assertNull(button2.getParent());

    c.add(button2);
    assertEquals(1, c.getWidgetCount());
    assertEquals(c, button2.getParent());
    assertNull(button1.getParent());

    c.clear();
    assertNull(button1.getParent());
    assertNull(button2.getParent());
    assertEquals(0, c.getWidgetCount());

  }

  public void testSetWidget() {
    SimpleContainer c = new SimpleContainer();

    TextButton button1 = new TextButton("test");
    TextButton button2 = new TextButton("test");

    assertNull(button1.getParent());
    assertNull(button2.getParent());
    assertEquals(0, c.getWidgetCount());

    c.setWidget(button1);
    assertEquals(1, c.getWidgetCount());
    assertEquals(c, button1.getParent());
    assertNull(button2.getParent());

    c.setWidget(button2);
    assertEquals(1, c.getWidgetCount());
    assertEquals(c, button2.getParent());
    assertNull(button1.getParent());

    c.clear();
    assertNull(button1.getParent());
    assertNull(button2.getParent());
    assertEquals(0, c.getWidgetCount());
  }

  public void testAddRemove() {
    SimpleContainer container = new SimpleContainer();
    TextButton button = new TextButton();

    container.add(button);
    container.setWidget(button);
    assertEquals(button, container.getWidget());
    for (Widget widget : container) {
      assertNotNull(widget);
    }

    assertTrue(container.remove(button));
    assertFalse(container.remove(button));
    assertNull(container.getWidget());
  }

  public void testMultipleWidgets() {
    SimpleContainer container = new SimpleContainer();
    TextButton button1 = new TextButton();
    TextButton button2 = new TextButton();

    container.add(button1);
    assertEquals(container, button1.getParent());
    assertNull(button2.getParent());

    container.add(button2);
    assertEquals(container, button2.getParent());
    assertNull(button1.getParent());
  }
}
