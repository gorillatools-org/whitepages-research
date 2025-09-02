package com.google.android.play.core.review;

import android.app.PendingIntent;

final class zza extends ReviewInfo {
    private final PendingIntent zza;
    private final boolean zzb;

    zza(PendingIntent pendingIntent, boolean z) {
        if (pendingIntent != null) {
            this.zza = pendingIntent;
            this.zzb = z;
            return;
        }
        throw new NullPointerException("Null pendingIntent");
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof ReviewInfo) {
            ReviewInfo reviewInfo = (ReviewInfo) obj;
            return this.zza.equals(reviewInfo.zza()) && this.zzb == reviewInfo.zzb();
        }
    }

    public final int hashCode() {
        return ((this.zza.hashCode() ^ 1000003) * 1000003) ^ (true != this.zzb ? 1237 : 1231);
    }

    public final String toString() {
        String obj = this.zza.toString();
        boolean z = this.zzb;
        return "ReviewInfo{pendingIntent=" + obj + ", isNoOp=" + z + "}";
    }

    /* access modifiers changed from: package-private */
    public final PendingIntent zza() {
        return this.zza;
    }

    /* access modifiers changed from: package-private */
    public final boolean zzb() {
        return this.zzb;
    }
}
