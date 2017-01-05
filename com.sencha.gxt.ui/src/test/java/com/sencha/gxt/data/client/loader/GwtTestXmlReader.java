package com.sencha.gxt.data.client.loader;

import java.util.List;

import org.junit.Test;

import com.google.gwt.core.client.GWT;
import com.google.gwt.junit.DoNotRunWith;
import com.google.gwt.junit.Platform;
import com.google.web.bindery.autobean.shared.AutoBean;
import com.google.web.bindery.autobean.shared.AutoBean.PropertyName;
import com.google.web.bindery.autobean.shared.AutoBeanFactory;
import com.sencha.gxt.data.client.DataTestCase;
import com.sencha.gxt.data.shared.loader.ListLoadResult;

public class GwtTestXmlReader extends DataTestCase {

  interface Data {
    int getNumber();

    @PropertyName("value")
    String getValue();
    
    //can be null, so boxed
    @PropertyName("@attrNum")
    Integer getAttr();
  }

  interface DataCollection {
    @PropertyName("simple")
    List<Data> getValues();
  }
  
  interface DataListLoadResult extends ListLoadResult<Data> {

  }
  
  interface DataWithComplexXPath {
    @PropertyName("value[@attr]/@attr")
    String getValueFromAttr();

    // @PropertyName("value[not(@attr)]")
    // String getValueWithoutAttr();

    @PropertyName("value[@attr]")
    String getValueWithAttr();

    // @PropertyName("simple[value/@attr]/@attr")
    // String getValueFromParentsAttr();
    // ...
  }
  
  interface DataWithXpath extends Data {
    @PropertyName("number/@attr")
    int getNumber();

    @PropertyName("value/@attr")
    String getValue();
  }

  interface StringInTag {
    @PropertyName("")
    String getText();
  }

  interface Strings {
    @PropertyName("str")
    List<String> getStrings();
  }

  interface TestAutoBeanFactory extends AutoBeanFactory {
    static TestAutoBeanFactory instance = GWT.create(TestAutoBeanFactory.class);

    AutoBean<DataCollection> collection();

    AutoBean<DataListLoadResult> loadResult();

    AutoBean<Data> object();

    AutoBean<StringInTag> stringInTag();

    AutoBean<Strings> strings();

    AutoBean<DataWithComplexXPath> uglyXpathObject();

    AutoBean<DataWithXpath> xpathObject();

    // AutoBean<StringsWithAttributes> stringsAttrs();
    
    AutoBean<NamespacedData> namedspace();
  }

  private static final String SimpleDataXml = "<simple><value attr='other'>result</value><number attr='3'>2</number></simple>";

  private static final String XpathDataXml = "<simple attr='d'><value>b</value><value attr='c'>a</value></simple>";

  private static final String CollectionDataXml = "<values><simple attrNum='1'><value>a</value><number>3</number></simple><simple attrNum='2'><value>b</value><number>2</number></simple><simple attrNum='3'><value>c</value><number>1</number></simple></values>";

  private static final String TagWithText = "<tag>contents</tag>";

  private static final String XmlCharsXml = "<strs><str attr='&lt;'>&amp;</str><str attr='&gt;'>&#38;</str><str attr='&#38;'><![CDATA[>]]></str><str attr='&amp;'>&lt;</str></strs>";

  public void testCollectionRead() {
    XmlReader<DataCollection, DataCollection> reader = new XmlReader<DataCollection, DataCollection>(
        TestAutoBeanFactory.instance, DataCollection.class);

    DataCollection c = reader.read(null, CollectionDataXml);
    assertEquals(3, c.getValues().size());
    Data d1 = c.getValues().get(0);
    assertEquals((Integer)1, d1.getAttr());
    assertEquals("a", d1.getValue());
    assertEquals(3, d1.getNumber());

    Data d2 = c.getValues().get(1);
    assertEquals((Integer)2, d2.getAttr());
    assertEquals("b", d2.getValue());
    assertEquals(2, d2.getNumber());

    Data d3 = c.getValues().get(2);
    assertEquals((Integer)3, d3.getAttr());
    assertEquals("c", d3.getValue());
    assertEquals(1, d3.getNumber());
  }

  @Test
  public void testReadAttrObject() {
    XmlReader<DataWithXpath, DataWithXpath> reader = new XmlReader<DataWithXpath, DataWithXpath>(
        TestAutoBeanFactory.instance, DataWithXpath.class);

    // make sure simple data types can be read from attrs, using xpaths
    DataWithXpath d = reader.read(null, SimpleDataXml);
    assertNotNull(d);
    assertEquals("other", d.getValue());
    assertEquals(3, d.getNumber());
  }

  @Test
  public void testReadSimpleObject() {
    XmlReader<Data, Data> reader = new XmlReader<Data, Data>(TestAutoBeanFactory.instance, Data.class);

    // make sure we can read simple data types from xml
    Data d = reader.read(null, SimpleDataXml);
    assertNotNull(d);
    assertEquals("result", d.getValue());
    assertEquals(2, d.getNumber());
  }

  public void testTagText() {
    XmlReader<StringInTag, StringInTag> reader = new XmlReader<StringInTag, StringInTag>(TestAutoBeanFactory.instance,
        StringInTag.class);
    StringInTag data = reader.read(null, TagWithText);

    assertEquals("contents", data.getText());
  }

  public void testXmlCharacters() {
    XmlReader<Strings, Strings> reader = new XmlReader<Strings, Strings>(TestAutoBeanFactory.instance, Strings.class);
    List<String> strs = reader.read(null, XmlCharsXml).getStrings();
    assertEquals(4, strs.size());
    assertEquals("&", strs.get(0));
    assertEquals("&", strs.get(1));
    assertEquals(">", strs.get(2));
    assertEquals("<", strs.get(3));
  }

  public void testXpathRead() {
    XmlReader<DataWithComplexXPath, DataWithComplexXPath> reader = new XmlReader<DataWithComplexXPath, DataWithComplexXPath>(
        TestAutoBeanFactory.instance, DataWithComplexXPath.class);
    DataWithComplexXPath d = reader.read(null, XpathDataXml);

    assertNotNull(d);

    assertEquals("a", d.getValueWithAttr());
    // assertEquals("b", d.getValueWithoutAttr());
    assertEquals("c", d.getValueFromAttr());

    // assertEquals("d", d.getValueFromParentsAttr());
  }

  interface NamespacedData {
//    @PropertyName("ns:element")
//    String getNamespacedElementContent();
    
    @PropertyName("element")
    String getNamespacedElementWithoutNamespaceContent();
    
    @PropertyName("otherElement/@ns:attribute")
    String getNamespacedAttributeContent();
  }
  private static final String NamespacedXml = "<outer xmlns:ns=\"http://example.com\"><ns:element>text</ns:element><otherElement ns:attribute='123' /></outer>";

  @DoNotRunWith(Platform.HtmlUnitUnknown)
  public void testNamespacedXml() {
    XmlReader<NamespacedData,NamespacedData> reader = new XmlReader<NamespacedData, NamespacedData>(TestAutoBeanFactory.instance, NamespacedData.class);
    NamespacedData d = reader.read(null, NamespacedXml);
    
    //These still do not work in IE8
    //assertEquals("text", d.getNamespacedElementContent());
//    assertEquals("text", d.getNamespacedElementWithoutNamespaceContent());
    
    assertEquals("123", d.getNamespacedAttributeContent());
  }
}
