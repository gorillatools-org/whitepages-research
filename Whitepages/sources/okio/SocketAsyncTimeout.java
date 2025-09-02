package okio;

import java.io.IOException;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;
import kotlin.jvm.internal.Intrinsics;

final class SocketAsyncTimeout extends AsyncTimeout {
    private final Socket socket;

    public SocketAsyncTimeout(Socket socket2) {
        Intrinsics.checkNotNullParameter(socket2, "socket");
        this.socket = socket2;
    }

    /* access modifiers changed from: protected */
    public IOException newTimeoutException(IOException iOException) {
        SocketTimeoutException socketTimeoutException = new SocketTimeoutException("timeout");
        if (iOException != null) {
            socketTimeoutException.initCause(iOException);
        }
        return socketTimeoutException;
    }

    /* access modifiers changed from: protected */
    public void timedOut() {
        try {
            this.socket.close();
        } catch (Exception e) {
            Logger access$getLogger$p = Okio__JvmOkioKt.logger;
            Level level = Level.WARNING;
            access$getLogger$p.log(level, "Failed to close timed out socket " + this.socket, e);
        } catch (AssertionError e2) {
            if (Okio.isAndroidGetsocknameError(e2)) {
                Logger access$getLogger$p2 = Okio__JvmOkioKt.logger;
                Level level2 = Level.WARNING;
                access$getLogger$p2.log(level2, "Failed to close timed out socket " + this.socket, e2);
                return;
            }
            throw e2;
        }
    }
}
