package com.sencha.gxt.widget.core.client;

import com.google.gwt.core.client.Callback;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.ui.RootPanel;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.loader.DataProxy;
import com.sencha.gxt.data.shared.loader.ListLoadConfigBean;
import com.sencha.gxt.data.shared.loader.ListLoadResult;
import com.sencha.gxt.data.shared.loader.ListLoader;
import com.sencha.gxt.data.shared.loader.LoadExceptionEvent;
import com.sencha.gxt.ui.resources.client.TestData;
import com.sencha.gxt.ui.resources.client.model.Stock;
import com.sencha.gxt.ui.resources.client.model.StockProperties;
import com.sencha.gxt.ui.resources.shared.BaseDto;
import com.sencha.gxt.widget.core.client.selection.SelectionChangedEvent;
import com.sencha.gxt.widget.core.client.selection.SelectionChangedEvent.SelectionChangedHandler;
import com.sencha.gxt.widget.core.client.selection.StoreSelectionModel;
import junit.framework.Assert;

public class GwtTestListView extends CoreBaseTestCase {

  public void testSelectionChanged() {
    ListStore<BaseDto> store = new ListStore<BaseDto>(new ModelKeyProvider<BaseDto>() {

      @Override
      public String getKey(BaseDto item) {
        return item.getId().toString();
      }
    });

    store.add(new BaseDto(1L, "Test"));

    ListView<BaseDto, String> view = new ListView<BaseDto, String>(store, new ValueProvider<BaseDto, String>() {
      @Override
      public String getPath() {
        return "name";
      }

      @Override
      public String getValue(BaseDto object) {
        return object.getName();
      }

      @Override
      public void setValue(BaseDto object, String value) {
        object.setName(value);
      }
    });

    view.getSelectionModel().addSelectionChangedHandler(new SelectionChangedHandler<BaseDto>() {

      @Override
      public void onSelectionChanged(SelectionChangedEvent<BaseDto> event) {
        StoreSelectionModel<BaseDto> c = event.getSource();
        assertNotNull(c);

        finishTest();
      }
    });

    RootPanel.get().add(view);

    delayTestFinish(500);

    view.getSelectionModel().select(0, false);
  }

  public void testSelectionChangedEvent() {
    ListStore<BaseDto> store = new ListStore<BaseDto>(new ModelKeyProvider<BaseDto>() {

      @Override
      public String getKey(BaseDto item) {
        return item.getId().toString();
      }
    });

    store.add(new BaseDto(1L, "Test"));

    final ListView<BaseDto, String> view = new ListView<BaseDto, String>(store, new ValueProvider<BaseDto, String>() {
      @Override
      public String getPath() {
        return "name";
      }

      @Override
      public String getValue(BaseDto object) {
        return object.getName();
      }

      @Override
      public void setValue(BaseDto object, String value) {
        object.setName(value);
      }
    });

    view.getSelectionModel().addSelectionChangedHandler(new SelectionChangedHandler<BaseDto>() {

      @Override
      public void onSelectionChanged(SelectionChangedEvent<BaseDto> event) {
        ListViewSelectionModel<?> c = (ListViewSelectionModel<?>) event.getSource();
        assertEquals(view, c.getListView());
      }
    });
  }

  public void testUpdate() {
    StockProperties props = GWT.create(StockProperties.class);
    ListStore<Stock> store = new ListStore<Stock>(props.key());
    store.addAll(TestData.getShortStocks());

    ListView<Stock, String> view = new ListView<Stock, String>(store, props.name());
    view.setPixelSize(400, 400);

    RootPanel.get().add(view);

    assertEquals(store.get(0).getName(), view.getElement(0).getInnerText());

    ListStore<Stock>.Record r = store.getRecord(store.get(0));
    // fires store update event
    r.addChange(props.name(), "foobar");

    assertEquals("foobar", view.getElement(0).getInnerText());

    // not auto commit
    assertFalse("foobar".equals(store.get(0).getName()));
  }

  public void testSetTestLoadingIndicator() {
    // Given ListView
    StockProperties props = GWT.create(StockProperties.class);
    ListStore<Stock> store = new ListStore<Stock>(props.key());
    store.addAll(TestData.getShortStocks());
    final ListView<Stock, String> listView = new ListView<Stock, String>(store, props.name());

    // And ListView has DataProxy for loader
    DataProxy<ListLoadConfigBean, ListLoadResult<Stock>> dataProxy = new DataProxy<ListLoadConfigBean, ListLoadResult<Stock>>() {
      @Override
      public void load(ListLoadConfigBean loadConfig, final Callback<ListLoadResult<Stock>, Throwable> callback) {
      }
    };

    // ListView has a loader
    ListLoader<ListLoadConfigBean, ListLoadResult<Stock>> loader = new ListLoader<ListLoadConfigBean, ListLoadResult<Stock>>(dataProxy);
    listView.setLoader(loader);

    // When setLoadingIndicator is set with safe html
    SafeHtmlBuilder shb = new SafeHtmlBuilder();
    shb.appendHtmlConstant("<a href=\"#\"></a>");
    SafeHtml safeHtml = shb.toSafeHtml();
    listView.setLoadingIndicator(safeHtml);

    // and loader is called to load
    loader.load();

    // Then the inner html will be html
    Scheduler.get().scheduleDeferred(new Command() {
      @Override
      public void execute() {
        String innerHtml = listView.getElement().getInnerHTML();
        assertEquals("<div class=\"loading-indicator\"><a href=\"#\"></a></div>", innerHtml);
        finishTest();
      }
    });

    delayTestFinish(1000);
  }

  public void testSetTestLoadingIndicatorWithText() {
    // Given ListView
    StockProperties props = GWT.create(StockProperties.class);
    ListStore<Stock> store = new ListStore<Stock>(props.key());
    store.addAll(TestData.getShortStocks());
    final ListView<Stock, String> listView = new ListView<Stock, String>(store, props.name());

    // And ListView has rpcProxy for loader
    DataProxy<ListLoadConfigBean, ListLoadResult<Stock>> dataProxy = new DataProxy<ListLoadConfigBean, ListLoadResult<Stock>>() {
      @Override
      public void load(ListLoadConfigBean loadConfig, final Callback<ListLoadResult<Stock>, Throwable> callback) {
      }
    };

    // ListView has a loader
    ListLoader<ListLoadConfigBean, ListLoadResult<Stock>> loader = new ListLoader<ListLoadConfigBean, ListLoadResult<Stock>>(
        dataProxy);
    listView.setLoader(loader);

    // When setLoadingIndicator is set with text
    listView.setLoadingIndicator("Loading...");

    // and loader is called to load
    loader.load();

    // Then the inner html will be text
    Scheduler.get().scheduleDeferred(new Command() {
      @Override
      public void execute() {
        String innerHtml = listView.getElement().getInnerHTML();
        assertEquals("<div class=\"loading-indicator\">Loading...</div>", innerHtml);
        finishTest();
      }
    });

    delayTestFinish(1000);
  }

  public void testSetTestLoadingIndicatorWithHtml() {
    // Given ListView
    StockProperties props = GWT.create(StockProperties.class);
    ListStore<Stock> store = new ListStore<Stock>(props.key());
    store.addAll(TestData.getShortStocks());
    final ListView<Stock, String> listView = new ListView<Stock, String>(store, props.name());

    // And ListView has dataProxy for loader
    DataProxy<ListLoadConfigBean, ListLoadResult<Stock>> dataProxy = new DataProxy<ListLoadConfigBean, ListLoadResult<Stock>>() {
      @Override
      public void load(ListLoadConfigBean loadConfig, final Callback<ListLoadResult<Stock>, Throwable> callback) {
      }
    };

    // ListView has a loader
    ListLoader<ListLoadConfigBean, ListLoadResult<Stock>> loader = new ListLoader<ListLoadConfigBean, ListLoadResult<Stock>>(dataProxy);
    listView.setLoader(loader);

    // When setLoadingIndicator is set with text
    listView.setLoadingIndicator("<b>Loading...</b>");

    // and loader is called to load
    loader.load();

    // Then the inner html will be text, and will escape any html
    Scheduler.get().scheduleDeferred(new Command() {
      @Override
      public void execute() {
        String innerHtml = listView.getElement().getInnerHTML();
        assertEquals("<div class=\"loading-indicator\">&lt;b&gt;Loading...&lt;/b&gt;</div>", innerHtml);
        finishTest();
      }
    });

    delayTestFinish(1000);
  }

  public void testSetTestLoadingIndicatorWithTextgetLoadingIndicator() {
    // Given ListView
    StockProperties props = GWT.create(StockProperties.class);
    ListStore<Stock> store = new ListStore<Stock>(props.key());
    store.addAll(TestData.getShortStocks());
    final ListView<Stock, String> listView = new ListView<Stock, String>(store, props.name());

    // When setLoadingIndicator is set with text
    listView.setLoadingIndicator("<b>Loading...</b>");

    // And getting the set text
    SafeHtml text = listView.getLoadingIndicator();

    // Then the set text will equal the gotten text
    Assert.assertEquals("&lt;b&gt;Loading...&lt;/b&gt;", text.asString());
  }

  /**
   * This test prints a stack trace on purpose.
   */
  public void testOnLoadError() {
    delayTestFinish(1000);
    
    // Given ListView
    StockProperties props = GWT.create(StockProperties.class);
    ListStore<Stock> store = new ListStore<Stock>(props.key());
    store.addAll(TestData.getShortStocks());
    final ListView<Stock, String> listView = new ListView<Stock, String>(store, props.name()) {
      @Override
      protected void onLoadError(LoadExceptionEvent<?> event) {
        // Then the test is success
        finishTest();
      }
      
      @Override
      protected void onLoad() {
        fail();
      }
    };

    // And ListView has dataProxy for loader
    DataProxy<ListLoadConfigBean, ListLoadResult<Stock>> dataProxy = new DataProxy<ListLoadConfigBean, ListLoadResult<Stock>>() {
      @Override
      public void load(ListLoadConfigBean loadConfig, final Callback<ListLoadResult<Stock>, Throwable> callback) {
        // When the loader fails to load
        callback.onFailure(new Exception("Ignore this exception, it is a test. GwtTestListView.testOnLoadError()."));
      }
    };

    // ListView has a loader
    ListLoader<ListLoadConfigBean, ListLoadResult<Stock>> loader = new ListLoader<ListLoadConfigBean, ListLoadResult<Stock>>(dataProxy);
    listView.setLoader(loader);

    // and loader is called to load
    loader.load();
  }

  /**
   * This test prints a stack trace on purpose.
   */
  public void testLoadingTextWithOnLoadError() {
    delayTestFinish(5000);

    // Given ListView
    StockProperties props = GWT.create(StockProperties.class);
    ListStore<Stock> store = new ListStore<Stock>(props.key());
    store.addAll(TestData.getShortStocks());
    final ListView<Stock, String> listView = new ListView<Stock, String>(store, props.name()) {
      @Override
      protected void onLoadError(LoadExceptionEvent<?> event) {
        // Then the element should restored to its orginal
        Scheduler.get().scheduleDeferred(new Command() {
          @Override
          public void execute() {
            // And the original elements have Google as an item
            String innerHtml = getElement().getInnerHTML();
            Assert.assertTrue(innerHtml.contains("Google"));

            // And the test is success
            finishTest();
          }
        });
      }
    };

    // And ListView has dataProxy for loader
    DataProxy<ListLoadConfigBean, ListLoadResult<Stock>> dataProxy = new DataProxy<ListLoadConfigBean, ListLoadResult<Stock>>() {
      @Override
      public void load(ListLoadConfigBean loadConfig, final Callback<ListLoadResult<Stock>, Throwable> callback) {
        String innerHtml = listView.getElement().getInnerHTML();
        Assert.assertTrue(innerHtml.contains("Loading..."));

        // When the loader fails to load
        callback.onFailure(new Exception("Ignore this exception, it is a test. GwtTestListView.testOnLoadError()."));
      }
    };

    // ListView has a loader
    final ListLoader<ListLoadConfigBean, ListLoadResult<Stock>> loader = new ListLoader<ListLoadConfigBean, ListLoadResult<Stock>>(dataProxy);
    listView.setLoader(loader);
    listView.setLoadingIndicator("Loading...");
    RootPanel.get().add(listView);

    Scheduler.get().scheduleDeferred(new Command() {
      @Override
      public void execute() {
        // And loader is called to load
        loader.load();
      }
    });
  }
  
}
