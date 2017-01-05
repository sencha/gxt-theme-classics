package com.sencha.gxt.widget.core.client.grid;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootPanel;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.client.loader.RpcProxy;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.loader.FilterConfig;
import com.sencha.gxt.data.shared.loader.FilterConfigBean;
import com.sencha.gxt.data.shared.loader.FilterPagingLoadConfig;
import com.sencha.gxt.data.shared.loader.FilterPagingLoadConfigBean;
import com.sencha.gxt.data.shared.loader.LoadResultListStoreBinding;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;
import com.sencha.gxt.data.shared.loader.PagingLoader;
import com.sencha.gxt.ui.resources.client.TestData;
import com.sencha.gxt.ui.resources.client.model.Stock;
import com.sencha.gxt.ui.resources.client.model.StockProperties;
import com.sencha.gxt.widget.core.client.CoreBaseTestCase;
import com.sencha.gxt.widget.core.client.form.NumberPropertyEditor;
import com.sencha.gxt.widget.core.client.form.NumberPropertyEditor.DoublePropertyEditor;
import com.sencha.gxt.widget.core.client.grid.filters.GridFilters;
import com.sencha.gxt.widget.core.client.grid.filters.NumericFilter;
import com.sencha.gxt.widget.core.client.grid.filters.RangeMenu;

@SuppressWarnings("unchecked")
public class GwtTestNumericFilter extends CoreBaseTestCase {

  private StockProperties props;
  private NumericFilter<Stock, Double> numericFilter;

  @Override
  protected void gwtSetUp() throws Exception {
    super.gwtSetUp();

    props = GWT.create(StockProperties.class);
    numericFilter = new NumericFilter<Stock, Double>(props.change(), new NumberPropertyEditor.DoublePropertyEditor());
  }

  public void testActiveLessThan() {
    // Given a numeric filter with range menu
    RangeMenu<Stock, Double> menu = (RangeMenu<Stock, Double>) numericFilter.getMenu();

    // And it's unactive by default
    assertFalse(numericFilter.isActive());

    // When I add a filter config comparison with value
    FilterConfig filterConfigLt = new FilterConfigBean();
    filterConfigLt.setComparison(RangeMenu.RangeItem.LESSTHAN.getKey());
    filterConfigLt.setValue("1.2");

    // And set filter config to the range menu
    List<FilterConfig> filterConfigs = new ArrayList<FilterConfig>();
    filterConfigs.add(filterConfigLt);
    menu.setValue(filterConfigs);

    // Then the menu should be active
    assertTrue(numericFilter.isActivatable());
    assertTrue(numericFilter.isActive());
  }

  public void testActiveGreaterThan() {
    // Given a numeric filter with range menu
    RangeMenu<Stock, Double> menu = (RangeMenu<Stock, Double>) numericFilter.getMenu();

    // And it's unactive by default
    assertFalse(numericFilter.isActive());

    // When I add a filter config comparison with value
    FilterConfig filterConfigGt = new FilterConfigBean();
    filterConfigGt.setComparison(RangeMenu.RangeItem.GREATERTHAN.getKey());
    filterConfigGt.setValue("1.3");

    // And set filter config to the range menu
    List<FilterConfig> filterConfigs = new ArrayList<FilterConfig>();
    filterConfigs.add(filterConfigGt);
    menu.setValue(filterConfigs);

    // Then the menu should be active
    assertTrue(numericFilter.isActivatable());
    assertTrue(numericFilter.isActive());
  }

  public void testActiveEqual() {
    // Given a numeric filter with range menu
    RangeMenu<Stock, Double> menu = (RangeMenu<Stock, Double>) numericFilter.getMenu();

    // And it's unactive by default
    assertFalse(numericFilter.isActive());

    // When I add a filter config comparison with value
    FilterConfig filterConfigEq = new FilterConfigBean();
    filterConfigEq.setComparison(RangeMenu.RangeItem.EQUAL.getKey());
    filterConfigEq.setValue("1.4");

    // And set filter config to the range menu
    List<FilterConfig> filterConfigs = new ArrayList<FilterConfig>();
    filterConfigs.add(filterConfigEq);
    menu.setValue(filterConfigs);

    // Then the menu should be active
    assertTrue(numericFilter.isActivatable());
    assertTrue(numericFilter.isActive());
  }

  public void testNotActiveLessThan() {
    // Given a numeric filter with range menu
    RangeMenu<Stock, Double> menu = (RangeMenu<Stock, Double>) numericFilter.getMenu();

    // And it's unactive by default
    assertFalse(numericFilter.isActive());

    // When I add a filter config comparison with value
    FilterConfig filterConfigLt = new FilterConfigBean();
    filterConfigLt.setComparison(RangeMenu.RangeItem.LESSTHAN.getKey());
    filterConfigLt.setValue(null);

    // And set filter config to the range menu
    List<FilterConfig> filterConfigs = new ArrayList<FilterConfig>();
    filterConfigs.add(filterConfigLt);
    menu.setValue(filterConfigs);

    // Then the menu should be active
    assertFalse(numericFilter.isActivatable());
    assertFalse(numericFilter.isActive());
  }

  public void testNotActiveGreaterThan() {
    // Given a numeric filter with range menu
    RangeMenu<Stock, Double> menu = (RangeMenu<Stock, Double>) numericFilter.getMenu();

    // And it's unactive by default
    assertFalse(numericFilter.isActive());

    // When I add a filter config comparison with value
    FilterConfig filterConfigGt = new FilterConfigBean();
    filterConfigGt.setComparison(RangeMenu.RangeItem.GREATERTHAN.getKey());
    filterConfigGt.setValue(null);

    // And set filter config to the range menu
    List<FilterConfig> filterConfigs = new ArrayList<FilterConfig>();
    filterConfigs.add(filterConfigGt);
    menu.setValue(filterConfigs);

    // Then the menu should be active
    assertFalse(numericFilter.isActivatable());
    assertFalse(numericFilter.isActive());
  }

  public void testNotActiveEqual() {
    // Given a numeric filter with range menu
    RangeMenu<Stock, Double> menu = (RangeMenu<Stock, Double>) numericFilter.getMenu();

    // And it's unactive by default
    assertFalse(numericFilter.isActive());

    // When I add a filter config comparison with value
    FilterConfig filterConfigEq = new FilterConfigBean();
    filterConfigEq.setComparison(RangeMenu.RangeItem.EQUAL.getKey());
    filterConfigEq.setValue(null);

    // And set filter config to the range menu
    List<FilterConfig> filterConfigs = new ArrayList<FilterConfig>();
    filterConfigs.add(filterConfigEq);
    menu.setValue(filterConfigs);

    // Then the menu should be active
    assertFalse(numericFilter.isActivatable());
    assertFalse(numericFilter.isActive());
  }

  public void testGetFilterConfigs() {
    // Given a numeric filter with range menu
    RangeMenu<Stock, Double> menu = (RangeMenu<Stock, Double>) numericFilter.getMenu();

    // And it's unactive by default
    assertFalse(numericFilter.isActive());

    // When I add a filter config comparison with value
    FilterConfig filterConfigLt = new FilterConfigBean();
    filterConfigLt.setComparison(RangeMenu.RangeItem.LESSTHAN.getKey());
    filterConfigLt.setValue("1.2");

    // And set filter config to the range menu
    List<FilterConfig> filterConfigs = new ArrayList<FilterConfig>();
    filterConfigs.add(filterConfigLt);
    menu.setValue(filterConfigs);

    List<FilterConfig> filterConfigsRtn = (List<FilterConfig>) numericFilter.getValue();
    assertEquals(filterConfigs.get(0).getValue(), filterConfigsRtn.get(0).getValue());

    List<FilterConfig> filterConfigsRtn2 = numericFilter.getFilterConfig();
    assertEquals(filterConfigs.get(0).getValue(), filterConfigsRtn2.get(0).getValue());
  }

  public class NumericFilterExt<M, V extends Number & Comparable<V>> extends NumericFilter<M, V> {
    public NumericFilterExt(ValueProvider<? super M, V> valueProvider, NumberPropertyEditor<V> propertyEditor) {
      super(valueProvider, propertyEditor);
    }

    @Override
    public boolean greaterThan(V a, V b) {
      return super.greaterThan(a, b);
    }

    @Override
    public boolean validateModel(M model) {
      return super.validateModel(model);
    }
  }

  public void testGreaterThan() {
    // Given a numeric filter extending to access protected methods
    NumericFilterExt<Stock, Double> numericFilter =
        new NumericFilterExt<Stock, Double>(props.change(), new NumberPropertyEditor.DoublePropertyEditor());

    // When values are compared, Then the comparison works as expected.
    assertFalse(numericFilter.greaterThan(1.1, 2.2));
    assertTrue(numericFilter.greaterThan(2.2, 1.1));
  }

  public void testValidateModelLessThan() {
    // Given a numeric filter extending to access protected methods
    NumericFilterExt<Stock, Double> numericFilter =
        new NumericFilterExt<Stock, Double>(props.change(), new NumberPropertyEditor.DoublePropertyEditor());

    // And get the menu for setting the filter config
    RangeMenu<Stock, Double> menu = (RangeMenu<Stock, Double>) numericFilter.getMenu();

    // And add a filter config comparison with value for change column
    FilterConfig filterConfigLt = new FilterConfigBean();
    filterConfigLt.setField("change");
    filterConfigLt.setComparison(RangeMenu.RangeItem.LESSTHAN.getKey());
    filterConfigLt.setValue("1.2");

    // And set filter config to the range menu
    List<FilterConfig> filterConfigs = new ArrayList<FilterConfig>();
    filterConfigs.add(filterConfigLt);
    menu.setValue(filterConfigs);

    // When change is set to lower than 1.2
    Stock stock = TestData.getCompanies().get(0);
    stock.setChange(0.5);

    // Then it should be lessthan 1.2
    assertTrue(numericFilter.validateModel(stock));

    // When the value is above 1.2
    stock.setChange(3.1);

    // Then it will not validate b/c its above 1.2
    assertFalse(numericFilter.validateModel(stock));
  }

  public void testValidateModelLessThanWhenValuesAreTheSame() {
    // Given a numeric filter extending to access protected methods
    NumericFilterExt<Stock, Double> numericFilter =
        new NumericFilterExt<Stock, Double>(props.change(), new NumberPropertyEditor.DoublePropertyEditor());

    // And get the menu for setting the filter config
    RangeMenu<Stock, Double> menu = (RangeMenu<Stock, Double>) numericFilter.getMenu();

    // And add a filter config comparison with value for change column
    FilterConfig filterConfigLt = new FilterConfigBean();
    filterConfigLt.setField("change");
    filterConfigLt.setComparison(RangeMenu.RangeItem.LESSTHAN.getKey());
    filterConfigLt.setValue("1.2");

    // And set filter config to the range menu
    List<FilterConfig> filterConfigs = new ArrayList<FilterConfig>();
    filterConfigs.add(filterConfigLt);
    menu.setValue(filterConfigs);

    // When change is set to lower than 1.2
    Stock stock = TestData.getCompanies().get(0);
    stock.setChange(1.2);

    // Then 1.2 < 1.2 is not less than
    assertFalse(numericFilter.validateModel(stock));
  }

  public void testValidateModelGreaterThan() {
    // Given a numeric filter extending to access protected methods
    NumericFilterExt<Stock, Double> numericFilter =
        new NumericFilterExt<Stock, Double>(props.change(), new NumberPropertyEditor.DoublePropertyEditor());

    // And get the menu for setting the filter config
    RangeMenu<Stock, Double> menu = (RangeMenu<Stock, Double>) numericFilter.getMenu();

    // And add a filter config comparison with value for change column
    FilterConfig filterConfigLt = new FilterConfigBean();
    filterConfigLt.setField("change");
    filterConfigLt.setComparison(RangeMenu.RangeItem.GREATERTHAN.getKey());
    filterConfigLt.setValue("1.2");

    // And set filter config to the range menu
    List<FilterConfig> filterConfigs = new ArrayList<FilterConfig>();
    filterConfigs.add(filterConfigLt);
    menu.setValue(filterConfigs);

    // When change is set to lower than 1.2
    Stock stock = TestData.getCompanies().get(0);
    stock.setChange(0.5);

    // Then .5 is less than 1.2
    assertFalse(numericFilter.validateModel(stock));

    // When change is set greater than 1.2
    stock.setChange(3.1);

    // Then 3.1 is greater than 1.2
    assertTrue(numericFilter.validateModel(stock));
  }

  public void testValidateModelGreaterThanWithTheSameValues() {
    // Given a numeric filter extending to access protected methods
    NumericFilterExt<Stock, Double> numericFilter =
        new NumericFilterExt<Stock, Double>(props.change(), new NumberPropertyEditor.DoublePropertyEditor());

    // And get the menu for setting the filter config
    RangeMenu<Stock, Double> menu = (RangeMenu<Stock, Double>) numericFilter.getMenu();

    // And add a filter config comparison with value for change column
    FilterConfig filterConfigLt = new FilterConfigBean();
    filterConfigLt.setField("change");
    filterConfigLt.setComparison(RangeMenu.RangeItem.GREATERTHAN.getKey());
    filterConfigLt.setValue("1.2");

    // And set filter config to the range menu
    List<FilterConfig> filterConfigs = new ArrayList<FilterConfig>();
    filterConfigs.add(filterConfigLt);
    menu.setValue(filterConfigs);

    // When change is set to lower than 1.2
    Stock stock = TestData.getCompanies().get(0);
    stock.setChange(1.2);

    // Then 1.2 is not greater than 1.2
    assertFalse(numericFilter.validateModel(stock));
  }

  public void testValidateModelEqual() {
    // Given a numeric filter extending to access protected methods
    NumericFilterExt<Stock, Double> numericFilter =
        new NumericFilterExt<Stock, Double>(props.change(), new NumberPropertyEditor.DoublePropertyEditor());

    // And get the menu for setting the filter config
    RangeMenu<Stock, Double> menu = (RangeMenu<Stock, Double>) numericFilter.getMenu();

    // And add a filter config comparison with value for change column
    FilterConfig filterConfigLt = new FilterConfigBean();
    filterConfigLt.setField("change");
    filterConfigLt.setComparison(RangeMenu.RangeItem.EQUAL.getKey());
    filterConfigLt.setValue("1.2");

    // And set filter config to the range menu
    List<FilterConfig> filterConfigs = new ArrayList<FilterConfig>();
    filterConfigs.add(filterConfigLt);
    menu.setValue(filterConfigs);

    // When change is set to 1.2
    Stock stock = TestData.getCompanies().get(0);
    stock.setChange(1.2);

    // Then the value is equal
    assertTrue(numericFilter.validateModel(stock));

    // When the value is set to not equal
    stock.setChange(1.21);

    // Then its not equal
    assertFalse(numericFilter.validateModel(stock));
  }

  public void testNumericValueInDataProxyMatchesEntered() {
    // Given a numeric filter
    NumericFilter<Stock, Double> filter = new NumericFilter<Stock, Double>(props.change(), new DoublePropertyEditor());

    // And a Grid with its parts consume the numeric filter
    ListStore<Stock> store = new ListStore<Stock>(props.key());

    RpcProxy<FilterPagingLoadConfig, PagingLoadResult<Stock>> proxy = new RpcProxy<FilterPagingLoadConfig, PagingLoadResult<Stock>>() {
      @Override
      public void load(FilterPagingLoadConfig loadConfig, AsyncCallback<PagingLoadResult<Stock>> callback) {
        for (FilterConfig myFilterConfig : loadConfig.getFilters()) {
          String value = myFilterConfig.getValue();

          // Then assert the value was a double, same as that was entered into the menu
          assertEquals("1.0", value);

          finishTest();
        }
      }
    };

    final PagingLoader<FilterPagingLoadConfig, PagingLoadResult<Stock>> loader = new PagingLoader<FilterPagingLoadConfig, PagingLoadResult<Stock>>(proxy);
    loader.useLoadConfig(new FilterPagingLoadConfigBean());
    loader.addLoadHandler(new LoadResultListStoreBinding<FilterPagingLoadConfig, Stock, PagingLoadResult<Stock>>(store));
    loader.setRemoteSort(true);

    ColumnConfig<Stock, Double> change = new ColumnConfig<Stock, Double>(props.change(), 80, "Change");

    List<ColumnConfig<Stock, ?>> columns = new ArrayList<ColumnConfig<Stock, ?>>();
    columns.add(change);

    ColumnModel<Stock> cm = new ColumnModel<Stock>(columns);

    Grid<Stock> grid = new Grid<Stock>(store, cm);
    grid.setLoader(loader);
    RootPanel.get().add(grid);

    // And the Grid uses the numeric filter
    GridFilters<Stock> gridFilters = new GridFilters<Stock>(loader);
    gridFilters.initPlugin(grid);
    gridFilters.setLocal(false);
    gridFilters.addFilter(filter);

    // And get the menu for setting the filter config
    RangeMenu<Stock, Double> menu = (RangeMenu<Stock, Double>) filter.getMenu();

    // When the filter config is added and updates the filter with the value, it calls load
    FilterConfig filterConfigLt = new FilterConfigBean();
    filterConfigLt.setField("change");
    filterConfigLt.setComparison(RangeMenu.RangeItem.GREATERTHAN.getKey());
    filterConfigLt.setValue("1");

    List<FilterConfig> filterConfigs = new ArrayList<FilterConfig>();
    filterConfigs.add(filterConfigLt);
    menu.setValue(filterConfigs);

    delayTestFinish(5000);
  }


  static class Data {
    private String key;
    private int index;
    private BigDecimal sum;

    public String getKey() {
      return key;
    }

    public void setKey(String key) {
      this.key = key;
    }

    public int getIndex() {
      return index;
    }

    public void setIndex(int index) {
      this.index = index;
    }

    public BigDecimal getSum() {
      return sum;
    }

    public void setSum(BigDecimal sum) {
      this.sum = sum;
    }
  }

  public void testValidateIntegerEquality() {
    // Given a Integer numeric filter
    ValueProvider<Data, Integer> vp = new ValueProvider<Data, Integer>() {
      @Override
      public Integer getValue(Data object) {
        return object.getIndex();
      }

      @Override
      public void setValue(Data object, Integer value) {
      }

      @Override
      public String getPath() {
        return "index";
      }
    };
    NumericFilterExt<Data, Integer> numericFilter =
        new NumericFilterExt<Data, Integer>(vp, new NumberPropertyEditor.IntegerPropertyEditor());

    // And get the menu for setting the filter config
    RangeMenu<Data, Integer> menu = (RangeMenu<Data, Integer>) numericFilter.getMenu();

    // And add a filter config comparison with value for change column
    FilterConfig filterConfigLt = new FilterConfigBean();
    filterConfigLt.setField("index");
    filterConfigLt.setComparison(RangeMenu.RangeItem.EQUAL.getKey());
    filterConfigLt.setValue("1");

    // And set filter config to the range menu
    List<FilterConfig> filterConfigs = new ArrayList<FilterConfig>();
    filterConfigs.add(filterConfigLt);
    menu.setValue(filterConfigs);

    // When its set
    Data data = new Data();
    data.setIndex(1);

    // Then the value is equal
    assertTrue(numericFilter.validateModel(data));
  }

  public void testValidateBigDecimalEquality() {
    // Given a Integer numeric filter
    ValueProvider<Data, BigDecimal> vp = new ValueProvider<Data, BigDecimal>() {
      @Override
      public BigDecimal getValue(Data object) {
        return object.getSum();
      }

      @Override
      public void setValue(Data object, BigDecimal value) {
      }

      @Override
      public String getPath() {
        return "sum";
      }
    };
    NumericFilterExt<Data, BigDecimal> numericFilter =
        new NumericFilterExt<Data, BigDecimal>(vp, new NumberPropertyEditor.BigDecimalPropertyEditor());

    // And get the menu for setting the filter config
    RangeMenu<Data, Integer> menu = (RangeMenu<Data, Integer>) numericFilter.getMenu();

    // And add a filter config comparison with value for change column
    FilterConfig filterConfigLt = new FilterConfigBean();
    filterConfigLt.setField("index");
    filterConfigLt.setComparison(RangeMenu.RangeItem.EQUAL.getKey());
    filterConfigLt.setValue("1");

    // And set filter config to the range menu
    List<FilterConfig> filterConfigs = new ArrayList<FilterConfig>();
    filterConfigs.add(filterConfigLt);
    menu.setValue(filterConfigs);

    // When its set
    Data data = new Data();
    data.setIndex(1);

    // Then the value is equal
    assertTrue(numericFilter.validateModel(data));
  }

}
