package com.sencha.gxt.widget.core.client.form.validator;

import java.util.Date;

import com.sencha.gxt.core.client.util.DateWrapper;
import com.sencha.gxt.widget.core.client.CoreBaseTestCase;

public class GwtTestMinDateValidator extends CoreBaseTestCase {

  public void testValidator() {
    MinDateValidator validator = new MinDateValidator(new Date());
    
    DateWrapper wrap = new DateWrapper();
    wrap = wrap.addDays(-10);
    assertEquals(1, validator.validate(null, wrap.asDate()).size());

    assertNull(validator.validate(null, new Date()));
    
    wrap = new DateWrapper(new Date());
    
    validator.setMinDate(wrap.asDate());

    wrap = wrap.addSeconds(-10);
    assertNull(validator.validate(null, wrap.asDate()));
  }
}
