package com.swmansion.rnscreens.utils;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.facebook.jni.annotations.DoNotStrip;
import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.PixelUtil;
import com.google.android.material.appbar.AppBarLayout;
import com.swmansion.rnscreens.ScreenStackHeaderConfig;
import java.lang.ref.WeakReference;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@DoNotStrip
public final class ScreenDummyLayoutHelper implements LifecycleEventListener {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    public static WeakReference weakInstance = new WeakReference((Object) null);
    private AppBarLayout appBarLayout;
    private CacheEntry cache;
    private CoordinatorLayout coordinatorLayout;
    private int defaultContentInsetStartWithNavigation;
    private float defaultFontSize;
    private View dummyContentView;
    private volatile boolean isLayoutInitialized;
    private WeakReference reactContextRef;
    private Toolbar toolbar;

    @DoNotStrip
    public static final ScreenDummyLayoutHelper getInstance() {
        return Companion.getInstance();
    }

    public void onHostPause() {
    }

    private final boolean maybeInitDummyLayoutWithHeader(ReactApplicationContext reactApplicationContext) {
        if (this.isLayoutInitialized) {
            return true;
        }
        if (!reactApplicationContext.hasCurrentActivity()) {
            return false;
        }
        Activity currentActivity = reactApplicationContext.getCurrentActivity();
        if (currentActivity != null) {
            synchronized (this) {
                if (this.isLayoutInitialized) {
                    return true;
                }
                initDummyLayoutWithHeader(currentActivity);
                Unit unit = Unit.INSTANCE;
                return true;
            }
        }
        throw new IllegalArgumentException("[RNScreens] Attempt to use context detached from activity. This could happen only due to race-condition.");
    }

    private final void initDummyLayoutWithHeader(Context context) {
        this.coordinatorLayout = new CoordinatorLayout(context);
        AppBarLayout appBarLayout2 = new AppBarLayout(context);
        appBarLayout2.setLayoutParams(new CoordinatorLayout.LayoutParams(-1, -2));
        this.appBarLayout = appBarLayout2;
        Toolbar toolbar2 = new Toolbar(context);
        toolbar2.setTitle((CharSequence) "FontSize123!#$");
        AppBarLayout.LayoutParams layoutParams = new AppBarLayout.LayoutParams(-1, -2);
        layoutParams.setScrollFlags(0);
        toolbar2.setLayoutParams(layoutParams);
        this.toolbar = toolbar2;
        TextView findTitleTextViewInToolbar = ScreenStackHeaderConfig.Companion.findTitleTextViewInToolbar(toolbar2);
        Intrinsics.checkNotNull(findTitleTextViewInToolbar);
        this.defaultFontSize = findTitleTextViewInToolbar.getTextSize();
        Toolbar toolbar3 = this.toolbar;
        View view = null;
        if (toolbar3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("toolbar");
            toolbar3 = null;
        }
        this.defaultContentInsetStartWithNavigation = toolbar3.getContentInsetStartWithNavigation();
        AppBarLayout appBarLayout3 = this.appBarLayout;
        if (appBarLayout3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("appBarLayout");
            appBarLayout3 = null;
        }
        Toolbar toolbar4 = this.toolbar;
        if (toolbar4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("toolbar");
            toolbar4 = null;
        }
        appBarLayout3.addView(toolbar4);
        View view2 = new View(context);
        view2.setLayoutParams(new CoordinatorLayout.LayoutParams(-1, -1));
        this.dummyContentView = view2;
        CoordinatorLayout coordinatorLayout2 = this.coordinatorLayout;
        if (coordinatorLayout2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("coordinatorLayout");
            coordinatorLayout2 = null;
        }
        AppBarLayout appBarLayout4 = this.appBarLayout;
        if (appBarLayout4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("appBarLayout");
            appBarLayout4 = null;
        }
        coordinatorLayout2.addView(appBarLayout4);
        View view3 = this.dummyContentView;
        if (view3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("dummyContentView");
        } else {
            view = view3;
        }
        coordinatorLayout2.addView(view);
        this.isLayoutInitialized = true;
    }

    @DoNotStrip
    private final float computeDummyLayout(int i, boolean z) {
        float f;
        if (!this.isLayoutInitialized && !maybeInitDummyLayoutWithHeader(requireReactContext(new ScreenDummyLayoutHelper$$ExternalSyntheticLambda0()))) {
            Log.e("ScreenDummyLayoutHelper", "[RNScreens] Failed to late-init layout while computing header height. This is most likely a race-condition-bug in react-native-screens, please file an issue at https://github.com/software-mansion/react-native-screens/issues");
            return 0.0f;
        } else if (this.cache.hasKey(new CacheKey(i, z))) {
            return this.cache.getHeaderHeight();
        } else {
            View decorView = requireActivity().getWindow().getDecorView();
            Intrinsics.checkNotNullExpressionValue(decorView, "getDecorView(...)");
            int width = decorView.getWidth();
            int height = decorView.getHeight();
            int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(width, 1073741824);
            int makeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(height, 1073741824);
            AppBarLayout appBarLayout2 = null;
            if (z) {
                Toolbar toolbar2 = this.toolbar;
                if (toolbar2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("toolbar");
                    toolbar2 = null;
                }
                toolbar2.setTitle((CharSequence) "");
                Toolbar toolbar3 = this.toolbar;
                if (toolbar3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("toolbar");
                    toolbar3 = null;
                }
                toolbar3.setContentInsetStartWithNavigation(0);
            } else {
                Toolbar toolbar4 = this.toolbar;
                if (toolbar4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("toolbar");
                    toolbar4 = null;
                }
                toolbar4.setTitle((CharSequence) "FontSize123!#$");
                Toolbar toolbar5 = this.toolbar;
                if (toolbar5 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("toolbar");
                    toolbar5 = null;
                }
                toolbar5.setContentInsetStartWithNavigation(this.defaultContentInsetStartWithNavigation);
            }
            ScreenStackHeaderConfig.Companion companion = ScreenStackHeaderConfig.Companion;
            Toolbar toolbar6 = this.toolbar;
            if (toolbar6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("toolbar");
                toolbar6 = null;
            }
            TextView findTitleTextViewInToolbar = companion.findTitleTextViewInToolbar(toolbar6);
            if (findTitleTextViewInToolbar != null) {
                if (i != -1) {
                    f = (float) i;
                } else {
                    f = this.defaultFontSize;
                }
                findTitleTextViewInToolbar.setTextSize(f);
            }
            CoordinatorLayout coordinatorLayout2 = this.coordinatorLayout;
            if (coordinatorLayout2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("coordinatorLayout");
                coordinatorLayout2 = null;
            }
            coordinatorLayout2.measure(makeMeasureSpec, makeMeasureSpec2);
            CoordinatorLayout coordinatorLayout3 = this.coordinatorLayout;
            if (coordinatorLayout3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("coordinatorLayout");
                coordinatorLayout3 = null;
            }
            coordinatorLayout3.layout(0, 0, width, height);
            AppBarLayout appBarLayout3 = this.appBarLayout;
            if (appBarLayout3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("appBarLayout");
            } else {
                appBarLayout2 = appBarLayout3;
            }
            float dIPFromPixel = PixelUtil.toDIPFromPixel((float) appBarLayout2.getHeight());
            this.cache = new CacheEntry(new CacheKey(i, z), dIPFromPixel);
            return dIPFromPixel;
        }
    }

    /* access modifiers changed from: private */
    public static final Object computeDummyLayout$lambda$7() {
        return "[RNScreens] Context was null-ed before dummy layout was initialized";
    }

    static /* synthetic */ ReactApplicationContext requireReactContext$default(ScreenDummyLayoutHelper screenDummyLayoutHelper, Function0 function0, int i, Object obj) {
        if ((i & 1) != 0) {
            function0 = null;
        }
        return screenDummyLayoutHelper.requireReactContext(function0);
    }

    private final ReactApplicationContext requireReactContext(Function0 function0) {
        Object obj = this.reactContextRef.get();
        if (function0 == null) {
            new ScreenDummyLayoutHelper$$ExternalSyntheticLambda2
            /*  JADX ERROR: Method code generation error
                jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x000a: CONSTRUCTOR  (r2v4 ? I:com.swmansion.rnscreens.utils.ScreenDummyLayoutHelper$$ExternalSyntheticLambda2) =  call: com.swmansion.rnscreens.utils.ScreenDummyLayoutHelper$$ExternalSyntheticLambda2.<init>():void type: CONSTRUCTOR in method: com.swmansion.rnscreens.utils.ScreenDummyLayoutHelper.requireReactContext(kotlin.jvm.functions.Function0):com.facebook.react.bridge.ReactApplicationContext, dex: classes2.dex
                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:256)
                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:221)
                	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:109)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:55)
                	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:98)
                	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:142)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:62)
                	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:211)
                	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:204)
                	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:318)
                	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:271)
                	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$2(ClassGen.java:240)
                	at java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183)
                	at java.util.ArrayList.forEach(ArrayList.java:1259)
                	at java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
                	at java.util.stream.Sink$ChainedReference.end(Sink.java:258)
                	at java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:483)
                	at java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:472)
                	at java.util.stream.ForEachOps$ForEachOp.evaluateSequential(ForEachOps.java:150)
                	at java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(ForEachOps.java:173)
                	at java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
                	at java.util.stream.ReferencePipeline.forEach(ReferencePipeline.java:485)
                	at jadx.core.codegen.ClassGen.addInnerClsAndMethods(ClassGen.java:236)
                	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:227)
                	at jadx.core.codegen.ClassGen.addClassCode(ClassGen.java:112)
                	at jadx.core.codegen.ClassGen.makeClass(ClassGen.java:78)
                	at jadx.core.codegen.CodeGen.wrapCodeGen(CodeGen.java:44)
                	at jadx.core.codegen.CodeGen.generateJavaCode(CodeGen.java:33)
                	at jadx.core.codegen.CodeGen.generate(CodeGen.java:21)
                	at jadx.core.ProcessClass.generateCode(ProcessClass.java:61)
                	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:273)
                Caused by: jadx.core.utils.exceptions.JadxRuntimeException: Code variable not set in r2v4 ?
                	at jadx.core.dex.instructions.args.SSAVar.getCodeVar(SSAVar.java:189)
                	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:620)
                	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:364)
                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:250)
                	... 34 more
                */
            /*
                this = this;
                java.lang.ref.WeakReference r0 = r1.reactContextRef
                java.lang.Object r0 = r0.get()
                if (r2 != 0) goto L_0x000d
                com.swmansion.rnscreens.utils.ScreenDummyLayoutHelper$$ExternalSyntheticLambda2 r2 = new com.swmansion.rnscreens.utils.ScreenDummyLayoutHelper$$ExternalSyntheticLambda2
                r2.<init>()
            L_0x000d:
                if (r0 == 0) goto L_0x0012
                com.facebook.react.bridge.ReactApplicationContext r0 = (com.facebook.react.bridge.ReactApplicationContext) r0
                return r0
            L_0x0012:
                java.lang.Object r2 = r2.invoke()
                java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
                java.lang.String r2 = r2.toString()
                r0.<init>(r2)
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.swmansion.rnscreens.utils.ScreenDummyLayoutHelper.requireReactContext(kotlin.jvm.functions.Function0):com.facebook.react.bridge.ReactApplicationContext");
        }

        /* access modifiers changed from: private */
        public static final Object requireReactContext$lambda$8() {
            return "[RNScreens] Attempt to require missing react context";
        }

        private final Activity requireActivity() {
            Activity currentActivity = requireReactContext$default(this, (Function0) null, 1, (Object) null).getCurrentActivity();
            if (currentActivity != null) {
                return currentActivity;
            }
            throw new IllegalArgumentException("[RNScreens] Attempt to use context detached from activity");
        }

        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            @DoNotStrip
            public final ScreenDummyLayoutHelper getInstance() {
                return (ScreenDummyLayoutHelper) ScreenDummyLayoutHelper.weakInstance.get();
            }
        }

        /* access modifiers changed from: private */
        public static final Object onHostResume$lambda$10() {
            return "[RNScreens] ReactContext missing in onHostResume! This should not happen.";
        }

        public void onHostResume() {
            ReactApplicationContext requireReactContext = requireReactContext(new ScreenDummyLayoutHelper$$ExternalSyntheticLambda1());
            if (maybeInitDummyLayoutWithHeader(requireReactContext)) {
                requireReactContext.removeLifecycleEventListener(this);
            } else {
                Log.w("ScreenDummyLayoutHelper", "[RNScreens] Failed to initialise dummy layout in onHostResume.");
            }
        }

        public void onHostDestroy() {
            ReactApplicationContext reactApplicationContext = (ReactApplicationContext) this.reactContextRef.get();
            if (reactApplicationContext != null) {
                reactApplicationContext.removeLifecycleEventListener(this);
            }
        }
    }
