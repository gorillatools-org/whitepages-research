package com.salesforce.marketingcloud.sfmcsdk.modules.push;

import com.salesforce.marketingcloud.sfmcsdk.modules.Config;
import com.salesforce.marketingcloud.sfmcsdk.modules.ModuleIdentifier;
import kotlin.jvm.internal.Intrinsics;

public abstract class PushModuleConfig implements Config {
    private final String moduleApplicationId;
    private final ModuleIdentifier moduleIdentifier = ModuleIdentifier.PUSH;
    private final int version = 1;

    public PushModuleConfig(String str) {
        Intrinsics.checkNotNullParameter(str, "moduleApplicationId");
        this.moduleApplicationId = str;
    }

    public String getModuleApplicationId() {
        return this.moduleApplicationId;
    }

    public ModuleIdentifier getModuleIdentifier() {
        return this.moduleIdentifier;
    }

    public int getVersion() {
        return this.version;
    }
}
