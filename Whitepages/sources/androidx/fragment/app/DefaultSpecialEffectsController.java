package androidx.fragment.app;

import android.animation.Animator;
import android.content.Context;
import android.graphics.Rect;
import android.support.v4.media.session.MediaControllerCompat$MediaControllerImplApi21$ExtraBinderRequestResultReceiver$$ExternalSyntheticThrowCCEIfNotNull0;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import androidx.collection.ArrayMap;
import androidx.core.os.CancellationSignal;
import androidx.core.view.OneShotPreDrawListener;
import androidx.core.view.ViewCompat;
import androidx.core.view.ViewGroupCompat;
import androidx.fragment.app.FragmentAnim;
import androidx.fragment.app.SpecialEffectsController;
import com.google.firebase.remoteconfig.RemoteConfigConstants;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

public final class DefaultSpecialEffectsController extends SpecialEffectsController {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public DefaultSpecialEffectsController(ViewGroup viewGroup) {
        super(viewGroup);
        Intrinsics.checkNotNullParameter(viewGroup, "container");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v0, resolved type: androidx.fragment.app.SpecialEffectsController$Operation} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v1, resolved type: androidx.fragment.app.SpecialEffectsController$Operation} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v8, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v1, resolved type: androidx.fragment.app.SpecialEffectsController$Operation} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v3, resolved type: androidx.fragment.app.SpecialEffectsController$Operation} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void executeOperations(java.util.List r14, boolean r15) {
        /*
            r13 = this;
            java.lang.String r0 = "operations"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r14, r0)
            r0 = r14
            java.lang.Iterable r0 = (java.lang.Iterable) r0
            java.util.Iterator r0 = r0.iterator()
        L_0x000c:
            boolean r1 = r0.hasNext()
            r2 = 0
            java.lang.String r3 = "operation.fragment.mView"
            if (r1 == 0) goto L_0x0036
            java.lang.Object r1 = r0.next()
            r4 = r1
            androidx.fragment.app.SpecialEffectsController$Operation r4 = (androidx.fragment.app.SpecialEffectsController.Operation) r4
            androidx.fragment.app.SpecialEffectsController$Operation$State$Companion r5 = androidx.fragment.app.SpecialEffectsController.Operation.State.Companion
            androidx.fragment.app.Fragment r6 = r4.getFragment()
            android.view.View r6 = r6.mView
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r6, r3)
            androidx.fragment.app.SpecialEffectsController$Operation$State r5 = r5.asOperationState(r6)
            androidx.fragment.app.SpecialEffectsController$Operation$State r6 = androidx.fragment.app.SpecialEffectsController.Operation.State.VISIBLE
            if (r5 != r6) goto L_0x000c
            androidx.fragment.app.SpecialEffectsController$Operation$State r4 = r4.getFinalState()
            if (r4 == r6) goto L_0x000c
            goto L_0x0037
        L_0x0036:
            r1 = r2
        L_0x0037:
            androidx.fragment.app.SpecialEffectsController$Operation r1 = (androidx.fragment.app.SpecialEffectsController.Operation) r1
            int r0 = r14.size()
            java.util.ListIterator r0 = r14.listIterator(r0)
        L_0x0041:
            boolean r4 = r0.hasPrevious()
            if (r4 == 0) goto L_0x0068
            java.lang.Object r4 = r0.previous()
            r5 = r4
            androidx.fragment.app.SpecialEffectsController$Operation r5 = (androidx.fragment.app.SpecialEffectsController.Operation) r5
            androidx.fragment.app.SpecialEffectsController$Operation$State$Companion r6 = androidx.fragment.app.SpecialEffectsController.Operation.State.Companion
            androidx.fragment.app.Fragment r7 = r5.getFragment()
            android.view.View r7 = r7.mView
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r7, r3)
            androidx.fragment.app.SpecialEffectsController$Operation$State r6 = r6.asOperationState(r7)
            androidx.fragment.app.SpecialEffectsController$Operation$State r7 = androidx.fragment.app.SpecialEffectsController.Operation.State.VISIBLE
            if (r6 == r7) goto L_0x0041
            androidx.fragment.app.SpecialEffectsController$Operation$State r5 = r5.getFinalState()
            if (r5 != r7) goto L_0x0041
            r2 = r4
        L_0x0068:
            androidx.fragment.app.SpecialEffectsController$Operation r2 = (androidx.fragment.app.SpecialEffectsController.Operation) r2
            r0 = 2
            boolean r3 = androidx.fragment.app.FragmentManager.isLoggingEnabled(r0)
            java.lang.String r10 = " to "
            java.lang.String r11 = "FragmentManager"
            if (r3 == 0) goto L_0x008f
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "Executing operations from "
            r3.append(r4)
            r3.append(r1)
            r3.append(r10)
            r3.append(r2)
            java.lang.String r3 = r3.toString()
            android.util.Log.v(r11, r3)
        L_0x008f:
            java.util.ArrayList r3 = new java.util.ArrayList
            r3.<init>()
            java.util.ArrayList r5 = new java.util.ArrayList
            r5.<init>()
            r4 = r14
            java.util.Collection r4 = (java.util.Collection) r4
            java.util.List r12 = kotlin.collections.CollectionsKt.toMutableList((java.util.Collection) r4)
            r13.syncAnimations(r14)
            java.util.Iterator r14 = r14.iterator()
        L_0x00a7:
            boolean r4 = r14.hasNext()
            if (r4 == 0) goto L_0x00e7
            java.lang.Object r4 = r14.next()
            androidx.fragment.app.SpecialEffectsController$Operation r4 = (androidx.fragment.app.SpecialEffectsController.Operation) r4
            androidx.core.os.CancellationSignal r6 = new androidx.core.os.CancellationSignal
            r6.<init>()
            r4.markStartedSpecialEffect(r6)
            androidx.fragment.app.DefaultSpecialEffectsController$AnimationInfo r7 = new androidx.fragment.app.DefaultSpecialEffectsController$AnimationInfo
            r7.<init>(r4, r6, r15)
            r3.add(r7)
            androidx.core.os.CancellationSignal r6 = new androidx.core.os.CancellationSignal
            r6.<init>()
            r4.markStartedSpecialEffect(r6)
            androidx.fragment.app.DefaultSpecialEffectsController$TransitionInfo r7 = new androidx.fragment.app.DefaultSpecialEffectsController$TransitionInfo
            r8 = 0
            r9 = 1
            if (r15 == 0) goto L_0x00d5
            if (r4 != r1) goto L_0x00d8
        L_0x00d3:
            r8 = r9
            goto L_0x00d8
        L_0x00d5:
            if (r4 != r2) goto L_0x00d8
            goto L_0x00d3
        L_0x00d8:
            r7.<init>(r4, r6, r15, r8)
            r5.add(r7)
            androidx.fragment.app.DefaultSpecialEffectsController$$ExternalSyntheticLambda0 r6 = new androidx.fragment.app.DefaultSpecialEffectsController$$ExternalSyntheticLambda0
            r6.<init>(r12, r4, r13)
            r4.addCompletionListener(r6)
            goto L_0x00a7
        L_0x00e7:
            r4 = r13
            r6 = r12
            r7 = r15
            r8 = r1
            r9 = r2
            java.util.Map r14 = r4.startTransitions(r5, r6, r7, r8, r9)
            java.lang.Boolean r15 = java.lang.Boolean.TRUE
            boolean r15 = r14.containsValue(r15)
            r13.startAnimations(r3, r12, r15, r14)
            java.util.Iterator r14 = r12.iterator()
        L_0x00fd:
            boolean r15 = r14.hasNext()
            if (r15 == 0) goto L_0x010d
            java.lang.Object r15 = r14.next()
            androidx.fragment.app.SpecialEffectsController$Operation r15 = (androidx.fragment.app.SpecialEffectsController.Operation) r15
            r13.applyContainerChanges(r15)
            goto L_0x00fd
        L_0x010d:
            r12.clear()
            boolean r14 = androidx.fragment.app.FragmentManager.isLoggingEnabled(r0)
            if (r14 == 0) goto L_0x0130
            java.lang.StringBuilder r14 = new java.lang.StringBuilder
            r14.<init>()
            java.lang.String r15 = "Completed executing operations from "
            r14.append(r15)
            r14.append(r1)
            r14.append(r10)
            r14.append(r2)
            java.lang.String r14 = r14.toString()
            android.util.Log.v(r11, r14)
        L_0x0130:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.fragment.app.DefaultSpecialEffectsController.executeOperations(java.util.List, boolean):void");
    }

    /* access modifiers changed from: private */
    public static final void executeOperations$lambda$2(List list, SpecialEffectsController.Operation operation, DefaultSpecialEffectsController defaultSpecialEffectsController) {
        Intrinsics.checkNotNullParameter(list, "$awaitingContainerChanges");
        Intrinsics.checkNotNullParameter(operation, "$operation");
        Intrinsics.checkNotNullParameter(defaultSpecialEffectsController, "this$0");
        if (list.contains(operation)) {
            list.remove(operation);
            defaultSpecialEffectsController.applyContainerChanges(operation);
        }
    }

    private final void syncAnimations(List list) {
        Fragment fragment = ((SpecialEffectsController.Operation) CollectionsKt.last(list)).getFragment();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            SpecialEffectsController.Operation operation = (SpecialEffectsController.Operation) it.next();
            operation.getFragment().mAnimationInfo.mEnterAnim = fragment.mAnimationInfo.mEnterAnim;
            operation.getFragment().mAnimationInfo.mExitAnim = fragment.mAnimationInfo.mExitAnim;
            operation.getFragment().mAnimationInfo.mPopEnterAnim = fragment.mAnimationInfo.mPopEnterAnim;
            operation.getFragment().mAnimationInfo.mPopExitAnim = fragment.mAnimationInfo.mPopExitAnim;
        }
    }

    private final void startAnimations(List list, List list2, boolean z, Map map) {
        Context context = getContainer().getContext();
        ArrayList<AnimationInfo> arrayList = new ArrayList<>();
        Iterator it = list.iterator();
        boolean z2 = false;
        while (it.hasNext()) {
            AnimationInfo animationInfo = (AnimationInfo) it.next();
            if (animationInfo.isVisibilityUnchanged()) {
                animationInfo.completeSpecialEffect();
            } else {
                Intrinsics.checkNotNullExpressionValue(context, "context");
                FragmentAnim.AnimationOrAnimator animation = animationInfo.getAnimation(context);
                if (animation == null) {
                    animationInfo.completeSpecialEffect();
                } else {
                    Animator animator = animation.animator;
                    if (animator == null) {
                        arrayList.add(animationInfo);
                    } else {
                        SpecialEffectsController.Operation operation = animationInfo.getOperation();
                        Fragment fragment = operation.getFragment();
                        if (Intrinsics.areEqual(map.get(operation), (Object) Boolean.TRUE)) {
                            if (FragmentManager.isLoggingEnabled(2)) {
                                Log.v("FragmentManager", "Ignoring Animator set on " + fragment + " as this Fragment was involved in a Transition.");
                            }
                            animationInfo.completeSpecialEffect();
                        } else {
                            boolean z3 = operation.getFinalState() == SpecialEffectsController.Operation.State.GONE;
                            List list3 = list2;
                            if (z3) {
                                list3.remove(operation);
                            }
                            View view = fragment.mView;
                            getContainer().startViewTransition(view);
                            DefaultSpecialEffectsController$startAnimations$1 defaultSpecialEffectsController$startAnimations$1 = r0;
                            View view2 = view;
                            SpecialEffectsController.Operation operation2 = operation;
                            DefaultSpecialEffectsController$startAnimations$1 defaultSpecialEffectsController$startAnimations$12 = new DefaultSpecialEffectsController$startAnimations$1(this, view2, z3, operation, animationInfo);
                            animator.addListener(defaultSpecialEffectsController$startAnimations$12);
                            animator.setTarget(view2);
                            animator.start();
                            if (FragmentManager.isLoggingEnabled(2)) {
                                Log.v("FragmentManager", "Animator from operation " + operation2 + " has started.");
                            }
                            animationInfo.getSignal().setOnCancelListener(new DefaultSpecialEffectsController$$ExternalSyntheticLambda1(animator, operation2));
                            z2 = true;
                        }
                    }
                }
            }
            Map map2 = map;
        }
        for (AnimationInfo animationInfo2 : arrayList) {
            SpecialEffectsController.Operation operation3 = animationInfo2.getOperation();
            Fragment fragment2 = operation3.getFragment();
            if (z) {
                if (FragmentManager.isLoggingEnabled(2)) {
                    Log.v("FragmentManager", "Ignoring Animation set on " + fragment2 + " as Animations cannot run alongside Transitions.");
                }
                animationInfo2.completeSpecialEffect();
            } else if (z2) {
                if (FragmentManager.isLoggingEnabled(2)) {
                    Log.v("FragmentManager", "Ignoring Animation set on " + fragment2 + " as Animations cannot run alongside Animators.");
                }
                animationInfo2.completeSpecialEffect();
            } else {
                View view3 = fragment2.mView;
                Intrinsics.checkNotNullExpressionValue(context, "context");
                FragmentAnim.AnimationOrAnimator animation2 = animationInfo2.getAnimation(context);
                if (animation2 != null) {
                    Animation animation3 = animation2.animation;
                    if (animation3 != null) {
                        if (operation3.getFinalState() != SpecialEffectsController.Operation.State.REMOVED) {
                            view3.startAnimation(animation3);
                            animationInfo2.completeSpecialEffect();
                        } else {
                            getContainer().startViewTransition(view3);
                            FragmentAnim.EndViewTransitionAnimation endViewTransitionAnimation = new FragmentAnim.EndViewTransitionAnimation(animation3, getContainer(), view3);
                            endViewTransitionAnimation.setAnimationListener(new DefaultSpecialEffectsController$startAnimations$3(operation3, this, view3, animationInfo2));
                            view3.startAnimation(endViewTransitionAnimation);
                            if (FragmentManager.isLoggingEnabled(2)) {
                                Log.v("FragmentManager", "Animation from operation " + operation3 + " has started.");
                            }
                        }
                        animationInfo2.getSignal().setOnCancelListener(new DefaultSpecialEffectsController$$ExternalSyntheticLambda2(view3, this, animationInfo2, operation3));
                    } else {
                        throw new IllegalStateException("Required value was null.");
                    }
                } else {
                    throw new IllegalStateException("Required value was null.");
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public static final void startAnimations$lambda$3(Animator animator, SpecialEffectsController.Operation operation) {
        Intrinsics.checkNotNullParameter(operation, "$operation");
        animator.end();
        if (FragmentManager.isLoggingEnabled(2)) {
            Log.v("FragmentManager", "Animator from operation " + operation + " has been canceled.");
        }
    }

    /* access modifiers changed from: private */
    public static final void startAnimations$lambda$4(View view, DefaultSpecialEffectsController defaultSpecialEffectsController, AnimationInfo animationInfo, SpecialEffectsController.Operation operation) {
        Intrinsics.checkNotNullParameter(defaultSpecialEffectsController, "this$0");
        Intrinsics.checkNotNullParameter(animationInfo, "$animationInfo");
        Intrinsics.checkNotNullParameter(operation, "$operation");
        view.clearAnimation();
        defaultSpecialEffectsController.getContainer().endViewTransition(view);
        animationInfo.completeSpecialEffect();
        if (FragmentManager.isLoggingEnabled(2)) {
            Log.v("FragmentManager", "Animation from operation " + operation + " has been cancelled.");
        }
    }

    private final Map startTransitions(List list, List list2, boolean z, SpecialEffectsController.Operation operation, SpecialEffectsController.Operation operation2) {
        String str;
        String str2;
        Object obj;
        Object obj2;
        String str3;
        Object obj3;
        View view;
        boolean z2;
        View view2;
        Object obj4;
        SpecialEffectsController.Operation operation3;
        ArrayList arrayList;
        LinkedHashMap linkedHashMap;
        View view3;
        Rect rect;
        Pair pair;
        View view4;
        View view5;
        DefaultSpecialEffectsController defaultSpecialEffectsController = this;
        boolean z3 = z;
        SpecialEffectsController.Operation operation4 = operation;
        SpecialEffectsController.Operation operation5 = operation2;
        LinkedHashMap linkedHashMap2 = new LinkedHashMap();
        ArrayList arrayList2 = new ArrayList();
        for (Object next : list) {
            if (!((TransitionInfo) next).isVisibilityUnchanged()) {
                arrayList2.add(next);
            }
        }
        ArrayList<TransitionInfo> arrayList3 = new ArrayList<>();
        for (Object next2 : arrayList2) {
            if (((TransitionInfo) next2).getHandlingImpl() != null) {
                arrayList3.add(next2);
            }
        }
        FragmentTransitionImpl fragmentTransitionImpl = null;
        for (TransitionInfo transitionInfo : arrayList3) {
            FragmentTransitionImpl handlingImpl = transitionInfo.getHandlingImpl();
            if (fragmentTransitionImpl == null || handlingImpl == fragmentTransitionImpl) {
                fragmentTransitionImpl = handlingImpl;
            } else {
                throw new IllegalArgumentException(("Mixing framework transitions and AndroidX transitions is not allowed. Fragment " + transitionInfo.getOperation().getFragment() + " returned Transition " + transitionInfo.getTransition() + " which uses a different Transition type than other Fragments.").toString());
            }
        }
        if (fragmentTransitionImpl == null) {
            Iterator it = list.iterator();
            while (it.hasNext()) {
                TransitionInfo transitionInfo2 = (TransitionInfo) it.next();
                linkedHashMap2.put(transitionInfo2.getOperation(), Boolean.FALSE);
                transitionInfo2.completeSpecialEffect();
            }
            return linkedHashMap2;
        }
        View view6 = new View(getContainer().getContext());
        Rect rect2 = new Rect();
        ArrayList arrayList4 = new ArrayList();
        ArrayList arrayList5 = new ArrayList();
        ArrayMap arrayMap = new ArrayMap();
        Iterator it2 = list.iterator();
        View view7 = null;
        Object obj5 = null;
        boolean z4 = false;
        while (true) {
            str = "FragmentManager";
            if (!it2.hasNext()) {
                break;
            }
            TransitionInfo transitionInfo3 = (TransitionInfo) it2.next();
            if (!transitionInfo3.hasSharedElementTransition() || operation4 == null || operation5 == null) {
                rect = rect2;
                view6 = view6;
                arrayList4 = arrayList4;
                linkedHashMap2 = linkedHashMap2;
                arrayMap = arrayMap;
                view7 = view7;
                arrayList5 = arrayList5;
            } else {
                Object wrapTransitionInSet = fragmentTransitionImpl.wrapTransitionInSet(fragmentTransitionImpl.cloneTransition(transitionInfo3.getSharedElementTransition()));
                ArrayList<String> sharedElementSourceNames = operation2.getFragment().getSharedElementSourceNames();
                Intrinsics.checkNotNullExpressionValue(sharedElementSourceNames, "lastIn.fragment.sharedElementSourceNames");
                ArrayList<String> sharedElementSourceNames2 = operation.getFragment().getSharedElementSourceNames();
                View view8 = view7;
                Intrinsics.checkNotNullExpressionValue(sharedElementSourceNames2, "firstOut.fragment.sharedElementSourceNames");
                ArrayList<String> sharedElementTargetNames = operation.getFragment().getSharedElementTargetNames();
                LinkedHashMap linkedHashMap3 = linkedHashMap2;
                Intrinsics.checkNotNullExpressionValue(sharedElementTargetNames, "firstOut.fragment.sharedElementTargetNames");
                int size = sharedElementTargetNames.size();
                View view9 = view6;
                int i = 0;
                while (i < size) {
                    int i2 = size;
                    int indexOf = sharedElementSourceNames.indexOf(sharedElementTargetNames.get(i));
                    ArrayList<String> arrayList6 = sharedElementTargetNames;
                    if (indexOf != -1) {
                        sharedElementSourceNames.set(indexOf, sharedElementSourceNames2.get(i));
                    }
                    i++;
                    size = i2;
                    sharedElementTargetNames = arrayList6;
                }
                ArrayList<String> sharedElementTargetNames2 = operation2.getFragment().getSharedElementTargetNames();
                Intrinsics.checkNotNullExpressionValue(sharedElementTargetNames2, "lastIn.fragment.sharedElementTargetNames");
                if (!z3) {
                    operation.getFragment().getExitTransitionCallback();
                    operation2.getFragment().getEnterTransitionCallback();
                    pair = TuplesKt.to((Object) null, (Object) null);
                } else {
                    operation.getFragment().getEnterTransitionCallback();
                    operation2.getFragment().getExitTransitionCallback();
                    pair = TuplesKt.to((Object) null, (Object) null);
                }
                MediaControllerCompat$MediaControllerImplApi21$ExtraBinderRequestResultReceiver$$ExternalSyntheticThrowCCEIfNotNull0.m(pair.component1());
                MediaControllerCompat$MediaControllerImplApi21$ExtraBinderRequestResultReceiver$$ExternalSyntheticThrowCCEIfNotNull0.m(pair.component2());
                int i3 = 0;
                for (int size2 = sharedElementSourceNames.size(); i3 < size2; size2 = size2) {
                    arrayMap.put(sharedElementSourceNames.get(i3), sharedElementTargetNames2.get(i3));
                    i3++;
                }
                if (FragmentManager.isLoggingEnabled(2)) {
                    Log.v(str, ">>> entering view names <<<");
                    for (Iterator<String> it3 = sharedElementTargetNames2.iterator(); it3.hasNext(); it3 = it3) {
                        Log.v(str, "Name: " + it3.next());
                    }
                    Log.v(str, ">>> exiting view names <<<");
                    for (Iterator<String> it4 = sharedElementSourceNames.iterator(); it4.hasNext(); it4 = it4) {
                        Log.v(str, "Name: " + it4.next());
                    }
                }
                ArrayMap arrayMap2 = new ArrayMap();
                View view10 = operation.getFragment().mView;
                Intrinsics.checkNotNullExpressionValue(view10, "firstOut.fragment.mView");
                defaultSpecialEffectsController.findNamedViews(arrayMap2, view10);
                arrayMap2.retainAll(sharedElementSourceNames);
                arrayMap.retainAll(arrayMap2.keySet());
                ArrayMap arrayMap3 = new ArrayMap();
                View view11 = operation2.getFragment().mView;
                Intrinsics.checkNotNullExpressionValue(view11, "lastIn.fragment.mView");
                defaultSpecialEffectsController.findNamedViews(arrayMap3, view11);
                arrayMap3.retainAll(sharedElementTargetNames2);
                arrayMap3.retainAll(arrayMap.values());
                FragmentTransition.retainValues(arrayMap, arrayMap3);
                Set<Object> keySet = arrayMap.keySet();
                Intrinsics.checkNotNullExpressionValue(keySet, "sharedElementNameMapping.keys");
                defaultSpecialEffectsController.retainMatchingViews(arrayMap2, keySet);
                Collection<Object> values = arrayMap.values();
                Intrinsics.checkNotNullExpressionValue(values, "sharedElementNameMapping.values");
                defaultSpecialEffectsController.retainMatchingViews(arrayMap3, values);
                if (arrayMap.isEmpty()) {
                    arrayList4.clear();
                    arrayList5.clear();
                    view7 = view8;
                    linkedHashMap2 = linkedHashMap3;
                    view6 = view9;
                    obj5 = null;
                } else {
                    ArrayMap arrayMap4 = arrayMap;
                    FragmentTransition.callSharedElementStartEnd(operation2.getFragment(), operation.getFragment(), z3, arrayMap2, true);
                    OneShotPreDrawListener.add(getContainer(), new DefaultSpecialEffectsController$$ExternalSyntheticLambda3(operation5, operation4, z3, arrayMap3));
                    arrayList4.addAll(arrayMap2.values());
                    if (!sharedElementSourceNames.isEmpty()) {
                        view4 = (View) arrayMap2.get(sharedElementSourceNames.get(0));
                        fragmentTransitionImpl.setEpicenter(wrapTransitionInSet, view4);
                    } else {
                        view4 = view8;
                    }
                    arrayList5.addAll(arrayMap3.values());
                    if (!sharedElementTargetNames2.isEmpty() && (view5 = (View) arrayMap3.get(sharedElementTargetNames2.get(0))) != null) {
                        OneShotPreDrawListener.add(getContainer(), new DefaultSpecialEffectsController$$ExternalSyntheticLambda4(fragmentTransitionImpl, view5, rect2));
                        z4 = true;
                    }
                    View view12 = view9;
                    fragmentTransitionImpl.setSharedElementTargets(wrapTransitionInSet, view12, arrayList4);
                    Object obj6 = wrapTransitionInSet;
                    ArrayList arrayList7 = arrayList5;
                    rect = rect2;
                    fragmentTransitionImpl.scheduleRemoveTargets(wrapTransitionInSet, (Object) null, (ArrayList) null, (Object) null, (ArrayList) null, obj6, arrayList7);
                    Boolean bool = Boolean.TRUE;
                    LinkedHashMap linkedHashMap4 = linkedHashMap3;
                    linkedHashMap4.put(operation4, bool);
                    linkedHashMap4.put(operation5, bool);
                    view7 = view4;
                    arrayList4 = arrayList4;
                    arrayMap = arrayMap4;
                    obj5 = obj6;
                    arrayList5 = arrayList7;
                    view6 = view12;
                    linkedHashMap2 = linkedHashMap4;
                }
            }
            rect2 = rect;
            z3 = z;
        }
        View view13 = view7;
        ArrayMap arrayMap5 = arrayMap;
        ArrayList arrayList8 = arrayList5;
        ArrayList arrayList9 = arrayList4;
        Rect rect3 = rect2;
        boolean z5 = true;
        LinkedHashMap linkedHashMap5 = linkedHashMap2;
        View view14 = view6;
        ArrayList arrayList10 = new ArrayList();
        Iterator it5 = list.iterator();
        Object obj7 = null;
        Object obj8 = null;
        while (it5.hasNext()) {
            TransitionInfo transitionInfo4 = (TransitionInfo) it5.next();
            if (transitionInfo4.isVisibilityUnchanged()) {
                linkedHashMap5.put(transitionInfo4.getOperation(), Boolean.FALSE);
                transitionInfo4.completeSpecialEffect();
            } else {
                Object cloneTransition = fragmentTransitionImpl.cloneTransition(transitionInfo4.getTransition());
                SpecialEffectsController.Operation operation6 = transitionInfo4.getOperation();
                boolean z6 = (obj5 == null || !(operation6 == operation4 || operation6 == operation5)) ? false : z5;
                if (cloneTransition != null) {
                    LinkedHashMap linkedHashMap6 = linkedHashMap5;
                    ArrayList arrayList11 = new ArrayList();
                    View view15 = operation6.getFragment().mView;
                    Object obj9 = obj5;
                    Intrinsics.checkNotNullExpressionValue(view15, "operation.fragment.mView");
                    defaultSpecialEffectsController.captureTransitioningViews(arrayList11, view15);
                    if (z6) {
                        if (operation6 == operation4) {
                            arrayList11.removeAll(CollectionsKt.toSet(arrayList9));
                        } else {
                            arrayList11.removeAll(CollectionsKt.toSet(arrayList8));
                        }
                    }
                    if (arrayList11.isEmpty()) {
                        fragmentTransitionImpl.addTarget(cloneTransition, view14);
                        view2 = view14;
                        obj4 = cloneTransition;
                        str3 = str;
                        obj2 = obj7;
                        obj = obj8;
                        arrayList = arrayList11;
                        view = view13;
                        linkedHashMap = linkedHashMap6;
                        obj3 = obj9;
                        z2 = true;
                        operation3 = operation6;
                        List list3 = list2;
                    } else {
                        fragmentTransitionImpl.addTargets(cloneTransition, arrayList11);
                        Object obj10 = cloneTransition;
                        view = view13;
                        SpecialEffectsController.Operation operation7 = operation6;
                        obj3 = obj9;
                        str3 = str;
                        z2 = true;
                        obj2 = obj7;
                        obj = obj8;
                        view2 = view14;
                        arrayList = arrayList11;
                        linkedHashMap = linkedHashMap6;
                        fragmentTransitionImpl.scheduleRemoveTargets(cloneTransition, obj10, arrayList11, (Object) null, (ArrayList) null, (Object) null, (ArrayList) null);
                        if (operation7.getFinalState() == SpecialEffectsController.Operation.State.GONE) {
                            operation3 = operation7;
                            list2.remove(operation3);
                            ArrayList arrayList12 = new ArrayList(arrayList);
                            arrayList12.remove(operation3.getFragment().mView);
                            obj4 = obj10;
                            fragmentTransitionImpl.scheduleHideFragmentView(obj4, operation3.getFragment().mView, arrayList12);
                            OneShotPreDrawListener.add(getContainer(), new DefaultSpecialEffectsController$$ExternalSyntheticLambda5(arrayList));
                        } else {
                            List list4 = list2;
                            obj4 = obj10;
                            operation3 = operation7;
                        }
                    }
                    if (operation3.getFinalState() == SpecialEffectsController.Operation.State.VISIBLE) {
                        arrayList10.addAll(arrayList);
                        if (z4) {
                            fragmentTransitionImpl.setEpicenter(obj4, rect3);
                        }
                        view3 = view;
                    } else {
                        view3 = view;
                        fragmentTransitionImpl.setEpicenter(obj4, view3);
                    }
                    linkedHashMap.put(operation3, Boolean.TRUE);
                    if (transitionInfo4.isOverlapAllowed()) {
                        obj8 = fragmentTransitionImpl.mergeTransitionsTogether(obj, obj4, (Object) null);
                        linkedHashMap5 = linkedHashMap;
                        view13 = view3;
                        view14 = view2;
                        z5 = z2;
                        obj5 = obj3;
                        str = str3;
                        obj7 = obj2;
                    } else {
                        obj8 = obj;
                        obj7 = fragmentTransitionImpl.mergeTransitionsTogether(obj2, obj4, (Object) null);
                        linkedHashMap5 = linkedHashMap;
                        view13 = view3;
                        view14 = view2;
                        z5 = z2;
                        obj5 = obj3;
                        str = str3;
                    }
                    defaultSpecialEffectsController = this;
                } else if (!z6) {
                    linkedHashMap5.put(operation6, Boolean.FALSE);
                    transitionInfo4.completeSpecialEffect();
                }
            }
        }
        String str4 = str;
        boolean z7 = z5;
        LinkedHashMap linkedHashMap7 = linkedHashMap5;
        Object obj11 = obj5;
        Object mergeTransitionsInSequence = fragmentTransitionImpl.mergeTransitionsInSequence(obj8, obj7, obj11);
        if (mergeTransitionsInSequence == null) {
            return linkedHashMap7;
        }
        ArrayList<TransitionInfo> arrayList13 = new ArrayList<>();
        for (Object next3 : list) {
            if (!((TransitionInfo) next3).isVisibilityUnchanged()) {
                arrayList13.add(next3);
            }
        }
        for (TransitionInfo transitionInfo5 : arrayList13) {
            Object transition = transitionInfo5.getTransition();
            SpecialEffectsController.Operation operation8 = transitionInfo5.getOperation();
            boolean z8 = (obj11 == null || !(operation8 == operation4 || operation8 == operation5)) ? false : z7;
            if (transition == null && !z8) {
                str2 = str4;
            } else if (!ViewCompat.isLaidOut(getContainer())) {
                if (FragmentManager.isLoggingEnabled(2)) {
                    str2 = str4;
                    Log.v(str2, "SpecialEffectsController: Container " + getContainer() + " has not been laid out. Completing operation " + operation8);
                } else {
                    str2 = str4;
                }
                transitionInfo5.completeSpecialEffect();
            } else {
                str2 = str4;
                fragmentTransitionImpl.setListenerForTransitionEnd(transitionInfo5.getOperation().getFragment(), mergeTransitionsInSequence, transitionInfo5.getSignal(), new DefaultSpecialEffectsController$$ExternalSyntheticLambda6(transitionInfo5, operation8));
            }
            str4 = str2;
        }
        String str5 = str4;
        if (!ViewCompat.isLaidOut(getContainer())) {
            return linkedHashMap7;
        }
        FragmentTransition.setViewVisibility(arrayList10, 4);
        ArrayList arrayList14 = arrayList8;
        ArrayList prepareSetNameOverridesReordered = fragmentTransitionImpl.prepareSetNameOverridesReordered(arrayList14);
        if (FragmentManager.isLoggingEnabled(2)) {
            Log.v(str5, ">>>>> Beginning transition <<<<<");
            Log.v(str5, ">>>>> SharedElementFirstOutViews <<<<<");
            Iterator it6 = arrayList9.iterator();
            while (it6.hasNext()) {
                Object next4 = it6.next();
                Intrinsics.checkNotNullExpressionValue(next4, "sharedElementFirstOutViews");
                View view16 = (View) next4;
                Log.v(str5, "View: " + view16 + " Name: " + ViewCompat.getTransitionName(view16));
            }
            Log.v(str5, ">>>>> SharedElementLastInViews <<<<<");
            Iterator it7 = arrayList14.iterator();
            while (it7.hasNext()) {
                Object next5 = it7.next();
                Intrinsics.checkNotNullExpressionValue(next5, "sharedElementLastInViews");
                View view17 = (View) next5;
                Log.v(str5, "View: " + view17 + " Name: " + ViewCompat.getTransitionName(view17));
            }
        }
        fragmentTransitionImpl.beginDelayedTransition(getContainer(), mergeTransitionsInSequence);
        fragmentTransitionImpl.setNameOverridesReordered(getContainer(), arrayList9, arrayList14, prepareSetNameOverridesReordered, arrayMap5);
        FragmentTransition.setViewVisibility(arrayList10, 0);
        fragmentTransitionImpl.swapSharedElementTargets(obj11, arrayList9, arrayList14);
        return linkedHashMap7;
    }

    /* access modifiers changed from: private */
    public static final void startTransitions$lambda$9(SpecialEffectsController.Operation operation, SpecialEffectsController.Operation operation2, boolean z, ArrayMap arrayMap) {
        Intrinsics.checkNotNullParameter(arrayMap, "$lastInViews");
        FragmentTransition.callSharedElementStartEnd(operation.getFragment(), operation2.getFragment(), z, arrayMap, false);
    }

    /* access modifiers changed from: private */
    public static final void startTransitions$lambda$10(FragmentTransitionImpl fragmentTransitionImpl, View view, Rect rect) {
        Intrinsics.checkNotNullParameter(fragmentTransitionImpl, "$impl");
        Intrinsics.checkNotNullParameter(rect, "$lastInEpicenterRect");
        fragmentTransitionImpl.getBoundsOnScreen(view, rect);
    }

    /* access modifiers changed from: private */
    public static final void startTransitions$lambda$11(ArrayList arrayList) {
        Intrinsics.checkNotNullParameter(arrayList, "$transitioningViews");
        FragmentTransition.setViewVisibility(arrayList, 4);
    }

    /* access modifiers changed from: private */
    public static final void startTransitions$lambda$14$lambda$13(TransitionInfo transitionInfo, SpecialEffectsController.Operation operation) {
        Intrinsics.checkNotNullParameter(transitionInfo, "$transitionInfo");
        Intrinsics.checkNotNullParameter(operation, "$operation");
        transitionInfo.completeSpecialEffect();
        if (FragmentManager.isLoggingEnabled(2)) {
            Log.v("FragmentManager", "Transition for operation " + operation + " has completed");
        }
    }

    private final void retainMatchingViews(ArrayMap arrayMap, Collection collection) {
        Set<Map.Entry<Object, Object>> entrySet = arrayMap.entrySet();
        Intrinsics.checkNotNullExpressionValue(entrySet, RemoteConfigConstants.ResponseFieldKey.ENTRIES);
        CollectionsKt.retainAll(entrySet, new DefaultSpecialEffectsController$retainMatchingViews$1(collection));
    }

    private final void captureTransitioningViews(ArrayList arrayList, View view) {
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            if (!ViewGroupCompat.isTransitionGroup(viewGroup)) {
                int childCount = viewGroup.getChildCount();
                for (int i = 0; i < childCount; i++) {
                    View childAt = viewGroup.getChildAt(i);
                    if (childAt.getVisibility() == 0) {
                        Intrinsics.checkNotNullExpressionValue(childAt, "child");
                        captureTransitioningViews(arrayList, childAt);
                    }
                }
            } else if (!arrayList.contains(view)) {
                arrayList.add(view);
            }
        } else if (!arrayList.contains(view)) {
            arrayList.add(view);
        }
    }

    private final void findNamedViews(Map map, View view) {
        String transitionName = ViewCompat.getTransitionName(view);
        if (transitionName != null) {
            map.put(transitionName, view);
        }
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            int childCount = viewGroup.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childAt = viewGroup.getChildAt(i);
                if (childAt.getVisibility() == 0) {
                    Intrinsics.checkNotNullExpressionValue(childAt, "child");
                    findNamedViews(map, childAt);
                }
            }
        }
    }

    private final void applyContainerChanges(SpecialEffectsController.Operation operation) {
        View view = operation.getFragment().mView;
        SpecialEffectsController.Operation.State finalState = operation.getFinalState();
        Intrinsics.checkNotNullExpressionValue(view, "view");
        finalState.applyState(view);
    }

    private static class SpecialEffectsInfo {
        private final SpecialEffectsController.Operation operation;
        private final CancellationSignal signal;

        public SpecialEffectsInfo(SpecialEffectsController.Operation operation2, CancellationSignal cancellationSignal) {
            Intrinsics.checkNotNullParameter(operation2, "operation");
            Intrinsics.checkNotNullParameter(cancellationSignal, "signal");
            this.operation = operation2;
            this.signal = cancellationSignal;
        }

        public final SpecialEffectsController.Operation getOperation() {
            return this.operation;
        }

        public final CancellationSignal getSignal() {
            return this.signal;
        }

        /* JADX WARNING: Code restructure failed: missing block: B:2:0x001b, code lost:
            r2 = androidx.fragment.app.SpecialEffectsController.Operation.State.VISIBLE;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final boolean isVisibilityUnchanged() {
            /*
                r3 = this;
                androidx.fragment.app.SpecialEffectsController$Operation$State$Companion r0 = androidx.fragment.app.SpecialEffectsController.Operation.State.Companion
                androidx.fragment.app.SpecialEffectsController$Operation r1 = r3.operation
                androidx.fragment.app.Fragment r1 = r1.getFragment()
                android.view.View r1 = r1.mView
                java.lang.String r2 = "operation.fragment.mView"
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r2)
                androidx.fragment.app.SpecialEffectsController$Operation$State r0 = r0.asOperationState(r1)
                androidx.fragment.app.SpecialEffectsController$Operation r1 = r3.operation
                androidx.fragment.app.SpecialEffectsController$Operation$State r1 = r1.getFinalState()
                if (r0 == r1) goto L_0x0024
                androidx.fragment.app.SpecialEffectsController$Operation$State r2 = androidx.fragment.app.SpecialEffectsController.Operation.State.VISIBLE
                if (r0 == r2) goto L_0x0022
                if (r1 == r2) goto L_0x0022
                goto L_0x0024
            L_0x0022:
                r0 = 0
                goto L_0x0025
            L_0x0024:
                r0 = 1
            L_0x0025:
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.fragment.app.DefaultSpecialEffectsController.SpecialEffectsInfo.isVisibilityUnchanged():boolean");
        }

        public final void completeSpecialEffect() {
            this.operation.completeSpecialEffect(this.signal);
        }
    }

    private static final class AnimationInfo extends SpecialEffectsInfo {
        private FragmentAnim.AnimationOrAnimator animation;
        private boolean isAnimLoaded;
        private final boolean isPop;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public AnimationInfo(SpecialEffectsController.Operation operation, CancellationSignal cancellationSignal, boolean z) {
            super(operation, cancellationSignal);
            Intrinsics.checkNotNullParameter(operation, "operation");
            Intrinsics.checkNotNullParameter(cancellationSignal, "signal");
            this.isPop = z;
        }

        public final FragmentAnim.AnimationOrAnimator getAnimation(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            if (this.isAnimLoaded) {
                return this.animation;
            }
            FragmentAnim.AnimationOrAnimator loadAnimation = FragmentAnim.loadAnimation(context, getOperation().getFragment(), getOperation().getFinalState() == SpecialEffectsController.Operation.State.VISIBLE, this.isPop);
            this.animation = loadAnimation;
            this.isAnimLoaded = true;
            return loadAnimation;
        }
    }

    private static final class TransitionInfo extends SpecialEffectsInfo {
        private final boolean isOverlapAllowed;
        private final Object sharedElementTransition;
        private final Object transition;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public TransitionInfo(SpecialEffectsController.Operation operation, CancellationSignal cancellationSignal, boolean z, boolean z2) {
            super(operation, cancellationSignal);
            Object obj;
            boolean z3;
            Object obj2;
            Intrinsics.checkNotNullParameter(operation, "operation");
            Intrinsics.checkNotNullParameter(cancellationSignal, "signal");
            SpecialEffectsController.Operation.State finalState = operation.getFinalState();
            SpecialEffectsController.Operation.State state = SpecialEffectsController.Operation.State.VISIBLE;
            if (finalState == state) {
                Fragment fragment = operation.getFragment();
                obj = z ? fragment.getReenterTransition() : fragment.getEnterTransition();
            } else {
                Fragment fragment2 = operation.getFragment();
                obj = z ? fragment2.getReturnTransition() : fragment2.getExitTransition();
            }
            this.transition = obj;
            if (operation.getFinalState() == state) {
                z3 = z ? operation.getFragment().getAllowReturnTransitionOverlap() : operation.getFragment().getAllowEnterTransitionOverlap();
            } else {
                z3 = true;
            }
            this.isOverlapAllowed = z3;
            if (z2) {
                obj2 = z ? operation.getFragment().getSharedElementReturnTransition() : operation.getFragment().getSharedElementEnterTransition();
            } else {
                obj2 = null;
            }
            this.sharedElementTransition = obj2;
        }

        public final Object getTransition() {
            return this.transition;
        }

        public final boolean isOverlapAllowed() {
            return this.isOverlapAllowed;
        }

        public final Object getSharedElementTransition() {
            return this.sharedElementTransition;
        }

        public final boolean hasSharedElementTransition() {
            return this.sharedElementTransition != null;
        }

        public final FragmentTransitionImpl getHandlingImpl() {
            FragmentTransitionImpl handlingImpl = getHandlingImpl(this.transition);
            FragmentTransitionImpl handlingImpl2 = getHandlingImpl(this.sharedElementTransition);
            if (handlingImpl == null || handlingImpl2 == null || handlingImpl == handlingImpl2) {
                return handlingImpl == null ? handlingImpl2 : handlingImpl;
            }
            throw new IllegalArgumentException(("Mixing framework transitions and AndroidX transitions is not allowed. Fragment " + getOperation().getFragment() + " returned Transition " + this.transition + " which uses a different Transition  type than its shared element transition " + this.sharedElementTransition).toString());
        }

        private final FragmentTransitionImpl getHandlingImpl(Object obj) {
            if (obj == null) {
                return null;
            }
            FragmentTransitionImpl fragmentTransitionImpl = FragmentTransition.PLATFORM_IMPL;
            if (fragmentTransitionImpl != null && fragmentTransitionImpl.canHandle(obj)) {
                return fragmentTransitionImpl;
            }
            FragmentTransitionImpl fragmentTransitionImpl2 = FragmentTransition.SUPPORT_IMPL;
            if (fragmentTransitionImpl2 != null && fragmentTransitionImpl2.canHandle(obj)) {
                return fragmentTransitionImpl2;
            }
            throw new IllegalArgumentException("Transition " + obj + " for fragment " + getOperation().getFragment() + " is not a valid framework Transition or AndroidX Transition");
        }
    }
}
