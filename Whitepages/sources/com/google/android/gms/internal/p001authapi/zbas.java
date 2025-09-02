package com.google.android.gms.internal.p001authapi;

import android.util.Base64;
import java.security.SecureRandom;

/* renamed from: com.google.android.gms.internal.auth-api.zbas  reason: invalid package */
public final class zbas {
    private static final SecureRandom zba = new SecureRandom();

    public static String zba() {
        byte[] bArr = new byte[16];
        zba.nextBytes(bArr);
        return Base64.encodeToString(bArr, 11);
    }
}
