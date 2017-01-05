package com.sencha.gxt.core.client.util;

import junit.framework.TestCase;


public class TestDateWrapper extends TestCase {

  public void testDateOfYearLeapYear() {
    DateWrapper dateWrapper = new DateWrapper(2016, 02, 01); // march 1, 2016
    assertEquals(61, dateWrapper.getDayOfYear());
  }

  public void testDateOfYearNormalYear() {
    DateWrapper dateWrapper = new DateWrapper(2015, 02, 01); // march 1, 2015
    assertEquals(60, dateWrapper.getDayOfYear());
  }
}
