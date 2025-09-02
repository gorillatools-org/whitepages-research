package com.facebook.imagepipeline.image;

import com.facebook.common.logging.FLog;

public abstract class DefaultCloseableImage extends BaseCloseableImage {
    /* access modifiers changed from: protected */
    public void finalize() {
        if (!isClosed()) {
            FLog.w("CloseableImage", "finalize: %s %x still open.", getClass().getSimpleName(), Integer.valueOf(System.identityHashCode(this)));
            try {
                close();
            } finally {
                super.finalize();
            }
        }
    }
}
