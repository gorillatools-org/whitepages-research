package com.wpconnect;

import android.content.Context;
import android.content.SharedPreferences;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class CallerIdPreferences {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final String KEY_PERMISSIONS_GRANTED = "permissions_granted";
    private static final String KEY_SERVICE_ENABLED = "service_enabled";
    private static final String PREFS_NAME = "caller_id_prefs";
    private static final String TAG = "CallerIdPreferences";
    /* access modifiers changed from: private */
    public static volatile CallerIdPreferences instance;
    private final SharedPreferences prefs;

    public /* synthetic */ CallerIdPreferences(Context context, DefaultConstructorMarker defaultConstructorMarker) {
        this(context);
    }

    private CallerIdPreferences(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFS_NAME, 0);
        Intrinsics.checkNotNullExpressionValue(sharedPreferences, "getSharedPreferences(...)");
        this.prefs = sharedPreferences;
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final CallerIdPreferences getInstance(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            CallerIdPreferences access$getInstance$cp = CallerIdPreferences.instance;
            if (access$getInstance$cp == null) {
                synchronized (this) {
                    access$getInstance$cp = CallerIdPreferences.instance;
                    if (access$getInstance$cp == null) {
                        Context applicationContext = context.getApplicationContext();
                        Intrinsics.checkNotNullExpressionValue(applicationContext, "getApplicationContext(...)");
                        access$getInstance$cp = new CallerIdPreferences(applicationContext, (DefaultConstructorMarker) null);
                        CallerIdPreferences.instance = access$getInstance$cp;
                    }
                }
            }
            return access$getInstance$cp;
        }
    }

    public final void setServiceEnabled(boolean z) {
        this.prefs.edit().putBoolean(KEY_SERVICE_ENABLED, z).apply();
    }

    public final void setPermissionsGranted(boolean z) {
        this.prefs.edit().putBoolean(KEY_PERMISSIONS_GRANTED, z).apply();
    }
}
