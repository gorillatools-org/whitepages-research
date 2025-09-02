package com.salesforce.marketingcloud.sfmcsdk;

public final class SFMCSdkInitializationStatus implements InitializationStatus {
    private final boolean success;

    public SFMCSdkInitializationStatus(boolean z) {
        this.success = z;
    }

    public int getStatus() {
        return this.success ? 1 : -1;
    }
}
