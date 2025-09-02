package com.salesforce.marketingcloud.sfmcsdk;

import com.salesforce.marketingcloud.sfmcsdk.modules.Config;
import com.salesforce.marketingcloud.sfmcsdk.modules.ModuleInterface;
import com.salesforce.marketingcloud.sfmcsdk.modules.ModuleReadyListener;
import java.util.concurrent.CountDownLatch;

public final /* synthetic */ class SFMCSdk$Companion$configure$1$3$2$3$$ExternalSyntheticLambda0 implements ModuleReadyListener {
    public final /* synthetic */ CountDownLatch f$0;
    public final /* synthetic */ Config f$1;

    public /* synthetic */ SFMCSdk$Companion$configure$1$3$2$3$$ExternalSyntheticLambda0(CountDownLatch countDownLatch, Config config) {
        this.f$0 = countDownLatch;
        this.f$1 = config;
    }

    public final void ready(ModuleInterface moduleInterface) {
        SFMCSdk$Companion$configure$1$3$2$3.m808invoke$lambda1(this.f$0, this.f$1, moduleInterface);
    }
}
