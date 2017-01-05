package com.sencha.gxt.widget.core.client.form;

import java.util.Arrays;

import com.sencha.gxt.core.client.IdentityValueProvider;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.widget.core.client.CoreBaseTestCase;
import com.sencha.gxt.widget.core.client.ListView;

public class GwtTestListField extends CoreBaseTestCase {
  public void testSetStore() throws Exception {
    ListStore<String> store = new ListStore<String>(new NullSafeStringModelKeyProvider());
    store.add("a");
    ListView<String, String> listView = new ListView<String, String>(store, new IdentityValueProvider<String>());
    ListField<String, String> field = new ListField<String, String>(listView);

    field.setValue("a");

    assertEquals("a", field.getValue());

    ListStore<String> newStore = new ListStore<String>(new NullSafeStringModelKeyProvider());
    newStore.addAll(Arrays.asList("b","c","d"));
    field.setStore(newStore);
    assertEquals(null, field.getValue());
  }

  public void testSetValue() throws Exception {
    ListStore<String> store = new ListStore<String>(new NullSafeStringModelKeyProvider());
    store.addAll(Arrays.asList("a", "b", "c", "d"));
    ListView<String, String> listView = new ListView<String, String>(store, new IdentityValueProvider<String>());
    ListField<String, String> field = new ListField<String, String>(listView);

    assertNull(field.getValue());

    field.setValue("c");
    assertEquals(1, listView.getSelectionModel().getSelectedItems().size());
    assertEquals("c", field.getValue());

    field.setValue(null);
    assertEquals(0, listView.getSelectionModel().getSelectedItems().size());
    assertEquals(null, field.getValue());

    field.setValue("foo");
    assertEquals(0, listView.getSelectionModel().getSelectedItems().size());
    assertEquals(null, field.getValue());


    //Selection model can't handle a null item selected
//    store.add(null);
//    field.setValue(null);
//    assertEquals(1, listView.getSelectionModel().getSelectedItems().size());
//    assertEquals(null, field.getValue());
  }

  public void testClear() throws Exception {
    ListStore<String> store = new ListStore<String>(new NullSafeStringModelKeyProvider());
    store.add("a");
    ListView<String, String> listView = new ListView<String, String>(store, new IdentityValueProvider<String>());
    ListField<String, String> field = new ListField<String, String>(listView);

    store.add("b");
    assertEquals(2, store.size());
    assertEquals(0, listView.getSelectionModel().getSelectedItems().size());
    assertEquals(null, field.getValue());

    field.setValue("b");
    assertEquals(1, listView.getSelectionModel().getSelectedItems().size());
    assertEquals("b", field.getValue());

    field.reset();
    assertEquals(2, store.size());
    assertEquals(0, listView.getSelectionModel().getSelectedItems().size());
    assertEquals(null, field.getValue());

    store.add("c");
    assertEquals(3, store.size());
    assertEquals(0, listView.getSelectionModel().getSelectedItems().size());

    field.clear();

    store.replaceAll(Arrays.asList("z", "y"));

    field.clear();
  }

  public void testReset() throws Exception {
    ListStore<String> store = new ListStore<String>(new NullSafeStringModelKeyProvider());
    store.add("a");
    ListView<String, String> listView = new ListView<String, String>(store, new IdentityValueProvider<String>());
    ListField<String, String> field = new ListField<String, String>(listView);

    store.add("b");
    assertEquals(2, store.size());
    assertEquals(0, listView.getSelectionModel().getSelectedItems().size());
    assertEquals(null, field.getValue());

    field.setValue("b");
    assertEquals(1, listView.getSelectionModel().getSelectedItems().size());
    assertEquals("b", field.getValue());

    field.reset();
    assertEquals(2, store.size());
    assertEquals(0, listView.getSelectionModel().getSelectedItems().size());
    assertEquals(null, field.getValue());

    store.add("c");
    assertEquals(3, store.size());
    assertEquals(0, listView.getSelectionModel().getSelectedItems().size());

    field.reset();

    store.replaceAll(Arrays.asList("z", "y"));

    field.reset();
  }

  private static class NullSafeStringModelKeyProvider implements ModelKeyProvider<String> {
    @Override
    public String getKey(String item) {
      return item == null ? "" : "+" + item;
    }
  }
}
