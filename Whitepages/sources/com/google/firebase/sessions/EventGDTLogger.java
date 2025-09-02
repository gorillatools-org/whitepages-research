package com.google.firebase.sessions;

import android.util.Log;
import com.google.android.datatransport.Encoding;
import com.google.android.datatransport.Event;
import com.google.android.datatransport.TransportFactory;
import com.google.firebase.inject.Provider;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;

public final class EventGDTLogger implements EventGDTLoggerInterface {
    private static final String AQS_LOG_SOURCE = "FIREBASE_APPQUALITY_SESSION";
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final String TAG = "EventGDTLogger";
    private final Provider<TransportFactory> transportFactoryProvider;

    public EventGDTLogger(Provider<TransportFactory> provider) {
        Intrinsics.checkNotNullParameter(provider, "transportFactoryProvider");
        this.transportFactoryProvider = provider;
    }

    public void log(SessionEvent sessionEvent) {
        Intrinsics.checkNotNullParameter(sessionEvent, "sessionEvent");
        this.transportFactoryProvider.get().getTransport(AQS_LOG_SOURCE, SessionEvent.class, Encoding.of("json"), new EventGDTLogger$$ExternalSyntheticLambda0(this)).send(Event.ofData(sessionEvent));
    }

    /* access modifiers changed from: private */
    public final byte[] encode(SessionEvent sessionEvent) {
        String encode = SessionEvents.INSTANCE.getSESSION_EVENT_ENCODER$com_google_firebase_firebase_sessions().encode(sessionEvent);
        Intrinsics.checkNotNullExpressionValue(encode, "SessionEvents.SESSION_EVENT_ENCODER.encode(value)");
        Log.d(TAG, "Session Event Type: " + sessionEvent.getEventType().name());
        byte[] bytes = encode.getBytes(Charsets.UTF_8);
        Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
        return bytes;
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
