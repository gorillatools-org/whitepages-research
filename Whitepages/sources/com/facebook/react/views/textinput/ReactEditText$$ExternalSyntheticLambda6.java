package com.facebook.react.views.textinput;

import androidx.core.util.Predicate;
import com.facebook.react.views.text.internal.span.CustomStyleSpan;

public final /* synthetic */ class ReactEditText$$ExternalSyntheticLambda6 implements Predicate {
    public final /* synthetic */ ReactEditText f$0;

    public /* synthetic */ ReactEditText$$ExternalSyntheticLambda6(ReactEditText reactEditText) {
        this.f$0 = reactEditText;
    }

    public final boolean test(Object obj) {
        return this.f$0.lambda$stripStyleEquivalentSpans$6((CustomStyleSpan) obj);
    }
}
