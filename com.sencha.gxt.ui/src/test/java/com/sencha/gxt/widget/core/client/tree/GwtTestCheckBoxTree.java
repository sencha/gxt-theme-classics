package com.sencha.gxt.widget.core.client.tree;

import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.RootPanel;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.core.client.util.EventTracker;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.TreeStore;
import com.sencha.gxt.ui.resources.client.TestData;
import com.sencha.gxt.ui.resources.shared.BaseDto;
import com.sencha.gxt.ui.resources.shared.FolderDto;
import com.sencha.gxt.widget.core.client.CoreBaseTestCase;
import com.sencha.gxt.widget.core.client.event.CheckChangeEvent;
import com.sencha.gxt.widget.core.client.event.CheckChangeEvent.CheckChangeHandler;
import com.sencha.gxt.widget.core.client.event.CheckChangedEvent;
import com.sencha.gxt.widget.core.client.event.CheckChangedEvent.CheckChangedHandler;
import com.sencha.gxt.widget.core.client.tree.Tree.CheckCascade;
import com.sencha.gxt.widget.core.client.tree.Tree.CheckState;

public class GwtTestCheckBoxTree extends CoreBaseTestCase {

  class KeyProvider implements ModelKeyProvider<BaseDto> {
    @Override
    public String getKey(BaseDto item) {
      return (item instanceof FolderDto ? "f-" : "m-") + item.getId().toString();
    }
  }

  public void testTree() {
    final TreeStore<BaseDto> store = getTreeStore(TestData.getMusicRootFolder());
    final Tree<BaseDto, String> tree = getTree(store);
    tree.setAutoLoad(true);
    tree.setCheckable(true);

    RootPanel.get().add(tree);
    tree.expandAll();

    final EventTracker tracker = new EventTracker();

    tree.addCheckChangeHandler(new CheckChangeHandler<BaseDto>() {
      @Override
      public void onCheckChange(CheckChangeEvent<BaseDto> event) {
        tracker.addEvent(CheckChangeEvent.getType());
      }
    });

    tree.addCheckChangedHandler(new CheckChangedHandler<BaseDto>() {

      @Override
      public void onCheckChanged(CheckChangedEvent<BaseDto> event) {
        tracker.addEvent(CheckChangedEvent.getType());
      }
    });

    // Setup an asynchronous event handler.
    Timer timer = new Timer() {
      public void run() {
        tree.setChecked(store.getRootItems().get(0), CheckState.CHECKED);
        assertEquals(1, tree.getCheckedSelection().size());
        assertEquals(CheckState.CHECKED, tree.getChecked(store.getRootItems().get(0)));
        assertEquals(1, tracker.getCount(CheckChangeEvent.getType()));
        assertEquals(1, tracker.getCount(CheckChangedEvent.getType()));

        tree.setChecked(store.getRootItems().get(1), CheckState.PARTIAL);
        assertEquals(1, tree.getCheckedSelection().size());
        assertEquals(CheckState.PARTIAL, tree.getChecked(store.getRootItems().get(1)));
        assertEquals(2, tracker.getCount(CheckChangeEvent.getType()));
        assertEquals(2, tracker.getCount(CheckChangedEvent.getType()));
        finishTest();
      }
    };

    delayTestFinish(2000);
    timer.schedule(500);
  }

  public void testCascadeTree() {
    final TreeStore<BaseDto> store = getTreeStore(TestData.getMusicRootFolder());
    final Tree<BaseDto, String> tree = getTree(store);
    tree.setAutoLoad(true);
    tree.setCheckable(true);
    tree.setCheckStyle(CheckCascade.TRI);

    RootPanel.get().add(tree);
    tree.expandAll();

    // Setup an asynchronous event handler.
    Timer timer = new Timer() {
      public void run() {
        BaseDto root = store.getRootItems().get(0);
        BaseDto child = store.getFirstChild(root);
        BaseDto subChild = store.getFirstChild(child);

        tree.setChecked(child, CheckState.CHECKED);

        assertEquals(CheckState.PARTIAL, tree.getChecked(root));
        assertEquals(CheckState.CHECKED, tree.getChecked(subChild));

        finishTest();
      }
    };

    delayTestFinish(2000);
    timer.schedule(500);
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
