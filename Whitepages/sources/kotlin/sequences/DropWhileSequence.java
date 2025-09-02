package kotlin.sequences;

import java.util.Iterator;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

public final class DropWhileSequence implements Sequence {
    /* access modifiers changed from: private */
    public final Function1 predicate;
    /* access modifiers changed from: private */
    public final Sequence sequence;

    public DropWhileSequence(Sequence sequence2, Function1 function1) {
        Intrinsics.checkNotNullParameter(sequence2, "sequence");
        Intrinsics.checkNotNullParameter(function1, "predicate");
        this.sequence = sequence2;
        this.predicate = function1;
    }

    public Iterator iterator() {
        return new DropWhileSequence$iterator$1(this);
    }
}
