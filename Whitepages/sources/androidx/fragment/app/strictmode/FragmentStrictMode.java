package androidx.fragment.app.strictmode;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class FragmentStrictMode {
    public static final FragmentStrictMode INSTANCE = new FragmentStrictMode();
    private static Policy defaultPolicy = Policy.LAX;

    public enum Flag {
        PENALTY_LOG,
        PENALTY_DEATH,
        DETECT_FRAGMENT_REUSE,
        DETECT_FRAGMENT_TAG_USAGE,
        DETECT_WRONG_NESTED_HIERARCHY,
        DETECT_RETAIN_INSTANCE_USAGE,
        DETECT_SET_USER_VISIBLE_HINT,
        DETECT_TARGET_FRAGMENT_USAGE,
        DETECT_WRONG_FRAGMENT_CONTAINER
    }

    public interface OnViolationListener {
    }

    private FragmentStrictMode() {
    }

    private final Policy getNearestPolicy(Fragment fragment) {
        while (fragment != null) {
            if (fragment.isAdded()) {
                FragmentManager parentFragmentManager = fragment.getParentFragmentManager();
                Intrinsics.checkNotNullExpressionValue(parentFragmentManager, "declaringFragment.parentFragmentManager");
                if (parentFragmentManager.getStrictModePolicy() != null) {
                    Policy strictModePolicy = parentFragmentManager.getStrictModePolicy();
                    Intrinsics.checkNotNull(strictModePolicy);
                    return strictModePolicy;
                }
            }
            fragment = fragment.getParentFragment();
        }
        return defaultPolicy;
    }

    public static final void onFragmentReuse(Fragment fragment, String str) {
        Intrinsics.checkNotNullParameter(fragment, "fragment");
        Intrinsics.checkNotNullParameter(str, "previousFragmentId");
        FragmentReuseViolation fragmentReuseViolation = new FragmentReuseViolation(fragment, str);
        FragmentStrictMode fragmentStrictMode = INSTANCE;
        fragmentStrictMode.logIfDebuggingEnabled(fragmentReuseViolation);
        Policy nearestPolicy = fragmentStrictMode.getNearestPolicy(fragment);
        if (nearestPolicy.getFlags$fragment_release().contains(Flag.DETECT_FRAGMENT_REUSE) && fragmentStrictMode.shouldHandlePolicyViolation(nearestPolicy, fragment.getClass(), fragmentReuseViolation.getClass())) {
            fragmentStrictMode.handlePolicyViolation(nearestPolicy, fragmentReuseViolation);
        }
    }

    public static final void onFragmentTagUsage(Fragment fragment, ViewGroup viewGroup) {
        Intrinsics.checkNotNullParameter(fragment, "fragment");
        FragmentTagUsageViolation fragmentTagUsageViolation = new FragmentTagUsageViolation(fragment, viewGroup);
        FragmentStrictMode fragmentStrictMode = INSTANCE;
        fragmentStrictMode.logIfDebuggingEnabled(fragmentTagUsageViolation);
        Policy nearestPolicy = fragmentStrictMode.getNearestPolicy(fragment);
        if (nearestPolicy.getFlags$fragment_release().contains(Flag.DETECT_FRAGMENT_TAG_USAGE) && fragmentStrictMode.shouldHandlePolicyViolation(nearestPolicy, fragment.getClass(), fragmentTagUsageViolation.getClass())) {
            fragmentStrictMode.handlePolicyViolation(nearestPolicy, fragmentTagUsageViolation);
        }
    }

    public static final void onWrongNestedHierarchy(Fragment fragment, Fragment fragment2, int i) {
        Intrinsics.checkNotNullParameter(fragment, "fragment");
        Intrinsics.checkNotNullParameter(fragment2, "expectedParentFragment");
        WrongNestedHierarchyViolation wrongNestedHierarchyViolation = new WrongNestedHierarchyViolation(fragment, fragment2, i);
        FragmentStrictMode fragmentStrictMode = INSTANCE;
        fragmentStrictMode.logIfDebuggingEnabled(wrongNestedHierarchyViolation);
        Policy nearestPolicy = fragmentStrictMode.getNearestPolicy(fragment);
        if (nearestPolicy.getFlags$fragment_release().contains(Flag.DETECT_WRONG_NESTED_HIERARCHY) && fragmentStrictMode.shouldHandlePolicyViolation(nearestPolicy, fragment.getClass(), wrongNestedHierarchyViolation.getClass())) {
            fragmentStrictMode.handlePolicyViolation(nearestPolicy, wrongNestedHierarchyViolation);
        }
    }

    public static final void onSetRetainInstanceUsage(Fragment fragment) {
        Intrinsics.checkNotNullParameter(fragment, "fragment");
        SetRetainInstanceUsageViolation setRetainInstanceUsageViolation = new SetRetainInstanceUsageViolation(fragment);
        FragmentStrictMode fragmentStrictMode = INSTANCE;
        fragmentStrictMode.logIfDebuggingEnabled(setRetainInstanceUsageViolation);
        Policy nearestPolicy = fragmentStrictMode.getNearestPolicy(fragment);
        if (nearestPolicy.getFlags$fragment_release().contains(Flag.DETECT_RETAIN_INSTANCE_USAGE) && fragmentStrictMode.shouldHandlePolicyViolation(nearestPolicy, fragment.getClass(), setRetainInstanceUsageViolation.getClass())) {
            fragmentStrictMode.handlePolicyViolation(nearestPolicy, setRetainInstanceUsageViolation);
        }
    }

    public static final void onGetRetainInstanceUsage(Fragment fragment) {
        Intrinsics.checkNotNullParameter(fragment, "fragment");
        GetRetainInstanceUsageViolation getRetainInstanceUsageViolation = new GetRetainInstanceUsageViolation(fragment);
        FragmentStrictMode fragmentStrictMode = INSTANCE;
        fragmentStrictMode.logIfDebuggingEnabled(getRetainInstanceUsageViolation);
        Policy nearestPolicy = fragmentStrictMode.getNearestPolicy(fragment);
        if (nearestPolicy.getFlags$fragment_release().contains(Flag.DETECT_RETAIN_INSTANCE_USAGE) && fragmentStrictMode.shouldHandlePolicyViolation(nearestPolicy, fragment.getClass(), getRetainInstanceUsageViolation.getClass())) {
            fragmentStrictMode.handlePolicyViolation(nearestPolicy, getRetainInstanceUsageViolation);
        }
    }

    public static final void onSetUserVisibleHint(Fragment fragment, boolean z) {
        Intrinsics.checkNotNullParameter(fragment, "fragment");
        SetUserVisibleHintViolation setUserVisibleHintViolation = new SetUserVisibleHintViolation(fragment, z);
        FragmentStrictMode fragmentStrictMode = INSTANCE;
        fragmentStrictMode.logIfDebuggingEnabled(setUserVisibleHintViolation);
        Policy nearestPolicy = fragmentStrictMode.getNearestPolicy(fragment);
        if (nearestPolicy.getFlags$fragment_release().contains(Flag.DETECT_SET_USER_VISIBLE_HINT) && fragmentStrictMode.shouldHandlePolicyViolation(nearestPolicy, fragment.getClass(), setUserVisibleHintViolation.getClass())) {
            fragmentStrictMode.handlePolicyViolation(nearestPolicy, setUserVisibleHintViolation);
        }
    }

    public static final void onSetTargetFragmentUsage(Fragment fragment, Fragment fragment2, int i) {
        Intrinsics.checkNotNullParameter(fragment, "violatingFragment");
        Intrinsics.checkNotNullParameter(fragment2, "targetFragment");
        SetTargetFragmentUsageViolation setTargetFragmentUsageViolation = new SetTargetFragmentUsageViolation(fragment, fragment2, i);
        FragmentStrictMode fragmentStrictMode = INSTANCE;
        fragmentStrictMode.logIfDebuggingEnabled(setTargetFragmentUsageViolation);
        Policy nearestPolicy = fragmentStrictMode.getNearestPolicy(fragment);
        if (nearestPolicy.getFlags$fragment_release().contains(Flag.DETECT_TARGET_FRAGMENT_USAGE) && fragmentStrictMode.shouldHandlePolicyViolation(nearestPolicy, fragment.getClass(), setTargetFragmentUsageViolation.getClass())) {
            fragmentStrictMode.handlePolicyViolation(nearestPolicy, setTargetFragmentUsageViolation);
        }
    }

    public static final void onGetTargetFragmentUsage(Fragment fragment) {
        Intrinsics.checkNotNullParameter(fragment, "fragment");
        GetTargetFragmentUsageViolation getTargetFragmentUsageViolation = new GetTargetFragmentUsageViolation(fragment);
        FragmentStrictMode fragmentStrictMode = INSTANCE;
        fragmentStrictMode.logIfDebuggingEnabled(getTargetFragmentUsageViolation);
        Policy nearestPolicy = fragmentStrictMode.getNearestPolicy(fragment);
        if (nearestPolicy.getFlags$fragment_release().contains(Flag.DETECT_TARGET_FRAGMENT_USAGE) && fragmentStrictMode.shouldHandlePolicyViolation(nearestPolicy, fragment.getClass(), getTargetFragmentUsageViolation.getClass())) {
            fragmentStrictMode.handlePolicyViolation(nearestPolicy, getTargetFragmentUsageViolation);
        }
    }

    public static final void onGetTargetFragmentRequestCodeUsage(Fragment fragment) {
        Intrinsics.checkNotNullParameter(fragment, "fragment");
        GetTargetFragmentRequestCodeUsageViolation getTargetFragmentRequestCodeUsageViolation = new GetTargetFragmentRequestCodeUsageViolation(fragment);
        FragmentStrictMode fragmentStrictMode = INSTANCE;
        fragmentStrictMode.logIfDebuggingEnabled(getTargetFragmentRequestCodeUsageViolation);
        Policy nearestPolicy = fragmentStrictMode.getNearestPolicy(fragment);
        if (nearestPolicy.getFlags$fragment_release().contains(Flag.DETECT_TARGET_FRAGMENT_USAGE) && fragmentStrictMode.shouldHandlePolicyViolation(nearestPolicy, fragment.getClass(), getTargetFragmentRequestCodeUsageViolation.getClass())) {
            fragmentStrictMode.handlePolicyViolation(nearestPolicy, getTargetFragmentRequestCodeUsageViolation);
        }
    }

    public static final void onWrongFragmentContainer(Fragment fragment, ViewGroup viewGroup) {
        Intrinsics.checkNotNullParameter(fragment, "fragment");
        Intrinsics.checkNotNullParameter(viewGroup, "container");
        WrongFragmentContainerViolation wrongFragmentContainerViolation = new WrongFragmentContainerViolation(fragment, viewGroup);
        FragmentStrictMode fragmentStrictMode = INSTANCE;
        fragmentStrictMode.logIfDebuggingEnabled(wrongFragmentContainerViolation);
        Policy nearestPolicy = fragmentStrictMode.getNearestPolicy(fragment);
        if (nearestPolicy.getFlags$fragment_release().contains(Flag.DETECT_WRONG_FRAGMENT_CONTAINER) && fragmentStrictMode.shouldHandlePolicyViolation(nearestPolicy, fragment.getClass(), wrongFragmentContainerViolation.getClass())) {
            fragmentStrictMode.handlePolicyViolation(nearestPolicy, wrongFragmentContainerViolation);
        }
    }

    private final void logIfDebuggingEnabled(Violation violation) {
        if (FragmentManager.isLoggingEnabled(3)) {
            Log.d("FragmentManager", "StrictMode violation in " + violation.getFragment().getClass().getName(), violation);
        }
    }

    private final boolean shouldHandlePolicyViolation(Policy policy, Class cls, Class cls2) {
        Set set = (Set) policy.getMAllowedViolations$fragment_release().get(cls.getName());
        if (set == null) {
            return true;
        }
        if (Intrinsics.areEqual((Object) cls2.getSuperclass(), (Object) Violation.class) || !CollectionsKt.contains(set, cls2.getSuperclass())) {
            return !set.contains(cls2);
        }
        return false;
    }

    private final void handlePolicyViolation(Policy policy, Violation violation) {
        Fragment fragment = violation.getFragment();
        String name = fragment.getClass().getName();
        if (policy.getFlags$fragment_release().contains(Flag.PENALTY_LOG)) {
            Log.d("FragmentStrictMode", "Policy violation in " + name, violation);
        }
        policy.getListener$fragment_release();
        if (policy.getFlags$fragment_release().contains(Flag.PENALTY_DEATH)) {
            runOnHostThread(fragment, new FragmentStrictMode$$ExternalSyntheticLambda0(name, violation));
        }
    }

    /* access modifiers changed from: private */
    public static final void handlePolicyViolation$lambda$1(String str, Violation violation) {
        Intrinsics.checkNotNullParameter(violation, "$violation");
        Log.e("FragmentStrictMode", "Policy violation with PENALTY_DEATH in " + str, violation);
        throw violation;
    }

    private final void runOnHostThread(Fragment fragment, Runnable runnable) {
        if (fragment.isAdded()) {
            Handler handler = fragment.getParentFragmentManager().getHost().getHandler();
            Intrinsics.checkNotNullExpressionValue(handler, "fragment.parentFragmentManager.host.handler");
            if (Intrinsics.areEqual((Object) handler.getLooper(), (Object) Looper.myLooper())) {
                runnable.run();
            } else {
                handler.post(runnable);
            }
        } else {
            runnable.run();
        }
    }

    public static final class Policy {
        public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
        public static final Policy LAX = new Policy(SetsKt.emptySet(), (OnViolationListener) null, MapsKt.emptyMap());
        private final Set flags;
        private final Map mAllowedViolations;

        public final OnViolationListener getListener$fragment_release() {
            return null;
        }

        public Policy(Set set, OnViolationListener onViolationListener, Map map) {
            Intrinsics.checkNotNullParameter(set, "flags");
            Intrinsics.checkNotNullParameter(map, "allowedViolations");
            this.flags = set;
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            for (Map.Entry entry : map.entrySet()) {
                linkedHashMap.put((String) entry.getKey(), (Set) entry.getValue());
            }
            this.mAllowedViolations = linkedHashMap;
        }

        public final Set getFlags$fragment_release() {
            return this.flags;
        }

        public final Map getMAllowedViolations$fragment_release() {
            return this.mAllowedViolations;
        }

        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }
        }
    }
}
