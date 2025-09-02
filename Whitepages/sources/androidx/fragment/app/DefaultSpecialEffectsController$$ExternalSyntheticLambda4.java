package androidx.fragment.app;

import android.graphics.Rect;
import android.view.View;

public final /* synthetic */ class DefaultSpecialEffectsController$$ExternalSyntheticLambda4 implements Runnable {
    public final /* synthetic */ FragmentTransitionImpl f$0;
    public final /* synthetic */ View f$1;
    public final /* synthetic */ Rect f$2;

    public /* synthetic */ DefaultSpecialEffectsController$$ExternalSyntheticLambda4(FragmentTransitionImpl fragmentTransitionImpl, View view, Rect rect) {
        this.f$0 = fragmentTransitionImpl;
        this.f$1 = view;
        this.f$2 = rect;
    }

    public final void run() {
        DefaultSpecialEffectsController.startTransitions$lambda$10(this.f$0, this.f$1, this.f$2);
    }
}
