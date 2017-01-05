package com.sencha.gxt.widget.core.client.container;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.core.client.Style.LayoutRegion;
import com.sencha.gxt.core.client.util.EventTracker;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.CoreBaseTestCase;
import com.sencha.gxt.widget.core.client.container.BorderLayoutContainer.BorderLayoutData;
import com.sencha.gxt.widget.core.client.event.CollapseItemEvent;
import com.sencha.gxt.widget.core.client.event.CollapseItemEvent.CollapseItemHandler;
import com.sencha.gxt.widget.core.client.event.ExpandItemEvent;
import com.sencha.gxt.widget.core.client.event.ExpandItemEvent.ExpandItemHandler;

public class GwtTestBorderLayoutContainer extends CoreBaseTestCase {

  public void testContainer() {
    final EventTracker eventTracker = new EventTracker();
    final BorderLayoutContainer con = new BorderLayoutContainer();

    con.addCollapseHandler(new CollapseItemHandler<ContentPanel>() {
      @Override
      public void onCollapse(CollapseItemEvent<ContentPanel> event) {
        eventTracker.addEvent(event.getAssociatedType());
      }
    });
    con.addExpandHandler(new ExpandItemHandler<ContentPanel>() {
      @Override
      public void onExpand(ExpandItemEvent<ContentPanel> event) {
        eventTracker.addEvent(event.getAssociatedType());
      }
    });

    final ContentPanel north = new ContentPanel();
    final ContentPanel west = new ContentPanel();
    final ContentPanel center = new ContentPanel();
    final ContentPanel east = new ContentPanel();
    final ContentPanel south = new ContentPanel();

    BorderLayoutData northData = new BorderLayoutData(100);
    northData.setMargins(new Margins(5));
    northData.setCollapsible(true);
    northData.setSplit(true);

    BorderLayoutData westData = new BorderLayoutData(150);
    westData.setCollapsible(true);
    westData.setSplit(true);
    westData.setCollapseMini(true);
    westData.setMargins(new Margins(0, 5, 0, 5));

    MarginData centerData = new MarginData();

    BorderLayoutData eastData = new BorderLayoutData(150);
    eastData.setMargins(new Margins(0, 5, 0, 5));
    eastData.setCollapsible(true);
    eastData.setSplit(true);

    BorderLayoutData southData = new BorderLayoutData(100);
    southData.setMargins(new Margins(5));
    southData.setCollapsible(true);
    southData.setCollapseMini(true);

    con.setNorthWidget(north, northData);
    con.setWestWidget(west, westData);
    con.setCenterWidget(center, centerData);
    con.setEastWidget(east, eastData);
    con.setSouthWidget(south, southData);

    RootPanel.get().add(con);

    Scheduler.get().scheduleDeferred(new ScheduledCommand() {
      @Override
      public void execute() {
        assertEquals(center, con.getCenterWidget());
        assertEquals(west, con.getWestWidget());
        assertEquals(east, con.getEastWidget());
        assertEquals(south, con.getSouthWidget());
        assertEquals(north, con.getNorthWidget());
        assertEquals(center, con.getRegionWidget(LayoutRegion.CENTER));
        assertEquals(west, con.getRegionWidget(LayoutRegion.WEST));
        assertEquals(east, con.getRegionWidget(LayoutRegion.EAST));
        assertEquals(north, con.getRegionWidget(LayoutRegion.NORTH));
        assertEquals(south, con.getRegionWidget(LayoutRegion.SOUTH));

        assertEquals(0, eventTracker.getCount(CollapseItemEvent.getType()));
        assertFalse(east.isCollapsed());
        con.collapse(LayoutRegion.EAST);
        assertEquals(1, eventTracker.getCount(CollapseItemEvent.getType()));
        assertTrue(east.isCollapsed());

        assertEquals(0, eventTracker.getCount(ExpandItemEvent.getType()));
        con.expand(LayoutRegion.EAST);
        assertEquals(1, eventTracker.getCount(ExpandItemEvent.getType()));
        assertFalse(east.isCollapsed());

        assertTrue(south.isVisible());
        con.hide(LayoutRegion.SOUTH);
        assertFalse(south.isVisible());
        con.show(LayoutRegion.SOUTH);
        assertTrue(south.isVisible());

        finishTest();
      }
    });

    delayTestFinish(200);
  }

  public void testGwtWidgetWithCollapsibleFeatureCollapsibleTrue() {
    // Given BorderLayoutData
    BorderLayoutContainer.BorderLayoutData blclayoutData = new BorderLayoutContainer.BorderLayoutData(200);

    // And setCollapsible is True
    blclayoutData.setCollapsible(true);

    // And border layout container
    BorderLayoutContainer borderLayoutContainer = new BorderLayoutContainer();

    // When ContentPanel is used with collapsible
    // Then content panel did not throw an assertion
    borderLayoutContainer.setNorthWidget(new ContentPanel(), blclayoutData);
    
    // When VerticalPanel a non content panel is used with collapsible, it will throw
    // unless assertions are disabled
    if (!BorderLayoutContainer.class.desiredAssertionStatus()) {
      return;
    }
    try {
      borderLayoutContainer.setNorthWidget(new VerticalPanel(), blclayoutData);
      fail();
    } catch (AssertionError e) {
      // Then an assertion is thrown
      String error = e.getMessage();
      assertTrue(error.contains("In order"));
      assertTrue(error.contains("setCollapsible(true)"));
    }
  }

  public void testGwtWidgetWithCollapsibleFeatureCollaspsibleFalse() {
    // Given BorderLayoutData
    BorderLayoutContainer.BorderLayoutData blclayoutData = new BorderLayoutContainer.BorderLayoutData(200);

    // And this is False
    blclayoutData.setCollapsible(false);

    // And border layout container
    BorderLayoutContainer borderLayoutContainer = new BorderLayoutContainer();

    // When ContentPanel is used with collapsible
    // Then content panel did not throw an assertion
    borderLayoutContainer.setNorthWidget(new ContentPanel(), blclayoutData);
   
    // When VerticalPanel is used with collapsible
    // Then assertion is not thrown
    borderLayoutContainer.setNorthWidget(new VerticalPanel(), blclayoutData);
  }

  public class MyContentPanelWidget implements IsWidget {
    private ContentPanel contentPanel;
    @Override
    public Widget asWidget() {
      if (contentPanel == null) {
        contentPanel = new ContentPanel();
      }
      return contentPanel;
    }
  }

  public void testIsWidgetContentPanel() {
    // Given layout data is collapsible, meaning it widget has to be a ContentPanel
    BorderLayoutData layoutData = new BorderLayoutData();
    layoutData.setCollapsible(true);

    // And having a BLC
    BorderLayoutContainer blc = new BorderLayoutContainer();
    try {
      // When setting a ContentPanel widget, wrapped in IsWidget,
      // Then it should not throw an assertion error, b/c ContentPanel is needed
      blc.setWestWidget(new MyContentPanelWidget(), layoutData);
    } catch (AssertionError e) {
      // This would throw if not doing widget.asWidget() in assertion condition
      fail();
    }
  }

}
