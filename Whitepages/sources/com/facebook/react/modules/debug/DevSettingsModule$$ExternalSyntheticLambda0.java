package com.facebook.react.modules.debug;

import com.facebook.react.devsupport.interfaces.DevOptionHandler;

public final /* synthetic */ class DevSettingsModule$$ExternalSyntheticLambda0 implements DevOptionHandler {
    public final /* synthetic */ String f$0;
    public final /* synthetic */ DevSettingsModule f$1;

    public /* synthetic */ DevSettingsModule$$ExternalSyntheticLambda0(String str, DevSettingsModule devSettingsModule) {
        this.f$0 = str;
        this.f$1 = devSettingsModule;
    }

    public final void onOptionSelected() {
        DevSettingsModule.addMenuItem$lambda$1(this.f$0, this.f$1);
    }
}
