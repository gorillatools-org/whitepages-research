package androidx.lifecycle;

import android.app.Activity;
import kotlin.jvm.internal.Intrinsics;

public final class ProcessLifecycleOwner$attach$1$onActivityPreCreated$1 extends EmptyActivityLifecycleCallbacks {
    final /* synthetic */ ProcessLifecycleOwner this$0;

    ProcessLifecycleOwner$attach$1$onActivityPreCreated$1(ProcessLifecycleOwner processLifecycleOwner) {
        this.this$0 = processLifecycleOwner;
    }

    public void onActivityPostStarted(Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        this.this$0.activityStarted$lifecycle_process_release();
    }

    public void onActivityPostResumed(Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        this.this$0.activityResumed$lifecycle_process_release();
    }
}
