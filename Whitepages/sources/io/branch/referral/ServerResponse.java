package io.branch.referral;

import com.facebook.react.devsupport.StackTraceHelper;
import org.json.JSONObject;

public class ServerResponse {
    private String message_;
    private Object post_;
    private String requestId_;
    private int statusCode_;
    private String tag_;

    public ServerResponse(String str, int i, String str2, String str3) {
        this.tag_ = str;
        this.statusCode_ = i;
        this.requestId_ = str2;
        this.message_ = str3;
    }

    public int getStatusCode() {
        return this.statusCode_;
    }

    public void setPost(Object obj) {
        this.post_ = obj;
    }

    public JSONObject getObject() {
        Object obj = this.post_;
        if (obj instanceof JSONObject) {
            return (JSONObject) obj;
        }
        return new JSONObject();
    }

    public String getFailReason() {
        try {
            JSONObject object = getObject();
            if (object == null || !object.has("error") || !object.getJSONObject("error").has(StackTraceHelper.MESSAGE_KEY)) {
                return "";
            }
            String string = object.getJSONObject("error").getString(StackTraceHelper.MESSAGE_KEY);
            if (string == null || string.trim().length() <= 0) {
                return string;
            }
            return string + ".";
        } catch (Exception e) {
            BranchLogger.w("Caught Exception ServerResponse getFailReason: " + e.getMessage());
            return "";
        }
    }

    public String getMessage() {
        return this.message_;
    }
}
