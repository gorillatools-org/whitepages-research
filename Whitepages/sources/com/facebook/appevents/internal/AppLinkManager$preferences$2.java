package com.facebook.appevents.internal;

import android.content.SharedPreferences;
import com.facebook.FacebookSdk;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

final class AppLinkManager$preferences$2 extends Lambda implements Function0 {
    public static final AppLinkManager$preferences$2 INSTANCE = new AppLinkManager$preferences$2();

    AppLinkManager$preferences$2() {
        super(0);
    }

    public final SharedPreferences invoke() {
        return FacebookSdk.getApplicationContext().getSharedPreferences("com.facebook.sdk.APPLINK_INFO", 0);
    }
}
