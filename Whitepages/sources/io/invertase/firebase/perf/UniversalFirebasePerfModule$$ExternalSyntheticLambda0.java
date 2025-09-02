package io.invertase.firebase.perf;

import android.os.Bundle;
import java.util.concurrent.Callable;

public final /* synthetic */ class UniversalFirebasePerfModule$$ExternalSyntheticLambda0 implements Callable {
    public final /* synthetic */ int f$0;
    public final /* synthetic */ Bundle f$1;
    public final /* synthetic */ Bundle f$2;

    public /* synthetic */ UniversalFirebasePerfModule$$ExternalSyntheticLambda0(int i, Bundle bundle, Bundle bundle2) {
        this.f$0 = i;
        this.f$1 = bundle;
        this.f$2 = bundle2;
    }

    public final Object call() {
        return UniversalFirebasePerfModule.lambda$stopTrace$2(this.f$0, this.f$1, this.f$2);
    }
}
