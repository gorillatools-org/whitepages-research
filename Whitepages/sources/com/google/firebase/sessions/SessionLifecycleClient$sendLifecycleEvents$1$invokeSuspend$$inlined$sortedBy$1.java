package com.google.firebase.sessions;

import android.os.Message;
import java.util.Comparator;
import kotlin.comparisons.ComparisonsKt;

public final class SessionLifecycleClient$sendLifecycleEvents$1$invokeSuspend$$inlined$sortedBy$1<T> implements Comparator {
    public final int compare(T t, T t2) {
        return ComparisonsKt.compareValues(Long.valueOf(((Message) t).getWhen()), Long.valueOf(((Message) t2).getWhen()));
    }
}
