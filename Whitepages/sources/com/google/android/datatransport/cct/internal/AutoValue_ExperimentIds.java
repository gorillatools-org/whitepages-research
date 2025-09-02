package com.google.android.datatransport.cct.internal;

import com.google.android.datatransport.cct.internal.ExperimentIds;
import java.util.Arrays;

final class AutoValue_ExperimentIds extends ExperimentIds {
    private final byte[] clearBlob;
    private final byte[] encryptedBlob;

    private AutoValue_ExperimentIds(byte[] bArr, byte[] bArr2) {
        this.clearBlob = bArr;
        this.encryptedBlob = bArr2;
    }

    public byte[] getClearBlob() {
        return this.clearBlob;
    }

    public byte[] getEncryptedBlob() {
        return this.encryptedBlob;
    }

    public String toString() {
        return "ExperimentIds{clearBlob=" + Arrays.toString(this.clearBlob) + ", encryptedBlob=" + Arrays.toString(this.encryptedBlob) + "}";
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ExperimentIds)) {
            return false;
        }
        ExperimentIds experimentIds = (ExperimentIds) obj;
        boolean z = experimentIds instanceof AutoValue_ExperimentIds;
        if (Arrays.equals(this.clearBlob, z ? ((AutoValue_ExperimentIds) experimentIds).clearBlob : experimentIds.getClearBlob())) {
            if (Arrays.equals(this.encryptedBlob, z ? ((AutoValue_ExperimentIds) experimentIds).encryptedBlob : experimentIds.getEncryptedBlob())) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        return ((Arrays.hashCode(this.clearBlob) ^ 1000003) * 1000003) ^ Arrays.hashCode(this.encryptedBlob);
    }

    static final class Builder extends ExperimentIds.Builder {
        private byte[] clearBlob;
        private byte[] encryptedBlob;

        Builder() {
        }

        public ExperimentIds.Builder setClearBlob(byte[] bArr) {
            this.clearBlob = bArr;
            return this;
        }

        public ExperimentIds.Builder setEncryptedBlob(byte[] bArr) {
            this.encryptedBlob = bArr;
            return this;
        }

        public ExperimentIds build() {
            return new AutoValue_ExperimentIds(this.clearBlob, this.encryptedBlob);
        }
    }
}
