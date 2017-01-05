package com.sencha.gxt.theme.blue.client.toolbar;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;
import com.sencha.gxt.theme.base.client.toolbar.PagingToolBarBaseAppearance;

public class BluePagingToolBarAppearance extends PagingToolBarBaseAppearance {
  public interface BluePagingToolBarResources extends PagingToolBarResources, ClientBundle {
    ImageResource first();

    ImageResource prev();

    ImageResource next();

    ImageResource last();

    ImageResource refresh();

    ImageResource loading();
  }

  public BluePagingToolBarAppearance() {
    this(GWT.<BluePagingToolBarResources> create(BluePagingToolBarResources.class));
  }

  public BluePagingToolBarAppearance(BluePagingToolBarResources resources) {
    super(resources);
  }
}
