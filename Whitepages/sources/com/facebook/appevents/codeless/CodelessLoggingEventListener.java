package com.facebook.appevents.codeless;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.appevents.codeless.internal.EventBinding;
import com.facebook.appevents.codeless.internal.ViewHierarchy;
import com.facebook.appevents.internal.AppEventUtility;
import com.facebook.internal.instrument.crashshield.CrashShieldHandler;
import java.lang.ref.WeakReference;
import kotlin.jvm.internal.Intrinsics;

public final class CodelessLoggingEventListener {
    public static final CodelessLoggingEventListener INSTANCE = new CodelessLoggingEventListener();

    private CodelessLoggingEventListener() {
    }

    public static final AutoLoggingOnClickListener getOnClickListener(EventBinding eventBinding, View view, View view2) {
        Class<CodelessLoggingEventListener> cls = CodelessLoggingEventListener.class;
        if (CrashShieldHandler.isObjectCrashing(cls)) {
            return null;
        }
        try {
            Intrinsics.checkNotNullParameter(eventBinding, "mapping");
            Intrinsics.checkNotNullParameter(view, "rootView");
            Intrinsics.checkNotNullParameter(view2, "hostView");
            return new AutoLoggingOnClickListener(eventBinding, view, view2);
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, cls);
            return null;
        }
    }

    public static final AutoLoggingOnItemClickListener getOnItemClickListener(EventBinding eventBinding, View view, AdapterView adapterView) {
        Class<CodelessLoggingEventListener> cls = CodelessLoggingEventListener.class;
        if (CrashShieldHandler.isObjectCrashing(cls)) {
            return null;
        }
        try {
            Intrinsics.checkNotNullParameter(eventBinding, "mapping");
            Intrinsics.checkNotNullParameter(view, "rootView");
            Intrinsics.checkNotNullParameter(adapterView, "hostView");
            return new AutoLoggingOnItemClickListener(eventBinding, view, adapterView);
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, cls);
            return null;
        }
    }

    public static final void logEvent$facebook_core_release(EventBinding eventBinding, View view, View view2) {
        Class<CodelessLoggingEventListener> cls = CodelessLoggingEventListener.class;
        if (!CrashShieldHandler.isObjectCrashing(cls)) {
            try {
                Intrinsics.checkNotNullParameter(eventBinding, "mapping");
                Intrinsics.checkNotNullParameter(view, "rootView");
                Intrinsics.checkNotNullParameter(view2, "hostView");
                String eventName = eventBinding.getEventName();
                Bundle parameters = CodelessMatcher.Companion.getParameters(eventBinding, view, view2);
                INSTANCE.updateParameters$facebook_core_release(parameters);
                FacebookSdk.getExecutor().execute(new CodelessLoggingEventListener$$ExternalSyntheticLambda0(eventName, parameters));
            } catch (Throwable th) {
                CrashShieldHandler.handleThrowable(th, cls);
            }
        }
    }

    /* access modifiers changed from: private */
    public static final void logEvent$lambda$0(String str, Bundle bundle) {
        Class<CodelessLoggingEventListener> cls = CodelessLoggingEventListener.class;
        if (!CrashShieldHandler.isObjectCrashing(cls)) {
            try {
                Intrinsics.checkNotNullParameter(str, "$eventName");
                Intrinsics.checkNotNullParameter(bundle, "$parameters");
                AppEventsLogger.Companion.newLogger(FacebookSdk.getApplicationContext()).logEvent(str, bundle);
            } catch (Throwable th) {
                CrashShieldHandler.handleThrowable(th, cls);
            }
        }
    }

    public final void updateParameters$facebook_core_release(Bundle bundle) {
        if (!CrashShieldHandler.isObjectCrashing(this)) {
            try {
                Intrinsics.checkNotNullParameter(bundle, "parameters");
                String string = bundle.getString("_valueToSum");
                if (string != null) {
                    bundle.putDouble("_valueToSum", AppEventUtility.normalizePrice(string));
                }
                bundle.putString("_is_fb_codeless", "1");
            } catch (Throwable th) {
                CrashShieldHandler.handleThrowable(th, this);
            }
        }
    }

    public static final class AutoLoggingOnClickListener implements View.OnClickListener {
        private View.OnClickListener existingOnClickListener;
        private WeakReference hostView;
        private EventBinding mapping;
        private WeakReference rootView;
        private boolean supportCodelessLogging = true;

        public AutoLoggingOnClickListener(EventBinding eventBinding, View view, View view2) {
            Intrinsics.checkNotNullParameter(eventBinding, "mapping");
            Intrinsics.checkNotNullParameter(view, "rootView");
            Intrinsics.checkNotNullParameter(view2, "hostView");
            this.mapping = eventBinding;
            this.hostView = new WeakReference(view2);
            this.rootView = new WeakReference(view);
            this.existingOnClickListener = ViewHierarchy.getExistingOnClickListener(view2);
        }

        public void onClick(View view) {
            if (!CrashShieldHandler.isObjectCrashing(this)) {
                try {
                    Intrinsics.checkNotNullParameter(view, "view");
                    View.OnClickListener onClickListener = this.existingOnClickListener;
                    if (onClickListener != null) {
                        onClickListener.onClick(view);
                    }
                    View view2 = (View) this.rootView.get();
                    View view3 = (View) this.hostView.get();
                    if (view2 != null && view3 != null) {
                        EventBinding eventBinding = this.mapping;
                        Intrinsics.checkNotNull(eventBinding, "null cannot be cast to non-null type com.facebook.appevents.codeless.internal.EventBinding");
                        CodelessLoggingEventListener.logEvent$facebook_core_release(eventBinding, view2, view3);
                    }
                } catch (Throwable th) {
                    CrashShieldHandler.handleThrowable(th, this);
                }
            }
        }

        public final boolean getSupportCodelessLogging() {
            return this.supportCodelessLogging;
        }
    }

    public static final class AutoLoggingOnItemClickListener implements AdapterView.OnItemClickListener {
        private AdapterView.OnItemClickListener existingOnItemClickListener;
        private WeakReference hostView;
        private EventBinding mapping;
        private WeakReference rootView;
        private boolean supportCodelessLogging = true;

        public AutoLoggingOnItemClickListener(EventBinding eventBinding, View view, AdapterView adapterView) {
            Intrinsics.checkNotNullParameter(eventBinding, "mapping");
            Intrinsics.checkNotNullParameter(view, "rootView");
            Intrinsics.checkNotNullParameter(adapterView, "hostView");
            this.mapping = eventBinding;
            this.hostView = new WeakReference(adapterView);
            this.rootView = new WeakReference(view);
            this.existingOnItemClickListener = adapterView.getOnItemClickListener();
        }

        public void onItemClick(AdapterView adapterView, View view, int i, long j) {
            Intrinsics.checkNotNullParameter(view, "view");
            AdapterView.OnItemClickListener onItemClickListener = this.existingOnItemClickListener;
            if (onItemClickListener != null) {
                onItemClickListener.onItemClick(adapterView, view, i, j);
            }
            View view2 = (View) this.rootView.get();
            AdapterView adapterView2 = (AdapterView) this.hostView.get();
            if (view2 != null && adapterView2 != null) {
                CodelessLoggingEventListener.logEvent$facebook_core_release(this.mapping, view2, adapterView2);
            }
        }

        public final boolean getSupportCodelessLogging() {
            return this.supportCodelessLogging;
        }
    }
}
