package com.sencha.gxt.widget.core.client.form;

import junit.framework.Test;

import com.google.gwt.junit.tools.GWTTestSuite;
import com.sencha.gxt.ui.client.UITestCase;

public class FormTestSuite extends UITestCase {

  public static Test suite() {
    GWTTestSuite suite = new GWTTestSuite("Form test suite");

    suite.addTestSuite(GwtTestCheckBox.class);
    suite.addTestSuite(GwtTestCheckBoxGroup.class);
    suite.addTestSuite(GwtTestComboBox.class);
    suite.addTestSuite(GwtTestConverterEditorAdapter.class);
    suite.addTestSuite(GwtTestDateField.class);
    suite.addTestSuite(GwtTestDualListField.class);
    suite.addTestSuite(GwtTestField.class);
    suite.addTestSuite(GwtTestFieldLabel.class);
    suite.addTestSuite(GwtTestFieldSet.class);
    suite.addTestSuite(GwtTestFormPanel.class);
    suite.addTestSuite(GwtTestHtmlEditor.class);
    suite.addTestSuite(GwtTestListField.class);
    suite.addTestSuite(GwtTestNumberField.class);
    suite.addTestSuite(GwtTestPasswordField.class);
    suite.addTestSuite(GwtTestRadio.class);
    suite.addTestSuite(GwtTestSpinnerField.class);
    suite.addTestSuite(GwtTestStringComboBox.class);
    suite.addTestSuite(GwtTestTextArea.class);
    suite.addTestSuite(GwtTestTextField.class);
    suite.addTestSuite(GwtTestTimeField.class);

    return suite;
  }
}
