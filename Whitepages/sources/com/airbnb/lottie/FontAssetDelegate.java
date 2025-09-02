package com.airbnb.lottie;

import android.graphics.Typeface;

public abstract class FontAssetDelegate {
    public abstract Typeface fetchFont(String str);

    public abstract Typeface fetchFont(String str, String str2, String str3);

    public String getFontPath(String str) {
        return null;
    }

    public String getFontPath(String str, String str2, String str3) {
        return null;
    }
}
