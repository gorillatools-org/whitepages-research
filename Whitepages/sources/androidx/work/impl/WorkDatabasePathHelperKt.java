package androidx.work.impl;

import androidx.work.Logger;
import kotlin.jvm.internal.Intrinsics;

public abstract class WorkDatabasePathHelperKt {
    /* access modifiers changed from: private */
    public static final String[] DATABASE_EXTRA_FILES = {"-journal", "-shm", "-wal"};
    /* access modifiers changed from: private */
    public static final String TAG;

    static {
        String tagWithPrefix = Logger.tagWithPrefix("WrkDbPathHelper");
        Intrinsics.checkNotNullExpressionValue(tagWithPrefix, "tagWithPrefix(\"WrkDbPathHelper\")");
        TAG = tagWithPrefix;
    }
}
