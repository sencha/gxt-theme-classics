package com.sencha.gxt.widget.core.client.form.validator;

import com.sencha.gxt.widget.core.client.CoreBaseTestCase;

public class GwtTestMaxNumberValidator extends CoreBaseTestCase {

  public void testValidator() {
    MaxNumberValidator<Number> validator = new MaxNumberValidator<Number>(10);
    
    assertEquals(1, validator.validate(null, 11).size());
    assertNull(validator.validate(null, 9));
  }
}
