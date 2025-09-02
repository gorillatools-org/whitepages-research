package com.swmansion.rnscreens;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.uimanager.UIManagerHelper;
import com.facebook.react.uimanager.events.Event;
import com.facebook.react.uimanager.events.EventDispatcher;
import com.swmansion.rnscreens.events.HeaderBackButtonClickedEvent;
import com.swmansion.rnscreens.events.ScreenAppearEvent;
import com.swmansion.rnscreens.events.ScreenDisappearEvent;
import com.swmansion.rnscreens.events.ScreenDismissedEvent;
import com.swmansion.rnscreens.events.ScreenTransitionProgressEvent;
import com.swmansion.rnscreens.events.ScreenWillAppearEvent;
import com.swmansion.rnscreens.events.ScreenWillDisappearEvent;
import com.swmansion.rnscreens.ext.ViewExtKt;
import java.util.ArrayList;
import java.util.List;
import kotlin.NoWhenBranchMatchedException;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public class ScreenFragment extends Fragment implements ScreenFragmentWrapper {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private boolean canDispatchAppear = true;
    private boolean canDispatchWillAppear = true;
    private final List childScreenContainers = new ArrayList();
    private boolean isTransitioning;
    public Screen screen;
    private boolean shouldUpdateOnResume;
    private float transitionProgress = -1.0f;

    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        /* JADX WARNING: Can't wrap try/catch for region: R(11:0|1|2|3|4|5|6|7|8|9|11) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0010 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x0019 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0022 */
        static {
            /*
                com.swmansion.rnscreens.ScreenFragment$ScreenLifecycleEvent[] r0 = com.swmansion.rnscreens.ScreenFragment.ScreenLifecycleEvent.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                com.swmansion.rnscreens.ScreenFragment$ScreenLifecycleEvent r1 = com.swmansion.rnscreens.ScreenFragment.ScreenLifecycleEvent.WILL_APPEAR     // Catch:{ NoSuchFieldError -> 0x0010 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0010 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0010 }
            L_0x0010:
                com.swmansion.rnscreens.ScreenFragment$ScreenLifecycleEvent r1 = com.swmansion.rnscreens.ScreenFragment.ScreenLifecycleEvent.DID_APPEAR     // Catch:{ NoSuchFieldError -> 0x0019 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0019 }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0019 }
            L_0x0019:
                com.swmansion.rnscreens.ScreenFragment$ScreenLifecycleEvent r1 = com.swmansion.rnscreens.ScreenFragment.ScreenLifecycleEvent.WILL_DISAPPEAR     // Catch:{ NoSuchFieldError -> 0x0022 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0022 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0022 }
            L_0x0022:
                com.swmansion.rnscreens.ScreenFragment$ScreenLifecycleEvent r1 = com.swmansion.rnscreens.ScreenFragment.ScreenLifecycleEvent.DID_DISAPPEAR     // Catch:{ NoSuchFieldError -> 0x002b }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002b }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002b }
            L_0x002b:
                $EnumSwitchMapping$0 = r0
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.swmansion.rnscreens.ScreenFragment.WhenMappings.<clinit>():void");
        }
    }

    public Fragment getFragment() {
        return this;
    }

    public enum ScreenLifecycleEvent {
        DID_APPEAR,
        WILL_APPEAR,
        DID_DISAPPEAR,
        WILL_DISAPPEAR;

        static {
            ScreenLifecycleEvent[] $values;
            $ENTRIES = EnumEntriesKt.enumEntries($values);
        }
    }

    public Screen getScreen() {
        Screen screen2 = this.screen;
        if (screen2 != null) {
            return screen2;
        }
        Intrinsics.throwUninitializedPropertyAccessException("screen");
        return null;
    }

    public void setScreen(Screen screen2) {
        Intrinsics.checkNotNullParameter(screen2, "<set-?>");
        this.screen = screen2;
    }

    public List getChildScreenContainers() {
        return this.childScreenContainers;
    }

    public ScreenFragment() {
        throw new IllegalStateException("Screen fragments should never be restored. Follow instructions from https://github.com/software-mansion/react-native-screens/issues/17#issuecomment-424704067 to properly configure your main activity.");
    }

    public ScreenFragment(Screen screen2) {
        Intrinsics.checkNotNullParameter(screen2, "screenView");
        setScreen(screen2);
    }

    public void onResume() {
        super.onResume();
        if (this.shouldUpdateOnResume) {
            this.shouldUpdateOnResume = false;
            ScreenWindowTraits.INSTANCE.trySetWindowTraits$react_native_screens_release(getScreen(), tryGetActivity(), tryGetContext());
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkNotNullParameter(layoutInflater, "inflater");
        getScreen().setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
        Context context = getContext();
        if (context == null) {
            return null;
        }
        ScreensFrameLayout screensFrameLayout = new ScreensFrameLayout(context);
        screensFrameLayout.addView(ViewExtKt.recycle(getScreen()));
        return screensFrameLayout;
    }

    private static final class ScreensFrameLayout extends FrameLayout {
        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public ScreensFrameLayout(Context context) {
            super(context);
            Intrinsics.checkNotNullParameter(context, "context");
        }

        public void clearFocus() {
            if (getVisibility() != 4) {
                super.clearFocus();
            }
        }
    }

    public void onContainerUpdate() {
        updateWindowTraits();
    }

    private final void updateWindowTraits() {
        FragmentActivity activity = getActivity();
        if (activity == null) {
            this.shouldUpdateOnResume = true;
        } else {
            ScreenWindowTraits.INSTANCE.trySetWindowTraits$react_native_screens_release(getScreen(), activity, tryGetContext());
        }
    }

    public Activity tryGetActivity() {
        Fragment fragment;
        FragmentActivity activity;
        FragmentActivity activity2 = getActivity();
        if (activity2 != null) {
            return activity2;
        }
        Context context = getScreen().getContext();
        if (context instanceof ReactContext) {
            ReactContext reactContext = (ReactContext) context;
            if (reactContext.getCurrentActivity() != null) {
                return reactContext.getCurrentActivity();
            }
        }
        for (ViewParent container = getScreen().getContainer(); container != null; container = container.getParent()) {
            if ((container instanceof Screen) && (fragment = ((Screen) container).getFragment()) != null && (activity = fragment.getActivity()) != null) {
                return activity;
            }
        }
        return null;
    }

    public ReactContext tryGetContext() {
        if (getContext() instanceof ReactContext) {
            Context context = getContext();
            Intrinsics.checkNotNull(context, "null cannot be cast to non-null type com.facebook.react.bridge.ReactContext");
            return (ReactContext) context;
        } else if (getScreen().getContext() instanceof ReactContext) {
            Context context2 = getScreen().getContext();
            Intrinsics.checkNotNull(context2, "null cannot be cast to non-null type com.facebook.react.bridge.ReactContext");
            return (ReactContext) context2;
        } else {
            for (ViewParent container = getScreen().getContainer(); container != null; container = container.getParent()) {
                if (container instanceof Screen) {
                    Screen screen2 = (Screen) container;
                    if (screen2.getContext() instanceof ReactContext) {
                        Context context3 = screen2.getContext();
                        Intrinsics.checkNotNull(context3, "null cannot be cast to non-null type com.facebook.react.bridge.ReactContext");
                        return (ReactContext) context3;
                    }
                }
            }
            return null;
        }
    }

    public boolean canDispatchLifecycleEvent(ScreenLifecycleEvent screenLifecycleEvent) {
        Intrinsics.checkNotNullParameter(screenLifecycleEvent, "event");
        int i = WhenMappings.$EnumSwitchMapping$0[screenLifecycleEvent.ordinal()];
        if (i == 1) {
            return this.canDispatchWillAppear;
        }
        if (i == 2) {
            return this.canDispatchAppear;
        }
        if (i != 3) {
            if (i != 4) {
                throw new NoWhenBranchMatchedException();
            } else if (!this.canDispatchAppear) {
                return true;
            }
        } else if (!this.canDispatchWillAppear) {
            return true;
        }
        return false;
    }

    public void updateLastEventDispatched(ScreenLifecycleEvent screenLifecycleEvent) {
        Intrinsics.checkNotNullParameter(screenLifecycleEvent, "event");
        int i = WhenMappings.$EnumSwitchMapping$0[screenLifecycleEvent.ordinal()];
        if (i == 1) {
            this.canDispatchWillAppear = false;
        } else if (i == 2) {
            this.canDispatchAppear = false;
        } else if (i == 3) {
            this.canDispatchWillAppear = true;
        } else if (i == 4) {
            this.canDispatchAppear = true;
        } else {
            throw new NoWhenBranchMatchedException();
        }
    }

    private final void dispatchOnWillAppear() {
        dispatchLifecycleEvent(ScreenLifecycleEvent.WILL_APPEAR, this);
        dispatchTransitionProgressEvent(0.0f, false);
    }

    private final void dispatchOnAppear() {
        dispatchLifecycleEvent(ScreenLifecycleEvent.DID_APPEAR, this);
        dispatchTransitionProgressEvent(1.0f, false);
    }

    private final void dispatchOnWillDisappear() {
        dispatchLifecycleEvent(ScreenLifecycleEvent.WILL_DISAPPEAR, this);
        dispatchTransitionProgressEvent(0.0f, true);
    }

    private final void dispatchOnDisappear() {
        dispatchLifecycleEvent(ScreenLifecycleEvent.DID_DISAPPEAR, this);
        dispatchTransitionProgressEvent(1.0f, true);
    }

    public void dispatchLifecycleEvent(ScreenLifecycleEvent screenLifecycleEvent, ScreenFragmentWrapper screenFragmentWrapper) {
        Event event;
        Intrinsics.checkNotNullParameter(screenLifecycleEvent, "event");
        Intrinsics.checkNotNullParameter(screenFragmentWrapper, "fragmentWrapper");
        Fragment fragment = screenFragmentWrapper.getFragment();
        if (fragment instanceof ScreenStackFragment) {
            ScreenStackFragment screenStackFragment = (ScreenStackFragment) fragment;
            if (screenStackFragment.canDispatchLifecycleEvent(screenLifecycleEvent)) {
                Screen screen2 = screenStackFragment.getScreen();
                screenFragmentWrapper.updateLastEventDispatched(screenLifecycleEvent);
                int surfaceId = UIManagerHelper.getSurfaceId((View) screen2);
                int i = WhenMappings.$EnumSwitchMapping$0[screenLifecycleEvent.ordinal()];
                if (i == 1) {
                    event = new ScreenWillAppearEvent(surfaceId, screen2.getId());
                } else if (i == 2) {
                    event = new ScreenAppearEvent(surfaceId, screen2.getId());
                } else if (i == 3) {
                    event = new ScreenWillDisappearEvent(surfaceId, screen2.getId());
                } else if (i == 4) {
                    event = new ScreenDisappearEvent(surfaceId, screen2.getId());
                } else {
                    throw new NoWhenBranchMatchedException();
                }
                Context context = getScreen().getContext();
                Intrinsics.checkNotNull(context, "null cannot be cast to non-null type com.facebook.react.bridge.ReactContext");
                EventDispatcher eventDispatcherForReactTag = UIManagerHelper.getEventDispatcherForReactTag((ReactContext) context, getScreen().getId());
                if (eventDispatcherForReactTag != null) {
                    eventDispatcherForReactTag.dispatchEvent(event);
                }
                screenFragmentWrapper.dispatchLifecycleEventInChildContainers(screenLifecycleEvent);
            }
        }
    }

    public void dispatchLifecycleEventInChildContainers(ScreenLifecycleEvent screenLifecycleEvent) {
        ScreenFragmentWrapper fragmentWrapper;
        Intrinsics.checkNotNullParameter(screenLifecycleEvent, "event");
        ArrayList<ScreenContainer> arrayList = new ArrayList<>();
        for (Object next : getChildScreenContainers()) {
            if (((ScreenContainer) next).getScreenCount() > 0) {
                arrayList.add(next);
            }
        }
        for (ScreenContainer topScreen : arrayList) {
            Screen topScreen2 = topScreen.getTopScreen();
            if (!(topScreen2 == null || (fragmentWrapper = topScreen2.getFragmentWrapper()) == null)) {
                dispatchLifecycleEvent(screenLifecycleEvent, fragmentWrapper);
            }
        }
    }

    public void dispatchHeaderBackButtonClickedEvent() {
        Context context = getScreen().getContext();
        Intrinsics.checkNotNull(context, "null cannot be cast to non-null type com.facebook.react.bridge.ReactContext");
        ReactContext reactContext = (ReactContext) context;
        int surfaceId = UIManagerHelper.getSurfaceId((Context) reactContext);
        EventDispatcher eventDispatcherForReactTag = UIManagerHelper.getEventDispatcherForReactTag(reactContext, getScreen().getId());
        if (eventDispatcherForReactTag != null) {
            eventDispatcherForReactTag.dispatchEvent(new HeaderBackButtonClickedEvent(surfaceId, getScreen().getId()));
        }
    }

    public void dispatchTransitionProgressEvent(float f, boolean z) {
        if ((this instanceof ScreenStackFragment) && this.transitionProgress != f) {
            float max = Math.max(0.0f, Math.min(1.0f, f));
            this.transitionProgress = max;
            short coalescingKey = Companion.getCoalescingKey(max);
            ScreenStackFragment screenStackFragment = (ScreenStackFragment) this;
            ScreenContainer container = screenStackFragment.getScreen().getContainer();
            boolean goingForward = container instanceof ScreenStack ? ((ScreenStack) container).getGoingForward() : false;
            Context context = screenStackFragment.getScreen().getContext();
            Intrinsics.checkNotNull(context, "null cannot be cast to non-null type com.facebook.react.bridge.ReactContext");
            ReactContext reactContext = (ReactContext) context;
            EventDispatcher eventDispatcherForReactTag = UIManagerHelper.getEventDispatcherForReactTag(reactContext, screenStackFragment.getScreen().getId());
            if (eventDispatcherForReactTag != null) {
                eventDispatcherForReactTag.dispatchEvent(new ScreenTransitionProgressEvent(UIManagerHelper.getSurfaceId((Context) reactContext), screenStackFragment.getScreen().getId(), this.transitionProgress, z, goingForward, coalescingKey));
            }
        }
    }

    public void addChildScreenContainer(ScreenContainer screenContainer) {
        Intrinsics.checkNotNullParameter(screenContainer, "container");
        getChildScreenContainers().add(screenContainer);
    }

    public void removeChildScreenContainer(ScreenContainer screenContainer) {
        Intrinsics.checkNotNullParameter(screenContainer, "container");
        getChildScreenContainers().remove(screenContainer);
    }

    public void onViewAnimationStart() {
        dispatchViewAnimationEvent(false);
    }

    public void onViewAnimationEnd() {
        dispatchViewAnimationEvent(true);
    }

    private final void dispatchViewAnimationEvent(boolean z) {
        this.isTransitioning = !z;
        Fragment parentFragment = getParentFragment();
        if (parentFragment != null && (!(parentFragment instanceof ScreenFragment) || ((ScreenFragment) parentFragment).isTransitioning)) {
            return;
        }
        if (isResumed()) {
            UiThreadUtil.runOnUiThread(new ScreenFragment$$ExternalSyntheticLambda0(z, this));
        } else if (z) {
            dispatchOnDisappear();
        } else {
            dispatchOnWillDisappear();
        }
    }

    /* access modifiers changed from: private */
    public static final void dispatchViewAnimationEvent$lambda$8(boolean z, ScreenFragment screenFragment) {
        if (z) {
            screenFragment.dispatchOnAppear();
        } else {
            screenFragment.dispatchOnWillAppear();
        }
    }

    public void onDestroy() {
        super.onDestroy();
        ScreenContainer container = getScreen().getContainer();
        if (container == null || !container.hasScreen(getScreen().getFragmentWrapper())) {
            Context context = getScreen().getContext();
            if (context instanceof ReactContext) {
                int surfaceId = UIManagerHelper.getSurfaceId(context);
                EventDispatcher eventDispatcherForReactTag = UIManagerHelper.getEventDispatcherForReactTag((ReactContext) context, getScreen().getId());
                if (eventDispatcherForReactTag != null) {
                    eventDispatcherForReactTag.dispatchEvent(new ScreenDismissedEvent(surfaceId, getScreen().getId()));
                }
            }
        }
        getChildScreenContainers().clear();
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final short getCoalescingKey(float f) {
            return (short) (f == 0.0f ? 1 : f == 1.0f ? 2 : 3);
        }

        private Companion() {
        }
    }
}
