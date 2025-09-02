package com.google.firebase.sessions;

import com.google.firebase.Firebase;
import com.google.firebase.FirebaseKt;
import java.util.Locale;
import java.util.UUID;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

public final class SessionGenerator {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private SessionDetails currentSession;
    private final String firstSessionId;
    private int sessionIndex;
    private final TimeProvider timeProvider;
    private final Function0 uuidGenerator;

    public SessionGenerator(TimeProvider timeProvider2, Function0 function0) {
        Intrinsics.checkNotNullParameter(timeProvider2, "timeProvider");
        Intrinsics.checkNotNullParameter(function0, "uuidGenerator");
        this.timeProvider = timeProvider2;
        this.uuidGenerator = function0;
        this.firstSessionId = generateSessionId();
        this.sessionIndex = -1;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ SessionGenerator(TimeProvider timeProvider2, Function0 function0, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(timeProvider2, (i & 2) != 0 ? AnonymousClass1.INSTANCE : function0);
    }

    public final SessionDetails getCurrentSession() {
        SessionDetails sessionDetails = this.currentSession;
        if (sessionDetails != null) {
            return sessionDetails;
        }
        Intrinsics.throwUninitializedPropertyAccessException("currentSession");
        return null;
    }

    public final boolean getHasGenerateSession() {
        return this.currentSession != null;
    }

    public final SessionDetails generateNewSession() {
        int i = this.sessionIndex + 1;
        this.sessionIndex = i;
        this.currentSession = new SessionDetails(i == 0 ? this.firstSessionId : generateSessionId(), this.firstSessionId, this.sessionIndex, this.timeProvider.currentTimeUs());
        return getCurrentSession();
    }

    private final String generateSessionId() {
        String uuid = ((UUID) this.uuidGenerator.invoke()).toString();
        Intrinsics.checkNotNullExpressionValue(uuid, "uuidGenerator().toString()");
        String lowerCase = StringsKt.replace$default(uuid, "-", "", false, 4, (Object) null).toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        return lowerCase;
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final SessionGenerator getInstance() {
            return ((FirebaseSessionsComponent) FirebaseKt.getApp(Firebase.INSTANCE).get(FirebaseSessionsComponent.class)).getSessionGenerator();
        }
    }
}
