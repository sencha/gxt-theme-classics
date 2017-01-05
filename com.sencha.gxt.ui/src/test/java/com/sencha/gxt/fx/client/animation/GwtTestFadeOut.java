package com.sencha.gxt.fx.client.animation;

import com.google.gwt.dom.client.Document;
import com.sencha.gxt.core.client.dom.XElement;
import com.sencha.gxt.fx.client.FxBaseTestCase;

public class GwtTestFadeOut extends FxBaseTestCase {

  public void testFadeOut() {
    XElement element = XElement.as(Document.get().createDivElement());
    Fx fx = new Fx(1);
    fx.run(new FadeOut(element));
    
    assertFalse(element.isVisible());
  }
}
