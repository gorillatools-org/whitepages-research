package okhttp3.internal.cache;

import kotlin.Unit;
import okhttp3.internal.cache.DiskLruCache;
import okio.ForwardingSource;
import okio.Source;

public final class DiskLruCache$Entry$newSource$1 extends ForwardingSource {
    final /* synthetic */ Source $fileSource;
    private boolean closed;
    final /* synthetic */ DiskLruCache.Entry this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    DiskLruCache$Entry$newSource$1(DiskLruCache.Entry entry, Source source, Source source2) {
        super(source2);
        this.this$0 = entry;
        this.$fileSource = source;
    }

    public void close() {
        super.close();
        if (!this.closed) {
            this.closed = true;
            synchronized (this.this$0.this$0) {
                try {
                    DiskLruCache.Entry entry = this.this$0;
                    entry.setLockingSourceCount$okhttp(entry.getLockingSourceCount$okhttp() - 1);
                    if (this.this$0.getLockingSourceCount$okhttp() == 0 && this.this$0.getZombie$okhttp()) {
                        DiskLruCache.Entry entry2 = this.this$0;
                        entry2.this$0.removeEntry$okhttp(entry2);
                    }
                    Unit unit = Unit.INSTANCE;
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
    }
}
