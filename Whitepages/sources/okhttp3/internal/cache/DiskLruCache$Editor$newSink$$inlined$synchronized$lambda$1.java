package okhttp3.internal.cache;

import java.io.IOException;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import okhttp3.internal.cache.DiskLruCache;

final class DiskLruCache$Editor$newSink$$inlined$synchronized$lambda$1 extends Lambda implements Function1 {
    final /* synthetic */ int $index$inlined;
    final /* synthetic */ DiskLruCache.Editor this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    DiskLruCache$Editor$newSink$$inlined$synchronized$lambda$1(DiskLruCache.Editor editor, int i) {
        super(1);
        this.this$0 = editor;
        this.$index$inlined = i;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((IOException) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(IOException iOException) {
        Intrinsics.checkNotNullParameter(iOException, "it");
        synchronized (this.this$0.this$0) {
            this.this$0.detach$okhttp();
            Unit unit = Unit.INSTANCE;
        }
    }
}
