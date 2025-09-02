package com.salesforce.marketingcloud.messages.iam;

import android.graphics.Typeface;
import com.salesforce.marketingcloud.MCKeep;

@MCKeep
public interface InAppMessageManager {

    @MCKeep
    public interface EventListener {
        void didCloseMessage(InAppMessage inAppMessage);

        void didShowMessage(InAppMessage inAppMessage);

        boolean shouldShowMessage(InAppMessage inAppMessage);
    }

    void setInAppMessageListener(EventListener eventListener);

    void setStatusBarColor(int i);

    void setTypeface(Typeface typeface);

    void showMessage(String str);
}
