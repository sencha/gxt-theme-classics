package com.sencha.gxt.xtemplates.client;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.sencha.gxt.core.client.XTemplates;
import com.sencha.gxt.core.client.dom.XDOM;
import com.sencha.gxt.core.client.dom.XElement;
import com.sencha.gxt.core.client.util.DateWrapper;

public class GwtTestXTemplatesOther extends GwtTestXTemplates {

  public interface TestSimpleVariableInterface extends XTemplates {
    @XTemplate("{test}")
    public SafeHtml getString(String test);
  }

  public void testSimpleVariable() {
    TestSimpleVariableInterface c = GWT.create(TestSimpleVariableInterface.class);
    String s = "This is a test";
    SafeHtml html = c.getString(s);
    assertEquals(s, html.asString());
  }

  public interface TestSimpleVariableStringStringInterface extends XTemplates {
    @XTemplate("{test}{test2}")
    public SafeHtml getString(String test, String test2);
  }

  public void testSimpleVariableStringString() {
    TestSimpleVariableStringStringInterface c = GWT.create(TestSimpleVariableStringStringInterface.class);
    String s = "This is a test";
    SafeHtml html = c.getString(s, s);
    assertEquals(s + s, html.asString());
  }

  public interface TestSimpleVariableAppendInterface extends XTemplates {
    @XTemplate("{[test + test2]}")
    public SafeHtml getString(String test, String test2);
  }

  public void testSimpleVariableAppend() {
    TestSimpleVariableAppendInterface c = GWT.create(TestSimpleVariableAppendInterface.class);
    String s = "This is a test";
    SafeHtml html = c.getString(s, s);
    assertEquals(s + s, html.asString());
  }

  public interface TestNestedVariableAppendInterface extends XTemplates {
    @XTemplate("{[a.toString + b.toString]}")
    SafeHtml getString(String a, String b);
  }
  
  public void testNestedVariableAppend() {
    TestNestedVariableAppendInterface c = GWT.create(TestNestedVariableAppendInterface.class);
    String s = "This is a test";
    SafeHtml html = c.getString(s, s);
    assertEquals(s + s, html.asString());
  }
  
  public interface TestSimpleThisIntegerScopeInterface extends XTemplates {
    @XTemplate("{test}")
    public SafeHtml getInteger(int test);
  }

  public void testSimpleThisIntegerScope() {
    TestSimpleThisIntegerScopeInterface c = GWT.create(TestSimpleThisIntegerScopeInterface.class);
    int i = 123;
    SafeHtml html = c.getInteger(i);
    assertEquals(new Integer(i).toString(), html.asString());
  }

  public interface TestSimpleThisStringScopeInterface extends XTemplates {
    @XTemplate("{.}")
    public SafeHtml getString(String test);
  }

  public void testSimpleThisStringScope() {
    TestSimpleThisStringScopeInterface c = GWT.create(TestSimpleThisStringScopeInterface.class);
    String s = "This is a test";
    SafeHtml html = c.getString(s);
    assertEquals(s, html.asString());
  }

  public interface TestSimpleThisIntegerScope2Interface extends XTemplates {
    @XTemplate("{.}")
    public SafeHtml getInteger(int test);
  }

  public void testSimpleThisIntegerScope2() {
    TestSimpleThisIntegerScope2Interface c = GWT.create(TestSimpleThisIntegerScope2Interface.class);
    int i = 123;
    SafeHtml html = c.getInteger(i);
    assertEquals(new Integer(i).toString(), html.asString());
  }
  public interface TestPojoStringInterface extends XTemplates {
    @XTemplate("{child.name}")
    public SafeHtml getChild(Child child);
  }

  public void testPojoString() {
    TestPojoStringInterface c = GWT.create(TestPojoStringInterface.class);
    Child child = new Child("Alec", 4, new DateWrapper(2004, 1, 1).asDate());
    String expected = "Alec";
    SafeHtml html = c.getChild(child);
    assertEquals(expected, html.asString());
  }

  public interface TestPojoIntegerInterface extends XTemplates {
    @XTemplate("{child.age}")
    public SafeHtml getChild(Child child);

    @XTemplate("{child.ageField}")
    public SafeHtml getChildField(Child child);
  }

  public void testPojoInteger() {
    TestPojoIntegerInterface c = GWT.create(TestPojoIntegerInterface.class);
    Child child = new Child("Alec", 4, new DateWrapper(2004, 1, 1).asDate());
    String expected = "4";
    SafeHtml html = c.getChild(child);
    assertEquals(expected, html.asString());

    SafeHtml htmlField = c.getChildField(child);
    assertEquals(expected, htmlField.asString());
  }

  public interface TestPojoParentStringInteface extends XTemplates {
    @XTemplate("{parent.name}")
    public SafeHtml getParent(Parent parent);

    @XTemplate("{parent.nameField}")
    public SafeHtml getParentField(Parent parent);
  }

  public void testPojoParentString() {
    TestPojoParentStringInteface c = GWT.create(TestPojoParentStringInteface.class);
    Parent parent = new Parent();
    parent.setName("Parent");
    List<Child> children = new ArrayList<Child>();
    children.add(new Child("Alec", 4, new DateWrapper(2004, 1, 1).asDate()));
    children.add(new Child("Lia", 2, new DateWrapper(2005, 1, 1).asDate()));
    parent.setChildren(children);
    String expected = "Parent";
    SafeHtml html = c.getParent(parent);
    assertEquals(expected, html.asString());

    SafeHtml htmlField = c.getParentField(parent);
    assertEquals(expected, htmlField.asString());
  }

  public interface TestRemoteFileInterface extends XTemplates {
    @XTemplate(source = "GwtTestXTemplates2.html")
    public SafeHtml getParent(Parent parents);
  }

  public void testRemoteFile() {
    TestRemoteFileInterface c = GWT.create(TestRemoteFileInterface.class);
    Parent parent = new Parent();
    parent.setName("Parent");
    List<Child> children = new ArrayList<Child>();
    children.add(new Child("Alec", 4, new DateWrapper(2004, 1, 1).asDate()));
    children.add(new Child("Lia", 2, new DateWrapper(2005, 1, 1).asDate()));
    parent.setChildren(children);
    String expected = "AlecLia";
    SafeHtml html = c.getParent(parent);
    assertEquals(expected, html.asString());
  }

  public interface TestAbsoluteRemoteFileInterface extends XTemplates {
    @XTemplate(source = "com/sencha/gxt/xtemplates/client/GwtTestXTemplates2.html")
    public SafeHtml getParent(Parent parents);
  }

  public void testAbsoluteRemoteFile() {
    TestAbsoluteRemoteFileInterface c = GWT.create(TestAbsoluteRemoteFileInterface.class);
    Parent parent = new Parent();
    parent.setName("Parent");
    List<Child> children = new ArrayList<Child>();
    children.add(new Child("Alec", 4, new DateWrapper(2004, 1, 1).asDate()));
    children.add(new Child("Lia", 2, new DateWrapper(2005, 1, 1).asDate()));
    parent.setChildren(children);
    String expected = "AlecLia";
    SafeHtml html = c.getParent(parent);
    assertEquals(expected, html.asString());
  }

  public interface TestRemoteFileParentInterface extends XTemplates {
    @XTemplate(source = "GwtTestXTemplates.html")
    public SafeHtml getParent(Parent parent);
  }

  public void testRemoteParentFile() {
    TestRemoteFileParentInterface c = GWT.create(TestRemoteFileParentInterface.class);
    Parent parent = new Parent();
    parent.setName("Parent");
    List<Child> children = new ArrayList<Child>();
    children.add(new Child("Alec", 4, new DateWrapper(2004, 1, 1).asDate()));
    children.add(new Child("Lia", 2, new DateWrapper(2005, 1, 1).asDate()));
    parent.setChildren(children);
    String expected = "AlecLia";
    SafeHtml html = c.getParent(parent);
    assertEquals(expected, html.asString());
  }
  public interface TestAutoBeansInterface extends XTemplates {
    @XTemplate("{stocks.name}")
    public SafeHtml getProxy(GwtTestXTemplatesProxy stocks);
  }

  public void testAutoBeans() {
    TestAutoBeansInterface c = GWT.create(TestAutoBeansInterface.class);
    String expected = "Apple Inc.";
    SafeHtml html = c.getProxy(AutoBeans.getStock());
    assertEquals(expected, html.asString());
  }

  public interface TestAutoBeansChildStringInterface extends XTemplates {
    @XTemplate("<tpl for=\"stocks\">{name}</tpl>")
    public SafeHtml getProxy(List<GwtTestXTemplatesProxy> stocks);
  }

  public void testAutoBeansChildString() {
    TestAutoBeansChildStringInterface c = GWT.create(TestAutoBeansChildStringInterface.class);
    String expected = "Apple Inc.Cisco Systems, Inc.";
    SafeHtml html = c.getProxy(AutoBeans.getStocks());
    assertEquals(expected, html.asString());
  }
  // passing list and number
  // passing object and number
  // listed named objcts
  public interface TestMultiInterface extends XTemplates {
    @XTemplate("{child.name}")
    public SafeHtml getChild(Child child);
    @XTemplate("{parent.name}")
    public SafeHtml getChild(Parent parent);
  }

  public void testMulti() {
    TestMultiInterface c = GWT.create(TestMultiInterface.class);
    Child child = new Child("Alec", 4, new DateWrapper(2004, 1, 1).asDate());
    Parent parent = new Parent();
    parent.setName("Parent");
    SafeHtml html = c.getChild(child);
    String expected = "Alec";
    assertEquals(expected, html.asString());
    html = c.getChild(parent);
    expected = "Parent";
    assertEquals(expected, html.asString());
  }
  public interface TestMulti2Interface extends XTemplates {
    @XTemplate("{child.name}")
    public SafeHtml getChild(Child child);
    @XTemplate("{par.name}")
    public SafeHtml getChild(Parent par);
  }

  public void testMulti2() {
    TestMulti2Interface c = GWT.create(TestMulti2Interface.class);
    Child child = new Child("Alec", 4, new DateWrapper(2004, 1, 1).asDate());
    Parent parent = new Parent();
    parent.setName("Parent");
    SafeHtml html = c.getChild(child);
    String expected = "Alec";
    assertEquals(expected, html.asString());
    html = c.getChild(parent);
    expected = "Parent";
    assertEquals(expected, html.asString());
  }
  public interface TestMulti3Interface extends XTemplates {
    @XTemplate("{child.name}")
    public SafeHtml getChild(Child child);
    @XTemplate("{parent.name}")
    public SafeHtml getParent(Parent parent);
  }

  public void testMulti3() {
    TestMulti3Interface c = GWT.create(TestMulti3Interface.class);
    Child child = new Child("Alec", 4, new DateWrapper(2004, 1, 1).asDate());
    Parent parent = new Parent();
    parent.setName("Parent");
    SafeHtml html = c.getChild(child);
    String expected = "Alec";
    assertEquals(expected, html.asString());
    html = c.getParent(parent);
    expected = "Parent";
    assertEquals(expected, html.asString());
  }
  public interface TestMulti4Interface extends XTemplates {
    @XTemplate("{child.name}")
    public SafeHtml getChild(Child child);
    @XTemplate("{parent.name}")
    public SafeHtml getParent(Parent parent);
  }

  public void testMulti4() {
    TestMulti4Interface c = GWT.create(TestMulti4Interface.class);
    Child child = new Child("Alec", 4, new DateWrapper(2004, 1, 1).asDate());
    Parent parent = new Parent();
    parent.setName("Parent");
    SafeHtml html = c.getChild(child);
    String expected = "Alec";
    assertEquals(expected, html.asString());
    html = c.getParent(parent);
    expected = "Parent";
    assertEquals(expected, html.asString());
  }
  public interface TestMulti5Interface extends XTemplates {
    @XTemplate("1{child.name}")
    public SafeHtml getChild(Child child);
    @XTemplate("2{child.name}")
    public SafeHtml getChild2(Child child);
  }

  public void testMulti5() {
    TestMulti5Interface c = GWT.create(TestMulti5Interface.class);
    Child child = new Child("Alec", 4, new DateWrapper(2004, 1, 1).asDate());
    Child child2 = new Child("Lia", 2, new DateWrapper(2005, 1, 1).asDate());
    SafeHtml html = c.getChild(child);
    String expected = "1Alec";
    assertEquals(expected, html.asString());
    html = c.getChild2(child2);
    expected = "2Lia";
    assertEquals(expected, html.asString());
  }
  public interface TestMulti6Interface extends XTemplates {
    @XTemplate("<tpl if=\"true\">1{child.name}</tpl>")
    public SafeHtml getChild(Child child);
    @XTemplate("2{child.name}")
    public SafeHtml getChild2(Child child);
  }

  public void testMulti6() {
    TestMulti6Interface c = GWT.create(TestMulti6Interface.class);
    Child child = new Child("Alec", 4, new DateWrapper(2004, 1, 1).asDate());
    Child child2 = new Child("Lia", 2, new DateWrapper(2005, 1, 1).asDate());
    SafeHtml html = c.getChild(child);
    String expected = "1Alec";
    assertEquals(expected, html.asString());
    html = c.getChild2(child2);
    expected = "2Lia";
    assertEquals(expected, html.asString());
  }
  public interface TestMulti7Interface extends XTemplates {
    @XTemplate("2{child.name}")
    public SafeHtml getChild(Child child);
    @XTemplate("<tpl if=\"true\">1{child.name}</tpl>")
    public SafeHtml getChild2(Child child);
  }

  public void testMulti7() {
    TestMulti7Interface c = GWT.create(TestMulti7Interface.class);
    Child child = new Child("Alec", 4, new DateWrapper(2004, 1, 1).asDate());
    Child child2 = new Child("Lia", 2, new DateWrapper(2005, 1, 1).asDate());
    SafeHtml html = c.getChild(child);
    String expected = "2Alec";
    assertEquals(expected, html.asString());
    html = c.getChild2(child2);
    expected = "1Lia";
    assertEquals(expected, html.asString());
  }

  static class SingleVarData {
    private String a;

    public void setA(String a) {
      this.a = a;
    }

    public String getA() {
      return a;
    }
  }
  interface SingleVarTest extends XTemplates {
    @XTemplate("{a}")
    SafeHtml template(SingleVarData data);
    
    @XTemplate("{getA}")
    SafeHtml templateWithFullMethod(SingleVarData data);
  }

  public void testSingleVarData() {
    // test for EXTGWT-596
    SingleVarData data = new SingleVarData();
    data.setA("asdf");

    SingleVarTest t = GWT.create(SingleVarTest.class);
    String results = t.template(data).asString();

    assertEquals("asdf", results);
    
    String fullMethodResults = t.templateWithFullMethod(data).asString();
    assertEquals("asdf", fullMethodResults);
  }

  static class NonGetData {
    private String value = "asdf";

    public String value() {
      return value;
    }
  }
  interface NonGetDataInterface extends XTemplates {
    @XTemplate("{obj.value}")
    SafeHtml template(NonGetData obj);
  }

  public void testNonGetData() {
    NonGetData data = new NonGetData();
    NonGetDataInterface tpl = GWT.create(NonGetDataInterface.class);

    assertEquals("asdf", tpl.template(data).asString());
  }

  interface WhitespaceInterface extends XTemplates {
    @XTemplate(source = "GwtTestXTemplates3.html")
    SafeHtml template();
  }

  public void testWhitespaceInTemplate() {
    WhitespaceInterface t = GWT.create(WhitespaceInterface.class);
    XElement elt = XDOM.create(t.template()).cast();

    assertEquals(4, elt.getChildCount());

    Element tplOuter = elt.selectNode(".tpl-outer");

    assertEquals(2, tplOuter.getChildCount());
  }
  
  static class AnnoyingMethodData {
    public String somethingEndingInnull() {
      return "a";
    }
    public String nullIsAnnoying() {
      return "b";
    }
    public String untrue() {
      return "c";
    }
  }
  interface AnnoyingMethodsTemplate extends XTemplates {
    @XTemplate("<tpl if='somethingEndingInnull:equals(\"a\")'>{somethingEndingInnull}</tpl>")
    SafeHtml a(AnnoyingMethodData data);
    @XTemplate("<tpl if='!nullIsAnnoying:equals(\"z\")'>{data.nullIsAnnoying}</tpl>")
    SafeHtml b(AnnoyingMethodData data);
    @XTemplate("<tpl if='untrue:equals(\"c\")'>{data.untrue}</tpl>")
    SafeHtml c(AnnoyingMethodData data);
  }
  public void testAnnoyingMethodNames() {
    AnnoyingMethodsTemplate tpl = GWT.create(AnnoyingMethodsTemplate.class);
    
    AnnoyingMethodData data = new AnnoyingMethodData();
    assertEquals("a", tpl.a(data).asString());
    assertEquals("b", tpl.b(data).asString());
    assertEquals("c", tpl.c(data).asString());

    
  }
}
