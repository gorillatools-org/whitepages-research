package androidx.fragment.app;

import androidx.fragment.app.SpecialEffectsController;

public final /* synthetic */ class SpecialEffectsController$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ SpecialEffectsController f$0;
    public final /* synthetic */ SpecialEffectsController.FragmentStateManagerOperation f$1;

    public /* synthetic */ SpecialEffectsController$$ExternalSyntheticLambda0(SpecialEffectsController specialEffectsController, SpecialEffectsController.FragmentStateManagerOperation fragmentStateManagerOperation) {
        this.f$0 = specialEffectsController;
        this.f$1 = fragmentStateManagerOperation;
    }

    public final void run() {
        SpecialEffectsController.enqueue$lambda$4$lambda$2(this.f$0, this.f$1);
    }
}
