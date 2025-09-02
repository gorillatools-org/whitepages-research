package com.google.firebase.perf.session.gauges;

import android.annotation.SuppressLint;
import android.content.Context;
import androidx.annotation.Keep;
import com.google.firebase.components.Lazy;
import com.google.firebase.perf.config.ConfigResolver;
import com.google.firebase.perf.logging.AndroidLogger;
import com.google.firebase.perf.session.PerfSession;
import com.google.firebase.perf.transport.TransportManager;
import com.google.firebase.perf.util.Timer;
import com.google.firebase.perf.v1.ApplicationProcessState;
import com.google.firebase.perf.v1.GaugeMetadata;
import com.google.firebase.perf.v1.GaugeMetric;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

@Keep
public class GaugeManager {
    private static final long APPROX_NUMBER_OF_DATA_POINTS_PER_GAUGE_METRIC = 20;
    private static final long INVALID_GAUGE_COLLECTION_FREQUENCY = -1;
    private static final long TIME_TO_WAIT_BEFORE_FLUSHING_GAUGES_QUEUE_MS = 20;
    private static final GaugeManager instance = new GaugeManager();
    private static final AndroidLogger logger = AndroidLogger.getInstance();
    private ApplicationProcessState applicationProcessState;
    private final ConfigResolver configResolver;
    private final Lazy<CpuGaugeCollector> cpuGaugeCollector;
    private ScheduledFuture gaugeManagerDataCollectionJob;
    private final Lazy<ScheduledExecutorService> gaugeManagerExecutor;
    private GaugeMetadataManager gaugeMetadataManager;
    private final Lazy<MemoryGaugeCollector> memoryGaugeCollector;
    private String sessionId;
    private final TransportManager transportManager;

    @SuppressLint({"ThreadPoolCreation"})
    private GaugeManager() {
        this(new Lazy(new GaugeManager$$ExternalSyntheticLambda2()), TransportManager.getInstance(), ConfigResolver.getInstance(), (GaugeMetadataManager) null, new Lazy(new GaugeManager$$ExternalSyntheticLambda3()), new Lazy(new GaugeManager$$ExternalSyntheticLambda4()));
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ CpuGaugeCollector lambda$new$0() {
        return new CpuGaugeCollector();
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ MemoryGaugeCollector lambda$new$1() {
        return new MemoryGaugeCollector();
    }

    GaugeManager(Lazy<ScheduledExecutorService> lazy, TransportManager transportManager2, ConfigResolver configResolver2, GaugeMetadataManager gaugeMetadataManager2, Lazy<CpuGaugeCollector> lazy2, Lazy<MemoryGaugeCollector> lazy3) {
        this.gaugeManagerDataCollectionJob = null;
        this.sessionId = null;
        this.applicationProcessState = ApplicationProcessState.APPLICATION_PROCESS_STATE_UNKNOWN;
        this.gaugeManagerExecutor = lazy;
        this.transportManager = transportManager2;
        this.configResolver = configResolver2;
        this.gaugeMetadataManager = gaugeMetadataManager2;
        this.cpuGaugeCollector = lazy2;
        this.memoryGaugeCollector = lazy3;
    }

    public void initializeGaugeMetadataManager(Context context) {
        this.gaugeMetadataManager = new GaugeMetadataManager(context);
    }

    public static synchronized GaugeManager getInstance() {
        GaugeManager gaugeManager;
        synchronized (GaugeManager.class) {
            gaugeManager = instance;
        }
        return gaugeManager;
    }

    public void startCollectingGauges(PerfSession perfSession, ApplicationProcessState applicationProcessState2) {
        if (this.sessionId != null) {
            stopCollectingGauges();
        }
        long startCollectingGauges = startCollectingGauges(applicationProcessState2, perfSession.getTimer());
        if (startCollectingGauges == -1) {
            logger.warn("Invalid gauge collection frequency. Unable to start collecting Gauges.");
            return;
        }
        String sessionId2 = perfSession.sessionId();
        this.sessionId = sessionId2;
        this.applicationProcessState = applicationProcessState2;
        try {
            long j = startCollectingGauges * 20;
            this.gaugeManagerDataCollectionJob = this.gaugeManagerExecutor.get().scheduleAtFixedRate(new GaugeManager$$ExternalSyntheticLambda1(this, sessionId2, applicationProcessState2), j, j, TimeUnit.MILLISECONDS);
        } catch (RejectedExecutionException e) {
            AndroidLogger androidLogger = logger;
            androidLogger.warn("Unable to start collecting Gauges: " + e.getMessage());
        }
    }

    private long startCollectingGauges(ApplicationProcessState applicationProcessState2, Timer timer) {
        long cpuGaugeCollectionFrequencyMs = getCpuGaugeCollectionFrequencyMs(applicationProcessState2);
        if (!startCollectingCpuMetrics(cpuGaugeCollectionFrequencyMs, timer)) {
            cpuGaugeCollectionFrequencyMs = -1;
        }
        long memoryGaugeCollectionFrequencyMs = getMemoryGaugeCollectionFrequencyMs(applicationProcessState2);
        if (!startCollectingMemoryMetrics(memoryGaugeCollectionFrequencyMs, timer)) {
            return cpuGaugeCollectionFrequencyMs;
        }
        if (cpuGaugeCollectionFrequencyMs == -1) {
            return memoryGaugeCollectionFrequencyMs;
        }
        return Math.min(cpuGaugeCollectionFrequencyMs, memoryGaugeCollectionFrequencyMs);
    }

    public void stopCollectingGauges() {
        String str = this.sessionId;
        if (str != null) {
            ApplicationProcessState applicationProcessState2 = this.applicationProcessState;
            this.cpuGaugeCollector.get().stopCollecting();
            this.memoryGaugeCollector.get().stopCollecting();
            ScheduledFuture scheduledFuture = this.gaugeManagerDataCollectionJob;
            if (scheduledFuture != null) {
                scheduledFuture.cancel(false);
            }
            this.gaugeManagerExecutor.get().schedule(new GaugeManager$$ExternalSyntheticLambda0(this, str, applicationProcessState2), 20, TimeUnit.MILLISECONDS);
            this.sessionId = null;
            this.applicationProcessState = ApplicationProcessState.APPLICATION_PROCESS_STATE_UNKNOWN;
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: syncFlush */
    public void lambda$stopCollectingGauges$3(String str, ApplicationProcessState applicationProcessState2) {
        GaugeMetric.Builder newBuilder = GaugeMetric.newBuilder();
        while (!this.cpuGaugeCollector.get().cpuMetricReadings.isEmpty()) {
            newBuilder.addCpuMetricReadings(this.cpuGaugeCollector.get().cpuMetricReadings.poll());
        }
        while (!this.memoryGaugeCollector.get().memoryMetricReadings.isEmpty()) {
            newBuilder.addAndroidMemoryReadings(this.memoryGaugeCollector.get().memoryMetricReadings.poll());
        }
        newBuilder.setSessionId(str);
        this.transportManager.log((GaugeMetric) newBuilder.build(), applicationProcessState2);
    }

    public boolean logGaugeMetadata(String str, ApplicationProcessState applicationProcessState2) {
        if (this.gaugeMetadataManager == null) {
            return false;
        }
        this.transportManager.log((GaugeMetric) GaugeMetric.newBuilder().setSessionId(str).setGaugeMetadata(getGaugeMetadata()).build(), applicationProcessState2);
        return true;
    }

    private GaugeMetadata getGaugeMetadata() {
        return (GaugeMetadata) GaugeMetadata.newBuilder().setDeviceRamSizeKb(this.gaugeMetadataManager.getDeviceRamSizeKb()).setMaxAppJavaHeapMemoryKb(this.gaugeMetadataManager.getMaxAppJavaHeapMemoryKb()).setMaxEncouragedAppJavaHeapMemoryKb(this.gaugeMetadataManager.getMaxEncouragedAppJavaHeapMemoryKb()).build();
    }

    private boolean startCollectingCpuMetrics(long j, Timer timer) {
        if (j == -1) {
            logger.debug("Invalid Cpu Metrics collection frequency. Did not collect Cpu Metrics.");
            return false;
        }
        this.cpuGaugeCollector.get().startCollecting(j, timer);
        return true;
    }

    private boolean startCollectingMemoryMetrics(long j, Timer timer) {
        if (j == -1) {
            logger.debug("Invalid Memory Metrics collection frequency. Did not collect Memory Metrics.");
            return false;
        }
        this.memoryGaugeCollector.get().startCollecting(j, timer);
        return true;
    }

    public void collectGaugeMetricOnce(Timer timer) {
        collectGaugeMetricOnce(this.cpuGaugeCollector.get(), this.memoryGaugeCollector.get(), timer);
    }

    private static void collectGaugeMetricOnce(CpuGaugeCollector cpuGaugeCollector2, MemoryGaugeCollector memoryGaugeCollector2, Timer timer) {
        cpuGaugeCollector2.collectOnce(timer);
        memoryGaugeCollector2.collectOnce(timer);
    }

    /* renamed from: com.google.firebase.perf.session.gauges.GaugeManager$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$firebase$perf$v1$ApplicationProcessState;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|6) */
        /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        static {
            /*
                com.google.firebase.perf.v1.ApplicationProcessState[] r0 = com.google.firebase.perf.v1.ApplicationProcessState.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$google$firebase$perf$v1$ApplicationProcessState = r0
                com.google.firebase.perf.v1.ApplicationProcessState r1 = com.google.firebase.perf.v1.ApplicationProcessState.BACKGROUND     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$google$firebase$perf$v1$ApplicationProcessState     // Catch:{ NoSuchFieldError -> 0x001d }
                com.google.firebase.perf.v1.ApplicationProcessState r1 = com.google.firebase.perf.v1.ApplicationProcessState.FOREGROUND     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.perf.session.gauges.GaugeManager.AnonymousClass1.<clinit>():void");
        }
    }

    private long getCpuGaugeCollectionFrequencyMs(ApplicationProcessState applicationProcessState2) {
        long j;
        int i = AnonymousClass1.$SwitchMap$com$google$firebase$perf$v1$ApplicationProcessState[applicationProcessState2.ordinal()];
        if (i == 1) {
            j = this.configResolver.getSessionsCpuCaptureFrequencyBackgroundMs();
        } else if (i != 2) {
            j = -1;
        } else {
            j = this.configResolver.getSessionsCpuCaptureFrequencyForegroundMs();
        }
        if (CpuGaugeCollector.isInvalidCollectionFrequency(j)) {
            return -1;
        }
        return j;
    }

    private long getMemoryGaugeCollectionFrequencyMs(ApplicationProcessState applicationProcessState2) {
        long j;
        int i = AnonymousClass1.$SwitchMap$com$google$firebase$perf$v1$ApplicationProcessState[applicationProcessState2.ordinal()];
        if (i == 1) {
            j = this.configResolver.getSessionsMemoryCaptureFrequencyBackgroundMs();
        } else if (i != 2) {
            j = -1;
        } else {
            j = this.configResolver.getSessionsMemoryCaptureFrequencyForegroundMs();
        }
        if (MemoryGaugeCollector.isInvalidCollectionFrequency(j)) {
            return -1;
        }
        return j;
    }
}
