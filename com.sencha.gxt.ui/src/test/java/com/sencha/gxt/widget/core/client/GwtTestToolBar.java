package com.sencha.gxt.widget.core.client;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.user.client.ui.RootPanel;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.toolbar.ToolBar;

public class GwtTestToolBar extends CoreBaseTestCase {

  public void testToolBar() {
    ToolBar bar = new ToolBar();

    bar.add(new TextButton("one"));
    bar.add(new TextButton("two"));

    RootPanel.get().add(bar);

    Scheduler.get().scheduleDeferred(new ScheduledCommand() {

      @Override
      public void execute() {
        finishTest();
      }
    });

    // let layout run
    delayTestFinish(200);
  }

}
