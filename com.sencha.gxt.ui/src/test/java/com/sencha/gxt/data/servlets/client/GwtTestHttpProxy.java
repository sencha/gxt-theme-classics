package com.sencha.gxt.data.servlets.client;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.junit.client.GWTTestCase;
import com.google.web.bindery.autobean.shared.AutoBean;
import com.google.web.bindery.autobean.shared.AutoBean.PropertyName;
import com.google.web.bindery.autobean.shared.AutoBeanFactory;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.client.loader.HttpProxy;
import com.sencha.gxt.data.client.loader.XmlReader;
import com.sencha.gxt.data.shared.PropertyAccess;
import com.sencha.gxt.data.shared.loader.BeforeLoadEvent;
import com.sencha.gxt.data.shared.loader.ListLoadConfig;
import com.sencha.gxt.data.shared.loader.ListLoadResult;
import com.sencha.gxt.data.shared.loader.ListLoadResultBean;
import com.sencha.gxt.data.shared.loader.ListLoader;
import com.sencha.gxt.data.shared.loader.LoadEvent;
import com.sencha.gxt.data.shared.loader.LoadExceptionEvent;
import com.sencha.gxt.data.shared.loader.LoaderHandler;

public class GwtTestHttpProxy extends GWTTestCase {

  @Override
  public String getModuleName() {
    return "com.sencha.gxt.data.servlets.DataTest";
  }

  interface XmlAutoBeanFactory extends AutoBeanFactory {
    static XmlAutoBeanFactory instance = GWT.create(XmlAutoBeanFactory.class);

    AutoBean<EmailCollection> items();

    AutoBean<ListLoadConfig> loadConfig();

  }

  interface Email {
    @PropertyName("Name")
    String getName();

    @PropertyName("Email")
    String getEmail();

    @PropertyName("Phone")
    String getPhone();

    @PropertyName("State")
    String getState();

    @PropertyName("Zip")
    String getZip();
  }

  interface EmailCollection {
    @PropertyName("record")
    List<Email> getValues();
  }

  interface EmailProperties extends PropertyAccess<Email> {

    ValueProvider<Email, String> name();

    ValueProvider<Email, String> email();

    ValueProvider<Email, String> phone();

    ValueProvider<Email, String> state();

    ValueProvider<Email, String> zip();
  }

  public void testHttpProxy() {
    String path = "data.xml";
    RequestBuilder builder = new RequestBuilder(RequestBuilder.GET, path);
    HttpProxy<ListLoadConfig> proxy = new HttpProxy<ListLoadConfig>(builder);

    delayTestFinish(2000);

    XmlReader<ListLoadResult<Email>, EmailCollection> reader = new XmlReader<ListLoadResult<Email>, EmailCollection>(
        XmlAutoBeanFactory.instance, EmailCollection.class) {
      protected com.sencha.gxt.data.shared.loader.ListLoadResult<Email> createReturnData(Object loadConfig,
          EmailCollection records) {
        return new ListLoadResultBean<Email>(records.getValues());
      };
    };

    final ListLoader<ListLoadConfig, ListLoadResult<Email>> loader = new ListLoader<ListLoadConfig, ListLoadResult<Email>>(
        proxy, reader);
    loader.useLoadConfig(XmlAutoBeanFactory.instance.create(ListLoadConfig.class).as());

    loader.addLoaderHandler(new LoaderHandler<ListLoadConfig, ListLoadResult<Email>>() {

      @Override
      public void onBeforeLoad(BeforeLoadEvent<ListLoadConfig> event) {

      }

      @Override
      public void onLoadException(LoadExceptionEvent<ListLoadConfig> event) {
        fail(event.getException().getMessage());
      }

      @Override
      public void onLoad(LoadEvent<ListLoadConfig, ListLoadResult<Email>> event) {
        assertTrue(event.getLoadResult().getData().size() > 10);
        finishTest();
      }

    });

    loader.load();

  }

}
