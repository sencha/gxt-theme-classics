package com.sencha.gxt.widget.core.client.grid;

import java.util.Map;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.HandlerRegistration;
import com.sencha.gxt.ui.resources.client.model.Stock;
import com.sencha.gxt.ui.resources.client.model.StockProperties;
import com.sencha.gxt.widget.core.client.CoreBaseTestCase;
import com.sencha.gxt.widget.core.client.grid.filters.Filter;
import com.sencha.gxt.widget.core.client.grid.filters.GridFilters;
import com.sencha.gxt.widget.core.client.grid.filters.StringFilter;

public class GwtTestAbstractGridFilters extends CoreBaseTestCase {

  public void testRemoveFilters() {
    StockProperties props = GWT.create(StockProperties.class);
    GridFilters<Stock> filters = new GridFilters<Stock>();

    StringFilter<Stock> nameFilter = new StringFilter<Stock>(props.name());

    filters.addFilter(nameFilter);

    assertEquals(1, getRegistrations(filters).size());

    filters.removeAll();

    assertEquals(0, getRegistrations(filters).size());

  }

  private native Map<Filter<Stock, ?>, HandlerRegistration> getRegistrations(GridFilters<Stock> filters) /*-{
		return filters.@com.sencha.gxt.widget.core.client.grid.filters.AbstractGridFilters::registrations;
  }-*/;

}
