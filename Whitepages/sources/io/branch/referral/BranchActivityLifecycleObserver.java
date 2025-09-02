package io.branch.referral;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import io.branch.referral.Branch;
import java.lang.ref.WeakReference;
import java.util.HashSet;
import java.util.Set;

class BranchActivityLifecycleObserver implements Application.ActivityLifecycleCallbacks {
    private Set activitiesOnStack_ = new HashSet();
    private int activityCnt_ = 0;

    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    BranchActivityLifecycleObserver() {
    }

    public void onActivityCreated(Activity activity, Bundle bundle) {
        BranchLogger.v("onActivityCreated, activity = " + activity);
        Branch instance = Branch.getInstance();
        if (instance != null) {
            instance.setIntentState(Branch.INTENT_STATE.PENDING);
        }
    }

    public void onActivityStarted(Activity activity) {
        BranchLogger.v("onActivityStarted, activity = " + activity);
        Branch instance = Branch.getInstance();
        if (instance != null) {
            instance.currentActivityReference_ = new WeakReference(activity);
            instance.setIntentState(Branch.INTENT_STATE.PENDING);
            this.activityCnt_++;
        }
    }

    public void onActivityResumed(Activity activity) {
        BranchLogger.v("onActivityResumed, activity = " + activity);
        Branch instance = Branch.getInstance();
        if (instance != null) {
            if (!Branch.bypassCurrentActivityIntentState()) {
                instance.onIntentReady(activity);
            }
            if (instance.getInitState() == Branch.SESSION_STATE.UNINITIALISED && !Branch.disableAutoSessionInitialization) {
                if (Branch.getPluginName() == null) {
                    BranchLogger.v("initializing session on user's behalf (onActivityResumed called but SESSION_STATE = UNINITIALISED)");
                    Branch.sessionBuilder(activity).isAutoInitialization(true).init();
                } else {
                    BranchLogger.v("onActivityResumed called and SESSION_STATE = UNINITIALISED, however this is a " + Branch.getPluginName() + " plugin, so we are NOT initializing session on user's behalf");
                }
            }
            this.activitiesOnStack_.add(activity.toString());
        }
    }

    public void onActivityPaused(Activity activity) {
        BranchLogger.v("onActivityPaused, activity = " + activity);
        Branch instance = Branch.getInstance();
        if (instance != null && instance.getShareLinkManager() != null) {
            instance.getShareLinkManager().cancelShareLinkDialog(true);
        }
    }

    public void onActivityStopped(Activity activity) {
        BranchLogger.v("onActivityStopped, activity = " + activity);
        Branch instance = Branch.getInstance();
        if (instance != null) {
            int i = this.activityCnt_ - 1;
            this.activityCnt_ = i;
            if (i < 1) {
                instance.setInstantDeepLinkPossible(false);
                instance.closeSessionInternal();
            }
        }
    }

    public void onActivityDestroyed(Activity activity) {
        BranchLogger.v("onActivityDestroyed, activity = " + activity);
        Branch instance = Branch.getInstance();
        if (instance != null) {
            if (instance.getCurrentActivity() == activity) {
                instance.currentActivityReference_.clear();
            }
            this.activitiesOnStack_.remove(activity.toString());
        }
    }

    /* access modifiers changed from: package-private */
    public boolean isCurrentActivityLaunchedFromStack() {
        Branch instance = Branch.getInstance();
        if (instance == null || instance.getCurrentActivity() == null) {
            return false;
        }
        return this.activitiesOnStack_.contains(instance.getCurrentActivity().toString());
    }
}
