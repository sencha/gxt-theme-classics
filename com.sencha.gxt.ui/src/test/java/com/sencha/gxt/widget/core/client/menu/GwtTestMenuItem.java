package com.sencha.gxt.widget.core.client.menu;

import com.google.gwt.user.client.ui.RootPanel;
import com.sencha.gxt.widget.core.client.CoreBaseTestCase;

public class GwtTestMenuItem extends CoreBaseTestCase {

  public void testMenuItem() {
    Menu menu = new Menu();
    
    MenuItem item = new MenuItem("foo");
    menu.add(item);
    
    RootPanel.get().add(menu);

    assertEquals("foo", item.getText());
  }
  
}
