package kotlin.text;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

final class MatcherMatchResult$groups$1$iterator$1 extends Lambda implements Function1 {
    final /* synthetic */ MatcherMatchResult$groups$1 this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    MatcherMatchResult$groups$1$iterator$1(MatcherMatchResult$groups$1 matcherMatchResult$groups$1) {
        super(1);
        this.this$0 = matcherMatchResult$groups$1;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        return invoke(((Number) obj).intValue());
    }

    public final MatchGroup invoke(int i) {
        return this.this$0.get(i);
    }
}
