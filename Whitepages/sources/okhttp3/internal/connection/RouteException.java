package okhttp3.internal.connection;

import java.io.IOException;
import kotlin.ExceptionsKt;
import kotlin.jvm.internal.Intrinsics;

public final class RouteException extends RuntimeException {
    private final IOException firstConnectException;
    private IOException lastConnectException;

    public final IOException getFirstConnectException() {
        return this.firstConnectException;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public RouteException(IOException iOException) {
        super(iOException);
        Intrinsics.checkNotNullParameter(iOException, "firstConnectException");
        this.firstConnectException = iOException;
        this.lastConnectException = iOException;
    }

    public final IOException getLastConnectException() {
        return this.lastConnectException;
    }

    public final void addConnectException(IOException iOException) {
        Intrinsics.checkNotNullParameter(iOException, "e");
        ExceptionsKt.addSuppressed(this.firstConnectException, iOException);
        this.lastConnectException = iOException;
    }
}
