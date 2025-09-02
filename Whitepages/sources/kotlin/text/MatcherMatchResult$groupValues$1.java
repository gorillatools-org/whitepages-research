package kotlin.text;

import kotlin.collections.AbstractList;

public final class MatcherMatchResult$groupValues$1 extends AbstractList {
    final /* synthetic */ MatcherMatchResult this$0;

    MatcherMatchResult$groupValues$1(MatcherMatchResult matcherMatchResult) {
        this.this$0 = matcherMatchResult;
    }

    public final /* bridge */ boolean contains(Object obj) {
        if (!(obj instanceof String)) {
            return false;
        }
        return contains((String) obj);
    }

    public /* bridge */ boolean contains(String str) {
        return super.contains(str);
    }

    public final /* bridge */ int indexOf(Object obj) {
        if (!(obj instanceof String)) {
            return -1;
        }
        return indexOf((String) obj);
    }

    public /* bridge */ int indexOf(String str) {
        return super.indexOf(str);
    }

    public final /* bridge */ int lastIndexOf(Object obj) {
        if (!(obj instanceof String)) {
            return -1;
        }
        return lastIndexOf((String) obj);
    }

    public /* bridge */ int lastIndexOf(String str) {
        return super.lastIndexOf(str);
    }

    public int getSize() {
        return this.this$0.getMatchResult().groupCount() + 1;
    }

    public String get(int i) {
        String group = this.this$0.getMatchResult().group(i);
        return group == null ? "" : group;
    }
}
