package com.facebook.soloader;

import android.content.Context;
import android.os.StrictMode;
import java.io.File;

public class ApplicationSoSource extends SoSource implements RecoverableSoSource {
    private final int flags;
    private DirectorySoSource soSource;

    public ApplicationSoSource(Context context, int i) {
        this.flags = i;
        this.soSource = new DirectorySoSource(getNativeLibDirFromContext(context), i);
    }

    private static File getNativeLibDirFromContext(Context context) {
        return new File(context.getApplicationInfo().nativeLibraryDir);
    }

    public int loadLibrary(String str, int i, StrictMode.ThreadPolicy threadPolicy) {
        return this.soSource.loadLibrary(str, i, threadPolicy);
    }

    /* access modifiers changed from: protected */
    public void prepare(int i) {
        this.soSource.prepare(i);
    }

    public String getName() {
        return "ApplicationSoSource";
    }

    public String toString() {
        return getName() + "[" + this.soSource.toString() + "]";
    }

    public SoSource recover(Context context) {
        this.soSource = new DirectorySoSource(getNativeLibDirFromContext(context), this.flags | 1);
        return this;
    }
}
