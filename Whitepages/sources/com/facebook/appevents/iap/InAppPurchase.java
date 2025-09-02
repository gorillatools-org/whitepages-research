package com.facebook.appevents.iap;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.salesforce.marketingcloud.config.a;
import java.util.Currency;
import kotlin.jvm.internal.Intrinsics;

public final class InAppPurchase {
    private final double amount;
    private final Currency currency;
    private final String eventName;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof InAppPurchase)) {
            return false;
        }
        InAppPurchase inAppPurchase = (InAppPurchase) obj;
        return Intrinsics.areEqual((Object) this.eventName, (Object) inAppPurchase.eventName) && Double.compare(this.amount, inAppPurchase.amount) == 0 && Intrinsics.areEqual((Object) this.currency, (Object) inAppPurchase.currency);
    }

    public int hashCode() {
        return (((this.eventName.hashCode() * 31) + Double.hashCode(this.amount)) * 31) + this.currency.hashCode();
    }

    public String toString() {
        return "InAppPurchase(eventName=" + this.eventName + ", amount=" + this.amount + ", currency=" + this.currency + ')';
    }

    public InAppPurchase(String str, double d, Currency currency2) {
        Intrinsics.checkNotNullParameter(str, a.h);
        Intrinsics.checkNotNullParameter(currency2, FirebaseAnalytics.Param.CURRENCY);
        this.eventName = str;
        this.amount = d;
        this.currency = currency2;
    }

    public final double getAmount() {
        return this.amount;
    }

    public final Currency getCurrency() {
        return this.currency;
    }

    public final String getEventName() {
        return this.eventName;
    }
}
