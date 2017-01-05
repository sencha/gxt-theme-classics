package com.sencha.gxt.widget.core.client.form.validator;

import junit.framework.Test;

import com.google.gwt.junit.tools.GWTTestSuite;

public class ValidatorTestSuite {

  public static Test suite() {
    GWTTestSuite suite = new GWTTestSuite("Validator test suite");
    suite.addTestSuite(GwtTestMinLengthValidator.class);
    suite.addTestSuite(GwtTestMaxLengthValidator.class);
    suite.addTestSuite(GwtTestMinDateValidator.class);
    suite.addTestSuite(GwtTestMinDateValidator.class);
    suite.addTestSuite(GwtTestMinNumberValidator.class);
    suite.addTestSuite(GwtTestMinNumberValidator.class);
    suite.addTestSuite(GwtTestRegExValidator.class);
    return suite;
  }
}
