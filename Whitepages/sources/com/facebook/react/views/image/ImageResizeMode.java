package com.facebook.react.views.image;

import android.graphics.Shader;
import com.facebook.common.logging.FLog;
import com.facebook.drawee.drawable.ScalingUtils$ScaleType;
import com.facebook.react.common.ReactConstants;
import kotlin.jvm.internal.Intrinsics;

public final class ImageResizeMode {
    public static final ImageResizeMode INSTANCE = new ImageResizeMode();
    private static final String RESIZE_MODE_CENTER = "center";
    private static final String RESIZE_MODE_CONTAIN = "contain";
    private static final String RESIZE_MODE_COVER = "cover";
    private static final String RESIZE_MODE_NONE = "none";
    private static final String RESIZE_MODE_REPEAT = "repeat";
    private static final String RESIZE_MODE_STRETCH = "stretch";

    private ImageResizeMode() {
    }

    public static final ScalingUtils$ScaleType toScaleType(String str) {
        if (str != null) {
            switch (str.hashCode()) {
                case -1881872635:
                    if (str.equals(RESIZE_MODE_STRETCH)) {
                        ScalingUtils$ScaleType scalingUtils$ScaleType = ScalingUtils$ScaleType.FIT_XY;
                        Intrinsics.checkNotNullExpressionValue(scalingUtils$ScaleType, "FIT_XY");
                        return scalingUtils$ScaleType;
                    }
                    break;
                case -1364013995:
                    if (str.equals(RESIZE_MODE_CENTER)) {
                        ScalingUtils$ScaleType scalingUtils$ScaleType2 = ScalingUtils$ScaleType.CENTER_INSIDE;
                        Intrinsics.checkNotNullExpressionValue(scalingUtils$ScaleType2, "CENTER_INSIDE");
                        return scalingUtils$ScaleType2;
                    }
                    break;
                case -934531685:
                    if (str.equals(RESIZE_MODE_REPEAT)) {
                        return ScaleTypeStartInside.Companion.getINSTANCE();
                    }
                    break;
                case 3387192:
                    if (str.equals("none")) {
                        return ScaleTypeStartInside.Companion.getINSTANCE();
                    }
                    break;
                case 94852023:
                    if (str.equals(RESIZE_MODE_COVER)) {
                        ScalingUtils$ScaleType scalingUtils$ScaleType3 = ScalingUtils$ScaleType.CENTER_CROP;
                        Intrinsics.checkNotNullExpressionValue(scalingUtils$ScaleType3, "CENTER_CROP");
                        return scalingUtils$ScaleType3;
                    }
                    break;
                case 951526612:
                    if (str.equals(RESIZE_MODE_CONTAIN)) {
                        ScalingUtils$ScaleType scalingUtils$ScaleType4 = ScalingUtils$ScaleType.FIT_CENTER;
                        Intrinsics.checkNotNullExpressionValue(scalingUtils$ScaleType4, "FIT_CENTER");
                        return scalingUtils$ScaleType4;
                    }
                    break;
            }
        }
        if (str != null) {
            FLog.w(ReactConstants.TAG, "Invalid resize mode: '" + str + "'");
        }
        return defaultValue();
    }

    public static final Shader.TileMode toTileMode(String str) {
        if (Intrinsics.areEqual((Object) RESIZE_MODE_CONTAIN, (Object) str) || Intrinsics.areEqual((Object) RESIZE_MODE_COVER, (Object) str) || Intrinsics.areEqual((Object) RESIZE_MODE_STRETCH, (Object) str) || Intrinsics.areEqual((Object) RESIZE_MODE_CENTER, (Object) str) || Intrinsics.areEqual((Object) "none", (Object) str)) {
            return Shader.TileMode.CLAMP;
        }
        if (Intrinsics.areEqual((Object) RESIZE_MODE_REPEAT, (Object) str)) {
            return Shader.TileMode.REPEAT;
        }
        if (str != null) {
            FLog.w(ReactConstants.TAG, "Invalid resize mode: '" + str + "'");
        }
        return defaultTileMode();
    }

    public static final ScalingUtils$ScaleType defaultValue() {
        ScalingUtils$ScaleType scalingUtils$ScaleType = ScalingUtils$ScaleType.CENTER_CROP;
        Intrinsics.checkNotNullExpressionValue(scalingUtils$ScaleType, "CENTER_CROP");
        return scalingUtils$ScaleType;
    }

    public static final Shader.TileMode defaultTileMode() {
        return Shader.TileMode.CLAMP;
    }
}
