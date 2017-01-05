package com.sencha.gxt.data.client.writer;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.http.client.URL;
import com.google.web.bindery.autobean.shared.AutoBean;
import com.google.web.bindery.autobean.shared.AutoBeanFactory;
import com.sencha.gxt.data.client.DataTestCase;

public class GwtTestUrlEncodingWriter extends DataTestCase {
  
  interface ABF extends AutoBeanFactory {
    AutoBean<SimpleBean> simple();

    AutoBean<NestedBean> nested();

    AutoBean<CollectionBean> collection();
  }
  
  interface SimpleBean {
    String getStringProperty();

    void setStringProperty(String stringProperty);

    int getIntProperty();

    void setIntProperty(int intProperty);

    Date getDateProperty();

    void setDateProperty(Date dateProperty);
  }
  
  interface NestedBean {
    SimpleBean getSubProperty();

    void setSubProperty(SimpleBean subProperty);
  }
  
  interface CollectionBean {
    List<SimpleBean> getCollection();

    void setCollection(List<SimpleBean> collection);
  }

  public void testSimpleBean() {
    ABF factory = GWT.create(ABF.class);
    SimpleBean bean = factory.simple().as();
    bean.setIntProperty(9374);
    bean.setStringProperty("hello");

    UrlEncodingWriter<SimpleBean> writer = new UrlEncodingWriter<SimpleBean>(factory, SimpleBean.class);
    String query = writer.write(bean);

    // assert each field, value
    assertTrue(query.contains("stringProperty=hello"));
    assertTrue(query.contains("intProperty=9374"));

    // assert total length (checking presence of &)
    // 1 is &
    int expectedLength = "stringProperty=hello".length() + "intProperty=9374".length() + 1;
    assertEquals(expectedLength, query.length());
  }

  public void testNestedObject() {
    ABF factory = GWT.create(ABF.class);
    SimpleBean simple = factory.simple().as();
    NestedBean bean = factory.nested().as();
    bean.setSubProperty(simple);
    simple.setStringProperty("fdsa");
    simple.setIntProperty(5555);

    UrlEncodingWriter<NestedBean> writer = new UrlEncodingWriter<NestedBean>(factory, NestedBean.class);
    String query = writer.write(bean);

    assertFalse(query.startsWith("&"));

    // assert each field, value
    assertTrue(query.contains("stringProperty=fdsa"));
    assertTrue(query.contains("intProperty=5555"));

    // assert total length
    int expectedLength = "stringProperty=fdsa".length() + "intProperty=5555".length() + 1;
    assertEquals(expectedLength, query.length());
  }

  public void testCollectionObject() {
    ABF factory = GWT.create(ABF.class);
    CollectionBean bean = factory.collection().as();
    SimpleBean simple1 = factory.simple().as();
    SimpleBean simple2 = factory.simple().as();
    bean.setCollection(Arrays.asList(simple1, simple2));
    simple1.setIntProperty(321);
    simple2.setIntProperty(0);

    UrlEncodingWriter<CollectionBean> writer = new UrlEncodingWriter<CollectionBean>(factory, CollectionBean.class);
    String query = writer.write(bean);

    // assert each field, value (in order through the list)
    assertTrue(query.contains("intProperty=321"));
    assertTrue(query.contains("&intProperty=0"));// to be sure it is last

    // assert total length
    int expectedLength = "intProperty=321".length() + "intProperty=0".length() + 1;
    assertEquals(expectedLength, query.length());
  }

  public void testEncodedCharacters() {
    ABF factory = GWT.create(ABF.class);
    SimpleBean bean = factory.simple().as();

    bean.setStringProperty("&=#?/+");
    UrlEncodingWriter<SimpleBean> writer = new UrlEncodingWriter<SimpleBean>(factory, SimpleBean.class);
    String query = writer.write(bean);

    String encodedValue = query.split("&")[1].substring("stringProperty=".length());
    assertEquals("&=#?/+", URL.decodeQueryString(encodedValue));

    assertFalse(encodedValue.contains("&"));
    assertFalse(encodedValue.contains("="));
    assertFalse(encodedValue.contains("#"));
    assertFalse(encodedValue.contains("?"));
    assertFalse(encodedValue.contains("/"));
    assertFalse(encodedValue.contains("+"));
  }
}
