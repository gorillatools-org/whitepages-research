package com.salesforce.marketingcloud;

import android.app.PendingIntent;
import android.content.Context;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@MCKeep
public interface UrlHandler {
    public static final String ACTION = "action";

    @Retention(RetentionPolicy.SOURCE)
    public @interface a {
    }

    PendingIntent handleUrl(Context context, String str, String str2);
}
