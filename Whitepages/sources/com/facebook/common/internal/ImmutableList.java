package com.facebook.common.internal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ImmutableList extends ArrayList {
    private ImmutableList(int i) {
        super(i);
    }

    private ImmutableList(List list) {
        super(list);
    }

    public static ImmutableList copyOf(List list) {
        return new ImmutableList(list);
    }

    public static ImmutableList of(Object... objArr) {
        ImmutableList immutableList = new ImmutableList(objArr.length);
        Collections.addAll(immutableList, objArr);
        return immutableList;
    }
}
