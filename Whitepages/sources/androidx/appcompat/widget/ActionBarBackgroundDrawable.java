package androidx.appcompat.widget;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Outline;
import android.graphics.drawable.Drawable;

class ActionBarBackgroundDrawable extends Drawable {
    final ActionBarContainer mContainer;

    public int getOpacity() {
        return 0;
    }

    public void setAlpha(int i) {
    }

    public void setColorFilter(ColorFilter colorFilter) {
    }

    public ActionBarBackgroundDrawable(ActionBarContainer actionBarContainer) {
        this.mContainer = actionBarContainer;
    }

    public void draw(Canvas canvas) {
        ActionBarContainer actionBarContainer = this.mContainer;
        if (actionBarContainer.mIsSplit) {
            Drawable drawable = actionBarContainer.mSplitBackground;
            if (drawable != null) {
                drawable.draw(canvas);
                return;
            }
            return;
        }
        Drawable drawable2 = actionBarContainer.mBackground;
        if (drawable2 != null) {
            drawable2.draw(canvas);
        }
        ActionBarContainer actionBarContainer2 = this.mContainer;
        Drawable drawable3 = actionBarContainer2.mStackedBackground;
        if (drawable3 != null && actionBarContainer2.mIsStacked) {
            drawable3.draw(canvas);
        }
    }

    public void getOutline(Outline outline) {
        ActionBarContainer actionBarContainer = this.mContainer;
        if (!actionBarContainer.mIsSplit) {
            Drawable drawable = actionBarContainer.mBackground;
            if (drawable != null) {
                Api21Impl.getOutline(drawable, outline);
            }
        } else if (actionBarContainer.mSplitBackground != null) {
            Api21Impl.getOutline(actionBarContainer.mBackground, outline);
        }
    }

    private static class Api21Impl {
        public static void getOutline(Drawable drawable, Outline outline) {
            drawable.getOutline(outline);
        }
    }
}
