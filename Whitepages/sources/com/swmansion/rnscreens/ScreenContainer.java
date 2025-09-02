package com.swmansion.rnscreens;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.inputmethod.InputMethodManager;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.facebook.react.ReactRootView;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.modules.core.ChoreographerCompat;
import com.facebook.react.modules.core.ReactChoreographer;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.UIManagerHelper;
import com.facebook.react.uimanager.events.EventDispatcher;
import com.swmansion.rnscreens.Screen;
import com.swmansion.rnscreens.events.ScreenDismissedEvent;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

public class ScreenContainer extends ViewGroup {
    protected FragmentManager fragmentManager;
    private boolean isAttached;
    /* access modifiers changed from: private */
    public boolean isLayoutEnqueued;
    private final ChoreographerCompat.FrameCallback layoutCallback = new ScreenContainer$layoutCallback$1(this);
    private boolean needsUpdate;
    private ScreenFragmentWrapper parentScreenWrapper;
    protected final ArrayList screenWrappers = new ArrayList();

    public ScreenContainer(Context context) {
        super(context);
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int childCount = getChildCount();
        for (int i5 = 0; i5 < childCount; i5++) {
            getChildAt(i5).layout(0, 0, getWidth(), getHeight());
        }
    }

    public void removeView(View view) {
        Intrinsics.checkNotNullParameter(view, "view");
        if (view == getFocusedChild()) {
            Object systemService = getContext().getSystemService("input_method");
            Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.view.inputmethod.InputMethodManager");
            ((InputMethodManager) systemService).hideSoftInputFromWindow(getWindowToken(), 2);
        }
        super.removeView(view);
    }

    public void requestLayout() {
        super.requestLayout();
        if (!this.isLayoutEnqueued && this.layoutCallback != null) {
            this.isLayoutEnqueued = true;
            ReactChoreographer.Companion.getInstance().postFrameCallback(ReactChoreographer.CallbackType.NATIVE_ANIMATED_MODULE, this.layoutCallback);
        }
    }

    public final void onChildUpdate() {
        performUpdatesNow();
    }

    /* access modifiers changed from: protected */
    public ScreenFragmentWrapper adapt(Screen screen) {
        Intrinsics.checkNotNullParameter(screen, "screen");
        return new ScreenFragment(screen);
    }

    public final void addScreen(Screen screen, int i) {
        Intrinsics.checkNotNullParameter(screen, "screen");
        ScreenFragmentWrapper adapt = adapt(screen);
        screen.setFragmentWrapper(adapt);
        this.screenWrappers.add(i, adapt);
        screen.setContainer(this);
        onScreenChanged();
    }

    public void removeScreenAt(int i) {
        ((ScreenFragmentWrapper) this.screenWrappers.get(i)).getScreen().setContainer((ScreenContainer) null);
        this.screenWrappers.remove(i);
        onScreenChanged();
    }

    public void removeAllScreens() {
        Iterator it = this.screenWrappers.iterator();
        Intrinsics.checkNotNullExpressionValue(it, "iterator(...)");
        while (it.hasNext()) {
            Object next = it.next();
            Intrinsics.checkNotNullExpressionValue(next, "next(...)");
            ((ScreenFragmentWrapper) next).getScreen().setContainer((ScreenContainer) null);
        }
        this.screenWrappers.clear();
        onScreenChanged();
    }

    public final int getScreenCount() {
        return this.screenWrappers.size();
    }

    public final Screen getScreenAt(int i) {
        return ((ScreenFragmentWrapper) this.screenWrappers.get(i)).getScreen();
    }

    public final ScreenFragmentWrapper getScreenFragmentWrapperAt(int i) {
        Object obj = this.screenWrappers.get(i);
        Intrinsics.checkNotNullExpressionValue(obj, "get(...)");
        return (ScreenFragmentWrapper) obj;
    }

    public Screen getTopScreen() {
        Object obj;
        Iterator it = this.screenWrappers.iterator();
        while (true) {
            if (!it.hasNext()) {
                obj = null;
                break;
            }
            obj = it.next();
            if (getActivityState((ScreenFragmentWrapper) obj) == Screen.ActivityState.ON_TOP) {
                break;
            }
        }
        ScreenFragmentWrapper screenFragmentWrapper = (ScreenFragmentWrapper) obj;
        if (screenFragmentWrapper != null) {
            return screenFragmentWrapper.getScreen();
        }
        return null;
    }

    private final void setFragmentManager(FragmentManager fragmentManager2) {
        this.fragmentManager = fragmentManager2;
        performUpdatesNow();
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x003b  */
    /* JADX WARNING: Removed duplicated region for block: B:7:0x0015  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final androidx.fragment.app.FragmentManager findFragmentManagerForReactRootView(com.facebook.react.ReactRootView r4) {
        /*
            r3 = this;
            android.content.Context r0 = r4.getContext()
        L_0x0004:
            boolean r1 = r0 instanceof androidx.fragment.app.FragmentActivity
            if (r1 != 0) goto L_0x0013
            boolean r2 = r0 instanceof android.content.ContextWrapper
            if (r2 == 0) goto L_0x0013
            android.content.ContextWrapper r0 = (android.content.ContextWrapper) r0
            android.content.Context r0 = r0.getBaseContext()
            goto L_0x0004
        L_0x0013:
            if (r1 == 0) goto L_0x003b
            androidx.fragment.app.FragmentActivity r0 = (androidx.fragment.app.FragmentActivity) r0
            androidx.fragment.app.FragmentManager r1 = r0.getSupportFragmentManager()
            java.util.List r1 = r1.getFragments()
            boolean r1 = r1.isEmpty()
            if (r1 == 0) goto L_0x002d
            androidx.fragment.app.FragmentManager r4 = r0.getSupportFragmentManager()
            kotlin.jvm.internal.Intrinsics.checkNotNull(r4)
            goto L_0x003a
        L_0x002d:
            androidx.fragment.app.Fragment r4 = androidx.fragment.app.FragmentManager.findFragment(r4)     // Catch:{ IllegalStateException -> 0x0036 }
            androidx.fragment.app.FragmentManager r4 = r4.getChildFragmentManager()     // Catch:{ IllegalStateException -> 0x0036 }
            goto L_0x003a
        L_0x0036:
            androidx.fragment.app.FragmentManager r4 = r0.getSupportFragmentManager()
        L_0x003a:
            return r4
        L_0x003b:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r0 = "In order to use RNScreens components your app's activity need to extend ReactActivity"
            r4.<init>(r0)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.swmansion.rnscreens.ScreenContainer.findFragmentManagerForReactRootView(com.facebook.react.ReactRootView):androidx.fragment.app.FragmentManager");
    }

    private final void setupFragmentManager() {
        boolean z;
        Unit unit;
        ViewParent viewParent = this;
        while (true) {
            z = viewParent instanceof ReactRootView;
            if (!z && !(viewParent instanceof Screen) && viewParent.getParent() != null) {
                viewParent = viewParent.getParent();
            }
        }
        if (viewParent instanceof Screen) {
            ScreenFragmentWrapper fragmentWrapper = ((Screen) viewParent).getFragmentWrapper();
            if (fragmentWrapper != null) {
                this.parentScreenWrapper = fragmentWrapper;
                fragmentWrapper.addChildScreenContainer(this);
                FragmentManager childFragmentManager = fragmentWrapper.getFragment().getChildFragmentManager();
                Intrinsics.checkNotNullExpressionValue(childFragmentManager, "getChildFragmentManager(...)");
                setFragmentManager(childFragmentManager);
                unit = Unit.INSTANCE;
            } else {
                unit = null;
            }
            if (unit == null) {
                throw new IllegalStateException("Parent Screen does not have its Fragment attached");
            }
        } else if (z) {
            setFragmentManager(findFragmentManagerForReactRootView((ReactRootView) viewParent));
        } else {
            throw new IllegalStateException("ScreenContainer is not attached under ReactRootView");
        }
    }

    /* access modifiers changed from: protected */
    public final FragmentTransaction createTransaction() {
        FragmentManager fragmentManager2 = this.fragmentManager;
        if (fragmentManager2 != null) {
            FragmentTransaction reorderingAllowed = fragmentManager2.beginTransaction().setReorderingAllowed(true);
            Intrinsics.checkNotNullExpressionValue(reorderingAllowed, "setReorderingAllowed(...)");
            return reorderingAllowed;
        }
        throw new IllegalArgumentException("fragment manager is null when creating transaction");
    }

    private final void attachScreen(FragmentTransaction fragmentTransaction, Fragment fragment) {
        fragmentTransaction.add(getId(), fragment);
    }

    public final void attachBelowTop() {
        if (this.screenWrappers.size() >= 2) {
            FragmentTransaction createTransaction = createTransaction();
            Screen topScreen = getTopScreen();
            Intrinsics.checkNotNull(topScreen, "null cannot be cast to non-null type com.swmansion.rnscreens.Screen");
            Fragment fragment = topScreen.getFragment();
            Intrinsics.checkNotNull(fragment, "null cannot be cast to non-null type androidx.fragment.app.Fragment");
            detachScreen(createTransaction, fragment);
            ArrayList arrayList = this.screenWrappers;
            attachScreen(createTransaction, ((ScreenFragmentWrapper) arrayList.get(arrayList.size() - 2)).getFragment());
            Fragment fragment2 = topScreen.getFragment();
            Intrinsics.checkNotNull(fragment2, "null cannot be cast to non-null type androidx.fragment.app.Fragment");
            attachScreen(createTransaction, fragment2);
            createTransaction.commitNowAllowingStateLoss();
            return;
        }
        throw new RuntimeException("[RNScreens] Unable to run transition for less than 2 screens.");
    }

    public final void detachBelowTop() {
        if (this.screenWrappers.size() >= 2) {
            FragmentTransaction createTransaction = createTransaction();
            ArrayList arrayList = this.screenWrappers;
            detachScreen(createTransaction, ((ScreenFragmentWrapper) arrayList.get(arrayList.size() - 2)).getFragment());
            createTransaction.commitNowAllowingStateLoss();
            return;
        }
        throw new RuntimeException("[RNScreens] Unable to run transition for less than 2 screens.");
    }

    public final void notifyTopDetached() {
        Screen topScreen = getTopScreen();
        Intrinsics.checkNotNull(topScreen, "null cannot be cast to non-null type com.swmansion.rnscreens.Screen");
        if (getContext() instanceof ReactContext) {
            int surfaceId = UIManagerHelper.getSurfaceId(getContext());
            Context context = getContext();
            Intrinsics.checkNotNull(context, "null cannot be cast to non-null type com.facebook.react.bridge.ReactContext");
            EventDispatcher eventDispatcherForReactTag = UIManagerHelper.getEventDispatcherForReactTag((ReactContext) context, topScreen.getId());
            if (eventDispatcherForReactTag != null) {
                eventDispatcherForReactTag.dispatchEvent(new ScreenDismissedEvent(surfaceId, topScreen.getId()));
            }
        }
    }

    private final void detachScreen(FragmentTransaction fragmentTransaction, Fragment fragment) {
        fragmentTransaction.remove(fragment);
    }

    private final Screen.ActivityState getActivityState(ScreenFragmentWrapper screenFragmentWrapper) {
        return screenFragmentWrapper.getScreen().getActivityState();
    }

    public boolean hasScreen(ScreenFragmentWrapper screenFragmentWrapper) {
        return CollectionsKt.contains(this.screenWrappers, screenFragmentWrapper);
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.isAttached = true;
        setupFragmentManager();
    }

    private final void removeMyFragments(FragmentManager fragmentManager2) {
        FragmentTransaction beginTransaction = fragmentManager2.beginTransaction();
        Intrinsics.checkNotNullExpressionValue(beginTransaction, "beginTransaction(...)");
        boolean z = false;
        for (Fragment fragment : fragmentManager2.getFragments()) {
            if ((fragment instanceof ScreenFragment) && ((ScreenFragment) fragment).getScreen().getContainer() == this) {
                beginTransaction.remove(fragment);
                z = true;
            }
        }
        if (z) {
            beginTransaction.commitNowAllowingStateLoss();
        }
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        FragmentManager fragmentManager2 = this.fragmentManager;
        if (fragmentManager2 != null && !fragmentManager2.isDestroyed()) {
            removeMyFragments(fragmentManager2);
            fragmentManager2.executePendingTransactions();
        }
        ScreenFragmentWrapper screenFragmentWrapper = this.parentScreenWrapper;
        if (screenFragmentWrapper != null) {
            screenFragmentWrapper.removeChildScreenContainer(this);
        }
        this.parentScreenWrapper = null;
        super.onDetachedFromWindow();
        this.isAttached = false;
        int childCount = getChildCount();
        while (true) {
            childCount--;
            if (-1 < childCount) {
                removeViewAt(childCount);
            } else {
                return;
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        int childCount = getChildCount();
        for (int i3 = 0; i3 < childCount; i3++) {
            getChildAt(i3).measure(i, i2);
        }
    }

    private final void onScreenChanged() {
        this.needsUpdate = true;
        Context context = getContext();
        Intrinsics.checkNotNull(context, "null cannot be cast to non-null type com.facebook.react.uimanager.ThemedReactContext");
        ((ThemedReactContext) context).getReactApplicationContext().runOnUiQueueThread(new ScreenContainer$$ExternalSyntheticLambda0(this));
    }

    /* access modifiers changed from: private */
    public static final void onScreenChanged$lambda$7(ScreenContainer screenContainer) {
        screenContainer.performUpdates();
    }

    /* access modifiers changed from: protected */
    public final void performUpdatesNow() {
        this.needsUpdate = true;
        performUpdates();
    }

    public final void performUpdates() {
        FragmentManager fragmentManager2;
        if (this.needsUpdate && this.isAttached && (fragmentManager2 = this.fragmentManager) != null) {
            if (fragmentManager2 == null || !fragmentManager2.isDestroyed()) {
                this.needsUpdate = false;
                onUpdate();
                notifyContainerUpdate();
            }
        }
    }

    public void onUpdate() {
        FragmentTransaction createTransaction = createTransaction();
        FragmentManager fragmentManager2 = this.fragmentManager;
        if (fragmentManager2 != null) {
            HashSet hashSet = new HashSet(fragmentManager2.getFragments());
            Iterator it = this.screenWrappers.iterator();
            Intrinsics.checkNotNullExpressionValue(it, "iterator(...)");
            while (it.hasNext()) {
                Object next = it.next();
                Intrinsics.checkNotNullExpressionValue(next, "next(...)");
                ScreenFragmentWrapper screenFragmentWrapper = (ScreenFragmentWrapper) next;
                if (getActivityState(screenFragmentWrapper) == Screen.ActivityState.INACTIVE && screenFragmentWrapper.getFragment().isAdded()) {
                    detachScreen(createTransaction, screenFragmentWrapper.getFragment());
                }
                hashSet.remove(screenFragmentWrapper.getFragment());
            }
            boolean z = false;
            if (!hashSet.isEmpty()) {
                for (Fragment fragment : (Fragment[]) hashSet.toArray(new Fragment[0])) {
                    if ((fragment instanceof ScreenFragment) && ((ScreenFragment) fragment).getScreen().getContainer() == null) {
                        detachScreen(createTransaction, fragment);
                    }
                }
            }
            boolean z2 = getTopScreen() == null;
            ArrayList arrayList = new ArrayList();
            Iterator it2 = this.screenWrappers.iterator();
            Intrinsics.checkNotNullExpressionValue(it2, "iterator(...)");
            while (it2.hasNext()) {
                Object next2 = it2.next();
                Intrinsics.checkNotNullExpressionValue(next2, "next(...)");
                ScreenFragmentWrapper screenFragmentWrapper2 = (ScreenFragmentWrapper) next2;
                Screen.ActivityState activityState = getActivityState(screenFragmentWrapper2);
                Screen.ActivityState activityState2 = Screen.ActivityState.INACTIVE;
                if (activityState != activityState2 && !screenFragmentWrapper2.getFragment().isAdded()) {
                    attachScreen(createTransaction, screenFragmentWrapper2.getFragment());
                    z = true;
                } else if (activityState != activityState2 && z) {
                    detachScreen(createTransaction, screenFragmentWrapper2.getFragment());
                    arrayList.add(screenFragmentWrapper2);
                }
                screenFragmentWrapper2.getScreen().setTransitioning(z2);
            }
            Iterator it3 = arrayList.iterator();
            Intrinsics.checkNotNullExpressionValue(it3, "iterator(...)");
            while (it3.hasNext()) {
                Object next3 = it3.next();
                Intrinsics.checkNotNullExpressionValue(next3, "next(...)");
                attachScreen(createTransaction, ((ScreenFragmentWrapper) next3).getFragment());
            }
            createTransaction.commitNowAllowingStateLoss();
            return;
        }
        throw new IllegalArgumentException("fragment manager is null when performing update in ScreenContainer");
    }

    /* access modifiers changed from: protected */
    public void notifyContainerUpdate() {
        ScreenFragmentWrapper fragmentWrapper;
        Screen topScreen = getTopScreen();
        if (topScreen != null && (fragmentWrapper = topScreen.getFragmentWrapper()) != null) {
            fragmentWrapper.onContainerUpdate();
        }
    }
}
