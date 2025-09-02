package com.RNAppleAuthentication;

import com.google.android.gms.common.Scopes;
import com.google.firebase.remoteconfig.RemoteConfigConstants;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class SignInWithAppleConfiguration {
    private final String clientId;
    private final String nonce;
    private final String rawNonce;
    private final String redirectUri;
    private final String responseType;
    private final String scope;
    private final String state;

    public /* synthetic */ SignInWithAppleConfiguration(String str, String str2, String str3, String str4, String str5, String str6, String str7, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, str3, str4, str5, str6, str7);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SignInWithAppleConfiguration)) {
            return false;
        }
        SignInWithAppleConfiguration signInWithAppleConfiguration = (SignInWithAppleConfiguration) obj;
        return Intrinsics.areEqual((Object) this.clientId, (Object) signInWithAppleConfiguration.clientId) && Intrinsics.areEqual((Object) this.redirectUri, (Object) signInWithAppleConfiguration.redirectUri) && Intrinsics.areEqual((Object) this.scope, (Object) signInWithAppleConfiguration.scope) && Intrinsics.areEqual((Object) this.responseType, (Object) signInWithAppleConfiguration.responseType) && Intrinsics.areEqual((Object) this.state, (Object) signInWithAppleConfiguration.state) && Intrinsics.areEqual((Object) this.rawNonce, (Object) signInWithAppleConfiguration.rawNonce) && Intrinsics.areEqual((Object) this.nonce, (Object) signInWithAppleConfiguration.nonce);
    }

    public int hashCode() {
        return (((((((((((this.clientId.hashCode() * 31) + this.redirectUri.hashCode()) * 31) + this.scope.hashCode()) * 31) + this.responseType.hashCode()) * 31) + this.state.hashCode()) * 31) + this.rawNonce.hashCode()) * 31) + this.nonce.hashCode();
    }

    public String toString() {
        return "SignInWithAppleConfiguration(clientId=" + this.clientId + ", redirectUri=" + this.redirectUri + ", scope=" + this.scope + ", responseType=" + this.responseType + ", state=" + this.state + ", rawNonce=" + this.rawNonce + ", nonce=" + this.nonce + ')';
    }

    private SignInWithAppleConfiguration(String str, String str2, String str3, String str4, String str5, String str6, String str7) {
        this.clientId = str;
        this.redirectUri = str2;
        this.scope = str3;
        this.responseType = str4;
        this.state = str5;
        this.rawNonce = str6;
        this.nonce = str7;
    }

    public final String getClientId() {
        return this.clientId;
    }

    public final String getRedirectUri() {
        return this.redirectUri;
    }

    public final String getScope() {
        return this.scope;
    }

    public final String getResponseType() {
        return this.responseType;
    }

    public final String getState() {
        return this.state;
    }

    public final String getRawNonce() {
        return this.rawNonce;
    }

    public final String getNonce() {
        return this.nonce;
    }

    public static final class Builder {
        private String clientId;
        private String nonce;
        private String rawNonce;
        private String redirectUri;
        private String responseType;
        private String scope;
        private String state;

        public final Builder clientId(String str) {
            Intrinsics.checkNotNullParameter(str, "clientId");
            this.clientId = str;
            return this;
        }

        public final Builder redirectUri(String str) {
            Intrinsics.checkNotNullParameter(str, "redirectUri");
            this.redirectUri = str;
            return this;
        }

        public final Builder scope(Scope scope2) {
            Intrinsics.checkNotNullParameter(scope2, "scope");
            this.scope = scope2.signal();
            return this;
        }

        public final Builder responseType(ResponseType responseType2) {
            Intrinsics.checkNotNullParameter(responseType2, "type");
            this.responseType = responseType2.signal();
            return this;
        }

        public final Builder state(String str) {
            Intrinsics.checkNotNullParameter(str, RemoteConfigConstants.ResponseFieldKey.STATE);
            this.state = str;
            return this;
        }

        public final Builder rawNonce(String str) {
            Intrinsics.checkNotNullParameter(str, "rawNonce");
            this.rawNonce = str;
            return this;
        }

        public final Builder nonce(String str) {
            Intrinsics.checkNotNullParameter(str, "nonce");
            this.nonce = str;
            return this;
        }

        public final SignInWithAppleConfiguration build() {
            String str;
            String str2;
            String str3;
            String str4;
            String str5;
            String str6;
            String str7;
            String str8 = this.clientId;
            if (str8 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("clientId");
                str = null;
            } else {
                str = str8;
            }
            String str9 = this.redirectUri;
            if (str9 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("redirectUri");
                str2 = null;
            } else {
                str2 = str9;
            }
            String str10 = this.scope;
            if (str10 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("scope");
                str3 = null;
            } else {
                str3 = str10;
            }
            String str11 = this.responseType;
            if (str11 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("responseType");
                str4 = null;
            } else {
                str4 = str11;
            }
            String str12 = this.state;
            if (str12 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(RemoteConfigConstants.ResponseFieldKey.STATE);
                str5 = null;
            } else {
                str5 = str12;
            }
            String str13 = this.rawNonce;
            if (str13 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("rawNonce");
                str6 = null;
            } else {
                str6 = str13;
            }
            String str14 = this.nonce;
            if (str14 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("nonce");
                str7 = null;
            } else {
                str7 = str14;
            }
            return new SignInWithAppleConfiguration(str, str2, str3, str4, str5, str6, str7, (DefaultConstructorMarker) null);
        }
    }

    public enum ResponseType {
        ;

        public abstract String signal();

        static final class CODE extends ResponseType {
            CODE(String str, int i) {
                super(str, i, (DefaultConstructorMarker) null);
            }

            public String signal() {
                return "code";
            }
        }

        static {
            ResponseType[] $values;
            $ENTRIES = EnumEntriesKt.enumEntries($values);
        }

        static final class ID_TOKEN extends ResponseType {
            ID_TOKEN(String str, int i) {
                super(str, i, (DefaultConstructorMarker) null);
            }

            public String signal() {
                return "id_token";
            }
        }

        static final class ALL extends ResponseType {
            ALL(String str, int i) {
                super(str, i, (DefaultConstructorMarker) null);
            }

            public String signal() {
                return "code id_token";
            }
        }
    }

    public enum Scope {
        ;

        public abstract String signal();

        static final class NAME extends Scope {
            NAME(String str, int i) {
                super(str, i, (DefaultConstructorMarker) null);
            }

            public String signal() {
                return "name";
            }
        }

        static {
            Scope[] $values;
            $ENTRIES = EnumEntriesKt.enumEntries($values);
        }

        static final class EMAIL extends Scope {
            EMAIL(String str, int i) {
                super(str, i, (DefaultConstructorMarker) null);
            }

            public String signal() {
                return Scopes.EMAIL;
            }
        }

        static final class ALL extends Scope {
            ALL(String str, int i) {
                super(str, i, (DefaultConstructorMarker) null);
            }

            public String signal() {
                return "name email";
            }
        }
    }
}
