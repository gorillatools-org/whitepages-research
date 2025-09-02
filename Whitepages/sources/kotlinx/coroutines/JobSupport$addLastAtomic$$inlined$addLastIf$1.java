package kotlinx.coroutines;

import kotlinx.coroutines.internal.LockFreeLinkedListKt;
import kotlinx.coroutines.internal.LockFreeLinkedListNode;

public final class JobSupport$addLastAtomic$$inlined$addLastIf$1 extends LockFreeLinkedListNode.CondAddOp {
    final /* synthetic */ Object $expect$inlined;
    final /* synthetic */ JobSupport this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public JobSupport$addLastAtomic$$inlined$addLastIf$1(LockFreeLinkedListNode lockFreeLinkedListNode, JobSupport jobSupport, Object obj) {
        super(lockFreeLinkedListNode);
        this.this$0 = jobSupport;
        this.$expect$inlined = obj;
    }

    public Object prepare(LockFreeLinkedListNode lockFreeLinkedListNode) {
        if (this.this$0.getState$kotlinx_coroutines_core() == this.$expect$inlined) {
            return null;
        }
        return LockFreeLinkedListKt.getCONDITION_FALSE();
    }
}
