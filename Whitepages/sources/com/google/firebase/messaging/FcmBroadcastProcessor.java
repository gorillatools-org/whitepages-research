package com.google.firebase.messaging;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Base64;
import android.util.Log;
import androidx.privacysandbox.ads.adservices.measurement.MeasurementManagerImplCommon$$ExternalSyntheticLambda3;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.util.PlatformVersion;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;

@KeepForSdk
public class FcmBroadcastProcessor {
    private static final String EXTRA_BINARY_DATA = "rawData";
    private static final String EXTRA_BINARY_DATA_BASE_64 = "gcm.rawData64";
    private static WithinAppServiceConnection fcmServiceConn;
    private static final Object lock = new Object();
    private final Context context;
    private final Executor executor;

    public FcmBroadcastProcessor(Context context2) {
        this.context = context2;
        this.executor = new MeasurementManagerImplCommon$$ExternalSyntheticLambda3();
    }

    public FcmBroadcastProcessor(Context context2, ExecutorService executorService) {
        this.context = context2;
        this.executor = executorService;
    }

    @KeepForSdk
    public Task<Integer> process(Intent intent) {
        String stringExtra = intent.getStringExtra(EXTRA_BINARY_DATA_BASE_64);
        if (stringExtra != null) {
            intent.putExtra("rawData", Base64.decode(stringExtra, 0));
            intent.removeExtra(EXTRA_BINARY_DATA_BASE_64);
        }
        return startMessagingService(this.context, intent);
    }

    @SuppressLint({"InlinedApi"})
    public Task<Integer> startMessagingService(Context context2, Intent intent) {
        boolean z = false;
        boolean z2 = PlatformVersion.isAtLeastO() && context2.getApplicationInfo().targetSdkVersion >= 26;
        if ((intent.getFlags() & 268435456) != 0) {
            z = true;
        }
        if (!z2 || z) {
            return Tasks.call(this.executor, new FcmBroadcastProcessor$$ExternalSyntheticLambda1(context2, intent)).continueWithTask(this.executor, new FcmBroadcastProcessor$$ExternalSyntheticLambda2(context2, intent, z));
        }
        return bindToMessagingService(context2, intent, z);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ Task lambda$startMessagingService$2(Context context2, Intent intent, boolean z, Task task) throws Exception {
        return (!PlatformVersion.isAtLeastO() || ((Integer) task.getResult()).intValue() != 402) ? task : bindToMessagingService(context2, intent, z).continueWith(new MeasurementManagerImplCommon$$ExternalSyntheticLambda3(), new FcmBroadcastProcessor$$ExternalSyntheticLambda0());
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ Integer lambda$startMessagingService$1(Task task) throws Exception {
        return 403;
    }

    private static Task<Integer> bindToMessagingService(Context context2, Intent intent, boolean z) {
        if (Log.isLoggable(Constants.TAG, 3)) {
            Log.d(Constants.TAG, "Binding to service");
        }
        WithinAppServiceConnection serviceConnection = getServiceConnection(context2, "com.google.firebase.MESSAGING_EVENT");
        if (!z) {
            return serviceConnection.sendIntent(intent).continueWith(new MeasurementManagerImplCommon$$ExternalSyntheticLambda3(), new FcmBroadcastProcessor$$ExternalSyntheticLambda3());
        }
        if (ServiceStarter.getInstance().hasWakeLockPermission(context2)) {
            WakeLockHolder.sendWakefulServiceIntent(context2, serviceConnection, intent);
        } else {
            serviceConnection.sendIntent(intent);
        }
        return Tasks.forResult(-1);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ Integer lambda$bindToMessagingService$3(Task task) throws Exception {
        return -1;
    }

    private static WithinAppServiceConnection getServiceConnection(Context context2, String str) {
        WithinAppServiceConnection withinAppServiceConnection;
        synchronized (lock) {
            try {
                if (fcmServiceConn == null) {
                    fcmServiceConn = new WithinAppServiceConnection(context2, str);
                }
                withinAppServiceConnection = fcmServiceConn;
            } catch (Throwable th) {
                throw th;
            }
        }
        return withinAppServiceConnection;
    }

    public static void reset() {
        synchronized (lock) {
            fcmServiceConn = null;
        }
    }

    public static void setServiceConnection(WithinAppServiceConnection withinAppServiceConnection) {
        synchronized (lock) {
            fcmServiceConn = withinAppServiceConnection;
        }
    }
}
