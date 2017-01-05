package com.sencha.gxt.widget.core.client;

import java.util.HashMap;
import java.util.Map;

import com.google.gwt.event.shared.GwtEvent.Type;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.button.ToolButton;
import com.sencha.gxt.widget.core.client.event.BeforeCollapseEvent;
import com.sencha.gxt.widget.core.client.event.BeforeCollapseEvent.BeforeCollapseHandler;
import com.sencha.gxt.widget.core.client.event.CollapseEvent;
import com.sencha.gxt.widget.core.client.event.CollapseEvent.CollapseHandler;

public class GwtTestContentPanel extends CoreBaseTestCase {
  public void testCollapsedInit() {
    final ContentPanel cp = new ContentPanel();
    cp.setCollapsible(true);
    RootPanel.get().add(cp);
    delayTestFinish(1000);
    cp.addCollapseHandler(new CollapseHandler() {
      @Override
      public void onCollapse(CollapseEvent event) {
        assertTrue(cp.getHeader().getTool(0).getStyleName().contains(ToolButton.DOWN.getStyle()));
        finishTest();
      }
    });
    cp.setExpanded(false);
  }

  public void testCollapseEvent() {
    final Map<Type<?>, Object> map = new HashMap<Type<?>, Object>();

    final ContentPanel p = new ContentPanel();

    p.setCollapsible(true);
    p.setAnimCollapse(false);

    p.add(new Label("test"));
    p.addBeforeCollapseHandler(new BeforeCollapseHandler() {
      @Override
      public void onBeforeCollapse(BeforeCollapseEvent event) {
        map.put(event.getAssociatedType(), true);
      }
    });
    p.addCollapseHandler(new CollapseHandler() {
      @Override
      public void onCollapse(CollapseEvent event) {
        map.put(event.getAssociatedType(), true);
      }
    });
    RootPanel.get().add(p);
    p.expand();
    p.collapse();
    assertEquals(true, map.get(BeforeCollapseEvent.getType()));
    assertEquals(true, map.get(CollapseEvent.getType()));
  }

  public void testExpandedInit() {
    ContentPanel cp = new ContentPanel();
    cp.setCollapsible(true);
    RootPanel.get().add(cp);
    assertTrue(cp.getHeader().getTool(0).getStyleName().contains(ToolButton.UP.getStyle()));
  }

  public void testNonAnimatedCollapsedInit() {
    ContentPanel cp = new ContentPanel();
    cp.setCollapsible(true);
    cp.setAnimCollapse(false);
    cp.setExpanded(false);
    RootPanel.get().add(cp);
    assertTrue(cp.getHeader().getTool(0).getStyleName().contains(ToolButton.DOWN.getStyle()));
  }

  public void testNonAnimatedExpandedInit() {
    ContentPanel cp = new ContentPanel();
    cp.setCollapsible(true);
    cp.setAnimCollapse(false);
    RootPanel.get().add(cp);
    assertTrue(cp.getHeader().getTool(0).getStyleName().contains(ToolButton.UP.getStyle()));
  }

  public void testNotifyShowHideOnCollapse() {
    final ContentPanel p = new ContentPanel();

    p.setCollapsible(true);
    p.setAnimCollapse(false);

    p.add(new TextButton("test") {
      @Override
      protected void notifyHide() {
        super.notifyHide();
        p.setData("notifyHideCalled", true);
      }

      @Override
      protected void notifyShow() {
        super.notifyShow();
        p.setData("notifyShowCalled", true);
      }
    });
    RootPanel.get().add(p);

    p.collapse();
    assertTrue((Boolean) p.getData("notifyHideCalled"));

    p.expand();
    assertTrue((Boolean) p.getData("notifyShowCalled"));
  }
}
