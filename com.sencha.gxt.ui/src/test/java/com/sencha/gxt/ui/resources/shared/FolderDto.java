package com.sencha.gxt.ui.resources.shared;

import java.util.List;


@SuppressWarnings("serial")
public class FolderDto extends BaseDto {

  private List<BaseDto> children;

  protected FolderDto() {

  }

  public FolderDto(Long id, String name) {
    super(id, name);
  }

  public List<BaseDto> getChildren() {
    return children;
  }

  public void setChildren(List<BaseDto> children) {
    this.children = children;
  }

  public void addChild(BaseDto child) {
    getChildren().add(child);
  }
}
