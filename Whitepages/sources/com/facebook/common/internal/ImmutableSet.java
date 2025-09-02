package com.facebook.common.internal;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class ImmutableSet extends HashSet {
    private ImmutableSet(Set set) {
        super(set);
    }

    public static ImmutableSet of(Object... objArr) {
        HashSet hashSet = new HashSet(objArr.length);
        Collections.addAll(hashSet, objArr);
        return new ImmutableSet(hashSet);
    }
}
