package com.sencha.gxt.widget.core.client.info;

import com.sencha.gxt.core.client.util.EventTracker;
import com.sencha.gxt.widget.core.client.CoreBaseTestCase;
import com.sencha.gxt.widget.core.client.event.HideEvent;
import com.sencha.gxt.widget.core.client.event.HideEvent.HideHandler;
import com.sencha.gxt.widget.core.client.event.ShowEvent;
import com.sencha.gxt.widget.core.client.event.ShowEvent.ShowHandler;

public class GwtTestInfo extends CoreBaseTestCase {

  private int count;

  @Override
  protected void gwtSetUp() throws Exception {
    super.gwtSetUp();

    count = 0;
  }

  /**
   * Test {@link Info} handlers work when they are set in the config.
   */
  public void testInfoShowAndHideHandlers() {
    final EventTracker eventTracker = new EventTracker();

    // Given we have a Info and InfoConfig with handlers.
    InfoConfig config = new DefaultInfoConfig("title", "message");
    config.setShowHandler(new ShowHandler() {
      @Override
      public void onShow(ShowEvent event) {
        // When the show handler works increment tracker.
        eventTracker.addEvent(ShowEvent.getType());
      }
    });

    config.setHideHandler(new HideHandler() {
      @Override
      public void onHide(HideEvent event) {
        // And When the hide handler works increment tracker.
        eventTracker.addEvent(HideEvent.getType());

        // Then assert both handlers fired.
        assertEquals(1, eventTracker.getCount(HideEvent.getType()));
        assertEquals(1, eventTracker.getCount(ShowEvent.getType()));

        finishTest();
      }
    });

    Info.display(config);

    delayTestFinish(3000);
  }

  /**
   * Test {@link Info} handlers work when two consecutive Info.dsplay(config) calls are called.
   */
  public void testInfoShowAndHideHandlersOnMultipleCalls() {
    final EventTracker eventTracker = new EventTracker();

    // Given we have a Info and InfoConfig with handlers.
    InfoConfig config = new DefaultInfoConfig("title", "message");
    config.setShowHandler(new ShowHandler() {
      @Override
      public void onShow(ShowEvent event) {
        // When the show handler works increment tracker.
        eventTracker.addEvent(ShowEvent.getType());
      }
    });

    config.setHideHandler(new HideHandler() {
      @Override
      public void onHide(HideEvent event) {
        // And When the hide handler works increment tracker.
        eventTracker.addEvent(HideEvent.getType());

        // Then b/c the same handler was used for both InfoConfigs, the same handlers should have twice as many.
        if (eventTracker.getCount(HideEvent.getType()) == 2) {
          assertEquals(2, eventTracker.getCount(HideEvent.getType()));
          assertEquals(2, eventTracker.getCount(ShowEvent.getType()));

          finishTest();
        }
      }
    });

    Info.display(config);
    Info.display(config);

    delayTestFinish(3000);
  }

  /**
   * Test {@link Info} with two different {@link InfoConfig}s and both should use their own set of handlers.
   */
  public void testInfoShowAndHideHandlersOnMultipleCallsAndDifferentConfigs() {
    final EventTracker eventTracker1 = new EventTracker();
    final EventTracker eventTracker2 = new EventTracker();

    // Given we have a Info with one configuration.
    InfoConfig config1 = new DefaultInfoConfig("title", "message");
    config1.setShowHandler(new ShowHandler() {
      @Override
      public void onShow(ShowEvent event) {
        // When Info1 show fires increment tracker1.
        eventTracker1.addEvent(ShowEvent.getType());
      }
    });
    config1.setHideHandler(new HideHandler() {
      @Override
      public void onHide(HideEvent event) {
        // And When Info1 hide fires increment tracker1.
        eventTracker1.addEvent(HideEvent.getType());

        // Then assert that Info1 tracking only increments once.
        assertEquals(1, eventTracker1.getCount(HideEvent.getType()));
        assertEquals(1, eventTracker1.getCount(ShowEvent.getType()));
      }
    });

    // And Given we have a Info with second configuration
    InfoConfig config2 = new DefaultInfoConfig("title", "message");
    config2.setShowHandler(new ShowHandler() {
      @Override
      public void onShow(ShowEvent event) {
        // When Info2 fires show increment tracker2.
        eventTracker2.addEvent(ShowEvent.getType());
      }
    });
    config2.setHideHandler(new HideHandler() {
      @Override
      public void onHide(HideEvent event) {
        // And When Info2 fires hide increment tracker2.
        eventTracker2.addEvent(HideEvent.getType());

        // Then assert that Info2 tracking only increments once.
        assertEquals(1, eventTracker2.getCount(HideEvent.getType()));
        assertEquals(1, eventTracker2.getCount(ShowEvent.getType()));

        finishTest();
      }
    });

    Info.display(config1);
    Info.display(config2);

    delayTestFinish(3000);
  }

  /**
   * Test shutting off handlers won't disrupt future handlers.
   */
  public void testInfoShowAndHideHandlersOnMultipleCallsAndTurnOffHandlers() {
    // Given Info has 3 configurations and this is the first.
    InfoConfig config1 = new DefaultInfoConfig("title", "message");
    config1.setShowHandler(new ShowHandler() {
      @Override
      public void onShow(ShowEvent event) {
      }
    });
    config1.setHideHandler(new HideHandler() {
      @Override
      public void onHide(HideEvent event) {
        // When the first handler is used, increment count for tracking, to verify it was used.
        count++;
        if (count > 1) {
          fail();
        }
      }
    });

    // And Given Info has 3 configurations and this is the second.testInfoShowAndHideHandlersCalledOneAfterAnother
    InfoConfig config2 = new DefaultInfoConfig("title", "message");
    // And When the second set of handlers is not used, it should fire the first set.
    config2.setShowHandler(null);
    config2.setHideHandler(null);

    // And Given Info has 3 configurations and this is the third.
    InfoConfig config3 = new DefaultInfoConfig("title", "message");
    config3.setShowHandler(new ShowHandler() {
      @Override
      public void onShow(ShowEvent event) {
      }
    });
    config3.setHideHandler(new HideHandler() {
      @Override
      public void onHide(HideEvent event) {
        // Then the third set of handlers fired, to let the test know it can finish, otherwise it would time out.
        finishTest();
      }
    });

    Info.display(config1);
    Info.display(config2);
    Info.display(config3);

    delayTestFinish(3000);
  }

  /**
   * Test {@link Info} is called one after another, calls the handlers once.
   */
  public void testInfoShowAndHideHandlersCalledOneAfterAnother() {
    final EventTracker eventTracker = new EventTracker();

    // Given Info has a config with handlers.
    final InfoConfig config = new DefaultInfoConfig("title", "message");
    config.setShowHandler(new ShowHandler() {
      @Override
      public void onShow(ShowEvent event) {
        // When show handler fires increment tracker.
        eventTracker.addEvent(ShowEvent.getType());
      }
    });

    config.setHideHandler(new HideHandler() {
      @Override
      public void onHide(HideEvent event) {
        // When hide handler fires increment tracker.
        eventTracker.addEvent(HideEvent.getType());

        // Then each time a handler tracker is counted assertion of only one increment should happen per call.
        assertEquals(count, eventTracker.getCount(HideEvent.getType()));
        assertEquals(count, eventTracker.getCount(ShowEvent.getType()));
        
        // Then after the 2nd call finish
        if (count == 2) {
          finishTest();
        }

        // And Given the Info count starts with 1, and each time its called count gets incremented.
        count++;
        Info.display(config);
      }
    });

    count++;
    Info.display(config);

    delayTestFinish(12000);
  }

  /**
   * Test 3 {@link Info} calls, and null handlers on second, to be sure its turned off in the future calls.
   */
  public void testInfoShowAndHideHandlersCalledOneAfterAnotherWithNullShowHandler() {
    final EventTracker eventTracker = new EventTracker();
    final boolean[] steps = {false, false};
    
    // Given Info has a config with handlers.
    final InfoConfig config = new DefaultInfoConfig("title", "message");
    config.setShowHandler(new ShowHandler() {
      @Override
      public void onShow(ShowEvent event) {
        // When show fires increments tracker.
        eventTracker.addEvent(ShowEvent.getType());
      }
    });

    config.setHideHandler(new HideHandler() {
      @Override
      public void onHide(HideEvent event) {
        // And When hide fires increments tracker.
        eventTracker.addEvent(HideEvent.getType());

        // Then on first call, both handlers will fire.
        if (count == 1) {
          assertEquals(count, eventTracker.getCount(HideEvent.getType()));
          assertEquals(count, eventTracker.getCount(ShowEvent.getType()));

          // And When the show handler is nulled/turned off, it should no longer increment on calls.
          // (The hide handler will behave identically, but not able to test it.)
          config.setShowHandler(null);
          
          // And When the step is marked for future check.
          steps[0] = true;
        }

        // And Then on the second call, be sure show handler didn't increment and hide did.
        if (count == 2) {
          assertEquals(count, eventTracker.getCount(HideEvent.getType()));
          assertEquals(count - 1, eventTracker.getCount(ShowEvent.getType()));
          
          // And When the step is marked for future check.
          steps[1] = true;
        }

        // And Then on the third call, check to see if show didn't call again and hide did.
        if (count == 3) {
          assertEquals(count, eventTracker.getCount(HideEvent.getType()));
          assertEquals(count - 2, eventTracker.getCount(ShowEvent.getType()));
          
          // And Then verify that steps 1 and 2 were called.
          assertTrue(steps[0]);
          assertTrue(steps[1]);
          
          finishTest();
        }

        count++;
        Info.display(config);
      }
    });

    count++;
    Info.display(config);

    delayTestFinish(10000);
  }

}
