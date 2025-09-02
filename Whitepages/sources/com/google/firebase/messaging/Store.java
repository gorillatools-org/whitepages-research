package com.google.firebase.messaging;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;
import androidx.core.content.ContextCompat;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.json.JSONException;
import org.json.JSONObject;

class Store {
    static final String NO_BACKUP_FILE = "com.google.android.gms.appid-no-backup";
    static final String PREFERENCES = "com.google.android.gms.appid";
    private static final String SCOPE_ALL = "*";
    private static final String STORE_KEY_TOKEN = "|T|";
    final SharedPreferences store;

    public Store(Context context) {
        this.store = context.getSharedPreferences(PREFERENCES, 0);
        checkForRestore(context, NO_BACKUP_FILE);
    }

    private void checkForRestore(Context context, String str) {
        File file = new File(ContextCompat.getNoBackupFilesDir(context), str);
        if (!file.exists()) {
            try {
                if (file.createNewFile() && !isEmpty()) {
                    Log.i(Constants.TAG, "App restored, clearing state");
                    deleteAll();
                }
            } catch (IOException e) {
                if (Log.isLoggable(Constants.TAG, 3)) {
                    Log.d(Constants.TAG, "Error creating file in no backup dir: " + e.getMessage());
                }
            }
        }
    }

    public synchronized boolean isEmpty() {
        return this.store.getAll().isEmpty();
    }

    private String createTokenKey(String str, String str2) {
        return str + STORE_KEY_TOKEN + str2 + "|" + SCOPE_ALL;
    }

    public synchronized void deleteAll() {
        this.store.edit().clear().commit();
    }

    public synchronized Token getToken(String str, String str2) {
        return Token.parse(this.store.getString(createTokenKey(str, str2), (String) null));
    }

    public synchronized void saveToken(String str, String str2, String str3, String str4) {
        String encode = Token.encode(str3, str4, System.currentTimeMillis());
        if (encode != null) {
            SharedPreferences.Editor edit = this.store.edit();
            edit.putString(createTokenKey(str, str2), encode);
            edit.commit();
        }
    }

    public synchronized void deleteToken(String str, String str2) {
        String createTokenKey = createTokenKey(str, str2);
        SharedPreferences.Editor edit = this.store.edit();
        edit.remove(createTokenKey);
        edit.commit();
    }

    static class Token {
        private static final String KEY_APP_VERSION = "appVersion";
        private static final String KEY_TIMESTAMP = "timestamp";
        private static final String KEY_TOKEN = "token";
        private static final long REFRESH_PERIOD_MILLIS = TimeUnit.DAYS.toMillis(7);
        final String appVersion;
        final long timestamp;
        final String token;

        private Token(String str, String str2, long j) {
            this.token = str;
            this.appVersion = str2;
            this.timestamp = j;
        }

        static Token parse(String str) {
            if (TextUtils.isEmpty(str)) {
                return null;
            }
            if (!str.startsWith("{")) {
                return new Token(str, (String) null, 0);
            }
            try {
                JSONObject jSONObject = new JSONObject(str);
                return new Token(jSONObject.getString(KEY_TOKEN), jSONObject.getString("appVersion"), jSONObject.getLong("timestamp"));
            } catch (JSONException e) {
                Log.w(Constants.TAG, "Failed to parse token: " + e);
                return null;
            }
        }

        static String encode(String str, String str2, long j) {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put(KEY_TOKEN, str);
                jSONObject.put("appVersion", str2);
                jSONObject.put("timestamp", j);
                return jSONObject.toString();
            } catch (JSONException e) {
                Log.w(Constants.TAG, "Failed to encode token: " + e);
                return null;
            }
        }

        /* access modifiers changed from: package-private */
        public boolean needsRefresh(String str) {
            return System.currentTimeMillis() > this.timestamp + REFRESH_PERIOD_MILLIS || !str.equals(this.appVersion);
        }
    }
}
