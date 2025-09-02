package androidx.fragment.app;

import androidx.collection.ArrayMap;
import androidx.fragment.app.SpecialEffectsController;

public final /* synthetic */ class DefaultSpecialEffectsController$$ExternalSyntheticLambda3 implements Runnable {
    public final /* synthetic */ SpecialEffectsController.Operation f$0;
    public final /* synthetic */ SpecialEffectsController.Operation f$1;
    public final /* synthetic */ boolean f$2;
    public final /* synthetic */ ArrayMap f$3;

    public /* synthetic */ DefaultSpecialEffectsController$$ExternalSyntheticLambda3(SpecialEffectsController.Operation operation, SpecialEffectsController.Operation operation2, boolean z, ArrayMap arrayMap) {
        this.f$0 = operation;
        this.f$1 = operation2;
        this.f$2 = z;
        this.f$3 = arrayMap;
    }

    public final void run() {
        DefaultSpecialEffectsController.startTransitions$lambda$9(this.f$0, this.f$1, this.f$2, this.f$3);
    }
}
