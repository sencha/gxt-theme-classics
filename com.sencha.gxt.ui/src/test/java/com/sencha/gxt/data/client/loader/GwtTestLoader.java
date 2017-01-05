package com.sencha.gxt.data.client.loader;

import com.google.gwt.core.client.Callback;
import com.sencha.gxt.data.client.DataTestCase;
import com.sencha.gxt.data.shared.loader.BeforeLoadEvent.BeforeLoadHandler;
import com.sencha.gxt.data.shared.loader.BeforeLoadEvent;
import com.sencha.gxt.data.shared.loader.DataProxy;
import com.sencha.gxt.data.shared.loader.Loader;

public class GwtTestLoader extends DataTestCase {

  public void testCancelLoad() {
    Loader<Object, Object> loader = new Loader<Object, Object>(new DataProxy<Object, Object>() {
        @Override
        public void load(Object loadConfig, Callback<Object, Throwable> callback) {
          fail();
        }
    });
    loader.addBeforeLoadHandler(new BeforeLoadHandler<Object>() {
      @Override
      public void onBeforeLoad(BeforeLoadEvent<Object> event) {
        event.setCancelled(true);
      }
    });
    loader.load();
    loader.load(new Object());
  }
}
