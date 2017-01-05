package com.sencha.gxt.widget.core.client;

import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.Store;
import com.sencha.gxt.ui.resources.client.TestData;
import com.sencha.gxt.ui.resources.client.model.Stock;
import com.sencha.gxt.widget.core.client.form.StoreFilterField;

public class GwtTestStoreFilterField extends CoreBaseTestCase {

  /**
   * Test issue EXTGWT-3002
   * StoreFilterField does not work correctly when emptyText is set.
   */
  public void testStoreFilterWorksWithEmptyText() {
    // When a list store has data
    ListStore<Stock> listStore = new ListStore<Stock>(new ModelKeyProvider<Stock>() {
      @Override
      public String getKey(Stock item) {
        return item.getSymbol();
      }
    });
    listStore.addAll(TestData.getShortStocks());
    
    // And a filter is setup for a column
    StoreFilterField<Stock> filter = new StoreFilterField<Stock>() {
      @Override
      protected boolean doSelect(Store<Stock> store, Stock parent, Stock item, String filter) {
        return item.getName().contains(filter);
      }
    };
    
    // When empty text is set
    filter.setEmptyText("Search");
    filter.bind(listStore);
    
    // Then assure the list has data
    assertTrue(listStore.size() > 10);
    
    // And when filter happens
    filter.validate();

    // Then assure the emptyText doesn't affect the sort
    assertTrue(listStore.size() > 10);
  }
  
}
