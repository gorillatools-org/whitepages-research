package com.facebook.common.util;

import android.content.ContentResolver;
import android.content.res.AssetFileDescriptor;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import com.facebook.common.internal.Preconditions;
import com.facebook.infer.annotation.Assertions;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.messaging.Constants;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.net.URL;

public abstract class UriUtil {
    private static final Uri LOCAL_CONTACT_IMAGE_URI = Uri.withAppendedPath((Uri) Assertions.assumeNotNull(ContactsContract.AUTHORITY_URI), "display_photo");

    public static URL uriToUrl(Uri uri) {
        if (uri == null) {
            return null;
        }
        try {
            return new URL(uri.toString());
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean isNetworkUri(Uri uri) {
        String schemeOrNull = getSchemeOrNull(uri);
        return "https".equals(schemeOrNull) || "http".equals(schemeOrNull);
    }

    public static boolean isLocalFileUri(Uri uri) {
        return "file".equals(getSchemeOrNull(uri));
    }

    public static boolean isLocalContentUri(Uri uri) {
        return FirebaseAnalytics.Param.CONTENT.equals(getSchemeOrNull(uri));
    }

    public static boolean isLocalContactUri(Uri uri) {
        if (uri.getPath() != null && isLocalContentUri(uri) && "com.android.contacts".equals(uri.getAuthority()) && !uri.getPath().startsWith((String) Assertions.assumeNotNull(LOCAL_CONTACT_IMAGE_URI.getPath()))) {
            return true;
        }
        return false;
    }

    public static boolean isLocalCameraUri(Uri uri) {
        String uri2 = uri.toString();
        return uri2.startsWith(MediaStore.Images.Media.EXTERNAL_CONTENT_URI.toString()) || uri2.startsWith(MediaStore.Images.Media.INTERNAL_CONTENT_URI.toString());
    }

    public static boolean isLocalAssetUri(Uri uri) {
        return "asset".equals(getSchemeOrNull(uri));
    }

    public static boolean isLocalResourceUri(Uri uri) {
        return "res".equals(getSchemeOrNull(uri));
    }

    public static boolean isQualifiedResourceUri(Uri uri) {
        return "android.resource".equals(getSchemeOrNull(uri));
    }

    public static boolean isDataUri(Uri uri) {
        return Constants.ScionAnalytics.MessageType.DATA_MESSAGE.equals(getSchemeOrNull(uri));
    }

    public static String getSchemeOrNull(Uri uri) {
        if (uri == null) {
            return null;
        }
        return uri.getScheme();
    }

    public static String getRealPathFromUri(ContentResolver contentResolver, Uri uri) {
        String[] strArr;
        String str;
        Uri uri2;
        int columnIndexOrThrow;
        String type = contentResolver.getType(uri);
        String str2 = null;
        if (isLocalContentUri(uri)) {
            boolean z = type != null && type.startsWith("video/");
            if ("com.android.providers.media.documents".equals(uri.getAuthority())) {
                String documentId = DocumentsContract.getDocumentId(uri);
                Preconditions.checkNotNull(documentId);
                String[] strArr2 = {documentId.split(":")[1]};
                uri2 = (Uri) Preconditions.checkNotNull(getExternalContentUri(z));
                str = getMediaIdString(z) + "=?";
                strArr = strArr2;
            } else {
                uri2 = uri;
                str = null;
                strArr = null;
            }
            Cursor query = contentResolver.query(uri2, new String[]{getDataPathString(z)}, str, strArr, (String) null);
            if (query != null) {
                try {
                    if (query.moveToFirst() && (columnIndexOrThrow = query.getColumnIndexOrThrow(getDataPathString(z))) != -1) {
                        str2 = query.getString(columnIndexOrThrow);
                    }
                } catch (Throwable th) {
                    query.close();
                    throw th;
                }
            }
            if (query == null) {
                return str2;
            }
            query.close();
            return str2;
        } else if (isLocalFileUri(uri)) {
            return uri.getPath();
        } else {
            return null;
        }
    }

    private static Uri getExternalContentUri(boolean z) {
        if (z) {
            return MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
        }
        return MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
    }

    private static String getMediaIdString(boolean z) {
        return "_id";
    }

    private static String getDataPathString(boolean z) {
        return "_data";
    }

    public static AssetFileDescriptor getAssetFileDescriptor(ContentResolver contentResolver, Uri uri) {
        if (isLocalContentUri(uri)) {
            try {
                return contentResolver.openAssetFileDescriptor(uri, "r");
            } catch (FileNotFoundException unused) {
            }
        }
        return null;
    }

    public static Uri getUriForFile(File file) {
        return Uri.fromFile(file);
    }
}
