package com.sencha.gxt.widget.core.client.menu;

import java.util.HashMap;
import java.util.Map;

import com.google.gwt.event.logical.shared.BeforeSelectionEvent;
import com.google.gwt.event.logical.shared.BeforeSelectionHandler;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.event.shared.GwtEvent.Type;
import com.google.gwt.user.client.ui.RootPanel;
import com.sencha.gxt.widget.core.client.CoreBaseTestCase;
import com.sencha.gxt.widget.core.client.event.BeforeHideEvent;
import com.sencha.gxt.widget.core.client.event.BeforeHideEvent.BeforeHideHandler;
import com.sencha.gxt.widget.core.client.event.BeforeShowEvent;
import com.sencha.gxt.widget.core.client.event.BeforeShowEvent.BeforeShowHandler;
import com.sencha.gxt.widget.core.client.event.HideEvent;
import com.sencha.gxt.widget.core.client.event.HideEvent.HideHandler;
import com.sencha.gxt.widget.core.client.event.ShowEvent;
import com.sencha.gxt.widget.core.client.event.ShowEvent.ShowHandler;

public class GwtTestMenu extends CoreBaseTestCase {

  @SuppressWarnings("rawtypes")
  public void testEvents() {
    final Map<Type, Object> map = new HashMap<Type, Object>();
    Menu menu = new Menu();

    menu.addBeforeShowHandler(new BeforeShowHandler() {
      @Override
      public void onBeforeShow(BeforeShowEvent event) {
        map.put(event.getAssociatedType(), true);
      }
    });
    menu.addShowHandler(new ShowHandler() {
      @Override
      public void onShow(ShowEvent event) {
        map.put(event.getAssociatedType(), true);
      }
    });
    menu.addBeforeHideHandler(new BeforeHideHandler() {
      @Override
      public void onBeforeHide(BeforeHideEvent event) {
        map.put(event.getAssociatedType(), true);
      }
    });
    menu.addHideHandler(new HideHandler() {
      @Override
      public void onHide(HideEvent event) {
        map.put(event.getAssociatedType(), true);
      }
    });

    menu.showAt(100, 100);

    assertEquals(true, map.get(BeforeShowEvent.getType()));
    assertEquals(true, map.get(ShowEvent.getType()));

    menu.hide();

    assertEquals(true, map.get(BeforeHideEvent.getType()));
    assertEquals(true, map.get(HideEvent.getType()));
  }

  @SuppressWarnings("rawtypes")
  public void testHideCancel() {
    final Map<Type, Object> map = new HashMap<Type, Object>();
    Menu menu = new Menu();

    menu.addBeforeHideHandler(new BeforeHideHandler() {
      @Override
      public void onBeforeHide(BeforeHideEvent event) {
        map.put(event.getAssociatedType(), true);
        event.setCancelled(true);
      }
    });
    menu.addHideHandler(new HideHandler() {
      @Override
      public void onHide(HideEvent event) {
        map.put(event.getAssociatedType(), true);
      }
    });

    menu.showAt(100, 100);
    menu.hide();

    assertEquals(true, map.get(BeforeHideEvent.getType()));
    assertNull(map.get(HideEvent.getType()));
  }

  @SuppressWarnings("rawtypes")
  public void testShowCancel() {
    final Map<Type, Object> map = new HashMap<Type, Object>();
    Menu menu = new Menu();

    menu.addBeforeShowHandler(new BeforeShowHandler() {
      @Override
      public void onBeforeShow(BeforeShowEvent event) {
        map.put(event.getAssociatedType(), true);
        event.setCancelled(true);
      }
    });
    menu.addShowHandler(new ShowHandler() {
      @Override
      public void onShow(ShowEvent event) {
        map.put(event.getAssociatedType(), true);
      }
    });

    menu.showAt(100, 100);

    assertEquals(true, map.get(BeforeShowEvent.getType()));
    assertNull(map.get(ShowEvent.getType()));
  }

  @SuppressWarnings("rawtypes")
  public void testSelection() {
    final Map<Type, Object> map = new HashMap<Type, Object>();

    Menu menu = new Menu();
    MenuItem item = new MenuItem();
    menu.add(item);
    menu.addSelectionHandler(new SelectionHandler<Item>() {

      @Override
      public void onSelection(SelectionEvent event) {
        map.put(event.getAssociatedType(), true);
      }
    });
    menu.addBeforeSelectionHandler(new BeforeSelectionHandler<Item>() {

      @Override
      public void onBeforeSelection(BeforeSelectionEvent event) {
        map.put(event.getAssociatedType(), true);
      }
    });
    RootPanel.get().add(menu);
    item.getElement().click();
    assertEquals(true, map.get(BeforeSelectionEvent.getType()));
    assertEquals(true, map.get(SelectionEvent.getType()));

  }

  public void testColorMenu() {
    ColorMenu menu = new ColorMenu();
    menu.setColor("ffffff");
    
    assertEquals("ffffff", menu.getColor());
    
    RootPanel.get().add(menu);
  }
}
