package com.google.firebase.crashlytics.ndk;

import com.google.firebase.crashlytics.internal.model.CrashlyticsReport;
import java.io.File;

final class SessionFiles {

    /* renamed from: app  reason: collision with root package name */
    public final File f10app;
    public final File binaryImages;
    public final File device;
    public final File metadata;
    public final NativeCore nativeCore;
    public final File os;
    public final File session;

    static final class NativeCore {
        public final CrashlyticsReport.ApplicationExitInfo applicationExitInfo;
        public final File minidump;

        NativeCore(File file, CrashlyticsReport.ApplicationExitInfo applicationExitInfo2) {
            this.minidump = file;
            this.applicationExitInfo = applicationExitInfo2;
        }

        /* access modifiers changed from: package-private */
        public boolean hasCore() {
            File file = this.minidump;
            return (file != null && file.exists()) || this.applicationExitInfo != null;
        }
    }

    static final class Builder {
        /* access modifiers changed from: private */

        /* renamed from: app  reason: collision with root package name */
        public File f11app;
        /* access modifiers changed from: private */
        public File binaryImages;
        /* access modifiers changed from: private */
        public File device;
        /* access modifiers changed from: private */
        public File metadata;
        /* access modifiers changed from: private */
        public NativeCore nativeCore;
        /* access modifiers changed from: private */
        public File os;
        /* access modifiers changed from: private */
        public File session;

        Builder() {
        }

        /* access modifiers changed from: package-private */
        public Builder nativeCore(NativeCore nativeCore2) {
            this.nativeCore = nativeCore2;
            return this;
        }

        /* access modifiers changed from: package-private */
        public Builder binaryImagesFile(File file) {
            this.binaryImages = file;
            return this;
        }

        /* access modifiers changed from: package-private */
        public Builder metadataFile(File file) {
            this.metadata = file;
            return this;
        }

        /* access modifiers changed from: package-private */
        public Builder sessionFile(File file) {
            this.session = file;
            return this;
        }

        /* access modifiers changed from: package-private */
        public Builder appFile(File file) {
            this.f11app = file;
            return this;
        }

        /* access modifiers changed from: package-private */
        public Builder deviceFile(File file) {
            this.device = file;
            return this;
        }

        /* access modifiers changed from: package-private */
        public Builder osFile(File file) {
            this.os = file;
            return this;
        }

        /* access modifiers changed from: package-private */
        public SessionFiles build() {
            return new SessionFiles(this);
        }
    }

    private SessionFiles(Builder builder) {
        this.nativeCore = builder.nativeCore;
        this.binaryImages = builder.binaryImages;
        this.metadata = builder.metadata;
        this.session = builder.session;
        this.f10app = builder.f11app;
        this.device = builder.device;
        this.os = builder.os;
    }
}
