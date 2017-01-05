package com.sencha.gxt.core.client.util;

import com.sencha.gxt.core.shared.CoreBaseTestCase;

public class GwtTestUtil extends CoreBaseTestCase {

  public void testConstrain() {
    assertEquals(50, Util.constrain(50, 25, 100));
    assertEquals(25, Util.constrain(0, 25, 100));
    assertEquals(100, Util.constrain(500, 25, 100));
  }

  public void testIsEmptyString() {
    assertTrue(Util.isEmptyString(""));
    assertTrue(Util.isEmptyString(null));
    assertFalse(Util.isEmptyString("foobar"));
  }

  public void testParseInt() {
    assertEquals(50, Util.parseInt("50", 0));
    assertEquals(0, Util.parseInt("abc", 0));
  }

}
