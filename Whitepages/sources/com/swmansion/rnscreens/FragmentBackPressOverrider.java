package com.swmansion.rnscreens;

import androidx.activity.OnBackPressedCallback;
import androidx.activity.OnBackPressedDispatcher;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import kotlin.jvm.internal.Intrinsics;

public final class FragmentBackPressOverrider {
    private final Fragment fragment;
    private boolean isCallbackAdded;
    private final OnBackPressedCallback onBackPressedCallback;
    private boolean overrideBackAction = true;

    public FragmentBackPressOverrider(Fragment fragment2, OnBackPressedCallback onBackPressedCallback2) {
        Intrinsics.checkNotNullParameter(fragment2, "fragment");
        Intrinsics.checkNotNullParameter(onBackPressedCallback2, "onBackPressedCallback");
        this.fragment = fragment2;
        this.onBackPressedCallback = onBackPressedCallback2;
    }

    public final boolean getOverrideBackAction() {
        return this.overrideBackAction;
    }

    public final void setOverrideBackAction(boolean z) {
        this.overrideBackAction = z;
    }

    public final void maybeAddBackCallback() {
        OnBackPressedDispatcher onBackPressedDispatcher;
        if (!this.isCallbackAdded && this.overrideBackAction) {
            FragmentActivity activity = this.fragment.getActivity();
            if (!(activity == null || (onBackPressedDispatcher = activity.getOnBackPressedDispatcher()) == null)) {
                onBackPressedDispatcher.addCallback(this.fragment, this.onBackPressedCallback);
            }
            this.isCallbackAdded = true;
        }
    }

    public final void removeBackCallbackIfAdded() {
        if (this.isCallbackAdded) {
            this.onBackPressedCallback.remove();
            this.isCallbackAdded = false;
        }
    }
}
