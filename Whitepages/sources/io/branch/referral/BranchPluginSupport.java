package io.branch.referral;

import android.content.Context;

public class BranchPluginSupport {
    private final Context context_;
    private final SystemObserver systemObserver_ = new SystemObserverInstance();

    BranchPluginSupport(Context context) {
        this.context_ = context;
    }

    private class SystemObserverInstance extends SystemObserver {
        public SystemObserverInstance() {
        }
    }
}
