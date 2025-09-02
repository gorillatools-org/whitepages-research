package com.facebook.react.views.imagehelper;

import android.content.Context;
import android.net.Uri;
import com.facebook.react.modules.fresco.ImageCacheControl;
import java.util.Objects;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public class ImageSource {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final String TRANSPARENT_BITMAP_URI = "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAEAAAABCAQAAAC1HAwCAAAAC0lEQVR42mNkYAAAAAYAAjCB0C8AAAAASUVORK5CYII=";
    private boolean _isResource;
    private final ImageCacheControl cacheControl;
    private final double size;
    private final String source;
    private final Uri uri;

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public ImageSource(Context context, String str) {
        this(context, str, 0.0d, 0.0d, (ImageCacheControl) null, 28, (DefaultConstructorMarker) null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public ImageSource(Context context, String str, double d) {
        this(context, str, d, 0.0d, (ImageCacheControl) null, 24, (DefaultConstructorMarker) null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public ImageSource(Context context, String str, double d, double d2) {
        this(context, str, d, d2, (ImageCacheControl) null, 16, (DefaultConstructorMarker) null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    public static final ImageSource getTransparentBitmapImageSource(Context context) {
        return Companion.getTransparentBitmapImageSource(context);
    }

    public ImageSource(Context context, String str, double d, double d2, ImageCacheControl imageCacheControl) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(imageCacheControl, "cacheControl");
        this.source = str;
        this.cacheControl = imageCacheControl;
        this.uri = computeUri(context);
        this.size = d * d2;
    }

    public final String getSource() {
        return this.source;
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public /* synthetic */ ImageSource(android.content.Context r12, java.lang.String r13, double r14, double r16, com.facebook.react.modules.fresco.ImageCacheControl r18, int r19, kotlin.jvm.internal.DefaultConstructorMarker r20) {
        /*
            r11 = this;
            r0 = r19 & 4
            r1 = 0
            if (r0 == 0) goto L_0x0008
            r6 = r1
            goto L_0x0009
        L_0x0008:
            r6 = r14
        L_0x0009:
            r0 = r19 & 8
            if (r0 == 0) goto L_0x000f
            r8 = r1
            goto L_0x0011
        L_0x000f:
            r8 = r16
        L_0x0011:
            r0 = r19 & 16
            if (r0 == 0) goto L_0x0019
            com.facebook.react.modules.fresco.ImageCacheControl r0 = com.facebook.react.modules.fresco.ImageCacheControl.DEFAULT
            r10 = r0
            goto L_0x001b
        L_0x0019:
            r10 = r18
        L_0x001b:
            r3 = r11
            r4 = r12
            r5 = r13
            r3.<init>(r4, r5, r6, r8, r10)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.views.imagehelper.ImageSource.<init>(android.content.Context, java.lang.String, double, double, com.facebook.react.modules.fresco.ImageCacheControl, int, kotlin.jvm.internal.DefaultConstructorMarker):void");
    }

    public final ImageCacheControl getCacheControl() {
        return this.cacheControl;
    }

    public Uri getUri() {
        return this.uri;
    }

    public final double getSize() {
        return this.size;
    }

    public boolean isResource() {
        return this._isResource;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !Intrinsics.areEqual((Object) getClass(), (Object) obj.getClass())) {
            return false;
        }
        ImageSource imageSource = (ImageSource) obj;
        if (Double.compare(imageSource.size, this.size) == 0 && isResource() == imageSource.isResource() && Intrinsics.areEqual((Object) getUri(), (Object) imageSource.getUri()) && Intrinsics.areEqual((Object) this.source, (Object) imageSource.source) && this.cacheControl == imageSource.cacheControl) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{getUri(), this.source, Double.valueOf(this.size), Boolean.valueOf(isResource()), this.cacheControl});
    }

    private final Uri computeUri(Context context) {
        try {
            Uri parse = Uri.parse(this.source);
            if (parse.getScheme() == null) {
                return computeLocalUri(context);
            }
            return parse;
        } catch (NullPointerException unused) {
            return computeLocalUri(context);
        }
    }

    private final Uri computeLocalUri(Context context) {
        this._isResource = true;
        return ResourceDrawableIdHelper.Companion.getInstance().getResourceDrawableUri(context, this.source);
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final ImageSource getTransparentBitmapImageSource(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            return new ImageSource(context, ImageSource.TRANSPARENT_BITMAP_URI, 0.0d, 0.0d, ImageCacheControl.DEFAULT, 12, (DefaultConstructorMarker) null);
        }
    }
}
