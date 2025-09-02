package com.facebook.imagepipeline.core;

import android.util.Log;
import com.facebook.common.logging.FLog;
import com.facebook.common.references.CloseableReference;
import com.facebook.common.references.ResourceReleaser;
import com.facebook.common.references.SharedReference;
import com.facebook.imagepipeline.debug.CloseableReferenceLeakTracker;
import java.io.Closeable;

public class CloseableReferenceFactory {
    private final CloseableReference.LeakHandler mLeakHandler;

    public CloseableReferenceFactory(final CloseableReferenceLeakTracker closeableReferenceLeakTracker) {
        this.mLeakHandler = new CloseableReference.LeakHandler() {
            public void reportLeak(SharedReference sharedReference, Throwable th) {
                closeableReferenceLeakTracker.trackCloseableReferenceLeak(sharedReference, th);
                Object obj = sharedReference.get();
                FLog.w("Fresco", "Finalized without closing: %x %x (type = %s).\nStack:\n%s", Integer.valueOf(System.identityHashCode(this)), Integer.valueOf(System.identityHashCode(sharedReference)), obj != null ? obj.getClass().getName() : "<value is null>", CloseableReferenceFactory.getStackTraceString(th));
            }

            public boolean requiresStacktrace() {
                return closeableReferenceLeakTracker.isSet();
            }
        };
    }

    public CloseableReference create(Closeable closeable) {
        return CloseableReference.of(closeable, this.mLeakHandler);
    }

    public CloseableReference create(Object obj, ResourceReleaser resourceReleaser) {
        return CloseableReference.of(obj, resourceReleaser, this.mLeakHandler);
    }

    /* access modifiers changed from: private */
    public static String getStackTraceString(Throwable th) {
        if (th == null) {
            return "";
        }
        return Log.getStackTraceString(th);
    }
}
