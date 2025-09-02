package com.facebook.internal.security;

import android.util.Base64;
import android.util.Log;
import com.facebook.FacebookSdk;
import com.google.firebase.crashlytics.internal.metadata.UserMetadata;
import com.google.firebase.messaging.Constants;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.X509EncodedKeySpec;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import kotlin.Unit;
import kotlin.io.TextStreamsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref$ObjectRef;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;
import org.json.JSONObject;

public final class OidcSecurityUtil {
    public static final OidcSecurityUtil INSTANCE = new OidcSecurityUtil();
    private static final String OPENID_KEYS_PATH = "/.well-known/oauth/openid/keys/";

    private OidcSecurityUtil() {
    }

    /* JADX INFO: finally extract failed */
    public static final String getRawKeyFromEndPoint(String str) {
        Intrinsics.checkNotNullParameter(str, "kid");
        URL url = new URL("https", "www." + FacebookSdk.getFacebookDomain(), OPENID_KEYS_PATH);
        ReentrantLock reentrantLock = new ReentrantLock();
        Condition newCondition = reentrantLock.newCondition();
        Ref$ObjectRef ref$ObjectRef = new Ref$ObjectRef();
        FacebookSdk.getExecutor().execute(new OidcSecurityUtil$$ExternalSyntheticLambda0(url, ref$ObjectRef, str, reentrantLock, newCondition));
        reentrantLock.lock();
        try {
            newCondition.await(5000, TimeUnit.MILLISECONDS);
            reentrantLock.unlock();
            return (String) ref$ObjectRef.element;
        } catch (Throwable th) {
            reentrantLock.unlock();
            throw th;
        }
    }

    /* access modifiers changed from: private */
    public static final void getRawKeyFromEndPoint$lambda$1(URL url, Ref$ObjectRef ref$ObjectRef, String str, ReentrantLock reentrantLock, Condition condition) {
        Intrinsics.checkNotNullParameter(url, "$openIdKeyUrl");
        Intrinsics.checkNotNullParameter(ref$ObjectRef, "$result");
        Intrinsics.checkNotNullParameter(str, "$kid");
        Intrinsics.checkNotNullParameter(reentrantLock, "$lock");
        URLConnection openConnection = url.openConnection();
        Intrinsics.checkNotNull(openConnection, "null cannot be cast to non-null type java.net.HttpURLConnection");
        HttpURLConnection httpURLConnection = (HttpURLConnection) openConnection;
        try {
            InputStream inputStream = httpURLConnection.getInputStream();
            Intrinsics.checkNotNullExpressionValue(inputStream, "connection.inputStream");
            String readText = TextStreamsKt.readText(new BufferedReader(new InputStreamReader(inputStream, Charsets.UTF_8), UserMetadata.MAX_INTERNAL_KEY_SIZE));
            httpURLConnection.getInputStream().close();
            ref$ObjectRef.element = new JSONObject(readText).optString(str);
            httpURLConnection.disconnect();
            reentrantLock.lock();
            try {
                condition.signal();
                Unit unit = Unit.INSTANCE;
                reentrantLock.unlock();
            } catch (Throwable th) {
                reentrantLock.unlock();
                throw th;
            }
        } catch (Exception e) {
            try {
                String name = INSTANCE.getClass().getName();
                String message = e.getMessage();
                if (message == null) {
                    message = "Error getting public key";
                }
                Log.d(name, message);
                httpURLConnection.disconnect();
                reentrantLock.lock();
                condition.signal();
                Unit unit2 = Unit.INSTANCE;
            } catch (Throwable th2) {
                httpURLConnection.disconnect();
                reentrantLock.lock();
                condition.signal();
                Unit unit3 = Unit.INSTANCE;
                throw th2;
            } finally {
                reentrantLock.unlock();
            }
        } catch (Throwable th3) {
            throw th3;
        }
    }

    public static final PublicKey getPublicKeyFromString(String str) {
        Intrinsics.checkNotNullParameter(str, "key");
        byte[] decode = Base64.decode(StringsKt.replace$default(StringsKt.replace$default(StringsKt.replace$default(str, ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE, "", false, 4, (Object) null), "-----BEGIN PUBLIC KEY-----", "", false, 4, (Object) null), "-----END PUBLIC KEY-----", "", false, 4, (Object) null), 0);
        Intrinsics.checkNotNullExpressionValue(decode, "decode(pubKeyString, Base64.DEFAULT)");
        PublicKey generatePublic = KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(decode));
        Intrinsics.checkNotNullExpressionValue(generatePublic, "kf.generatePublic(x509publicKey)");
        return generatePublic;
    }

    public static final boolean verify(PublicKey publicKey, String str, String str2) {
        Intrinsics.checkNotNullParameter(publicKey, "publicKey");
        Intrinsics.checkNotNullParameter(str, Constants.ScionAnalytics.MessageType.DATA_MESSAGE);
        Intrinsics.checkNotNullParameter(str2, "signature");
        try {
            Signature instance = Signature.getInstance("SHA256withRSA");
            instance.initVerify(publicKey);
            byte[] bytes = str.getBytes(Charsets.UTF_8);
            Intrinsics.checkNotNullExpressionValue(bytes, "this as java.lang.String).getBytes(charset)");
            instance.update(bytes);
            byte[] decode = Base64.decode(str2, 8);
            Intrinsics.checkNotNullExpressionValue(decode, "decode(signature, Base64.URL_SAFE)");
            return instance.verify(decode);
        } catch (Exception unused) {
            return false;
        }
    }
}
