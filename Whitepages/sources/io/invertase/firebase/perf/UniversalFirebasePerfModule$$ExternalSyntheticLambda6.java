package io.invertase.firebase.perf;

import com.google.firebase.perf.FirebasePerformance;
import java.util.concurrent.Callable;

public final /* synthetic */ class UniversalFirebasePerfModule$$ExternalSyntheticLambda6 implements Callable {
    public final /* synthetic */ Boolean f$0;

    public /* synthetic */ UniversalFirebasePerfModule$$ExternalSyntheticLambda6(Boolean bool) {
        this.f$0 = bool;
    }

    public final Object call() {
        return FirebasePerformance.getInstance().setPerformanceCollectionEnabled(this.f$0);
    }
}
