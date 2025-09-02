package okhttp3;

import java.security.cert.Certificate;
import java.util.List;
import javax.net.ssl.SSLPeerUnverifiedException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

final class Handshake$peerCertificates$2 extends Lambda implements Function0 {
    final /* synthetic */ Function0 $peerCertificatesFn;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    Handshake$peerCertificates$2(Function0 function0) {
        super(0);
        this.$peerCertificatesFn = function0;
    }

    public final List<Certificate> invoke() {
        try {
            return (List) this.$peerCertificatesFn.invoke();
        } catch (SSLPeerUnverifiedException unused) {
            return CollectionsKt.emptyList();
        }
    }
}
