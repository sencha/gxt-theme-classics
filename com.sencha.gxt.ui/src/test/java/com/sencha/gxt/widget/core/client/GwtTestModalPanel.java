package com.sencha.gxt.widget.core.client;

import com.sencha.gxt.widget.core.client.button.TextButton;

public class GwtTestModalPanel extends CoreBaseTestCase {

  public void testModalPanel() {
    ModalPanel panel = new ModalPanel();
    panel.show(new TextButton("Modal"));
    
  }
  
}
