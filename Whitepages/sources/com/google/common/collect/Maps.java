package com.google.common.collect;

import com.google.common.base.Function;
import java.util.Map;

public abstract class Maps {

    /* renamed from: com.google.common.collect.Maps$1  reason: invalid class name */
    abstract class AnonymousClass1 extends TransformedIterator {
    }

    private enum EntryFunction implements Function {
        KEY {
            public Object apply(Map.Entry entry) {
                return entry.getKey();
            }
        },
        VALUE {
            public Object apply(Map.Entry entry) {
                return entry.getValue();
            }
        }
    }

    static Function valueFunction() {
        return EntryFunction.VALUE;
    }

    static boolean equalsImpl(Map map, Object obj) {
        if (map == obj) {
            return true;
        }
        if (obj instanceof Map) {
            return map.entrySet().equals(((Map) obj).entrySet());
        }
        return false;
    }

    static String toStringImpl(Map map) {
        StringBuilder newStringBuilderForCollection = Collections2.newStringBuilderForCollection(map.size());
        newStringBuilderForCollection.append('{');
        boolean z = true;
        for (Map.Entry entry : map.entrySet()) {
            if (!z) {
                newStringBuilderForCollection.append(", ");
            }
            newStringBuilderForCollection.append(entry.getKey());
            newStringBuilderForCollection.append('=');
            newStringBuilderForCollection.append(entry.getValue());
            z = false;
        }
        newStringBuilderForCollection.append('}');
        return newStringBuilderForCollection.toString();
    }
}
