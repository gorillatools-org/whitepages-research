package com.salesforce.marketingcloud.messages;

import com.salesforce.marketingcloud.MCKeep;
import com.salesforce.marketingcloud.messages.geofence.GeofenceMessageResponse;
import com.salesforce.marketingcloud.messages.proximity.ProximityMessageResponse;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@MCKeep
public interface RegionMessageManager {
    public static final String BUNDLE_KEY_MESSAGING_ENABLED = "com.salesforce.marketingcloud.messaging.ENABLED";

    @MCKeep
    public interface GeofenceMessageResponseListener {
        void onGeofenceMessageResponse(GeofenceMessageResponse geofenceMessageResponse);
    }

    @MCKeep
    public interface ProximityMessageResponseListener {
        void onProximityMessageResponse(ProximityMessageResponse proximityMessageResponse);
    }

    @MCKeep
    public interface RegionTransitionEventListener {
        public static final int TRANSITION_ENTERED = 1;
        public static final int TRANSITION_EXITED = 2;

        @Retention(RetentionPolicy.SOURCE)
        public @interface a {
        }

        void onTransitionEvent(int i, Region region);
    }

    void disableGeofenceMessaging();

    void disableProximityMessaging();

    boolean enableGeofenceMessaging();

    boolean enableProximityMessaging();

    boolean isGeofenceMessagingEnabled();

    boolean isProximityMessagingEnabled();

    void registerGeofenceMessageResponseListener(GeofenceMessageResponseListener geofenceMessageResponseListener);

    void registerProximityMessageResponseListener(ProximityMessageResponseListener proximityMessageResponseListener);

    void registerRegionTransitionEventListener(RegionTransitionEventListener regionTransitionEventListener);

    void unregisterGeofenceMessageResponseListener(GeofenceMessageResponseListener geofenceMessageResponseListener);

    void unregisterProximityMessageResponseListener(ProximityMessageResponseListener proximityMessageResponseListener);

    void unregisterRegionTransitionEventListener(RegionTransitionEventListener regionTransitionEventListener);
}
