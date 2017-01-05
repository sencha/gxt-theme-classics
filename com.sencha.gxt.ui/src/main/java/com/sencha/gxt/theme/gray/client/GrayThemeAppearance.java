package com.sencha.gxt.theme.gray.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.sencha.gxt.core.client.resources.StyleInjectorHelper;
import com.sencha.gxt.core.client.resources.ThemeStyles.Styles;
import com.sencha.gxt.core.client.resources.ThemeStyles.ThemeAppearance;

public class GrayThemeAppearance implements ThemeAppearance {

  static interface Bundle extends ClientBundle {

    @Source({"com/sencha/gxt/theme/base/client/BaseTheme.gss", "GrayTheme.gss"})
    GrayStyles css();
  }

  interface GrayStyles extends Styles {
    String borderColor();

    String borderColorLight();

    String backgroundColorLight();
  }

  private Bundle bundle;
  private GrayStyles style;

  @Override
  public Styles style() {
    return style;
  }

  public GrayThemeAppearance() {
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
