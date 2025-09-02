package com.google.firebase.crashlytics.internal.common;

import java.io.File;
import java.io.FilenameFilter;

public final /* synthetic */ class CrashlyticsController$$ExternalSyntheticLambda1 implements FilenameFilter {
    public final boolean accept(File file, String str) {
        return str.startsWith(CrashlyticsController.APP_EXCEPTION_MARKER_PREFIX);
    }
}
