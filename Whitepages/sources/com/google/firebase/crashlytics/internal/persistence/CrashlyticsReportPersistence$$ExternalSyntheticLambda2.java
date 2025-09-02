package com.google.firebase.crashlytics.internal.persistence;

import java.io.File;
import java.io.FilenameFilter;

public final /* synthetic */ class CrashlyticsReportPersistence$$ExternalSyntheticLambda2 implements FilenameFilter {
    public final boolean accept(File file, String str) {
        return CrashlyticsReportPersistence.isNormalPriorityEventFile(file, str);
    }
}
