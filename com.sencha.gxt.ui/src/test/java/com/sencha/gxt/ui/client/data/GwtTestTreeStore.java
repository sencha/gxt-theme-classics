package com.sencha.gxt.ui.client.data;

import com.google.gwt.user.client.ui.RootPanel;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.TreeStore;
import com.sencha.gxt.data.shared.event.StoreRemoveEvent;
import com.sencha.gxt.data.shared.event.StoreRemoveEvent.StoreRemoveHandler;
import com.sencha.gxt.data.shared.event.TreeStoreRemoveEvent;
import com.sencha.gxt.ui.client.UITestCase;
import com.sencha.gxt.ui.resources.client.TestData;
import com.sencha.gxt.ui.resources.shared.BaseDto;
import com.sencha.gxt.ui.resources.shared.FolderDto;
import com.sencha.gxt.widget.core.client.tree.Tree;

public class GwtTestTreeStore extends UITestCase {

  class KeyProvider implements ModelKeyProvider<BaseDto> {
    @Override
    public String getKey(BaseDto item) {
      return (item instanceof FolderDto ? "f-" : "m-") + item.getId().toString();
    }
  }

  public void testAssured() {
    assertEquals(true, true);
  }

  public void testingDataStore0() {
    final TreeStore<BaseDto> store = getTreeStore(TestData.getMusicRootFolder());
    final Tree<BaseDto, String> tree = getTree(store);
    tree.setAutoLoad(true);
    RootPanel.get().add(tree);
    assertEquals(4, tree.getStore().getChildCount((BaseDto) store.getRootItems().get(0)));
  }
  
  public void testRemoveChildren() {
    final TreeStore<BaseDto> store = getTreeStore(TestData.getMusicRootFolder());
    
    final BaseDto parent = store.getRootItems().get(0);
    
    store.addStoreRemoveHandler(new StoreRemoveHandler<BaseDto>() {

      @Override
      public void onRemove(StoreRemoveEvent<BaseDto> event) {
        TreeStoreRemoveEvent<BaseDto> e = (TreeStoreRemoveEvent<BaseDto>)event;
        assertNotNull(e);
        assertNotNull(e.getChildren());
        assertEquals(parent, e.getParent());
      }
    });
    
    store.removeChildren(store.getRootItems().get(0));
  }

  public void testingDataStore1() {
    final TreeStore<BaseDto> store = getTreeStore(TestData.getMusicRootFolder());
    final Tree<BaseDto, String> tree = getTree(store);
    tree.setAutoLoad(true);
    RootPanel.get().add(tree);
    assertEquals(4, tree.getStore().getChildCount((BaseDto) store.getRootItems().get(1)));
  }

  public void testingDataStore2() {
    final TreeStore<BaseDto> store = getTreeStore(TestData.getMusicRootFolder());
    final Tree<BaseDto, String> tree = getTree(store);
    tree.setAutoLoad(true);
    RootPanel.get().add(tree);
    assertEquals(1, tree.getStore().getChildCount((BaseDto) store.getRootItems().get(2)));
  }

  private Tree<BaseDto, String> getTree(TreeStore<BaseDto> store) {

    final Tree<BaseDto, String> tree = new Tree<BaseDto, String>(store, new ValueProvider<BaseDto, String>() {

      @Override
      public String getPath() {
        return "name";
      }

      @Override
      public String getValue(BaseDto object) {
        return object.getName();
      }

      @Override
      public void setValue(BaseDto object, String value) {
      }
    });
    tree.setWidth(300);

    tree.setTitle("TreeTitle");
    return tree;
  }

  private TreeStore<BaseDto> getTreeStore(BaseDto data) {
    TreeStore<BaseDto> store = new TreeStore<BaseDto>(new KeyProvider());

    // FolderDto root = TestData.getMusicRootFolder();
    FolderDto root = (FolderDto) data;
    for (BaseDto base : root.getChildren()) {
      store.add(base);
      if (base instanceof FolderDto) {
        processFolder(store, (FolderDto) base);
      }
    }
    return store;
  }

  private void processFolder(TreeStore<BaseDto> store, FolderDto folder) {
    for (BaseDto child : folder.getChildren()) {
      store.add(folder, child);
      if (child instanceof FolderDto) {
        processFolder(store, (FolderDto) child);
      }
    }
  }
}
