package okhttp3.internal.connection;

import java.net.Proxy;
import java.net.URI;
import java.util.Collection;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import okhttp3.HttpUrl;
import okhttp3.internal.Util;

final class RouteSelector$resetNextProxy$1 extends Lambda implements Function0 {
    final /* synthetic */ Proxy $proxy;
    final /* synthetic */ HttpUrl $url;
    final /* synthetic */ RouteSelector this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    RouteSelector$resetNextProxy$1(RouteSelector routeSelector, Proxy proxy, HttpUrl httpUrl) {
        super(0);
        this.this$0 = routeSelector;
        this.$proxy = proxy;
        this.$url = httpUrl;
    }

    public final List<Proxy> invoke() {
        Proxy proxy = this.$proxy;
        if (proxy != null) {
            return CollectionsKt.listOf(proxy);
        }
        URI uri = this.$url.uri();
        if (uri.getHost() == null) {
            return Util.immutableListOf(Proxy.NO_PROXY);
        }
        List<Proxy> select = this.this$0.address.proxySelector().select(uri);
        Collection collection = select;
        if (collection == null || collection.isEmpty()) {
            return Util.immutableListOf(Proxy.NO_PROXY);
        }
        return Util.toImmutableList(select);
    }
}
