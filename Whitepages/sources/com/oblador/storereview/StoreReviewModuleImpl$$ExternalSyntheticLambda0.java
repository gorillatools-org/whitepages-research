package com.oblador.storereview;

import com.facebook.react.bridge.ReactApplicationContext;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.play.core.review.ReviewManager;

public final /* synthetic */ class StoreReviewModuleImpl$$ExternalSyntheticLambda0 implements OnCompleteListener {
    public final /* synthetic */ ReactApplicationContext f$0;
    public final /* synthetic */ ReviewManager f$1;

    public /* synthetic */ StoreReviewModuleImpl$$ExternalSyntheticLambda0(ReactApplicationContext reactApplicationContext, ReviewManager reviewManager) {
        this.f$0 = reactApplicationContext;
        this.f$1 = reviewManager;
    }

    public final void onComplete(Task task) {
        StoreReviewModuleImpl.lambda$requestReview$0(this.f$0, this.f$1, task);
    }
}
