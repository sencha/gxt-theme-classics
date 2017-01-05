package com.sencha.gxt.core.client.dom.impl;

import junit.framework.Test;

import com.google.gwt.junit.tools.GWTTestSuite;

public class CoreImplTestSuite {

  public static Test suite() {
    GWTTestSuite suite = new GWTTestSuite("Core implementation test suite");
    suite.addTestSuite(GwtTestComputedStyleImpl.class);
    return suite;
  }
}
