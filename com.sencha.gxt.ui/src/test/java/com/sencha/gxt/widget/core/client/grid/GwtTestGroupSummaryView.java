package com.sencha.gxt.widget.core.client.grid;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.NodeList;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.user.client.ui.RootPanel;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.core.client.dom.XElement;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.ui.resources.client.TestData;
import com.sencha.gxt.ui.resources.client.model.Stock;
import com.sencha.gxt.ui.resources.client.model.StockProperties;
import com.sencha.gxt.widget.core.client.CoreBaseTestCase;

public class GwtTestGroupSummaryView extends CoreBaseTestCase {

  public void testSummaryWithHtml() {
    StockProperties props = GWT.create(StockProperties.class);

    SummaryColumnConfig<Stock, String> nameCol = new SummaryColumnConfig<Stock, String>(props.name(), 200, "Company");
    final SummaryColumnConfig<Stock, String> symbolCol = new SummaryColumnConfig<Stock, String>(props.symbol(), 100, "Symbol");
    SummaryColumnConfig<Stock, Double> lastCol = new SummaryColumnConfig<Stock, Double>(props.last(), 75, "Last");

    List<ColumnConfig<Stock, ?>> l = new ArrayList<ColumnConfig<Stock, ?>>();
    l.add(nameCol);
    l.add(symbolCol);
    l.add(lastCol);
    SummaryColumnConfig<Stock, Double> randomNumbers = new SummaryColumnConfig<Stock, Double>(props.last());
    randomNumbers.setSummaryType(new SummaryType.AvgSummaryType<Double>());
    randomNumbers.setSummaryRenderer(new SummaryRenderer<Stock>() {
      @Override
      public SafeHtml render(Number value, Map<ValueProvider<? super Stock, ?>, Number> data) {
        return SafeHtmlUtils.fromSafeConstant("<b>bold text</b>");
      }
    });
    l.add(randomNumbers);

    ColumnModel<Stock> cm = new ColumnModel<Stock>(l);

    ListStore<Stock> store = new ListStore<Stock>(props.key());
    store.addAll(TestData.getShortStocks());

    Grid<Stock> grid = new Grid<Stock>(store, cm);
    GroupSummaryView<Stock> view = new GroupSummaryView<Stock>();
    grid.setView(view);
    grid.setLazyRowRender(0);
    grid.setPixelSize(500,500);
    view.groupBy(nameCol);
    
    RootPanel.get().add(grid);
    
    NodeList<XElement> summaries = view.getSummaries().cast();
    
    assertEquals(summaries.getItem(0).getInnerHTML(), "bold text", summaries.getItem(0).select("b").getItem(0).getInnerText());
  }
}
