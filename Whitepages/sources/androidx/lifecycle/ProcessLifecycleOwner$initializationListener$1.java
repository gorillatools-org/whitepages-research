package androidx.lifecycle;

import androidx.lifecycle.ReportFragment;

public final class ProcessLifecycleOwner$initializationListener$1 implements ReportFragment.ActivityInitializationListener {
    final /* synthetic */ ProcessLifecycleOwner this$0;

    public void onCreate() {
    }

    ProcessLifecycleOwner$initializationListener$1(ProcessLifecycleOwner processLifecycleOwner) {
        this.this$0 = processLifecycleOwner;
    }

    public void onStart() {
        this.this$0.activityStarted$lifecycle_process_release();
    }

    public void onResume() {
        this.this$0.activityResumed$lifecycle_process_release();
    }
}
