package com.facebook.react.uimanager.drawable;

import kotlin.jvm.internal.MutablePropertyReference0Impl;

/* synthetic */ class CompositeBackgroundDrawable$withNewOutline$2 extends MutablePropertyReference0Impl {
    CompositeBackgroundDrawable$withNewOutline$2(Object obj) {
        super(obj, CompositeBackgroundDrawable.class, "outline", "getOutline()Lcom/facebook/react/uimanager/drawable/OutlineDrawable;", 0);
    }

    public Object get() {
        return ((CompositeBackgroundDrawable) this.receiver).getOutline();
    }

    public void set(Object obj) {
        ((CompositeBackgroundDrawable) this.receiver).outline = (OutlineDrawable) obj;
    }
}
