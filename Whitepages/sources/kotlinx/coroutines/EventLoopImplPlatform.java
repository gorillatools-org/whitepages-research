package kotlinx.coroutines;

import java.util.concurrent.locks.LockSupport;

public abstract class EventLoopImplPlatform extends EventLoop {
    /* access modifiers changed from: protected */
    public abstract Thread getThread();

    /* access modifiers changed from: protected */
    public final void unpark() {
        Thread thread = getThread();
        if (Thread.currentThread() != thread) {
            AbstractTimeSourceKt.getTimeSource();
            LockSupport.unpark(thread);
        }
    }
}
