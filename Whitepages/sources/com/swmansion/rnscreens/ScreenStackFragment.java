package com.swmansion.rnscreens;

import android.animation.StateListAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.WindowInsets;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.Transformation;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;
import com.facebook.react.uimanager.PixelUtil;
import com.facebook.react.uimanager.PointerEvents;
import com.facebook.react.uimanager.ReactPointerEventsView;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.UIManagerHelper;
import com.facebook.react.uimanager.events.EventDispatcher;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.ShapeAppearanceModel;
import com.swmansion.rnscreens.ScreenStackHeaderSubview;
import com.swmansion.rnscreens.bottomsheet.DimmingViewManager;
import com.swmansion.rnscreens.bottomsheet.SheetDelegate;
import com.swmansion.rnscreens.bottomsheet.SheetUtilsKt;
import com.swmansion.rnscreens.events.ScreenDismissedEvent;
import com.swmansion.rnscreens.ext.ViewExtKt;
import com.swmansion.rnscreens.utils.DeviceUtils;
import com.swmansion.rnscreens.utils.ViewBackgroundUtilsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

public final class ScreenStackFragment extends ScreenFragment implements ScreenStackFragmentWrapper {
    private AppBarLayout appBarLayout;
    private ScreensCoordinatorLayout coordinatorLayout;
    private DimmingViewManager dimmingDelegate;
    private boolean isToolbarShadowHidden;
    private boolean isToolbarTranslucent;
    private View lastFocusedChild;
    private Function1 onSearchViewCreate;
    private CustomSearchView searchView;
    private SheetDelegate sheetDelegate;
    private Toolbar toolbar;

    public Animation onCreateAnimation(int i, boolean z, int i2) {
        return null;
    }

    public final CustomSearchView getSearchView() {
        return this.searchView;
    }

    public final void setOnSearchViewCreate(Function1 function1) {
        this.onSearchViewCreate = function1;
    }

    private final ScreenStack getScreenStack() {
        ScreenContainer container = getScreen().getContainer();
        if (container instanceof ScreenStack) {
            return (ScreenStack) container;
        }
        throw new IllegalStateException("ScreenStackFragment added into a non-stack container");
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ScreenStackFragment(Screen screen) {
        super(screen);
        Intrinsics.checkNotNullParameter(screen, "screenView");
    }

    public ScreenStackFragment() {
        throw new IllegalStateException("ScreenStack fragments should never be restored. Follow instructions from https://github.com/software-mansion/react-native-screens/issues/17#issuecomment-424704067 to properly configure your main activity.");
    }

    public void removeToolbar() {
        Toolbar toolbar2;
        AppBarLayout appBarLayout2 = this.appBarLayout;
        if (!(appBarLayout2 == null || (toolbar2 = this.toolbar) == null || toolbar2.getParent() != appBarLayout2)) {
            appBarLayout2.removeView(toolbar2);
        }
        this.toolbar = null;
    }

    public void setToolbar(Toolbar toolbar2) {
        Intrinsics.checkNotNullParameter(toolbar2, "toolbar");
        AppBarLayout appBarLayout2 = this.appBarLayout;
        if (appBarLayout2 != null) {
            appBarLayout2.addView(toolbar2);
        }
        AppBarLayout.LayoutParams layoutParams = new AppBarLayout.LayoutParams(-1, -2);
        layoutParams.setScrollFlags(0);
        toolbar2.setLayoutParams(layoutParams);
        this.toolbar = toolbar2;
    }

    public void setToolbarShadowHidden(boolean z) {
        if (this.isToolbarShadowHidden != z) {
            AppBarLayout appBarLayout2 = this.appBarLayout;
            if (appBarLayout2 != null) {
                appBarLayout2.setElevation(z ? 0.0f : PixelUtil.toPixelFromDIP(4.0f));
            }
            AppBarLayout appBarLayout3 = this.appBarLayout;
            if (appBarLayout3 != null) {
                appBarLayout3.setStateListAnimator((StateListAnimator) null);
            }
            this.isToolbarShadowHidden = z;
        }
    }

    public void setToolbarTranslucent(boolean z) {
        AppBarLayout.ScrollingViewBehavior scrollingViewBehavior;
        if (this.isToolbarTranslucent != z) {
            ViewGroup.LayoutParams layoutParams = getScreen().getLayoutParams();
            Intrinsics.checkNotNull(layoutParams, "null cannot be cast to non-null type androidx.coordinatorlayout.widget.CoordinatorLayout.LayoutParams");
            CoordinatorLayout.LayoutParams layoutParams2 = (CoordinatorLayout.LayoutParams) layoutParams;
            if (z) {
                scrollingViewBehavior = null;
            } else {
                scrollingViewBehavior = new AppBarLayout.ScrollingViewBehavior();
            }
            layoutParams2.setBehavior(scrollingViewBehavior);
            this.isToolbarTranslucent = z;
        }
    }

    public void onContainerUpdate() {
        super.onContainerUpdate();
        ScreenStackHeaderConfig headerConfig = getScreen().getHeaderConfig();
        if (headerConfig != null) {
            headerConfig.onUpdate();
        }
    }

    public void onViewAnimationEnd() {
        super.onViewAnimationEnd();
        notifyViewAppearTransitionEnd();
        getScreen().endRemovalTransition();
    }

    private final void notifyViewAppearTransitionEnd() {
        View view = getView();
        ViewParent parent = view != null ? view.getParent() : null;
        if (parent instanceof ScreenStack) {
            ((ScreenStack) parent).onViewAppearTransitionEnd();
        }
    }

    public final void dismissSelf$react_native_screens_release() {
        if (!isRemoving() || !isDetached()) {
            ThemedReactContext reactContext = getScreen().getReactContext();
            int surfaceId = UIManagerHelper.getSurfaceId((Context) reactContext);
            EventDispatcher eventDispatcherForReactTag = UIManagerHelper.getEventDispatcherForReactTag(reactContext, getScreen().getId());
            if (eventDispatcherForReactTag != null) {
                eventDispatcherForReactTag.dispatchEvent(new ScreenDismissedEvent(surfaceId, getScreen().getId()));
            }
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        CoordinatorLayout.Behavior behavior;
        AppBarLayout appBarLayout2;
        AppBarLayout appBarLayout3;
        AppBarLayout appBarLayout4;
        Intrinsics.checkNotNullParameter(layoutInflater, "inflater");
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext(...)");
        this.coordinatorLayout = new ScreensCoordinatorLayout(requireContext, this);
        Screen screen = getScreen();
        CoordinatorLayout.LayoutParams layoutParams = new CoordinatorLayout.LayoutParams(-1, -1);
        if (SheetUtilsKt.usesFormSheetPresentation(getScreen())) {
            behavior = createBottomSheetBehaviour();
        } else if (this.isToolbarTranslucent) {
            behavior = null;
        } else {
            behavior = new AppBarLayout.ScrollingViewBehavior();
        }
        layoutParams.setBehavior(behavior);
        screen.setLayoutParams(layoutParams);
        ScreensCoordinatorLayout screensCoordinatorLayout = this.coordinatorLayout;
        if (screensCoordinatorLayout == null) {
            Intrinsics.throwUninitializedPropertyAccessException("coordinatorLayout");
            screensCoordinatorLayout = null;
        }
        screensCoordinatorLayout.addView(ViewExtKt.recycle(getScreen()));
        if (!SheetUtilsKt.usesFormSheetPresentation(getScreen())) {
            Context context = getContext();
            if (context != null) {
                appBarLayout2 = new AppBarLayout(context);
                appBarLayout2.setBackgroundColor(0);
                appBarLayout2.setLayoutParams(new AppBarLayout.LayoutParams(-1, -2));
            } else {
                appBarLayout2 = null;
            }
            this.appBarLayout = appBarLayout2;
            ScreensCoordinatorLayout screensCoordinatorLayout2 = this.coordinatorLayout;
            if (screensCoordinatorLayout2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("coordinatorLayout");
                screensCoordinatorLayout2 = null;
            }
            screensCoordinatorLayout2.addView(this.appBarLayout);
            if (this.isToolbarShadowHidden && (appBarLayout4 = this.appBarLayout) != null) {
                appBarLayout4.setTargetElevation(0.0f);
            }
            Toolbar toolbar2 = this.toolbar;
            if (!(toolbar2 == null || (appBarLayout3 = this.appBarLayout) == null)) {
                appBarLayout3.addView(ViewExtKt.recycle(toolbar2));
            }
            setHasOptionsMenu(true);
        } else {
            getScreen().setClipToOutline(true);
            attachShapeToScreen(getScreen());
            getScreen().setElevation(getScreen().getSheetElevation());
            SheetDelegate requireSheetDelegate = requireSheetDelegate();
            BottomSheetBehavior<Screen> sheetBehavior = getScreen().getSheetBehavior();
            Intrinsics.checkNotNull(sheetBehavior);
            SheetDelegate.configureBottomSheetBehaviour$react_native_screens_release$default(requireSheetDelegate, sheetBehavior, (KeyboardState) null, 0, 6, (Object) null);
            DimmingViewManager requireDimmingDelegate = requireDimmingDelegate(true);
            Screen screen2 = getScreen();
            ScreensCoordinatorLayout screensCoordinatorLayout3 = this.coordinatorLayout;
            if (screensCoordinatorLayout3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("coordinatorLayout");
                screensCoordinatorLayout3 = null;
            }
            requireDimmingDelegate.onViewHierarchyCreated(screen2, screensCoordinatorLayout3);
            Screen screen3 = getScreen();
            BottomSheetBehavior<Screen> sheetBehavior2 = getScreen().getSheetBehavior();
            Intrinsics.checkNotNull(sheetBehavior2);
            requireDimmingDelegate.onBehaviourAttached(screen3, sheetBehavior2);
            ScreenContainer container = getScreen().getContainer();
            Intrinsics.checkNotNull(container);
            ScreensCoordinatorLayout screensCoordinatorLayout4 = this.coordinatorLayout;
            if (screensCoordinatorLayout4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("coordinatorLayout");
                screensCoordinatorLayout4 = null;
            }
            screensCoordinatorLayout4.measure(View.MeasureSpec.makeMeasureSpec(container.getWidth(), 1073741824), View.MeasureSpec.makeMeasureSpec(container.getHeight(), 1073741824));
            ScreensCoordinatorLayout screensCoordinatorLayout5 = this.coordinatorLayout;
            if (screensCoordinatorLayout5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("coordinatorLayout");
                screensCoordinatorLayout5 = null;
            }
            screensCoordinatorLayout5.layout(0, 0, container.getWidth(), container.getHeight());
        }
        ScreensCoordinatorLayout screensCoordinatorLayout6 = this.coordinatorLayout;
        if (screensCoordinatorLayout6 != null) {
            return screensCoordinatorLayout6;
        }
        Intrinsics.throwUninitializedPropertyAccessException("coordinatorLayout");
        return null;
    }

    public void onViewCreated(View view, Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v6, resolved type: android.animation.AnimatorSet$Builder} */
    /* JADX WARNING: type inference failed for: r2v0 */
    /* JADX WARNING: type inference failed for: r2v1, types: [android.view.View] */
    /* JADX WARNING: type inference failed for: r2v5 */
    /* JADX WARNING: type inference failed for: r2v7 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.animation.Animator onCreateAnimator(int r8, boolean r9, int r10) {
        /*
            r7 = this;
            r8 = 2
            r10 = 1
            r0 = 0
            com.swmansion.rnscreens.Screen r1 = r7.getScreen()
            boolean r1 = com.swmansion.rnscreens.bottomsheet.SheetUtilsKt.usesFormSheetPresentation(r1)
            r2 = 0
            if (r1 != 0) goto L_0x000f
            return r2
        L_0x000f:
            android.animation.AnimatorSet r1 = new android.animation.AnimatorSet
            r1.<init>()
            com.swmansion.rnscreens.bottomsheet.DimmingViewManager r3 = requireDimmingDelegate$default(r7, r0, r10, r2)
            r4 = 0
            if (r9 == 0) goto L_0x007e
            float r5 = r3.getMaxAlpha$react_native_screens_release()
            float[] r8 = new float[r8]
            r8[r0] = r4
            r8[r10] = r5
            android.animation.ValueAnimator r8 = android.animation.ValueAnimator.ofFloat(r8)
            com.swmansion.rnscreens.ScreenStackFragment$$ExternalSyntheticLambda0 r10 = new com.swmansion.rnscreens.ScreenStackFragment$$ExternalSyntheticLambda0
            r10.<init>(r3)
            r8.addUpdateListener(r10)
            com.swmansion.rnscreens.ScreenStackFragment$$ExternalSyntheticLambda1 r10 = new com.swmansion.rnscreens.ScreenStackFragment$$ExternalSyntheticLambda1
            r10.<init>(r7)
            com.swmansion.rnscreens.transition.ExternalBoundaryValuesEvaluator r0 = new com.swmansion.rnscreens.transition.ExternalBoundaryValuesEvaluator
            com.swmansion.rnscreens.ScreenStackFragment$$ExternalSyntheticLambda2 r5 = new com.swmansion.rnscreens.ScreenStackFragment$$ExternalSyntheticLambda2
            r5.<init>()
            r0.<init>(r10, r5)
            com.swmansion.rnscreens.Screen r10 = r7.getScreen()
            int r10 = r10.getHeight()
            float r10 = (float) r10
            java.lang.Float r10 = java.lang.Float.valueOf(r10)
            java.lang.Float r4 = java.lang.Float.valueOf(r4)
            java.lang.Object[] r10 = new java.lang.Object[]{r10, r4}
            android.animation.ValueAnimator r10 = android.animation.ValueAnimator.ofObject(r0, r10)
            com.swmansion.rnscreens.ScreenStackFragment$$ExternalSyntheticLambda3 r0 = new com.swmansion.rnscreens.ScreenStackFragment$$ExternalSyntheticLambda3
            r0.<init>(r7)
            r10.addUpdateListener(r0)
            android.animation.AnimatorSet$Builder r10 = r1.play(r10)
            com.swmansion.rnscreens.Screen r0 = r7.getScreen()
            com.swmansion.rnscreens.Screen r4 = r7.getScreen()
            int r4 = r4.getSheetInitialDetentIndex()
            boolean r0 = r3.willDimForDetentIndex(r0, r4)
            if (r0 == 0) goto L_0x0078
            r2 = r10
        L_0x0078:
            if (r2 == 0) goto L_0x00ca
            r2.with(r8)
            goto L_0x00ca
        L_0x007e:
            com.swmansion.rnscreens.bottomsheet.DimmingView r5 = r3.getDimmingView$react_native_screens_release()
            float r5 = r5.getAlpha()
            float[] r6 = new float[r8]
            r6[r0] = r5
            r6[r10] = r4
            android.animation.ValueAnimator r5 = android.animation.ValueAnimator.ofFloat(r6)
            com.swmansion.rnscreens.ScreenStackFragment$$ExternalSyntheticLambda4 r6 = new com.swmansion.rnscreens.ScreenStackFragment$$ExternalSyntheticLambda4
            r6.<init>(r3)
            r5.addUpdateListener(r6)
            com.swmansion.rnscreens.ScreenStackFragment$ScreensCoordinatorLayout r3 = r7.coordinatorLayout
            if (r3 != 0) goto L_0x00a2
            java.lang.String r3 = "coordinatorLayout"
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r3)
            goto L_0x00a3
        L_0x00a2:
            r2 = r3
        L_0x00a3:
            int r2 = r2.getBottom()
            com.swmansion.rnscreens.Screen r3 = r7.getScreen()
            int r3 = r3.getTop()
            int r2 = r2 - r3
            float r2 = (float) r2
            float[] r8 = new float[r8]
            r8[r0] = r4
            r8[r10] = r2
            android.animation.ValueAnimator r8 = android.animation.ValueAnimator.ofFloat(r8)
            com.swmansion.rnscreens.ScreenStackFragment$$ExternalSyntheticLambda5 r10 = new com.swmansion.rnscreens.ScreenStackFragment$$ExternalSyntheticLambda5
            r10.<init>(r7)
            r8.addUpdateListener(r10)
            android.animation.AnimatorSet$Builder r10 = r1.play(r5)
            r10.with(r8)
        L_0x00ca:
            com.swmansion.rnscreens.events.ScreenAnimationDelegate r8 = new com.swmansion.rnscreens.events.ScreenAnimationDelegate
            com.swmansion.rnscreens.events.ScreenEventEmitter r10 = new com.swmansion.rnscreens.events.ScreenEventEmitter
            com.swmansion.rnscreens.Screen r0 = r7.getScreen()
            r10.<init>(r0)
            if (r9 == 0) goto L_0x00da
            com.swmansion.rnscreens.events.ScreenAnimationDelegate$AnimationType r9 = com.swmansion.rnscreens.events.ScreenAnimationDelegate.AnimationType.ENTER
            goto L_0x00dc
        L_0x00da:
            com.swmansion.rnscreens.events.ScreenAnimationDelegate$AnimationType r9 = com.swmansion.rnscreens.events.ScreenAnimationDelegate.AnimationType.EXIT
        L_0x00dc:
            r8.<init>(r7, r10, r9)
            r1.addListener(r8)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.swmansion.rnscreens.ScreenStackFragment.onCreateAnimator(int, boolean, int):android.animation.Animator");
    }

    /* access modifiers changed from: private */
    public static final void onCreateAnimator$lambda$10$lambda$9(DimmingViewManager dimmingViewManager, ValueAnimator valueAnimator) {
        Intrinsics.checkNotNullParameter(valueAnimator, "anim");
        Object animatedValue = valueAnimator.getAnimatedValue();
        Float f = animatedValue instanceof Float ? (Float) animatedValue : null;
        if (f != null) {
            dimmingViewManager.getDimmingView$react_native_screens_release().setAlpha(f.floatValue());
        }
    }

    /* access modifiers changed from: private */
    public static final float onCreateAnimator$lambda$11(ScreenStackFragment screenStackFragment, Number number) {
        return (float) screenStackFragment.getScreen().getHeight();
    }

    /* access modifiers changed from: private */
    public static final Float onCreateAnimator$lambda$12(Number number) {
        return Float.valueOf(0.0f);
    }

    /* access modifiers changed from: private */
    public static final void onCreateAnimator$lambda$15$lambda$14(ScreenStackFragment screenStackFragment, ValueAnimator valueAnimator) {
        Intrinsics.checkNotNullParameter(valueAnimator, "anim");
        Object animatedValue = valueAnimator.getAnimatedValue();
        Float f = animatedValue instanceof Float ? (Float) animatedValue : null;
        if (f != null) {
            screenStackFragment.getScreen().setTranslationY(f.floatValue());
        }
    }

    /* access modifiers changed from: private */
    public static final void onCreateAnimator$lambda$19$lambda$18(DimmingViewManager dimmingViewManager, ValueAnimator valueAnimator) {
        Intrinsics.checkNotNullParameter(valueAnimator, "anim");
        Object animatedValue = valueAnimator.getAnimatedValue();
        Float f = animatedValue instanceof Float ? (Float) animatedValue : null;
        if (f != null) {
            dimmingViewManager.getDimmingView$react_native_screens_release().setAlpha(f.floatValue());
        }
    }

    /* access modifiers changed from: private */
    public static final void onCreateAnimator$lambda$22$lambda$21(ScreenStackFragment screenStackFragment, ValueAnimator valueAnimator) {
        Intrinsics.checkNotNullParameter(valueAnimator, "anim");
        Object animatedValue = valueAnimator.getAnimatedValue();
        Float f = animatedValue instanceof Float ? (Float) animatedValue : null;
        if (f != null) {
            screenStackFragment.getScreen().setTranslationY(f.floatValue());
        }
    }

    private final BottomSheetBehavior createBottomSheetBehaviour() {
        return new BottomSheetBehavior();
    }

    private final Integer resolveBackgroundColor(Screen screen) {
        Integer num;
        ColorStateList tintList;
        Drawable background = screen.getBackground();
        ColorDrawable colorDrawable = background instanceof ColorDrawable ? (ColorDrawable) background : null;
        if (colorDrawable != null) {
            num = Integer.valueOf(colorDrawable.getColor());
        } else {
            Drawable background2 = screen.getBackground();
            MaterialShapeDrawable materialShapeDrawable = background2 instanceof MaterialShapeDrawable ? (MaterialShapeDrawable) background2 : null;
            num = (materialShapeDrawable == null || (tintList = materialShapeDrawable.getTintList()) == null) ? null : Integer.valueOf(tintList.getDefaultColor());
        }
        if (num != null) {
            return num;
        }
        ScreenContentWrapper screenContentWrapper = screen.getContentWrapper().get();
        if (screenContentWrapper == null) {
            return null;
        }
        return ViewBackgroundUtilsKt.resolveBackgroundColor(screenContentWrapper);
    }

    private final void attachShapeToScreen(Screen screen) {
        float pixelFromDIP = PixelUtil.toPixelFromDIP(screen.getSheetCornerRadius());
        ShapeAppearanceModel.Builder builder = new ShapeAppearanceModel.Builder();
        int i = 0;
        builder.setTopLeftCorner(0, pixelFromDIP);
        builder.setTopRightCorner(0, pixelFromDIP);
        ShapeAppearanceModel build = builder.build();
        Intrinsics.checkNotNullExpressionValue(build, "build(...)");
        MaterialShapeDrawable materialShapeDrawable = new MaterialShapeDrawable(build);
        Integer resolveBackgroundColor = resolveBackgroundColor(screen);
        if (resolveBackgroundColor != null) {
            i = resolveBackgroundColor.intValue();
        }
        materialShapeDrawable.setTint(i);
        screen.setBackground(materialShapeDrawable);
    }

    public void onStart() {
        View view = this.lastFocusedChild;
        if (view != null) {
            view.requestFocus();
        }
        super.onStart();
    }

    public void onStop() {
        if (DeviceUtils.INSTANCE.isPlatformAndroidTV(getContext())) {
            this.lastFocusedChild = findLastFocusedChild();
        }
        super.onStop();
    }

    public void onPrepareOptionsMenu(Menu menu) {
        ScreenStackHeaderConfig headerConfig;
        Intrinsics.checkNotNullParameter(menu, "menu");
        if (!getScreen().isTransparent() || ((headerConfig = getScreen().getHeaderConfig()) != null && !headerConfig.isHeaderHidden())) {
            updateToolbarMenu(menu);
        }
        super.onPrepareOptionsMenu(menu);
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        Intrinsics.checkNotNullParameter(menu, "menu");
        Intrinsics.checkNotNullParameter(menuInflater, "inflater");
        updateToolbarMenu(menu);
        super.onCreateOptionsMenu(menu, menuInflater);
    }

    private final boolean shouldShowSearchBar() {
        ScreenStackHeaderConfig headerConfig = getScreen().getHeaderConfig();
        int configSubviewsCount = headerConfig != null ? headerConfig.getConfigSubviewsCount() : 0;
        if (headerConfig != null && configSubviewsCount > 0) {
            for (int i = 0; i < configSubviewsCount; i++) {
                if (headerConfig.getConfigSubview(i).getType() == ScreenStackHeaderSubview.Type.SEARCH_BAR) {
                    return true;
                }
            }
        }
        return false;
    }

    private final void updateToolbarMenu(Menu menu) {
        menu.clear();
        if (shouldShowSearchBar()) {
            Context context = getContext();
            if (this.searchView == null && context != null) {
                CustomSearchView customSearchView = new CustomSearchView(context, this);
                this.searchView = customSearchView;
                Function1 function1 = this.onSearchViewCreate;
                if (function1 != null) {
                    function1.invoke(customSearchView);
                }
            }
            MenuItem add = menu.add("");
            add.setShowAsAction(2);
            add.setActionView(this.searchView);
        }
    }

    private final View findLastFocusedChild() {
        for (ViewGroup screen = getScreen(); screen != null; screen = screen instanceof ViewGroup ? screen.getFocusedChild() : null) {
            if (screen.isFocused()) {
                return screen;
            }
        }
        return null;
    }

    public boolean canNavigateBack() {
        ScreenContainer container = getScreen().getContainer();
        if (!(container instanceof ScreenStack)) {
            throw new IllegalStateException("ScreenStackFragment added into a non-stack container");
        } else if (!Intrinsics.areEqual((Object) ((ScreenStack) container).getRootScreen(), (Object) getScreen())) {
            return true;
        } else {
            Fragment parentFragment = getParentFragment();
            if (parentFragment instanceof ScreenStackFragment) {
                return ((ScreenStackFragment) parentFragment).canNavigateBack();
            }
            return false;
        }
    }

    public void dismissFromContainer() {
        getScreenStack().dismiss(this);
    }

    static /* synthetic */ DimmingViewManager requireDimmingDelegate$default(ScreenStackFragment screenStackFragment, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        return screenStackFragment.requireDimmingDelegate(z);
    }

    private final DimmingViewManager requireDimmingDelegate(boolean z) {
        DimmingViewManager dimmingViewManager = this.dimmingDelegate;
        if (dimmingViewManager == null || z) {
            if (dimmingViewManager != null) {
                dimmingViewManager.invalidate(getScreen().getSheetBehavior());
            }
            this.dimmingDelegate = new DimmingViewManager(getScreen().getReactContext(), getScreen());
        }
        DimmingViewManager dimmingViewManager2 = this.dimmingDelegate;
        Intrinsics.checkNotNull(dimmingViewManager2);
        return dimmingViewManager2;
    }

    private final SheetDelegate requireSheetDelegate() {
        if (this.sheetDelegate == null) {
            this.sheetDelegate = new SheetDelegate(getScreen());
        }
        SheetDelegate sheetDelegate2 = this.sheetDelegate;
        Intrinsics.checkNotNull(sheetDelegate2);
        return sheetDelegate2;
    }

    private static final class ScreensCoordinatorLayout extends CoordinatorLayout implements ReactPointerEventsView {
        private final Animation.AnimationListener animationListener;
        /* access modifiers changed from: private */
        public final ScreenStackFragment fragment;
        private final ReactPointerEventsView pointerEventsImpl;

        public PointerEvents getPointerEvents() {
            return this.pointerEventsImpl.getPointerEvents();
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public ScreensCoordinatorLayout(Context context, ScreenStackFragment screenStackFragment, ReactPointerEventsView reactPointerEventsView) {
            super(context);
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(screenStackFragment, "fragment");
            Intrinsics.checkNotNullParameter(reactPointerEventsView, "pointerEventsImpl");
            this.fragment = screenStackFragment;
            this.pointerEventsImpl = reactPointerEventsView;
            this.animationListener = new ScreenStackFragment$ScreensCoordinatorLayout$animationListener$1(this);
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public ScreensCoordinatorLayout(Context context, ScreenStackFragment screenStackFragment) {
            this(context, screenStackFragment, new PointerEventsBoxNoneImpl());
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(screenStackFragment, "fragment");
        }

        public WindowInsets onApplyWindowInsets(WindowInsets windowInsets) {
            WindowInsets onApplyWindowInsets = super.onApplyWindowInsets(windowInsets);
            Intrinsics.checkNotNullExpressionValue(onApplyWindowInsets, "onApplyWindowInsets(...)");
            return onApplyWindowInsets;
        }

        public void startAnimation(Animation animation) {
            Intrinsics.checkNotNullParameter(animation, "animation");
            ScreensAnimation screensAnimation = new ScreensAnimation(this.fragment);
            screensAnimation.setDuration(animation.getDuration());
            if (!(animation instanceof AnimationSet) || this.fragment.isRemoving()) {
                AnimationSet animationSet = new AnimationSet(true);
                animationSet.addAnimation(animation);
                animationSet.addAnimation(screensAnimation);
                animationSet.setAnimationListener(this.animationListener);
                super.startAnimation(animationSet);
                return;
            }
            AnimationSet animationSet2 = (AnimationSet) animation;
            animationSet2.addAnimation(screensAnimation);
            animationSet2.setAnimationListener(this.animationListener);
            super.startAnimation(animationSet2);
        }

        public void clearFocus() {
            if (getVisibility() != 4) {
                super.clearFocus();
            }
        }

        /* access modifiers changed from: protected */
        public void onLayout(boolean z, int i, int i2, int i3, int i4) {
            super.onLayout(z, i, i2, i3, i4);
            if (SheetUtilsKt.usesFormSheetPresentation(this.fragment.getScreen())) {
                this.fragment.getScreen().onBottomSheetBehaviorDidLayout$react_native_screens_release(z);
            }
        }
    }

    private static final class ScreensAnimation extends Animation {
        private final ScreenFragment mFragment;

        public ScreensAnimation(ScreenFragment screenFragment) {
            Intrinsics.checkNotNullParameter(screenFragment, "mFragment");
            this.mFragment = screenFragment;
        }

        /* access modifiers changed from: protected */
        public void applyTransformation(float f, Transformation transformation) {
            Intrinsics.checkNotNullParameter(transformation, "t");
            super.applyTransformation(f, transformation);
            ScreenFragment screenFragment = this.mFragment;
            screenFragment.dispatchTransitionProgressEvent(f, !screenFragment.isResumed());
        }
    }
}
