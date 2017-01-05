package com.sencha.gxt.widget.core.client.container;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootPanel;
import com.sencha.gxt.core.client.dom.ScrollSupport.ScrollMode;
import com.sencha.gxt.core.client.resources.ThemeStyles;
import com.sencha.gxt.widget.core.client.CoreBaseTestCase;
import com.sencha.gxt.widget.core.client.container.CssFloatLayoutContainer.CssFloatData;

public class GwtTestCssFloatLayoutContainer extends CoreBaseTestCase {

  public void testContainer() {
    CssFloatLayoutContainer con = new CssFloatLayoutContainer();
    
    con.setBorders(true);
    con.setPixelSize(400, 400);

    final HTML html = new HTML("one");
    html.addStyleName(ThemeStyles.get().style().border());
    con.add(html, new CssFloatData(.5));

    final HTML html2 = new HTML("two");
    html2.addStyleName(ThemeStyles.get().style().border());
    con.add(html2, new CssFloatData(.5));

    RootPanel.get().add(con);
    
    Scheduler.get().scheduleDeferred(new ScheduledCommand() {
      
      @Override
      public void execute() {
        assertTrue(html.getOffsetWidth() > 150);
        assertTrue(html2.getOffsetWidth() > 150);
        finishTest();
      }
    });
    
    delayTestFinish(100);
  }
 
  
  public void testScrollSupport() {
    CssFloatLayoutContainer con = new CssFloatLayoutContainer();
    
    assertNotNull(con.getScrollSupport());
    
    con.setScrollMode(ScrollMode.AUTO);
    
    assertEquals(ScrollMode.AUTO, con.getScrollMode());
  }
  
}
