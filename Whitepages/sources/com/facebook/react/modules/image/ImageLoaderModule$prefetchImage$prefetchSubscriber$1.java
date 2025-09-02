package com.facebook.react.modules.image;

import com.facebook.datasource.BaseDataSubscriber;
import com.facebook.datasource.DataSource;
import com.facebook.react.bridge.Promise;
import kotlin.jvm.internal.Intrinsics;

public final class ImageLoaderModule$prefetchImage$prefetchSubscriber$1 extends BaseDataSubscriber {
    final /* synthetic */ Promise $promise;
    final /* synthetic */ int $requestId;
    final /* synthetic */ ImageLoaderModule this$0;

    ImageLoaderModule$prefetchImage$prefetchSubscriber$1(ImageLoaderModule imageLoaderModule, int i, Promise promise) {
        this.this$0 = imageLoaderModule;
        this.$requestId = i;
        this.$promise = promise;
    }

    /* access modifiers changed from: protected */
    public void onNewResultImpl(DataSource dataSource) {
        Intrinsics.checkNotNullParameter(dataSource, "dataSource");
        if (dataSource.isFinished()) {
            try {
                DataSource unused = this.this$0.removeRequest(this.$requestId);
                this.$promise.resolve(Boolean.TRUE);
            } catch (Exception e) {
                this.$promise.reject("E_PREFETCH_FAILURE", (Throwable) e);
            } catch (Throwable th) {
                dataSource.close();
                throw th;
            }
            dataSource.close();
        }
    }

    /* access modifiers changed from: protected */
    public void onFailureImpl(DataSource dataSource) {
        Intrinsics.checkNotNullParameter(dataSource, "dataSource");
        try {
            DataSource unused = this.this$0.removeRequest(this.$requestId);
            this.$promise.reject("E_PREFETCH_FAILURE", dataSource.getFailureCause());
        } finally {
            dataSource.close();
        }
    }
}
