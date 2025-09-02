package com.google.firebase.perf.transport;

import android.content.Context;
import com.google.firebase.perf.config.ConfigResolver;
import com.google.firebase.perf.logging.AndroidLogger;
import com.google.firebase.perf.metrics.resource.ResourceType;
import com.google.firebase.perf.util.Clock;
import com.google.firebase.perf.util.Constants;
import com.google.firebase.perf.util.Rate;
import com.google.firebase.perf.util.Timer;
import com.google.firebase.perf.util.Utils;
import com.google.firebase.perf.v1.PerfMetric;
import com.google.firebase.perf.v1.PerfSession;
import com.google.firebase.perf.v1.SessionVerbosity;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

final class RateLimiter {
    private final ConfigResolver configResolver;
    private final double fragmentBucketId;
    private boolean isLogcatEnabled;
    private RateLimiterImpl networkLimiter;
    private final double samplingBucketId;
    private RateLimiterImpl traceLimiter;

    public RateLimiter(Context context, Rate rate, long j) {
        this(rate, j, new Clock(), getSamplingBucketId(), getSamplingBucketId(), ConfigResolver.getInstance());
        this.isLogcatEnabled = Utils.isDebugLoggingEnabled(context);
    }

    static double getSamplingBucketId() {
        return new Random().nextDouble();
    }

    RateLimiter(Rate rate, long j, Clock clock, double d, double d2, ConfigResolver configResolver2) {
        double d3 = d;
        double d4 = d2;
        this.traceLimiter = null;
        this.networkLimiter = null;
        boolean z = false;
        this.isLogcatEnabled = false;
        Utils.checkArgument(0.0d <= d3 && d3 < 1.0d, "Sampling bucket ID should be in range [0.0, 1.0).");
        if (0.0d <= d4 && d4 < 1.0d) {
            z = true;
        }
        Utils.checkArgument(z, "Fragment sampling bucket ID should be in range [0.0, 1.0).");
        this.samplingBucketId = d3;
        this.fragmentBucketId = d4;
        this.configResolver = configResolver2;
        Rate rate2 = rate;
        long j2 = j;
        Clock clock2 = clock;
        ConfigResolver configResolver3 = configResolver2;
        this.traceLimiter = new RateLimiterImpl(rate2, j2, clock2, configResolver3, ResourceType.TRACE, this.isLogcatEnabled);
        this.networkLimiter = new RateLimiterImpl(rate2, j2, clock2, configResolver3, ResourceType.NETWORK, this.isLogcatEnabled);
    }

    private boolean isDeviceAllowedToSendTraces() {
        return this.samplingBucketId < this.configResolver.getTraceSamplingRate();
    }

    private boolean isDeviceAllowedToSendNetworkEvents() {
        return this.samplingBucketId < this.configResolver.getNetworkRequestSamplingRate();
    }

    private boolean isDeviceAllowedToSendFragmentScreenTraces() {
        return this.fragmentBucketId < this.configResolver.getFragmentSamplingRate();
    }

    /* access modifiers changed from: protected */
    public boolean isFragmentScreenTrace(PerfMetric perfMetric) {
        return perfMetric.hasTraceMetric() && perfMetric.getTraceMetric().getName().startsWith(Constants.SCREEN_TRACE_PREFIX) && perfMetric.getTraceMetric().containsCustomAttributes(Constants.ACTIVITY_ATTRIBUTE_KEY);
    }

    /* access modifiers changed from: package-private */
    public boolean isEventRateLimited(PerfMetric perfMetric) {
        if (!isRateLimitApplicable(perfMetric)) {
            return false;
        }
        if (perfMetric.hasNetworkRequestMetric()) {
            return !this.networkLimiter.check(perfMetric);
        }
        if (perfMetric.hasTraceMetric()) {
            return !this.traceLimiter.check(perfMetric);
        }
        return true;
    }

    /* access modifiers changed from: package-private */
    public boolean isEventSampled(PerfMetric perfMetric) {
        if (perfMetric.hasTraceMetric() && !isDeviceAllowedToSendTraces() && !hasVerboseSessions(perfMetric.getTraceMetric().getPerfSessionsList())) {
            return false;
        }
        if (isFragmentScreenTrace(perfMetric) && !isDeviceAllowedToSendFragmentScreenTraces() && !hasVerboseSessions(perfMetric.getTraceMetric().getPerfSessionsList())) {
            return false;
        }
        if (!perfMetric.hasNetworkRequestMetric() || isDeviceAllowedToSendNetworkEvents() || hasVerboseSessions(perfMetric.getNetworkRequestMetric().getPerfSessionsList())) {
            return true;
        }
        return false;
    }

    private boolean hasVerboseSessions(List<PerfSession> list) {
        if (list.size() <= 0 || list.get(0).getSessionVerbosityCount() <= 0 || list.get(0).getSessionVerbosity(0) != SessionVerbosity.GAUGES_AND_SYSTEM_EVENTS) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: package-private */
    public boolean isRateLimitApplicable(PerfMetric perfMetric) {
        if ((!perfMetric.hasTraceMetric() || ((!perfMetric.getTraceMetric().getName().equals(Constants.TraceNames.FOREGROUND_TRACE_NAME.toString()) && !perfMetric.getTraceMetric().getName().equals(Constants.TraceNames.BACKGROUND_TRACE_NAME.toString())) || perfMetric.getTraceMetric().getCountersCount() <= 0)) && !perfMetric.hasGaugeMetric()) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    public void changeRate(boolean z) {
        this.traceLimiter.changeRate(z);
        this.networkLimiter.changeRate(z);
    }

    /* access modifiers changed from: package-private */
    public boolean getIsDeviceAllowedToSendTraces() {
        return isDeviceAllowedToSendTraces();
    }

    /* access modifiers changed from: package-private */
    public boolean getIsDeviceAllowedToSendNetworkEvents() {
        return isDeviceAllowedToSendNetworkEvents();
    }

    /* access modifiers changed from: package-private */
    public boolean getIsDeviceAllowedToSendFragmentScreenTraces() {
        return isDeviceAllowedToSendFragmentScreenTraces();
    }

    static class RateLimiterImpl {
        private static final long MICROS_IN_A_SECOND = TimeUnit.SECONDS.toMicros(1);
        private static final AndroidLogger logger = AndroidLogger.getInstance();
        private long backgroundCapacity;
        private Rate backgroundRate;
        private long capacity;
        private final Clock clock;
        private long foregroundCapacity;
        private Rate foregroundRate;
        private final boolean isLogcatEnabled;
        private Timer lastTimeTokenReplenished;
        private Rate rate;
        private double tokenCount;

        RateLimiterImpl(Rate rate2, long j, Clock clock2, ConfigResolver configResolver, @ResourceType String str, boolean z) {
            this.clock = clock2;
            this.capacity = j;
            this.rate = rate2;
            this.tokenCount = (double) j;
            this.lastTimeTokenReplenished = clock2.getTime();
            setRateByReadingRemoteConfigValues(configResolver, str, z);
            this.isLogcatEnabled = z;
        }

        /* access modifiers changed from: package-private */
        /* JADX WARNING: Code restructure failed: missing block: B:17:0x004a, code lost:
            return false;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public synchronized boolean check(com.google.firebase.perf.v1.PerfMetric r5) {
            /*
                r4 = this;
                monitor-enter(r4)
                com.google.firebase.perf.util.Clock r5 = r4.clock     // Catch:{ all -> 0x002e }
                com.google.firebase.perf.util.Timer r5 = r5.getTime()     // Catch:{ all -> 0x002e }
                com.google.firebase.perf.util.Timer r0 = r4.lastTimeTokenReplenished     // Catch:{ all -> 0x002e }
                long r0 = r0.getDurationMicros(r5)     // Catch:{ all -> 0x002e }
                double r0 = (double) r0     // Catch:{ all -> 0x002e }
                com.google.firebase.perf.util.Rate r2 = r4.rate     // Catch:{ all -> 0x002e }
                double r2 = r2.getTokensPerSeconds()     // Catch:{ all -> 0x002e }
                double r0 = r0 * r2
                long r2 = MICROS_IN_A_SECOND     // Catch:{ all -> 0x002e }
                double r2 = (double) r2     // Catch:{ all -> 0x002e }
                double r0 = r0 / r2
                r2 = 0
                int r2 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
                if (r2 <= 0) goto L_0x0030
                double r2 = r4.tokenCount     // Catch:{ all -> 0x002e }
                double r2 = r2 + r0
                long r0 = r4.capacity     // Catch:{ all -> 0x002e }
                double r0 = (double) r0     // Catch:{ all -> 0x002e }
                double r0 = java.lang.Math.min(r2, r0)     // Catch:{ all -> 0x002e }
                r4.tokenCount = r0     // Catch:{ all -> 0x002e }
                r4.lastTimeTokenReplenished = r5     // Catch:{ all -> 0x002e }
                goto L_0x0030
            L_0x002e:
                r5 = move-exception
                goto L_0x004c
            L_0x0030:
                double r0 = r4.tokenCount     // Catch:{ all -> 0x002e }
                r2 = 4607182418800017408(0x3ff0000000000000, double:1.0)
                int r5 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
                if (r5 < 0) goto L_0x003e
                double r0 = r0 - r2
                r4.tokenCount = r0     // Catch:{ all -> 0x002e }
                monitor-exit(r4)
                r5 = 1
                return r5
            L_0x003e:
                boolean r5 = r4.isLogcatEnabled     // Catch:{ all -> 0x002e }
                if (r5 == 0) goto L_0x0049
                com.google.firebase.perf.logging.AndroidLogger r5 = logger     // Catch:{ all -> 0x002e }
                java.lang.String r0 = "Exceeded log rate limit, dropping the log."
                r5.warn(r0)     // Catch:{ all -> 0x002e }
            L_0x0049:
                monitor-exit(r4)
                r5 = 0
                return r5
            L_0x004c:
                monitor-exit(r4)     // Catch:{ all -> 0x002e }
                throw r5
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.perf.transport.RateLimiter.RateLimiterImpl.check(com.google.firebase.perf.v1.PerfMetric):boolean");
        }

        /* access modifiers changed from: package-private */
        public synchronized void changeRate(boolean z) {
            Rate rate2;
            if (z) {
                try {
                    rate2 = this.foregroundRate;
                } catch (Throwable th) {
                    while (true) {
                        throw th;
                    }
                }
            } else {
                rate2 = this.backgroundRate;
            }
            this.rate = rate2;
            this.capacity = z ? this.foregroundCapacity : this.backgroundCapacity;
        }

        private void setRateByReadingRemoteConfigValues(ConfigResolver configResolver, @ResourceType String str, boolean z) {
            String str2 = str;
            long flimitSec = getFlimitSec(configResolver, str);
            long flimitEvents = getFlimitEvents(configResolver, str);
            TimeUnit timeUnit = TimeUnit.SECONDS;
            Rate rate2 = new Rate(flimitEvents, flimitSec, timeUnit);
            this.foregroundRate = rate2;
            this.foregroundCapacity = flimitEvents;
            if (z) {
                logger.debug("Foreground %s logging rate:%f, burst capacity:%d", str2, rate2, Long.valueOf(flimitEvents));
            }
            long blimitSec = getBlimitSec(configResolver, str);
            long blimitEvents = getBlimitEvents(configResolver, str);
            Rate rate3 = new Rate(blimitEvents, blimitSec, timeUnit);
            this.backgroundRate = rate3;
            this.backgroundCapacity = blimitEvents;
            if (z) {
                logger.debug("Background %s logging rate:%f, capacity:%d", str2, rate3, Long.valueOf(blimitEvents));
            }
        }

        private static long getFlimitSec(ConfigResolver configResolver, @ResourceType String str) {
            if (str == ResourceType.TRACE) {
                return configResolver.getRateLimitSec();
            }
            return configResolver.getRateLimitSec();
        }

        private static long getFlimitEvents(ConfigResolver configResolver, @ResourceType String str) {
            if (str == ResourceType.TRACE) {
                return configResolver.getTraceEventCountForeground();
            }
            return configResolver.getNetworkEventCountForeground();
        }

        private static long getBlimitSec(ConfigResolver configResolver, @ResourceType String str) {
            if (str == ResourceType.TRACE) {
                return configResolver.getRateLimitSec();
            }
            return configResolver.getRateLimitSec();
        }

        private static long getBlimitEvents(ConfigResolver configResolver, @ResourceType String str) {
            if (str == ResourceType.TRACE) {
                return configResolver.getTraceEventCountBackground();
            }
            return configResolver.getNetworkEventCountBackground();
        }

        /* access modifiers changed from: package-private */
        public Rate getForegroundRate() {
            return this.foregroundRate;
        }

        /* access modifiers changed from: package-private */
        public long getForegroundCapacity() {
            return this.foregroundCapacity;
        }

        /* access modifiers changed from: package-private */
        public Rate getBackgroundRate() {
            return this.backgroundRate;
        }

        /* access modifiers changed from: package-private */
        public long getBackgroundCapacity() {
            return this.backgroundCapacity;
        }

        /* access modifiers changed from: package-private */
        public Rate getRate() {
            return this.rate;
        }

        /* access modifiers changed from: package-private */
        public void setRate(Rate rate2) {
            this.rate = rate2;
        }
    }
}
