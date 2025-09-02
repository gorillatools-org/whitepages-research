package com.google.android.datatransport.cct.internal;

import com.google.android.datatransport.cct.internal.AutoValue_ExperimentIds;

public abstract class ExperimentIds {

    public static abstract class Builder {
        public abstract ExperimentIds build();

        public abstract Builder setClearBlob(byte[] bArr);

        public abstract Builder setEncryptedBlob(byte[] bArr);
    }

    public abstract byte[] getClearBlob();

    public abstract byte[] getEncryptedBlob();

    public static Builder builder() {
        return new AutoValue_ExperimentIds.Builder();
    }
}
