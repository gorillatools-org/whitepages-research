package okhttp3;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public interface Dns {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final Dns SYSTEM = new Companion.DnsSystem();

    List<InetAddress> lookup(String str) throws UnknownHostException;

    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = null;

        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private static final class DnsSystem implements Dns {
            public List<InetAddress> lookup(String str) {
                Intrinsics.checkNotNullParameter(str, "hostname");
                try {
                    InetAddress[] allByName = InetAddress.getAllByName(str);
                    Intrinsics.checkNotNullExpressionValue(allByName, "InetAddress.getAllByName(hostname)");
                    return ArraysKt.toList(allByName);
                } catch (NullPointerException e) {
                    UnknownHostException unknownHostException = new UnknownHostException("Broken system behaviour for dns lookup of " + str);
                    unknownHostException.initCause(e);
                    throw unknownHostException;
                }
            }
        }
    }
}
