package androidx.fragment.app;

import androidx.fragment.app.SpecialEffectsController;
import java.util.List;

public final /* synthetic */ class DefaultSpecialEffectsController$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ List f$0;
    public final /* synthetic */ SpecialEffectsController.Operation f$1;
    public final /* synthetic */ DefaultSpecialEffectsController f$2;

    public /* synthetic */ DefaultSpecialEffectsController$$ExternalSyntheticLambda0(List list, SpecialEffectsController.Operation operation, DefaultSpecialEffectsController defaultSpecialEffectsController) {
        this.f$0 = list;
        this.f$1 = operation;
        this.f$2 = defaultSpecialEffectsController;
    }

    public final void run() {
        DefaultSpecialEffectsController.executeOperations$lambda$2(this.f$0, this.f$1, this.f$2);
    }
}
