package com.salesforce.marketingcloud.sfmcsdk.modules.cdp;

import com.salesforce.marketingcloud.sfmcsdk.modules.Module;
import kotlin.jvm.internal.DefaultConstructorMarker;

public final class CdpModule extends Module {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final String TAG = "~$CdpSdkModule";

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    public String getName() {
        return "CDP";
    }
}
