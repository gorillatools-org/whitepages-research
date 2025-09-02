package com.microsoft.codepush.react;

import android.content.Context;
import android.content.SharedPreferences;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SettingsManager {
    private SharedPreferences mSettings;

    public SettingsManager(Context context) {
        this.mSettings = context.getSharedPreferences("CodePush", 0);
    }

    public JSONArray getFailedUpdates() {
        String string = this.mSettings.getString("CODE_PUSH_FAILED_UPDATES", (String) null);
        if (string == null) {
            return new JSONArray();
        }
        try {
            return new JSONArray(string);
        } catch (JSONException unused) {
            JSONArray jSONArray = new JSONArray();
            this.mSettings.edit().putString("CODE_PUSH_FAILED_UPDATES", jSONArray.toString()).commit();
            return jSONArray;
        }
    }

    public JSONObject getPendingUpdate() {
        String string = this.mSettings.getString("CODE_PUSH_PENDING_UPDATE", (String) null);
        if (string == null) {
            return null;
        }
        try {
            return new JSONObject(string);
        } catch (JSONException unused) {
            CodePushUtils.log("Unable to parse pending update metadata " + string + " stored in SharedPreferences");
            return null;
        }
    }

    public boolean isFailedHash(String str) {
        JSONArray failedUpdates = getFailedUpdates();
        if (str != null) {
            int i = 0;
            while (i < failedUpdates.length()) {
                try {
                    if (str.equals(failedUpdates.getJSONObject(i).getString("packageHash"))) {
                        return true;
                    }
                    i++;
                } catch (JSONException e) {
                    throw new CodePushUnknownException("Unable to read failedUpdates data stored in SharedPreferences.", e);
                }
            }
        }
        return false;
    }

    public boolean isPendingUpdate(String str) {
        JSONObject pendingUpdate = getPendingUpdate();
        if (pendingUpdate != null) {
            try {
                if (pendingUpdate.getBoolean("isLoading") || (str != null && !pendingUpdate.getString("hash").equals(str))) {
                    return false;
                }
                return true;
            } catch (JSONException e) {
                throw new CodePushUnknownException("Unable to read pending update metadata in isPendingUpdate.", e);
            }
        }
        return false;
    }

    public void removeFailedUpdates() {
        this.mSettings.edit().remove("CODE_PUSH_FAILED_UPDATES").commit();
    }

    public void removePendingUpdate() {
        this.mSettings.edit().remove("CODE_PUSH_PENDING_UPDATE").commit();
    }

    public void saveFailedUpdate(JSONObject jSONObject) {
        JSONArray jSONArray;
        try {
            if (!isFailedHash(jSONObject.getString("packageHash"))) {
                String string = this.mSettings.getString("CODE_PUSH_FAILED_UPDATES", (String) null);
                if (string == null) {
                    jSONArray = new JSONArray();
                } else {
                    try {
                        jSONArray = new JSONArray(string);
                    } catch (JSONException e) {
                        throw new CodePushMalformedDataException("Unable to parse failed updates information " + string + " stored in SharedPreferences", (Throwable) e);
                    }
                }
                jSONArray.put(jSONObject);
                this.mSettings.edit().putString("CODE_PUSH_FAILED_UPDATES", jSONArray.toString()).commit();
            }
        } catch (JSONException e2) {
            throw new CodePushUnknownException("Unable to read package hash from package.", e2);
        }
    }

    public JSONObject getLatestRollbackInfo() {
        String string = this.mSettings.getString("LATEST_ROLLBACK_INFO", (String) null);
        if (string == null) {
            return null;
        }
        try {
            return new JSONObject(string);
        } catch (JSONException unused) {
            CodePushUtils.log("Unable to parse latest rollback metadata " + string + " stored in SharedPreferences");
            return null;
        }
    }

    public void setLatestRollbackInfo(String str) {
        JSONObject latestRollbackInfo = getLatestRollbackInfo();
        int i = 0;
        if (latestRollbackInfo != null) {
            try {
                if (latestRollbackInfo.getString("packageHash").equals(str)) {
                    i = latestRollbackInfo.getInt("count");
                }
            } catch (JSONException unused) {
                CodePushUtils.log("Unable to parse latest rollback info.");
            }
        } else {
            latestRollbackInfo = new JSONObject();
        }
        try {
            latestRollbackInfo.put("packageHash", str);
            latestRollbackInfo.put("time", System.currentTimeMillis());
            latestRollbackInfo.put("count", i + 1);
            this.mSettings.edit().putString("LATEST_ROLLBACK_INFO", latestRollbackInfo.toString()).commit();
        } catch (JSONException e) {
            throw new CodePushUnknownException("Unable to save latest rollback info.", e);
        }
    }

    public void savePendingUpdate(String str, boolean z) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("hash", str);
            jSONObject.put("isLoading", z);
            this.mSettings.edit().putString("CODE_PUSH_PENDING_UPDATE", jSONObject.toString()).commit();
        } catch (JSONException e) {
            throw new CodePushUnknownException("Unable to save pending update.", e);
        }
    }
}
