package com.facebook.react.devsupport;

import android.view.View;
import androidx.core.view.OnApplyWindowInsetsListener;
import androidx.core.view.WindowInsetsCompat;
import kotlin.jvm.functions.Function2;

public final /* synthetic */ class LogBoxDialog$$ExternalSyntheticLambda1 implements OnApplyWindowInsetsListener {
    public final /* synthetic */ Function2 f$0;

    public /* synthetic */ LogBoxDialog$$ExternalSyntheticLambda1(Function2 function2) {
        this.f$0 = function2;
    }

    public final WindowInsetsCompat onApplyWindowInsets(View view, WindowInsetsCompat windowInsetsCompat) {
        return LogBoxDialog.onCreate$lambda$3$lambda$2(this.f$0, view, windowInsetsCompat);
    }
}
