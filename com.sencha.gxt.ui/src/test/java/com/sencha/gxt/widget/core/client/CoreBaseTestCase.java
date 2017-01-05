package com.sencha.gxt.widget.core.client;

import com.google.gwt.junit.client.GWTTestCase;
import com.google.gwt.user.client.ui.RootPanel;

public abstract class CoreBaseTestCase extends GWTTestCase {

  public String getModuleName() {
    return "com.sencha.gxt.widget.core.CoreTest";
  }

  @Override
  protected void gwtTearDown() throws Exception {
    RootPanel.get().clear();
  }

}
