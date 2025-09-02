package com.facebook.imagepipeline.producers;

import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import com.facebook.common.memory.PooledByteBufferFactory;
import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.request.ImageRequest;
import java.io.IOException;
import java.util.concurrent.Executor;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class LocalAssetFetchProducer extends LocalFetchProducer {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private final AssetManager assetManager;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public LocalAssetFetchProducer(Executor executor, PooledByteBufferFactory pooledByteBufferFactory, AssetManager assetManager2) {
        super(executor, pooledByteBufferFactory);
        Intrinsics.checkNotNullParameter(executor, "executor");
        Intrinsics.checkNotNullParameter(pooledByteBufferFactory, "pooledByteBufferFactory");
        Intrinsics.checkNotNullParameter(assetManager2, "assetManager");
        this.assetManager = assetManager2;
    }

    /* access modifiers changed from: protected */
    public EncodedImage getEncodedImage(ImageRequest imageRequest) {
        Intrinsics.checkNotNullParameter(imageRequest, "imageRequest");
        return getEncodedImage(this.assetManager.open(Companion.getAssetName(imageRequest), 2), getLength(imageRequest));
    }

    private final int getLength(ImageRequest imageRequest) {
        AssetFileDescriptor assetFileDescriptor = null;
        try {
            AssetFileDescriptor openFd = this.assetManager.openFd(Companion.getAssetName(imageRequest));
            int length = (int) openFd.getLength();
            try {
                openFd.close();
                return length;
            } catch (IOException unused) {
                return length;
            }
        } catch (IOException unused2) {
            if (assetFileDescriptor != null) {
                try {
                    assetFileDescriptor.close();
                } catch (IOException unused3) {
                }
            }
            return -1;
        } catch (Throwable th) {
            if (assetFileDescriptor != null) {
                try {
                    assetFileDescriptor.close();
                } catch (IOException unused4) {
                }
            }
            throw th;
        }
    }

    /* access modifiers changed from: protected */
    public String getProducerName() {
        return "LocalAssetFetchProducer";
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* access modifiers changed from: private */
        public final String getAssetName(ImageRequest imageRequest) {
            String path = imageRequest.getSourceUri().getPath();
            Intrinsics.checkNotNull(path);
            String substring = path.substring(1);
            Intrinsics.checkNotNullExpressionValue(substring, "substring(...)");
            return substring;
        }
    }
}
