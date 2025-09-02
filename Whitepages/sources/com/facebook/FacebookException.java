package com.facebook;

import com.facebook.internal.FeatureManager;
import com.facebook.internal.instrument.errorreport.ErrorReportHandler;
import java.util.Random;
import kotlin.jvm.internal.DefaultConstructorMarker;

public class FacebookException extends RuntimeException {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final long serialVersionUID = 1;

    public FacebookException() {
    }

    public FacebookException(String str) {
        super(str);
        Random random = new Random();
        if (str != null && FacebookSdk.isInitialized() && random.nextInt(100) > 50) {
            FeatureManager.checkFeature(FeatureManager.Feature.ErrorReport, new FacebookException$$ExternalSyntheticLambda0(str));
        }
    }

    /* access modifiers changed from: private */
    public static final void _init_$lambda$0(String str, boolean z) {
        if (z) {
            try {
                ErrorReportHandler.save(str);
            } catch (Exception unused) {
            }
        }
    }

    public FacebookException(String str, Throwable th) {
        super(str, th);
    }

    public FacebookException(Throwable th) {
        super(th);
    }

    public String toString() {
        String message = getMessage();
        return message == null ? "" : message;
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
