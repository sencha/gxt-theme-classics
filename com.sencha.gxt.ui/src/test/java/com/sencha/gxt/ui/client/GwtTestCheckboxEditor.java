package com.sencha.gxt.ui.client;

import com.google.gwt.core.shared.GWT;
import com.sencha.gxt.ui.client.GwtTestCheckboxEditor.CheckBoxWrapper;
import com.sencha.gxt.widget.core.client.form.CheckBox;
import com.sencha.gxt.widget.core.client.form.Field;
import com.sencha.gxt.ui.client.GwtTestEditorFields.FieldDataEditor;

public class GwtTestCheckboxEditor extends GwtTestEditorFields<Boolean, CheckBoxWrapper> {
  public static class CheckBoxWrapper extends FieldDataEditor<Boolean> {
    private final CheckBox checkBox = new CheckBox();

    @Override
    public Field<Boolean> data() {
      return checkBox;
    }
  }
  public interface BooleanDriver extends DataDriver<Boolean, CheckBoxWrapper> {}

  @Override
  public Boolean[] values() {
    return new Boolean[] {true, false};
  }

  @Override
  public Data<Boolean> makeData() {
    return new Data<Boolean>(true);
  }

  @Override
  public DataDriver<Boolean, CheckBoxWrapper> makeDriver() {
    return GWT.create(BooleanDriver.class);
  }

  @Override
  public CheckBoxWrapper makeEditor() {
    return new CheckBoxWrapper();
  }


  @Override
  public void testHasErrors() {
    //do nothing, CheckBox doesn't do anything with markInvalid, can't validate false
  }
}
