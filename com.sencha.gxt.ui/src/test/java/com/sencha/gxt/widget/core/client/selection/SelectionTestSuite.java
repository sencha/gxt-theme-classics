package com.sencha.gxt.widget.core.client.selection;

import junit.framework.Test;

import com.google.gwt.junit.tools.GWTTestSuite;
import com.sencha.gxt.ui.client.UITestCase;

public class SelectionTestSuite extends UITestCase {

  public static Test suite() {
    GWTTestSuite suite = new GWTTestSuite("Selection test suite");
    suite.addTestSuite(GwtTestAbstractStoreSelectionModel.class);
    return suite;
  }
  
}
