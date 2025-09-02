package androidx.fragment.app;

import android.animation.Animator;
import androidx.core.os.CancellationSignal;
import androidx.fragment.app.SpecialEffectsController;

public final /* synthetic */ class DefaultSpecialEffectsController$$ExternalSyntheticLambda1 implements CancellationSignal.OnCancelListener {
    public final /* synthetic */ Animator f$0;
    public final /* synthetic */ SpecialEffectsController.Operation f$1;

    public /* synthetic */ DefaultSpecialEffectsController$$ExternalSyntheticLambda1(Animator animator, SpecialEffectsController.Operation operation) {
        this.f$0 = animator;
        this.f$1 = operation;
    }

    public final void onCancel() {
        DefaultSpecialEffectsController.startAnimations$lambda$3(this.f$0, this.f$1);
    }
}
