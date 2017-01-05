package com.sencha.gxt.widget.core.client.info;

import junit.framework.Test;

import com.google.gwt.junit.tools.GWTTestSuite;

public class InfoTestSuite {

  public static Test suite() {
    GWTTestSuite suite = new GWTTestSuite("Info test suite");
    suite.addTestSuite(GwtTestInfo.class);
    return suite;
  }
  
}
