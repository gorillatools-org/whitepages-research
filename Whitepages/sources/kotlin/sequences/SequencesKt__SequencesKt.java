package kotlin.sequences;

import java.util.Iterator;
import kotlin.jvm.internal.Intrinsics;

abstract class SequencesKt__SequencesKt extends SequencesKt__SequencesJVMKt {
    public static Sequence asSequence(Iterator it) {
        Intrinsics.checkNotNullParameter(it, "<this>");
        return constrainOnce(new SequencesKt__SequencesKt$asSequence$$inlined$Sequence$1(it));
    }

    public static final Sequence constrainOnce(Sequence sequence) {
        Intrinsics.checkNotNullParameter(sequence, "<this>");
        return sequence instanceof ConstrainedOnceSequence ? sequence : new ConstrainedOnceSequence(sequence);
    }
}
