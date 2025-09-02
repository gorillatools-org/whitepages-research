package androidx.fragment.app;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.util.Log;
import android.view.View;
import androidx.fragment.app.DefaultSpecialEffectsController;
import androidx.fragment.app.SpecialEffectsController;
import kotlin.jvm.internal.Intrinsics;

public final class DefaultSpecialEffectsController$startAnimations$1 extends AnimatorListenerAdapter {
    final /* synthetic */ DefaultSpecialEffectsController.AnimationInfo $animationInfo;
    final /* synthetic */ boolean $isHideOperation;
    final /* synthetic */ SpecialEffectsController.Operation $operation;
    final /* synthetic */ View $viewToAnimate;
    final /* synthetic */ DefaultSpecialEffectsController this$0;

    DefaultSpecialEffectsController$startAnimations$1(DefaultSpecialEffectsController defaultSpecialEffectsController, View view, boolean z, SpecialEffectsController.Operation operation, DefaultSpecialEffectsController.AnimationInfo animationInfo) {
        this.this$0 = defaultSpecialEffectsController;
        this.$viewToAnimate = view;
        this.$isHideOperation = z;
        this.$operation = operation;
        this.$animationInfo = animationInfo;
    }

    public void onAnimationEnd(Animator animator) {
        Intrinsics.checkNotNullParameter(animator, "anim");
        this.this$0.getContainer().endViewTransition(this.$viewToAnimate);
        if (this.$isHideOperation) {
            SpecialEffectsController.Operation.State finalState = this.$operation.getFinalState();
            View view = this.$viewToAnimate;
            Intrinsics.checkNotNullExpressionValue(view, "viewToAnimate");
            finalState.applyState(view);
        }
        this.$animationInfo.completeSpecialEffect();
        if (FragmentManager.isLoggingEnabled(2)) {
            Log.v("FragmentManager", "Animator from operation " + this.$operation + " has ended.");
        }
    }
}
