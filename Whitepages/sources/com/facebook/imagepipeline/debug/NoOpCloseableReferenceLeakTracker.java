package com.facebook.imagepipeline.debug;

import com.facebook.common.references.SharedReference;
import kotlin.jvm.internal.Intrinsics;

public final class NoOpCloseableReferenceLeakTracker implements CloseableReferenceLeakTracker {
    public boolean isSet() {
        return false;
    }

    public void trackCloseableReferenceLeak(SharedReference sharedReference, Throwable th) {
        Intrinsics.checkNotNullParameter(sharedReference, "reference");
    }
}
