package okhttp3;

import java.security.Principal;
import java.security.PublicKey;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.net.ssl.SSLPeerUnverifiedException;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlin.text.StringsKt;
import okhttp3.internal.HostnamesKt;
import okhttp3.internal.tls.CertificateChainCleaner;
import okio.ByteString;

public final class CertificatePinner {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final CertificatePinner DEFAULT = new Builder().build();
    private final CertificateChainCleaner certificateChainCleaner;
    private final Set<Pin> pins;

    public static final String pin(Certificate certificate) {
        return Companion.pin(certificate);
    }

    public static final ByteString sha1Hash(X509Certificate x509Certificate) {
        return Companion.sha1Hash(x509Certificate);
    }

    public static final ByteString sha256Hash(X509Certificate x509Certificate) {
        return Companion.sha256Hash(x509Certificate);
    }

    public CertificatePinner(Set<Pin> set, CertificateChainCleaner certificateChainCleaner2) {
        Intrinsics.checkNotNullParameter(set, "pins");
        this.pins = set;
        this.certificateChainCleaner = certificateChainCleaner2;
    }

    public final Set<Pin> getPins() {
        return this.pins;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ CertificatePinner(Set set, CertificateChainCleaner certificateChainCleaner2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(set, (i & 2) != 0 ? null : certificateChainCleaner2);
    }

    public final CertificateChainCleaner getCertificateChainCleaner$okhttp() {
        return this.certificateChainCleaner;
    }

    public final void check(String str, List<? extends Certificate> list) throws SSLPeerUnverifiedException {
        Intrinsics.checkNotNullParameter(str, "hostname");
        Intrinsics.checkNotNullParameter(list, "peerCertificates");
        check$okhttp(str, new CertificatePinner$check$1(this, list, str));
    }

    public final void check$okhttp(String str, Function0 function0) {
        Pin next;
        Intrinsics.checkNotNullParameter(str, "hostname");
        Intrinsics.checkNotNullParameter(function0, "cleanedPeerCertificatesFn");
        List<Pin> findMatchingPins = findMatchingPins(str);
        if (!findMatchingPins.isEmpty()) {
            List<X509Certificate> list = (List) function0.invoke();
            loop0:
            for (X509Certificate x509Certificate : list) {
                Iterator<Pin> it = findMatchingPins.iterator();
                ByteString byteString = null;
                ByteString byteString2 = null;
                while (true) {
                    if (it.hasNext()) {
                        next = it.next();
                        String hashAlgorithm = next.getHashAlgorithm();
                        int hashCode = hashAlgorithm.hashCode();
                        if (hashCode == -903629273) {
                            if (!hashAlgorithm.equals("sha256")) {
                                break loop0;
                            }
                            if (byteString == null) {
                                byteString = Companion.sha256Hash(x509Certificate);
                            }
                            if (Intrinsics.areEqual((Object) next.getHash(), (Object) byteString)) {
                                return;
                            }
                        } else if (hashCode != 3528965 || !hashAlgorithm.equals("sha1")) {
                            break loop0;
                        } else {
                            if (byteString2 == null) {
                                byteString2 = Companion.sha1Hash(x509Certificate);
                            }
                            if (Intrinsics.areEqual((Object) next.getHash(), (Object) byteString2)) {
                                return;
                            }
                        }
                    }
                }
                throw new AssertionError("unsupported hashAlgorithm: " + next.getHashAlgorithm());
            }
            StringBuilder sb = new StringBuilder();
            sb.append("Certificate pinning failure!");
            sb.append("\n  Peer certificate chain:");
            for (X509Certificate x509Certificate2 : list) {
                sb.append("\n    ");
                sb.append(Companion.pin(x509Certificate2));
                sb.append(": ");
                Principal subjectDN = x509Certificate2.getSubjectDN();
                Intrinsics.checkNotNullExpressionValue(subjectDN, "element.subjectDN");
                sb.append(subjectDN.getName());
            }
            sb.append("\n  Pinned certificates for ");
            sb.append(str);
            sb.append(":");
            for (Pin append : findMatchingPins) {
                sb.append("\n    ");
                sb.append(append);
            }
            String sb2 = sb.toString();
            Intrinsics.checkNotNullExpressionValue(sb2, "StringBuilder().apply(builderAction).toString()");
            throw new SSLPeerUnverifiedException(sb2);
        }
    }

    public final void check(String str, Certificate... certificateArr) throws SSLPeerUnverifiedException {
        Intrinsics.checkNotNullParameter(str, "hostname");
        Intrinsics.checkNotNullParameter(certificateArr, "peerCertificates");
        check(str, (List<? extends Certificate>) ArraysKt.toList(certificateArr));
    }

    public final List<Pin> findMatchingPins(String str) {
        Intrinsics.checkNotNullParameter(str, "hostname");
        List<Pin> emptyList = CollectionsKt.emptyList();
        for (Object next : this.pins) {
            if (((Pin) next).matchesHostname(str)) {
                if (emptyList.isEmpty()) {
                    emptyList = new ArrayList<>();
                }
                TypeIntrinsics.asMutableList(emptyList).add(next);
            }
        }
        return emptyList;
    }

    public final CertificatePinner withCertificateChainCleaner$okhttp(CertificateChainCleaner certificateChainCleaner2) {
        Intrinsics.checkNotNullParameter(certificateChainCleaner2, "certificateChainCleaner");
        if (Intrinsics.areEqual((Object) this.certificateChainCleaner, (Object) certificateChainCleaner2)) {
            return this;
        }
        return new CertificatePinner(this.pins, certificateChainCleaner2);
    }

    public boolean equals(Object obj) {
        if (obj instanceof CertificatePinner) {
            CertificatePinner certificatePinner = (CertificatePinner) obj;
            return Intrinsics.areEqual((Object) certificatePinner.pins, (Object) this.pins) && Intrinsics.areEqual((Object) certificatePinner.certificateChainCleaner, (Object) this.certificateChainCleaner);
        }
    }

    public int hashCode() {
        int hashCode = (1517 + this.pins.hashCode()) * 41;
        CertificateChainCleaner certificateChainCleaner2 = this.certificateChainCleaner;
        return hashCode + (certificateChainCleaner2 != null ? certificateChainCleaner2.hashCode() : 0);
    }

    public static final class Pin {
        private final ByteString hash;
        private final String hashAlgorithm;
        private final String pattern;

        public Pin(String str, String str2) {
            Intrinsics.checkNotNullParameter(str, "pattern");
            Intrinsics.checkNotNullParameter(str2, "pin");
            if ((StringsKt.startsWith$default(str, "*.", false, 2, (Object) null) && StringsKt.indexOf$default((CharSequence) str, "*", 1, false, 4, (Object) null) == -1) || (StringsKt.startsWith$default(str, "**.", false, 2, (Object) null) && StringsKt.indexOf$default((CharSequence) str, "*", 2, false, 4, (Object) null) == -1) || StringsKt.indexOf$default((CharSequence) str, "*", 0, false, 6, (Object) null) == -1) {
                String canonicalHost = HostnamesKt.toCanonicalHost(str);
                if (canonicalHost != null) {
                    this.pattern = canonicalHost;
                    if (StringsKt.startsWith$default(str2, "sha1/", false, 2, (Object) null)) {
                        this.hashAlgorithm = "sha1";
                        ByteString.Companion companion = ByteString.Companion;
                        String substring = str2.substring(5);
                        Intrinsics.checkNotNullExpressionValue(substring, "(this as java.lang.String).substring(startIndex)");
                        ByteString decodeBase64 = companion.decodeBase64(substring);
                        if (decodeBase64 != null) {
                            this.hash = decodeBase64;
                            return;
                        }
                        throw new IllegalArgumentException("Invalid pin hash: " + str2);
                    } else if (StringsKt.startsWith$default(str2, "sha256/", false, 2, (Object) null)) {
                        this.hashAlgorithm = "sha256";
                        ByteString.Companion companion2 = ByteString.Companion;
                        String substring2 = str2.substring(7);
                        Intrinsics.checkNotNullExpressionValue(substring2, "(this as java.lang.String).substring(startIndex)");
                        ByteString decodeBase642 = companion2.decodeBase64(substring2);
                        if (decodeBase642 != null) {
                            this.hash = decodeBase642;
                            return;
                        }
                        throw new IllegalArgumentException("Invalid pin hash: " + str2);
                    } else {
                        throw new IllegalArgumentException("pins must start with 'sha256/' or 'sha1/': " + str2);
                    }
                } else {
                    throw new IllegalArgumentException("Invalid pattern: " + str);
                }
            } else {
                throw new IllegalArgumentException(("Unexpected pattern: " + str).toString());
            }
        }

        public final String getPattern() {
            return this.pattern;
        }

        public final String getHashAlgorithm() {
            return this.hashAlgorithm;
        }

        public final ByteString getHash() {
            return this.hash;
        }

        public final boolean matchesHostname(String str) {
            Intrinsics.checkNotNullParameter(str, "hostname");
            if (StringsKt.startsWith$default(this.pattern, "**.", false, 2, (Object) null)) {
                int length = this.pattern.length() - 3;
                int length2 = str.length() - length;
                if (!StringsKt.regionMatches$default(str, str.length() - length, this.pattern, 3, length, false, 16, (Object) null)) {
                    return false;
                }
                if (!(length2 == 0 || str.charAt(length2 - 1) == '.')) {
                    return false;
                }
            } else if (!StringsKt.startsWith$default(this.pattern, "*.", false, 2, (Object) null)) {
                return Intrinsics.areEqual((Object) str, (Object) this.pattern);
            } else {
                int length3 = this.pattern.length() - 1;
                int length4 = str.length() - length3;
                if (!StringsKt.regionMatches$default(str, str.length() - length3, this.pattern, 1, length3, false, 16, (Object) null)) {
                    return false;
                }
                if (StringsKt.lastIndexOf$default((CharSequence) str, '.', length4 - 1, false, 4, (Object) null) != -1) {
                    return false;
                }
            }
            return true;
        }

        public final boolean matchesCertificate(X509Certificate x509Certificate) {
            Intrinsics.checkNotNullParameter(x509Certificate, "certificate");
            String str = this.hashAlgorithm;
            int hashCode = str.hashCode();
            if (hashCode != -903629273) {
                if (hashCode == 3528965 && str.equals("sha1")) {
                    return Intrinsics.areEqual((Object) this.hash, (Object) CertificatePinner.Companion.sha1Hash(x509Certificate));
                }
            } else if (str.equals("sha256")) {
                return Intrinsics.areEqual((Object) this.hash, (Object) CertificatePinner.Companion.sha256Hash(x509Certificate));
            }
            return false;
        }

        public String toString() {
            return this.hashAlgorithm + '/' + this.hash.base64();
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Pin)) {
                return false;
            }
            Pin pin = (Pin) obj;
            return Intrinsics.areEqual((Object) this.pattern, (Object) pin.pattern) && Intrinsics.areEqual((Object) this.hashAlgorithm, (Object) pin.hashAlgorithm) && Intrinsics.areEqual((Object) this.hash, (Object) pin.hash);
        }

        public int hashCode() {
            return (((this.pattern.hashCode() * 31) + this.hashAlgorithm.hashCode()) * 31) + this.hash.hashCode();
        }
    }

    public static final class Builder {
        private final List<Pin> pins = new ArrayList();

        public final List<Pin> getPins() {
            return this.pins;
        }

        public final Builder add(String str, String... strArr) {
            Intrinsics.checkNotNullParameter(str, "pattern");
            Intrinsics.checkNotNullParameter(strArr, "pins");
            for (String pin : strArr) {
                this.pins.add(new Pin(str, pin));
            }
            return this;
        }

        public final CertificatePinner build() {
            return new CertificatePinner(CollectionsKt.toSet(this.pins), (CertificateChainCleaner) null, 2, (DefaultConstructorMarker) null);
        }
    }

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final ByteString sha1Hash(X509Certificate x509Certificate) {
            Intrinsics.checkNotNullParameter(x509Certificate, "$this$sha1Hash");
            ByteString.Companion companion = ByteString.Companion;
            PublicKey publicKey = x509Certificate.getPublicKey();
            Intrinsics.checkNotNullExpressionValue(publicKey, "publicKey");
            byte[] encoded = publicKey.getEncoded();
            Intrinsics.checkNotNullExpressionValue(encoded, "publicKey.encoded");
            return ByteString.Companion.of$default(companion, encoded, 0, 0, 3, (Object) null).sha1();
        }

        public final ByteString sha256Hash(X509Certificate x509Certificate) {
            Intrinsics.checkNotNullParameter(x509Certificate, "$this$sha256Hash");
            ByteString.Companion companion = ByteString.Companion;
            PublicKey publicKey = x509Certificate.getPublicKey();
            Intrinsics.checkNotNullExpressionValue(publicKey, "publicKey");
            byte[] encoded = publicKey.getEncoded();
            Intrinsics.checkNotNullExpressionValue(encoded, "publicKey.encoded");
            return ByteString.Companion.of$default(companion, encoded, 0, 0, 3, (Object) null).sha256();
        }

        public final String pin(Certificate certificate) {
            Intrinsics.checkNotNullParameter(certificate, "certificate");
            if (certificate instanceof X509Certificate) {
                return "sha256/" + sha256Hash((X509Certificate) certificate).base64();
            }
            throw new IllegalArgumentException("Certificate pinning requires X509 certificates");
        }
    }
}
