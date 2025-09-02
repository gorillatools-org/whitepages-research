package com.facebook.common.internal;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ImmutableMap extends HashMap {
    private ImmutableMap(Map map) {
        super(map);
    }

    public static Map of(Object obj, Object obj2) {
        HashMap hashMap = new HashMap(1);
        hashMap.put(obj, obj2);
        return Collections.unmodifiableMap(hashMap);
    }

    public static Map of(Object obj, Object obj2, Object obj3, Object obj4) {
        HashMap hashMap = new HashMap(2);
        hashMap.put(obj, obj2);
        hashMap.put(obj3, obj4);
        return Collections.unmodifiableMap(hashMap);
    }

    public static ImmutableMap copyOf(Map map) {
        return new ImmutableMap(map);
    }
}
