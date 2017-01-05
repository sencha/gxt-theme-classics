package com.sencha.gxt.theme.base.client.field;

import com.sencha.gxt.cell.core.client.form.RadioCell.RadioAppearance;

public class RadioDefaultAppearance extends CheckBoxDefaultAppearance implements RadioAppearance {

  public RadioDefaultAppearance() {
    super();
    type = "radio";
  }

  public RadioDefaultAppearance(CheckBoxResources resources) {
    super(resources);
  }

}