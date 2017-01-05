package com.sencha.gxt.widget.core.client.container;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.user.client.ui.RootPanel;
import com.sencha.gxt.widget.core.client.CoreBaseTestCase;
import com.sencha.gxt.widget.core.client.container.BoxLayoutContainer.BoxLayoutData;
import com.sencha.gxt.widget.core.client.container.BoxLayoutContainer.BoxLayoutPack;

public class GwtTestBoxLayoutContainer extends CoreBaseTestCase {

  public void testPackStart() {
    final HBoxLayoutContainer con = new HBoxLayoutContainer();
    con.setPack(BoxLayoutPack.START);
    con.setPixelSize(600, 600);
    final HBoxLayoutContainer t1 = new HBoxLayoutContainer();
    final HBoxLayoutContainer t2 = new HBoxLayoutContainer();
    final HBoxLayoutContainer t3 = new HBoxLayoutContainer();
    t1.setPixelSize(100, 100);
    t2.setPixelSize(100, 100);
    t3.setPixelSize(100, 100);
    
    con.add(t1);
    con.add(t2);
    con.add(t3);

    RootPanel.get().add(con);

    Scheduler.get().scheduleDeferred(new ScheduledCommand() {
      
      @Override
      public void execute() {
        int w = t1.getOffsetWidth();
        assertEquals(w*0, t1.getElement().getLeft());
        assertEquals(w*1, t2.getElement().getLeft());
        assertEquals(w*2, t3.getElement().getLeft());
        finishTest();
      }
    });
    
    delayTestFinish(200);

  }

  public void testPackEnd() {
    final HBoxLayoutContainer con = new HBoxLayoutContainer();
    con.setPack(BoxLayoutPack.END);
    con.setPixelSize(600, 600);
    final HBoxLayoutContainer t1 = new HBoxLayoutContainer();
    final HBoxLayoutContainer t2 = new HBoxLayoutContainer();
    final HBoxLayoutContainer t3 = new HBoxLayoutContainer();
    t1.setPixelSize(100, 100);
    t2.setPixelSize(100, 100);
    t3.setPixelSize(100, 100);
    
    con.add(t1);
    con.add(t2);
    con.add(t3);

    RootPanel.get().add(con);

    Scheduler.get().scheduleDeferred(new ScheduledCommand() {
      
      @Override
      public void execute() {
        int w = t1.getOffsetWidth();
        assertEquals(600-w*3, t1.getElement().getLeft());
        assertEquals(600-w*2, t2.getElement().getLeft());
        assertEquals(600-w*1, t3.getElement().getLeft());
        finishTest();
      }
    });
    
    delayTestFinish(200);

  }

  public void testPackCenter() {
    final HBoxLayoutContainer con = new HBoxLayoutContainer();
    con.setPack(BoxLayoutPack.CENTER);
    con.setPixelSize(600, 600);
    final HBoxLayoutContainer t1 = new HBoxLayoutContainer();
    final HBoxLayoutContainer t2 = new HBoxLayoutContainer();
    final HBoxLayoutContainer t3 = new HBoxLayoutContainer();
    t1.setPixelSize(100, 100);
    t2.setPixelSize(100, 100);
    t3.setPixelSize(100, 100);
    
    con.add(t1);
    con.add(t2);
    con.add(t3);

    RootPanel.get().add(con);

    Scheduler.get().scheduleDeferred(new ScheduledCommand() {
      
      @Override
      public void execute() {
        int w = t1.getOffsetWidth();
        assertEquals(150+w*0, t1.getElement().getLeft());
        assertEquals(150+w*1, t2.getElement().getLeft());
        assertEquals(150+w*2, t3.getElement().getLeft());
        finishTest();
      }
    });
    
    delayTestFinish(200);

  }
  
  public void testFlex() {
    final HBoxLayoutContainer con = new HBoxLayoutContainer();
    con.setPack(BoxLayoutPack.START);
    con.setPixelSize(600, 600);
    final HBoxLayoutContainer t1 = new HBoxLayoutContainer();
    final HBoxLayoutContainer t2 = new HBoxLayoutContainer();
    final HBoxLayoutContainer t3 = new HBoxLayoutContainer();
    t1.setPixelSize(100, 100);
    t2.setPixelSize(100, 100);
    t3.setPixelSize(100, 100);

    BoxLayoutData flex = new BoxLayoutData();
    flex.setFlex(1);

    con.add(t1,flex);
    con.add(t2,flex);
    con.add(t3,flex);

    RootPanel.get().add(con);

    Scheduler.get().scheduleDeferred(new ScheduledCommand() {
      
      @Override
      public void execute() {
        assertEquals(0, t1.getElement().getLeft());
        assertEquals(200, t2.getElement().getLeft());
        assertEquals(400, t3.getElement().getLeft());
        finishTest();
      }
    });
    
    delayTestFinish(200);

  }
  
}
