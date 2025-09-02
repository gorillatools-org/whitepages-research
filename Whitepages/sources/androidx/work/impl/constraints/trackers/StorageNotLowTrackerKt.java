package androidx.work.impl.constraints.trackers;

import androidx.work.Logger;
import kotlin.jvm.internal.Intrinsics;

public abstract class StorageNotLowTrackerKt {
    /* access modifiers changed from: private */
    public static final String TAG;

    static {
        String tagWithPrefix = Logger.tagWithPrefix("StorageNotLowTracker");
        Intrinsics.checkNotNullExpressionValue(tagWithPrefix, "tagWithPrefix(\"StorageNotLowTracker\")");
        TAG = tagWithPrefix;
    }
}
