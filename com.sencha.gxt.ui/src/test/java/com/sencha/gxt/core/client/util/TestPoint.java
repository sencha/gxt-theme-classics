package com.sencha.gxt.core.client.util;

import junit.framework.TestCase;

public class TestPoint extends TestCase {

  public void testEquals() {
    Point sheep = new Point(4, 4);
    Point dolly = new Point(4, 4);
    Point blackSheep = new Point(-4, -4);

    assertTrue(sheep.equals(sheep));
    assertTrue(sheep.equals(dolly));
    assertFalse(sheep.equals(blackSheep));
  }
  
  public void testX() {
    assertEquals((new Point(0, 0)).getX(), 0);
    assertEquals((new Point(0, 3)).getX(), 0);
    assertEquals((new Point(14, 3)).getX(), 14);
    assertEquals((new Point(-26, 4)).getX(), -26);
  }

  public void testY() {
    assertEquals((new Point(0, 0)).getY(), 0);
    assertEquals((new Point(0, 3)).getY(), 3);
    assertEquals((new Point(3, 14)).getY(), 14);
    assertEquals((new Point(4, -26)).getY(), -26);
  }

}
