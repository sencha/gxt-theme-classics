package com.sencha.gxt.widget.core.client.form.validator;

import com.sencha.gxt.widget.core.client.CoreBaseTestCase;

public class GwtTestMinLengthValidator extends CoreBaseTestCase {

  public void testValidator() {
    MinLengthValidator validator = new MinLengthValidator(10);
    
    assertEquals(1, validator.validate(null, "").size());
    assertNull(validator.validate(null, "123456789023"));
  }
}
