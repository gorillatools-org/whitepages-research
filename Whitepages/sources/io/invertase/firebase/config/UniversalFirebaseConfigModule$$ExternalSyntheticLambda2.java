package io.invertase.firebase.config;

import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.FirebaseApp;
import java.util.HashMap;

public final /* synthetic */ class UniversalFirebaseConfigModule$$ExternalSyntheticLambda2 implements Runnable {
    public final /* synthetic */ HashMap f$0;
    public final /* synthetic */ FirebaseApp f$1;
    public final /* synthetic */ TaskCompletionSource f$2;

    public /* synthetic */ UniversalFirebaseConfigModule$$ExternalSyntheticLambda2(HashMap hashMap, FirebaseApp firebaseApp, TaskCompletionSource taskCompletionSource) {
        this.f$0 = hashMap;
        this.f$1 = firebaseApp;
        this.f$2 = taskCompletionSource;
    }

    public final void run() {
        UniversalFirebaseConfigModule.lambda$setCustomSignals$3(this.f$0, this.f$1, this.f$2);
    }
}
