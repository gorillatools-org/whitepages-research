package androidx.work.impl.constraints.trackers;

import androidx.work.Logger;
import kotlin.jvm.internal.Intrinsics;

public abstract class BroadcastReceiverConstraintTrackerKt {
    /* access modifiers changed from: private */
    public static final String TAG;

    static {
        String tagWithPrefix = Logger.tagWithPrefix("BrdcstRcvrCnstrntTrckr");
        Intrinsics.checkNotNullExpressionValue(tagWithPrefix, "tagWithPrefix(\"BrdcstRcvrCnstrntTrckr\")");
        TAG = tagWithPrefix;
    }
}
