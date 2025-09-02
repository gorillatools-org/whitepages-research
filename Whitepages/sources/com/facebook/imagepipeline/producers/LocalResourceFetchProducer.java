package com.facebook.imagepipeline.producers;

import android.content.res.AssetFileDescriptor;
import android.content.res.Resources;
import com.facebook.common.memory.PooledByteBufferFactory;
import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.request.ImageRequest;
import java.io.IOException;
import java.util.concurrent.Executor;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class LocalResourceFetchProducer extends LocalFetchProducer {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private final Resources resources;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public LocalResourceFetchProducer(Executor executor, PooledByteBufferFactory pooledByteBufferFactory, Resources resources2) {
        super(executor, pooledByteBufferFactory);
        Intrinsics.checkNotNullParameter(executor, "executor");
        Intrinsics.checkNotNullParameter(pooledByteBufferFactory, "pooledByteBufferFactory");
        Intrinsics.checkNotNullParameter(resources2, "resources");
        this.resources = resources2;
    }

    /* access modifiers changed from: protected */
    public EncodedImage getEncodedImage(ImageRequest imageRequest) {
        Intrinsics.checkNotNullParameter(imageRequest, "imageRequest");
        return getEncodedImage(this.resources.openRawResource(Companion.getResourceId(imageRequest)), getLength(imageRequest));
    }

    private final int getLength(ImageRequest imageRequest) {
        AssetFileDescriptor assetFileDescriptor = null;
        try {
            AssetFileDescriptor openRawResourceFd = this.resources.openRawResourceFd(Companion.getResourceId(imageRequest));
            int length = (int) openRawResourceFd.getLength();
            try {
                openRawResourceFd.close();
                return length;
            } catch (IOException unused) {
                return length;
            }
        } catch (Resources.NotFoundException unused2) {
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
        return "LocalResourceFetchProducer";
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* access modifiers changed from: private */
        public final int getResourceId(ImageRequest imageRequest) {
            String path = imageRequest.getSourceUri().getPath();
            if (path != null) {
                String substring = path.substring(1);
                Intrinsics.checkNotNullExpressionValue(substring, "substring(...)");
                return Integer.parseInt(substring);
            }
            throw new IllegalStateException("Required value was null.");
        }
    }
}
