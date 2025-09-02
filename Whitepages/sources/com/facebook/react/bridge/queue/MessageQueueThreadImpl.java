package com.facebook.react.bridge.queue;

import android.os.Looper;
import android.os.Process;
import android.os.SystemClock;
import android.util.Pair;
import com.facebook.common.logging.FLog;
import com.facebook.proguard.annotations.DoNotStrip;
import com.facebook.react.bridge.SoftAssertions;
import com.facebook.react.common.ReactConstants;
import com.facebook.react.common.futures.SimpleSettableFuture;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;

@DoNotStrip
public class MessageQueueThreadImpl implements MessageQueueThread {
    private final String mAssertionErrorMessage;
    private final MessageQueueThreadHandler mHandler;
    private volatile boolean mIsFinished;
    private final Looper mLooper;
    private final String mName;
    private final MessageQueueThreadPerfStats mPerfStats;

    private MessageQueueThreadImpl(String str, Looper looper, QueueThreadExceptionHandler queueThreadExceptionHandler) {
        this(str, looper, queueThreadExceptionHandler, (MessageQueueThreadPerfStats) null);
    }

    private MessageQueueThreadImpl(String str, Looper looper, QueueThreadExceptionHandler queueThreadExceptionHandler, MessageQueueThreadPerfStats messageQueueThreadPerfStats) {
        this.mIsFinished = false;
        this.mName = str;
        this.mLooper = looper;
        this.mHandler = new MessageQueueThreadHandler(looper, queueThreadExceptionHandler);
        this.mPerfStats = messageQueueThreadPerfStats;
        this.mAssertionErrorMessage = "Expected to be called from the '" + getName() + "' thread!";
    }

    @DoNotStrip
    public boolean runOnQueue(Runnable runnable) {
        if (this.mIsFinished) {
            FLog.w(ReactConstants.TAG, "Tried to enqueue runnable on already finished thread: '" + getName() + "... dropping Runnable.");
            return false;
        }
        this.mHandler.post(runnable);
        return true;
    }

    @DoNotStrip
    public <T> Future<T> callOnQueue(Callable<T> callable) {
        SimpleSettableFuture simpleSettableFuture = new SimpleSettableFuture();
        runOnQueue(new MessageQueueThreadImpl$$ExternalSyntheticLambda0(simpleSettableFuture, callable));
        return simpleSettableFuture;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$callOnQueue$0(SimpleSettableFuture simpleSettableFuture, Callable callable) {
        try {
            simpleSettableFuture.set(callable.call());
        } catch (Exception e) {
            simpleSettableFuture.setException(e);
        }
    }

    @DoNotStrip
    public boolean isOnThread() {
        return this.mLooper.getThread() == Thread.currentThread();
    }

    @DoNotStrip
    public void assertIsOnThread() {
        SoftAssertions.assertCondition(isOnThread(), this.mAssertionErrorMessage);
    }

    @DoNotStrip
    public void assertIsOnThread(String str) {
        boolean isOnThread = isOnThread();
        SoftAssertions.assertCondition(isOnThread, this.mAssertionErrorMessage + " " + str);
    }

    @DoNotStrip
    public void quitSynchronous() {
        this.mIsFinished = true;
        this.mLooper.quit();
        if (this.mLooper.getThread() != Thread.currentThread()) {
            try {
                this.mLooper.getThread().join();
            } catch (InterruptedException unused) {
                throw new RuntimeException("Got interrupted waiting to join thread " + this.mName);
            }
        }
    }

    @DoNotStrip
    public MessageQueueThreadPerfStats getPerfStats() {
        return this.mPerfStats;
    }

    @DoNotStrip
    public void resetPerfStats() {
        assignToPerfStats(this.mPerfStats, -1, -1);
        runOnQueue(new MessageQueueThreadImpl$$ExternalSyntheticLambda2(this));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$resetPerfStats$1() {
        assignToPerfStats(this.mPerfStats, SystemClock.uptimeMillis(), SystemClock.currentThreadTimeMillis());
    }

    @DoNotStrip
    public boolean isIdle() {
        return this.mLooper.getQueue().isIdle();
    }

    private static void assignToPerfStats(MessageQueueThreadPerfStats messageQueueThreadPerfStats, long j, long j2) {
        if (messageQueueThreadPerfStats != null) {
            messageQueueThreadPerfStats.wallTime = j;
            messageQueueThreadPerfStats.cpuTime = j2;
        }
    }

    public Looper getLooper() {
        return this.mLooper;
    }

    public String getName() {
        return this.mName;
    }

    /* renamed from: com.facebook.react.bridge.queue.MessageQueueThreadImpl$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$facebook$react$bridge$queue$MessageQueueThreadSpec$ThreadType;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|6) */
        /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        static {
            /*
                com.facebook.react.bridge.queue.MessageQueueThreadSpec$ThreadType[] r0 = com.facebook.react.bridge.queue.MessageQueueThreadSpec.ThreadType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$facebook$react$bridge$queue$MessageQueueThreadSpec$ThreadType = r0
                com.facebook.react.bridge.queue.MessageQueueThreadSpec$ThreadType r1 = com.facebook.react.bridge.queue.MessageQueueThreadSpec.ThreadType.MAIN_UI     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$facebook$react$bridge$queue$MessageQueueThreadSpec$ThreadType     // Catch:{ NoSuchFieldError -> 0x001d }
                com.facebook.react.bridge.queue.MessageQueueThreadSpec$ThreadType r1 = com.facebook.react.bridge.queue.MessageQueueThreadSpec.ThreadType.NEW_BACKGROUND     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.bridge.queue.MessageQueueThreadImpl.AnonymousClass1.<clinit>():void");
        }
    }

    public static MessageQueueThreadImpl create(MessageQueueThreadSpec messageQueueThreadSpec, QueueThreadExceptionHandler queueThreadExceptionHandler) {
        int i = AnonymousClass1.$SwitchMap$com$facebook$react$bridge$queue$MessageQueueThreadSpec$ThreadType[messageQueueThreadSpec.getThreadType().ordinal()];
        if (i == 1) {
            return createForMainThread(messageQueueThreadSpec.getName(), queueThreadExceptionHandler);
        }
        if (i == 2) {
            return startNewBackgroundThread(messageQueueThreadSpec.getName(), messageQueueThreadSpec.getStackSize(), queueThreadExceptionHandler);
        }
        throw new RuntimeException("Unknown thread type: " + messageQueueThreadSpec.getThreadType());
    }

    private static MessageQueueThreadImpl createForMainThread(String str, QueueThreadExceptionHandler queueThreadExceptionHandler) {
        return new MessageQueueThreadImpl(str, Looper.getMainLooper(), queueThreadExceptionHandler);
    }

    private static MessageQueueThreadImpl startNewBackgroundThread(String str, long j, QueueThreadExceptionHandler queueThreadExceptionHandler) {
        SimpleSettableFuture simpleSettableFuture = new SimpleSettableFuture();
        MessageQueueThreadImpl$$ExternalSyntheticLambda1 messageQueueThreadImpl$$ExternalSyntheticLambda1 = new MessageQueueThreadImpl$$ExternalSyntheticLambda1(simpleSettableFuture);
        new Thread((ThreadGroup) null, messageQueueThreadImpl$$ExternalSyntheticLambda1, "mqt_" + str, j).start();
        Pair pair = (Pair) simpleSettableFuture.getOrThrow();
        return new MessageQueueThreadImpl(str, (Looper) pair.first, queueThreadExceptionHandler, (MessageQueueThreadPerfStats) pair.second);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$startNewBackgroundThread$2(SimpleSettableFuture simpleSettableFuture) {
        Process.setThreadPriority(-4);
        Looper.prepare();
        MessageQueueThreadPerfStats messageQueueThreadPerfStats = new MessageQueueThreadPerfStats();
        assignToPerfStats(messageQueueThreadPerfStats, SystemClock.uptimeMillis(), SystemClock.currentThreadTimeMillis());
        simpleSettableFuture.set(new Pair(Looper.myLooper(), messageQueueThreadPerfStats));
        Looper.loop();
    }
}
