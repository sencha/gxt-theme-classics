package com.sencha.gxt.fx.client.easing;

import junit.framework.Test;

import com.google.gwt.junit.tools.GWTTestSuite;
import com.sencha.gxt.ui.client.UITestCase;

public class FxEasingTestSuite extends UITestCase {

  public static Test suite() {
    GWTTestSuite suite = new GWTTestSuite("Easing test suite");
    suite.addTestSuite(TestEasing.class);
    return suite;
  }
  
}
