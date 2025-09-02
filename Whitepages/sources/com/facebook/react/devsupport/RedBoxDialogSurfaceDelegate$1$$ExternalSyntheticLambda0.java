package com.facebook.react.devsupport;

import android.view.View;
import androidx.core.view.OnApplyWindowInsetsListener;
import androidx.core.view.WindowInsetsCompat;
import com.facebook.react.devsupport.RedBoxDialogSurfaceDelegate;

public final /* synthetic */ class RedBoxDialogSurfaceDelegate$1$$ExternalSyntheticLambda0 implements OnApplyWindowInsetsListener {
    public final /* synthetic */ int f$0;

    public /* synthetic */ RedBoxDialogSurfaceDelegate$1$$ExternalSyntheticLambda0(int i) {
        this.f$0 = i;
    }

    public final WindowInsetsCompat onApplyWindowInsets(View view, WindowInsetsCompat windowInsetsCompat) {
        return RedBoxDialogSurfaceDelegate.AnonymousClass1.lambda$onCreate$0(this.f$0, view, windowInsetsCompat);
    }
}
