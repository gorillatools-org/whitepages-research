package com.facebook.appevents.codeless;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewTreeObserver;
import android.widget.AdapterView;
import android.widget.ListView;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.codeless.CodelessLoggingEventListener;
import com.facebook.appevents.codeless.RCTCodelessLoggingEventListener;
import com.facebook.appevents.codeless.internal.EventBinding;
import com.facebook.appevents.codeless.internal.ParameterComponent;
import com.facebook.appevents.codeless.internal.PathComponent;
import com.facebook.appevents.codeless.internal.ViewHierarchy;
import com.facebook.appevents.internal.AppEventUtility;
import com.facebook.internal.FetchedAppSettings;
import com.facebook.internal.FetchedAppSettingsManager;
import com.facebook.internal.InternalSettings;
import com.facebook.internal.Utility;
import com.facebook.internal.instrument.crashshield.CrashShieldHandler;
import com.facebook.react.BuildConfig;
import com.salesforce.marketingcloud.config.a;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.WeakHashMap;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

public final class CodelessMatcher {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final String TAG = CodelessMatcher.class.getCanonicalName();
    private static CodelessMatcher codelessMatcher;
    private final Set activitiesSet;
    private final HashMap activityToListenerMap;
    private HashSet listenerSet;
    private final Handler uiThreadHandler;
    private final Set viewMatchers;

    public /* synthetic */ CodelessMatcher(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    private CodelessMatcher() {
        this.uiThreadHandler = new Handler(Looper.getMainLooper());
        Set newSetFromMap = Collections.newSetFromMap(new WeakHashMap());
        Intrinsics.checkNotNullExpressionValue(newSetFromMap, "newSetFromMap(WeakHashMap())");
        this.activitiesSet = newSetFromMap;
        this.viewMatchers = new LinkedHashSet();
        this.listenerSet = new HashSet();
        this.activityToListenerMap = new HashMap();
    }

    public static final /* synthetic */ CodelessMatcher access$getCodelessMatcher$cp() {
        Class<CodelessMatcher> cls = CodelessMatcher.class;
        if (CrashShieldHandler.isObjectCrashing(cls)) {
            return null;
        }
        try {
            return codelessMatcher;
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, cls);
            return null;
        }
    }

    public static final /* synthetic */ String access$getTAG$cp() {
        Class<CodelessMatcher> cls = CodelessMatcher.class;
        if (CrashShieldHandler.isObjectCrashing(cls)) {
            return null;
        }
        try {
            return TAG;
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, cls);
            return null;
        }
    }

    public static final /* synthetic */ void access$setCodelessMatcher$cp(CodelessMatcher codelessMatcher2) {
        Class<CodelessMatcher> cls = CodelessMatcher.class;
        if (!CrashShieldHandler.isObjectCrashing(cls)) {
            try {
                codelessMatcher = codelessMatcher2;
            } catch (Throwable th) {
                CrashShieldHandler.handleThrowable(th, cls);
            }
        }
    }

    public final void add(Activity activity) {
        if (!CrashShieldHandler.isObjectCrashing(this)) {
            try {
                Intrinsics.checkNotNullParameter(activity, "activity");
                if (!InternalSettings.isUnityApp()) {
                    if (Thread.currentThread() == Looper.getMainLooper().getThread()) {
                        this.activitiesSet.add(activity);
                        this.listenerSet.clear();
                        HashSet hashSet = (HashSet) this.activityToListenerMap.get(Integer.valueOf(activity.hashCode()));
                        if (hashSet != null) {
                            this.listenerSet = hashSet;
                        }
                        startTracking();
                        return;
                    }
                    throw new FacebookException("Can't add activity to CodelessMatcher on non-UI thread");
                }
            } catch (Throwable th) {
                CrashShieldHandler.handleThrowable(th, this);
            }
        }
    }

    public final void remove(Activity activity) {
        if (!CrashShieldHandler.isObjectCrashing(this)) {
            try {
                Intrinsics.checkNotNullParameter(activity, "activity");
                if (!InternalSettings.isUnityApp()) {
                    if (Thread.currentThread() == Looper.getMainLooper().getThread()) {
                        this.activitiesSet.remove(activity);
                        this.viewMatchers.clear();
                        HashMap hashMap = this.activityToListenerMap;
                        Integer valueOf = Integer.valueOf(activity.hashCode());
                        Object clone = this.listenerSet.clone();
                        Intrinsics.checkNotNull(clone, "null cannot be cast to non-null type java.util.HashSet<kotlin.String>{ kotlin.collections.TypeAliasesKt.HashSet<kotlin.String> }");
                        hashMap.put(valueOf, (HashSet) clone);
                        this.listenerSet.clear();
                        return;
                    }
                    throw new FacebookException("Can't remove activity from CodelessMatcher on non-UI thread");
                }
            } catch (Throwable th) {
                CrashShieldHandler.handleThrowable(th, this);
            }
        }
    }

    public final void destroy(Activity activity) {
        if (!CrashShieldHandler.isObjectCrashing(this)) {
            try {
                Intrinsics.checkNotNullParameter(activity, "activity");
                this.activityToListenerMap.remove(Integer.valueOf(activity.hashCode()));
            } catch (Throwable th) {
                CrashShieldHandler.handleThrowable(th, this);
            }
        }
    }

    private final void startTracking() {
        if (!CrashShieldHandler.isObjectCrashing(this)) {
            try {
                if (Thread.currentThread() == Looper.getMainLooper().getThread()) {
                    matchViews();
                } else {
                    this.uiThreadHandler.post(new CodelessMatcher$$ExternalSyntheticLambda0(this));
                }
            } catch (Throwable th) {
                CrashShieldHandler.handleThrowable(th, this);
            }
        }
    }

    /* access modifiers changed from: private */
    public static final void startTracking$lambda$1(CodelessMatcher codelessMatcher2) {
        Class<CodelessMatcher> cls = CodelessMatcher.class;
        if (!CrashShieldHandler.isObjectCrashing(cls)) {
            try {
                Intrinsics.checkNotNullParameter(codelessMatcher2, "this$0");
                codelessMatcher2.matchViews();
            } catch (Throwable th) {
                CrashShieldHandler.handleThrowable(th, cls);
            }
        }
    }

    private final void matchViews() {
        if (!CrashShieldHandler.isObjectCrashing(this)) {
            try {
                for (Activity activity : this.activitiesSet) {
                    if (activity != null) {
                        View rootView = AppEventUtility.getRootView(activity);
                        String simpleName = activity.getClass().getSimpleName();
                        Handler handler = this.uiThreadHandler;
                        HashSet hashSet = this.listenerSet;
                        Intrinsics.checkNotNullExpressionValue(simpleName, "activityName");
                        this.viewMatchers.add(new ViewMatcher(rootView, handler, hashSet, simpleName));
                    }
                }
            } catch (Throwable th) {
                CrashShieldHandler.handleThrowable(th, this);
            }
        }
    }

    public static final class MatchedView {
        private final WeakReference view;
        private final String viewMapKey;

        public MatchedView(View view2, String str) {
            Intrinsics.checkNotNullParameter(view2, "view");
            Intrinsics.checkNotNullParameter(str, "viewMapKey");
            this.view = new WeakReference(view2);
            this.viewMapKey = str;
        }

        public final String getViewMapKey() {
            return this.viewMapKey;
        }

        public final View getView() {
            WeakReference weakReference = this.view;
            if (weakReference != null) {
                return (View) weakReference.get();
            }
            return null;
        }
    }

    public static final class ViewMatcher implements ViewTreeObserver.OnGlobalLayoutListener, ViewTreeObserver.OnScrollChangedListener, Runnable {
        public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
        private final String activityName;
        private List eventBindings;
        private final Handler handler;
        private final HashSet listenerSet;
        private final WeakReference rootView;

        public ViewMatcher(View view, Handler handler2, HashSet hashSet, String str) {
            Intrinsics.checkNotNullParameter(handler2, "handler");
            Intrinsics.checkNotNullParameter(hashSet, "listenerSet");
            Intrinsics.checkNotNullParameter(str, "activityName");
            this.rootView = new WeakReference(view);
            this.handler = handler2;
            this.listenerSet = hashSet;
            this.activityName = str;
            handler2.postDelayed(this, 200);
        }

        public void run() {
            View view;
            if (!CrashShieldHandler.isObjectCrashing(this)) {
                try {
                    FetchedAppSettings appSettingsWithoutQuery = FetchedAppSettingsManager.getAppSettingsWithoutQuery(FacebookSdk.getApplicationId());
                    if (appSettingsWithoutQuery == null) {
                        return;
                    }
                    if (appSettingsWithoutQuery.getCodelessEventsEnabled()) {
                        List parseArray = EventBinding.Companion.parseArray(appSettingsWithoutQuery.getEventBindings());
                        this.eventBindings = parseArray;
                        if (parseArray != null && (view = (View) this.rootView.get()) != null) {
                            ViewTreeObserver viewTreeObserver = view.getViewTreeObserver();
                            if (viewTreeObserver.isAlive()) {
                                viewTreeObserver.addOnGlobalLayoutListener(this);
                                viewTreeObserver.addOnScrollChangedListener(this);
                            }
                            startMatch();
                        }
                    }
                } catch (Throwable th) {
                    CrashShieldHandler.handleThrowable(th, this);
                }
            }
        }

        public void onGlobalLayout() {
            startMatch();
        }

        public void onScrollChanged() {
            startMatch();
        }

        private final void startMatch() {
            List list = this.eventBindings;
            if (list != null && this.rootView.get() != null) {
                int size = list.size();
                for (int i = 0; i < size; i++) {
                    findView((EventBinding) list.get(i), (View) this.rootView.get());
                }
            }
        }

        private final void findView(EventBinding eventBinding, View view) {
            if (eventBinding != null && view != null) {
                String activityName2 = eventBinding.getActivityName();
                if (activityName2 == null || activityName2.length() == 0 || Intrinsics.areEqual((Object) eventBinding.getActivityName(), (Object) this.activityName)) {
                    List viewPath = eventBinding.getViewPath();
                    if (viewPath.size() <= 25) {
                        for (MatchedView attachListener : Companion.findViewByPath(eventBinding, view, viewPath, 0, -1, this.activityName)) {
                            attachListener(attachListener, view, eventBinding);
                        }
                    }
                }
            }
        }

        private final void attachListener(MatchedView matchedView, View view, EventBinding eventBinding) {
            if (eventBinding != null) {
                try {
                    View view2 = matchedView.getView();
                    if (view2 != null) {
                        View findRCTRootView = ViewHierarchy.findRCTRootView(view2);
                        if (findRCTRootView == null || !ViewHierarchy.INSTANCE.isRCTButton(view2, findRCTRootView)) {
                            String name = view2.getClass().getName();
                            Intrinsics.checkNotNullExpressionValue(name, "view.javaClass.name");
                            if (!StringsKt.startsWith$default(name, BuildConfig.LIBRARY_PACKAGE_NAME, false, 2, (Object) null)) {
                                if (!(view2 instanceof AdapterView)) {
                                    attachOnClickListener(matchedView, view, eventBinding);
                                } else if (view2 instanceof ListView) {
                                    attachOnItemClickListener(matchedView, view, eventBinding);
                                }
                            }
                        } else {
                            attachRCTListener(matchedView, view, eventBinding);
                        }
                    }
                } catch (Exception e) {
                    Utility.logd(CodelessMatcher.access$getTAG$cp(), e);
                }
            }
        }

        private final void attachOnClickListener(MatchedView matchedView, View view, EventBinding eventBinding) {
            boolean z;
            View view2 = matchedView.getView();
            if (view2 != null) {
                String viewMapKey = matchedView.getViewMapKey();
                View.OnClickListener existingOnClickListener = ViewHierarchy.getExistingOnClickListener(view2);
                if (existingOnClickListener instanceof CodelessLoggingEventListener.AutoLoggingOnClickListener) {
                    Intrinsics.checkNotNull(existingOnClickListener, "null cannot be cast to non-null type com.facebook.appevents.codeless.CodelessLoggingEventListener.AutoLoggingOnClickListener");
                    if (((CodelessLoggingEventListener.AutoLoggingOnClickListener) existingOnClickListener).getSupportCodelessLogging()) {
                        z = true;
                        if (!this.listenerSet.contains(viewMapKey) && !z) {
                            view2.setOnClickListener(CodelessLoggingEventListener.getOnClickListener(eventBinding, view, view2));
                            this.listenerSet.add(viewMapKey);
                            return;
                        }
                    }
                }
                z = false;
                if (!this.listenerSet.contains(viewMapKey)) {
                }
            }
        }

        private final void attachOnItemClickListener(MatchedView matchedView, View view, EventBinding eventBinding) {
            boolean z;
            AdapterView adapterView = (AdapterView) matchedView.getView();
            if (adapterView != null) {
                String viewMapKey = matchedView.getViewMapKey();
                AdapterView.OnItemClickListener onItemClickListener = adapterView.getOnItemClickListener();
                if (onItemClickListener instanceof CodelessLoggingEventListener.AutoLoggingOnItemClickListener) {
                    Intrinsics.checkNotNull(onItemClickListener, "null cannot be cast to non-null type com.facebook.appevents.codeless.CodelessLoggingEventListener.AutoLoggingOnItemClickListener");
                    if (((CodelessLoggingEventListener.AutoLoggingOnItemClickListener) onItemClickListener).getSupportCodelessLogging()) {
                        z = true;
                        if (!this.listenerSet.contains(viewMapKey) && !z) {
                            adapterView.setOnItemClickListener(CodelessLoggingEventListener.getOnItemClickListener(eventBinding, view, adapterView));
                            this.listenerSet.add(viewMapKey);
                            return;
                        }
                    }
                }
                z = false;
                if (!this.listenerSet.contains(viewMapKey)) {
                }
            }
        }

        private final void attachRCTListener(MatchedView matchedView, View view, EventBinding eventBinding) {
            boolean z;
            View view2 = matchedView.getView();
            if (view2 != null) {
                String viewMapKey = matchedView.getViewMapKey();
                View.OnTouchListener existingOnTouchListener = ViewHierarchy.getExistingOnTouchListener(view2);
                if (existingOnTouchListener instanceof RCTCodelessLoggingEventListener.AutoLoggingOnTouchListener) {
                    Intrinsics.checkNotNull(existingOnTouchListener, "null cannot be cast to non-null type com.facebook.appevents.codeless.RCTCodelessLoggingEventListener.AutoLoggingOnTouchListener");
                    if (((RCTCodelessLoggingEventListener.AutoLoggingOnTouchListener) existingOnTouchListener).getSupportCodelessLogging()) {
                        z = true;
                        if (!this.listenerSet.contains(viewMapKey) && !z) {
                            view2.setOnTouchListener(RCTCodelessLoggingEventListener.getOnTouchListener(eventBinding, view, view2));
                            this.listenerSet.add(viewMapKey);
                            return;
                        }
                    }
                }
                z = false;
                if (!this.listenerSet.contains(viewMapKey)) {
                }
            }
        }

        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final List findViewByPath(EventBinding eventBinding, View view, List list, int i, int i2, String str) {
                Intrinsics.checkNotNullParameter(list, a.j);
                Intrinsics.checkNotNullParameter(str, "mapKey");
                String str2 = str + '.' + i2;
                ArrayList arrayList = new ArrayList();
                if (view == null) {
                    return arrayList;
                }
                if (i >= list.size()) {
                    arrayList.add(new MatchedView(view, str2));
                } else {
                    PathComponent pathComponent = (PathComponent) list.get(i);
                    if (Intrinsics.areEqual((Object) pathComponent.getClassName(), (Object) "..")) {
                        ViewParent parent = view.getParent();
                        if (parent instanceof ViewGroup) {
                            List findVisibleChildren = findVisibleChildren((ViewGroup) parent);
                            int size = findVisibleChildren.size();
                            for (int i3 = 0; i3 < size; i3++) {
                                arrayList.addAll(findViewByPath(eventBinding, (View) findVisibleChildren.get(i3), list, i + 1, i3, str2));
                            }
                        }
                        return arrayList;
                    } else if (Intrinsics.areEqual((Object) pathComponent.getClassName(), (Object) ".")) {
                        arrayList.add(new MatchedView(view, str2));
                        return arrayList;
                    } else if (!isTheSameView(view, pathComponent, i2)) {
                        return arrayList;
                    } else {
                        if (i == list.size() - 1) {
                            arrayList.add(new MatchedView(view, str2));
                        }
                    }
                }
                if (view instanceof ViewGroup) {
                    List findVisibleChildren2 = findVisibleChildren((ViewGroup) view);
                    int size2 = findVisibleChildren2.size();
                    for (int i4 = 0; i4 < size2; i4++) {
                        arrayList.addAll(findViewByPath(eventBinding, (View) findVisibleChildren2.get(i4), list, i + 1, i4, str2));
                    }
                }
                return arrayList;
            }

            /* JADX WARNING: Code restructure failed: missing block: B:12:0x0065, code lost:
                if (kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r10.getClass().getSimpleName(), (java.lang.Object) (java.lang.String) r12.get(r12.size() - 1)) == false) goto L_0x0067;
             */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            private final boolean isTheSameView(android.view.View r10, com.facebook.appevents.codeless.internal.PathComponent r11, int r12) {
                /*
                    r9 = this;
                    int r0 = r11.getIndex()
                    r1 = -1
                    r2 = 0
                    if (r0 == r1) goto L_0x000f
                    int r0 = r11.getIndex()
                    if (r12 == r0) goto L_0x000f
                    return r2
                L_0x000f:
                    java.lang.Class r12 = r10.getClass()
                    java.lang.String r12 = r12.getCanonicalName()
                    java.lang.String r0 = r11.getClassName()
                    boolean r12 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r12, (java.lang.Object) r0)
                    r0 = 1
                    if (r12 != 0) goto L_0x0068
                    java.lang.String r12 = r11.getClassName()
                    kotlin.text.Regex r1 = new kotlin.text.Regex
                    java.lang.String r3 = ".*android\\..*"
                    r1.<init>((java.lang.String) r3)
                    boolean r12 = r1.matches(r12)
                    if (r12 == 0) goto L_0x0067
                    java.lang.String r3 = r11.getClassName()
                    java.lang.String r12 = "."
                    java.lang.String[] r4 = new java.lang.String[]{r12}
                    r7 = 6
                    r8 = 0
                    r5 = 0
                    r6 = 0
                    java.util.List r12 = kotlin.text.StringsKt.split$default((java.lang.CharSequence) r3, (java.lang.String[]) r4, (boolean) r5, (int) r6, (int) r7, (java.lang.Object) r8)
                    r1 = r12
                    java.util.Collection r1 = (java.util.Collection) r1
                    boolean r1 = r1.isEmpty()
                    if (r1 != 0) goto L_0x0067
                    int r1 = r12.size()
                    int r1 = r1 - r0
                    java.lang.Object r12 = r12.get(r1)
                    java.lang.String r12 = (java.lang.String) r12
                    java.lang.Class r1 = r10.getClass()
                    java.lang.String r1 = r1.getSimpleName()
                    boolean r12 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r1, (java.lang.Object) r12)
                    if (r12 != 0) goto L_0x0068
                L_0x0067:
                    return r2
                L_0x0068:
                    int r12 = r11.getMatchBitmask()
                    com.facebook.appevents.codeless.internal.PathComponent$MatchBitmaskType r1 = com.facebook.appevents.codeless.internal.PathComponent.MatchBitmaskType.ID
                    int r1 = r1.getValue()
                    r12 = r12 & r1
                    if (r12 <= 0) goto L_0x0080
                    int r12 = r11.getId()
                    int r1 = r10.getId()
                    if (r12 == r1) goto L_0x0080
                    return r2
                L_0x0080:
                    int r12 = r11.getMatchBitmask()
                    com.facebook.appevents.codeless.internal.PathComponent$MatchBitmaskType r1 = com.facebook.appevents.codeless.internal.PathComponent.MatchBitmaskType.TEXT
                    int r1 = r1.getValue()
                    r12 = r12 & r1
                    java.lang.String r1 = ""
                    if (r12 <= 0) goto L_0x00ac
                    java.lang.String r12 = r11.getText()
                    java.lang.String r3 = com.facebook.appevents.codeless.internal.ViewHierarchy.getTextOfView(r10)
                    java.lang.String r4 = com.facebook.internal.Utility.sha256hash(r3)
                    java.lang.String r4 = com.facebook.internal.Utility.coerceValueIfNullOrEmpty(r4, r1)
                    boolean r3 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r12, (java.lang.Object) r3)
                    if (r3 != 0) goto L_0x00ac
                    boolean r12 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r12, (java.lang.Object) r4)
                    if (r12 != 0) goto L_0x00ac
                    return r2
                L_0x00ac:
                    int r12 = r11.getMatchBitmask()
                    com.facebook.appevents.codeless.internal.PathComponent$MatchBitmaskType r3 = com.facebook.appevents.codeless.internal.PathComponent.MatchBitmaskType.DESCRIPTION
                    int r3 = r3.getValue()
                    r12 = r12 & r3
                    if (r12 <= 0) goto L_0x00e2
                    java.lang.String r12 = r11.getDescription()
                    java.lang.CharSequence r3 = r10.getContentDescription()
                    if (r3 != 0) goto L_0x00c5
                    r3 = r1
                    goto L_0x00cd
                L_0x00c5:
                    java.lang.CharSequence r3 = r10.getContentDescription()
                    java.lang.String r3 = r3.toString()
                L_0x00cd:
                    java.lang.String r4 = com.facebook.internal.Utility.sha256hash(r3)
                    java.lang.String r4 = com.facebook.internal.Utility.coerceValueIfNullOrEmpty(r4, r1)
                    boolean r3 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r12, (java.lang.Object) r3)
                    if (r3 != 0) goto L_0x00e2
                    boolean r12 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r12, (java.lang.Object) r4)
                    if (r12 != 0) goto L_0x00e2
                    return r2
                L_0x00e2:
                    int r12 = r11.getMatchBitmask()
                    com.facebook.appevents.codeless.internal.PathComponent$MatchBitmaskType r3 = com.facebook.appevents.codeless.internal.PathComponent.MatchBitmaskType.HINT
                    int r3 = r3.getValue()
                    r12 = r12 & r3
                    if (r12 <= 0) goto L_0x010c
                    java.lang.String r12 = r11.getHint()
                    java.lang.String r3 = com.facebook.appevents.codeless.internal.ViewHierarchy.getHintOfView(r10)
                    java.lang.String r4 = com.facebook.internal.Utility.sha256hash(r3)
                    java.lang.String r4 = com.facebook.internal.Utility.coerceValueIfNullOrEmpty(r4, r1)
                    boolean r3 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r12, (java.lang.Object) r3)
                    if (r3 != 0) goto L_0x010c
                    boolean r12 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r12, (java.lang.Object) r4)
                    if (r12 != 0) goto L_0x010c
                    return r2
                L_0x010c:
                    int r12 = r11.getMatchBitmask()
                    com.facebook.appevents.codeless.internal.PathComponent$MatchBitmaskType r3 = com.facebook.appevents.codeless.internal.PathComponent.MatchBitmaskType.TAG
                    int r3 = r3.getValue()
                    r12 = r12 & r3
                    if (r12 <= 0) goto L_0x0142
                    java.lang.String r11 = r11.getTag()
                    java.lang.Object r12 = r10.getTag()
                    if (r12 != 0) goto L_0x0125
                    r10 = r1
                    goto L_0x012d
                L_0x0125:
                    java.lang.Object r10 = r10.getTag()
                    java.lang.String r10 = r10.toString()
                L_0x012d:
                    java.lang.String r12 = com.facebook.internal.Utility.sha256hash(r10)
                    java.lang.String r12 = com.facebook.internal.Utility.coerceValueIfNullOrEmpty(r12, r1)
                    boolean r10 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r11, (java.lang.Object) r10)
                    if (r10 != 0) goto L_0x0142
                    boolean r10 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r11, (java.lang.Object) r12)
                    if (r10 != 0) goto L_0x0142
                    return r2
                L_0x0142:
                    return r0
                */
                throw new UnsupportedOperationException("Method not decompiled: com.facebook.appevents.codeless.CodelessMatcher.ViewMatcher.Companion.isTheSameView(android.view.View, com.facebook.appevents.codeless.internal.PathComponent, int):boolean");
            }

            private final List findVisibleChildren(ViewGroup viewGroup) {
                ArrayList arrayList = new ArrayList();
                int childCount = viewGroup.getChildCount();
                for (int i = 0; i < childCount; i++) {
                    View childAt = viewGroup.getChildAt(i);
                    if (childAt.getVisibility() == 0) {
                        Intrinsics.checkNotNullExpressionValue(childAt, "child");
                        arrayList.add(childAt);
                    }
                }
                return arrayList;
            }
        }
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final synchronized CodelessMatcher getInstance() {
            CodelessMatcher access$getCodelessMatcher$cp;
            try {
                if (CodelessMatcher.access$getCodelessMatcher$cp() == null) {
                    CodelessMatcher.access$setCodelessMatcher$cp(new CodelessMatcher((DefaultConstructorMarker) null));
                }
                access$getCodelessMatcher$cp = CodelessMatcher.access$getCodelessMatcher$cp();
                Intrinsics.checkNotNull(access$getCodelessMatcher$cp, "null cannot be cast to non-null type com.facebook.appevents.codeless.CodelessMatcher");
            } catch (Throwable th) {
                while (true) {
                    throw th;
                }
            }
            return access$getCodelessMatcher$cp;
        }

        public final Bundle getParameters(EventBinding eventBinding, View view, View view2) {
            List<ParameterComponent> viewParameters;
            List list;
            Intrinsics.checkNotNullParameter(view, "rootView");
            Intrinsics.checkNotNullParameter(view2, "hostView");
            Bundle bundle = new Bundle();
            if (!(eventBinding == null || (viewParameters = eventBinding.getViewParameters()) == null)) {
                for (ParameterComponent parameterComponent : viewParameters) {
                    if (parameterComponent.getValue() == null || parameterComponent.getValue().length() <= 0) {
                        if (parameterComponent.getPath().size() > 0) {
                            if (Intrinsics.areEqual((Object) parameterComponent.getPathType(), (Object) "relative")) {
                                ViewMatcher.Companion companion = ViewMatcher.Companion;
                                List path = parameterComponent.getPath();
                                String simpleName = view2.getClass().getSimpleName();
                                Intrinsics.checkNotNullExpressionValue(simpleName, "hostView.javaClass.simpleName");
                                list = companion.findViewByPath(eventBinding, view2, path, 0, -1, simpleName);
                            } else {
                                ViewMatcher.Companion companion2 = ViewMatcher.Companion;
                                List path2 = parameterComponent.getPath();
                                String simpleName2 = view.getClass().getSimpleName();
                                Intrinsics.checkNotNullExpressionValue(simpleName2, "rootView.javaClass.simpleName");
                                list = companion2.findViewByPath(eventBinding, view, path2, 0, -1, simpleName2);
                            }
                            Iterator it = list.iterator();
                            while (true) {
                                if (!it.hasNext()) {
                                    break;
                                }
                                MatchedView matchedView = (MatchedView) it.next();
                                if (matchedView.getView() != null) {
                                    String textOfView = ViewHierarchy.getTextOfView(matchedView.getView());
                                    if (textOfView.length() > 0) {
                                        bundle.putString(parameterComponent.getName(), textOfView);
                                        break;
                                    }
                                }
                            }
                        }
                    } else {
                        bundle.putString(parameterComponent.getName(), parameterComponent.getValue());
                    }
                }
            }
            return bundle;
        }
    }
}
