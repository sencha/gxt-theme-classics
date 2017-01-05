package com.sencha.gxt.widget.core.client.container;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootPanel;
import com.sencha.gxt.core.client.dom.ScrollSupport.ScrollMode;
import com.sencha.gxt.widget.core.client.CoreBaseTestCase;
import com.sencha.gxt.widget.core.client.button.TextButton;

public class GwtTestFlowLayoutContainer extends CoreBaseTestCase {

  public void testContainer() {
    FlowLayoutContainer con = new FlowLayoutContainer();
    con.add(new TextButton("button"));

    Scheduler.get().scheduleDeferred(new ScheduledCommand() {

      @Override
      public void execute() {
        finishTest();
      }
    });

    RootPanel.get().add(con);
    delayTestFinish(200);
  }
  
  public void testScrollSupport() {
    final FlowLayoutContainer con = new FlowLayoutContainer();
    con.getScrollSupport().setScrollMode(ScrollMode.AUTOY);
    con.setPixelSize(400, 200);
    
    for (int i = 0; i < 20; i++) {
      con.add(new HTML("Widget " + i));
    }

    Scheduler.get().scheduleDeferred(new ScheduledCommand() {

      @Override
      public void execute() {
        finishTest();
      }
    });

    RootPanel.get().add(con);
    
    con.getScrollSupport().setVerticalScrollPosition(100);
    
    delayTestFinish(200);
  }

}
