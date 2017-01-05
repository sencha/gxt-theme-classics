package com.sencha.gxt.widget.core.client.form;

import java.util.HashMap;
import java.util.Map;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.InputElement;
import com.google.gwt.dom.client.NodeList;
import com.google.gwt.editor.client.Editor.Path;
import com.google.gwt.event.logical.shared.BeforeSelectionEvent;
import com.google.gwt.event.logical.shared.BeforeSelectionHandler;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.event.shared.GwtEvent.Type;
import com.google.gwt.user.client.ui.RootPanel;
import com.sencha.gxt.cell.core.client.form.ComboBoxCell;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.core.client.util.EventTracker;
import com.sencha.gxt.data.shared.LabelProvider;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import com.sencha.gxt.data.shared.StringLabelProvider;
import com.sencha.gxt.ui.resources.shared.BaseDto;
import com.sencha.gxt.widget.core.client.CoreBaseTestCase;
import com.sencha.gxt.widget.core.client.event.CellBeforeSelectionEvent;
import com.sencha.gxt.widget.core.client.event.CellSelectionEvent;
import com.sencha.gxt.widget.core.client.event.CollapseEvent;
import com.sencha.gxt.widget.core.client.event.CollapseEvent.CollapseHandler;
import com.sencha.gxt.widget.core.client.event.ExpandEvent;
import com.sencha.gxt.widget.core.client.event.ExpandEvent.ExpandHandler;

public class GwtTestComboBox extends CoreBaseTestCase {

  public class TestModel {

    private String key;

    public TestModel(String key) {
      this.setKey(key);
    }

    public String getKey() {
      return key;
    }

    public void setKey(String key) {
      this.key = key;
    }

  }

  public interface TestModelProperties extends PropertyAccess<TestModel> {

    ValueProvider<TestModel, String> key();

    @Path("key")
    LabelProvider<TestModel> keyLabel();

    @Path("key")
    ModelKeyProvider<TestModel> keyProvider();

  }

  @SuppressWarnings("rawtypes")
  public void testComboBox() {
    ListStore<BaseDto> store = new ListStore<BaseDto>(new ModelKeyProvider<BaseDto>() {

      @Override
      public String getKey(BaseDto item) {
        return item.getId().toString();
      }
    });

    store.add(new BaseDto(1L, "Test"));

    final Map<Type, Object> map = new HashMap<Type, Object>();

    ComboBox<BaseDto> combo = new ComboBox<BaseDto>(store, new StringLabelProvider<BaseDto>());
    combo.addExpandHandler(new ExpandHandler() {

      @Override
      public void onExpand(ExpandEvent event) {
        map.put(ExpandEvent.getType(), true);
      }
    });

    combo.addCollapseHandler(new CollapseHandler() {

      @Override
      public void onCollapse(CollapseEvent event) {
        map.put(CollapseEvent.getType(), true);
      }
    });

    RootPanel.get().add(combo);

    combo.focus();

    combo.expand();
    combo.collapse();

    assertEquals(true, map.get(ExpandEvent.getType()));
    assertEquals(true, map.get(CollapseEvent.getType()));
  }
  
  public void testDisabled() {
    ComboBox<TestModel> field = new ComboBox<TestModel>(new ListStore<TestModel>(GWT.<TestModelProperties> create(
        TestModelProperties.class).keyProvider()), new StringLabelProvider<TestModel>());

    InputElement input = field.getElement().selectNode("input").cast();
    assertEquals(false, input.isDisabled());
    field.disable();
    assertEquals(true, input.isDisabled());

    RootPanel.get().add(field);
    assertEquals(true, input.isDisabled());

    field.redraw();
    // input element redrawn
    input = field.getElement().selectNode("input").cast();
    assertEquals(true, input.isDisabled());

    field.enable();
    assertEquals(false, input.isDisabled());
  }

  // public void testExpandWithForceSelection93363() {
  // final ComboBox<TestModel> combo = new ComboBox<TestModel>();
  // combo.setName("person");
  // combo.setFieldLabel("Person");
  // final List<TestModel> persons = Arrays.asList(new
  // TestModel("Mickey Mouse"), new TestModel("Goofy"), new TestModel(
  // "Donald Duck"));
  // ListStore<TestModel> store = new ListStore<TestModel>();
  // store.add(persons);
  //
  // combo.setStore(store);
  // combo.setForceSelection(true);
  // combo.setDisplayField("name");
  // combo.setAllowBlank(false);
  // combo.setValidateOnBlur(false);
  // combo.setTriggerAction(TriggerAction.ALL);
  // combo.setValue(new TestModel("Bamse"));
  //
  // RootPanel.get().add(combo);
  //
  // combo.focus();
  // combo.trigger.click();
  // assertEquals(3,
  // combo.getListView().el().select(".x-combo-list-item").getLength());
  // assertTrue(combo.isExpanded());
  // assertTrue(combo.getListView().el().isConnected());
  //
  // combo.collapse();
  // assertFalse(combo.isExpanded());
  // assertFalse(combo.getListView().el().isConnected());
  //
  // assertNull(combo.getValue());
  //
  // combo.focus();
  // combo.trigger.click();
  //
  // // is it still displaying all 3 items?
  // assertEquals(3,
  // combo.getListView().el().select(".x-combo-list-item").getLength());
  // assertTrue(combo.isExpanded());
  // assertTrue(combo.getListView().el().isConnected());
  // }
  //
  // public void testReuseLoadConfig() {
  // RpcProxy<PagingLoadResult<TestModel>> proxy = new
  // RpcProxy<PagingLoadResult<TestModel>>() {
  // @Override
  // public void load(Object loadConfig,
  // AsyncCallback<PagingLoadResult<TestModel>> callback) {
  // List<TestModel> data = new ArrayList<TestModel>();
  // data.add(new TestModel("test1"));
  // PagingLoadResult<TestModel> result = new
  // BasePagingLoadResult<TestModel>(data);
  // callback.onSuccess(result);
  // }
  // };
  //
  // final BasePagingLoadConfig config = new BasePagingLoadConfig();
  //
  // BasePagingLoader<PagingLoadResult<ModelData>> loader = new
  // BasePagingLoader<PagingLoadResult<ModelData>>(proxy);
  // loader.setRemoteSort(true);
  // loader.useLoadConfig(config);
  //
  // ListStore<TestModel> store = new ListStore<TestModel>(loader);
  //
  // ComboBox<TestModel> combo = new ComboBox<TestModel>() {
  // @Override
  // protected PagingLoadConfig getParams(String query) {
  // PagingLoadConfig cfg = super.getParams(query);
  // assertEquals(config, cfg);
  // return cfg;
  // }
  //
  // };
  // combo.setDisplayField("name");
  // combo.setStore(store);
  // combo.setPageSize(10);
  //
  // RootPanel.get().add(combo);
  //
  // combo.el().selectNode(".x-form-trigger-arrow").click();
  // }
  //
  // class TestModel extends BaseModelData {
  // public TestModel(final String name) {
  // setName(name);
  // }
  //
  // public String getName() {
  // return (String) get("name");
  // }
  //
  // public void setName(final String name) {
  // set("name", name);
  // }
  // }

  public void testReadOnly() {
    ComboBox<TestModel> comboBox = new ComboBox<TestModel>(new ListStore<TestModel>(GWT.<TestModelProperties> create(
        TestModelProperties.class).keyProvider()), new StringLabelProvider<TestModel>());
    RootPanel.get().add(comboBox);

    NodeList<Element> input = comboBox.getElement().select("input");
    assertTrue(input.getLength() > 0);
    for (int i = 0; i < input.getLength(); i++) {
      assertFalse(input.getItem(i).hasAttribute("readonly"));
    }

    comboBox.setReadOnly(true);
    input = comboBox.getElement().select("input");
    assertTrue(input.getLength() > 0);
    for (int i = 0; i < input.getLength(); i++) {
      assertTrue(input.getItem(i).hasAttribute("readonly"));
    }

    comboBox.redraw();
    input = comboBox.getElement().select("input");
    assertTrue(input.getLength() > 0);
    for (int i = 0; i < input.getLength(); i++) {
      assertTrue(input.getItem(i).hasAttribute("readonly"));
    }
  }

  public void testSelectionEvent() {
    final EventTracker tracker = new EventTracker();
    
    ListStore<BaseDto> store = new ListStore<BaseDto>(new ModelKeyProvider<BaseDto>() {

      @Override
      public String getKey(BaseDto item) {
        return item.getId().toString();
      }
    });

    store.add(new BaseDto(1L, "Test"));

    ComboBox<BaseDto> combo = new ComboBox<BaseDto>(store, new StringLabelProvider<BaseDto>());
    combo.addSelectionHandler(new SelectionHandler<BaseDto>() {
      
      @Override
      public void onSelection(SelectionEvent<BaseDto> event) {
        CellSelectionEvent<BaseDto> e = (CellSelectionEvent<BaseDto>)event;
        assertNotNull(e.getContext());
        tracker.addEvent(CellSelectionEvent.getType());
      }
    });
    
    combo.addBeforeSelectionHandler(new BeforeSelectionHandler<BaseDto>() {
      
      @Override
      public void onBeforeSelection(BeforeSelectionEvent<BaseDto> event) {
        CellBeforeSelectionEvent<BaseDto> e = (CellBeforeSelectionEvent<BaseDto>)event;
        assertNotNull(e.getContext());
        tracker.addEvent(CellBeforeSelectionEvent.getType());
      }
    });

    RootPanel.get().add(combo);

    
    combo.expand();
    combo.getListView().getSelectionModel().select(0, false);
    onSelect(combo.getCell(), store.get(0));
    
    assertEquals(1, tracker.getCount(CellSelectionEvent.getType()));
    assertEquals(1, tracker.getCount(CellBeforeSelectionEvent.getType()));
  }

  private native void onSelect(ComboBoxCell<BaseDto> combo, BaseDto model) /*-{
    combo.@com.sencha.gxt.cell.core.client.form.ComboBoxCell::onSelect(Ljava/lang/Object;)(model);
  }-*/;

}
