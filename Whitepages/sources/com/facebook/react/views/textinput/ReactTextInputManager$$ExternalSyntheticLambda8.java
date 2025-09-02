package com.facebook.react.views.textinput;

import android.view.View;
import com.facebook.react.uimanager.ThemedReactContext;

public final /* synthetic */ class ReactTextInputManager$$ExternalSyntheticLambda8 implements View.OnFocusChangeListener {
    public final /* synthetic */ ThemedReactContext f$0;
    public final /* synthetic */ ReactEditText f$1;

    public /* synthetic */ ReactTextInputManager$$ExternalSyntheticLambda8(ThemedReactContext themedReactContext, ReactEditText reactEditText) {
        this.f$0 = themedReactContext;
        this.f$1 = reactEditText;
    }

    public final void onFocusChange(View view, boolean z) {
        ReactTextInputManager.lambda$addEventEmitters$0(this.f$0, this.f$1, view, z);
    }
}
