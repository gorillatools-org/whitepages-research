package com.google.android.datatransport.runtime;

import com.google.android.datatransport.runtime.AutoValue_EventInternal;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public abstract class EventInternal {
    /* access modifiers changed from: protected */
    public abstract Map getAutoMetadata();

    public abstract Integer getCode();

    public abstract EncodedPayload getEncodedPayload();

    public abstract long getEventMillis();

    public abstract byte[] getExperimentIdsClear();

    public abstract byte[] getExperimentIdsEncrypted();

    public abstract Integer getProductId();

    public abstract String getPseudonymousId();

    public abstract String getTransportName();

    public abstract long getUptimeMillis();

    public final Map getMetadata() {
        return Collections.unmodifiableMap(getAutoMetadata());
    }

    public final int getInteger(String str) {
        String str2 = (String) getAutoMetadata().get(str);
        if (str2 == null) {
            return 0;
        }
        return Integer.valueOf(str2).intValue();
    }

    public final long getLong(String str) {
        String str2 = (String) getAutoMetadata().get(str);
        if (str2 == null) {
            return 0;
        }
        return Long.valueOf(str2).longValue();
    }

    public final String get(String str) {
        String str2 = (String) getAutoMetadata().get(str);
        return str2 == null ? "" : str2;
    }

    public Builder toBuilder() {
        return new AutoValue_EventInternal.Builder().setTransportName(getTransportName()).setCode(getCode()).setProductId(getProductId()).setPseudonymousId(getPseudonymousId()).setExperimentIdsClear(getExperimentIdsClear()).setExperimentIdsEncrypted(getExperimentIdsEncrypted()).setEncodedPayload(getEncodedPayload()).setEventMillis(getEventMillis()).setUptimeMillis(getUptimeMillis()).setAutoMetadata(new HashMap(getAutoMetadata()));
    }

    public static Builder builder() {
        return new AutoValue_EventInternal.Builder().setAutoMetadata(new HashMap());
    }

    public static abstract class Builder {
        public abstract EventInternal build();

        /* access modifiers changed from: protected */
        public abstract Map getAutoMetadata();

        /* access modifiers changed from: protected */
        public abstract Builder setAutoMetadata(Map map);

        public abstract Builder setCode(Integer num);

        public abstract Builder setEncodedPayload(EncodedPayload encodedPayload);

        public abstract Builder setEventMillis(long j);

        public abstract Builder setExperimentIdsClear(byte[] bArr);

        public abstract Builder setExperimentIdsEncrypted(byte[] bArr);

        public abstract Builder setProductId(Integer num);

        public abstract Builder setPseudonymousId(String str);

        public abstract Builder setTransportName(String str);

        public abstract Builder setUptimeMillis(long j);

        public final Builder addMetadata(String str, String str2) {
            getAutoMetadata().put(str, str2);
            return this;
        }

        public final Builder addMetadata(String str, long j) {
            getAutoMetadata().put(str, String.valueOf(j));
            return this;
        }

        public final Builder addMetadata(String str, int i) {
            getAutoMetadata().put(str, String.valueOf(i));
            return this;
        }
    }
}
