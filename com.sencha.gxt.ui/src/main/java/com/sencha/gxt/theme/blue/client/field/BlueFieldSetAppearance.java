package com.sencha.gxt.theme.blue.client.field;

import com.google.gwt.core.client.GWT;
import com.sencha.gxt.theme.base.client.field.FieldSetDefaultAppearance;

public class BlueFieldSetAppearance extends FieldSetDefaultAppearance {

  public interface BlueFieldSetResources extends FieldSetResources {
    
    @Override
    @Source({"com/sencha/gxt/theme/base/client/field/FieldSet.gss", "BlueFieldSet.gss"})
    public BlueFieldSetStyle css();
  }
  
  public interface BlueFieldSetStyle extends FieldSetStyle {
    
  }
  
  
  public BlueFieldSetAppearance() {
    this(GWT.<BlueFieldSetResources>create(BlueFieldSetResources.class));
  }
  
  public BlueFieldSetAppearance(BlueFieldSetResources resources) {
    super(resources);
  }
  
}
