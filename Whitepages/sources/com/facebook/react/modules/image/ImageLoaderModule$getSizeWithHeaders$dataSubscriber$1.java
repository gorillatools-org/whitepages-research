package com.facebook.react.modules.image;

import com.facebook.common.references.CloseableReference;
import com.facebook.datasource.BaseDataSubscriber;
import com.facebook.datasource.DataSource;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.WritableMap;
import kotlin.jvm.internal.Intrinsics;

public final class ImageLoaderModule$getSizeWithHeaders$dataSubscriber$1 extends BaseDataSubscriber {
    final /* synthetic */ Promise $promise;

    ImageLoaderModule$getSizeWithHeaders$dataSubscriber$1(Promise promise) {
        this.$promise = promise;
    }

    /* access modifiers changed from: protected */
    public void onNewResultImpl(DataSource dataSource) {
        Intrinsics.checkNotNullParameter(dataSource, "dataSource");
        if (dataSource.isFinished()) {
            CloseableReference closeableReference = (CloseableReference) dataSource.getResult();
            if (closeableReference != null) {
                try {
                    Object obj = closeableReference.get();
                    Intrinsics.checkNotNullExpressionValue(obj, "get(...)");
                    CloseableImage closeableImage = (CloseableImage) obj;
                    WritableMap createMap = Arguments.createMap();
                    Intrinsics.checkNotNullExpressionValue(createMap, "createMap(...)");
                    createMap.putInt("width", closeableImage.getWidth());
                    createMap.putInt("height", closeableImage.getHeight());
                    this.$promise.resolve(createMap);
                } catch (Exception e) {
                    this.$promise.reject("E_GET_SIZE_FAILURE", (Throwable) e);
                } catch (Throwable th) {
                    CloseableReference.closeSafely(closeableReference);
                    throw th;
                }
                CloseableReference.closeSafely(closeableReference);
                return;
            }
            this.$promise.reject("E_GET_SIZE_FAILURE", "Failed to get the size of the image");
        }
    }

    /* access modifiers changed from: protected */
    public void onFailureImpl(DataSource dataSource) {
        Intrinsics.checkNotNullParameter(dataSource, "dataSource");
        this.$promise.reject("E_GET_SIZE_FAILURE", dataSource.getFailureCause());
    }
}
