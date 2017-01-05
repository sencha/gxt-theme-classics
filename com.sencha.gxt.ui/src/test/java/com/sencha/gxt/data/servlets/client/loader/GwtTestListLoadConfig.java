package com.sencha.gxt.data.servlets.client.loader;

import com.google.gwt.core.client.GWT;
import com.google.gwt.junit.client.GWTTestCase;
import com.google.web.bindery.autobean.shared.AutoBean;
import com.google.web.bindery.autobean.shared.AutoBeanFactory;
import com.sencha.gxt.data.shared.SortDir;
import com.sencha.gxt.data.shared.SortInfoBean;
import com.sencha.gxt.data.shared.loader.ListLoadConfig;
import com.sencha.gxt.data.shared.loader.ListLoadConfigBean;
import com.sencha.gxt.data.shared.writer.JsonWriter;

public class GwtTestListLoadConfig extends GWTTestCase {

  interface ABF extends AutoBeanFactory {
    AutoBean<ListLoadConfig> listLoadConfig();
  }

  @Override
  public String getModuleName() {
    return "com.sencha.gxt.data.servlets.DataTest";
  }

  public void testBeanToString() {
    ListLoadConfigBean cfg = new ListLoadConfigBean();
    cfg.getSortInfo().add(new SortInfoBean("asdf", SortDir.ASC));

    ABF abf = GWT.create(ABF.class);

    JsonWriter<ListLoadConfig> writer = new JsonWriter<ListLoadConfig>(abf, ListLoadConfig.class);
    String data = writer.write(cfg);
    assertNotNull(data);
  }

}
