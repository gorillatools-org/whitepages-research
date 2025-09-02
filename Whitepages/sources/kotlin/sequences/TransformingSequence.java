package kotlin.sequences;

import java.util.Iterator;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

public final class TransformingSequence implements Sequence {
    /* access modifiers changed from: private */
    public final Sequence sequence;
    /* access modifiers changed from: private */
    public final Function1 transformer;

    public TransformingSequence(Sequence sequence2, Function1 function1) {
        Intrinsics.checkNotNullParameter(sequence2, "sequence");
        Intrinsics.checkNotNullParameter(function1, "transformer");
        this.sequence = sequence2;
        this.transformer = function1;
    }

    public Iterator iterator() {
        return new TransformingSequence$iterator$1(this);
    }
}
