package com.facebook.fresco.ui.common;

import android.net.Uri;
import com.facebook.common.internal.Fn;
import kotlin.jvm.internal.Intrinsics;

public final class MultiUriHelper {
    public static final MultiUriHelper INSTANCE = new MultiUriHelper();

    private MultiUriHelper() {
    }

    public static final Uri getMainUri(Object obj, Object obj2, Object[] objArr, Fn fn) {
        Intrinsics.checkNotNullParameter(fn, "requestToUri");
        Uri uri = obj != null ? (Uri) fn.apply(obj) : null;
        if (uri != null) {
            return uri;
        }
        if (!(objArr == null || objArr.length == 0)) {
            Object obj3 = objArr[0];
            Uri uri2 = obj3 != null ? (Uri) fn.apply(obj3) : null;
            if (uri2 != null) {
                return uri2;
            }
        }
        if (obj2 != null) {
            return (Uri) fn.apply(obj2);
        }
        return null;
    }
}
