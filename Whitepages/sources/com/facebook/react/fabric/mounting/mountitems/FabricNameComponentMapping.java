package com.facebook.react.fabric.mounting.mountitems;

import com.facebook.react.views.image.ReactImageManager;
import com.facebook.react.views.modal.ReactModalHostManager;
import com.facebook.react.views.progressbar.ReactProgressBarViewManager;
import com.facebook.react.views.scroll.ReactScrollViewManager;
import com.facebook.react.views.text.ReactRawTextManager;
import com.facebook.react.views.text.ReactTextViewManager;
import java.util.Map;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;

public final class FabricNameComponentMapping {
    public static final FabricNameComponentMapping INSTANCE = new FabricNameComponentMapping();
    private static final Map<String, String> componentNames = MapsKt.mapOf(TuplesKt.to("View", "RCTView"), TuplesKt.to("Image", ReactImageManager.REACT_CLASS), TuplesKt.to("ScrollView", ReactScrollViewManager.REACT_CLASS), TuplesKt.to("Slider", "RCTSlider"), TuplesKt.to("ModalHostView", ReactModalHostManager.REACT_CLASS), TuplesKt.to("Paragraph", ReactTextViewManager.REACT_CLASS), TuplesKt.to("Text", ReactTextViewManager.REACT_CLASS), TuplesKt.to("RawText", ReactRawTextManager.REACT_CLASS), TuplesKt.to("ActivityIndicatorView", ReactProgressBarViewManager.REACT_CLASS), TuplesKt.to("ShimmeringView", "RKShimmeringView"), TuplesKt.to("TemplateView", "RCTTemplateView"), TuplesKt.to("AxialGradientView", "RCTAxialGradientView"), TuplesKt.to("Video", "RCTVideo"), TuplesKt.to("Map", "RCTMap"), TuplesKt.to("WebView", "RCTWebView"), TuplesKt.to("Keyframes", "RCTKeyframes"), TuplesKt.to("ImpressionTrackingView", "RCTImpressionTrackingView"));

    private FabricNameComponentMapping() {
    }

    public static final String getFabricComponentName(String str) {
        Intrinsics.checkNotNullParameter(str, "componentName");
        String str2 = componentNames.get(str);
        return str2 == null ? str : str2;
    }
}
