package com.sencha.gxt.xtemplates.client;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.sencha.gxt.core.client.XTemplates;
import com.sencha.gxt.core.client.XTemplates.TemplateCondition;
import com.sencha.gxt.core.client.util.DateWrapper;

public class GwtTestXTemplatesLoopingIF extends GwtTestXTemplates {
  /**
   * Simple subclass of Child, making sure we can read from supertype properties
   * 
   */
  public class ChildSub extends Child {
    public ChildSub(String name, int age, Date bday) {
      super(name, age, bday);
    }
  }

  public interface TestPojoIntegerIfGTInterface extends XTemplates {
    @XTemplate("<tpl if='child.age &gt; 1'>true</tpl>")
    public SafeHtml getChild(ChildSub child);

    @XTemplate("<tpl if='child.ageField &gt; 1'>true</tpl>")
    public SafeHtml getChildField(ChildSub child);
  }

  public void testPojoIntegerIfSuccess() {
    TestPojoIntegerIfGTInterface c = GWT.create(TestPojoIntegerIfGTInterface.class);
    ChildSub child = new ChildSub("Alec", 4, new DateWrapper(2004, 1, 1).asDate());
    String expected = "true";
    SafeHtml html = c.getChild(child);
    assertEquals(expected, html.asString());

    SafeHtml htmlField = c.getChildField(child);
    assertEquals(expected, htmlField.asString());
  }

  public void testPojoIntegerIfFail() {
    TestPojoIntegerIfGTInterface c = GWT.create(TestPojoIntegerIfGTInterface.class);
    ChildSub child = new ChildSub("Alec", 0, new DateWrapper(2004, 1, 1).asDate());
    String expected = "";
    SafeHtml html = c.getChild(child);
    assertEquals(expected, html.asString());

    SafeHtml htmlField = c.getChildField(child);
    assertEquals(expected, htmlField.asString());
  }

  public interface TestPojoIntegerIfLessThanInterface extends XTemplates {
    @XTemplate("<tpl if=\"child.age < 1\">true</tpl>")
    public SafeHtml getChild(Child child);
  }

  public void testPojoIntegerIfLessThanSuccess() {
    TestPojoIntegerIfLessThanInterface c = GWT.create(TestPojoIntegerIfLessThanInterface.class);
    Child child = new Child("Alec", 4, new DateWrapper(2004, 1, 1).asDate());
    String expected = "";
    SafeHtml html = c.getChild(child);
    assertEquals(expected, html.asString());
  }

  public void testPojoIntegerIfLessThanFail() {
    TestPojoIntegerIfLessThanInterface c = GWT.create(TestPojoIntegerIfLessThanInterface.class);
    Child child = new Child("Alec", 0, new DateWrapper(2004, 1, 1).asDate());
    String expected = "true";
    SafeHtml html = c.getChild(child);
    assertEquals(expected, html.asString());
  }

  public interface TestPojoIntegerIfLTInterface extends XTemplates {
    @XTemplate("<tpl if=\"child.age &lt; 1\">true</tpl>")
    public SafeHtml getChild(Child child);
  }

  public void testPojoIntegerIfLTSuccess() {
    TestPojoIntegerIfLTInterface c = GWT.create(TestPojoIntegerIfLTInterface.class);
    Child child = new Child("Alec", 0, new DateWrapper(2004, 1, 1).asDate());
    String expected = "true";
    SafeHtml html = c.getChild(child);
    assertEquals(expected, html.asString());
  }

  public void testPojoIntegerIfLTFail() {
    TestPojoIntegerIfLTInterface c = GWT.create(TestPojoIntegerIfLTInterface.class);
    Child child = new Child("Alec", 4, new DateWrapper(2004, 1, 1).asDate());
    String expected = "";
    SafeHtml html = c.getChild(child);
    assertEquals(expected, html.asString());
  }

  public interface TestPojoParentChildParentStringInteface extends XTemplates {
    @XTemplate("<tpl for=\"parent.children\">{parent.name}</tpl>")
    public SafeHtml getParent(Parent parent);
  }

  public void testPojoParentChildPaNull() {
    TestPojoParentChildParentStringInteface c = GWT.create(TestPojoParentChildParentStringInteface.class);
    Parent parent = new Parent();
    parent.setName("Parent");
    parent.setChildren(null);
    String expected = "";
    SafeHtml html = c.getParent(parent);
    assertEquals(expected, html.asString());
  }

  public void testPojoParentChildPaString0() {
    TestPojoParentChildParentStringInteface c = GWT.create(TestPojoParentChildParentStringInteface.class);
    Parent parent = new Parent();
    parent.setName("Parent");
    List<Child> children = new ArrayList<Child>();
    parent.setChildren(children);
    String expected = "";
    SafeHtml html = c.getParent(parent);
    assertEquals(expected, html.asString());
  }

  public void testPojoParentChildPaString1() {
    TestPojoParentChildParentStringInteface c = GWT.create(TestPojoParentChildParentStringInteface.class);
    Parent parent = new Parent();
    parent.setName("Parent");
    List<Child> children = new ArrayList<Child>();
    children.add(new Child("Alec", 4, new DateWrapper(2004, 1, 1).asDate()));
    parent.setChildren(children);
    String expected = "Parent";
    SafeHtml html = c.getParent(parent);
    assertEquals(expected, html.asString());
  }

  public void testPojoParentChildPaStringMany() {
    TestPojoParentChildParentStringInteface c = GWT.create(TestPojoParentChildParentStringInteface.class);
    Parent parent = new Parent();
    parent.setName("Parent");
    List<Child> children = new ArrayList<Child>();
    children.add(new Child("Alec", 4, new DateWrapper(2004, 1, 1).asDate()));
    children.add(new Child("Lia", 2, new DateWrapper(2005, 1, 1).asDate()));
    parent.setChildren(children);
    String expected = "ParentParent";
    SafeHtml html = c.getParent(parent);
    assertEquals(expected, html.asString());
  }

  public interface TestPojoParentChildParentString2Inteface extends XTemplates {
    @XTemplate("<tpl for=\"children\">{parent.name}</tpl>")
    public SafeHtml getParent(Parent data);
  }

  public void testPojoParentChildParentString2() {
    TestPojoParentChildParentString2Inteface c = GWT.create(TestPojoParentChildParentString2Inteface.class);
    Parent parent = new Parent();
    parent.setName("Parent");
    List<Child> children = new ArrayList<Child>();
    children.add(new Child("Alec", 4, new DateWrapper(2004, 1, 1).asDate()));
    children.add(new Child("Lia", 2, new DateWrapper(2005, 1, 1).asDate()));
    parent.setChildren(children);
    String expected = "ParentParent";
    SafeHtml html = c.getParent(parent);
    assertEquals(expected, html.asString());
  }

  public interface TestPojoParentChildParentString3Inteface extends XTemplates {
    @XTemplate("<tpl for=\"children\">{parent.data.name}</tpl>")
    public SafeHtml getParent(Parent data);
  }

  public void testPojoParentChildParentString3() {
    TestPojoParentChildParentString3Inteface c = GWT.create(TestPojoParentChildParentString3Inteface.class);
    Parent parent = new Parent();
    parent.setName("Parent");
    List<Child> children = new ArrayList<Child>();
    children.add(new Child("Alec", 4, new DateWrapper(2004, 1, 1).asDate()));
    children.add(new Child("Lia", 2, new DateWrapper(2005, 1, 1).asDate()));
    parent.setChildren(children);
    String expected = "ParentParent";
    SafeHtml html = c.getParent(parent);
    assertEquals(expected, html.asString());
  }

  public interface TestPojoChildrenArrayInterface extends XTemplates {
    @XTemplate("<tpl for=\"children\">{name}</tpl>")
    public SafeHtml getChildren(List<ChildSub> children);
  }

  public void testPojoChildrenArrayNull() {
    TestPojoChildrenArrayInterface c = GWT.create(TestPojoChildrenArrayInterface.class);
    String expected = "";
    SafeHtml html = c.getChildren(null);
    assertEquals(expected, html.asString());
  }

  public void testPojoChildrenArray0() {
    TestPojoChildrenArrayInterface c = GWT.create(TestPojoChildrenArrayInterface.class);
    List<ChildSub> children = new ArrayList<ChildSub>();
    String expected = "";
    SafeHtml html = c.getChildren(children);
    assertEquals(expected, html.asString());
  }

  public void testPojoChildrenArray1() {
    TestPojoChildrenArrayInterface c = GWT.create(TestPojoChildrenArrayInterface.class);
    List<ChildSub> children = new ArrayList<ChildSub>();
    children.add(new ChildSub("Alec", 4, new DateWrapper(2004, 1, 1).asDate()));
    String expected = "Alec";
    SafeHtml html = c.getChildren(children);
    assertEquals(expected, html.asString());
  }

  public void testPojoChildrenArrayMany() {
    TestPojoChildrenArrayInterface c = GWT.create(TestPojoChildrenArrayInterface.class);
    List<ChildSub> children = new ArrayList<ChildSub>();
    children.add(new ChildSub("Alec", 4, new DateWrapper(2004, 1, 1).asDate()));
    children.add(new ChildSub("Lia", 2, new DateWrapper(2005, 1, 1).asDate()));
    String expected = "AlecLia";
    SafeHtml html = c.getChildren(children);
    assertEquals(expected, html.asString());
  }

  //
  // // non parent -> myParent
  // // nested loops parent -> parent ->child
  // // null list

  public interface TestPojoParentChildStringInterface extends XTemplates {
    @XTemplate("<tpl for=\"parent.children\">{name}</tpl>")
    public SafeHtml getParent(Parent parent);
  }

  public void testPojoParentChildStringNull() {
    TestPojoParentChildStringInterface c = GWT.create(TestPojoParentChildStringInterface.class);
    Parent parent = new Parent();
    parent.setName("Parent");
    parent.setChildren(null);
    String expected = "";
    SafeHtml html = c.getParent(parent);
    assertEquals(expected, html.asString());
  }

  public void testPojoParentChildString0() {
    TestPojoParentChildStringInterface c = GWT.create(TestPojoParentChildStringInterface.class);
    Parent parent = new Parent();
    parent.setName("Parent");
    List<Child> children = new ArrayList<Child>();
    parent.setChildren(children);
    String expected = "";
    SafeHtml html = c.getParent(parent);
    assertEquals(expected, html.asString());
  }

  public void testPojoParentChildString1() {
    TestPojoParentChildStringInterface c = GWT.create(TestPojoParentChildStringInterface.class);
    Parent parent = new Parent();
    parent.setName("Parent");
    List<Child> children = new ArrayList<Child>();
    children.add(new Child("Alec", 4, new DateWrapper(2004, 1, 1).asDate()));
    parent.setChildren(children);
    String expected = "Alec";
    SafeHtml html = c.getParent(parent);
    assertEquals(expected, html.asString());
  }

  public void testPojoParentChildStringMany() {
    TestPojoParentChildStringInterface c = GWT.create(TestPojoParentChildStringInterface.class);
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

  public interface TestPojoParentChildCounterInterface extends XTemplates {
    @XTemplate("<tpl for=\"parent.children\">{#}</tpl>")
    public SafeHtml getParent(Parent parent);
  }

  public void testPojoParentChildCounterNull() {
    TestPojoParentChildCounterInterface c = GWT.create(TestPojoParentChildCounterInterface.class);
    Parent parent = new Parent();
    parent.setName("Parent");
    parent.setChildren(null);
    String expected = "";
    SafeHtml html = c.getParent(parent);
    assertEquals(expected, html.asString());
  }

  public void testPojoParentChildCounter0() {
    TestPojoParentChildCounterInterface c = GWT.create(TestPojoParentChildCounterInterface.class);
    Parent parent = new Parent();
    parent.setName("Parent");
    List<Child> children = new ArrayList<Child>();
    parent.setChildren(children);
    String expected = "";
    SafeHtml html = c.getParent(parent);
    assertEquals(expected, html.asString());
  }

  public void testPojoParentChildCounter1() {
    TestPojoParentChildCounterInterface c = GWT.create(TestPojoParentChildCounterInterface.class);
    Parent parent = new Parent();
    parent.setName("Parent");
    List<Child> children = new ArrayList<Child>();
    children.add(new Child("Alec", 4, new DateWrapper(2004, 1, 1).asDate()));
    parent.setChildren(children);
    String expected = "1";
    SafeHtml html = c.getParent(parent);
    assertEquals(expected, html.asString());
  }

  public void testPojoParentChildCounterMany() {
    TestPojoParentChildCounterInterface c = GWT.create(TestPojoParentChildCounterInterface.class);
    Parent parent = new Parent();
    parent.setName("Parent");
    List<Child> children = new ArrayList<Child>();
    children.add(new Child("Alec", 4, new DateWrapper(2004, 1, 1).asDate()));
    children.add(new Child("Lia", 2, new DateWrapper(2005, 1, 1).asDate()));
    parent.setChildren(children);
    String expected = "12";
    SafeHtml html = c.getParent(parent);
    assertEquals(expected, html.asString());
  }

  public interface TestPojoParentChildIntegerInterface extends XTemplates {
    @XTemplate("<tpl for=\"parent.children\">{age}</tpl>")
    public SafeHtml getParent(Parent parent);
  }

  public void testPojoParentChildIntegerNull() {
    TestPojoParentChildIntegerInterface c = GWT.create(TestPojoParentChildIntegerInterface.class);
    Parent parent = new Parent();
    parent.setName("Parent");
    parent.setChildren(null);
    String expected = "";
    SafeHtml html = c.getParent(parent);
    assertEquals(expected, html.asString());
  }

  public void testPojoParentChildInteger0() {
    TestPojoParentChildIntegerInterface c = GWT.create(TestPojoParentChildIntegerInterface.class);
    Parent parent = new Parent();
    parent.setName("Parent");
    List<Child> children = new ArrayList<Child>();
    parent.setChildren(children);
    String expected = "";
    SafeHtml html = c.getParent(parent);
    assertEquals(expected, html.asString());
  }

  public void testPojoParentChildInteger1() {
    TestPojoParentChildIntegerInterface c = GWT.create(TestPojoParentChildIntegerInterface.class);
    Parent parent = new Parent();
    parent.setName("Parent");
    List<Child> children = new ArrayList<Child>();
    children.add(new Child("Alec", 4, new DateWrapper(2004, 1, 1).asDate()));
    parent.setChildren(children);
    String expected = "4";
    SafeHtml html = c.getParent(parent);
    assertEquals(expected, html.asString());
  }

  public void testPojoParentChildIntegerMany() {
    TestPojoParentChildIntegerInterface c = GWT.create(TestPojoParentChildIntegerInterface.class);
    Parent parent = new Parent();
    parent.setName("Parent");
    List<Child> children = new ArrayList<Child>();
    children.add(new Child("Alec", 4, new DateWrapper(2004, 1, 1).asDate()));
    children.add(new Child("Lia", 2, new DateWrapper(2005, 1, 1).asDate()));
    parent.setChildren(children);
    String expected = "42";
    SafeHtml html = c.getParent(parent);
    assertEquals(expected, html.asString());
  }

  public interface TestPojoParentChildForIFGTInterface extends XTemplates {
    @XTemplate("<tpl for=\"children\"><tpl if=\"age &gt; 2\">{name}</tpl></tpl>")
    public SafeHtml getParent(Parent parent);
  }

  public void testPojoParentChildForIFGTNull() {
    TestPojoParentChildForIFGTInterface c = GWT.create(TestPojoParentChildForIFGTInterface.class);
    Parent parent = new Parent();
    parent.setName("Parent");
    parent.setChildren(null);
    String expected = "";
    SafeHtml html = c.getParent(parent);
    assertEquals(expected, html.asString());
  }

  public void testPojoParentChildForIFGT0() {
    TestPojoParentChildForIFGTInterface c = GWT.create(TestPojoParentChildForIFGTInterface.class);
    Parent parent = new Parent();
    parent.setName("Parent");
    List<Child> children = new ArrayList<Child>();
    children.add(new Child("Alec", 0, new DateWrapper(2004, 1, 1).asDate()));
    children.add(new Child("Lia", 2, new DateWrapper(2005, 1, 1).asDate()));
    parent.setChildren(children);
    String expected = "";
    SafeHtml html = c.getParent(parent);
    assertEquals(expected, html.asString());
  }

  public void testPojoParentChildForIFGT1() {
    TestPojoParentChildForIFGTInterface c = GWT.create(TestPojoParentChildForIFGTInterface.class);
    Parent parent = new Parent();
    parent.setName("Parent");
    List<Child> children = new ArrayList<Child>();
    children.add(new Child("Alec", 4, new DateWrapper(2004, 1, 1).asDate()));
    children.add(new Child("Lia", 2, new DateWrapper(2005, 1, 1).asDate()));
    parent.setChildren(children);
    String expected = "Alec";
    SafeHtml html = c.getParent(parent);
    assertEquals(expected, html.asString());
  }

  public void testPojoParentChildForIFGTMany() {
    TestPojoParentChildForIFGTInterface c = GWT.create(TestPojoParentChildForIFGTInterface.class);
    Parent parent = new Parent();
    parent.setName("Parent");
    List<Child> children = new ArrayList<Child>();
    children.add(new Child("Alec", 4, new DateWrapper(2004, 1, 1).asDate()));
    children.add(new Child("Lia", 3, new DateWrapper(2005, 1, 1).asDate()));
    parent.setChildren(children);
    String expected = "AlecLia";
    SafeHtml html = c.getParent(parent);
    assertEquals(expected, html.asString());
  }

  public interface TestPojoParentChildForIFGTEqInterface extends XTemplates {
    @XTemplate("<tpl for=\"children\"><tpl if=\"age &gt;= 2\">{name}</tpl></tpl>")
    public SafeHtml getParent(Parent parent);
  }

  public void testPojoParentChildForIFGTEqNull() {
    TestPojoParentChildForIFGTEqInterface c = GWT.create(TestPojoParentChildForIFGTEqInterface.class);
    Parent parent = new Parent();
    parent.setName("Parent");
    parent.setChildren(null);
    String expected = "";
    SafeHtml html = c.getParent(parent);
    assertEquals(expected, html.asString());
  }

  public void testPojoParentChildForIFGTEq0() {
    TestPojoParentChildForIFGTEqInterface c = GWT.create(TestPojoParentChildForIFGTEqInterface.class);
    Parent parent = new Parent();
    parent.setName("Parent");
    List<Child> children = new ArrayList<Child>();
    children.add(new Child("Alec", 0, new DateWrapper(2004, 1, 1).asDate()));
    children.add(new Child("Lia", 1, new DateWrapper(2005, 1, 1).asDate()));
    parent.setChildren(children);
    String expected = "";
    SafeHtml html = c.getParent(parent);
    assertEquals(expected, html.asString());
  }

  public void testPojoParentChildForIFGTEq1() {
    TestPojoParentChildForIFGTEqInterface c = GWT.create(TestPojoParentChildForIFGTEqInterface.class);
    Parent parent = new Parent();
    parent.setName("Parent");
    List<Child> children = new ArrayList<Child>();
    children.add(new Child("Alec", 1, new DateWrapper(2004, 1, 1).asDate()));
    children.add(new Child("Lia", 2, new DateWrapper(2005, 1, 1).asDate()));
    parent.setChildren(children);
    String expected = "Lia";
    SafeHtml html = c.getParent(parent);
    assertEquals(expected, html.asString());
  }

  public void testPojoParentChildForIFGTEqMany() {
    TestPojoParentChildForIFGTEqInterface c = GWT.create(TestPojoParentChildForIFGTEqInterface.class);
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

  public interface TestPojoParentChildForIFLTInterface extends XTemplates {
    @XTemplate("<tpl for=\"children\"><tpl if=\"age &lt; 2\">{name}</tpl></tpl>")
    public SafeHtml getParent(Parent parent);
  }

  public void testPojoParentChildForIFLTNull() {
    TestPojoParentChildForIFLTInterface c = GWT.create(TestPojoParentChildForIFLTInterface.class);
    Parent parent = new Parent();
    parent.setName("Parent");
    parent.setChildren(null);
    String expected = "";
    SafeHtml html = c.getParent(parent);
    assertEquals(expected, html.asString());
  }

  public void testPojoParentChildForIFLT0() {
    TestPojoParentChildForIFLTInterface c = GWT.create(TestPojoParentChildForIFLTInterface.class);
    Parent parent = new Parent();
    parent.setName("Parent");
    List<Child> children = new ArrayList<Child>();
    children.add(new Child("Alec", 0, new DateWrapper(2004, 1, 1).asDate()));
    children.add(new Child("Lia", 2, new DateWrapper(2005, 1, 1).asDate()));
    parent.setChildren(children);
    String expected = "Alec";
    SafeHtml html = c.getParent(parent);
    assertEquals(expected, html.asString());
  }

  public void testPojoParentChildForIFLT1() {
    TestPojoParentChildForIFLTInterface c = GWT.create(TestPojoParentChildForIFLTInterface.class);
    Parent parent = new Parent();
    parent.setName("Parent");
    List<Child> children = new ArrayList<Child>();
    children.add(new Child("Alec", 1, new DateWrapper(2004, 1, 1).asDate()));
    children.add(new Child("Lia", 2, new DateWrapper(2005, 1, 1).asDate()));
    parent.setChildren(children);
    String expected = "Alec";
    SafeHtml html = c.getParent(parent);
    assertEquals(expected, html.asString());
  }

  public void testPojoParentChildForIFLTMany() {
    TestPojoParentChildForIFLTInterface c = GWT.create(TestPojoParentChildForIFLTInterface.class);
    Parent parent = new Parent();
    parent.setName("Parent");
    List<Child> children = new ArrayList<Child>();
    children.add(new Child("Alec", 4, new DateWrapper(2004, 1, 1).asDate()));
    children.add(new Child("Lia", 3, new DateWrapper(2005, 1, 1).asDate()));
    parent.setChildren(children);
    String expected = "";
    SafeHtml html = c.getParent(parent);
    assertEquals(expected, html.asString());
  }

  public interface TestPojoParentChildForIFLTEqInterface extends XTemplates {
    @XTemplate("<tpl for=\"children\"><tpl if=\"age &lt;= 2\">{name}</tpl></tpl>")
    public SafeHtml getParent(Parent parent);
  }

  public void testPojoParentChildForIFLTEqNull() {
    TestPojoParentChildForIFLTEqInterface c = GWT.create(TestPojoParentChildForIFLTEqInterface.class);
    Parent parent = new Parent();
    parent.setName("Parent");
    parent.setChildren(null);
    String expected = "";
    SafeHtml html = c.getParent(parent);
    assertEquals(expected, html.asString());
  }

  public void testPojoParentChildForIFLTEq0() {
    TestPojoParentChildForIFLTEqInterface c = GWT.create(TestPojoParentChildForIFLTEqInterface.class);
    Parent parent = new Parent();
    parent.setName("Parent");
    List<Child> children = new ArrayList<Child>();
    children.add(new Child("Alec", 0, new DateWrapper(2004, 1, 1).asDate()));
    children.add(new Child("Lia", 2, new DateWrapper(2005, 1, 1).asDate()));
    parent.setChildren(children);
    String expected = "AlecLia";
    SafeHtml html = c.getParent(parent);
    assertEquals(expected, html.asString());
  }

  public void testPojoParentChildForIFLTEq1() {
    TestPojoParentChildForIFLTEqInterface c = GWT.create(TestPojoParentChildForIFLTEqInterface.class);
    Parent parent = new Parent();
    parent.setName("Parent");
    List<Child> children = new ArrayList<Child>();
    children.add(new Child("Alec", 4, new DateWrapper(2004, 1, 1).asDate()));
    children.add(new Child("Lia", 2, new DateWrapper(2005, 1, 1).asDate()));
    parent.setChildren(children);
    String expected = "Lia";
    SafeHtml html = c.getParent(parent);
    assertEquals(expected, html.asString());
  }

  public void testPojoParentChildForIFLTEqMany() {
    TestPojoParentChildForIFLTEqInterface c = GWT.create(TestPojoParentChildForIFLTEqInterface.class);
    Parent parent = new Parent();
    parent.setName("Parent");
    List<Child> children = new ArrayList<Child>();
    children.add(new Child("Alec", 4, new DateWrapper(2004, 1, 1).asDate()));
    children.add(new Child("Lia", 3, new DateWrapper(2005, 1, 1).asDate()));
    parent.setChildren(children);
    String expected = "";
    SafeHtml html = c.getParent(parent);
    assertEquals(expected, html.asString());
  }

  public interface TestPojoParentChildForIFLessThanInterface extends XTemplates {
    @XTemplate("<tpl for=\"children\"><tpl if=\"age < 2\">{name}</tpl></tpl>")
    public SafeHtml getParent(Parent parent);
  }

  public void testPojoParentChildForIFLessThanNull() {
    TestPojoParentChildForIFLessThanInterface c = GWT.create(TestPojoParentChildForIFLessThanInterface.class);
    Parent parent = new Parent();
    parent.setName("Parent");
    parent.setChildren(null);
    String expected = "";
    SafeHtml html = c.getParent(parent);
    assertEquals(expected, html.asString());
  }

  public void testPojoParentChildForIFLessThanMany() {
    TestPojoParentChildForIFLessThanInterface c = GWT.create(TestPojoParentChildForIFLessThanInterface.class);
    Parent parent = new Parent();
    parent.setName("Parent");
    List<Child> children = new ArrayList<Child>();
    children.add(new Child("Alec", 0, new DateWrapper(2004, 1, 1).asDate()));
    children.add(new Child("Lia", 1, new DateWrapper(2005, 1, 1).asDate()));
    parent.setChildren(children);
    String expected = "AlecLia";
    SafeHtml html = c.getParent(parent);
    assertEquals(expected, html.asString());
  }

  public void testPojoParentChildForIFLessThan1() {
    TestPojoParentChildForIFLessThanInterface c = GWT.create(TestPojoParentChildForIFLessThanInterface.class);
    Parent parent = new Parent();
    parent.setName("Parent");
    List<Child> children = new ArrayList<Child>();
    children.add(new Child("Alec", 1, new DateWrapper(2004, 1, 1).asDate()));
    children.add(new Child("Lia", 2, new DateWrapper(2005, 1, 1).asDate()));
    parent.setChildren(children);
    String expected = "Alec";
    SafeHtml html = c.getParent(parent);
    assertEquals(expected, html.asString());
  }

  public void testPojoParentChildForIFLessThan0() {
    TestPojoParentChildForIFLessThanInterface c = GWT.create(TestPojoParentChildForIFLessThanInterface.class);
    Parent parent = new Parent();
    parent.setName("Parent");
    List<Child> children = new ArrayList<Child>();
    children.add(new Child("Alec", 4, new DateWrapper(2004, 1, 1).asDate()));
    children.add(new Child("Lia", 3, new DateWrapper(2005, 1, 1).asDate()));
    parent.setChildren(children);
    String expected = "";
    SafeHtml html = c.getParent(parent);
    assertEquals(expected, html.asString());
  }

  public interface TestPojoParentChildForIFLessThanEqInterface extends XTemplates {
    @XTemplate("<tpl for=\"children\"><tpl if=\"age <= 2\">{name}</tpl></tpl>")
    public SafeHtml getParent(Parent parent);
  }

  public void testPojoParentChildForIFLessThanEqNull() {
    TestPojoParentChildForIFLessThanEqInterface c = GWT.create(TestPojoParentChildForIFLessThanEqInterface.class);
    Parent parent = new Parent();
    parent.setName("Parent");
    parent.setChildren(null);
    String expected = "";
    SafeHtml html = c.getParent(parent);
    assertEquals(expected, html.asString());
  }

  public void testPojoParentChildForIFLessThanEqMany() {
    TestPojoParentChildForIFLessThanEqInterface c = GWT.create(TestPojoParentChildForIFLessThanEqInterface.class);
    Parent parent = new Parent();
    parent.setName("Parent");
    List<Child> children = new ArrayList<Child>();
    children.add(new Child("Alec", 0, new DateWrapper(2004, 1, 1).asDate()));
    children.add(new Child("Lia", 2, new DateWrapper(2005, 1, 1).asDate()));
    parent.setChildren(children);
    String expected = "AlecLia";
    SafeHtml html = c.getParent(parent);
    assertEquals(expected, html.asString());
  }

  public void testPojoParentChildForIFLessThanEq1() {
    TestPojoParentChildForIFLessThanEqInterface c = GWT.create(TestPojoParentChildForIFLessThanEqInterface.class);
    Parent parent = new Parent();
    parent.setName("Parent");
    List<Child> children = new ArrayList<Child>();
    children.add(new Child("Alec", 4, new DateWrapper(2004, 1, 1).asDate()));
    children.add(new Child("Lia", 2, new DateWrapper(2005, 1, 1).asDate()));
    parent.setChildren(children);
    String expected = "Lia";
    SafeHtml html = c.getParent(parent);
    assertEquals(expected, html.asString());
  }

  public void testPojoParentChildForIFLessThanEq0() {
    TestPojoParentChildForIFLessThanEqInterface c = GWT.create(TestPojoParentChildForIFLessThanEqInterface.class);
    Parent parent = new Parent();
    parent.setName("Parent");
    List<Child> children = new ArrayList<Child>();
    children.add(new Child("Alec", 4, new DateWrapper(2004, 1, 1).asDate()));
    children.add(new Child("Lia", 3, new DateWrapper(2005, 1, 1).asDate()));
    parent.setChildren(children);
    String expected = "";
    SafeHtml html = c.getParent(parent);
    assertEquals(expected, html.asString());
  }

  public interface TestPojoParentParentForIFParentInterface extends XTemplates {
    @XTemplate("<tpl for=\"children\" if=\"parent.name==\'Parent\'\">{parent.name}</tpl>")
    public SafeHtml getParent(Parent parent);
  }

  public void testPojoParentParentForIF() {
    TestPojoParentParentForIFParentInterface c = GWT.create(TestPojoParentParentForIFParentInterface.class);
    Parent parent = new Parent();
    parent.setName("Parent");
    List<Child> children = new ArrayList<Child>();
    children.add(new Child("Alec", 4, new DateWrapper(2004, 1, 1).asDate()));
    children.add(new Child("Lia", 2, new DateWrapper(2005, 1, 1).asDate()));
    parent.setChildren(children);
    String expected = "ParentParent";
    SafeHtml html = c.getParent(parent);
    assertEquals(expected, html.asString());
  }

  public interface TestPojoParentParentChildForIFParentInterface extends XTemplates {
    @XTemplate("<tpl for=\"children\" if=\"parent.name==\'Parent\'\"><tpl if=\"age &gt; 2\">{name}</tpl></tpl>")
    public SafeHtml getParent(Parent parent);
  }

  public void testPojoParentParentChildForIFParent() {
    TestPojoParentParentChildForIFParentInterface c = GWT.create(TestPojoParentParentChildForIFParentInterface.class);
    Parent parent = new Parent();
    parent.setName("Parent");
    List<Child> children = new ArrayList<Child>();
    children.add(new Child("Alec", 4, new DateWrapper(2004, 1, 1).asDate()));
    children.add(new Child("Lia", 2, new DateWrapper(2005, 1, 1).asDate()));
    parent.setChildren(children);
    String expected = "Alec";
    SafeHtml html = c.getParent(parent);
    assertEquals(expected, html.asString());
  }

  public interface TestPojoParentParentForIFInterface extends XTemplates {
    @XTemplate("<tpl for=\"children\" if=\"name==\'Parent\'\">{parent.name}</tpl>")
    public SafeHtml getParent(Parent parent);
  }

  public void testPojoParentParentForIFParent() {
    TestPojoParentParentForIFInterface c = GWT.create(TestPojoParentParentForIFInterface.class);
    Parent parent = new Parent();
    parent.setName("Parent");
    List<Child> children = new ArrayList<Child>();
    children.add(new Child("Alec", 4, new DateWrapper(2004, 1, 1).asDate()));
    children.add(new Child("Lia", 2, new DateWrapper(2005, 1, 1).asDate()));
    parent.setChildren(children);
    String expected = "ParentParent";
    SafeHtml html = c.getParent(parent);
    assertEquals(expected, html.asString());
  }

  public interface TestPojoParentParentChildForIFInterface extends XTemplates {
    @XTemplate("<tpl for=\"children\" if=\"name==\'Parent\'\"><tpl if=\"# &lt; 2\">{name}{[#]}</tpl></tpl>")
    public SafeHtml getParent(Parent parent);
  }

  public void testPojoParentParentChildForIF() {
    TestPojoParentParentChildForIFInterface c = GWT.create(TestPojoParentParentChildForIFInterface.class);
    Parent parent = new Parent();
    parent.setName("Parent");
    List<Child> children = new ArrayList<Child>();
    children.add(new Child("Alec", 4, new DateWrapper(2004, 1, 1).asDate()));
    children.add(new Child("Lia", 2, new DateWrapper(2005, 1, 1).asDate()));
    parent.setChildren(children);
    String expected = "Alec1";
    SafeHtml html = c.getParent(parent);
    assertEquals(expected, html.asString());
  }

  interface StringListInterface extends XTemplates {
    @XTemplate("<tpl for='items'>{.}</tpl>")
    public SafeHtml renderItem(List<String> items);
  }

  public void testStringList() {
    StringListInterface t = GWT.create(StringListInterface.class);
    assertEquals("ab", t.renderItem(Arrays.asList("", "a", "b")).asString());
  }

  interface BooleanValueIfInterface extends XTemplates {
    @XTemplate("<tpl if='value'>true</tpl>")
    SafeHtml isTrue(Boolean value);

    @XTemplate("<tpl if='!value'>true</tpl>")
    SafeHtml isFalse(Boolean value);
  }

  public void testBooleanValueInIf() {
    BooleanValueIfInterface t = GWT.create(BooleanValueIfInterface.class);
    assertEquals("true", t.isTrue(true).asString());
    assertEquals("", t.isTrue(false).asString());

    assertEquals("", t.isFalse(true).asString());
    assertEquals("true", t.isFalse(false).asString());
  }

  static class BeanWithBoolValue {
    private boolean value;

    public void setValue(boolean value) {
      this.value = value;
    }

    public boolean getValue() {
      return value;
    }
    public boolean isValueTrue() {
      return value;
    }
    
    public boolean hasTrueValue() {
      return value;
    }
  }
  interface BooleanBeanIfInterface extends XTemplates {
    @XTemplate("<tpl if='value'>true</tpl>")
    SafeHtml isTrue(BeanWithBoolValue obj);

    @XTemplate("<tpl if='!obj.value'>true</tpl>")
    SafeHtml isFalse(BeanWithBoolValue obj);

    @XTemplate("<tpl if='valueTrue'>true</tpl>")
    SafeHtml isMethodWithoutIs(BeanWithBoolValue obj);

    @XTemplate("<tpl if='isValueTrue'>true</tpl>")
    SafeHtml isMethod(BeanWithBoolValue obj);

    @XTemplate("<tpl if='trueValue'>true</tpl>")
    SafeHtml hasMethodWithoutHas(BeanWithBoolValue obj);

    @XTemplate("<tpl if='hasTrueValue'>true</tpl>")
    SafeHtml hasMethod(BeanWithBoolValue obj);
  }

  public void testBooleanBeanInIf() {
    BooleanBeanIfInterface tpl = GWT.create(BooleanBeanIfInterface.class);
    BeanWithBoolValue t = new BeanWithBoolValue();
    t.setValue(true);
    BeanWithBoolValue f = new BeanWithBoolValue();
    f.setValue(false);
    assertEquals("true", tpl.isTrue(t).asString());
    assertEquals("", tpl.isTrue(f).asString());

    assertEquals("", tpl.isFalse(t).asString());
    assertEquals("true", tpl.isFalse(f).asString());
  }
  
  public void testBooleanIsMethods() {
    BooleanBeanIfInterface tpl = GWT.create(BooleanBeanIfInterface.class);
    BeanWithBoolValue t = new BeanWithBoolValue();
    t.setValue(true);
    BeanWithBoolValue f = new BeanWithBoolValue();
    f.setValue(false);
    
    assertEquals("true", tpl.isMethodWithoutIs(t).asString());
    assertEquals("", tpl.isMethod(f).asString());
  }
  
  public void testBooleanHasMethods() {
    BooleanBeanIfInterface tpl = GWT.create(BooleanBeanIfInterface.class);
    BeanWithBoolValue t = new BeanWithBoolValue();
    t.setValue(true);
    BeanWithBoolValue f = new BeanWithBoolValue();
    f.setValue(false);
    
    assertEquals("true", tpl.hasMethodWithoutHas(t).asString());
    assertEquals("", tpl.hasMethod(f).asString());
  }

  interface TemplateUsingEquals extends XTemplates {
    @XTemplate("<tpl if='o1:equals(o2)'>true</tpl>")
    SafeHtml tpl(Object o1, Object o2);
  }

  public void testCustomCondition() {
    TemplateUsingEquals t = GWT.create(TemplateUsingEquals.class);
    assertEquals("true", t.tpl("", "").asString());
    assertEquals("", t.tpl("a", "z").asString());
  }

  interface TemplateUsingEqualsNestedProperties extends XTemplates {
    @XTemplate("<tpl if='c1.name:equals(c2.name)'>true</tpl>")
    SafeHtml tpl(Child c1, Child c2);
  }

  public void testCustomConditionNestedProperties() {
    TemplateUsingEqualsNestedProperties t = GWT.create(TemplateUsingEqualsNestedProperties.class);
    assertEquals("true", t.tpl(new Child("asdf", 1, null), new Child("asdf", 10, new Date())).asString());
    assertEquals("", t.tpl(new Child("asdf", 1, null), new Child("fdsa", 1, null)).asString());
  }
  
  @TemplateCondition(methodName = "contains", type = List.class)
  interface TemplateUsingOtherBooleanMethod extends XTemplates {
    @XTemplate("<tpl if='data:contains(item)'>true</tpl>")
    SafeHtml tpl(List<Object> data, Object item);
  }

  public void testOtherBooleanMethod() {
    String data = "fdsa";
    TemplateUsingOtherBooleanMethod t = GWT.create(TemplateUsingOtherBooleanMethod.class);
    assertEquals("true", t.tpl(Arrays.<Object> asList(0, data), data).asString());
    assertEquals("", t.tpl(Arrays.<Object> asList("faaaaa", "doreme"), data).asString());
  }

  @TemplateCondition(name = "has", methodName = "contains", type = List.class)
  interface TemplateUsingRenamedBooleanMethod extends XTemplates {
    @XTemplate("<tpl if='list:has(item)'>true</tpl>")
    SafeHtml tpl(List<? extends Object> list, Object item);
    
    @XTemplate("<tpl if='list:has(\"a\")'>true</tpl>")
    SafeHtml tpl2(List<String> list);
  }

  public void testRenamedBooleanMethod() {
    String data = "fdsa";
    TemplateUsingRenamedBooleanMethod t = GWT.create(TemplateUsingRenamedBooleanMethod.class);
    assertEquals("true", t.tpl(Arrays.<Object> asList(0, data), data).asString());
    assertEquals("", t.tpl(Arrays.asList("faaaaa", "doreme"), data).asString());
    
    assertEquals("true", t.tpl2(Arrays.asList("a","b")).asString());
    assertEquals("", t.tpl2(Arrays.asList(null,"b")).asString());
  }
}
