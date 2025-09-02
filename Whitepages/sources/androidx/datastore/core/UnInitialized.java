package androidx.datastore.core;

import kotlin.jvm.internal.DefaultConstructorMarker;

public final class UnInitialized extends State {
    public static final UnInitialized INSTANCE = new UnInitialized();

    private UnInitialized() {
        super(-1, (DefaultConstructorMarker) null);
    }
}
