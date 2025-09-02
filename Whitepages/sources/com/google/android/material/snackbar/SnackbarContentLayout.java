package com.google.android.material.snackbar;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.view.ViewCompat;
import com.google.android.material.R$dimen;
import com.google.android.material.R$id;

public class SnackbarContentLayout extends LinearLayout {
    private Button actionView;
    private int maxInlineActionWidth;
    private TextView messageView;

    public SnackbarContentLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    /* access modifiers changed from: protected */
    public void onFinishInflate() {
        super.onFinishInflate();
        this.messageView = (TextView) findViewById(R$id.snackbar_text);
        this.actionView = (Button) findViewById(R$id.snackbar_action);
    }

    public TextView getMessageView() {
        return this.messageView;
    }

    public Button getActionView() {
        return this.actionView;
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        if (getOrientation() != 1) {
            int dimensionPixelSize = getResources().getDimensionPixelSize(R$dimen.design_snackbar_padding_vertical_2lines);
            int dimensionPixelSize2 = getResources().getDimensionPixelSize(R$dimen.design_snackbar_padding_vertical);
            boolean z = this.messageView.getLayout().getLineCount() > 1;
            if (!z || this.maxInlineActionWidth <= 0 || this.actionView.getMeasuredWidth() <= this.maxInlineActionWidth) {
                if (!z) {
                    dimensionPixelSize = dimensionPixelSize2;
                }
                if (!updateViewsWithinLayout(0, dimensionPixelSize, dimensionPixelSize)) {
                    return;
                }
            } else if (!updateViewsWithinLayout(1, dimensionPixelSize, dimensionPixelSize - dimensionPixelSize2)) {
                return;
            }
            super.onMeasure(i, i2);
        }
    }

    private boolean updateViewsWithinLayout(int i, int i2, int i3) {
        boolean z;
        if (i != getOrientation()) {
            setOrientation(i);
            z = true;
        } else {
            z = false;
        }
        if (this.messageView.getPaddingTop() == i2 && this.messageView.getPaddingBottom() == i3) {
            return z;
        }
        updateTopBottomPadding(this.messageView, i2, i3);
        return true;
    }

    private static void updateTopBottomPadding(View view, int i, int i2) {
        if (ViewCompat.isPaddingRelative(view)) {
            ViewCompat.setPaddingRelative(view, ViewCompat.getPaddingStart(view), i, ViewCompat.getPaddingEnd(view), i2);
        } else {
            view.setPadding(view.getPaddingLeft(), i, view.getPaddingRight(), i2);
        }
    }

    public void setMaxInlineActionWidth(int i) {
        this.maxInlineActionWidth = i;
    }
}
