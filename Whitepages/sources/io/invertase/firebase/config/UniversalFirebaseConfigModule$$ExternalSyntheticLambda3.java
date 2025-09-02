package io.invertase.firebase.config;

import com.google.firebase.FirebaseApp;
import java.util.concurrent.Callable;

public final /* synthetic */ class UniversalFirebaseConfigModule$$ExternalSyntheticLambda3 implements Callable {
    public final /* synthetic */ UniversalFirebaseConfigModule f$0;
    public final /* synthetic */ String f$1;
    public final /* synthetic */ FirebaseApp f$2;

    public /* synthetic */ UniversalFirebaseConfigModule$$ExternalSyntheticLambda3(UniversalFirebaseConfigModule universalFirebaseConfigModule, String str, FirebaseApp firebaseApp) {
        this.f$0 = universalFirebaseConfigModule;
        this.f$1 = str;
        this.f$2 = firebaseApp;
    }

    public final Object call() {
        return this.f$0.lambda$setDefaultsFromResource$2(this.f$1, this.f$2);
    }
}
