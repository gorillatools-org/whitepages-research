package com.salesforce.marketingcloud.analytics;

import com.salesforce.marketingcloud.analytics.l;
import com.salesforce.marketingcloud.messages.Region;
import com.salesforce.marketingcloud.messages.iam.InAppMessage;
import com.salesforce.marketingcloud.messages.iam.j;
import com.salesforce.marketingcloud.messages.inbox.InboxMessage;
import com.salesforce.marketingcloud.notifications.NotificationMessage;
import com.salesforce.marketingcloud.sfmcsdk.components.events.Event;
import java.util.List;
import org.json.JSONObject;

public abstract class i implements j, k, AnalyticsManager, g, f, m, n, l {
    public void a(long j) {
    }

    public void a(e eVar, Event... eventArr) {
    }

    public void a(l.a aVar, JSONObject jSONObject) {
    }

    public void a(Region region) {
    }

    public void a(InAppMessage inAppMessage) {
    }

    public void a(InAppMessage inAppMessage, j jVar) {
    }

    public void a(InAppMessage inAppMessage, JSONObject jSONObject) {
    }

    public void a(InboxMessage inboxMessage) {
    }

    public void a(NotificationMessage notificationMessage) {
    }

    public void a(NotificationMessage notificationMessage, boolean z) {
    }

    public void a(String str, String str2, String str3, String str4) {
    }

    public void a(String str, String str2, List<String> list) {
    }

    public void a(JSONObject jSONObject) {
    }

    public abstract void a(boolean z);

    public boolean areAnalyticsEnabled() {
        return true;
    }

    public boolean arePiAnalyticsEnabled() {
        return true;
    }

    public void b(long j) {
    }

    public void b(Region region) {
    }

    public void b(InAppMessage inAppMessage) {
    }

    public void b(NotificationMessage notificationMessage) {
    }

    public void b(JSONObject jSONObject) {
    }

    public void c(long j) {
    }

    public void disableAnalytics() {
    }

    public void disablePiAnalytics() {
    }

    public void enableAnalytics() {
    }

    public void enablePiAnalytics() {
    }

    public String getPiIdentifier() {
        return null;
    }

    public void setPiIdentifier(String str) {
    }

    public void trackCartContents(PiCart piCart) {
    }

    public void trackCartConversion(PiOrder piOrder) {
    }

    public void trackInboxOpenEvent(InboxMessage inboxMessage) {
    }

    public final void trackPageView(String str) {
        trackPageView(str, (String) null, (String) null, (String) null);
    }

    public void trackPageView(String str, String str2, String str3, String str4) {
    }

    public final void trackPageView(String str, String str2) {
        trackPageView(str, str2, (String) null, (String) null);
    }

    public final void trackPageView(String str, String str2, String str3) {
        trackPageView(str, str2, str3, (String) null);
    }
}
