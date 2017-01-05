package com.sencha.gxt.ui.client;

import com.google.gwt.core.shared.GWT;
import com.sencha.gxt.ui.client.GwtTestTextFieldEditor.TextFieldWrapper;
import com.sencha.gxt.widget.core.client.form.Field;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.ui.client.GwtTestEditorFields.FieldDataEditor;

public class GwtTestTextFieldEditor extends GwtTestEditorFields<String, TextFieldWrapper> {
  public static class TextFieldWrapper extends FieldDataEditor<String> {
    private final TextField textField = new TextField();

    @Override
    public Field<String> data() {
      return textField;
    }
  }

  public interface StringDriver extends DataDriver<String, TextFieldWrapper> {
  }

  @Override
  public String[] values() {
    return new String[]{"foo", "bar"};
  }

  @Override
  public TextFieldWrapper makeEditor() {
    return new TextFieldWrapper();
  }

  @Override
  public DataDriver<String, TextFieldWrapper> makeDriver() {
    return GWT.create(StringDriver.class);
  }
}
