package com.facebook.react.modules.image;

import android.net.Uri;
import com.facebook.imagepipeline.core.ImagePipeline;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.GuardedAsyncTask;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.WritableMap;
import io.branch.rnbranch.RNBranchModule;
import kotlin.jvm.internal.Intrinsics;

public final class ImageLoaderModule$queryCache$1 extends GuardedAsyncTask<Void, Void> {
    final /* synthetic */ Promise $promise;
    final /* synthetic */ ReadableArray $uris;
    final /* synthetic */ ImageLoaderModule this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    ImageLoaderModule$queryCache$1(ImageLoaderModule imageLoaderModule, ReadableArray readableArray, Promise promise, ReactApplicationContext reactApplicationContext) {
        super((ReactContext) reactApplicationContext);
        this.this$0 = imageLoaderModule;
        this.$uris = readableArray;
        this.$promise = promise;
    }

    /* access modifiers changed from: protected */
    public void doInBackgroundGuarded(Void... voidArr) {
        Intrinsics.checkNotNullParameter(voidArr, RNBranchModule.NATIVE_INIT_SESSION_FINISHED_EVENT_PARAMS);
        WritableMap createMap = Arguments.createMap();
        Intrinsics.checkNotNullExpressionValue(createMap, "createMap(...)");
        ImagePipeline access$getImagePipeline = this.this$0.getImagePipeline();
        int size = this.$uris.size();
        for (int i = 0; i < size; i++) {
            String string = this.$uris.getString(i);
            if (!(string == null || string.length() == 0)) {
                Uri parse = Uri.parse(string);
                if (access$getImagePipeline.isInBitmapMemoryCache(parse)) {
                    createMap.putString(string, "memory");
                } else if (access$getImagePipeline.isInDiskCacheSync(parse)) {
                    createMap.putString(string, "disk");
                }
            }
        }
        this.$promise.resolve(createMap);
    }
}
