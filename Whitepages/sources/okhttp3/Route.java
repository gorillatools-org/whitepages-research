package okhttp3;

import java.net.InetSocketAddress;
import java.net.Proxy;
import kotlin.jvm.internal.Intrinsics;

public final class Route {
    private final Address address;
    private final Proxy proxy;
    private final InetSocketAddress socketAddress;

    public Route(Address address2, Proxy proxy2, InetSocketAddress inetSocketAddress) {
        Intrinsics.checkNotNullParameter(address2, "address");
        Intrinsics.checkNotNullParameter(proxy2, "proxy");
        Intrinsics.checkNotNullParameter(inetSocketAddress, "socketAddress");
        this.address = address2;
        this.proxy = proxy2;
        this.socketAddress = inetSocketAddress;
    }

    public final Address address() {
        return this.address;
    }

    public final Proxy proxy() {
        return this.proxy;
    }

    public final InetSocketAddress socketAddress() {
        return this.socketAddress;
    }

    /* renamed from: -deprecated_address  reason: not valid java name */
    public final Address m1062deprecated_address() {
        return this.address;
    }

    /* renamed from: -deprecated_proxy  reason: not valid java name */
    public final Proxy m1063deprecated_proxy() {
        return this.proxy;
    }

    /* renamed from: -deprecated_socketAddress  reason: not valid java name */
    public final InetSocketAddress m1064deprecated_socketAddress() {
        return this.socketAddress;
    }

    public final boolean requiresTunnel() {
        return this.address.sslSocketFactory() != null && this.proxy.type() == Proxy.Type.HTTP;
    }

    public boolean equals(Object obj) {
        if (obj instanceof Route) {
            Route route = (Route) obj;
            return Intrinsics.areEqual((Object) route.address, (Object) this.address) && Intrinsics.areEqual((Object) route.proxy, (Object) this.proxy) && Intrinsics.areEqual((Object) route.socketAddress, (Object) this.socketAddress);
        }
    }

    public int hashCode() {
        return ((((527 + this.address.hashCode()) * 31) + this.proxy.hashCode()) * 31) + this.socketAddress.hashCode();
    }

    public String toString() {
        return "Route{" + this.socketAddress + '}';
    }
}
