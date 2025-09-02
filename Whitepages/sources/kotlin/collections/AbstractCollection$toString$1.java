package kotlin.collections;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

final class AbstractCollection$toString$1 extends Lambda implements Function1 {
    final /* synthetic */ AbstractCollection this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    AbstractCollection$toString$1(AbstractCollection abstractCollection) {
        super(1);
        this.this$0 = abstractCollection;
    }

    public final CharSequence invoke(Object obj) {
        return obj == this.this$0 ? "(this Collection)" : String.valueOf(obj);
    }
}
