package com.sencha.gxt.widget.core.client.button;

import java.util.HashMap;
import java.util.Map;

import com.google.gwt.event.shared.GwtEvent.Type;
import com.google.gwt.user.client.ui.RootPanel;
import com.sencha.gxt.widget.core.client.CoreBaseTestCase;
import com.sencha.gxt.widget.core.client.event.AddEvent;
import com.sencha.gxt.widget.core.client.event.AddEvent.AddHandler;
import com.sencha.gxt.widget.core.client.event.RemoveEvent;
import com.sencha.gxt.widget.core.client.event.RemoveEvent.RemoveHandler;

public class GwtTestButtonBar extends CoreBaseTestCase {

  public void testClear() {
    ButtonBar bar = new ButtonBar();
    bar.setEnableOverflow(false); // overflow calcs wrong in unit test
    bar.add(new TextButton("Button"));
    
    assertEquals(1, bar.getWidgetCount());
    bar.clear();
  
    assertEquals(0, bar.getWidgetCount());
    
    RootPanel.get().add(bar);
    
    bar.add(new TextButton("Button"));
    bar.add(new TextButton("Button"));
    
    // 2 buttons
    assertEquals(2, bar.getWidgetCount());
    bar.clear();
  
    assertEquals(0, bar.getWidgetCount());
  }
  
  @SuppressWarnings("rawtypes")
  public void testButtonBar() {
    final Map<Type, Object> map = new HashMap<Type, Object>();

    ButtonBar bar = new ButtonBar();
    bar.addAddHandler(new AddHandler() {
      @Override
      public void onAdd(AddEvent event) {
        map.put(event.getAssociatedType(), true);
      }
    });
    bar.addRemoveHandler(new RemoveHandler() {
      @Override
      public void onRemove(RemoveEvent event) {
        map.put(event.getAssociatedType(), true);
      }
    });

    TextButton btn1 = new TextButton();
    TextButton btn2 = new TextButton();
    TextButton btn3 = new TextButton();

    bar.add(btn1);
    bar.add(btn2);

    assertEquals(2, bar.getWidgetCount());

    // after render we get the fill tool item added
    RootPanel.get().add(bar);

    bar.remove(btn1);
    
    // 1 button
    assertEquals(1, bar.getWidgetCount());

    bar.add(btn3);
    
    // 2 buttons
    assertEquals(2, bar.getWidgetCount());

    assertEquals(true, map.get(AddEvent.getType()));
    assertEquals(true, map.get(RemoveEvent.getType()));
  }

}
