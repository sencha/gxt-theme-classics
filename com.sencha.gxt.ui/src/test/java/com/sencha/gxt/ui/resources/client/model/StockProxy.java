package com.sencha.gxt.ui.resources.client.model;

import java.util.Date;

public interface StockProxy {

  public String getName();

  public void setName(String name);

  public double getChange();

  public void setChange(double change);

  public void setSymbol(String symbol);

  public String getSymbol();

  public Date getDate();

  public void setDate(Date date);

}
