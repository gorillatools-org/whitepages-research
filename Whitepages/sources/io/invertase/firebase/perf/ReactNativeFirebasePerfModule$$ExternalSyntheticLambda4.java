package io.invertase.firebase.perf;

import com.facebook.react.bridge.Promise;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public final /* synthetic */ class ReactNativeFirebasePerfModule$$ExternalSyntheticLambda4 implements OnCompleteListener {
    public final /* synthetic */ Promise f$0;

    public /* synthetic */ ReactNativeFirebasePerfModule$$ExternalSyntheticLambda4(Promise promise) {
        this.f$0 = promise;
    }

    public final void onComplete(Task task) {
        ReactNativeFirebasePerfModule.lambda$stopScreenTrace$4(this.f$0, task);
    }
}
