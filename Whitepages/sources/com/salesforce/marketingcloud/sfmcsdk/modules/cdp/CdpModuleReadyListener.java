package com.salesforce.marketingcloud.sfmcsdk.modules.cdp;

import com.salesforce.marketingcloud.sfmcsdk.modules.ModuleInterface;
import com.salesforce.marketingcloud.sfmcsdk.modules.ModuleReadyListener;
import kotlin.jvm.internal.Intrinsics;

public interface CdpModuleReadyListener extends ModuleReadyListener {
    void ready(CdpModuleInterface cdpModuleInterface);

    void ready(ModuleInterface moduleInterface) {
        Intrinsics.checkNotNullParameter(moduleInterface, "module");
        ready((CdpModuleInterface) moduleInterface);
    }
}
