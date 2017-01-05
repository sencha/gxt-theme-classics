package com.sencha.gxt.fx.client.animation;

import com.google.gwt.dom.client.Document;
import com.sencha.gxt.core.client.dom.XElement;
import com.sencha.gxt.fx.client.FxBaseTestCase;

public class GwtTestBaseEffect extends FxBaseTestCase {

  public void testGetValue() {
    BaseEffect effect = new BaseEffect(XElement.as(Document.get().createDivElement()));
    
    assertEquals(7.5, effect.getValue(5, 10, 0.5));
  }
}
