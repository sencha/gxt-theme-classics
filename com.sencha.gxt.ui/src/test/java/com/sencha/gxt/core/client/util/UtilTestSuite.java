package com.sencha.gxt.core.client.util;

import junit.framework.Test;

import com.google.gwt.junit.tools.GWTTestSuite;
import com.sencha.gxt.core.shared.CoreBaseTestCase;

public class UtilTestSuite extends CoreBaseTestCase {

  public static Test suite() {
    GWTTestSuite suite = new GWTTestSuite("Util test suite");
    suite.addTestSuite(GwtTestDelayedTask.class);
    suite.addTestSuite(GwtTestFormat.class);
    suite.addTestSuite(GwtTestTextMetrics.class);
    suite.addTestSuite(GwtTestUtil.class);
    suite.addTestSuite(TestDateWrapper.class);
    suite.addTestSuite(TestMargins.class);
    suite.addTestSuite(TestPadding.class);
    suite.addTestSuite(TestPoint.class);
    suite.addTestSuite(TestRectangle.class);
    suite.addTestSuite(TestRegion.class);
    suite.addTestSuite(TestSize.class);
    return suite;
  }

}
