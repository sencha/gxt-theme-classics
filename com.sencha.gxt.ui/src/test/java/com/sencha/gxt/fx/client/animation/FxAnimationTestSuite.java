package com.sencha.gxt.fx.client.animation;

import junit.framework.Test;

import com.google.gwt.junit.tools.GWTTestSuite;
import com.sencha.gxt.ui.client.UITestCase;

public class FxAnimationTestSuite extends UITestCase {

  public static Test suite() {
    GWTTestSuite suite = new GWTTestSuite("Animation test suite");
    suite.addTestSuite(GwtTestBaseEffect.class);
    suite.addTestSuite(GwtTestBlink.class);
    suite.addTestSuite(GwtTestFadeIn.class);
    suite.addTestSuite(GwtTestFadeOut.class);
    suite.addTestSuite(GwtTestFx.class);
    suite.addTestSuite(GwtTestMove.class);
    suite.addTestSuite(GwtTestMultiEffect.class);
    suite.addTestSuite(GwtTestScroll.class);
    suite.addTestSuite(GwtTestSingleStyleEffect.class);
    suite.addTestSuite(GwtTestSlideIn.class);
    suite.addTestSuite(GwtTestSlideOut.class);
    return suite;
  }
  
}
