package com.sencha.gxt.widget.core.client.form.validator;

import com.sencha.gxt.widget.core.client.CoreBaseTestCase;

public class GwtTestMaxLengthValidator extends CoreBaseTestCase {

  public void testValidator() {
    MaxLengthValidator validator = new MaxLengthValidator(10);
    
    assertEquals(1, validator.validate(null, "123456789023").size());
    assertNull(validator.validate(null, ""));
  }
}
