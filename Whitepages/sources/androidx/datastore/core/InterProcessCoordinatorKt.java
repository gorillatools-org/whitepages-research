package androidx.datastore.core;

import kotlin.jvm.internal.Intrinsics;

public abstract class InterProcessCoordinatorKt {
    public static final InterProcessCoordinator createSingleProcessCoordinator(String str) {
        Intrinsics.checkNotNullParameter(str, "filePath");
        return new SingleProcessCoordinator(str);
    }
}
