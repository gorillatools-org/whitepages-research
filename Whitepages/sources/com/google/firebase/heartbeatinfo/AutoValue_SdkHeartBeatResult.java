package com.google.firebase.heartbeatinfo;

final class AutoValue_SdkHeartBeatResult extends SdkHeartBeatResult {
    private final long millis;
    private final String sdkName;

    AutoValue_SdkHeartBeatResult(String str, long j) {
        if (str != null) {
            this.sdkName = str;
            this.millis = j;
            return;
        }
        throw new NullPointerException("Null sdkName");
    }

    public String getSdkName() {
        return this.sdkName;
    }

    public long getMillis() {
        return this.millis;
    }

    public String toString() {
        return "SdkHeartBeatResult{sdkName=" + this.sdkName + ", millis=" + this.millis + "}";
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof SdkHeartBeatResult)) {
            return false;
        }
        SdkHeartBeatResult sdkHeartBeatResult = (SdkHeartBeatResult) obj;
        if (!this.sdkName.equals(sdkHeartBeatResult.getSdkName()) || this.millis != sdkHeartBeatResult.getMillis()) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        long j = this.millis;
        return ((this.sdkName.hashCode() ^ 1000003) * 1000003) ^ ((int) (j ^ (j >>> 32)));
    }
}
