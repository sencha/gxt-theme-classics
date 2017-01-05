package com.sencha.gxt.state.client;

import java.util.Collections;

import com.sencha.gxt.core.client.GXT;
import com.sencha.gxt.core.client.IdentityValueProvider;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;

public class GwtTestGridStateHandler extends StateBaseTest {
  public void testSimpleHandler() {
    StateManager.get().setProvider(new CookieProvider("/", null, null, GXT.isSecure()));

    ListStore<String> store = new ListStore<String>(new ModelKeyProvider<String>() {
      @Override
      public String getKey(String item) {
        return item;
      }
    });
    ColumnConfig<String, String> col = new ColumnConfig<String, String>(new IdentityValueProvider<String>());
    ColumnModel<String> columns = new ColumnModel<String>(Collections.<ColumnConfig<String,?>>singletonList(col));

    Grid<String> grid = new Grid<String>(store, columns);

    GridStateHandler<String> handler = new GridStateHandler<String>(grid);
    handler.loadState();
  }

  public void testStateIdHandler() {
    StateManager.get().setProvider(new CookieProvider("/", null, null, GXT.isSecure()));

    ListStore<String> store = new ListStore<String>(new ModelKeyProvider<String>() {
      @Override
      public String getKey(String item) {
        return item;
      }
    });
    ColumnConfig<String, String> col = new ColumnConfig<String, String>(new IdentityValueProvider<String>());
    ColumnModel<String> columns = new ColumnModel<String>(Collections.<ColumnConfig<String,?>>singletonList(col));

    Grid<String> grid = new Grid<String>(store, columns);

    GridStateHandler<String> handler = new GridStateHandler<String>(grid, getClass().getName());
    handler.loadState();
  }
}
