package com.sencha.gxt.widget.core.client.container;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootPanel;
import com.sencha.gxt.core.client.dom.ScrollSupport.ScrollMode;
import com.sencha.gxt.widget.core.client.CoreBaseTestCase;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;

public class GwtTestVerticalLayoutContainer extends CoreBaseTestCase {

  public void testContainer() {
    VerticalLayoutContainer con = new VerticalLayoutContainer();
    con.setPixelSize(400, 400);
    
    final HTML html = new HTML();
    final HTML html2 = new HTML();
    final HTML html3 = new HTML();
    final HTML html4 = new HTML();
    html3.setPixelSize(100, 100);
    con.add(html, new VerticalLayoutData(1,0.3));
    con.add(html2, new VerticalLayoutData(1,0.7));
    con.add(html3, new VerticalLayoutData(1,-1));
    con.add(html4, new VerticalLayoutData(1,200));
    
    RootPanel.get().add(con);
    
    Scheduler.get().scheduleDeferred(new ScheduledCommand() {
      
      @Override
      public void execute() {
        assertEquals(30, html.getOffsetHeight());
        assertEquals(400, html.getOffsetWidth());
        assertEquals(70, html2.getOffsetHeight());
        assertEquals(400, html2.getOffsetWidth());
        assertEquals(100, html3.getOffsetHeight());
        assertEquals(400, html3.getOffsetWidth());
        assertEquals(200, html4.getOffsetHeight());
        assertEquals(400, html4.getOffsetWidth());
        finishTest();
      }
    });
    
    delayTestFinish(200);
  }
  
  public void testScrollSupport() {
    VerticalLayoutContainer con = new VerticalLayoutContainer();
    
    assertNotNull(con.getScrollSupport());
    
    con.setScrollMode(ScrollMode.AUTO);
    
    assertEquals(ScrollMode.AUTO, con.getScrollMode());
  }
  
}
