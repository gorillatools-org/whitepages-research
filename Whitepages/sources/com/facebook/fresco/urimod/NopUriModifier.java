package com.facebook.fresco.urimod;

import android.net.Uri;
import kotlin.jvm.internal.Intrinsics;

public final class NopUriModifier implements UriModifierInterface {
    public static final NopUriModifier INSTANCE = new NopUriModifier();

    public Uri modifyPrefetchUri(Uri uri, Object obj) {
        Intrinsics.checkNotNullParameter(uri, "uri");
        return uri;
    }

    private NopUriModifier() {
    }
}
