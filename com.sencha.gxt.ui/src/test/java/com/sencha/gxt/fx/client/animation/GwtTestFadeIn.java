package com.sencha.gxt.fx.client.animation;

import com.google.gwt.user.client.ui.RootPanel;
import com.sencha.gxt.fx.client.FxBaseTestCase;
import com.sencha.gxt.fx.client.animation.AfterAnimateEvent.AfterAnimateHandler;
import com.sencha.gxt.widget.core.client.button.TextButton;

public class GwtTestFadeIn extends FxBaseTestCase {

  public void testFadeIn() {
    final TextButton button = new TextButton();
    RootPanel.get().add(button);
    button.getElement().setVisible(false);
//    button.setVisible(false);
    Fx fx = new Fx(1000);
    fx.addAfterAnimateHandler(new AfterAnimateHandler() {
      @Override
      public void onAfterAnimate(AfterAnimateEvent event) {
//        button.setVisible(true);
        assertTrue(button.isVisible());
        finishTest();
      }
    });
    fx.run(new FadeIn(button.getElement()));

    delayTestFinish(1500);
  }

}
