package com.sencha.gxt.core.client.util;

import java.util.HashMap;
import java.util.Map;

import com.google.gwt.event.shared.GwtEvent.Type;

@SuppressWarnings("rawtypes")
public class EventTracker {

  private Map<Type, Integer> map = new HashMap<Type, Integer>();

  public void addEvent(Type type) {
    Integer count = map.get(type);
    count = count == null ? 1 : ++count;
    map.put(type, count);
  }

  public int getCount(Type type) {
    Integer count = map.get(type);
    return count == null ? 0 : count;
  }

  public void clear() {
    map.clear();
  }
}
