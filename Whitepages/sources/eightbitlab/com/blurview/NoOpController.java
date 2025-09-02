package eightbitlab.com.blurview;

import android.graphics.Canvas;
import android.graphics.drawable.Drawable;

public class NoOpController implements BlurController {
    public void destroy() {
    }

    public boolean draw(Canvas canvas) {
        return true;
    }

    public BlurViewFacade setBlurAutoUpdate(boolean z) {
        return this;
    }

    public BlurViewFacade setBlurEnabled(boolean z) {
        return this;
    }

    public BlurViewFacade setBlurRadius(float f) {
        return this;
    }

    public BlurViewFacade setFrameClearDrawable(Drawable drawable) {
        return this;
    }

    public BlurViewFacade setOverlayColor(int i) {
        return this;
    }

    public void updateBlurViewSize() {
    }
}
