package okhttp3.internal.connection;

import kotlin.jvm.internal.DefaultConstructorMarker;
import okhttp3.internal.concurrent.Task;

public final class RealConnectionPool$cleanupTask$1 extends Task {
    final /* synthetic */ RealConnectionPool this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    RealConnectionPool$cleanupTask$1(RealConnectionPool realConnectionPool, String str) {
        super(str, false, 2, (DefaultConstructorMarker) null);
        this.this$0 = realConnectionPool;
    }

    public long runOnce() {
        return this.this$0.cleanup(System.nanoTime());
    }
}
