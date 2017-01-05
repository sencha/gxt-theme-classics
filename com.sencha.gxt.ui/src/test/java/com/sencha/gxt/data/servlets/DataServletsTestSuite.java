package com.sencha.gxt.data.servlets;

import junit.framework.Test;

import com.google.gwt.junit.tools.GWTTestSuite;
import com.sencha.gxt.data.servlets.client.GwtTestHttpProxy;
import com.sencha.gxt.data.servlets.client.GwtTestRpcProxy;
import com.sencha.gxt.data.servlets.client.GwtTestScriptTagProxy;
import com.sencha.gxt.data.servlets.client.GwtTestSortInfo;
import com.sencha.gxt.data.servlets.client.loader.GwtTestListLoadConfig;

public class DataServletsTestSuite {
  public static Test suite() {
    GWTTestSuite suite = new GWTTestSuite("Data integration test suite, including servlets");
    suite.addTestSuite(GwtTestHttpProxy.class);
    suite.addTestSuite(GwtTestScriptTagProxy.class);
    suite.addTestSuite(GwtTestRpcProxy.class);
    suite.addTestSuite(GwtTestSortInfo.class);
    suite.addTestSuite(GwtTestListLoadConfig.class);

    return suite;
  }
}
