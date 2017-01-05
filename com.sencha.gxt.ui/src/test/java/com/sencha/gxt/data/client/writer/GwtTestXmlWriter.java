package com.sencha.gxt.data.client.writer;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.web.bindery.autobean.shared.AutoBean;
import com.google.web.bindery.autobean.shared.AutoBean.PropertyName;
import com.google.web.bindery.autobean.shared.AutoBeanFactory;
import com.sencha.gxt.data.client.DataTestCase;
import com.sencha.gxt.data.client.loader.XmlReader;
import com.sencha.gxt.data.shared.loader.ListLoadResult;

public class GwtTestXmlWriter extends DataTestCase {
  interface TestAutoBeanFactory extends AutoBeanFactory {
    static TestAutoBeanFactory instance = GWT.create(TestAutoBeanFactory.class);

    AutoBean<Data> object();

    AutoBean<DataWithXpath> xpathObject();

    AutoBean<DataCollection> collection();

    AutoBean<DataWrapper> objectWrapper();

    AutoBean<DataListLoadResult> loadResult();

    AutoBean<DataWithComplexXPath> uglyXpathObject();

    AutoBean<StringInTag> stringInTag();
  }

  interface Data {
    @PropertyName("value")
    String getValue();

    int getNumber();
  }

  interface DataWithXpath extends Data {
    @PropertyName("value/@attr")
    String getValue();

    @PropertyName("number/@attr")
    int getNumber();
  }
  interface DataCollection {
    @PropertyName("simple")
    List<Data> getValues();
  }
  interface DataWithComplexXPath {
    @PropertyName("value[@attr]")
    String getValueWithAttr();

    // @PropertyName("value[not(@attr)]")
    // String getValueWithoutAttr();

    @PropertyName("value[@attr]/@attr")
    String getValueFromAttr();

    // @PropertyName("simple[value/@attr]/@attr")
    // String getValueFromParentsAttr();
    // ...
  }
  interface DataListLoadResult extends ListLoadResult<Data> {

  }

  private static final String SimpleDataXml = "<simple><value attr='other'>result</value><number attr='3'>2</number></simple>";
  // private static final String XpathDataXml =
  // "<simple attr='d'><value>b</value><value attr='c'>a</value></simple>";
  private static final String CollectionDataXml = "<values><simple><value>a</value><number>3</number></simple><simple><value>b</value><number>2</number></simple><simple><value>c</value><number>1</number></simple></values>";


  public void testXmlToModelAndBack() {

    XmlReader<Data, Data> reader = new XmlReader<Data, Data>(TestAutoBeanFactory.instance, Data.class);
    Data d = reader.read(null, SimpleDataXml);

    XmlWriter<Data> writer = new XmlWriter<Data>(TestAutoBeanFactory.instance, Data.class, "simple");

    Data d2 = reader.read(null, writer.write(d));

    assertEquals(d.getNumber(), d2.getNumber());
    assertEquals(d.getValue(), d2.getValue());
  }

  public void testXmlAttrToModelAndBack() {
    XmlReader<DataWithXpath, DataWithXpath> reader = new XmlReader<DataWithXpath, DataWithXpath>(
        TestAutoBeanFactory.instance, DataWithXpath.class);

    // make sure simple data types can be read from attrs, using xpaths
    DataWithXpath d = reader.read(null, SimpleDataXml);

    XmlWriter<DataWithXpath> writer = new XmlWriter<DataWithXpath>(TestAutoBeanFactory.instance, DataWithXpath.class,
        "simple");
    DataWithXpath d2 = reader.read(null, writer.write(d));

    assertEquals(d.getNumber(), d2.getNumber());
    assertEquals(d.getValue(), d2.getValue());
  }

  public void testXmlToListAndBack() {
    XmlReader<DataCollection, DataCollection> reader = new XmlReader<DataCollection, DataCollection>(
        TestAutoBeanFactory.instance, DataCollection.class);

    DataCollection c = reader.read(null, CollectionDataXml);

    XmlWriter<DataCollection> writer = new XmlWriter<GwtTestXmlWriter.DataCollection>(TestAutoBeanFactory.instance,
        DataCollection.class, "values");
    DataCollection c2 = reader.read(null, writer.write(c));

    assertEquals(c.getValues().size(), c2.getValues().size());
    for (int i = 0; i < c.getValues().size(); i++) {
      assertEquals(c.getValues().get(i).getNumber(), c2.getValues().get(i).getNumber());
      assertEquals(c.getValues().get(i).getValue(), c2.getValues().get(i).getValue());
    }
  }

  interface DataWrapper {
    Data getObject();
  }

  private static final String TagWithWrappedObject = "<data><object><value>result</value><number>2</number></object></data>";

  public void testXmlToWrappedObjAndBack() {
    XmlReader<DataWrapper, DataWrapper> reader = new XmlReader<DataWrapper, DataWrapper>(TestAutoBeanFactory.instance,
        DataWrapper.class);
    DataWrapper data = reader.read(null, TagWithWrappedObject);

    XmlWriter<DataWrapper> writer = new XmlWriter<DataWrapper>(TestAutoBeanFactory.instance, DataWrapper.class, "data");
    DataWrapper data2 = reader.read(null, writer.write(data));
    assertEquals(data.getObject().getNumber(), data2.getObject().getNumber());
    assertEquals(data.getObject().getValue(), data2.getObject().getValue());
  }

  interface StringInTag {
    @PropertyName("")
    String getText();
  }

  private static final String TagWithText = "<tag>contents</tag>";

  public void testStringInTag() {
    XmlReader<StringInTag, StringInTag> reader = new XmlReader<StringInTag, StringInTag>(TestAutoBeanFactory.instance,
        StringInTag.class);
    StringInTag data = reader.read(null, TagWithText);

    assertEquals("contents", data.getText());

    XmlWriter<StringInTag> writer = new XmlWriter<StringInTag>(TestAutoBeanFactory.instance, StringInTag.class, "tag");

    String xml = writer.write(data);
    assertEquals(TagWithText, xml.trim());
  }

  public void testNulls() {
    //null root object, still emits a outer tag
    XmlWriter<StringInTag> writer1 = new XmlWriter<StringInTag>(TestAutoBeanFactory.instance, StringInTag.class, "tag");
    String xml1 = writer1.write(null);
    assertEquals("<tag/>", xml1.trim());//might be <tag></tag>

    XmlWriter<DataWrapper> writer2 = new XmlWriter<DataWrapper>(TestAutoBeanFactory.instance, DataWrapper.class, "data");
    String xml2 = writer2.write(null);
    assertEquals("<data/>", xml2.trim());//might be <data></data>

    //null property in object
    String xml3 = writer1.write(TestAutoBeanFactory.instance.stringInTag().as());
    assertEquals("<tag/>", xml3.trim());

    //one null, one non-null property in object
    XmlWriter<Data> writer3 = new XmlWriter<Data>(TestAutoBeanFactory.instance, Data.class, "data");
    String xml4 = writer3.write(new Data() {
      @Override
      public String getValue() {
        return null;
      }

      @Override
      public int getNumber() {
        return 0;
      }
    });
    assertEquals("<data><number>0</number></data>", xml4.trim());

    //null object in collection, just skip it
    XmlWriter<DataCollection> writer4 = new XmlWriter<DataCollection>(TestAutoBeanFactory.instance, DataCollection.class, "outer");
    String xml5 = writer4.write(new DataCollection() {
      @Override
      public List<Data> getValues() {
        List<Data> list = new ArrayList<Data>();
        list.add(null);
        return list;
      }
    });
    assertEquals("<outer/>", xml5.trim());
  }
}
