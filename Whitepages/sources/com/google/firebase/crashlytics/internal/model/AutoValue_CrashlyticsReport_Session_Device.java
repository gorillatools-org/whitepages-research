package com.google.firebase.crashlytics.internal.model;

import com.google.firebase.crashlytics.internal.model.CrashlyticsReport;

final class AutoValue_CrashlyticsReport_Session_Device extends CrashlyticsReport.Session.Device {
    private final int arch;
    private final int cores;
    private final long diskSpace;
    private final String manufacturer;
    private final String model;
    private final String modelClass;
    private final long ram;
    private final boolean simulator;
    private final int state;

    private AutoValue_CrashlyticsReport_Session_Device(int i, String str, int i2, long j, long j2, boolean z, int i3, String str2, String str3) {
        this.arch = i;
        this.model = str;
        this.cores = i2;
        this.ram = j;
        this.diskSpace = j2;
        this.simulator = z;
        this.state = i3;
        this.manufacturer = str2;
        this.modelClass = str3;
    }

    public int getArch() {
        return this.arch;
    }

    public String getModel() {
        return this.model;
    }

    public int getCores() {
        return this.cores;
    }

    public long getRam() {
        return this.ram;
    }

    public long getDiskSpace() {
        return this.diskSpace;
    }

    public boolean isSimulator() {
        return this.simulator;
    }

    public int getState() {
        return this.state;
    }

    public String getManufacturer() {
        return this.manufacturer;
    }

    public String getModelClass() {
        return this.modelClass;
    }

    public String toString() {
        return "Device{arch=" + this.arch + ", model=" + this.model + ", cores=" + this.cores + ", ram=" + this.ram + ", diskSpace=" + this.diskSpace + ", simulator=" + this.simulator + ", state=" + this.state + ", manufacturer=" + this.manufacturer + ", modelClass=" + this.modelClass + "}";
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof CrashlyticsReport.Session.Device)) {
            return false;
        }
        CrashlyticsReport.Session.Device device = (CrashlyticsReport.Session.Device) obj;
        if (this.arch == device.getArch() && this.model.equals(device.getModel()) && this.cores == device.getCores() && this.ram == device.getRam() && this.diskSpace == device.getDiskSpace() && this.simulator == device.isSimulator() && this.state == device.getState() && this.manufacturer.equals(device.getManufacturer()) && this.modelClass.equals(device.getModelClass())) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        long j = this.ram;
        long j2 = this.diskSpace;
        return ((((((((((((((((this.arch ^ 1000003) * 1000003) ^ this.model.hashCode()) * 1000003) ^ this.cores) * 1000003) ^ ((int) (j ^ (j >>> 32)))) * 1000003) ^ ((int) (j2 ^ (j2 >>> 32)))) * 1000003) ^ (this.simulator ? 1231 : 1237)) * 1000003) ^ this.state) * 1000003) ^ this.manufacturer.hashCode()) * 1000003) ^ this.modelClass.hashCode();
    }

    static final class Builder extends CrashlyticsReport.Session.Device.Builder {
        private int arch;
        private int cores;
        private long diskSpace;
        private String manufacturer;
        private String model;
        private String modelClass;
        private long ram;
        private byte set$0;
        private boolean simulator;
        private int state;

        Builder() {
        }

        public CrashlyticsReport.Session.Device.Builder setArch(int i) {
            this.arch = i;
            this.set$0 = (byte) (this.set$0 | 1);
            return this;
        }

        public CrashlyticsReport.Session.Device.Builder setModel(String str) {
            if (str != null) {
                this.model = str;
                return this;
            }
            throw new NullPointerException("Null model");
        }

        public CrashlyticsReport.Session.Device.Builder setCores(int i) {
            this.cores = i;
            this.set$0 = (byte) (this.set$0 | 2);
            return this;
        }

        public CrashlyticsReport.Session.Device.Builder setRam(long j) {
            this.ram = j;
            this.set$0 = (byte) (this.set$0 | 4);
            return this;
        }

        public CrashlyticsReport.Session.Device.Builder setDiskSpace(long j) {
            this.diskSpace = j;
            this.set$0 = (byte) (this.set$0 | 8);
            return this;
        }

        public CrashlyticsReport.Session.Device.Builder setSimulator(boolean z) {
            this.simulator = z;
            this.set$0 = (byte) (this.set$0 | 16);
            return this;
        }

        public CrashlyticsReport.Session.Device.Builder setState(int i) {
            this.state = i;
            this.set$0 = (byte) (this.set$0 | 32);
            return this;
        }

        public CrashlyticsReport.Session.Device.Builder setManufacturer(String str) {
            if (str != null) {
                this.manufacturer = str;
                return this;
            }
            throw new NullPointerException("Null manufacturer");
        }

        public CrashlyticsReport.Session.Device.Builder setModelClass(String str) {
            if (str != null) {
                this.modelClass = str;
                return this;
            }
            throw new NullPointerException("Null modelClass");
        }

        public CrashlyticsReport.Session.Device build() {
            String str;
            String str2;
            String str3;
            if (this.set$0 == 63 && (str = this.model) != null && (str2 = this.manufacturer) != null && (str3 = this.modelClass) != null) {
                return new AutoValue_CrashlyticsReport_Session_Device(this.arch, str, this.cores, this.ram, this.diskSpace, this.simulator, this.state, str2, str3);
            }
            StringBuilder sb = new StringBuilder();
            if ((this.set$0 & 1) == 0) {
                sb.append(" arch");
            }
            if (this.model == null) {
                sb.append(" model");
            }
            if ((this.set$0 & 2) == 0) {
                sb.append(" cores");
            }
            if ((this.set$0 & 4) == 0) {
                sb.append(" ram");
            }
            if ((this.set$0 & 8) == 0) {
                sb.append(" diskSpace");
            }
            if ((this.set$0 & 16) == 0) {
                sb.append(" simulator");
            }
            if ((this.set$0 & 32) == 0) {
                sb.append(" state");
            }
            if (this.manufacturer == null) {
                sb.append(" manufacturer");
            }
            if (this.modelClass == null) {
                sb.append(" modelClass");
            }
            throw new IllegalStateException("Missing required properties:" + sb);
        }
    }
}
