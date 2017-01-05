package com.sencha.gxt.theme.blueext.client.button;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.resources.client.ImageResource.ImageOptions;
import com.google.gwt.resources.client.ImageResource.RepeatStyle;
import com.sencha.gxt.theme.base.client.button.ButtonGroupBaseAppearance;
import com.sencha.gxt.theme.base.client.button.ButtonGroupBaseTableFrameResources;
import com.sencha.gxt.theme.base.client.frame.TableFrame;

public class BlueButtonGroupAppearance extends ButtonGroupBaseAppearance {

  public interface BlueButtonGroupTableFrameResources extends ButtonGroupBaseTableFrameResources {

    @Source({
        "com/sencha/gxt/theme/base/client/frame/TableFrame.gss",
        "com/sencha/gxt/theme/base/client/button/ButtonGroupTableFrame.gss"})
    @Override
    ButtonGroupTableFrameStyle style();

    @Source("com/sencha/gxt/core/public/clear.gif")
    @ImageOptions(repeatStyle = RepeatStyle.Both)
    ImageResource background();

    @Source("groupTopLeftBorder.gif")
    @ImageOptions(repeatStyle = RepeatStyle.Vertical)
    @Override
    ImageResource topLeftBorder();

    @ImageOptions(repeatStyle = RepeatStyle.Horizontal)
    @Source("groupTopBorder.gif")
    @Override
    ImageResource topBorder();

    @Override
    @Source("groupTopRightBorder.gif")
    ImageResource topRightBorder();

    @ImageOptions(repeatStyle = RepeatStyle.Vertical)
    @Source("groupLeftBorder.gif")
    @Override
    ImageResource leftBorder();

    @ImageOptions(repeatStyle = RepeatStyle.Vertical)
    @Source("groupRightBorder.gif")
    @Override
    ImageResource rightBorder();

    @ImageOptions(repeatStyle = RepeatStyle.Horizontal)
    @Source("groupBottomBorder.gif")
    @Override
    ImageResource bottomBorder();

    @ImageOptions(repeatStyle = RepeatStyle.Horizontal)
    @Source("groupTopNoHeadBorder.gif")
    ImageResource topNoHeadBorder();
  }
  
  public BlueButtonGroupAppearance() {
    this(GWT.<ButtonGroupResources>create(ButtonGroupResources.class));
  }

  public BlueButtonGroupAppearance(ButtonGroupResources resources) {
    super(resources, GWT.<GroupTemplate> create(GroupTemplate.class), new TableFrame(
        GWT.<BlueButtonGroupTableFrameResources> create(BlueButtonGroupTableFrameResources.class)));
  }

}
