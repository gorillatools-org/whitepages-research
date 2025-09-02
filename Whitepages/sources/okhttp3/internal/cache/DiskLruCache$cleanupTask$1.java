package okhttp3.internal.cache;

import kotlin.jvm.internal.DefaultConstructorMarker;
import okhttp3.internal.concurrent.Task;

public final class DiskLruCache$cleanupTask$1 extends Task {
    final /* synthetic */ DiskLruCache this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    DiskLruCache$cleanupTask$1(DiskLruCache diskLruCache, String str) {
        super(str, false, 2, (DefaultConstructorMarker) null);
        this.this$0 = diskLruCache;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:?, code lost:
        r6.this$0.mostRecentRebuildFailed = true;
        r6.this$0.journalWriter = okio.Okio.buffer(okio.Okio.blackhole());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x004d, code lost:
        return -1;
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x001f */
    /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x0038 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public long runOnce() {
        /*
            r6 = this;
            okhttp3.internal.cache.DiskLruCache r0 = r6.this$0
            monitor-enter(r0)
            okhttp3.internal.cache.DiskLruCache r1 = r6.this$0     // Catch:{ all -> 0x001d }
            boolean r1 = r1.initialized     // Catch:{ all -> 0x001d }
            r2 = -1
            if (r1 == 0) goto L_0x004c
            okhttp3.internal.cache.DiskLruCache r1 = r6.this$0     // Catch:{ all -> 0x001d }
            boolean r1 = r1.getClosed$okhttp()     // Catch:{ all -> 0x001d }
            if (r1 == 0) goto L_0x0016
            goto L_0x004c
        L_0x0016:
            r1 = 1
            okhttp3.internal.cache.DiskLruCache r4 = r6.this$0     // Catch:{ IOException -> 0x001f }
            r4.trimToSize()     // Catch:{ IOException -> 0x001f }
            goto L_0x0024
        L_0x001d:
            r1 = move-exception
            goto L_0x004e
        L_0x001f:
            okhttp3.internal.cache.DiskLruCache r4 = r6.this$0     // Catch:{ all -> 0x001d }
            r4.mostRecentTrimFailed = r1     // Catch:{ all -> 0x001d }
        L_0x0024:
            okhttp3.internal.cache.DiskLruCache r4 = r6.this$0     // Catch:{ IOException -> 0x0038 }
            boolean r4 = r4.journalRebuildRequired()     // Catch:{ IOException -> 0x0038 }
            if (r4 == 0) goto L_0x004a
            okhttp3.internal.cache.DiskLruCache r4 = r6.this$0     // Catch:{ IOException -> 0x0038 }
            r4.rebuildJournal$okhttp()     // Catch:{ IOException -> 0x0038 }
            okhttp3.internal.cache.DiskLruCache r4 = r6.this$0     // Catch:{ IOException -> 0x0038 }
            r5 = 0
            r4.redundantOpCount = r5     // Catch:{ IOException -> 0x0038 }
            goto L_0x004a
        L_0x0038:
            okhttp3.internal.cache.DiskLruCache r4 = r6.this$0     // Catch:{ all -> 0x001d }
            r4.mostRecentRebuildFailed = r1     // Catch:{ all -> 0x001d }
            okhttp3.internal.cache.DiskLruCache r1 = r6.this$0     // Catch:{ all -> 0x001d }
            okio.Sink r4 = okio.Okio.blackhole()     // Catch:{ all -> 0x001d }
            okio.BufferedSink r4 = okio.Okio.buffer((okio.Sink) r4)     // Catch:{ all -> 0x001d }
            r1.journalWriter = r4     // Catch:{ all -> 0x001d }
        L_0x004a:
            monitor-exit(r0)
            return r2
        L_0x004c:
            monitor-exit(r0)
            return r2
        L_0x004e:
            monitor-exit(r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.cache.DiskLruCache$cleanupTask$1.runOnce():long");
    }
}
