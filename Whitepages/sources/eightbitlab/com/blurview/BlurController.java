package eightbitlab.com.blurview;

import android.graphics.Canvas;

public interface BlurController extends BlurViewFacade {
    void destroy();

    boolean draw(Canvas canvas);

    void updateBlurViewSize();
}
