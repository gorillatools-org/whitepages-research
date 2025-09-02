package com.facebook.fresco.middleware;

import android.graphics.PointF;
import android.graphics.Rect;
import android.net.Uri;
import com.facebook.fresco.ui.common.ControllerListener2;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;

public final class MiddlewareUtils {
    public static final MiddlewareUtils INSTANCE = new MiddlewareUtils();

    private MiddlewareUtils() {
    }

    public static final ControllerListener2.Extras obtainExtras(Map map, Map map2, Map map3, Map map4, Rect rect, String str, PointF pointF, Map map5, Object obj, boolean z, Uri uri) {
        Intrinsics.checkNotNullParameter(map, "componentAttribution");
        Intrinsics.checkNotNullParameter(map2, "shortcutAttribution");
        ControllerListener2.Extras extras = new ControllerListener2.Extras();
        if (rect != null) {
            extras.viewportWidth = rect.width();
            extras.viewportHeight = rect.height();
        }
        extras.scaleType = str;
        if (pointF != null) {
            extras.focusX = Float.valueOf(pointF.x);
            extras.focusY = Float.valueOf(pointF.y);
        }
        extras.callerContext = obj;
        extras.logWithHighSamplingRate = z;
        extras.mainUri = uri;
        extras.datasourceExtras = map3;
        extras.imageExtras = map5;
        extras.shortcutExtras = map2;
        extras.componentExtras = map;
        extras.imageSourceExtras = map4;
        return extras;
    }
}
