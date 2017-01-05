package com.sencha.gxt.widget.core.client.tree;

import com.google.gwt.event.logical.shared.BeforeSelectionEvent;
import com.google.gwt.event.logical.shared.BeforeSelectionHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.RootPanel;
import com.sencha.gxt.core.client.Style.SelectionMode;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.TreeStore;
import com.sencha.gxt.ui.resources.client.TestData;
import com.sencha.gxt.ui.resources.shared.BaseDto;
import com.sencha.gxt.ui.resources.shared.FolderDto;
import com.sencha.gxt.widget.core.client.CoreBaseTestCase;

public class GwtTestTreeView extends CoreBaseTestCase {

  class KeyProvider implements ModelKeyProvider<BaseDto> {
    @Override
    public String getKey(BaseDto item) {
      return (item instanceof FolderDto ? "f-" : "m-") + item.getId().toString();
    }
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

  private Tree<BaseDto, String> getTree(TreeStore<BaseDto> store) {

    final Tree<BaseDto, String> tree = new Tree<BaseDto, String>(store, new ValueProvider<BaseDto, String>() {

      @Override
      public String getValue(BaseDto object) {
        return object.getName();
      }

      @Override
      public void setValue(BaseDto object, String value) {
      }

      @Override
      public String getPath() {
        return "name";
      }
    });
    tree.setWidth(300);

    tree.setTitle("TreeTitle");
    return tree;
  }

  private void processFolder(TreeStore<BaseDto> store, FolderDto folder) {
    for (BaseDto child : folder.getChildren()) {
      store.add(folder, child);
      if (child instanceof FolderDto) {
        processFolder(store, (FolderDto) child);
      }
    }
  }

  public void testAssured() {
    assertEquals(true, true);
  }

  public void testScrollDelay() {
    final TreeStore<BaseDto> store = getTreeStore(TestData.getNodeLeaf());
    final TreeView<BaseDto> treeView = new TreeView<BaseDto>();
    final Tree<BaseDto, String> tree = getTree(store);
    final int delay = 143;
    tree.setView(treeView);
    tree.setAutoLoad(true);
    treeView.setScrollDelay(delay);
    RootPanel.get().add(tree);
    assertEquals(treeView.getScrollDelay(), delay);
  }

  public void testSetCacheSize() {
    final TreeStore<BaseDto> store = getTreeStore(TestData.getNodeLeaf());
    final TreeView<BaseDto> treeView = new TreeView<BaseDto>();
    final Tree<BaseDto, String> tree = getTree(store);
    final int size = 3;
    tree.setView(treeView);
    tree.setAutoLoad(true);
    treeView.setCacheSize(size);
    RootPanel.get().add(tree);
    assertEquals(treeView.getCacheSize(), size);
  }

  public void testSetCleanDelay() {
    final TreeStore<BaseDto> store = getTreeStore(TestData.getNodeLeaf());
    final TreeView<BaseDto> treeView = new TreeView<BaseDto>();
    final Tree<BaseDto, String> tree = getTree(store);
    final int cleanDelay = 3;
    tree.setView(treeView);
    tree.setAutoLoad(true);
    treeView.setCleanDelay(cleanDelay);
    RootPanel.get().add(tree);
    assertEquals(treeView.getCleanDelay(), cleanDelay);
  }

  public void testNullType() {
    FolderDto folder = TestData.getNodeLeaf();
    final TreeStore<BaseDto> store = getTreeStore(folder);
    final TreeView<BaseDto> treeView = new TreeView<BaseDto>();
    final Tree<BaseDto, String> tree = getTree(store);
    TreeSelectionModel<BaseDto> sm = new TreeSelectionModel<BaseDto>();
    sm.setSelectionMode(SelectionMode.SINGLE);
    tree.setSelectionModel(sm);
    final int cleanDelay = 3;
    tree.setView(treeView);
    tree.setAutoLoad(true);
    treeView.setCleanDelay(cleanDelay);
    RootPanel.get().add(tree);
    sm.select(store.getChild(0), false);
  }

  public void testSingleTree() {
    FolderDto folder = TestData.getNodeLeaf();
    final TreeStore<BaseDto> store = getTreeStore(folder);
    final TreeView<BaseDto> treeView = new TreeView<BaseDto>();
    final Tree<BaseDto, String> tree = getTree(store);
    TreeSelectionModel<BaseDto> sm = new TreeSelectionModel<BaseDto>();
    sm.setSelectionMode(SelectionMode.SINGLE);
    sm.addBeforeSelectionHandler(new BeforeSelectionHandler<BaseDto>() {
      @Override
      public void onBeforeSelection(BeforeSelectionEvent<BaseDto> event) {
      }
    });
    tree.setSelectionModel(sm);
    final int cleanDelay = 3;
    tree.setView(treeView);
    tree.setAutoLoad(true);
    treeView.setCleanDelay(cleanDelay);
    RootPanel.get().add(tree);
    sm.select(store.getChild(0), false);
  }

  public void testSingleTreeDoubleSelect() {
    FolderDto folder = TestData.getNodeLeaf();
    final TreeStore<BaseDto> store = getTreeStore(folder);
    final TreeView<BaseDto> treeView = new TreeView<BaseDto>();
    final Tree<BaseDto, String> tree = getTree(store);
    TreeSelectionModel<BaseDto> sm = new TreeSelectionModel<BaseDto>();
    sm.setSelectionMode(SelectionMode.SINGLE);
    HandlerRegistration reg = sm.addBeforeSelectionHandler(new BeforeSelectionHandler<BaseDto>() {
      @Override
      public void onBeforeSelection(BeforeSelectionEvent<BaseDto> event) {
      }
    });
    tree.setSelectionModel(sm);
    final int cleanDelay = 3;
    tree.setView(treeView);
    tree.setAutoLoad(true);
    treeView.setCleanDelay(cleanDelay);
    RootPanel.get().add(tree);
    sm.select(store.getChild(0), false);
    reg.removeHandler();
    reg = sm.addBeforeSelectionHandler(new BeforeSelectionHandler<BaseDto>() {
      @Override
      public void onBeforeSelection(BeforeSelectionEvent<BaseDto> event) {
        fail();
      }
    });
    store.getChild(0);
  }
}
