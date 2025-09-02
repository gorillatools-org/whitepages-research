package com.facebook.react.modules.devtoolsruntimesettings;

final class Settings {
    private boolean recordChangeDescriptions;
    private boolean shouldReloadAndProfile;

    public final boolean getShouldReloadAndProfile() {
        return this.shouldReloadAndProfile;
    }

    public final void setShouldReloadAndProfile(boolean z) {
        this.shouldReloadAndProfile = z;
    }

    public final boolean getRecordChangeDescriptions() {
        return this.recordChangeDescriptions;
    }

    public final void setRecordChangeDescriptions(boolean z) {
        this.recordChangeDescriptions = z;
    }
}
