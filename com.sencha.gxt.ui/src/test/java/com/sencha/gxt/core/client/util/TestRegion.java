package com.sencha.gxt.core.client.util;

import junit.framework.TestCase;

public class TestRegion extends TestCase {

  public void testRegion() {
    Region region = new Region(0, 50, 500, 550);

    assertEquals(0, region.getTop());
    assertEquals(50, region.getRight());
    assertEquals(500, region.getBottom());
    assertEquals(550, region.getLeft());
  }

}
