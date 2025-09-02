package com.facebook;

import android.content.Intent;
import com.facebook.internal.CallbackManagerImpl;

public interface CallbackManager {
    boolean onActivityResult(int i, int i2, Intent intent);

    public static final class Factory {
        public static final Factory INSTANCE = new Factory();

        private Factory() {
        }

        public static final CallbackManager create() {
            return new CallbackManagerImpl();
        }
    }
}
