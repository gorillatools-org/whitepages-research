package com.facebook.react.modules.core;

import android.util.SparseArray;
import android.view.Choreographer;
import com.facebook.proguard.annotations.DoNotStrip;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.common.SystemClock;
import com.facebook.react.devsupport.interfaces.DevSupportManager;
import com.facebook.react.jstasks.HeadlessJsTaskContext;
import com.facebook.react.jstasks.HeadlessJsTaskEventListener;
import com.facebook.react.modules.core.ReactChoreographer;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;

public class JavaTimerManager implements LifecycleEventListener, HeadlessJsTaskEventListener {
    private static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final float FRAME_DURATION_MS = 16.666666f;
    private static final float IDLE_CALLBACK_FRAME_DEADLINE_MS = 1.0f;
    private static final int TIMER_QUEUE_CAPACITY = 11;
    /* access modifiers changed from: private */
    public IdleCallbackRunnable currentIdleCallbackRunnable;
    private final DevSupportManager devSupportManager;
    private boolean frameCallbackPosted;
    private boolean frameIdleCallbackPosted;
    /* access modifiers changed from: private */
    public final Object idleCallbackGuard = new Object();
    private final IdleFrameCallback idleFrameCallback = new IdleFrameCallback();
    /* access modifiers changed from: private */
    public final AtomicBoolean isPaused = new AtomicBoolean(true);
    /* access modifiers changed from: private */
    public final AtomicBoolean isRunningTasks = new AtomicBoolean(false);
    /* access modifiers changed from: private */
    public final JavaScriptTimerExecutor javaScriptTimerExecutor;
    /* access modifiers changed from: private */
    public final ReactApplicationContext reactApplicationContext;
    /* access modifiers changed from: private */
    public final ReactChoreographer reactChoreographer;
    /* access modifiers changed from: private */
    public boolean sendIdleEvents;
    private final TimerFrameCallback timerFrameCallback = new TimerFrameCallback();
    /* access modifiers changed from: private */
    public final Object timerGuard = new Object();
    /* access modifiers changed from: private */
    public final SparseArray<Timer> timerIdsToTimers = new SparseArray<>();
    /* access modifiers changed from: private */
    public final PriorityQueue<Timer> timers = new PriorityQueue<>(11, new JavaTimerManager$$ExternalSyntheticLambda1(new JavaTimerManager$$ExternalSyntheticLambda0()));

    public JavaTimerManager(ReactApplicationContext reactApplicationContext2, JavaScriptTimerExecutor javaScriptTimerExecutor2, ReactChoreographer reactChoreographer2, DevSupportManager devSupportManager2) {
        Intrinsics.checkNotNullParameter(reactApplicationContext2, "reactApplicationContext");
        Intrinsics.checkNotNullParameter(javaScriptTimerExecutor2, "javaScriptTimerExecutor");
        Intrinsics.checkNotNullParameter(reactChoreographer2, "reactChoreographer");
        Intrinsics.checkNotNullParameter(devSupportManager2, "devSupportManager");
        this.reactApplicationContext = reactApplicationContext2;
        this.javaScriptTimerExecutor = javaScriptTimerExecutor2;
        this.reactChoreographer = reactChoreographer2;
        this.devSupportManager = devSupportManager2;
        reactApplicationContext2.addLifecycleEventListener(this);
        HeadlessJsTaskContext.Companion.getInstance(reactApplicationContext2).addTaskEventListener(this);
    }

    private static final class Timer {
        private final int interval;
        private final boolean repeat;
        private long targetTime;
        private final int timerId;

        public Timer(int i, long j, int i2, boolean z) {
            this.timerId = i;
            this.targetTime = j;
            this.interval = i2;
            this.repeat = z;
        }

        public final int getTimerId() {
            return this.timerId;
        }

        public final long getTargetTime() {
            return this.targetTime;
        }

        public final void setTargetTime(long j) {
            this.targetTime = j;
        }

        public final int getInterval() {
            return this.interval;
        }

        public final boolean getRepeat() {
            return this.repeat;
        }
    }

    /* access modifiers changed from: private */
    public static final int timers$lambda$0(Timer timer, Timer timer2) {
        return MathKt.getSign(timer.getTargetTime() - timer2.getTargetTime());
    }

    /* access modifiers changed from: private */
    public static final int timers$lambda$1(Function2 function2, Object obj, Object obj2) {
        return ((Number) function2.invoke(obj, obj2)).intValue();
    }

    public void onHostPause() {
        this.isPaused.set(true);
        clearFrameCallback();
        maybeIdleCallback();
    }

    public void onHostDestroy() {
        clearFrameCallback();
        maybeIdleCallback();
    }

    public void onHostResume() {
        this.isPaused.set(false);
        setChoreographerCallback();
        maybeSetChoreographerIdleCallback();
    }

    public void onHeadlessJsTaskStart(int i) {
        if (!this.isRunningTasks.getAndSet(true)) {
            setChoreographerCallback();
            maybeSetChoreographerIdleCallback();
        }
    }

    public void onHeadlessJsTaskFinish(int i) {
        if (!HeadlessJsTaskContext.Companion.getInstance(this.reactApplicationContext).hasActiveTasks()) {
            this.isRunningTasks.set(false);
            clearFrameCallback();
            maybeIdleCallback();
        }
    }

    public void onInstanceDestroy() {
        HeadlessJsTaskContext.Companion.getInstance(this.reactApplicationContext).removeTaskEventListener(this);
        this.reactApplicationContext.removeLifecycleEventListener(this);
        clearFrameCallback();
        clearChoreographerIdleCallback();
    }

    private final void maybeSetChoreographerIdleCallback() {
        synchronized (this.idleCallbackGuard) {
            try {
                if (this.sendIdleEvents) {
                    setChoreographerIdleCallback();
                }
                Unit unit = Unit.INSTANCE;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    private final void maybeIdleCallback() {
        if (this.isPaused.get() && !this.isRunningTasks.get()) {
            clearFrameCallback();
        }
    }

    private final void setChoreographerCallback() {
        if (!this.frameCallbackPosted) {
            this.reactChoreographer.postFrameCallback(ReactChoreographer.CallbackType.TIMERS_EVENTS, this.timerFrameCallback);
            this.frameCallbackPosted = true;
        }
    }

    private final void clearFrameCallback() {
        HeadlessJsTaskContext instance = HeadlessJsTaskContext.Companion.getInstance(this.reactApplicationContext);
        if (this.frameCallbackPosted && this.isPaused.get() && !instance.hasActiveTasks()) {
            this.reactChoreographer.removeFrameCallback(ReactChoreographer.CallbackType.TIMERS_EVENTS, this.timerFrameCallback);
            this.frameCallbackPosted = false;
        }
    }

    private final void setChoreographerIdleCallback() {
        if (!this.frameIdleCallbackPosted) {
            this.reactChoreographer.postFrameCallback(ReactChoreographer.CallbackType.IDLE_EVENT, this.idleFrameCallback);
            this.frameIdleCallbackPosted = true;
        }
    }

    private final void clearChoreographerIdleCallback() {
        if (this.frameIdleCallbackPosted) {
            this.reactChoreographer.removeFrameCallback(ReactChoreographer.CallbackType.IDLE_EVENT, this.idleFrameCallback);
            this.frameIdleCallbackPosted = false;
        }
    }

    @DoNotStrip
    public void createTimer(int i, long j, boolean z) {
        Timer timer = new Timer(i, (SystemClock.nanoTime() / ((long) 1000000)) + j, (int) j, z);
        synchronized (this.timerGuard) {
            this.timers.add(timer);
            this.timerIdsToTimers.put(i, timer);
            Unit unit = Unit.INSTANCE;
        }
    }

    public void createAndMaybeCallTimer(int i, int i2, double d, boolean z) {
        long currentTimeMillis = SystemClock.currentTimeMillis();
        long j = (long) d;
        if (this.devSupportManager.getDevSupportEnabled() && Math.abs(j - currentTimeMillis) > 60000) {
            this.javaScriptTimerExecutor.emitTimeDriftWarning("Debugger and device times have drifted by more than 60s. Please correct this by running adb shell \"date `date +%m%d%H%M%Y.%S`\" on your debugger machine.");
        }
        long max = Math.max(0, (j - currentTimeMillis) + ((long) i2));
        if (i2 != 0 || z) {
            createTimer(i, max, z);
            return;
        }
        WritableArray createArray = Arguments.createArray();
        createArray.pushInt(i);
        JavaScriptTimerExecutor javaScriptTimerExecutor2 = this.javaScriptTimerExecutor;
        Intrinsics.checkNotNull(createArray);
        javaScriptTimerExecutor2.callTimers(createArray);
    }

    @DoNotStrip
    public void deleteTimer(int i) {
        synchronized (this.timerGuard) {
            Timer timer = this.timerIdsToTimers.get(i);
            if (timer != null) {
                this.timerIdsToTimers.remove(i);
                this.timers.remove(timer);
            }
        }
    }

    @DoNotStrip
    public void setSendIdleEvents(boolean z) {
        synchronized (this.idleCallbackGuard) {
            this.sendIdleEvents = z;
            Unit unit = Unit.INSTANCE;
        }
        UiThreadUtil.runOnUiThread(new JavaTimerManager$$ExternalSyntheticLambda2(this, z));
    }

    /* access modifiers changed from: private */
    public static final void setSendIdleEvents$lambda$7(JavaTimerManager javaTimerManager, boolean z) {
        synchronized (javaTimerManager.idleCallbackGuard) {
            if (z) {
                try {
                    javaTimerManager.setChoreographerIdleCallback();
                } catch (Throwable th) {
                    throw th;
                }
            } else {
                javaTimerManager.clearChoreographerIdleCallback();
            }
            Unit unit = Unit.INSTANCE;
        }
    }

    public final boolean hasActiveTimersInRange$ReactAndroid_release(long j) {
        synchronized (this.timerGuard) {
            Timer peek = this.timers.peek();
            if (peek == null) {
                return false;
            }
            if (Companion.isTimerInRange(peek, j)) {
                return true;
            }
            Iterator<Timer> it = this.timers.iterator();
            Intrinsics.checkNotNullExpressionValue(it, "iterator(...)");
            while (it.hasNext()) {
                Timer next = it.next();
                Companion companion = Companion;
                Intrinsics.checkNotNull(next);
                if (companion.isTimerInRange(next, j)) {
                    return true;
                }
            }
            Unit unit = Unit.INSTANCE;
            return false;
        }
    }

    private final class TimerFrameCallback implements Choreographer.FrameCallback {
        private WritableArray timersToCall;

        public TimerFrameCallback() {
        }

        public void doFrame(long j) {
            if (!JavaTimerManager.this.isPaused.get() || JavaTimerManager.this.isRunningTasks.get()) {
                long j2 = j / ((long) 1000000);
                Object access$getTimerGuard$p = JavaTimerManager.this.timerGuard;
                JavaTimerManager javaTimerManager = JavaTimerManager.this;
                synchronized (access$getTimerGuard$p) {
                    while (true) {
                        try {
                            if (javaTimerManager.timers.isEmpty()) {
                                break;
                            }
                            Object peek = javaTimerManager.timers.peek();
                            Intrinsics.checkNotNull(peek);
                            if (((Timer) peek).getTargetTime() >= j2) {
                                break;
                            }
                            Timer timer = (Timer) javaTimerManager.timers.poll();
                            if (timer == null) {
                                break;
                            }
                            if (this.timersToCall == null) {
                                this.timersToCall = Arguments.createArray();
                            }
                            WritableArray writableArray = this.timersToCall;
                            if (writableArray != null) {
                                writableArray.pushInt(timer.getTimerId());
                            }
                            if (timer.getRepeat()) {
                                timer.setTargetTime(((long) timer.getInterval()) + j2);
                                javaTimerManager.timers.add(timer);
                            } else {
                                javaTimerManager.timerIdsToTimers.remove(timer.getTimerId());
                            }
                        } finally {
                        }
                    }
                    Unit unit = Unit.INSTANCE;
                }
                WritableArray writableArray2 = this.timersToCall;
                if (writableArray2 != null) {
                    JavaTimerManager.this.javaScriptTimerExecutor.callTimers(writableArray2);
                    this.timersToCall = null;
                }
                JavaTimerManager.this.reactChoreographer.postFrameCallback(ReactChoreographer.CallbackType.TIMERS_EVENTS, this);
            }
        }
    }

    private final class IdleFrameCallback implements Choreographer.FrameCallback {
        public IdleFrameCallback() {
        }

        public void doFrame(long j) {
            if (!JavaTimerManager.this.isPaused.get() || JavaTimerManager.this.isRunningTasks.get()) {
                IdleCallbackRunnable access$getCurrentIdleCallbackRunnable$p = JavaTimerManager.this.currentIdleCallbackRunnable;
                if (access$getCurrentIdleCallbackRunnable$p != null) {
                    access$getCurrentIdleCallbackRunnable$p.cancel();
                }
                JavaTimerManager javaTimerManager = JavaTimerManager.this;
                javaTimerManager.currentIdleCallbackRunnable = new IdleCallbackRunnable(j);
                JavaTimerManager.this.reactApplicationContext.runOnJSQueueThread(JavaTimerManager.this.currentIdleCallbackRunnable);
                JavaTimerManager.this.reactChoreographer.postFrameCallback(ReactChoreographer.CallbackType.IDLE_EVENT, this);
            }
        }
    }

    private final class IdleCallbackRunnable implements Runnable {
        private final long frameStartTime;
        private volatile boolean isCancelled;

        public IdleCallbackRunnable(long j) {
            this.frameStartTime = j;
        }

        public void run() {
            boolean access$getSendIdleEvents$p;
            if (!this.isCancelled) {
                long uptimeMillis = SystemClock.uptimeMillis() - (this.frameStartTime / ((long) 1000000));
                long currentTimeMillis = SystemClock.currentTimeMillis() - uptimeMillis;
                if (JavaTimerManager.FRAME_DURATION_MS - ((float) uptimeMillis) >= JavaTimerManager.IDLE_CALLBACK_FRAME_DEADLINE_MS) {
                    Object access$getIdleCallbackGuard$p = JavaTimerManager.this.idleCallbackGuard;
                    JavaTimerManager javaTimerManager = JavaTimerManager.this;
                    synchronized (access$getIdleCallbackGuard$p) {
                        access$getSendIdleEvents$p = javaTimerManager.sendIdleEvents;
                        Unit unit = Unit.INSTANCE;
                    }
                    if (access$getSendIdleEvents$p) {
                        JavaTimerManager.this.javaScriptTimerExecutor.callIdleCallbacks((double) currentTimeMillis);
                    }
                    JavaTimerManager.this.currentIdleCallbackRunnable = null;
                }
            }
        }

        public final void cancel() {
            this.isCancelled = true;
        }
    }

    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* access modifiers changed from: private */
        public final boolean isTimerInRange(Timer timer, long j) {
            return !timer.getRepeat() && ((long) timer.getInterval()) < j;
        }
    }
}
