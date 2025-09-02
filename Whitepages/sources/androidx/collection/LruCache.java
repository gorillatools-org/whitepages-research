package androidx.collection;

import androidx.collection.internal.Lock;
import androidx.collection.internal.LruHashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;

public class LruCache {
    private int createCount;
    private int evictionCount;
    private int hitCount;
    private final Lock lock;
    private final LruHashMap map;
    private int maxSize;
    private int missCount;
    private int putCount;
    private int size;

    /* access modifiers changed from: protected */
    public Object create(Object obj) {
        Intrinsics.checkNotNullParameter(obj, "key");
        return null;
    }

    /* access modifiers changed from: protected */
    public void entryRemoved(boolean z, Object obj, Object obj2, Object obj3) {
        Intrinsics.checkNotNullParameter(obj, "key");
        Intrinsics.checkNotNullParameter(obj2, "oldValue");
    }

    /* access modifiers changed from: protected */
    public int sizeOf(Object obj, Object obj2) {
        Intrinsics.checkNotNullParameter(obj, "key");
        Intrinsics.checkNotNullParameter(obj2, "value");
        return 1;
    }

    public LruCache(int i) {
        this.maxSize = i;
        if (i > 0) {
            this.map = new LruHashMap(0, 0.75f);
            this.lock = new Lock();
            return;
        }
        throw new IllegalArgumentException("maxSize <= 0");
    }

    public void resize(int i) {
        if (i > 0) {
            synchronized (this.lock) {
                this.maxSize = i;
                Unit unit = Unit.INSTANCE;
            }
            trimToSize(i);
            return;
        }
        throw new IllegalArgumentException("maxSize <= 0");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0021, code lost:
        r0 = create(r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0025, code lost:
        if (r0 != null) goto L_0x0029;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0027, code lost:
        return null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0029, code lost:
        r1 = r5.lock;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x002b, code lost:
        monitor-enter(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:?, code lost:
        r5.createCount++;
        r2 = r5.map.put(r6, r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0038, code lost:
        if (r2 == null) goto L_0x0042;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x003a, code lost:
        r5.map.put(r6, r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0040, code lost:
        r6 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0042, code lost:
        r5.size += safeSizeOf(r6, r0);
        r3 = kotlin.Unit.INSTANCE;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x004d, code lost:
        monitor-exit(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x004e, code lost:
        if (r2 == null) goto L_0x0056;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0050, code lost:
        entryRemoved(false, r6, r0, r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0056, code lost:
        trimToSize(r5.maxSize);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x005d, code lost:
        throw r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:?, code lost:
        return r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:?, code lost:
        return r2;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object get(java.lang.Object r6) {
        /*
            r5 = this;
            java.lang.String r0 = "key"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r6, r0)
            androidx.collection.internal.Lock r0 = r5.lock
            monitor-enter(r0)
            androidx.collection.internal.LruHashMap r1 = r5.map     // Catch:{ all -> 0x0018 }
            java.lang.Object r1 = r1.get(r6)     // Catch:{ all -> 0x0018 }
            if (r1 == 0) goto L_0x001a
            int r6 = r5.hitCount     // Catch:{ all -> 0x0018 }
            int r6 = r6 + 1
            r5.hitCount = r6     // Catch:{ all -> 0x0018 }
            monitor-exit(r0)
            return r1
        L_0x0018:
            r6 = move-exception
            goto L_0x005e
        L_0x001a:
            int r1 = r5.missCount     // Catch:{ all -> 0x0018 }
            int r1 = r1 + 1
            r5.missCount = r1     // Catch:{ all -> 0x0018 }
            monitor-exit(r0)
            java.lang.Object r0 = r5.create(r6)
            if (r0 != 0) goto L_0x0029
            r6 = 0
            return r6
        L_0x0029:
            androidx.collection.internal.Lock r1 = r5.lock
            monitor-enter(r1)
            int r2 = r5.createCount     // Catch:{ all -> 0x0040 }
            int r2 = r2 + 1
            r5.createCount = r2     // Catch:{ all -> 0x0040 }
            androidx.collection.internal.LruHashMap r2 = r5.map     // Catch:{ all -> 0x0040 }
            java.lang.Object r2 = r2.put(r6, r0)     // Catch:{ all -> 0x0040 }
            if (r2 == 0) goto L_0x0042
            androidx.collection.internal.LruHashMap r3 = r5.map     // Catch:{ all -> 0x0040 }
            r3.put(r6, r2)     // Catch:{ all -> 0x0040 }
            goto L_0x004d
        L_0x0040:
            r6 = move-exception
            goto L_0x005c
        L_0x0042:
            int r3 = r5.size     // Catch:{ all -> 0x0040 }
            int r4 = r5.safeSizeOf(r6, r0)     // Catch:{ all -> 0x0040 }
            int r3 = r3 + r4
            r5.size = r3     // Catch:{ all -> 0x0040 }
            kotlin.Unit r3 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x0040 }
        L_0x004d:
            monitor-exit(r1)
            if (r2 == 0) goto L_0x0056
            r1 = 0
            r5.entryRemoved(r1, r6, r0, r2)
            r0 = r2
            goto L_0x005b
        L_0x0056:
            int r6 = r5.maxSize
            r5.trimToSize(r6)
        L_0x005b:
            return r0
        L_0x005c:
            monitor-exit(r1)
            throw r6
        L_0x005e:
            monitor-exit(r0)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.collection.LruCache.get(java.lang.Object):java.lang.Object");
    }

    public final Object put(Object obj, Object obj2) {
        Object put;
        Intrinsics.checkNotNullParameter(obj, "key");
        Intrinsics.checkNotNullParameter(obj2, "value");
        synchronized (this.lock) {
            try {
                this.putCount++;
                this.size += safeSizeOf(obj, obj2);
                put = this.map.put(obj, obj2);
                if (put != null) {
                    this.size -= safeSizeOf(obj, put);
                }
                Unit unit = Unit.INSTANCE;
            } catch (Throwable th) {
                throw th;
            }
        }
        if (put != null) {
            entryRemoved(false, obj, put, obj2);
        }
        trimToSize(this.maxSize);
        return put;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:30:0x0060, code lost:
        throw new java.lang.IllegalStateException("LruCache.sizeOf() is reporting inconsistent results!");
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void trimToSize(int r6) {
        /*
            r5 = this;
        L_0x0000:
            androidx.collection.internal.Lock r0 = r5.lock
            monitor-enter(r0)
            int r1 = r5.size     // Catch:{ all -> 0x0014 }
            if (r1 < 0) goto L_0x0059
            androidx.collection.internal.LruHashMap r1 = r5.map     // Catch:{ all -> 0x0014 }
            boolean r1 = r1.isEmpty()     // Catch:{ all -> 0x0014 }
            if (r1 == 0) goto L_0x0016
            int r1 = r5.size     // Catch:{ all -> 0x0014 }
            if (r1 != 0) goto L_0x0059
            goto L_0x0016
        L_0x0014:
            r6 = move-exception
            goto L_0x0061
        L_0x0016:
            int r1 = r5.size     // Catch:{ all -> 0x0014 }
            if (r1 <= r6) goto L_0x0057
            androidx.collection.internal.LruHashMap r1 = r5.map     // Catch:{ all -> 0x0014 }
            boolean r1 = r1.isEmpty()     // Catch:{ all -> 0x0014 }
            if (r1 == 0) goto L_0x0023
            goto L_0x0057
        L_0x0023:
            androidx.collection.internal.LruHashMap r1 = r5.map     // Catch:{ all -> 0x0014 }
            java.util.Set r1 = r1.getEntries()     // Catch:{ all -> 0x0014 }
            java.lang.Iterable r1 = (java.lang.Iterable) r1     // Catch:{ all -> 0x0014 }
            java.lang.Object r1 = kotlin.collections.CollectionsKt.firstOrNull((java.lang.Iterable) r1)     // Catch:{ all -> 0x0014 }
            java.util.Map$Entry r1 = (java.util.Map.Entry) r1     // Catch:{ all -> 0x0014 }
            if (r1 != 0) goto L_0x0035
            monitor-exit(r0)
            return
        L_0x0035:
            java.lang.Object r2 = r1.getKey()     // Catch:{ all -> 0x0014 }
            java.lang.Object r1 = r1.getValue()     // Catch:{ all -> 0x0014 }
            androidx.collection.internal.LruHashMap r3 = r5.map     // Catch:{ all -> 0x0014 }
            r3.remove(r2)     // Catch:{ all -> 0x0014 }
            int r3 = r5.size     // Catch:{ all -> 0x0014 }
            int r4 = r5.safeSizeOf(r2, r1)     // Catch:{ all -> 0x0014 }
            int r3 = r3 - r4
            r5.size = r3     // Catch:{ all -> 0x0014 }
            int r3 = r5.evictionCount     // Catch:{ all -> 0x0014 }
            r4 = 1
            int r3 = r3 + r4
            r5.evictionCount = r3     // Catch:{ all -> 0x0014 }
            monitor-exit(r0)
            r0 = 0
            r5.entryRemoved(r4, r2, r1, r0)
            goto L_0x0000
        L_0x0057:
            monitor-exit(r0)
            return
        L_0x0059:
            java.lang.String r6 = "LruCache.sizeOf() is reporting inconsistent results!"
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException     // Catch:{ all -> 0x0014 }
            r1.<init>(r6)     // Catch:{ all -> 0x0014 }
            throw r1     // Catch:{ all -> 0x0014 }
        L_0x0061:
            monitor-exit(r0)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.collection.LruCache.trimToSize(int):void");
    }

    public final Object remove(Object obj) {
        Object remove;
        Intrinsics.checkNotNullParameter(obj, "key");
        synchronized (this.lock) {
            try {
                remove = this.map.remove(obj);
                if (remove != null) {
                    this.size -= safeSizeOf(obj, remove);
                }
                Unit unit = Unit.INSTANCE;
            } catch (Throwable th) {
                throw th;
            }
        }
        if (remove != null) {
            entryRemoved(false, obj, remove, (Object) null);
        }
        return remove;
    }

    private final int safeSizeOf(Object obj, Object obj2) {
        int sizeOf = sizeOf(obj, obj2);
        if (sizeOf >= 0) {
            return sizeOf;
        }
        throw new IllegalStateException(("Negative size: " + obj + '=' + obj2).toString());
    }

    public final void evictAll() {
        trimToSize(-1);
    }

    public final int size() {
        int i;
        synchronized (this.lock) {
            i = this.size;
        }
        return i;
    }

    public final int maxSize() {
        int i;
        synchronized (this.lock) {
            i = this.maxSize;
        }
        return i;
    }

    public final int hitCount() {
        int i;
        synchronized (this.lock) {
            i = this.hitCount;
        }
        return i;
    }

    public final int missCount() {
        int i;
        synchronized (this.lock) {
            i = this.missCount;
        }
        return i;
    }

    public final int createCount() {
        int i;
        synchronized (this.lock) {
            i = this.createCount;
        }
        return i;
    }

    public final int putCount() {
        int i;
        synchronized (this.lock) {
            i = this.putCount;
        }
        return i;
    }

    public final int evictionCount() {
        int i;
        synchronized (this.lock) {
            i = this.evictionCount;
        }
        return i;
    }

    public final Map<Object, Object> snapshot() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        synchronized (this.lock) {
            try {
                for (Map.Entry entry : this.map.getEntries()) {
                    linkedHashMap.put(entry.getKey(), entry.getValue());
                }
                Unit unit = Unit.INSTANCE;
            } catch (Throwable th) {
                throw th;
            }
        }
        return linkedHashMap;
    }

    public String toString() {
        String str;
        synchronized (this.lock) {
            try {
                int i = this.hitCount;
                int i2 = this.missCount + i;
                str = "LruCache[maxSize=" + this.maxSize + ",hits=" + this.hitCount + ",misses=" + this.missCount + ",hitRate=" + (i2 != 0 ? (i * 100) / i2 : 0) + "%]";
            } catch (Throwable th) {
                throw th;
            }
        }
        return str;
    }
}
