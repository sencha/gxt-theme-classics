package com.sencha.gxt.widget.core.client.container;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.user.client.ui.RootPanel;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.CoreBaseTestCase;

public class GwtTestCardLayoutContainer extends CoreBaseTestCase {

  public void testContainer() {
    final CardLayoutContainer con = new CardLayoutContainer();
    
    final ContentPanel c1 = new ContentPanel();
    final ContentPanel c2 = new ContentPanel();
    final ContentPanel c3 = new ContentPanel();
    final ContentPanel c4 = new ContentPanel();
    con.add(c1);
    con.add(c2);
    con.add(c3);

    con.setActiveWidget(c1);

    RootPanel.get().add(con);
    
    Scheduler.get().scheduleDeferred(new ScheduledCommand() {
      
      @Override
      public void execute() {
        assertEquals(c1, con.getActiveWidget());
        
        assertFalse(c2.isVisible());
        con.setActiveWidget(c2);
        assertEquals(c2, con.getActiveWidget());
        assertTrue(c2.isVisible());
        
        con.insert(c4, 1);
        assertEquals(c4,con.getWidget(1));
        assertEquals(4,con.getWidgetCount());

        finishTest();
      }
    });
    
    delayTestFinish(200);
    
    
  }
}
