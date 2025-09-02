package com.google.firebase.perf.application;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import androidx.fragment.app.FragmentActivity;
import com.google.firebase.perf.config.ConfigResolver;
import com.google.firebase.perf.logging.AndroidLogger;
import com.google.firebase.perf.metrics.FrameMetricsCalculator;
import com.google.firebase.perf.metrics.Trace;
import com.google.firebase.perf.session.SessionManager;
import com.google.firebase.perf.transport.TransportManager;
import com.google.firebase.perf.util.Clock;
import com.google.firebase.perf.util.Constants;
import com.google.firebase.perf.util.Optional;
import com.google.firebase.perf.util.ScreenTraceUtil;
import com.google.firebase.perf.util.Timer;
import com.google.firebase.perf.v1.ApplicationProcessState;
import com.google.firebase.perf.v1.TraceMetric;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class AppStateMonitor implements Application.ActivityLifecycleCallbacks {
    private static volatile AppStateMonitor instance;
    private static final AndroidLogger logger = AndroidLogger.getInstance();
    private final WeakHashMap<Activity, FragmentStateMonitor> activityToFragmentStateMonitorMap;
    private final WeakHashMap<Activity, FrameMetricsRecorder> activityToRecorderMap;
    private final WeakHashMap<Activity, Boolean> activityToResumedMap;
    private final WeakHashMap<Activity, Trace> activityToScreenTraceMap;
    private Set<AppColdStartCallback> appColdStartSubscribers;
    private final Set<WeakReference<AppStateCallback>> appStateSubscribers;
    private final Clock clock;
    private final ConfigResolver configResolver;
    private ApplicationProcessState currentAppState;
    private boolean isColdStart;
    private boolean isRegisteredForLifecycleCallbacks;
    private final Map<String, Long> metricToCountMap;
    private Timer resumeTime;
    private final boolean screenPerformanceRecordingSupported;
    private Timer stopTime;
    private final TransportManager transportManager;
    private final AtomicInteger tsnsCount;

    public interface AppColdStartCallback {
        void onAppColdStart();
    }

    public interface AppStateCallback {
        void onUpdateAppState(ApplicationProcessState applicationProcessState);
    }

    public void onActivityPaused(Activity activity) {
    }

    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    public static AppStateMonitor getInstance() {
        if (instance == null) {
            synchronized (AppStateMonitor.class) {
                try {
                    if (instance == null) {
                        instance = new AppStateMonitor(TransportManager.getInstance(), new Clock());
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        return instance;
    }

    AppStateMonitor(TransportManager transportManager2, Clock clock2) {
        this(transportManager2, clock2, ConfigResolver.getInstance(), isScreenPerformanceRecordingSupported());
    }

    AppStateMonitor(TransportManager transportManager2, Clock clock2, ConfigResolver configResolver2, boolean z) {
        this.activityToResumedMap = new WeakHashMap<>();
        this.activityToRecorderMap = new WeakHashMap<>();
        this.activityToFragmentStateMonitorMap = new WeakHashMap<>();
        this.activityToScreenTraceMap = new WeakHashMap<>();
        this.metricToCountMap = new HashMap();
        this.appStateSubscribers = new HashSet();
        this.appColdStartSubscribers = new HashSet();
        this.tsnsCount = new AtomicInteger(0);
        this.currentAppState = ApplicationProcessState.BACKGROUND;
        this.isRegisteredForLifecycleCallbacks = false;
        this.isColdStart = true;
        this.transportManager = transportManager2;
        this.clock = clock2;
        this.configResolver = configResolver2;
        this.screenPerformanceRecordingSupported = z;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x001b, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void registerActivityLifecycleCallbacks(android.content.Context r2) {
        /*
            r1 = this;
            monitor-enter(r1)
            boolean r0 = r1.isRegisteredForLifecycleCallbacks     // Catch:{ all -> 0x0018 }
            if (r0 == 0) goto L_0x0007
            monitor-exit(r1)
            return
        L_0x0007:
            android.content.Context r2 = r2.getApplicationContext()     // Catch:{ all -> 0x0018 }
            boolean r0 = r2 instanceof android.app.Application     // Catch:{ all -> 0x0018 }
            if (r0 == 0) goto L_0x001a
            android.app.Application r2 = (android.app.Application) r2     // Catch:{ all -> 0x0018 }
            r2.registerActivityLifecycleCallbacks(r1)     // Catch:{ all -> 0x0018 }
            r2 = 1
            r1.isRegisteredForLifecycleCallbacks = r2     // Catch:{ all -> 0x0018 }
            goto L_0x001a
        L_0x0018:
            r2 = move-exception
            goto L_0x001c
        L_0x001a:
            monitor-exit(r1)
            return
        L_0x001c:
            monitor-exit(r1)     // Catch:{ all -> 0x0018 }
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.perf.application.AppStateMonitor.registerActivityLifecycleCallbacks(android.content.Context):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x001b, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void unregisterActivityLifecycleCallbacks(android.content.Context r2) {
        /*
            r1 = this;
            monitor-enter(r1)
            boolean r0 = r1.isRegisteredForLifecycleCallbacks     // Catch:{ all -> 0x0018 }
            if (r0 != 0) goto L_0x0007
            monitor-exit(r1)
            return
        L_0x0007:
            android.content.Context r2 = r2.getApplicationContext()     // Catch:{ all -> 0x0018 }
            boolean r0 = r2 instanceof android.app.Application     // Catch:{ all -> 0x0018 }
            if (r0 == 0) goto L_0x001a
            android.app.Application r2 = (android.app.Application) r2     // Catch:{ all -> 0x0018 }
            r2.unregisterActivityLifecycleCallbacks(r1)     // Catch:{ all -> 0x0018 }
            r2 = 0
            r1.isRegisteredForLifecycleCallbacks = r2     // Catch:{ all -> 0x0018 }
            goto L_0x001a
        L_0x0018:
            r2 = move-exception
            goto L_0x001c
        L_0x001a:
            monitor-exit(r1)
            return
        L_0x001c:
            monitor-exit(r1)     // Catch:{ all -> 0x0018 }
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.perf.application.AppStateMonitor.unregisterActivityLifecycleCallbacks(android.content.Context):void");
    }

    public void incrementCount(String str, long j) {
        synchronized (this.metricToCountMap) {
            try {
                Long l = this.metricToCountMap.get(str);
                if (l == null) {
                    this.metricToCountMap.put(str, Long.valueOf(j));
                } else {
                    this.metricToCountMap.put(str, Long.valueOf(l.longValue() + j));
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public void incrementTsnsCount(int i) {
        this.tsnsCount.addAndGet(i);
    }

    private void startFrameMonitoring(Activity activity) {
        if (isScreenTraceSupported() && this.configResolver.isPerformanceMonitoringEnabled()) {
            FrameMetricsRecorder frameMetricsRecorder = new FrameMetricsRecorder(activity);
            this.activityToRecorderMap.put(activity, frameMetricsRecorder);
            if (activity instanceof FragmentActivity) {
                FragmentStateMonitor fragmentStateMonitor = new FragmentStateMonitor(this.clock, this.transportManager, this, frameMetricsRecorder);
                this.activityToFragmentStateMonitorMap.put(activity, fragmentStateMonitor);
                ((FragmentActivity) activity).getSupportFragmentManager().registerFragmentLifecycleCallbacks(fragmentStateMonitor, true);
            }
        }
    }

    public void onActivityCreated(Activity activity, Bundle bundle) {
        startFrameMonitoring(activity);
    }

    public void onActivityDestroyed(Activity activity) {
        this.activityToRecorderMap.remove(activity);
        if (this.activityToFragmentStateMonitorMap.containsKey(activity)) {
            ((FragmentActivity) activity).getSupportFragmentManager().unregisterFragmentLifecycleCallbacks(this.activityToFragmentStateMonitorMap.remove(activity));
        }
    }

    public synchronized void onActivityStarted(Activity activity) {
        try {
            if (isScreenTraceSupported() && this.configResolver.isPerformanceMonitoringEnabled()) {
                if (!this.activityToRecorderMap.containsKey(activity)) {
                    startFrameMonitoring(activity);
                }
                this.activityToRecorderMap.get(activity).start();
                Trace trace = new Trace(getScreenTraceName(activity), this.transportManager, this.clock, this);
                trace.start();
                this.activityToScreenTraceMap.put(activity, trace);
            }
        } catch (Throwable th) {
            while (true) {
                throw th;
            }
        }
    }

    public synchronized void onActivityStopped(Activity activity) {
        try {
            if (isScreenTraceSupported()) {
                sendScreenTrace(activity);
            }
            if (this.activityToResumedMap.containsKey(activity)) {
                this.activityToResumedMap.remove(activity);
                if (this.activityToResumedMap.isEmpty()) {
                    this.stopTime = this.clock.getTime();
                    sendSessionLog(Constants.TraceNames.FOREGROUND_TRACE_NAME.toString(), this.resumeTime, this.stopTime);
                    updateAppState(ApplicationProcessState.BACKGROUND);
                }
            }
        } catch (Throwable th) {
            while (true) {
                throw th;
            }
        }
    }

    public synchronized void onActivityResumed(Activity activity) {
        try {
            if (this.activityToResumedMap.isEmpty()) {
                this.resumeTime = this.clock.getTime();
                this.activityToResumedMap.put(activity, Boolean.TRUE);
                if (this.isColdStart) {
                    updateAppState(ApplicationProcessState.FOREGROUND);
                    sendAppColdStartUpdate();
                    this.isColdStart = false;
                } else {
                    sendSessionLog(Constants.TraceNames.BACKGROUND_TRACE_NAME.toString(), this.stopTime, this.resumeTime);
                    updateAppState(ApplicationProcessState.FOREGROUND);
                }
            } else {
                this.activityToResumedMap.put(activity, Boolean.TRUE);
            }
        } catch (Throwable th) {
            while (true) {
                throw th;
            }
        }
    }

    public boolean isColdStart() {
        return this.isColdStart;
    }

    public ApplicationProcessState getAppState() {
        return this.currentAppState;
    }

    public void registerForAppState(WeakReference<AppStateCallback> weakReference) {
        synchronized (this.appStateSubscribers) {
            this.appStateSubscribers.add(weakReference);
        }
    }

    public void unregisterForAppState(WeakReference<AppStateCallback> weakReference) {
        synchronized (this.appStateSubscribers) {
            this.appStateSubscribers.remove(weakReference);
        }
    }

    public void registerForAppColdStart(AppColdStartCallback appColdStartCallback) {
        synchronized (this.appColdStartSubscribers) {
            this.appColdStartSubscribers.add(appColdStartCallback);
        }
    }

    private void updateAppState(ApplicationProcessState applicationProcessState) {
        this.currentAppState = applicationProcessState;
        synchronized (this.appStateSubscribers) {
            try {
                Iterator<WeakReference<AppStateCallback>> it = this.appStateSubscribers.iterator();
                while (it.hasNext()) {
                    AppStateCallback appStateCallback = (AppStateCallback) it.next().get();
                    if (appStateCallback != null) {
                        appStateCallback.onUpdateAppState(this.currentAppState);
                    } else {
                        it.remove();
                    }
                }
            } finally {
            }
        }
    }

    private void sendAppColdStartUpdate() {
        synchronized (this.appColdStartSubscribers) {
            try {
                for (AppColdStartCallback next : this.appColdStartSubscribers) {
                    if (next != null) {
                        next.onAppColdStart();
                    }
                }
            } finally {
            }
        }
    }

    public boolean isForeground() {
        return this.currentAppState == ApplicationProcessState.FOREGROUND;
    }

    private void sendScreenTrace(Activity activity) {
        Trace trace = this.activityToScreenTraceMap.get(activity);
        if (trace != null) {
            this.activityToScreenTraceMap.remove(activity);
            Optional<FrameMetricsCalculator.PerfFrameMetrics> stop = this.activityToRecorderMap.get(activity).stop();
            if (!stop.isAvailable()) {
                logger.warn("Failed to record frame data for %s.", activity.getClass().getSimpleName());
                return;
            }
            ScreenTraceUtil.addFrameCounters(trace, stop.get());
            trace.stop();
        }
    }

    private void sendSessionLog(String str, Timer timer, Timer timer2) {
        if (this.configResolver.isPerformanceMonitoringEnabled()) {
            TraceMetric.Builder addPerfSessions = TraceMetric.newBuilder().setName(str).setClientStartTimeUs(timer.getMicros()).setDurationUs(timer.getDurationMicros(timer2)).addPerfSessions(SessionManager.getInstance().perfSession().build());
            int andSet = this.tsnsCount.getAndSet(0);
            synchronized (this.metricToCountMap) {
                try {
                    addPerfSessions.putAllCounters(this.metricToCountMap);
                    if (andSet != 0) {
                        addPerfSessions.putCounters(Constants.CounterNames.TRACE_STARTED_NOT_STOPPED.toString(), (long) andSet);
                    }
                    this.metricToCountMap.clear();
                } catch (Throwable th) {
                    while (true) {
                        throw th;
                    }
                }
            }
            this.transportManager.log((TraceMetric) addPerfSessions.build(), ApplicationProcessState.FOREGROUND_BACKGROUND);
        }
    }

    /* access modifiers changed from: protected */
    public boolean isScreenTraceSupported() {
        return this.screenPerformanceRecordingSupported;
    }

    private static boolean isScreenPerformanceRecordingSupported() {
        return FrameMetricsRecorder.isFrameMetricsRecordingSupported();
    }

    public static String getScreenTraceName(Activity activity) {
        return Constants.SCREEN_TRACE_PREFIX + activity.getClass().getSimpleName();
    }

    /* access modifiers changed from: package-private */
    public WeakHashMap<Activity, Boolean> getResumed() {
        return this.activityToResumedMap;
    }

    /* access modifiers changed from: package-private */
    public WeakHashMap<Activity, Trace> getActivity2ScreenTrace() {
        return this.activityToScreenTraceMap;
    }

    /* access modifiers changed from: package-private */
    public Timer getPauseTime() {
        return this.stopTime;
    }

    /* access modifiers changed from: package-private */
    public void setStopTime(Timer timer) {
        this.stopTime = timer;
    }

    /* access modifiers changed from: package-private */
    public Timer getResumeTime() {
        return this.resumeTime;
    }

    public void setIsColdStart(boolean z) {
        this.isColdStart = z;
    }
}
