package com.sencha.gxt.data.shared;

import com.sencha.gxt.core.client.ValueProvider;

public class Data {

  public static class DataKeyProvider implements ModelKeyProvider<Data> {
    @Override
    public String getKey(Data item) {
      return item.id;
    }
  }

  public static class DataNumberProperty implements ValueProvider<Data, Integer> {
    public static final DataNumberProperty instance = new DataNumberProperty();

    @Override
    public Integer getValue(Data object) {
      return object.number;
    }

    @Override
    public void setValue(Data object, Integer value) {
      object.number = value;
    }

    @Override
    public String getPath() {
      return "number";
    }
  }

  public static class DataNameProperty implements ValueProvider<Data, String> {
    public static final DataNameProperty instance = new DataNameProperty();
    @Override
    public String getValue(Data object) {
      return object.name;
    }

    @Override
    public void setValue(Data object, String value) {
      object.name = value;
    }

    @Override
    public String getPath() {
      return "name";
    }
  }

  public static class DataDescriptionProperty implements ValueProvider<Data, String> {
    public static final DataDescriptionProperty instance = new DataDescriptionProperty();

    @Override
    public String getValue(Data object) {
      return object.getDescription();
    }

    @Override
    public void setValue(Data object, String value) {
      object.setDescription(value);
    }

    @Override
    public String getPath() {
      return "description";
    }
  }

  private static int nextId = 0;
  private final String id = String.valueOf(nextId++);
  private String name;
  private int number;
  private String description;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getNumber() {
    return number;
  }

  public void setNumber(int number) {
    this.number = number;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getId() {
    return id;
  }

}
