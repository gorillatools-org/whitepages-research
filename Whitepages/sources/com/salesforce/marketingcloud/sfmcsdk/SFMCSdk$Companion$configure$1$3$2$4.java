package com.salesforce.marketingcloud.sfmcsdk;

import android.content.Context;
import com.salesforce.marketingcloud.sfmcsdk.components.identity.Identity;
import com.salesforce.marketingcloud.sfmcsdk.components.logging.SFMCSdkLogger;
import com.salesforce.marketingcloud.sfmcsdk.modules.Config;
import com.salesforce.marketingcloud.sfmcsdk.modules.ModuleInterface;
import com.salesforce.marketingcloud.sfmcsdk.modules.cdp.CdpModule;
import java.util.concurrent.CountDownLatch;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

final class SFMCSdk$Companion$configure$1$3$2$4 extends Lambda implements Function0 {
    final /* synthetic */ SFMCSdkComponents $components;
    final /* synthetic */ Config $config;
    final /* synthetic */ Context $context;
    final /* synthetic */ CountDownLatch $moduleInitLatch;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    SFMCSdk$Companion$configure$1$3$2$4(Context context, Config config, SFMCSdkComponents sFMCSdkComponents, CountDownLatch countDownLatch) {
        super(0);
        this.$context = context;
        this.$config = config;
        this.$components = sFMCSdkComponents;
        this.$moduleInitLatch = countDownLatch;
    }

    public final void invoke() {
        CdpModule cdpModule$sfmcsdk_release = SFMCSdk.Companion.getCdpModule$sfmcsdk_release();
        Context context = this.$context;
        Config config = this.$config;
        cdpModule$sfmcsdk_release.initModule(context, config, this.$components, new SFMCSdk$Companion$configure$1$3$2$4$$ExternalSyntheticLambda0(this.$moduleInitLatch, config));
    }

    /* access modifiers changed from: private */
    /* renamed from: invoke$lambda-1  reason: not valid java name */
    public static final void m809invoke$lambda1(CountDownLatch countDownLatch, Config config, ModuleInterface moduleInterface) {
        Intrinsics.checkNotNullParameter(countDownLatch, "$moduleInitLatch");
        Intrinsics.checkNotNullParameter(config, "$config");
        Intrinsics.checkNotNullParameter(moduleInterface, "module");
        countDownLatch.countDown();
        Unit unit = Unit.INSTANCE;
        Identity.Companion.getInstance().setModuleIdentity$sfmcsdk_release(moduleInterface.getModuleIdentity());
        SFMCSdkLogger.INSTANCE.d("~$SFMCSdk", new SFMCSdk$Companion$configure$1$3$2$4$1$1$1(config));
    }
}
