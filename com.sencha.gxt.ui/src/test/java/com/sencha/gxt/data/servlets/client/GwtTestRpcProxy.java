package com.sencha.gxt.data.servlets.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.junit.client.GWTTestCase;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.sencha.gxt.data.servlets.shared.TestDataService;
import com.sencha.gxt.data.servlets.shared.TestDataServiceAsync;
import com.sencha.gxt.data.shared.loader.PagingLoadConfigBean;
import com.sencha.gxt.ui.resources.shared.FolderDto;

/**
 * Test to make sure RPC will be available for the rest of the proxy tests
 */
public class GwtTestRpcProxy extends GWTTestCase {

  @Override
  public String getModuleName() {
    return "com.sencha.gxt.data.servlets.DataTest";
  }

  public void testRpcFolder() {
    TestDataServiceAsync service = getService();
    delayTestFinish(1000);
    service.getTreeModel(new AsyncCallback<FolderDto>() {
      public void onFailure(Throwable caught) {
        fail();
      }

      public void onSuccess(FolderDto result) {
        assertNotNull(result);
        finishTest();
      }
    });
  }

  @SuppressWarnings({"unchecked", "rawtypes"})
  public void testRpcLoadConfig() {
    PagingLoadConfigBean config = new PagingLoadConfigBean();
    config.setLimit(10);
    config.setOffset(0);

    delayTestFinish(1000);
    AsyncCallback asyncCallback = new AsyncCallback() {
      public void onFailure(Throwable caught) {
        fail();
      }

      public void onSuccess(Object result) {
        assertNotNull(result);
        finishTest();
      }
    };

    getService().getStringLoadResult("test", asyncCallback);
    getService().getModelLoadResult("test", asyncCallback);

  }

  public void testRpcString() {
    TestDataServiceAsync service = getService();
    // the first GWT.create may take a long time, so don't start the async timer
    // until now
    delayTestFinish(1000);
    service.getTestString(new AsyncCallback<String>() {
      public void onFailure(Throwable caught) {
        fail();
      }

      public void onSuccess(String result) {
        assertNotNull(result);
        finishTest();
      }
    });
  }

  private TestDataServiceAsync getService() {
    TestDataServiceAsync service = GWT.create(TestDataService.class);
    ((ServiceDefTarget) service).setServiceEntryPoint(GWT.getModuleBaseURL() + "TestDataService");
    return service;
  }
}
