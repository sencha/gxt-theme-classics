package com.sencha.gxt;

import junit.framework.Test;

import com.google.gwt.junit.tools.GWTTestSuite;
import com.sencha.gxt.cell.core.CellTestSuite;
import com.sencha.gxt.core.CoreTestSuite;
import com.sencha.gxt.data.DataTestSuite;
import com.sencha.gxt.fx.client.FxTestSuite;
import com.sencha.gxt.messages.MessagesSuite;
import com.sencha.gxt.ui.UITestSuite;
import com.sencha.gxt.widget.core.client.WidgetTestSuite;
import com.sencha.gxt.xtemplates.XTemplatesTestSuite;

public class GXTTestSuite {
  public static Test suite() {
    GWTTestSuite suite = new GWTTestSuite("GXT Root Test Suite");
    suite.addTest(CellTestSuite.suite());
    suite.addTest(CoreTestSuite.suite());
    suite.addTest(DataTestSuite.suite());
    suite.addTest(FxTestSuite.suite());
    
    suite.addTest(MessagesSuite.suite());

    // TODO this is for the ui package, and needs to be removed when its tests
    // are repackaged where they belong
    suite.addTest(UITestSuite.suite());

    suite.addTest(WidgetTestSuite.suite());
    suite.addTest(XTemplatesTestSuite.suite());

    return suite;
  }
}
