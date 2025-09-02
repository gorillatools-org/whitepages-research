package com.salesforce.marketingcloud.sfmcsdk.components.http;

import kotlin.Pair;

public abstract class Authenticator {
    public abstract void deleteCachedToken();

    public abstract Pair getCachedTokenHeader();

    public abstract Pair refreshAuthTokenHeader();

    public static /* synthetic */ Pair getAuthTokenHeader$sfmcsdk_release$default(Authenticator authenticator, boolean z, int i, Object obj) {
        if (obj == null) {
            if ((i & 1) != 0) {
                z = false;
            }
            return authenticator.getAuthTokenHeader$sfmcsdk_release(z);
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getAuthTokenHeader");
    }

    public final synchronized Pair getAuthTokenHeader$sfmcsdk_release(boolean z) {
        Pair pair;
        if (z) {
            try {
                pair = refreshAuthTokenHeader();
            } catch (Throwable th) {
                while (true) {
                    throw th;
                }
            }
        } else {
            pair = getCachedTokenHeader();
            if (pair == null) {
                pair = refreshAuthTokenHeader();
            }
        }
        return pair;
    }
}
