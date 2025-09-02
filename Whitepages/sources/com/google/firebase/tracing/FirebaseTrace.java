package com.google.firebase.tracing;

import android.os.Trace;

public final class FirebaseTrace {
    private FirebaseTrace() {
    }

    public static void pushTrace(String str) {
        Trace.beginSection(str);
    }

    public static void popTrace() {
        Trace.endSection();
    }
}
