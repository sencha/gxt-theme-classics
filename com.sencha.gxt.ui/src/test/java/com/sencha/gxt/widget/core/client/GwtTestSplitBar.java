package com.sencha.gxt.widget.core.client;

import java.util.HashMap;
import java.util.Map;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.GwtEvent.Type;
import com.google.gwt.user.client.ui.RootPanel;
import com.sencha.gxt.core.client.Style.LayoutRegion;
import com.sencha.gxt.ui.client.UITestCase;
import com.sencha.gxt.widget.core.client.container.FlowLayoutContainer;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;

public class GwtTestSplitBar extends UITestCase {

  @SuppressWarnings("rawtypes")
  public void testClick() {
    final Map<Type, Object> map = new HashMap<Type, Object>();

    FlowLayoutContainer con = new FlowLayoutContainer();
    con.setPixelSize(400, 400);
    con.setBorders(true);

    SplitBar bar = new SplitBar(LayoutRegion.EAST, con);
    bar.setCollapsible(true);

    bar.addClickHandler(new ClickHandler() {

      @Override
      public void onClick(ClickEvent event) {
        map.put(event.getAssociatedType(), true);
      }

    });
    RootPanel.get().add(con);
    bar.getElement().click();
    assertEquals(true, map.get(ClickEvent.getType()));
  }

  @SuppressWarnings("rawtypes")
  public void testCollapse() {
    final Map<Type, Object> map = new HashMap<Type, Object>();

    FlowLayoutContainer con = new FlowLayoutContainer();
    con.setPixelSize(400, 400);
    con.setBorders(true);

    SplitBar bar = new SplitBar(LayoutRegion.EAST, con);
    bar.setCollapsible(true);

    bar.addSelectHandler(new SelectHandler() {
      
      @Override
      public void onSelect(SelectEvent event) {
        map.put(event.getAssociatedType(), true); 
      }
    });

    RootPanel.get().add(con);
    bar.getElement().selectNode(bar.getAppearance().miniSelector()).click();
    assertEquals(true, map.get(SelectEvent.getType()));
  }
}
