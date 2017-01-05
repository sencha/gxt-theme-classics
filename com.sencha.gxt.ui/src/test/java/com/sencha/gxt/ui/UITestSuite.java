package com.sencha.gxt.ui;

import com.google.gwt.junit.tools.GWTTestSuite;
import com.sencha.gxt.ui.client.GwtTestAdapterFieldEditor;
import com.sencha.gxt.ui.client.GwtTestCheckboxEditor;
import com.sencha.gxt.ui.client.GwtTestGXT;
import com.sencha.gxt.ui.client.GwtTestTextFieldEditor;
import com.sencha.gxt.ui.client.data.DataTestSuite;
import com.sencha.gxt.ui.client.toggle.GwtTestToggleGroup;
import junit.framework.Test;

public class UITestSuite {

  public static Test suite() {
    GWTTestSuite suite = new GWTTestSuite("GXT general test suite.");

    suite.addTest(DataTestSuite.suite());
    suite.addTestSuite(GwtTestAdapterFieldEditor.class);
    suite.addTestSuite(GwtTestCheckboxEditor.class);
    suite.addTestSuite(GwtTestGXT.class);
    suite.addTestSuite(GwtTestTextFieldEditor.class);
    suite.addTestSuite(GwtTestToggleGroup.class);
    suite.addTestSuite(TestMockUtils.class);

    return suite;
  }
}
