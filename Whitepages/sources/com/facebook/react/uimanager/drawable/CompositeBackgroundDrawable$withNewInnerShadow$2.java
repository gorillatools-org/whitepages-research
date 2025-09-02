package com.facebook.react.uimanager.drawable;

import android.graphics.drawable.LayerDrawable;
import kotlin.jvm.internal.MutablePropertyReference0Impl;

/* synthetic */ class CompositeBackgroundDrawable$withNewInnerShadow$2 extends MutablePropertyReference0Impl {
    CompositeBackgroundDrawable$withNewInnerShadow$2(Object obj) {
        super(obj, CompositeBackgroundDrawable.class, "innerShadows", "getInnerShadows()Landroid/graphics/drawable/LayerDrawable;", 0);
    }

    public Object get() {
        return ((CompositeBackgroundDrawable) this.receiver).getInnerShadows();
    }

    public void set(Object obj) {
        ((CompositeBackgroundDrawable) this.receiver).innerShadows = (LayerDrawable) obj;
    }
}
