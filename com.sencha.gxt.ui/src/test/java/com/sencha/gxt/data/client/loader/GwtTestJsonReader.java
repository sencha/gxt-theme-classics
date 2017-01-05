package com.sencha.gxt.data.client.loader;

import java.util.List;

import org.junit.Test;

import com.google.gwt.core.client.GWT;
import com.google.web.bindery.autobean.shared.AutoBean;
import com.google.web.bindery.autobean.shared.AutoBeanFactory;
import com.sencha.gxt.data.client.DataTestCase;
import com.sencha.gxt.data.shared.loader.JsonReader;
import com.sencha.gxt.data.shared.loader.ListLoadResult;

public class GwtTestJsonReader extends DataTestCase {
  
  interface TestAutoBeanFactory extends AutoBeanFactory {
    static TestAutoBeanFactory instance = GWT.create(TestAutoBeanFactory.class);

    AutoBean<Data> data();

    AutoBean<DataCollection> dataCollection();

    AutoBean<DataListLoadResult> dataLoadResult();
  }

  interface Data {
    String getValue();
  }
  interface DataCollection {
    List<Data> getData();
  }
  interface DataListLoadResult extends ListLoadResult<Data> {
  }

  @Test
  public void testReadSimpleObject() {
    JsonReader<Data, Data> reader = new JsonReader<Data, Data>(TestAutoBeanFactory.instance, Data.class);

    Data d = reader.read(null, "{\"value\":\"result\"}");
    assertNotNull(d);
    assertEquals("result", d.getValue());
  }

  @Test
  public void testReadSimpleCollection() {
    JsonReader<DataCollection, DataCollection> reader = new JsonReader<DataCollection, DataCollection>(
        TestAutoBeanFactory.instance, DataCollection.class);

    DataCollection data = reader.read(null, "{\"data\":[{\"value\":\"result1\"},{\"value\":\"result2\"}]}");
    assertNotNull(data);
    assertEquals(2, data.getData().size());
    assertEquals("result1", data.getData().get(0).getValue());
    assertEquals("result2", data.getData().get(1).getValue());
  }
}
