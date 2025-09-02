package androidx.work.impl;

import androidx.work.impl.model.WorkGenerationalId;

public final /* synthetic */ class Processor$$ExternalSyntheticLambda1 implements Runnable {
    public final /* synthetic */ Processor f$0;
    public final /* synthetic */ WorkGenerationalId f$1;
    public final /* synthetic */ boolean f$2;

    public /* synthetic */ Processor$$ExternalSyntheticLambda1(Processor processor, WorkGenerationalId workGenerationalId, boolean z) {
        this.f$0 = processor;
        this.f$1 = workGenerationalId;
        this.f$2 = z;
    }

    public final void run() {
        this.f$0.lambda$runOnExecuted$1(this.f$1, this.f$2);
    }
}
