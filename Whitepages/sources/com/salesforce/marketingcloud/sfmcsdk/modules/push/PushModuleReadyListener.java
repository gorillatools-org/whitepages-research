package com.salesforce.marketingcloud.sfmcsdk.modules.push;

import com.salesforce.marketingcloud.sfmcsdk.modules.ModuleInterface;
import com.salesforce.marketingcloud.sfmcsdk.modules.ModuleReadyListener;
import kotlin.jvm.internal.Intrinsics;

public interface PushModuleReadyListener extends ModuleReadyListener {
    void ready(PushModuleInterface pushModuleInterface);

    void ready(ModuleInterface moduleInterface) {
        Intrinsics.checkNotNullParameter(moduleInterface, "module");
        ready((PushModuleInterface) moduleInterface);
    }
}
