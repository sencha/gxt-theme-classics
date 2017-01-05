package com.sencha.gxt.fx.client.animation;

import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.RootPanel;
import com.sencha.gxt.fx.client.FxBaseTestCase;
import com.sencha.gxt.widget.core.client.button.TextButton;

public class GwtTestMultiEffect extends FxBaseTestCase {

  public void testMultiEffect() {
    final TextButton button = new TextButton();
    button.getElement().setVisible(false);
    RootPanel.get().add(button);
    
    MultiEffect mul = new MultiEffect(button.getElement());
    mul.addEffects(new Move(button.getElement(), 100, 200));
    mul.addEffects(new FadeIn(button.getElement()));
    
    Fx fx = new Fx(1);
    fx.run(mul);
    
    new Timer() {
      @Override
      public void run() {
        assertEquals(100, button.getElement().getX());
        assertEquals(200, button.getElement().getY());
        assertTrue(button.getElement().isVisible());
        finishTest();
      }
    }.schedule(50);
    delayTestFinish(100);
  }
  
}
