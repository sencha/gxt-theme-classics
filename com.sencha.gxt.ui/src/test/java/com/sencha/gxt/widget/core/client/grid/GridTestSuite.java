package com.sencha.gxt.widget.core.client.grid;

import junit.framework.Test;

import com.google.gwt.junit.tools.GWTTestSuite;
import com.sencha.gxt.ui.client.UITestCase;

public class GridTestSuite extends UITestCase {

  public static Test suite() {
    GWTTestSuite suite = new GWTTestSuite("Grid test suite");
    suite.addTestSuite(GwtTestAbstractGridFilters.class);
    suite.addTestSuite(GwtTestAggRowConfig.class);
    suite.addTestSuite(GwtTestCheckBoxSelectionModel.class);
    suite.addTestSuite(GwtTestFilterGrid.class);
    suite.addTestSuite(GwtTestGrid.class);
    suite.addTestSuite(GwtTestGroupingView.class);
    suite.addTestSuite(GwtTestGroupSummaryView.class);
    suite.addTestSuite(GwtTestHeaderConfig.class);
    suite.addTestSuite(GwtTestDateFilter.class);

    return suite;
  }
}
