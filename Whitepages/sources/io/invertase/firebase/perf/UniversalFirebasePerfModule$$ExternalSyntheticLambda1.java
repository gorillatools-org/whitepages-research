package io.invertase.firebase.perf;

import java.util.concurrent.Callable;

public final /* synthetic */ class UniversalFirebasePerfModule$$ExternalSyntheticLambda1 implements Callable {
    public final /* synthetic */ String f$0;
    public final /* synthetic */ String f$1;
    public final /* synthetic */ int f$2;

    public /* synthetic */ UniversalFirebasePerfModule$$ExternalSyntheticLambda1(String str, String str2, int i) {
        this.f$0 = str;
        this.f$1 = str2;
        this.f$2 = i;
    }

    public final Object call() {
        return UniversalFirebasePerfModule.lambda$startHttpMetric$5(this.f$0, this.f$1, this.f$2);
    }
}
