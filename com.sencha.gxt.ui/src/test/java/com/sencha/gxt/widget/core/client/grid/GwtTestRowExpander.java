package com.sencha.gxt.widget.core.client.grid;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.cell.client.DateCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.client.ui.RootPanel;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.ui.resources.client.TestData;
import com.sencha.gxt.ui.resources.client.model.Stock;
import com.sencha.gxt.ui.resources.client.model.StockProperties;
import com.sencha.gxt.widget.core.client.CoreBaseTestCase;
import com.sencha.gxt.widget.core.client.event.ViewReadyEvent;
import com.sencha.gxt.widget.core.client.event.ViewReadyEvent.ViewReadyHandler;

public class GwtTestRowExpander extends CoreBaseTestCase {

  public void testRowExpander() {
    StockProperties props = GWT.create(StockProperties.class);

    final String desc = "Lorem ipsum dolor sit amet.";

    final RowExpander<Stock> expander = new RowExpander<Stock>(new AbstractCell<Stock>() {

      @Override
      public void render(Context context, Stock value, SafeHtmlBuilder sb) {
        sb.appendHtmlConstant("<p style='margin: 5px 5px 10px'><b>Company:</b>" + value.getName() + "</p>");
        sb.appendHtmlConstant("<p style='margin: 5px 5px 10px'><b>Summary:</b> " + desc);
      }
    });

    ColumnConfig<Stock, String> nameCol = new ColumnConfig<Stock, String>(props.name(), 200, "Company");
    ColumnConfig<Stock, String> symbolCol = new ColumnConfig<Stock, String>(props.symbol(), 100, "Symbol");
    ColumnConfig<Stock, Double> lastCol = new ColumnConfig<Stock, Double>(props.last(), 75, "Last");

    ColumnConfig<Stock, Double> changeCol = new ColumnConfig<Stock, Double>(props.change(), 100, "Change");

    ColumnConfig<Stock, Date> lastTransCol = new ColumnConfig<Stock, Date>(props.lastTrans(), 100, "Last Updated");
    lastTransCol.setCell(new DateCell(DateTimeFormat.getFormat("MM/dd/yyyy")));

    List<ColumnConfig<Stock, ?>> l = new ArrayList<ColumnConfig<Stock, ?>>();
    l.add(expander);
    l.add(nameCol);
    l.add(symbolCol);
    l.add(lastCol);
    l.add(changeCol);
    l.add(lastTransCol);
    ColumnModel<Stock> cm = new ColumnModel<Stock>(l);

    ListStore<Stock> store = new ListStore<Stock>(props.key());
    store.addAll(TestData.getShortStocks());

    final Grid<Stock> grid = new Grid<Stock>(store, cm);
    grid.setPixelSize(600, 400);
    grid.getView().setAutoExpandColumn(nameCol);
    grid.setBorders(false);
    grid.getView().setStripeRows(true);
    grid.getView().setColumnLines(true);

    expander.initPlugin(grid);

    delayTestFinish(5000);
    grid.addViewReadyHandler(new ViewReadyHandler() {
      @Override
      public void onViewReady(ViewReadyEvent event) {
        expander.expandRow(0);

        assertTrue(expander.isExpanded(0));
        
        
        assertFalse(expander.isExpanded(10000));
        
        finishTest();
      }
    });

    RootPanel.get().add(grid);
  }

}
