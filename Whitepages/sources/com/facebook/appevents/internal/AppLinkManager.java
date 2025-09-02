package com.facebook.appevents.internal;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import com.facebook.FacebookSdk;
import com.facebook.internal.instrument.crashshield.CrashShieldHandler;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;

public final class AppLinkManager {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static volatile AppLinkManager instance;
    private final Lazy preferences$delegate;

    public /* synthetic */ AppLinkManager(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    private AppLinkManager() {
        this.preferences$delegate = LazyKt.lazy(AppLinkManager$preferences$2.INSTANCE);
    }

    public static final /* synthetic */ AppLinkManager access$getInstance$cp() {
        Class<AppLinkManager> cls = AppLinkManager.class;
        if (CrashShieldHandler.isObjectCrashing(cls)) {
            return null;
        }
        try {
            return instance;
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, cls);
            return null;
        }
    }

    public static final /* synthetic */ void access$setInstance$cp(AppLinkManager appLinkManager) {
        Class<AppLinkManager> cls = AppLinkManager.class;
        if (!CrashShieldHandler.isObjectCrashing(cls)) {
            try {
                instance = appLinkManager;
            } catch (Throwable th) {
                CrashShieldHandler.handleThrowable(th, cls);
            }
        }
    }

    private final SharedPreferences getPreferences() {
        if (CrashShieldHandler.isObjectCrashing(this)) {
            return null;
        }
        try {
            Object value = this.preferences$delegate.getValue();
            Intrinsics.checkNotNullExpressionValue(value, "<get-preferences>(...)");
            return (SharedPreferences) value;
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, this);
            return null;
        }
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final AppLinkManager getInstance() {
            AppLinkManager access$getInstance$cp = AppLinkManager.access$getInstance$cp();
            if (access$getInstance$cp == null) {
                synchronized (this) {
                    if (!FacebookSdk.isInitialized()) {
                        return null;
                    }
                    access$getInstance$cp = AppLinkManager.access$getInstance$cp();
                    if (access$getInstance$cp == null) {
                        access$getInstance$cp = new AppLinkManager((DefaultConstructorMarker) null);
                        AppLinkManager.access$setInstance$cp(access$getInstance$cp);
                    }
                }
            }
            return access$getInstance$cp;
        }
    }

    public final void handleURL(Activity activity) {
        if (!CrashShieldHandler.isObjectCrashing(this)) {
            try {
                Intrinsics.checkNotNullParameter(activity, "activity");
                Uri data = activity.getIntent().getData();
                if (data != null) {
                    Intent intent = activity.getIntent();
                    Intrinsics.checkNotNullExpressionValue(intent, "activity.intent");
                    processCampaignIds(data, intent);
                }
            } catch (Throwable th) {
                CrashShieldHandler.handleThrowable(th, this);
            }
        }
    }

    public final void processCampaignIds(Uri uri, Intent intent) {
        if (!CrashShieldHandler.isObjectCrashing(this)) {
            try {
                Intrinsics.checkNotNullParameter(uri, "uri");
                Intrinsics.checkNotNullParameter(intent, "intent");
                String campaignIDFromUri = getCampaignIDFromUri(uri);
                if (campaignIDFromUri == null) {
                    campaignIDFromUri = getCampaignIDFromIntentExtra(intent);
                }
                if (campaignIDFromUri != null) {
                    getPreferences().edit().putString("campaign_ids", campaignIDFromUri).apply();
                }
            } catch (Throwable th) {
                CrashShieldHandler.handleThrowable(th, this);
            }
        }
    }

    public final String getCampaignIDFromUri(Uri uri) {
        if (CrashShieldHandler.isObjectCrashing(this)) {
            return null;
        }
        try {
            Intrinsics.checkNotNullParameter(uri, "uri");
            String queryParameter = uri.getQueryParameter("al_applink_data");
            if (queryParameter == null) {
                return null;
            }
            return new JSONObject(queryParameter).getString("campaign_ids");
        } catch (Exception unused) {
            Log.d("AppLinkManager", "Fail to parse Applink data from Uri");
            return null;
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, this);
            return null;
        }
    }

    public final String getCampaignIDFromIntentExtra(Intent intent) {
        if (CrashShieldHandler.isObjectCrashing(this)) {
            return null;
        }
        try {
            Intrinsics.checkNotNullParameter(intent, "intent");
            Bundle bundleExtra = intent.getBundleExtra("al_applink_data");
            if (bundleExtra == null) {
                return null;
            }
            return bundleExtra.getString("campaign_ids");
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, this);
            return null;
        }
    }

    public final String getInfo(String str) {
        if (CrashShieldHandler.isObjectCrashing(this)) {
            return null;
        }
        try {
            Intrinsics.checkNotNullParameter(str, "key");
            return getPreferences().getString(str, (String) null);
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, this);
            return null;
        }
    }

    public final void setupLifecycleListener(Application application) {
        if (!CrashShieldHandler.isObjectCrashing(this)) {
            try {
                Intrinsics.checkNotNullParameter(application, "application");
                application.registerActivityLifecycleCallbacks(new AppLinkManager$setupLifecycleListener$1());
            } catch (Throwable th) {
                CrashShieldHandler.handleThrowable(th, this);
            }
        }
    }
}
