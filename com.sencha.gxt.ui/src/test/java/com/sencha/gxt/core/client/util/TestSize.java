package com.sencha.gxt.core.client.util;

import junit.framework.TestCase;

public class TestSize extends TestCase {

  public void testEquals() {
    Size sheep = new Size(4, 4);
    Size dolly = new Size(4, 4);
    Size blackSheep = new Size(-4, -4);

    assertTrue(sheep.equals(sheep));
    assertTrue(sheep.equals(dolly));
    assertFalse(sheep.equals(blackSheep));
  }

}
