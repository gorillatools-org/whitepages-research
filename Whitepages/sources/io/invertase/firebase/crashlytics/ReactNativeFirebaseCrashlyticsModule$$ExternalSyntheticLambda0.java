package io.invertase.firebase.crashlytics;

import com.facebook.react.bridge.Promise;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public final /* synthetic */ class ReactNativeFirebaseCrashlyticsModule$$ExternalSyntheticLambda0 implements OnCompleteListener {
    public final /* synthetic */ Promise f$0;

    public /* synthetic */ ReactNativeFirebaseCrashlyticsModule$$ExternalSyntheticLambda0(Promise promise) {
        this.f$0 = promise;
    }

    public final void onComplete(Task task) {
        ReactNativeFirebaseCrashlyticsModule.lambda$checkForUnsentReports$0(this.f$0, task);
    }
}
