package com.sencha.gxt.data.client.loader;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.web.bindery.autobean.shared.AutoBean;
import com.google.web.bindery.autobean.shared.AutoBeanFactory;
import com.sencha.gxt.data.client.DataTestCase;

public class GwtTestJsoReader extends DataTestCase {

  public void testLoadData() {
    ABF abf = GWT.create(ABF.class);
    JsoReader<NamedThing, NamedThing> reader = new JsoReader<GwtTestJsoReader.NamedThing, GwtTestJsoReader.NamedThing>(
        abf, NamedThing.class);

    NamedThing abc = reader.read(null, makeData());

    assertEquals("abc", abc.getName());
  }

  interface NamedThing {
    String getName();
  }

  interface ABF extends AutoBeanFactory {
    AutoBean<NamedThing> blah();
  }

  private native JavaScriptObject makeData() /*-{
		return {
			name : "abc"
		};
  }-*/;
}
