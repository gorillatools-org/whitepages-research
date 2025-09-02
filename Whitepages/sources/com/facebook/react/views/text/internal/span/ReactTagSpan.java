package com.facebook.react.views.text.internal.span;

public final class ReactTagSpan implements ReactSpan {
    private final int reactTag;

    public ReactTagSpan(int i) {
        this.reactTag = i;
    }

    public final int getReactTag() {
        return this.reactTag;
    }
}
