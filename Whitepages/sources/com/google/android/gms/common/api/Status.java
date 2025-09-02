package com.google.android.gms.common.api;

import android.app.Activity;
import android.app.ActivityOptions;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.IntentSenderRequest;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.util.PlatformVersion;

@SafeParcelable.Class(creator = "StatusCreator")
public final class Status extends AbstractSafeParcelable implements Result, ReflectedParcelable {
    public static final Parcelable.Creator<Status> CREATOR = new zze();
    @ShowFirstParty
    @KeepForSdk
    public static final Status RESULT_CANCELED = new Status(16);
    @KeepForSdk
    public static final Status RESULT_DEAD_CLIENT = new Status(18);
    @ShowFirstParty
    @KeepForSdk
    public static final Status RESULT_INTERNAL_ERROR = new Status(8);
    @ShowFirstParty
    @KeepForSdk
    public static final Status RESULT_INTERRUPTED = new Status(14);
    @ShowFirstParty
    @KeepForSdk
    public static final Status RESULT_SUCCESS = new Status(0);
    @ShowFirstParty
    @KeepForSdk
    public static final Status RESULT_SUCCESS_CACHE = new Status(-1);
    @ShowFirstParty
    @KeepForSdk
    public static final Status RESULT_TIMEOUT = new Status(15);
    @ShowFirstParty
    public static final Status zza = new Status(17);
    @SafeParcelable.Field(getter = "getStatusCode", id = 1)
    private final int zzb;
    @SafeParcelable.Field(getter = "getStatusMessage", id = 2)
    private final String zzc;
    @SafeParcelable.Field(getter = "getPendingIntent", id = 3)
    private final PendingIntent zzd;
    @SafeParcelable.Field(getter = "getConnectionResult", id = 4)
    private final ConnectionResult zze;

    public Status(int i) {
        this(i, (String) null);
    }

    @SafeParcelable.Constructor
    Status(@SafeParcelable.Param(id = 1) int i, @SafeParcelable.Param(id = 2) String str, @SafeParcelable.Param(id = 3) PendingIntent pendingIntent, @SafeParcelable.Param(id = 4) ConnectionResult connectionResult) {
        this.zzb = i;
        this.zzc = str;
        this.zzd = pendingIntent;
        this.zze = connectionResult;
    }

    public Status(ConnectionResult connectionResult, String str) {
        this(connectionResult, str, 17);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Status)) {
            return false;
        }
        Status status = (Status) obj;
        if (this.zzb != status.zzb || !Objects.equal(this.zzc, status.zzc) || !Objects.equal(this.zzd, status.zzd) || !Objects.equal(this.zze, status.zze)) {
            return false;
        }
        return true;
    }

    public ConnectionResult getConnectionResult() {
        return this.zze;
    }

    public PendingIntent getResolution() {
        return this.zzd;
    }

    public Status getStatus() {
        return this;
    }

    public int getStatusCode() {
        return this.zzb;
    }

    public String getStatusMessage() {
        return this.zzc;
    }

    public boolean hasResolution() {
        return this.zzd != null;
    }

    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(this.zzb), this.zzc, this.zzd, this.zze);
    }

    public boolean isCanceled() {
        return this.zzb == 16;
    }

    public boolean isInterrupted() {
        return this.zzb == 14;
    }

    public boolean isSuccess() {
        return this.zzb <= 0;
    }

    public void startResolutionForResult(Activity activity, int i) throws IntentSender.SendIntentException {
        if (hasResolution()) {
            Bundle bundle = PlatformVersion.isAtLeastU() ? ActivityOptions.makeBasic().setPendingIntentBackgroundActivityStartMode(1).toBundle() : null;
            PendingIntent pendingIntent = this.zzd;
            Preconditions.checkNotNull(pendingIntent);
            activity.startIntentSenderForResult(pendingIntent.getIntentSender(), i, (Intent) null, 0, 0, 0, bundle);
        }
    }

    public String toString() {
        Objects.ToStringHelper stringHelper = Objects.toStringHelper(this);
        stringHelper.add("statusCode", zza());
        stringHelper.add("resolution", this.zzd);
        return stringHelper.toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, getStatusCode());
        SafeParcelWriter.writeString(parcel, 2, getStatusMessage(), false);
        SafeParcelWriter.writeParcelable(parcel, 3, this.zzd, i, false);
        SafeParcelWriter.writeParcelable(parcel, 4, getConnectionResult(), i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final String zza() {
        String str = this.zzc;
        return str != null ? str : CommonStatusCodes.getStatusCodeString(this.zzb);
    }

    public Status(int i, String str) {
        this(i, str, (PendingIntent) null);
    }

    @KeepForSdk
    @Deprecated
    public Status(ConnectionResult connectionResult, String str, int i) {
        this(i, str, connectionResult.getResolution(), connectionResult);
    }

    public Status(int i, String str, PendingIntent pendingIntent) {
        this(i, str, pendingIntent, (ConnectionResult) null);
    }

    public void startResolutionForResult(ActivityResultLauncher activityResultLauncher) {
        if (hasResolution()) {
            PendingIntent pendingIntent = this.zzd;
            Preconditions.checkNotNull(pendingIntent);
            activityResultLauncher.launch(new IntentSenderRequest.Builder(pendingIntent.getIntentSender()).build());
        }
    }
}
