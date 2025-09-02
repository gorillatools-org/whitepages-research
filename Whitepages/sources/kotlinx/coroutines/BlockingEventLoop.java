package kotlinx.coroutines;

public final class BlockingEventLoop extends EventLoopImplBase {
    private final Thread thread;

    /* access modifiers changed from: protected */
    public Thread getThread() {
        return this.thread;
    }

    public BlockingEventLoop(Thread thread2) {
        this.thread = thread2;
    }
}
