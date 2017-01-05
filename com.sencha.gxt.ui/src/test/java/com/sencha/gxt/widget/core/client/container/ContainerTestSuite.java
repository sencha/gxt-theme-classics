package com.sencha.gxt.widget.core.client.container;

import junit.framework.Test;

import com.google.gwt.junit.tools.GWTTestSuite;

public class ContainerTestSuite {

  public static Test suite() {
    GWTTestSuite suite = new GWTTestSuite("Container test suite");
    suite.addTestSuite(GwtTestCenterLayoutContainer.class);
    suite.addTestSuite(GwtTestContainer.class);
    suite.addTestSuite(GwtTestBorderLayoutContainer.class);
    suite.addTestSuite(GwtTestCardLayoutContainer.class);
    suite.addTestSuite(GwtTestInsertContainer.class);
    suite.addTestSuite(GwtTestFlowLayoutContainer.class);
    suite.addTestSuite(GwtTestAccordionLayoutContainer.class);
    suite.addTestSuite(GwtTestCssFloatLayoutContainer.class);
    suite.addTestSuite(GwtTestBoxLayoutContainer.class);
    suite.addTestSuite(GwtTestHBoxLayoutContainer.class);
    suite.addTestSuite(GwtTestHorizontalLayoutContainer.class);
    suite.addTestSuite(GwtTestPortalLayoutContainer.class);
    suite.addTestSuite(GwtTestVBoxLayoutContainer.class);
    suite.addTestSuite(GwtTestHtmlLayoutContainer.class);
    suite.addTestSuite(GwtTestCenterLayoutContainer.class);
    suite.addTestSuite(GwtTestVerticalLayoutContainer.class);
    suite.addTestSuite(GwtTestNorthSouthContainer.class);
    suite.addTestSuite(GwtTestPortalLayoutContainer.class);
    suite.addTestSuite(GwtTestViewport.class);
    suite.addTestSuite(GwtTestSimpleContainer.class);
    return suite;
  }
}