package com.google.firebase.perf.metrics.validator;

import android.content.Context;
import com.google.firebase.perf.logging.AndroidLogger;
import com.google.firebase.perf.util.Constants;
import com.google.firebase.perf.v1.PerfMetric;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public abstract class PerfMetricValidator {
    public abstract boolean isValidPerfMetric();

    private static List<PerfMetricValidator> getValidators(PerfMetric perfMetric, Context context) {
        ArrayList arrayList = new ArrayList();
        if (perfMetric.hasTraceMetric()) {
            arrayList.add(new FirebasePerfTraceValidator(perfMetric.getTraceMetric()));
        }
        if (perfMetric.hasNetworkRequestMetric()) {
            arrayList.add(new FirebasePerfNetworkValidator(perfMetric.getNetworkRequestMetric(), context));
        }
        if (perfMetric.hasApplicationInfo()) {
            arrayList.add(new FirebasePerfApplicationInfoValidator(perfMetric.getApplicationInfo()));
        }
        if (perfMetric.hasGaugeMetric()) {
            arrayList.add(new FirebasePerfGaugeMetricValidator(perfMetric.getGaugeMetric()));
        }
        return arrayList;
    }

    public static boolean isValid(PerfMetric perfMetric, Context context) {
        List<PerfMetricValidator> validators = getValidators(perfMetric, context);
        if (validators.isEmpty()) {
            AndroidLogger.getInstance().debug("No validators found for PerfMetric.");
            return false;
        }
        for (PerfMetricValidator isValidPerfMetric : validators) {
            if (!isValidPerfMetric.isValidPerfMetric()) {
                return false;
            }
        }
        return true;
    }

    public static String validateTraceName(String str) {
        if (str == null) {
            return "Trace name must not be null";
        }
        if (str.length() > 100) {
            return String.format(Locale.US, "Trace name must not exceed %d characters", new Object[]{100});
        }
        if (!str.startsWith("_")) {
            return null;
        }
        for (Constants.TraceNames traceNames : Constants.TraceNames.values()) {
            if (traceNames.toString().equals(str)) {
                return null;
            }
        }
        if (str.startsWith(Constants.SCREEN_TRACE_PREFIX)) {
            return null;
        }
        return "Trace name must not start with '_'";
    }

    public static String validateMetricName(String str) {
        if (str == null) {
            return "Metric name must not be null";
        }
        if (str.length() > 100) {
            return String.format(Locale.US, "Metric name must not exceed %d characters", new Object[]{100});
        }
        if (!str.startsWith("_")) {
            return null;
        }
        for (Constants.CounterNames counterNames : Constants.CounterNames.values()) {
            if (counterNames.toString().equals(str)) {
                return null;
            }
        }
        return "Metric name must not start with '_'";
    }

    public static void validateAttribute(String str, String str2) {
        if (str == null || str.length() == 0) {
            throw new IllegalArgumentException("Attribute key must not be null or empty");
        } else if (str2 == null || str2.length() == 0) {
            throw new IllegalArgumentException("Attribute value must not be null or empty");
        } else if (str.length() > 40) {
            throw new IllegalArgumentException(String.format(Locale.US, "Attribute key length must not exceed %d characters", new Object[]{40}));
        } else if (str2.length() > 100) {
            throw new IllegalArgumentException(String.format(Locale.US, "Attribute value length must not exceed %d characters", new Object[]{100}));
        } else if (!str.matches("^(?!(firebase_|google_|ga_))[A-Za-z][A-Za-z_0-9]*")) {
            throw new IllegalArgumentException("Attribute key must start with letter, must only contain alphanumeric characters and underscore and must not start with \"firebase_\", \"google_\" and \"ga_");
        }
    }
}
