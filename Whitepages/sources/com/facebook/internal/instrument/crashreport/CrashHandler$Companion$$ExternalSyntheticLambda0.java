package com.facebook.internal.instrument.crashreport;

import com.facebook.internal.instrument.InstrumentData;
import com.facebook.internal.instrument.crashreport.CrashHandler;
import java.util.Comparator;

public final /* synthetic */ class CrashHandler$Companion$$ExternalSyntheticLambda0 implements Comparator {
    public final int compare(Object obj, Object obj2) {
        return CrashHandler.Companion.sendExceptionReports$lambda$2((InstrumentData) obj, (InstrumentData) obj2);
    }
}
