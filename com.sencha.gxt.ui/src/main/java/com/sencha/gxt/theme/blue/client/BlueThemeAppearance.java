package com.sencha.gxt.theme.blue.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.sencha.gxt.core.client.resources.StyleInjectorHelper;
import com.sencha.gxt.core.client.resources.ThemeStyles.Styles;
import com.sencha.gxt.core.client.resources.ThemeStyles.ThemeAppearance;

public class BlueThemeAppearance implements ThemeAppearance {

  static interface Bundle extends ClientBundle {

    @Source({"com/sencha/gxt/theme/base/client/BaseTheme.gss", "BlueTheme.gss"})
    BlueStyles css();
  }

  interface BlueStyles extends Styles {
    String borderColor();

    String borderColorLight();

    String backgroundColorLight();
  }

  private Bundle bundle;
  private BlueStyles style;

  @Override
  public Styles style() {
    return style;
  }

  public BlueThemeAppearance() {
    this.bundle = GWT.create(Bundle.class);
    this.style = bundle.css();

    StyleInjectorHelper.ensureInjected(this.style, true);
  }

  @Override
  public String borderColor() {
    return style.borderColor();
  }

  @Override
  public String borderColorLight() {
    return style.borderColorLight();
  }

  @Override
  public String backgroundColorLight() {
    return style.backgroundColorLight();
  }
}
