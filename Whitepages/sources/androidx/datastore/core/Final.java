package androidx.datastore.core;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class Final extends State {
    private final Throwable finalException;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public Final(Throwable th) {
        super(Integer.MAX_VALUE, (DefaultConstructorMarker) null);
        Intrinsics.checkNotNullParameter(th, "finalException");
        this.finalException = th;
    }

    public final Throwable getFinalException() {
        return this.finalException;
    }
}
