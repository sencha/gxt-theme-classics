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
import com.sencha.gxt.widget.core.client.event.ActivateEvent;
import com.sencha.gxt.widget.core.client.event.ActivateEvent.ActivateHandler;
import com.sencha.gxt.widget.core.client.event.DeactivateEvent;
import com.sencha.gxt.widget.core.client.event.DeactivateEvent.DeactivateHandler;

public class GwtTestSeparatorMenuItem extends CoreBaseTestCase {

  @SuppressWarnings("rawtypes")
  public void testActivateDeactivate() {
    final Map<Type, Object> map = new HashMap<Type, Object>();

    Menu menu = new Menu();
    SeparatorMenuItem item = new SeparatorMenuItem();
    menu.add(item);
    item.addActivateHandler(new ActivateHandler<Item>() {
      @Override
      public void onActivate(ActivateEvent event) {
        map.put(event.getAssociatedType(), true);
      }
    });
    item.addDeactivateHandler(new DeactivateHandler<Item>() {

      @Override
      public void onDeactivate(DeactivateEvent event) {
        map.put(event.getAssociatedType(), true);
      }
    });
    RootPanel.get().add(menu);
    item.activate(true);
    assertEquals(true, map.get(ActivateEvent.getType()));
    map.clear();

    item.deactivate();
    assertEquals(true, map.get(DeactivateEvent.getType()));
  }

}
