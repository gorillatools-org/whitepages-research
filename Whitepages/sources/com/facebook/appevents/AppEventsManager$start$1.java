package com.facebook.appevents;

import com.facebook.appevents.aam.MetadataIndexer;
import com.facebook.appevents.cloudbridge.AppEventsCAPIManager;
import com.facebook.appevents.eventdeactivation.EventDeactivationManager;
import com.facebook.appevents.gps.ara.GpsAraTriggersManager;
import com.facebook.appevents.gps.pa.PACustomAudienceClient;
import com.facebook.appevents.gps.topics.GpsTopicsManager;
import com.facebook.appevents.iap.InAppPurchaseManager;
import com.facebook.appevents.integrity.BannedParamManager;
import com.facebook.appevents.integrity.BlocklistEventsManager;
import com.facebook.appevents.integrity.MACARuleMatchingManager;
import com.facebook.appevents.integrity.ProtectedModeManager;
import com.facebook.appevents.integrity.RedactedEventsManager;
import com.facebook.appevents.integrity.SensitiveParamsManager;
import com.facebook.appevents.integrity.StdParamsEnforcementManager;
import com.facebook.appevents.ml.ModelManager;
import com.facebook.appevents.restrictivedatafilter.RestrictiveDataManager;
import com.facebook.internal.FeatureManager;
import com.facebook.internal.FetchedAppSettings;
import com.facebook.internal.FetchedAppSettingsManager;

public final class AppEventsManager$start$1 implements FetchedAppSettingsManager.FetchedAppSettingsCallback {
    public void onError() {
    }

    AppEventsManager$start$1() {
    }

    public void onSuccess(FetchedAppSettings fetchedAppSettings) {
        FeatureManager.checkFeature(FeatureManager.Feature.AAM, new AppEventsManager$start$1$$ExternalSyntheticLambda0());
        FeatureManager.checkFeature(FeatureManager.Feature.RestrictiveDataFiltering, new AppEventsManager$start$1$$ExternalSyntheticLambda7());
        FeatureManager.checkFeature(FeatureManager.Feature.PrivacyProtection, new AppEventsManager$start$1$$ExternalSyntheticLambda8());
        FeatureManager.checkFeature(FeatureManager.Feature.EventDeactivation, new AppEventsManager$start$1$$ExternalSyntheticLambda9());
        FeatureManager.checkFeature(FeatureManager.Feature.BannedParamFiltering, new AppEventsManager$start$1$$ExternalSyntheticLambda10());
        FeatureManager.checkFeature(FeatureManager.Feature.IapLogging, new AppEventsManager$start$1$$ExternalSyntheticLambda11());
        FeatureManager.checkFeature(FeatureManager.Feature.StdParamEnforcement, new AppEventsManager$start$1$$ExternalSyntheticLambda12());
        FeatureManager.checkFeature(FeatureManager.Feature.ProtectedMode, new AppEventsManager$start$1$$ExternalSyntheticLambda13());
        FeatureManager.checkFeature(FeatureManager.Feature.MACARuleMatching, new AppEventsManager$start$1$$ExternalSyntheticLambda14());
        FeatureManager.checkFeature(FeatureManager.Feature.BlocklistEvents, new AppEventsManager$start$1$$ExternalSyntheticLambda15());
        FeatureManager.checkFeature(FeatureManager.Feature.FilterRedactedEvents, new AppEventsManager$start$1$$ExternalSyntheticLambda1());
        FeatureManager.checkFeature(FeatureManager.Feature.FilterSensitiveParams, new AppEventsManager$start$1$$ExternalSyntheticLambda2());
        FeatureManager.checkFeature(FeatureManager.Feature.CloudBridge, new AppEventsManager$start$1$$ExternalSyntheticLambda3());
        FeatureManager.checkFeature(FeatureManager.Feature.GPSARATriggers, new AppEventsManager$start$1$$ExternalSyntheticLambda4());
        FeatureManager.checkFeature(FeatureManager.Feature.GPSPACAProcessing, new AppEventsManager$start$1$$ExternalSyntheticLambda5());
        FeatureManager.checkFeature(FeatureManager.Feature.GPSTopicsObservation, new AppEventsManager$start$1$$ExternalSyntheticLambda6());
    }

    /* access modifiers changed from: private */
    public static final void onSuccess$lambda$0(boolean z) {
        if (z) {
            MetadataIndexer.enable();
        }
    }

    /* access modifiers changed from: private */
    public static final void onSuccess$lambda$1(boolean z) {
        if (z) {
            RestrictiveDataManager.enable();
        }
    }

    /* access modifiers changed from: private */
    public static final void onSuccess$lambda$2(boolean z) {
        if (z) {
            ModelManager.enable();
        }
    }

    /* access modifiers changed from: private */
    public static final void onSuccess$lambda$3(boolean z) {
        if (z) {
            EventDeactivationManager.enable();
        }
    }

    /* access modifiers changed from: private */
    public static final void onSuccess$lambda$4(boolean z) {
        if (z) {
            BannedParamManager.enable();
        }
    }

    /* access modifiers changed from: private */
    public static final void onSuccess$lambda$5(boolean z) {
        if (z) {
            InAppPurchaseManager.enableAutoLogging();
        }
    }

    /* access modifiers changed from: private */
    public static final void onSuccess$lambda$6(boolean z) {
        if (z) {
            StdParamsEnforcementManager.enable();
        }
    }

    /* access modifiers changed from: private */
    public static final void onSuccess$lambda$7(boolean z) {
        if (z) {
            ProtectedModeManager.enable();
        }
    }

    /* access modifiers changed from: private */
    public static final void onSuccess$lambda$8(boolean z) {
        if (z) {
            MACARuleMatchingManager.enable();
        }
    }

    /* access modifiers changed from: private */
    public static final void onSuccess$lambda$9(boolean z) {
        if (z) {
            BlocklistEventsManager.enable();
        }
    }

    /* access modifiers changed from: private */
    public static final void onSuccess$lambda$10(boolean z) {
        if (z) {
            RedactedEventsManager.enable();
        }
    }

    /* access modifiers changed from: private */
    public static final void onSuccess$lambda$11(boolean z) {
        if (z) {
            SensitiveParamsManager.enable();
        }
    }

    /* access modifiers changed from: private */
    public static final void onSuccess$lambda$12(boolean z) {
        if (z) {
            AppEventsCAPIManager.enable();
        }
    }

    /* access modifiers changed from: private */
    public static final void onSuccess$lambda$13(boolean z) {
        if (z) {
            GpsAraTriggersManager.enable();
        }
    }

    /* access modifiers changed from: private */
    public static final void onSuccess$lambda$14(boolean z) {
        if (z) {
            PACustomAudienceClient.enable();
        }
    }

    /* access modifiers changed from: private */
    public static final void onSuccess$lambda$15(boolean z) {
        if (z) {
            GpsTopicsManager.enableTopicsObservation();
        }
    }
}
