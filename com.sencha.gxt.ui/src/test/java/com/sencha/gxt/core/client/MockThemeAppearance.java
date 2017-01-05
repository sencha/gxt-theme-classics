package com.sencha.gxt.core.client;

import com.sencha.gxt.core.client.resources.ThemeStyles.Styles;
import com.sencha.gxt.core.client.resources.ThemeStyles.ThemeAppearance;

public class MockThemeAppearance implements ThemeAppearance {

  @Override
  public Styles style() {
    return null;
  }

  @Override
  public String borderColor() {
    return null;
  }

  @Override
  public String borderColorLight() {
    return null;
  }

  @Override
  public String backgroundColorLight() {
    return null;
  }
}
