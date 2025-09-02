package io.invertase.firebase.config;

import com.google.firebase.FirebaseApp;
import java.util.concurrent.Callable;

public final /* synthetic */ class UniversalFirebaseConfigModule$$ExternalSyntheticLambda0 implements Callable {
    public final /* synthetic */ FirebaseApp f$0;
    public final /* synthetic */ long f$1;

    public /* synthetic */ UniversalFirebaseConfigModule$$ExternalSyntheticLambda0(FirebaseApp firebaseApp, long j) {
        this.f$0 = firebaseApp;
        this.f$1 = j;
    }

    public final Object call() {
        return UniversalFirebaseConfigModule.lambda$fetch$0(this.f$0, this.f$1);
    }
}
