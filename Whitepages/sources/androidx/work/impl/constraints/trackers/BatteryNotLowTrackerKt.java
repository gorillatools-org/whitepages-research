package androidx.work.impl.constraints.trackers;

import androidx.work.Logger;
import kotlin.jvm.internal.Intrinsics;

public abstract class BatteryNotLowTrackerKt {
    /* access modifiers changed from: private */
    public static final String TAG;

    static {
        String tagWithPrefix = Logger.tagWithPrefix("BatteryNotLowTracker");
        Intrinsics.checkNotNullExpressionValue(tagWithPrefix, "tagWithPrefix(\"BatteryNotLowTracker\")");
        TAG = tagWithPrefix;
    }
}
