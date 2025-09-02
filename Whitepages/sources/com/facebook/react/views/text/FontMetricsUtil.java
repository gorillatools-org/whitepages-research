package com.facebook.react.views.text;

import android.content.Context;
import android.graphics.Rect;
import android.text.Layout;
import android.text.TextPaint;
import android.util.DisplayMetrics;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import kotlin.jvm.internal.Intrinsics;

public final class FontMetricsUtil {
    private static final float AMPLIFICATION_FACTOR = 100.0f;
    private static final String CAP_HEIGHT_MEASUREMENT_TEXT = "T";
    public static final FontMetricsUtil INSTANCE = new FontMetricsUtil();
    private static final String X_HEIGHT_MEASUREMENT_TEXT = "x";

    private FontMetricsUtil() {
    }

    public static final WritableArray getFontMetrics(CharSequence charSequence, Layout layout, TextPaint textPaint, Context context) {
        CharSequence charSequence2 = charSequence;
        Layout layout2 = layout;
        TextPaint textPaint2 = textPaint;
        Intrinsics.checkNotNullParameter(charSequence2, "text");
        Intrinsics.checkNotNullParameter(layout2, "layout");
        Intrinsics.checkNotNullParameter(textPaint2, "paint");
        Intrinsics.checkNotNullParameter(context, "context");
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        WritableArray createArray = Arguments.createArray();
        TextPaint textPaint3 = new TextPaint(textPaint2);
        textPaint3.setTextSize(textPaint3.getTextSize() * AMPLIFICATION_FACTOR);
        Rect rect = new Rect();
        int i = 0;
        int i2 = 1;
        textPaint3.getTextBounds(CAP_HEIGHT_MEASUREMENT_TEXT, 0, 1, rect);
        float height = (((float) rect.height()) / AMPLIFICATION_FACTOR) / displayMetrics.density;
        Rect rect2 = new Rect();
        textPaint3.getTextBounds(X_HEIGHT_MEASUREMENT_TEXT, 0, 1, rect2);
        float height2 = (((float) rect2.height()) / AMPLIFICATION_FACTOR) / displayMetrics.density;
        int lineCount = layout.getLineCount();
        while (i < lineCount) {
            float lineWidth = (charSequence.length() <= 0 || charSequence2.charAt(layout2.getLineEnd(i) - i2) != 10) ? layout2.getLineWidth(i) : layout2.getLineMax(i);
            Rect rect3 = new Rect();
            layout2.getLineBounds(i, rect3);
            WritableMap createMap = Arguments.createMap();
            createMap.putDouble(X_HEIGHT_MEASUREMENT_TEXT, (double) (layout2.getLineLeft(i) / displayMetrics.density));
            createMap.putDouble("y", (double) (((float) rect3.top) / displayMetrics.density));
            createMap.putDouble("width", (double) (lineWidth / displayMetrics.density));
            createMap.putDouble("height", (double) (((float) rect3.height()) / displayMetrics.density));
            createMap.putDouble("descender", (double) (((float) layout2.getLineDescent(i)) / displayMetrics.density));
            createMap.putDouble("ascender", (double) (((float) (-layout2.getLineAscent(i))) / displayMetrics.density));
            createMap.putDouble("baseline", (double) (((float) layout2.getLineBaseline(i)) / displayMetrics.density));
            createMap.putDouble("capHeight", (double) height);
            createMap.putDouble("xHeight", (double) height2);
            createMap.putString("text", charSequence2.subSequence(layout2.getLineStart(i), layout2.getLineEnd(i)).toString());
            createArray.pushMap(createMap);
            i++;
            i2 = 1;
        }
        Intrinsics.checkNotNull(createArray);
        return createArray;
    }
}
