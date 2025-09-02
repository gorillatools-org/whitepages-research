package com.facebook.internal;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Build;
import com.google.firebase.remoteconfig.RemoteConfigConstants;
import java.util.HashSet;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

public final class FacebookSignatureValidator {
    public static final FacebookSignatureValidator INSTANCE = new FacebookSignatureValidator();
    private static final HashSet validAppSignatureHashes = SetsKt.hashSetOf("8a3c4b262d721acd49a4bf97d5213199c86fa2b9", "cc2751449a350f668590264ed76692694a80308a", "a4b7452e2ed8f5f191058ca7bbfd26b0d3214bfc", "df6b721c8b4d3b6eb44c861d4415007e5a35fc95", "9b8f518b086098de3d77736f9458a3d2f6f95a37", "2438bce1ddb7bd026d5ff89f598b3b5e5bb824b3", "c56fb7d591ba6704df047fd98f535372fea00211");

    private FacebookSignatureValidator() {
    }

    public static final boolean validateSignature(Context context, String str) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(str, RemoteConfigConstants.RequestFieldKey.PACKAGE_NAME);
        String str2 = Build.BRAND;
        int i = context.getApplicationInfo().flags;
        Intrinsics.checkNotNullExpressionValue(str2, "brand");
        if (StringsKt.startsWith$default(str2, "generic", false, 2, (Object) null) && (i & 2) != 0) {
            return true;
        }
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(str, 64);
            Signature[] signatureArr = packageInfo.signatures;
            if (signatureArr == null) {
                return false;
            }
            Intrinsics.checkNotNullExpressionValue(signatureArr, "packageInfo.signatures");
            if (signatureArr.length == 0) {
                return false;
            }
            Signature[] signatureArr2 = packageInfo.signatures;
            Intrinsics.checkNotNullExpressionValue(signatureArr2, "packageInfo.signatures");
            for (Signature byteArray : signatureArr2) {
                HashSet hashSet = validAppSignatureHashes;
                byte[] byteArray2 = byteArray.toByteArray();
                Intrinsics.checkNotNullExpressionValue(byteArray2, "it.toByteArray()");
                if (!CollectionsKt.contains(hashSet, Utility.sha1hash(byteArray2))) {
                    return false;
                }
            }
            return true;
        } catch (PackageManager.NameNotFoundException unused) {
            return false;
        }
    }
}
