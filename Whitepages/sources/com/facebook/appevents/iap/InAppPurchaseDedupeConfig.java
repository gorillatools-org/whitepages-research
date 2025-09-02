package com.facebook.appevents.iap;

import android.os.Bundle;
import com.facebook.FacebookSdk;
import com.facebook.appevents.OperationalData;
import com.facebook.appevents.OperationalDataEnum;
import com.facebook.internal.FetchedAppSettings;
import com.facebook.internal.FetchedAppSettingsManager;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Currency;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

public final class InAppPurchaseDedupeConfig {
    public static final InAppPurchaseDedupeConfig INSTANCE = new InAppPurchaseDedupeConfig();
    private static final List defaultCurrencyParameterEquivalents = CollectionsKt.listOf("fb_currency");
    private static final List defaultDedupeParameters = CollectionsKt.listOf(TuplesKt.to("fb_iap_product_id", CollectionsKt.listOf("fb_iap_product_id")), TuplesKt.to("fb_iap_product_description", CollectionsKt.listOf("fb_iap_product_description")), TuplesKt.to("fb_iap_product_title", CollectionsKt.listOf("fb_iap_product_title")), TuplesKt.to("fb_iap_purchase_token", CollectionsKt.listOf("fb_iap_purchase_token")));
    private static final long defaultDedupeWindow = TimeUnit.MINUTES.toMillis(1);
    private static final List defaultValueParameterEquivalents = CollectionsKt.listOf("_valueToSum");

    private InAppPurchaseDedupeConfig() {
    }

    public final List getDedupeParameters(boolean z) {
        FetchedAppSettings appSettingsWithoutQuery = FetchedAppSettingsManager.getAppSettingsWithoutQuery(FacebookSdk.getApplicationId());
        if ((appSettingsWithoutQuery != null ? appSettingsWithoutQuery.getProdDedupeParameters() : null) == null || appSettingsWithoutQuery.getProdDedupeParameters().isEmpty()) {
            return defaultDedupeParameters;
        }
        if (!z) {
            return appSettingsWithoutQuery.getProdDedupeParameters();
        }
        ArrayList arrayList = new ArrayList();
        for (Pair pair : appSettingsWithoutQuery.getProdDedupeParameters()) {
            for (String pair2 : (List) pair.getSecond()) {
                arrayList.add(new Pair(pair2, CollectionsKt.listOf(pair.getFirst())));
            }
        }
        return arrayList;
    }

    public final List getTestDedupeParameters(boolean z) {
        Collection testDedupeParameters;
        FetchedAppSettings appSettingsWithoutQuery = FetchedAppSettingsManager.getAppSettingsWithoutQuery(FacebookSdk.getApplicationId());
        if (appSettingsWithoutQuery == null || (testDedupeParameters = appSettingsWithoutQuery.getTestDedupeParameters()) == null || testDedupeParameters.isEmpty()) {
            return null;
        }
        if (!z) {
            return appSettingsWithoutQuery.getTestDedupeParameters();
        }
        ArrayList arrayList = new ArrayList();
        for (Pair pair : appSettingsWithoutQuery.getTestDedupeParameters()) {
            for (String pair2 : (List) pair.getSecond()) {
                arrayList.add(new Pair(pair2, CollectionsKt.listOf(pair.getFirst())));
            }
        }
        return arrayList;
    }

    public final List getCurrencyParameterEquivalents() {
        FetchedAppSettings appSettingsWithoutQuery = FetchedAppSettingsManager.getAppSettingsWithoutQuery(FacebookSdk.getApplicationId());
        if ((appSettingsWithoutQuery != null ? appSettingsWithoutQuery.getCurrencyDedupeParameters() : null) == null || appSettingsWithoutQuery.getCurrencyDedupeParameters().isEmpty()) {
            return defaultCurrencyParameterEquivalents;
        }
        return appSettingsWithoutQuery.getCurrencyDedupeParameters();
    }

    public final List getValueParameterEquivalents() {
        FetchedAppSettings appSettingsWithoutQuery = FetchedAppSettingsManager.getAppSettingsWithoutQuery(FacebookSdk.getApplicationId());
        if ((appSettingsWithoutQuery != null ? appSettingsWithoutQuery.getPurchaseValueDedupeParameters() : null) == null || appSettingsWithoutQuery.getPurchaseValueDedupeParameters().isEmpty()) {
            return defaultValueParameterEquivalents;
        }
        return appSettingsWithoutQuery.getPurchaseValueDedupeParameters();
    }

    public final Currency getCurrencyOfManualEvent(Bundle bundle) {
        Iterator it = getCurrencyParameterEquivalents().iterator();
        while (true) {
            String str = null;
            if (!it.hasNext()) {
                return null;
            }
            String str2 = (String) it.next();
            if (bundle != null) {
                try {
                    str = bundle.getString(str2);
                } catch (Exception unused) {
                    continue;
                }
            }
            if (str == null) {
                continue;
            } else if (str.length() != 0) {
                return Currency.getInstance(str);
            }
        }
    }

    public final Double getValueOfManualEvent(Double d, Bundle bundle) {
        if (d != null) {
            return d;
        }
        for (String str : getValueParameterEquivalents()) {
            if (bundle != null) {
                try {
                    return Double.valueOf(bundle.getDouble(str));
                } catch (Exception unused) {
                }
            }
        }
        return null;
    }

    public final long getDedupeWindow() {
        Long dedupeWindow;
        FetchedAppSettings appSettingsWithoutQuery = FetchedAppSettingsManager.getAppSettingsWithoutQuery(FacebookSdk.getApplicationId());
        if ((appSettingsWithoutQuery != null ? appSettingsWithoutQuery.getDedupeWindow() : null) == null || ((dedupeWindow = appSettingsWithoutQuery.getDedupeWindow()) != null && dedupeWindow.longValue() == 0)) {
            return defaultDedupeWindow;
        }
        return appSettingsWithoutQuery.getDedupeWindow().longValue();
    }

    public final Pair addDedupeParameters(Bundle bundle, Bundle bundle2, OperationalData operationalData) {
        if (bundle == null) {
            return new Pair(bundle2, operationalData);
        }
        try {
            for (String next : bundle.keySet()) {
                String string = bundle.getString(next);
                if (string != null) {
                    OperationalData.Companion companion = OperationalData.Companion;
                    OperationalDataEnum operationalDataEnum = OperationalDataEnum.IAPParameters;
                    Intrinsics.checkNotNullExpressionValue(next, "key");
                    Pair addParameterAndReturn = companion.addParameterAndReturn(operationalDataEnum, next, string, bundle2, operationalData);
                    Bundle bundle3 = (Bundle) addParameterAndReturn.component1();
                    operationalData = (OperationalData) addParameterAndReturn.component2();
                    bundle2 = bundle3;
                }
            }
        } catch (Exception unused) {
        }
        return new Pair(bundle2, operationalData);
    }
}
