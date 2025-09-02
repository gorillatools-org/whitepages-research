package com.salesforce.marketingcloud.sfmcsdk;

import com.salesforce.marketingcloud.sfmcsdk.components.logging.SFMCSdkLogger;
import com.salesforce.marketingcloud.sfmcsdk.modules.Config;
import com.salesforce.marketingcloud.sfmcsdk.modules.cdp.CdpModuleConfig;
import com.salesforce.marketingcloud.sfmcsdk.modules.push.PushModuleConfig;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class SFMCSdkModuleConfig {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private final CdpModuleConfig cdpModuleConfig;
    private List<? extends Config> configs;
    private final PushModuleConfig pushModuleConfig;

    public /* synthetic */ SFMCSdkModuleConfig(Builder builder, DefaultConstructorMarker defaultConstructorMarker) {
        this(builder);
    }

    public static final SFMCSdkModuleConfig build(Function1 function1) {
        return Companion.build(function1);
    }

    private SFMCSdkModuleConfig(PushModuleConfig pushModuleConfig2, CdpModuleConfig cdpModuleConfig2) {
        this.pushModuleConfig = pushModuleConfig2;
        this.cdpModuleConfig = cdpModuleConfig2;
        this.configs = CollectionsKt.listOfNotNull(pushModuleConfig2, cdpModuleConfig2);
    }

    public final PushModuleConfig getPushModuleConfig() {
        return this.pushModuleConfig;
    }

    public final CdpModuleConfig getCdpModuleConfig() {
        return this.cdpModuleConfig;
    }

    public final List<Config> getConfigs$sfmcsdk_release() {
        return this.configs;
    }

    public final void setConfigs$sfmcsdk_release(List<? extends Config> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.configs = list;
    }

    private SFMCSdkModuleConfig(Builder builder) {
        this(builder.getPushModuleConfig(), builder.getCdpModuleConfig());
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final SFMCSdkModuleConfig build(Function1 function1) {
            Intrinsics.checkNotNullParameter(function1, "block");
            Builder builder = new Builder();
            function1.invoke(builder);
            return builder.build();
        }
    }

    public static final class Builder {
        public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
        private static final String TAG = "~$SFMCSdkModuleConfig.Builder";
        private CdpModuleConfig cdpModuleConfig;
        private PushModuleConfig pushModuleConfig;

        public final PushModuleConfig getPushModuleConfig() {
            return this.pushModuleConfig;
        }

        public final void setPushModuleConfig(PushModuleConfig pushModuleConfig2) {
            if (pushModuleConfig2 == null || !pushModuleConfig2.isModuleCompatible()) {
                SFMCSdkLogger.INSTANCE.w(TAG, new SFMCSdkModuleConfig$Builder$pushModuleConfig$1(pushModuleConfig2));
                pushModuleConfig2 = null;
            }
            this.pushModuleConfig = pushModuleConfig2;
        }

        public final CdpModuleConfig getCdpModuleConfig() {
            return this.cdpModuleConfig;
        }

        public final void setCdpModuleConfig(CdpModuleConfig cdpModuleConfig2) {
            if (cdpModuleConfig2 == null || !cdpModuleConfig2.isModuleCompatible()) {
                SFMCSdkLogger.INSTANCE.w(TAG, new SFMCSdkModuleConfig$Builder$cdpModuleConfig$1(cdpModuleConfig2));
                cdpModuleConfig2 = null;
            }
            this.cdpModuleConfig = cdpModuleConfig2;
        }

        public final SFMCSdkModuleConfig build() {
            return new SFMCSdkModuleConfig(this, (DefaultConstructorMarker) null);
        }

        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }
        }
    }
}
