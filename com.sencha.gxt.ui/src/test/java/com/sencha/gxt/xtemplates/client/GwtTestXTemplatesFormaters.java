package com.sencha.gxt.xtemplates.client;

import java.util.Map;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.DateTimeFormat.PredefinedFormat;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.sencha.gxt.core.client.XTemplates;
import com.sencha.gxt.core.client.XTemplates.Formatter;
import com.sencha.gxt.core.client.XTemplates.FormatterFactories;
import com.sencha.gxt.core.client.XTemplates.FormatterFactory;
import com.sencha.gxt.core.client.util.DateWrapper;
import com.sencha.gxt.core.shared.FastMap;

public class GwtTestXTemplatesFormaters extends GwtTestXTemplates {
  static class MapDataFactory {
    public static MapData getFormat(String property) {
      return new MapData(property);
    }
  }
  static class MapData implements Formatter<Map<String, String>> {
    private final String prop;

    public MapData(String property) {
      this.prop = property;
    }

    @Override
    public String format(Map<String, String> data) {
      return data.get(prop);
    }
  }
  @FormatterFactories(@FormatterFactory(factory = MapDataFactory.class, name = "value"))
  public interface TestWithCustomFormatterInterface extends XTemplates {
    @XTemplate("{map:value(\"prop\")}")
    public SafeHtml getMapProperty(Map<String, String> map);

    @XTemplate("{map:value({key})}")
    SafeHtml getPropertyWithKey(Map<String, String> map, String key);
  }

  public void testWithCustomFormatter() {
    TestWithCustomFormatterInterface format = GWT.create(TestWithCustomFormatterInterface.class);
    Map<String, String> map = new FastMap<String>();
    map.put("a", "asdf");
    map.put("b", "1234");
    map.put("prop", "value");

    assertEquals("value", format.getMapProperty(map).asString());

    assertEquals("asdf", format.getPropertyWithKey(map, "a").asString());
    assertEquals("1234", format.getPropertyWithKey(map, "b").asString());
  }

  public interface TestPojoDateDefaultInterface extends XTemplates {
    @XTemplate("{child.bday}")
    public SafeHtml getChild(Child child);
  }

  public void testPojoDateDefault() {
    TestPojoDateDefaultInterface c = GWT.create(TestPojoDateDefaultInterface.class);
    Child child = new Child("Alec", 4, new DateWrapper(2004, 1, 1).asDate());
    String expected = new DateWrapper(2004, 1, 1).asDate().toString();
    SafeHtml html = c.getChild(child);
    assertEquals(expected, html.asString());
  }

  public interface TestPojoDateInterface extends XTemplates {
    @XTemplate("{child.bday:date(\"M/d/yyyy\")}")
    public SafeHtml getChild(Child child);
  }

  public void testPojoDate() {
    TestPojoDateInterface c = GWT.create(TestPojoDateInterface.class);
    Child child = new Child("Alec", 4, new DateWrapper(2004, 1, 1).asDate());
    DateTimeFormat format = DateTimeFormat.getFormat("M/d/yyyy");
    String expected = format.format(new DateWrapper(2004, 1, 1).asDate());
    SafeHtml html = c.getChild(child);
    assertEquals(expected, html.asString());
  }

  public interface TestPojoCurrencyDefaultInteger extends XTemplates {
    @XTemplate("{parent.income:currency}")
    public SafeHtml getParent(Parent parent);
  }

  public void testPojoCurrencyDefault() {
    TestPojoCurrencyDefaultInteger c = GWT.create(TestPojoCurrencyDefaultInteger.class);
    double income = 1000000000d;
    Parent parent = new Parent();
    parent.setIncome(income);
    NumberFormat format = NumberFormat.getCurrencyFormat();
    String expected = format.format(income);
    SafeHtml html = c.getParent(parent);
    assertEquals(expected, html.asString());
  }

  public interface TestPojoCurrencyInterface extends XTemplates {
    @XTemplate("{parent.income:currency(\"USD\")}")
    public SafeHtml getParent(Parent parent);
  }

  public void testPojoCurrency() {
    TestPojoCurrencyInterface c = GWT.create(TestPojoCurrencyInterface.class);
    double income = 1000000000d;
    Parent parent = new Parent();
    parent.setIncome(income);
    NumberFormat format = NumberFormat.getCurrencyFormat("USD");
    String expected = format.format(income);
    SafeHtml html = c.getParent(parent);
    assertEquals(expected, html.asString());
  }

  public interface TestPojoNumberDefaultInteger extends XTemplates {
    @XTemplate("{parent.income:number}")
    public SafeHtml getParent(Parent parent);
  }

  public void testPojoNumberDefault() {
    TestPojoNumberDefaultInteger c = GWT.create(TestPojoNumberDefaultInteger.class);
    double income = 1000000000d;
    Parent parent = new Parent();
    parent.setIncome(income);
    NumberFormat format = NumberFormat.getFormat("#");
    String expected = format.format(income);
    SafeHtml html = c.getParent(parent);
    assertEquals(expected, html.asString());
  }

  public interface TestPojoNumberInteger extends XTemplates {
    @XTemplate("{parent.income:number(\"000.000\")}")
    public SafeHtml getParent(Parent parent);
  }

  public void testPojoNumber() {
    TestPojoNumberInteger c = GWT.create(TestPojoNumberInteger.class);
    double income = 1000000000d;
    Parent parent = new Parent();
    parent.setIncome(income);
    NumberFormat format = NumberFormat.getFormat("000.000");
    String expected = format.format(income);
    SafeHtml html = c.getParent(parent);
    assertEquals(expected, html.asString());
  }

  public interface TestPojoDefaultScientificInterface extends XTemplates {
    @XTemplate("{parent.income:scientific}")
    public SafeHtml getParent(Parent parent);
  }

  public void testPojoDefaultScientific() {
    TestPojoDefaultScientificInterface c = GWT.create(TestPojoDefaultScientificInterface.class);
    double income = 1000000000d;
    Parent parent = new Parent();
    parent.setIncome(income);
    NumberFormat format = NumberFormat.getScientificFormat();
    String expected = format.format(income);
    SafeHtml html = c.getParent(parent);
    assertEquals(expected, html.asString());
  }

  public interface TestPojoDefaultPercentageInterface extends XTemplates {
    @XTemplate("{parent.income:percentage}")
    public SafeHtml getParent(Parent parent);
  }

  public void testPojoDefaultPercentage() {
    TestPojoDefaultPercentageInterface c = GWT.create(TestPojoDefaultPercentageInterface.class);
    double income = 99d;
    Parent parent = new Parent();
    parent.setIncome(income);
    NumberFormat format = NumberFormat.getPercentFormat();
    String expected = format.format(income);
    SafeHtml html = c.getParent(parent);
    assertEquals(expected, html.asString());
  }

  public interface TestPojoDateVariableInterface extends XTemplates {
    @XTemplate("{child.bday:date({format})}")
    public SafeHtml getChild(String format, Child child);
  }

//  public void testPojoDateFormat() {
//    TestPojoDateVariableInterface c = GWT.create(TestPojoDateVariableInterface.class);
//    String dateFormat = "M/d/yyyy";
//    Child child = new Child("Alec", 4, new DateWrapper(2004, 1, 1).asDate());
//    DateTimeFormat format = DateTimeFormat.getFormat(dateFormat);
//    String expected = format.format(new DateWrapper(2004, 1, 1).asDate());
//    SafeHtml html = c.getChild(dateFormat, child);
//    assertEquals(expected, html.asString());
//  }

  public interface TestPojoDateDateTimeFormatVariableInterface extends XTemplates {
    @XTemplate("{child.bday:date({format})}")
    public SafeHtml getChild(DateTimeFormat.PredefinedFormat format, Child child);
  }

  public void testPojoDateDateTimeFormatVariable() {
    TestPojoDateDateTimeFormatVariableInterface c = GWT.create(TestPojoDateDateTimeFormatVariableInterface.class);
    PredefinedFormat dateFormat = PredefinedFormat.ISO_8601;
    Child child = new Child("Alec", 4, new DateWrapper(2004, 1, 1).asDate());
    DateTimeFormat format = DateTimeFormat.getFormat(dateFormat);
    String expected = format.format(new DateWrapper(2004, 1, 1).asDate());
    SafeHtml html = c.getChild(PredefinedFormat.ISO_8601, child);
    assertEquals(expected, html.asString());
  }

  public interface TestPojoDateChildVariableInterface extends XTemplates {
    @XTemplate("{child.bday:date({child.name})}")
    public SafeHtml getParent(Child child);
  }

  public void testPojoDateChildVariable() {
    TestPojoDateChildVariableInterface c = GWT.create(TestPojoDateChildVariableInterface.class);
    String dateFormat = "M/d/yyyy";

    Child child = new Child(dateFormat, 4, new DateWrapper(2004, 1, 1).asDate());
    DateTimeFormat format = DateTimeFormat.getFormat(dateFormat);
    String expected = format.format(new DateWrapper(2004, 1, 1).asDate());
    SafeHtml html = c.getParent(child);
    assertEquals(expected, html.asString());
  }

  public interface TestPojoNumberVariableInteger extends XTemplates {
    @XTemplate("{parent.income:number(\"000.000\")}")
    public SafeHtml getParent(String format, Parent parent);
  }

  public void testPojoNumberVariable() {
    TestPojoNumberVariableInteger c = GWT.create(TestPojoNumberVariableInteger.class);
    double income = 1000000000d;
    Parent parent = new Parent();
    parent.setIncome(income);
    NumberFormat format = NumberFormat.getFormat("000.000");
    String expected = format.format(income);
    SafeHtml html = c.getParent(new Double(income).toString(), parent);
    assertEquals(expected, html.asString());
  }

  public interface TestPojoNumberFormatVariableInteger extends XTemplates {
    @XTemplate("{parent.income:number(\"000.000\")}")
    public SafeHtml getParent(NumberFormat format, Parent parent);
  }

  public void testPojoNumberFormatVariable() {
    TestPojoNumberFormatVariableInteger c = GWT.create(TestPojoNumberFormatVariableInteger.class);
    double income = 1000000000d;
    Parent parent = new Parent();
    parent.setIncome(income);
    NumberFormat format = NumberFormat.getFormat("000.000");
    String expected = format.format(income);
    SafeHtml html = c.getParent(format, parent);
    assertEquals(expected, html.asString());
  }

  public interface TestPojoNumberFormatChildVariableInteger extends XTemplates {
    @XTemplate("{parent.income:number({parent.name})}")
    public SafeHtml getParent(Parent parent);
  }

  public void testPojoNumberFormatChildVariable() {
    TestPojoNumberFormatChildVariableInteger c = GWT.create(TestPojoNumberFormatChildVariableInteger.class);
    String numberFormat = "000.000";
    double income = 1000000000d;

    Parent parent = new Parent();
    parent.setName(numberFormat);
    parent.setIncome(income);
    NumberFormat format = NumberFormat.getFormat(numberFormat);
    String expected = format.format(income);
    SafeHtml html = c.getParent(parent);
    assertEquals(expected, html.asString());
  }
  
  public interface TestNullNumberFormat extends XTemplates {
    @XTemplate("{n:number}{n:decimal}{n:percentage}")
    SafeHtml nullNumber(Number n);
  }
  public void testNullNumberFormat() {
    TestNullNumberFormat template = GWT.create(TestNullNumberFormat.class);
    assertEquals("", template.nullNumber(null).asString());
  }
  public static NullFormatter getFormat() {
    return new NullFormatter();
  }
  public static class NullFormatter implements Formatter<Object> {
    @Override
    public String format(Object data) {
      return data == null ? "null" : "non-null";
    }
  }
  @FormatterFactories(@FormatterFactory(factory=GwtTestXTemplatesFormaters.class, name="nullThing", acceptsNull=true))
  public interface TestNullFormatter extends XTemplates {
    @XTemplate("{obj:nullThing}")
    SafeHtml nullValue(Object obj);
  }
  public void testNullFormatter() {
    TestNullFormatter template = GWT.create(TestNullFormatter.class);
    assertEquals("null", template.nullValue(null).asString());
    assertEquals("non-null", template.nullValue(new Object()).asString());
  }

}
