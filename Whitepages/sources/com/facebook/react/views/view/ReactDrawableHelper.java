package com.facebook.react.views.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.util.TypedValue;
import com.facebook.react.bridge.JSApplicationIllegalArgumentException;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.uimanager.PixelUtil;
import com.facebook.react.uimanager.ViewProps;
import kotlin.jvm.internal.Intrinsics;

public final class ReactDrawableHelper {
    public static final ReactDrawableHelper INSTANCE = new ReactDrawableHelper();
    private static final TypedValue resolveOutValue = new TypedValue();

    private ReactDrawableHelper() {
    }

    public static final Drawable createDrawableFromJSDescription(Context context, ReadableMap readableMap) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(readableMap, "drawableDescriptionDict");
        String string = readableMap.getString("type");
        if (Intrinsics.areEqual((Object) "ThemeAttrAndroid", (Object) string)) {
            String string2 = readableMap.getString("attribute");
            if (string2 != null) {
                ReactDrawableHelper reactDrawableHelper = INSTANCE;
                int attrId = reactDrawableHelper.getAttrId(context, string2);
                if (context.getTheme().resolveAttribute(attrId, resolveOutValue, true)) {
                    return reactDrawableHelper.setRadius(readableMap, reactDrawableHelper.getDefaultThemeDrawable(context));
                }
                throw new JSApplicationIllegalArgumentException("Attribute " + string2 + " with id " + attrId + " couldn't be resolved into a drawable");
            }
            throw new JSApplicationIllegalArgumentException("JS description missing 'attribute' field");
        } else if (Intrinsics.areEqual((Object) "RippleAndroid", (Object) string)) {
            ReactDrawableHelper reactDrawableHelper2 = INSTANCE;
            return reactDrawableHelper2.setRadius(readableMap, reactDrawableHelper2.getRippleDrawable(context, readableMap));
        } else {
            throw new JSApplicationIllegalArgumentException("Invalid type for android drawable: " + string);
        }
    }

    @SuppressLint({"DiscouragedApi", "InternalInsetResource"})
    private final int getAttrId(Context context, String str) {
        if (Intrinsics.areEqual((Object) "selectableItemBackground", (Object) str)) {
            return 16843534;
        }
        if (Intrinsics.areEqual((Object) "selectableItemBackgroundBorderless", (Object) str)) {
            return 16843868;
        }
        return context.getResources().getIdentifier(str, "attr", "android");
    }

    private final Drawable getDefaultThemeDrawable(Context context) {
        return context.getResources().getDrawable(resolveOutValue.resourceId, context.getTheme());
    }

    private final RippleDrawable getRippleDrawable(Context context, ReadableMap readableMap) {
        int color = getColor(context, readableMap);
        return new RippleDrawable(new ColorStateList(new int[][]{new int[0]}, new int[]{color}), (Drawable) null, getMask(readableMap));
    }

    private final Drawable setRadius(ReadableMap readableMap, Drawable drawable) {
        if (readableMap.hasKey("rippleRadius") && (drawable instanceof RippleDrawable)) {
            ((RippleDrawable) drawable).setRadius((int) PixelUtil.toPixelFromDIP(readableMap.getDouble("rippleRadius")));
        }
        return drawable;
    }

    private final int getColor(Context context, ReadableMap readableMap) {
        if (readableMap.hasKey(ViewProps.COLOR) && !readableMap.isNull(ViewProps.COLOR)) {
            return readableMap.getInt(ViewProps.COLOR);
        }
        Resources.Theme theme = context.getTheme();
        TypedValue typedValue = resolveOutValue;
        if (theme.resolveAttribute(16843820, typedValue, true)) {
            return context.getResources().getColor(typedValue.resourceId, context.getTheme());
        }
        throw new JSApplicationIllegalArgumentException("Attribute colorControlHighlight couldn't be resolved into a drawable");
    }

    private final Drawable getMask(ReadableMap readableMap) {
        if (!readableMap.hasKey("borderless") || readableMap.isNull("borderless") || !readableMap.getBoolean("borderless")) {
            return new ColorDrawable(-1);
        }
        return null;
    }
}
