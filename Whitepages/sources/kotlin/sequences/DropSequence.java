package kotlin.sequences;

import java.util.Iterator;
import kotlin.jvm.internal.Intrinsics;

public final class DropSequence implements Sequence, DropTakeSequence {
    /* access modifiers changed from: private */
    public final int count;
    /* access modifiers changed from: private */
    public final Sequence sequence;

    public DropSequence(Sequence sequence2, int i) {
        Intrinsics.checkNotNullParameter(sequence2, "sequence");
        this.sequence = sequence2;
        this.count = i;
        if (i < 0) {
            throw new IllegalArgumentException(("count must be non-negative, but was " + i + '.').toString());
        }
    }

    public Sequence drop(int i) {
        int i2 = this.count + i;
        return i2 < 0 ? new DropSequence(this, i) : new DropSequence(this.sequence, i2);
    }

    public Iterator iterator() {
        return new DropSequence$iterator$1(this);
    }
}
