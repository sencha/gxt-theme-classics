package com.sencha.gxt.ui.resources.client.model;

import java.util.Date;

import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;

public interface StockProperties extends PropertyAccess<Stock> {
  @Path("symbol")
  ModelKeyProvider<Stock> key();

  ValueProvider<Stock, String> name();
  
  ValueProvider<Stock, String> symbol();
  
  ValueProvider<Stock, Double> last();
  
  ValueProvider<Stock, Double> change();
  
  ValueProvider<Stock, Date> lastTrans();
  
  ValueProvider<Stock, String> industry();
}