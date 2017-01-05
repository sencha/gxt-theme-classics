package com.sencha.gxt.ui.resources.shared;

import java.io.Serializable;

public class BaseDto implements Serializable {

  private Long id;
  private String name;
  
  protected BaseDto() {
    
  }
  
  public BaseDto(Long id, String name) {
    this.id = id;
    this.name = name;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

}
