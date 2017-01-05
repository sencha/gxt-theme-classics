package com.sencha.gxt.widget.core.client.tree;

import java.util.ArrayList;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.dom.client.Style.Display;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.RootPanel;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.Store;
import com.sencha.gxt.data.shared.Store.StoreFilter;
import com.sencha.gxt.data.shared.TreeStore;
import com.sencha.gxt.ui.resources.client.TestData;
import com.sencha.gxt.ui.resources.shared.BaseDto;
import com.sencha.gxt.ui.resources.shared.FolderDto;
import com.sencha.gxt.ui.resources.shared.MusicDto;
import com.sencha.gxt.widget.core.client.CoreBaseTestCase;
import com.sencha.gxt.widget.core.client.event.BeforeCollapseItemEvent;
import com.sencha.gxt.widget.core.client.event.BeforeCollapseItemEvent.BeforeCollapseItemHandler;
import com.sencha.gxt.widget.core.client.event.CheckChangeEvent;
import com.sencha.gxt.widget.core.client.event.CheckChangeEvent.CheckChangeHandler;
import com.sencha.gxt.widget.core.client.event.CheckChangedEvent;
import com.sencha.gxt.widget.core.client.event.CheckChangedEvent.CheckChangedHandler;
import com.sencha.gxt.widget.core.client.event.CollapseItemEvent;
import com.sencha.gxt.widget.core.client.event.CollapseItemEvent.CollapseItemHandler;
import com.sencha.gxt.widget.core.client.event.DisableEvent;
import com.sencha.gxt.widget.core.client.event.DisableEvent.DisableHandler;
import com.sencha.gxt.widget.core.client.event.EnableEvent;
import com.sencha.gxt.widget.core.client.event.EnableEvent.EnableHandler;
import com.sencha.gxt.widget.core.client.event.ExpandItemEvent;
import com.sencha.gxt.widget.core.client.event.ExpandItemEvent.ExpandItemHandler;
import com.sencha.gxt.widget.core.client.selection.SelectionChangedEvent;
import com.sencha.gxt.widget.core.client.selection.SelectionChangedEvent.SelectionChangedHandler;
import com.sencha.gxt.widget.core.client.tree.Tree.CheckState;
import com.sencha.gxt.widget.core.client.tree.Tree.TreeNode;

public class GwtTestTree extends CoreBaseTestCase {
  class EscapedKeyProvider implements ModelKeyProvider<BaseDto> {
    @Override
    public String getKey(BaseDto item) {
      return (item instanceof FolderDto ? "f-'\"" : "m-") + item.getId().toString();
    }
  }
  class KeyProvider implements ModelKeyProvider<BaseDto> {
    @Override
    public String getKey(BaseDto item) {
      return (item instanceof FolderDto ? "f-" : "m-") + item.getId().toString();
    }
  }

  private static final String TreeTitle = "TreeTitle";

  private static int seq = -1;

  private static int getSequence() {
    seq = seq + 1;
    return seq;
  }

  public void testAssured() {
    assertEquals(true, true);
  }

  public void testBeforeCollapseEvent() {
    clearSequence();
    final TreeStore<BaseDto> store = getTreeStore(TestData.getMusicRootFolder());
    final Tree<BaseDto, String> tree = getTree(store);
    tree.setAutoLoad(true);
    tree.addCollapseHandler(new CollapseItemHandler<BaseDto>() {
      public void onCollapse(CollapseItemEvent<BaseDto> event) {
        assertEquals(1, getSequence());
        finishTest();
      }
    });
    tree.addBeforeCollapseHandler(new BeforeCollapseItemHandler<BaseDto>() {
      @Override
      public void onBeforeCollapse(BeforeCollapseItemEvent<BaseDto> event) {
        assertEquals(0, getSequence());
      }
    });
    RootPanel.get().add(tree);
    tree.expandAll();

    // Setup an asynchronous event handler.
    Timer timer = new Timer() {
      public void run() {
        tree.setExpanded(store.getRootItems().get(0), false, false);
      }
    };

    delayTestFinish(2000);
    timer.schedule(500);
  }

  public void testCheckedChangedSelectionBoth1() {
    final TreeStore<BaseDto> store = getTreeStore(TestData.getNodeLeaf());

    final Tree<BaseDto, String> tree = getTree(store);
    tree.setCheckable(true);
    tree.setAutoLoad(true);
    tree.addCheckChangedHandler(new CheckChangedHandler<BaseDto>() {
      @Override
      public void onCheckChanged(CheckChangedEvent<BaseDto> event) {
        BaseDto leaf = store.findModelWithKey("m-1");
        assertTrue(tree.isChecked(leaf));
        finishTest();
      }
    });

    RootPanel.get().add(tree);
    // Setup an asynchronous event handler.
    Timer timer = new Timer() {
      public void run() {
        BaseDto leaf = store.findModelWithKey("m-1");
        tree.setChecked(leaf, CheckState.CHECKED);
      }
    };
    delayTestFinish(2000);
    timer.schedule(500);
  }

  public void testCheckedSelectionBoth1() {
    final TreeStore<BaseDto> store = getTreeStore(TestData.getNodeLeaf());

    final Tree<BaseDto, String> tree = getTree(store);
    tree.setCheckable(true);
    tree.setAutoLoad(true);
    tree.addCheckChangeHandler(new CheckChangeHandler<BaseDto>() {
      @Override
      public void onCheckChange(CheckChangeEvent<BaseDto> event) {
        BaseDto leaf = store.findModelWithKey("m-1");
        assertTrue(tree.isChecked(leaf));
        finishTest();
      }
    });

    RootPanel.get().add(tree);
    // Setup an asynchronous event handler.
    Timer timer = new Timer() {
      public void run() {
        BaseDto leaf = store.findModelWithKey("m-1");
        tree.setChecked(leaf, CheckState.CHECKED);
      }
    };
    delayTestFinish(2000);
    timer.schedule(500);
  }

  public void testCheckedSelectionBoth2() {
    final TreeStore<BaseDto> store = getTreeStore(TestData.getNodeLeaf());

    final Tree<BaseDto, String> tree = getTree(store);
    tree.setCheckable(true);
    tree.setAutoLoad(true);
    tree.addCheckChangeHandler(new CheckChangeHandler<BaseDto>() {
      @Override
      public void onCheckChange(CheckChangeEvent<BaseDto> event) {
        BaseDto leaf = store.findModelWithKey("m-1");
        BaseDto branch = store.findModelWithKey("f-2");
        assertFalse(tree.isChecked(leaf));
        assertTrue(tree.isChecked(branch));
        finishTest();
      }
    });

    RootPanel.get().add(tree);
    // Setup an asynchronous event handler.
    Timer timer = new Timer() {
      public void run() {
        BaseDto branch = store.findModelWithKey("f-2");
        tree.setChecked(branch, CheckState.CHECKED);
      }
    };
    delayTestFinish(2000);
    timer.schedule(500);
  }

  public void testCheckedSelectionBoth3() {
    final TreeStore<BaseDto> store = getTreeStore(TestData.getNodeLeaf());

    final Tree<BaseDto, String> tree = getTree(store);
    tree.setCheckable(true);
    tree.setAutoLoad(true);
    tree.addCheckChangeHandler(new CheckChangeHandler<BaseDto>() {
      @Override
      public void onCheckChange(CheckChangeEvent<BaseDto> event) {
        if (2 == (event.getItem().getId())) {
          BaseDto branch = store.findModelWithKey("f-2");
          assertTrue(tree.isChecked(branch));
          finishTest();
        }
      }
    });

    RootPanel.get().add(tree);
    // Setup an asynchronous event handler.
    Timer timer = new Timer() {
      public void run() {
        BaseDto leaf = store.findModelWithKey("m-1");
        tree.setChecked(leaf, CheckState.CHECKED);
      }
    };
    delayTestFinish(2000);
    timer.schedule(500);
  }

  public void testCheckedSelectionLeaf1() {
    final TreeStore<BaseDto> store = getTreeStore(TestData.getNodeLeaf());

    final Tree<BaseDto, String> tree = getTree(store);
    tree.setCheckable(true);
    tree.setAutoLoad(true);
    tree.setCheckNodes(Tree.CheckNodes.LEAF);
    tree.addCheckChangeHandler(new CheckChangeHandler<BaseDto>() {
      @Override
      public void onCheckChange(CheckChangeEvent<BaseDto> event) {
        BaseDto leaf = store.findModelWithKey("m-1");
        assertTrue(tree.isChecked(leaf));
        finishTest();
      }
    });

    RootPanel.get().add(tree);
    // Setup an asynchronous event handler.
    Timer timer = new Timer() {
      public void run() {
        BaseDto leaf = store.findModelWithKey("m-1");
        tree.setChecked(leaf, CheckState.CHECKED);
      }
    };
    delayTestFinish(2000);
    timer.schedule(500);
  }

  public void testCheckedSelectionParent1() {
    final TreeStore<BaseDto> store = getTreeStore(TestData.getNodeLeaf());

    final Tree<BaseDto, String> tree = getTree(store);
    tree.setCheckable(true);
    tree.setAutoLoad(true);
    tree.setCheckNodes(Tree.CheckNodes.PARENT);
    tree.addCheckChangeHandler(new CheckChangeHandler<BaseDto>() {
      @Override
      public void onCheckChange(CheckChangeEvent<BaseDto> event) {
        BaseDto leaf = store.findModelWithKey("m-1");
        BaseDto branch = store.findModelWithKey("f-2");
        assertFalse(tree.isChecked(leaf));
        assertTrue(tree.isChecked(branch));
        finishTest();
      }
    });

    RootPanel.get().add(tree);
    // Setup an asynchronous event handler.
    Timer timer = new Timer() {
      public void run() {
        BaseDto branch = store.findModelWithKey("f-2");
        tree.setChecked(branch, CheckState.CHECKED);
      }
    };
    delayTestFinish(2000);
    timer.schedule(500);
  }

  public void testCollapseEvent() {
    final TreeStore<BaseDto> store = getTreeStore(TestData.getMusicRootFolder());

    final Tree<BaseDto, String> tree = getTree(store);
    tree.setAutoLoad(true);
    tree.addCollapseHandler(new CollapseItemHandler<BaseDto>() {
      public void onCollapse(CollapseItemEvent<BaseDto> event) {
        finishTest();
      }
    });
    RootPanel.get().add(tree);
    tree.expandAll();

    // Setup an asynchronous event handler.
    Timer timer = new Timer() {
      public void run() {
        tree.setExpanded(store.getRootItems().get(0), false);
      }
    };

    delayTestFinish(2000);
    timer.schedule(500);
  }

  public void testDisableEvent() {
    final TreeStore<BaseDto> store = getTreeStore(TestData.getMusicRootFolder());

    final Tree<BaseDto, String> tree = getTree(store);
    tree.setAutoLoad(true);
    tree.addDisableHandler(new DisableHandler() {
      @Override
      public void onDisable(DisableEvent event) {
        finishTest();

      }
    });
    RootPanel.get().add(tree);
    // Setup an asynchronous event handler.
    Timer timer = new Timer() {
      public void run() {
        tree.disable();
      }
    };

    delayTestFinish(2000);
    timer.schedule(500);
  }

  public void testEnableEvent() {
    final TreeStore<BaseDto> store = getTreeStore(TestData.getMusicRootFolder());

    final Tree<BaseDto, String> tree = getTree(store);
    tree.setAutoLoad(true);
    tree.addEnableHandler(new EnableHandler() {
      @Override
      public void onEnable(EnableEvent event) {
        finishTest();
      }
    });
    tree.disable();
    RootPanel.get().add(tree);
    // Setup an asynchronous event handler.
    Timer timer = new Timer() {
      public void run() {
        tree.enable();
      }
    };
    delayTestFinish(2000);
    timer.schedule(500);
  }

  public void testEscapedKeys() {
    final TreeStore<BaseDto> store = getTreeStoreEscaped(TestData.getMusicRootFolder());

    final Tree<BaseDto, String> tree = getTree(store);

    RootPanel.get().add(tree);

    TreeNode<BaseDto> node = tree.findNode(store.getRootItems().get(0));
    assertNotNull(node);
  }

  public void testExpandEvent() {
    final TreeStore<BaseDto> store = getTreeStore(TestData.getMusicRootFolder());

    final Tree<BaseDto, String> tree = getTree(store);
    tree.setAutoLoad(true);
    tree.addExpandHandler(new ExpandItemHandler<BaseDto>() {
      public void onExpand(ExpandItemEvent<BaseDto> event) {
        finishTest();
      }
    });
    RootPanel.get().add(tree);
    // Setup an asynchronous event handler.
    Timer timer = new Timer() {
      public void run() {
        tree.setExpanded(store.getRootItems().get(0), true, false);
      }
    };

    delayTestFinish(2000);
    timer.schedule(500);
  }

  public void testFilterWhileDetached() {
    final TreeStore<BaseDto> store = getTreeStore(TestData.getMusicRootFolder());
    final Tree<BaseDto, String> tree = getTree(store);

    RootPanel.get().add(tree);

    store.replaceChildren(store.getRootItems().get(0), new ArrayList<BaseDto>());
    store.addFilter(new StoreFilter<BaseDto>() {
      @Override
      public boolean select(Store<BaseDto> store, BaseDto parent, BaseDto item) {
        return (item instanceof MusicDto && ((MusicDto) item).getAuthor().startsWith("B"));
      }
    });
    store.setEnableFilters(true);

    RootPanel.get().add(tree);
    Scheduler.get().scheduleDeferred(new ScheduledCommand() {
      @Override
      public void execute() {
        tree.getElement().getStyle().setDisplay(Display.NONE);
        store.setEnableFilters(false);
        store.setEnableFilters(true);
        finishTest();
      }
    });
    delayTestFinish(1000);
  }

  public void testLeaf() {
    final TreeStore<BaseDto> store = getTreeStore(TestData.getNodeLeaf());

    final Tree<BaseDto, String> tree = getTree(store);
    tree.setAutoLoad(true);
    RootPanel.get().add(tree);

    BaseDto branch = store.findModelWithKey("f-2");

    assertFalse(tree.isLeaf(branch));

    BaseDto leaf = store.findModelWithKey("m-1");
    assertTrue(tree.isLeaf(leaf));

  }

  public void testReplaceChildrenFiltered() {
    final TreeStore<BaseDto> store = getTreeStore(TestData.getMusicRootFolder());
    final Tree<BaseDto, String> tree = getTree(store);

    RootPanel.get().add(tree);

    store.replaceChildren(store.getRootItems().get(0), new ArrayList<BaseDto>());
    BaseDto one = store.getRootItems().get(2);
    store.addFilter(new StoreFilter<BaseDto>() {
      @Override
      public boolean select(Store<BaseDto> store, BaseDto parent, BaseDto item) {
        return (item instanceof MusicDto && ((MusicDto) item).getAuthor().startsWith("B"));
      }
    });
    store.setEnableFilters(true);

    store.replaceChildren(one, new ArrayList<BaseDto>());
  }

  public void testReplaceNodesWhileDetached() {
    final TreeStore<BaseDto> store = getTreeStore(TestData.getMusicRootFolder());
    replaceWithInitialStructure(store);

    final Tree<BaseDto, String> tree = getTree(store);
    tree.setPixelSize(500, 500);

    RootPanel.get().add(tree);
    Scheduler.get().scheduleDeferred(new ScheduledCommand() {
      @Override
      public void execute() {
        tree.removeFromParent();
        replaceWithInitialStructure(store);
        finishTest();
      }
    });
    delayTestFinish(1000);
  }

  public void testSelectionModelEvent() {
    TreeStore<BaseDto> store = getTreeStore(TestData.getMusicRootFolder());
    final Tree<BaseDto, String> tree = getTree(store);
    tree.getSelectionModel().addSelectionChangedHandler(new SelectionChangedHandler<BaseDto>() {
      @Override
      public void onSelectionChanged(SelectionChangedEvent<BaseDto> event) {
        TreeSelectionModel<BaseDto> sm = (TreeSelectionModel<BaseDto>) event.getSource();
        Tree<BaseDto, ?> t = sm.getTree();
        assertEquals(tree, t);
      }
    });
  }

  public void testTitle() {
    assertEquals(TreeTitle, getTree(getTreeStore(TestData.getMusicRootFolder())).getTitle());
  }

  public void testUpdateFilteredModel() {
    final TreeStore<BaseDto> store = getTreeStore(TestData.getMusicRootFolder());
    final Tree<BaseDto, String> tree = getTree(store);

    RootPanel.get().add(tree);
    // tree.expandAll();

    store.addFilter(new StoreFilter<BaseDto>() {
      @Override
      public boolean select(Store<BaseDto> store, BaseDto parent, BaseDto item) {
        return (item instanceof MusicDto && ((MusicDto) item).getAuthor().startsWith("B"));
      }
    });
    store.setEnableFilters(true);

    MusicDto newMusic = new MusicDto(-1l, "test", "genre", "no one");
    store.add(store.getRootItems().get(0), newMusic);

    store.update(newMusic);
  }

  public void testReplaceStore() {
    final TreeStore<BaseDto> initialStore = getTreeStore(TestData.getNodeLeaf());

    final Tree<BaseDto, String> tree = getTree(initialStore);

    //no delays when redrawing so we can test synchronously
    tree.getView().setScrollDelay(0);

    //other tests confirm that the tree behaves when created normally
    RootPanel.get().add(tree);

    //remove it from the dom so we can take away the store
    tree.removeFromParent();

    //remove the store
    tree.setStore(null);

    //make sure it has no store, no contents
    assertNull(tree.getStore());
    assertEquals("", tree.getElement().getInnerText());

    //play with the initial store, be sure tree isn't listening
    assertNull(tree.findNode(initialStore.getRootItems().get(0)));
    tree.setChecked(initialStore.getRootItems().get(0), CheckState.CHECKED);
    assertEquals(0, tree.getCheckedSelection().size());
    initialStore.clear();

    //apply new store
    tree.setStore(getTreeStore(TestData.getNodeLeaf()));

    //add to dom
    RootPanel.get().add(tree);

    //make sure that the items were drawn when we attached
    assertFalse("".equals(tree.getElement().getInnerText()));

    //try a simple operation
    tree.expandAll();

    //replace store without removing
    tree.setStore(new TreeStore<BaseDto>(new KeyProvider()));

    //verify no items in blank store
    assertEquals("", tree.getElement().getInnerText());

    try {
      tree.setStore(null);
      fail("attached tree cannot have store removed");
    } catch (IllegalStateException ex) {
      //expected
    }

    //finally, null again to break leftover tasks, resetting scrolldelay to default
    tree.getView().setScrollDelay(1);
    tree.setStore(getTreeStore(TestData.getNodeLeaf()));
    tree.removeFromParent();
    tree.setStore(null);

    //check for any leftover tasks
    delayTestFinish(1000);
    new Timer() {
      @Override
      public void run() {
        finishTest();
      }
    }.schedule(100);
  }

  public void testUnrenderedSetExpanded() {
    final TreeStore<BaseDto> store = getTreeStore(TestData.getMusicRootFolder());
    FolderDto child = (FolderDto) store.getFirstChild(store.getRootItems().get(0));
    FolderDto grandchild = new FolderDto(-1l, "grandchild");
    store.add(child, grandchild);
    store.add(grandchild, new MusicDto(-2l, "leaf", "", ""));
    final Tree<BaseDto, String> tree = getTree(store);
    tree.getView().setScrollDelay(0);

    RootPanel.get().add(tree);

    tree.setExpanded(grandchild, true);
    assertTrue(tree.isExpanded(store.getParent(grandchild)));
    assertTrue(tree.isExpanded(grandchild));
  }

  public void testUnrenderedIsExpanded() {
    final TreeStore<BaseDto> store = getTreeStore(TestData.getMusicRootFolder());
    FolderDto child = (FolderDto) store.getFirstChild(store.getRootItems().get(0));
    FolderDto grandchild = new FolderDto(-1l, "grandchild");
    store.add(child, grandchild);
    store.add(grandchild, new MusicDto(-2l, "leaf", "", ""));
    final Tree<BaseDto, String> tree = getTree(store);
    tree.getView().setScrollDelay(0);

    RootPanel.get().add(tree);

    assertFalse(tree.isExpanded(grandchild));
  }

  public void testUnrenderedUpdate() {
    final TreeStore<BaseDto> store = getTreeStore(TestData.getMusicRootFolder());
    FolderDto child = (FolderDto) store.getFirstChild(store.getRootItems().get(0));
    FolderDto grandchild = new FolderDto(-1l, "grandchild");
    store.add(child, grandchild);
    store.add(grandchild, new MusicDto(-2l, "leaf", "", ""));
    final Tree<BaseDto, String> tree = getTree(store);
    tree.getView().setScrollDelay(0);

    RootPanel.get().add(tree);

    store.update(grandchild);
  }

  public void testInitialCollapseAll() {
    final TreeStore<BaseDto> initialStore = getTreeStore(TestData.getNodeLeaf());
    final Tree<BaseDto, String> tree = getTree(initialStore);
    tree.getView().setScrollDelay(0);
    RootPanel.get().add(tree);
    tree.collapseAll();
  }
  public void testInitialExpandAll() {
    final TreeStore<BaseDto> initialStore = getTreeStore(TestData.getNodeLeaf());
    final Tree<BaseDto, String> tree = getTree(initialStore);
    tree.getView().setScrollDelay(0);
    RootPanel.get().add(tree);
    tree.expandAll();
  }

  public void testDetachedClear() {
    TreeStore<BaseDto> store = getTreeStore(TestData.getNodeLeaf());

    //clear tree itself
    Tree<BaseDto, String> tree = getTree(store);
    tree.clear();

    //clear store
    tree = getTree(store);
    store.clear();

    //attach then detach and clear tree
    store = getTreeStore(TestData.getNodeLeaf());
    tree = getTree(store);
    RootPanel.get().add(tree);
    tree.removeFromParent();
    tree.clear();

    //attach then deatch and clear tree
    tree = getTree(store);
    RootPanel.get().add(tree);
    tree.removeFromParent();
    store.clear();

    //same as last two, but with a delay to let any other bookkeeping work
    final TreeStore<BaseDto> asyncStore1 = getTreeStore(TestData.getNodeLeaf());
    final Tree<BaseDto, String> asyncTree1 = getTree(asyncStore1);
    RootPanel.get().add(asyncTree1);

    final TreeStore<BaseDto> asyncStore2 = getTreeStore(TestData.getNodeLeaf());
    final Tree<BaseDto, String> asyncTree2 = getTree(asyncStore2);
    RootPanel.get().add(asyncTree2);

    Scheduler.get().scheduleDeferred(new ScheduledCommand() {
      @Override
      public void execute() {
        asyncTree1.removeFromParent();
        asyncTree2.removeFromParent();
        Scheduler.get().scheduleDeferred(new ScheduledCommand() {
          @Override
          public void execute() {
            asyncTree1.clear();
            asyncStore2.clear();
            finishTest();
          }
        });
      }
    });
    delayTestFinish(1000);
  }

  private void clearSequence() {
    seq = -1;
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

  private TreeStore<BaseDto> getTreeStoreEscaped(BaseDto data) {
    TreeStore<BaseDto> store = new TreeStore<BaseDto>(new EscapedKeyProvider());

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

  private void replaceWithInitialStructure(TreeStore<BaseDto> store) {
    store.clear();
    FolderDto root = TestData.getMusicRootFolder();
    for (BaseDto base : root.getChildren()) {
      store.add(base);
      if (base instanceof FolderDto) {
        processFolder(store, (FolderDto) base);
      }
    }
  }
}
