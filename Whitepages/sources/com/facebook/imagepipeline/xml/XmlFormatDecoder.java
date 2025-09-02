package com.facebook.imagepipeline.xml;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import androidx.core.content.res.ResourcesCompat;
import com.facebook.common.logging.FLog;
import com.facebook.common.util.UriUtil;
import com.facebook.imagepipeline.common.ImageDecodeOptions;
import com.facebook.imagepipeline.decoder.ImageDecoder;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.image.DefaultCloseableXml;
import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.image.QualityInfo;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

public final class XmlFormatDecoder implements ImageDecoder {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private final Map resourceIdCache = new ConcurrentHashMap();
    private final Resources resources;

    public XmlFormatDecoder(Resources resources2) {
        Intrinsics.checkNotNullParameter(resources2, "resources");
        this.resources = resources2;
    }

    public CloseableImage decode(EncodedImage encodedImage, int i, QualityInfo qualityInfo, ImageDecodeOptions imageDecodeOptions) {
        Intrinsics.checkNotNullParameter(encodedImage, "encodedImage");
        Intrinsics.checkNotNullParameter(qualityInfo, "qualityInfo");
        Intrinsics.checkNotNullParameter(imageDecodeOptions, "options");
        try {
            String source = encodedImage.getSource();
            if (source != null) {
                Drawable drawable = ResourcesCompat.getDrawable(this.resources, getXmlResourceId(source), (Resources.Theme) null);
                if (drawable != null) {
                    return new DefaultCloseableXml(drawable);
                }
                return null;
            }
            throw new IllegalStateException("No source in encoded image");
        } catch (Throwable th) {
            FLog.e("XmlFormatDecoder", "Cannot decode xml", th);
            return null;
        }
    }

    private final int getXmlResourceId(String str) {
        Map map = this.resourceIdCache;
        Object obj = map.get(str);
        if (obj == null) {
            Uri parse = Uri.parse(str);
            Intrinsics.checkNotNullExpressionValue(parse, "parse(...)");
            obj = Integer.valueOf(parseImageSourceResourceId(parse));
            map.put(str, obj);
        }
        return ((Number) obj).intValue();
    }

    private final int parseImageSourceResourceId(Uri uri) {
        Integer intOrNull;
        if (UriUtil.isLocalResourceUri(uri) || UriUtil.isQualifiedResourceUri(uri)) {
            List<String> pathSegments = uri.getPathSegments();
            Intrinsics.checkNotNullExpressionValue(pathSegments, "getPathSegments(...)");
            String str = (String) CollectionsKt.lastOrNull(pathSegments);
            if (str != null && (intOrNull = StringsKt.toIntOrNull(str)) != null) {
                return intOrNull.intValue();
            }
            String path = uri.getPath();
            throw new IllegalStateException(("Unable to read resource ID from " + path).toString());
        }
        throw new IllegalStateException(("Unsupported uri " + uri).toString());
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
