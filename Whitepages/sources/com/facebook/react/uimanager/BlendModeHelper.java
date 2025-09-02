package com.facebook.react.uimanager;

import android.annotation.TargetApi;
import android.graphics.BlendMode;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import androidx.core.view.ViewGroupKt;
import com.facebook.react.R;
import kotlin.jvm.internal.Intrinsics;

@TargetApi(29)
public final class BlendModeHelper {
    public static final BlendModeHelper INSTANCE = new BlendModeHelper();

    private BlendModeHelper() {
    }

    public static final BlendMode parseMixBlendMode(String str) {
        if (str == null || Build.VERSION.SDK_INT < 29) {
            return null;
        }
        switch (str.hashCode()) {
            case -2120744511:
                if (str.equals("luminosity")) {
                    return BlendMode.LUMINOSITY;
                }
                break;
            case -1427739212:
                if (str.equals("hard-light")) {
                    return BlendMode.HARD_LIGHT;
                }
                break;
            case -1338968417:
                if (str.equals("darken")) {
                    return BlendMode.DARKEN;
                }
                break;
            case -1247677005:
                if (str.equals("soft-light")) {
                    return BlendMode.SOFT_LIGHT;
                }
                break;
            case -1091287984:
                if (str.equals("overlay")) {
                    return BlendMode.OVERLAY;
                }
                break;
            case -1039745817:
                if (str.equals("normal")) {
                    return null;
                }
                break;
            case -907689876:
                if (str.equals("screen")) {
                    return BlendMode.SCREEN;
                }
                break;
            case -230491182:
                if (str.equals("saturation")) {
                    return BlendMode.SATURATION;
                }
                break;
            case -120580883:
                if (str.equals("color-dodge")) {
                    return BlendMode.COLOR_DODGE;
                }
                break;
            case 103672:
                if (str.equals("hue")) {
                    return BlendMode.HUE;
                }
                break;
            case 94842723:
                if (str.equals(ViewProps.COLOR)) {
                    return BlendMode.COLOR;
                }
                break;
            case 170546239:
                if (str.equals("lighten")) {
                    return BlendMode.LIGHTEN;
                }
                break;
            case 653829668:
                if (str.equals("multiply")) {
                    return BlendMode.MULTIPLY;
                }
                break;
            case 1242982905:
                if (str.equals("color-burn")) {
                    return BlendMode.COLOR_BURN;
                }
                break;
            case 1686617550:
                if (str.equals("exclusion")) {
                    return BlendMode.EXCLUSION;
                }
                break;
            case 1728361789:
                if (str.equals("difference")) {
                    return BlendMode.DIFFERENCE;
                }
                break;
        }
        throw new IllegalArgumentException("Invalid mix-blend-mode name: " + str);
    }

    public static final boolean needsIsolatedLayer(ViewGroup viewGroup) {
        Intrinsics.checkNotNullParameter(viewGroup, "view");
        for (View tag : ViewGroupKt.getChildren(viewGroup)) {
            if (tag.getTag(R.id.mix_blend_mode) != null) {
                return true;
            }
        }
        return false;
    }
}
