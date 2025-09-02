package com.facebook.react.devsupport;

import android.content.Context;
import com.facebook.react.modules.debug.interfaces.DeveloperSettings;
import kotlin.jvm.internal.Intrinsics;

public final class PerftestDevSupportManager extends ReleaseDevSupportManager {
    private final DevServerHelper devServerHelper;
    private final DeveloperSettings devSettings;

    public PerftestDevSupportManager(Context context) {
        Intrinsics.checkNotNullParameter(context, "applicationContext");
        this.devSettings = new DevInternalSettings(context, new PerftestDevSupportManager$devSettings$1());
        this.devServerHelper = new DevServerHelper(getDevSettings(), context, getDevSettings().getPackagerConnectionSettings());
    }

    public DeveloperSettings getDevSettings() {
        return this.devSettings;
    }

    public void startInspector() {
        this.devServerHelper.openInspectorConnection();
    }

    public void stopInspector() {
        this.devServerHelper.closeInspectorConnection();
    }
}
