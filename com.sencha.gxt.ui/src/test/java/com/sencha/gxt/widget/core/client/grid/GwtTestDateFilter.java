package com.sencha.gxt.widget.core.client.grid;

import java.util.Date;

import com.google.gwt.core.client.GWT;
import com.sencha.gxt.core.client.util.DateWrapper;
import com.sencha.gxt.ui.resources.client.model.Stock;
import com.sencha.gxt.ui.resources.client.model.StockProperties;
import com.sencha.gxt.widget.core.client.CoreBaseTestCase;
import com.sencha.gxt.widget.core.client.DatePicker;
import com.sencha.gxt.widget.core.client.grid.filters.DateFilter;
import com.sencha.gxt.widget.core.client.menu.CheckMenuItem;

@SuppressWarnings("deprecation")
public class GwtTestDateFilter extends CoreBaseTestCase {

  private StockProperties props;
  private DateFilter<Stock> dateFilter;

  @Override
  protected void gwtSetUp() throws Exception {
    super.gwtSetUp();

    props = GWT.create(StockProperties.class);
    dateFilter = new DateFilter<Stock>(props.lastTrans());
  }

  public void testMinMax() {
    DatePicker before = dateFilter.getBeforeMenu().getDatePicker();
    DatePicker on = dateFilter.getOnMenu().getDatePicker();
    DatePicker after = dateFilter.getAfterMenu().getDatePicker();

    assertTrue(null, before.getMinDate() == null);
    assertTrue(null, on.getMinDate() == null);
    assertTrue(null, after.getMinDate() == null);

    Date min = new DateWrapper().addDays(-5).asDate();
    Date max = new DateWrapper().addMonths(2).asDate();

    dateFilter.setMinDate(min);
    dateFilter.setMaxDate(max);

    assertTrue(equals(min, before.getMinDate()));
    assertTrue(equals(min, on.getMinDate()));
    assertTrue(equals(min, after.getMinDate()));

    assertTrue(equals(max, before.getMaxDate()));
    assertTrue(equals(max, on.getMaxDate()));
    assertTrue(equals(max, after.getMaxDate()));
  }

  private boolean equals(Date d1, Date d2) {
    return d1.getDate() == d2.getDate() && d1.getMonth() == d2.getMonth() && d1.getYear() == d2.getYear();
  }

  public void testActive() {
    // Given datefilter and before and active date pickers
    DatePicker before = dateFilter.getBeforeMenu().getDatePicker();
    DatePicker after = dateFilter.getAfterMenu().getDatePicker();

    // And the datefilter active is false
    assertFalse(dateFilter.isActive());

    // When the after and before dates are set
    after.setValue(new Date(2013 - 1900, 9, 1));
    before.setValue(new Date(2013 - 1900, 9, 30));

    // Then the datefilter active is true
    assertTrue(dateFilter.isActive());
  }

  public void testDeactivateAfter() {
    // Given the datefilter
    DatePicker after = dateFilter.getAfterMenu().getDatePicker();

    // And after filter active is false
    assertFalse(dateFilter.isActive());

    // When the date is set
    after.setValue(new Date(2013 - 1900, 9, 30));

    // Then the after filter is active
    assertTrue(dateFilter.isActive());

    // When the after date is set to false
    after.setValue(null);

    // Then the filter should not be active
    assertFalse(dateFilter.isActive());
  }

  public void testBeforeSelected() {
    // Given datefilter and the before CheckMenuItem
    CheckMenuItem beforeCheckMenuItem = (CheckMenuItem) dateFilter.getBeforeMenu().getParentItem();

    // And checked is false
    assertFalse(beforeCheckMenuItem.isChecked());

    // When the date is set
    DatePicker before = dateFilter.getBeforeMenu().getDatePicker();
    before.setValue(new Date(2013 - 1900, 9, 30));

    // Then the CheckMenuItem is true
    assertTrue(beforeCheckMenuItem.isChecked());
  }

  public void testAfterSelected() {
    // Given datefilter and the after CheckMenuItem
    CheckMenuItem afterCheckMenuItem = (CheckMenuItem) dateFilter.getAfterMenu().getParentItem();

    // And checked is false
    assertFalse(afterCheckMenuItem.isChecked());

    // When the date is set
    DatePicker after = dateFilter.getAfterMenu().getDatePicker();
    after.setValue(new Date(2013 - 1900, 9, 1));

    // Then the CheckMenuItem is true
    assertTrue(afterCheckMenuItem.isChecked());
  }

  public void testBeforeUnSelected() {
    // Given datefilter and the before CheckMenuItem
    CheckMenuItem beforeCheckMenuItem = (CheckMenuItem) dateFilter.getBeforeMenu().getParentItem();

    // When the date is set
    DatePicker before = dateFilter.getBeforeMenu().getDatePicker();
    before.setValue(new Date(2013 - 1900, 9, 30));

    // Then the CheckMenuItem is true
    assertTrue(beforeCheckMenuItem.isChecked());

    // And the datefilter is active
    assertTrue(dateFilter.isActive());

    // Then the menu is unchecked
    beforeCheckMenuItem.setChecked(false);

    // Then the filter should not be active
    assertFalse(dateFilter.isActivatable());
    assertFalse(dateFilter.isActive());
  }

  public void testAfterUnSelected() {
    // Given datefilter and the after CheckMenuItem
    CheckMenuItem afterAfterCheckMenuItem = (CheckMenuItem) dateFilter.getAfterMenu().getParentItem();

    // When the date is set
    DatePicker after = dateFilter.getAfterMenu().getDatePicker();
    after.setValue(new Date(2013 - 1900, 9, 1));

    // Then the CheckMenuItem is true
    assertTrue(afterAfterCheckMenuItem.isChecked());

    // And the datefilter is active
    assertTrue(dateFilter.isActive());

    // Then the menu is unchecked
    afterAfterCheckMenuItem.setChecked(false);

    // Then the filter should not be active
    assertFalse(dateFilter.isActive());
  }

  public void testSelectingBothThenUnselecting() {
    // Given datefilter and CheckMenuItem
    CheckMenuItem beforeCheckMenuItem = (CheckMenuItem) dateFilter.getBeforeMenu().getParentItem();
    CheckMenuItem afterCheckMenuItem = (CheckMenuItem) dateFilter.getAfterMenu().getParentItem();

    // And the items are not checked
    assertFalse(beforeCheckMenuItem.isChecked());
    assertFalse(afterCheckMenuItem.isChecked());

    // When the date is set
    DatePicker before = dateFilter.getBeforeMenu().getDatePicker();
    before.setValue(new Date(2013 - 1900, 9, 30));

    DatePicker after = dateFilter.getAfterMenu().getDatePicker();
    after.setValue(new Date(2013 - 1900, 9, 2));

    // Then the boxes are checked
    assertTrue(beforeCheckMenuItem.isChecked());
    assertTrue(afterCheckMenuItem.isChecked());

    // Then the filter is active
    assertTrue(dateFilter.isActive());

    // When both filters are unchecked
    beforeCheckMenuItem.setChecked(false);
    afterCheckMenuItem.setChecked(false);

    // Then the filter will be not active
    assertFalse(dateFilter.isActive());
  }

  /**
   * EXTGWT-3257 - When reselecting before/after don't select after/before
   */
  public void testSelectingBothThenUnselectingAndReselectBefore() {
    // Given datefilter and check menu items
    CheckMenuItem beforeCheckMenuItem = (CheckMenuItem) dateFilter.getBeforeMenu().getParentItem();
    CheckMenuItem afterCheckMenuItem = (CheckMenuItem) dateFilter.getAfterMenu().getParentItem();

    // And they start out false
    assertFalse(beforeCheckMenuItem.isChecked());
    assertFalse(afterCheckMenuItem.isChecked());

    // When the date is set
    DatePicker before = dateFilter.getBeforeMenu().getDatePicker();
    before.setValue(new Date(2013 - 1900, 9, 30));

    DatePicker after = dateFilter.getAfterMenu().getDatePicker();
    after.setValue(new Date(2013 - 1900, 9, 2));

    // Then menu items are checked
    assertTrue(beforeCheckMenuItem.isChecked());
    assertTrue(afterCheckMenuItem.isChecked());

    // When the menu items are unchecked
    beforeCheckMenuItem.setChecked(false);
    afterCheckMenuItem.setChecked(false);

    // And then one menu item are checked true
    beforeCheckMenuItem.setChecked(true);

    // Then only one menu item should be checked true
    assertTrue(beforeCheckMenuItem.isChecked());
    assertFalse(afterCheckMenuItem.isChecked());
  }

  /**
   * EXTGWT-3257 - When reselecting before/after don't select after/before
   */
  public void testSelectingBothThenUnselectingAndReselectAfter() {
    // Given datefilter and check menu items
    CheckMenuItem beforeCheckMenuItem = (CheckMenuItem) dateFilter.getBeforeMenu().getParentItem();
    CheckMenuItem afterCheckMenuItem = (CheckMenuItem) dateFilter.getAfterMenu().getParentItem();

    // And they start out false
    assertFalse(beforeCheckMenuItem.isChecked());
    assertFalse(afterCheckMenuItem.isChecked());

    // When the menu items are checked
    DatePicker before = dateFilter.getBeforeMenu().getDatePicker();
    before.setValue(new Date(2013 - 1900, 9, 30));

    DatePicker after = dateFilter.getAfterMenu().getDatePicker();
    after.setValue(new Date(2013 - 1900, 9, 2));

    // Then menu items are checked
    assertTrue(beforeCheckMenuItem.isChecked());
    assertTrue(afterCheckMenuItem.isChecked());

    // When menu items are unchecked
    beforeCheckMenuItem.setChecked(false, false);
    afterCheckMenuItem.setChecked(false, false);

    // And then one menu item are checked true
    afterCheckMenuItem.setChecked(true);

    // Then only one menu item should be checked
    assertFalse(beforeCheckMenuItem.isChecked());
    assertTrue(afterCheckMenuItem.isChecked());
  }

  public void testSelectingAfterBeforeBefore() {
    // Given datefilter and check menu items
    CheckMenuItem beforeCheckMenuItem = (CheckMenuItem) dateFilter.getBeforeMenu().getParentItem();
    CheckMenuItem afterCheckMenuItem = (CheckMenuItem) dateFilter.getAfterMenu().getParentItem();

    // And they start out false
    assertFalse(beforeCheckMenuItem.isChecked());
    assertFalse(afterCheckMenuItem.isChecked());

    // When selecting after
    DatePicker after = dateFilter.getAfterMenu().getDatePicker();
    after.setValue(new Date(2013 - 1900, 9, 15));

    // And selecting before before after
    DatePicker before = dateFilter.getBeforeMenu().getDatePicker();
    before.setValue(new Date(2013 - 1900, 9, 1));

    // Then only before should be checked
    assertTrue(beforeCheckMenuItem.isChecked());
    assertFalse(afterCheckMenuItem.isChecked());
  }

  public void testSelectingBeforeAfterAfter() {
    // Given datefilter and check menu items
    CheckMenuItem beforeCheckMenuItem = (CheckMenuItem) dateFilter.getBeforeMenu().getParentItem();
    CheckMenuItem afterCheckMenuItem = (CheckMenuItem) dateFilter.getAfterMenu().getParentItem();

    // And they start out false
    assertFalse(beforeCheckMenuItem.isChecked());
    assertFalse(afterCheckMenuItem.isChecked());

    // When selecting after
    DatePicker after = dateFilter.getAfterMenu().getDatePicker();
    after.setValue(new Date(2013 - 1900, 9, 15));

    // And selecting before after after
    DatePicker before = dateFilter.getBeforeMenu().getDatePicker();
    before.setValue(new Date(2013 - 1900, 9, 30));

    // Then both are selected
    assertTrue(beforeCheckMenuItem.isChecked());
    assertTrue(afterCheckMenuItem.isChecked());
  }

  public void testSelectingAfterAfterBefore() {
    // Given datefilter and check menu items
    CheckMenuItem beforeCheckMenuItem = (CheckMenuItem) dateFilter.getBeforeMenu().getParentItem();
    CheckMenuItem afterCheckMenuItem = (CheckMenuItem) dateFilter.getAfterMenu().getParentItem();

    // And they start out false
    assertFalse(beforeCheckMenuItem.isChecked());
    assertFalse(afterCheckMenuItem.isChecked());

    // When selecting before
    DatePicker before = dateFilter.getBeforeMenu().getDatePicker();
    before.setValue(new Date(2013 - 1900, 9, 1));

    // And selecting after
    DatePicker after = dateFilter.getAfterMenu().getDatePicker();
    after.setValue(new Date(2013 - 1900, 9, 15));

    // Then after will be selected
    assertFalse(beforeCheckMenuItem.isChecked());
    assertTrue(afterCheckMenuItem.isChecked());
  }

  public void testSelectingOn() {
    // Given datefilter and check menu items
    CheckMenuItem onCheckMenuItem = (CheckMenuItem) dateFilter.getOnMenu().getParentItem();

    // And its false
    assertFalse(onCheckMenuItem.isChecked());

    // And selecting the on filter
    DatePicker on = dateFilter.getOnMenu().getDatePicker();
    on.setValue(new Date(2013 - 1900, 9, 15));

    // Then the on filter is checked
    assertTrue(onCheckMenuItem.isChecked());

    // And the filter should be active
    assertTrue(dateFilter.isActive());
  }

  public void testSelectingOnThenUnchecking() {
    // Given datefilter and check menu items
    CheckMenuItem onCheckMenuItem = (CheckMenuItem) dateFilter.getOnMenu().getParentItem();

    // And its false
    assertFalse(onCheckMenuItem.isChecked());

    // When selecting the on filter
    DatePicker on = dateFilter.getOnMenu().getDatePicker();
    on.setValue(new Date(2013 - 1900, 9, 15));

    // Then the on filter is checked
    assertTrue(onCheckMenuItem.isChecked());

    // And the filter should be active
    assertTrue(dateFilter.isActive());

    // And when on is unchecked
    onCheckMenuItem.setChecked(false);

    // Then the filter should not be active
    assertFalse(dateFilter.isActive());
  }

  public void testSelectingOnAfterBeforeAndAfter() {
    // Given datefilter and check menu items
    CheckMenuItem beforeCheckMenuItem = (CheckMenuItem) dateFilter.getBeforeMenu().getParentItem();
    CheckMenuItem afterCheckMenuItem = (CheckMenuItem) dateFilter.getAfterMenu().getParentItem();
    CheckMenuItem onCheckMenuItem = (CheckMenuItem) dateFilter.getOnMenu().getParentItem();

    // And they start out false
    assertFalse(beforeCheckMenuItem.isChecked());
    assertFalse(afterCheckMenuItem.isChecked());
    assertFalse(onCheckMenuItem.isChecked());

    // When selecting before
    DatePicker before = dateFilter.getBeforeMenu().getDatePicker();
    before.setValue(new Date(2013 - 1900, 9, 1));

    // And selecting after
    DatePicker after = dateFilter.getAfterMenu().getDatePicker();
    after.setValue(new Date(2013 - 1900, 9, 15));

    // And selecting the on filter
    DatePicker on = dateFilter.getOnMenu().getDatePicker();
    on.setValue(new Date(2013 - 1900, 9, 15));

    // Then only the on filter should be selected
    assertFalse(beforeCheckMenuItem.isChecked());
    assertFalse(afterCheckMenuItem.isChecked());
    assertTrue(onCheckMenuItem.isChecked());

    // And the filter should be active
    assertTrue(dateFilter.isActive());
  }

}
