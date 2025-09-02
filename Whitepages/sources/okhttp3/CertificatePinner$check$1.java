package okhttp3;

import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import okhttp3.internal.tls.CertificateChainCleaner;

final class CertificatePinner$check$1 extends Lambda implements Function0 {
    final /* synthetic */ String $hostname;
    final /* synthetic */ List $peerCertificates;
    final /* synthetic */ CertificatePinner this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    CertificatePinner$check$1(CertificatePinner certificatePinner, List list, String str) {
        super(0);
        this.this$0 = certificatePinner;
        this.$peerCertificates = list;
        this.$hostname = str;
    }

    public final List<X509Certificate> invoke() {
        List<Certificate> list;
        CertificateChainCleaner certificateChainCleaner$okhttp = this.this$0.getCertificateChainCleaner$okhttp();
        if (certificateChainCleaner$okhttp == null || (list = certificateChainCleaner$okhttp.clean(this.$peerCertificates, this.$hostname)) == null) {
            list = this.$peerCertificates;
        }
        Iterable<Certificate> iterable = list;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
        for (Certificate certificate : iterable) {
            if (certificate != null) {
                arrayList.add((X509Certificate) certificate);
            } else {
                throw new NullPointerException("null cannot be cast to non-null type java.security.cert.X509Certificate");
            }
        }
        return arrayList;
    }
}
