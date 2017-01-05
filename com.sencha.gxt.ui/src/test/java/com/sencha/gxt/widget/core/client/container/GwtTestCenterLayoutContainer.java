package com.sencha.gxt.widget.core.client.container;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.sencha.gxt.widget.core.client.CoreBaseTestCase;

public class GwtTestCenterLayoutContainer extends CoreBaseTestCase {

  public void testContainer() {
    CenterLayoutContainer con = new CenterLayoutContainer();
    con.setPixelSize(500, 500);
    
    final Label lbl = new Label("Center Me");
    con.add(lbl);
    
    RootPanel.get().add(con);
    
    Scheduler.get().scheduleDeferred(new ScheduledCommand() {
      
      @Override
      public void execute() {
        assertTrue(lbl.getElement().getStyle().getLeft().length() > 1);
        assertTrue(lbl.getElement().getStyle().getTop().length() > 1);
        finishTest();
      }
    });
    
    assertTrue(lbl.getElement().getStyle().getLeft().equals(""));
    assertTrue(lbl.getElement().getStyle().getTop().equals(""));
    
    delayTestFinish(100);
   
  }
  
}
