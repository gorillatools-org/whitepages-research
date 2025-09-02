package com.facebook.internal;

import kotlin.text.StringsKt;

public final class InternalSettings {
    public static final InternalSettings INSTANCE = new InternalSettings();
    private static volatile String customUserAgent;

    private InternalSettings() {
    }

    public static final String getCustomUserAgent() {
        return customUserAgent;
    }

    public static final boolean isUnityApp() {
        String str = customUserAgent;
        return str != null && StringsKt.startsWith$default(str, "Unity.", false, 2, (Object) null);
    }
}
