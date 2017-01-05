package com.sencha.gxt.cell.core;

import com.sencha.gxt.widget.core.client.form.GwtTestStringComboBox;
import junit.framework.Test;

import com.google.gwt.junit.tools.GWTTestSuite;
import com.sencha.gxt.cell.core.client.form.GwtTestComboBoxCell;

public class CellTestSuite {
  public static Test suite() {
    GWTTestSuite suite = new GWTTestSuite("Cell unit test suite");
    // client
    suite.addTestSuite(GwtTestComboBoxCell.class);
    suite.addTestSuite(GwtTestStringComboBox.class);
    return suite;
  }
}
