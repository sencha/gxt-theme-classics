package com.sencha.gxt.widget.core.client.grid;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.RootPanel;
import com.sencha.gxt.core.client.util.DateWrapper;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.ui.resources.client.TestData;
import com.sencha.gxt.ui.resources.client.model.Stock;
import com.sencha.gxt.ui.resources.client.model.StockProperties;
import com.sencha.gxt.widget.core.client.CoreBaseTestCase;
import com.sencha.gxt.widget.core.client.form.NumberPropertyEditor.DoublePropertyEditor;
import com.sencha.gxt.widget.core.client.grid.filters.DateFilter;
import com.sencha.gxt.widget.core.client.grid.filters.GridFilters;
import com.sencha.gxt.widget.core.client.grid.filters.NumericFilter;
import com.sencha.gxt.widget.core.client.grid.filters.StringFilter;

public class GwtTestFilterGrid extends CoreBaseTestCase {

  public void testGrid() {
    StockProperties props = GWT.create(StockProperties.class);

    ColumnConfig<Stock, String> nameCol = new ColumnConfig<Stock, String>(props.name(), 200, "Company");
    ColumnConfig<Stock, String> symbolCol = new ColumnConfig<Stock, String>(props.symbol(), 100, "Symbol");
    ColumnConfig<Stock, Double> lastCol = new ColumnConfig<Stock, Double>(props.last(), 75, "Last");

    List<ColumnConfig<Stock, ?>> l = new ArrayList<ColumnConfig<Stock, ?>>();
    l.add(nameCol);
    l.add(symbolCol);
    l.add(lastCol);

    ColumnModel<Stock> cm = new ColumnModel<Stock>(l);

    final ListStore<Stock> store = new ListStore<Stock>(props.key());
    store.addAll(TestData.getShortStocks());

    final Grid<Stock> grid = new Grid<Stock>(store, cm);
    grid.setBorders(false);
    grid.getView().setAutoExpandColumn(nameCol);

    NumericFilter<Stock, Double> lastFilter = new NumericFilter<Stock, Double>(props.last(), new DoublePropertyEditor());
    StringFilter<Stock> nameFilter = new StringFilter<Stock>(props.name());
    DateFilter<Stock> dateFilter = new DateFilter<Stock>(props.lastTrans());
    dateFilter.setMinDate(new DateWrapper().addDays(-5).asDate());
    dateFilter.setMaxDate(new DateWrapper().addMonths(2).asDate());

    GridFilters<Stock> filters = new GridFilters<Stock>();
    filters.initPlugin(grid);
    filters.setLocal(true);
    filters.addFilter(lastFilter);
    filters.addFilter(nameFilter);
    filters.addFilter(dateFilter);

    nameFilter.setValue("A");
    nameFilter.setActive(true, false);

    lastFilter.setGreaterThanValue(100D);
    lastFilter.setActive(true, false);

    Timer t = new Timer() {

      @Override
      public void run() {
        // will be 1 based on known test stock data
        assertEquals(store.size(), 1);
        finishTest();
      }
    };
    t.schedule(3000);

    delayTestFinish(5000);

    RootPanel.get().add(grid);

    assertEquals(true, grid.isAttached());
  }

  public void testGridFiltersPostRender() {
    StockProperties props = GWT.create(StockProperties.class);

    ColumnConfig<Stock, String> nameCol = new ColumnConfig<Stock, String>(props.name(), 200, "Company");
    ColumnConfig<Stock, String> symbolCol = new ColumnConfig<Stock, String>(props.symbol(), 100, "Symbol");
    ColumnConfig<Stock, Double> lastCol = new ColumnConfig<Stock, Double>(props.last(), 75, "Last");

    List<ColumnConfig<Stock, ?>> l = new ArrayList<ColumnConfig<Stock, ?>>();
    l.add(nameCol);
    l.add(symbolCol);
    l.add(lastCol);

    ColumnModel<Stock> cm = new ColumnModel<Stock>(l);

    final ListStore<Stock> store = new ListStore<Stock>(props.key());
    store.addAll(TestData.getShortStocks());

    final Grid<Stock> grid = new Grid<Stock>(store, cm);


    final NumericFilter<Stock, Double> lastFilter = new NumericFilter<Stock, Double>(props.last(),
        new DoublePropertyEditor());
    final StringFilter<Stock> nameFilter = new StringFilter<Stock>(props.name());

    final GridFilters<Stock> filters = new GridFilters<Stock>();
    filters.initPlugin(grid);
    filters.setLocal(true);
    filters.addFilter(lastFilter);
    filters.addFilter(nameFilter);

    Timer t = new Timer() {

      @Override
      public void run() {
        assertEquals(store.size(), 11);

        nameFilter.setValue("A");
        nameFilter.setActive(true, false);

        lastFilter.setGreaterThanValue(100D);
        lastFilter.setActive(true, false);

        filters.reload();

        assertEquals(store.size(), 1);

        nameFilter.setValue(null);
        lastFilter.setGreaterThanValue(null);

        filters.reload();
        assertEquals(store.size(), 11);

        finishTest();
      }
    };
    t.schedule(3000);

    delayTestFinish(5000);

    RootPanel.get().add(grid);

    assertEquals(true, grid.isAttached());
  }
}
