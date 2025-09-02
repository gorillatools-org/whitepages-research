package com.swmansion.rnscreens;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.SparseArray;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.ImageView;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.ViewGroupKt;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.uimanager.PixelUtil;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.UIManagerHelper;
import com.facebook.react.uimanager.events.EventDispatcher;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.ShapeAppearanceModel;
import com.swmansion.rnscreens.ScreenContentWrapper;
import com.swmansion.rnscreens.bottomsheet.BottomSheetBehaviorExtKt;
import com.swmansion.rnscreens.bottomsheet.SheetUtilsKt;
import com.swmansion.rnscreens.events.HeaderHeightChangeEvent;
import com.swmansion.rnscreens.events.SheetDetentChangedEvent;
import com.swmansion.rnscreens.ext.ViewExtKt;
import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class Screen extends FabricEnabledViewGroup implements ScreenContentWrapper.OnLayoutCallback {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private ActivityState activityState;
    private ScreenContainer container;
    private WeakReference contentWrapper = new WeakReference((Object) null);
    private ScreenFooter footer;
    private ScreenFragmentWrapper fragmentWrapper;
    private boolean isBeingRemoved;
    private boolean isGestureEnabled = true;
    private Boolean isNavigationBarHidden;
    private Boolean isNavigationBarTranslucent;
    private boolean isSheetGrabberVisible;
    private Boolean isStatusBarAnimated;
    private Boolean isStatusBarHidden;
    private Boolean isStatusBarTranslucent;
    private boolean isTransitioning;
    private boolean nativeBackButtonDismissalEnabled;
    private Integer navigationBarColor;
    private final ThemedReactContext reactContext;
    private ReplaceAnimation replaceAnimation = ReplaceAnimation.POP;
    private Integer screenOrientation;
    private boolean sheetClosesOnTouchOutside = true;
    private float sheetCornerRadius;
    private List sheetDetents = CollectionsKt.mutableListOf(Double.valueOf(1.0d));
    private float sheetElevation = 24.0f;
    private boolean sheetExpandsWhenScrolledToEdge = true;
    private int sheetInitialDetentIndex;
    private int sheetLargestUndimmedDetentIndex = -1;
    private boolean shouldTriggerPostponedTransitionAfterLayout;
    private boolean shouldUpdateSheetCornerRadius;
    private StackAnimation stackAnimation = StackAnimation.DEFAULT;
    private StackPresentation stackPresentation = StackPresentation.PUSH;
    private Integer statusBarColor;
    private String statusBarStyle;

    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0010 */
        static {
            /*
                com.swmansion.rnscreens.Screen$StackPresentation[] r0 = com.swmansion.rnscreens.Screen.StackPresentation.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                com.swmansion.rnscreens.Screen$StackPresentation r1 = com.swmansion.rnscreens.Screen.StackPresentation.TRANSPARENT_MODAL     // Catch:{ NoSuchFieldError -> 0x0010 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0010 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0010 }
            L_0x0010:
                com.swmansion.rnscreens.Screen$StackPresentation r1 = com.swmansion.rnscreens.Screen.StackPresentation.FORM_SHEET     // Catch:{ NoSuchFieldError -> 0x0019 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0019 }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0019 }
            L_0x0019:
                $EnumSwitchMapping$0 = r0
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.swmansion.rnscreens.Screen.WhenMappings.<clinit>():void");
        }
    }

    /* access modifiers changed from: protected */
    public void dispatchRestoreInstanceState(SparseArray sparseArray) {
        Intrinsics.checkNotNullParameter(sparseArray, "container");
    }

    /* access modifiers changed from: protected */
    public void dispatchSaveInstanceState(SparseArray sparseArray) {
        Intrinsics.checkNotNullParameter(sparseArray, "container");
    }

    public void setLayerType(int i, Paint paint) {
    }

    public final ThemedReactContext getReactContext() {
        return this.reactContext;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public Screen(ThemedReactContext themedReactContext) {
        super(themedReactContext);
        Intrinsics.checkNotNullParameter(themedReactContext, "reactContext");
        this.reactContext = themedReactContext;
        setLayoutParams(new WindowManager.LayoutParams(2));
        this.nativeBackButtonDismissalEnabled = true;
    }

    public final Fragment getFragment() {
        ScreenFragmentWrapper screenFragmentWrapper = this.fragmentWrapper;
        if (screenFragmentWrapper != null) {
            return screenFragmentWrapper.getFragment();
        }
        return null;
    }

    public final WeakReference<ScreenContentWrapper> getContentWrapper() {
        return this.contentWrapper;
    }

    public final void setContentWrapper(WeakReference<ScreenContentWrapper> weakReference) {
        Intrinsics.checkNotNullParameter(weakReference, "<set-?>");
        this.contentWrapper = weakReference;
    }

    public final BottomSheetBehavior<Screen> getSheetBehavior() {
        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        CoordinatorLayout.LayoutParams layoutParams2 = layoutParams instanceof CoordinatorLayout.LayoutParams ? (CoordinatorLayout.LayoutParams) layoutParams : null;
        CoordinatorLayout.Behavior behavior = layoutParams2 != null ? layoutParams2.getBehavior() : null;
        if (behavior instanceof BottomSheetBehavior) {
            return (BottomSheetBehavior) behavior;
        }
        return null;
    }

    public final EventDispatcher getReactEventDispatcher() {
        return UIManagerHelper.getEventDispatcherForReactTag(this.reactContext, getId());
    }

    public final ScreenFragmentWrapper getFragmentWrapper() {
        return this.fragmentWrapper;
    }

    public final void setFragmentWrapper(ScreenFragmentWrapper screenFragmentWrapper) {
        this.fragmentWrapper = screenFragmentWrapper;
    }

    public final ScreenContainer getContainer() {
        return this.container;
    }

    public final void setContainer(ScreenContainer screenContainer) {
        this.container = screenContainer;
    }

    public final ActivityState getActivityState() {
        return this.activityState;
    }

    public final StackPresentation getStackPresentation() {
        return this.stackPresentation;
    }

    public final void setStackPresentation(StackPresentation stackPresentation2) {
        Intrinsics.checkNotNullParameter(stackPresentation2, "<set-?>");
        this.stackPresentation = stackPresentation2;
    }

    public final ReplaceAnimation getReplaceAnimation() {
        return this.replaceAnimation;
    }

    public final void setReplaceAnimation(ReplaceAnimation replaceAnimation2) {
        Intrinsics.checkNotNullParameter(replaceAnimation2, "<set-?>");
        this.replaceAnimation = replaceAnimation2;
    }

    public final StackAnimation getStackAnimation() {
        return this.stackAnimation;
    }

    public final void setStackAnimation(StackAnimation stackAnimation2) {
        Intrinsics.checkNotNullParameter(stackAnimation2, "<set-?>");
        this.stackAnimation = stackAnimation2;
    }

    public final void setGestureEnabled(boolean z) {
        this.isGestureEnabled = z;
    }

    public final Integer getScreenOrientation() {
        return this.screenOrientation;
    }

    public final Boolean isStatusBarAnimated() {
        return this.isStatusBarAnimated;
    }

    public final void setStatusBarAnimated(Boolean bool) {
        this.isStatusBarAnimated = bool;
    }

    public final boolean isBeingRemoved() {
        return this.isBeingRemoved;
    }

    public final void setBeingRemoved(boolean z) {
        this.isBeingRemoved = z;
    }

    public final void setSheetGrabberVisible(boolean z) {
        this.isSheetGrabberVisible = z;
    }

    public final float getSheetCornerRadius() {
        return this.sheetCornerRadius;
    }

    public final void setSheetCornerRadius(float f) {
        if (this.sheetCornerRadius != f) {
            this.sheetCornerRadius = f;
            this.shouldUpdateSheetCornerRadius = true;
        }
    }

    public final boolean getSheetExpandsWhenScrolledToEdge() {
        return this.sheetExpandsWhenScrolledToEdge;
    }

    public final void setSheetExpandsWhenScrolledToEdge(boolean z) {
        this.sheetExpandsWhenScrolledToEdge = z;
    }

    public final List<Double> getSheetDetents() {
        return this.sheetDetents;
    }

    public final void setSheetDetents(List<Double> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.sheetDetents = list;
    }

    public final int getSheetLargestUndimmedDetentIndex() {
        return this.sheetLargestUndimmedDetentIndex;
    }

    public final void setSheetLargestUndimmedDetentIndex(int i) {
        this.sheetLargestUndimmedDetentIndex = i;
    }

    public final int getSheetInitialDetentIndex() {
        return this.sheetInitialDetentIndex;
    }

    public final void setSheetInitialDetentIndex(int i) {
        this.sheetInitialDetentIndex = i;
    }

    public final boolean getSheetClosesOnTouchOutside() {
        return this.sheetClosesOnTouchOutside;
    }

    public final void setSheetClosesOnTouchOutside(boolean z) {
        this.sheetClosesOnTouchOutside = z;
    }

    public final float getSheetElevation() {
        return this.sheetElevation;
    }

    public final void setSheetElevation(float f) {
        this.sheetElevation = f;
    }

    public final boolean getShouldTriggerPostponedTransitionAfterLayout() {
        return this.shouldTriggerPostponedTransitionAfterLayout;
    }

    public final void setShouldTriggerPostponedTransitionAfterLayout(boolean z) {
        this.shouldTriggerPostponedTransitionAfterLayout = z;
    }

    public final ScreenFooter getFooter() {
        return this.footer;
    }

    public final void setFooter(ScreenFooter screenFooter) {
        BottomSheetBehavior<Screen> sheetBehavior;
        if (screenFooter == null && this.footer != null) {
            BottomSheetBehavior<Screen> sheetBehavior2 = getSheetBehavior();
            if (sheetBehavior2 != null) {
                ScreenFooter screenFooter2 = this.footer;
                Intrinsics.checkNotNull(screenFooter2);
                screenFooter2.unregisterWithSheetBehavior(sheetBehavior2);
            }
        } else if (!(screenFooter == null || (sheetBehavior = getSheetBehavior()) == null)) {
            screenFooter.registerWithSheetBehavior(sheetBehavior);
        }
        this.footer = screenFooter;
    }

    private final boolean isNativeStackScreen() {
        return this.container instanceof ScreenStack;
    }

    public void onContentWrapperLayout(boolean z, int i, int i2, int i3, int i4) {
        int i5 = i4 - i2;
        if (SheetUtilsKt.isSheetFitToContents(this)) {
            BottomSheetBehavior<Screen> sheetBehavior = getSheetBehavior();
            if (sheetBehavior != null) {
                BottomSheetBehaviorExtKt.useSingleDetent$default(sheetBehavior, Integer.valueOf(i5), false, 2, (Object) null);
            }
            this.shouldTriggerPostponedTransitionAfterLayout = true;
            ViewGroup parentAsViewGroup = ViewExtKt.parentAsViewGroup(this);
            if (parentAsViewGroup != null && !parentAsViewGroup.isInLayout()) {
                parentAsViewGroup.requestLayout();
            }
        }
    }

    public final void registerLayoutCallbackForWrapper(ScreenContentWrapper screenContentWrapper) {
        Intrinsics.checkNotNullParameter(screenContentWrapper, "wrapper");
        screenContentWrapper.setDelegate$react_native_screens_release(this);
        this.contentWrapper = new WeakReference(screenContentWrapper);
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        if (z && isNativeStackScreen() && !SheetUtilsKt.usesFormSheetPresentation(this)) {
            dispatchShadowStateUpdate(i3 - i, i4 - i2, i2);
            notifyHeaderHeightChange(i2);
        }
    }

    public final void onBottomSheetBehaviorDidLayout$react_native_screens_release(boolean z) {
        if (SheetUtilsKt.usesFormSheetPresentation(this)) {
            if (z && isNativeStackScreen()) {
                dispatchShadowStateUpdate(getWidth(), getHeight(), getTop());
            }
            maybeTriggerPostponedTransition();
            ScreenFooter screenFooter = this.footer;
            if (screenFooter != null) {
                int left = getLeft();
                int top = getTop();
                int right = getRight();
                int bottom = getBottom();
                ScreenContainer screenContainer = this.container;
                Intrinsics.checkNotNull(screenContainer);
                screenFooter.onParentLayout(z, left, top, right, bottom, screenContainer.getHeight());
            }
        }
    }

    private final void maybeTriggerPostponedTransition() {
        if (this.shouldTriggerPostponedTransitionAfterLayout) {
            this.shouldTriggerPostponedTransitionAfterLayout = false;
            Fragment fragment = getFragment();
            if (fragment != null) {
                fragment.startPostponedEnterTransition();
            }
        }
    }

    private final void updateScreenSizePaper(int i, int i2) {
        ThemedReactContext themedReactContext = this.reactContext;
        themedReactContext.runOnNativeModulesQueueThread(new Screen$updateScreenSizePaper$1(this, i, i2, themedReactContext.getExceptionHandler()));
    }

    private final void dispatchShadowStateUpdate(int i, int i2, int i3) {
        updateScreenSizePaper(i, i2);
    }

    public final ScreenStackHeaderConfig getHeaderConfig() {
        Object obj;
        Iterator it = ViewGroupKt.getChildren(this).iterator();
        while (true) {
            if (!it.hasNext()) {
                obj = null;
                break;
            }
            obj = it.next();
            if (((View) obj) instanceof ScreenStackHeaderConfig) {
                break;
            }
        }
        if (obj instanceof ScreenStackHeaderConfig) {
            return (ScreenStackHeaderConfig) obj;
        }
        return null;
    }

    public final void setTransitioning(boolean z) {
        if (this.isTransitioning != z) {
            this.isTransitioning = z;
            boolean hasWebView = hasWebView(this);
            int i = 2;
            if (!hasWebView || getLayerType() == 2) {
                if (!z || hasWebView) {
                    i = 0;
                }
                super.setLayerType(i, (Paint) null);
            }
        }
    }

    public final boolean isTransparent() {
        int i = WhenMappings.$EnumSwitchMapping$0[this.stackPresentation.ordinal()];
        return i == 1 || i == 2;
    }

    private final boolean hasWebView(ViewGroup viewGroup) {
        int childCount = viewGroup.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = viewGroup.getChildAt(i);
            if (childAt instanceof WebView) {
                return true;
            }
            if ((childAt instanceof ViewGroup) && hasWebView((ViewGroup) childAt)) {
                return true;
            }
        }
        return false;
    }

    public final void setActivityState(ActivityState activityState2) {
        Intrinsics.checkNotNullParameter(activityState2, "activityState");
        ActivityState activityState3 = this.activityState;
        if (activityState2 != activityState3) {
            if ((this.container instanceof ScreenStack) && activityState3 != null) {
                Intrinsics.checkNotNull(activityState3);
                if (activityState2.compareTo(activityState3) < 0) {
                    throw new IllegalStateException("[RNScreens] activityState can only progress in NativeStack");
                }
            }
            this.activityState = activityState2;
            ScreenContainer screenContainer = this.container;
            if (screenContainer != null) {
                screenContainer.onChildUpdate();
            }
        }
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void setScreenOrientation(java.lang.String r3) {
        /*
            r2 = this;
            if (r3 != 0) goto L_0x0006
            r3 = 0
            r2.screenOrientation = r3
            return
        L_0x0006:
            com.swmansion.rnscreens.ScreenWindowTraits r0 = com.swmansion.rnscreens.ScreenWindowTraits.INSTANCE
            r0.applyDidSetOrientation$react_native_screens_release()
            int r1 = r3.hashCode()
            switch(r1) {
                case -1894896954: goto L_0x0057;
                case 96673: goto L_0x004b;
                case 729267099: goto L_0x0040;
                case 1430647483: goto L_0x0035;
                case 1651658175: goto L_0x002a;
                case 1730732811: goto L_0x001e;
                case 2118770584: goto L_0x0013;
                default: goto L_0x0012;
            }
        L_0x0012:
            goto L_0x005f
        L_0x0013:
            java.lang.String r1 = "landscape_right"
            boolean r3 = r3.equals(r1)
            if (r3 != 0) goto L_0x001c
            goto L_0x005f
        L_0x001c:
            r3 = 0
            goto L_0x0063
        L_0x001e:
            java.lang.String r1 = "landscape_left"
            boolean r3 = r3.equals(r1)
            if (r3 != 0) goto L_0x0027
            goto L_0x005f
        L_0x0027:
            r3 = 8
            goto L_0x0063
        L_0x002a:
            java.lang.String r1 = "portrait_up"
            boolean r3 = r3.equals(r1)
            if (r3 != 0) goto L_0x0033
            goto L_0x005f
        L_0x0033:
            r3 = 1
            goto L_0x0063
        L_0x0035:
            java.lang.String r1 = "landscape"
            boolean r3 = r3.equals(r1)
            if (r3 != 0) goto L_0x003e
            goto L_0x005f
        L_0x003e:
            r3 = 6
            goto L_0x0063
        L_0x0040:
            java.lang.String r1 = "portrait"
            boolean r3 = r3.equals(r1)
            if (r3 != 0) goto L_0x0049
            goto L_0x005f
        L_0x0049:
            r3 = 7
            goto L_0x0063
        L_0x004b:
            java.lang.String r1 = "all"
            boolean r3 = r3.equals(r1)
            if (r3 != 0) goto L_0x0054
            goto L_0x005f
        L_0x0054:
            r3 = 10
            goto L_0x0063
        L_0x0057:
            java.lang.String r1 = "portrait_down"
            boolean r3 = r3.equals(r1)
            if (r3 != 0) goto L_0x0061
        L_0x005f:
            r3 = -1
            goto L_0x0063
        L_0x0061:
            r3 = 9
        L_0x0063:
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
            r2.screenOrientation = r3
            com.swmansion.rnscreens.ScreenFragmentWrapper r3 = r2.fragmentWrapper
            if (r3 == 0) goto L_0x0074
            android.app.Activity r3 = r3.tryGetActivity()
            r0.setOrientation$react_native_screens_release(r2, r3)
        L_0x0074:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.swmansion.rnscreens.Screen.setScreenOrientation(java.lang.String):void");
    }

    public final void changeAccessibilityMode(int i) {
        CustomToolbar toolbar;
        setImportantForAccessibility(i);
        ScreenStackHeaderConfig headerConfig = getHeaderConfig();
        if (headerConfig != null && (toolbar = headerConfig.getToolbar()) != null) {
            toolbar.setImportantForAccessibility(i);
        }
    }

    public final String getStatusBarStyle() {
        return this.statusBarStyle;
    }

    public final void setStatusBarStyle(String str) {
        if (str != null) {
            ScreenWindowTraits.INSTANCE.applyDidSetStatusBarAppearance$react_native_screens_release();
        }
        this.statusBarStyle = str;
        ScreenFragmentWrapper screenFragmentWrapper = this.fragmentWrapper;
        if (screenFragmentWrapper != null) {
            ScreenWindowTraits.INSTANCE.setStyle$react_native_screens_release(this, screenFragmentWrapper.tryGetActivity(), screenFragmentWrapper.tryGetContext());
        }
    }

    public final Boolean isStatusBarHidden() {
        return this.isStatusBarHidden;
    }

    public final void setStatusBarHidden(Boolean bool) {
        if (bool != null) {
            ScreenWindowTraits.INSTANCE.applyDidSetStatusBarAppearance$react_native_screens_release();
        }
        this.isStatusBarHidden = bool;
        ScreenFragmentWrapper screenFragmentWrapper = this.fragmentWrapper;
        if (screenFragmentWrapper != null) {
            ScreenWindowTraits.INSTANCE.setHidden$react_native_screens_release(this, screenFragmentWrapper.tryGetActivity());
        }
    }

    public final Boolean isStatusBarTranslucent() {
        return this.isStatusBarTranslucent;
    }

    public final void setStatusBarTranslucent(Boolean bool) {
        if (bool != null) {
            ScreenWindowTraits.INSTANCE.applyDidSetStatusBarAppearance$react_native_screens_release();
        }
        this.isStatusBarTranslucent = bool;
        ScreenFragmentWrapper screenFragmentWrapper = this.fragmentWrapper;
        if (screenFragmentWrapper != null) {
            ScreenWindowTraits.INSTANCE.setTranslucent$react_native_screens_release(this, screenFragmentWrapper.tryGetActivity(), screenFragmentWrapper.tryGetContext());
        }
    }

    public final Integer getStatusBarColor() {
        return this.statusBarColor;
    }

    public final void setStatusBarColor(Integer num) {
        if (num != null) {
            ScreenWindowTraits.INSTANCE.applyDidSetStatusBarAppearance$react_native_screens_release();
        }
        this.statusBarColor = num;
        ScreenFragmentWrapper screenFragmentWrapper = this.fragmentWrapper;
        if (screenFragmentWrapper != null) {
            ScreenWindowTraits.INSTANCE.setColor$react_native_screens_release(this, screenFragmentWrapper.tryGetActivity(), screenFragmentWrapper.tryGetContext());
        }
    }

    public final Integer getNavigationBarColor() {
        return this.navigationBarColor;
    }

    public final void setNavigationBarColor(Integer num) {
        if (num != null) {
            ScreenWindowTraits.INSTANCE.applyDidSetNavigationBarAppearance$react_native_screens_release();
        }
        this.navigationBarColor = num;
        ScreenFragmentWrapper screenFragmentWrapper = this.fragmentWrapper;
        if (screenFragmentWrapper != null) {
            ScreenWindowTraits.INSTANCE.setNavigationBarColor$react_native_screens_release(this, screenFragmentWrapper.tryGetActivity());
        }
    }

    public final Boolean isNavigationBarTranslucent() {
        return this.isNavigationBarTranslucent;
    }

    public final void setNavigationBarTranslucent(Boolean bool) {
        if (bool != null) {
            ScreenWindowTraits.INSTANCE.applyDidSetNavigationBarAppearance$react_native_screens_release();
        }
        this.isNavigationBarTranslucent = bool;
        ScreenFragmentWrapper screenFragmentWrapper = this.fragmentWrapper;
        if (screenFragmentWrapper != null) {
            ScreenWindowTraits.INSTANCE.setNavigationBarTranslucent$react_native_screens_release(this, screenFragmentWrapper.tryGetActivity());
        }
    }

    public final Boolean isNavigationBarHidden() {
        return this.isNavigationBarHidden;
    }

    public final void setNavigationBarHidden(Boolean bool) {
        if (bool != null) {
            ScreenWindowTraits.INSTANCE.applyDidSetNavigationBarAppearance$react_native_screens_release();
        }
        this.isNavigationBarHidden = bool;
        ScreenFragmentWrapper screenFragmentWrapper = this.fragmentWrapper;
        if (screenFragmentWrapper != null) {
            ScreenWindowTraits.INSTANCE.setNavigationBarHidden$react_native_screens_release(this, screenFragmentWrapper.tryGetActivity());
        }
    }

    public final boolean getNativeBackButtonDismissalEnabled() {
        return this.nativeBackButtonDismissalEnabled;
    }

    public final void setNativeBackButtonDismissalEnabled(boolean z) {
        this.nativeBackButtonDismissalEnabled = z;
    }

    public final void startRemovalTransition() {
        if (!this.isBeingRemoved) {
            this.isBeingRemoved = true;
            startTransitionRecursive(this);
        }
    }

    public final void endRemovalTransition() {
        if (this.isBeingRemoved) {
            this.isBeingRemoved = false;
            endTransitionRecursive(this);
        }
    }

    private final void endTransitionRecursive(ViewGroup viewGroup) {
        for (View view : ViewGroupKt.getChildren(viewGroup)) {
            viewGroup.endViewTransition(view);
            if (view instanceof ScreenStackHeaderConfig) {
                endTransitionRecursive(((ScreenStackHeaderConfig) view).getToolbar());
            }
            if (view instanceof ViewGroup) {
                endTransitionRecursive((ViewGroup) view);
            }
        }
    }

    private final void startTransitionRecursive(ViewGroup viewGroup) {
        if (viewGroup != null) {
            int childCount = viewGroup.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childAt = viewGroup.getChildAt(i);
                if ((viewGroup instanceof SwipeRefreshLayout) && (childAt instanceof ImageView)) {
                    viewGroup.addView(new View(getContext()), i);
                } else if (childAt != null) {
                    viewGroup.startViewTransition(childAt);
                }
                if (childAt instanceof ScreenStackHeaderConfig) {
                    startTransitionRecursive(((ScreenStackHeaderConfig) childAt).getToolbar());
                }
                if (childAt instanceof ViewGroup) {
                    startTransitionRecursive((ViewGroup) childAt);
                }
            }
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (SheetUtilsKt.usesFormSheetPresentation(this)) {
            return true;
        }
        return super.onTouchEvent(motionEvent);
    }

    private final void notifyHeaderHeightChange(int i) {
        Context context = getContext();
        Intrinsics.checkNotNull(context, "null cannot be cast to non-null type com.facebook.react.bridge.ReactContext");
        ReactContext reactContext2 = (ReactContext) context;
        int surfaceId = UIManagerHelper.getSurfaceId((Context) reactContext2);
        EventDispatcher eventDispatcherForReactTag = UIManagerHelper.getEventDispatcherForReactTag(reactContext2, getId());
        if (eventDispatcherForReactTag != null) {
            eventDispatcherForReactTag.dispatchEvent(new HeaderHeightChangeEvent(surfaceId, getId(), i));
        }
    }

    public final void onSheetDetentChanged$react_native_screens_release(int i, boolean z) {
        dispatchSheetDetentChanged(i, z);
    }

    private final void dispatchSheetDetentChanged(int i, boolean z) {
        int surfaceId = UIManagerHelper.getSurfaceId((Context) this.reactContext);
        EventDispatcher reactEventDispatcher = getReactEventDispatcher();
        if (reactEventDispatcher != null) {
            reactEventDispatcher.dispatchEvent(new SheetDetentChangedEvent(surfaceId, getId(), i, z));
        }
    }

    public final void onFinalizePropsUpdate$react_native_screens_release() {
        if (this.shouldUpdateSheetCornerRadius) {
            this.shouldUpdateSheetCornerRadius = false;
            onSheetCornerRadiusChange$react_native_screens_release();
        }
    }

    public final void onSheetCornerRadiusChange$react_native_screens_release() {
        if (this.stackPresentation == StackPresentation.FORM_SHEET && getBackground() != null) {
            Drawable background = getBackground();
            MaterialShapeDrawable materialShapeDrawable = background instanceof MaterialShapeDrawable ? (MaterialShapeDrawable) background : null;
            if (materialShapeDrawable != null) {
                float dIPFromPixel = PixelUtil.toDIPFromPixel(this.sheetCornerRadius);
                ShapeAppearanceModel.Builder builder = new ShapeAppearanceModel.Builder();
                builder.setTopLeftCorner(0, dIPFromPixel);
                builder.setTopRightCorner(0, dIPFromPixel);
                materialShapeDrawable.setShapeAppearanceModel(builder.build());
            }
        }
    }

    public enum StackPresentation {
        PUSH,
        MODAL,
        TRANSPARENT_MODAL,
        FORM_SHEET;

        static {
            StackPresentation[] $values;
            $ENTRIES = EnumEntriesKt.enumEntries($values);
        }
    }

    public enum StackAnimation {
        DEFAULT,
        NONE,
        FADE,
        SLIDE_FROM_BOTTOM,
        SLIDE_FROM_RIGHT,
        SLIDE_FROM_LEFT,
        FADE_FROM_BOTTOM,
        IOS_FROM_RIGHT,
        IOS_FROM_LEFT;

        static {
            StackAnimation[] $values;
            $ENTRIES = EnumEntriesKt.enumEntries($values);
        }
    }

    public enum ReplaceAnimation {
        PUSH,
        POP;

        static {
            ReplaceAnimation[] $values;
            $ENTRIES = EnumEntriesKt.enumEntries($values);
        }
    }

    public enum ActivityState {
        INACTIVE,
        TRANSITIONING_OR_BELOW_TOP,
        ON_TOP;

        static {
            ActivityState[] $values;
            $ENTRIES = EnumEntriesKt.enumEntries($values);
        }
    }

    public enum WindowTraits {
        ORIENTATION,
        COLOR,
        STYLE,
        TRANSLUCENT,
        HIDDEN,
        ANIMATED,
        NAVIGATION_BAR_COLOR,
        NAVIGATION_BAR_TRANSLUCENT,
        NAVIGATION_BAR_HIDDEN;

        static {
            WindowTraits[] $values;
            $ENTRIES = EnumEntriesKt.enumEntries($values);
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
