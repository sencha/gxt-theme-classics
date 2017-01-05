package com.sencha.gxt.core.client.util;

import java.util.HashMap;
import java.util.Map;

import com.google.gwt.user.client.Timer;
import com.sencha.gxt.core.shared.CoreBaseTestCase;

public class GwtTestDelayedTask extends CoreBaseTestCase {

  public void testCancel() {
    final Map<String, Object> map = new HashMap<String, Object>();
    DelayedTask task = new DelayedTask() {
      @Override
      public void onExecute() {
        map.put("foo", "bar");
      }
    };

    task.delay(1000);
    task.delay(2000);

    Timer timer = new Timer() {
      @Override
      public void run() {
        assertNull(map.get("foo"));
        finishTest();
      }
    };

    timer.schedule(1000);
    delayTestFinish(2000);
  }

  public void testDelay() {
    final Map<String, Object> map = new HashMap<String, Object>();
    DelayedTask task = new DelayedTask() {
      @Override
      public void onExecute() {
        map.put("foo", "bar");
        
      }
    };

    task.delay(500);
    assertNull(map.get("foo"));

    Timer timer = new Timer() {
      @Override
      public void run() {
        assertEquals("bar", map.get("foo"));
        finishTest();
      }
    };

    timer.schedule(1000);
    delayTestFinish(2000);
  }

  public void testExecute() {
    final Map<String, Object> map = new HashMap<String, Object>();
    DelayedTask task = new DelayedTask() {
      @Override
      public void onExecute() {
        map.put("foo", "bar");
      }
    };

    task.delay(0);

    assertEquals("bar", map.get("foo"));
  }

}
