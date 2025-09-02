package okhttp3.internal.platform;

import java.util.List;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Protocol;
import okhttp3.internal.SuppressSignatureCheck;

public class Jdk9Platform extends Platform {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    public static final boolean isAvailable;

    @SuppressSignatureCheck
    public void configureTlsExtensions(SSLSocket sSLSocket, String str, List<Protocol> list) {
        Intrinsics.checkNotNullParameter(sSLSocket, "sslSocket");
        Intrinsics.checkNotNullParameter(list, "protocols");
        SSLParameters sSLParameters = sSLSocket.getSSLParameters();
        List<String> alpnProtocolNames = Platform.Companion.alpnProtocolNames(list);
        Intrinsics.checkNotNullExpressionValue(sSLParameters, "sslParameters");
        Object[] array = alpnProtocolNames.toArray(new String[0]);
        if (array != null) {
            sSLParameters.setApplicationProtocols((String[]) array);
            sSLSocket.setSSLParameters(sSLParameters);
            return;
        }
        throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T>");
    }

    @SuppressSignatureCheck
    public String getSelectedProtocol(SSLSocket sSLSocket) {
        Intrinsics.checkNotNullParameter(sSLSocket, "sslSocket");
        try {
            String m = sSLSocket.getApplicationProtocol();
            if (m == null) {
                return null;
            }
            if (m.hashCode() == 0) {
                if (m.equals("")) {
                    return null;
                }
            }
            return m;
        } catch (UnsupportedOperationException unused) {
            return null;
        }
    }

    public X509TrustManager trustManager(SSLSocketFactory sSLSocketFactory) {
        Intrinsics.checkNotNullParameter(sSLSocketFactory, "sslSocketFactory");
        throw new UnsupportedOperationException("clientBuilder.sslSocketFactory(SSLSocketFactory) not supported on JDK 9+");
    }

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final boolean isAvailable() {
            return Jdk9Platform.isAvailable;
        }

        public final Jdk9Platform buildIfSupported() {
            if (isAvailable()) {
                return new Jdk9Platform();
            }
            return null;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0020, code lost:
        if (r0.intValue() >= 9) goto L_0x0022;
     */
    static {
        /*
            okhttp3.internal.platform.Jdk9Platform$Companion r0 = new okhttp3.internal.platform.Jdk9Platform$Companion
            r1 = 0
            r0.<init>(r1)
            Companion = r0
            java.lang.String r0 = "java.specification.version"
            java.lang.String r0 = java.lang.System.getProperty(r0)
            if (r0 == 0) goto L_0x0015
            java.lang.Integer r0 = kotlin.text.StringsKt.toIntOrNull(r0)
            goto L_0x0016
        L_0x0015:
            r0 = r1
        L_0x0016:
            r2 = 0
            r3 = 1
            if (r0 == 0) goto L_0x0024
            int r0 = r0.intValue()
            r1 = 9
            if (r0 < r1) goto L_0x002c
        L_0x0022:
            r2 = r3
            goto L_0x002c
        L_0x0024:
            java.lang.Class<javax.net.ssl.SSLSocket> r0 = javax.net.ssl.SSLSocket.class
            java.lang.String r4 = "getApplicationProtocol"
            r0.getMethod(r4, r1)     // Catch:{ NoSuchMethodException -> 0x002c }
            goto L_0x0022
        L_0x002c:
            isAvailable = r2
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.platform.Jdk9Platform.<clinit>():void");
    }
}
