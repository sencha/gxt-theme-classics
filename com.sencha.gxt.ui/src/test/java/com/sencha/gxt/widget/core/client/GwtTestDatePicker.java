package com.sencha.gxt.widget.core.client;

import java.util.Date;

import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.datepicker.client.CalendarUtil;
import com.sencha.gxt.core.client.util.DateWrapper;
import com.sencha.gxt.widget.core.client.button.TextButton;

public class GwtTestDatePicker extends CoreBaseTestCase {

  @SuppressWarnings("deprecation")
  public void testDatePicker() {
    Date d = new Date(110, 2, 31);

    DatePicker picker = new DatePicker();
    assertNull(picker.getValue());

    picker.setValue(d);
    assertNotNull(picker.getValue());
    assertTrue(new DateWrapper(d).resetTime().getTime() == picker.getValue().getTime());

    RootPanel.get().add(picker);
    assertNotNull(picker.getValue());
    assertTrue(new DateWrapper(d).resetTime().getTime() == picker.getValue().getTime());
  }

  public void testMinMax() {
    DatePicker picker = new DatePicker();
    picker.setMinDate(new Date());
    picker.setMaxDate(new Date());

    RootPanel.get().add(picker);
  }

  public void testNull() {
    DatePicker picker = new DatePicker();
    picker.setValue(null);

    RootPanel.get().add(picker);

    picker.setValue(new Date());
    picker.setValue(null);

    assertEquals(null, picker.getValue());
  }

  public void testTodayButtonEnabledBySettingMinDate() {
    // Given a DatePicker
    DatePicker picker = new DatePicker();

    // And we get TodayBtn in the datepicker
    TextButton todayBtn = getTodayButton(picker);

    // And it should be enabled by default
    assertTrue(todayBtn.isEnabled());

    // When a minDate is set into the future
    Date minDate = new Date();
    CalendarUtil.addDaysToDate(minDate, 5);
    picker.setMinDate(minDate);

    // Then the datepicker is today button is disabled
    assertFalse(todayBtn.isEnabled());

    // When mindate is set in the past
    minDate = new Date();
    CalendarUtil.addDaysToDate(minDate, -15);
    picker.setMinDate(minDate);

    // Then the datepicker is button enabled
    assertTrue(todayBtn.isEnabled());

    // When mindate is set to nothing
    picker.setMinDate(null);

    // Then the datepicker is button enabled
    assertTrue(todayBtn.isEnabled());
  }

  public void testTodayButtoneEnabledBySettingMaxDate() {
    // Given a DatePicker
    DatePicker picker = new DatePicker();

    // And we get TodayBtn in the datepicker
    TextButton todayBtn = getTodayButton(picker);

    // And it should be enabled by default
    assertTrue(todayBtn.isEnabled());

    // When a maxDate is set into the past
    Date maxDate = new Date();
    CalendarUtil.addDaysToDate(maxDate, -5);
    picker.setMaxDate(maxDate);

    // Then the datepicker is today button is disabled
    assertFalse(todayBtn.isEnabled());

    // When maxdate is set in the future
    maxDate = new Date();
    CalendarUtil.addDaysToDate(maxDate, +15);
    picker.setMaxDate(maxDate);

    // Then the datepicker is button enabled
    assertTrue(todayBtn.isEnabled());

    // When maxdate is set to nothing
    picker.setMaxDate(null);

    // Then the datepicker is button enabled
    assertTrue(todayBtn.isEnabled());
  }

  @SuppressWarnings("deprecation")
  public void testTodayButtonEnabledBySettingMinDateCloseToEndOfDay() {
    // Given a DatePicker
    DatePicker picker = new DatePicker();

    // And we get TodayBtn in the datepicker
    TextButton todayBtn = getTodayButton(picker);

    // And it should be enabled by default
    assertTrue(todayBtn.isEnabled());

    // When a minDate is set to end of day
    Date endOfToday = new Date();
    endOfToday.setHours(23);
    endOfToday.setMinutes(59);
    endOfToday.setSeconds(59);
    picker.setMinDate(endOfToday);

    // Then the datepicker is today button is enabled
    assertTrue(todayBtn.isEnabled());

    // When the date is start of tomorrow, or in the future
    Date startOfTomorrow = new Date();
    CalendarUtil.addDaysToDate(startOfTomorrow, 1);
    startOfTomorrow.setHours(0);
    startOfTomorrow.setMinutes(0);
    startOfTomorrow.setSeconds(0);
    picker.setMinDate(startOfTomorrow);

    // Then the datepicker is today button is disabled
    assertFalse(todayBtn.isEnabled());
  }

  @SuppressWarnings("deprecation")
  public void testTodayButtonEnabledBySettingMaxDateCloseToStartOfYesterday() {
    // Given a DatePicker
    DatePicker picker = new DatePicker();

    // And we get TodayBtn in the datepicker
    TextButton todayBtn = getTodayButton(picker);

    // And it should be enabled by default
    assertTrue(todayBtn.isEnabled());

    // When a maxDate is set to start of day
    Date startOfToday = new Date();
    startOfToday.setHours(0);
    startOfToday.setMinutes(0);
    startOfToday.setSeconds(0);
    picker.setMaxDate(startOfToday);

    // Then the datepicker is today button is enabled
    assertTrue(todayBtn.isEnabled());

    // When the date is end of yesterday, or in the past
    Date endOfYesterday = new Date();
    CalendarUtil.addDaysToDate(endOfYesterday, -1);
    endOfYesterday.setHours(23);
    endOfYesterday.setMinutes(59);
    endOfYesterday.setSeconds(59);
    picker.setMaxDate(endOfYesterday);

    // Then the datepicker is today button is disabled
    assertFalse(todayBtn.isEnabled());
  }

  public void testBothMinMaxDatesAndCheckTodayButton() {
    // Given a DatePicker
    DatePicker picker = new DatePicker();

    // And we get Today button
    TextButton todayBtn = getTodayButton(picker);

    // And it should be enabled by default
    assertTrue(todayBtn.isEnabled());

    // When both min and max are set, and both are in the past
    Date minDate = new Date();
    Date maxDate = new Date();
    CalendarUtil.addDaysToDate(minDate, -2);
    CalendarUtil.addDaysToDate(maxDate, -15);
    picker.setMinDate(minDate);
    picker.setMaxDate(maxDate);

    // Then today button will be disabled
    assertFalse(todayBtn.isEnabled());

    // When both dates are set into the future
    minDate = new Date();
    maxDate = new Date();
    CalendarUtil.addDaysToDate(minDate, +2);
    CalendarUtil.addDaysToDate(maxDate, +15);
    picker.setMinDate(minDate);
    picker.setMaxDate(maxDate);

    // Then today button will be disabled
    assertFalse(todayBtn.isEnabled());

    // When the dates spanning today
    minDate = new Date();
    maxDate = new Date();
    CalendarUtil.addDaysToDate(minDate, -2);
    CalendarUtil.addDaysToDate(maxDate, +15);
    picker.setMinDate(minDate);
    picker.setMaxDate(maxDate);

    // Then today button will be enabled
    assertTrue(todayBtn.isEnabled());

    // When the dates spanning today
    minDate = new Date();
    maxDate = new Date();
    CalendarUtil.addDaysToDate(minDate, -5);
    picker.setMinDate(minDate);
    picker.setMaxDate(maxDate);

    // Then today button will be enabled
    assertTrue(todayBtn.isEnabled());

    // When the dates spanning today
    minDate = new Date();
    maxDate = new Date();
    CalendarUtil.addDaysToDate(maxDate, +5);
    picker.setMinDate(minDate);
    picker.setMaxDate(maxDate);

    // Then today button will be enabled
    assertTrue(todayBtn.isEnabled());
  }

  public void testBothMinMaxDatesAndCheckTodayButtonWithNulls() {
    // Given a DatePicker
    DatePicker picker = new DatePicker();

    // And we get Today button
    TextButton todayBtn = getTodayButton(picker);

    // And it should be enabled by default
    assertTrue(todayBtn.isEnabled());

    // When only the MaxDate is set
    Date maxDate = new Date();
    CalendarUtil.addDaysToDate(maxDate, +15);
    picker.setMinDate(null);
    picker.setMaxDate(maxDate);

    // Then today button will be disabled
    assertTrue(todayBtn.isEnabled());

    // When only the MinDate is set
    Date minDate = new Date();
    CalendarUtil.addDaysToDate(minDate, -15);
    picker.setMinDate(minDate);
    picker.setMaxDate(null);

    // Then today button will be disabled
    assertTrue(todayBtn.isEnabled());
  }

  public void testDateTimeInfo() {
    DatePicker picker = new DatePicker();
    assertNotNull(picker.getDateTimeInfo());

    DatePicker.DatePickerDateTimeFormatInfo info = picker.getDateTimeInfo();

    String[] values = info.getMonthsShort();
    assertNotNull(values);
    assertEquals(12, values.length);

    values = info.getMonthsFullStandalone();
    assertNotNull(values);
    assertEquals(12, values.length);

    values = info.getWeekdaysNarrow();
    assertNotNull(values);
    assertEquals(7, values.length);

    String[] test = new String[]{"1","2","3","4","5","6","7","8","9","10","11","12","13"};
    info.setMonthsFullStandalone(test);
    info.setFirstDayOfTheWeek(3);
    picker.setDateTimeInfo(info);

    values = info.getMonthsFullStandalone();
    assertNotNull(values);
    assertEquals(13, values.length);

    assertEquals(3, picker.getStartDay());
  }

  private native TextButton getTodayButton(DatePicker picker) /*-{
    return picker.@com.sencha.gxt.widget.core.client.DatePicker::todayBtn;
  }-*/;

}
