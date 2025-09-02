package kotlin.text;

import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;

public final class MatchGroup {
    private final IntRange range;
    private final String value;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MatchGroup)) {
            return false;
        }
        MatchGroup matchGroup = (MatchGroup) obj;
        return Intrinsics.areEqual((Object) this.value, (Object) matchGroup.value) && Intrinsics.areEqual((Object) this.range, (Object) matchGroup.range);
    }

    public int hashCode() {
        return (this.value.hashCode() * 31) + this.range.hashCode();
    }

    public String toString() {
        return "MatchGroup(value=" + this.value + ", range=" + this.range + ')';
    }

    public MatchGroup(String str, IntRange intRange) {
        Intrinsics.checkNotNullParameter(str, "value");
        Intrinsics.checkNotNullParameter(intRange, "range");
        this.value = str;
        this.range = intRange;
    }
}
