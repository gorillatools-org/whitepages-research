package androidx.lifecycle;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Handler;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.ReportFragment;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class ProcessLifecycleOwner implements LifecycleOwner {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    public static final ProcessLifecycleOwner newInstance = new ProcessLifecycleOwner();
    private final Runnable delayedPauseRunnable = new ProcessLifecycleOwner$$ExternalSyntheticLambda0(this);
    private Handler handler;
    /* access modifiers changed from: private */
    public final ReportFragment.ActivityInitializationListener initializationListener = new ProcessLifecycleOwner$initializationListener$1(this);
    private boolean pauseSent = true;
    private final LifecycleRegistry registry = new LifecycleRegistry(this);
    private int resumedCounter;
    private int startedCounter;
    private boolean stopSent = true;

    public static final LifecycleOwner get() {
        return Companion.get();
    }

    private ProcessLifecycleOwner() {
    }

    /* access modifiers changed from: private */
    public static final void delayedPauseRunnable$lambda$0(ProcessLifecycleOwner processLifecycleOwner) {
        Intrinsics.checkNotNullParameter(processLifecycleOwner, "this$0");
        processLifecycleOwner.dispatchPauseIfNeeded$lifecycle_process_release();
        processLifecycleOwner.dispatchStopIfNeeded$lifecycle_process_release();
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final LifecycleOwner get() {
            return ProcessLifecycleOwner.newInstance;
        }

        public final void init$lifecycle_process_release(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            ProcessLifecycleOwner.newInstance.attach$lifecycle_process_release(context);
        }
    }

    public final void activityStarted$lifecycle_process_release() {
        int i = this.startedCounter + 1;
        this.startedCounter = i;
        if (i == 1 && this.stopSent) {
            this.registry.handleLifecycleEvent(Lifecycle.Event.ON_START);
            this.stopSent = false;
        }
    }

    public final void activityResumed$lifecycle_process_release() {
        int i = this.resumedCounter + 1;
        this.resumedCounter = i;
        if (i != 1) {
            return;
        }
        if (this.pauseSent) {
            this.registry.handleLifecycleEvent(Lifecycle.Event.ON_RESUME);
            this.pauseSent = false;
            return;
        }
        Handler handler2 = this.handler;
        Intrinsics.checkNotNull(handler2);
        handler2.removeCallbacks(this.delayedPauseRunnable);
    }

    public final void activityPaused$lifecycle_process_release() {
        int i = this.resumedCounter - 1;
        this.resumedCounter = i;
        if (i == 0) {
            Handler handler2 = this.handler;
            Intrinsics.checkNotNull(handler2);
            handler2.postDelayed(this.delayedPauseRunnable, 700);
        }
    }

    public final void activityStopped$lifecycle_process_release() {
        this.startedCounter--;
        dispatchStopIfNeeded$lifecycle_process_release();
    }

    public final void dispatchPauseIfNeeded$lifecycle_process_release() {
        if (this.resumedCounter == 0) {
            this.pauseSent = true;
            this.registry.handleLifecycleEvent(Lifecycle.Event.ON_PAUSE);
        }
    }

    public final void dispatchStopIfNeeded$lifecycle_process_release() {
        if (this.startedCounter == 0 && this.pauseSent) {
            this.registry.handleLifecycleEvent(Lifecycle.Event.ON_STOP);
            this.stopSent = true;
        }
    }

    public final void attach$lifecycle_process_release(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.handler = new Handler();
        this.registry.handleLifecycleEvent(Lifecycle.Event.ON_CREATE);
        Context applicationContext = context.getApplicationContext();
        Intrinsics.checkNotNull(applicationContext, "null cannot be cast to non-null type android.app.Application");
        ((Application) applicationContext).registerActivityLifecycleCallbacks(new ProcessLifecycleOwner$attach$1(this));
    }

    public Lifecycle getLifecycle() {
        return this.registry;
    }

    public static final class Api29Impl {
        public static final Api29Impl INSTANCE = new Api29Impl();

        private Api29Impl() {
        }

        public static final void registerActivityLifecycleCallbacks(Activity activity, Application.ActivityLifecycleCallbacks activityLifecycleCallbacks) {
            Intrinsics.checkNotNullParameter(activity, "activity");
            Intrinsics.checkNotNullParameter(activityLifecycleCallbacks, "callback");
            activity.registerActivityLifecycleCallbacks(activityLifecycleCallbacks);
        }
    }
}
