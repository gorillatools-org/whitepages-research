package com.facebook.react.views.text;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import com.facebook.react.uimanager.ViewProps;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class ReactFontManager {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    public static ReactFontManager instance;
    private final com.facebook.react.common.assets.ReactFontManager delegate;

    public /* synthetic */ ReactFontManager(com.facebook.react.common.assets.ReactFontManager reactFontManager, DefaultConstructorMarker defaultConstructorMarker) {
        this(reactFontManager);
    }

    public static final ReactFontManager getInstance() {
        return Companion.getInstance();
    }

    private ReactFontManager(com.facebook.react.common.assets.ReactFontManager reactFontManager) {
        this.delegate = reactFontManager;
    }

    public final Typeface getTypeface(String str, int i, AssetManager assetManager) {
        Intrinsics.checkNotNullParameter(str, "fontFamilyName");
        Intrinsics.checkNotNullParameter(assetManager, "assetManager");
        return this.delegate.getTypeface(str, i, assetManager);
    }

    public final Typeface getTypeface(String str, int i, boolean z, AssetManager assetManager) {
        Intrinsics.checkNotNullParameter(str, "fontFamilyName");
        Intrinsics.checkNotNullParameter(assetManager, "assetManager");
        return this.delegate.getTypeface(str, i, z, assetManager);
    }

    public final Typeface getTypeface(String str, int i, int i2, AssetManager assetManager) {
        Intrinsics.checkNotNullParameter(str, "fontFamilyName");
        Intrinsics.checkNotNullParameter(assetManager, "assetManager");
        return this.delegate.getTypeface(str, i, i2, assetManager);
    }

    public final void addCustomFont(Context context, String str, int i) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(str, ViewProps.FONT_FAMILY);
        this.delegate.addCustomFont(context, str, i);
    }

    public final void addCustomFont(String str, Typeface typeface) {
        Intrinsics.checkNotNullParameter(str, ViewProps.FONT_FAMILY);
        this.delegate.addCustomFont(str, typeface);
    }

    public final void setTypeface(String str, int i, Typeface typeface) {
        Intrinsics.checkNotNullParameter(str, "fontFamilyName");
        Intrinsics.checkNotNullParameter(typeface, "typeface");
        this.delegate.setTypeface(str, i, typeface);
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final ReactFontManager getInstance() {
            ReactFontManager access$getInstance$cp = ReactFontManager.instance;
            if (access$getInstance$cp != null) {
                return access$getInstance$cp;
            }
            ReactFontManager reactFontManager = new ReactFontManager(com.facebook.react.common.assets.ReactFontManager.Companion.getInstance(), (DefaultConstructorMarker) null);
            ReactFontManager.instance = reactFontManager;
            return reactFontManager;
        }
    }
}
