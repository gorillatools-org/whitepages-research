package com.facebook.react.views.modal;

import android.content.DialogInterface;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.events.EventDispatcher;
import com.facebook.react.views.modal.ReactModalHostView;

public final /* synthetic */ class ReactModalHostManager$$ExternalSyntheticLambda0 implements ReactModalHostView.OnRequestCloseListener {
    public final /* synthetic */ EventDispatcher f$0;
    public final /* synthetic */ ThemedReactContext f$1;
    public final /* synthetic */ ReactModalHostView f$2;

    public /* synthetic */ ReactModalHostManager$$ExternalSyntheticLambda0(EventDispatcher eventDispatcher, ThemedReactContext themedReactContext, ReactModalHostView reactModalHostView) {
        this.f$0 = eventDispatcher;
        this.f$1 = themedReactContext;
        this.f$2 = reactModalHostView;
    }

    public final void onRequestClose(DialogInterface dialogInterface) {
        ReactModalHostManager.addEventEmitters$lambda$0(this.f$0, this.f$1, this.f$2, dialogInterface);
    }
}
