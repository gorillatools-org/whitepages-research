package com.salesforce.marketingcloud.sfmcsdk.modules;

import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

final class ModuleReadyHandler$execute$1 extends Lambda implements Function0 {
    final /* synthetic */ ModuleReadyListener $listener;
    final /* synthetic */ ModuleInterface $module;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    ModuleReadyHandler$execute$1(ModuleInterface moduleInterface, ModuleReadyListener moduleReadyListener) {
        super(0);
        this.$module = moduleInterface;
        this.$listener = moduleReadyListener;
    }

    public final String invoke() {
        return "Error delivering module " + this.$module + " to " + this.$listener;
    }
}
