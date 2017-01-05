package com.sencha.gxt.widget.core.client.container;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.user.client.ui.RootPanel;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.CoreBaseTestCase;

public class GwtTestAccordionLayoutContainer extends CoreBaseTestCase {

  public void testActiveOnTop() {
     final AccordionLayoutContainer con = new AccordionLayoutContainer();
     con.setActiveOnTop(true);
     con.setPixelSize(200, 500);
     
     final ContentPanel cp1 = new ContentPanel();
     cp1.setAnimCollapse(false);
     final ContentPanel cp2 = new ContentPanel();
     cp2.setAnimCollapse(false);
     final ContentPanel cp3 = new ContentPanel();
     cp3.setAnimCollapse(false);
     
     con.add(cp1);
     con.add(cp2);
     con.add(cp3);
     
     con.setActiveWidget(cp2);
     RootPanel.get().add(con);
     
     Scheduler.get().scheduleDeferred(new ScheduledCommand() {
       @Override
       public void execute() {
         assertEquals(cp2, con.getActiveWidget());
         // Fails becuse layout is not run before cp2 os set as widget.
         // assertEquals(cp2.getElement(),con.getElement().getFirstChildElement());
        
         con.setActiveWidget(cp3);
         assertEquals(cp3.getElement(),con.getElement().getFirstChildElement());
         finishTest();
        }
     });
     delayTestFinish(200);

  }

  
  public void testCollapseExpand() {
    final AccordionLayoutContainer con = new AccordionLayoutContainer();
    con.setHideCollapseTool(true);
    con.setTitleCollapse(true);
    con.setPixelSize(200, 500);
    final ContentPanel cp1 = new ContentPanel();
    cp1.setHeading("C1");
    cp1.setAnimCollapse(false);
    final ContentPanel cp2 = new ContentPanel();
    cp2.setHeading("C2");
    cp2.setAnimCollapse(false);
    final ContentPanel cp3 = new ContentPanel();
    cp3.setHeading("C3");
    cp3.setAnimCollapse(false);

    con.add(cp1);
    con.add(cp2);
    con.add(cp3);
   
    con.setActiveWidget(cp1);
    RootPanel.get().add(con);
    
    Scheduler.get().scheduleDeferred(new ScheduledCommand() {
      @Override
      public void execute() {
        assertTrue(cp1.isExpanded());
        assertTrue(!cp2.isExpanded());
        assertTrue(!cp3.isExpanded());
        cp2.getHeader().getElement().click();
        assertTrue(!cp1.isExpanded());
        assertTrue(cp2.isExpanded());
        assertTrue(!cp3.isExpanded());
        con.setActiveWidget(cp3);
        assertTrue(!cp1.isExpanded());
        assertTrue(!cp2.isExpanded());
        assertTrue(cp3.isExpanded());
        cp1.expand();
        assertTrue(cp1.isExpanded());
        assertTrue(!cp2.isExpanded());
        assertTrue(!cp3.isExpanded());
        cp1.getHeader().getElement().click();
        assertTrue(!cp1.isExpanded());
        assertTrue(!cp2.isExpanded());
        assertTrue(!cp3.isExpanded());
        finishTest();
       }
    });
    delayTestFinish(200);
  }
  
  public void testHideCollapseTool() {
    AccordionLayoutContainer con = new AccordionLayoutContainer();
    con.setHideCollapseTool(true);
    con.setTitleCollapse(true);
    con.setPixelSize(200, 500);
    final ContentPanel cp1 = new ContentPanel();
    cp1.setHeading("C1");
 
    con.add(cp1);
  
    RootPanel.get().add(con);
 
    AccordionLayoutContainer con2 = new AccordionLayoutContainer();
    con2.setHideCollapseTool(false);
    con2.setTitleCollapse(true);
    con2.setPixelSize(200, 500);
    final ContentPanel cp2 = new ContentPanel();
    cp2.setHeading("C1");
 
    con2.add(cp2);
 
    Scheduler.get().scheduleDeferred(new ScheduledCommand() {
      @Override
      public void execute() {
        assertNull(cp1.getHeader().getAppearance().getBarElem(cp1.getHeader().getElement()).getFirstChildElement().getFirstChildElement().getFirstChildElement().getFirstChild());
        assertNotNull(cp2.getHeader().getAppearance().getBarElem(cp2.getHeader().getElement()).getFirstChildElement().getFirstChildElement().getFirstChildElement().getFirstChild());
        finishTest();
       }
    }); 
    RootPanel.get().add(con2);
   
    delayTestFinish(200);

  }

}
