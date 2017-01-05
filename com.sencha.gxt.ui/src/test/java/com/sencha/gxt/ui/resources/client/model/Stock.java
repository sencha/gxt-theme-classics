package com.sencha.gxt.ui.resources.client.model;

import java.util.Date;

public class Stock {

  private String name;
  private String symbol;
  private double open;
  private double change;
  private double last;
  // private double pctChange;
  private Date date;
  private String industry;

  public Stock() {
  }

  public Stock(String name, String symbol, double open, double last) {
    this();
    this.name = name;
    this.symbol = symbol;
    this.open = open;
    this.last = last;
  }

  public Stock(String name, double open, double change, double pctChange, Date date, String industry) {
    this();
    this.name = name;
    this.open = open;
    this.change = change;
    // this.pctChange = pctChange;
    this.date = date;
    this.industry = industry;
  }

  public double getChange() {
    return change;
  }

  public void setChange(double change) {
    this.change = change;
  }

  public String getIndustry() {
    return industry;
  }

  public void setIndustry(String industry) {
    this.industry = industry;
  }

  public double getLast() {
    return last;
  }

  public void setLast(double last) {
    this.last = last;
  }

  public Date getLastTrans() {
    return date;
  }

  public void setLastTrans(Date date) {
    this.date = date;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public double getOpen() {
    return open;
  }

  public void setOpen(double open) {
    this.open = open;
  }

  /*
   * read-only property, based on other values
   */
  public double getPercentChange() {
    return getChange() / getOpen();
  }

  public String getSymbol() {
    return symbol;
  }

  public void setSymbol(String symbol) {
    this.symbol = symbol;
  }


  public String toString() {
    return getName();
  }
}
