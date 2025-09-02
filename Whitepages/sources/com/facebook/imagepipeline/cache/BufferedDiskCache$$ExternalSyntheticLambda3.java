package com.facebook.imagepipeline.cache;

import java.util.concurrent.Callable;

public final /* synthetic */ class BufferedDiskCache$$ExternalSyntheticLambda3 implements Callable {
    public final /* synthetic */ Object f$0;
    public final /* synthetic */ BufferedDiskCache f$1;

    public /* synthetic */ BufferedDiskCache$$ExternalSyntheticLambda3(Object obj, BufferedDiskCache bufferedDiskCache) {
        this.f$0 = obj;
        this.f$1 = bufferedDiskCache;
    }

    public final Object call() {
        return BufferedDiskCache.clearAll$lambda$8(this.f$0, this.f$1);
    }
}
