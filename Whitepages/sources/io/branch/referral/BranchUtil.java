package io.branch.referral;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import io.branch.referral.BranchJsonConfig;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class BranchUtil {
    private static boolean isTestModeEnabled_ = false;
    private static Boolean testModeEnabledViaCompileTimeConfiguration;

    static boolean checkTestMode(Context context) {
        if (!isTestModeEnabled_ && testModeEnabledViaCompileTimeConfiguration == null) {
            BranchJsonConfig instance = BranchJsonConfig.getInstance(context);
            if (instance.isValid(BranchJsonConfig.BranchJsonKey.useTestInstance)) {
                Boolean useTestInstance = instance.getUseTestInstance();
                isTestModeEnabled_ = useTestInstance != null ? useTestInstance.booleanValue() : false;
            } else {
                isTestModeEnabled_ = readTestMode(context);
            }
            testModeEnabledViaCompileTimeConfiguration = Boolean.valueOf(isTestModeEnabled_);
        }
        return isTestModeEnabled_;
    }

    private static boolean readTestMode(Context context) {
        boolean parseBoolean;
        boolean z = isTestModeEnabled_;
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
            Bundle bundle = applicationInfo.metaData;
            if (bundle != null) {
                if (bundle.containsKey("io.branch.sdk.TestMode")) {
                    parseBoolean = applicationInfo.metaData.getBoolean("io.branch.sdk.TestMode", false);
                    return parseBoolean;
                }
            }
            Resources resources = context.getResources();
            parseBoolean = Boolean.parseBoolean(resources.getString(resources.getIdentifier("io.branch.sdk.TestMode", "string", context.getPackageName())));
            return parseBoolean;
        } catch (Exception unused) {
            return z;
        }
    }

    public static String readBranchKey(Context context) {
        BranchJsonConfig instance = BranchJsonConfig.getInstance(context);
        String branchKey = instance.isValid() ? instance.getBranchKey() : null;
        if (branchKey != null) {
            return branchKey;
        }
        String str = isTestModeEnabled() ? "io.branch.sdk.BranchKey.test" : "io.branch.sdk.BranchKey";
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
            Bundle bundle = applicationInfo.metaData;
            if (bundle != null && (branchKey = bundle.getString(str)) == null && isTestModeEnabled()) {
                branchKey = applicationInfo.metaData.getString("io.branch.sdk.BranchKey");
            }
        } catch (PackageManager.NameNotFoundException e) {
            BranchLogger.d(e.getMessage());
        }
        if (branchKey != null) {
            return branchKey;
        }
        Resources resources = context.getResources();
        return resources.getString(resources.getIdentifier(str, "string", context.getPackageName()));
    }

    public static boolean getEnableLoggingConfig(Context context) {
        BranchJsonConfig instance = BranchJsonConfig.getInstance(context);
        if (instance.isValid()) {
            return Boolean.TRUE.equals(instance.getEnableLogging());
        }
        return false;
    }

    public static boolean getDeferInitForPluginRuntimeConfig(Context context) {
        BranchJsonConfig instance = BranchJsonConfig.getInstance(context);
        if (instance.isValid()) {
            return Boolean.TRUE.equals(instance.getDeferInitForPluginRuntime());
        }
        return false;
    }

    public static void setAPIBaseUrlFromConfig(Context context) {
        String aPIUrl = BranchJsonConfig.getInstance(context).getAPIUrl();
        if (!TextUtils.isEmpty(aPIUrl)) {
            Branch.setAPIUrl(aPIUrl);
        }
    }

    public static void setFbAppIdFromConfig(Context context) {
        String fbAppId = BranchJsonConfig.getInstance(context).getFbAppId();
        if (!TextUtils.isEmpty(fbAppId)) {
            Branch.setFBAppID(fbAppId);
        }
    }

    public static boolean isTestModeEnabled() {
        return isTestModeEnabled_;
    }

    static void setTestMode(boolean z) {
        isTestModeEnabled_ = z;
    }

    public static class JsonReader {
        private final JSONObject jsonObject;

        public JsonReader(JSONObject jSONObject) {
            JSONObject jSONObject2 = new JSONObject();
            try {
                jSONObject2 = new JSONObject(jSONObject.toString());
            } catch (JSONException e) {
                BranchLogger.d(e.getMessage());
            }
            this.jsonObject = jSONObject2;
        }

        public JSONObject getJsonObject() {
            return this.jsonObject;
        }

        public Integer readOutInt(String str, Integer num) {
            if (!this.jsonObject.has(str)) {
                return num;
            }
            Integer valueOf = Integer.valueOf(this.jsonObject.optInt(str));
            this.jsonObject.remove(str);
            return valueOf;
        }

        public String readOutString(String str) {
            String optString = this.jsonObject.optString(str);
            this.jsonObject.remove(str);
            return optString;
        }

        public long readOutLong(String str) {
            long optLong = this.jsonObject.optLong(str);
            this.jsonObject.remove(str);
            return optLong;
        }

        public Double readOutDouble(String str, Double d) {
            if (!this.jsonObject.has(str)) {
                return d;
            }
            Double valueOf = Double.valueOf(this.jsonObject.optDouble(str));
            this.jsonObject.remove(str);
            return valueOf;
        }

        public boolean readOutBoolean(String str) {
            boolean optBoolean = this.jsonObject.optBoolean(str);
            this.jsonObject.remove(str);
            return optBoolean;
        }

        public JSONArray readOutJsonArray(String str) {
            JSONArray optJSONArray = this.jsonObject.optJSONArray(str);
            this.jsonObject.remove(str);
            return optJSONArray;
        }

        public Object readOut(String str) {
            Object opt = this.jsonObject.opt(str);
            this.jsonObject.remove(str);
            return opt;
        }
    }

    public static Drawable getDrawable(Context context, int i) {
        return context.getResources().getDrawable(i, context.getTheme());
    }

    public static int dpToPx(Context context, int i) {
        return Math.round(((float) i) * (context.getResources().getDisplayMetrics().xdpi / 160.0f));
    }
}
