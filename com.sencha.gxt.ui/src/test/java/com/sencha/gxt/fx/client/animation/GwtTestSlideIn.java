package com.sencha.gxt.fx.client.animation;

import com.google.gwt.user.client.ui.RootPanel;
import com.sencha.gxt.core.client.Style.Direction;
import com.sencha.gxt.fx.client.FxBaseTestCase;
import com.sencha.gxt.fx.client.animation.AfterAnimateEvent.AfterAnimateHandler;
import com.sencha.gxt.widget.core.client.button.TextButton;

public class GwtTestSlideIn extends FxBaseTestCase {

  public void testSlideIn() {
    final TextButton button = new TextButton();
    RootPanel.get().add(button);
//    button.setVisible(false);
    button.getElement().setVisible(false);
    Fx fx = new Fx(1000);
    fx.addAfterAnimateHandler(new AfterAnimateHandler() {
      @Override
      public void onAfterAnimate(AfterAnimateEvent event) {
//        button.setVisible(true);
        assertTrue(button.isVisible());
        finishTest();
      }
    });
    fx.run(new SlideIn(button.getElement(), Direction.DOWN));

    delayTestFinish(1500);
  }

}
