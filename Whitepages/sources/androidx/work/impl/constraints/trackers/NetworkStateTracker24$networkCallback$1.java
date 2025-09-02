package androidx.work.impl.constraints.trackers;

import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import androidx.work.Logger;
import kotlin.jvm.internal.Intrinsics;

public final class NetworkStateTracker24$networkCallback$1 extends ConnectivityManager.NetworkCallback {
    final /* synthetic */ NetworkStateTracker24 this$0;

    NetworkStateTracker24$networkCallback$1(NetworkStateTracker24 networkStateTracker24) {
        this.this$0 = networkStateTracker24;
    }

    public void onCapabilitiesChanged(Network network, NetworkCapabilities networkCapabilities) {
        Intrinsics.checkNotNullParameter(network, "network");
        Intrinsics.checkNotNullParameter(networkCapabilities, "capabilities");
        Logger logger = Logger.get();
        String access$getTAG$p = NetworkStateTrackerKt.TAG;
        logger.debug(access$getTAG$p, "Network capabilities changed: " + networkCapabilities);
        NetworkStateTracker24 networkStateTracker24 = this.this$0;
        networkStateTracker24.setState(NetworkStateTrackerKt.getActiveNetworkState(networkStateTracker24.connectivityManager));
    }

    public void onLost(Network network) {
        Intrinsics.checkNotNullParameter(network, "network");
        Logger.get().debug(NetworkStateTrackerKt.TAG, "Network connection lost");
        NetworkStateTracker24 networkStateTracker24 = this.this$0;
        networkStateTracker24.setState(NetworkStateTrackerKt.getActiveNetworkState(networkStateTracker24.connectivityManager));
    }
}
