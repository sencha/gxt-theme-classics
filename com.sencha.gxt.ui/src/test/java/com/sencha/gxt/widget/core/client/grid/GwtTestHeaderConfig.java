package com.sencha.gxt.widget.core.client.grid;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.RootPanel;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.ui.resources.client.TestData;
import com.sencha.gxt.ui.resources.client.model.Stock;
import com.sencha.gxt.ui.resources.client.model.StockProperties;
import com.sencha.gxt.widget.core.client.CoreBaseTestCase;

public class GwtTestHeaderConfig extends CoreBaseTestCase {

  public void testHeaderConfigAutoCreate() {
    StockProperties props = GWT.create(StockProperties.class);

    ColumnConfig<Stock, String> nameCol = new ColumnConfig<Stock, String>(props.name(), 200, "Company");
    ColumnConfig<Stock, String> symbolCol = new ColumnConfig<Stock, String>(props.symbol(), 100, "Symbol");
    ColumnConfig<Stock, Double> lastCol = new ColumnConfig<Stock, Double>(props.last(), 75, "Last");

    List<ColumnConfig<Stock, ?>> l = new ArrayList<ColumnConfig<Stock, ?>>();
    l.add(nameCol);
    l.add(symbolCol);
    l.add(lastCol);

    ColumnModel<Stock> cm = new ColumnModel<Stock>(l);
    
    cm.addHeaderGroup(0, 1, new HeaderGroupConfig("Test", 1, 2));

    assertEquals(1, cm.getHeaderGroups().size());
    
    ListStore<Stock> store = new ListStore<Stock>(props.key());
    store.addAll(TestData.getShortStocks());

    Grid<Stock> grid = new Grid<Stock>(store, cm);

    RootPanel.get().add(grid);

    // we no longer autocreate so know new groups should have been created
    assertEquals(1, cm.getHeaderGroups().size());
    
    assertEquals(true, grid.isAttached());
  }
  
}
