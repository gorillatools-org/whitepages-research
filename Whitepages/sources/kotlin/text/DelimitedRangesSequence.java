package kotlin.text;

import java.util.Iterator;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.Sequence;

final class DelimitedRangesSequence implements Sequence {
    /* access modifiers changed from: private */
    public final Function2 getNextMatch;
    /* access modifiers changed from: private */
    public final CharSequence input;
    /* access modifiers changed from: private */
    public final int limit;
    /* access modifiers changed from: private */
    public final int startIndex;

    public DelimitedRangesSequence(CharSequence charSequence, int i, int i2, Function2 function2) {
        Intrinsics.checkNotNullParameter(charSequence, "input");
        Intrinsics.checkNotNullParameter(function2, "getNextMatch");
        this.input = charSequence;
        this.startIndex = i;
        this.limit = i2;
        this.getNextMatch = function2;
    }

    public Iterator iterator() {
        return new DelimitedRangesSequence$iterator$1(this);
    }
}
