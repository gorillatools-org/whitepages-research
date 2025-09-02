package com.google.android.datatransport.runtime;

import com.google.android.datatransport.runtime.EventInternal;
import java.util.Arrays;
import java.util.Map;

final class AutoValue_EventInternal extends EventInternal {
    private final Map autoMetadata;
    private final Integer code;
    private final EncodedPayload encodedPayload;
    private final long eventMillis;
    private final byte[] experimentIdsClear;
    private final byte[] experimentIdsEncrypted;
    private final Integer productId;
    private final String pseudonymousId;
    private final String transportName;
    private final long uptimeMillis;

    private AutoValue_EventInternal(String str, Integer num, EncodedPayload encodedPayload2, long j, long j2, Map map, Integer num2, String str2, byte[] bArr, byte[] bArr2) {
        this.transportName = str;
        this.code = num;
        this.encodedPayload = encodedPayload2;
        this.eventMillis = j;
        this.uptimeMillis = j2;
        this.autoMetadata = map;
        this.productId = num2;
        this.pseudonymousId = str2;
        this.experimentIdsClear = bArr;
        this.experimentIdsEncrypted = bArr2;
    }

    public String getTransportName() {
        return this.transportName;
    }

    public Integer getCode() {
        return this.code;
    }

    public EncodedPayload getEncodedPayload() {
        return this.encodedPayload;
    }

    public long getEventMillis() {
        return this.eventMillis;
    }

    public long getUptimeMillis() {
        return this.uptimeMillis;
    }

    /* access modifiers changed from: protected */
    public Map getAutoMetadata() {
        return this.autoMetadata;
    }

    public Integer getProductId() {
        return this.productId;
    }

    public String getPseudonymousId() {
        return this.pseudonymousId;
    }

    public byte[] getExperimentIdsClear() {
        return this.experimentIdsClear;
    }

    public byte[] getExperimentIdsEncrypted() {
        return this.experimentIdsEncrypted;
    }

    public String toString() {
        return "EventInternal{transportName=" + this.transportName + ", code=" + this.code + ", encodedPayload=" + this.encodedPayload + ", eventMillis=" + this.eventMillis + ", uptimeMillis=" + this.uptimeMillis + ", autoMetadata=" + this.autoMetadata + ", productId=" + this.productId + ", pseudonymousId=" + this.pseudonymousId + ", experimentIdsClear=" + Arrays.toString(this.experimentIdsClear) + ", experimentIdsEncrypted=" + Arrays.toString(this.experimentIdsEncrypted) + "}";
    }

    public boolean equals(Object obj) {
        Integer num;
        Integer num2;
        String str;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof EventInternal)) {
            return false;
        }
        EventInternal eventInternal = (EventInternal) obj;
        if (this.transportName.equals(eventInternal.getTransportName()) && ((num = this.code) != null ? num.equals(eventInternal.getCode()) : eventInternal.getCode() == null) && this.encodedPayload.equals(eventInternal.getEncodedPayload()) && this.eventMillis == eventInternal.getEventMillis() && this.uptimeMillis == eventInternal.getUptimeMillis() && this.autoMetadata.equals(eventInternal.getAutoMetadata()) && ((num2 = this.productId) != null ? num2.equals(eventInternal.getProductId()) : eventInternal.getProductId() == null) && ((str = this.pseudonymousId) != null ? str.equals(eventInternal.getPseudonymousId()) : eventInternal.getPseudonymousId() == null)) {
            boolean z = eventInternal instanceof AutoValue_EventInternal;
            if (Arrays.equals(this.experimentIdsClear, z ? ((AutoValue_EventInternal) eventInternal).experimentIdsClear : eventInternal.getExperimentIdsClear())) {
                if (Arrays.equals(this.experimentIdsEncrypted, z ? ((AutoValue_EventInternal) eventInternal).experimentIdsEncrypted : eventInternal.getExperimentIdsEncrypted())) {
                    return true;
                }
            }
        }
        return false;
    }

    public int hashCode() {
        int hashCode = (this.transportName.hashCode() ^ 1000003) * 1000003;
        Integer num = this.code;
        int i = 0;
        int hashCode2 = num == null ? 0 : num.hashCode();
        long j = this.eventMillis;
        long j2 = this.uptimeMillis;
        int hashCode3 = (((((((((hashCode ^ hashCode2) * 1000003) ^ this.encodedPayload.hashCode()) * 1000003) ^ ((int) (j ^ (j >>> 32)))) * 1000003) ^ ((int) (j2 ^ (j2 >>> 32)))) * 1000003) ^ this.autoMetadata.hashCode()) * 1000003;
        Integer num2 = this.productId;
        int hashCode4 = (hashCode3 ^ (num2 == null ? 0 : num2.hashCode())) * 1000003;
        String str = this.pseudonymousId;
        if (str != null) {
            i = str.hashCode();
        }
        return ((((hashCode4 ^ i) * 1000003) ^ Arrays.hashCode(this.experimentIdsClear)) * 1000003) ^ Arrays.hashCode(this.experimentIdsEncrypted);
    }

    static final class Builder extends EventInternal.Builder {
        private Map autoMetadata;
        private Integer code;
        private EncodedPayload encodedPayload;
        private Long eventMillis;
        private byte[] experimentIdsClear;
        private byte[] experimentIdsEncrypted;
        private Integer productId;
        private String pseudonymousId;
        private String transportName;
        private Long uptimeMillis;

        Builder() {
        }

        public EventInternal.Builder setTransportName(String str) {
            if (str != null) {
                this.transportName = str;
                return this;
            }
            throw new NullPointerException("Null transportName");
        }

        public EventInternal.Builder setCode(Integer num) {
            this.code = num;
            return this;
        }

        public EventInternal.Builder setEncodedPayload(EncodedPayload encodedPayload2) {
            if (encodedPayload2 != null) {
                this.encodedPayload = encodedPayload2;
                return this;
            }
            throw new NullPointerException("Null encodedPayload");
        }

        public EventInternal.Builder setEventMillis(long j) {
            this.eventMillis = Long.valueOf(j);
            return this;
        }

        public EventInternal.Builder setUptimeMillis(long j) {
            this.uptimeMillis = Long.valueOf(j);
            return this;
        }

        /* access modifiers changed from: protected */
        public EventInternal.Builder setAutoMetadata(Map map) {
            if (map != null) {
                this.autoMetadata = map;
                return this;
            }
            throw new NullPointerException("Null autoMetadata");
        }

        /* access modifiers changed from: protected */
        public Map getAutoMetadata() {
            Map map = this.autoMetadata;
            if (map != null) {
                return map;
            }
            throw new IllegalStateException("Property \"autoMetadata\" has not been set");
        }

        public EventInternal.Builder setProductId(Integer num) {
            this.productId = num;
            return this;
        }

        public EventInternal.Builder setPseudonymousId(String str) {
            this.pseudonymousId = str;
            return this;
        }

        public EventInternal.Builder setExperimentIdsClear(byte[] bArr) {
            this.experimentIdsClear = bArr;
            return this;
        }

        public EventInternal.Builder setExperimentIdsEncrypted(byte[] bArr) {
            this.experimentIdsEncrypted = bArr;
            return this;
        }

        public EventInternal build() {
            String str = "";
            if (this.transportName == null) {
                str = str + " transportName";
            }
            if (this.encodedPayload == null) {
                str = str + " encodedPayload";
            }
            if (this.eventMillis == null) {
                str = str + " eventMillis";
            }
            if (this.uptimeMillis == null) {
                str = str + " uptimeMillis";
            }
            if (this.autoMetadata == null) {
                str = str + " autoMetadata";
            }
            if (str.isEmpty()) {
                return new AutoValue_EventInternal(this.transportName, this.code, this.encodedPayload, this.eventMillis.longValue(), this.uptimeMillis.longValue(), this.autoMetadata, this.productId, this.pseudonymousId, this.experimentIdsClear, this.experimentIdsEncrypted);
            }
            throw new IllegalStateException("Missing required properties:" + str);
        }
    }
}
