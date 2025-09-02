package com.facebook.react.views.unimplementedview;

import android.content.Context;
import android.widget.LinearLayout;
import androidx.appcompat.widget.AppCompatTextView;
import kotlin.jvm.internal.Intrinsics;

public final class ReactUnimplementedView extends LinearLayout {
    private final AppCompatTextView textView;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ReactUnimplementedView(Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        AppCompatTextView appCompatTextView = new AppCompatTextView(context);
        this.textView = appCompatTextView;
        appCompatTextView.setLayoutParams(new LinearLayout.LayoutParams(-2, -1));
        appCompatTextView.setGravity(17);
        appCompatTextView.setTextColor(-1);
        setBackgroundColor(1442775040);
        setGravity(1);
        setOrientation(1);
        addView(appCompatTextView);
    }

    public final void setName$ReactAndroid_release(String str) {
        Intrinsics.checkNotNullParameter(str, "name");
        AppCompatTextView appCompatTextView = this.textView;
        appCompatTextView.setText("'" + str + "' is not Fabric compatible yet.");
    }
}
