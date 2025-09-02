package com.facebook.appevents.cloudbridge;

import com.google.firebase.analytics.FirebaseAnalytics;

public enum ConversionsAPICustomEventField {
    VALUE_TO_SUM("value"),
    EVENT_TIME("event_time"),
    EVENT_NAME("event_name"),
    CONTENT_IDS("content_ids"),
    CONTENTS("contents"),
    CONTENT_TYPE("content_type"),
    DESCRIPTION("description"),
    LEVEL(FirebaseAnalytics.Param.LEVEL),
    MAX_RATING_VALUE("max_rating_value"),
    NUM_ITEMS("num_items"),
    PAYMENT_INFO_AVAILABLE("payment_info_available"),
    REGISTRATION_METHOD("registration_method"),
    SEARCH_STRING("search_string"),
    SUCCESS(FirebaseAnalytics.Param.SUCCESS),
    ORDER_ID("order_id"),
    AD_TYPE("ad_type"),
    CURRENCY(FirebaseAnalytics.Param.CURRENCY);
    
    private final String rawValue;

    private ConversionsAPICustomEventField(String str) {
        this.rawValue = str;
    }

    public final String getRawValue() {
        return this.rawValue;
    }
}
