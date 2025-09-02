package androidx.concurrent.futures;

import com.google.common.util.concurrent.ListenableFuture;
import java.lang.ref.WeakReference;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

public abstract class CallbackToFutureAdapter {

    public interface Resolver {
        Object attachCompleter(Completer completer);
    }

    public static ListenableFuture getFuture(Resolver resolver) {
        Completer completer = new Completer();
        SafeFuture safeFuture = new SafeFuture(completer);
        completer.future = safeFuture;
        completer.tag = resolver.getClass();
        try {
            Object attachCompleter = resolver.attachCompleter(completer);
            if (attachCompleter != null) {
                completer.tag = attachCompleter;
            }
        } catch (Exception e) {
            safeFuture.setException(e);
        }
        return safeFuture;
    }

    private static final class SafeFuture implements ListenableFuture {
        final WeakReference completerWeakReference;
        private final AbstractResolvableFuture delegate = new AbstractResolvableFuture() {
            /* access modifiers changed from: protected */
            public String pendingToString() {
                Completer completer = (Completer) SafeFuture.this.completerWeakReference.get();
                if (completer == null) {
                    return "Completer object has been garbage collected, future will fail soon";
                }
                return "tag=[" + completer.tag + "]";
            }
        };

        SafeFuture(Completer completer) {
            this.completerWeakReference = new WeakReference(completer);
        }

        public boolean cancel(boolean z) {
            Completer completer = (Completer) this.completerWeakReference.get();
            boolean cancel = this.delegate.cancel(z);
            if (cancel && completer != null) {
                completer.fireCancellationListeners();
            }
            return cancel;
        }

        /* access modifiers changed from: package-private */
        public boolean cancelWithoutNotifyingCompleter(boolean z) {
            return this.delegate.cancel(z);
        }

        /* access modifiers changed from: package-private */
        public boolean set(Object obj) {
            return this.delegate.set(obj);
        }

        /* access modifiers changed from: package-private */
        public boolean setException(Throwable th) {
            return this.delegate.setException(th);
        }

        public boolean isCancelled() {
            return this.delegate.isCancelled();
        }

        public boolean isDone() {
            return this.delegate.isDone();
        }

        public Object get() {
            return this.delegate.get();
        }

        public Object get(long j, TimeUnit timeUnit) {
            return this.delegate.get(j, timeUnit);
        }

        public void addListener(Runnable runnable, Executor executor) {
            this.delegate.addListener(runnable, executor);
        }

        public String toString() {
            return this.delegate.toString();
        }
    }

    public static final class Completer {
        private boolean attemptedSetting;
        private ResolvableFuture cancellationFuture = ResolvableFuture.create();
        SafeFuture future;
        Object tag;

        Completer() {
        }

        public boolean set(Object obj) {
            boolean z = true;
            this.attemptedSetting = true;
            SafeFuture safeFuture = this.future;
            if (safeFuture == null || !safeFuture.set(obj)) {
                z = false;
            }
            if (z) {
                setCompletedNormally();
            }
            return z;
        }

        public boolean setException(Throwable th) {
            boolean z = true;
            this.attemptedSetting = true;
            SafeFuture safeFuture = this.future;
            if (safeFuture == null || !safeFuture.setException(th)) {
                z = false;
            }
            if (z) {
                setCompletedNormally();
            }
            return z;
        }

        public boolean setCancelled() {
            boolean z = true;
            this.attemptedSetting = true;
            SafeFuture safeFuture = this.future;
            if (safeFuture == null || !safeFuture.cancelWithoutNotifyingCompleter(true)) {
                z = false;
            }
            if (z) {
                setCompletedNormally();
            }
            return z;
        }

        /* access modifiers changed from: package-private */
        public void fireCancellationListeners() {
            this.tag = null;
            this.future = null;
            this.cancellationFuture.set((Object) null);
        }

        private void setCompletedNormally() {
            this.tag = null;
            this.future = null;
            this.cancellationFuture = null;
        }

        /* access modifiers changed from: protected */
        public void finalize() {
            ResolvableFuture resolvableFuture;
            SafeFuture safeFuture = this.future;
            if (safeFuture != null && !safeFuture.isDone()) {
                safeFuture.setException(new FutureGarbageCollectedException("The completer object was garbage collected - this future would otherwise never complete. The tag was: " + this.tag));
            }
            if (!this.attemptedSetting && (resolvableFuture = this.cancellationFuture) != null) {
                resolvableFuture.set((Object) null);
            }
        }
    }

    static final class FutureGarbageCollectedException extends Throwable {
        FutureGarbageCollectedException(String str) {
            super(str);
        }

        public synchronized Throwable fillInStackTrace() {
            return this;
        }
    }
}
