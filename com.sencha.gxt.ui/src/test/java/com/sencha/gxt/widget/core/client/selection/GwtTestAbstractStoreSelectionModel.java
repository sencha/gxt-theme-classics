package com.sencha.gxt.widget.core.client.selection;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.user.client.ui.RootPanel;
import com.sencha.gxt.core.client.util.EventTracker;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.ui.resources.client.TestData;
import com.sencha.gxt.ui.resources.client.model.Stock;
import com.sencha.gxt.ui.resources.client.model.StockProperties;
import com.sencha.gxt.widget.core.client.CoreBaseTestCase;
import com.sencha.gxt.widget.core.client.ListView;
import com.sencha.gxt.widget.core.client.selection.SelectionChangedEvent.SelectionChangedHandler;

public class GwtTestAbstractStoreSelectionModel extends CoreBaseTestCase {

  private static class Data {
    private static int nextId = 0;
    public final String id = String.valueOf(nextId++);
  }
  private class DataKeyProvider implements ModelKeyProvider<Data> {
    @Override
    public String getKey(Data item) {
      return item.id;
    }
  }

  public void testUpdate() {

    ListStore<Data> l = new ListStore<Data>(new DataKeyProvider());

    AbstractStoreSelectionModel<Data> s = new AbstractStoreSelectionModel<Data>() {
      @Override
      protected void onSelectChange(Data model, boolean select) {
      }
    };
    s.bind(l);

    Data data = new Data();
    l.add(data);

    s.select(data, true);

    assertEquals(data, s.getSelectedItem());

  }

  public void testSelections() {
    StockProperties props = GWT.create(StockProperties.class);
    final EventTracker tracker = new EventTracker();

    ListStore<Stock> store = new ListStore<Stock>(props.key());
    store.addAll(TestData.getShortStocks());

    ListView<Stock, String> view = new ListView<Stock, String>(store, props.name());
    view.getSelectionModel().addSelectionChangedHandler(new SelectionChangedHandler<Stock>() {

      @Override
      public void onSelectionChanged(SelectionChangedEvent<Stock> event) {
        tracker.addEvent(SelectionChangedEvent.getType());
      }
    });

    RootPanel.get().add(view);

    view.getSelectionModel().select(0, 3, false);
    assertEquals(1, tracker.getCount(SelectionChangedEvent.getType()));

    view.getSelectionModel().select(4, false);
    // should only be 1 new event, not 2 for deselect / select
    assertEquals(2, tracker.getCount(SelectionChangedEvent.getType()));

  }

  public void testMultiSelections() {
    StockProperties props = GWT.create(StockProperties.class);
    final EventTracker tracker = new EventTracker();

    ListStore<Stock> store = new ListStore<Stock>(props.key());
    store.addAll(TestData.getShortStocks());

    ListView<Stock, String> view = new ListView<Stock, String>(store, props.name());

    view.getSelectionModel().addSelectionHandler(new SelectionHandler<Stock>() {
      @Override
      public void onSelection(SelectionEvent<Stock> event) {
        tracker.addEvent(SelectionEvent.getType());
      }
    });


    RootPanel.get().add(view);
    
    view.getSelectionModel().select(0, 3, false);
    assertEquals(4, tracker.getCount(SelectionEvent.getType()));
   
    view.getSelectionModel().select(0, 6, true);
    // 4 previous + 3 new
    assertEquals(7, tracker.getCount(SelectionEvent.getType()));
  }
}
