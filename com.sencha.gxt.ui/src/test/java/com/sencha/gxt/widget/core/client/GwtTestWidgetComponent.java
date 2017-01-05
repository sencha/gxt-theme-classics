package com.sencha.gxt.widget.core.client;

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootPanel;
import com.sencha.gxt.ui.client.UITestCase;
import com.sencha.gxt.widget.core.client.container.FlowLayoutContainer;

public class GwtTestWidgetComponent extends UITestCase {

  public void testRemoveFromParent() {
    HTML html = new HTML();
    
    FlowLayoutContainer layout1 = new FlowLayoutContainer();
    layout1.add(html);
    RootPanel.get().add(layout1);
    
    WidgetComponent w = new WidgetComponent(html);
    FlowLayoutContainer layout2 = new FlowLayoutContainer();
    layout2.add(w);
    
    assertEquals(w, html.getParent());
    assertEquals(layout2, w.getParent());
    
    RootPanel.get().add(layout2);
  }
  
  public void testWidgetComponent() {
    HTML html = new HTML();
    WidgetComponent w = new WidgetComponent(html);
    
    RootPanel.get().add(w);
    
    assertEquals(html, w.getWidget());
    assertEquals(html.getElement(), w.getElement());
    assertTrue(html.isAttached());
    assertTrue(w.isAttached());
  }
  
}
