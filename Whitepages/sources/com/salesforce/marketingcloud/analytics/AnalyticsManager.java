package com.salesforce.marketingcloud.analytics;

import com.salesforce.marketingcloud.MCKeep;
import com.salesforce.marketingcloud.g;
import com.salesforce.marketingcloud.messages.inbox.InboxMessage;

@MCKeep
public interface AnalyticsManager {
    public static final String TAG = g.a("AnalyticsManager");

    boolean areAnalyticsEnabled();

    boolean arePiAnalyticsEnabled();

    void disableAnalytics();

    void disablePiAnalytics();

    void enableAnalytics();

    void enablePiAnalytics();

    String getPiIdentifier();

    void setPiIdentifier(String str);

    void trackCartContents(PiCart piCart);

    void trackCartConversion(PiOrder piOrder);

    void trackInboxOpenEvent(InboxMessage inboxMessage);

    void trackPageView(String str);

    void trackPageView(String str, String str2);

    void trackPageView(String str, String str2, String str3);

    void trackPageView(String str, String str2, String str3, String str4);
}
