package com.salesforce.marketingcloud.sfmcsdk.components.behaviors;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import com.salesforce.marketingcloud.sfmcsdk.components.behaviors.BehaviorManagerImpl;
import com.salesforce.marketingcloud.sfmcsdk.components.logging.SFMCSdkLogger;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Unit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

public final class LifecycleListener implements DefaultLifecycleObserver, Application.ActivityLifecycleCallbacks {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final String TAG = "~$LifecycleListener";
    /* access modifiers changed from: private */
    @SuppressLint({"StaticFieldLeak"})
    public static LifecycleListener instance;
    private Context context;
    private AtomicBoolean inForeground;

    public /* synthetic */ LifecycleListener(Context context2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context2);
    }

    public void onActivityCreated(Activity activity, Bundle bundle) {
        Intrinsics.checkNotNullParameter(activity, "activity");
    }

    public void onActivityDestroyed(Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
    }

    public void onActivityPaused(Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
    }

    public void onActivityResumed(Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
    }

    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(bundle, "outState");
    }

    public void onActivityStopped(Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
    }

    public /* bridge */ /* synthetic */ void onCreate(LifecycleOwner lifecycleOwner) {
        super.onCreate(lifecycleOwner);
    }

    public /* bridge */ /* synthetic */ void onDestroy(LifecycleOwner lifecycleOwner) {
        super.onDestroy(lifecycleOwner);
    }

    public /* bridge */ /* synthetic */ void onPause(LifecycleOwner lifecycleOwner) {
        super.onPause(lifecycleOwner);
    }

    public /* bridge */ /* synthetic */ void onResume(LifecycleOwner lifecycleOwner) {
        super.onResume(lifecycleOwner);
    }

    private LifecycleListener(Context context2) {
        this.context = context2;
        this.inForeground = new AtomicBoolean(false);
    }

    public final AtomicBoolean getInForeground() {
        return this.inForeground;
    }

    public final void setInForeground(AtomicBoolean atomicBoolean) {
        Intrinsics.checkNotNullParameter(atomicBoolean, "<set-?>");
        this.inForeground = atomicBoolean;
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final LifecycleListener getInstance$sfmcsdk_release() {
            return LifecycleListener.instance;
        }

        public final void setInstance$sfmcsdk_release(LifecycleListener lifecycleListener) {
            LifecycleListener.instance = lifecycleListener;
        }

        public final LifecycleListener getInstance(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            if (getInstance$sfmcsdk_release() == null) {
                setInstance$sfmcsdk_release(new LifecycleListener(context, (DefaultConstructorMarker) null));
            }
            LifecycleListener instance$sfmcsdk_release = getInstance$sfmcsdk_release();
            if (instance$sfmcsdk_release != null) {
                return instance$sfmcsdk_release;
            }
            throw new NullPointerException("null cannot be cast to non-null type com.salesforce.marketingcloud.sfmcsdk.components.behaviors.LifecycleListener");
        }
    }

    public void onStart(LifecycleOwner lifecycleOwner) {
        Intrinsics.checkNotNullParameter(lifecycleOwner, "owner");
        super.onStart(lifecycleOwner);
        if (!this.inForeground.getAndSet(true)) {
            SFMCSdkLogger.INSTANCE.d(TAG, LifecycleListener$onStart$1.INSTANCE);
            BehaviorManagerImpl.Companion.notifyBehavior$sfmcsdk_release(this.context, BehaviorType.APPLICATION_FOREGROUNDED, new Bundle());
        }
    }

    public void onStop(LifecycleOwner lifecycleOwner) {
        Intrinsics.checkNotNullParameter(lifecycleOwner, "owner");
        if (this.inForeground.getAndSet(false)) {
            SFMCSdkLogger.INSTANCE.d(TAG, LifecycleListener$onStop$1.INSTANCE);
            BehaviorManagerImpl.Companion.notifyBehavior$sfmcsdk_release(this.context, BehaviorType.APPLICATION_BACKGROUNDED, new Bundle());
        }
        super.onStop(lifecycleOwner);
    }

    public void onActivityStarted(Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        BehaviorManagerImpl.Companion companion = BehaviorManagerImpl.Companion;
        Context context2 = this.context;
        BehaviorType behaviorType = BehaviorType.SCREEN_ENTRY;
        Bundle bundle = new Bundle();
        bundle.putString("screen_name", Reflection.getOrCreateKotlinClass(activity.getClass()).getSimpleName());
        Unit unit = Unit.INSTANCE;
        companion.notifyBehavior$sfmcsdk_release(context2, behaviorType, bundle);
    }
}
