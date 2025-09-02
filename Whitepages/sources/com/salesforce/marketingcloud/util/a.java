package com.salesforce.marketingcloud.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Base64;
import com.salesforce.marketingcloud.tozny.AesCbcWithIntegrity;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;

@SuppressLint({"UnknownNullness"})
public class a extends AesCbcWithIntegrity implements c {
    private static final String b = "F6389234-1024-481F-9173-37D9D7F5051F";
    private static final String c = "com.salesforce.marketingcloud.storagePrefs";
    private static final String d = "install_date_enc";
    private static final int e = 500;
    private final AesCbcWithIntegrity.SecretKeys a;

    public a(Context context, String str, String str2, String str3) throws GeneralSecurityException, UnsupportedEncodingException {
        this(context, str, str2, str3, 500);
    }

    public String a(String str) throws GeneralSecurityException, UnsupportedEncodingException {
        if (str == null) {
            return null;
        }
        return AesCbcWithIntegrity.encrypt(str, this.a).toString();
    }

    public String b(String str) throws GeneralSecurityException, UnsupportedEncodingException {
        if (str == null) {
            return null;
        }
        return AesCbcWithIntegrity.decryptString(new AesCbcWithIntegrity.CipherTextIvMac(str), this.a);
    }

    protected a(Context context, String str, String str2, String str3, int i) throws GeneralSecurityException, UnsupportedEncodingException {
        this.a = AesCbcWithIntegrity.generateKeyFromPassword(a(str, str2, str3), a(context, str), i);
        a();
    }

    private String a(String str, String str2, String str3) {
        return Base64.encodeToString(l.e(str + "--" + l.e(str2) + "__" + l.e(str3)).getBytes(l.b), 2);
    }

    /* access modifiers changed from: protected */
    public String a(Context context, String str) throws GeneralSecurityException {
        SharedPreferences sharedPreferences = context.getSharedPreferences(c, 0);
        String string = sharedPreferences.getString(d, (String) null);
        if (string != null) {
            return string;
        }
        String saltString = AesCbcWithIntegrity.saltString(AesCbcWithIntegrity.generateSalt());
        sharedPreferences.edit().putString(d, saltString).apply();
        return saltString;
    }

    private void a() throws GeneralSecurityException, UnsupportedEncodingException {
        if (!b.equals(b(a(b)))) {
            throw new GeneralSecurityException("Encryption/decryption test failed");
        }
    }
}
