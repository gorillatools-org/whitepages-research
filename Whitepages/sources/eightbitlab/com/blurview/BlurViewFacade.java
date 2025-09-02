package eightbitlab.com.blurview;

import android.graphics.drawable.Drawable;

public interface BlurViewFacade {
    BlurViewFacade setBlurAutoUpdate(boolean z);

    BlurViewFacade setBlurEnabled(boolean z);

    BlurViewFacade setBlurRadius(float f);

    BlurViewFacade setFrameClearDrawable(Drawable drawable);

    BlurViewFacade setOverlayColor(int i);
}
