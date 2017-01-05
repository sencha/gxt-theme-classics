package com.sencha.gxt.widget.core.client.grid;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.user.client.ui.RootPanel;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.ui.resources.client.TestData;
import com.sencha.gxt.ui.resources.client.model.Stock;
import com.sencha.gxt.ui.resources.client.model.StockProperties;
import com.sencha.gxt.widget.core.client.CoreBaseTestCase;
import com.sencha.gxt.widget.core.client.event.ViewReadyEvent;
import com.sencha.gxt.widget.core.client.event.ViewReadyEvent.ViewReadyHandler;

public class GwtTestAggRowConfig extends CoreBaseTestCase {
    public void testCellStyles() {
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
      
      AggregationRowConfig<Stock> agg = new AggregationRowConfig<Stock>();
      agg.setRenderer(nameCol, new AggregationRenderer<Stock>() {
        @Override
        public SafeHtml render(int colIndex, Grid<Stock> grid) {
          return SafeHtmlUtils.fromSafeConstant("name");
        }
      });
      agg.setRenderer(symbolCol, new AggregationRenderer<Stock>() {
        @Override
        public SafeHtml render(int colIndex, Grid<Stock> grid) {
          return SafeHtmlUtils.fromSafeConstant("symbol");
        }
      });
      agg.setCellStyle(nameCol, "bar");
      agg.setCellStyle(symbolCol, "foo");
      cm.addAggregationRow(agg);
      
      RootPanel.get().add(grid);
      delayTestFinish(2000);
      grid.addViewReadyHandler(new ViewReadyHandler() {
        @Override
        public void onViewReady(ViewReadyEvent event) {
          assertEquals(1, grid.getElement().select(".bar").getLength());
          assertEquals(1, grid.getElement().select(".foo").getLength());

          assertEquals("name", grid.getElement().selectNode(".bar").getInnerText());
          assertEquals("symbol", grid.getElement().selectNode(".foo").getInnerText());
          finishTest();
        }
      });
    }
}
