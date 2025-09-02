package com.swmansion.rnscreens.events;

import android.animation.Animator;
import com.swmansion.rnscreens.ScreenStackFragmentWrapper;
import kotlin.NoWhenBranchMatchedException;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class ScreenAnimationDelegate implements Animator.AnimatorListener {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private final AnimationType animationType;
    private LifecycleState currentState = LifecycleState.INITIALIZED;
    private final ScreenEventEmitter eventEmitter;
    private final ScreenStackFragmentWrapper wrapper;

    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        /* JADX WARNING: Can't wrap try/catch for region: R(13:0|(2:1|2)|3|5|6|(2:7|8)|9|11|12|13|14|15|17) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x0033 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0019 */
        static {
            /*
                com.swmansion.rnscreens.events.ScreenAnimationDelegate$LifecycleState[] r0 = com.swmansion.rnscreens.events.ScreenAnimationDelegate.LifecycleState.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                r1 = 1
                com.swmansion.rnscreens.events.ScreenAnimationDelegate$LifecycleState r2 = com.swmansion.rnscreens.events.ScreenAnimationDelegate.LifecycleState.INITIALIZED     // Catch:{ NoSuchFieldError -> 0x0010 }
                int r2 = r2.ordinal()     // Catch:{ NoSuchFieldError -> 0x0010 }
                r0[r2] = r1     // Catch:{ NoSuchFieldError -> 0x0010 }
            L_0x0010:
                r2 = 2
                com.swmansion.rnscreens.events.ScreenAnimationDelegate$LifecycleState r3 = com.swmansion.rnscreens.events.ScreenAnimationDelegate.LifecycleState.START_DISPATCHED     // Catch:{ NoSuchFieldError -> 0x0019 }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x0019 }
                r0[r3] = r2     // Catch:{ NoSuchFieldError -> 0x0019 }
            L_0x0019:
                com.swmansion.rnscreens.events.ScreenAnimationDelegate$LifecycleState r3 = com.swmansion.rnscreens.events.ScreenAnimationDelegate.LifecycleState.END_DISPATCHED     // Catch:{ NoSuchFieldError -> 0x0022 }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x0022 }
                r4 = 3
                r0[r3] = r4     // Catch:{ NoSuchFieldError -> 0x0022 }
            L_0x0022:
                $EnumSwitchMapping$0 = r0
                com.swmansion.rnscreens.events.ScreenAnimationDelegate$AnimationType[] r0 = com.swmansion.rnscreens.events.ScreenAnimationDelegate.AnimationType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                com.swmansion.rnscreens.events.ScreenAnimationDelegate$AnimationType r3 = com.swmansion.rnscreens.events.ScreenAnimationDelegate.AnimationType.ENTER     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r0[r3] = r1     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                com.swmansion.rnscreens.events.ScreenAnimationDelegate$AnimationType r1 = com.swmansion.rnscreens.events.ScreenAnimationDelegate.AnimationType.EXIT     // Catch:{ NoSuchFieldError -> 0x003b }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003b }
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003b }
            L_0x003b:
                $EnumSwitchMapping$1 = r0
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.swmansion.rnscreens.events.ScreenAnimationDelegate.WhenMappings.<clinit>():void");
        }
    }

    public void onAnimationCancel(Animator animator) {
        Intrinsics.checkNotNullParameter(animator, "animation");
    }

    public void onAnimationRepeat(Animator animator) {
        Intrinsics.checkNotNullParameter(animator, "animation");
    }

    public ScreenAnimationDelegate(ScreenStackFragmentWrapper screenStackFragmentWrapper, ScreenEventEmitter screenEventEmitter, AnimationType animationType2) {
        Intrinsics.checkNotNullParameter(screenStackFragmentWrapper, "wrapper");
        Intrinsics.checkNotNullParameter(animationType2, "animationType");
        this.wrapper = screenStackFragmentWrapper;
        this.eventEmitter = screenEventEmitter;
        this.animationType = animationType2;
    }

    public enum AnimationType {
        ENTER,
        EXIT;

        static {
            AnimationType[] $values;
            $ENTRIES = EnumEntriesKt.enumEntries($values);
        }
    }

    private final void progressState() {
        LifecycleState lifecycleState;
        int i = WhenMappings.$EnumSwitchMapping$0[this.currentState.ordinal()];
        if (i == 1) {
            lifecycleState = LifecycleState.START_DISPATCHED;
        } else if (i == 2) {
            lifecycleState = LifecycleState.END_DISPATCHED;
        } else if (i == 3) {
            lifecycleState = LifecycleState.END_DISPATCHED;
        } else {
            throw new NoWhenBranchMatchedException();
        }
        this.currentState = lifecycleState;
    }

    public void onAnimationStart(Animator animator) {
        Intrinsics.checkNotNullParameter(animator, "animation");
        if (this.currentState == LifecycleState.INITIALIZED) {
            progressState();
            int i = WhenMappings.$EnumSwitchMapping$1[this.animationType.ordinal()];
            boolean z = true;
            if (i == 1) {
                ScreenEventEmitter screenEventEmitter = this.eventEmitter;
                if (screenEventEmitter != null) {
                    screenEventEmitter.dispatchOnWillAppear();
                }
            } else if (i == 2) {
                ScreenEventEmitter screenEventEmitter2 = this.eventEmitter;
                if (screenEventEmitter2 != null) {
                    screenEventEmitter2.dispatchOnWillDisappear();
                }
            } else {
                throw new NoWhenBranchMatchedException();
            }
            if (this.animationType != AnimationType.EXIT) {
                z = false;
            }
            ScreenEventEmitter screenEventEmitter3 = this.eventEmitter;
            if (screenEventEmitter3 != null) {
                screenEventEmitter3.dispatchTransitionProgress(0.0f, z, z);
            }
        }
    }

    public void onAnimationEnd(Animator animator) {
        Intrinsics.checkNotNullParameter(animator, "animation");
        if (this.currentState == LifecycleState.START_DISPATCHED) {
            progressState();
            animator.removeListener(this);
            int i = WhenMappings.$EnumSwitchMapping$1[this.animationType.ordinal()];
            boolean z = true;
            if (i == 1) {
                ScreenEventEmitter screenEventEmitter = this.eventEmitter;
                if (screenEventEmitter != null) {
                    screenEventEmitter.dispatchOnAppear();
                }
            } else if (i == 2) {
                ScreenEventEmitter screenEventEmitter2 = this.eventEmitter;
                if (screenEventEmitter2 != null) {
                    screenEventEmitter2.dispatchOnDisappear();
                }
            } else {
                throw new NoWhenBranchMatchedException();
            }
            if (this.animationType != AnimationType.EXIT) {
                z = false;
            }
            ScreenEventEmitter screenEventEmitter3 = this.eventEmitter;
            if (screenEventEmitter3 != null) {
                screenEventEmitter3.dispatchTransitionProgress(1.0f, z, z);
            }
            this.wrapper.getScreen().endRemovalTransition();
        }
    }

    private enum LifecycleState {
        INITIALIZED,
        START_DISPATCHED,
        END_DISPATCHED;

        static {
            LifecycleState[] $values;
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
