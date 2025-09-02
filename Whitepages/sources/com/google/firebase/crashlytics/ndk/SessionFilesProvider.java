package com.google.firebase.crashlytics.ndk;

import com.google.firebase.crashlytics.internal.NativeSessionFileProvider;
import com.google.firebase.crashlytics.internal.model.CrashlyticsReport;
import com.google.firebase.crashlytics.ndk.SessionFiles;
import java.io.File;

class SessionFilesProvider implements NativeSessionFileProvider {
    private final SessionFiles sessionFiles;

    SessionFilesProvider(SessionFiles sessionFiles2) {
        this.sessionFiles = sessionFiles2;
    }

    public File getMinidumpFile() {
        return this.sessionFiles.nativeCore.minidump;
    }

    public CrashlyticsReport.ApplicationExitInfo getApplicationExitInto() {
        SessionFiles.NativeCore nativeCore = this.sessionFiles.nativeCore;
        if (nativeCore != null) {
            return nativeCore.applicationExitInfo;
        }
        return null;
    }

    public File getBinaryImagesFile() {
        return this.sessionFiles.binaryImages;
    }

    public File getMetadataFile() {
        return this.sessionFiles.metadata;
    }

    public File getSessionFile() {
        return this.sessionFiles.session;
    }

    public File getAppFile() {
        return this.sessionFiles.f10app;
    }

    public File getDeviceFile() {
        return this.sessionFiles.device;
    }

    public File getOsFile() {
        return this.sessionFiles.os;
    }
}
