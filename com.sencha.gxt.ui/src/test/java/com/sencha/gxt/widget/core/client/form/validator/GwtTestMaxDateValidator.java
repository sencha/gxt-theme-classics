package com.sencha.gxt.widget.core.client.form.validator;

import java.util.Date;

import com.sencha.gxt.core.client.util.DateWrapper;
import com.sencha.gxt.widget.core.client.CoreBaseTestCase;

public class GwtTestMaxDateValidator extends CoreBaseTestCase {

  public void testValidator() {
    MaxDateValidator validator = new MaxDateValidator(new Date());
    
    assertEquals(1, validator.validate(null, new Date()).size());
    
    DateWrapper wrap = new DateWrapper();
    wrap = wrap.addDays(-10);
    assertNull(validator.validate(null, wrap.asDate()));

    wrap = new DateWrapper(new Date());

    validator.setMaxDate(wrap.asDate());
    
    wrap = wrap.addSeconds(10);
    assertNull(validator.validate(null, wrap.asDate()));
  }
}
