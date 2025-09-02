package androidx.core.app;

import android.os.Bundle;
import android.os.IBinder;

public abstract class BundleCompat {
    public static void putBinder(Bundle bundle, String str, IBinder iBinder) {
        bundle.putBinder(str, iBinder);
    }
}
