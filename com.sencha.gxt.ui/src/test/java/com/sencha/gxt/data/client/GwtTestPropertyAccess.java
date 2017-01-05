package com.sencha.gxt.data.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.PropertyAccess;

public class GwtTestPropertyAccess extends DataTestCase {
  static class OuterData {
    boolean set = false;
    boolean get = false;

    public String getReadOnlyString() {
      get = true;
      return "asdf";
    }

    public void setWriteOnlyString(String value) {
      set = true;
    }

    String property;

    public String stringWithoutGet() {
      return property;
    }

    public String getString() {
      return property;
    }

    public void setString(String string) {
      property = string;
    }

    InnerData data;

    public InnerData getInnerData() {
      return data;
    }

    public void setInnerData(InnerData data) {
      this.data = data;
    }

  }
  static class InnerData {
    String property;

    public String getString() {
      return property;
    }

    public void setString(String string) {
      property = string;
    }
  }
  interface TestPropAccess extends PropertyAccess<OuterData> {
    ValueProvider<OuterData, String> readOnlyString();

    ValueProvider<OuterData, String> writeOnlyString();

    ValueProvider<OuterData, String> string();// without path

    ValueProvider<OuterData, String> stringWithoutGet();// without path

    @Path("string")
    ValueProvider<OuterData, String> outerStringUsingPath();

    @Path("stringWithoutGet")
    ValueProvider<OuterData, String> stringWithoutGetUsingPath();

    @Path("innerData.string")
    ValueProvider<OuterData, String> innerStringUsingPath();

    ValueProvider<OuterData, InnerData> innerData();
  }

  public void testSimpleGetterSetter() {
    TestPropAccess access = GWT.create(TestPropAccess.class);

    ValueProvider<OuterData, String> string = access.string();
    assertEquals("string", string.getPath());
    OuterData data = new OuterData();

    data.setString("hello");
    assertEquals("hello", string.getValue(data));
    string.setValue(data, "bye");
    assertEquals("bye", data.getString());
  }

  public void testGetterWithoutGet() {
    TestPropAccess access = GWT.create(TestPropAccess.class);

    ValueProvider<OuterData, String> string = access.stringWithoutGet();
    assertEquals("stringWithoutGet", string.getPath());
    OuterData data = new OuterData();

    data.setString("hello");
    assertEquals("hello", string.getValue(data));
  }

  public void testGetterWithoutGetUsingPath() {
    TestPropAccess access = GWT.create(TestPropAccess.class);

    ValueProvider<OuterData, String> string = access.stringWithoutGetUsingPath();
    assertEquals("stringWithoutGet", string.getPath());
    OuterData data = new OuterData();

    data.setString("hello");
    assertEquals("hello", string.getValue(data));
  }

  public void testPathGetterSetter() {
    TestPropAccess access = GWT.create(TestPropAccess.class);

    ValueProvider<OuterData, String> string = access.outerStringUsingPath();

    assertEquals("string", string.getPath());
    OuterData data = new OuterData();

    data.setString("hello");
    assertEquals("hello", string.getValue(data));
    string.setValue(data, "bye");
    assertEquals("bye", data.getString());
  }

  public void testWriteOnly() {
    TestPropAccess access = GWT.create(TestPropAccess.class);

    ValueProvider<OuterData, String> wo = access.writeOnlyString();
    OuterData data = new OuterData();
    assertFalse(data.set);
    wo.setValue(data, "garbage");
    assertTrue(data.set);
  }

  public void testReadOnly() {
    TestPropAccess access = GWT.create(TestPropAccess.class);

    ValueProvider<OuterData, String> wo = access.readOnlyString();
    OuterData data = new OuterData();
    assertFalse(data.get);
    String str = wo.getValue(data);
    assertEquals("asdf", str);// canned value
    assertTrue(data.get);
  }

  public void testNestedObject() {
    TestPropAccess access = GWT.create(TestPropAccess.class);

    ValueProvider<OuterData, InnerData> innerData = access.innerData();
    assertEquals("innerData", innerData.getPath());
    OuterData data = new OuterData();
    InnerData inner = new InnerData();
    data.setInnerData(inner);

    assertEquals(inner, innerData.getValue(data));

    InnerData newInner = new InnerData();
    innerData.setValue(data, newInner);
    assertEquals(newInner, innerData.getValue(data));
  }

  public void testNestedPath() {
    TestPropAccess access = GWT.create(TestPropAccess.class);

    ValueProvider<OuterData, String> string = access.innerStringUsingPath();
    assertEquals("innerData.string", string.getPath());
    OuterData data = new OuterData();
    data.setInnerData(new InnerData());

    data.getInnerData().setString("hello");
    assertEquals("hello", string.getValue(data));
    string.setValue(data, "bye");
    assertEquals("bye", data.getInnerData().getString());
  }

  static class SuperClass { 
    private String name;
    public String getName() {
      return name;
    }
    public void setName(String name) {
      this.name = name;
    }
    
  }
  interface SuperClassProperties extends PropertyAccess<SuperClass> {
    ValueProvider<SuperClass, String> name(); 
  }
  interface SuperClassAsTProperties<T extends SuperClass> extends PropertyAccess<T> {
    ValueProvider<T, String> name(); 
  }
  static class SubClass extends SuperClass {
    private int number;
    public void setNumber(int number) {
      this.number = number;
    }
    public int getNumber() {
      return number;
    }
  }
  interface SubClassPropertiesWithoutOverrides extends SuperClassAsTProperties<SubClass> {
    ValueProvider<SubClass, Integer> number();
    
    // not overriding name()
  }
  interface SubClassPropertiesWithOverrides extends SuperClassAsTProperties<SubClass> {
    ValueProvider<SubClass, Integer> number();
    
    @Override
    public ValueProvider<SubClass, String> name();
  }
  
  public void testInheritingPropertyAccess() {
    SuperClassProperties superClassProperties = GWT.create(SuperClassProperties.class);
    SubClassPropertiesWithoutOverrides subClassProperties = GWT.create(SubClassPropertiesWithoutOverrides.class);
    SubClassPropertiesWithOverrides subClassPropertiesWithOverride = GWT.create(SubClassPropertiesWithOverrides.class);
    
    SubClass subClass = new SubClass();
    subClass.setName("asdf");
    subClass.setNumber(101);
    
    assertEquals("asdf", superClassProperties.name().getValue(subClass));
    assertEquals((Integer)101, subClassProperties.number().getValue(subClass));
    assertEquals("asdf", subClassProperties.name().getValue(subClass));
    
    assertEquals("asdf", subClassPropertiesWithOverride.name().getValue(subClass));
  }

  // Commented out until EXTGWT-2464 is fixed
//  public void testGenericPropertyAccess() {
//    SuperClassAsTProperties<SuperClass> props = GWT.create(SuperClassAsTProperties.class);
//    
//    SuperClass superClass = new SuperClass();
//    superClass.setName("hello");
//    
//    SubClass subClass = new SubClass();
//    subClass.setName("goodbye");
//    subClass.setNumber(99);
//    
//    assertEquals("hello", props.name().getValue(superClass));
//    assertEquals("goodbye", props.name().getValue(subClass));
//  }


  static class OuterDataWithFields {

    public String stringProperty;

    public InnerDataWithFields data;

    public InnerDataWithFields getWrappedData() {
      return data;
    }
    public void setWrappedData(InnerDataWithFields data) {
      this.data = data;
    }
  }
  static class InnerDataWithFields {
    public String stringProperty;

    public String getWrappedStringProperty() {
      return stringProperty;
    }
    public void setWrappedStringProperty(String wrapped) {
      this.stringProperty = wrapped;
    }
  }
  interface TestPropAccessWithFields extends PropertyAccess<OuterData> {
    ValueProvider<OuterDataWithFields, String> stringProperty();

    @Path("stringProperty")
    ValueProvider<OuterDataWithFields, String> outerStringFieldUsingPath();

    @Path("data.stringProperty")
    ValueProvider<OuterDataWithFields, String> innerStringUsingPathAndFields();

    @Path("wrappedData.stringProperty")
    ValueProvider<OuterDataWithFields, String> innerStringUsingMethodThenField();

    @Path("data.wrappedStringProperty")
    ValueProvider<OuterDataWithFields, String> innerStringUsingFieldThenMethod();
  }

  public void testSimpleField() {
    TestPropAccessWithFields access = GWT.create(TestPropAccessWithFields.class);

    ValueProvider<OuterDataWithFields, String> string = access.stringProperty();

    OuterDataWithFields data = new OuterDataWithFields();

    data.stringProperty = "hello";
    assertEquals("hello", string.getValue(data));
    string.setValue(data, "bye");
    assertEquals("bye", data.stringProperty);
  }
  public void testSimpleFieldWithPath() {
    TestPropAccessWithFields access = GWT.create(TestPropAccessWithFields.class);

    ValueProvider<OuterDataWithFields, String> string = access.outerStringFieldUsingPath();

    OuterDataWithFields data = new OuterDataWithFields();

    data.stringProperty = "hello";
    assertEquals("hello", string.getValue(data));
    string.setValue(data, "bye");
    assertEquals("bye", data.stringProperty);
  }

  public void testNestedField() {
    TestPropAccessWithFields access = GWT.create(TestPropAccessWithFields.class);

    ValueProvider<OuterDataWithFields, String> string = access.innerStringUsingPathAndFields();

    OuterDataWithFields data = new OuterDataWithFields();
    data.data = new InnerDataWithFields();
    data.data.stringProperty = "hello";

    data.stringProperty = "hello";
    assertEquals("hello", string.getValue(data));
    string.setValue(data, "bye");
    assertEquals("bye", data.data.stringProperty);
  }
  public void testNestedMethodThenField() {
    TestPropAccessWithFields access = GWT.create(TestPropAccessWithFields.class);

    ValueProvider<OuterDataWithFields, String> string = access.innerStringUsingMethodThenField();

    OuterDataWithFields data = new OuterDataWithFields();
    data.data = new InnerDataWithFields();
    data.data.stringProperty = "hello";

    data.stringProperty = "hello";
    assertEquals("hello", string.getValue(data));
    string.setValue(data, "bye");
    assertEquals("bye", data.data.stringProperty);
  }
  public void testNestedFieldThenMethod() {
    TestPropAccessWithFields access = GWT.create(TestPropAccessWithFields.class);

    ValueProvider<OuterDataWithFields, String> string = access.innerStringUsingFieldThenMethod();

    OuterDataWithFields data = new OuterDataWithFields();
    data.data = new InnerDataWithFields();
    data.data.stringProperty = "hello";

    data.stringProperty = "hello";
    assertEquals("hello", string.getValue(data));
    string.setValue(data, "bye");
    assertEquals("bye", data.data.stringProperty);
  }
}
