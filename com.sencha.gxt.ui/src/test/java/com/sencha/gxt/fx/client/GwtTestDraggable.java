package com.sencha.gxt.fx.client;

import com.google.gwt.user.client.ui.RootPanel;
import com.sencha.gxt.core.client.dom.XElement;
import com.sencha.gxt.widget.core.client.button.TextButton;

public class GwtTestDraggable extends FxBaseTestCase {

  public void testProxy() {
    TextButton button = new TextButton();
    RootPanel.get().add(button);
    Draggable drag = new Draggable(button);
    
    XElement proxy = drag.createProxy();
    assertFalse(proxy.isVisible());
  }
  
}
