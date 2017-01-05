package com.sencha.gxt.widget.core.client.tree;

import junit.framework.Test;

import com.google.gwt.junit.tools.GWTTestSuite;

public class TreeTestSuite {

  public static Test suite() {
    GWTTestSuite suite = new GWTTestSuite("Tree test suite");
    suite.addTestSuite(GwtTestCheckBoxTree.class);
    suite.addTestSuite(GwtTestTree.class);
    suite.addTestSuite(GwtTestTreeView.class);
    suite.addTestSuite(GwtTestTreeGrid.class);
    return suite;
  }
}
