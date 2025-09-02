package kotlin.text;

import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;

public abstract class RegexKt {
    /* access modifiers changed from: private */
    public static final MatchResult findNext(Matcher matcher, int i, CharSequence charSequence) {
        if (!matcher.find(i)) {
            return null;
        }
        return new MatcherMatchResult(matcher, charSequence);
    }

    /* access modifiers changed from: private */
    public static final IntRange range(MatchResult matchResult, int i) {
        return RangesKt.until(matchResult.start(i), matchResult.end(i));
    }
}
