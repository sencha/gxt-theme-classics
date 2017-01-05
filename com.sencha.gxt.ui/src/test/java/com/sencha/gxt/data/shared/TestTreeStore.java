package com.sencha.gxt.data.shared;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import junit.framework.Assert;
import junit.framework.TestCase;

import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.Store.StoreFilter;
import com.sencha.gxt.data.shared.Store.StoreSortInfo;
import com.sencha.gxt.data.shared.TreeStore.TreeNode;
import com.sencha.gxt.data.shared.event.StoreAddEvent;
import com.sencha.gxt.data.shared.event.StoreAddEvent.StoreAddHandler;
import com.sencha.gxt.data.shared.event.StoreDataChangeEvent;
import com.sencha.gxt.data.shared.event.StoreDataChangeEvent.StoreDataChangeHandler;
import com.sencha.gxt.data.shared.event.StoreUpdateEvent;
import com.sencha.gxt.data.shared.event.StoreUpdateEvent.StoreUpdateHandler;

public class TestTreeStore extends TestCase {
  private static class Data {
    private static int nextId = 1;
    public final String id = String.valueOf(nextId++);
    private String name;

    public Data(String name) {
      this.name = name;
    }

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }
  }
  private static class DataKeyProvider implements ModelKeyProvider<Data> {
    @Override
    public String getKey(Data item) {
      return item.id;
    }
  }
  @SuppressWarnings("unused")
  private static class NameValueProvider implements ValueProvider<Data, String> {
    public static final NameValueProvider instance = new NameValueProvider();

    @Override
    public String getValue(Data object) {
      return object.getName();
    }

    @Override
    public void setValue(Data object, String value) {
      object.setName(value);
    }

    @Override
    public String getPath() {
      return "name";
    }
  }

  private static class ReportingStoreAddHandler<M> implements StoreAddHandler<M> {
    private List<M> items;

    @Override
    public void onAdd(StoreAddEvent<M> event) {
      items = event.getItems();
    }

    public List<M> getLatestItems() {
      return items;
    }
  }

  public void testAddNodes() {
    TreeStore<Data> treeStore = new TreeStore<Data>(new DataKeyProvider());

    Data root = new Data("root");
    Data parent = new Data("parent");
    Data child = new Data("child");

    treeStore.add(root);
    treeStore.add(root, parent);
    treeStore.add(parent, child);

    Assert.assertEquals(parent, treeStore.getParent(child));
    Assert.assertEquals(root, treeStore.getParent(parent));
    Assert.assertEquals(3, treeStore.getDepth(child));
  }

  public void testAddAllNodes() {
    TreeStore<Data> treeStore = new TreeStore<Data>(new DataKeyProvider());

    Data root = new Data("root");
    Data parent = new Data("parent");
    Data parent2 = new Data("parent 2");
    Data child = new Data("child");
    Data child2 = new Data("child 2");

    treeStore.add(root);
    treeStore.add(root, Arrays.asList(parent, parent2));
    treeStore.add(parent, Arrays.asList(child, child2));

    Assert.assertEquals(parent, treeStore.getParent(child));
    Assert.assertEquals(parent, treeStore.getParent(child2));
    Assert.assertEquals(root, treeStore.getParent(parent));
    Assert.assertEquals(root, treeStore.getParent(parent2));
    Assert.assertEquals(3, treeStore.getDepth(child));
  }

  public void testRemoveNodes() {
    TreeStore<Data> treeStore = new TreeStore<Data>(new DataKeyProvider());

    Data root = new Data("root");
    Data parent = new Data("parent");
    Data child = new Data("child");

    treeStore.add(root);
    treeStore.add(root, parent);
    treeStore.add(parent, child);

    treeStore.remove(parent);

    Assert.assertFalse(treeStore.hasChildren(root));
  }

  public void testRemoveChildren() {
    TreeStore<Data> treeStore = new TreeStore<Data>(new DataKeyProvider());

    Data root = new Data("root");
    Data parent = new Data("parent");
    Data child = new Data("child");

    treeStore.add(root);
    treeStore.add(root, parent);
    treeStore.add(parent, child);

    treeStore.removeChildren(parent);

    Assert.assertFalse(treeStore.hasChildren(parent));
    Assert.assertEquals(0, treeStore.getChildCount(parent));
    Assert.assertEquals(2, treeStore.getAllItemsCount());

    treeStore.add(parent, child);

    treeStore.removeChildren(root);

    Assert.assertFalse(treeStore.hasChildren(root));
    Assert.assertEquals(0, treeStore.getChildCount(root));
    Assert.assertEquals(1, treeStore.getAllItemsCount());
  }

  public void testRearrangeNodes() {
    TreeStore<Data> treeStore = new TreeStore<Data>(new DataKeyProvider());
    Data root = new Data("root");
    Data parent = new Data("parent");
    Data child = new Data("child");
    treeStore.add(root);
    treeStore.add(root, parent);
    treeStore.add(parent, child);

    treeStore.remove(child);
    Assert.assertFalse(treeStore.hasChildren(parent));

    treeStore.add(root, child);
    Assert.assertEquals(2, treeStore.getChildCount(root));

    treeStore.remove(parent);
    Assert.assertEquals(1, treeStore.getChildCount(root));

    treeStore.add(child, parent);
    Assert.assertEquals(3, treeStore.getDepth(parent));
  }

  public void testGetAllItems() {
    TreeStore<Data> treeStore = new TreeStore<Data>(new DataKeyProvider());
    Data root = new Data("root");
    Data parent = new Data("parent");
    Data child = new Data("child");
    treeStore.add(root);
    treeStore.add(root, parent);
    treeStore.add(parent, child);

    List<Data> all = treeStore.getAll();
    Assert.assertEquals(3, all.size());
    Assert.assertTrue(all.contains(root));
    Assert.assertTrue(all.contains(parent));
    Assert.assertTrue(all.contains(child));
  }

  public void testGetAllChildren() {
    TreeStore<Data> treeStore = new TreeStore<Data>(new DataKeyProvider());
    Data root = new Data("root");
    Data parent = new Data("parent");
    Data child = new Data("child");
    treeStore.add(root);
    treeStore.add(root, parent);
    treeStore.add(parent, child);

    Assert.assertEquals(0, treeStore.getAllChildren(child).size());
    Assert.assertEquals(1, treeStore.getAllChildren(parent).size());
    Assert.assertEquals(child, treeStore.getAllChildren(parent).get(0));

    Assert.assertEquals(2, treeStore.getAllChildren(root).size());
  }

  public void testFilters() {
    TreeStore<Data> treeStore = new TreeStore<Data>(new DataKeyProvider());

    Data root = new Data("root");
    Data parent = new Data("parent");
    Data child = new Data("child");

    treeStore.add(root);
    treeStore.add(root, parent);
    treeStore.add(parent, child);

    treeStore.addFilter(new StoreFilter<Data>() {
      @Override
      public boolean select(Store<Data> store, Data parent, Data item) {
        return item.getName().startsWith("p");
      }
    });
    treeStore.setEnableFilters(true);

    Assert.assertEquals(2, treeStore.getAllItemsCount());
    Assert.assertEquals(1, treeStore.getChildCount(root));
    Assert.assertEquals(0, treeStore.getChildCount(parent));

    treeStore.setEnableFilters(false);

    Assert.assertEquals(3, treeStore.getAllItemsCount());
    Assert.assertEquals(1, treeStore.getChildCount(parent));
  }

  public void testFilterNothing() {
    TreeStore<Data> treeStore = new TreeStore<Data>(new DataKeyProvider());
    Data root = new Data("root");
    Data parent = new Data("parent");
    Data child = new Data("child");

    treeStore.add(root);
    treeStore.add(root, parent);
    treeStore.add(parent, child);

    treeStore.addFilter(new StoreFilter<Data>() {
      @Override
      public boolean select(Store<Data> store, Data parent, Data item) {
        return false;
      }
    });

    treeStore.setEnableFilters(true);
    Assert.assertEquals(0, treeStore.getAllItemsCount());
  }

  public void testFilterShowOne() {
    TreeStore<Data> treeStore = new TreeStore<Data>(new DataKeyProvider());
    Data root = new Data("root");
    Data parent = new Data("parent");
    Data child = new Data("child");

    treeStore.add(root);
    treeStore.add(root, parent);
    treeStore.add(parent, child);

    treeStore.addFilter(new StoreFilter<Data>() {
      @Override
      public boolean select(Store<Data> store, Data parent, Data item) {
        return item.getName().equals("last");
      }
    });

    treeStore.setEnableFilters(true);

    treeStore.add(parent, new Data("last"));

    Assert.assertEquals(3, treeStore.getAllItemsCount());
  }

  public void testFilterAdd() {
    TreeStore<Data> treeStore = new TreeStore<Data>(new DataKeyProvider());

    Data root = new Data("root");
    Data parent = new Data("parent");
    Data child = new Data("child");

    treeStore.add(root);
    treeStore.add(root, parent);
    treeStore.add(parent, child);

    treeStore.addFilter(new StoreFilter<Data>() {
      @Override
      public boolean select(Store<Data> store, Data parent, Data item) {
        return item.getName().contains("p");
      }
    });
    treeStore.setEnableFilters(true);

    // child isn't visible, all others are

    // add a visible item to a visible parent
    Data visible1 = new Data("asdf_p_asdf");
    treeStore.add(root, visible1);
    Assert.assertEquals(3, treeStore.getAllItemsCount());

    // add a invisible item to an invisible parent
    Data invisible = new Data("asdf__asdf2");
    treeStore.add(child, invisible);
    Assert.assertEquals(3, treeStore.getAllItemsCount());

    // add a visible item to an invisible parent with an already invisible child
    Data visible2 = new Data("asdf_p_asdf3");
    treeStore.add(child, visible2);
    Assert.assertEquals(5, treeStore.getAllItemsCount());

    // turn off filters, make sure everything is still there
    treeStore.setEnableFilters(false);

    Assert.assertEquals(6, treeStore.getAllItemsCount());
  }

  public void testFiltersEnabledNoFilters() {
    TreeStore<Data> treeStore = new TreeStore<Data>(new DataKeyProvider());
    treeStore.setEnableFilters(true);

    Data root = new Data("root");
    Data parent = new Data("parent");
    Data child = new Data("child");
    Data child2 = new Data("child2");

    treeStore.add(root);
    treeStore.add(root, parent);
    treeStore.add(parent, child);
    treeStore.add(parent, child2);

    treeStore.add(child, Arrays.asList(new Data("one"), new Data("two")));

    treeStore.remove(child2);

    treeStore.replaceSubTree(child, Arrays.asList(new TreeNode<Data>() {
      Data wrapped = new Data("subtree");
      @Override
      public List<? extends TreeNode<Data>> getChildren() {
        return new ArrayList<TreeNode<Data>>();
      }
      @Override
      public Data getData() {
        return wrapped;
      }
    }));
    assertEquals(4, treeStore.getAllItemsCount());

    treeStore.addFilter(new StoreFilter<Data>() {
      @Override
      public boolean select(Store<Data> store, Data parent, Data item) {
        return "child".equals(item.getName());
      }
    });
    assertEquals(3, treeStore.getAllItemsCount());

    treeStore.clear();
    assertEquals(0, treeStore.getAllItemsCount());
  }

  public void testFilterAddAll() {
    TreeStore<Data> treeStore = new TreeStore<Data>(new DataKeyProvider());

    Data root = new Data("root");
    Data parent = new Data("parent");
    Data child = new Data("child");

    treeStore.add(root);
    treeStore.add(root, parent);
    treeStore.add(parent, child);

    treeStore.addFilter(new StoreFilter<Data>() {
      @Override
      public boolean select(Store<Data> store, Data parent, Data item) {
        return item.getName().contains("p");
      }
    });
    treeStore.setEnableFilters(true);

    // child isn't visible, all others are

    // add all visible items to a visible parent
    Data visible1 = new Data("asdf_p_asdf");
    treeStore.add(root, Arrays.asList(visible1));
    Assert.assertEquals(3, treeStore.getAllItemsCount());

    // add all invisible items to an invisible parent
    Data invisible = new Data("asdf__asdf2");
    treeStore.add(child, Arrays.asList(invisible));
    Assert.assertEquals(3, treeStore.getAllItemsCount());

    // add a visible item to an invisible parent with an already invisible child
    Data visible2 = new Data("asdf_p_asdf3");
    treeStore.add(child, Arrays.asList(visible2));
    Assert.assertEquals(5, treeStore.getAllItemsCount());

    // turn off filters, make sure everything is still there
    treeStore.setEnableFilters(false);

    Assert.assertEquals(6, treeStore.getAllItemsCount());
  }

  public void testFilterReplaceAll() {
    TreeStore<Data> treeStore = new TreeStore<Data>(new DataKeyProvider());

    Data root = new Data("root");
    Data child;

    treeStore.add(root);

    treeStore.addFilter(new StoreFilter<Data>() {
      @Override
      public boolean select(Store<Data> store, Data parent, Data item) {
        return item.getName().contains("v");
      }
    });
    treeStore.setEnableFilters(true);

    // parent not visible and empty, add visible child
    treeStore.replaceChildren(root, Arrays.asList(new Data("visible children")));
    Assert.assertEquals(2, treeStore.getAllItemsCount());

    // parent not visible by itself, replace visible children with non-visible
    treeStore.replaceChildren(root, Arrays.asList(new Data("not _isible")));
    Assert.assertEquals(0, treeStore.getAllItemsCount());

    //parent not visible, replace invisible children with visible children
    treeStore.replaceChildren(root, Arrays.asList(child = new Data("visible again")));
    Assert.assertEquals(2, treeStore.getAllItemsCount());

    //parent visible and empty, add visible child
    treeStore.replaceChildren(child, Arrays.asList(new Data("child visible")));
    Assert.assertEquals(3, treeStore.getAllItemsCount());

    //parent visible, replace visible children with non-visible
    treeStore.replaceChildren(child, Arrays.asList(new Data("in_isible child")));
    Assert.assertEquals(2, treeStore.getAllItemsCount());

    //parent visible, replace non-visible children with visible children
    treeStore.replaceChildren(child, Arrays.asList(new Data("child visible again")));
    Assert.assertEquals(3, treeStore.getAllItemsCount());
  }

  public void testFilterRemove() {
    TreeStore<Data> treeStore = new TreeStore<Data>(new DataKeyProvider());

    Data root = new Data("root");
    Data parent = new Data("parent");
    Data child = new Data("child");

    treeStore.add(root);
    treeStore.add(root, parent);
    treeStore.add(parent, child);

    treeStore.addFilter(new StoreFilter<Data>() {
      @Override
      public boolean select(Store<Data> store, Data parent, Data item) {
        return item.getName().contains("p");
      }
    });

    Data visible1 = new Data("asdf_p_asdf");
    Data visible2 = new Data("asdfasdf_ppp_asdf");
    Data notVisible = new Data("");

    // visible parent, visible child
    treeStore.add(parent, visible1);

    // invisible parent, visible child (so both are visible)
    treeStore.add(child, visible2);

    // visible parent, invisible child
    treeStore.add(root, notVisible);

    treeStore.setEnableFilters(true);

    // test removing a visible child
    treeStore.remove(visible1);
    Assert.assertEquals(4, treeStore.getAllItemsCount());

    // test removing the otherwise not visible parent of a visible child
    treeStore.remove(child);
    Assert.assertEquals(2, treeStore.getAllItemsCount());

    // test removing an invisible child
    treeStore.remove(notVisible);
    Assert.assertEquals(2, treeStore.getAllItemsCount());

    // test removing a root node
    treeStore.remove(root);
    Assert.assertEquals(0, treeStore.getAllItemsCount());
  }

  public class DataNode implements TreeStore.TreeNode<Data> {
    private List<DataNode> children;
    private Data data;

    public DataNode(Data data, DataNode... children) {
      this.data = data;
      this.children = Arrays.asList(children);
    }

    @Override
    public List<? extends TreeNode<Data>> getChildren() {
      return children;
    }

    @Override
    public Data getData() {
      return data;
    }
  }

  public void testBuildWithExternalNodes() {
    Data root = new Data("root");
    Data parent = new Data("parent");
    Data child = new Data("child");

    DataNode childNode = new DataNode(child);
    DataNode parentNode = new DataNode(parent, childNode);
    DataNode rootNode = new DataNode(root, parentNode);

    TreeStore<Data> treeStore = new TreeStore<Data>(new DataKeyProvider());

    treeStore.addSubTree(0, Arrays.asList(rootNode));

    Assert.assertEquals(parent, treeStore.getParent(child));
    Assert.assertEquals(root, treeStore.getParent(parent));
    Assert.assertEquals(3, treeStore.getDepth(child));
  }

  public class DataTree extends Data implements TreeStore.TreeNode<DataTree> {
    private List<DataTree> children;

    public DataTree(String name, DataTree... children) {
      super(name);
      this.children = Arrays.asList(children);
    }

    @Override
    public List<? extends TreeNode<DataTree>> getChildren() {
      return children;
    }

    @Override
    public DataTree getData() {
      return this;
    }
  }

  public void testBuildTreeWithNodes() {
    DataTree child = new DataTree("child");
    DataTree parent = new DataTree("parent", child);
    DataTree root = new DataTree("root", parent);

    TreeStore<DataTree> treeStore = new TreeStore<DataTree>(new DataKeyProvider());

    treeStore.addSubTree(0, Arrays.asList(root));

    Assert.assertEquals(parent, treeStore.getParent(child));
    Assert.assertEquals(root, treeStore.getParent(parent));
    Assert.assertEquals(3, treeStore.getDepth(child));
  }

  class Stock {
    public Stock(String key, String name, int sortindex) {
      this.key = key;
      this.name = name;
      this.sortindex = sortindex;
    }

    int sortindex = 0;
    String key = "";
    String name = "";

    public String getKey() {
      return key;
    }

    public void setKey(String key) {
      this.key = key;
    }

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public int getSortindex() {
      return sortindex;
    }

    public void setSortindex(int sortindex) {
      this.sortindex = sortindex;
    }

    public String toString() {
      return "<Stock: " + key + ">";
    }
  }

  public void testAddItemsToSortedTree() {
    // User-submitted test case
    // EXTGWT-1244
    //

    TreeStore<Stock> store = new TreeStore<Stock>(new ModelKeyProvider<Stock>() {
      @Override
      public String getKey(Stock item) {
        return item.getKey();
      }
    });

    store.addSortInfo(new StoreSortInfo<Stock>(new ValueProvider<Stock, Integer>() {
      @Override
      public String getPath() {
        return "sortindex";
      }

      @Override
      public Integer getValue(Stock object) {
        return object.getSortindex();
      }

      @Override
      public void setValue(Stock object, Integer value) {
        // no-op
      }
    }, SortDir.ASC));

    List<Stock> list = new ArrayList<Stock>();
    Stock stock5 = new Stock("key5", "E (1)", 1);
    Stock stock3 = new Stock("key3", "C (3)", 3);
    Stock stock2 = new Stock("key2", "B (2)", 2);
    Stock stock4 = new Stock("key4", "D (4)", 4);
    Stock stock1 = new Stock("key1", "A (5)", 5);

    list.add(stock1);
    list.add(stock2);
    list.add(stock3);
    list.add(stock4);
    list.add(stock5);

    Stock root = new Stock("muh", "root", 0);

    store.add(root);
    for (Stock stock : list) {
      store.add(root, stock);
    }
  }

  public void testAddItemToSortedTreeEvents() {
    // Added for EXTGWT-2338
    TreeStore<Stock> store = new TreeStore<Stock>(new ModelKeyProvider<Stock>() {
      @Override
      public String getKey(Stock item) {
        return item.getKey();
      }
    });

    store.addSortInfo(new StoreSortInfo<Stock>(new ValueProvider<Stock, Integer>() {
      @Override
      public String getPath() {
        return "sortindex";
      }

      @Override
      public Integer getValue(Stock object) {
        return object.getSortindex();
      }

      @Override
      public void setValue(Stock object, Integer value) {
        // no-op
      }
    }, SortDir.ASC));

    ReportingStoreAddHandler<Stock> handler = new ReportingStoreAddHandler<Stock>();
    store.addStoreAddHandler(handler);

    List<Stock> list = new ArrayList<Stock>();
    final Stock stock5 = new Stock("key5", "E (1)", 1);
    final Stock stock3 = new Stock("key3", "C (3)", 3);
    final Stock stock2 = new Stock("key2", "B (2)", 2);
    final Stock stock4 = new Stock("key4", "D (4)", 4);
    final Stock stock1 = new Stock("key1", "A (5)", 5);

    list.add(stock1);
    list.add(stock2);
    list.add(stock3);
    list.add(stock4);
    list.add(stock5);

    List<Stock> latestItems;
    for (Stock stock : list) {
      store.add(stock);
      latestItems = handler.getLatestItems();
      assertEquals(latestItems.size(), 1);
      assertSame(latestItems.get(0), stock);
    }
  }

  public void testAddItemsToSortedTreeEvents() {
    // Added for EXTGWT-2338
    TreeStore<Stock> store = new TreeStore<Stock>(new ModelKeyProvider<Stock>() {
      @Override
      public String getKey(Stock item) {
        return item.getKey();
      }
    });

    store.addSortInfo(new StoreSortInfo<Stock>(new ValueProvider<Stock, Integer>() {
      @Override
      public String getPath() {
        return "sortindex";
      }

      @Override
      public Integer getValue(Stock object) {
        return object.getSortindex();
      }

      @Override
      public void setValue(Stock object, Integer value) {
        // no-op
      }
    }, SortDir.ASC));

    List<Stock> list = new ArrayList<Stock>();
    final Stock stock5 = new Stock("key5", "E (1)", 1);
    final Stock stock3 = new Stock("key3", "C (3)", 3);
    final Stock stock2 = new Stock("key2", "B (2)", 2);
    final Stock stock4 = new Stock("key4", "D (4)", 4);
    final Stock stock1 = new Stock("key1", "A (5)", 5);

    list.add(stock1);
    list.add(stock2);
    list.add(stock3);
    list.add(stock4);
    list.add(stock5);

    Stock root = new Stock("muh", "root", 0);

    store.add(root);

    ReportingStoreAddHandler<Stock> handler = new ReportingStoreAddHandler<Stock>();
    store.addStoreAddHandler(handler);

    List<Stock> latestItems;

    store.add(root, Arrays.asList(stock4, stock2));
    latestItems = handler.getLatestItems();
    assertEquals(2, latestItems.size());
    assertTrue(latestItems.contains(stock2));
    assertTrue(latestItems.contains(stock4));

    assertEquals(2, store.getChildCount(root));

    // Insert at beginning, end
    store.add(root, Arrays.asList(stock5, stock1));
    latestItems = handler.getLatestItems();
    assertEquals(2, latestItems.size());
    assertTrue(latestItems.contains(stock1));
    assertTrue(latestItems.contains(stock5));

    // Insert in middle
    // Deliberately using list to trip off index bug
    store.add(root, Arrays.asList(stock3));
    latestItems = handler.getLatestItems();
    assertEquals(1, latestItems.size());
    assertTrue(latestItems.contains(stock3));
  }

  public void testAddSubTreeEvents() {
    DataTree a = new DataTree("a", 
        new DataTree("aa", 
            new DataTree("aaa"), 
            new DataTree("aac")),
            new DataTree("ab", 
                new DataTree("aba"),
                new DataTree("abc")));
    TreeStore<DataTree> store = new TreeStore<DataTree>(new DataKeyProvider());
    ReportingStoreAddHandler<DataTree> reportingAddHandler = new ReportingStoreAddHandler<DataTree>();
    store.addStoreAddHandler(reportingAddHandler);

    // Add to the root unfiltered
    store.addSubTree(0, Arrays.asList(a));
    assertEquals(1, reportingAddHandler.getLatestItems().size());

    // Enable a filter
    store.addFilter(new StoreFilter<DataTree>() {
      @Override
      public boolean select(Store<DataTree> store, DataTree parent, DataTree item) {
        return item.getName().endsWith("c");
      }
    });
    store.setEnableFilters(true);
    DataTree b = new DataTree("b", new DataTree("ba"));
    DataTree c = new DataTree("c", new DataTree("ca"), new DataTree("cb"));
    DataTree d = new DataTree("d", new DataTree("da"), new DataTree("dc"));

    // Add Filtered Items to the root
    store.addSubTree(1, Arrays.asList(b,c,d));
    assertEquals(2, reportingAddHandler.getLatestItems().size());

    DataTree de = new DataTree("de", 
        new DataTree("dea"),
        new DataTree("dec"));
    DataTree df = new DataTree("df");

    // Add filtered items to an existing node
    store.addSubTree(d, 1, Arrays.asList(de, df));
    assertEquals(1, reportingAddHandler.getLatestItems().size());

    // Enable a sort (reverse all existing sort)
    store.addSortInfo(new StoreSortInfo<DataTree>(new NameValueProvider(), SortDir.DESC));

    DataTree ac = new DataTree("ac", new DataTree("aca"));
    DataTree ad = new DataTree("ad", new DataTree("ada"));
    store.addSubTree(a, 0, Arrays.asList(ad, ac));
    assertEquals(1, reportingAddHandler.getLatestItems().size());

    // Disable filter, just add items sorted
    store.setEnableFilters(false);
    DataTree ae = new DataTree("ae");
    DataTree af = new DataTree("af");
    store.addSubTree(a, 0, Arrays.asList(af, ae));
    assertEquals(2, reportingAddHandler.getLatestItems().size());
  }

  public void testUpdateEvents() {
    DataTree aaa, aac, a = new DataTree("a", 
        new DataTree("aa", 
            aaa = new DataTree("aaa"), 
            aac = new DataTree("aac")),
            new DataTree("aab", 
                new DataTree("aaba"),
                new DataTree("aabc")));
    TreeStore<DataTree> store = new TreeStore<DataTree>(new DataKeyProvider());
    store.addSubTree(0, Arrays.asList(a));

    final boolean[] updated = {false};
    store.addStoreUpdateHandler(new StoreUpdateHandler<TestTreeStore.DataTree>() {
      @Override
      public void onUpdate(StoreUpdateEvent<DataTree> event) {
        updated[0] = true;
      }
    });

    assertFalse(updated[0]);
    store.update(a);
    assertTrue(updated[0]);

    updated[0] = false;
    store.update(aaa);
    assertTrue(updated[0]);

    updated[0] = false;
    store.update(aac);
    assertTrue(updated[0]);

    // add a filter, try again
    store.addFilter(new StoreFilter<DataTree>() {
      @Override
      public boolean select(Store<DataTree> store, DataTree parent, DataTree item) {
        return item.getName().endsWith("c");
      }
    });
    store.setEnableFilters(true);

    updated[0] = false;
    store.update(a);
    assertTrue(updated[0]);

    updated[0] = false;
    store.update(aaa);
    assertFalse(updated[0]);

    updated[0] = false;
    store.update(aac);
    assertTrue(updated[0]);
  }

  public void testReplaceSubTreeEvents() {
    DataTree aaa, aac, aab, a = new DataTree("a", 
        new DataTree("aa", 
            aaa = new DataTree("aaa"), 
            aac = new DataTree("aac")),
            aab = new DataTree("aab", 
                new DataTree("aaba"),
                new DataTree("aabc")));
    TreeStore<DataTree> store = new TreeStore<DataTree>(new DataKeyProvider());
    store.addSubTree(0, Arrays.asList(a));

    final boolean[] dataChanged = {false};
    store.addStoreDataChangeHandler(new StoreDataChangeHandler<TestTreeStore.DataTree>() {
      @Override
      public void onDataChange(StoreDataChangeEvent<DataTree> event) {
        dataChanged[0] = true;
      }
    });

    assertFalse(dataChanged[0]);
    store.replaceSubTree(aaa, new ArrayList<DataTree>());
    assertTrue(dataChanged[0]);

    dataChanged[0] = false;
    DataTree aabb = new DataTree("aabb");
    DataTree aabd = new DataTree("aabd", new DataTree("aabdc"));
    store.replaceSubTree(aab, Arrays.asList(aabb, aabd));
    assertTrue(dataChanged[0]);

    // add a filter, try again
    store.addFilter(new StoreFilter<DataTree>() {
      @Override
      public boolean select(Store<DataTree> store, DataTree parent, DataTree item) {
        return item.getName().endsWith("c");
      }
    });
    store.setEnableFilters(true);

    dataChanged[0] = false;
    store.replaceSubTree(aaa, new ArrayList<DataTree>());
    assertFalse(dataChanged[0]);

    store.replaceSubTree(aac, new ArrayList<DataTree>());
    assertTrue(dataChanged[0]);
  }

  public void testReplaceChildrenEvents() {
    DataTree aaa, aac, aab, a = new DataTree("a", 
        new DataTree("aa", 
            aaa = new DataTree("aaa"), 
            aac = new DataTree("aac")),
            aab = new DataTree("aab", 
                new DataTree("aaba"),
                new DataTree("aabc")));
    TreeStore<DataTree> store = new TreeStore<DataTree>(new DataKeyProvider());
    store.addSubTree(0, Arrays.asList(a));

    final boolean[] dataChanged = {false};
    store.addStoreDataChangeHandler(new StoreDataChangeHandler<TestTreeStore.DataTree>() {
      @Override
      public void onDataChange(StoreDataChangeEvent<DataTree> event) {
        dataChanged[0] = true;
      }
    });

    assertFalse(dataChanged[0]);
    store.replaceChildren(aaa, new ArrayList<DataTree>());
    assertTrue(dataChanged[0]);

    dataChanged[0] = false;
    DataTree aabb = new DataTree("aabb");
    DataTree aabd = new DataTree("aabd", new DataTree("aabdc"));
    store.replaceChildren(aab, Arrays.asList(aabb, aabd));
    assertTrue(dataChanged[0]);

    // add a filter, try again
    store.addFilter(new StoreFilter<DataTree>() {
      @Override
      public boolean select(Store<DataTree> store, DataTree parent, DataTree item) {
        return item.getName().endsWith("c");
      }
    });
    store.setEnableFilters(true);

    dataChanged[0] = false;
    store.replaceChildren(aaa, new ArrayList<DataTree>());
    assertFalse(dataChanged[0]);

    store.replaceChildren(aac, new ArrayList<DataTree>());
    assertTrue(dataChanged[0]);
  }
}
