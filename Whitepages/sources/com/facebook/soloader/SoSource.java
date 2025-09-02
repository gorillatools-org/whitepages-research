package com.facebook.soloader;

import android.os.StrictMode;

public abstract class SoSource {
    public abstract String getName();

    public abstract int loadLibrary(String str, int i, StrictMode.ThreadPolicy threadPolicy);

    /* access modifiers changed from: protected */
    public void prepare(int i) {
    }

    public String toString() {
        return getName();
    }
}
