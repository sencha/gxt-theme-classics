package com.sencha.gxt.ui.resources.client.model;

import java.util.Date;

import com.google.gwt.i18n.client.DateTimeFormat;

public class Plant {

  private DateTimeFormat df = DateTimeFormat.getFormat("MM/dd/y");

  private String name;
  private String light;
  private double price;
  private Date available;
  private boolean indoor;

  public Plant() {

  }

  public Plant(String name, String light, double price, String available, boolean indoor) {
    setName(name);
    setLight(light);
    setPrice(price);
    setAvailable(df.parse(available));
    setIndoor(indoor);
  }

  public Date getAvailable() {
    return available;
  }

  public void setAvailable(Date available) {
    this.available = available;
  }

  public boolean isIndoor() {
    return indoor;
  }

  public void setIndoor(boolean indoor) {
    this.indoor = indoor;
  }

  public String getLight() {
    return light;
  }

  public void setLight(String light) {
    this.light = light;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

}
