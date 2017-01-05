package com.sencha.gxt.widget.core.client.grid;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.junit.DoNotRunWith;
import com.google.gwt.junit.Platform;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.ui.RootPanel;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.Store;
import com.sencha.gxt.ui.resources.client.TestData;
import com.sencha.gxt.ui.resources.client.model.Stock;
import com.sencha.gxt.ui.resources.client.model.StockProperties;
import com.sencha.gxt.widget.core.client.CoreBaseTestCase;
import com.sencha.gxt.widget.core.client.container.Viewport;
import com.sencha.gxt.widget.core.client.event.ViewReadyEvent;
import com.sencha.gxt.widget.core.client.event.ViewReadyEvent.ViewReadyHandler;
import com.sencha.gxt.widget.core.client.form.FieldSet;
import com.sencha.gxt.widget.core.client.selection.CellSelectionChangedEvent;
import com.sencha.gxt.widget.core.client.selection.CellSelectionChangedEvent.CellSelectionChangedHandler;
import com.sencha.gxt.widget.core.client.selection.SelectionChangedEvent;
import com.sencha.gxt.widget.core.client.selection.SelectionChangedEvent.SelectionChangedHandler;

public class GwtTestGrid extends CoreBaseTestCase {

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

    ListStore<Stock> store = new ListStore<Stock>(props.key());
    store.addAll(TestData.getShortStocks());

    final Grid<Stock> grid = new Grid<Stock>(store, cm);
    grid.setBorders(false);
    grid.getView().setAutoExpandColumn(nameCol);
    grid.getView().setStripeRows(true);
    grid.getView().setColumnLines(true);

    grid.setColumnReordering(true);

    delayTestFinish(5000);
    grid.addViewReadyHandler(new ViewReadyHandler() {
      @Override
      public void onViewReady(ViewReadyEvent event) {
        finishTest();
      }
    });

    RootPanel.get().add(grid);

    assertEquals(true, grid.isAttached());
  }
  
  public void testSelectionModelEvent() {
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
    
    grid.getSelectionModel().addSelectionChangedHandler(new SelectionChangedHandler<Stock>() {

      @Override
      public void onSelectionChanged(SelectionChangedEvent<Stock> event) {
        GridSelectionModel<Stock> sm = (GridSelectionModel<Stock>)event.getSource();
        Grid<Stock> g = sm.getGrid();
        assertEquals(grid,  g);
      }
    });
  }

  public void testAttachSelections() {
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
    grid.setBorders(false);
    grid.getView().setAutoExpandColumn(nameCol);
    grid.getView().setStripeRows(true);
    grid.getView().setColumnLines(true);

    grid.setColumnReordering(true);

    RootPanel.get().add(grid);

    assertEquals(true, grid.isAttached());

    grid.getSelectionModel().select(0, false);

    RootPanel.get().remove(grid);

    assertEquals(false, grid.isAttached());

    RootPanel.get().add(grid);

    assertEquals(true, grid.isAttached());

    assertEquals(1, grid.getSelectionModel().getSelectedItems().size());
  }

  public void testReconfigure() {
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
    grid.setBorders(false);
    grid.getView().setAutoExpandColumn(nameCol);
    grid.getView().setStripeRows(true);
    grid.getView().setColumnLines(true);

    grid.setColumnReordering(true);

    List<ColumnConfig<Stock, ?>> l2 = new ArrayList<ColumnConfig<Stock, ?>>();
    l2.add(nameCol);
    l2.add(symbolCol);
    l2.add(lastCol);

    ColumnModel<Stock> cm2 = new ColumnModel<Stock>(l2);

    ListStore<Stock> store2 = new ListStore<Stock>(props.key());
    grid.reconfigure(store2, cm2);

    RootPanel.get().add(grid);

    assertSame(cm2, grid.getColumnModel());
    assertSame(store2, grid.getStore());

    //not sure this is a legit test, but works for now
    assertSame(store2, getStore(grid.getSelectionModel()));
  }

  private native Store<Stock> getStore(GridSelectionModel<Stock> selectionModel) /*-{
    return selectionModel.@com.sencha.gxt.widget.core.client.grid.GridSelectionModel::listStore;
  }-*/;

  public void testCellSelectionModel() {
    StockProperties props = GWT.create(StockProperties.class);

    ColumnConfig<Stock, String> nameCol = new ColumnConfig<Stock, String>(props.name(), 200, "Company");
    ColumnConfig<Stock, Double> lastCol = new ColumnConfig<Stock, Double>(props.last(), 75, "Last");

    List<ColumnConfig<Stock, ?>> l = new ArrayList<ColumnConfig<Stock, ?>>();
    l.add(nameCol);
    l.add(lastCol);

    ColumnModel<Stock> cm = new ColumnModel<Stock>(l);

    final ListStore<Stock> store = new ListStore<Stock>(props.key());
    store.addAll(TestData.getShortStocks());

    final Grid<Stock> grid = new Grid<Stock>(store, cm);

    CellSelectionModel<Stock> csm = new CellSelectionModel<Stock>();
    csm.addCellSelectionChangedHandler(new CellSelectionChangedHandler<Stock>() {

      @Override
      public void onCellSelectionChanged(CellSelectionChangedEvent<Stock> event) {
        assertEquals(1, event.getSelection().size());
        assertEquals(store.get(0), event.getSelection().get(0).getModel());
        assertEquals(1, event.getSelection().get(0).getCell());
        finishTest();
      }
    });
    grid.setSelectionModel(csm);

    RootPanel.get().add(grid);

    delayTestFinish(2000);

    csm.selectCell(0, 1);
  }

  public void testGridRenderRows() {
    StockProperties props = GWT.create(StockProperties.class);

    ColumnConfig<Stock, String> nameCol = new ColumnConfig<Stock, String>(props.name(), 200, "Company");
    ColumnConfig<Stock, Double> lastCol = new ColumnConfig<Stock, Double>(props.last(), 75, "Last");

    List<ColumnConfig<Stock, ?>> l = new ArrayList<ColumnConfig<Stock, ?>>();
    l.add(nameCol);
    l.add(lastCol);

    ColumnModel<Stock> cm = new ColumnModel<Stock>(l);

    ListStore<Stock> store = new ListStore<Stock>(props.key());
    store.addAll(TestData.getShortStocks());

    final Grid<Stock> grid = new Grid<Stock>(store, cm);
    grid.setLazyRowRender(0);

    delayTestFinish(2000);
    grid.addViewReadyHandler(new ViewReadyHandler() {
      @Override
      public void onViewReady(ViewReadyEvent event) {
        finishTest();
      }
    });

    RootPanel.get().add(grid);

    assertEquals(true, grid.isAttached());
  }
  
  @DoNotRunWith({Platform.HtmlUnitLayout})
  public void testGridAutoSizeWithHideHeadersTrue() {
    // Given a grid exists
    StockProperties props = GWT.create(StockProperties.class);

    final ColumnConfig<Stock, String> nameCol = new ColumnConfig<Stock, String>(props.name(), 200, "Company");
    final ColumnConfig<Stock, String> symbolCol = new ColumnConfig<Stock, String>(props.symbol(), 100, "Symbol");

    List<ColumnConfig<Stock, ?>> l = new ArrayList<ColumnConfig<Stock, ?>>();
    l.add(nameCol);
    l.add(symbolCol);

    ColumnModel<Stock> cm = new ColumnModel<Stock>(l);

    ListStore<Stock> store = new ListStore<Stock>(props.key());
    store.add(TestData.getShortStocks().get(0));
    store.add(TestData.getShortStocks().get(1));

    final Grid<Stock> grid = new Grid<Stock>(store, cm);
    grid.setBorders(true);
    // When the headers are hidden
    grid.setHideHeaders(true);
    grid.getView().setForceFit(true);
    grid.getView().setColumnLines(true);
    
    delayTestFinish(2000);
    grid.addViewReadyHandler(new ViewReadyHandler() {
      @Override
      public void onViewReady(ViewReadyEvent event) {
        finishTest();
      }
    });

    // And when the grid is added to a fieldset
    final FieldSet fs = new FieldSet();
    fs.setHeading("Test Grid");
    fs.setWidget(grid);
    
    Viewport viewport = new Viewport();
    viewport.add(fs);
    RootPanel.get().add(viewport);
    
    Scheduler.get().scheduleDeferred(new Command() {
      public void execute () {
        int colWidth = nameCol.getWidth() + symbolCol.getWidth();
        int fsWidth = fs.getWidget().getOffsetWidth();
        
        // Then the grid columns will auto size in the fieldset
        // 20 is the aprox magic padding, so exact math isn't needed
        assertTrue(colWidth > fsWidth - 20);
      }
    });
    
  }
  
}
