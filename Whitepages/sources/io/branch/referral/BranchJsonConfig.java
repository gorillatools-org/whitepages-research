package io.branch.referral;

import android.content.Context;
import android.util.Log;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import org.json.JSONException;
import org.json.JSONObject;

public class BranchJsonConfig {
    private static BranchJsonConfig instance;
    private JSONObject mConfiguration = null;

    public enum BranchJsonKey {
        branchKey,
        testKey,
        liveKey,
        useTestInstance,
        enableLogging,
        deferInitForPluginRuntime,
        apiUrl,
        fbAppId
    }

    private BranchJsonConfig(Context context) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(context.getAssets().open("branch.json")));
            try {
                StringBuilder sb = new StringBuilder();
                while (true) {
                    String readLine = bufferedReader.readLine();
                    if (readLine != null) {
                        sb.append(readLine);
                    } else {
                        this.mConfiguration = new JSONObject(sb.toString());
                        return;
                    }
                }
            } catch (IOException e) {
                Log.e("BranchJsonConfig", "Error loading branch.json: " + e.getMessage());
            } catch (JSONException e2) {
                Log.e("BranchJsonConfig", "Error parsing branch.json: " + e2.getMessage());
            }
        } catch (FileNotFoundException unused) {
        }
    }

    public static BranchJsonConfig getInstance(Context context) {
        if (instance == null) {
            instance = new BranchJsonConfig(context);
        }
        return instance;
    }

    public boolean isValid() {
        return this.mConfiguration != null;
    }

    public boolean isValid(BranchJsonKey branchJsonKey) {
        JSONObject jSONObject = this.mConfiguration;
        return jSONObject != null && jSONObject.has(branchJsonKey.toString());
    }

    public Boolean getEnableLogging() {
        BranchJsonKey branchJsonKey = BranchJsonKey.enableLogging;
        if (!isValid(branchJsonKey)) {
            return null;
        }
        try {
            return Boolean.valueOf(this.mConfiguration.getBoolean(branchJsonKey.toString()));
        } catch (JSONException e) {
            Log.e("BranchJsonConfig", "Error parsing branch.json: " + e.getMessage());
            return Boolean.FALSE;
        }
    }

    public Boolean getDeferInitForPluginRuntime() {
        BranchJsonKey branchJsonKey = BranchJsonKey.deferInitForPluginRuntime;
        if (!isValid(branchJsonKey)) {
            return null;
        }
        try {
            return Boolean.valueOf(this.mConfiguration.getBoolean(branchJsonKey.toString()));
        } catch (JSONException e) {
            Log.e("BranchJsonConfig", "Error parsing branch.json: " + e.getMessage());
            return Boolean.FALSE;
        }
    }

    public String getBranchKey() {
        BranchJsonKey branchJsonKey = BranchJsonKey.branchKey;
        if (!isValid(branchJsonKey) && (!isValid(BranchJsonKey.liveKey) || !isValid(BranchJsonKey.testKey) || !isValid(BranchJsonKey.useTestInstance))) {
            return null;
        }
        try {
            if (isValid(branchJsonKey)) {
                return this.mConfiguration.getString(branchJsonKey.toString());
            }
            return getUseTestInstance().booleanValue() ? getTestKey() : getLiveKey();
        } catch (JSONException e) {
            Log.e("BranchJsonConfig", "Error parsing branch.json: " + e.getMessage());
            return null;
        }
    }

    private String getLiveKey() {
        BranchJsonKey branchJsonKey = BranchJsonKey.liveKey;
        if (!isValid(branchJsonKey)) {
            return null;
        }
        try {
            return this.mConfiguration.getString(branchJsonKey.toString());
        } catch (JSONException e) {
            Log.e("BranchJsonConfig", "Error parsing branch.json: " + e.getMessage());
            return null;
        }
    }

    private String getTestKey() {
        JSONObject jSONObject = this.mConfiguration;
        if (jSONObject == null) {
            return null;
        }
        try {
            BranchJsonKey branchJsonKey = BranchJsonKey.testKey;
            if (!jSONObject.has(branchJsonKey.toString())) {
                return null;
            }
            return this.mConfiguration.getString(branchJsonKey.toString());
        } catch (JSONException e) {
            Log.e("BranchJsonConfig", "Error parsing branch.json: " + e.getMessage());
            return null;
        }
    }

    public Boolean getUseTestInstance() {
        BranchJsonKey branchJsonKey = BranchJsonKey.useTestInstance;
        if (!isValid(branchJsonKey)) {
            return null;
        }
        try {
            return Boolean.valueOf(this.mConfiguration.getBoolean(branchJsonKey.toString()));
        } catch (JSONException e) {
            Log.e("BranchJsonConfig", "Error parsing branch.json: " + e.getMessage());
            return Boolean.FALSE;
        }
    }

    public String getAPIUrl() {
        JSONObject jSONObject = this.mConfiguration;
        if (jSONObject == null) {
            return null;
        }
        try {
            BranchJsonKey branchJsonKey = BranchJsonKey.apiUrl;
            if (!jSONObject.has(branchJsonKey.toString())) {
                return null;
            }
            return this.mConfiguration.getString(branchJsonKey.toString());
        } catch (JSONException e) {
            Log.e("BranchJsonConfig", "Error parsing branch.json: " + e.getMessage());
            return null;
        }
    }

    public String getFbAppId() {
        JSONObject jSONObject = this.mConfiguration;
        if (jSONObject == null) {
            return null;
        }
        try {
            BranchJsonKey branchJsonKey = BranchJsonKey.fbAppId;
            if (!jSONObject.has(branchJsonKey.toString())) {
                return null;
            }
            return this.mConfiguration.getString(branchJsonKey.toString());
        } catch (JSONException e) {
            Log.e("BranchJsonConfig", "Error parsing branch.json: " + e.getMessage());
            return null;
        }
    }
}
