package com.sencha.gxt.widget.core.client;

import java.util.HashMap;
import java.util.Map;

import com.google.gwt.dom.client.Document;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.event.shared.GwtEvent.Type;
import com.google.gwt.user.client.ui.RootPanel;
import com.sencha.gxt.widget.core.client.event.BeforeHideEvent;
import com.sencha.gxt.widget.core.client.event.BeforeHideEvent.BeforeHideHandler;
import com.sencha.gxt.widget.core.client.event.BeforeShowEvent;
import com.sencha.gxt.widget.core.client.event.BeforeShowEvent.BeforeShowHandler;
import com.sencha.gxt.widget.core.client.event.DisableEvent;
import com.sencha.gxt.widget.core.client.event.DisableEvent.DisableHandler;
import com.sencha.gxt.widget.core.client.event.EnableEvent;
import com.sencha.gxt.widget.core.client.event.EnableEvent.EnableHandler;
import com.sencha.gxt.widget.core.client.event.HideEvent;
import com.sencha.gxt.widget.core.client.event.HideEvent.HideHandler;
import com.sencha.gxt.widget.core.client.event.MoveEvent;
import com.sencha.gxt.widget.core.client.event.MoveEvent.MoveHandler;
import com.sencha.gxt.widget.core.client.event.ShowEvent;
import com.sencha.gxt.widget.core.client.event.ShowEvent.ShowHandler;

public class GwtTestComponent extends CoreBaseTestCase {

  public void testData() {
    Component component = new Component() {
      {
        setElement(Document.get().createDivElement());
      }
    };

    component.setData("string", "string");
    component.setData("num", new Integer(9));

    assertEquals("string", component.getData("string"));
    assertEquals(new Integer(9), component.getData("num"));
  }

  // TODO: focus and blur events
  @SuppressWarnings("rawtypes")
  public void testEnableDisableEvents() {
    final Map<Type, Object> map = new HashMap<Type, Object>();

    Component component = new Component() {
      {
        setElement(Document.get().createDivElement());
      }
    };

    component.addEnableHandler(new EnableHandler() {
      @Override
      public void onEnable(EnableEvent event) {
        map.put(event.getAssociatedType(), true);
      }
    });
    component.addDisableHandler(new DisableHandler() {
      @Override
      public void onDisable(DisableEvent event) {
        map.put(event.getAssociatedType(), true);
      }
    });

    component.enable();
    component.disable();

    assertEquals(true, map.get(EnableEvent.getType()));
    assertEquals(true, map.get(DisableEvent.getType()));
  }

  @SuppressWarnings("rawtypes")
  public void testMoveResizeEvents() {
    final Map<Type, Object> map = new HashMap<Type, Object>();

    Component component = new Component() {
      {
        setElement(Document.get().createDivElement());
      }
    };

    component.addMoveHandler(new MoveHandler() {
      @Override
      public void onMove(MoveEvent event) {
        map.put(event.getAssociatedType(), true);
      }
    });

    component.addResizeHandler(new ResizeHandler() {
      @Override
      public void onResize(ResizeEvent event) {
        map.put(event.getAssociatedType(), true);
      }
    });

    RootPanel.get().add(component);

    component.setPixelSize(50, 50);
    component.setPosition(50, 50);

    assertEquals(true, map.get(MoveEvent.getType()));
    assertEquals(true, map.get(ResizeEvent.getType()));
  }

  @SuppressWarnings("rawtypes")
  public void testShowHideEvents() {
    final Map<Type, Object> map = new HashMap<Type, Object>();

    Component component = new Component() {
      {
        setElement(Document.get().createDivElement());
      }
    };

    component.addBeforeHideHandler(new BeforeHideHandler() {
      @Override
      public void onBeforeHide(BeforeHideEvent event) {
        map.put(event.getAssociatedType(), true);
      }
    });
    component.addHideHandler(new HideHandler() {
      @Override
      public void onHide(HideEvent event) {
        map.put(event.getAssociatedType(), true);
      }
    });
    component.addBeforeShowHandler(new BeforeShowHandler() {
      @Override
      public void onBeforeShow(BeforeShowEvent event) {
        map.put(event.getAssociatedType(), true);
      }
    });
    component.addShowHandler(new ShowHandler() {
      @Override
      public void onShow(ShowEvent event) {
        map.put(event.getAssociatedType(), true);
      }
    });

    component.hide();
    component.show();

    assertEquals(true, map.get(BeforeHideEvent.getType()));
    assertEquals(true, map.get(HideEvent.getType()));
    assertEquals(true, map.get(BeforeShowEvent.getType()));
    assertEquals(true, map.get(ShowEvent.getType()));
  }

  public void testFocusHidden() {
    Component component = new Component() {
      {
        setElement(Document.get().createDivElement());
      }
    };
    
    RootPanel.get().add(component);
    
    component.setVisible(false);
    
    component.focus();
  }
  
}
