package com.facebook.common.references;

import com.facebook.common.references.CloseableReference;

public class FinalizerCloseableReference extends CloseableReference {
    public CloseableReference clone() {
        return this;
    }

    public void close() {
    }

    FinalizerCloseableReference(Object obj, ResourceReleaser resourceReleaser, CloseableReference.LeakHandler leakHandler, Throwable th) {
        super(obj, resourceReleaser, leakHandler, th, true);
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
        com.facebook.common.logging.FLog.w("FinalizerCloseableReference", "Finalized without closing: %x %x (type = %s)", r3, r4, r0);
        r5.mSharedReference.deleteReference();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0041, code lost:
        super.finalize();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0044, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void finalize() {
        /*
            r5 = this;
            monitor-enter(r5)     // Catch:{ all -> 0x0045 }
            boolean r0 = r5.mIsClosed     // Catch:{ all -> 0x000a }
            if (r0 == 0) goto L_0x000c
            monitor-exit(r5)     // Catch:{ all -> 0x000a }
            super.finalize()
            return
        L_0x000a:
            r0 = move-exception
            goto L_0x0047
        L_0x000c:
            monitor-exit(r5)     // Catch:{ all -> 0x000a }
            com.facebook.common.references.SharedReference r0 = r5.mSharedReference     // Catch:{ all -> 0x0045 }
            java.lang.Object r0 = r0.get()     // Catch:{ all -> 0x0045 }
            java.lang.String r1 = "FinalizerCloseableReference"
            java.lang.String r2 = "Finalized without closing: %x %x (type = %s)"
            int r3 = java.lang.System.identityHashCode(r5)     // Catch:{ all -> 0x0045 }
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)     // Catch:{ all -> 0x0045 }
            com.facebook.common.references.SharedReference r4 = r5.mSharedReference     // Catch:{ all -> 0x0045 }
            int r4 = java.lang.System.identityHashCode(r4)     // Catch:{ all -> 0x0045 }
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)     // Catch:{ all -> 0x0045 }
            if (r0 != 0) goto L_0x002d
            r0 = 0
            goto L_0x0035
        L_0x002d:
            java.lang.Class r0 = r0.getClass()     // Catch:{ all -> 0x0045 }
            java.lang.String r0 = r0.getName()     // Catch:{ all -> 0x0045 }
        L_0x0035:
            java.lang.Object[] r0 = new java.lang.Object[]{r3, r4, r0}     // Catch:{ all -> 0x0045 }
            com.facebook.common.logging.FLog.w((java.lang.String) r1, (java.lang.String) r2, (java.lang.Object[]) r0)     // Catch:{ all -> 0x0045 }
            com.facebook.common.references.SharedReference r0 = r5.mSharedReference     // Catch:{ all -> 0x0045 }
            r0.deleteReference()     // Catch:{ all -> 0x0045 }
            super.finalize()
            return
        L_0x0045:
            r0 = move-exception
            goto L_0x0049
        L_0x0047:
            monitor-exit(r5)     // Catch:{ all -> 0x000a }
            throw r0     // Catch:{ all -> 0x0045 }
        L_0x0049:
            super.finalize()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.common.references.FinalizerCloseableReference.finalize():void");
    }
}
