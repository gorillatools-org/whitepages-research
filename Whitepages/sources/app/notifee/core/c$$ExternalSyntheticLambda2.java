package app.notifee.core;

import java.util.List;
import java.util.concurrent.Callable;

public final /* synthetic */ class c$$ExternalSyntheticLambda2 implements Callable {
    public final /* synthetic */ List f$0;
    public final /* synthetic */ int f$1;
    public final /* synthetic */ String f$2;

    public /* synthetic */ c$$ExternalSyntheticLambda2(List list, int i, String str) {
        this.f$0 = list;
        this.f$1 = i;
        this.f$2 = str;
    }

    public final Object call() {
        return c.a(this.f$0, this.f$1, this.f$2);
    }
}
