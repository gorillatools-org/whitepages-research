package com.facebook.react.uimanager.drawable;

import android.graphics.drawable.Drawable;
import kotlin.jvm.internal.MutablePropertyReference0Impl;

/* synthetic */ class CompositeBackgroundDrawable$withNewFeedbackUnderlay$2 extends MutablePropertyReference0Impl {
    CompositeBackgroundDrawable$withNewFeedbackUnderlay$2(Object obj) {
        super(obj, CompositeBackgroundDrawable.class, "feedbackUnderlay", "getFeedbackUnderlay()Landroid/graphics/drawable/Drawable;", 0);
    }

    public Object get() {
        return ((CompositeBackgroundDrawable) this.receiver).getFeedbackUnderlay();
    }

    public void set(Object obj) {
        ((CompositeBackgroundDrawable) this.receiver).feedbackUnderlay = (Drawable) obj;
    }
}
