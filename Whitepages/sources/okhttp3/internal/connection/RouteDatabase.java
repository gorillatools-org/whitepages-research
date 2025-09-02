package okhttp3.internal.connection;

import java.util.LinkedHashSet;
import java.util.Set;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Route;

public final class RouteDatabase {
    private final Set<Route> failedRoutes = new LinkedHashSet();

    public final synchronized void failed(Route route) {
        Intrinsics.checkNotNullParameter(route, "failedRoute");
        this.failedRoutes.add(route);
    }

    public final synchronized void connected(Route route) {
        Intrinsics.checkNotNullParameter(route, "route");
        this.failedRoutes.remove(route);
    }

    public final synchronized boolean shouldPostpone(Route route) {
        Intrinsics.checkNotNullParameter(route, "route");
        return this.failedRoutes.contains(route);
    }
}
