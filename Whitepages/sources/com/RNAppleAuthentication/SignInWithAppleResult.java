package com.RNAppleAuthentication;

import com.google.firebase.remoteconfig.RemoteConfigConstants;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public abstract class SignInWithAppleResult {
    public /* synthetic */ SignInWithAppleResult(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    public static final class Success extends SignInWithAppleResult {
        private final String code;
        private final String id_token;
        private final String state;
        private final String user;

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Success)) {
                return false;
            }
            Success success = (Success) obj;
            return Intrinsics.areEqual((Object) this.code, (Object) success.code) && Intrinsics.areEqual((Object) this.id_token, (Object) success.id_token) && Intrinsics.areEqual((Object) this.state, (Object) success.state) && Intrinsics.areEqual((Object) this.user, (Object) success.user);
        }

        public int hashCode() {
            return (((((this.code.hashCode() * 31) + this.id_token.hashCode()) * 31) + this.state.hashCode()) * 31) + this.user.hashCode();
        }

        public String toString() {
            return "Success(code=" + this.code + ", id_token=" + this.id_token + ", state=" + this.state + ", user=" + this.user + ')';
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public Success(String str, String str2, String str3, String str4) {
            super((DefaultConstructorMarker) null);
            Intrinsics.checkNotNullParameter(str, "code");
            Intrinsics.checkNotNullParameter(str2, "id_token");
            Intrinsics.checkNotNullParameter(str3, RemoteConfigConstants.ResponseFieldKey.STATE);
            Intrinsics.checkNotNullParameter(str4, "user");
            this.code = str;
            this.id_token = str2;
            this.state = str3;
            this.user = str4;
        }

        public final String getCode() {
            return this.code;
        }

        public final String getId_token() {
            return this.id_token;
        }

        public final String getState() {
            return this.state;
        }

        public final String getUser() {
            return this.user;
        }
    }

    private SignInWithAppleResult() {
    }

    public static final class Failure extends SignInWithAppleResult {
        private final Throwable error;

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof Failure) && Intrinsics.areEqual((Object) this.error, (Object) ((Failure) obj).error);
        }

        public int hashCode() {
            return this.error.hashCode();
        }

        public String toString() {
            return "Failure(error=" + this.error + ')';
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public Failure(Throwable th) {
            super((DefaultConstructorMarker) null);
            Intrinsics.checkNotNullParameter(th, "error");
            this.error = th;
        }

        public final Throwable getError() {
            return this.error;
        }
    }

    public static final class Cancel extends SignInWithAppleResult {
        public static final Cancel INSTANCE = new Cancel();

        private Cancel() {
            super((DefaultConstructorMarker) null);
        }
    }
}
