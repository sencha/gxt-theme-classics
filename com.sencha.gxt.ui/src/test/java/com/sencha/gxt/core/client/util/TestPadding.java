package com.sencha.gxt.core.client.util;

import junit.framework.TestCase;

public class TestPadding extends TestCase {

  public void testPadding() {
    Padding zeroPadding = new Padding();
    assertEquals(0, zeroPadding.getTop());
    assertEquals(0, zeroPadding.getRight());
    assertEquals(0, zeroPadding.getBottom());
    assertEquals(0, zeroPadding.getLeft());

    Padding uniformPadding = new Padding(500);
    assertEquals(500, uniformPadding.getTop());
    assertEquals(500, uniformPadding.getRight());
    assertEquals(500, uniformPadding.getBottom());
    assertEquals(500, uniformPadding.getLeft());

    Padding padding = new Padding(0, 50, 500, 550);
    assertEquals(0, padding.getTop());
    assertEquals(50, padding.getRight());
    assertEquals(500, padding.getBottom());
    assertEquals(550, padding.getLeft());
  }

}
