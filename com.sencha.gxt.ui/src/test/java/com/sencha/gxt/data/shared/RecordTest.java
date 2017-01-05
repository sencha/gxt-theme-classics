package com.sencha.gxt.data.shared;

import java.util.Collection;

import junit.framework.TestCase;

import com.sencha.gxt.core.client.util.EventTracker;
import com.sencha.gxt.data.shared.Data.DataNameProperty;
import com.sencha.gxt.data.shared.Data.DataNumberProperty;
import com.sencha.gxt.data.shared.Store.Change;
import com.sencha.gxt.data.shared.event.StoreUpdateEvent;
import com.sencha.gxt.data.shared.event.StoreUpdateEvent.StoreUpdateHandler;

public class RecordTest extends TestCase {

  public void testPropertyChange() {
    Data model = data("foo");

    // Not needed, but added to be explicit
    model.setDescription(null);

    ListStore<Data> store = new ListStore<Data>(new Data.DataKeyProvider());
    store.add(model);

    store.getRecord(model).addChange(Data.DataDescriptionProperty.instance, "bar");
    store.commitChanges();

    assertEquals(model.getDescription(), "bar");
  }

  public void testModifiedRecords() {
    Data model = data("foo");

    ListStore<Data> store = new ListStore<Data>(new Data.DataKeyProvider());
    store.add(model);

    // create a record with a property value
    ListStore<Data>.Record record = store.getRecord(model);

    // start editing, and change a property
    record.addChange(DataNameProperty.instance, "bar");

    assertEquals(1, store.getModifiedRecords().size());

    store.commitChanges();

    assertEquals(0, store.getModifiedRecords().size());

    // start editing, and change a property, this change should do nothing
    // as changed value equals current value
    record = store.getRecord(model);
    record.addChange(DataNameProperty.instance, "bar");

    assertEquals(0, store.getModifiedRecords().size());

    record.addChange(DataNameProperty.instance, "foobar");

    assertEquals(1, store.getModifiedRecords().size());

    store.rejectChanges();

    assertEquals(0, store.getModifiedRecords().size());
  }

  public void testRecordRevert() {
    Data model = data("foo");
    model.setNumber(100);

    ListStore<Data> store = new ListStore<Data>(new Data.DataKeyProvider());
    store.add(model);

    // create a record with a property value
    ListStore<Data>.Record record = store.getRecord(model);

    record.addChange(DataNameProperty.instance, "bar");
    record.addChange(DataNumberProperty.instance, 101);

    assertEquals("bar", record.getValue(DataNameProperty.instance));
    assertEquals((Integer)101, record.getValue(DataNumberProperty.instance));

    record.revert();

    assertEquals("foo", record.getValue(DataNameProperty.instance));
    assertEquals((Integer)100, record.getValue(DataNumberProperty.instance));

    record.commit(true);

    assertEquals("foo", model.getName());
    assertEquals(100, model.getNumber());

    record.addChange(DataNameProperty.instance, "bar");
    record.addChange(DataNumberProperty.instance, 101);

    record.revert(DataNameProperty.instance);

    assertEquals("foo", record.getValue(DataNameProperty.instance));
    assertEquals((Integer)101, record.getValue(DataNumberProperty.instance));

    record.commit(false);

    assertEquals("foo", model.getName());
    assertEquals(101, model.getNumber());
  }

  public void testEvents() {
    Data model = data("foo");

    final EventTracker tracker = new EventTracker();

    ListStore<Data> store = new ListStore<Data>(new Data.DataKeyProvider());
    store.add(model);

    store.addStoreUpdateHandler(new StoreUpdateHandler<Data>() {

      @Override
      public void onUpdate(StoreUpdateEvent<Data> event) {
        tracker.addEvent(StoreUpdateEvent.getType());
      }
    });

    // create a record with a property value
    ListStore<Data>.Record record = store.getRecord(model);

    // start editing, and change a property
    record.addChange(DataNameProperty.instance, "bar");

    assertEquals(0, tracker.getCount(StoreUpdateEvent.getType()));

    record.commit(true);

    assertEquals(1, tracker.getCount(StoreUpdateEvent.getType()));
  }

  public void testRecordChanges() {
    Data model = data("foo");

    ListStore<Data> store = new ListStore<Data>(new Data.DataKeyProvider());
    store.add(model);

    // create a record with a property value
    ListStore<Data>.Record record = store.getRecord(model);

    // start editing, and change a property
    record.addChange(DataNameProperty.instance, "bar");

    // model should not be updated
    assertEquals("foo", model.getName());

    // verify that the changes contain the changed property
    Collection<Change<Data, ?>> changes = record.getChanges();
    assertEquals(1, changes.size());
    assertEquals("bar", record.getChange(DataNameProperty.instance).getValue());

    // rollback the changes
    record.revert();

    // verify that there are no changes and that the old property value is there
    changes = record.getChanges();
    assertEquals(0, changes.size());
    assertEquals("foo", model.getName());

    // start editing, and change a property
    record.addChange(DataNameProperty.instance, "bar");
    record.commit(true);

    assertEquals("bar", model.getName());
  }

  private Data data(String name) {
    Data m = new Data();
    m.setName(name);
    return m;
  }

}
