package com.google.firebase.sessions;

import com.google.firebase.remoteconfig.RemoteConfigConstants;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;

public final class AndroidApplicationInfo {
    private final String appBuildVersion;
    private final List<ProcessDetails> appProcessDetails;
    private final ProcessDetails currentProcessDetails;
    private final String deviceManufacturer;
    private final String packageName;
    private final String versionName;

    public static /* synthetic */ AndroidApplicationInfo copy$default(AndroidApplicationInfo androidApplicationInfo, String str, String str2, String str3, String str4, ProcessDetails processDetails, List<ProcessDetails> list, int i, Object obj) {
        if ((i & 1) != 0) {
            str = androidApplicationInfo.packageName;
        }
        if ((i & 2) != 0) {
            str2 = androidApplicationInfo.versionName;
        }
        String str5 = str2;
        if ((i & 4) != 0) {
            str3 = androidApplicationInfo.appBuildVersion;
        }
        String str6 = str3;
        if ((i & 8) != 0) {
            str4 = androidApplicationInfo.deviceManufacturer;
        }
        String str7 = str4;
        if ((i & 16) != 0) {
            processDetails = androidApplicationInfo.currentProcessDetails;
        }
        ProcessDetails processDetails2 = processDetails;
        if ((i & 32) != 0) {
            list = androidApplicationInfo.appProcessDetails;
        }
        return androidApplicationInfo.copy(str, str5, str6, str7, processDetails2, list);
    }

    public final String component1() {
        return this.packageName;
    }

    public final String component2() {
        return this.versionName;
    }

    public final String component3() {
        return this.appBuildVersion;
    }

    public final String component4() {
        return this.deviceManufacturer;
    }

    public final ProcessDetails component5() {
        return this.currentProcessDetails;
    }

    public final List<ProcessDetails> component6() {
        return this.appProcessDetails;
    }

    public final AndroidApplicationInfo copy(String str, String str2, String str3, String str4, ProcessDetails processDetails, List<ProcessDetails> list) {
        Intrinsics.checkNotNullParameter(str, RemoteConfigConstants.RequestFieldKey.PACKAGE_NAME);
        Intrinsics.checkNotNullParameter(str2, "versionName");
        Intrinsics.checkNotNullParameter(str3, "appBuildVersion");
        Intrinsics.checkNotNullParameter(str4, "deviceManufacturer");
        Intrinsics.checkNotNullParameter(processDetails, "currentProcessDetails");
        Intrinsics.checkNotNullParameter(list, "appProcessDetails");
        return new AndroidApplicationInfo(str, str2, str3, str4, processDetails, list);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AndroidApplicationInfo)) {
            return false;
        }
        AndroidApplicationInfo androidApplicationInfo = (AndroidApplicationInfo) obj;
        return Intrinsics.areEqual((Object) this.packageName, (Object) androidApplicationInfo.packageName) && Intrinsics.areEqual((Object) this.versionName, (Object) androidApplicationInfo.versionName) && Intrinsics.areEqual((Object) this.appBuildVersion, (Object) androidApplicationInfo.appBuildVersion) && Intrinsics.areEqual((Object) this.deviceManufacturer, (Object) androidApplicationInfo.deviceManufacturer) && Intrinsics.areEqual((Object) this.currentProcessDetails, (Object) androidApplicationInfo.currentProcessDetails) && Intrinsics.areEqual((Object) this.appProcessDetails, (Object) androidApplicationInfo.appProcessDetails);
    }

    public int hashCode() {
        return (((((((((this.packageName.hashCode() * 31) + this.versionName.hashCode()) * 31) + this.appBuildVersion.hashCode()) * 31) + this.deviceManufacturer.hashCode()) * 31) + this.currentProcessDetails.hashCode()) * 31) + this.appProcessDetails.hashCode();
    }

    public String toString() {
        return "AndroidApplicationInfo(packageName=" + this.packageName + ", versionName=" + this.versionName + ", appBuildVersion=" + this.appBuildVersion + ", deviceManufacturer=" + this.deviceManufacturer + ", currentProcessDetails=" + this.currentProcessDetails + ", appProcessDetails=" + this.appProcessDetails + ')';
    }

    public AndroidApplicationInfo(String str, String str2, String str3, String str4, ProcessDetails processDetails, List<ProcessDetails> list) {
        Intrinsics.checkNotNullParameter(str, RemoteConfigConstants.RequestFieldKey.PACKAGE_NAME);
        Intrinsics.checkNotNullParameter(str2, "versionName");
        Intrinsics.checkNotNullParameter(str3, "appBuildVersion");
        Intrinsics.checkNotNullParameter(str4, "deviceManufacturer");
        Intrinsics.checkNotNullParameter(processDetails, "currentProcessDetails");
        Intrinsics.checkNotNullParameter(list, "appProcessDetails");
        this.packageName = str;
        this.versionName = str2;
        this.appBuildVersion = str3;
        this.deviceManufacturer = str4;
        this.currentProcessDetails = processDetails;
        this.appProcessDetails = list;
    }

    public final String getPackageName() {
        return this.packageName;
    }

    public final String getVersionName() {
        return this.versionName;
    }

    public final String getAppBuildVersion() {
        return this.appBuildVersion;
    }

    public final String getDeviceManufacturer() {
        return this.deviceManufacturer;
    }

    public final ProcessDetails getCurrentProcessDetails() {
        return this.currentProcessDetails;
    }

    public final List<ProcessDetails> getAppProcessDetails() {
        return this.appProcessDetails;
    }
}
