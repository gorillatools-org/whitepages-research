package com.facebook.drawee.components;

import android.os.Handler;
import android.os.Looper;
import com.facebook.drawee.components.DeferredReleaser;
import java.util.ArrayList;

class DeferredReleaserConcurrentImpl extends DeferredReleaser {
    /* access modifiers changed from: private */
    public final Object mLock = new Object();
    /* access modifiers changed from: private */
    public ArrayList mPendingReleasables = new ArrayList();
    /* access modifiers changed from: private */
    public ArrayList mTempList = new ArrayList();
    private final Handler mUiHandler = new Handler(Looper.getMainLooper());
    private final Runnable releaseRunnable = new Runnable() {
        public void run() {
            synchronized (DeferredReleaserConcurrentImpl.this.mLock) {
                ArrayList r1 = DeferredReleaserConcurrentImpl.this.mTempList;
                DeferredReleaserConcurrentImpl deferredReleaserConcurrentImpl = DeferredReleaserConcurrentImpl.this;
                deferredReleaserConcurrentImpl.mTempList = deferredReleaserConcurrentImpl.mPendingReleasables;
                DeferredReleaserConcurrentImpl.this.mPendingReleasables = r1;
            }
            int size = DeferredReleaserConcurrentImpl.this.mTempList.size();
            for (int i = 0; i < size; i++) {
                ((DeferredReleaser.Releasable) DeferredReleaserConcurrentImpl.this.mTempList.get(i)).release();
            }
            DeferredReleaserConcurrentImpl.this.mTempList.clear();
        }
    };

    /* JADX WARNING: Code restructure failed: missing block: B:18:0x002a, code lost:
        if (r1 == false) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x002c, code lost:
        r2.mUiHandler.post(r2.releaseRunnable);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:?, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void scheduleDeferredRelease(com.facebook.drawee.components.DeferredReleaser.Releasable r3) {
        /*
            r2 = this;
            boolean r0 = com.facebook.drawee.components.DeferredReleaser.isOnUiThread()
            if (r0 != 0) goto L_0x000a
            r3.release()
            return
        L_0x000a:
            java.lang.Object r0 = r2.mLock
            monitor-enter(r0)
            java.util.ArrayList r1 = r2.mPendingReleasables     // Catch:{ all -> 0x0017 }
            boolean r1 = r1.contains(r3)     // Catch:{ all -> 0x0017 }
            if (r1 == 0) goto L_0x0019
            monitor-exit(r0)     // Catch:{ all -> 0x0017 }
            return
        L_0x0017:
            r3 = move-exception
            goto L_0x0034
        L_0x0019:
            java.util.ArrayList r1 = r2.mPendingReleasables     // Catch:{ all -> 0x0017 }
            r1.add(r3)     // Catch:{ all -> 0x0017 }
            java.util.ArrayList r3 = r2.mPendingReleasables     // Catch:{ all -> 0x0017 }
            int r3 = r3.size()     // Catch:{ all -> 0x0017 }
            r1 = 1
            if (r3 != r1) goto L_0x0028
            goto L_0x0029
        L_0x0028:
            r1 = 0
        L_0x0029:
            monitor-exit(r0)     // Catch:{ all -> 0x0017 }
            if (r1 == 0) goto L_0x0033
            android.os.Handler r3 = r2.mUiHandler
            java.lang.Runnable r0 = r2.releaseRunnable
            r3.post(r0)
        L_0x0033:
            return
        L_0x0034:
            monitor-exit(r0)     // Catch:{ all -> 0x0017 }
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.drawee.components.DeferredReleaserConcurrentImpl.scheduleDeferredRelease(com.facebook.drawee.components.DeferredReleaser$Releasable):void");
    }

    public void cancelDeferredRelease(DeferredReleaser.Releasable releasable) {
        synchronized (this.mLock) {
            this.mPendingReleasables.remove(releasable);
        }
    }
}
