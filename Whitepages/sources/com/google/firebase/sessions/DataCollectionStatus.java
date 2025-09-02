package com.google.firebase.sessions;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class DataCollectionStatus {
    private final DataCollectionState crashlytics;
    private final DataCollectionState performance;
    private final double sessionSamplingRate;

    public DataCollectionStatus() {
        this((DataCollectionState) null, (DataCollectionState) null, 0.0d, 7, (DefaultConstructorMarker) null);
    }

    public static /* synthetic */ DataCollectionStatus copy$default(DataCollectionStatus dataCollectionStatus, DataCollectionState dataCollectionState, DataCollectionState dataCollectionState2, double d, int i, Object obj) {
        if ((i & 1) != 0) {
            dataCollectionState = dataCollectionStatus.performance;
        }
        if ((i & 2) != 0) {
            dataCollectionState2 = dataCollectionStatus.crashlytics;
        }
        if ((i & 4) != 0) {
            d = dataCollectionStatus.sessionSamplingRate;
        }
        return dataCollectionStatus.copy(dataCollectionState, dataCollectionState2, d);
    }

    public final DataCollectionState component1() {
        return this.performance;
    }

    public final DataCollectionState component2() {
        return this.crashlytics;
    }

    public final double component3() {
        return this.sessionSamplingRate;
    }

    public final DataCollectionStatus copy(DataCollectionState dataCollectionState, DataCollectionState dataCollectionState2, double d) {
        Intrinsics.checkNotNullParameter(dataCollectionState, "performance");
        Intrinsics.checkNotNullParameter(dataCollectionState2, "crashlytics");
        return new DataCollectionStatus(dataCollectionState, dataCollectionState2, d);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof DataCollectionStatus)) {
            return false;
        }
        DataCollectionStatus dataCollectionStatus = (DataCollectionStatus) obj;
        return this.performance == dataCollectionStatus.performance && this.crashlytics == dataCollectionStatus.crashlytics && Double.compare(this.sessionSamplingRate, dataCollectionStatus.sessionSamplingRate) == 0;
    }

    public int hashCode() {
        return (((this.performance.hashCode() * 31) + this.crashlytics.hashCode()) * 31) + Double.hashCode(this.sessionSamplingRate);
    }

    public String toString() {
        return "DataCollectionStatus(performance=" + this.performance + ", crashlytics=" + this.crashlytics + ", sessionSamplingRate=" + this.sessionSamplingRate + ')';
    }

    public DataCollectionStatus(DataCollectionState dataCollectionState, DataCollectionState dataCollectionState2, double d) {
        Intrinsics.checkNotNullParameter(dataCollectionState, "performance");
        Intrinsics.checkNotNullParameter(dataCollectionState2, "crashlytics");
        this.performance = dataCollectionState;
        this.crashlytics = dataCollectionState2;
        this.sessionSamplingRate = d;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ DataCollectionStatus(DataCollectionState dataCollectionState, DataCollectionState dataCollectionState2, double d, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? DataCollectionState.COLLECTION_SDK_NOT_INSTALLED : dataCollectionState, (i & 2) != 0 ? DataCollectionState.COLLECTION_SDK_NOT_INSTALLED : dataCollectionState2, (i & 4) != 0 ? 1.0d : d);
    }

    public final DataCollectionState getPerformance() {
        return this.performance;
    }

    public final DataCollectionState getCrashlytics() {
        return this.crashlytics;
    }

    public final double getSessionSamplingRate() {
        return this.sessionSamplingRate;
    }
}
