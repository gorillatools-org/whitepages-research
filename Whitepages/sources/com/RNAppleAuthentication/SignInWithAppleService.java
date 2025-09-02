package com.RNAppleAuthentication;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.RNAppleAuthentication.webview.SignInWebViewDialogFragment;
import com.google.firebase.remoteconfig.RemoteConfigConstants;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

public final class SignInWithAppleService {
    private final Function1 callback;
    private final SignInWithAppleConfiguration configuration;
    private final FragmentManager fragmentManager;
    private final String fragmentTag;

    public SignInWithAppleService(FragmentManager fragmentManager2, String str, SignInWithAppleConfiguration signInWithAppleConfiguration, Function1 function1) {
        Intrinsics.checkNotNullParameter(fragmentManager2, "fragmentManager");
        Intrinsics.checkNotNullParameter(str, "fragmentTag");
        Intrinsics.checkNotNullParameter(signInWithAppleConfiguration, "configuration");
        Intrinsics.checkNotNullParameter(function1, "callback");
        this.fragmentManager = fragmentManager2;
        this.fragmentTag = str;
        this.configuration = signInWithAppleConfiguration;
        this.callback = function1;
        Fragment findFragmentByTag = fragmentManager2.findFragmentByTag(str);
        SignInWebViewDialogFragment signInWebViewDialogFragment = findFragmentByTag instanceof SignInWebViewDialogFragment ? (SignInWebViewDialogFragment) findFragmentByTag : null;
        if (signInWebViewDialogFragment != null) {
            signInWebViewDialogFragment.configure(function1);
        }
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public SignInWithAppleService(FragmentManager fragmentManager2, String str, SignInWithAppleConfiguration signInWithAppleConfiguration, SignInWithAppleCallback signInWithAppleCallback) {
        this(fragmentManager2, str, signInWithAppleConfiguration, SignInWithAppleCallbackKt.toFunction(signInWithAppleCallback));
        Intrinsics.checkNotNullParameter(fragmentManager2, "fragmentManager");
        Intrinsics.checkNotNullParameter(str, "fragmentTag");
        Intrinsics.checkNotNullParameter(signInWithAppleConfiguration, "configuration");
        Intrinsics.checkNotNullParameter(signInWithAppleCallback, "callback");
    }

    public static final class AuthenticationAttempt implements Parcelable {
        public static final CREATOR CREATOR = new CREATOR((DefaultConstructorMarker) null);
        private final String authenticationUri;
        private final String redirectUri;
        private final String state;

        public int describeContents() {
            return 0;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof AuthenticationAttempt)) {
                return false;
            }
            AuthenticationAttempt authenticationAttempt = (AuthenticationAttempt) obj;
            return Intrinsics.areEqual((Object) this.authenticationUri, (Object) authenticationAttempt.authenticationUri) && Intrinsics.areEqual((Object) this.redirectUri, (Object) authenticationAttempt.redirectUri) && Intrinsics.areEqual((Object) this.state, (Object) authenticationAttempt.state);
        }

        public int hashCode() {
            return (((this.authenticationUri.hashCode() * 31) + this.redirectUri.hashCode()) * 31) + this.state.hashCode();
        }

        public String toString() {
            return "AuthenticationAttempt(authenticationUri=" + this.authenticationUri + ", redirectUri=" + this.redirectUri + ", state=" + this.state + ')';
        }

        public AuthenticationAttempt(String str, String str2, String str3) {
            Intrinsics.checkNotNullParameter(str, "authenticationUri");
            Intrinsics.checkNotNullParameter(str2, "redirectUri");
            Intrinsics.checkNotNullParameter(str3, RemoteConfigConstants.ResponseFieldKey.STATE);
            this.authenticationUri = str;
            this.redirectUri = str2;
            this.state = str3;
        }

        public final String getAuthenticationUri() {
            return this.authenticationUri;
        }

        public final String getRedirectUri() {
            return this.redirectUri;
        }

        public final String getState() {
            return this.state;
        }

        /* JADX WARNING: Illegal instructions before constructor call */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public AuthenticationAttempt(android.os.Parcel r4) {
            /*
                r3 = this;
                java.lang.String r0 = "parcel"
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r4, r0)
                java.lang.String r0 = r4.readString()
                java.lang.String r1 = "invalid"
                if (r0 != 0) goto L_0x000e
                r0 = r1
            L_0x000e:
                java.lang.String r2 = r4.readString()
                if (r2 != 0) goto L_0x0015
                r2 = r1
            L_0x0015:
                java.lang.String r4 = r4.readString()
                if (r4 != 0) goto L_0x001c
                goto L_0x001d
            L_0x001c:
                r1 = r4
            L_0x001d:
                r3.<init>(r0, r2, r1)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.RNAppleAuthentication.SignInWithAppleService.AuthenticationAttempt.<init>(android.os.Parcel):void");
        }

        public void writeToParcel(Parcel parcel, int i) {
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            parcel.writeString(this.authenticationUri);
            parcel.writeString(this.redirectUri);
            parcel.writeString(this.state);
        }

        public static final class CREATOR implements Parcelable.Creator {
            public /* synthetic */ CREATOR(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private CREATOR() {
            }

            public AuthenticationAttempt createFromParcel(Parcel parcel) {
                Intrinsics.checkNotNullParameter(parcel, "parcel");
                return new AuthenticationAttempt(parcel);
            }

            public AuthenticationAttempt[] newArray(int i) {
                return new AuthenticationAttempt[i];
            }

            public final AuthenticationAttempt create(SignInWithAppleConfiguration signInWithAppleConfiguration) {
                Intrinsics.checkNotNullParameter(signInWithAppleConfiguration, "configuration");
                Uri.Builder buildUpon = Uri.parse("https://appleid.apple.com/auth/authorize").buildUpon();
                buildUpon.appendQueryParameter("client_id", signInWithAppleConfiguration.getClientId());
                buildUpon.appendQueryParameter("redirect_uri", signInWithAppleConfiguration.getRedirectUri());
                buildUpon.appendQueryParameter("response_type", signInWithAppleConfiguration.getResponseType());
                buildUpon.appendQueryParameter("scope", signInWithAppleConfiguration.getScope());
                buildUpon.appendQueryParameter("response_mode", "form_post");
                buildUpon.appendQueryParameter(RemoteConfigConstants.ResponseFieldKey.STATE, signInWithAppleConfiguration.getState());
                if (!StringsKt.isBlank(signInWithAppleConfiguration.getNonce())) {
                    buildUpon.appendQueryParameter("nonce", signInWithAppleConfiguration.getNonce());
                }
                String uri = buildUpon.build().toString();
                Intrinsics.checkNotNullExpressionValue(uri, "toString(...)");
                return new AuthenticationAttempt(uri, signInWithAppleConfiguration.getRedirectUri(), signInWithAppleConfiguration.getState());
            }
        }
    }

    public final void show() {
        SignInWebViewDialogFragment newInstance = SignInWebViewDialogFragment.Companion.newInstance(AuthenticationAttempt.CREATOR.create(this.configuration));
        newInstance.configure(this.callback);
        newInstance.show(this.fragmentManager, this.fragmentTag);
    }
}
