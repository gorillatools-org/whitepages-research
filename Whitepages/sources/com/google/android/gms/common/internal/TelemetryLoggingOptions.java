package com.google.android.gms.common.internal;

import android.os.Bundle;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.Api;

@KeepForSdk
public class TelemetryLoggingOptions implements Api.ApiOptions.Optional {
    public static final TelemetryLoggingOptions zaa = builder().build();
    private final String zab;

    @KeepForSdk
    public static class Builder {
        private String zaa;

        private Builder() {
        }

        /* synthetic */ Builder(zaac zaac) {
        }

        @KeepForSdk
        public TelemetryLoggingOptions build() {
            return new TelemetryLoggingOptions(this.zaa, (zaad) null);
        }

        @KeepForSdk
        public Builder setApi(String str) {
            this.zaa = str;
            return this;
        }
    }

    /* synthetic */ TelemetryLoggingOptions(String str, zaad zaad) {
        this.zab = str;
    }

    @KeepForSdk
    public static Builder builder() {
        return new Builder((zaac) null);
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof TelemetryLoggingOptions)) {
            return false;
        }
        return Objects.equal(this.zab, ((TelemetryLoggingOptions) obj).zab);
    }

    public final int hashCode() {
        return Objects.hashCode(this.zab);
    }

    public final Bundle zaa() {
        Bundle bundle = new Bundle();
        String str = this.zab;
        if (str != null) {
            bundle.putString("api", str);
        }
        return bundle;
    }
}
