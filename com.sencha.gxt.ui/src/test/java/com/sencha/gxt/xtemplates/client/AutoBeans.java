package com.sencha.gxt.xtemplates.client;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.TextResource;
import com.google.web.bindery.autobean.shared.AutoBean;
import com.google.web.bindery.autobean.shared.AutoBeanCodex;
import com.google.web.bindery.autobean.shared.AutoBeanFactory;
import com.google.web.bindery.autobean.shared.Splittable;
import com.google.web.bindery.autobean.shared.impl.StringQuoter;

public class AutoBeans {

  public interface DataBundle extends ClientBundle {

    @Source("GwtTestXTemplates.json")
    TextResource stockData();
  }

  public interface StockResult {
    List<GwtTestXTemplatesProxy> getStocks();
  }
 
  public interface ExampleDataFactory extends AutoBeanFactory {
    AutoBean<StockResult> stockResult();
  }

  public static DataBundle dataBundle = GWT.create(DataBundle.class);
  public static ExampleDataFactory dataFactory = GWT.create(ExampleDataFactory.class);

  public static List<GwtTestXTemplatesProxy> getStocks() {
    Splittable s = StringQuoter.split(dataBundle.stockData().getText());
    AutoBean<StockResult> result = AutoBeanCodex.decode(dataFactory, StockResult.class, s);
    return result.as().getStocks();
  }
  
  public static GwtTestXTemplatesProxy getStock() {
    return getStocks().get(0);
  }
}