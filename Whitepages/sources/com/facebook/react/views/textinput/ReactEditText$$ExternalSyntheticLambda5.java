package com.facebook.react.views.textinput;

import androidx.core.util.Predicate;
import com.facebook.react.views.text.internal.span.CustomLetterSpacingSpan;

public final /* synthetic */ class ReactEditText$$ExternalSyntheticLambda5 implements Predicate {
    public final /* synthetic */ ReactEditText f$0;

    public /* synthetic */ ReactEditText$$ExternalSyntheticLambda5(ReactEditText reactEditText) {
        this.f$0 = reactEditText;
    }

    public final boolean test(Object obj) {
        return this.f$0.lambda$stripStyleEquivalentSpans$5((CustomLetterSpacingSpan) obj);
    }
}
