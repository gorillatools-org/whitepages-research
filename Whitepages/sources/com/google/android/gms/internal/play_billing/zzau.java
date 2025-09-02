package com.google.android.gms.internal.play_billing;

import java.util.Iterator;
import java.util.Set;

public final class zzau {
    static int zza(Set set) {
        Iterator it = set.iterator();
        int i = 0;
        while (it.hasNext()) {
            Object next = it.next();
            i += next != null ? next.hashCode() : 0;
        }
        return i;
    }
}
