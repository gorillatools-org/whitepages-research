package com.google.android.gms.internal.measurement;

public final class zzjg extends Exception {
    public zzjg() {
    }

    public zzjg(String str) {
        super(str);
    }

    public zzjg(String str, Throwable th) {
        super("ContentProvider query failed", th);
    }
}
