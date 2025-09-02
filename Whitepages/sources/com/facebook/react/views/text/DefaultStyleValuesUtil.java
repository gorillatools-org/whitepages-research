package com.facebook.react.views.text;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import kotlin.jvm.internal.Intrinsics;

public final class DefaultStyleValuesUtil {
    public static final DefaultStyleValuesUtil INSTANCE = new DefaultStyleValuesUtil();

    private DefaultStyleValuesUtil() {
    }

    public static final ColorStateList getDefaultTextColorHint(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return INSTANCE.getDefaultTextAttribute(context, 16842906);
    }

    public static final ColorStateList getDefaultTextColor(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return INSTANCE.getDefaultTextAttribute(context, 16842904);
    }

    public static final int getDefaultTextColorHighlight(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        ColorStateList defaultTextAttribute = INSTANCE.getDefaultTextAttribute(context, 16842905);
        if (defaultTextAttribute != null) {
            return defaultTextAttribute.getDefaultColor();
        }
        return 0;
    }

    private final ColorStateList getDefaultTextAttribute(Context context, int i) {
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(new int[]{i});
        Intrinsics.checkNotNullExpressionValue(obtainStyledAttributes, "obtainStyledAttributes(...)");
        return obtainStyledAttributes.getColorStateList(0);
    }
}
