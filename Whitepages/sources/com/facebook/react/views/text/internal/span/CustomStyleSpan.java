package com.facebook.react.views.text.internal.span;

import android.content.res.AssetManager;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.text.TextPaint;
import android.text.style.MetricAffectingSpan;
import com.facebook.react.common.assets.ReactFontManager;
import com.facebook.react.views.text.ReactTypefaceUtils;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class CustomStyleSpan extends MetricAffectingSpan implements ReactSpan {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private final AssetManager assetManager;
    private final String fontFamily;
    private final String fontFeatureSettings;
    private final int privateStyle;
    private final int privateWeight;

    public final String getFontFeatureSettings() {
        return this.fontFeatureSettings;
    }

    public final String getFontFamily() {
        return this.fontFamily;
    }

    public CustomStyleSpan(int i, int i2, String str, String str2, AssetManager assetManager2) {
        Intrinsics.checkNotNullParameter(assetManager2, "assetManager");
        this.privateStyle = i;
        this.privateWeight = i2;
        this.fontFeatureSettings = str;
        this.fontFamily = str2;
        this.assetManager = assetManager2;
    }

    public void updateDrawState(TextPaint textPaint) {
        Intrinsics.checkNotNullParameter(textPaint, "ds");
        Companion.apply(textPaint, this.privateStyle, this.privateWeight, this.fontFeatureSettings, this.fontFamily, this.assetManager);
    }

    public void updateMeasureState(TextPaint textPaint) {
        Intrinsics.checkNotNullParameter(textPaint, "paint");
        Companion.apply(textPaint, this.privateStyle, this.privateWeight, this.fontFeatureSettings, this.fontFamily, this.assetManager);
    }

    public final int getStyle() {
        int i = this.privateStyle;
        if (i == -1) {
            return 0;
        }
        return i;
    }

    public final int getWeight() {
        int i = this.privateWeight;
        return i == -1 ? ReactFontManager.TypefaceStyle.NORMAL : i;
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* access modifiers changed from: private */
        public final void apply(Paint paint, int i, int i2, String str, String str2, AssetManager assetManager) {
            Typeface applyStyles = ReactTypefaceUtils.applyStyles(paint.getTypeface(), i, i2, str2, assetManager);
            paint.setFontFeatureSettings(str);
            paint.setTypeface(applyStyles);
            paint.setSubpixelText(true);
        }
    }
}
