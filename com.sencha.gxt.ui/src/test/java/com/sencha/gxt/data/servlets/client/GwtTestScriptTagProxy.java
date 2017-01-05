package com.sencha.gxt.data.servlets.client;

import java.util.Arrays;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.junit.client.GWTTestCase;
import com.google.web.bindery.autobean.shared.AutoBean;
import com.google.web.bindery.autobean.shared.AutoBeanFactory;
import com.sencha.gxt.data.client.loader.JsoReader;
import com.sencha.gxt.data.client.loader.ScriptTagProxy;
import com.sencha.gxt.data.client.writer.UrlEncodingWriter;
import com.sencha.gxt.data.shared.loader.LoadEvent;
import com.sencha.gxt.data.shared.loader.LoadHandler;
import com.sencha.gxt.data.shared.loader.Loader;

public class GwtTestScriptTagProxy extends GWTTestCase {

  @Override
  public String getModuleName() {
    return "com.sencha.gxt.data.servlets.DataTest";
  }

  interface ABF extends AutoBeanFactory {
    AutoBean<Request1> req1();

    AutoBean<Response1> resp1();

    AutoBean<Request2> req2();
  }

  interface Request1 {
    String getA();

    void setA(String a);
  }
  interface Response1 {
    List<String> getA();

    void setA(List<String> a);
  }

  public void testSimpleRequest() {
    ABF abf = GWT.create(ABF.class);
    ScriptTagProxy<Request1> script = new ScriptTagProxy<Request1>(GWT.getModuleBaseURL() + "jsonp");
    script.setWriter(new UrlEncodingWriter<GwtTestScriptTagProxy.Request1>(abf, Request1.class));

    JsoReader<Response1, Response1> reader = new JsoReader<Response1, Response1>(abf, Response1.class);

    Loader<Request1, Response1> loader = new Loader<Request1, Response1>(script, reader);

    final Request1 loadConfig = abf.req1().as();
    loadConfig.setA("1234!@#$%^&*()");

    loader.addLoadHandler(new LoadHandler<Request1, Response1>() {
      @Override
      public void onLoad(LoadEvent<Request1, Response1> event) {
        assertEquals(loadConfig.getA(), event.getLoadConfig().getA());
        assertEquals(loadConfig.getA(), event.getLoadResult().getA().get(0));

        finishTest();
      }
    });

    delayTestFinish(1000);

    loader.load(loadConfig);
  }

  interface Request2 {
    List<Request1> getSubReqs();

    void setSubReqs(List<Request1> subReqs);
  }

  public void testMultipleSameNameFields() {
    ABF abf = GWT.create(ABF.class);
    ScriptTagProxy<Request2> script = new ScriptTagProxy<Request2>(GWT.getModuleBaseURL() + "jsonp");
    script.setWriter(new UrlEncodingWriter<GwtTestScriptTagProxy.Request2>(abf, Request2.class));
    
    JsoReader<Response1, Response1> reader = new JsoReader<Response1, Response1>(abf, Response1.class);

    Loader<Request2, Response1> loader = new Loader<Request2, Response1>(script, reader);

    final Request2 loadConfig = abf.req2().as();
    Request1 req1 = abf.req1().as();
    req1.setA("lmno");
    Request1 req2 = abf.req1().as();
    req2.setA("abcd");
    Request1 req3 = abf.req1().as();
    req3.setA("zyxw");
    loadConfig.setSubReqs(Arrays.asList(req1, req2, req3));

    loader.addLoadHandler(new LoadHandler<Request2, Response1>() {
      @Override
      public void onLoad(LoadEvent<Request2, Response1> event) {
        assertEquals(loadConfig.getSubReqs(), event.getLoadConfig().getSubReqs());
        for (int i = 0; i < loadConfig.getSubReqs().size(); i++) {
          assertEquals(loadConfig.getSubReqs().get(i).getA(), event.getLoadResult().getA().get(i));
        }

        finishTest();
      }
    });

    delayTestFinish(1000);

    loader.load(loadConfig);

  }

}
