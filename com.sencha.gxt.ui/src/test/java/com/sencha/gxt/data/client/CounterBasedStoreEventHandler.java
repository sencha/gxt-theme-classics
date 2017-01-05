package com.sencha.gxt.data.client;

import com.sencha.gxt.data.shared.event.StoreAddEvent;
import com.sencha.gxt.data.shared.event.StoreClearEvent;
import com.sencha.gxt.data.shared.event.StoreDataChangeEvent;
import com.sencha.gxt.data.shared.event.StoreFilterEvent;
import com.sencha.gxt.data.shared.event.StoreHandlers;
import com.sencha.gxt.data.shared.event.StoreRecordChangeEvent;
import com.sencha.gxt.data.shared.event.StoreRemoveEvent;
import com.sencha.gxt.data.shared.event.StoreSortEvent;
import com.sencha.gxt.data.shared.event.StoreUpdateEvent;

public class CounterBasedStoreEventHandler<T> implements StoreHandlers<T> {
  private int count = 0;

  @Override
  public void onAdd(StoreAddEvent<T> data) {
    count++;
  }

  @Override
  public void onRemove(StoreRemoveEvent<T> data) {
    count++;
  }

  @Override
  public void onFilter(StoreFilterEvent<T> store) {
    count++;
  }

  @Override
  public void onClear(StoreClearEvent<T> store) {
    count++;
  }

  @Override
  public void onRecordChange(StoreRecordChangeEvent<T> event) {
    count++;
  }

  @Override
  public void onUpdate(StoreUpdateEvent<T> event) {
    count++;
  }

  public int getCount() {
    return count;
  }

  @Override
  public void onDataChange(StoreDataChangeEvent<T> event) {
    count++;
  }

  @Override
  public void onSort(StoreSortEvent<T> event) {
    count++;
  }
}