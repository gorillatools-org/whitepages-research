package com.facebook.share.internal;

import android.net.Uri;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable;
import com.facebook.AccessToken;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.HttpMethod;
import com.facebook.internal.Utility;
import com.reactnativecommunity.clipboard.ClipboardModule;
import java.io.File;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class ShareInternalUtility {
    public static final ShareInternalUtility INSTANCE = new ShareInternalUtility();

    private ShareInternalUtility() {
    }

    public static final GraphRequest newUploadStagingResourceWithImageRequest(AccessToken accessToken, File file, GraphRequest.Callback callback) {
        GraphRequest.ParcelableResourceWithMimeType parcelableResourceWithMimeType = new GraphRequest.ParcelableResourceWithMimeType((Parcelable) ParcelFileDescriptor.open(file, 268435456), ClipboardModule.MIMETYPE_PNG);
        Bundle bundle = new Bundle(1);
        bundle.putParcelable("file", parcelableResourceWithMimeType);
        return new GraphRequest(accessToken, "me/staging_resources", bundle, HttpMethod.POST, callback, (String) null, 32, (DefaultConstructorMarker) null);
    }

    public static final GraphRequest newUploadStagingResourceWithImageRequest(AccessToken accessToken, Uri uri, GraphRequest.Callback callback) {
        Intrinsics.checkNotNullParameter(uri, "imageUri");
        String path = uri.getPath();
        if (Utility.isFileUri(uri) && path != null) {
            return newUploadStagingResourceWithImageRequest(accessToken, new File(path), callback);
        }
        if (Utility.isContentUri(uri)) {
            GraphRequest.ParcelableResourceWithMimeType parcelableResourceWithMimeType = new GraphRequest.ParcelableResourceWithMimeType((Parcelable) uri, ClipboardModule.MIMETYPE_PNG);
            Bundle bundle = new Bundle(1);
            bundle.putParcelable("file", parcelableResourceWithMimeType);
            return new GraphRequest(accessToken, "me/staging_resources", bundle, HttpMethod.POST, callback, (String) null, 32, (DefaultConstructorMarker) null);
        }
        throw new FacebookException("The image Uri must be either a file:// or content:// Uri");
    }
}
