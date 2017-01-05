package com.sencha.gxt.widget.core.client.container;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.user.client.ui.RootPanel;
import com.sencha.gxt.widget.core.client.CoreBaseTestCase;
import com.sencha.gxt.widget.core.client.container.BoxLayoutContainer.BoxLayoutPack;
import com.sencha.gxt.widget.core.client.container.VBoxLayoutContainer.VBoxLayoutAlign;

public class GwtTestVBoxLayoutContainer extends CoreBaseTestCase {

  public void testAlignBottom() {
    final VBoxLayoutContainer con = new VBoxLayoutContainer();
    con.setPack(BoxLayoutPack.CENTER);
    con.setVBoxLayoutAlign(VBoxLayoutAlign.RIGHT);
    con.setPixelSize(600, 600);
    
    final VBoxLayoutContainer c1 = new VBoxLayoutContainer();
    final VBoxLayoutContainer c2 = new VBoxLayoutContainer();
    final VBoxLayoutContainer c3 = new VBoxLayoutContainer();

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
        assertEquals(500, c1.getElement().getLeft());
        assertEquals(400, c2.getElement().getLeft());
        assertEquals(300, c3.getElement().getLeft());
        assertEquals(3, con.getWidgetCount());
        finishTest();
      }
    });
    
    delayTestFinish(200);
  }
  
  public void testAlignMiddle() {
    final VBoxLayoutContainer con = new VBoxLayoutContainer();
    con.setPack(BoxLayoutPack.CENTER);
    con.setVBoxLayoutAlign(VBoxLayoutAlign.CENTER);
    // overflow messes up calcs
    con.setPixelSize(600, 600);
    
    final VBoxLayoutContainer c1 = new VBoxLayoutContainer();
    final VBoxLayoutContainer c2 = new VBoxLayoutContainer();
    final VBoxLayoutContainer c3 = new VBoxLayoutContainer();

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
        assertEquals(250, c1.getElement().getLeft());
        assertEquals(200, c2.getElement().getLeft());
        assertEquals(150, c3.getElement().getLeft());
        assertEquals(3, con.getWidgetCount());
        finishTest();
      }
    });
    
    delayTestFinish(200);
  }

  public void testAlignTop() {
    final VBoxLayoutContainer con = new VBoxLayoutContainer();
    con.setPack(BoxLayoutPack.CENTER);
    con.setVBoxLayoutAlign(VBoxLayoutAlign.LEFT);
    con.setPixelSize(600, 600);
    
    final VBoxLayoutContainer c1 = new VBoxLayoutContainer();
    final VBoxLayoutContainer c2 = new VBoxLayoutContainer();
    final VBoxLayoutContainer c3 = new VBoxLayoutContainer();

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
        assertEquals(0, c1.getElement().getLeft());
        assertEquals(0, c2.getElement().getLeft());
        assertEquals(0, c3.getElement().getLeft());
        assertEquals(3, con.getWidgetCount());
        finishTest();
      }
    });
    delayTestFinish(200);
  }
  
  public void testAlignStretch() {
    final VBoxLayoutContainer con = new VBoxLayoutContainer();
    con.setPack(BoxLayoutPack.CENTER);
    con.setVBoxLayoutAlign(VBoxLayoutAlign.STRETCH);
    // overflow messes up calcs
    con.setPixelSize(600, 600);
    
    final VBoxLayoutContainer c1 = new VBoxLayoutContainer();
    final VBoxLayoutContainer c2 = new VBoxLayoutContainer();
    final VBoxLayoutContainer c3 = new VBoxLayoutContainer();

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
        assertEquals(600, c1.getElement().getOffsetWidth());
        assertEquals(600, c2.getElement().getOffsetWidth());
        assertEquals(600, c3.getElement().getOffsetWidth());
        assertEquals(3, con.getWidgetCount());
        finishTest();
      }
    });
    delayTestFinish(200);
  }

  public void testAlignStretchMax() {
    final VBoxLayoutContainer con = new VBoxLayoutContainer();
    con.setPack(BoxLayoutPack.CENTER);
    con.setVBoxLayoutAlign(VBoxLayoutAlign.STRETCHMAX);
    // overflow messes up calcs
    con.setPixelSize(600, 600);
    
    final VBoxLayoutContainer c1 = new VBoxLayoutContainer();
    final VBoxLayoutContainer c2 = new VBoxLayoutContainer();
    final VBoxLayoutContainer c3 = new VBoxLayoutContainer();

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
        assertEquals(300, c1.getElement().getOffsetWidth());
        assertEquals(300, c2.getElement().getOffsetWidth());
        assertEquals(300, c3.getElement().getOffsetWidth());
        assertEquals(3, con.getWidgetCount());
        finishTest();
      }
    });
    delayTestFinish(200);
  }
}