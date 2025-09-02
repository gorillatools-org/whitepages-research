package androidx.fragment.app;

import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import androidx.fragment.app.DefaultSpecialEffectsController;
import androidx.fragment.app.SpecialEffectsController;
import kotlin.jvm.internal.Intrinsics;

public final class DefaultSpecialEffectsController$startAnimations$3 implements Animation.AnimationListener {
    final /* synthetic */ DefaultSpecialEffectsController.AnimationInfo $animationInfo;
    final /* synthetic */ SpecialEffectsController.Operation $operation;
    final /* synthetic */ View $viewToAnimate;
    final /* synthetic */ DefaultSpecialEffectsController this$0;

    public void onAnimationRepeat(Animation animation) {
        Intrinsics.checkNotNullParameter(animation, "animation");
    }

    DefaultSpecialEffectsController$startAnimations$3(SpecialEffectsController.Operation operation, DefaultSpecialEffectsController defaultSpecialEffectsController, View view, DefaultSpecialEffectsController.AnimationInfo animationInfo) {
        this.$operation = operation;
        this.this$0 = defaultSpecialEffectsController;
        this.$viewToAnimate = view;
        this.$animationInfo = animationInfo;
    }

    public void onAnimationStart(Animation animation) {
        Intrinsics.checkNotNullParameter(animation, "animation");
        if (FragmentManager.isLoggingEnabled(2)) {
            Log.v("FragmentManager", "Animation from operation " + this.$operation + " has reached onAnimationStart.");
        }
    }

    public void onAnimationEnd(Animation animation) {
        Intrinsics.checkNotNullParameter(animation, "animation");
        this.this$0.getContainer().post(new DefaultSpecialEffectsController$startAnimations$3$$ExternalSyntheticLambda0(this.this$0, this.$viewToAnimate, this.$animationInfo));
        if (FragmentManager.isLoggingEnabled(2)) {
            Log.v("FragmentManager", "Animation from operation " + this.$operation + " has ended.");
        }
    }

    /* access modifiers changed from: private */
    public static final void onAnimationEnd$lambda$0(DefaultSpecialEffectsController defaultSpecialEffectsController, View view, DefaultSpecialEffectsController.AnimationInfo animationInfo) {
        Intrinsics.checkNotNullParameter(defaultSpecialEffectsController, "this$0");
        Intrinsics.checkNotNullParameter(animationInfo, "$animationInfo");
        defaultSpecialEffectsController.getContainer().endViewTransition(view);
        animationInfo.completeSpecialEffect();
    }
}
