package com.facebook.appevents.suggestedevents;

import android.app.Activity;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.ViewTreeObserver;
import com.facebook.appevents.codeless.internal.SensitiveUserDataUtils;
import com.facebook.appevents.internal.AppEventUtility;
import com.facebook.appevents.suggestedevents.ViewOnClickListener;
import com.facebook.internal.instrument.crashshield.CrashShieldHandler;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class ViewObserver implements ViewTreeObserver.OnGlobalLayoutListener {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final Map observers = new HashMap();
    private final WeakReference activityWeakReference;
    private final AtomicBoolean isTracking;
    private final Handler uiThreadHandler;

    public /* synthetic */ ViewObserver(Activity activity, DefaultConstructorMarker defaultConstructorMarker) {
        this(activity);
    }

    private ViewObserver(Activity activity) {
        this.activityWeakReference = new WeakReference(activity);
        this.uiThreadHandler = new Handler(Looper.getMainLooper());
        this.isTracking = new AtomicBoolean(false);
    }

    public static final /* synthetic */ Map access$getObservers$cp() {
        Class<ViewObserver> cls = ViewObserver.class;
        if (CrashShieldHandler.isObjectCrashing(cls)) {
            return null;
        }
        try {
            return observers;
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, cls);
            return null;
        }
    }

    public static final /* synthetic */ void access$startTracking(ViewObserver viewObserver) {
        Class<ViewObserver> cls = ViewObserver.class;
        if (!CrashShieldHandler.isObjectCrashing(cls)) {
            try {
                viewObserver.startTracking();
            } catch (Throwable th) {
                CrashShieldHandler.handleThrowable(th, cls);
            }
        }
    }

    public static final /* synthetic */ void access$stopTracking(ViewObserver viewObserver) {
        Class<ViewObserver> cls = ViewObserver.class;
        if (!CrashShieldHandler.isObjectCrashing(cls)) {
            try {
                viewObserver.stopTracking();
            } catch (Throwable th) {
                CrashShieldHandler.handleThrowable(th, cls);
            }
        }
    }

    private final void startTracking() {
        View rootView;
        if (!CrashShieldHandler.isObjectCrashing(this)) {
            try {
                if (!this.isTracking.getAndSet(true) && (rootView = AppEventUtility.getRootView((Activity) this.activityWeakReference.get())) != null) {
                    ViewTreeObserver viewTreeObserver = rootView.getViewTreeObserver();
                    if (viewTreeObserver.isAlive()) {
                        viewTreeObserver.addOnGlobalLayoutListener(this);
                        process();
                    }
                }
            } catch (Throwable th) {
                CrashShieldHandler.handleThrowable(th, this);
            }
        }
    }

    private final void stopTracking() {
        View rootView;
        if (!CrashShieldHandler.isObjectCrashing(this)) {
            try {
                if (this.isTracking.getAndSet(false) && (rootView = AppEventUtility.getRootView((Activity) this.activityWeakReference.get())) != null) {
                    ViewTreeObserver viewTreeObserver = rootView.getViewTreeObserver();
                    if (viewTreeObserver.isAlive()) {
                        viewTreeObserver.removeOnGlobalLayoutListener(this);
                    }
                }
            } catch (Throwable th) {
                CrashShieldHandler.handleThrowable(th, this);
            }
        }
    }

    public void onGlobalLayout() {
        if (!CrashShieldHandler.isObjectCrashing(this)) {
            try {
                process();
            } catch (Throwable th) {
                CrashShieldHandler.handleThrowable(th, this);
            }
        }
    }

    private final void process() {
        if (!CrashShieldHandler.isObjectCrashing(this)) {
            try {
                ViewObserver$$ExternalSyntheticLambda0 viewObserver$$ExternalSyntheticLambda0 = new ViewObserver$$ExternalSyntheticLambda0(this);
                if (Thread.currentThread() == Looper.getMainLooper().getThread()) {
                    viewObserver$$ExternalSyntheticLambda0.run();
                } else {
                    this.uiThreadHandler.post(viewObserver$$ExternalSyntheticLambda0);
                }
            } catch (Throwable th) {
                CrashShieldHandler.handleThrowable(th, this);
            }
        }
    }

    /* access modifiers changed from: private */
    public static final void process$lambda$0(ViewObserver viewObserver) {
        Class<ViewObserver> cls = ViewObserver.class;
        if (!CrashShieldHandler.isObjectCrashing(cls)) {
            try {
                Intrinsics.checkNotNullParameter(viewObserver, "this$0");
                try {
                    View rootView = AppEventUtility.getRootView((Activity) viewObserver.activityWeakReference.get());
                    Activity activity = (Activity) viewObserver.activityWeakReference.get();
                    if (rootView == null) {
                        return;
                    }
                    if (activity != null) {
                        for (View view : SuggestedEventViewHierarchy.getAllClickableViews(rootView)) {
                            if (!SensitiveUserDataUtils.isSensitiveUserData(view)) {
                                String textOfViewRecursively = SuggestedEventViewHierarchy.getTextOfViewRecursively(view);
                                if (textOfViewRecursively.length() > 0 && textOfViewRecursively.length() <= 300) {
                                    ViewOnClickListener.Companion companion = ViewOnClickListener.Companion;
                                    String localClassName = activity.getLocalClassName();
                                    Intrinsics.checkNotNullExpressionValue(localClassName, "activity.localClassName");
                                    companion.attachListener$facebook_core_release(view, rootView, localClassName);
                                }
                            }
                        }
                    }
                } catch (Exception unused) {
                }
            } catch (Throwable th) {
                CrashShieldHandler.handleThrowable(th, cls);
            }
        }
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final void startTrackingActivity(Activity activity) {
            Intrinsics.checkNotNullParameter(activity, "activity");
            int hashCode = activity.hashCode();
            Map access$getObservers$cp = ViewObserver.access$getObservers$cp();
            Integer valueOf = Integer.valueOf(hashCode);
            Object obj = access$getObservers$cp.get(valueOf);
            if (obj == null) {
                obj = new ViewObserver(activity, (DefaultConstructorMarker) null);
                access$getObservers$cp.put(valueOf, obj);
            }
            ViewObserver.access$startTracking((ViewObserver) obj);
        }

        public final void stopTrackingActivity(Activity activity) {
            Intrinsics.checkNotNullParameter(activity, "activity");
            ViewObserver viewObserver = (ViewObserver) ViewObserver.access$getObservers$cp().remove(Integer.valueOf(activity.hashCode()));
            if (viewObserver != null) {
                ViewObserver.access$stopTracking(viewObserver);
            }
        }
    }
}
