package com.google.firebase.perf.metrics;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.Process;
import android.view.View;
import android.view.ViewTreeObserver;
import androidx.annotation.Keep;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.lifecycle.ProcessLifecycleOwner;
import com.facebook.hermes.intl.Constants;
import com.google.firebase.FirebaseApp;
import com.google.firebase.StartupTime;
import com.google.firebase.perf.config.ConfigResolver;
import com.google.firebase.perf.session.PerfSession;
import com.google.firebase.perf.transport.TransportManager;
import com.google.firebase.perf.util.Clock;
import com.google.firebase.perf.util.Constants;
import com.google.firebase.perf.util.Timer;
import com.google.firebase.perf.v1.ApplicationProcessState;
import com.google.firebase.perf.v1.TraceMetric;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class AppStartTrace implements Application.ActivityLifecycleCallbacks, LifecycleObserver {
    private static final int CORE_POOL_SIZE = 0;
    private static final long MAX_LATENCY_BEFORE_UI_INIT = TimeUnit.MINUTES.toMicros(1);
    private static final int MAX_POOL_SIZE = 1;
    private static final Timer PERF_CLASS_LOAD_TIME = new Clock().getTime();
    private static ExecutorService executorService;
    private static volatile AppStartTrace instance;
    private Context appContext;
    private WeakReference<Activity> appStartActivity;
    private final Clock clock;
    private final ConfigResolver configResolver;
    private final TraceMetric.Builder experimentTtid;
    private final Timer firebaseClassLoadTime;
    private Timer firstBackgroundTime;
    private Timer firstForegroundTime;
    private boolean isRegisteredForLifecycleCallbacks = false;
    /* access modifiers changed from: private */
    public boolean isStartedFromBackground;
    private boolean isTooLateToInitUI = false;
    private WeakReference<Activity> launchActivity;
    /* access modifiers changed from: private */
    public Timer onCreateTime;
    private int onDrawCount;
    private final DrawCounter onDrawCounterListener;
    private Timer onDrawPostAtFrontOfQueueTime;
    private Timer onResumeTime;
    private Timer onStartTime;
    private Timer preDrawPostAtFrontOfQueueTime;
    private Timer preDrawPostTime;
    private final Timer processStartTime;
    private PerfSession startSession;
    private boolean systemForegroundCheck;
    private final TransportManager transportManager;

    @Keep
    public static void setLauncherActivityOnCreateTime(String str) {
    }

    @Keep
    public static void setLauncherActivityOnResumeTime(String str) {
    }

    @Keep
    public static void setLauncherActivityOnStartTime(String str) {
    }

    public void onActivityDestroyed(Activity activity) {
    }

    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    public void onActivityStopped(Activity activity) {
    }

    static /* synthetic */ int access$308(AppStartTrace appStartTrace) {
        int i = appStartTrace.onDrawCount;
        appStartTrace.onDrawCount = i + 1;
        return i;
    }

    public static AppStartTrace getInstance() {
        return instance != null ? instance : getInstance(TransportManager.getInstance(), new Clock());
    }

    @SuppressLint({"ThreadPoolCreation"})
    static AppStartTrace getInstance(TransportManager transportManager2, Clock clock2) {
        if (instance == null) {
            synchronized (AppStartTrace.class) {
                try {
                    if (instance == null) {
                        instance = new AppStartTrace(transportManager2, clock2, ConfigResolver.getInstance(), new ThreadPoolExecutor(0, 1, MAX_LATENCY_BEFORE_UI_INIT + 10, TimeUnit.SECONDS, new LinkedBlockingQueue()));
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        return instance;
    }

    AppStartTrace(TransportManager transportManager2, Clock clock2, ConfigResolver configResolver2, ExecutorService executorService2) {
        Timer timer = null;
        this.onCreateTime = null;
        this.onStartTime = null;
        this.onResumeTime = null;
        this.firstForegroundTime = null;
        this.firstBackgroundTime = null;
        this.preDrawPostTime = null;
        this.preDrawPostAtFrontOfQueueTime = null;
        this.onDrawPostAtFrontOfQueueTime = null;
        this.isStartedFromBackground = false;
        this.onDrawCount = 0;
        this.onDrawCounterListener = new DrawCounter();
        this.systemForegroundCheck = false;
        this.transportManager = transportManager2;
        this.clock = clock2;
        this.configResolver = configResolver2;
        executorService = executorService2;
        this.experimentTtid = TraceMetric.newBuilder().setName("_experiment_app_start_ttid");
        this.processStartTime = Timer.ofElapsedRealtime(Process.getStartElapsedRealtime());
        StartupTime startupTime = (StartupTime) FirebaseApp.getInstance().get(StartupTime.class);
        this.firebaseClassLoadTime = startupTime != null ? Timer.ofElapsedRealtime(startupTime.getElapsedRealtime()) : timer;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0038, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void registerActivityLifecycleCallbacks(android.content.Context r3) {
        /*
            r2 = this;
            monitor-enter(r2)
            boolean r0 = r2.isRegisteredForLifecycleCallbacks     // Catch:{ all -> 0x002e }
            if (r0 == 0) goto L_0x0007
            monitor-exit(r2)
            return
        L_0x0007:
            androidx.lifecycle.LifecycleOwner r0 = androidx.lifecycle.ProcessLifecycleOwner.get()     // Catch:{ all -> 0x002e }
            androidx.lifecycle.Lifecycle r0 = r0.getLifecycle()     // Catch:{ all -> 0x002e }
            r0.addObserver(r2)     // Catch:{ all -> 0x002e }
            android.content.Context r3 = r3.getApplicationContext()     // Catch:{ all -> 0x002e }
            boolean r0 = r3 instanceof android.app.Application     // Catch:{ all -> 0x002e }
            if (r0 == 0) goto L_0x0037
            r0 = r3
            android.app.Application r0 = (android.app.Application) r0     // Catch:{ all -> 0x002e }
            r0.registerActivityLifecycleCallbacks(r2)     // Catch:{ all -> 0x002e }
            boolean r0 = r2.systemForegroundCheck     // Catch:{ all -> 0x002e }
            r1 = 1
            if (r0 != 0) goto L_0x0030
            boolean r0 = isAnyAppProcessInForeground(r3)     // Catch:{ all -> 0x002e }
            if (r0 == 0) goto L_0x002c
            goto L_0x0030
        L_0x002c:
            r0 = 0
            goto L_0x0031
        L_0x002e:
            r3 = move-exception
            goto L_0x0039
        L_0x0030:
            r0 = r1
        L_0x0031:
            r2.systemForegroundCheck = r0     // Catch:{ all -> 0x002e }
            r2.isRegisteredForLifecycleCallbacks = r1     // Catch:{ all -> 0x002e }
            r2.appContext = r3     // Catch:{ all -> 0x002e }
        L_0x0037:
            monitor-exit(r2)
            return
        L_0x0039:
            monitor-exit(r2)     // Catch:{ all -> 0x002e }
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.perf.metrics.AppStartTrace.registerActivityLifecycleCallbacks(android.content.Context):void");
    }

    public synchronized void unregisterActivityLifecycleCallbacks() {
        if (this.isRegisteredForLifecycleCallbacks) {
            ProcessLifecycleOwner.get().getLifecycle().removeObserver(this);
            ((Application) this.appContext).unregisterActivityLifecycleCallbacks(this);
            this.isRegisteredForLifecycleCallbacks = false;
        }
    }

    private Timer getStartTimerCompat() {
        Timer timer = this.processStartTime;
        if (timer != null) {
            return timer;
        }
        return getClassLoadTimeCompat();
    }

    private Timer getClassLoadTimeCompat() {
        Timer timer = this.firebaseClassLoadTime;
        if (timer != null) {
            return timer;
        }
        return PERF_CLASS_LOAD_TIME;
    }

    /* access modifiers changed from: private */
    public void recordPreDraw() {
        if (this.preDrawPostTime == null) {
            this.preDrawPostTime = this.clock.getTime();
            this.experimentTtid.setClientStartTimeUs(getStartTimerCompat().getMicros()).setDurationUs(getStartTimerCompat().getDurationMicros(this.preDrawPostTime));
            logExperimentTrace(this.experimentTtid);
        }
    }

    /* access modifiers changed from: private */
    public void recordPreDrawFrontOfQueue() {
        if (this.preDrawPostAtFrontOfQueueTime == null) {
            this.preDrawPostAtFrontOfQueueTime = this.clock.getTime();
            this.experimentTtid.addSubtraces((TraceMetric) TraceMetric.newBuilder().setName("_experiment_preDrawFoQ").setClientStartTimeUs(getStartTimerCompat().getMicros()).setDurationUs(getStartTimerCompat().getDurationMicros(this.preDrawPostAtFrontOfQueueTime)).build());
            logExperimentTrace(this.experimentTtid);
        }
    }

    /* access modifiers changed from: private */
    public void recordOnDrawFrontOfQueue() {
        if (this.onDrawPostAtFrontOfQueueTime == null) {
            this.onDrawPostAtFrontOfQueueTime = this.clock.getTime();
            this.experimentTtid.addSubtraces((TraceMetric) TraceMetric.newBuilder().setName("_experiment_onDrawFoQ").setClientStartTimeUs(getStartTimerCompat().getMicros()).setDurationUs(getStartTimerCompat().getDurationMicros(this.onDrawPostAtFrontOfQueueTime)).build());
            if (this.processStartTime != null) {
                this.experimentTtid.addSubtraces((TraceMetric) TraceMetric.newBuilder().setName("_experiment_procStart_to_classLoad").setClientStartTimeUs(getStartTimerCompat().getMicros()).setDurationUs(getStartTimerCompat().getDurationMicros(getClassLoadTimeCompat())).build());
            }
            this.experimentTtid.putCustomAttributes("systemDeterminedForeground", this.systemForegroundCheck ? "true" : Constants.CASEFIRST_FALSE);
            this.experimentTtid.putCounters("onDrawCount", (long) this.onDrawCount);
            this.experimentTtid.addPerfSessions(this.startSession.build());
            logExperimentTrace(this.experimentTtid);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0041, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0043, code lost:
        return;
     */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x003e A[Catch:{ all -> 0x001a }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void onActivityCreated(android.app.Activity r4, android.os.Bundle r5) {
        /*
            r3 = this;
            monitor-enter(r3)
            boolean r5 = r3.isStartedFromBackground     // Catch:{ all -> 0x001a }
            if (r5 != 0) goto L_0x0042
            com.google.firebase.perf.util.Timer r5 = r3.onCreateTime     // Catch:{ all -> 0x001a }
            if (r5 == 0) goto L_0x000a
            goto L_0x0042
        L_0x000a:
            boolean r5 = r3.systemForegroundCheck     // Catch:{ all -> 0x001a }
            r0 = 1
            if (r5 != 0) goto L_0x001c
            android.content.Context r5 = r3.appContext     // Catch:{ all -> 0x001a }
            boolean r5 = isAnyAppProcessInForeground(r5)     // Catch:{ all -> 0x001a }
            if (r5 == 0) goto L_0x0018
            goto L_0x001c
        L_0x0018:
            r5 = 0
            goto L_0x001d
        L_0x001a:
            r4 = move-exception
            goto L_0x0044
        L_0x001c:
            r5 = r0
        L_0x001d:
            r3.systemForegroundCheck = r5     // Catch:{ all -> 0x001a }
            java.lang.ref.WeakReference r5 = new java.lang.ref.WeakReference     // Catch:{ all -> 0x001a }
            r5.<init>(r4)     // Catch:{ all -> 0x001a }
            r3.launchActivity = r5     // Catch:{ all -> 0x001a }
            com.google.firebase.perf.util.Clock r4 = r3.clock     // Catch:{ all -> 0x001a }
            com.google.firebase.perf.util.Timer r4 = r4.getTime()     // Catch:{ all -> 0x001a }
            r3.onCreateTime = r4     // Catch:{ all -> 0x001a }
            com.google.firebase.perf.util.Timer r4 = r3.getStartTimerCompat()     // Catch:{ all -> 0x001a }
            com.google.firebase.perf.util.Timer r5 = r3.onCreateTime     // Catch:{ all -> 0x001a }
            long r4 = r4.getDurationMicros(r5)     // Catch:{ all -> 0x001a }
            long r1 = MAX_LATENCY_BEFORE_UI_INIT     // Catch:{ all -> 0x001a }
            int r4 = (r4 > r1 ? 1 : (r4 == r1 ? 0 : -1))
            if (r4 <= 0) goto L_0x0040
            r3.isTooLateToInitUI = r0     // Catch:{ all -> 0x001a }
        L_0x0040:
            monitor-exit(r3)
            return
        L_0x0042:
            monitor-exit(r3)
            return
        L_0x0044:
            monitor-exit(r3)     // Catch:{ all -> 0x001a }
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.perf.metrics.AppStartTrace.onActivityCreated(android.app.Activity, android.os.Bundle):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x001b, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void onActivityStarted(android.app.Activity r1) {
        /*
            r0 = this;
            monitor-enter(r0)
            boolean r1 = r0.isStartedFromBackground     // Catch:{ all -> 0x0018 }
            if (r1 != 0) goto L_0x001a
            com.google.firebase.perf.util.Timer r1 = r0.onStartTime     // Catch:{ all -> 0x0018 }
            if (r1 != 0) goto L_0x001a
            boolean r1 = r0.isTooLateToInitUI     // Catch:{ all -> 0x0018 }
            if (r1 == 0) goto L_0x000e
            goto L_0x001a
        L_0x000e:
            com.google.firebase.perf.util.Clock r1 = r0.clock     // Catch:{ all -> 0x0018 }
            com.google.firebase.perf.util.Timer r1 = r1.getTime()     // Catch:{ all -> 0x0018 }
            r0.onStartTime = r1     // Catch:{ all -> 0x0018 }
            monitor-exit(r0)
            return
        L_0x0018:
            r1 = move-exception
            goto L_0x001c
        L_0x001a:
            monitor-exit(r0)
            return
        L_0x001c:
            monitor-exit(r0)     // Catch:{ all -> 0x0018 }
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.perf.metrics.AppStartTrace.onActivityStarted(android.app.Activity):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:23:0x00a3, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x00a5, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void onActivityResumed(android.app.Activity r6) {
        /*
            r5 = this;
            monitor-enter(r5)
            boolean r0 = r5.isStartedFromBackground     // Catch:{ all -> 0x003b }
            if (r0 != 0) goto L_0x00a4
            boolean r0 = r5.isTooLateToInitUI     // Catch:{ all -> 0x003b }
            if (r0 == 0) goto L_0x000b
            goto L_0x00a4
        L_0x000b:
            com.google.firebase.perf.config.ConfigResolver r0 = r5.configResolver     // Catch:{ all -> 0x003b }
            boolean r0 = r0.getIsExperimentTTIDEnabled()     // Catch:{ all -> 0x003b }
            if (r0 == 0) goto L_0x003d
            r1 = 16908290(0x1020002, float:2.3877235E-38)
            android.view.View r1 = r6.findViewById(r1)     // Catch:{ all -> 0x003b }
            if (r1 == 0) goto L_0x003d
            android.view.ViewTreeObserver r2 = r1.getViewTreeObserver()     // Catch:{ all -> 0x003b }
            com.google.firebase.perf.metrics.AppStartTrace$DrawCounter r3 = r5.onDrawCounterListener     // Catch:{ all -> 0x003b }
            r2.addOnDrawListener(r3)     // Catch:{ all -> 0x003b }
            com.google.firebase.perf.metrics.AppStartTrace$$ExternalSyntheticLambda0 r2 = new com.google.firebase.perf.metrics.AppStartTrace$$ExternalSyntheticLambda0     // Catch:{ all -> 0x003b }
            r2.<init>(r5)     // Catch:{ all -> 0x003b }
            com.google.firebase.perf.util.FirstDrawDoneListener.registerForNextDraw(r1, r2)     // Catch:{ all -> 0x003b }
            com.google.firebase.perf.metrics.AppStartTrace$$ExternalSyntheticLambda1 r2 = new com.google.firebase.perf.metrics.AppStartTrace$$ExternalSyntheticLambda1     // Catch:{ all -> 0x003b }
            r2.<init>(r5)     // Catch:{ all -> 0x003b }
            com.google.firebase.perf.metrics.AppStartTrace$$ExternalSyntheticLambda2 r3 = new com.google.firebase.perf.metrics.AppStartTrace$$ExternalSyntheticLambda2     // Catch:{ all -> 0x003b }
            r3.<init>(r5)     // Catch:{ all -> 0x003b }
            com.google.firebase.perf.util.PreDrawListener.registerForNextDraw(r1, r2, r3)     // Catch:{ all -> 0x003b }
            goto L_0x003d
        L_0x003b:
            r6 = move-exception
            goto L_0x00a6
        L_0x003d:
            com.google.firebase.perf.util.Timer r1 = r5.onResumeTime     // Catch:{ all -> 0x003b }
            if (r1 == 0) goto L_0x0043
            monitor-exit(r5)
            return
        L_0x0043:
            java.lang.ref.WeakReference r1 = new java.lang.ref.WeakReference     // Catch:{ all -> 0x003b }
            r1.<init>(r6)     // Catch:{ all -> 0x003b }
            r5.appStartActivity = r1     // Catch:{ all -> 0x003b }
            com.google.firebase.perf.util.Clock r1 = r5.clock     // Catch:{ all -> 0x003b }
            com.google.firebase.perf.util.Timer r1 = r1.getTime()     // Catch:{ all -> 0x003b }
            r5.onResumeTime = r1     // Catch:{ all -> 0x003b }
            com.google.firebase.perf.session.SessionManager r1 = com.google.firebase.perf.session.SessionManager.getInstance()     // Catch:{ all -> 0x003b }
            com.google.firebase.perf.session.PerfSession r1 = r1.perfSession()     // Catch:{ all -> 0x003b }
            r5.startSession = r1     // Catch:{ all -> 0x003b }
            com.google.firebase.perf.logging.AndroidLogger r1 = com.google.firebase.perf.logging.AndroidLogger.getInstance()     // Catch:{ all -> 0x003b }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x003b }
            r2.<init>()     // Catch:{ all -> 0x003b }
            java.lang.String r3 = "onResume(): "
            r2.append(r3)     // Catch:{ all -> 0x003b }
            java.lang.Class r6 = r6.getClass()     // Catch:{ all -> 0x003b }
            java.lang.String r6 = r6.getName()     // Catch:{ all -> 0x003b }
            r2.append(r6)     // Catch:{ all -> 0x003b }
            java.lang.String r6 = ": "
            r2.append(r6)     // Catch:{ all -> 0x003b }
            com.google.firebase.perf.util.Timer r6 = r5.getClassLoadTimeCompat()     // Catch:{ all -> 0x003b }
            com.google.firebase.perf.util.Timer r3 = r5.onResumeTime     // Catch:{ all -> 0x003b }
            long r3 = r6.getDurationMicros(r3)     // Catch:{ all -> 0x003b }
            r2.append(r3)     // Catch:{ all -> 0x003b }
            java.lang.String r6 = " microseconds"
            r2.append(r6)     // Catch:{ all -> 0x003b }
            java.lang.String r6 = r2.toString()     // Catch:{ all -> 0x003b }
            r1.debug(r6)     // Catch:{ all -> 0x003b }
            java.util.concurrent.ExecutorService r6 = executorService     // Catch:{ all -> 0x003b }
            com.google.firebase.perf.metrics.AppStartTrace$$ExternalSyntheticLambda3 r1 = new com.google.firebase.perf.metrics.AppStartTrace$$ExternalSyntheticLambda3     // Catch:{ all -> 0x003b }
            r1.<init>(r5)     // Catch:{ all -> 0x003b }
            r6.execute(r1)     // Catch:{ all -> 0x003b }
            if (r0 != 0) goto L_0x00a2
            r5.unregisterActivityLifecycleCallbacks()     // Catch:{ all -> 0x003b }
        L_0x00a2:
            monitor-exit(r5)
            return
        L_0x00a4:
            monitor-exit(r5)
            return
        L_0x00a6:
            monitor-exit(r5)     // Catch:{ all -> 0x003b }
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.perf.metrics.AppStartTrace.onActivityResumed(android.app.Activity):void");
    }

    private void logExperimentTrace(TraceMetric.Builder builder) {
        if (this.preDrawPostTime != null && this.preDrawPostAtFrontOfQueueTime != null && this.onDrawPostAtFrontOfQueueTime != null) {
            executorService.execute(new AppStartTrace$$ExternalSyntheticLambda4(this, builder));
            unregisterActivityLifecycleCallbacks();
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$logExperimentTrace$0(TraceMetric.Builder builder) {
        this.transportManager.log((TraceMetric) builder.build(), ApplicationProcessState.FOREGROUND_BACKGROUND);
    }

    /* access modifiers changed from: private */
    public void logAppStartTrace() {
        TraceMetric.Builder durationUs = TraceMetric.newBuilder().setName(Constants.TraceNames.APP_START_TRACE_NAME.toString()).setClientStartTimeUs(getClassLoadTimeCompat().getMicros()).setDurationUs(getClassLoadTimeCompat().getDurationMicros(this.onResumeTime));
        ArrayList arrayList = new ArrayList(3);
        arrayList.add((TraceMetric) TraceMetric.newBuilder().setName(Constants.TraceNames.ON_CREATE_TRACE_NAME.toString()).setClientStartTimeUs(getClassLoadTimeCompat().getMicros()).setDurationUs(getClassLoadTimeCompat().getDurationMicros(this.onCreateTime)).build());
        if (this.onStartTime != null) {
            TraceMetric.Builder newBuilder = TraceMetric.newBuilder();
            newBuilder.setName(Constants.TraceNames.ON_START_TRACE_NAME.toString()).setClientStartTimeUs(this.onCreateTime.getMicros()).setDurationUs(this.onCreateTime.getDurationMicros(this.onStartTime));
            arrayList.add((TraceMetric) newBuilder.build());
            TraceMetric.Builder newBuilder2 = TraceMetric.newBuilder();
            newBuilder2.setName(Constants.TraceNames.ON_RESUME_TRACE_NAME.toString()).setClientStartTimeUs(this.onStartTime.getMicros()).setDurationUs(this.onStartTime.getDurationMicros(this.onResumeTime));
            arrayList.add((TraceMetric) newBuilder2.build());
        }
        durationUs.addAllSubtraces(arrayList).addPerfSessions(this.startSession.build());
        this.transportManager.log((TraceMetric) durationUs.build(), ApplicationProcessState.FOREGROUND_BACKGROUND);
    }

    public void onActivityPaused(Activity activity) {
        View findViewById;
        if (!this.isStartedFromBackground && !this.isTooLateToInitUI && this.configResolver.getIsExperimentTTIDEnabled() && (findViewById = activity.findViewById(16908290)) != null) {
            findViewById.getViewTreeObserver().removeOnDrawListener(this.onDrawCounterListener);
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    @Keep
    public void onAppEnteredForeground() {
        if (!this.isStartedFromBackground && !this.isTooLateToInitUI && this.firstForegroundTime == null) {
            this.firstForegroundTime = this.clock.getTime();
            this.experimentTtid.addSubtraces((TraceMetric) TraceMetric.newBuilder().setName("_experiment_firstForegrounding").setClientStartTimeUs(getStartTimerCompat().getMicros()).setDurationUs(getStartTimerCompat().getDurationMicros(this.firstForegroundTime)).build());
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    @Keep
    public void onAppEnteredBackground() {
        if (!this.isStartedFromBackground && !this.isTooLateToInitUI && this.firstBackgroundTime == null) {
            this.firstBackgroundTime = this.clock.getTime();
            this.experimentTtid.addSubtraces((TraceMetric) TraceMetric.newBuilder().setName("_experiment_firstBackgrounding").setClientStartTimeUs(getStartTimerCompat().getMicros()).setDurationUs(getStartTimerCompat().getDurationMicros(this.firstBackgroundTime)).build());
        }
    }

    public static boolean isAnyAppProcessInForeground(Context context) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
        if (activityManager == null) {
            return true;
        }
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = activityManager.getRunningAppProcesses();
        if (runningAppProcesses == null) {
            return false;
        }
        String packageName = context.getPackageName();
        String str = packageName + ":";
        for (ActivityManager.RunningAppProcessInfo next : runningAppProcesses) {
            if (next.importance == 100 && (next.processName.equals(packageName) || next.processName.startsWith(str))) {
                return true;
            }
        }
        return false;
    }

    public static boolean isScreenOn(Context context) {
        PowerManager powerManager = (PowerManager) context.getSystemService("power");
        if (powerManager == null) {
            return true;
        }
        return powerManager.isInteractive();
    }

    public static class StartFromBackgroundRunnable implements Runnable {
        private final AppStartTrace trace;

        public StartFromBackgroundRunnable(AppStartTrace appStartTrace) {
            this.trace = appStartTrace;
        }

        public void run() {
            if (this.trace.onCreateTime == null) {
                boolean unused = this.trace.isStartedFromBackground = true;
            }
        }
    }

    private final class DrawCounter implements ViewTreeObserver.OnDrawListener {
        private DrawCounter() {
        }

        public void onDraw() {
            AppStartTrace.access$308(AppStartTrace.this);
        }
    }

    /* access modifiers changed from: package-private */
    public Activity getLaunchActivity() {
        return this.launchActivity.get();
    }

    /* access modifiers changed from: package-private */
    public Activity getAppStartActivity() {
        return this.appStartActivity.get();
    }

    /* access modifiers changed from: package-private */
    public Timer getOnCreateTime() {
        return this.onCreateTime;
    }

    /* access modifiers changed from: package-private */
    public Timer getOnStartTime() {
        return this.onStartTime;
    }

    /* access modifiers changed from: package-private */
    public Timer getOnResumeTime() {
        return this.onResumeTime;
    }

    /* access modifiers changed from: package-private */
    public void setIsStartFromBackground() {
        this.isStartedFromBackground = true;
    }
}
