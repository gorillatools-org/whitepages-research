package androidx.fragment.app;

import android.view.View;
import androidx.fragment.app.DefaultSpecialEffectsController;

public final /* synthetic */ class DefaultSpecialEffectsController$startAnimations$3$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ DefaultSpecialEffectsController f$0;
    public final /* synthetic */ View f$1;
    public final /* synthetic */ DefaultSpecialEffectsController.AnimationInfo f$2;

    public /* synthetic */ DefaultSpecialEffectsController$startAnimations$3$$ExternalSyntheticLambda0(DefaultSpecialEffectsController defaultSpecialEffectsController, View view, DefaultSpecialEffectsController.AnimationInfo animationInfo) {
        this.f$0 = defaultSpecialEffectsController;
        this.f$1 = view;
        this.f$2 = animationInfo;
    }

    public final void run() {
        DefaultSpecialEffectsController$startAnimations$3.onAnimationEnd$lambda$0(this.f$0, this.f$1, this.f$2);
    }
}
