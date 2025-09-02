package com.salesforce.marketingcloud.storage;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import com.salesforce.marketingcloud.g;
import java.io.UnsupportedEncodingException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.security.GeneralSecurityException;
import java.util.Locale;

@SuppressLint({"UnknownNullness"})
public interface c {
    public static final String a = "mcsdk_custprefs_%s";
    public static final String b = "et_attributes_cache";
    public static final String c = "et_tags_cache";
    public static final String d = "et_subscriber_cache";
    public static final String e = "gcm_reg_id_key";
    public static final String f = "et_session_id_cache";
    public static final String g = "et_user_id_cache";
    public static final String h = "mc_last_sent_registration";
    public static final String i = "sender_id";
    public static final String j = "subscriber_jwt";
    public static final String k = "predictive_intelligence_identifier";

    public static class a implements c {
        private final SharedPreferences l;
        private final com.salesforce.marketingcloud.util.c m;

        a(Context context, com.salesforce.marketingcloud.util.c cVar, String str) {
            this.l = context.getSharedPreferences(b(str), 0);
            this.m = cVar;
            b();
        }

        private void b() {
            if (this.l.contains("gcm_sender_id")) {
                this.l.edit().remove("gcm_sender_id").apply();
            }
        }

        private String c(String str, String str2) {
            String str3 = null;
            String string = this.l.getString(str, (String) null);
            if (string != null) {
                try {
                    str3 = this.m.b(string);
                } catch (Exception e) {
                    g.e(e.c, e, "Failed to encrypt %s", str);
                }
            }
            return str3 == null ? str2 : str3;
        }

        private void d(String str, String str2) throws GeneralSecurityException, UnsupportedEncodingException {
            this.l.edit().putString(str, this.m.a(str2)).apply();
        }

        public final void a() {
            this.l.edit().clear().apply();
        }

        public final void a(String str, String str2) {
            try {
                d(str, str2);
            } catch (UnsupportedEncodingException | GeneralSecurityException e) {
                g.e(e.c, String.format(Locale.ENGLISH, "Value for key %s not stored.", new Object[]{str}), e);
            }
        }

        public final String b(String str, String str2) {
            return c(str, str2);
        }

        static String b(String str) {
            return String.format(Locale.ENGLISH, c.a, new Object[]{str});
        }

        public final void a(String str) {
            this.l.edit().remove(str).apply();
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface b {
    }

    void a();

    void a(String str);

    void a(String str, String str2);

    String b(String str, String str2);
}
