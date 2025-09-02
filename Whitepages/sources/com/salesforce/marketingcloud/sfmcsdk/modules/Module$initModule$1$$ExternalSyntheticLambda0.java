package com.salesforce.marketingcloud.sfmcsdk.modules;

public final /* synthetic */ class Module$initModule$1$$ExternalSyntheticLambda0 implements ModuleReadyListener {
    public final /* synthetic */ Module$initModule$1 f$0;
    public final /* synthetic */ Module f$1;
    public final /* synthetic */ Config f$2;
    public final /* synthetic */ ModuleReadyListener f$3;

    public /* synthetic */ Module$initModule$1$$ExternalSyntheticLambda0(Module$initModule$1 module$initModule$1, Module module, Config config, ModuleReadyListener moduleReadyListener) {
        this.f$0 = module$initModule$1;
        this.f$1 = module;
        this.f$2 = config;
        this.f$3 = moduleReadyListener;
    }

    public final void ready(ModuleInterface moduleInterface) {
        Module$initModule$1.m812execute$lambda2(this.f$0, this.f$1, this.f$2, this.f$3, moduleInterface);
    }
}
