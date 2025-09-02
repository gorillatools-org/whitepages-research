package com.google.firebase.perf.metrics.validator;

import android.content.Context;
import com.google.firebase.perf.logging.AndroidLogger;
import com.google.firebase.perf.util.URLAllowlist;
import com.google.firebase.perf.v1.NetworkRequestMetric;
import java.net.URI;

final class FirebasePerfNetworkValidator extends PerfMetricValidator {
    private static final int EMPTY_PORT = -1;
    private static final String HTTPS = "https";
    private static final String HTTP_SCHEMA = "http";
    private static final AndroidLogger logger = AndroidLogger.getInstance();
    private final Context appContext;
    private final NetworkRequestMetric networkMetric;

    private boolean isValidHttpResponseCode(int i) {
        return i > 0;
    }

    private boolean isValidPayload(long j) {
        return j >= 0;
    }

    private boolean isValidPort(int i) {
        return i == -1 || i > 0;
    }

    private boolean isValidTime(long j) {
        return j >= 0;
    }

    private boolean isValidUserInfo(String str) {
        return str == null;
    }

    FirebasePerfNetworkValidator(NetworkRequestMetric networkRequestMetric, Context context) {
        this.appContext = context;
        this.networkMetric = networkRequestMetric;
    }

    public boolean isValidPerfMetric() {
        if (isEmptyUrl(this.networkMetric.getUrl())) {
            AndroidLogger androidLogger = logger;
            androidLogger.warn("URL is missing:" + this.networkMetric.getUrl());
            return false;
        }
        URI resultUrl = getResultUrl(this.networkMetric.getUrl());
        if (resultUrl == null) {
            logger.warn("URL cannot be parsed");
            return false;
        } else if (!isAllowlisted(resultUrl, this.appContext)) {
            AndroidLogger androidLogger2 = logger;
            androidLogger2.warn("URL fails allowlist rule: " + resultUrl);
            return false;
        } else if (!isValidHost(resultUrl.getHost())) {
            logger.warn("URL host is null or invalid");
            return false;
        } else if (!isValidScheme(resultUrl.getScheme())) {
            logger.warn("URL scheme is null or invalid");
            return false;
        } else if (!isValidUserInfo(resultUrl.getUserInfo())) {
            logger.warn("URL user info is null");
            return false;
        } else if (!isValidPort(resultUrl.getPort())) {
            logger.warn("URL port is less than or equal to 0");
            return false;
        } else {
            if (!isValidHttpMethod(this.networkMetric.hasHttpMethod() ? this.networkMetric.getHttpMethod() : null)) {
                AndroidLogger androidLogger3 = logger;
                androidLogger3.warn("HTTP Method is null or invalid: " + this.networkMetric.getHttpMethod());
                return false;
            } else if (this.networkMetric.hasHttpResponseCode() && !isValidHttpResponseCode(this.networkMetric.getHttpResponseCode())) {
                AndroidLogger androidLogger4 = logger;
                androidLogger4.warn("HTTP ResponseCode is a negative value:" + this.networkMetric.getHttpResponseCode());
                return false;
            } else if (this.networkMetric.hasRequestPayloadBytes() && !isValidPayload(this.networkMetric.getRequestPayloadBytes())) {
                AndroidLogger androidLogger5 = logger;
                androidLogger5.warn("Request Payload is a negative value:" + this.networkMetric.getRequestPayloadBytes());
                return false;
            } else if (this.networkMetric.hasResponsePayloadBytes() && !isValidPayload(this.networkMetric.getResponsePayloadBytes())) {
                AndroidLogger androidLogger6 = logger;
                androidLogger6.warn("Response Payload is a negative value:" + this.networkMetric.getResponsePayloadBytes());
                return false;
            } else if (!this.networkMetric.hasClientStartTimeUs() || this.networkMetric.getClientStartTimeUs() <= 0) {
                AndroidLogger androidLogger7 = logger;
                androidLogger7.warn("Start time of the request is null, or zero, or a negative value:" + this.networkMetric.getClientStartTimeUs());
                return false;
            } else if (this.networkMetric.hasTimeToRequestCompletedUs() && !isValidTime(this.networkMetric.getTimeToRequestCompletedUs())) {
                AndroidLogger androidLogger8 = logger;
                androidLogger8.warn("Time to complete the request is a negative value:" + this.networkMetric.getTimeToRequestCompletedUs());
                return false;
            } else if (this.networkMetric.hasTimeToResponseInitiatedUs() && !isValidTime(this.networkMetric.getTimeToResponseInitiatedUs())) {
                AndroidLogger androidLogger9 = logger;
                androidLogger9.warn("Time from the start of the request to the start of the response is null or a negative value:" + this.networkMetric.getTimeToResponseInitiatedUs());
                return false;
            } else if (!this.networkMetric.hasTimeToResponseCompletedUs() || this.networkMetric.getTimeToResponseCompletedUs() <= 0) {
                AndroidLogger androidLogger10 = logger;
                androidLogger10.warn("Time from the start of the request to the end of the response is null, negative or zero:" + this.networkMetric.getTimeToResponseCompletedUs());
                return false;
            } else if (this.networkMetric.hasHttpResponseCode()) {
                return true;
            } else {
                logger.warn("Did not receive a HTTP Response Code");
                return false;
            }
        }
    }

    private boolean isEmptyUrl(String str) {
        return isBlank(str);
    }

    private URI getResultUrl(String str) {
        if (str == null) {
            return null;
        }
        try {
            return URI.create(str);
        } catch (IllegalArgumentException | IllegalStateException e) {
            logger.warn("getResultUrl throws exception %s", e.getMessage());
            return null;
        }
    }

    private boolean isAllowlisted(URI uri, Context context) {
        if (uri == null) {
            return false;
        }
        return URLAllowlist.isURLAllowlisted(uri, context);
    }

    private boolean isValidHost(String str) {
        return str != null && !isBlank(str) && str.length() <= 255;
    }

    private boolean isValidScheme(String str) {
        if (str == null) {
            return false;
        }
        return HTTP_SCHEMA.equalsIgnoreCase(str) || HTTPS.equalsIgnoreCase(str);
    }

    /* access modifiers changed from: package-private */
    public boolean isValidHttpMethod(NetworkRequestMetric.HttpMethod httpMethod) {
        return (httpMethod == null || httpMethod == NetworkRequestMetric.HttpMethod.HTTP_METHOD_UNKNOWN) ? false : true;
    }

    private boolean isBlank(String str) {
        if (str == null) {
            return true;
        }
        return str.trim().isEmpty();
    }
}
