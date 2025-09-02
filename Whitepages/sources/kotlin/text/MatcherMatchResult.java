package kotlin.text;

import java.util.List;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import kotlin.jvm.internal.Intrinsics;

final class MatcherMatchResult implements MatchResult {
    private List groupValues_;
    private final MatchGroupCollection groups = new MatcherMatchResult$groups$1(this);
    private final CharSequence input;
    private final Matcher matcher;

    public MatcherMatchResult(Matcher matcher2, CharSequence charSequence) {
        Intrinsics.checkNotNullParameter(matcher2, "matcher");
        Intrinsics.checkNotNullParameter(charSequence, "input");
        this.matcher = matcher2;
        this.input = charSequence;
    }

    /* access modifiers changed from: private */
    public final MatchResult getMatchResult() {
        return this.matcher;
    }

    public List getGroupValues() {
        if (this.groupValues_ == null) {
            this.groupValues_ = new MatcherMatchResult$groupValues$1(this);
        }
        List list = this.groupValues_;
        Intrinsics.checkNotNull(list);
        return list;
    }
}
