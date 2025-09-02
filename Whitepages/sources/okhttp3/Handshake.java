package okhttp3;

import java.io.IOException;
import java.security.Principal;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSession;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal.Util;

public final class Handshake {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private final CipherSuite cipherSuite;
    private final List<Certificate> localCertificates;
    private final Lazy peerCertificates$delegate;
    private final TlsVersion tlsVersion;

    public static final Handshake get(SSLSession sSLSession) throws IOException {
        return Companion.get(sSLSession);
    }

    public static final Handshake get(TlsVersion tlsVersion2, CipherSuite cipherSuite2, List<? extends Certificate> list, List<? extends Certificate> list2) {
        return Companion.get(tlsVersion2, cipherSuite2, list, list2);
    }

    public final List<Certificate> peerCertificates() {
        return (List) this.peerCertificates$delegate.getValue();
    }

    public Handshake(TlsVersion tlsVersion2, CipherSuite cipherSuite2, List<? extends Certificate> list, Function0 function0) {
        Intrinsics.checkNotNullParameter(tlsVersion2, "tlsVersion");
        Intrinsics.checkNotNullParameter(cipherSuite2, "cipherSuite");
        Intrinsics.checkNotNullParameter(list, "localCertificates");
        Intrinsics.checkNotNullParameter(function0, "peerCertificatesFn");
        this.tlsVersion = tlsVersion2;
        this.cipherSuite = cipherSuite2;
        this.localCertificates = list;
        this.peerCertificates$delegate = LazyKt.lazy(new Handshake$peerCertificates$2(function0));
    }

    public final TlsVersion tlsVersion() {
        return this.tlsVersion;
    }

    public final CipherSuite cipherSuite() {
        return this.cipherSuite;
    }

    public final List<Certificate> localCertificates() {
        return this.localCertificates;
    }

    /* renamed from: -deprecated_tlsVersion  reason: not valid java name */
    public final TlsVersion m978deprecated_tlsVersion() {
        return this.tlsVersion;
    }

    /* renamed from: -deprecated_cipherSuite  reason: not valid java name */
    public final CipherSuite m973deprecated_cipherSuite() {
        return this.cipherSuite;
    }

    /* renamed from: -deprecated_peerCertificates  reason: not valid java name */
    public final List<Certificate> m976deprecated_peerCertificates() {
        return peerCertificates();
    }

    public final Principal peerPrincipal() {
        Object firstOrNull = CollectionsKt.firstOrNull((List) peerCertificates());
        if (!(firstOrNull instanceof X509Certificate)) {
            firstOrNull = null;
        }
        X509Certificate x509Certificate = (X509Certificate) firstOrNull;
        if (x509Certificate != null) {
            return x509Certificate.getSubjectX500Principal();
        }
        return null;
    }

    /* renamed from: -deprecated_peerPrincipal  reason: not valid java name */
    public final Principal m977deprecated_peerPrincipal() {
        return peerPrincipal();
    }

    /* renamed from: -deprecated_localCertificates  reason: not valid java name */
    public final List<Certificate> m974deprecated_localCertificates() {
        return this.localCertificates;
    }

    public final Principal localPrincipal() {
        Object firstOrNull = CollectionsKt.firstOrNull((List) this.localCertificates);
        if (!(firstOrNull instanceof X509Certificate)) {
            firstOrNull = null;
        }
        X509Certificate x509Certificate = (X509Certificate) firstOrNull;
        if (x509Certificate != null) {
            return x509Certificate.getSubjectX500Principal();
        }
        return null;
    }

    /* renamed from: -deprecated_localPrincipal  reason: not valid java name */
    public final Principal m975deprecated_localPrincipal() {
        return localPrincipal();
    }

    public boolean equals(Object obj) {
        if (obj instanceof Handshake) {
            Handshake handshake = (Handshake) obj;
            return handshake.tlsVersion == this.tlsVersion && Intrinsics.areEqual((Object) handshake.cipherSuite, (Object) this.cipherSuite) && Intrinsics.areEqual((Object) handshake.peerCertificates(), (Object) peerCertificates()) && Intrinsics.areEqual((Object) handshake.localCertificates, (Object) this.localCertificates);
        }
    }

    public int hashCode() {
        return ((((((527 + this.tlsVersion.hashCode()) * 31) + this.cipherSuite.hashCode()) * 31) + peerCertificates().hashCode()) * 31) + this.localCertificates.hashCode();
    }

    public String toString() {
        Iterable<Certificate> peerCertificates = peerCertificates();
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(peerCertificates, 10));
        for (Certificate name : peerCertificates) {
            arrayList.add(getName(name));
        }
        String obj = arrayList.toString();
        StringBuilder sb = new StringBuilder();
        sb.append("Handshake{");
        sb.append("tlsVersion=");
        sb.append(this.tlsVersion);
        sb.append(' ');
        sb.append("cipherSuite=");
        sb.append(this.cipherSuite);
        sb.append(' ');
        sb.append("peerCertificates=");
        sb.append(obj);
        sb.append(' ');
        sb.append("localCertificates=");
        Iterable<Certificate> iterable = this.localCertificates;
        ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
        for (Certificate name2 : iterable) {
            arrayList2.add(getName(name2));
        }
        sb.append(arrayList2);
        sb.append('}');
        return sb.toString();
    }

    private final String getName(Certificate certificate) {
        if (certificate instanceof X509Certificate) {
            return ((X509Certificate) certificate).getSubjectDN().toString();
        }
        String type = certificate.getType();
        Intrinsics.checkNotNullExpressionValue(type, "type");
        return type;
    }

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final Handshake get(SSLSession sSLSession) throws IOException {
            List<Certificate> list;
            Intrinsics.checkNotNullParameter(sSLSession, "$this$handshake");
            String cipherSuite = sSLSession.getCipherSuite();
            if (cipherSuite != null) {
                int hashCode = cipherSuite.hashCode();
                if (hashCode == 1019404634 ? cipherSuite.equals("TLS_NULL_WITH_NULL_NULL") : hashCode == 1208658923 && cipherSuite.equals("SSL_NULL_WITH_NULL_NULL")) {
                    throw new IOException("cipherSuite == " + cipherSuite);
                }
                CipherSuite forJavaName = CipherSuite.Companion.forJavaName(cipherSuite);
                String protocol = sSLSession.getProtocol();
                if (protocol == null) {
                    throw new IllegalStateException("tlsVersion == null");
                } else if (!Intrinsics.areEqual((Object) "NONE", (Object) protocol)) {
                    TlsVersion forJavaName2 = TlsVersion.Companion.forJavaName(protocol);
                    try {
                        list = toImmutableList(sSLSession.getPeerCertificates());
                    } catch (SSLPeerUnverifiedException unused) {
                        list = CollectionsKt.emptyList();
                    }
                    return new Handshake(forJavaName2, forJavaName, toImmutableList(sSLSession.getLocalCertificates()), new Handshake$Companion$handshake$1(list));
                } else {
                    throw new IOException("tlsVersion == NONE");
                }
            } else {
                throw new IllegalStateException("cipherSuite == null");
            }
        }

        private final List<Certificate> toImmutableList(Certificate[] certificateArr) {
            if (certificateArr != null) {
                return Util.immutableListOf((Certificate[]) Arrays.copyOf(certificateArr, certificateArr.length));
            }
            return CollectionsKt.emptyList();
        }

        /* renamed from: -deprecated_get  reason: not valid java name */
        public final Handshake m979deprecated_get(SSLSession sSLSession) throws IOException {
            Intrinsics.checkNotNullParameter(sSLSession, "sslSession");
            return get(sSLSession);
        }

        public final Handshake get(TlsVersion tlsVersion, CipherSuite cipherSuite, List<? extends Certificate> list, List<? extends Certificate> list2) {
            Intrinsics.checkNotNullParameter(tlsVersion, "tlsVersion");
            Intrinsics.checkNotNullParameter(cipherSuite, "cipherSuite");
            Intrinsics.checkNotNullParameter(list, "peerCertificates");
            Intrinsics.checkNotNullParameter(list2, "localCertificates");
            return new Handshake(tlsVersion, cipherSuite, Util.toImmutableList(list2), new Handshake$Companion$get$1(Util.toImmutableList(list)));
        }
    }
}
