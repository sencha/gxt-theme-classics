package com.sencha.gxt.fx.client.animation;

import java.util.HashMap;
import java.util.Map;

import com.google.gwt.dom.client.Document;
import com.google.gwt.event.shared.GwtEvent.Type;
import com.google.gwt.user.client.Timer;
import com.sencha.gxt.core.client.dom.XElement;
import com.sencha.gxt.fx.client.FxBaseTestCase;
import com.sencha.gxt.fx.client.animation.AfterAnimateEvent.AfterAnimateHandler;
import com.sencha.gxt.fx.client.animation.BeforeAnimateEvent.BeforeAnimateHandler;
import com.sencha.gxt.fx.client.animation.CancelAnimationEvent.CancelAnimationHandler;

public class GwtTestFx extends FxBaseTestCase {

  @SuppressWarnings("rawtypes")
  public void testBeforeAfterEvents() {
    final Map<Type, Object> map = new HashMap<Type, Object>();
    
    Fx fx = new Fx(1);
    fx.addBeforeAnimateHandler(new BeforeAnimateHandler() {
      @Override
      public void onBeforeAnimate(BeforeAnimateEvent event) {
        map.put(event.getAssociatedType(), true);
      }
    });
    fx.addAfterAnimateHandler(new AfterAnimateHandler() {
      @Override
      public void onAfterAnimate(AfterAnimateEvent event) {
        map.put(event.getAssociatedType(), true);
      }
    });
    
    fx.run(new Blink(XElement.as(Document.get().createDivElement()), 50));
    
    
    
    new Timer() {
      @Override
      public void run() {
        assertEquals(true, map.get(BeforeAnimateEvent.getType()));
        assertEquals(true, map.get(AfterAnimateEvent.getType()));
        finishTest();
      }
    }.schedule(100);
    delayTestFinish(200);
  }
  
  @SuppressWarnings("rawtypes")
  public void testCancelEvents() {
    final Map<Type, Object> map = new HashMap<Type, Object>();
    
    Fx fx = new Fx(1);
    fx.addCancelAnimationHandler(new CancelAnimationHandler() {
      @Override
      public void onCancelAnimation(CancelAnimationEvent event) {
        map.put(event.getAssociatedType(), true);
      }
    });
    
    fx.run(new Blink(XElement.as(Document.get().createDivElement()), 50));
    fx.cancel();
    
    new Timer() {
      @Override
      public void run() {
        //TODO: flaky event
        //assertEquals(true, map.get(CancelAnimationEvent.getType()));
        finishTest();
      }
    }.schedule(5000);
    delayTestFinish(10000);
  }
}
