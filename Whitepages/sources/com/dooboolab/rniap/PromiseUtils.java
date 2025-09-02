package com.dooboolab.rniap;

import com.facebook.react.bridge.BaseJavaModule;
import com.facebook.react.bridge.Promise;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

public final class PromiseUtils {
    public static final PromiseUtils INSTANCE = new PromiseUtils();
    private static final HashMap promises = new HashMap();

    private PromiseUtils() {
    }

    public final void addPromiseForKey(String str, Promise promise) {
        Intrinsics.checkNotNullParameter(str, "key");
        Intrinsics.checkNotNullParameter(promise, BaseJavaModule.METHOD_TYPE_PROMISE);
        HashMap hashMap = promises;
        Object obj = hashMap.get(str);
        if (obj == null) {
            obj = new ArrayList();
            hashMap.put(str, obj);
        }
        ((List) obj).add(promise);
    }

    public final void resolvePromisesForKey(String str, Object obj) {
        Intrinsics.checkNotNullParameter(str, "key");
        List<Promise> list = (List) promises.get(str);
        if (list != null) {
            for (Promise safeResolve : list) {
                PromiseUtlisKt.safeResolve(safeResolve, obj);
            }
        }
        promises.remove(str);
    }

    public final void rejectAllPendingPromises() {
        HashMap hashMap = promises;
        ArrayList<Promise> arrayList = new ArrayList<>();
        for (Map.Entry value : hashMap.entrySet()) {
            CollectionsKt.addAll((Collection) arrayList, (Iterable) value.getValue());
        }
        for (Promise safeReject : arrayList) {
            PromiseUtlisKt.safeReject(safeReject, "E_CONNECTION_CLOSED", "Connection has been closed", (Throwable) null);
        }
        promises.clear();
    }

    public final void rejectPromisesForKey(String str, String str2, String str3, Exception exc) {
        Intrinsics.checkNotNullParameter(str, "key");
        Intrinsics.checkNotNullParameter(str2, "code");
        List<Promise> list = (List) promises.get(str);
        if (list != null) {
            for (Promise safeReject : list) {
                PromiseUtlisKt.safeReject(safeReject, str2, str3, exc);
            }
        }
        promises.remove(str);
    }
}
