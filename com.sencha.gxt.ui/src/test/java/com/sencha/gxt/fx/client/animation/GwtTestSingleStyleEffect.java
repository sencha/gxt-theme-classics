package com.sencha.gxt.fx.client.animation;

import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.RootPanel;
import com.sencha.gxt.fx.client.FxBaseTestCase;
import com.sencha.gxt.widget.core.client.button.TextButton;

public class GwtTestSingleStyleEffect extends FxBaseTestCase {

  public void testSingleStyle() {
    final TextButton button = new TextButton();
    RootPanel.get().add(button);
    
    SingleStyleEffect style = new SingleStyleEffect(button.getElement(), "opacity", 0.25, 0.75);
    
    Fx fx = new Fx(1);
    fx.run(style);
    
    new Timer() {
      @Override
      public void run() {
        //TODO: setproperty not working
        //assertEquals(0.75, button.getElement().getStyle().getProperty("opacity"));
        finishTest();
      }
    }.schedule(50);
    delayTestFinish(100);
  }
  
}
