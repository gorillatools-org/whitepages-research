package androidx.datastore.core;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class ReadException extends State {
    private final Throwable readException;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ReadException(Throwable th, int i) {
        super(i, (DefaultConstructorMarker) null);
        Intrinsics.checkNotNullParameter(th, "readException");
        this.readException = th;
    }

    public final Throwable getReadException() {
        return this.readException;
    }
}
