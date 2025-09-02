package io.invertase.firebase.analytics;

import android.os.Bundle;
import java.util.concurrent.Callable;

public final /* synthetic */ class UniversalFirebaseAnalyticsModule$$ExternalSyntheticLambda5 implements Callable {
    public final /* synthetic */ UniversalFirebaseAnalyticsModule f$0;
    public final /* synthetic */ String f$1;
    public final /* synthetic */ Bundle f$2;

    public /* synthetic */ UniversalFirebaseAnalyticsModule$$ExternalSyntheticLambda5(UniversalFirebaseAnalyticsModule universalFirebaseAnalyticsModule, String str, Bundle bundle) {
        this.f$0 = universalFirebaseAnalyticsModule;
        this.f$1 = str;
        this.f$2 = bundle;
    }

    public final Object call() {
        return this.f$0.lambda$logEvent$0(this.f$1, this.f$2);
    }
}
