package com.facebook.react.views.switchview;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import androidx.appcompat.widget.SwitchCompat;
import kotlin.jvm.internal.Intrinsics;

public final class ReactSwitch extends SwitchCompat {
    private boolean allowChange = true;
    private Integer trackColorForFalse;
    private Integer trackColorForTrue;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ReactSwitch(Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    public void setChecked(boolean z) {
        if (!this.allowChange || isChecked() == z) {
            super.setChecked(isChecked());
            return;
        }
        this.allowChange = false;
        super.setChecked(z);
        setTrackColor(z);
    }

    public void setBackgroundColor(int i) {
        setBackground(new RippleDrawable(createRippleDrawableColorStateList(i), new ColorDrawable(i), (Drawable) null));
    }

    public final void setColor(Drawable drawable, Integer num) {
        Intrinsics.checkNotNullParameter(drawable, "drawable");
        if (num == null) {
            drawable.clearColorFilter();
        } else {
            drawable.setColorFilter(new PorterDuffColorFilter(num.intValue(), PorterDuff.Mode.MULTIPLY));
        }
    }

    public final void setTrackColor(Integer num) {
        Drawable trackDrawable = super.getTrackDrawable();
        Intrinsics.checkNotNullExpressionValue(trackDrawable, "getTrackDrawable(...)");
        setColor(trackDrawable, num);
    }

    public final void setThumbColor(Integer num) {
        Drawable thumbDrawable = super.getThumbDrawable();
        Intrinsics.checkNotNullExpressionValue(thumbDrawable, "getThumbDrawable(...)");
        setColor(thumbDrawable, num);
        if (num != null && (super.getBackground() instanceof RippleDrawable)) {
            ColorStateList createRippleDrawableColorStateList = createRippleDrawableColorStateList(num.intValue());
            Drawable background = super.getBackground();
            Intrinsics.checkNotNull(background, "null cannot be cast to non-null type android.graphics.drawable.RippleDrawable");
            ((RippleDrawable) background).setColor(createRippleDrawableColorStateList);
        }
    }

    public final void setOn(boolean z) {
        if (isChecked() != z) {
            super.setChecked(z);
            setTrackColor(z);
        }
        this.allowChange = true;
    }

    public final void setTrackColorForTrue(Integer num) {
        if (!Intrinsics.areEqual((Object) num, (Object) this.trackColorForTrue)) {
            this.trackColorForTrue = num;
            if (isChecked()) {
                setTrackColor(this.trackColorForTrue);
            }
        }
    }

    public final void setTrackColorForFalse(Integer num) {
        if (!Intrinsics.areEqual((Object) num, (Object) this.trackColorForFalse)) {
            this.trackColorForFalse = num;
            if (!isChecked()) {
                setTrackColor(this.trackColorForFalse);
            }
        }
    }

    private final void setTrackColor(boolean z) {
        Integer num = this.trackColorForTrue;
        if (num != null || this.trackColorForFalse != null) {
            if (!z) {
                num = this.trackColorForFalse;
            }
            setTrackColor(num);
        }
    }

    private final ColorStateList createRippleDrawableColorStateList(int i) {
        return new ColorStateList(new int[][]{new int[]{16842919}}, new int[]{i});
    }
}
