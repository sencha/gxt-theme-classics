package com.sencha.gxt.widget.core.client.form;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import com.google.gwt.cell.client.TextCell;
import com.sencha.gxt.core.client.IdentityValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.widget.core.client.CoreBaseTestCase;

public class GwtTestDualListField extends CoreBaseTestCase {
  public void testSetValue() {
    DualListField<String, String> field = new DualListField<String, String>(new NullSafeStringModelKeyProvider(), new IdentityValueProvider<String>(), new TextCell());
    field.getFromStore().addAll(Arrays.asList("a", "b", "c", "d"));
    assertEquals(0, field.getToStore().size());
    assertEquals(4, field.getFromStore().size());

    field.setValue(Arrays.asList("a", "b"));
    assertEquals(2, field.getToStore().size());
    assertEquals(2, field.getFromStore().size());
    assertEquals(Arrays.asList("a", "b"), field.getValue());

    field.setValue(Arrays.asList("a", "z"));
    assertEquals(1, field.getToStore().size());
    assertEquals(3, field.getFromStore().size());
    assertEquals(Collections.singletonList("a"), field.getValue());

    field.getToStore().add("e");
    assertEquals(2, field.getToStore().size());
    assertEquals(Arrays.asList("a", "e"), field.getValue());

    field.setValue(null);
    assertEquals(5, field.getFromStore().size());
    assertEquals(new ArrayList<String>(), field.getValue());
  }

  public void testReset() {
    DualListField<String, String> field = new DualListField<String, String>(new NullSafeStringModelKeyProvider(), new IdentityValueProvider<String>(), new TextCell());
    field.getFromStore().addAll(Arrays.asList("a", "b", "c", "d"));

    field.setValue(Arrays.asList("a"));
    assertEquals(3, field.getFromStore().size());

    field.reset();
    assertEquals(4, field.getFromStore().size());
    assertEquals(new ArrayList<String>(), field.getValue());
  }

  public void testButtons() {
    DualListField<String, String> field = new DualListField<String, String>(new NullSafeStringModelKeyProvider(), new IdentityValueProvider<String>(), new TextCell());
    field.getFromStore().addAll(Arrays.asList("a", "b", "c", "d"));

    assertNotNull(field.getLeftButton());
    assertNotNull(field.getAllLeftButton());
    assertNotNull(field.getRightButton());
    assertNotNull(field.getAllRightButton());
    assertNotNull(field.getUpButton());
    assertNotNull(field.getDownButton());
  }

  public void testClear() {
    DualListField<String, String> field = new DualListField<String, String>(new NullSafeStringModelKeyProvider(), new IdentityValueProvider<String>(), new TextCell());
    field.getFromStore().addAll(Arrays.asList("a", "b", "c", "d"));

    field.setValue(Arrays.asList("a"));
    assertEquals(3, field.getFromStore().size());

    field.clear();
    assertEquals(4, field.getFromStore().size());
    assertEquals(new ArrayList<String>(), field.getValue());
  }

  private static class NullSafeStringModelKeyProvider implements ModelKeyProvider<String> {
    @Override
    public String getKey(String item) {
      return item == null ? "" : "+" + item;
    }
  }
}
