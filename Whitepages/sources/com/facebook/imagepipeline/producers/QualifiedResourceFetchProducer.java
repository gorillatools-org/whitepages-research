package com.facebook.imagepipeline.producers;

import android.content.ContentResolver;
import com.facebook.common.memory.PooledByteBufferFactory;
import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.request.ImageRequest;
import java.io.InputStream;
import java.util.concurrent.Executor;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class QualifiedResourceFetchProducer extends LocalFetchProducer {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private final ContentResolver contentResolver;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public QualifiedResourceFetchProducer(Executor executor, PooledByteBufferFactory pooledByteBufferFactory, ContentResolver contentResolver2) {
        super(executor, pooledByteBufferFactory);
        Intrinsics.checkNotNullParameter(executor, "executor");
        Intrinsics.checkNotNullParameter(pooledByteBufferFactory, "pooledByteBufferFactory");
        Intrinsics.checkNotNullParameter(contentResolver2, "contentResolver");
        this.contentResolver = contentResolver2;
    }

    /* access modifiers changed from: protected */
    public EncodedImage getEncodedImage(ImageRequest imageRequest) {
        Intrinsics.checkNotNullParameter(imageRequest, "imageRequest");
        InputStream openInputStream = this.contentResolver.openInputStream(imageRequest.getSourceUri());
        if (openInputStream != null) {
            EncodedImage encodedImage = getEncodedImage(openInputStream, -1);
            Intrinsics.checkNotNullExpressionValue(encodedImage, "getEncodedImage(...)");
            return encodedImage;
        }
        throw new IllegalStateException("ContentResolver returned null InputStream");
    }

    /* access modifiers changed from: protected */
    public String getProducerName() {
        return "QualifiedResourceFetchProducer";
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
