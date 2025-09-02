package com.facebook.common.references;

import com.facebook.common.internal.Preconditions;
import com.facebook.common.references.CloseableReference;

public class RefCountCloseableReference extends CloseableReference {
    private RefCountCloseableReference(SharedReference sharedReference, CloseableReference.LeakHandler leakHandler, Throwable th) {
        super(sharedReference, leakHandler, th);
    }

    RefCountCloseableReference(Object obj, ResourceReleaser resourceReleaser, CloseableReference.LeakHandler leakHandler, Throwable th) {
        super(obj, resourceReleaser, leakHandler, th, false);
    }

    public CloseableReference clone() {
        Preconditions.checkState(isValid());
        return new RefCountCloseableReference(this.mSharedReference, this.mLeakHandler, this.mStacktrace);
    }
}
