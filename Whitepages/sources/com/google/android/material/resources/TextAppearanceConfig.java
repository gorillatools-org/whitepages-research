package com.google.android.material.resources;

public abstract class TextAppearanceConfig {
    private static boolean shouldLoadFontSynchronously;

    public static boolean shouldLoadFontSynchronously() {
        return shouldLoadFontSynchronously;
    }
}
