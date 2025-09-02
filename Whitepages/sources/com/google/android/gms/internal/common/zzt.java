package com.google.android.gms.internal.common;

import java.util.Objects;
import org.jspecify.annotations.NullMarked;

@NullMarked
public final class zzt {
    static final CharSequence zza(Object obj, String str) {
        Objects.requireNonNull(obj);
        return obj instanceof CharSequence ? (CharSequence) obj : obj.toString();
    }
}
