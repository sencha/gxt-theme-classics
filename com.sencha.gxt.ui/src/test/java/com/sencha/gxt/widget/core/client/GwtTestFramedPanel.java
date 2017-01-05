package com.sencha.gxt.widget.core.client;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.junit.DoNotRunWith;
import com.google.gwt.junit.Platform;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootPanel;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.SimpleContainer;

public class GwtTestFramedPanel extends CoreBaseTestCase {

  public void testFramedPanel() {
    FramedPanel framedPanel = new FramedPanel();
    RootPanel.get().add(framedPanel);

    assertFalse(framedPanel.getButtonBar().isVisible());
  }

  public void testFramedPanelWithButton() {
    FramedPanel framedPanel = new FramedPanel();
    framedPanel.addButton(new TextButton("button"));
    RootPanel.get().add(framedPanel);

    assertTrue(framedPanel.getButtonBar().isVisible());
  }

  @DoNotRunWith(Platform.HtmlUnitLayout)
  public void testFramedPanelSizes() {
    final FramedPanel innerInner = new FramedPanel();
    innerInner.add(new HTML("Inner Inner"));

    final FramedPanel inner = new FramedPanel();
    inner.addButton(new TextButton("Inner"));
    inner.add(innerInner);

    final FramedPanel outer = new FramedPanel();
    outer.addButton(new TextButton("Outer"));
    outer.add(inner);

    final SimpleContainer sc = new SimpleContainer();
    sc.setPixelSize(400, 300);
    sc.add(outer);
    
    RootPanel.get().add(sc);
    
    delayTestFinish(3000);
    
    Scheduler.get().scheduleDeferred(new ScheduledCommand() {
      public void execute() {
        assertEquals(300, sc.getOffsetHeight());
        assertEquals(300, outer.getOffsetHeight());
        assertEquals(232, inner.getOffsetHeight());
        assertEquals(164, innerInner.getOffsetHeight());
       
        finishTest();
      }
    });
  }

}
