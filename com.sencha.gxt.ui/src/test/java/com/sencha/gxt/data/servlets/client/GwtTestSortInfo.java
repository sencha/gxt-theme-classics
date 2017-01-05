package com.sencha.gxt.data.servlets.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.junit.client.GWTTestCase;
import com.google.web.bindery.autobean.shared.AutoBean;
import com.google.web.bindery.autobean.shared.AutoBeanFactory;
import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.RequestFactory;
import com.google.web.bindery.requestfactory.shared.Service;
import com.sencha.gxt.data.servlets.server.DummyService;
import com.sencha.gxt.data.shared.SortDir;
import com.sencha.gxt.data.shared.SortInfo;
import com.sencha.gxt.data.shared.SortInfoBean;

public class GwtTestSortInfo extends GWTTestCase {
  @Override
  public String getModuleName() {
    return "com.sencha.gxt.data.servlets.DataTest";
  }
  interface ABF extends AutoBeanFactory {
    AutoBean<SortInfo> sortInfo();
  }

  public void testBeanToAutoBean() {
    SortInfoBean bean = new SortInfoBean("asdf", SortDir.ASC);

    ABF abf = GWT.create(ABF.class);
    SortInfo ab = abf.create(SortInfo.class, bean).as();
    assertEquals(bean.getSortField(), ab.getSortField());
    assertEquals(bean.getSortDir(), ab.getSortDir());
  }

  @Service(DummyService.class)
  interface RC extends RequestContext {
    Request<SortInfo> fakeLoad(SortInfo info);
  }
  interface RF extends RequestFactory {
    RC req();
  }
//
//  public void testSendBean() {
//    final SortInfoBean bean = new SortInfoBean("asdf", SortDir.ASC);
//    ABF abf = GWT.create(ABF.class);
//
//    AutoBean<SortInfo> wrapped = abf.create(SortInfo.class, bean);
//    wrapped.setFrozen(true);
//
//    RF rf = GWT.create(RF.class);
//    rf.initialize(new SimpleEventBus());
//
//    wrapped.setTag(Constants.STABLE_ID, ((IdFactory) rf).allocateId(SortInfo.class));
//
//    delayTestFinish(5000);
//    rf.req().fakeLoad(wrapped.as()).fire(new Receiver<SortInfo>() {
//      @Override
//      public void onSuccess(SortInfo response) {
//        assertEquals(bean.getSortField(), response.getSortField());
//        assertEquals(bean.getSortDir(), response.getSortDir());
//        finishTest();
//      }
//
//      @Override
//      public void onFailure(ServerFailure error) {
//        fail(error.getMessage());
//      }
//    });
//  }
}
