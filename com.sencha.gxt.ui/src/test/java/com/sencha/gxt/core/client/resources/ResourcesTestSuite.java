package com.sencha.gxt.core.client.resources;

import com.google.gwt.junit.tools.GWTTestSuite;
import junit.framework.Test;

public class ResourcesTestSuite {
  public static Test suite() {
    GWTTestSuite suite = new GWTTestSuite("Core resource types test suite");
    suite.addTestSuite(GwtTestStyleInjectorHelper.class);
    return suite;
  }
}
