package com.google.firebase.perf.metrics.validator;

import com.google.firebase.perf.v1.GaugeMetric;

final class FirebasePerfGaugeMetricValidator extends PerfMetricValidator {
    private final GaugeMetric gaugeMetric;

    FirebasePerfGaugeMetricValidator(GaugeMetric gaugeMetric2) {
        this.gaugeMetric = gaugeMetric2;
    }

    public boolean isValidPerfMetric() {
        return this.gaugeMetric.hasSessionId() && (this.gaugeMetric.getCpuMetricReadingsCount() > 0 || this.gaugeMetric.getAndroidMemoryReadingsCount() > 0 || (this.gaugeMetric.hasGaugeMetadata() && this.gaugeMetric.getGaugeMetadata().hasMaxAppJavaHeapMemoryKb()));
    }
}
