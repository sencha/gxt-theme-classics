package com.sencha.gxt.data.shared;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import junit.framework.TestCase;

import com.google.gwt.event.shared.HandlerRegistration;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.client.CounterBasedStoreEventHandler;
import com.sencha.gxt.data.shared.Data.DataKeyProvider;
import com.sencha.gxt.data.shared.Data.DataNameProperty;
import com.sencha.gxt.data.shared.Data.DataNumberProperty;
import com.sencha.gxt.data.shared.Store.Change;
import com.sencha.gxt.data.shared.Store.StoreFilter;
import com.sencha.gxt.data.shared.Store.StoreSortInfo;
import com.sencha.gxt.data.shared.event.StoreAddEvent;
import com.sencha.gxt.data.shared.event.StoreAddEvent.StoreAddHandler;

/**
 * All of these tests can run on a standard JVM, make a new tests for code that relies on generation
 */
public class TestListStore extends TestCase {

  /**
   * test adding individual elements after sort. this is important to make sure that add(M) optimizations are working
   * correctly
   */
  public void testAddAfterSort() {
    ListStore<Data> store = new ListStore<Data>(new DataKeyProvider());
    store.add(data("n"));
    store.add(data("m"));
    store.addSortInfo(new StoreSortInfo<Data>(DataNameProperty.instance, new Comparator<String>() {
      @Override
      public int compare(String o1, String o2) {
        return o1.compareTo(o2);
      }
    }, SortDir.ASC));

    assertEquals("m", store.get(0).getName());
    assertEquals("n", store.get(store.size() - 1).getName());

    // append y, make sure it stays at the end (note that index means nothing
    // when sorted)
    store.add(data("y"));
    assertEquals("m", store.get(0).getName());
    assertEquals("y", store.get(store.size() - 1).getName());

    // append b, make sure it moves to the front
    store.add(data("b"));
    assertEquals("b", store.get(0).getName());
    assertEquals("y", store.get(store.size() - 1).getName());

    // insert a, make sure it stays at the front
    store.add(0, data("a"));
    assertEquals("a", store.get(0).getName());
    assertEquals("y", store.get(store.size() - 1).getName());

    // insert z, make sure it moves to the end
    store.add(data("z"));
    assertEquals("a", store.get(0).getName());
    assertEquals("z", store.get(store.size() - 1).getName());

    // insert b again, make sure it doesnt cause problems being added twice, and
    // shows up in the right place
    // TODO also consider testing where the new 'same' element is inserted
    store.add(data("b"));
    assertEquals("a", store.get(0).getName());
    assertEquals("b", store.get(1).getName());
    assertEquals("b", store.get(2).getName());

    // Last, clear out the store, and add things back in, make sure they sort
    store.clear();
    store.add(data("z"));
    store.add(data("a"));
    store.add(data("b"));
    assertEquals("a", store.get(0).getName());
    assertEquals("b", store.get(1).getName());
    assertEquals("z", store.get(2).getName());
  }

  public void testAddAll() {
    ListStore<Data> store = new ListStore<Data>(new DataKeyProvider());
    store.add(data("1"));
    store.add(data("2"));
    assertEquals(2, store.size());

    store.addAll(1, Arrays.asList(data("3"), data("4")));
    assertEquals(4, store.size());

    assertEquals("1", store.get(0).getName());
    assertEquals("3", store.get(1).getName());
    assertEquals("4", store.get(2).getName());
    assertEquals("2", store.get(3).getName());

  }

  public void testAddAllAfterSort() {
    ListStore<Data> store = new ListStore<Data>(new DataKeyProvider());
    store.add(data("n"));
    store.add(data("l"));
    store.addSortInfo(new StoreSortInfo<Data>(DataNameProperty.instance, new Comparator<String>() {
      @Override
      public int compare(String o1, String o2) {
        return o1.compareTo(o2);
      }
    }, SortDir.ASC));
    assertEquals("l", store.get(0).getName());
    assertEquals("n", store.get(1).getName());

    // addAll, in no particular order
    store.addAll(Arrays.asList(data("m"), data("a"), data("z"), data("o")));

    assertEquals(6, store.size());
    assertEquals("a", store.get(0).getName());
    assertEquals("z", store.get(5).getName());

    // clear out the store, call addAll again with already enabled sort
    store.clear();
    store.addAll(Arrays.asList(data("m"), data("a"), data("z"), data("o")));

    assertEquals(4, store.size());
    assertEquals("a", store.get(0).getName());
    assertEquals("z", store.get(3).getName());
  }

  public void testAddAllEventSubscription() {
    ListStore<Data> store = new ListStore<Data>(new DataKeyProvider());
    CounterBasedStoreEventHandler<Data> counterHandler = new CounterBasedStoreEventHandler<Data>();
    HandlerRegistration reg = store.addStoreAddHandler(counterHandler);

    // one, two
    store.addAll(Arrays.asList(data("abc")));
    store.addAll(Arrays.asList(data("def"), data("ghi")));

    store.addFilter(new StoreFilter<Data>() {
      @Override
      public boolean select(Store<Data> store, Data parent, Data item) {
        return item.getName().contains("a");
      }
    });
    store.setEnableFilters(true);
    // three, four
    store.addAll(Arrays.asList(data("asdf")));
    store.addAll(Arrays.asList(data("ab"), data("bra"), data("ca"), data("da"), data("bra")));
    // skip (not visible)
    store.addAll(Arrays.asList(data("ghj")));

    store.setEnableFilters(false);

    // five
    store.addAll(Arrays.asList(data("fdsa")));

    // skip (no items, no event)
    store.addAll(Collections.<Data> emptyList());

    reg.removeHandler();

    // skip (handler removed)
    store.addAll(Arrays.asList(data("a")));

    assertEquals(5, counterHandler.getCount());
  }

  public void testAddAllFiltersEnabled() {
    ListStore<Data> store = new ListStore<Data>(new DataKeyProvider());
    store.setEnableFilters(true);

    store.addAll(Arrays.asList(data("abc")));
    assertEquals(1, store.size());
  }

  // added for EXTGWT-2554
  public void testAddAllFilteredAndSorted() {
    ListStore<Data> store = new ListStore<Data>(new DataKeyProvider());
    store.setEnableFilters(true);
    store.addFilter(new StoreFilter<Data>() {
      @Override
      public boolean select(Store<Data> store, Data parent, Data item) {
        return item.getName().startsWith("a");
      }
    });
    store.addSortInfo(new StoreSortInfo<Data>(DataNumberProperty.instance, SortDir.ASC));

    final ArrayList<Data> added = new ArrayList<Data>();
    store.addStoreAddHandler(new StoreAddHandler<Data>() {
      @Override
      public void onAdd(StoreAddEvent<Data> event) {
        added.addAll(event.getItems());
      }
    });

    store.addAll(Arrays.asList(data("abc", 4), data("def", 1), data("apple", 2), data("xxx", 0)));
    assertEquals(2, added.size());
  }

  public void testAddFilteredAndSorted() {
    ListStore<Data> store = new ListStore<Data>(new DataKeyProvider());
    store.setEnableFilters(true);
    store.addFilter(new StoreFilter<Data>() {
      @Override
      public boolean select(Store<Data> store, Data parent, Data item) {
        return item.getName().startsWith("a");
      }
    });
    store.addSortInfo(new StoreSortInfo<Data>(DataNumberProperty.instance, SortDir.ASC));

    final ArrayList<Data> added = new ArrayList<Data>();
    store.addStoreAddHandler(new StoreAddHandler<Data>() {
      @Override
      public void onAdd(StoreAddEvent<Data> event) {
        added.addAll(event.getItems());
      }
    });

    store.add(data("abc", 4));
    store.add(data("def", 1));
    store.add(data("apple", 2));
    store.add(data("xxx", 0));
    assertEquals(2, added.size());
    assertEquals(2, store.get(0).getNumber());
    assertEquals(4, store.get(1).getNumber());
  }

  public void testAddEventSubscription() {
    ListStore<Data> store = new ListStore<Data>(new DataKeyProvider());
    CounterBasedStoreEventHandler<Data> counterHandler = new CounterBasedStoreEventHandler<Data>();
    HandlerRegistration reg = store.addStoreAddHandler(counterHandler);

    // one
    store.add(data("abc"));

    store.addFilter(new StoreFilter<Data>() {
      @Override
      public boolean select(Store<Data> store, Data parent, Data item) {
        return item.getName().contains("a");
      }
    });
    store.setEnableFilters(true);

    // two
    store.add(data("asdf"));
    // skip (not visible), but added anyway
    store.add(data("ghj"));

    store.setEnableFilters(false);

    // three
    store.add(data("..."));
    reg.removeHandler();
    // skip (handler removed)
    store.add(data("fdsa"));

    assertEquals(3, counterHandler.getCount());
  }

  public void testAddFiltersEnabled() {
    ListStore<Data> store = new ListStore<Data>(new DataKeyProvider());
    store.setEnableFilters(true);

    store.add(data("abc"));
    assertEquals(1, store.size());
  }

  public void testAutoCommitRecordChange() {
    ListStore<Data> store = new ListStore<Data>(new DataKeyProvider());
    store.setAutoCommit(true);
    Data item = data("test");
    store.add(item);

    CounterBasedStoreEventHandler<Data> handler = new CounterBasedStoreEventHandler<Data>();
    store.addStoreUpdateHandler(handler);
    store.getRecord(item).addChange(DataNameProperty.instance, "asdf");

    // verify data change to underlying model
    assertEquals("asdf", item.getName());

    // verify update event
    assertEquals(1, handler.getCount());
  }

  public void testMultiSort() {
    ListStore<Data> store = new ListStore<Data>(new DataKeyProvider());

    store.add(data("a", 1));
    store.add(0, data("b", 1));
    store.add(data("b", 3));
    store.add(data("z", 2));
    store.add(data("a", 2));

    store.addSortInfo(new StoreSortInfo<Data>(DataNumberProperty.instance, SortDir.ASC));
    store.addSortInfo(new StoreSortInfo<Data>(DataNameProperty.instance, SortDir.DESC));

    // 0 = b,1
    // 1 = a,1
    // 2 = z,2
    // 3 = a,2
    // 4 = b,3

    assertEquals("b", store.get(0).getName());
    assertEquals(1, store.get(0).getNumber());

    assertEquals("a", store.get(1).getName());
    assertEquals(1, store.get(1).getNumber());

    assertEquals("z", store.get(2).getName());
    assertEquals(2, store.get(2).getNumber());

    assertEquals("a", store.get(3).getName());
    assertEquals(2, store.get(3).getNumber());

    assertEquals("b", store.get(4).getName());
    assertEquals(3, store.get(4).getNumber());

    store.clearSortInfo();
    store.addSortInfo(new StoreSortInfo<Data>(DataNameProperty.instance, SortDir.ASC));

    // marked as ? because sort might not be stable, haven't checked
    // 0 = a,?
    // 1 = a,?
    // 2 = b,?
    // 3 = b,?
    // 4 = z,?

    assertEquals("a", store.get(0).getName());
    assertEquals("a", store.get(1).getName());
    assertEquals("b", store.get(2).getName());
    assertEquals("b", store.get(3).getName());
    assertEquals("z", store.get(4).getName());

  }

  // These next tests mostly exercise the Store functionality and should perhaps
  // be in a different file
  public void testRecordChange() {
    ListStore<Data> store = new ListStore<Data>(new DataKeyProvider());
    Data item = data("test");
    store.add(item);

    store.getRecord(item).addChange(DataNameProperty.instance, "asdf");
    assertEquals("test", item.getName());

    Change<Data, String> modification = store.getRecord(item).getChange(DataNameProperty.instance);
    assertFalse(modification.isCurrentValue(item));

    assertEquals("asdf", modification.getValue());
    assertEquals("asdf", store.getRecord(item).getValue(DataNameProperty.instance));
  }

  public void testRecordCommit() {
    ListStore<Data> store = new ListStore<Data>(new DataKeyProvider());
    Data item = data("test");
    store.add(item);

    CounterBasedStoreEventHandler<Data> handler = new CounterBasedStoreEventHandler<Data>();
    store.addStoreUpdateHandler(handler);
    store.getRecord(item).addChange(DataNameProperty.instance, "asdf");

    assertEquals(0, handler.getCount());

    store.commitChanges();
    assertEquals("asdf", item.getName());
    assertEquals(1, handler.getCount());
  }

  public void testRecordReject() {
    ListStore<Data> store = new ListStore<Data>(new DataKeyProvider());
    Data item = data("test");
    store.add(item);

    store.getRecord(item).addChange(DataNameProperty.instance, "asdf");

    store.rejectChanges();
    assertEquals("test", item.getName());
  }

  public void testReplaceAllFiltered() {
    ListStore<Data> store = new ListStore<Data>(new DataKeyProvider());

    store.setEnableFilters(true);
    store.replaceAll(Arrays.asList(data("abc")));
    assertEquals(1, store.size());
  }

  public void testReplaceAllWithZeroFilters() {
    ListStore<Data> store = new ListStore<Data>(new DataKeyProvider());
    store.add(data("abc"));
    store.add(data("def"));
    StoreFilter<Data> f = new StoreFilter<Data>() {
      @Override
      public boolean select(Store<Data> store, Data parent, Data item) {
        return item.getName().startsWith("a");
      }
    };
    store.addFilter(f);
    store.setEnableFilters(true);
    assertEquals(1, store.size());

    store.removeFilter(f);
    store.replaceAll(Arrays.asList(data("aeiou"), data("apple"), data("xyz")));
    assertEquals(3, store.size());
  }

  /**
   * Test basic sort functions
   */
  public void testSort() {
    ListStore<Data> store = new ListStore<Data>(new DataKeyProvider());

    store.add(data("one"));
    store.add(data("two"));
    store.add(0, data("three"));

    store.addSortInfo(new StoreSortInfo<Data>(new Comparator<Data>() {
      @Override
      public int compare(Data o1, Data o2) {
        return Integer.valueOf(o1.getId()).compareTo(Integer.valueOf(o2.getId()));
      }
    }, SortDir.ASC));

    assertEquals("one", store.get(0).getName());
    assertEquals("two", store.get(1).getName());
    assertEquals("three", store.get(2).getName());

    // reverse
    store.getSortInfo().get(0).setDirection(SortDir.DESC);
    store.applySort(false);
    assertEquals("three", store.get(0).getName());
    assertEquals("two", store.get(1).getName());
    assertEquals("one", store.get(2).getName());

    // no change
    store.clearSortInfo();
    assertEquals("three", store.get(0).getName());
  }

  public void testSortNoValue() {
    ListStore<Data> store = new ListStore<Data>(new DataKeyProvider());

    store.add(data("one"));
    store.add(data("two"));
    store.add(data(null));

    StoreSortInfo<Data> sorter = new StoreSortInfo<Data>(new ValueProvider<Data, String>() {
      @Override
      public String getValue(Data object) {
        return object.getName();
      }

      @Override
      public void setValue(Data object, String value) {
      }

      @Override
      public String getPath() {
        return "name";
      }
    }, SortDir.ASC);

    store.addSortInfo(sorter);

    assertEquals(null, store.get(0).getName());
    assertEquals("one", store.get(1).getName());
    assertEquals("two", store.get(2).getName());

    // reverse
    store.getSortInfo().get(0).setDirection(SortDir.DESC);
    store.applySort(false);

    assertEquals("two", store.get(0).getName());
    assertEquals("one", store.get(1).getName());
    assertEquals(null, store.get(2).getName());
  }

  /**
   * test sorting with properties instead of models
   */
  public void testSortProperty() {
    ListStore<Data> store = new ListStore<Data>(new DataKeyProvider());
    store.add(data("a"));
    store.add(data("b"));
    store.add(0, data("c"));
    store.addSortInfo(new StoreSortInfo<Data>(DataNameProperty.instance, new Comparator<String>() {
      @Override
      public int compare(String o1, String o2) {
        return o1.compareTo(o2);
      }
    }, SortDir.ASC));

    assertEquals("a", store.get(0).getName());
    assertEquals("b", store.get(1).getName());
    assertEquals("c", store.get(2).getName());
  }

  public void testStoreFilter() {
    ListStore<Data> store = new ListStore<Data>(new DataKeyProvider());

    store.add(data("abc"));
    store.add(data("xyz"));

    store.addFilter(new StoreFilter<Data>() {
      @Override
      public boolean select(Store<Data> store, Data parent, Data item) {
        return item.getName().contains("b");
      }
    });

    store.add(data("bad"));
    store.setEnableFilters(true);

    store.add(data("cat"));

    assertEquals(2, store.size());

    store.setEnableFilters(false);

    assertEquals(4, store.size());
  }

  public void testAddAllIndexFiltered() {
    ListStore<Data> store = new ListStore<Data>(new DataKeyProvider());
    store.add(data("1"));
    store.add(data("2"));
    assertEquals(2, store.size());
    store.setEnableFilters(true);
    store.addFilter(new StoreFilter<Data>() {
      @Override
      public boolean select(Store<Data> store, Data parent, Data item) {
        return !"1".equals(item.getName());
      }
    });

    store.addAll(0, Arrays.asList(data("3"), data("4")));
    assertEquals(3, store.size());

    assertEquals("3", store.get(0).getName());
    assertEquals("4", store.get(1).getName());
    assertEquals("2", store.get(2).getName());
  }

  public void testStoreRemove() {
    ListStore<Data> store = new ListStore<Data>(new DataKeyProvider());
    Data hidden1 = data("filtered out");
    store.add(hidden1);
    Data hidden2 = data("invisible");
    store.add(hidden2);
    Data available1 = data("available");
    store.add(available1);
    Data available2 = data("abc");
    store.add(available2);

    CounterBasedStoreEventHandler<Data> handler = new CounterBasedStoreEventHandler<Data>();
    store.addStoreRemoveHandler(handler);

    store.addFilter(new StoreFilter<Data>() {
      @Override
      public boolean select(Store<Data> store, Data parent, Data item) {
        return item.getName().startsWith("a");
      }
    });
    store.setEnableFilters(true);
    // skip (not visible), but removed anyway
    store.remove(hidden1);
    // one
    store.remove(available1);

    // only one left visible
    assertEquals(1, store.size());

    store.setEnableFilters(false);

    assertEquals(2, store.size());
    // two
    store.remove(hidden2);
    // three
    store.remove(available2);

    assertEquals(0, store.size());
    assertEquals(3, handler.getCount());

    store.add(hidden1);
    store.add(available1);

    assertEquals(2, store.size());
    store.setEnableFilters(true);

    store.add(hidden2);
    store.add(available2);
    assertEquals(2, store.size());

    store.clear();
    assertEquals(0, store.size());
    store.setEnableFilters(false);

    store.add(hidden1);
    store.add(available1);
    assertEquals(2, store.size());

    store.setEnableFilters(true);
    store.add(hidden2);
    store.add(available2);
    assertEquals(2, store.size());
  }

  public void testUpdate() {
    Data d1 = data("one", 1);
    Data d2 = data("two", 2);
    Data d1Update = data("one update", 1);

    List<Data> models = new ArrayList<Data>();
    models.add(d1);
    models.add(d2);

    ListStore<Data> store = new ListStore<Data>(new ModelKeyProvider<Data>() {

      @Override
      public String getKey(Data item) {
        return "" + item.getNumber();
      }
    });
    store.addAll(models);

    store.getRecord(d1).addChange(Data.DataNameProperty.instance, "one changed");
    store.update(d1Update);

    assertFalse(store.hasRecord(d1));

    store.getRecord(d2).addChange(Data.DataNameProperty.instance, "two changed");
    store.update(d2);

    assertFalse(store.hasRecord(d2));

    store.getRecord(d2);
    assertTrue(store.hasRecord(d2));
    store.update(d2);
    assertFalse(store.hasRecord(d2));
  }

  public void testUnmodifiableList() {
    Data d1 = data("one", 1);
    Data d2 = data("two", 2);

    List<Data> models = new ArrayList<Data>();
    models.add(d1);
    models.add(d2);

    models = Collections.unmodifiableList(models);

    ListStore<Data> store = new ListStore<Data>(new DataKeyProvider());
    store.addSortInfo(new StoreSortInfo<Data>(DataNumberProperty.instance, SortDir.ASC));

    store.replaceAll(models);

  }

  private Data data(String name) {
    Data m = new Data();
    m.setName(name);
    return m;
  }

  private Data data(String name, int number) {
    Data m = new Data();
    m.setName(name);
    m.setNumber(number);
    return m;
  }
}
