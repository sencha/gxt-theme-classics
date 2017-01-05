package com.sencha.gxt.widget.core.client.container;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.user.client.ui.RootPanel;
import com.sencha.gxt.widget.core.client.CoreBaseTestCase;
import com.sencha.gxt.widget.core.client.container.BoxLayoutContainer.BoxLayoutPack;
import com.sencha.gxt.widget.core.client.container.HBoxLayoutContainer.HBoxLayoutAlign;

public class GwtTestHBoxLayoutContainer extends CoreBaseTestCase {

  public void testAlignBottom() {
    final HBoxLayoutContainer con = new HBoxLayoutContainer();
    con.setPack(BoxLayoutPack.CENTER);
    con.setHBoxLayoutAlign(HBoxLayoutAlign.BOTTOM);
    // overflow messes up calcs
    con.setEnableOverflow(false);
    con.setPixelSize(600, 600);
    
    final HBoxLayoutContainer c1 = new HBoxLayoutContainer();
    final HBoxLayoutContainer c2 = new HBoxLayoutContainer();
    final HBoxLayoutContainer c3 = new HBoxLayoutContainer();

    c1.setPixelSize(100,100);
    c2.setPixelSize(200,200);
    c3.setPixelSize(300,300);

    con.add(c1);
    con.add(c2);
    con.add(c3);

    RootPanel.get().add(con);

    Scheduler.get().scheduleDeferred(new ScheduledCommand() {
      
      @Override
      public void execute() {
        assertEquals(500, c1.getElement().getTop());
        assertEquals(400, c2.getElement().getTop());
        assertEquals(300, c3.getElement().getTop());
        assertEquals(3, con.getWidgetCount());
        finishTest();
      }
    });
    
    delayTestFinish(200);
  }
  
  public void testAlignMiddle() {
    final HBoxLayoutContainer con = new HBoxLayoutContainer();
    con.setPack(BoxLayoutPack.CENTER);
    con.setHBoxLayoutAlign(HBoxLayoutAlign.MIDDLE);
    // overflow messes up calcs
    con.setEnableOverflow(false);
    con.setPixelSize(600, 600);
    
    final HBoxLayoutContainer c1 = new HBoxLayoutContainer();
    final HBoxLayoutContainer c2 = new HBoxLayoutContainer();
    final HBoxLayoutContainer c3 = new HBoxLayoutContainer();

    c1.setPixelSize(100,100);
    c2.setPixelSize(200,200);
    c3.setPixelSize(300,300);

    con.add(c1);
    con.add(c2);
    con.add(c3);

    RootPanel.get().add(con);

    Scheduler.get().scheduleDeferred(new ScheduledCommand() {
      
      @Override
      public void execute() {
        assertEquals(250, c1.getElement().getTop());
        assertEquals(200, c2.getElement().getTop());
        assertEquals(150, c3.getElement().getTop());
        assertEquals(3, con.getWidgetCount());
        finishTest();
      }
    });
    
    delayTestFinish(200);
  }

  public void testAlignTop() {
    final HBoxLayoutContainer con = new HBoxLayoutContainer();
    con.setPack(BoxLayoutPack.CENTER);
    con.setHBoxLayoutAlign(HBoxLayoutAlign.TOP);
    // overflow messes up calcs
    con.setEnableOverflow(false);
    con.setPixelSize(600, 600);
    
    final HBoxLayoutContainer c1 = new HBoxLayoutContainer();
    final HBoxLayoutContainer c2 = new HBoxLayoutContainer();
    final HBoxLayoutContainer c3 = new HBoxLayoutContainer();

    c1.setPixelSize(100,100);
    c2.setPixelSize(200,200);
    c3.setPixelSize(300,300);

    con.add(c1);
    con.add(c2);
    con.add(c3);

    RootPanel.get().add(con);

    Scheduler.get().scheduleDeferred(new ScheduledCommand() {
      
      @Override
      public void execute() {
        assertEquals(0, c1.getElement().getTop());
        assertEquals(0, c2.getElement().getTop());
        assertEquals(0, c3.getElement().getTop());
        assertEquals(3, con.getWidgetCount());
        finishTest();
      }
    });
    delayTestFinish(200);
  }
  
  public void testAlignStretch() {
    final HBoxLayoutContainer con = new HBoxLayoutContainer();
    con.setPack(BoxLayoutPack.CENTER);
    con.setHBoxLayoutAlign(HBoxLayoutAlign.STRETCH);
    // overflow messes up calcs
    con.setEnableOverflow(false);
    con.setPixelSize(600, 600);
    
    final HBoxLayoutContainer c1 = new HBoxLayoutContainer();
    final HBoxLayoutContainer c2 = new HBoxLayoutContainer();
    final HBoxLayoutContainer c3 = new HBoxLayoutContainer();

    c1.setPixelSize(100,100);
    c2.setPixelSize(200,200);
    c3.setPixelSize(300,300);

    con.add(c1);
    con.add(c2);
    con.add(c3);

    RootPanel.get().add(con);

    Scheduler.get().scheduleDeferred(new ScheduledCommand() {
      
      @Override
      public void execute() {
        assertEquals(600, c1.getElement().getOffsetHeight());
        assertEquals(600, c2.getElement().getOffsetHeight());
        assertEquals(600, c3.getElement().getOffsetHeight());
        assertEquals(3, con.getWidgetCount());
        finishTest();
      }
    });
    delayTestFinish(200);
  }

  public void testAlignStretchMax() {
    final HBoxLayoutContainer con = new HBoxLayoutContainer();
    con.setPack(BoxLayoutPack.CENTER);
    con.setHBoxLayoutAlign(HBoxLayoutAlign.STRETCHMAX);
    // overflow messes up calcs
    con.setEnableOverflow(false);
    con.setPixelSize(600, 600);
    
    final HBoxLayoutContainer c1 = new HBoxLayoutContainer();
    final HBoxLayoutContainer c2 = new HBoxLayoutContainer();
    final HBoxLayoutContainer c3 = new HBoxLayoutContainer();

    c1.setPixelSize(100,100);
    c2.setPixelSize(200,200);
    c3.setPixelSize(300,300);

    con.add(c1);
    con.add(c2);
    con.add(c3);

    RootPanel.get().add(con);

    Scheduler.get().scheduleDeferred(new ScheduledCommand() {
      
      @Override
      public void execute() {
        assertEquals(300, c1.getElement().getOffsetHeight());
        assertEquals(300, c2.getElement().getOffsetHeight());
        assertEquals(300, c3.getElement().getOffsetHeight());
        assertEquals(3, con.getWidgetCount());
        finishTest();
      }
    });
    delayTestFinish(200);
  }
}
