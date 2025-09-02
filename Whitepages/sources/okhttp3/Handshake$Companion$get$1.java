package okhttp3;

import java.security.cert.Certificate;
import java.util.List;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

final class Handshake$Companion$get$1 extends Lambda implements Function0 {
    final /* synthetic */ List $peerCertificatesCopy;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    Handshake$Companion$get$1(List list) {
        super(0);
        this.$peerCertificatesCopy = list;
    }

    public final List<Certificate> invoke() {
        return this.$peerCertificatesCopy;
    }
}
