package com.sencha.gxt.ui.client.data.loader;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.http.client.RequestBuilder;
import com.google.web.bindery.autobean.shared.AutoBean;
import com.google.web.bindery.autobean.shared.AutoBeanFactory;
import com.google.web.bindery.autobean.shared.AutoBean.PropertyName;
import com.sencha.gxt.data.client.loader.HttpProxy;
import com.sencha.gxt.data.client.loader.XmlReader;
import com.sencha.gxt.data.shared.loader.BeforeLoadEvent;
import com.sencha.gxt.data.shared.loader.ListLoadConfig;
import com.sencha.gxt.data.shared.loader.ListLoadResult;
import com.sencha.gxt.data.shared.loader.ListLoadResultBean;
import com.sencha.gxt.data.shared.loader.ListLoader;
import com.sencha.gxt.data.shared.loader.LoadEvent;
import com.sencha.gxt.data.shared.loader.LoadExceptionEvent;
import com.sencha.gxt.data.shared.loader.LoaderHandler;
import com.sencha.gxt.ui.client.UITestCase;

public class GwtTestListLoader extends UITestCase {
  
  public interface DataRecord {
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
  
  public interface SampleAutoBeanFactory extends AutoBeanFactory {
    AutoBean<ItemsResult> jsonItems();
  }
  
  public interface ItemsResult {
    @PropertyName("record")
    List<DataRecord> getRecords();
  }
  
  private class DataRecordXmlReader extends XmlReader<ListLoadResult<DataRecord>, ItemsResult> {
    public DataRecordXmlReader(AutoBeanFactory factory, Class<ItemsResult> rootBeanType) {
      super(factory, rootBeanType);
    }

    @Override
    protected ListLoadResult<DataRecord> createReturnData(Object loadConfig, ItemsResult incomingData) {
      return new ListLoadResultBean<DataRecord>(incomingData.getRecords());
    }
  }

  public void testListLoader() {
    SampleAutoBeanFactory factory = GWT.create(SampleAutoBeanFactory.class);
    DataRecordXmlReader reader = new DataRecordXmlReader(factory, ItemsResult.class);
    
    RequestBuilder rb = new RequestBuilder(RequestBuilder.GET, GWT.getModuleBaseURL() + "data.xml");
    HttpProxy<ListLoadConfig> jsonProxy = new HttpProxy<ListLoadConfig>(rb);

    ListLoader<ListLoadConfig, ListLoadResult<DataRecord>> loader = new ListLoader<ListLoadConfig, ListLoadResult<DataRecord>>(
        jsonProxy, reader);

    loader.addLoadHandler(new LoaderHandler<ListLoadConfig, ListLoadResult<DataRecord>>() {
      @Override
      public void onLoad(LoadEvent<ListLoadConfig, ListLoadResult<DataRecord>> event) {
        assertNotNull(event.getLoadResult().getData().get(0).getName());
        finishTest();
      }

      @Override
      public void onBeforeLoad(BeforeLoadEvent<ListLoadConfig> event) {
      }

      @Override
      public void onLoadException(LoadExceptionEvent<ListLoadConfig> event) {
        fail();
      }
    });

    loader.load();
    delayTestFinish(5000);
  }

}
