package kotlin.text;

import java.util.Iterator;
import kotlin.collections.AbstractCollection;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
import kotlin.sequences.SequencesKt;

public final class MatcherMatchResult$groups$1 extends AbstractCollection implements MatchGroupCollection {
    final /* synthetic */ MatcherMatchResult this$0;

    public boolean isEmpty() {
        return false;
    }

    MatcherMatchResult$groups$1(MatcherMatchResult matcherMatchResult) {
        this.this$0 = matcherMatchResult;
    }

    public final /* bridge */ boolean contains(Object obj) {
        if (!(obj == null ? true : obj instanceof MatchGroup)) {
            return false;
        }
        return contains((MatchGroup) obj);
    }

    public /* bridge */ boolean contains(MatchGroup matchGroup) {
        return super.contains(matchGroup);
    }

    public int getSize() {
        return this.this$0.getMatchResult().groupCount() + 1;
    }

    public Iterator iterator() {
        return SequencesKt.map(CollectionsKt.asSequence(CollectionsKt.getIndices(this)), new MatcherMatchResult$groups$1$iterator$1(this)).iterator();
    }

    public MatchGroup get(int i) {
        IntRange access$range = RegexKt.range(this.this$0.getMatchResult(), i);
        if (access$range.getStart().intValue() < 0) {
            return null;
        }
        String group = this.this$0.getMatchResult().group(i);
        Intrinsics.checkNotNullExpressionValue(group, "group(...)");
        return new MatchGroup(group, access$range);
    }
}
