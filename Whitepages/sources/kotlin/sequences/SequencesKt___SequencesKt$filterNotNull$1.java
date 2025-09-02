package kotlin.sequences;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

final class SequencesKt___SequencesKt$filterNotNull$1 extends Lambda implements Function1 {
    public static final SequencesKt___SequencesKt$filterNotNull$1 INSTANCE = new SequencesKt___SequencesKt$filterNotNull$1();

    SequencesKt___SequencesKt$filterNotNull$1() {
        super(1);
    }

    public final Boolean invoke(Object obj) {
        return Boolean.valueOf(obj == null);
    }
}
