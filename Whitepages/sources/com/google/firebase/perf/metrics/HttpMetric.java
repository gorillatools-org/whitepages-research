package com.google.firebase.perf.metrics;

import com.google.firebase.perf.FirebasePerformanceAttributable;
import com.google.firebase.perf.config.ConfigResolver;
import com.google.firebase.perf.logging.AndroidLogger;
import com.google.firebase.perf.metrics.validator.PerfMetricValidator;
import com.google.firebase.perf.transport.TransportManager;
import com.google.firebase.perf.util.Timer;
import java.net.URL;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class HttpMetric implements FirebasePerformanceAttributable {
    private static final AndroidLogger logger = AndroidLogger.getInstance();
    private final Map<String, String> customAttributesMap;
    private boolean isDisabled;
    private boolean isStopped;
    private final NetworkRequestMetricBuilder networkMetricBuilder;
    private final Timer timer;

    public HttpMetric(String str, String str2, TransportManager transportManager, Timer timer2) {
        this.isStopped = false;
        this.isDisabled = false;
        this.customAttributesMap = new ConcurrentHashMap();
        this.timer = timer2;
        NetworkRequestMetricBuilder httpMethod = NetworkRequestMetricBuilder.builder(transportManager).setUrl(str).setHttpMethod(str2);
        this.networkMetricBuilder = httpMethod;
        httpMethod.setManualNetworkRequestMetric();
        if (!ConfigResolver.getInstance().isPerformanceMonitoringEnabled()) {
            logger.info("HttpMetric feature is disabled. URL %s", str);
            this.isDisabled = true;
        }
    }

    public HttpMetric(URL url, String str, TransportManager transportManager, Timer timer2) {
        this(url.toString(), str, transportManager, timer2);
    }

    public void setHttpResponseCode(int i) {
        this.networkMetricBuilder.setHttpResponseCode(i);
    }

    public void setRequestPayloadSize(long j) {
        this.networkMetricBuilder.setRequestPayloadBytes(j);
    }

    public void setResponsePayloadSize(long j) {
        this.networkMetricBuilder.setResponsePayloadBytes(j);
    }

    public void setResponseContentType(String str) {
        this.networkMetricBuilder.setResponseContentType(str);
    }

    public void start() {
        this.timer.reset();
        this.networkMetricBuilder.setRequestStartTimeMicros(this.timer.getMicros());
    }

    public void markRequestComplete() {
        this.networkMetricBuilder.setTimeToRequestCompletedMicros(this.timer.getDurationMicros());
    }

    public void markResponseStart() {
        this.networkMetricBuilder.setTimeToResponseInitiatedMicros(this.timer.getDurationMicros());
    }

    public void stop() {
        if (!this.isDisabled) {
            this.networkMetricBuilder.setTimeToResponseCompletedMicros(this.timer.getDurationMicros()).setCustomAttributes(this.customAttributesMap).build();
            this.isStopped = true;
        }
    }

    public void putAttribute(String str, String str2) {
        boolean z;
        try {
            str = str.trim();
            str2 = str2.trim();
            checkAttribute(str, str2);
            logger.debug("Setting attribute '%s' to %s on network request '%s'", str, str2, this.networkMetricBuilder.getUrl());
            z = true;
        } catch (Exception e) {
            logger.error("Cannot set attribute '%s' with value '%s' (%s)", str, str2, e.getMessage());
            z = false;
        }
        if (z) {
            this.customAttributesMap.put(str, str2);
        }
    }

    private void checkAttribute(String str, String str2) {
        if (this.isStopped) {
            throw new IllegalArgumentException("HttpMetric has been logged already so unable to modify attributes");
        } else if (this.customAttributesMap.containsKey(str) || this.customAttributesMap.size() < 5) {
            PerfMetricValidator.validateAttribute(str, str2);
        } else {
            throw new IllegalArgumentException(String.format(Locale.ENGLISH, "Exceeds max limit of number of attributes - %d", new Object[]{5}));
        }
    }

    public void removeAttribute(String str) {
        if (this.isStopped) {
            logger.error("Can't remove a attribute from a HttpMetric that's stopped.");
        } else {
            this.customAttributesMap.remove(str);
        }
    }

    public String getAttribute(String str) {
        return this.customAttributesMap.get(str);
    }

    public Map<String, String> getAttributes() {
        return new HashMap(this.customAttributesMap);
    }
}
