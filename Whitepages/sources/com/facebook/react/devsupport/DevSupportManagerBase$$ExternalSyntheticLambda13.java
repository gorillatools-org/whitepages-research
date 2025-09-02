package com.facebook.react.devsupport;

import com.facebook.react.devsupport.DevInternalSettings;

public final /* synthetic */ class DevSupportManagerBase$$ExternalSyntheticLambda13 implements DevInternalSettings.Listener {
    public final /* synthetic */ DevSupportManagerBase f$0;

    public /* synthetic */ DevSupportManagerBase$$ExternalSyntheticLambda13(DevSupportManagerBase devSupportManagerBase) {
        this.f$0 = devSupportManagerBase;
    }

    public final void onInternalSettingsChanged() {
        this.f$0.reloadSettings();
    }
}
