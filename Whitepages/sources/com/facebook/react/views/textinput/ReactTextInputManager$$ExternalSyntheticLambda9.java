package com.facebook.react.views.textinput;

import android.view.KeyEvent;
import android.widget.TextView;
import com.facebook.react.uimanager.ThemedReactContext;

public final /* synthetic */ class ReactTextInputManager$$ExternalSyntheticLambda9 implements TextView.OnEditorActionListener {
    public final /* synthetic */ ReactEditText f$0;
    public final /* synthetic */ ThemedReactContext f$1;

    public /* synthetic */ ReactTextInputManager$$ExternalSyntheticLambda9(ReactEditText reactEditText, ThemedReactContext themedReactContext) {
        this.f$0 = reactEditText;
        this.f$1 = themedReactContext;
    }

    public final boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        return ReactTextInputManager.lambda$addEventEmitters$1(this.f$0, this.f$1, textView, i, keyEvent);
    }
}
