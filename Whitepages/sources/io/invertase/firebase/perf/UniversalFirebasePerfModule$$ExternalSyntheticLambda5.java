package io.invertase.firebase.perf;

import android.app.Activity;
import java.util.concurrent.Callable;

public final /* synthetic */ class UniversalFirebasePerfModule$$ExternalSyntheticLambda5 implements Callable {
    public final /* synthetic */ Activity f$0;
    public final /* synthetic */ String f$1;
    public final /* synthetic */ int f$2;

    public /* synthetic */ UniversalFirebasePerfModule$$ExternalSyntheticLambda5(Activity activity, String str, int i) {
        this.f$0 = activity;
        this.f$1 = str;
        this.f$2 = i;
    }

    public final Object call() {
        return UniversalFirebasePerfModule.lambda$startScreenTrace$3(this.f$0, this.f$1, this.f$2);
    }
}
