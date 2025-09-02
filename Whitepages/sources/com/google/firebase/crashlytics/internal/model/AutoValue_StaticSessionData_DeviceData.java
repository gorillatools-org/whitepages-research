package com.google.firebase.crashlytics.internal.model;

import com.google.firebase.crashlytics.internal.model.StaticSessionData;

final class AutoValue_StaticSessionData_DeviceData extends StaticSessionData.DeviceData {
    private final int arch;
    private final int availableProcessors;
    private final long diskSpace;
    private final boolean isEmulator;
    private final String manufacturer;
    private final String model;
    private final String modelClass;
    private final int state;
    private final long totalRam;

    AutoValue_StaticSessionData_DeviceData(int i, String str, int i2, long j, long j2, boolean z, int i3, String str2, String str3) {
        this.arch = i;
        if (str != null) {
            this.model = str;
            this.availableProcessors = i2;
            this.totalRam = j;
            this.diskSpace = j2;
            this.isEmulator = z;
            this.state = i3;
            if (str2 != null) {
                this.manufacturer = str2;
                if (str3 != null) {
                    this.modelClass = str3;
                    return;
                }
                throw new NullPointerException("Null modelClass");
            }
            throw new NullPointerException("Null manufacturer");
        }
        throw new NullPointerException("Null model");
    }

    public int arch() {
        return this.arch;
    }

    public String model() {
        return this.model;
    }

    public int availableProcessors() {
        return this.availableProcessors;
    }

    public long totalRam() {
        return this.totalRam;
    }

    public long diskSpace() {
        return this.diskSpace;
    }

    public boolean isEmulator() {
        return this.isEmulator;
    }

    public int state() {
        return this.state;
    }

    public String manufacturer() {
        return this.manufacturer;
    }

    public String modelClass() {
        return this.modelClass;
    }

    public String toString() {
        return "DeviceData{arch=" + this.arch + ", model=" + this.model + ", availableProcessors=" + this.availableProcessors + ", totalRam=" + this.totalRam + ", diskSpace=" + this.diskSpace + ", isEmulator=" + this.isEmulator + ", state=" + this.state + ", manufacturer=" + this.manufacturer + ", modelClass=" + this.modelClass + "}";
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof StaticSessionData.DeviceData)) {
            return false;
        }
        StaticSessionData.DeviceData deviceData = (StaticSessionData.DeviceData) obj;
        if (this.arch == deviceData.arch() && this.model.equals(deviceData.model()) && this.availableProcessors == deviceData.availableProcessors() && this.totalRam == deviceData.totalRam() && this.diskSpace == deviceData.diskSpace() && this.isEmulator == deviceData.isEmulator() && this.state == deviceData.state() && this.manufacturer.equals(deviceData.manufacturer()) && this.modelClass.equals(deviceData.modelClass())) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        long j = this.totalRam;
        long j2 = this.diskSpace;
        return ((((((((((((((((this.arch ^ 1000003) * 1000003) ^ this.model.hashCode()) * 1000003) ^ this.availableProcessors) * 1000003) ^ ((int) (j ^ (j >>> 32)))) * 1000003) ^ ((int) (j2 ^ (j2 >>> 32)))) * 1000003) ^ (this.isEmulator ? 1231 : 1237)) * 1000003) ^ this.state) * 1000003) ^ this.manufacturer.hashCode()) * 1000003) ^ this.modelClass.hashCode();
    }
}
