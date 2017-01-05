package com.sencha.gxt.fx.client.animation;

import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.RootPanel;
import com.sencha.gxt.core.client.Style.ScrollDir;
import com.sencha.gxt.fx.client.FxBaseTestCase;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.button.TextButton;

public class GwtTestScroll extends FxBaseTestCase {

  public void testScroll() {
    final ContentPanel cp = new ContentPanel();
    cp.setPixelSize(1, 1);
    cp.add(new TextButton());
    RootPanel.get().add(cp);
    cp.setVisible(false);
    Fx fx = new Fx(1);
    fx.run(new com.sencha.gxt.fx.client.animation.Scroll(cp.getElement(), ScrollDir.VERTICAL, 1));
    
    new Timer() {
      @Override
      public void run() {
        //TODO: scrolltop not getting set
        //assertEquals(1, cp.getElement().getScrollTop());
        finishTest();
      }
    }.schedule(50);
    delayTestFinish(100);
  }
  
}
