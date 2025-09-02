package androidx.appcompat.widget;

import android.view.View;

public abstract class TooltipCompat {
    public static void setTooltipText(View view, CharSequence charSequence) {
        Api26Impl.setTooltipText(view, charSequence);
    }

    static class Api26Impl {
        static void setTooltipText(View view, CharSequence charSequence) {
            view.setTooltipText(charSequence);
        }
    }
}
