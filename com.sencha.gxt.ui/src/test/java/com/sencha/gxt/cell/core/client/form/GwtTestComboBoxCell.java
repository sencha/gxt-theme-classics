package com.sencha.gxt.cell.core.client.form;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.google.gwt.cell.client.Cell.Context;
import com.google.gwt.core.client.Callback;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.event.logical.shared.BeforeSelectionEvent;
import com.google.gwt.event.logical.shared.BeforeSelectionHandler;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.junit.DoNotRunWith;
import com.google.gwt.junit.Platform;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.RootPanel;
import com.sencha.gxt.cell.core.client.CellBaseTestCase;
import com.sencha.gxt.core.client.IdentityValueProvider;
import com.sencha.gxt.core.client.dom.XElement;
import com.sencha.gxt.core.client.util.EventTracker;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.StringLabelProvider;
import com.sencha.gxt.data.shared.loader.DataProxy;
import com.sencha.gxt.data.shared.loader.ListLoadConfigBean;
import com.sencha.gxt.data.shared.loader.ListLoadResult;
import com.sencha.gxt.data.shared.loader.ListLoadResultBean;
import com.sencha.gxt.data.shared.loader.ListLoader;
import com.sencha.gxt.data.shared.loader.LoadEvent;
import com.sencha.gxt.data.shared.loader.LoadHandler;
import com.sencha.gxt.ui.resources.client.TestData;
import com.sencha.gxt.ui.resources.client.model.Stock;
import com.sencha.gxt.ui.resources.client.model.StockProperties;
import com.sencha.gxt.widget.core.client.event.CellBeforeSelectionEvent;
import com.sencha.gxt.widget.core.client.event.CellSelectionEvent;
import com.sencha.gxt.widget.core.client.event.ViewReadyEvent;
import com.sencha.gxt.widget.core.client.event.ViewReadyEvent.ViewReadyHandler;
import com.sencha.gxt.widget.core.client.form.ComboBox;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;

public class GwtTestComboBoxCell extends CellBaseTestCase {

  public void testComboBoxCell() {
    final EventTracker tracker = new EventTracker();

    StockProperties props = GWT.create(StockProperties.class);

    IdentityValueProvider<Stock> identity = new IdentityValueProvider<Stock>();

    ColumnConfig<Stock, Stock> nameCol = new ColumnConfig<Stock, Stock>(identity, 200, "Company");

    ListStore<Stock> cellStore = new ListStore<Stock>(props.key());
    final ComboBoxCell<Stock> cell = new ComboBoxCell<Stock>(cellStore, new StringLabelProvider<Stock>());
    cell.addBeforeSelectionHandler(new BeforeSelectionHandler<Stock>() {

      @Override
      public void onBeforeSelection(BeforeSelectionEvent<Stock> event) {
        CellBeforeSelectionEvent<Stock> cellEvent = (CellBeforeSelectionEvent<Stock>) event;
        assertEquals(0, cellEvent.getContext().getIndex());
        tracker.addEvent(CellBeforeSelectionEvent.getType());
      }
    });
    cell.addSelectionHandler(new SelectionHandler<Stock>() {

      @Override
      public void onSelection(SelectionEvent<Stock> event) {
        CellSelectionEvent<Stock> cellEvent = (CellSelectionEvent<Stock>) event;
        assertEquals(0, cellEvent.getContext().getIndex());
        tracker.addEvent(CellSelectionEvent.getType());
      }
    });
    nameCol.setCell(cell);

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

    delayTestFinish(5000);
    grid.addViewReadyHandler(new ViewReadyHandler() {
      @Override
      public void onViewReady(ViewReadyEvent event) {
        Context c = new Context(0, 0, store.getKeyProvider().getKey(store.get(0)));
        cell.expand(c, grid.getView().getCell(0, 0).<XElement> cast(), null, store.get(0));

        onSelect(cell, store.get(0));

        assertEquals(1, tracker.getCount(CellBeforeSelectionEvent.getType()));
        assertEquals(1, tracker.getCount(CellSelectionEvent.getType()));

        finishTest();
      }
    });

    RootPanel.get().add(grid);

    assertEquals(true, grid.isAttached());
  }

  public void testSelectedItemNoLongerInStore() {
    StockProperties props = GWT.create(StockProperties.class);

    ListStore<Stock> cellStore = new ListStore<Stock>(props.key());

    ComboBox<Stock> combo = new ComboBox<Stock>(cellStore, new StringLabelProvider<Stock>());

    delayTestFinish(5000);
    RootPanel.get().add(combo);

    assertEquals(true, combo.isAttached());

    combo.focus();
    combo.setText("foo");
    combo.expand();

    cellStore.replaceAll(TestData.getShortStocks());

    assertTrue(combo.isExpanded());
    combo.select(cellStore.get(3));

    assertTrue(combo.isExpanded());

    // replacing with same data, but because Stock does not have an equals method
    // they appear as different objects, so the selected item can no longer be found
    cellStore.replaceAll(TestData.getShortStocks());

    Scheduler.get().scheduleDeferred(new ScheduledCommand() {
      @Override
      public void execute() {
        finishTest();
      }
    });
  }

  /**
   * When no loading text is configured, the combobox will not expand until data is loaded
   */
  @DoNotRunWith(Platform.HtmlUnitLayout)
  public void testLoadingWithOutLoadingText() {
    // Given ComboBox with simple configuration, and no loading text configured
    StockProperties props = GWT.create(StockProperties.class);
    ListStore<Stock> store = new ListStore<Stock>(props.key());
    store.addAll(TestData.getShortStocks());
    final ComboBox<Stock> combo = new ComboBox<Stock>(store, new StringLabelProvider<Stock>());

    RootPanel.get().add(combo);

    // And dataProxy for loader, with slow loading timer
    DataProxy<ListLoadConfigBean, ListLoadResult<Stock>> dataProxy = new DataProxy<ListLoadConfigBean, ListLoadResult<Stock>>() {
      @Override
      public void load(ListLoadConfigBean loadConfig, final Callback<ListLoadResult<Stock>, Throwable> callback) {
        Timer t = new Timer() {
          @Override
          public void run() {
            ListLoadResultBean<Stock> loadResultBean = new ListLoadResultBean<Stock>();
            List<Stock> results = new LinkedList<Stock>();
            results.add(TestData.getCompanies().get(0));
            loadResultBean.setData(results);
            callback.onSuccess(loadResultBean);
          }
        };
        t.schedule(1000);
      }
    };

    // And combo has a loader and No loading text configured
    ListLoader<ListLoadConfigBean, ListLoadResult<Stock>> loader = new ListLoader<ListLoadConfigBean, ListLoadResult<Stock>>(
        dataProxy);
    combo.setLoader(loader);
    
    // Then on loaded the combox is expanded
    loader.addLoadHandler(new LoadHandler<ListLoadConfigBean, ListLoadResult<Stock>>() {
      @Override
      public void onLoad(LoadEvent<ListLoadConfigBean, ListLoadResult<Stock>> event) {
        Scheduler.get().scheduleDeferred(new Command() {
          @Override
          public void execute() {
            assertTrue(combo.isExpanded());
            finishTest();
          }
        });
      }
    });

    // When expand is called
    combo.setMinChars(0);

    combo.focus();

    combo.doQuery("c", false);

    // Then the the combo is not expanded yet, b/c the data hasn't loaded
    Scheduler.get().scheduleDeferred(new Command() {
      @Override
      public void execute() {
        assertFalse(combo.isExpanded());
      }
    });

    delayTestFinish(3000);
  }
  
  /**
   * When loading text is configured, the combobox will not expand until data is loaded
   */
  public void testLoadingWithLoadingText() {
    // Given ComboBox with simple configuration, with loading text configured
    StockProperties props = GWT.create(StockProperties.class);
    ListStore<Stock> store = new ListStore<Stock>(props.key());
    store.addAll(TestData.getShortStocks());
    final ComboBox<Stock> combo = new ComboBox<Stock>(store, new StringLabelProvider<Stock>());

    // And dataProxy for loader, with slow loading timer
    DataProxy<ListLoadConfigBean, ListLoadResult<Stock>> dataProxy = new DataProxy<ListLoadConfigBean, ListLoadResult<Stock>>() {
      @Override
      public void load(ListLoadConfigBean loadConfig, final Callback<ListLoadResult<Stock>, Throwable> callback) {
        Timer t = new Timer() {
          @Override
          public void run() {
            ListLoadResultBean<Stock> loadResultBean = new ListLoadResultBean<Stock>();
            List<Stock> results = new LinkedList<Stock>();
            results.add(TestData.getCompanies().get(0));
            loadResultBean.setData(results);
            callback.onSuccess(loadResultBean);
          }
        };
        t.schedule(1000);
      }
    };

    // And combo has a loader
    ListLoader<ListLoadConfigBean, ListLoadResult<Stock>> loader = new ListLoader<ListLoadConfigBean, ListLoadResult<Stock>>(
        dataProxy);
    combo.setLoader(loader);
    combo.getListView().setLoadingIndicator("Loading...");
    
    // Then on loaded the combox is expanded
    loader.addLoadHandler(new LoadHandler<ListLoadConfigBean, ListLoadResult<Stock>>() {
      @Override
      public void onLoad(LoadEvent<ListLoadConfigBean, ListLoadResult<Stock>> event) {
        Scheduler.get().scheduleDeferred(new Command() {
          @Override
          public void execute() {
            assertTrue(combo.isExpanded());
            finishTest();
          }
        });
      }
    });

    // When expand is called
    combo.setMinChars(0);
    combo.doQuery("c", false);

    // Then the the combo is expanded
    Scheduler.get().scheduleDeferred(new Command() {
      @Override
      public void execute() {
        assertTrue(combo.isExpanded());
      }
    });

    delayTestFinish(3000);
  }

  private native void onSelect(ComboBoxCell<Stock> combo, Stock model) /*-{
		combo.@com.sencha.gxt.cell.core.client.form.ComboBoxCell::onSelect(Ljava/lang/Object;)(model);
  }-*/;

}
