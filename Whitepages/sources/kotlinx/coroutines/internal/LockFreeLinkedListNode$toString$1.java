package kotlinx.coroutines.internal;

import kotlin.jvm.internal.PropertyReference0Impl;
import kotlinx.coroutines.DebugStringsKt;

/* synthetic */ class LockFreeLinkedListNode$toString$1 extends PropertyReference0Impl {
    LockFreeLinkedListNode$toString$1(Object obj) {
        super(obj, DebugStringsKt.class, "classSimpleName", "getClassSimpleName(Ljava/lang/Object;)Ljava/lang/String;", 1);
    }

    public Object get() {
        return DebugStringsKt.getClassSimpleName(this.receiver);
    }
}
