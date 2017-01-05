package com.sencha.gxt.widget.core.client.menu;

import junit.framework.Test;

import com.google.gwt.junit.tools.GWTTestSuite;

public class MenuTestSuite {

  public static Test suite() {
    GWTTestSuite suite = new GWTTestSuite("Menu test suite");
    suite.addTestSuite(GwtTestMenu.class);
    suite.addTestSuite(GwtTestMenuItem.class);
    suite.addTestSuite(GwtTestCheckMenuItem.class);
    suite.addTestSuite(GwtTestSeparatorMenuItem.class);
    return suite;
  }
}
