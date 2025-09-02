package com.facebook.fresco.urimod;

import android.net.Uri;

public interface UriModifierInterface {
    Uri modifyPrefetchUri(Uri uri, Object obj);
}
