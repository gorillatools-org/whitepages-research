package com.facebook.appevents.internal;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import com.facebook.FacebookSdk;
import kotlin.jvm.internal.DefaultConstructorMarker;

public final class SourceApplicationInfo {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private final String callingApplicationPackage;
    private final boolean isOpenedByAppLink;

    public /* synthetic */ SourceApplicationInfo(String str, boolean z, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, z);
    }

    private SourceApplicationInfo(String str, boolean z) {
        this.callingApplicationPackage = str;
        this.isOpenedByAppLink = z;
    }

    public String toString() {
        String str;
        if (this.isOpenedByAppLink) {
            str = "Applink";
        } else {
            str = "Unclassified";
        }
        if (this.callingApplicationPackage == null) {
            return str;
        }
        return str + '(' + this.callingApplicationPackage + ')';
    }

    public final void writeSourceApplicationInfoToDisk() {
        SharedPreferences.Editor edit = PreferenceManager.getDefaultSharedPreferences(FacebookSdk.getApplicationContext()).edit();
        edit.putString("com.facebook.appevents.SourceApplicationInfo.callingApplicationPackage", this.callingApplicationPackage);
        edit.putBoolean("com.facebook.appevents.SourceApplicationInfo.openedByApplink", this.isOpenedByAppLink);
        edit.apply();
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final SourceApplicationInfo getStoredSourceApplicatioInfo() {
            SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(FacebookSdk.getApplicationContext());
            if (!defaultSharedPreferences.contains("com.facebook.appevents.SourceApplicationInfo.callingApplicationPackage")) {
                return null;
            }
            return new SourceApplicationInfo(defaultSharedPreferences.getString("com.facebook.appevents.SourceApplicationInfo.callingApplicationPackage", (String) null), defaultSharedPreferences.getBoolean("com.facebook.appevents.SourceApplicationInfo.openedByApplink", false), (DefaultConstructorMarker) null);
        }

        public final void clearSavedSourceApplicationInfoFromDisk() {
            SharedPreferences.Editor edit = PreferenceManager.getDefaultSharedPreferences(FacebookSdk.getApplicationContext()).edit();
            edit.remove("com.facebook.appevents.SourceApplicationInfo.callingApplicationPackage");
            edit.remove("com.facebook.appevents.SourceApplicationInfo.openedByApplink");
            edit.apply();
        }
    }
}
