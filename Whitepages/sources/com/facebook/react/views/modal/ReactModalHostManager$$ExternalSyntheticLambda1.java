package com.facebook.react.views.modal;

import android.content.DialogInterface;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.events.EventDispatcher;

public final /* synthetic */ class ReactModalHostManager$$ExternalSyntheticLambda1 implements DialogInterface.OnShowListener {
    public final /* synthetic */ EventDispatcher f$0;
    public final /* synthetic */ ThemedReactContext f$1;
    public final /* synthetic */ ReactModalHostView f$2;

    public /* synthetic */ ReactModalHostManager$$ExternalSyntheticLambda1(EventDispatcher eventDispatcher, ThemedReactContext themedReactContext, ReactModalHostView reactModalHostView) {
        this.f$0 = eventDispatcher;
        this.f$1 = themedReactContext;
        this.f$2 = reactModalHostView;
    }

    public final void onShow(DialogInterface dialogInterface) {
        ReactModalHostManager.addEventEmitters$lambda$1(this.f$0, this.f$1, this.f$2, dialogInterface);
    }
}
