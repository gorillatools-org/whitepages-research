package com.salesforce.marketingcloud.util;

import android.annotation.SuppressLint;
import android.content.Context;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.text.NumberFormat;

@SuppressLint({"UnknownNullness"})
public class i extends a {
    private static final String f = "ETPush";

    public i(Context context, String str, String str2, String str3) throws GeneralSecurityException, UnsupportedEncodingException {
        super(context, str, str2, str3, 10000);
    }

    /* access modifiers changed from: protected */
    public final String a(Context context, String str) throws GeneralSecurityException {
        int i;
        String string = context.getSharedPreferences(f, 0).getString("install_date_enc", (String) null);
        if (string != null) {
            try {
                i = NumberFormat.getInstance().parse(l.e(str + "29200FA5-DF79-4C3F-BC0F-E2FF3CE6199A")).intValue();
            } catch (Exception unused) {
                i = 200;
            }
            return l.e(string.substring(Integer.valueOf(String.valueOf(i).substring(0, 1)).intValue()));
        }
        throw new GeneralSecurityException("Unable to get old salt.");
    }
}
