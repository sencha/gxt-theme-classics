package com.sencha.gxt.widget.core.client.container;

import com.google.gwt.user.client.ui.RootPanel;
import com.sencha.gxt.widget.core.client.CoreBaseTestCase;


public class GwtTestViewport extends CoreBaseTestCase {

  public void testContainer() {
    Viewport viewport = new Viewport();
    
    final SimpleContainer con = new SimpleContainer();
    viewport.add(con);
    
    RootPanel.get().add(viewport);
    
  }
  
}
