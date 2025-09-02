package okhttp3.internal.platform.android;

import javax.net.ssl.SSLSocket;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal.platform.BouncyCastlePlatform;
import okhttp3.internal.platform.android.DeferredSocketAdapter;

public final class BouncyCastleSocketAdapter$Companion$factory$1 implements DeferredSocketAdapter.Factory {
    BouncyCastleSocketAdapter$Companion$factory$1() {
    }

    public boolean matchesSocket(SSLSocket sSLSocket) {
        Intrinsics.checkNotNullParameter(sSLSocket, "sslSocket");
        boolean isSupported = BouncyCastlePlatform.Companion.isSupported();
        return false;
    }

    public SocketAdapter create(SSLSocket sSLSocket) {
        Intrinsics.checkNotNullParameter(sSLSocket, "sslSocket");
        return new BouncyCastleSocketAdapter();
    }
}
