package androidx.work.impl.utils;

import java.util.concurrent.Callable;

public final /* synthetic */ class IdGenerator$$ExternalSyntheticLambda1 implements Callable {
    public final /* synthetic */ IdGenerator f$0;
    public final /* synthetic */ int f$1;
    public final /* synthetic */ int f$2;

    public /* synthetic */ IdGenerator$$ExternalSyntheticLambda1(IdGenerator idGenerator, int i, int i2) {
        this.f$0 = idGenerator;
        this.f$1 = i;
        this.f$2 = i2;
    }

    public final Object call() {
        return IdGenerator.nextJobSchedulerIdWithRange$lambda$0(this.f$0, this.f$1, this.f$2);
    }
}
