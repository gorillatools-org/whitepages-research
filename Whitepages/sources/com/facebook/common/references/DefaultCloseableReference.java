package com.facebook.common.references;

import com.facebook.common.internal.Preconditions;
import com.facebook.common.references.CloseableReference;

public class DefaultCloseableReference extends CloseableReference {
    private DefaultCloseableReference(SharedReference sharedReference, CloseableReference.LeakHandler leakHandler, Throwable th) {
        super(sharedReference, leakHandler, th);
    }

    DefaultCloseableReference(Object obj, ResourceReleaser resourceReleaser, CloseableReference.LeakHandler leakHandler, Throwable th) {
        super(obj, resourceReleaser, leakHandler, th, true);
    }

    public CloseableReference clone() {
        Preconditions.checkState(isValid());
        return new DefaultCloseableReference(this.mSharedReference, this.mLeakHandler, this.mStacktrace != null ? new Throwable() : null);
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:12:?, code lost:
        r0 = r5.mSharedReference.get();
        r3 = java.lang.Integer.valueOf(java.lang.System.identityHashCode(r5));
        r4 = java.lang.Integer.valueOf(java.lang.System.identityHashCode(r5.mSharedReference));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0029, code lost:
        if (r0 != null) goto L_0x002d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x002b, code lost:
        r0 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x002d, code lost:
        r0 = r0.getClass().getName();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0035, code lost:
        com.facebook.common.logging.FLog.w("DefaultCloseableReference", "Finalized without closing: %x %x (type = %s)", r3, r4, r0);
        r0 = r5.mLeakHandler;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x003e, code lost:
        if (r0 == null) goto L_0x004a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0040, code lost:
        r0.reportLeak(r5.mSharedReference, r5.mStacktrace);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x004a, code lost:
        close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x004d, code lost:
        super.finalize();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0050, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void finalize() {
        /*
            r5 = this;
            monitor-enter(r5)     // Catch:{ all -> 0x0048 }
            boolean r0 = r5.mIsClosed     // Catch:{ all -> 0x000a }
            if (r0 == 0) goto L_0x000c
            monitor-exit(r5)     // Catch:{ all -> 0x000a }
            super.finalize()
            return
        L_0x000a:
            r0 = move-exception
            goto L_0x0051
        L_0x000c:
            monitor-exit(r5)     // Catch:{ all -> 0x000a }
            com.facebook.common.references.SharedReference r0 = r5.mSharedReference     // Catch:{ all -> 0x0048 }
            java.lang.Object r0 = r0.get()     // Catch:{ all -> 0x0048 }
            java.lang.String r1 = "DefaultCloseableReference"
            java.lang.String r2 = "Finalized without closing: %x %x (type = %s)"
            int r3 = java.lang.System.identityHashCode(r5)     // Catch:{ all -> 0x0048 }
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)     // Catch:{ all -> 0x0048 }
            com.facebook.common.references.SharedReference r4 = r5.mSharedReference     // Catch:{ all -> 0x0048 }
            int r4 = java.lang.System.identityHashCode(r4)     // Catch:{ all -> 0x0048 }
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)     // Catch:{ all -> 0x0048 }
            if (r0 != 0) goto L_0x002d
            r0 = 0
            goto L_0x0035
        L_0x002d:
            java.lang.Class r0 = r0.getClass()     // Catch:{ all -> 0x0048 }
            java.lang.String r0 = r0.getName()     // Catch:{ all -> 0x0048 }
        L_0x0035:
            java.lang.Object[] r0 = new java.lang.Object[]{r3, r4, r0}     // Catch:{ all -> 0x0048 }
            com.facebook.common.logging.FLog.w((java.lang.String) r1, (java.lang.String) r2, (java.lang.Object[]) r0)     // Catch:{ all -> 0x0048 }
            com.facebook.common.references.CloseableReference$LeakHandler r0 = r5.mLeakHandler     // Catch:{ all -> 0x0048 }
            if (r0 == 0) goto L_0x004a
            com.facebook.common.references.SharedReference r1 = r5.mSharedReference     // Catch:{ all -> 0x0048 }
            java.lang.Throwable r2 = r5.mStacktrace     // Catch:{ all -> 0x0048 }
            r0.reportLeak(r1, r2)     // Catch:{ all -> 0x0048 }
            goto L_0x004a
        L_0x0048:
            r0 = move-exception
            goto L_0x0053
        L_0x004a:
            r5.close()     // Catch:{ all -> 0x0048 }
            super.finalize()
            return
        L_0x0051:
            monitor-exit(r5)     // Catch:{ all -> 0x000a }
            throw r0     // Catch:{ all -> 0x0048 }
        L_0x0053:
            super.finalize()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.common.references.DefaultCloseableReference.finalize():void");
    }
}
