package com.sencha.gxt.widget.core.client.button;

import junit.framework.Test;

import com.google.gwt.junit.tools.GWTTestSuite;

public class ButtonTestSuite  {

  public static Test suite() {
    GWTTestSuite suite = new GWTTestSuite("Button test suite");
    suite.addTestSuite(GwtTestButton.class);
    suite.addTestSuite(GwtTestButtonBar.class);
    suite.addTestSuite(GwtTestIconButton.class);
    suite.addTestSuite(GwtTestSplitButton.class);
    suite.addTestSuite(GwtTestToggleButton.class);
    suite.addTestSuite(GwtTestToolButton.class);
    return suite;
  }
}
