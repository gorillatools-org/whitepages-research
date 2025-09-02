package com.facebook.common.media;

import com.salesforce.marketingcloud.config.a;
import java.util.Locale;
import java.util.Map;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

public final class MediaUtils {
    public static final Map ADDITIONAL_ALLOWED_MIME_TYPES = MapsKt.mapOf(TuplesKt.to("mkv", "video/x-matroska"), TuplesKt.to("glb", "model/gltf-binary"));
    public static final MediaUtils INSTANCE = new MediaUtils();

    private MediaUtils() {
    }

    public static final boolean isVideo(String str) {
        if (str != null) {
            return StringsKt.startsWith$default(str, "video/", false, 2, (Object) null);
        }
        return false;
    }

    public static final String extractMime(String str) {
        Intrinsics.checkNotNullParameter(str, a.j);
        String extractExtension = INSTANCE.extractExtension(str);
        if (extractExtension == null) {
            return null;
        }
        Locale locale = Locale.US;
        Intrinsics.checkNotNullExpressionValue(locale, "US");
        String lowerCase = extractExtension.toLowerCase(locale);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        if (lowerCase == null) {
            return null;
        }
        String mimeTypeFromExtension = MimeTypeMapWrapper.getMimeTypeFromExtension(lowerCase);
        return mimeTypeFromExtension == null ? (String) ADDITIONAL_ALLOWED_MIME_TYPES.get(lowerCase) : mimeTypeFromExtension;
    }

    private final String extractExtension(String str) {
        int lastIndexOf$default = StringsKt.lastIndexOf$default((CharSequence) str, '.', 0, false, 6, (Object) null);
        if (lastIndexOf$default < 0 || lastIndexOf$default == str.length() - 1) {
            return null;
        }
        String substring = str.substring(lastIndexOf$default + 1);
        Intrinsics.checkNotNullExpressionValue(substring, "substring(...)");
        return substring;
    }
}
