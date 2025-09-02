package io.invertase.firebase.perf;

import com.facebook.react.bridge.Promise;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public final /* synthetic */ class ReactNativeFirebasePerfModule$$ExternalSyntheticLambda2 implements OnCompleteListener {
    public final /* synthetic */ Promise f$0;

    public /* synthetic */ ReactNativeFirebasePerfModule$$ExternalSyntheticLambda2(Promise promise) {
        this.f$0 = promise;
    }

    public final void onComplete(Task task) {
        ReactNativeFirebasePerfModule.lambda$stopHttpMetric$6(this.f$0, task);
    }
}
