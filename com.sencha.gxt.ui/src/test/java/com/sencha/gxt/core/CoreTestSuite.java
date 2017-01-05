package com.sencha.gxt.core;

import com.google.gwt.junit.tools.GWTTestSuite;
import com.sencha.gxt.core.client.GwtTestDomHelper;
import com.sencha.gxt.core.client.GwtTestDomQuery;
import com.sencha.gxt.core.client.GwtTestXElement;
import com.sencha.gxt.core.client.dom.impl.CoreImplTestSuite;
import com.sencha.gxt.core.client.resources.ResourcesTestSuite;
import com.sencha.gxt.core.client.util.UtilTestSuite;
import com.sencha.gxt.core.shared.GwtTestExpandedHtmlSanitizer;
import junit.framework.Test;

public class CoreTestSuite  {

  public static Test suite() {
    GWTTestSuite suite = new GWTTestSuite("Core test suite");

    // client tests
    suite.addTestSuite(GwtTestDomHelper.class);
    suite.addTestSuite(GwtTestXElement.class);
    suite.addTestSuite(GwtTestDomQuery.class);
    suite.addTest(CoreImplTestSuite.suite());
    suite.addTest(UtilTestSuite.suite());
    suite.addTest(ResourcesTestSuite.suite());

    // shared tests
    suite.addTestSuite(GwtTestExpandedHtmlSanitizer.class);
    
    return suite;
  }
  
}
