package com.sencha.gxt.data.servlets.shared;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.sencha.gxt.ui.resources.shared.FolderDto;

@RemoteServiceRelativePath("/TestDataService")
public interface TestDataService extends RemoteService {

  FolderDto getModelLoadResult(String config);

  String getStringLoadResult(String config);

  String getTestString();

  FolderDto getTreeModel();

}
