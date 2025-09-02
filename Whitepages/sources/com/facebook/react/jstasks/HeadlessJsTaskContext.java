package com.facebook.react.jstasks;

import android.util.SparseArray;
import com.facebook.infer.annotation.Assertions;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactSoftExceptionLogger;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.common.LifecycleState;
import com.facebook.react.modules.appregistry.AppRegistry;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import java.lang.ref.WeakReference;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicInteger;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class HeadlessJsTaskContext {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    public static final WeakHashMap<ReactContext, HeadlessJsTaskContext> INSTANCES = new WeakHashMap<>();
    private final Map<Integer, HeadlessJsTaskConfig> activeTaskConfigs;
    private final Set<Integer> activeTasks;
    private final Set<HeadlessJsTaskEventListener> headlessJsTaskEventListeners;
    private final AtomicInteger lastTaskId;
    private final WeakReference<ReactContext> reactContext;
    private final SparseArray<Runnable> taskTimeouts;

    public /* synthetic */ HeadlessJsTaskContext(ReactContext reactContext2, DefaultConstructorMarker defaultConstructorMarker) {
        this(reactContext2);
    }

    public static final HeadlessJsTaskContext getInstance(ReactContext reactContext2) {
        return Companion.getInstance(reactContext2);
    }

    private HeadlessJsTaskContext(ReactContext reactContext2) {
        this.reactContext = new WeakReference<>(reactContext2);
        this.headlessJsTaskEventListeners = new CopyOnWriteArraySet();
        this.lastTaskId = new AtomicInteger(0);
        this.activeTasks = new CopyOnWriteArraySet();
        this.activeTaskConfigs = new ConcurrentHashMap();
        this.taskTimeouts = new SparseArray<>();
    }

    public final synchronized void addTaskEventListener(HeadlessJsTaskEventListener headlessJsTaskEventListener) {
        Intrinsics.checkNotNullParameter(headlessJsTaskEventListener, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
        this.headlessJsTaskEventListeners.add(headlessJsTaskEventListener);
        for (Integer intValue : this.activeTasks) {
            headlessJsTaskEventListener.onHeadlessJsTaskStart(intValue.intValue());
        }
    }

    public final void removeTaskEventListener(HeadlessJsTaskEventListener headlessJsTaskEventListener) {
        Intrinsics.checkNotNullParameter(headlessJsTaskEventListener, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
        this.headlessJsTaskEventListeners.remove(headlessJsTaskEventListener);
    }

    public final boolean hasActiveTasks() {
        return !this.activeTasks.isEmpty();
    }

    public final synchronized int startTask(HeadlessJsTaskConfig headlessJsTaskConfig) {
        int incrementAndGet;
        Intrinsics.checkNotNullParameter(headlessJsTaskConfig, "taskConfig");
        incrementAndGet = this.lastTaskId.incrementAndGet();
        startTask(headlessJsTaskConfig, incrementAndGet);
        return incrementAndGet;
    }

    private final synchronized void startTask(HeadlessJsTaskConfig headlessJsTaskConfig, int i) {
        try {
            UiThreadUtil.assertOnUiThread();
            ReactContext reactContext2 = (ReactContext) Assertions.assertNotNull(this.reactContext.get(), "Tried to start a task on a react context that has already been destroyed");
            if (reactContext2.getLifecycleState() == LifecycleState.RESUMED) {
                if (!headlessJsTaskConfig.isAllowedInForeground()) {
                    String taskKey = headlessJsTaskConfig.getTaskKey();
                    throw new IllegalStateException(("Tried to start task " + taskKey + " while in foreground, but this is not allowed.").toString());
                }
            }
            this.activeTasks.add(Integer.valueOf(i));
            this.activeTaskConfigs.put(Integer.valueOf(i), new HeadlessJsTaskConfig(headlessJsTaskConfig));
            if (reactContext2.hasActiveReactInstance()) {
                ((AppRegistry) reactContext2.getJSModule(AppRegistry.class)).startHeadlessTask(i, headlessJsTaskConfig.getTaskKey(), headlessJsTaskConfig.getData());
            } else {
                ReactSoftExceptionLogger.logSoftException("HeadlessJsTaskContext", new RuntimeException("Cannot start headless task, CatalystInstance not available"));
            }
            if (headlessJsTaskConfig.getTimeout() > 0) {
                scheduleTaskTimeout(i, headlessJsTaskConfig.getTimeout());
            }
            for (HeadlessJsTaskEventListener onHeadlessJsTaskStart : this.headlessJsTaskEventListeners) {
                onHeadlessJsTaskStart.onHeadlessJsTaskStart(i);
            }
        } catch (Throwable th) {
            while (true) {
                throw th;
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x004c, code lost:
        return false;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized boolean retryTask(int r11) {
        /*
            r10 = this;
            monitor-enter(r10)
            java.util.Map<java.lang.Integer, com.facebook.react.jstasks.HeadlessJsTaskConfig> r0 = r10.activeTaskConfigs     // Catch:{ all -> 0x0049 }
            java.lang.Integer r1 = java.lang.Integer.valueOf(r11)     // Catch:{ all -> 0x0049 }
            java.lang.Object r0 = r0.get(r1)     // Catch:{ all -> 0x0049 }
            com.facebook.react.jstasks.HeadlessJsTaskConfig r0 = (com.facebook.react.jstasks.HeadlessJsTaskConfig) r0     // Catch:{ all -> 0x0049 }
            if (r0 == 0) goto L_0x004e
            com.facebook.react.jstasks.HeadlessJsTaskRetryPolicy r1 = r0.getRetryPolicy()     // Catch:{ all -> 0x0049 }
            if (r1 == 0) goto L_0x004b
            boolean r2 = r1.canRetry()     // Catch:{ all -> 0x0049 }
            if (r2 != 0) goto L_0x001c
            goto L_0x004b
        L_0x001c:
            r10.removeTimeout(r11)     // Catch:{ all -> 0x0049 }
            com.facebook.react.jstasks.HeadlessJsTaskConfig r2 = new com.facebook.react.jstasks.HeadlessJsTaskConfig     // Catch:{ all -> 0x0049 }
            java.lang.String r4 = r0.getTaskKey()     // Catch:{ all -> 0x0049 }
            com.facebook.react.bridge.WritableMap r5 = r0.getData()     // Catch:{ all -> 0x0049 }
            long r6 = r0.getTimeout()     // Catch:{ all -> 0x0049 }
            boolean r8 = r0.isAllowedInForeground()     // Catch:{ all -> 0x0049 }
            com.facebook.react.jstasks.HeadlessJsTaskRetryPolicy r9 = r1.update()     // Catch:{ all -> 0x0049 }
            r3 = r2
            r3.<init>(r4, r5, r6, r8, r9)     // Catch:{ all -> 0x0049 }
            com.facebook.react.jstasks.HeadlessJsTaskContext$$ExternalSyntheticLambda0 r0 = new com.facebook.react.jstasks.HeadlessJsTaskContext$$ExternalSyntheticLambda0     // Catch:{ all -> 0x0049 }
            r0.<init>(r10, r2, r11)     // Catch:{ all -> 0x0049 }
            int r11 = r1.getDelay()     // Catch:{ all -> 0x0049 }
            long r1 = (long) r11     // Catch:{ all -> 0x0049 }
            com.facebook.react.bridge.UiThreadUtil.runOnUiThread(r0, r1)     // Catch:{ all -> 0x0049 }
            monitor-exit(r10)
            r11 = 1
            return r11
        L_0x0049:
            r11 = move-exception
            goto L_0x006e
        L_0x004b:
            monitor-exit(r10)
            r11 = 0
            return r11
        L_0x004e:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x0049 }
            r0.<init>()     // Catch:{ all -> 0x0049 }
            java.lang.String r1 = "Tried to retrieve non-existent task config with id "
            r0.append(r1)     // Catch:{ all -> 0x0049 }
            r0.append(r11)     // Catch:{ all -> 0x0049 }
            java.lang.String r11 = "."
            r0.append(r11)     // Catch:{ all -> 0x0049 }
            java.lang.String r11 = r0.toString()     // Catch:{ all -> 0x0049 }
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException     // Catch:{ all -> 0x0049 }
            java.lang.String r11 = r11.toString()     // Catch:{ all -> 0x0049 }
            r0.<init>(r11)     // Catch:{ all -> 0x0049 }
            throw r0     // Catch:{ all -> 0x0049 }
        L_0x006e:
            monitor-exit(r10)     // Catch:{ all -> 0x0049 }
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.jstasks.HeadlessJsTaskContext.retryTask(int):boolean");
    }

    /* access modifiers changed from: private */
    public static final void retryTask$lambda$3(HeadlessJsTaskContext headlessJsTaskContext, HeadlessJsTaskConfig headlessJsTaskConfig, int i) {
        headlessJsTaskContext.startTask(headlessJsTaskConfig, i);
    }

    public final synchronized void finishTask(int i) {
        boolean remove = this.activeTasks.remove(Integer.valueOf(i));
        this.activeTaskConfigs.remove(Integer.valueOf(i));
        removeTimeout(i);
        if (remove) {
            UiThreadUtil.runOnUiThread(new HeadlessJsTaskContext$$ExternalSyntheticLambda2(this, i));
        }
    }

    /* access modifiers changed from: private */
    public static final void finishTask$lambda$4(HeadlessJsTaskContext headlessJsTaskContext, int i) {
        for (HeadlessJsTaskEventListener onHeadlessJsTaskFinish : headlessJsTaskContext.headlessJsTaskEventListeners) {
            onHeadlessJsTaskFinish.onHeadlessJsTaskFinish(i);
        }
    }

    private final void removeTimeout(int i) {
        Runnable runnable = this.taskTimeouts.get(i);
        if (runnable != null) {
            UiThreadUtil.removeOnUiThread(runnable);
            this.taskTimeouts.remove(i);
        }
    }

    public final synchronized boolean isTaskRunning(int i) {
        return this.activeTasks.contains(Integer.valueOf(i));
    }

    private final void scheduleTaskTimeout(int i, long j) {
        HeadlessJsTaskContext$$ExternalSyntheticLambda1 headlessJsTaskContext$$ExternalSyntheticLambda1 = new HeadlessJsTaskContext$$ExternalSyntheticLambda1(this, i);
        this.taskTimeouts.append(i, headlessJsTaskContext$$ExternalSyntheticLambda1);
        UiThreadUtil.runOnUiThread(headlessJsTaskContext$$ExternalSyntheticLambda1, j);
    }

    /* access modifiers changed from: private */
    public static final void scheduleTaskTimeout$lambda$5(HeadlessJsTaskContext headlessJsTaskContext, int i) {
        headlessJsTaskContext.finishTask(i);
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final HeadlessJsTaskContext getInstance(ReactContext reactContext) {
            Intrinsics.checkNotNullParameter(reactContext, "context");
            WeakHashMap access$getINSTANCES$cp = HeadlessJsTaskContext.INSTANCES;
            Object obj = access$getINSTANCES$cp.get(reactContext);
            if (obj == null) {
                obj = new HeadlessJsTaskContext(reactContext, (DefaultConstructorMarker) null);
                access$getINSTANCES$cp.put(reactContext, obj);
            }
            return (HeadlessJsTaskContext) obj;
        }
    }
}
