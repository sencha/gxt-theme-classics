package com.sencha.gxt.widget.core.client.menu;

import java.util.HashMap;
import java.util.Map;

import com.google.gwt.event.shared.GwtEvent.Type;
import com.google.gwt.user.client.ui.RootPanel;
import com.sencha.gxt.widget.core.client.CoreBaseTestCase;
import com.sencha.gxt.widget.core.client.event.BeforeCheckChangeEvent;
import com.sencha.gxt.widget.core.client.event.BeforeCheckChangeEvent.BeforeCheckChangeHandler;
import com.sencha.gxt.widget.core.client.event.CheckChangeEvent;
import com.sencha.gxt.widget.core.client.event.CheckChangeEvent.CheckChangeHandler;

public class GwtTestCheckMenuItem extends CoreBaseTestCase {
  @SuppressWarnings("rawtypes")
  public void testMinMax() {
    final Map<Type, Object> map = new HashMap<Type, Object>();

    CheckMenuItem item = new CheckMenuItem("foo");
    item.addBeforeCheckChangeHandler(new BeforeCheckChangeHandler<CheckMenuItem>() {

      @Override
      public void onBeforeCheckChange(BeforeCheckChangeEvent event) {
        map.put(event.getAssociatedType(), true);
      }
    });
    item.addCheckChangeHandler(new CheckChangeHandler<CheckMenuItem>() {

      @Override
      public void onCheckChange(CheckChangeEvent event) {
        map.put(event.getAssociatedType(), true);
      }
    });

    RootPanel.get().add(item);
    item.setChecked(true, false);
    assertEquals(true, map.get(CheckChangeEvent.getType()));
    assertEquals(true, map.get(BeforeCheckChangeEvent.getType()));

  }
  
  @SuppressWarnings("rawtypes")
  public void testMinMax2() {
    final Map<Type, Object> map = new HashMap<Type, Object>();

    CheckMenuItem item = new CheckMenuItem("foo");
    item.addBeforeCheckChangeHandler(new BeforeCheckChangeHandler<CheckMenuItem>() {

      @Override
      public void onBeforeCheckChange(BeforeCheckChangeEvent event) {
        map.put(event.getAssociatedType(), true);
      }
    });
    item.addCheckChangeHandler(new CheckChangeHandler<CheckMenuItem>() {

      @Override
      public void onCheckChange(CheckChangeEvent event) {
        map.put(event.getAssociatedType(), true);
      }
    });
    item.setChecked(true, false);

    RootPanel.get().add(item);
    assertEquals(true, map.get(CheckChangeEvent.getType()));
    assertEquals(true, map.get(BeforeCheckChangeEvent.getType()));

  }

}
