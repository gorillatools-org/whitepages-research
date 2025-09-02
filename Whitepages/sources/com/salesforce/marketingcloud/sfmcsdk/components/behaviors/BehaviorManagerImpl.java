package com.salesforce.marketingcloud.sfmcsdk.components.behaviors;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.ArrayMap;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.google.firebase.messaging.Constants;
import com.salesforce.marketingcloud.sfmcsdk.SFMCSdk;
import com.salesforce.marketingcloud.sfmcsdk.components.logging.SFMCSdkLogger;
import com.salesforce.marketingcloud.sfmcsdk.util.ApplicationUtils;
import com.salesforce.marketingcloud.sfmcsdk.util.FileUtilsKt;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import kotlin.Unit;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class BehaviorManagerImpl implements BehaviorManager {
    public static final String BUNDLE_KEY_APP_NAME = "application_name";
    public static final String BUNDLE_KEY_CURRENT_VERSION = "current_version";
    public static final String BUNDLE_KEY_PREVIOUS_VERSION = "previous_version";
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final String KEY_PREFS_CAPTURED_APP_VERSION = "captured_app_version";
    private static final String TAG = "~$BehaviorManager";
    private BehaviorReceiver behaviorReceiver;
    private final ArrayMap<BehaviorType, Set<BehaviorListener>> behaviorTypeListeners = new ArrayMap<>();
    private Context context;
    private final ExecutorService executorService;
    private final Map<BehaviorType, Bundle> stickyBehaviors = new LinkedHashMap();

    public BehaviorManagerImpl(ExecutorService executorService2) {
        Intrinsics.checkNotNullParameter(executorService2, "executorService");
        this.executorService = executorService2;
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public static /* synthetic */ void getKEY_PREFS_CAPTURED_APP_VERSION$sfmcsdk_release$annotations() {
        }

        private Companion() {
        }

        public final void notifyBehavior$sfmcsdk_release(Context context, BehaviorType behaviorType, Bundle bundle) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(behaviorType, "behaviorType");
            Intrinsics.checkNotNullParameter(bundle, "extras");
            Intent intent = new Intent(behaviorType.getIntentFilter$sfmcsdk_release());
            bundle.putString(BehaviorManagerImpl.BUNDLE_KEY_APP_NAME, ApplicationUtils.getApplicationName(context));
            bundle.putString(BehaviorManagerImpl.BUNDLE_KEY_CURRENT_VERSION, ApplicationUtils.getApplicationVersion(context));
            intent.putExtras(bundle);
            SFMCSdkLogger.INSTANCE.d(BehaviorManagerImpl.TAG, new BehaviorManagerImpl$Companion$notifyBehavior$1(behaviorType, bundle));
            LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
        }

        public static final class BehaviorRunnable implements Runnable {
            private final Behavior behavior;
            /* access modifiers changed from: private */
            public final BehaviorType behaviorType;
            private final Set<BehaviorListener> listeners;

            public BehaviorRunnable(Set<? extends BehaviorListener> set, BehaviorType behaviorType2, Bundle bundle) {
                Intrinsics.checkNotNullParameter(set, "listeners");
                Intrinsics.checkNotNullParameter(behaviorType2, "behaviorType");
                Intrinsics.checkNotNullParameter(bundle, Constants.ScionAnalytics.MessageType.DATA_MESSAGE);
                this.listeners = set;
                this.behaviorType = behaviorType2;
                this.behavior = behaviorType2.toBehavior$sfmcsdk_release(bundle);
            }

            public void run() {
                Behavior behavior2 = this.behavior;
                if (behavior2 != null) {
                    SFMCSdk.Companion.track(BehaviorTypeKt.toEvent(behavior2));
                    for (BehaviorListener behaviorListener : this.listeners) {
                        try {
                            SFMCSdkLogger.INSTANCE.d(BehaviorManagerImpl.TAG, new BehaviorManagerImpl$Companion$BehaviorRunnable$run$1$1$1$1(this, behaviorListener));
                            behaviorListener.onBehavior(behavior2);
                        } catch (Exception e) {
                            SFMCSdkLogger.INSTANCE.e(BehaviorManagerImpl.TAG, e, new BehaviorManagerImpl$Companion$BehaviorRunnable$run$1$1$1$2(e));
                        }
                    }
                }
            }
        }
    }

    public final ArrayMap<BehaviorType, Set<BehaviorListener>> getBehaviorTypeListeners$sfmcsdk_release() {
        return this.behaviorTypeListeners;
    }

    public void registerForBehaviors(EnumSet<BehaviorType> enumSet, BehaviorListener behaviorListener) {
        Bundle bundle;
        Intrinsics.checkNotNullParameter(enumSet, "behaviorTypes");
        Intrinsics.checkNotNullParameter(behaviorListener, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
        synchronized (this.behaviorTypeListeners) {
            try {
                for (BehaviorType behaviorType : enumSet) {
                    Set set = this.behaviorTypeListeners.get(behaviorType);
                    if (set == null) {
                        set = new HashSet();
                        this.behaviorTypeListeners.put(behaviorType, set);
                    }
                    SFMCSdkLogger.INSTANCE.d(TAG, new BehaviorManagerImpl$registerForBehaviors$1$1$1(behaviorListener, behaviorType));
                    set.add(behaviorListener);
                }
                Unit unit = Unit.INSTANCE;
            } finally {
            }
        }
        synchronized (this.stickyBehaviors) {
            try {
                for (BehaviorType behaviorType2 : enumSet) {
                    if (behaviorType2.getSticky$sfmcsdk_release() && (bundle = this.stickyBehaviors.get(behaviorType2)) != null) {
                        ExecutorService executorService2 = this.executorService;
                        Set of = SetsKt.setOf(behaviorListener);
                        Intrinsics.checkNotNullExpressionValue(behaviorType2, "behaviorType");
                        executorService2.submit(new Companion.BehaviorRunnable(of, behaviorType2, bundle));
                        SFMCSdkLogger.INSTANCE.d(TAG, new BehaviorManagerImpl$registerForBehaviors$2$1$1$1(behaviorType2, behaviorListener));
                    }
                }
                Unit unit2 = Unit.INSTANCE;
            } finally {
            }
        }
    }

    public void unregisterForAllBehaviors(BehaviorListener behaviorListener) {
        Intrinsics.checkNotNullParameter(behaviorListener, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
        synchronized (this.behaviorTypeListeners) {
            try {
                SFMCSdkLogger.INSTANCE.d(TAG, new BehaviorManagerImpl$unregisterForAllBehaviors$1$1(behaviorListener));
                Set<Map.Entry<BehaviorType, Set<BehaviorListener>>> entrySet = this.behaviorTypeListeners.entrySet();
                Intrinsics.checkNotNullExpressionValue(entrySet, "behaviorTypeListeners.entries");
                for (Map.Entry value : entrySet) {
                    ((Set) value.getValue()).remove(behaviorListener);
                }
                Unit unit = Unit.INSTANCE;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    /* access modifiers changed from: private */
    public final void onBehavior(BehaviorType behaviorType, Bundle bundle) {
        bundle.putLong("timestamp", System.currentTimeMillis());
        synchronized (this.behaviorTypeListeners) {
            Set set = this.behaviorTypeListeners.get(behaviorType);
            if (set != null) {
                Intrinsics.checkNotNullExpressionValue(set, "it");
                if (!set.isEmpty()) {
                    try {
                        this.executorService.submit(new Companion.BehaviorRunnable(set, behaviorType, bundle));
                    } catch (Exception e) {
                        SFMCSdkLogger.INSTANCE.e(TAG, e, new BehaviorManagerImpl$onBehavior$1$1$1(behaviorType, bundle));
                    }
                }
                Unit unit = Unit.INSTANCE;
            }
        }
        synchronized (this.stickyBehaviors) {
            try {
                List<BehaviorType> behaviorTypesToClear$sfmcsdk_release = behaviorType.getBehaviorTypesToClear$sfmcsdk_release();
                if (behaviorTypesToClear$sfmcsdk_release != null) {
                    for (BehaviorType put : behaviorTypesToClear$sfmcsdk_release) {
                        this.stickyBehaviors.put(put, (Object) null);
                    }
                }
                if (behaviorType.getSticky$sfmcsdk_release()) {
                    this.stickyBehaviors.put(behaviorType, bundle);
                }
                Unit unit2 = Unit.INSTANCE;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final BehaviorManager initIfNecessary$sfmcsdk_release(Context context2) {
        Intrinsics.checkNotNullParameter(context2, "context");
        if (this.context == null) {
            this.context = context2;
        }
        if (this.behaviorReceiver == null) {
            SFMCSdkLogger.INSTANCE.d(TAG, BehaviorManagerImpl$initIfNecessary$1.INSTANCE);
            BehaviorReceiver behaviorReceiver2 = new BehaviorReceiver();
            this.behaviorReceiver = behaviorReceiver2;
            LocalBroadcastManager instance = LocalBroadcastManager.getInstance(context2);
            IntentFilter intentFilter = new IntentFilter();
            for (BehaviorType intentFilter$sfmcsdk_release : BehaviorType.values()) {
                intentFilter.addAction(intentFilter$sfmcsdk_release.getIntentFilter$sfmcsdk_release());
            }
            Unit unit = Unit.INSTANCE;
            instance.registerReceiver(behaviorReceiver2, intentFilter);
            SharedPreferences sharedPreferences = context2.getSharedPreferences(FileUtilsKt.getFilenamePrefixForSFMCSdk(com.facebook.hermes.intl.Constants.COLLATION_DEFAULT), 0);
            String string = sharedPreferences.getString(KEY_PREFS_CAPTURED_APP_VERSION, (String) null);
            String applicationVersion = ApplicationUtils.getApplicationVersion(context2);
            if (!Intrinsics.areEqual((Object) applicationVersion, (Object) string) && applicationVersion != null) {
                sharedPreferences.edit().putString(KEY_PREFS_CAPTURED_APP_VERSION, applicationVersion).apply();
                Companion companion = Companion;
                BehaviorType behaviorType = BehaviorType.APP_VERSION_CHANGED;
                Bundle bundle = new Bundle();
                bundle.putString(BUNDLE_KEY_PREVIOUS_VERSION, string);
                Unit unit2 = Unit.INSTANCE;
                companion.notifyBehavior$sfmcsdk_release(context2, behaviorType, bundle);
            }
        }
        return this;
    }

    public final void tearDown$sfmcsdk_release() {
        BehaviorReceiver behaviorReceiver2;
        Context context2 = this.context;
        if (context2 != null && (behaviorReceiver2 = this.behaviorReceiver) != null) {
            LocalBroadcastManager.getInstance(context2).unregisterReceiver(behaviorReceiver2);
        }
    }

    private final class BehaviorReceiver extends BroadcastReceiver {
        public BehaviorReceiver() {
        }

        public void onReceive(Context context, Intent intent) {
            if (intent != null) {
                BehaviorManagerImpl behaviorManagerImpl = BehaviorManagerImpl.this;
                String action = intent.getAction();
                if (action != null) {
                    BehaviorType fromString = BehaviorType.Companion.fromString(action);
                    if (fromString != null) {
                        SFMCSdkLogger.INSTANCE.d(BehaviorManagerImpl.TAG, new BehaviorManagerImpl$BehaviorReceiver$onReceive$1$1$1(fromString, intent));
                        Bundle extras = intent.getExtras();
                        if (extras == null) {
                            extras = new Bundle();
                        }
                        Intrinsics.checkNotNullExpressionValue(extras, "it.extras ?: Bundle()");
                        behaviorManagerImpl.onBehavior(fromString, extras);
                        return;
                    }
                    SFMCSdkLogger.INSTANCE.w(BehaviorManagerImpl.TAG, BehaviorManagerImpl$BehaviorReceiver$onReceive$1$1$2.INSTANCE);
                }
            }
        }
    }
}
