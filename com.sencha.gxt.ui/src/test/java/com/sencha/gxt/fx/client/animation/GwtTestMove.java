package com.sencha.gxt.fx.client.animation;

import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.RootPanel;
import com.sencha.gxt.fx.client.FxBaseTestCase;
import com.sencha.gxt.widget.core.client.button.TextButton;

public class GwtTestMove extends FxBaseTestCase {

  public void testMove() {
    final TextButton button = new TextButton();
    RootPanel.get().add(button);
//    button.setVisible(false);
    Fx fx = new Fx(1);
    fx.run(new Move(button.getElement(), 200, 300));
    
    new Timer() {
      @Override
      public void run() {
        assertEquals(200, button.getElement().getX());
        assertEquals(300, button.getElement().getY());
        finishTest();
      }
    }.schedule(50);
    delayTestFinish(100);
  }
}
