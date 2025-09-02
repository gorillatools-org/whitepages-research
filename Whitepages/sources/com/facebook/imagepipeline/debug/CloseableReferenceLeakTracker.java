package com.facebook.imagepipeline.debug;

import com.facebook.common.references.SharedReference;

public interface CloseableReferenceLeakTracker {
    boolean isSet();

    void trackCloseableReferenceLeak(SharedReference sharedReference, Throwable th);
}
