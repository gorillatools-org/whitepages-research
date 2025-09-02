package com.facebook.imagepipeline.producers;

import android.content.ContentResolver;
import android.content.res.AssetFileDescriptor;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.provider.ContactsContract;
import com.facebook.common.memory.PooledByteBufferFactory;
import com.facebook.common.util.UriUtil;
import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.request.ImageRequest;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.Executor;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

public final class LocalContentUriFetchProducer extends LocalFetchProducer {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final String[] PROJECTION = {"_id", "_data"};
    private final ContentResolver contentResolver;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public LocalContentUriFetchProducer(Executor executor, PooledByteBufferFactory pooledByteBufferFactory, ContentResolver contentResolver2) {
        super(executor, pooledByteBufferFactory);
        Intrinsics.checkNotNullParameter(executor, "executor");
        Intrinsics.checkNotNullParameter(pooledByteBufferFactory, "pooledByteBufferFactory");
        Intrinsics.checkNotNullParameter(contentResolver2, "contentResolver");
        this.contentResolver = contentResolver2;
    }

    /* access modifiers changed from: protected */
    public EncodedImage getEncodedImage(ImageRequest imageRequest) {
        EncodedImage cameraImage;
        InputStream inputStream;
        Intrinsics.checkNotNullParameter(imageRequest, "imageRequest");
        Uri sourceUri = imageRequest.getSourceUri();
        Intrinsics.checkNotNullExpressionValue(sourceUri, "getSourceUri(...)");
        if (UriUtil.isLocalContactUri(sourceUri)) {
            String uri = sourceUri.toString();
            Intrinsics.checkNotNullExpressionValue(uri, "toString(...)");
            if (StringsKt.endsWith$default(uri, "/photo", false, 2, (Object) null)) {
                inputStream = this.contentResolver.openInputStream(sourceUri);
            } else {
                String uri2 = sourceUri.toString();
                Intrinsics.checkNotNullExpressionValue(uri2, "toString(...)");
                if (StringsKt.endsWith$default(uri2, "/display_photo", false, 2, (Object) null)) {
                    try {
                        AssetFileDescriptor openAssetFileDescriptor = this.contentResolver.openAssetFileDescriptor(sourceUri, "r");
                        if (openAssetFileDescriptor != null) {
                            inputStream = openAssetFileDescriptor.createInputStream();
                        } else {
                            throw new IllegalStateException("Required value was null.");
                        }
                    } catch (IOException unused) {
                        throw new IOException("Contact photo does not exist: " + sourceUri);
                    }
                } else {
                    InputStream openContactPhotoInputStream = ContactsContract.Contacts.openContactPhotoInputStream(this.contentResolver, sourceUri);
                    if (openContactPhotoInputStream != null) {
                        inputStream = openContactPhotoInputStream;
                    } else {
                        throw new IOException("Contact photo does not exist: " + sourceUri);
                    }
                }
            }
            if (inputStream != null) {
                return getEncodedImage(inputStream, -1);
            }
            throw new IllegalStateException("Required value was null.");
        } else if (UriUtil.isLocalCameraUri(sourceUri) && (cameraImage = getCameraImage(sourceUri)) != null) {
            return cameraImage;
        } else {
            InputStream openInputStream = this.contentResolver.openInputStream(sourceUri);
            if (openInputStream != null) {
                return getEncodedImage(openInputStream, -1);
            }
            throw new IllegalStateException("Required value was null.");
        }
    }

    private final EncodedImage getCameraImage(Uri uri) {
        try {
            ParcelFileDescriptor openFileDescriptor = this.contentResolver.openFileDescriptor(uri, "r");
            if (openFileDescriptor != null) {
                EncodedImage encodedImage = getEncodedImage(new FileInputStream(openFileDescriptor.getFileDescriptor()), (int) openFileDescriptor.getStatSize());
                Intrinsics.checkNotNullExpressionValue(encodedImage, "getEncodedImage(...)");
                openFileDescriptor.close();
                return encodedImage;
            }
            throw new IllegalStateException("Required value was null.");
        } catch (FileNotFoundException unused) {
            return null;
        }
    }

    /* access modifiers changed from: protected */
    public String getProducerName() {
        return "LocalContentUriFetchProducer";
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
