package com.google.android.gms.location;

import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.DefaultClock;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.location.zzbe;

@VisibleForTesting
public interface Geofence {
    public static final int GEOFENCE_TRANSITION_DWELL = 4;
    public static final int GEOFENCE_TRANSITION_ENTER = 1;
    public static final int GEOFENCE_TRANSITION_EXIT = 2;
    public static final long NEVER_EXPIRE = -1;

    @VisibleForTesting
    public static final class Builder {
        private String zza = null;
        @TransitionTypes
        private int zzb = 0;
        private long zzc = Long.MIN_VALUE;
        private short zzd = -1;
        private double zze;
        private double zzf;
        private float zzg;
        private int zzh = 0;
        private int zzi = -1;

        public Geofence build() {
            String str = this.zza;
            if (str != null) {
                int i = this.zzb;
                if (i == 0) {
                    throw new IllegalArgumentException("Transitions types not set.");
                } else if ((i & 4) == 0 || this.zzi >= 0) {
                    long j = this.zzc;
                    if (j == Long.MIN_VALUE) {
                        throw new IllegalArgumentException("Expiration not set.");
                    } else if (this.zzd != -1) {
                        int i2 = this.zzh;
                        if (i2 >= 0) {
                            return new zzbe(str, i, 1, this.zze, this.zzf, this.zzg, j, i2, this.zzi);
                        }
                        throw new IllegalArgumentException("Notification responsiveness should be nonnegative.");
                    } else {
                        throw new IllegalArgumentException("Geofence region not set.");
                    }
                } else {
                    throw new IllegalArgumentException("Non-negative loitering delay needs to be set when transition types include GEOFENCE_TRANSITION_DWELLING.");
                }
            } else {
                throw new IllegalArgumentException("Request ID not set.");
            }
        }

        public Builder setCircularRegion(double d, double d2, float f) {
            boolean z = false;
            boolean z2 = d >= -90.0d && d <= 90.0d;
            StringBuilder sb = new StringBuilder(42);
            sb.append("Invalid latitude: ");
            sb.append(d);
            Preconditions.checkArgument(z2, sb.toString());
            boolean z3 = d2 >= -180.0d && d2 <= 180.0d;
            StringBuilder sb2 = new StringBuilder(43);
            sb2.append("Invalid longitude: ");
            sb2.append(d2);
            Preconditions.checkArgument(z3, sb2.toString());
            if (f > 0.0f) {
                z = true;
            }
            StringBuilder sb3 = new StringBuilder(31);
            sb3.append("Invalid radius: ");
            sb3.append(f);
            Preconditions.checkArgument(z, sb3.toString());
            this.zzd = 1;
            this.zze = d;
            this.zzf = d2;
            this.zzg = f;
            return this;
        }

        public Builder setExpirationDuration(long j) {
            if (j < 0) {
                this.zzc = -1;
            } else {
                this.zzc = DefaultClock.getInstance().elapsedRealtime() + j;
            }
            return this;
        }

        public Builder setLoiteringDelay(int i) {
            this.zzi = i;
            return this;
        }

        public Builder setNotificationResponsiveness(int i) {
            this.zzh = i;
            return this;
        }

        public Builder setRequestId(String str) {
            this.zza = (String) Preconditions.checkNotNull(str, "Request ID can't be set to null");
            return this;
        }

        public Builder setTransitionTypes(@TransitionTypes int i) {
            this.zzb = i;
            return this;
        }
    }

    public @interface GeofenceTransition {
    }

    public @interface TransitionTypes {
    }

    String getRequestId();
}
