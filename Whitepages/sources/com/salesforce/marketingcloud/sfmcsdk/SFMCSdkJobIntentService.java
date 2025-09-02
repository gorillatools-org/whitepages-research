package com.salesforce.marketingcloud.sfmcsdk;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.core.app.JobIntentService;
import com.salesforce.marketingcloud.sfmcsdk.components.behaviors.BehaviorManagerImpl;
import com.salesforce.marketingcloud.sfmcsdk.components.behaviors.BehaviorType;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class SFMCSdkJobIntentService extends JobIntentService {
    private static final String ACTION_SYSTEM_BEHAVIOR = "com.salesforce.marketingcloud.sfmcsdk.SYSTEM_BEHAVIOR";
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final String EXTRA_BEHAVIOR = "behavior";
    private static final String EXTRA_DATA = "data";
    private static final int JOB_ID = 331122;

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final void enqueueSystemBehavior(Context context, BehaviorType behaviorType, Bundle bundle) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(behaviorType, "behaviorType");
            Bundle bundle2 = new Bundle();
            bundle2.putString(SFMCSdkJobIntentService.EXTRA_BEHAVIOR, behaviorType.getIntentFilter$sfmcsdk_release());
            bundle2.putBundle("data", bundle);
            JobIntentService.enqueueWork(context, (Class<?>) SFMCSdkJobIntentService.class, (int) SFMCSdkJobIntentService.JOB_ID, new Intent(SFMCSdkJobIntentService.ACTION_SYSTEM_BEHAVIOR).putExtras(bundle2));
        }
    }

    /* access modifiers changed from: protected */
    public void onHandleWork(Intent intent) {
        Bundle bundleExtra;
        Intrinsics.checkNotNullParameter(intent, "intent");
        String action = intent.getAction();
        if (action != null) {
            Context applicationContext = getApplicationContext();
            if (Intrinsics.areEqual((Object) action, (Object) ACTION_SYSTEM_BEHAVIOR) && (bundleExtra = intent.getBundleExtra("data")) != null) {
                Intrinsics.checkNotNullExpressionValue(applicationContext, "context");
                handleSystemBehavior(applicationContext, BehaviorType.Companion.fromString(intent.getStringExtra(EXTRA_BEHAVIOR)), bundleExtra);
            }
        }
    }

    private final void handleSystemBehavior(Context context, BehaviorType behaviorType, Bundle bundle) {
        if (behaviorType != null) {
            BehaviorManagerImpl.Companion.notifyBehavior$sfmcsdk_release(context, behaviorType, bundle);
        }
    }
}
