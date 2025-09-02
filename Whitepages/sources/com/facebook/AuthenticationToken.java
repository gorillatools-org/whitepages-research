package com.facebook;

import android.os.Parcel;
import android.os.Parcelable;
import com.facebook.internal.Validate;
import com.facebook.internal.security.OidcSecurityUtil;
import java.io.IOException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.util.List;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

public final class AuthenticationToken implements Parcelable {
    public static final Parcelable.Creator<AuthenticationToken> CREATOR = new AuthenticationToken$Companion$CREATOR$1();
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private final AuthenticationTokenClaims claims;
    private final String expectedNonce;
    private final AuthenticationTokenHeader header;
    private final String signature;
    private final String token;

    public int describeContents() {
        return 0;
    }

    public AuthenticationToken(String str, String str2) {
        Intrinsics.checkNotNullParameter(str, "token");
        Intrinsics.checkNotNullParameter(str2, "expectedNonce");
        Validate.notEmpty(str, "token");
        Validate.notEmpty(str2, "expectedNonce");
        List split$default = StringsKt.split$default((CharSequence) str, new String[]{"."}, false, 0, 6, (Object) null);
        if (split$default.size() == 3) {
            String str3 = (String) split$default.get(0);
            String str4 = (String) split$default.get(1);
            String str5 = (String) split$default.get(2);
            this.token = str;
            this.expectedNonce = str2;
            AuthenticationTokenHeader authenticationTokenHeader = new AuthenticationTokenHeader(str3);
            this.header = authenticationTokenHeader;
            this.claims = new AuthenticationTokenClaims(str4, str2);
            if (isValidSignature(str3, str4, str5, authenticationTokenHeader.getKid())) {
                this.signature = str5;
                return;
            }
            throw new IllegalArgumentException("Invalid Signature");
        }
        throw new IllegalArgumentException("Invalid IdToken string");
    }

    public AuthenticationToken(Parcel parcel) {
        Intrinsics.checkNotNullParameter(parcel, "parcel");
        this.token = Validate.notNullOrEmpty(parcel.readString(), "token");
        this.expectedNonce = Validate.notNullOrEmpty(parcel.readString(), "expectedNonce");
        Parcelable readParcelable = parcel.readParcelable(AuthenticationTokenHeader.class.getClassLoader());
        if (readParcelable != null) {
            this.header = (AuthenticationTokenHeader) readParcelable;
            Parcelable readParcelable2 = parcel.readParcelable(AuthenticationTokenClaims.class.getClassLoader());
            if (readParcelable2 != null) {
                this.claims = (AuthenticationTokenClaims) readParcelable2;
                this.signature = Validate.notNullOrEmpty(parcel.readString(), "signature");
                return;
            }
            throw new IllegalStateException("Required value was null.");
        }
        throw new IllegalStateException("Required value was null.");
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AuthenticationToken)) {
            return false;
        }
        AuthenticationToken authenticationToken = (AuthenticationToken) obj;
        if (!Intrinsics.areEqual((Object) this.token, (Object) authenticationToken.token) || !Intrinsics.areEqual((Object) this.expectedNonce, (Object) authenticationToken.expectedNonce) || !Intrinsics.areEqual((Object) this.header, (Object) authenticationToken.header) || !Intrinsics.areEqual((Object) this.claims, (Object) authenticationToken.claims) || !Intrinsics.areEqual((Object) this.signature, (Object) authenticationToken.signature)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return ((((((((527 + this.token.hashCode()) * 31) + this.expectedNonce.hashCode()) * 31) + this.header.hashCode()) * 31) + this.claims.hashCode()) * 31) + this.signature.hashCode();
    }

    public void writeToParcel(Parcel parcel, int i) {
        Intrinsics.checkNotNullParameter(parcel, "dest");
        parcel.writeString(this.token);
        parcel.writeString(this.expectedNonce);
        parcel.writeParcelable(this.header, i);
        parcel.writeParcelable(this.claims, i);
        parcel.writeString(this.signature);
    }

    private final boolean isValidSignature(String str, String str2, String str3, String str4) {
        try {
            String rawKeyFromEndPoint = OidcSecurityUtil.getRawKeyFromEndPoint(str4);
            if (rawKeyFromEndPoint == null) {
                return false;
            }
            PublicKey publicKeyFromString = OidcSecurityUtil.getPublicKeyFromString(rawKeyFromEndPoint);
            return OidcSecurityUtil.verify(publicKeyFromString, str + '.' + str2, str3);
        } catch (IOException | InvalidKeySpecException unused) {
            return false;
        }
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
