package com.salesforce.marketingcloud.messages.iam;

import android.graphics.Typeface;
import com.salesforce.marketingcloud.UrlHandler;
import com.salesforce.marketingcloud.media.o;

interface i {
    boolean canDisplay(InAppMessage inAppMessage);

    int getStatusBarColor();

    Typeface getTypeface();

    void handleMessageFinished(InAppMessage inAppMessage, j jVar);

    o imageHandler();

    UrlHandler urlHandler();
}
