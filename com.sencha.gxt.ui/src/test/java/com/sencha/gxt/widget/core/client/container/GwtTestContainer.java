package com.sencha.gxt.widget.core.client.container;

import com.google.gwt.user.client.ui.RootPanel;
import com.sencha.gxt.core.client.dom.XElement;
import com.sencha.gxt.core.client.util.EventTracker;
import com.sencha.gxt.widget.core.client.CoreBaseTestCase;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.event.AddEvent;
import com.sencha.gxt.widget.core.client.event.BeforeAddEvent;
import com.sencha.gxt.widget.core.client.event.BeforeRemoveEvent;
import com.sencha.gxt.widget.core.client.event.ContainerHandler;
import com.sencha.gxt.widget.core.client.event.RemoveEvent;

public class GwtTestContainer extends CoreBaseTestCase {

  public void testContainerEvents() {
    final EventTracker eventTracker = new EventTracker();

    FlowLayoutContainer container = new FlowLayoutContainer();

    container.addContainerHandler(new ContainerHandler() {

      @Override
      public void onAdd(AddEvent event) {
        eventTracker.addEvent(event.getAssociatedType());
      }

      @Override
      public void onBeforeAdd(BeforeAddEvent event) {
        eventTracker.addEvent(event.getAssociatedType());
      }

      @Override
      public void onBeforeRemove(BeforeRemoveEvent event) {
        eventTracker.addEvent(event.getAssociatedType());
      }

      @Override
      public void onRemove(RemoveEvent event) {
        eventTracker.addEvent(event.getAssociatedType());
      }
    });

    TextButton button = new TextButton();
    container.add(button);
    container.remove(button);

    assertEquals(1, eventTracker.getCount(RemoveEvent.getType()));
    assertEquals(1, eventTracker.getCount(BeforeRemoveEvent.getType()));
    assertEquals(1, eventTracker.getCount(AddEvent.getType()));
    assertEquals(1, eventTracker.getCount(BeforeAddEvent.getType()));
  }

  public void testEnableDisable() {
    TextButton button = new TextButton();

    FlowLayoutContainer container = new FlowLayoutContainer();
    container.add(button);

    assertEquals(true, button.isEnabled());

    RootPanel.get().add(container);

    container.disable();

    assertEquals(false, button.isEnabled());

    container.enable();

    assertEquals(true, button.isEnabled());

  }

  public void testClear() {
    TextButton button1 = new TextButton();
    TextButton button2 = new TextButton();

    FlowLayoutContainer container = new FlowLayoutContainer();

    assertEquals(0, container.getWidgetCount());

    container.add(button1);
    container.add(button2);

    assertEquals(2, container.getWidgetCount());

    container.clear();

    assertEquals(0, container.getWidgetCount());

  }

  public void testAddRemove() {
    String id = "TestId";
    TextButton button1 = new TextButton();
    TextButton button2 = new TextButton();
    button2.setId(id);

    XElement elem = button1.getElement();

    FlowLayoutContainer container = new FlowLayoutContainer();
    RootPanel.get().add(container);
    assertNull(container.findWidget(elem));
    assertNull(container.getItemByItemId(id));
    assertEquals(-1, container.getWidgetIndex(button2));

    container.add(button1);
    container.add(button2);

    assertTrue(button1.isAttached());
    assertTrue(button2.isAttached());
    assertEquals(button1, container.findWidget(elem));
    assertEquals(button2, container.getItemByItemId(id));
    assertEquals(button2, container.getWidget(1));
    assertEquals(1, container.getWidgetIndex(button2));
    assertEquals(container, button2.getParent());

    container.remove(0);

    assertFalse(button1.isAttached());
    assertEquals(-1, container.getWidgetIndex(button1));
    assertEquals(1, container.getWidgetCount());

    container.remove(button2);

    assertFalse(button2.isAttached());
    assertEquals(0, container.getWidgetCount());
    assertNull(button2.getParent());

  }

}
