package com.salesforce.marketingcloud.internal;

import com.salesforce.marketingcloud.MarketingCloudConfig;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class e {
    public static final a a = new a((DefaultConstructorMarker) null);

    public static final class a {
        private a() {
        }

        public final boolean a(MarketingCloudConfig marketingCloudConfig, MarketingCloudConfig marketingCloudConfig2) {
            Intrinsics.checkNotNullParameter(marketingCloudConfig, "self");
            Intrinsics.checkNotNullParameter(marketingCloudConfig2, "other");
            return marketingCloudConfig.m614applicationChanged(marketingCloudConfig2);
        }

        public /* synthetic */ a(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public static final boolean a(MarketingCloudConfig marketingCloudConfig, MarketingCloudConfig marketingCloudConfig2) {
        return a.a(marketingCloudConfig, marketingCloudConfig2);
    }
}
