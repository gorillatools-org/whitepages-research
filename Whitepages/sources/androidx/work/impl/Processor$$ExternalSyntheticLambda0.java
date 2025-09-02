package androidx.work.impl;

import java.util.ArrayList;
import java.util.concurrent.Callable;

public final /* synthetic */ class Processor$$ExternalSyntheticLambda0 implements Callable {
    public final /* synthetic */ Processor f$0;
    public final /* synthetic */ ArrayList f$1;
    public final /* synthetic */ String f$2;

    public /* synthetic */ Processor$$ExternalSyntheticLambda0(Processor processor, ArrayList arrayList, String str) {
        this.f$0 = processor;
        this.f$1 = arrayList;
        this.f$2 = str;
    }

    public final Object call() {
        return this.f$0.lambda$startWork$0(this.f$1, this.f$2);
    }
}
