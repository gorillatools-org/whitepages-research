package androidx.core.app;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

public abstract class PendingIntentCompat {
    public static PendingIntent getActivity(Context context, int i, Intent intent, int i2, boolean z) {
        return PendingIntent.getActivity(context, i, intent, addMutabilityFlags(z, i2));
    }

    private static int addMutabilityFlags(boolean z, int i) {
        int i2;
        if (!z) {
            i2 = 67108864;
        } else if (Build.VERSION.SDK_INT < 31) {
            return i;
        } else {
            i2 = 33554432;
        }
        return i | i2;
    }
}
