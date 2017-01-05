package com.sencha.gxt.widget.core.client.grid;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.RootPanel;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.SortDir;
import com.sencha.gxt.ui.resources.client.TestData;
import com.sencha.gxt.ui.resources.client.model.Stock;
import com.sencha.gxt.ui.resources.client.model.StockProperties;
import com.sencha.gxt.widget.core.client.CoreBaseTestCase;
import com.sencha.gxt.widget.core.client.event.ViewReadyEvent;
import com.sencha.gxt.widget.core.client.event.ViewReadyEvent.ViewReadyHandler;
import com.sencha.gxt.widget.core.client.grid.GridView.GridAppearance;
import com.sencha.gxt.widget.core.client.grid.GroupingView.GroupingViewAppearance;

public class GwtTestGroupingView extends CoreBaseTestCase {

  public void testConstructors() {
    GridAppearance appearance = GWT.create(GridAppearance.class);
    GroupingViewAppearance groupAppearance = GWT.create(GroupingViewAppearance.class);
    
    
    GroupingView<Stock> view = new GroupingView<Stock>();
    assertNotNull(view.getAppearance());
    assertNotNull(view.getGroupingAppearance());
    
    view = new GroupingView<Stock>(groupAppearance);
    assertNotNull(view.getGroupingAppearance());
    assertEquals(groupAppearance, view.getGroupingAppearance());
   
    view = new GroupingView<Stock>(appearance, groupAppearance);
    assertEquals(appearance, view.getAppearance());
    assertEquals(groupAppearance, view.getGroupingAppearance());
  }

  public void testGroupAfterAttach() {
    StockProperties props = GWT.create(StockProperties.class);

    final ColumnConfig<Stock, String> nameCol = new ColumnConfig<Stock, String>(props.name(), 200, "Company");
    ColumnConfig<Stock, String> symbolCol = new ColumnConfig<Stock, String>(props.symbol(), 100, "Symbol");
    ColumnConfig<Stock, Double> lastCol = new ColumnConfig<Stock, Double>(props.last(), 75, "Last");

    List<ColumnConfig<Stock, ?>> l = new ArrayList<ColumnConfig<Stock, ?>>();
    l.add(nameCol);
    l.add(symbolCol);
    l.add(lastCol);

    ColumnModel<Stock> cm = new ColumnModel<Stock>(l);

    ListStore<Stock> store = new ListStore<Stock>(props.key());
    store.addAll(TestData.getShortStocks());

    Grid<Stock> grid = new Grid<Stock>(store, cm);
    final GroupingView<Stock> view = new GroupingView<Stock>();
    grid.setView(view);

    grid.addViewReadyHandler(new ViewReadyHandler() {
      @Override
      public void onViewReady(ViewReadyEvent event) {
        view.groupBy(nameCol);
        finishTest();
      }
    });
    delayTestFinish(2000);
    RootPanel.get().add(grid);
  }

  public void testGroupBeforeAndAfterAttach() {
    StockProperties props = GWT.create(StockProperties.class);

    ColumnConfig<Stock, String> nameCol = new ColumnConfig<Stock, String>(props.name(), 200, "Company");
    final ColumnConfig<Stock, String> symbolCol = new ColumnConfig<Stock, String>(props.symbol(), 100, "Symbol");
    ColumnConfig<Stock, Double> lastCol = new ColumnConfig<Stock, Double>(props.last(), 75, "Last");

    List<ColumnConfig<Stock, ?>> l = new ArrayList<ColumnConfig<Stock, ?>>();
    l.add(nameCol);
    l.add(symbolCol);
    l.add(lastCol);

    ColumnModel<Stock> cm = new ColumnModel<Stock>(l);

    ListStore<Stock> store = new ListStore<Stock>(props.key());
    store.addAll(TestData.getShortStocks());

    Grid<Stock> grid = new Grid<Stock>(store, cm);
    final GroupingView<Stock> view = new GroupingView<Stock>();
    grid.setView(view);

    view.groupBy(nameCol);
    grid.addViewReadyHandler(new ViewReadyHandler() {
      @Override
      public void onViewReady(ViewReadyEvent event) {
        view.groupBy(symbolCol);
        finishTest();
      }
    });
    delayTestFinish(2000);
    RootPanel.get().add(grid);
  }
  
  public void testGroupBeforeAttach() {
    StockProperties props = GWT.create(StockProperties.class);

    ColumnConfig<Stock, String> nameCol = new ColumnConfig<Stock, String>(props.name(), 200, "Company");
    ColumnConfig<Stock, String> symbolCol = new ColumnConfig<Stock, String>(props.symbol(), 100, "Symbol");
    ColumnConfig<Stock, Double> lastCol = new ColumnConfig<Stock, Double>(props.last(), 75, "Last");

    List<ColumnConfig<Stock, ?>> l = new ArrayList<ColumnConfig<Stock, ?>>();
    l.add(nameCol);
    l.add(symbolCol);
    l.add(lastCol);

    ColumnModel<Stock> cm = new ColumnModel<Stock>(l);

    ListStore<Stock> store = new ListStore<Stock>(props.key());
    store.addAll(TestData.getShortStocks());

    final Grid<Stock> grid = new Grid<Stock>(store, cm);
    GroupingView<Stock> view = new GroupingView<Stock>();
    grid.setView(view);

    view.groupBy(nameCol);

    RootPanel.get().add(grid);
  }

  public void testGroupByAfterAttach() {
    StockProperties props = GWT.create(StockProperties.class);

    ColumnConfig<Stock, String> nameCol = new ColumnConfig<Stock, String>(props.name(), 200, "Company");
    final ColumnConfig<Stock, String> symbolCol = new ColumnConfig<Stock, String>(props.symbol(), 100, "Symbol");
    ColumnConfig<Stock, Double> lastCol = new ColumnConfig<Stock, Double>(props.last(), 75, "Last");

    List<ColumnConfig<Stock, ?>> l = new ArrayList<ColumnConfig<Stock, ?>>();
    l.add(nameCol);
    l.add(symbolCol);
    l.add(lastCol);

    ColumnModel<Stock> cm = new ColumnModel<Stock>(l);

    ListStore<Stock> store = new ListStore<Stock>(props.key());
    store.addAll(TestData.getShortStocks());

    Grid<Stock> grid = new Grid<Stock>(store, cm);
    final GroupingView<Stock> view = new GroupingView<Stock>();
    grid.setView(view);

    delayTestFinish(5000);
    RootPanel.get().add(grid);


    view.groupBy(symbolCol);

    new Timer() {
      @Override
      public void run() {
        Element group = view.getGroups().getItem(0);
        assertTrue(view.isExpanded(group));
        
        finishTest();
      }
    }.schedule(2000);
  }

  public void testGroupByNullData() {
    StockProperties props = GWT.create(StockProperties.class);

    ColumnConfig<Stock, String> nameCol = new ColumnConfig<Stock, String>(props.name(), 200, "Company");
    ColumnConfig<Stock, String> symbolCol = new ColumnConfig<Stock, String>(props.symbol(), 100, "Symbol");
    ColumnConfig<Stock, Double> lastCol = new ColumnConfig<Stock, Double>(props.last(), 75, "Last");

    List<ColumnConfig<Stock, ?>> l = new ArrayList<ColumnConfig<Stock, ?>>();
    l.add(nameCol);
    l.add(symbolCol);
    l.add(lastCol);

    ColumnModel<Stock> cm = new ColumnModel<Stock>(l);

    ListStore<Stock> store = new ListStore<Stock>(props.key());
    store.addAll(TestData.getShortStocks());
    store.get(0).setName(null);

    final Grid<Stock> grid = new Grid<Stock>(store, cm);
    GroupingView<Stock> view = new GroupingView<Stock>();
    grid.setView(view);

    view.groupBy(nameCol);

    RootPanel.get().add(grid);
    delayTestFinish(5000);
    new Timer() {
      @Override
      public void run() {
        finishTest();
      }
    }.schedule(2000);
  }
  
  public void testSortBeforeGroupBy() {
    StockProperties props = GWT.create(StockProperties.class);

    ColumnConfig<Stock, String> nameCol = new ColumnConfig<Stock, String>(props.name(), 200, "Company");
    final ColumnConfig<Stock, String> symbolCol = new ColumnConfig<Stock, String>(props.symbol(), 100, "Symbol");
    ColumnConfig<Stock, Double> lastCol = new ColumnConfig<Stock, Double>(props.last(), 75, "Last");

    List<ColumnConfig<Stock, ?>> l = new ArrayList<ColumnConfig<Stock, ?>>();
    l.add(nameCol);
    l.add(symbolCol);
    l.add(lastCol);

    ColumnModel<Stock> cm = new ColumnModel<Stock>(l);

    ListStore<Stock> store = new ListStore<Stock>(props.key());
    store.addAll(TestData.getShortStocks());

    Grid<Stock> grid = new Grid<Stock>(store, cm);
    final GroupingView<Stock> view = new GroupingView<Stock>();
    grid.setView(view);

    grid.addViewReadyHandler(new ViewReadyHandler() {
      @Override
      public void onViewReady(ViewReadyEvent event) {
        view.doSort(0, SortDir.ASC);
        view.groupBy(symbolCol);
        finishTest();
      }
    });
    delayTestFinish(2000);
    RootPanel.get().add(grid);
  }
}
