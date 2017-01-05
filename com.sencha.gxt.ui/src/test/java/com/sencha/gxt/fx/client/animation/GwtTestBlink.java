package com.sencha.gxt.fx.client.animation;

import com.google.gwt.dom.client.Document;
import com.sencha.gxt.core.client.dom.XElement;
import com.sencha.gxt.fx.client.FxBaseTestCase;

public class GwtTestBlink extends FxBaseTestCase {

  public void testBlink() {
    XElement element = XElement.as(Document.get().createDivElement());
    Blink blink = new Blink(element, 50);
    
    assertEquals(50, blink.getInterval());
    
  }
}
