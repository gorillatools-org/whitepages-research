package com.facebook.imagepipeline.cache;

import com.facebook.cache.common.CacheKey;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.logging.FLog;
import com.facebook.common.references.CloseableReference;
import com.facebook.imagepipeline.image.EncodedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class StagingArea {
    private static final Class TAG = StagingArea.class;
    private Map mMap = new HashMap();

    private StagingArea() {
    }

    public static StagingArea getInstance() {
        return new StagingArea();
    }

    public synchronized void put(CacheKey cacheKey, EncodedImage encodedImage) {
        Preconditions.checkNotNull(cacheKey);
        Preconditions.checkArgument(Boolean.valueOf(EncodedImage.isValid(encodedImage)));
        EncodedImage.closeSafely((EncodedImage) this.mMap.put(cacheKey, EncodedImage.cloneOrNull(encodedImage)));
        logStats();
    }

    public void clearAll() {
        ArrayList arrayList;
        synchronized (this) {
            arrayList = new ArrayList(this.mMap.values());
            this.mMap.clear();
        }
        for (int i = 0; i < arrayList.size(); i++) {
            EncodedImage encodedImage = (EncodedImage) arrayList.get(i);
            if (encodedImage != null) {
                encodedImage.close();
            }
        }
    }

    public boolean remove(CacheKey cacheKey) {
        EncodedImage encodedImage;
        Preconditions.checkNotNull(cacheKey);
        synchronized (this) {
            encodedImage = (EncodedImage) this.mMap.remove(cacheKey);
        }
        if (encodedImage == null) {
            return false;
        }
        try {
            return encodedImage.isValid();
        } finally {
            encodedImage.close();
        }
    }

    /* JADX INFO: finally extract failed */
    public synchronized boolean remove(CacheKey cacheKey, EncodedImage encodedImage) {
        Preconditions.checkNotNull(cacheKey);
        Preconditions.checkNotNull(encodedImage);
        Preconditions.checkArgument(Boolean.valueOf(EncodedImage.isValid(encodedImage)));
        EncodedImage encodedImage2 = (EncodedImage) this.mMap.get(cacheKey);
        if (encodedImage2 == null) {
            return false;
        }
        CloseableReference byteBufferRef = encodedImage2.getByteBufferRef();
        CloseableReference byteBufferRef2 = encodedImage.getByteBufferRef();
        if (!(byteBufferRef == null || byteBufferRef2 == null)) {
            try {
                if (byteBufferRef.get() == byteBufferRef2.get()) {
                    this.mMap.remove(cacheKey);
                    CloseableReference.closeSafely(byteBufferRef2);
                    CloseableReference.closeSafely(byteBufferRef);
                    EncodedImage.closeSafely(encodedImage2);
                    logStats();
                    return true;
                }
            } catch (Throwable th) {
                CloseableReference.closeSafely(byteBufferRef2);
                CloseableReference.closeSafely(byteBufferRef);
                EncodedImage.closeSafely(encodedImage2);
                throw th;
            }
        }
        CloseableReference.closeSafely(byteBufferRef2);
        CloseableReference.closeSafely(byteBufferRef);
        EncodedImage.closeSafely(encodedImage2);
        return false;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0044, code lost:
        r0 = r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x004b, code lost:
        return r0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized com.facebook.imagepipeline.image.EncodedImage get(com.facebook.cache.common.CacheKey r6) {
        /*
            r5 = this;
            monitor-enter(r5)
            com.facebook.common.internal.Preconditions.checkNotNull(r6)     // Catch:{ all -> 0x0048 }
            java.util.Map r0 = r5.mMap     // Catch:{ all -> 0x0048 }
            java.lang.Object r0 = r0.get(r6)     // Catch:{ all -> 0x0048 }
            com.facebook.imagepipeline.image.EncodedImage r0 = (com.facebook.imagepipeline.image.EncodedImage) r0     // Catch:{ all -> 0x0048 }
            if (r0 == 0) goto L_0x004a
            monitor-enter(r0)     // Catch:{ all -> 0x0048 }
            boolean r1 = com.facebook.imagepipeline.image.EncodedImage.isValid(r0)     // Catch:{ all -> 0x003d }
            if (r1 != 0) goto L_0x003f
            java.util.Map r1 = r5.mMap     // Catch:{ all -> 0x003d }
            r1.remove(r6)     // Catch:{ all -> 0x003d }
            java.lang.Class r1 = TAG     // Catch:{ all -> 0x003d }
            java.lang.String r2 = "Found closed reference %d for key %s (%d)"
            int r3 = java.lang.System.identityHashCode(r0)     // Catch:{ all -> 0x003d }
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)     // Catch:{ all -> 0x003d }
            java.lang.String r4 = r6.getUriString()     // Catch:{ all -> 0x003d }
            int r6 = java.lang.System.identityHashCode(r6)     // Catch:{ all -> 0x003d }
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)     // Catch:{ all -> 0x003d }
            java.lang.Object[] r6 = new java.lang.Object[]{r3, r4, r6}     // Catch:{ all -> 0x003d }
            com.facebook.common.logging.FLog.w((java.lang.Class) r1, (java.lang.String) r2, (java.lang.Object[]) r6)     // Catch:{ all -> 0x003d }
            monitor-exit(r0)     // Catch:{ all -> 0x003d }
            monitor-exit(r5)
            r6 = 0
            return r6
        L_0x003d:
            r6 = move-exception
            goto L_0x0046
        L_0x003f:
            com.facebook.imagepipeline.image.EncodedImage r6 = com.facebook.imagepipeline.image.EncodedImage.cloneOrNull(r0)     // Catch:{ all -> 0x003d }
            monitor-exit(r0)     // Catch:{ all -> 0x003d }
            r0 = r6
            goto L_0x004a
        L_0x0046:
            monitor-exit(r0)     // Catch:{ all -> 0x003d }
            throw r6     // Catch:{ all -> 0x0048 }
        L_0x0048:
            r6 = move-exception
            goto L_0x004c
        L_0x004a:
            monitor-exit(r5)
            return r0
        L_0x004c:
            monitor-exit(r5)     // Catch:{ all -> 0x0048 }
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.imagepipeline.cache.StagingArea.get(com.facebook.cache.common.CacheKey):com.facebook.imagepipeline.image.EncodedImage");
    }

    public synchronized boolean containsKey(CacheKey cacheKey) {
        Preconditions.checkNotNull(cacheKey);
        if (!this.mMap.containsKey(cacheKey)) {
            return false;
        }
        EncodedImage encodedImage = (EncodedImage) this.mMap.get(cacheKey);
        synchronized (encodedImage) {
            if (EncodedImage.isValid(encodedImage)) {
                return true;
            }
            this.mMap.remove(cacheKey);
            FLog.w(TAG, "Found closed reference %d for key %s (%d)", Integer.valueOf(System.identityHashCode(encodedImage)), cacheKey.getUriString(), Integer.valueOf(System.identityHashCode(cacheKey)));
            return false;
        }
    }

    private synchronized void logStats() {
        FLog.v(TAG, "Count = %d", (Object) Integer.valueOf(this.mMap.size()));
    }
}
