package com.facebook.common.references;

import android.graphics.Bitmap;
import com.facebook.common.internal.Closeables;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.logging.FLog;
import java.io.Closeable;
import java.io.IOException;

public abstract class CloseableReference implements Cloneable, Closeable {
    private static final ResourceReleaser DEFAULT_CLOSEABLE_RELEASER = new ResourceReleaser() {
        public void release(Closeable closeable) {
            try {
                Closeables.close(closeable, true);
            } catch (IOException unused) {
            }
        }
    };
    private static final LeakHandler DEFAULT_LEAK_HANDLER = new LeakHandler() {
        public boolean requiresStacktrace() {
            return false;
        }

        public void reportLeak(SharedReference sharedReference, Throwable th) {
            String str;
            Object obj = sharedReference.get();
            Class r0 = CloseableReference.TAG;
            Integer valueOf = Integer.valueOf(System.identityHashCode(this));
            Integer valueOf2 = Integer.valueOf(System.identityHashCode(sharedReference));
            if (obj == null) {
                str = null;
            } else {
                str = obj.getClass().getName();
            }
            FLog.w(r0, "Finalized without closing: %x %x (type = %s)", valueOf, valueOf2, str);
        }
    };
    /* access modifiers changed from: private */
    public static Class TAG = CloseableReference.class;
    private static int sBitmapCloseableRefType;
    protected boolean mIsClosed = false;
    protected final LeakHandler mLeakHandler;
    protected final SharedReference mSharedReference;
    protected final Throwable mStacktrace;

    public interface LeakHandler {
        void reportLeak(SharedReference sharedReference, Throwable th);

        boolean requiresStacktrace();
    }

    public abstract CloseableReference clone();

    protected CloseableReference(SharedReference sharedReference, LeakHandler leakHandler, Throwable th) {
        this.mSharedReference = (SharedReference) Preconditions.checkNotNull(sharedReference);
        sharedReference.addReference();
        this.mLeakHandler = leakHandler;
        this.mStacktrace = th;
    }

    protected CloseableReference(Object obj, ResourceReleaser resourceReleaser, LeakHandler leakHandler, Throwable th, boolean z) {
        this.mSharedReference = new SharedReference(obj, resourceReleaser, z);
        this.mLeakHandler = leakHandler;
        this.mStacktrace = th;
    }

    public static CloseableReference of(Closeable closeable) {
        return of((Object) closeable, DEFAULT_CLOSEABLE_RELEASER);
    }

    public static CloseableReference of(Object obj, ResourceReleaser resourceReleaser) {
        return of(obj, resourceReleaser, DEFAULT_LEAK_HANDLER);
    }

    public static CloseableReference of(Closeable closeable, LeakHandler leakHandler) {
        Throwable th = null;
        if (closeable == null) {
            return null;
        }
        ResourceReleaser resourceReleaser = DEFAULT_CLOSEABLE_RELEASER;
        if (leakHandler.requiresStacktrace()) {
            th = new Throwable();
        }
        return of(closeable, resourceReleaser, leakHandler, th);
    }

    public static CloseableReference of(Object obj, ResourceReleaser resourceReleaser, LeakHandler leakHandler) {
        Throwable th = null;
        if (obj == null) {
            return null;
        }
        if (leakHandler.requiresStacktrace()) {
            th = new Throwable();
        }
        return of(obj, resourceReleaser, leakHandler, th);
    }

    public static CloseableReference of(Object obj, ResourceReleaser resourceReleaser, LeakHandler leakHandler, Throwable th) {
        if (obj == null) {
            return null;
        }
        if ((obj instanceof Bitmap) || (obj instanceof HasBitmap)) {
            int i = sBitmapCloseableRefType;
            if (i == 1) {
                return new FinalizerCloseableReference(obj, resourceReleaser, leakHandler, th);
            }
            if (i == 2) {
                return new RefCountCloseableReference(obj, resourceReleaser, leakHandler, th);
            }
            if (i == 3) {
                return new NoOpCloseableReference(obj);
            }
        }
        return new DefaultCloseableReference(obj, resourceReleaser, leakHandler, th);
    }

    public synchronized Object get() {
        Preconditions.checkState(!this.mIsClosed);
        return Preconditions.checkNotNull(this.mSharedReference.get());
    }

    public synchronized CloseableReference cloneOrNull() {
        if (!isValid()) {
            return null;
        }
        return clone();
    }

    public synchronized boolean isValid() {
        return !this.mIsClosed;
    }

    public int getValueHash() {
        if (isValid()) {
            return System.identityHashCode(this.mSharedReference.get());
        }
        return 0;
    }

    public void close() {
        synchronized (this) {
            try {
                if (!this.mIsClosed) {
                    this.mIsClosed = true;
                    this.mSharedReference.deleteReference();
                }
            } catch (Throwable th) {
                while (true) {
                    throw th;
                }
            }
        }
    }

    public static boolean isValid(CloseableReference closeableReference) {
        return closeableReference != null && closeableReference.isValid();
    }

    public static CloseableReference cloneOrNull(CloseableReference closeableReference) {
        if (closeableReference != null) {
            return closeableReference.cloneOrNull();
        }
        return null;
    }

    public static void closeSafely(CloseableReference closeableReference) {
        if (closeableReference != null) {
            closeableReference.close();
        }
    }
}
