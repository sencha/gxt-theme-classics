package com.sencha.gxt.widget.core.client.form.validator;

import com.sencha.gxt.widget.core.client.CoreBaseTestCase;

public class GwtTestMinNumberValidator extends CoreBaseTestCase {

  public void testValidator() {
    MinNumberValidator<Number> validator = new MinNumberValidator<Number>(10);
    
    assertEquals(1, validator.validate(null, 9).size());
    assertNull(validator.validate(null, 11));
  }
}
