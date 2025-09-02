package com.google.android.gms.fido.fido2.api.common;

import android.net.Uri;

public abstract class BrowserRequestOptions extends RequestOptions {
    public abstract byte[] getClientDataHash();

    public abstract Uri getOrigin();
}
