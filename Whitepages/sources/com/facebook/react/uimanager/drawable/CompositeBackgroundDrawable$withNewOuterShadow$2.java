package com.facebook.react.uimanager.drawable;

import android.graphics.drawable.LayerDrawable;
import kotlin.jvm.internal.MutablePropertyReference0Impl;

/* synthetic */ class CompositeBackgroundDrawable$withNewOuterShadow$2 extends MutablePropertyReference0Impl {
    CompositeBackgroundDrawable$withNewOuterShadow$2(Object obj) {
        super(obj, CompositeBackgroundDrawable.class, "outerShadows", "getOuterShadows()Landroid/graphics/drawable/LayerDrawable;", 0);
    }

    public Object get() {
        return ((CompositeBackgroundDrawable) this.receiver).getOuterShadows();
    }

    public void set(Object obj) {
        ((CompositeBackgroundDrawable) this.receiver).outerShadows = (LayerDrawable) obj;
    }
}
