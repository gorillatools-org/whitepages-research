package com.microsoft.codepush.react;

import android.content.Context;
import android.content.SharedPreferences;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;
import com.google.firebase.messaging.Constants;
import com.google.firebase.remoteconfig.RemoteConfigConstants;
import org.json.JSONException;
import org.json.JSONObject;

public class CodePushTelemetryManager {
    private final String APP_VERSION_KEY = RemoteConfigConstants.RequestFieldKey.APP_VERSION;
    private final String DEPLOYMENT_FAILED_STATUS = "DeploymentFailed";
    private final String DEPLOYMENT_KEY_KEY = "deploymentKey";
    private final String DEPLOYMENT_SUCCEEDED_STATUS = "DeploymentSucceeded";
    private final String LABEL_KEY = Constants.ScionAnalytics.PARAM_LABEL;
    private final String LAST_DEPLOYMENT_REPORT_KEY = "CODE_PUSH_LAST_DEPLOYMENT_REPORT";
    private final String PACKAGE_KEY = "package";
    private final String PREVIOUS_DEPLOYMENT_KEY_KEY = "previousDeploymentKey";
    private final String PREVIOUS_LABEL_OR_APP_VERSION_KEY = "previousLabelOrAppVersion";
    private final String RETRY_DEPLOYMENT_REPORT_KEY = "CODE_PUSH_RETRY_DEPLOYMENT_REPORT";
    private final String STATUS_KEY = "status";
    private SharedPreferences mSettings;

    public CodePushTelemetryManager(Context context) {
        this.mSettings = context.getSharedPreferences("CodePush", 0);
    }

    public WritableMap getBinaryUpdateReport(String str) {
        String previousStatusReportIdentifier = getPreviousStatusReportIdentifier();
        if (previousStatusReportIdentifier == null) {
            clearRetryStatusReport();
            WritableMap createMap = Arguments.createMap();
            createMap.putString(RemoteConfigConstants.RequestFieldKey.APP_VERSION, str);
            return createMap;
        } else if (previousStatusReportIdentifier.equals(str)) {
            return null;
        } else {
            clearRetryStatusReport();
            WritableMap createMap2 = Arguments.createMap();
            if (isStatusReportIdentifierCodePushLabel(previousStatusReportIdentifier)) {
                String deploymentKeyFromStatusReportIdentifier = getDeploymentKeyFromStatusReportIdentifier(previousStatusReportIdentifier);
                String versionLabelFromStatusReportIdentifier = getVersionLabelFromStatusReportIdentifier(previousStatusReportIdentifier);
                createMap2.putString(RemoteConfigConstants.RequestFieldKey.APP_VERSION, str);
                createMap2.putString("previousDeploymentKey", deploymentKeyFromStatusReportIdentifier);
                createMap2.putString("previousLabelOrAppVersion", versionLabelFromStatusReportIdentifier);
            } else {
                createMap2.putString(RemoteConfigConstants.RequestFieldKey.APP_VERSION, str);
                createMap2.putString("previousLabelOrAppVersion", previousStatusReportIdentifier);
            }
            return createMap2;
        }
    }

    public WritableMap getRetryStatusReport() {
        String string = this.mSettings.getString("CODE_PUSH_RETRY_DEPLOYMENT_REPORT", (String) null);
        if (string != null) {
            clearRetryStatusReport();
            try {
                return CodePushUtils.convertJsonObjectToWritable(new JSONObject(string));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public WritableMap getRollbackReport(WritableMap writableMap) {
        WritableMap createMap = Arguments.createMap();
        createMap.putMap("package", writableMap);
        createMap.putString("status", "DeploymentFailed");
        return createMap;
    }

    public WritableMap getUpdateReport(WritableMap writableMap) {
        String packageStatusReportIdentifier = getPackageStatusReportIdentifier(writableMap);
        String previousStatusReportIdentifier = getPreviousStatusReportIdentifier();
        if (packageStatusReportIdentifier != null) {
            if (previousStatusReportIdentifier == null) {
                clearRetryStatusReport();
                WritableMap createMap = Arguments.createMap();
                createMap.putMap("package", writableMap);
                createMap.putString("status", "DeploymentSucceeded");
                return createMap;
            } else if (!previousStatusReportIdentifier.equals(packageStatusReportIdentifier)) {
                clearRetryStatusReport();
                WritableMap createMap2 = Arguments.createMap();
                if (isStatusReportIdentifierCodePushLabel(previousStatusReportIdentifier)) {
                    String deploymentKeyFromStatusReportIdentifier = getDeploymentKeyFromStatusReportIdentifier(previousStatusReportIdentifier);
                    String versionLabelFromStatusReportIdentifier = getVersionLabelFromStatusReportIdentifier(previousStatusReportIdentifier);
                    createMap2.putMap("package", writableMap);
                    createMap2.putString("status", "DeploymentSucceeded");
                    createMap2.putString("previousDeploymentKey", deploymentKeyFromStatusReportIdentifier);
                    createMap2.putString("previousLabelOrAppVersion", versionLabelFromStatusReportIdentifier);
                    return createMap2;
                }
                createMap2.putMap("package", writableMap);
                createMap2.putString("status", "DeploymentSucceeded");
                createMap2.putString("previousLabelOrAppVersion", previousStatusReportIdentifier);
                return createMap2;
            }
        }
        return null;
    }

    public void recordStatusReported(ReadableMap readableMap) {
        if (readableMap.hasKey("status") && "DeploymentFailed".equals(readableMap.getString("status"))) {
            return;
        }
        if (readableMap.hasKey(RemoteConfigConstants.RequestFieldKey.APP_VERSION)) {
            saveStatusReportedForIdentifier(readableMap.getString(RemoteConfigConstants.RequestFieldKey.APP_VERSION));
        } else if (readableMap.hasKey("package")) {
            saveStatusReportedForIdentifier(getPackageStatusReportIdentifier(readableMap.getMap("package")));
        }
    }

    public void saveStatusReportForRetry(ReadableMap readableMap) {
        this.mSettings.edit().putString("CODE_PUSH_RETRY_DEPLOYMENT_REPORT", CodePushUtils.convertReadableToJsonObject(readableMap).toString()).commit();
    }

    private void clearRetryStatusReport() {
        this.mSettings.edit().remove("CODE_PUSH_RETRY_DEPLOYMENT_REPORT").commit();
    }

    private String getDeploymentKeyFromStatusReportIdentifier(String str) {
        String[] split = str.split(":");
        if (split.length > 0) {
            return split[0];
        }
        return null;
    }

    private String getPackageStatusReportIdentifier(ReadableMap readableMap) {
        String tryGetString = CodePushUtils.tryGetString(readableMap, "deploymentKey");
        String tryGetString2 = CodePushUtils.tryGetString(readableMap, Constants.ScionAnalytics.PARAM_LABEL);
        if (tryGetString == null || tryGetString2 == null) {
            return null;
        }
        return tryGetString + ":" + tryGetString2;
    }

    private String getPreviousStatusReportIdentifier() {
        return this.mSettings.getString("CODE_PUSH_LAST_DEPLOYMENT_REPORT", (String) null);
    }

    private String getVersionLabelFromStatusReportIdentifier(String str) {
        String[] split = str.split(":");
        if (split.length > 1) {
            return split[1];
        }
        return null;
    }

    private boolean isStatusReportIdentifierCodePushLabel(String str) {
        return str != null && str.contains(":");
    }

    private void saveStatusReportedForIdentifier(String str) {
        this.mSettings.edit().putString("CODE_PUSH_LAST_DEPLOYMENT_REPORT", str).commit();
    }
}
