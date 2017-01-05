package com.sencha.gxt.data.servlets.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.sencha.gxt.data.servlets.shared.TestDataService;
import com.sencha.gxt.ui.resources.shared.FolderDto;

public class TestDataServlet extends RemoteServiceServlet implements TestDataService {

  @Override
  public FolderDto getModelLoadResult(String config) {
    return new FolderDto(1l, "Music");
  }

  @Override
  public String getStringLoadResult(String config) {
    return config;
  }

  @Override
  public String getTestString() {
    return "hello";
  }

  @Override
  public FolderDto getTreeModel() {
    return new FolderDto(1l, "Music");
  }

}
