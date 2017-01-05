package com.sencha.gxt.widget.core.client.form;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.sencha.gxt.data.shared.Converter;
import com.sencha.gxt.data.shared.LabelProvider;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.ui.resources.client.TestData;
import com.sencha.gxt.ui.resources.client.model.Stock;
import com.sencha.gxt.ui.resources.client.model.StockProperties;
import com.sencha.gxt.widget.core.client.CoreBaseTestCase;

public class GwtTestConverterEditorAdapter extends CoreBaseTestCase {
  static class StockHolder {
    private String stockId;
    public StockHolder(String id) {
      this.stockId = id;
    }
    public String getStockId() {
      return stockId;
    }
    public void setStockId(String stockId) {
      this.stockId = stockId;
    }
  }
  static class StockPicker implements Editor<StockHolder> {
    @Path("stockId")
    ConverterEditorAdapter<String, Stock, ComboBox<Stock>> ed;

    @Ignore
    ComboBox<Stock> stockEditor;

    ListStore<Stock> store;

    public StockPicker() {
      StockProperties props = GWT.create(StockProperties.class);
      store = new ListStore<Stock>(props.key());
      store.addAll(TestData.getShortStocks());
      
      stockEditor = new ComboBox<Stock>(store, new LabelProvider<Stock>() {
        @Override
        public String getLabel(Stock item) {
          return item.getName();
        }
      });
      ed = new ConverterEditorAdapter<String, Stock, ComboBox<Stock>>(stockEditor, new Converter<String, Stock>() {
        @Override
        public String convertFieldValue(Stock object) {
          return store.getKeyProvider().getKey(object);
        }
        @Override
        public Stock convertModelValue(String object) {
          return store.findModelWithKey(object);
        }
      });
    }
  }
  interface Driver extends SimpleBeanEditorDriver<StockHolder, StockPicker> {}
  
  public void testNull() {
    StockPicker ed = new StockPicker();
    Driver d = GWT.create(Driver.class);
    d.initialize(ed);
    
    d.edit(new StockHolder(null));
    
    assertEquals(null, ed.stockEditor.getValue());
  }
  public void testNotNull() {
    StockPicker ed = new StockPicker();
    Driver d = GWT.create(Driver.class);
    d.initialize(ed);
    
    d.edit(new StockHolder("AAPL"));
    
    assertEquals("Apple", ed.stockEditor.getValue().getName());
  }
  public void testChange() {
    StockPicker ed = new StockPicker();
    Driver d = GWT.create(Driver.class);
    d.initialize(ed);
    
    d.edit(new StockHolder("AAPL"));
    ed.stockEditor.setValue(ed.store.findModelWithKey("CSCO"));
    
    StockHolder result = d.flush();
    assertEquals("CSCO", result.getStockId());
  }
}
