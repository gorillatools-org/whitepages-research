package com.google.android.gms.common.api.internal;

import android.os.SystemClock;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.BaseGmsClient;
import com.google.android.gms.common.internal.ConnectionTelemetryConfiguration;
import com.google.android.gms.common.internal.MethodInvocation;
import com.google.android.gms.common.internal.RootTelemetryConfigManager;
import com.google.android.gms.common.internal.RootTelemetryConfiguration;
import com.google.android.gms.common.util.ArrayUtils;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

final class zacd implements OnCompleteListener {
    private final GoogleApiManager zaa;
    private final int zab;
    private final ApiKey zac;
    private final long zad;
    private final long zae;

    zacd(GoogleApiManager googleApiManager, int i, ApiKey apiKey, long j, long j2, String str, String str2) {
        this.zaa = googleApiManager;
        this.zab = i;
        this.zac = apiKey;
        this.zad = j;
        this.zae = j2;
    }

    static zacd zaa(GoogleApiManager googleApiManager, int i, ApiKey apiKey) {
        boolean z;
        if (!googleApiManager.zaD()) {
            return null;
        }
        RootTelemetryConfiguration config = RootTelemetryConfigManager.getInstance().getConfig();
        if (config == null) {
            z = true;
        } else if (!config.getMethodInvocationTelemetryEnabled()) {
            return null;
        } else {
            z = config.getMethodTimingTelemetryEnabled();
            zabq zai = googleApiManager.zai(apiKey);
            if (zai != null) {
                if (!(zai.zaf() instanceof BaseGmsClient)) {
                    return null;
                }
                BaseGmsClient baseGmsClient = (BaseGmsClient) zai.zaf();
                if (baseGmsClient.hasConnectionInfo() && !baseGmsClient.isConnecting()) {
                    ConnectionTelemetryConfiguration zab2 = zab(zai, baseGmsClient, i);
                    if (zab2 == null) {
                        return null;
                    }
                    zai.zaq();
                    z = zab2.getMethodTimingTelemetryEnabled();
                }
            }
        }
        return new zacd(googleApiManager, i, apiKey, z ? System.currentTimeMillis() : 0, z ? SystemClock.elapsedRealtime() : 0, (String) null, (String) null);
    }

    private static ConnectionTelemetryConfiguration zab(zabq zabq, BaseGmsClient baseGmsClient, int i) {
        int[] methodInvocationMethodKeyAllowlist;
        int[] methodInvocationMethodKeyDisallowlist;
        ConnectionTelemetryConfiguration telemetryConfiguration = baseGmsClient.getTelemetryConfiguration();
        if (telemetryConfiguration == null || !telemetryConfiguration.getMethodInvocationTelemetryEnabled() || ((methodInvocationMethodKeyAllowlist = telemetryConfiguration.getMethodInvocationMethodKeyAllowlist()) != null ? !ArrayUtils.contains(methodInvocationMethodKeyAllowlist, i) : !((methodInvocationMethodKeyDisallowlist = telemetryConfiguration.getMethodInvocationMethodKeyDisallowlist()) == null || !ArrayUtils.contains(methodInvocationMethodKeyDisallowlist, i))) || zabq.zac() >= telemetryConfiguration.getMaxMethodInvocationsLogged()) {
            return null;
        }
        return telemetryConfiguration;
    }

    public final void onComplete(Task task) {
        zabq zai;
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        long j;
        long j2;
        if (this.zaa.zaD()) {
            RootTelemetryConfiguration config = RootTelemetryConfigManager.getInstance().getConfig();
            if ((config == null || config.getMethodInvocationTelemetryEnabled()) && (zai = this.zaa.zai(this.zac)) != null && (zai.zaf() instanceof BaseGmsClient)) {
                BaseGmsClient baseGmsClient = (BaseGmsClient) zai.zaf();
                boolean z = true;
                int i6 = 0;
                boolean z2 = this.zad > 0;
                int gCoreServiceId = baseGmsClient.getGCoreServiceId();
                int i7 = 100;
                if (config != null) {
                    boolean methodTimingTelemetryEnabled = z2 & config.getMethodTimingTelemetryEnabled();
                    int batchPeriodMillis = config.getBatchPeriodMillis();
                    int maxMethodInvocationsInBatch = config.getMaxMethodInvocationsInBatch();
                    i3 = config.getVersion();
                    if (baseGmsClient.hasConnectionInfo() && !baseGmsClient.isConnecting()) {
                        ConnectionTelemetryConfiguration zab2 = zab(zai, baseGmsClient, this.zab);
                        if (zab2 != null) {
                            if (!zab2.getMethodTimingTelemetryEnabled() || this.zad <= 0) {
                                z = false;
                            }
                            maxMethodInvocationsInBatch = zab2.getMaxMethodInvocationsLogged();
                            methodTimingTelemetryEnabled = z;
                        } else {
                            return;
                        }
                    }
                    i2 = batchPeriodMillis;
                    i = maxMethodInvocationsInBatch;
                } else {
                    i3 = 0;
                    i = 100;
                    i2 = 5000;
                }
                GoogleApiManager googleApiManager = this.zaa;
                if (task.isSuccessful()) {
                    i4 = 0;
                } else {
                    if (!task.isCanceled()) {
                        Exception exception = task.getException();
                        if (exception instanceof ApiException) {
                            Status status = ((ApiException) exception).getStatus();
                            i7 = status.getStatusCode();
                            ConnectionResult connectionResult = status.getConnectionResult();
                            if (connectionResult != null) {
                                i4 = connectionResult.getErrorCode();
                                i6 = i7;
                            }
                        } else {
                            i6 = 101;
                            i4 = -1;
                        }
                    }
                    i6 = i7;
                    i4 = -1;
                }
                if (z2) {
                    long j3 = this.zad;
                    long j4 = this.zae;
                    long currentTimeMillis = System.currentTimeMillis();
                    i5 = (int) (SystemClock.elapsedRealtime() - j4);
                    j = currentTimeMillis;
                    j2 = j3;
                } else {
                    j2 = 0;
                    j = 0;
                    i5 = -1;
                }
                googleApiManager.zaw(new MethodInvocation(this.zab, i6, i4, j2, j, (String) null, (String) null, gCoreServiceId, i5), i3, (long) i2, i);
            }
        }
    }
}
