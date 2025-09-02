package com.google.firebase.crashlytics.internal.persistence;

import java.io.File;
import java.io.FilenameFilter;

public final /* synthetic */ class CrashlyticsReportPersistence$$ExternalSyntheticLambda1 implements FilenameFilter {
    public final boolean accept(File file, String str) {
        return str.startsWith(CrashlyticsReportPersistence.EVENT_FILE_NAME_PREFIX);
    }
}
