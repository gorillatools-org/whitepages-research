package com.facebook.appevents;

import com.facebook.AccessToken;
import com.facebook.FacebookSdk;
import com.facebook.internal.Utility;
import com.google.firebase.remoteconfig.RemoteConfigConstants;
import java.io.ObjectStreamException;
import java.io.Serializable;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class AccessTokenAppIdPair implements Serializable {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final long serialVersionUID = 1;
    private final String accessTokenString;
    private final String applicationId;

    public AccessTokenAppIdPair(String str, String str2) {
        Intrinsics.checkNotNullParameter(str2, "applicationId");
        this.applicationId = str2;
        this.accessTokenString = Utility.isNullOrEmpty(str) ? null : str;
    }

    public final String getApplicationId() {
        return this.applicationId;
    }

    public final String getAccessTokenString() {
        return this.accessTokenString;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public AccessTokenAppIdPair(AccessToken accessToken) {
        this(accessToken.getToken(), FacebookSdk.getApplicationId());
        Intrinsics.checkNotNullParameter(accessToken, "accessToken");
    }

    public int hashCode() {
        String str = this.accessTokenString;
        return (str != null ? str.hashCode() : 0) ^ this.applicationId.hashCode();
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof AccessTokenAppIdPair)) {
            return false;
        }
        AccessTokenAppIdPair accessTokenAppIdPair = (AccessTokenAppIdPair) obj;
        if (!Utility.areObjectsEqual(accessTokenAppIdPair.accessTokenString, this.accessTokenString) || !Utility.areObjectsEqual(accessTokenAppIdPair.applicationId, this.applicationId)) {
            return false;
        }
        return true;
    }

    public static final class SerializationProxyV1 implements Serializable {
        public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
        private static final long serialVersionUID = -2488473066578201069L;
        private final String accessTokenString;
        private final String appId;

        public SerializationProxyV1(String str, String str2) {
            Intrinsics.checkNotNullParameter(str2, RemoteConfigConstants.RequestFieldKey.APP_ID);
            this.accessTokenString = str;
            this.appId = str2;
        }

        private final Object readResolve() throws ObjectStreamException {
            return new AccessTokenAppIdPair(this.accessTokenString, this.appId);
        }

        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }
        }
    }

    private final Object writeReplace() throws ObjectStreamException {
        return new SerializationProxyV1(this.accessTokenString, this.applicationId);
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
