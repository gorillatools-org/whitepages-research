package com.facebook.common.media;

import android.webkit.MimeTypeMap;
import com.facebook.common.internal.ImmutableMap;
import com.reactnativecommunity.clipboard.ClipboardModule;
import java.util.Map;

public abstract class MimeTypeMapWrapper {
    private static final Map sExtensionToMimeTypeMap = ImmutableMap.of("heif", ClipboardModule.MIMETYPE_HEIF, "heic", ClipboardModule.MIMETYPE_HEIC);
    private static final MimeTypeMap sMimeTypeMap = MimeTypeMap.getSingleton();
    private static final Map sMimeTypeToExtensionMap = ImmutableMap.of(ClipboardModule.MIMETYPE_HEIF, "heif", ClipboardModule.MIMETYPE_HEIC, "heic");

    public static String getMimeTypeFromExtension(String str) {
        String str2 = (String) sExtensionToMimeTypeMap.get(str);
        if (str2 != null) {
            return str2;
        }
        return sMimeTypeMap.getMimeTypeFromExtension(str);
    }
}
