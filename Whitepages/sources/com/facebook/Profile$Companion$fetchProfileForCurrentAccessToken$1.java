package com.facebook;

import android.net.Uri;
import android.util.Log;
import com.facebook.internal.Utility;
import org.json.JSONObject;

public final class Profile$Companion$fetchProfileForCurrentAccessToken$1 implements Utility.GraphMeRequestWithCacheCallback {
    Profile$Companion$fetchProfileForCurrentAccessToken$1() {
    }

    public void onSuccess(JSONObject jSONObject) {
        Uri uri = null;
        String optString = jSONObject != null ? jSONObject.optString("id") : null;
        if (optString == null) {
            Log.w(Profile.TAG, "No user ID returned on Me request");
            return;
        }
        String optString2 = jSONObject.optString("link");
        String optString3 = jSONObject.optString("profile_picture", (String) null);
        String optString4 = jSONObject.optString("first_name");
        String optString5 = jSONObject.optString("middle_name");
        String optString6 = jSONObject.optString("last_name");
        String optString7 = jSONObject.optString("name");
        Uri parse = optString2 != null ? Uri.parse(optString2) : null;
        if (optString3 != null) {
            uri = Uri.parse(optString3);
        }
        Profile.Companion.setCurrentProfile(new Profile(optString, optString4, optString5, optString6, optString7, parse, uri));
    }

    public void onFailure(FacebookException facebookException) {
        String access$getTAG$cp = Profile.TAG;
        Log.e(access$getTAG$cp, "Got unexpected exception: " + facebookException);
    }
}
