package com.google.firebase.crashlytics.internal.model;

import com.google.firebase.crashlytics.internal.model.StaticSessionData;

final class AutoValue_StaticSessionData extends StaticSessionData {
    private final StaticSessionData.AppData appData;
    private final StaticSessionData.DeviceData deviceData;
    private final StaticSessionData.OsData osData;

    AutoValue_StaticSessionData(StaticSessionData.AppData appData2, StaticSessionData.OsData osData2, StaticSessionData.DeviceData deviceData2) {
        if (appData2 != null) {
            this.appData = appData2;
            if (osData2 != null) {
                this.osData = osData2;
                if (deviceData2 != null) {
                    this.deviceData = deviceData2;
                    return;
                }
                throw new NullPointerException("Null deviceData");
            }
            throw new NullPointerException("Null osData");
        }
        throw new NullPointerException("Null appData");
    }

    public StaticSessionData.AppData appData() {
        return this.appData;
    }

    public StaticSessionData.OsData osData() {
        return this.osData;
    }

    public StaticSessionData.DeviceData deviceData() {
        return this.deviceData;
    }

    public String toString() {
        return "StaticSessionData{appData=" + this.appData + ", osData=" + this.osData + ", deviceData=" + this.deviceData + "}";
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof StaticSessionData)) {
            return false;
        }
        StaticSessionData staticSessionData = (StaticSessionData) obj;
        if (!this.appData.equals(staticSessionData.appData()) || !this.osData.equals(staticSessionData.osData()) || !this.deviceData.equals(staticSessionData.deviceData())) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return ((((this.appData.hashCode() ^ 1000003) * 1000003) ^ this.osData.hashCode()) * 1000003) ^ this.deviceData.hashCode();
    }
}
