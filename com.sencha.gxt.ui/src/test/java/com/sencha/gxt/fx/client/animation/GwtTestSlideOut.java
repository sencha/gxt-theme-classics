package com.sencha.gxt.fx.client.animation;

import com.google.gwt.user.client.ui.RootPanel;
import com.sencha.gxt.core.client.Style.Direction;
import com.sencha.gxt.fx.client.FxBaseTestCase;
import com.sencha.gxt.fx.client.animation.AfterAnimateEvent.AfterAnimateHandler;
import com.sencha.gxt.widget.core.client.button.TextButton;

public class GwtTestSlideOut extends FxBaseTestCase {

  public void testSlideOut() {
    final TextButton button = new TextButton();
    RootPanel.get().add(button);

    Fx fx = new Fx(500);
    fx.addAfterAnimateHandler(new AfterAnimateHandler() {

      @Override
      public void onAfterAnimate(AfterAnimateEvent event) {
//        button.setVisible(false);
        assertFalse(button.isVisible());
        finishTest();
      }
    });
    fx.run(new SlideOut(button.getElement(), Direction.UP));
    delayTestFinish(1500);
  }

}
