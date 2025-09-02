package com.facebook.react.modules.camera;

import android.net.Uri;
import android.util.Base64OutputStream;
import com.facebook.fbreact.specs.NativeImageStoreAndroidSpec;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.module.annotations.ReactModule;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.Executors;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@ReactModule(name = "ImageStoreManager")
public final class ImageStoreManager extends NativeImageStoreAndroidSpec {
    private static final int BUFFER_SIZE = 8192;
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final String NAME = "ImageStoreManager";

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ImageStoreManager(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        Intrinsics.checkNotNullParameter(reactApplicationContext, "reactContext");
    }

    public void getBase64ForTag(String str, Callback callback, Callback callback2) {
        Intrinsics.checkNotNullParameter(str, "uri");
        Intrinsics.checkNotNullParameter(callback, FirebaseAnalytics.Param.SUCCESS);
        Intrinsics.checkNotNullParameter(callback2, "error");
        Executors.newSingleThreadExecutor().execute(new ImageStoreManager$$ExternalSyntheticLambda0(this, str, callback, callback2));
    }

    /* access modifiers changed from: private */
    public static final void getBase64ForTag$lambda$0(ImageStoreManager imageStoreManager, String str, Callback callback, Callback callback2) {
        InputStream openInputStream;
        Companion companion;
        try {
            openInputStream = imageStoreManager.getReactApplicationContext().getContentResolver().openInputStream(Uri.parse(str));
            Intrinsics.checkNotNull(openInputStream, "null cannot be cast to non-null type java.io.InputStream");
            try {
                companion = Companion;
                callback.invoke(companion.convertInputStreamToBase64OutputStream$ReactAndroid_release(openInputStream));
            } catch (IOException e) {
                callback2.invoke(e.getMessage());
                companion = Companion;
            }
            companion.closeQuietly(openInputStream);
        } catch (FileNotFoundException e2) {
            callback2.invoke(e2.getMessage());
        } catch (Throwable th) {
            Companion.closeQuietly(openInputStream);
            throw th;
        }
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* access modifiers changed from: private */
        public final void closeQuietly(Closeable closeable) {
            try {
                closeable.close();
            } catch (IOException unused) {
            }
        }

        /* JADX INFO: finally extract failed */
        public final String convertInputStreamToBase64OutputStream$ReactAndroid_release(InputStream inputStream) throws IOException {
            Intrinsics.checkNotNullParameter(inputStream, "inputStream");
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            Base64OutputStream base64OutputStream = new Base64OutputStream(byteArrayOutputStream, 2);
            byte[] bArr = new byte[8192];
            while (true) {
                try {
                    int read = inputStream.read(bArr);
                    if (read > -1) {
                        base64OutputStream.write(bArr, 0, read);
                    } else {
                        closeQuietly(base64OutputStream);
                        String byteArrayOutputStream2 = byteArrayOutputStream.toString();
                        Intrinsics.checkNotNullExpressionValue(byteArrayOutputStream2, "toString(...)");
                        return byteArrayOutputStream2;
                    }
                } catch (Throwable th) {
                    closeQuietly(base64OutputStream);
                    throw th;
                }
            }
        }
    }
}
