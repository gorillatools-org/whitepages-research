package androidx.fragment.app;

import androidx.fragment.app.DefaultSpecialEffectsController;
import androidx.fragment.app.SpecialEffectsController;

public final /* synthetic */ class DefaultSpecialEffectsController$$ExternalSyntheticLambda6 implements Runnable {
    public final /* synthetic */ DefaultSpecialEffectsController.TransitionInfo f$0;
    public final /* synthetic */ SpecialEffectsController.Operation f$1;

    public /* synthetic */ DefaultSpecialEffectsController$$ExternalSyntheticLambda6(DefaultSpecialEffectsController.TransitionInfo transitionInfo, SpecialEffectsController.Operation operation) {
        this.f$0 = transitionInfo;
        this.f$1 = operation;
    }

    public final void run() {
        DefaultSpecialEffectsController.startTransitions$lambda$14$lambda$13(this.f$0, this.f$1);
    }
}
