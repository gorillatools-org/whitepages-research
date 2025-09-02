package com.salesforce.marketingcloud.sfmcsdk.modules;

import android.content.Context;
import com.salesforce.marketingcloud.sfmcsdk.SFMCSdkComponents;

public interface Config {
    static /* synthetic */ void getMAX_SUPPORTED_VERSION$annotations() {
    }

    int getMAX_SUPPORTED_VERSION() {
        return 1;
    }

    String getModuleApplicationId();

    ModuleIdentifier getModuleIdentifier();

    int getVersion();

    void init(Context context, SFMCSdkComponents sFMCSdkComponents, ModuleReadyListener moduleReadyListener);

    boolean isModuleCompatible() {
        return getVersion() <= getMAX_SUPPORTED_VERSION();
    }
}
