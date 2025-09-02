package com.facebook.common.internal;

import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

public abstract class Sets {
    public static Set newIdentityHashSet() {
        return newSetFromMap(new IdentityHashMap());
    }

    public static Set newSetFromMap(Map map) {
        return Collections.newSetFromMap(map);
    }

    public static CopyOnWriteArraySet newCopyOnWriteArraySet() {
        return new CopyOnWriteArraySet();
    }
}
