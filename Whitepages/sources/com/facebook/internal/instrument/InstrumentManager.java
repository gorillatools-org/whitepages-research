package com.facebook.internal.instrument;

import com.facebook.FacebookSdk;
import com.facebook.internal.FeatureManager;
import com.facebook.internal.instrument.anrreport.ANRHandler;
import com.facebook.internal.instrument.crashreport.CrashHandler;
import com.facebook.internal.instrument.crashshield.CrashShieldHandler;
import com.facebook.internal.instrument.errorreport.ErrorReportHandler;
import com.facebook.internal.instrument.threadcheck.ThreadCheckHandler;

public final class InstrumentManager {
    public static final InstrumentManager INSTANCE = new InstrumentManager();

    private InstrumentManager() {
    }

    public static final void start() {
        if (FacebookSdk.getAutoLogAppEventsEnabled()) {
            FeatureManager.checkFeature(FeatureManager.Feature.CrashReport, new InstrumentManager$$ExternalSyntheticLambda0());
            FeatureManager.checkFeature(FeatureManager.Feature.ErrorReport, new InstrumentManager$$ExternalSyntheticLambda1());
            FeatureManager.checkFeature(FeatureManager.Feature.AnrReport, new InstrumentManager$$ExternalSyntheticLambda2());
        }
    }

    /* access modifiers changed from: private */
    public static final void start$lambda$0(boolean z) {
        if (z) {
            CrashHandler.Companion.enable();
            if (FeatureManager.isEnabled(FeatureManager.Feature.CrashShield)) {
                ExceptionAnalyzer.enable();
                CrashShieldHandler.enable();
            }
            if (FeatureManager.isEnabled(FeatureManager.Feature.ThreadCheck)) {
                ThreadCheckHandler.enable();
            }
        }
    }

    /* access modifiers changed from: private */
    public static final void start$lambda$1(boolean z) {
        if (z) {
            ErrorReportHandler.enable();
        }
    }

    /* access modifiers changed from: private */
    public static final void start$lambda$2(boolean z) {
        if (z) {
            ANRHandler.enable();
        }
    }
}
