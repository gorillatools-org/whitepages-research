package com.facebook.react.modules.intent;

import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.bridge.Promise;
import kotlin.Unit;

public final class IntentModule$waitForActivityAndGetInitialURL$1 implements LifecycleEventListener {
    final /* synthetic */ IntentModule this$0;

    public void onHostDestroy() {
    }

    public void onHostPause() {
    }

    IntentModule$waitForActivityAndGetInitialURL$1(IntentModule intentModule) {
        this.this$0 = intentModule;
    }

    public void onHostResume() {
        this.this$0.getReactApplicationContext().removeLifecycleEventListener(this);
        IntentModule intentModule = this.this$0;
        synchronized (intentModule) {
            try {
                for (Promise initialURL : intentModule.pendingOpenURLPromises) {
                    intentModule.getInitialURL(initialURL);
                }
                intentModule.initialURLListener = null;
                intentModule.pendingOpenURLPromises.clear();
                Unit unit = Unit.INSTANCE;
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
