package com.sencha.gxt.xtemplates.client;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.sencha.gxt.core.client.XTemplates;

public class GwtTestXTemplates extends XTemplateTestCase {

  public class Parent {
    public String nameField;
    public double incomeField;
    public List<Child> childrenField = new ArrayList<Child>();

    public String getName() {
      return nameField;
    }

    public void setName(String name) {
      this.nameField = name;
    }

    public double getIncome() {
      return incomeField;
    }

    public void setIncome(double income) {
      this.incomeField = income;
    }

    public List<Child> getChildren() {
      return childrenField;
    }

    public void setChildren(List<Child> children) {
      this.childrenField = children;
    }

    public Parent() {
    }
  }

  public class Child {
    public String nameField;
    public int ageField;
    public Date bdayField;

    public Child(String name, int age, Date bday) {
      setName(name);
      setAge(age);
      setBday(bday);
    }

    public String getName() {
      return nameField;
    }

    public void setName(String name) {
      this.nameField = name;
    }

    public int getAge() {
      return ageField;
    }

    public void setAge(int age) {
      this.ageField = age;
    }

    public Date getBday() {
      return bdayField;
    }

    public void setBday(Date bday) {
      this.bdayField = bday;
    }
  }

  public interface TestAssureSuccessInterface extends XTemplates {
    @XTemplate("a")
    public SafeHtml getString();
  }

  public void testAssureSuccess() {
    TestAssureSuccessInterface c = GWT.create(TestAssureSuccessInterface.class);
    String s = "a";
    SafeHtml html = c.getString();
    assertEquals(s, html.asString());
  }
}
