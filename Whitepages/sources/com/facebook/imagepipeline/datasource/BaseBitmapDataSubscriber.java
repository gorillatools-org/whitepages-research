package com.facebook.imagepipeline.datasource;

import android.graphics.Bitmap;
import com.facebook.common.references.CloseableReference;
import com.facebook.datasource.BaseDataSubscriber;
import com.facebook.datasource.DataSource;
import com.facebook.imagepipeline.image.CloseableBitmap;

public abstract class BaseBitmapDataSubscriber extends BaseDataSubscriber {
    /* access modifiers changed from: protected */
    public abstract void onNewResultImpl(Bitmap bitmap);

    public void onNewResultImpl(DataSource dataSource) {
        if (dataSource.isFinished()) {
            CloseableReference closeableReference = (CloseableReference) dataSource.getResult();
            try {
                onNewResultImpl((closeableReference == null || !(closeableReference.get() instanceof CloseableBitmap)) ? null : ((CloseableBitmap) closeableReference.get()).getUnderlyingBitmap());
            } finally {
                CloseableReference.closeSafely(closeableReference);
            }
        }
    }
}
