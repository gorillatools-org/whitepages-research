package androidx.appcompat.widget;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.LinearLayout;

public class FitWindowsLinearLayout extends LinearLayout {
    private FitWindowsViewGroup$OnFitSystemWindowsListener mListener;

    public void setOnFitSystemWindowsListener(FitWindowsViewGroup$OnFitSystemWindowsListener fitWindowsViewGroup$OnFitSystemWindowsListener) {
    }

    public FitWindowsLinearLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    /* access modifiers changed from: protected */
    public boolean fitSystemWindows(Rect rect) {
        FitWindowsViewGroup$OnFitSystemWindowsListener fitWindowsViewGroup$OnFitSystemWindowsListener = this.mListener;
        if (fitWindowsViewGroup$OnFitSystemWindowsListener != null) {
            fitWindowsViewGroup$OnFitSystemWindowsListener.onFitSystemWindows(rect);
        }
        return super.fitSystemWindows(rect);
    }
}
