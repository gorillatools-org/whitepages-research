package com.facebook.react.modules.image;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.SparseArray;
import com.facebook.common.executors.CallerThreadExecutor;
import com.facebook.datasource.DataSource;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.fbreact.specs.NativeImageLoaderAndroidSpec;
import com.facebook.imagepipeline.core.ImagePipeline;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.facebook.react.bridge.BaseJavaModule;
import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.modules.fresco.ImageCacheControl;
import com.facebook.react.modules.fresco.ReactNetworkImageRequest;
import com.facebook.react.views.image.ReactCallerContextFactory;
import com.facebook.react.views.imagehelper.ImageSource;
import kotlin.Unit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@ReactModule(name = "ImageLoader")
public final class ImageLoaderModule extends NativeImageLoaderAndroidSpec implements LifecycleEventListener {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final String ERROR_GET_SIZE_FAILURE = "E_GET_SIZE_FAILURE";
    private static final String ERROR_INVALID_URI = "E_INVALID_URI";
    private static final String ERROR_PREFETCH_FAILURE = "E_PREFETCH_FAILURE";
    public static final String NAME = "ImageLoader";
    private ImagePipeline _imagePipeline;
    private final Object callerContext;
    private ReactCallerContextFactory callerContextFactory;
    private final Object enqueuedRequestMonitor;
    private final SparseArray<DataSource> enqueuedRequests;

    public void onHostPause() {
    }

    public void onHostResume() {
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0004, code lost:
        r0 = r0.getOrCreateCallerContext("", "");
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final java.lang.Object getCallerContext() {
        /*
            r2 = this;
            com.facebook.react.views.image.ReactCallerContextFactory r0 = r2.callerContextFactory
            if (r0 == 0) goto L_0x000c
            java.lang.String r1 = ""
            java.lang.Object r0 = r0.getOrCreateCallerContext(r1, r1)
            if (r0 != 0) goto L_0x000e
        L_0x000c:
            java.lang.Object r0 = r2.callerContext
        L_0x000e:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.modules.image.ImageLoaderModule.getCallerContext():java.lang.Object");
    }

    /* access modifiers changed from: private */
    public final ImagePipeline getImagePipeline() {
        ImagePipeline imagePipeline = this._imagePipeline;
        if (imagePipeline != null) {
            return imagePipeline;
        }
        ImagePipeline imagePipeline2 = Fresco.getImagePipeline();
        Intrinsics.checkNotNullExpressionValue(imagePipeline2, "getImagePipeline(...)");
        return imagePipeline2;
    }

    private final void setImagePipeline(ImagePipeline imagePipeline) {
        this._imagePipeline = imagePipeline;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ImageLoaderModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        Intrinsics.checkNotNullParameter(reactApplicationContext, "reactContext");
        this.enqueuedRequestMonitor = new Object();
        this.enqueuedRequests = new SparseArray<>();
        this.callerContext = this;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ImageLoaderModule(ReactApplicationContext reactApplicationContext, Object obj) {
        super(reactApplicationContext);
        Intrinsics.checkNotNullParameter(reactApplicationContext, "reactContext");
        this.enqueuedRequestMonitor = new Object();
        this.enqueuedRequests = new SparseArray<>();
        this.callerContext = obj;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ImageLoaderModule(ReactApplicationContext reactApplicationContext, ImagePipeline imagePipeline, ReactCallerContextFactory reactCallerContextFactory) {
        super(reactApplicationContext);
        Intrinsics.checkNotNullParameter(reactApplicationContext, "reactContext");
        Intrinsics.checkNotNullParameter(imagePipeline, "imagePipeline");
        Intrinsics.checkNotNullParameter(reactCallerContextFactory, "callerContextFactory");
        this.enqueuedRequestMonitor = new Object();
        this.enqueuedRequests = new SparseArray<>();
        this.callerContextFactory = reactCallerContextFactory;
        setImagePipeline(imagePipeline);
        this.callerContext = null;
    }

    @ReactMethod
    public void getSize(String str, Promise promise) {
        Intrinsics.checkNotNullParameter(promise, BaseJavaModule.METHOD_TYPE_PROMISE);
        if (str == null || str.length() == 0) {
            promise.reject(ERROR_INVALID_URI, "Cannot get the size of an image for an empty URI");
            return;
        }
        ReactApplicationContext reactApplicationContext = getReactApplicationContext();
        Intrinsics.checkNotNullExpressionValue(reactApplicationContext, "getReactApplicationContext(...)");
        ImageRequest build = ImageRequestBuilder.newBuilderWithSource(new ImageSource(reactApplicationContext, str, 0.0d, 0.0d, (ImageCacheControl) null, 28, (DefaultConstructorMarker) null).getUri()).build();
        Intrinsics.checkNotNullExpressionValue(build, "build(...)");
        getImagePipeline().fetchDecodedImage(build, getCallerContext()).subscribe(new ImageLoaderModule$getSize$dataSubscriber$1(promise), CallerThreadExecutor.getInstance());
    }

    @ReactMethod
    public void getSizeWithHeaders(String str, ReadableMap readableMap, Promise promise) {
        Intrinsics.checkNotNullParameter(promise, BaseJavaModule.METHOD_TYPE_PROMISE);
        if (str == null || str.length() == 0) {
            promise.reject(ERROR_INVALID_URI, "Cannot get the size of an image for an empty URI");
            return;
        }
        ReactApplicationContext reactApplicationContext = getReactApplicationContext();
        Intrinsics.checkNotNullExpressionValue(reactApplicationContext, "getReactApplicationContext(...)");
        ImageRequestBuilder newBuilderWithSource = ImageRequestBuilder.newBuilderWithSource(new ImageSource(reactApplicationContext, str, 0.0d, 0.0d, (ImageCacheControl) null, 28, (DefaultConstructorMarker) null).getUri());
        Intrinsics.checkNotNullExpressionValue(newBuilderWithSource, "newBuilderWithSource(...)");
        getImagePipeline().fetchDecodedImage(ReactNetworkImageRequest.Companion.fromBuilderWithHeaders$default(ReactNetworkImageRequest.Companion, newBuilderWithSource, readableMap, (ImageCacheControl) null, 4, (Object) null), getCallerContext()).subscribe(new ImageLoaderModule$getSizeWithHeaders$dataSubscriber$1(promise), CallerThreadExecutor.getInstance());
    }

    public void prefetchImage(String str, double d, Promise promise) {
        Intrinsics.checkNotNullParameter(promise, BaseJavaModule.METHOD_TYPE_PROMISE);
        int i = (int) d;
        if (str == null || str.length() == 0) {
            promise.reject(ERROR_INVALID_URI, "Cannot prefetch an image for an empty URI");
            return;
        }
        ImageRequest build = ImageRequestBuilder.newBuilderWithSource(Uri.parse(str)).build();
        Intrinsics.checkNotNullExpressionValue(build, "build(...)");
        DataSource prefetchToDiskCache = getImagePipeline().prefetchToDiskCache(build, getCallerContext());
        ImageLoaderModule$prefetchImage$prefetchSubscriber$1 imageLoaderModule$prefetchImage$prefetchSubscriber$1 = new ImageLoaderModule$prefetchImage$prefetchSubscriber$1(this, i, promise);
        registerRequest(i, prefetchToDiskCache);
        prefetchToDiskCache.subscribe(imageLoaderModule$prefetchImage$prefetchSubscriber$1, CallerThreadExecutor.getInstance());
    }

    public void abortRequest(double d) {
        DataSource removeRequest = removeRequest((int) d);
        if (removeRequest != null) {
            removeRequest.close();
        }
    }

    @ReactMethod
    public void queryCache(ReadableArray readableArray, Promise promise) {
        Intrinsics.checkNotNullParameter(readableArray, "uris");
        Intrinsics.checkNotNullParameter(promise, BaseJavaModule.METHOD_TYPE_PROMISE);
        new ImageLoaderModule$queryCache$1(this, readableArray, promise, getReactApplicationContext()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    private final void registerRequest(int i, DataSource dataSource) {
        synchronized (this.enqueuedRequestMonitor) {
            this.enqueuedRequests.put(i, dataSource);
            Unit unit = Unit.INSTANCE;
        }
    }

    /* access modifiers changed from: private */
    public final DataSource removeRequest(int i) {
        DataSource dataSource;
        synchronized (this.enqueuedRequestMonitor) {
            dataSource = this.enqueuedRequests.get(i);
            this.enqueuedRequests.remove(i);
        }
        return dataSource;
    }

    public void onHostDestroy() {
        synchronized (this.enqueuedRequestMonitor) {
            try {
                int size = this.enqueuedRequests.size();
                for (int i = 0; i < size; i++) {
                    DataSource valueAt = this.enqueuedRequests.valueAt(i);
                    Intrinsics.checkNotNullExpressionValue(valueAt, "valueAt(...)");
                    valueAt.close();
                }
                this.enqueuedRequests.clear();
                Unit unit = Unit.INSTANCE;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
