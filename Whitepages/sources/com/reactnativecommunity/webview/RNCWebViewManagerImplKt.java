package com.reactnativecommunity.webview;

import kotlin.text.Regex;

public abstract class RNCWebViewManagerImplKt {
    private static final Regex invalidCharRegex = new Regex("[\\\\/%\"]");

    public static final Regex getInvalidCharRegex() {
        return invalidCharRegex;
    }
}
