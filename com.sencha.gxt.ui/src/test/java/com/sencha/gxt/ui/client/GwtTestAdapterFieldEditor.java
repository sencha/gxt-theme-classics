package com.sencha.gxt.ui.client;

import java.util.List;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.EditorError;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.ui.client.GwtTestAdapterFieldEditor.AdapterFieldWrapper;
import com.sencha.gxt.widget.core.client.form.AdapterField;
import com.sencha.gxt.ui.client.AbstractGwtTestEditors.DataEditor;
import com.sencha.gxt.widget.core.client.form.validator.AbstractValidator;

public class GwtTestAdapterFieldEditor extends AbstractGwtTestEditors<Object, AdapterFieldWrapper> {
  public static class AdapterFieldWrapper extends DataEditor<Object> {
    private final AdapterField<Object> adapterField = new AdapterField<Object>(new Label()) {
      @Override
      public void setValue(Object o) {
        ((Label) getWidget()).setText(o == null ? null : o.toString());
      }

      @Override
      public Object getValue() {
        return ((Label) getWidget()).getText();
      }
    };

    @Override
    public AdapterField<Object> data() {
      return adapterField;
    }

    @Override
    public Widget asWidget() {
      return adapterField;
    }
  }

  public interface AdapterDriver extends DataDriver<Object, AdapterFieldWrapper> {}

  @Override
  public DataDriver<Object, AdapterFieldWrapper> makeDriver() {
    return GWT.create(AdapterDriver.class);
  }

  @Override
  public AdapterFieldWrapper makeEditor() {
    return new AdapterFieldWrapper();
  }

  @Override
  public Object[] values() {
    return new Object[]{ "foo", new Object() };
  }

  public void testHasErrors() {
    AdapterFieldWrapper ed = makeEditor();
    DataDriver<Object, AdapterFieldWrapper> d = makeDriver();
    d.initialize(ed);
    RootPanel.get().add(ed);

    d.edit(makeData());

    ed.data().addValidator(new AbstractValidator<Object>() {
      @Override
      public List<EditorError> validate(Editor<Object> editor, Object value) {
        return createError(editor, "Some Validation Failed", value);
      }
    });
    ed.data().setValue(values()[0]);

    //calling validate directly, as we can't blur the field
    ed.data().validate();

    d.flush();

    assertTrue(d.hasErrors());
  }

  public void testErrorsWhileEditing() {
    AdapterFieldWrapper ed = makeEditor();
    DataDriver<Object, AdapterFieldWrapper> d = makeDriver();
    d.initialize(ed);
    RootPanel.get().add(ed);

    d.edit(makeData());

    ed.data().addValidator(new AbstractValidator<Object>() {
      @Override
      public List<EditorError> validate(Editor<Object> editor, Object value) {
        if (value == null) {
          return createError(editor, "Cannot be null", value);
        }
        return null;
      }
    });
    ed.data().setValue(null);

    //calling validate directly, as we can't blur the field
    ed.data().validate();

    ed.data().setValue(values()[0]);

    ed.data().validate();

    d.flush();

    assertFalse(d.hasErrors());
  }

  public void testForceInvalidErrors() {
    AdapterFieldWrapper ed = makeEditor();
    DataDriver<Object, AdapterFieldWrapper> d = makeDriver();
    d.initialize(ed);
    RootPanel.get().add(ed);

    d.edit(makeData());

    ed.data().forceInvalid("Should not be valid");

    d.flush();

    assertTrue(d.hasErrors());
  }

  public void testClearInvalidErrors() {
    AdapterFieldWrapper ed = makeEditor();
    DataDriver<Object, AdapterFieldWrapper> d = makeDriver();
    d.initialize(ed);
    RootPanel.get().add(ed);

    d.edit(makeData());

    ed.data().forceInvalid("Should not be valid");

    d.flush();

    ed.data().clearInvalid();

    d.flush();

    assertFalse(d.hasErrors());
  }

  public void testClearErrors() {
    AdapterFieldWrapper ed = makeEditor();
    DataDriver<Object, AdapterFieldWrapper> d = makeDriver();
    d.initialize(ed);
    RootPanel.get().add(ed);

    d.edit(makeData());

    ed.data().forceInvalid("Should not be valid");

    d.flush();

    ed.data().clear();

    d.flush();

    assertFalse(d.hasErrors());
  }

  public void testMarkInvalidErrors() {
    AdapterFieldWrapper ed = makeEditor();
    DataDriver<Object, AdapterFieldWrapper> d = makeDriver();
    d.initialize(ed);
    RootPanel.get().add(ed);

    d.edit(makeData());

    ed.data().markInvalid("Should not be valid");

    d.flush();

    assertFalse(d.hasErrors());
  }
}
