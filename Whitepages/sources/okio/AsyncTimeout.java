package okio;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;

public class AsyncTimeout extends Timeout {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    public static final long IDLE_TIMEOUT_MILLIS;
    /* access modifiers changed from: private */
    public static final long IDLE_TIMEOUT_NANOS;
    private static final int TIMEOUT_WRITE_SIZE = 65536;
    /* access modifiers changed from: private */
    public static final Condition condition;
    /* access modifiers changed from: private */
    public static AsyncTimeout head;
    /* access modifiers changed from: private */
    public static final ReentrantLock lock;
    /* access modifiers changed from: private */
    public boolean inQueue;
    /* access modifiers changed from: private */
    public AsyncTimeout next;
    /* access modifiers changed from: private */
    public long timeoutAt;

    /* access modifiers changed from: protected */
    public void timedOut() {
    }

    public final void enter() {
        long timeoutNanos = timeoutNanos();
        boolean hasDeadline = hasDeadline();
        if (timeoutNanos != 0 || hasDeadline) {
            Companion.scheduleTimeout(this, timeoutNanos, hasDeadline);
        }
    }

    public final boolean exit() {
        return Companion.cancelScheduledTimeout(this);
    }

    /* access modifiers changed from: private */
    public final long remainingNanos(long j) {
        return this.timeoutAt - j;
    }

    public final Sink sink(Sink sink) {
        Intrinsics.checkNotNullParameter(sink, "sink");
        return new AsyncTimeout$sink$1(this, sink);
    }

    public final Source source(Source source) {
        Intrinsics.checkNotNullParameter(source, "source");
        return new AsyncTimeout$source$1(this, source);
    }

    public final <T> T withTimeout(Function0 function0) {
        Intrinsics.checkNotNullParameter(function0, "block");
        enter();
        try {
            T invoke = function0.invoke();
            InlineMarker.finallyStart(1);
            if (!exit()) {
                InlineMarker.finallyEnd(1);
                return invoke;
            }
            throw access$newTimeoutException((IOException) null);
        } catch (IOException e) {
            e = e;
            if (exit()) {
                e = access$newTimeoutException(e);
            }
            throw e;
        } catch (Throwable th) {
            InlineMarker.finallyStart(1);
            exit();
            InlineMarker.finallyEnd(1);
            throw th;
        }
    }

    public final IOException access$newTimeoutException(IOException iOException) {
        return newTimeoutException(iOException);
    }

    /* access modifiers changed from: protected */
    public IOException newTimeoutException(IOException iOException) {
        InterruptedIOException interruptedIOException = new InterruptedIOException("timeout");
        if (iOException != null) {
            interruptedIOException.initCause(iOException);
        }
        return interruptedIOException;
    }

    private static final class Watchdog extends Thread {
        public Watchdog() {
            super("Okio Watchdog");
            setDaemon(true);
        }

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|(5:5|6|7|19|8)(5:10|11|12|13|(2:15|23)(1:22))|18) */
        /* JADX WARNING: Code restructure failed: missing block: B:16:0x0028, code lost:
            r1.unlock();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:17:0x002b, code lost:
            throw r0;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:9:0x001b, code lost:
            r0 = move-exception;
         */
        /* JADX WARNING: Missing exception handler attribute for start block: B:0:0x0000 */
        /* JADX WARNING: Removed duplicated region for block: B:0:0x0000 A[LOOP:0: B:0:0x0000->B:18:0x0000, LOOP_START, MTH_ENTER_BLOCK, SYNTHETIC, Splitter:B:0:0x0000] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void run() {
            /*
                r3 = this;
            L_0x0000:
                okio.AsyncTimeout$Companion r0 = okio.AsyncTimeout.Companion     // Catch:{ InterruptedException -> 0x0000 }
                java.util.concurrent.locks.ReentrantLock r1 = r0.getLock()     // Catch:{ InterruptedException -> 0x0000 }
                r1.lock()     // Catch:{ InterruptedException -> 0x0000 }
                okio.AsyncTimeout r0 = r0.awaitTimeout$okio()     // Catch:{ all -> 0x001b }
                okio.AsyncTimeout r2 = okio.AsyncTimeout.head     // Catch:{ all -> 0x001b }
                if (r0 != r2) goto L_0x001d
                r0 = 0
                okio.AsyncTimeout.head = r0     // Catch:{ all -> 0x001b }
                r1.unlock()     // Catch:{ InterruptedException -> 0x0000 }
                return
            L_0x001b:
                r0 = move-exception
                goto L_0x0028
            L_0x001d:
                kotlin.Unit r2 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x001b }
                r1.unlock()     // Catch:{ InterruptedException -> 0x0000 }
                if (r0 == 0) goto L_0x0000
                r0.timedOut()     // Catch:{ InterruptedException -> 0x0000 }
                goto L_0x0000
            L_0x0028:
                r1.unlock()     // Catch:{ InterruptedException -> 0x0000 }
                throw r0     // Catch:{ InterruptedException -> 0x0000 }
            */
            throw new UnsupportedOperationException("Method not decompiled: okio.AsyncTimeout.Watchdog.run():void");
        }
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final ReentrantLock getLock() {
            return AsyncTimeout.lock;
        }

        public final Condition getCondition() {
            return AsyncTimeout.condition;
        }

        /* access modifiers changed from: private */
        public final void scheduleTimeout(AsyncTimeout asyncTimeout, long j, boolean z) {
            ReentrantLock lock = AsyncTimeout.Companion.getLock();
            lock.lock();
            try {
                if (!asyncTimeout.inQueue) {
                    asyncTimeout.inQueue = true;
                    if (AsyncTimeout.head == null) {
                        AsyncTimeout.head = new AsyncTimeout();
                        new Watchdog().start();
                    }
                    long nanoTime = System.nanoTime();
                    int i = (j > 0 ? 1 : (j == 0 ? 0 : -1));
                    if (i != 0 && z) {
                        asyncTimeout.timeoutAt = Math.min(j, asyncTimeout.deadlineNanoTime() - nanoTime) + nanoTime;
                    } else if (i != 0) {
                        asyncTimeout.timeoutAt = j + nanoTime;
                    } else if (z) {
                        asyncTimeout.timeoutAt = asyncTimeout.deadlineNanoTime();
                    } else {
                        throw new AssertionError();
                    }
                    long access$remainingNanos = asyncTimeout.remainingNanos(nanoTime);
                    AsyncTimeout access$getHead$cp = AsyncTimeout.head;
                    Intrinsics.checkNotNull(access$getHead$cp);
                    while (true) {
                        if (access$getHead$cp.next == null) {
                            break;
                        }
                        AsyncTimeout access$getNext$p = access$getHead$cp.next;
                        Intrinsics.checkNotNull(access$getNext$p);
                        if (access$remainingNanos < access$getNext$p.remainingNanos(nanoTime)) {
                            break;
                        }
                        access$getHead$cp = access$getHead$cp.next;
                        Intrinsics.checkNotNull(access$getHead$cp);
                    }
                    asyncTimeout.next = access$getHead$cp.next;
                    access$getHead$cp.next = asyncTimeout;
                    if (access$getHead$cp == AsyncTimeout.head) {
                        AsyncTimeout.Companion.getCondition().signal();
                    }
                    Unit unit = Unit.INSTANCE;
                    lock.unlock();
                    return;
                }
                throw new IllegalStateException("Unbalanced enter/exit");
            } catch (Throwable th) {
                lock.unlock();
                throw th;
            }
        }

        /* access modifiers changed from: private */
        public final boolean cancelScheduledTimeout(AsyncTimeout asyncTimeout) {
            ReentrantLock lock = AsyncTimeout.Companion.getLock();
            lock.lock();
            try {
                if (!asyncTimeout.inQueue) {
                    return false;
                }
                asyncTimeout.inQueue = false;
                for (AsyncTimeout access$getHead$cp = AsyncTimeout.head; access$getHead$cp != null; access$getHead$cp = access$getHead$cp.next) {
                    if (access$getHead$cp.next == asyncTimeout) {
                        access$getHead$cp.next = asyncTimeout.next;
                        asyncTimeout.next = null;
                        lock.unlock();
                        return false;
                    }
                }
                lock.unlock();
                return true;
            } finally {
                lock.unlock();
            }
        }

        public final AsyncTimeout awaitTimeout$okio() {
            AsyncTimeout access$getHead$cp = AsyncTimeout.head;
            Intrinsics.checkNotNull(access$getHead$cp);
            AsyncTimeout access$getNext$p = access$getHead$cp.next;
            if (access$getNext$p == null) {
                long nanoTime = System.nanoTime();
                getCondition().await(AsyncTimeout.IDLE_TIMEOUT_MILLIS, TimeUnit.MILLISECONDS);
                AsyncTimeout access$getHead$cp2 = AsyncTimeout.head;
                Intrinsics.checkNotNull(access$getHead$cp2);
                if (access$getHead$cp2.next != null || System.nanoTime() - nanoTime < AsyncTimeout.IDLE_TIMEOUT_NANOS) {
                    return null;
                }
                return AsyncTimeout.head;
            }
            long access$remainingNanos = access$getNext$p.remainingNanos(System.nanoTime());
            if (access$remainingNanos > 0) {
                getCondition().await(access$remainingNanos, TimeUnit.NANOSECONDS);
                return null;
            }
            AsyncTimeout access$getHead$cp3 = AsyncTimeout.head;
            Intrinsics.checkNotNull(access$getHead$cp3);
            access$getHead$cp3.next = access$getNext$p.next;
            access$getNext$p.next = null;
            return access$getNext$p;
        }
    }

    static {
        ReentrantLock reentrantLock = new ReentrantLock();
        lock = reentrantLock;
        Condition newCondition = reentrantLock.newCondition();
        Intrinsics.checkNotNullExpressionValue(newCondition, "lock.newCondition()");
        condition = newCondition;
        long millis = TimeUnit.SECONDS.toMillis(60);
        IDLE_TIMEOUT_MILLIS = millis;
        IDLE_TIMEOUT_NANOS = TimeUnit.MILLISECONDS.toNanos(millis);
    }
}
