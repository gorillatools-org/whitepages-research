package io.branch.referral;

import android.content.Context;
import android.text.TextUtils;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;

public abstract class AppStoreReferrer {
    private static String installID_ = "bnc_no_value";

    public static void processReferrerInfo(Context context, String str, long j, long j2, String str2, Boolean bool) {
        PrefHelper instance = PrefHelper.getInstance(context);
        if (!TextUtils.isEmpty(str2)) {
            instance.setAppStoreSource(str2);
            if (str2.equals(Defines$Jsonkey.Meta_Install_Referrer.getKey())) {
                instance.setIsMetaClickThrough(bool.booleanValue());
            }
        }
        if (j > 0) {
            instance.setLong("bnc_referrer_click_ts", j);
        }
        if (j2 > 0) {
            instance.setLong("bnc_install_begin_ts", j2);
        }
        if (str != null) {
            try {
                String decode = URLDecoder.decode(str, "UTF-8");
                HashMap hashMap = new HashMap();
                String[] split = decode.split("&");
                instance.setAppStoreReferrer(decode);
                for (String str3 : split) {
                    if (!TextUtils.isEmpty(str3)) {
                        String[] split2 = str3.split((str3.contains("=") || !str3.contains("-")) ? "=" : "-");
                        if (split2.length > 1) {
                            hashMap.put(URLDecoder.decode(split2[0], "UTF-8"), URLDecoder.decode(split2[1], "UTF-8"));
                        }
                    }
                }
                Defines$Jsonkey defines$Jsonkey = Defines$Jsonkey.LinkClickID;
                if (hashMap.containsKey(defines$Jsonkey.getKey())) {
                    String str4 = (String) hashMap.get(defines$Jsonkey.getKey());
                    installID_ = str4;
                    instance.setLinkClickIdentifier(str4);
                }
                Defines$Jsonkey defines$Jsonkey2 = Defines$Jsonkey.IsFullAppConv;
                if (hashMap.containsKey(defines$Jsonkey2.getKey())) {
                    Defines$Jsonkey defines$Jsonkey3 = Defines$Jsonkey.ReferringLink;
                    if (hashMap.containsKey(defines$Jsonkey3.getKey())) {
                        instance.setIsFullAppConversion(Boolean.parseBoolean((String) hashMap.get(defines$Jsonkey2.getKey())));
                        instance.setAppLink((String) hashMap.get(defines$Jsonkey3.getKey()));
                    }
                }
                Defines$Jsonkey defines$Jsonkey4 = Defines$Jsonkey.GoogleSearchInstallReferrer;
                if (hashMap.containsKey(defines$Jsonkey4.getKey())) {
                    instance.setGoogleSearchInstallIdentifier((String) hashMap.get(defines$Jsonkey4.getKey()));
                }
                if (hashMap.containsValue(Defines$Jsonkey.PlayAutoInstalls.getKey())) {
                    BranchPreinstall.setBranchPreInstallGoogleReferrer(context, hashMap);
                }
            } catch (UnsupportedEncodingException e) {
                BranchLogger.w("Caught UnsupportedEncodingException " + e.getMessage());
            } catch (IllegalArgumentException e2) {
                BranchLogger.w("Caught IllegalArgumentException " + e2.getMessage());
            }
        }
    }

    public static String getInstallationID() {
        return installID_;
    }
}
