package com.sencha.gxt.widget.core.client.container;

import com.google.gwt.user.client.ui.RootPanel;
import com.sencha.gxt.widget.core.client.CoreBaseTestCase;
import com.sencha.gxt.widget.core.client.button.TextButton;

public class GwtTestInsertContainer extends CoreBaseTestCase {

  public void testInsert() {
    TextButton button1 = new TextButton();
    TextButton button2 = new TextButton();
    TextButton button3 = new TextButton();
    TextButton button4 = new TextButton();

    FlowLayoutContainer container = new FlowLayoutContainer();
    RootPanel.get().add(container);
    
    container.add(button1);
    container.add(button2);
    
    container.insert(button3, 1);
    
    assertEquals(3,container.getWidgetCount());
    assertEquals(1, container.getWidgetIndex(button3));
    assertEquals(2, container.getWidgetIndex(button2));
    assertTrue(button3.isAttached());
    assertEquals(container, button3.getParent());
    
    container.insert(button4, 1);
    container.insert(button4, 0);
    
    assertEquals(4,container.getWidgetCount());
    assertEquals(0, container.getWidgetIndex(button4));
    assertTrue(button4.isAttached());
    assertEquals(container, button4.getParent());
  }
  
}
