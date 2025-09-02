package com.salesforce.marketingcloud.sfmcsdk;

import android.content.Context;
import com.salesforce.marketingcloud.sfmcsdk.SFMCSdk;
import kotlin.jvm.functions.Function1;

public final /* synthetic */ class SFMCSdk$Companion$$ExternalSyntheticLambda1 implements Runnable {
    public final /* synthetic */ SFMCSdkModuleConfig f$0;
    public final /* synthetic */ Function1 f$1;
    public final /* synthetic */ Context f$2;

    public /* synthetic */ SFMCSdk$Companion$$ExternalSyntheticLambda1(SFMCSdkModuleConfig sFMCSdkModuleConfig, Function1 function1, Context context) {
        this.f$0 = sFMCSdkModuleConfig;
        this.f$1 = function1;
        this.f$2 = context;
    }

    public final void run() {
        SFMCSdk.Companion.m806configure$lambda10$lambda9(this.f$0, this.f$1, this.f$2);
    }
}
