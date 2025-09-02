package com.wpconnect;

import android.app.PendingIntent;
import android.content.Context;
import com.salesforce.marketingcloud.UrlHandler;

public final /* synthetic */ class MainApplication$$ExternalSyntheticLambda0 implements UrlHandler {
    public final /* synthetic */ MainApplication f$0;

    public /* synthetic */ MainApplication$$ExternalSyntheticLambda0(MainApplication mainApplication) {
        this.f$0 = mainApplication;
    }

    public final PendingIntent handleUrl(Context context, String str, String str2) {
        return MainApplication.onCreate$lambda$1(this.f$0, context, str, str2);
    }
}
