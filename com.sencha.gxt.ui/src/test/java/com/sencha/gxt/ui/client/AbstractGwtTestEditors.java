package com.sencha.gxt.ui.client;

import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.user.client.TakesValue;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.RootPanel;
import com.sencha.gxt.ui.client.AbstractGwtTestEditors.DataEditor;

public abstract class AbstractGwtTestEditors<T, E extends DataEditor<T>> extends UITestCase {
  public static class Data<T> {
    private T data;

    public Data(T data) {
      this.data = data;
    }

    public T getData() {
      return data;
    }
    public void setData(T data) {
      this.data = data;
    }
  }
  public static abstract class DataEditor<T> implements Editor<Data<T>>, IsWidget {
    public abstract TakesValue<T> data();
  }
  public interface DataDriver<T, E extends DataEditor<T>> extends SimpleBeanEditorDriver<Data<T>, E> {}


  public abstract DataDriver<T, E> makeDriver();
  public Data<T> makeData() {
    return new Data<T>(null);
  }
  public abstract E makeEditor();

  public abstract T[] values();

  public void testBindData() {
    E ed = makeEditor();
    DataDriver<T, E> d = makeDriver();
    d.initialize(ed);
    RootPanel.get().add(ed);

    Data<T> data = makeData();
    data.setData(values()[0]);
    d.edit(data);

    assertEquals(values()[0], ed.data().getValue());
  }

  public void testFlushData() {
    E ed = makeEditor();
    DataDriver<T, E> d = makeDriver();
    d.initialize(ed);
    RootPanel.get().add(ed);

    d.edit(makeData());

    ed.data().setValue(values()[0]);

    assertEquals(values()[0], d.flush().getData());
  }
}
