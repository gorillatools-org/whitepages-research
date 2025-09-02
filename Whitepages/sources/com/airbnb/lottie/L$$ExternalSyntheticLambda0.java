package com.airbnb.lottie;

import android.content.Context;
import com.airbnb.lottie.network.LottieNetworkCacheProvider;
import java.io.File;

public final /* synthetic */ class L$$ExternalSyntheticLambda0 implements LottieNetworkCacheProvider {
    public final /* synthetic */ Context f$0;

    public /* synthetic */ L$$ExternalSyntheticLambda0(Context context) {
        this.f$0 = context;
    }

    public final File getCacheDir() {
        return L.lambda$networkCache$0(this.f$0);
    }
}
