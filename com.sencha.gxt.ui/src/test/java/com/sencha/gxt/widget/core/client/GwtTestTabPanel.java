package com.sencha.gxt.widget.core.client;

import java.util.HashMap;
import java.util.Map;

import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.event.shared.GwtEvent.Type;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.core.client.dom.XElement;
import com.sencha.gxt.core.client.util.EventTracker;
import com.sencha.gxt.ui.client.UITestCase;
import com.sencha.gxt.widget.core.client.event.AddEvent;
import com.sencha.gxt.widget.core.client.event.AddEvent.AddHandler;
import com.sencha.gxt.widget.core.client.event.BeforeAddEvent;
import com.sencha.gxt.widget.core.client.event.BeforeAddEvent.BeforeAddHandler;
import com.sencha.gxt.widget.core.client.event.BeforeRemoveEvent;
import com.sencha.gxt.widget.core.client.event.BeforeRemoveEvent.BeforeRemoveHandler;
import com.sencha.gxt.widget.core.client.event.RemoveEvent;
import com.sencha.gxt.widget.core.client.event.RemoveEvent.RemoveHandler;

public class GwtTestTabPanel extends UITestCase {

  @SuppressWarnings({"rawtypes"})
  public void testEvents() {
    final Map<Type, Object> map = new HashMap<Type, Object>();

    TabPanel panel = new TabPanel();

    panel.getContainer().addAddHandler(new AddHandler() {
      @Override
      public void onAdd(AddEvent event) {
        map.put(event.getAssociatedType(), true);
      }
    });

    panel.getContainer().addBeforeAddHandler(new BeforeAddHandler() {

      @Override
      public void onBeforeAdd(BeforeAddEvent event) {
        map.put(event.getAssociatedType(), true);
      }

    });
    Label item = new Label();
    panel.add(item, "Tab");
    panel.setActiveWidget(item);

    RootPanel.get().add(panel);

    assertEquals(true, map.get(AddEvent.getType()));
    assertEquals(true, map.get(BeforeAddEvent.getType()));
    map.clear();

    panel.getContainer().addBeforeRemoveHandler(new BeforeRemoveHandler() {

      @Override
      public void onBeforeRemove(BeforeRemoveEvent event) {
        map.put(event.getAssociatedType(), true);
      }
    });
    panel.remove(item);
    assertEquals(true, map.get(BeforeRemoveEvent.getType()));

  }

  public void testFindItem() {
    Widget a = new Label();
    Widget b = new Label();
    b.getElement().setId("abc");
    Widget c = new Label();

    TabPanel panel = new TabPanel();

    panel.add(a, "Title For A");
    panel.add(b, "Title For B");
    panel.add(c, "Title For C");

    assertSame(a, panel.findItem("Title For A", true));
    assertNull(panel.findItem("Title For A", false));

    assertSame(b, panel.findItem("abc", true));
    assertSame(b, panel.findItem("Title For B", true));

    assertSame(b, panel.findItem("abc", false));
    assertNull(panel.findItem("Title For B", false));
  }

  public void testFireEvents() {
    final EventTracker tracker = new EventTracker();

    TabPanel tp = new TabPanel();

    Label item = new Label();
    Label item2 = new Label();

    tp.add(item, "one");
    tp.add(item2, "two");

    tp.addSelectionHandler(new SelectionHandler<Widget>() {

      @Override
      public void onSelection(SelectionEvent<Widget> event) {
        tracker.addEvent(SelectionEvent.getType());
      }
    });

    tp.setActiveWidget(item2);
    tp.setActiveWidget(item, false);

    assertEquals(1, tracker.getCount(SelectionEvent.getType()));
    
    tp.setActiveWidget(item2, true);
    
    assertEquals(2, tracker.getCount(SelectionEvent.getType()));
  }

  public void testHtmlItemAdd() {
    TabPanel tp = new TabPanel();
    Label item = new Label();
    TabItemConfig cfg = new TabItemConfig();
    cfg.setHTML("te <i>st</i>");
    tp.add(item, cfg);

    // assuming TabPanelBaseAppearance
    XElement header = tp.getElement().child("em");
    assertEquals("te st", header.getInnerText());
    assertEquals("st", header.child("i").getInnerHTML());
  }

  public void testHtmlItemUpdate() {
    TabPanel tp = new TabPanel();
    Label item = new Label();
    TabItemConfig cfg = new TabItemConfig("test");
    tp.add(item, cfg);

    RootPanel.get().add(tp);

    cfg.setHTML("te <i>st</i>");
    tp.update(item, cfg);
    // assuming TabPanelBaseAppearance
    XElement header = tp.getElement().child("em");
    assertEquals("te st", header.getInnerText());
    assertEquals("st", header.child("i").getInnerHTML());
  }

  @SuppressWarnings("rawtypes")
  public void testRemove() {
    final Map<Type, Object> map = new HashMap<Type, Object>();

    TabPanel panel = new TabPanel();

    final Label item1 = new Label();
    panel.add(item1, "Tab 1");

    panel.getContainer().addRemoveHandler(new RemoveHandler() {
      @Override
      public void onRemove(RemoveEvent event) {
        map.put(event.getAssociatedType(), true);
        assertTrue(event.getWidget() == item1);
      }
    });

    panel.remove(item1);

    assertEquals(true, map.get(RemoveEvent.getType()));
  }

  public void testTabPanel() {
    TabPanel panel = new TabPanel();

    Label item1 = new Label();
    panel.add(item1, "Tab 1");

    assertTrue(panel.getWidgetCount() == 1);
    assertTrue(panel.getWidget(0) == item1);
    assertEquals(0, panel.getWidgetIndex(item1));

    Label item2 = new Label();
    panel.add(item2, "Tab 2");

    panel.remove(item1);

    assertTrue(panel.getWidgetCount() == 1);
  }

  public void testTextItemAdd() {
    TabPanel tp = new TabPanel();
    Label item = new Label();
    tp.add(item, "te <i>st</i>");

    // assuming TabPanelBaseAppearance
    XElement header = tp.getElement().child("em");
    assertEquals("te <i>st</i>", header.getInnerText());
  }

  public void testTextItemUpdate() {
    TabPanel tp = new TabPanel();
    Label item = new Label();
    TabItemConfig cfg = new TabItemConfig("test");
    tp.add(item, cfg);

    cfg.setHTML("te <i>st</i>");
    tp.update(item, cfg);
    // assuming TabPanelBaseAppearance
    XElement header = tp.getElement().child("em");
    assertEquals("te st", header.getInnerText());
  }

}
