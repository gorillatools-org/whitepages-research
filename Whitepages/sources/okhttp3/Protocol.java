package okhttp3;

import java.io.IOException;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public enum Protocol {
    HTTP_1_0("http/1.0"),
    HTTP_1_1("http/1.1"),
    SPDY_3("spdy/3.1"),
    HTTP_2("h2"),
    H2_PRIOR_KNOWLEDGE("h2_prior_knowledge"),
    QUIC("quic");
    
    public static final Companion Companion = null;
    /* access modifiers changed from: private */
    public final String protocol;

    public static final Protocol get(String str) throws IOException {
        return Companion.get(str);
    }

    private Protocol(String str) {
        this.protocol = str;
    }

    static {
        Companion = new Companion((DefaultConstructorMarker) null);
    }

    public String toString() {
        return this.protocol;
    }

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final Protocol get(String str) throws IOException {
            Intrinsics.checkNotNullParameter(str, "protocol");
            Protocol protocol = Protocol.HTTP_1_0;
            if (!Intrinsics.areEqual((Object) str, (Object) protocol.protocol)) {
                protocol = Protocol.HTTP_1_1;
                if (!Intrinsics.areEqual((Object) str, (Object) protocol.protocol)) {
                    protocol = Protocol.H2_PRIOR_KNOWLEDGE;
                    if (!Intrinsics.areEqual((Object) str, (Object) protocol.protocol)) {
                        protocol = Protocol.HTTP_2;
                        if (!Intrinsics.areEqual((Object) str, (Object) protocol.protocol)) {
                            protocol = Protocol.SPDY_3;
                            if (!Intrinsics.areEqual((Object) str, (Object) protocol.protocol)) {
                                protocol = Protocol.QUIC;
                                if (!Intrinsics.areEqual((Object) str, (Object) protocol.protocol)) {
                                    throw new IOException("Unexpected protocol: " + str);
                                }
                            }
                        }
                    }
                }
            }
            return protocol;
        }
    }
}
