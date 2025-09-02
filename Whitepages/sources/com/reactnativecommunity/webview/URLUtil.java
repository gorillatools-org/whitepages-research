package com.reactnativecommunity.webview;

import android.net.Uri;
import android.webkit.MimeTypeMap;
import com.google.firebase.sessions.settings.RemoteSettings;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class URLUtil {
    private static final Pattern CONTENT_DISPOSITION_PATTERN = Pattern.compile("attachment(?:;\\s*filename\\s*=\\s*(\"?)([^\"]*)\\1)?(?:;\\s*filename\\s*\\*\\s*=\\s*([^']*)'[^']*'([^']*))?\\s*$", 2);

    public static final String guessFileName(String str, String str2, String str3) {
        String str4;
        String decode;
        int lastIndexOf;
        int lastIndexOf2;
        String str5 = null;
        if (str2 != null) {
            str4 = parseContentDisposition(str2);
            if (str4 != null && (lastIndexOf2 = str4.lastIndexOf(47) + 1) > 0) {
                str4 = str4.substring(lastIndexOf2);
            }
        } else {
            str4 = null;
        }
        if (str4 == null && (decode = Uri.decode(str)) != null) {
            int indexOf = decode.indexOf(63);
            if (indexOf > 0) {
                decode = decode.substring(0, indexOf);
            }
            if (!decode.endsWith(RemoteSettings.FORWARD_SLASH_STRING) && (lastIndexOf = decode.lastIndexOf(47) + 1) > 0) {
                str4 = decode.substring(lastIndexOf);
            }
        }
        if (str4 == null) {
            str4 = "downloadfile";
        }
        int indexOf2 = str4.indexOf(46);
        if (indexOf2 < 0) {
            if (!(str3 == null || (str5 = MimeTypeMap.getSingleton().getExtensionFromMimeType(str3)) == null)) {
                str5 = "." + str5;
            }
            if (str5 == null) {
                if (str3 == null || !str3.toLowerCase(Locale.ROOT).startsWith("text/")) {
                    str5 = ".bin";
                } else if (str3.equalsIgnoreCase("text/html")) {
                    str5 = ".html";
                } else {
                    str5 = ".txt";
                }
            }
        } else {
            if (str3 != null) {
                String mimeTypeFromExtension = MimeTypeMap.getSingleton().getMimeTypeFromExtension(str4.substring(str4.lastIndexOf(46) + 1));
                if (!(mimeTypeFromExtension == null || mimeTypeFromExtension.equalsIgnoreCase(str3) || (str5 = MimeTypeMap.getSingleton().getExtensionFromMimeType(str3)) == null)) {
                    str5 = "." + str5;
                }
            }
            if (str5 == null) {
                str5 = str4.substring(indexOf2);
            }
            str4 = str4.substring(0, indexOf2);
        }
        return str4 + str5;
    }

    static String parseContentDisposition(String str) {
        try {
            Matcher matcher = CONTENT_DISPOSITION_PATTERN.matcher(str);
            if (!matcher.find()) {
                return null;
            }
            if (!(matcher.group(3) == null || matcher.group(4) == null)) {
                try {
                    return URLDecoder.decode(matcher.group(4), matcher.group(3).isEmpty() ? "UTF-8" : matcher.group(3));
                } catch (UnsupportedEncodingException unused) {
                }
            }
            return matcher.group(2);
        } catch (IllegalStateException unused2) {
            return null;
        }
    }
}
