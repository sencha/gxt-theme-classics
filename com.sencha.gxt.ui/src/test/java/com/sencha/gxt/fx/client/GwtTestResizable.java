package com.sencha.gxt.fx.client;

import com.google.gwt.user.client.ui.RootPanel;
import com.sencha.gxt.widget.core.client.Resizable;
import com.sencha.gxt.widget.core.client.button.TextButton;

public class GwtTestResizable extends FxBaseTestCase {

  public void testResizable() {
    TextButton button = new TextButton();
    RootPanel.get().add(button);
    new Resizable(button);
  }

}
