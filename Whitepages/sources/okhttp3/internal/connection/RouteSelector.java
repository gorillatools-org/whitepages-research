package okhttp3.internal.connection;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.SocketAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Address;
import okhttp3.Call;
import okhttp3.EventListener;
import okhttp3.HttpUrl;
import okhttp3.Route;

public final class RouteSelector {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    public final Address address;
    private final Call call;
    private final EventListener eventListener;
    private List<? extends InetSocketAddress> inetSocketAddresses = CollectionsKt.emptyList();
    private int nextProxyIndex;
    private final List<Route> postponedRoutes = new ArrayList();
    private List<? extends Proxy> proxies = CollectionsKt.emptyList();
    private final RouteDatabase routeDatabase;

    public RouteSelector(Address address2, RouteDatabase routeDatabase2, Call call2, EventListener eventListener2) {
        Intrinsics.checkNotNullParameter(address2, "address");
        Intrinsics.checkNotNullParameter(routeDatabase2, "routeDatabase");
        Intrinsics.checkNotNullParameter(call2, "call");
        Intrinsics.checkNotNullParameter(eventListener2, "eventListener");
        this.address = address2;
        this.routeDatabase = routeDatabase2;
        this.call = call2;
        this.eventListener = eventListener2;
        resetNextProxy(address2.url(), address2.proxy());
    }

    public final boolean hasNext() {
        return hasNextProxy() || !this.postponedRoutes.isEmpty();
    }

    public final Selection next() throws IOException {
        if (hasNext()) {
            ArrayList arrayList = new ArrayList();
            while (hasNextProxy()) {
                Proxy nextProxy = nextProxy();
                for (InetSocketAddress route : this.inetSocketAddresses) {
                    Route route2 = new Route(this.address, nextProxy, route);
                    if (this.routeDatabase.shouldPostpone(route2)) {
                        this.postponedRoutes.add(route2);
                    } else {
                        arrayList.add(route2);
                    }
                }
                if (!arrayList.isEmpty()) {
                    break;
                }
            }
            if (arrayList.isEmpty()) {
                CollectionsKt.addAll((Collection) arrayList, (Iterable) this.postponedRoutes);
                this.postponedRoutes.clear();
            }
            return new Selection(arrayList);
        }
        throw new NoSuchElementException();
    }

    private final void resetNextProxy(HttpUrl httpUrl, Proxy proxy) {
        RouteSelector$resetNextProxy$1 routeSelector$resetNextProxy$1 = new RouteSelector$resetNextProxy$1(this, proxy, httpUrl);
        this.eventListener.proxySelectStart(this.call, httpUrl);
        List<? extends Proxy> invoke = routeSelector$resetNextProxy$1.invoke();
        this.proxies = invoke;
        this.nextProxyIndex = 0;
        this.eventListener.proxySelectEnd(this.call, httpUrl, invoke);
    }

    private final boolean hasNextProxy() {
        return this.nextProxyIndex < this.proxies.size();
    }

    private final Proxy nextProxy() throws IOException {
        if (hasNextProxy()) {
            List<? extends Proxy> list = this.proxies;
            int i = this.nextProxyIndex;
            this.nextProxyIndex = i + 1;
            Proxy proxy = (Proxy) list.get(i);
            resetNextInetSocketAddress(proxy);
            return proxy;
        }
        throw new SocketException("No route to " + this.address.url().host() + "; exhausted proxy configurations: " + this.proxies);
    }

    private final void resetNextInetSocketAddress(Proxy proxy) throws IOException {
        String str;
        int i;
        ArrayList arrayList = new ArrayList();
        this.inetSocketAddresses = arrayList;
        if (proxy.type() == Proxy.Type.DIRECT || proxy.type() == Proxy.Type.SOCKS) {
            str = this.address.url().host();
            i = this.address.url().port();
        } else {
            SocketAddress address2 = proxy.address();
            if (address2 instanceof InetSocketAddress) {
                InetSocketAddress inetSocketAddress = (InetSocketAddress) address2;
                str = Companion.getSocketHost(inetSocketAddress);
                i = inetSocketAddress.getPort();
            } else {
                throw new IllegalArgumentException(("Proxy.address() is not an InetSocketAddress: " + address2.getClass()).toString());
            }
        }
        if (1 > i || 65535 < i) {
            throw new SocketException("No route to " + str + ':' + i + "; port is out of range");
        } else if (proxy.type() == Proxy.Type.SOCKS) {
            arrayList.add(InetSocketAddress.createUnresolved(str, i));
        } else {
            this.eventListener.dnsStart(this.call, str);
            List<InetAddress> lookup = this.address.dns().lookup(str);
            if (!lookup.isEmpty()) {
                this.eventListener.dnsEnd(this.call, str, lookup);
                for (InetAddress inetSocketAddress2 : lookup) {
                    arrayList.add(new InetSocketAddress(inetSocketAddress2, i));
                }
                return;
            }
            throw new UnknownHostException(this.address.dns() + " returned no addresses for " + str);
        }
    }

    public static final class Selection {
        private int nextRouteIndex;
        private final List<Route> routes;

        public Selection(List<Route> list) {
            Intrinsics.checkNotNullParameter(list, "routes");
            this.routes = list;
        }

        public final List<Route> getRoutes() {
            return this.routes;
        }

        public final boolean hasNext() {
            return this.nextRouteIndex < this.routes.size();
        }

        public final Route next() {
            if (hasNext()) {
                List<Route> list = this.routes;
                int i = this.nextRouteIndex;
                this.nextRouteIndex = i + 1;
                return list.get(i);
            }
            throw new NoSuchElementException();
        }
    }

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final String getSocketHost(InetSocketAddress inetSocketAddress) {
            Intrinsics.checkNotNullParameter(inetSocketAddress, "$this$socketHost");
            InetAddress address = inetSocketAddress.getAddress();
            if (address != null) {
                String hostAddress = address.getHostAddress();
                Intrinsics.checkNotNullExpressionValue(hostAddress, "address.hostAddress");
                return hostAddress;
            }
            String hostName = inetSocketAddress.getHostName();
            Intrinsics.checkNotNullExpressionValue(hostName, "hostName");
            return hostName;
        }
    }
}
