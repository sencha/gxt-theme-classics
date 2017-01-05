package com.sencha.gxt.ui.client.data;

import junit.framework.Test;

import com.google.gwt.junit.tools.GWTTestSuite;
import com.sencha.gxt.ui.client.UITestCase;
import com.sencha.gxt.ui.client.data.loader.GwtTestListLoader;

public class DataTestSuite extends UITestCase {

  public static Test suite() {
    GWTTestSuite suite = new GWTTestSuite("Data test suite");

    suite.addTestSuite(GwtTestTreeStore.class);
    suite.addTestSuite(GwtTestListLoader.class);

    return suite;
  }

}
