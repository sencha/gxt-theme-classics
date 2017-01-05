package com.sencha.gxt.fx.client.easing;

import junit.framework.TestCase;

import com.sencha.gxt.fx.client.easing.Default;
import com.sencha.gxt.fx.client.easing.Linear;

public class TestEasing extends TestCase {

  public void testEasing() {
    assertEquals(5.0, new Linear().func(5));
    assertEquals(1.0, new Default().func(5));
  }
}
