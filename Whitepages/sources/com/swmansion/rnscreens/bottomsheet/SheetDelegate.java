package com.swmansion.rnscreens.bottomsheet;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.view.WindowMetrics;
import android.view.inputmethod.InputMethodManager;
import androidx.core.graphics.Insets;
import androidx.core.view.OnApplyWindowInsetsListener;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;
import com.facebook.react.uimanager.ThemedReactContext;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.swmansion.rnscreens.InsetsObserverProxy;
import com.swmansion.rnscreens.KeyboardDidHide;
import com.swmansion.rnscreens.KeyboardNotVisible;
import com.swmansion.rnscreens.KeyboardState;
import com.swmansion.rnscreens.KeyboardVisible;
import com.swmansion.rnscreens.Screen;
import com.swmansion.rnscreens.ScreenContainer;
import com.swmansion.rnscreens.ScreenStackFragment;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class SheetDelegate implements LifecycleEventObserver, OnApplyWindowInsetsListener {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private boolean isKeyboardVisible;
    private final KeyboardHandler keyboardHandlerCallback;
    private KeyboardState keyboardState = KeyboardNotVisible.INSTANCE;
    private int lastStableDetentIndex;
    private int lastStableState;
    private final Screen screen;
    private final SheetStateObserver sheetStateObserver;

    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        /* JADX WARNING: Can't wrap try/catch for region: R(9:0|1|2|3|4|5|6|7|9) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0010 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x0019 */
        static {
            /*
                androidx.lifecycle.Lifecycle$Event[] r0 = androidx.lifecycle.Lifecycle.Event.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                androidx.lifecycle.Lifecycle$Event r1 = androidx.lifecycle.Lifecycle.Event.ON_START     // Catch:{ NoSuchFieldError -> 0x0010 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0010 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0010 }
            L_0x0010:
                androidx.lifecycle.Lifecycle$Event r1 = androidx.lifecycle.Lifecycle.Event.ON_RESUME     // Catch:{ NoSuchFieldError -> 0x0019 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0019 }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0019 }
            L_0x0019:
                androidx.lifecycle.Lifecycle$Event r1 = androidx.lifecycle.Lifecycle.Event.ON_PAUSE     // Catch:{ NoSuchFieldError -> 0x0022 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0022 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0022 }
            L_0x0022:
                $EnumSwitchMapping$0 = r0
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.swmansion.rnscreens.bottomsheet.SheetDelegate.WhenMappings.<clinit>():void");
        }
    }

    private final boolean shouldDismissSheetInState(int i) {
        return i == 5;
    }

    public SheetDelegate(Screen screen2) {
        Intrinsics.checkNotNullParameter(screen2, "screen");
        this.screen = screen2;
        this.lastStableDetentIndex = screen2.getSheetInitialDetentIndex();
        this.lastStableState = SheetUtils.INSTANCE.sheetStateFromDetentIndex(screen2.getSheetInitialDetentIndex(), screen2.getSheetDetents().size());
        SheetStateObserver sheetStateObserver2 = new SheetStateObserver();
        this.sheetStateObserver = sheetStateObserver2;
        this.keyboardHandlerCallback = new KeyboardHandler();
        boolean z = screen2.getFragment() instanceof ScreenStackFragment;
        Fragment fragment = screen2.getFragment();
        Intrinsics.checkNotNull(fragment);
        fragment.getLifecycle().addObserver(this);
        BottomSheetBehavior sheetBehavior = getSheetBehavior();
        if (sheetBehavior != null) {
            sheetBehavior.addBottomSheetCallback(sheetStateObserver2);
            return;
        }
        throw new IllegalStateException("[RNScreens] Sheet delegate accepts screen with initialized sheet behaviour only.");
    }

    public final Screen getScreen() {
        return this.screen;
    }

    private final BottomSheetBehavior getSheetBehavior() {
        return this.screen.getSheetBehavior();
    }

    private final ScreenStackFragment getStackFragment() {
        Fragment fragment = this.screen.getFragment();
        Intrinsics.checkNotNull(fragment, "null cannot be cast to non-null type com.swmansion.rnscreens.ScreenStackFragment");
        return (ScreenStackFragment) fragment;
    }

    private final View requireDecorView() {
        Activity currentActivity = this.screen.getReactContext().getCurrentActivity();
        if (currentActivity != null) {
            View decorView = currentActivity.getWindow().getDecorView();
            Intrinsics.checkNotNullExpressionValue(decorView, "getDecorView(...)");
            return decorView;
        }
        throw new IllegalStateException("[RNScreens] Attempt to access activity on detached context");
    }

    public void onStateChanged(LifecycleOwner lifecycleOwner, Lifecycle.Event event) {
        Intrinsics.checkNotNullParameter(lifecycleOwner, "source");
        Intrinsics.checkNotNullParameter(event, "event");
        int i = WhenMappings.$EnumSwitchMapping$0[event.ordinal()];
        if (i == 1) {
            handleHostFragmentOnStart();
        } else if (i == 2) {
            handleHostFragmentOnResume();
        } else if (i == 3) {
            handleHostFragmentOnPause();
        }
    }

    private final void handleHostFragmentOnStart() {
        InsetsObserverProxy.INSTANCE.registerOnView(requireDecorView());
    }

    private final void handleHostFragmentOnResume() {
        InsetsObserverProxy.INSTANCE.addOnApplyWindowInsetsListener(this);
    }

    private final void handleHostFragmentOnPause() {
        InsetsObserverProxy.INSTANCE.removeOnApplyWindowInsetsListener(this);
    }

    /* access modifiers changed from: private */
    public final void onSheetStateChanged(int i) {
        SheetUtils sheetUtils = SheetUtils.INSTANCE;
        boolean isStateStable = sheetUtils.isStateStable(i);
        if (isStateStable) {
            this.lastStableState = i;
            this.lastStableDetentIndex = sheetUtils.detentIndexFromSheetState(i, this.screen.getSheetDetents().size());
        }
        this.screen.onSheetDetentChanged$react_native_screens_release(this.lastStableDetentIndex, isStateStable);
        if (shouldDismissSheetInState(i)) {
            getStackFragment().dismissSelf$react_native_screens_release();
        }
    }

    public static /* synthetic */ BottomSheetBehavior configureBottomSheetBehaviour$react_native_screens_release$default(SheetDelegate sheetDelegate, BottomSheetBehavior bottomSheetBehavior, KeyboardState keyboardState2, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            keyboardState2 = KeyboardNotVisible.INSTANCE;
        }
        if ((i2 & 4) != 0) {
            i = sheetDelegate.lastStableDetentIndex;
        }
        return sheetDelegate.configureBottomSheetBehaviour$react_native_screens_release(bottomSheetBehavior, keyboardState2, i);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0163, code lost:
        if (r1.getHeight() <= 0) goto L_0x0166;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.material.bottomsheet.BottomSheetBehavior configureBottomSheetBehaviour$react_native_screens_release(com.google.android.material.bottomsheet.BottomSheetBehavior r15, com.swmansion.rnscreens.KeyboardState r16, int r17) {
        /*
            r14 = this;
            r0 = r14
            r8 = r15
            r1 = r16
            r2 = r17
            java.lang.String r3 = "behavior"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r15, r3)
            java.lang.String r3 = "keyboardState"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r1, r3)
            java.lang.Integer r3 = r14.tryResolveContainerHeight()
            if (r3 == 0) goto L_0x032e
            r4 = 1
            r15.setHideable(r4)
            r15.setDraggable(r4)
            com.swmansion.rnscreens.bottomsheet.SheetDelegate$SheetStateObserver r5 = r0.sheetStateObserver
            r15.addBottomSheetCallback(r5)
            com.swmansion.rnscreens.Screen r5 = r0.screen
            com.swmansion.rnscreens.ScreenFooter r5 = r5.getFooter()
            if (r5 == 0) goto L_0x002d
            r5.registerWithSheetBehavior(r15)
        L_0x002d:
            boolean r5 = r1 instanceof com.swmansion.rnscreens.KeyboardNotVisible
            java.lang.String r6 = ". Expected at most 3."
            java.lang.String r7 = "[RNScreens] Invalid detent count "
            r9 = 0
            r10 = 3
            r11 = 0
            r12 = 2
            if (r5 == 0) goto L_0x0188
            com.swmansion.rnscreens.Screen r1 = r0.screen
            java.util.List r1 = r1.getSheetDetents()
            java.util.Collection r1 = (java.util.Collection) r1
            int r1 = r1.size()
            if (r1 == r4) goto L_0x013b
            if (r1 == r12) goto L_0x00e9
            if (r1 != r10) goto L_0x00c5
            com.swmansion.rnscreens.bottomsheet.SheetUtils r1 = com.swmansion.rnscreens.bottomsheet.SheetUtils.INSTANCE
            com.swmansion.rnscreens.Screen r5 = r0.screen
            java.util.List r5 = r5.getSheetDetents()
            java.util.Collection r5 = (java.util.Collection) r5
            int r5 = r5.size()
            int r1 = r1.sheetStateFromDetentIndex(r2, r5)
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)
            com.swmansion.rnscreens.Screen r2 = r0.screen
            java.util.List r2 = r2.getSheetDetents()
            java.lang.Object r2 = r2.get(r11)
            java.lang.Number r2 = (java.lang.Number) r2
            double r5 = r2.doubleValue()
            int r2 = r3.intValue()
            double r9 = (double) r2
            double r5 = r5 * r9
            int r2 = (int) r5
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            com.swmansion.rnscreens.Screen r5 = r0.screen
            java.util.List r5 = r5.getSheetDetents()
            java.lang.Object r5 = r5.get(r4)
            java.lang.Number r5 = (java.lang.Number) r5
            double r5 = r5.doubleValue()
            com.swmansion.rnscreens.Screen r7 = r0.screen
            java.util.List r7 = r7.getSheetDetents()
            java.lang.Object r7 = r7.get(r12)
            java.lang.Number r7 = (java.lang.Number) r7
            double r9 = r7.doubleValue()
            double r5 = r5 / r9
            float r5 = (float) r5
            java.lang.Float r5 = java.lang.Float.valueOf(r5)
            double r6 = (double) r4
            com.swmansion.rnscreens.Screen r4 = r0.screen
            java.util.List r4 = r4.getSheetDetents()
            java.lang.Object r4 = r4.get(r12)
            java.lang.Number r4 = (java.lang.Number) r4
            double r9 = r4.doubleValue()
            double r6 = r6 - r9
            int r3 = r3.intValue()
            double r3 = (double) r3
            double r6 = r6 * r3
            int r3 = (int) r6
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
            com.google.android.material.bottomsheet.BottomSheetBehavior r1 = com.swmansion.rnscreens.bottomsheet.BottomSheetBehaviorExtKt.useThreeDetents(r15, r1, r2, r5, r3)
            goto L_0x0327
        L_0x00c5:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            r2.append(r7)
            com.swmansion.rnscreens.Screen r3 = r0.screen
            java.util.List r3 = r3.getSheetDetents()
            java.util.Collection r3 = (java.util.Collection) r3
            int r3 = r3.size()
            r2.append(r3)
            r2.append(r6)
            java.lang.String r2 = r2.toString()
            r1.<init>(r2)
            throw r1
        L_0x00e9:
            com.swmansion.rnscreens.bottomsheet.SheetUtils r1 = com.swmansion.rnscreens.bottomsheet.SheetUtils.INSTANCE
            com.swmansion.rnscreens.Screen r5 = r0.screen
            java.util.List r5 = r5.getSheetDetents()
            java.util.Collection r5 = (java.util.Collection) r5
            int r5 = r5.size()
            int r1 = r1.sheetStateFromDetentIndex(r2, r5)
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)
            com.swmansion.rnscreens.Screen r2 = r0.screen
            java.util.List r2 = r2.getSheetDetents()
            java.lang.Object r2 = r2.get(r11)
            java.lang.Number r2 = (java.lang.Number) r2
            double r5 = r2.doubleValue()
            int r2 = r3.intValue()
            double r9 = (double) r2
            double r5 = r5 * r9
            int r2 = (int) r5
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            com.swmansion.rnscreens.Screen r5 = r0.screen
            java.util.List r5 = r5.getSheetDetents()
            java.lang.Object r4 = r5.get(r4)
            java.lang.Number r4 = (java.lang.Number) r4
            double r4 = r4.doubleValue()
            int r3 = r3.intValue()
            double r6 = (double) r3
            double r4 = r4 * r6
            int r3 = (int) r4
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
            com.google.android.material.bottomsheet.BottomSheetBehavior r1 = com.swmansion.rnscreens.bottomsheet.BottomSheetBehaviorExtKt.useTwoDetents(r15, r1, r2, r3)
            goto L_0x0327
        L_0x013b:
            com.swmansion.rnscreens.Screen r1 = r0.screen
            boolean r1 = com.swmansion.rnscreens.bottomsheet.SheetUtilsKt.isSheetFitToContents(r1)
            if (r1 == 0) goto L_0x0168
            com.swmansion.rnscreens.Screen r1 = r0.screen
            java.lang.ref.WeakReference r1 = r1.getContentWrapper()
            java.lang.Object r1 = r1.get()
            com.swmansion.rnscreens.ScreenContentWrapper r1 = (com.swmansion.rnscreens.ScreenContentWrapper) r1
            if (r1 == 0) goto L_0x0166
            int r2 = r1.getHeight()
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            boolean r3 = r1.isLaidOut()
            if (r3 != 0) goto L_0x0183
            int r1 = r1.getHeight()
            if (r1 <= 0) goto L_0x0166
            goto L_0x0183
        L_0x0166:
            r2 = r9
            goto L_0x0183
        L_0x0168:
            com.swmansion.rnscreens.Screen r1 = r0.screen
            java.util.List r1 = r1.getSheetDetents()
            java.lang.Object r1 = kotlin.collections.CollectionsKt.first((java.util.List) r1)
            java.lang.Number r1 = (java.lang.Number) r1
            double r1 = r1.doubleValue()
            int r3 = r3.intValue()
            double r3 = (double) r3
            double r1 = r1 * r3
            int r1 = (int) r1
            java.lang.Integer r2 = java.lang.Integer.valueOf(r1)
        L_0x0183:
            com.swmansion.rnscreens.bottomsheet.BottomSheetBehaviorExtKt.useSingleDetent$default(r15, r2, r11, r12, r9)
            goto L_0x0217
        L_0x0188:
            boolean r2 = r1 instanceof com.swmansion.rnscreens.KeyboardVisible
            if (r2 == 0) goto L_0x021a
            int r2 = r15.getMaxHeight()
            com.swmansion.rnscreens.KeyboardVisible r1 = (com.swmansion.rnscreens.KeyboardVisible) r1
            int r3 = r1.getHeight()
            int r2 = r2 - r3
            if (r2 <= r4) goto L_0x01a4
            int r2 = r15.getMaxHeight()
            int r1 = r1.getHeight()
            int r2 = r2 - r1
        L_0x01a2:
            r13 = r2
            goto L_0x01a9
        L_0x01a4:
            int r2 = r15.getMaxHeight()
            goto L_0x01a2
        L_0x01a9:
            com.swmansion.rnscreens.Screen r1 = r0.screen
            java.util.List r1 = r1.getSheetDetents()
            java.util.Collection r1 = (java.util.Collection) r1
            int r1 = r1.size()
            if (r1 == r4) goto L_0x020b
            if (r1 == r12) goto L_0x01f6
            if (r1 != r10) goto L_0x01d2
            java.lang.Integer r2 = java.lang.Integer.valueOf(r10)
            r6 = 14
            r7 = 0
            r3 = 0
            r4 = 0
            r5 = 0
            r1 = r15
            com.swmansion.rnscreens.bottomsheet.BottomSheetBehaviorExtKt.useThreeDetents$default(r1, r2, r3, r4, r5, r6, r7)
            r15.setMaxHeight(r13)
            com.swmansion.rnscreens.bottomsheet.SheetDelegate$KeyboardHandler r1 = r0.keyboardHandlerCallback
            r15.addBottomSheetCallback(r1)
            goto L_0x0217
        L_0x01d2:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            r2.append(r7)
            com.swmansion.rnscreens.Screen r3 = r0.screen
            java.util.List r3 = r3.getSheetDetents()
            java.util.Collection r3 = (java.util.Collection) r3
            int r3 = r3.size()
            r2.append(r3)
            r2.append(r6)
            java.lang.String r2 = r2.toString()
            r1.<init>(r2)
            throw r1
        L_0x01f6:
            java.lang.Integer r2 = java.lang.Integer.valueOf(r10)
            java.lang.Integer r4 = java.lang.Integer.valueOf(r13)
            r5 = 2
            r6 = 0
            r3 = 0
            r1 = r15
            com.swmansion.rnscreens.bottomsheet.BottomSheetBehaviorExtKt.useTwoDetents$default(r1, r2, r3, r4, r5, r6)
            com.swmansion.rnscreens.bottomsheet.SheetDelegate$KeyboardHandler r1 = r0.keyboardHandlerCallback
            r15.addBottomSheetCallback(r1)
            goto L_0x0217
        L_0x020b:
            java.lang.Integer r1 = java.lang.Integer.valueOf(r13)
            com.swmansion.rnscreens.bottomsheet.BottomSheetBehaviorExtKt.useSingleDetent$default(r15, r1, r11, r12, r9)
            com.swmansion.rnscreens.bottomsheet.SheetDelegate$KeyboardHandler r1 = r0.keyboardHandlerCallback
            r15.addBottomSheetCallback(r1)
        L_0x0217:
            r1 = r8
            goto L_0x0327
        L_0x021a:
            boolean r1 = r1 instanceof com.swmansion.rnscreens.KeyboardDidHide
            if (r1 == 0) goto L_0x0328
            com.swmansion.rnscreens.bottomsheet.SheetDelegate$KeyboardHandler r1 = r0.keyboardHandlerCallback
            r15.removeBottomSheetCallback(r1)
            com.swmansion.rnscreens.Screen r1 = r0.screen
            java.util.List r1 = r1.getSheetDetents()
            java.util.Collection r1 = (java.util.Collection) r1
            int r1 = r1.size()
            if (r1 == r4) goto L_0x0308
            if (r1 == r12) goto L_0x02c6
            if (r1 != r10) goto L_0x02a2
            com.swmansion.rnscreens.Screen r1 = r0.screen
            java.util.List r1 = r1.getSheetDetents()
            java.lang.Object r1 = r1.get(r11)
            java.lang.Number r1 = (java.lang.Number) r1
            double r1 = r1.doubleValue()
            int r5 = r3.intValue()
            double r5 = (double) r5
            double r1 = r1 * r5
            int r1 = (int) r1
            java.lang.Integer r5 = java.lang.Integer.valueOf(r1)
            com.swmansion.rnscreens.Screen r1 = r0.screen
            java.util.List r1 = r1.getSheetDetents()
            java.lang.Object r1 = r1.get(r4)
            java.lang.Number r1 = (java.lang.Number) r1
            double r1 = r1.doubleValue()
            com.swmansion.rnscreens.Screen r6 = r0.screen
            java.util.List r6 = r6.getSheetDetents()
            java.lang.Object r6 = r6.get(r12)
            java.lang.Number r6 = (java.lang.Number) r6
            double r6 = r6.doubleValue()
            double r1 = r1 / r6
            float r1 = (float) r1
            java.lang.Float r6 = java.lang.Float.valueOf(r1)
            double r1 = (double) r4
            com.swmansion.rnscreens.Screen r4 = r0.screen
            java.util.List r4 = r4.getSheetDetents()
            java.lang.Object r4 = r4.get(r12)
            java.lang.Number r4 = (java.lang.Number) r4
            double r9 = r4.doubleValue()
            double r1 = r1 - r9
            int r3 = r3.intValue()
            double r3 = (double) r3
            double r1 = r1 * r3
            int r1 = (int) r1
            java.lang.Integer r7 = java.lang.Integer.valueOf(r1)
            r9 = 1
            r10 = 0
            r2 = 0
            r1 = r15
            r3 = r5
            r4 = r6
            r5 = r7
            r6 = r9
            r7 = r10
            com.google.android.material.bottomsheet.BottomSheetBehavior r1 = com.swmansion.rnscreens.bottomsheet.BottomSheetBehaviorExtKt.useThreeDetents$default(r1, r2, r3, r4, r5, r6, r7)
            goto L_0x0327
        L_0x02a2:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            r2.append(r7)
            com.swmansion.rnscreens.Screen r3 = r0.screen
            java.util.List r3 = r3.getSheetDetents()
            java.util.Collection r3 = (java.util.Collection) r3
            int r3 = r3.size()
            r2.append(r3)
            r2.append(r6)
            java.lang.String r2 = r2.toString()
            r1.<init>(r2)
            throw r1
        L_0x02c6:
            com.swmansion.rnscreens.Screen r1 = r0.screen
            java.util.List r1 = r1.getSheetDetents()
            java.lang.Object r1 = r1.get(r11)
            java.lang.Number r1 = (java.lang.Number) r1
            double r1 = r1.doubleValue()
            int r5 = r3.intValue()
            double r5 = (double) r5
            double r1 = r1 * r5
            int r1 = (int) r1
            java.lang.Integer r5 = java.lang.Integer.valueOf(r1)
            com.swmansion.rnscreens.Screen r1 = r0.screen
            java.util.List r1 = r1.getSheetDetents()
            java.lang.Object r1 = r1.get(r4)
            java.lang.Number r1 = (java.lang.Number) r1
            double r1 = r1.doubleValue()
            int r3 = r3.intValue()
            double r3 = (double) r3
            double r1 = r1 * r3
            int r1 = (int) r1
            java.lang.Integer r4 = java.lang.Integer.valueOf(r1)
            r6 = 1
            r7 = 0
            r2 = 0
            r1 = r15
            r3 = r5
            r5 = r6
            r6 = r7
            com.google.android.material.bottomsheet.BottomSheetBehavior r1 = com.swmansion.rnscreens.bottomsheet.BottomSheetBehaviorExtKt.useTwoDetents$default(r1, r2, r3, r4, r5, r6)
            goto L_0x0327
        L_0x0308:
            com.swmansion.rnscreens.Screen r1 = r0.screen
            java.util.List r1 = r1.getSheetDetents()
            java.lang.Object r1 = kotlin.collections.CollectionsKt.first((java.util.List) r1)
            java.lang.Number r1 = (java.lang.Number) r1
            double r1 = r1.doubleValue()
            int r3 = r3.intValue()
            double r3 = (double) r3
            double r1 = r1 * r3
            int r1 = (int) r1
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)
            com.google.android.material.bottomsheet.BottomSheetBehavior r1 = com.swmansion.rnscreens.bottomsheet.BottomSheetBehaviorExtKt.useSingleDetent(r15, r1, r11)
        L_0x0327:
            return r1
        L_0x0328:
            kotlin.NoWhenBranchMatchedException r1 = new kotlin.NoWhenBranchMatchedException
            r1.<init>()
            throw r1
        L_0x032e:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.String r2 = "[RNScreens] Failed to find window height during bottom sheet behaviour configuration"
            r1.<init>(r2)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.swmansion.rnscreens.bottomsheet.SheetDelegate.configureBottomSheetBehaviour$react_native_screens_release(com.google.android.material.bottomsheet.BottomSheetBehavior, com.swmansion.rnscreens.KeyboardState, int):com.google.android.material.bottomsheet.BottomSheetBehavior");
    }

    public WindowInsetsCompat onApplyWindowInsets(View view, WindowInsetsCompat windowInsetsCompat) {
        Intrinsics.checkNotNullParameter(view, "v");
        Intrinsics.checkNotNullParameter(windowInsetsCompat, "insets");
        boolean isVisible = windowInsetsCompat.isVisible(WindowInsetsCompat.Type.ime());
        Insets insets = windowInsetsCompat.getInsets(WindowInsetsCompat.Type.ime());
        Intrinsics.checkNotNullExpressionValue(insets, "getInsets(...)");
        if (isVisible) {
            this.isKeyboardVisible = true;
            this.keyboardState = new KeyboardVisible(insets.bottom);
            BottomSheetBehavior sheetBehavior = getSheetBehavior();
            if (sheetBehavior != null) {
                configureBottomSheetBehaviour$react_native_screens_release$default(this, sheetBehavior, this.keyboardState, 0, 4, (Object) null);
            }
            Insets insets2 = windowInsetsCompat.getInsets(WindowInsetsCompat.Type.navigationBars());
            Intrinsics.checkNotNullExpressionValue(insets2, "getInsets(...)");
            WindowInsetsCompat build = new WindowInsetsCompat.Builder(windowInsetsCompat).setInsets(WindowInsetsCompat.Type.navigationBars(), Insets.of(insets2.left, insets2.top, insets2.right, 0)).build();
            Intrinsics.checkNotNullExpressionValue(build, "build(...)");
            return build;
        }
        BottomSheetBehavior sheetBehavior2 = getSheetBehavior();
        if (sheetBehavior2 != null) {
            if (this.isKeyboardVisible) {
                configureBottomSheetBehaviour$react_native_screens_release$default(this, sheetBehavior2, KeyboardDidHide.INSTANCE, 0, 4, (Object) null);
            } else {
                KeyboardState keyboardState2 = this.keyboardState;
                KeyboardNotVisible keyboardNotVisible = KeyboardNotVisible.INSTANCE;
                if (!Intrinsics.areEqual((Object) keyboardState2, (Object) keyboardNotVisible)) {
                    configureBottomSheetBehaviour$react_native_screens_release$default(this, sheetBehavior2, keyboardNotVisible, 0, 4, (Object) null);
                }
            }
        }
        this.keyboardState = KeyboardNotVisible.INSTANCE;
        this.isKeyboardVisible = false;
        Insets insets3 = windowInsetsCompat.getInsets(WindowInsetsCompat.Type.navigationBars());
        Intrinsics.checkNotNullExpressionValue(insets3, "getInsets(...)");
        WindowInsetsCompat build2 = new WindowInsetsCompat.Builder(windowInsetsCompat).setInsets(WindowInsetsCompat.Type.navigationBars(), Insets.of(insets3.left, insets3.top, insets3.right, 0)).build();
        Intrinsics.checkNotNullExpressionValue(build2, "build(...)");
        return build2;
    }

    private final Integer tryResolveContainerHeight() {
        WindowMetrics m;
        Rect m2;
        DisplayMetrics displayMetrics;
        ScreenContainer container = this.screen.getContainer();
        if (container != null) {
            return Integer.valueOf(container.getHeight());
        }
        ThemedReactContext reactContext = this.screen.getReactContext();
        Resources resources = reactContext.getResources();
        if (resources != null && (displayMetrics = resources.getDisplayMetrics()) != null) {
            return Integer.valueOf(displayMetrics.heightPixels);
        }
        if (Build.VERSION.SDK_INT >= 30) {
            Object systemService = reactContext.getSystemService("window");
            WindowManager windowManager = systemService instanceof WindowManager ? (WindowManager) systemService : null;
            if (!(windowManager == null || (m = windowManager.getCurrentWindowMetrics()) == null || (m2 = m.getBounds()) == null)) {
                return Integer.valueOf(m2.height());
            }
        }
        return null;
    }

    private final class KeyboardHandler extends BottomSheetBehavior.BottomSheetCallback {
        public void onSlide(View view, float f) {
            Intrinsics.checkNotNullParameter(view, "bottomSheet");
        }

        public KeyboardHandler() {
        }

        public void onStateChanged(View view, int i) {
            Intrinsics.checkNotNullParameter(view, "bottomSheet");
            if (i == 4 && WindowInsetsCompat.toWindowInsetsCompat(view.getRootWindowInsets()).isVisible(WindowInsetsCompat.Type.ime())) {
                view.requestFocus();
                ((InputMethodManager) SheetDelegate.this.getScreen().getReactContext().getSystemService(InputMethodManager.class)).hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        }
    }

    private final class SheetStateObserver extends BottomSheetBehavior.BottomSheetCallback {
        public void onSlide(View view, float f) {
            Intrinsics.checkNotNullParameter(view, "bottomSheet");
        }

        public SheetStateObserver() {
        }

        public void onStateChanged(View view, int i) {
            Intrinsics.checkNotNullParameter(view, "bottomSheet");
            SheetDelegate.this.onSheetStateChanged(i);
        }
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
