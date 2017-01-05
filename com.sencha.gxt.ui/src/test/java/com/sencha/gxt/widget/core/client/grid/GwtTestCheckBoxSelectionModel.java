package com.sencha.gxt.widget.core.client.grid;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.RootPanel;
import com.sencha.gxt.core.client.IdentityValueProvider;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.ui.resources.client.TestData;
import com.sencha.gxt.ui.resources.client.model.Stock;
import com.sencha.gxt.ui.resources.client.model.StockProperties;
import com.sencha.gxt.widget.core.client.CoreBaseTestCase;
import com.sencha.gxt.widget.core.client.event.ViewReadyEvent;
import com.sencha.gxt.widget.core.client.event.ViewReadyEvent.ViewReadyHandler;

public class GwtTestCheckBoxSelectionModel extends CoreBaseTestCase {

  public void testGrid() {
    StockProperties props = GWT.create(StockProperties.class);

    IdentityValueProvider<Stock> identity = new IdentityValueProvider<Stock>();

    final CheckBoxSelectionModel<Stock> sm = new CheckBoxSelectionModel<Stock>(identity);

    ColumnConfig<Stock, String> nameCol = new ColumnConfig<Stock, String>(props.name(), 200, "Company");
    ColumnConfig<Stock, String> symbolCol = new ColumnConfig<Stock, String>(props.symbol(), 100, "Symbol");
    ColumnConfig<Stock, Double> lastCol = new ColumnConfig<Stock, Double>(props.last(), 75, "Last");

    List<ColumnConfig<Stock, ?>> l = new ArrayList<ColumnConfig<Stock, ?>>();
    l.add(sm.getColumn());
    l.add(nameCol);
    l.add(symbolCol);
    l.add(lastCol);

    ColumnModel<Stock> cm = new ColumnModel<Stock>(l);

    final ListStore<Stock> store = new ListStore<Stock>(props.key());
    store.addAll(TestData.getShortStocks());

    final Grid<Stock> grid = new Grid<Stock>(store, cm);
    grid.setSelectionModel(sm);
    grid.setBorders(false);
    grid.getView().setAutoExpandColumn(nameCol);
    grid.getView().setStripeRows(true);
    grid.getView().setColumnLines(true);

    assertEquals(false, sm.isSelectAllChecked());
    assertEquals(0, sm.getSelectedItems().size());

    delayTestFinish(5000);
    grid.addViewReadyHandler(new ViewReadyHandler() {
      @Override
      public void onViewReady(ViewReadyEvent event) {
        sm.setSelectAllChecked(true);
        assertEquals(true, sm.isSelectAllChecked());
        assertEquals(store.size(), sm.getSelectedItems().size());

        sm.setSelectAllChecked(false);
        assertEquals(0, sm.getSelectedItems().size());

        finishTest();
      }
    });

    RootPanel.get().add(grid);

    assertEquals(true, grid.isAttached());

  }

}
