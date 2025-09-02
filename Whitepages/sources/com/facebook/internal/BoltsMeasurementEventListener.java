package com.facebook.internal;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.facebook.appevents.InternalAppEventsLogger;
import com.facebook.internal.instrument.crashshield.CrashShieldHandler;
import java.util.Set;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;

public final class BoltsMeasurementEventListener extends BroadcastReceiver {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final String MEASUREMENT_EVENT_NOTIFICATION_NAME = "com.parse.bolts.measurement_event";
    private static BoltsMeasurementEventListener singleton;
    private final Context applicationContext;

    public /* synthetic */ BoltsMeasurementEventListener(Context context, DefaultConstructorMarker defaultConstructorMarker) {
        this(context);
    }

    public static final /* synthetic */ BoltsMeasurementEventListener access$getSingleton$cp() {
        Class<BoltsMeasurementEventListener> cls = BoltsMeasurementEventListener.class;
        if (CrashShieldHandler.isObjectCrashing(cls)) {
            return null;
        }
        try {
            return singleton;
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, cls);
            return null;
        }
    }

    public static final /* synthetic */ void access$open(BoltsMeasurementEventListener boltsMeasurementEventListener) {
        Class<BoltsMeasurementEventListener> cls = BoltsMeasurementEventListener.class;
        if (!CrashShieldHandler.isObjectCrashing(cls)) {
            try {
                boltsMeasurementEventListener.open();
            } catch (Throwable th) {
                CrashShieldHandler.handleThrowable(th, cls);
            }
        }
    }

    public static final /* synthetic */ void access$setSingleton$cp(BoltsMeasurementEventListener boltsMeasurementEventListener) {
        Class<BoltsMeasurementEventListener> cls = BoltsMeasurementEventListener.class;
        if (!CrashShieldHandler.isObjectCrashing(cls)) {
            try {
                singleton = boltsMeasurementEventListener;
            } catch (Throwable th) {
                CrashShieldHandler.handleThrowable(th, cls);
            }
        }
    }

    private BoltsMeasurementEventListener(Context context) {
        Context applicationContext2 = context.getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext2, "context.applicationContext");
        this.applicationContext = applicationContext2;
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final BoltsMeasurementEventListener getInstance(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            if (BoltsMeasurementEventListener.access$getSingleton$cp() != null) {
                return BoltsMeasurementEventListener.access$getSingleton$cp();
            }
            BoltsMeasurementEventListener boltsMeasurementEventListener = new BoltsMeasurementEventListener(context, (DefaultConstructorMarker) null);
            BoltsMeasurementEventListener.access$open(boltsMeasurementEventListener);
            BoltsMeasurementEventListener.access$setSingleton$cp(boltsMeasurementEventListener);
            return BoltsMeasurementEventListener.access$getSingleton$cp();
        }
    }

    private final void open() {
        if (!CrashShieldHandler.isObjectCrashing(this)) {
            try {
                LocalBroadcastManager instance = LocalBroadcastManager.getInstance(this.applicationContext);
                Intrinsics.checkNotNullExpressionValue(instance, "getInstance(applicationContext)");
                instance.registerReceiver(this, new IntentFilter(MEASUREMENT_EVENT_NOTIFICATION_NAME));
            } catch (Throwable th) {
                CrashShieldHandler.handleThrowable(th, this);
            }
        }
    }

    private final void close() {
        if (!CrashShieldHandler.isObjectCrashing(this)) {
            try {
                LocalBroadcastManager instance = LocalBroadcastManager.getInstance(this.applicationContext);
                Intrinsics.checkNotNullExpressionValue(instance, "getInstance(applicationContext)");
                instance.unregisterReceiver(this);
            } catch (Throwable th) {
                CrashShieldHandler.handleThrowable(th, this);
            }
        }
    }

    public final void finalize() {
        if (!CrashShieldHandler.isObjectCrashing(this)) {
            try {
                close();
            } catch (Throwable th) {
                CrashShieldHandler.handleThrowable(th, this);
            }
        }
    }

    public void onReceive(Context context, Intent intent) {
        if (!CrashShieldHandler.isObjectCrashing(this)) {
            try {
                InternalAppEventsLogger internalAppEventsLogger = new InternalAppEventsLogger(context);
                StringBuilder sb = new StringBuilder();
                sb.append("bf_");
                Set<String> set = null;
                sb.append(intent != null ? intent.getStringExtra("event_name") : null);
                String sb2 = sb.toString();
                Bundle bundleExtra = intent != null ? intent.getBundleExtra("event_args") : null;
                Bundle bundle = new Bundle();
                if (bundleExtra != null) {
                    set = bundleExtra.keySet();
                }
                if (set != null) {
                    for (String next : set) {
                        Intrinsics.checkNotNullExpressionValue(next, "key");
                        bundle.putString(new Regex("[ -]*$").replace(new Regex("^[ -]*").replace(new Regex("[^0-9a-zA-Z _-]").replace(next, "-"), ""), ""), (String) bundleExtra.get(next));
                    }
                }
                internalAppEventsLogger.logEvent(sb2, bundle);
            } catch (Throwable th) {
                CrashShieldHandler.handleThrowable(th, this);
            }
        }
    }
}
