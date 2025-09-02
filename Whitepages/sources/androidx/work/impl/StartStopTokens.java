package androidx.work.impl;

import androidx.work.impl.model.WorkGenerationalId;
import androidx.work.impl.model.WorkSpec;
import androidx.work.impl.model.WorkSpecKt;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

public final class StartStopTokens {
    private final Object lock = new Object();
    private final Map runs = new LinkedHashMap();

    public final StartStopToken tokenFor(WorkGenerationalId workGenerationalId) {
        StartStopToken startStopToken;
        Intrinsics.checkNotNullParameter(workGenerationalId, "id");
        synchronized (this.lock) {
            try {
                Map map = this.runs;
                Object obj = map.get(workGenerationalId);
                if (obj == null) {
                    obj = new StartStopToken(workGenerationalId);
                    map.put(workGenerationalId, obj);
                }
                startStopToken = (StartStopToken) obj;
            } catch (Throwable th) {
                throw th;
            }
        }
        return startStopToken;
    }

    public final StartStopToken remove(WorkGenerationalId workGenerationalId) {
        StartStopToken startStopToken;
        Intrinsics.checkNotNullParameter(workGenerationalId, "id");
        synchronized (this.lock) {
            startStopToken = (StartStopToken) this.runs.remove(workGenerationalId);
        }
        return startStopToken;
    }

    public final List remove(String str) {
        List list;
        Intrinsics.checkNotNullParameter(str, "workSpecId");
        synchronized (this.lock) {
            try {
                Map map = this.runs;
                LinkedHashMap linkedHashMap = new LinkedHashMap();
                for (Map.Entry entry : map.entrySet()) {
                    if (Intrinsics.areEqual((Object) ((WorkGenerationalId) entry.getKey()).getWorkSpecId(), (Object) str)) {
                        linkedHashMap.put(entry.getKey(), entry.getValue());
                    }
                }
                for (WorkGenerationalId remove : linkedHashMap.keySet()) {
                    this.runs.remove(remove);
                }
                list = CollectionsKt.toList(linkedHashMap.values());
            } finally {
            }
        }
        return list;
    }

    public final boolean contains(WorkGenerationalId workGenerationalId) {
        boolean containsKey;
        Intrinsics.checkNotNullParameter(workGenerationalId, "id");
        synchronized (this.lock) {
            containsKey = this.runs.containsKey(workGenerationalId);
        }
        return containsKey;
    }

    public final StartStopToken tokenFor(WorkSpec workSpec) {
        Intrinsics.checkNotNullParameter(workSpec, "spec");
        return tokenFor(WorkSpecKt.generationalId(workSpec));
    }
}
