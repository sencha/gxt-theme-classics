package com.sencha.gxt.core.client.util;

import junit.framework.TestCase;

public class TestMargins extends TestCase {

  public void testMargins() {
    Margins zeroMargin = new Margins();
    assertEquals(0, zeroMargin.getTop());
    assertEquals(0, zeroMargin.getRight());
    assertEquals(0, zeroMargin.getBottom());
    assertEquals(0, zeroMargin.getLeft());

    Margins uniformMargin = new Margins(500);
    assertEquals(500, uniformMargin.getTop());
    assertEquals(500, uniformMargin.getRight());
    assertEquals(500, uniformMargin.getBottom());
    assertEquals(500, uniformMargin.getLeft());

    Margins margin = new Margins(0, 50, 500, 550);
    assertEquals(0, margin.getTop());
    assertEquals(50, margin.getRight());
    assertEquals(500, margin.getBottom());
    assertEquals(550, margin.getLeft());
  }

}
