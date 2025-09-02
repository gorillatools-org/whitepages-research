package com.facebook.react.uimanager.drawable;

import com.facebook.react.modules.appstate.AppStateModule;
import kotlin.jvm.internal.MutablePropertyReference0Impl;

/* synthetic */ class CompositeBackgroundDrawable$withNewBackground$2 extends MutablePropertyReference0Impl {
    CompositeBackgroundDrawable$withNewBackground$2(Object obj) {
        super(obj, CompositeBackgroundDrawable.class, AppStateModule.APP_STATE_BACKGROUND, "getBackground()Lcom/facebook/react/uimanager/drawable/BackgroundDrawable;", 0);
    }

    public Object get() {
        return ((CompositeBackgroundDrawable) this.receiver).getBackground();
    }

    public void set(Object obj) {
        ((CompositeBackgroundDrawable) this.receiver).background = (BackgroundDrawable) obj;
    }
}
