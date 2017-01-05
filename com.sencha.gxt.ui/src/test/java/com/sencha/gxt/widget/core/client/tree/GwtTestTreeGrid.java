package com.sencha.gxt.widget.core.client.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.junit.DoNotRunWith;
import com.google.gwt.junit.Platform;
import com.google.gwt.user.client.ui.RootPanel;
import com.sencha.gxt.core.client.ToStringValueProvider;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.TreeStore;
import com.sencha.gxt.ui.resources.client.TestData;
import com.sencha.gxt.ui.resources.shared.BaseDto;
import com.sencha.gxt.ui.resources.shared.FolderDto;
import com.sencha.gxt.ui.resources.shared.MusicDto;
import com.sencha.gxt.widget.core.client.CoreBaseTestCase;
import com.sencha.gxt.widget.core.client.event.ViewReadyEvent;
import com.sencha.gxt.widget.core.client.event.ViewReadyEvent.ViewReadyHandler;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.selection.SelectionChangedEvent;
import com.sencha.gxt.widget.core.client.selection.SelectionChangedEvent.SelectionChangedHandler;
import com.sencha.gxt.widget.core.client.treegrid.TreeGrid;
import com.sencha.gxt.widget.core.client.treegrid.TreeGridSelectionModel;

public class GwtTestTreeGrid extends CoreBaseTestCase {
  class KeyProvider implements ModelKeyProvider<BaseDto> {
    @Override
    public String getKey(BaseDto item) {
      return (item instanceof FolderDto ? "f-" : "m-") + item.getId().toString();
    }
  }

  public void testSimpleTreeGrid() {
    TreeStore<BaseDto> treeStore = getTreeStore(TestData.getMusicRootFolder());

    ArrayList<ColumnConfig<BaseDto, ?>> columns = new ArrayList<ColumnConfig<BaseDto, ?>>();
    ColumnConfig<BaseDto, String> column = new ColumnConfig<BaseDto, String>(new ToStringValueProvider<BaseDto>(""));
    columns.add(column);

    TreeGrid<BaseDto> tree = new TreeGrid<BaseDto>(treeStore, new ColumnModel<BaseDto>(columns), column);
    delayTestFinish(2000);
    tree.addViewReadyHandler(new ViewReadyHandler() {
      @Override
      public void onViewReady(ViewReadyEvent event) {
        finishTest();
      }
    });
    RootPanel.get().add(tree);
  }

  public void testSelectionModelEvent() {
    TreeStore<BaseDto> treeStore = getTreeStore(TestData.getMusicRootFolder());

    ArrayList<ColumnConfig<BaseDto, ?>> columns = new ArrayList<ColumnConfig<BaseDto, ?>>();
    ColumnConfig<BaseDto, String> column = new ColumnConfig<BaseDto, String>(new ToStringValueProvider<BaseDto>(""));
    columns.add(column);

    final TreeGrid<BaseDto> tree = new TreeGrid<BaseDto>(treeStore, new ColumnModel<BaseDto>(columns), column);

    tree.getSelectionModel().addSelectionChangedHandler(new SelectionChangedHandler<BaseDto>() {
      @Override
      public void onSelectionChanged(SelectionChangedEvent<BaseDto> event) {
        TreeGridSelectionModel<BaseDto> sm = (TreeGridSelectionModel<BaseDto>) event.getSource();
        assertNotNull(sm.getGrid());
        assertEquals(tree, sm.getTreeGrid());
      }
    });
  }

  public void testUpdateUnexpandedNodeTreeGrid() {
    final FolderDto f1 = new FolderDto(1l, "Folder 1");
    final FolderDto f2 = new FolderDto(2l, "Folder 2");
    final TreeStore<BaseDto> treeStore = new TreeStore<BaseDto>(new KeyProvider());
    treeStore.add(f1);
    treeStore.add(f1, f2);

    ArrayList<ColumnConfig<BaseDto, ?>> columns = new ArrayList<ColumnConfig<BaseDto, ?>>();
    ColumnConfig<BaseDto, String> column = new ColumnConfig<BaseDto, String>(new ToStringValueProvider<BaseDto>());
    columns.add(column);

    TreeGrid<BaseDto> tree = new TreeGrid<BaseDto>(treeStore, new ColumnModel<BaseDto>(columns), column);
    delayTestFinish(2000);

    tree.addViewReadyHandler(new ViewReadyHandler() {
      @Override
      public void onViewReady(ViewReadyEvent event) {
        try {
          treeStore.update(new FolderDto(2l, "new item"));
          assertEquals("new item", treeStore.getChildren(f1).get(0).getName());
          finishTest();
        } catch (Exception ex) {
          fail(ex.getMessage());
        }
      }
    });
    RootPanel.get().add(tree);
  }

  /**
   * Tests a specific error scenario that is currently not reproducible with HtmlUnit. In order to run this test, add
   * the following to VM arguments for the test case (e.g. in the VM Arguments section of the Arguments tab of the
   * Eclipse launch configuration. Once launched browse to the URL that appears in the console window).
   * 
   * <pre>
   * -Dgwt.args="-runStyle Manual:1"
   * </pre>
   */
  @DoNotRunWith(Platform.HtmlUnitUnknown)
  public void testExpandInsertedNotFirstNotLastNoChildrenLeafTreeGrid() {
    final FolderDto f1 = new FolderDto(1l, "Folder 1");
    final FolderDto f2 = new FolderDto(2l, "Folder 2");
    final FolderDto f3 = new FolderDto(3l, "Folder 3");
    TreeStore<BaseDto> treeStore = new TreeStore<BaseDto>(new KeyProvider());
    treeStore.add(f1);
    treeStore.add(f1, f2);
    treeStore.add(f3);

    ArrayList<ColumnConfig<BaseDto, ?>> columns = new ArrayList<ColumnConfig<BaseDto, ?>>();
    ColumnConfig<BaseDto, String> column = new ColumnConfig<BaseDto, String>(new ToStringValueProvider<BaseDto>(""));
    columns.add(column);

    final TreeGrid<BaseDto> tree = new TreeGrid<BaseDto>(treeStore, new ColumnModel<BaseDto>(columns), column) {
      @Override
      protected boolean hasChildren(BaseDto model) {
        return true;
      }
    };
    delayTestFinish(2000);

    tree.addViewReadyHandler(new ViewReadyHandler() {
      @Override
      public void onViewReady(ViewReadyEvent event) {
        try {
          tree.setExpanded(f1, true);
          tree.setExpanded(f2, true);
          finishTest();
        } catch (Exception ex) {
          fail(ex.getMessage());
        }
      }
    });
    RootPanel.get().add(tree);
  }

  public void testAddItemOrder() {
    TreeStore<BaseDto> treeStore = new TreeStore<BaseDto>(new KeyProvider());
    ArrayList<ColumnConfig<BaseDto, ?>> columns = new ArrayList<ColumnConfig<BaseDto, ?>>();
    ColumnConfig<BaseDto, String> column = new ColumnConfig<BaseDto, String>(new ToStringValueProvider<BaseDto>(""));
    columns.add(column);
    TreeGrid<BaseDto> tree = new TreeGrid<BaseDto>(treeStore, new ColumnModel<BaseDto>(columns), column) {
      @Override
      protected boolean hasChildren(BaseDto model) {
        return true;
      }
    };
    tree.setLazyRowRender(0);
    RootPanel.get().add(tree);

    ListStore<BaseDto> listStore = tree.getStore();

    // first batch of items to add
    FolderDto b = new FolderDto(1l, "b");// initial root
    FolderDto bb = new FolderDto(2l, "bb");// append 0 child
    FolderDto bba = new FolderDto(3l, "bba");// insert 0 child
    FolderDto bd = new FolderDto(4l, "bd");// append child
    FolderDto bc = new FolderDto(5l, "bc");// insert child
    // FolderDto ab = new FolderDto(6l, "ab");
    FolderDto a = new FolderDto(7l, "a");// insert 0 root
    FolderDto c = new FolderDto(8l, "c");// insert root
    FolderDto d = new FolderDto(9l, "d");// append root
    List<FolderDto> ordered = Arrays.asList(a, b, bb, bba, bc, bd, c, d);

    // insert these items, expanding as we go
    treeStore.add(b);
    tree.setExpanded(b, true);
    treeStore.add(b, bb);
    tree.setExpanded(bb, true);
    treeStore.insert(bb, 0, bba);
    treeStore.add(b, bd);
    treeStore.insert(b, 1, bc);
    treeStore.insert(0, a);
    treeStore.insert(2, c);
    treeStore.add(d);
    assertEquals(ordered, listStore.getAll());

    // finally, append children to collapsed parents
    FolderDto ab = new FolderDto(10l, "ab");// append 0 child
    FolderDto ad = new FolderDto(11l, "ad");// append child
    FolderDto aa = new FolderDto(12l, "aa");// insert 0 child
    FolderDto ac = new FolderDto(13l, "ac");// insert child

    treeStore.add(a, ab);
    treeStore.add(a, ad);
    treeStore.insert(a, 0, aa);
    treeStore.insert(a, 2, ac);

    // visible items should be unchanged, since a is collapsed
    assertEquals(ordered, listStore.getAll());
  }

  public void testReplaceItemsOrder() {
    TreeStore<BaseDto> treeStore = new TreeStore<BaseDto>(new KeyProvider());
    ArrayList<ColumnConfig<BaseDto, ?>> columns = new ArrayList<ColumnConfig<BaseDto, ?>>();
    ColumnConfig<BaseDto, String> column = new ColumnConfig<BaseDto, String>(new ToStringValueProvider<BaseDto>(""));
    columns.add(column);
    TreeGrid<BaseDto> tree = new TreeGrid<BaseDto>(treeStore, new ColumnModel<BaseDto>(columns), column) {
      @Override
      protected boolean hasChildren(BaseDto model) {
        return true;
      }
    };
    tree.setLazyRowRender(0);
    RootPanel.get().add(tree);

    ListStore<BaseDto> listStore = tree.getStore();

    FolderDto a = new FolderDto(1l, "a");
    FolderDto b = new FolderDto(2l, "b");
    FolderDto c = new FolderDto(3l, "c");
    FolderDto aa = new FolderDto(4l, "aa");
    FolderDto ab = new FolderDto(5l, "ab");
    FolderDto ac = new FolderDto(6l, "ac");
    List<FolderDto> ordered = Arrays.asList(a, aa, ab, ac, b, c);

    treeStore.add(Arrays.<BaseDto> asList(a, b, c));
    tree.setExpanded(a, true);
    treeStore.add(a, TestData.getMusicRootFolder().getChildren());

    treeStore.replaceChildren(a, Arrays.<BaseDto> asList(aa, ab, ac));
    assertEquals(Arrays.<BaseDto> asList(a, b, c), listStore.getAll());

    tree.expandAll();
    assertEquals(ordered, listStore.getAll());
  }

  public void testExpandAllCollapseAll() {
    TreeStore<BaseDto> treeStore = getTreeStore(TestData.getMusicRootFolder());

    ArrayList<ColumnConfig<BaseDto, ?>> columns = new ArrayList<ColumnConfig<BaseDto, ?>>();
    ColumnConfig<BaseDto, String> column = new ColumnConfig<BaseDto, String>(new ToStringValueProvider<BaseDto>(""));
    columns.add(column);

    TreeGrid<BaseDto> tree = new TreeGrid<BaseDto>(treeStore, new ColumnModel<BaseDto>(columns), column);
    tree.setLazyRowRender(0);
    RootPanel.get().add(tree);

    ListStore<BaseDto> listStore = tree.getStore();

    assertEquals(TestData.getMusicRootFolder().getChildren().size(), listStore.size());

    tree.expandAll();

    assertEquals(treeStore.getAllItemsCount(), listStore.size());

    tree.collapseAll();

    assertEquals(TestData.getMusicRootFolder().getChildren().size(), listStore.size());
  }

  public void testInitialCollapseAll() {
    final TreeStore<BaseDto> initialStore = getTreeStore(TestData.getNodeLeaf());
    ArrayList<ColumnConfig<BaseDto, ?>> columns = new ArrayList<ColumnConfig<BaseDto, ?>>();
    ColumnConfig<BaseDto, String> column = new ColumnConfig<BaseDto, String>(new ToStringValueProvider<BaseDto>(""));
    columns.add(column);

    TreeGrid<BaseDto> tree = new TreeGrid<BaseDto>(initialStore, new ColumnModel<BaseDto>(columns), column);
    tree.setLazyRowRender(0);
    RootPanel.get().add(tree);

    tree.collapseAll();
  }


  public void testCollapseNoChildren() {
    TreeStore<BaseDto> treeStore = getTreeStore(TestData.getMusicRootFolder());
    ArrayList<ColumnConfig<BaseDto, ?>> columns = new ArrayList<ColumnConfig<BaseDto, ?>>();
    ColumnConfig<BaseDto, String> column = new ColumnConfig<BaseDto, String>(new ToStringValueProvider<BaseDto>(""));
    columns.add(column);
    TreeGrid<BaseDto> tree = new TreeGrid<BaseDto>(treeStore, new ColumnModel<BaseDto>(columns), column) {
      @Override
      protected boolean hasChildren(BaseDto model) {
        return true;
      }
    };
    tree.setLazyRowRender(0);
    RootPanel.get().add(tree);

    tree.expandAll();

    treeStore.removeChildren(treeStore.getChild(0));

    tree.collapseAll();
  }

  public void testReplaceNodesWhileDetached() {
    final TreeStore<BaseDto> treeStore = getTreeStore(TestData.getMusicRootFolder());
    replaceWithInitialStructure(treeStore);

    ArrayList<ColumnConfig<BaseDto, ?>> columns = new ArrayList<ColumnConfig<BaseDto, ?>>();
    ColumnConfig<BaseDto, String> column = new ColumnConfig<BaseDto, String>(new ToStringValueProvider<BaseDto>(""),
        200, "Name");
    columns.add(column);

    final TreeGrid<BaseDto> tree = new TreeGrid<BaseDto>(treeStore, new ColumnModel<BaseDto>(columns), column);
    tree.setPixelSize(500, 500);

    RootPanel.get().add(tree);
    Scheduler.get().scheduleDeferred(new ScheduledCommand() {
      @Override
      public void execute() {
        tree.removeFromParent();
        replaceWithInitialStructure(treeStore);
        finishTest();
      }
    });
    delayTestFinish(1000);
  }



  public void testUnrenderedSetExpanded() {
    final TreeStore<BaseDto> store = getTreeStore(TestData.getMusicRootFolder());
    FolderDto child = (FolderDto) store.getFirstChild(store.getRootItems().get(0));
    FolderDto grandchild = new FolderDto(-1l, "grandchild");
    store.add(child, grandchild);
    store.add(grandchild, new MusicDto(-2l, "leaf", "", ""));
    ArrayList<ColumnConfig<BaseDto, ?>> columns = new ArrayList<ColumnConfig<BaseDto, ?>>();
    ColumnConfig<BaseDto, String> column = new ColumnConfig<BaseDto, String>(new ToStringValueProvider<BaseDto>(""),
            200, "Name");
    columns.add(column);

    final TreeGrid<BaseDto> tree = new TreeGrid<BaseDto>(store, new ColumnModel<BaseDto>(columns), column);
    tree.setPixelSize(500, 500);
    tree.setLazyRowRender(0);

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
    ArrayList<ColumnConfig<BaseDto, ?>> columns = new ArrayList<ColumnConfig<BaseDto, ?>>();
    ColumnConfig<BaseDto, String> column = new ColumnConfig<BaseDto, String>(new ToStringValueProvider<BaseDto>(""),
            200, "Name");
    columns.add(column);

    final TreeGrid<BaseDto> tree = new TreeGrid<BaseDto>(store, new ColumnModel<BaseDto>(columns), column);
    tree.setPixelSize(500, 500);
    tree.setLazyRowRender(0);

    RootPanel.get().add(tree);

    assertFalse(tree.isExpanded(grandchild));
  }

  public void testUnrenderedUpdate() {
    final TreeStore<BaseDto> store = getTreeStore(TestData.getMusicRootFolder());
    FolderDto child = (FolderDto) store.getFirstChild(store.getRootItems().get(0));
    FolderDto grandchild = new FolderDto(-1l, "grandchild");
    store.add(child, grandchild);
    store.add(grandchild, new MusicDto(-2l, "leaf", "", ""));
    ArrayList<ColumnConfig<BaseDto, ?>> columns = new ArrayList<ColumnConfig<BaseDto, ?>>();
    ColumnConfig<BaseDto, String> column = new ColumnConfig<BaseDto, String>(new ToStringValueProvider<BaseDto>(""),
            200, "Name");
    columns.add(column);

    final TreeGrid<BaseDto> tree = new TreeGrid<BaseDto>(store, new ColumnModel<BaseDto>(columns), column);
    tree.setPixelSize(500, 500);
    tree.setLazyRowRender(0);

    RootPanel.get().add(tree);

    store.update(grandchild);
  }

  // This is used when the treegrid is built the first time - standard setup
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

  private TreeStore<BaseDto> getTreeStore(BaseDto data) {
    TreeStore<BaseDto> store = new TreeStore<BaseDto>(new KeyProvider());

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
