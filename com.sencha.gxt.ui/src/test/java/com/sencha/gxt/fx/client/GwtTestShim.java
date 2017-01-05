package com.sencha.gxt.fx.client;

import com.google.gwt.dom.client.Document;
import com.google.gwt.user.client.ui.Frame;
import com.google.gwt.user.client.ui.RootPanel;
import com.sencha.gxt.core.client.dom.XElement;

public class GwtTestShim extends FxBaseTestCase {

  public void testCreate() {
    XElement element = Document.get().createDivElement().cast();
    
    XElement shim = Shim.get().createShim(element, 5, 5, 400, 500);
    
    assertEquals(400, shim.getWidth(false));
    assertEquals(500, shim.getHeight(false));
    assertEquals(element.getZIndex() + 1, shim.getZIndex());
  }
  public void testShimFrame() {
    Frame frame = new Frame();
    RootPanel.get().add(frame);
    
    Shim.get().cover(true);
    Shim.get().uncover();
    
    //testing for the fix EXTGWT-1392
    assertNotNull(frame.getElement().getParentElement());
  }
  
  public void testShimAll() {
    Frame frame = new Frame();
    RootPanel.get().add(frame);
    
    Shim.get().cover(false);
    Shim.get().uncover();
    
    //not needed, but testing for EXTGWT-1392 anyway
    assertNotNull(frame.getElement().getParentElement());
  }
}
