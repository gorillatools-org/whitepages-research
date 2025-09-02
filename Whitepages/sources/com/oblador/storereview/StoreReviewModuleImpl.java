package com.oblador.storereview;

import android.app.Activity;
import android.util.Log;
import com.facebook.react.bridge.ReactApplicationContext;
import com.google.android.gms.tasks.Task;
import com.google.android.play.core.review.ReviewInfo;
import com.google.android.play.core.review.ReviewManager;
import com.google.android.play.core.review.ReviewManagerFactory;

public abstract class StoreReviewModuleImpl {
    public static void requestReview(ReactApplicationContext reactApplicationContext) {
        ReviewManager create = ReviewManagerFactory.create(reactApplicationContext);
        create.requestReviewFlow().addOnCompleteListener(new StoreReviewModuleImpl$$ExternalSyntheticLambda0(reactApplicationContext, create));
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$requestReview$0(ReactApplicationContext reactApplicationContext, ReviewManager reviewManager, Task task) {
        if (task.isSuccessful()) {
            ReviewInfo reviewInfo = (ReviewInfo) task.getResult();
            Activity currentActivity = reactApplicationContext.getCurrentActivity();
            if (currentActivity != null) {
                reviewManager.launchReviewFlow(currentActivity, reviewInfo);
            } else {
                Log.w("RNStoreReview", "Current activity is null. Unable to launch review flow.");
            }
        } else {
            Log.w("RNStoreReview", "Requesting review failed", task.getException());
        }
    }
}
