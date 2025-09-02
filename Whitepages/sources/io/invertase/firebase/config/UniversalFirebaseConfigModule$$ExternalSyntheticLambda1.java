package io.invertase.firebase.config;

import android.os.Bundle;
import com.google.firebase.FirebaseApp;
import java.util.concurrent.Callable;

public final /* synthetic */ class UniversalFirebaseConfigModule$$ExternalSyntheticLambda1 implements Callable {
    public final /* synthetic */ Bundle f$0;
    public final /* synthetic */ FirebaseApp f$1;

    public /* synthetic */ UniversalFirebaseConfigModule$$ExternalSyntheticLambda1(Bundle bundle, FirebaseApp firebaseApp) {
        this.f$0 = bundle;
        this.f$1 = firebaseApp;
    }

    public final Object call() {
        return UniversalFirebaseConfigModule.lambda$setConfigSettings$1(this.f$0, this.f$1);
    }
}
