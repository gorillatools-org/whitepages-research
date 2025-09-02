package com.swmansion.rnscreens;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Build;
import android.view.View;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.uimanager.UIManagerHelper;
import com.facebook.react.uimanager.events.EventDispatcher;
import com.swmansion.rnscreens.Screen;
import com.swmansion.rnscreens.events.StackFinishTransitioningEvent;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref$ObjectRef;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlin.ranges.RangesKt;

public final class ScreenStack extends ScreenContainer {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private ChildDrawingOrderStrategy childDrawingOrderStrategy;
    private final Set dismissedWrappers = new HashSet();
    private final List drawingOpPool = new ArrayList();
    private List drawingOps = new ArrayList();
    private boolean goingForward;
    private int previousChildrenCount;
    private boolean removalTransitionStarted;
    private final ArrayList stack = new ArrayList();
    private ScreenStackFragmentWrapper topScreenWrapper;

    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[Screen.StackPresentation.values().length];
            try {
                iArr[Screen.StackPresentation.FORM_SHEET.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public ScreenStack(Context context) {
        super(context);
    }

    public final boolean getGoingForward() {
        return this.goingForward;
    }

    public final void setGoingForward(boolean z) {
        this.goingForward = z;
    }

    public final void dismiss(ScreenStackFragmentWrapper screenStackFragmentWrapper) {
        Intrinsics.checkNotNullParameter(screenStackFragmentWrapper, "screenFragment");
        this.dismissedWrappers.add(screenStackFragmentWrapper);
        performUpdatesNow();
    }

    public Screen getTopScreen() {
        ScreenStackFragmentWrapper screenStackFragmentWrapper = this.topScreenWrapper;
        if (screenStackFragmentWrapper != null) {
            return screenStackFragmentWrapper.getScreen();
        }
        return null;
    }

    public final ArrayList<ScreenStackFragmentWrapper> getFragments() {
        return this.stack;
    }

    public final Screen getRootScreen() {
        Object obj;
        Screen screen;
        Iterator it = this.screenWrappers.iterator();
        while (true) {
            if (!it.hasNext()) {
                obj = null;
                break;
            }
            obj = it.next();
            if (!CollectionsKt.contains(this.dismissedWrappers, (ScreenFragmentWrapper) obj)) {
                break;
            }
        }
        ScreenFragmentWrapper screenFragmentWrapper = (ScreenFragmentWrapper) obj;
        if (screenFragmentWrapper != null && (screen = screenFragmentWrapper.getScreen()) != null) {
            return screen;
        }
        throw new IllegalStateException("[RNScreens] Stack has no root screen set");
    }

    /* access modifiers changed from: protected */
    public ScreenStackFragmentWrapper adapt(Screen screen) {
        Intrinsics.checkNotNullParameter(screen, "screen");
        if (WhenMappings.$EnumSwitchMapping$0[screen.getStackPresentation().ordinal()] == 1) {
            return new ScreenStackFragment(screen);
        }
        return new ScreenStackFragment(screen);
    }

    public void startViewTransition(View view) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.startViewTransition(view);
        ChildDrawingOrderStrategy childDrawingOrderStrategy2 = this.childDrawingOrderStrategy;
        if (childDrawingOrderStrategy2 != null) {
            childDrawingOrderStrategy2.enable();
        }
        this.removalTransitionStarted = true;
    }

    public void endViewTransition(View view) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.endViewTransition(view);
        ChildDrawingOrderStrategy childDrawingOrderStrategy2 = this.childDrawingOrderStrategy;
        if (childDrawingOrderStrategy2 != null) {
            childDrawingOrderStrategy2.disable();
        }
        if (this.removalTransitionStarted) {
            this.removalTransitionStarted = false;
            dispatchOnFinishTransitioning();
        }
    }

    public final void onViewAppearTransitionEnd() {
        if (!this.removalTransitionStarted) {
            dispatchOnFinishTransitioning();
        }
    }

    private final void dispatchOnFinishTransitioning() {
        int surfaceId = UIManagerHelper.getSurfaceId((View) this);
        Context context = getContext();
        Intrinsics.checkNotNull(context, "null cannot be cast to non-null type com.facebook.react.bridge.ReactContext");
        EventDispatcher eventDispatcherForReactTag = UIManagerHelper.getEventDispatcherForReactTag((ReactContext) context, getId());
        if (eventDispatcherForReactTag != null) {
            eventDispatcherForReactTag.dispatchEvent(new StackFinishTransitioningEvent(surfaceId, getId()));
        }
    }

    public void removeScreenAt(int i) {
        TypeIntrinsics.asMutableCollection(this.dismissedWrappers).remove(getScreenFragmentWrapperAt(i));
        super.removeScreenAt(i);
    }

    public void removeAllScreens() {
        this.dismissedWrappers.clear();
        super.removeAllScreens();
    }

    public boolean hasScreen(ScreenFragmentWrapper screenFragmentWrapper) {
        return super.hasScreen(screenFragmentWrapper) && !CollectionsKt.contains(this.dismissedWrappers, screenFragmentWrapper);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v1, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v5, resolved type: com.swmansion.rnscreens.ScreenStackFragmentWrapper} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x00d2  */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x00da  */
    /* JADX WARNING: Removed duplicated region for block: B:70:0x013d  */
    /* JADX WARNING: Removed duplicated region for block: B:74:0x0159 A[LOOP:0: B:72:0x0153->B:74:0x0159, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:78:0x0189 A[LOOP:1: B:76:0x0183->B:78:0x0189, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:83:0x01a7  */
    /* JADX WARNING: Removed duplicated region for block: B:87:0x01df  */
    /* JADX WARNING: Removed duplicated region for block: B:97:0x021d  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onUpdate() {
        /*
            r11 = this;
            kotlin.jvm.internal.Ref$ObjectRef r0 = new kotlin.jvm.internal.Ref$ObjectRef
            r0.<init>()
            kotlin.jvm.internal.Ref$ObjectRef r1 = new kotlin.jvm.internal.Ref$ObjectRef
            r1.<init>()
            r2 = 0
            r11.childDrawingOrderStrategy = r2
            java.util.ArrayList r3 = r11.screenWrappers
            java.util.List r3 = kotlin.collections.CollectionsKt.asReversedMutable(r3)
            java.lang.Iterable r3 = (java.lang.Iterable) r3
            kotlin.sequences.Sequence r3 = kotlin.collections.CollectionsKt.asSequence(r3)
            com.swmansion.rnscreens.ScreenStack$$ExternalSyntheticLambda0 r4 = new com.swmansion.rnscreens.ScreenStack$$ExternalSyntheticLambda0
            r4.<init>(r11)
            kotlin.sequences.Sequence r3 = kotlin.sequences.SequencesKt.filter(r3, r4)
            java.lang.Object r4 = kotlin.sequences.SequencesKt.firstOrNull(r3)
            r0.element = r4
            com.swmansion.rnscreens.ScreenStack$$ExternalSyntheticLambda1 r4 = new com.swmansion.rnscreens.ScreenStack$$ExternalSyntheticLambda1
            r4.<init>()
            kotlin.sequences.Sequence r3 = kotlin.sequences.SequencesKt.dropWhile(r3, r4)
            java.lang.Object r3 = kotlin.sequences.SequencesKt.firstOrNull(r3)
            com.swmansion.rnscreens.ScreenFragmentWrapper r3 = (com.swmansion.rnscreens.ScreenFragmentWrapper) r3
            if (r3 == 0) goto L_0x003d
            java.lang.Object r4 = r0.element
            if (r3 != r4) goto L_0x003e
        L_0x003d:
            r3 = r2
        L_0x003e:
            r1.element = r3
            java.util.ArrayList r3 = r11.stack
            java.lang.Object r4 = r0.element
            boolean r3 = kotlin.collections.CollectionsKt.contains(r3, r4)
            java.lang.Object r4 = r0.element
            com.swmansion.rnscreens.ScreenStackFragmentWrapper r5 = r11.topScreenWrapper
            r6 = 0
            r7 = 1
            if (r4 == r5) goto L_0x0052
            r8 = r7
            goto L_0x0053
        L_0x0052:
            r8 = r6
        L_0x0053:
            if (r4 == 0) goto L_0x00a3
            if (r3 != 0) goto L_0x00a3
            if (r5 == 0) goto L_0x009d
            if (r5 == 0) goto L_0x0065
            java.util.ArrayList r4 = r11.screenWrappers
            boolean r4 = r4.contains(r5)
            if (r4 != r7) goto L_0x0065
            r4 = r7
            goto L_0x0066
        L_0x0065:
            r4 = r6
        L_0x0066:
            java.lang.Object r5 = r0.element
            com.swmansion.rnscreens.ScreenFragmentWrapper r5 = (com.swmansion.rnscreens.ScreenFragmentWrapper) r5
            com.swmansion.rnscreens.Screen r5 = r5.getScreen()
            com.swmansion.rnscreens.Screen$ReplaceAnimation r5 = r5.getReplaceAnimation()
            com.swmansion.rnscreens.Screen$ReplaceAnimation r8 = com.swmansion.rnscreens.Screen.ReplaceAnimation.PUSH
            if (r5 != r8) goto L_0x0078
            r5 = r7
            goto L_0x0079
        L_0x0078:
            r5 = r6
        L_0x0079:
            if (r4 != 0) goto L_0x0080
            if (r5 == 0) goto L_0x007e
            goto L_0x0080
        L_0x007e:
            r4 = r6
            goto L_0x0081
        L_0x0080:
            r4 = r7
        L_0x0081:
            if (r4 == 0) goto L_0x0090
            java.lang.Object r5 = r0.element
            com.swmansion.rnscreens.ScreenFragmentWrapper r5 = (com.swmansion.rnscreens.ScreenFragmentWrapper) r5
            com.swmansion.rnscreens.Screen r5 = r5.getScreen()
        L_0x008b:
            com.swmansion.rnscreens.Screen$StackAnimation r5 = r5.getStackAnimation()
            goto L_0x00bc
        L_0x0090:
            com.swmansion.rnscreens.ScreenStackFragmentWrapper r5 = r11.topScreenWrapper
            if (r5 == 0) goto L_0x009b
            com.swmansion.rnscreens.Screen r5 = r5.getScreen()
            if (r5 == 0) goto L_0x009b
            goto L_0x008b
        L_0x009b:
            r5 = r2
            goto L_0x00bc
        L_0x009d:
            com.swmansion.rnscreens.Screen$StackAnimation r5 = com.swmansion.rnscreens.Screen.StackAnimation.NONE
            r11.goingForward = r7
        L_0x00a1:
            r4 = r7
            goto L_0x00bc
        L_0x00a3:
            if (r4 == 0) goto L_0x00ba
            if (r5 == 0) goto L_0x00ba
            if (r8 == 0) goto L_0x00ba
            if (r5 == 0) goto L_0x00b7
            com.swmansion.rnscreens.Screen r4 = r5.getScreen()
            if (r4 == 0) goto L_0x00b7
            com.swmansion.rnscreens.Screen$StackAnimation r4 = r4.getStackAnimation()
            r5 = r4
            goto L_0x00b8
        L_0x00b7:
            r5 = r2
        L_0x00b8:
            r4 = r6
            goto L_0x00bc
        L_0x00ba:
            r5 = r2
            goto L_0x00a1
        L_0x00bc:
            r11.goingForward = r4
            if (r4 == 0) goto L_0x00da
            java.lang.Object r8 = r0.element
            if (r8 == 0) goto L_0x00da
            com.swmansion.rnscreens.ScreenStack$Companion r9 = Companion
            com.swmansion.rnscreens.ScreenFragmentWrapper r8 = (com.swmansion.rnscreens.ScreenFragmentWrapper) r8
            boolean r8 = r9.needsDrawReordering(r8, r5)
            if (r8 == 0) goto L_0x00da
            java.lang.Object r8 = r1.element
            if (r8 != 0) goto L_0x00da
            com.swmansion.rnscreens.SwapLastTwo r3 = new com.swmansion.rnscreens.SwapLastTwo
            r3.<init>()
            r11.childDrawingOrderStrategy = r3
            goto L_0x0137
        L_0x00da:
            java.lang.Object r8 = r0.element
            if (r8 == 0) goto L_0x0137
            if (r3 == 0) goto L_0x0137
            com.swmansion.rnscreens.ScreenStackFragmentWrapper r3 = r11.topScreenWrapper
            if (r3 == 0) goto L_0x0137
            com.swmansion.rnscreens.Screen r3 = r3.getScreen()
            if (r3 == 0) goto L_0x0137
            boolean r3 = r3.isTransparent()
            if (r3 != r7) goto L_0x0137
            java.lang.Object r3 = r0.element
            com.swmansion.rnscreens.ScreenFragmentWrapper r3 = (com.swmansion.rnscreens.ScreenFragmentWrapper) r3
            com.swmansion.rnscreens.Screen r3 = r3.getScreen()
            boolean r3 = r3.isTransparent()
            if (r3 != 0) goto L_0x0137
            java.util.ArrayList r3 = r11.stack
            java.util.List r3 = kotlin.collections.CollectionsKt.asReversedMutable(r3)
            java.lang.Iterable r3 = (java.lang.Iterable) r3
            kotlin.sequences.Sequence r3 = kotlin.collections.CollectionsKt.asSequence(r3)
            com.swmansion.rnscreens.ScreenStack$$ExternalSyntheticLambda2 r8 = new com.swmansion.rnscreens.ScreenStack$$ExternalSyntheticLambda2
            r8.<init>(r0)
            kotlin.sequences.Sequence r3 = kotlin.sequences.SequencesKt.takeWhile(r3, r8)
            int r3 = kotlin.sequences.SequencesKt.count(r3)
            if (r3 <= r7) goto L_0x0137
            com.swmansion.rnscreens.ReverseOrderInRange r8 = new com.swmansion.rnscreens.ReverseOrderInRange
            kotlin.ranges.IntRange r9 = new kotlin.ranges.IntRange
            java.util.ArrayList r10 = r11.stack
            int r10 = kotlin.collections.CollectionsKt.getLastIndex(r10)
            int r10 = r10 - r3
            int r10 = r10 + r7
            int r3 = java.lang.Math.max(r10, r6)
            java.util.ArrayList r6 = r11.stack
            int r6 = kotlin.collections.CollectionsKt.getLastIndex(r6)
            r9.<init>(r3, r6)
            r8.<init>(r9)
            r11.childDrawingOrderStrategy = r8
        L_0x0137:
            androidx.fragment.app.FragmentTransaction r3 = r11.createTransaction()
            if (r5 == 0) goto L_0x0140
            com.swmansion.rnscreens.utils.FragmentTransactionKtKt.setTweenAnimations(r3, r5, r4)
        L_0x0140:
            java.util.ArrayList r4 = r11.stack
            kotlin.sequences.Sequence r4 = kotlin.collections.CollectionsKt.asSequence(r4)
            com.swmansion.rnscreens.ScreenStack$$ExternalSyntheticLambda3 r5 = new com.swmansion.rnscreens.ScreenStack$$ExternalSyntheticLambda3
            r5.<init>(r11)
            kotlin.sequences.Sequence r4 = kotlin.sequences.SequencesKt.filter(r4, r5)
            java.util.Iterator r4 = r4.iterator()
        L_0x0153:
            boolean r5 = r4.hasNext()
            if (r5 == 0) goto L_0x0167
            java.lang.Object r5 = r4.next()
            com.swmansion.rnscreens.ScreenStackFragmentWrapper r5 = (com.swmansion.rnscreens.ScreenStackFragmentWrapper) r5
            androidx.fragment.app.Fragment r5 = r5.getFragment()
            r3.remove(r5)
            goto L_0x0153
        L_0x0167:
            java.util.ArrayList r4 = r11.screenWrappers
            kotlin.sequences.Sequence r4 = kotlin.collections.CollectionsKt.asSequence(r4)
            com.swmansion.rnscreens.ScreenStack$$ExternalSyntheticLambda4 r5 = new com.swmansion.rnscreens.ScreenStack$$ExternalSyntheticLambda4
            r5.<init>(r1)
            kotlin.sequences.Sequence r4 = kotlin.sequences.SequencesKt.takeWhile(r4, r5)
            com.swmansion.rnscreens.ScreenStack$$ExternalSyntheticLambda5 r5 = new com.swmansion.rnscreens.ScreenStack$$ExternalSyntheticLambda5
            r5.<init>(r0, r11)
            kotlin.sequences.Sequence r4 = kotlin.sequences.SequencesKt.filter(r4, r5)
            java.util.Iterator r4 = r4.iterator()
        L_0x0183:
            boolean r5 = r4.hasNext()
            if (r5 == 0) goto L_0x0197
            java.lang.Object r5 = r4.next()
            com.swmansion.rnscreens.ScreenFragmentWrapper r5 = (com.swmansion.rnscreens.ScreenFragmentWrapper) r5
            androidx.fragment.app.Fragment r5 = r5.getFragment()
            r3.remove(r5)
            goto L_0x0183
        L_0x0197:
            java.lang.Object r4 = r1.element
            if (r4 == 0) goto L_0x01df
            com.swmansion.rnscreens.ScreenFragmentWrapper r4 = (com.swmansion.rnscreens.ScreenFragmentWrapper) r4
            androidx.fragment.app.Fragment r4 = r4.getFragment()
            boolean r4 = r4.isAdded()
            if (r4 != 0) goto L_0x01df
            java.lang.Object r4 = r0.element
            com.swmansion.rnscreens.ScreenFragmentWrapper r4 = (com.swmansion.rnscreens.ScreenFragmentWrapper) r4
            java.util.ArrayList r5 = r11.screenWrappers
            kotlin.sequences.Sequence r5 = kotlin.collections.CollectionsKt.asSequence(r5)
            com.swmansion.rnscreens.ScreenStack$$ExternalSyntheticLambda6 r6 = new com.swmansion.rnscreens.ScreenStack$$ExternalSyntheticLambda6
            r6.<init>(r1)
            kotlin.sequences.Sequence r5 = kotlin.sequences.SequencesKt.dropWhile(r5, r6)
            java.util.Iterator r5 = r5.iterator()
        L_0x01be:
            boolean r6 = r5.hasNext()
            if (r6 == 0) goto L_0x0217
            java.lang.Object r6 = r5.next()
            com.swmansion.rnscreens.ScreenFragmentWrapper r6 = (com.swmansion.rnscreens.ScreenFragmentWrapper) r6
            int r7 = r11.getId()
            androidx.fragment.app.Fragment r6 = r6.getFragment()
            androidx.fragment.app.FragmentTransaction r6 = r3.add((int) r7, (androidx.fragment.app.Fragment) r6)
            com.swmansion.rnscreens.ScreenStack$$ExternalSyntheticLambda7 r7 = new com.swmansion.rnscreens.ScreenStack$$ExternalSyntheticLambda7
            r7.<init>(r4)
            r6.runOnCommit(r7)
            goto L_0x01be
        L_0x01df:
            java.lang.Object r4 = r0.element
            if (r4 == 0) goto L_0x0217
            com.swmansion.rnscreens.ScreenFragmentWrapper r4 = (com.swmansion.rnscreens.ScreenFragmentWrapper) r4
            androidx.fragment.app.Fragment r4 = r4.getFragment()
            boolean r4 = r4.isAdded()
            if (r4 != 0) goto L_0x0217
            java.lang.Object r4 = r0.element
            com.swmansion.rnscreens.ScreenFragmentWrapper r4 = (com.swmansion.rnscreens.ScreenFragmentWrapper) r4
            com.swmansion.rnscreens.Screen r4 = r4.getScreen()
            boolean r4 = com.swmansion.rnscreens.bottomsheet.SheetUtilsKt.isSheetFitToContents(r4)
            if (r4 == 0) goto L_0x0208
            java.lang.Object r4 = r0.element
            com.swmansion.rnscreens.ScreenFragmentWrapper r4 = (com.swmansion.rnscreens.ScreenFragmentWrapper) r4
            androidx.fragment.app.Fragment r4 = r4.getFragment()
            r4.postponeEnterTransition()
        L_0x0208:
            int r4 = r11.getId()
            java.lang.Object r5 = r0.element
            com.swmansion.rnscreens.ScreenFragmentWrapper r5 = (com.swmansion.rnscreens.ScreenFragmentWrapper) r5
            androidx.fragment.app.Fragment r5 = r5.getFragment()
            r3.add((int) r4, (androidx.fragment.app.Fragment) r5)
        L_0x0217:
            java.lang.Object r0 = r0.element
            boolean r4 = r0 instanceof com.swmansion.rnscreens.ScreenStackFragmentWrapper
            if (r4 == 0) goto L_0x0220
            r2 = r0
            com.swmansion.rnscreens.ScreenStackFragmentWrapper r2 = (com.swmansion.rnscreens.ScreenStackFragmentWrapper) r2
        L_0x0220:
            r11.topScreenWrapper = r2
            java.util.ArrayList r0 = r11.stack
            r0.clear()
            java.util.ArrayList r0 = r11.stack
            java.util.ArrayList r2 = r11.screenWrappers
            kotlin.sequences.Sequence r2 = kotlin.collections.CollectionsKt.asSequence(r2)
            com.swmansion.rnscreens.ScreenStack$$ExternalSyntheticLambda8 r4 = new com.swmansion.rnscreens.ScreenStack$$ExternalSyntheticLambda8
            r4.<init>()
            kotlin.sequences.Sequence r2 = kotlin.sequences.SequencesKt.map(r2, r4)
            kotlin.collections.CollectionsKt.addAll((java.util.Collection) r0, (kotlin.sequences.Sequence) r2)
            java.lang.Object r0 = r1.element
            com.swmansion.rnscreens.ScreenFragmentWrapper r0 = (com.swmansion.rnscreens.ScreenFragmentWrapper) r0
            r11.turnOffA11yUnderTransparentScreen(r0)
            r3.commitNowAllowingStateLoss()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.swmansion.rnscreens.ScreenStack.onUpdate():void");
    }

    /* access modifiers changed from: private */
    public static final boolean onUpdate$lambda$1(ScreenStack screenStack, ScreenFragmentWrapper screenFragmentWrapper) {
        Intrinsics.checkNotNullParameter(screenFragmentWrapper, "it");
        return !CollectionsKt.contains(screenStack.dismissedWrappers, screenFragmentWrapper) && screenFragmentWrapper.getScreen().getActivityState() != Screen.ActivityState.INACTIVE;
    }

    /* access modifiers changed from: private */
    public static final boolean onUpdate$lambda$2(ScreenFragmentWrapper screenFragmentWrapper) {
        Intrinsics.checkNotNullParameter(screenFragmentWrapper, "it");
        return screenFragmentWrapper.getScreen().isTransparent();
    }

    /* access modifiers changed from: private */
    public static final boolean onUpdate$lambda$5(Ref$ObjectRef ref$ObjectRef, ScreenStackFragmentWrapper screenStackFragmentWrapper) {
        Intrinsics.checkNotNullParameter(screenStackFragmentWrapper, "it");
        return screenStackFragmentWrapper != ref$ObjectRef.element && screenStackFragmentWrapper.getScreen().isTransparent();
    }

    /* access modifiers changed from: private */
    public static final boolean onUpdate$lambda$15$lambda$6(ScreenStack screenStack, ScreenStackFragmentWrapper screenStackFragmentWrapper) {
        Intrinsics.checkNotNullParameter(screenStackFragmentWrapper, "wrapper");
        return !screenStack.screenWrappers.contains(screenStackFragmentWrapper) || screenStack.dismissedWrappers.contains(screenStackFragmentWrapper);
    }

    /* access modifiers changed from: private */
    public static final boolean onUpdate$lambda$15$lambda$8(Ref$ObjectRef ref$ObjectRef, ScreenFragmentWrapper screenFragmentWrapper) {
        Intrinsics.checkNotNullParameter(screenFragmentWrapper, "it");
        return screenFragmentWrapper != ref$ObjectRef.element;
    }

    /* access modifiers changed from: private */
    public static final boolean onUpdate$lambda$15$lambda$9(Ref$ObjectRef ref$ObjectRef, ScreenStack screenStack, ScreenFragmentWrapper screenFragmentWrapper) {
        Intrinsics.checkNotNullParameter(screenFragmentWrapper, "it");
        return (screenFragmentWrapper != ref$ObjectRef.element && !CollectionsKt.contains(screenStack.dismissedWrappers, screenFragmentWrapper)) || screenFragmentWrapper.getScreen().getActivityState() == Screen.ActivityState.INACTIVE;
    }

    /* access modifiers changed from: private */
    public static final boolean onUpdate$lambda$15$lambda$11(Ref$ObjectRef ref$ObjectRef, ScreenFragmentWrapper screenFragmentWrapper) {
        Intrinsics.checkNotNullParameter(screenFragmentWrapper, "it");
        return screenFragmentWrapper != ref$ObjectRef.element;
    }

    /* access modifiers changed from: private */
    public static final void onUpdate$lambda$15$lambda$13$lambda$12(ScreenFragmentWrapper screenFragmentWrapper) {
        Screen screen;
        if (screenFragmentWrapper != null && (screen = screenFragmentWrapper.getScreen()) != null) {
            screen.bringToFront();
        }
    }

    /* access modifiers changed from: private */
    public static final ScreenStackFragmentWrapper onUpdate$lambda$15$lambda$14(ScreenFragmentWrapper screenFragmentWrapper) {
        Intrinsics.checkNotNullParameter(screenFragmentWrapper, "it");
        return (ScreenStackFragmentWrapper) screenFragmentWrapper;
    }

    private final void turnOffA11yUnderTransparentScreen(ScreenFragmentWrapper screenFragmentWrapper) {
        ScreenStackFragmentWrapper screenStackFragmentWrapper;
        if (this.screenWrappers.size() > 1 && screenFragmentWrapper != null && (screenStackFragmentWrapper = this.topScreenWrapper) != null && screenStackFragmentWrapper.getScreen().isTransparent()) {
            ArrayList arrayList = this.screenWrappers;
            for (ScreenFragmentWrapper screenFragmentWrapper2 : CollectionsKt.asReversed(CollectionsKt.slice(arrayList, RangesKt.until(0, arrayList.size() - 1)))) {
                screenFragmentWrapper2.getScreen().changeAccessibilityMode(4);
                if (Intrinsics.areEqual((Object) screenFragmentWrapper2, (Object) screenFragmentWrapper)) {
                    break;
                }
            }
        }
        Screen topScreen = getTopScreen();
        if (topScreen != null) {
            topScreen.changeAccessibilityMode(0);
        }
    }

    /* access modifiers changed from: protected */
    public void notifyContainerUpdate() {
        for (ScreenStackFragmentWrapper onContainerUpdate : this.stack) {
            onContainerUpdate.onContainerUpdate();
        }
    }

    private final void drawAndRelease() {
        List<DrawingOp> list = this.drawingOps;
        this.drawingOps = new ArrayList();
        for (DrawingOp drawingOp : list) {
            drawingOp.draw();
            this.drawingOpPool.add(drawingOp);
        }
    }

    /* access modifiers changed from: protected */
    public void dispatchDraw(Canvas canvas) {
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        super.dispatchDraw(canvas);
        if (this.drawingOps.size() < this.previousChildrenCount) {
            this.childDrawingOrderStrategy = null;
        }
        this.previousChildrenCount = this.drawingOps.size();
        ChildDrawingOrderStrategy childDrawingOrderStrategy2 = this.childDrawingOrderStrategy;
        if (childDrawingOrderStrategy2 != null) {
            childDrawingOrderStrategy2.apply(this.drawingOps);
        }
        drawAndRelease();
    }

    /* access modifiers changed from: protected */
    public boolean drawChild(Canvas canvas, View view, long j) {
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        Intrinsics.checkNotNullParameter(view, "child");
        List list = this.drawingOps;
        DrawingOp obtainDrawingOp = obtainDrawingOp();
        obtainDrawingOp.setCanvas(canvas);
        obtainDrawingOp.setChild(view);
        obtainDrawingOp.setDrawingTime(j);
        list.add(obtainDrawingOp);
        return true;
    }

    /* access modifiers changed from: private */
    public final void performDraw(DrawingOp drawingOp) {
        Canvas canvas = drawingOp.getCanvas();
        Intrinsics.checkNotNull(canvas);
        super.drawChild(canvas, drawingOp.getChild(), drawingOp.getDrawingTime());
    }

    private final DrawingOp obtainDrawingOp() {
        if (this.drawingOpPool.isEmpty()) {
            return new DrawingOp();
        }
        List list = this.drawingOpPool;
        return (DrawingOp) list.remove(CollectionsKt.getLastIndex(list));
    }

    public final class DrawingOp {
        private Canvas canvas;
        private View child;
        private long drawingTime;

        public DrawingOp() {
        }

        public final Canvas getCanvas() {
            return this.canvas;
        }

        public final void setCanvas(Canvas canvas2) {
            this.canvas = canvas2;
        }

        public final View getChild() {
            return this.child;
        }

        public final void setChild(View view) {
            this.child = view;
        }

        public final long getDrawingTime() {
            return this.drawingTime;
        }

        public final void setDrawingTime(long j) {
            this.drawingTime = j;
        }

        public final void draw() {
            ScreenStack.this.performDraw(this);
            this.canvas = null;
            this.child = null;
            this.drawingTime = 0;
        }
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* access modifiers changed from: private */
        public final boolean needsDrawReordering(ScreenFragmentWrapper screenFragmentWrapper, Screen.StackAnimation stackAnimation) {
            if (stackAnimation == null) {
                stackAnimation = screenFragmentWrapper.getScreen().getStackAnimation();
            }
            return (Build.VERSION.SDK_INT >= 33 || stackAnimation == Screen.StackAnimation.SLIDE_FROM_BOTTOM || stackAnimation == Screen.StackAnimation.FADE_FROM_BOTTOM || stackAnimation == Screen.StackAnimation.IOS_FROM_RIGHT || stackAnimation == Screen.StackAnimation.IOS_FROM_LEFT) && stackAnimation != Screen.StackAnimation.NONE;
        }
    }
}
