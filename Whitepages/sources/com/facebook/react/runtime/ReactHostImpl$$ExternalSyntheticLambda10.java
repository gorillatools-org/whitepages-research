package com.facebook.react.runtime;

import java.lang.ref.WeakReference;

public final /* synthetic */ class ReactHostImpl$$ExternalSyntheticLambda10 implements Runnable {
    public final /* synthetic */ WeakReference f$0;
    public final /* synthetic */ int f$1;

    public /* synthetic */ ReactHostImpl$$ExternalSyntheticLambda10(WeakReference weakReference, int i) {
        this.f$0 = weakReference;
        this.f$1 = i;
    }

    public final void run() {
        ReactHostImpl.lambda$createMemoryPressureListener$8(this.f$0, this.f$1);
    }
}
