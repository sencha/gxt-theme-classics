package com.sencha.gxt.ui.client;

import java.util.List;

import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.EditorError;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.ui.client.GwtTestEditorFields.FieldDataEditor;
import com.sencha.gxt.widget.core.client.form.Field;
import com.sencha.gxt.widget.core.client.form.validator.AbstractValidator;

public abstract class GwtTestEditorFields<T, E extends FieldDataEditor<T>> extends AbstractGwtTestEditors<T, E> {
  public static abstract class FieldDataEditor<T> extends DataEditor<T> {
    public abstract Field<T> data();
    @Override
    public Widget asWidget() {
      return data();
    }
  }

  public void testHasErrors() {
    E ed = makeEditor();
    DataDriver<T, E> d = makeDriver();
    d.initialize(ed);
    RootPanel.get().add(ed);

    d.edit(makeData());

    ed.data().addValidator(new AbstractValidator<T>() {
      @Override
      public List<EditorError> validate(Editor<T> editor, T value) {
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
    E ed = makeEditor();
    DataDriver<T, E> d = makeDriver();
    d.initialize(ed);
    RootPanel.get().add(ed);

    d.edit(makeData());

    ed.data().addValidator(new AbstractValidator<T>() {
      @Override
      public List<EditorError> validate(Editor<T> editor, T value) {
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
    E ed = makeEditor();
    DataDriver<T, E> d = makeDriver();
    d.initialize(ed);
    RootPanel.get().add(ed);

    d.edit(makeData());

    ed.data().forceInvalid("Should not be valid");

    d.flush();

    assertTrue(d.hasErrors());
  }

  public void testClearInvalidErrors() {
    E ed = makeEditor();
    DataDriver<T, E> d = makeDriver();
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
    E ed = makeEditor();
    DataDriver<T, E> d = makeDriver();
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
    E ed = makeEditor();
    DataDriver<T, E> d = makeDriver();
    d.initialize(ed);
    RootPanel.get().add(ed);

    d.edit(makeData());

    ed.data().markInvalid("Should not be valid");

    d.flush();

    assertFalse(d.hasErrors());
  }
}
