package com.sencha.gxt.widget.core.client.tips;

import junit.framework.Test;

import com.google.gwt.junit.tools.GWTTestSuite;

public class TipsTestSuite  {

  public static Test suite() {
    GWTTestSuite suite = new GWTTestSuite("Tool tip test suite");
    suite.addTestSuite(GwtTestToolTip.class);
    suite.addTestSuite(GwtTestQuickTip.class);
    return suite;
  }
  
}
