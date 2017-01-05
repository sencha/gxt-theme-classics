package com.sencha.gxt.fx.client;

import junit.framework.Test;

import com.google.gwt.junit.tools.GWTTestSuite;
import com.sencha.gxt.fx.client.animation.FxAnimationTestSuite;
import com.sencha.gxt.fx.client.easing.FxEasingTestSuite;

public class FxTestSuite {

  public static Test suite() {
    GWTTestSuite suite = new GWTTestSuite("Fx test suite");
    suite.addTestSuite(GwtTestDraggable.class);
    suite.addTestSuite(GwtTestResizable.class);
    suite.addTestSuite(GwtTestShim.class);
    suite.addTest(FxAnimationTestSuite.suite());
    suite.addTest(FxEasingTestSuite.suite());
    return suite;
  }
  
}
