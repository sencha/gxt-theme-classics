package com.sencha.gxt.widget.core.client.form;

import java.math.BigDecimal;
import java.math.BigInteger;

import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.InputElement;
import com.google.gwt.dom.client.NodeList;
import com.google.gwt.editor.client.Editor.Path;
import com.google.gwt.i18n.client.LocaleInfo;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.junit.client.WithProperties;
import com.google.gwt.junit.client.WithProperties.Property;
import com.google.gwt.user.client.ui.RootPanel;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import com.sencha.gxt.widget.core.client.CoreBaseTestCase;
import com.sencha.gxt.widget.core.client.form.NumberPropertyEditor.BigDecimalPropertyEditor;
import com.sencha.gxt.widget.core.client.form.NumberPropertyEditor.BigIntegerPropertyEditor;
import com.sencha.gxt.widget.core.client.form.NumberPropertyEditor.DoublePropertyEditor;
import com.sencha.gxt.widget.core.client.form.NumberPropertyEditor.FloatPropertyEditor;
import com.sencha.gxt.widget.core.client.form.NumberPropertyEditor.IntegerPropertyEditor;
import com.sencha.gxt.widget.core.client.form.NumberPropertyEditor.LongPropertyEditor;
import com.sencha.gxt.widget.core.client.form.NumberPropertyEditor.ShortPropertyEditor;
import com.sencha.gxt.widget.core.client.form.validator.MaxNumberValidator;
import com.sencha.gxt.widget.core.client.form.validator.MinNumberValidator;

public class GwtTestNumberField extends CoreBaseTestCase {

  public class TestModel {

    private Integer key;

    public TestModel(Integer key) {
      this.key = key;
    }

    public Integer getKey() {
      return key;
    }

    public void setKey(Integer key) {
      this.key = key;
    }

  }

  public interface TestModelProperties extends PropertyAccess<TestModel> {

    ValueProvider<TestModel, Integer> key();

    @Path("key")
    ModelKeyProvider<TestModel> keyProvider();

  }

  public void testAllowNegative() {
    // This test is for EXTGWT-1616, to make sure no errors occur when setting
    // this value.
    // The test cannot simulate keystrokes, so just verifying that the exception
    // doesn't occur
    NumberField<Integer> field = new NumberField<Integer>(new IntegerPropertyEditor());
    field.setAllowNegative(false);

    field.setAllowNegative(true);
  }

  public void testAttributes() {
    NumberField<Integer> field = new NumberField<Integer>(new IntegerPropertyEditor());
    field.setName("name");
    field.setReadOnly(true);

    RootPanel.get().add(field);

    assertEquals("name", field.getElement().selectNode("input").getPropertyString("name"));
    assertTrue(field.getElement().selectNode("input").<InputElement> cast().isReadOnly());

    field.setReadOnly(false);
    assertFalse(field.getElement().selectNode("input").<InputElement> cast().isReadOnly());
  };

  public void testCharacters() {
    NumberField<Integer> field = new NumberField<Integer>(new IntegerPropertyEditor());
    assertEquals(LocaleInfo.getCurrentLocale().getNumberConstants().decimalSeparator(), field.getDecimalSeparator());

    field.setDecimalSeparator("*");
    assertEquals("*", field.getDecimalSeparator());

    assertEquals("0123456789", field.getBaseChars());

    field.setBaseChars("234");
    assertEquals("234", field.getBaseChars());

  }

  @WithProperties(@Property(name = "locale", value = "de_DE"))
  public void testDeLocalePropertyEditor() {
    runLocaleSpecificPropertyEditorTest("1.234", "1,234.567");
  }

  public void testDisabled() {
    NumberField<Integer> field = new NumberField<Integer>(new IntegerPropertyEditor());

    InputElement input = field.getElement().selectNode("input").cast();
    assertEquals(false, input.isDisabled());
    field.disable();
    assertEquals(true, input.isDisabled());

    RootPanel.get().add(field);
    assertEquals(true, input.isDisabled());

    field.redraw();
    // input element redrawn
    input = field.getElement().selectNode("input").cast();
    assertEquals(true, input.isDisabled());

    field.enable();
    assertEquals(false, input.isDisabled());
  }

  public void testEmptyText() {
    NumberField<Integer> field = new NumberField<Integer>(new IntegerPropertyEditor());
    field.setEmptyText("empty");

    assertEquals("empty", field.getElement().selectNode("input").getPropertyString("placeholder"));

    RootPanel.get().add(field);

    field.redraw();

    assertEquals("empty", field.getElement().selectNode("input").getPropertyString("placeholder"));
  }

  public void testMinMax() {
    NumberField<Integer> field = new NumberField<Integer>(new IntegerPropertyEditor());
    field.addValidator(new MinNumberValidator<Integer>(10));
    field.addValidator(new MaxNumberValidator<Integer>(100));

    RootPanel.get().add(field);

    assertTrue(field.isValid());
    field.setValue(5);
    assertFalse(field.isValid());

    field.setValue(50);
    assertTrue(field.isValid());

    field.setValue(500);
    assertFalse(field.isValid());
  }

  public void testReadOnly() {
    NumberField<Integer> field = new NumberField<Integer>(new IntegerPropertyEditor());
    RootPanel.get().add(field);

    NodeList<Element> inputs;
    inputs = field.getElement().select("input");
    assertTrue(inputs.getLength() > 0);
    for (int i = 0; i < inputs.getLength(); i++) {
      assertFalse(inputs.getItem(i).hasAttribute("readonly"));
    }

    field.setReadOnly(true);
    inputs = field.getElement().select("input");
    assertTrue(inputs.getLength() > 0);
    for (int i = 0; i < inputs.getLength(); i++) {
      assertTrue(inputs.getItem(i).hasAttribute("readonly"));
    }

    field.redraw();
    inputs = field.getElement().select("input");
    assertTrue(inputs.getLength() > 0);
    for (int i = 0; i < inputs.getLength(); i++) {
      assertTrue(inputs.getItem(i).hasAttribute("readonly"));
    }
  }

  public void testRedraw() {
    NumberField<Integer> field = new NumberField<Integer>(new IntegerPropertyEditor());
    field.setValue(10);

    RootPanel.get().add(field);

    field.redraw();

    assertEquals(Integer.valueOf(10), field.getCurrentValue());
    assertEquals("10", field.getText());
  }

  @WithProperties(@Property(name = "locale", value = "en_US"))
  public void testUsLocalePropertyEditor() {
    runLocaleSpecificPropertyEditorTest("1,234", "1.234,567");
  }

  private boolean isValidNumber(NumberField<?> field, String inputText) {
    boolean isValidNumber;
    try {
      field.setText(inputText);
      isValidNumber = field.isCurrentValid(true);
    } catch (NumberFormatException e) {
      isValidNumber = false;
    }
    return isValidNumber;
  }

  private boolean isValidNumber(NumberPropertyEditor<?> editor, String inputText) {
    boolean isValidNumber;
    try {
      editor.parseString(inputText);
      isValidNumber = true;
    } catch (NumberFormatException e) {
      isValidNumber = false;
    }
    return isValidNumber;
  }

  private void runLocaleSpecificPropertyEditorTest(String validForLocale, String notValidForLocale) {
    NumberFormat nf = NumberFormat.getFormat("#,##0.###");

    DoublePropertyEditor dpe = new DoublePropertyEditor(nf);
    assertEquals(1234d, dpe.parseString(validForLocale));
    assertFalse(isValidNumber(dpe, notValidForLocale));
    NumberField<Double> dnf = new NumberField<Double>(dpe);
    assertTrue(isValidNumber(dnf, validForLocale));
    assertFalse(isValidNumber(dnf, notValidForLocale));

    FloatPropertyEditor fpe = new FloatPropertyEditor(nf);
    Float floatValue = fpe.parseString(validForLocale);
    assertEquals(1234f, floatValue);
    assertFalse(isValidNumber(fpe, notValidForLocale));
    NumberField<Float> fnf = new NumberField<Float>(fpe);
    assertTrue(isValidNumber(fnf, validForLocale));
    assertFalse(isValidNumber(fnf, notValidForLocale));

    BigDecimalPropertyEditor bdpe = new BigDecimalPropertyEditor(nf);
    BigDecimal bigDecimalValue = bdpe.parseString(validForLocale);
    assertEquals(new BigDecimal(1234d), bigDecimalValue);
    assertFalse(isValidNumber(bdpe, notValidForLocale));
    NumberField<BigDecimal> bdnf = new NumberField<BigDecimal>(bdpe);
    assertTrue(isValidNumber(bdnf, validForLocale));
    assertFalse(isValidNumber(bdnf, notValidForLocale));

    BigIntegerPropertyEditor bipe = new BigIntegerPropertyEditor(nf);
    BigInteger bigIntegerValue = bipe.parseString(validForLocale);
    assertEquals(new BigInteger("1234"), bigIntegerValue);
    assertFalse(isValidNumber(bipe, notValidForLocale));
    NumberField<BigInteger> binf = new NumberField<BigInteger>(bipe);
    assertTrue(isValidNumber(binf, validForLocale));
    assertFalse(isValidNumber(binf, notValidForLocale));

    IntegerPropertyEditor ipe = new IntegerPropertyEditor(nf);
    Integer integerValue = ipe.parseString(validForLocale);
    assertEquals(new Integer(1234), integerValue);
    assertFalse(isValidNumber(ipe, notValidForLocale));
    NumberField<Integer> inf = new NumberField<Integer>(ipe);
    assertTrue(isValidNumber(inf, validForLocale));
    assertFalse(isValidNumber(inf, notValidForLocale));

    LongPropertyEditor lpe = new LongPropertyEditor(nf);
    Long longValue = lpe.parseString(validForLocale);
    assertEquals(new Long(1234), longValue);
    assertFalse(isValidNumber(lpe, notValidForLocale));
    NumberField<Long> lnf = new NumberField<Long>(lpe);
    assertTrue(isValidNumber(lnf, validForLocale));
    assertFalse(isValidNumber(lnf, notValidForLocale));

    ShortPropertyEditor spe = new ShortPropertyEditor(nf);
    Short shortValue = spe.parseString(validForLocale);
    assertEquals(new Short((short) 1234), shortValue);
    assertFalse(isValidNumber(spe, notValidForLocale));
    NumberField<Short> snf = new NumberField<Short>(spe);
    assertTrue(isValidNumber(snf, validForLocale));
    assertFalse(isValidNumber(snf, notValidForLocale));
  }

}
