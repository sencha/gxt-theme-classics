package com.sencha.gxt.widget.core.client.form.validator;

import com.sencha.gxt.widget.core.client.CoreBaseTestCase;

public class GwtTestRegExValidator extends CoreBaseTestCase {
  private static final String REGEX = "([0-9]|[0-5][0-9])";

  public void testComplexExpression() {
    RegExValidator v = new RegExValidator(REGEX);

    assertNull(v.validate(null, "1"));
    assertNull(v.validate(null, "abc12def"));// this used to pass in dev mode and fail in prod
    assertNull(v.validate(null, "99"));

    assertNotNull(v.validate(null, "aa"));
    
    v.setRegex("^(a|aa)$");
    
    assertNull(v.validate(null, "a"));
    assertNull(v.validate(null, "aa"));
    
    assertNotNull(v.validate(null, "abc"));
 }

}
