package com.facebook.appevents.aam;

import android.app.Activity;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.EditText;
import com.facebook.appevents.InternalAppEventsLogger;
import com.facebook.appevents.internal.AppEventUtility;
import com.facebook.internal.instrument.crashshield.CrashShieldHandler;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;
import kotlin.text.StringsKt;

public final class MetadataViewObserver implements ViewTreeObserver.OnGlobalFocusChangeListener {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final Map observers = new HashMap();
    private final WeakReference activityWeakReference;
    private final AtomicBoolean isTracking;
    private final Set processedText;
    private final Handler uiThreadHandler;

    public /* synthetic */ MetadataViewObserver(Activity activity, DefaultConstructorMarker defaultConstructorMarker) {
        this(activity);
    }

    private MetadataViewObserver(Activity activity) {
        this.processedText = new LinkedHashSet();
        this.uiThreadHandler = new Handler(Looper.getMainLooper());
        this.activityWeakReference = new WeakReference(activity);
        this.isTracking = new AtomicBoolean(false);
    }

    public static final /* synthetic */ Map access$getObservers$cp() {
        Class<MetadataViewObserver> cls = MetadataViewObserver.class;
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

    public static final /* synthetic */ void access$startTracking(MetadataViewObserver metadataViewObserver) {
        Class<MetadataViewObserver> cls = MetadataViewObserver.class;
        if (!CrashShieldHandler.isObjectCrashing(cls)) {
            try {
                metadataViewObserver.startTracking();
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
                        viewTreeObserver.addOnGlobalFocusChangeListener(this);
                    }
                }
            } catch (Throwable th) {
                CrashShieldHandler.handleThrowable(th, this);
            }
        }
    }

    public void onGlobalFocusChanged(View view, View view2) {
        if (!CrashShieldHandler.isObjectCrashing(this)) {
            if (view != null) {
                try {
                    process(view);
                } catch (Throwable th) {
                    CrashShieldHandler.handleThrowable(th, this);
                    return;
                }
            }
            if (view2 != null) {
                process(view2);
            }
        }
    }

    private final void process(View view) {
        if (!CrashShieldHandler.isObjectCrashing(this)) {
            try {
                runOnUIThread(new MetadataViewObserver$$ExternalSyntheticLambda0(view, this));
            } catch (Throwable th) {
                CrashShieldHandler.handleThrowable(th, this);
            }
        }
    }

    /* access modifiers changed from: private */
    public static final void process$lambda$0(View view, MetadataViewObserver metadataViewObserver) {
        Class<MetadataViewObserver> cls = MetadataViewObserver.class;
        if (!CrashShieldHandler.isObjectCrashing(cls)) {
            try {
                Intrinsics.checkNotNullParameter(view, "$view");
                Intrinsics.checkNotNullParameter(metadataViewObserver, "this$0");
                if (view instanceof EditText) {
                    metadataViewObserver.processEditText(view);
                }
            } catch (Throwable th) {
                CrashShieldHandler.handleThrowable(th, cls);
            }
        }
    }

    private final void processEditText(View view) {
        if (!CrashShieldHandler.isObjectCrashing(this)) {
            try {
                Intrinsics.checkNotNull(view, "null cannot be cast to non-null type android.widget.EditText");
                String lowerCase = StringsKt.trim(((EditText) view).getText().toString()).toString().toLowerCase();
                Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase()");
                if (lowerCase.length() != 0) {
                    if (this.processedText.contains(lowerCase)) {
                        return;
                    }
                    if (lowerCase.length() <= 100) {
                        this.processedText.add(lowerCase);
                        HashMap hashMap = new HashMap();
                        List currentViewIndicators = MetadataMatcher.getCurrentViewIndicators(view);
                        List list = null;
                        for (MetadataRule metadataRule : MetadataRule.Companion.getRules()) {
                            Companion companion = Companion;
                            String access$preNormalize = companion.preNormalize(metadataRule.getName(), lowerCase);
                            if (metadataRule.getValRule().length() <= 0 || MetadataMatcher.matchValue(access$preNormalize, metadataRule.getValRule())) {
                                if (MetadataMatcher.matchIndicator(currentViewIndicators, metadataRule.getKeyRules())) {
                                    companion.putUserData(hashMap, metadataRule.getName(), access$preNormalize);
                                } else {
                                    if (list == null) {
                                        list = MetadataMatcher.getAroundViewIndicators(view);
                                    }
                                    if (MetadataMatcher.matchIndicator(list, metadataRule.getKeyRules())) {
                                        companion.putUserData(hashMap, metadataRule.getName(), access$preNormalize);
                                    }
                                }
                            }
                        }
                        InternalAppEventsLogger.Companion.setInternalUserData(hashMap);
                    }
                }
            } catch (Throwable th) {
                CrashShieldHandler.handleThrowable(th, this);
            }
        }
    }

    private final void runOnUIThread(Runnable runnable) {
        if (!CrashShieldHandler.isObjectCrashing(this)) {
            try {
                if (Thread.currentThread() == Looper.getMainLooper().getThread()) {
                    runnable.run();
                } else {
                    this.uiThreadHandler.post(runnable);
                }
            } catch (Throwable th) {
                CrashShieldHandler.handleThrowable(th, this);
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
            Map access$getObservers$cp = MetadataViewObserver.access$getObservers$cp();
            Integer valueOf = Integer.valueOf(hashCode);
            Object obj = access$getObservers$cp.get(valueOf);
            if (obj == null) {
                obj = new MetadataViewObserver(activity, (DefaultConstructorMarker) null);
                access$getObservers$cp.put(valueOf, obj);
            }
            MetadataViewObserver.access$startTracking((MetadataViewObserver) obj);
        }

        /* access modifiers changed from: private */
        public final String preNormalize(String str, String str2) {
            return Intrinsics.areEqual((Object) "r2", (Object) str) ? new Regex("[^\\d.]").replace(str2, "") : str2;
        }

        /* access modifiers changed from: private */
        /* JADX WARNING: Can't fix incorrect switch cases order */
        /* JADX WARNING: Code restructure failed: missing block: B:10:0x0042, code lost:
            if (r7.equals("r4") == false) goto L_0x0079;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:11:0x0045, code lost:
            r8 = new kotlin.text.Regex("[^a-z]+").replace(r8, "");
         */
        /* JADX WARNING: Code restructure failed: missing block: B:8:0x0039, code lost:
            if (r7.equals("r5") == false) goto L_0x0079;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final void putUserData(java.util.Map r6, java.lang.String r7, java.lang.String r8) {
            /*
                r5 = this;
                int r0 = r7.hashCode()
                r1 = 0
                r2 = 2
                r3 = 0
                switch(r0) {
                    case 3585: goto L_0x0053;
                    case 3586: goto L_0x003c;
                    case 3587: goto L_0x0033;
                    case 3588: goto L_0x000c;
                    default: goto L_0x000a;
                }
            L_0x000a:
                goto L_0x0079
            L_0x000c:
                java.lang.String r0 = "r6"
                boolean r0 = r7.equals(r0)
                if (r0 != 0) goto L_0x0015
                goto L_0x0079
            L_0x0015:
                java.lang.String r0 = "-"
                boolean r1 = kotlin.text.StringsKt.contains$default((java.lang.CharSequence) r8, (java.lang.CharSequence) r0, (boolean) r3, (int) r2, (java.lang.Object) r1)
                if (r1 == 0) goto L_0x0079
                kotlin.text.Regex r1 = new kotlin.text.Regex
                r1.<init>((java.lang.String) r0)
                java.util.List r8 = r1.split(r8, r3)
                java.util.Collection r8 = (java.util.Collection) r8
                java.lang.String[] r0 = new java.lang.String[r3]
                java.lang.Object[] r8 = r8.toArray(r0)
                java.lang.String[] r8 = (java.lang.String[]) r8
                r8 = r8[r3]
                goto L_0x0079
            L_0x0033:
                java.lang.String r0 = "r5"
                boolean r0 = r7.equals(r0)
                if (r0 != 0) goto L_0x0045
                goto L_0x0079
            L_0x003c:
                java.lang.String r0 = "r4"
                boolean r0 = r7.equals(r0)
                if (r0 != 0) goto L_0x0045
                goto L_0x0079
            L_0x0045:
                kotlin.text.Regex r0 = new kotlin.text.Regex
                java.lang.String r1 = "[^a-z]+"
                r0.<init>((java.lang.String) r1)
                java.lang.String r1 = ""
                java.lang.String r8 = r0.replace(r8, r1)
                goto L_0x0079
            L_0x0053:
                java.lang.String r0 = "r3"
                boolean r0 = r7.equals(r0)
                if (r0 != 0) goto L_0x005c
                goto L_0x0079
            L_0x005c:
                java.lang.String r0 = "m"
                boolean r4 = kotlin.text.StringsKt.startsWith$default(r8, r0, r3, r2, r1)
                if (r4 != 0) goto L_0x0078
                java.lang.String r4 = "b"
                boolean r4 = kotlin.text.StringsKt.startsWith$default(r8, r4, r3, r2, r1)
                if (r4 != 0) goto L_0x0078
                java.lang.String r4 = "ge"
                boolean r8 = kotlin.text.StringsKt.startsWith$default(r8, r4, r3, r2, r1)
                if (r8 == 0) goto L_0x0075
                goto L_0x0078
            L_0x0075:
                java.lang.String r8 = "f"
                goto L_0x0079
            L_0x0078:
                r8 = r0
            L_0x0079:
                r6.put(r7, r8)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.facebook.appevents.aam.MetadataViewObserver.Companion.putUserData(java.util.Map, java.lang.String, java.lang.String):void");
        }
    }
}
