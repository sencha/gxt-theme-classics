package com.sencha.gxt.core.client.util;

import junit.framework.TestCase;

public class TestRectangle extends TestCase {

  public void testContains() {
    Rectangle rect = new Rectangle(50, 50, 550, 550);
    Point in = new Point(100, 100);
    Point out = new Point(0, 0);
    Point farOut = new Point(1000, 1000);

    assertTrue(rect.contains(in));
    assertFalse(rect.contains(out));
    assertFalse(rect.contains(farOut));
  }

}
