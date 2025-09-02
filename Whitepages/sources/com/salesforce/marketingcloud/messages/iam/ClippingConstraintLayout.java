package com.salesforce.marketingcloud.messages.iam;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.TypedValue;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.salesforce.marketingcloud.MCKeep;

@SuppressLint({"UnknownNullness"})
@MCKeep
public class ClippingConstraintLayout extends ConstraintLayout {
    private float borderWidth;
    private float cornerRadius;
    private Path path = new Path();
    private RectF rect = new RectF(0.0f, 0.0f, 0.0f, 0.0f);
    private Rect tmpRect = new Rect(0, 0, 0, 0);

    public ClippingConstraintLayout(Context context) {
        super(context);
        init();
    }

    private void init() {
        Resources resources = getResources();
        this.cornerRadius = (float) Math.floor((double) TypedValue.applyDimension(1, 30.0f, resources.getDisplayMetrics()));
        this.borderWidth = TypedValue.applyDimension(1, 3.0f, resources.getDisplayMetrics());
    }

    public void dispatchDraw(Canvas canvas) {
        int save = canvas.save();
        canvas.clipPath(this.path);
        super.dispatchDraw(canvas);
        canvas.restoreToCount(save);
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        this.path.reset();
        getDrawingRect(this.tmpRect);
        RectF rectF = this.rect;
        Rect rect2 = this.tmpRect;
        float f = this.borderWidth;
        rectF.set(((float) rect2.left) + f, ((float) rect2.top) + f, ((float) rect2.right) - f, ((float) rect2.bottom) - f);
        Path path2 = this.path;
        RectF rectF2 = this.rect;
        float f2 = this.cornerRadius;
        path2.addRoundRect(rectF2, f2, f2, Path.Direction.CW);
        this.path.close();
    }

    public void setClippingDetails(float f, float f2) {
        if (this.borderWidth != f || this.cornerRadius != f2) {
            this.borderWidth = f;
            this.cornerRadius = (float) Math.floor((double) f2);
            invalidate();
        }
    }

    public ClippingConstraintLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    public ClippingConstraintLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init();
    }
}
