package okhttp3.internal.platform.android;

import javax.net.ssl.SSLSocket;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import okhttp3.internal.platform.android.DeferredSocketAdapter;

public final class AndroidSocketAdapter$Companion$factory$1 implements DeferredSocketAdapter.Factory {
    final /* synthetic */ String $packageName;

    AndroidSocketAdapter$Companion$factory$1(String str) {
        this.$packageName = str;
    }

    public boolean matchesSocket(SSLSocket sSLSocket) {
        Intrinsics.checkNotNullParameter(sSLSocket, "sslSocket");
        String name = sSLSocket.getClass().getName();
        Intrinsics.checkNotNullExpressionValue(name, "sslSocket.javaClass.name");
        return StringsKt.startsWith$default(name, this.$packageName + '.', false, 2, (Object) null);
    }

    public SocketAdapter create(SSLSocket sSLSocket) {
        Intrinsics.checkNotNullParameter(sSLSocket, "sslSocket");
        return AndroidSocketAdapter.Companion.build(sSLSocket.getClass());
    }
}
