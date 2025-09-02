package com.google.firebase.crashlytics;

import android.os.Bundle;
import com.google.android.gms.measurement.AppMeasurement;
import com.google.firebase.analytics.connector.AnalyticsConnector;
import com.google.firebase.annotations.DeferredApi;
import com.google.firebase.crashlytics.internal.Logger;
import com.google.firebase.crashlytics.internal.analytics.AnalyticsEventLogger;
import com.google.firebase.crashlytics.internal.analytics.BlockingAnalyticsEventLogger;
import com.google.firebase.crashlytics.internal.analytics.BreadcrumbAnalyticsEventReceiver;
import com.google.firebase.crashlytics.internal.analytics.CrashlyticsOriginAnalyticsEventLogger;
import com.google.firebase.crashlytics.internal.analytics.UnavailableAnalyticsEventLogger;
import com.google.firebase.crashlytics.internal.breadcrumbs.BreadcrumbHandler;
import com.google.firebase.crashlytics.internal.breadcrumbs.BreadcrumbSource;
import com.google.firebase.crashlytics.internal.breadcrumbs.DisabledBreadcrumbSource;
import com.google.firebase.inject.Deferred;
import com.google.firebase.inject.Provider;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class AnalyticsDeferredProxy {
    private final Deferred<AnalyticsConnector> analyticsConnectorDeferred;
    private volatile AnalyticsEventLogger analyticsEventLogger;
    private final List<BreadcrumbHandler> breadcrumbHandlerList;
    private volatile BreadcrumbSource breadcrumbSource;

    public AnalyticsDeferredProxy(Deferred<AnalyticsConnector> deferred) {
        this(deferred, new DisabledBreadcrumbSource(), new UnavailableAnalyticsEventLogger());
    }

    public AnalyticsDeferredProxy(Deferred<AnalyticsConnector> deferred, BreadcrumbSource breadcrumbSource2, AnalyticsEventLogger analyticsEventLogger2) {
        this.analyticsConnectorDeferred = deferred;
        this.breadcrumbSource = breadcrumbSource2;
        this.breadcrumbHandlerList = new ArrayList();
        this.analyticsEventLogger = analyticsEventLogger2;
        init();
    }

    public BreadcrumbSource getDeferredBreadcrumbSource() {
        return new AnalyticsDeferredProxy$$ExternalSyntheticLambda0(this);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$getDeferredBreadcrumbSource$0(BreadcrumbHandler breadcrumbHandler) {
        synchronized (this) {
            try {
                if (this.breadcrumbSource instanceof DisabledBreadcrumbSource) {
                    this.breadcrumbHandlerList.add(breadcrumbHandler);
                }
                this.breadcrumbSource.registerBreadcrumbHandler(breadcrumbHandler);
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$getAnalyticsEventLogger$1(String str, Bundle bundle) {
        this.analyticsEventLogger.logEvent(str, bundle);
    }

    public AnalyticsEventLogger getAnalyticsEventLogger() {
        return new AnalyticsDeferredProxy$$ExternalSyntheticLambda1(this);
    }

    private void init() {
        this.analyticsConnectorDeferred.whenAvailable(new AnalyticsDeferredProxy$$ExternalSyntheticLambda2(this));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$init$2(Provider provider) {
        Logger.getLogger().d("AnalyticsConnector now available.");
        AnalyticsConnector analyticsConnector = (AnalyticsConnector) provider.get();
        CrashlyticsOriginAnalyticsEventLogger crashlyticsOriginAnalyticsEventLogger = new CrashlyticsOriginAnalyticsEventLogger(analyticsConnector);
        CrashlyticsAnalyticsListener crashlyticsAnalyticsListener = new CrashlyticsAnalyticsListener();
        if (subscribeToAnalyticsEvents(analyticsConnector, crashlyticsAnalyticsListener) != null) {
            Logger.getLogger().d("Registered Firebase Analytics listener.");
            BreadcrumbAnalyticsEventReceiver breadcrumbAnalyticsEventReceiver = new BreadcrumbAnalyticsEventReceiver();
            BlockingAnalyticsEventLogger blockingAnalyticsEventLogger = new BlockingAnalyticsEventLogger(crashlyticsOriginAnalyticsEventLogger, 500, TimeUnit.MILLISECONDS);
            synchronized (this) {
                try {
                    for (BreadcrumbHandler registerBreadcrumbHandler : this.breadcrumbHandlerList) {
                        breadcrumbAnalyticsEventReceiver.registerBreadcrumbHandler(registerBreadcrumbHandler);
                    }
                    crashlyticsAnalyticsListener.setBreadcrumbEventReceiver(breadcrumbAnalyticsEventReceiver);
                    crashlyticsAnalyticsListener.setCrashlyticsOriginEventReceiver(blockingAnalyticsEventLogger);
                    this.breadcrumbSource = breadcrumbAnalyticsEventReceiver;
                    this.analyticsEventLogger = blockingAnalyticsEventLogger;
                } catch (Throwable th) {
                    throw th;
                }
            }
            return;
        }
        Logger.getLogger().w("Could not register Firebase Analytics listener; a listener is already registered.");
    }

    @DeferredApi
    private static AnalyticsConnector.AnalyticsConnectorHandle subscribeToAnalyticsEvents(AnalyticsConnector analyticsConnector, CrashlyticsAnalyticsListener crashlyticsAnalyticsListener) {
        AnalyticsConnector.AnalyticsConnectorHandle registerAnalyticsConnectorListener = analyticsConnector.registerAnalyticsConnectorListener("clx", crashlyticsAnalyticsListener);
        if (registerAnalyticsConnectorListener == null) {
            Logger.getLogger().d("Could not register AnalyticsConnectorListener with Crashlytics origin.");
            registerAnalyticsConnectorListener = analyticsConnector.registerAnalyticsConnectorListener(AppMeasurement.CRASH_ORIGIN, crashlyticsAnalyticsListener);
            if (registerAnalyticsConnectorListener != null) {
                Logger.getLogger().w("A new version of the Google Analytics for Firebase SDK is now available. For improved performance and compatibility with Crashlytics, please update to the latest version.");
            }
        }
        return registerAnalyticsConnectorListener;
    }
}
