package com.facebook.internal.gatekeeper;

import com.google.firebase.remoteconfig.RemoteConfigConstants;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import kotlin.jvm.internal.Intrinsics;

public final class GateKeeperRuntimeCache {
    private final ConcurrentHashMap gateKeepers = new ConcurrentHashMap();

    public final void setGateKeepers(String str, List list) {
        Intrinsics.checkNotNullParameter(str, RemoteConfigConstants.RequestFieldKey.APP_ID);
        Intrinsics.checkNotNullParameter(list, "gateKeeperList");
        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            GateKeeper gateKeeper = (GateKeeper) it.next();
            concurrentHashMap.put(gateKeeper.getName(), gateKeeper);
        }
        this.gateKeepers.put(str, concurrentHashMap);
    }

    public final List dumpGateKeepers(String str) {
        Intrinsics.checkNotNullParameter(str, RemoteConfigConstants.RequestFieldKey.APP_ID);
        ConcurrentHashMap concurrentHashMap = (ConcurrentHashMap) this.gateKeepers.get(str);
        if (concurrentHashMap == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList(concurrentHashMap.size());
        for (Map.Entry value : concurrentHashMap.entrySet()) {
            arrayList.add((GateKeeper) value.getValue());
        }
        return arrayList;
    }
}
