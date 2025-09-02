package androidx.work.impl.utils;

import java.util.concurrent.Callable;

public final /* synthetic */ class IdGenerator$$ExternalSyntheticLambda0 implements Callable {
    public final /* synthetic */ IdGenerator f$0;

    public /* synthetic */ IdGenerator$$ExternalSyntheticLambda0(IdGenerator idGenerator) {
        this.f$0 = idGenerator;
    }

    public final Object call() {
        return IdGenerator.nextAlarmManagerId$lambda$1(this.f$0);
    }
}
