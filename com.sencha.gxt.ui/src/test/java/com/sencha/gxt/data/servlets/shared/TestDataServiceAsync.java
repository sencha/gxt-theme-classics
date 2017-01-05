package com.sencha.gxt.data.servlets.shared;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.sencha.gxt.ui.resources.shared.FolderDto;

public interface TestDataServiceAsync {

  public void getTreeModel(AsyncCallback<FolderDto> callback);

  public void getTestString(AsyncCallback<String> callback);

//  public void getTestModel(AsyncCallback<BaseTreeModel> callback);

  public void getStringLoadResult(String config, AsyncCallback<String> callback);

  public void getModelLoadResult(String config, AsyncCallback<FolderDto> callback);

}
