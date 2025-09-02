package androidx.work.impl.constraints;

import androidx.work.Logger;
import kotlin.jvm.internal.Intrinsics;

public abstract class WorkConstraintsTrackerKt {
    /* access modifiers changed from: private */
    public static final String TAG;

    static {
        String tagWithPrefix = Logger.tagWithPrefix("WorkConstraintsTracker");
        Intrinsics.checkNotNullExpressionValue(tagWithPrefix, "tagWithPrefix(\"WorkConstraintsTracker\")");
        TAG = tagWithPrefix;
    }
}
