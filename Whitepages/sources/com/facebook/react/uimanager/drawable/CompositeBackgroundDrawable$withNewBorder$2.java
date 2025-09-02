package com.facebook.react.uimanager.drawable;

import kotlin.jvm.internal.MutablePropertyReference0Impl;

/* synthetic */ class CompositeBackgroundDrawable$withNewBorder$2 extends MutablePropertyReference0Impl {
    CompositeBackgroundDrawable$withNewBorder$2(Object obj) {
        super(obj, CompositeBackgroundDrawable.class, "border", "getBorder()Lcom/facebook/react/uimanager/drawable/BorderDrawable;", 0);
    }

    public Object get() {
        return ((CompositeBackgroundDrawable) this.receiver).getBorder();
    }

    public void set(Object obj) {
        ((CompositeBackgroundDrawable) this.receiver).border = (BorderDrawable) obj;
    }
}
