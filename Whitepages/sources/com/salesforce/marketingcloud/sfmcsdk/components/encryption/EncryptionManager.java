package com.salesforce.marketingcloud.sfmcsdk.components.encryption;

import android.content.Context;
import com.google.firebase.messaging.Constants;
import kotlin.jvm.internal.Intrinsics;

public final class EncryptionManager {
    private final String encryptionKey;

    public EncryptionManager(Context context, String str) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(str, "moduleApplicationId");
        String encryptionKey2 = new SalesforceKeyGenerator(context, new KeyStoreWrapper(context)).getEncryptionKey(str);
        Intrinsics.checkNotNullExpressionValue(encryptionKey2, "salesforceKeyGenerator.g…nKey(moduleApplicationId)");
        this.encryptionKey = encryptionKey2;
    }

    public final String getEncryptionKey$sfmcsdk_release() {
        return this.encryptionKey;
    }

    public final byte[] generateIV() {
        byte[] generateInitVector = Encryptor.generateInitVector();
        Intrinsics.checkNotNullExpressionValue(generateInitVector, "generateInitVector()");
        return generateInitVector;
    }

    public final String encrypt(String str) {
        Intrinsics.checkNotNullParameter(str, Constants.ScionAnalytics.MessageType.DATA_MESSAGE);
        return Encryptor.encrypt(str, this.encryptionKey);
    }

    public final String encrypt(String str, byte[] bArr) {
        Intrinsics.checkNotNullParameter(str, Constants.ScionAnalytics.MessageType.DATA_MESSAGE);
        Intrinsics.checkNotNullParameter(bArr, "iv");
        return Encryptor.encrypt(str, this.encryptionKey, bArr);
    }

    public final String decrypt(String str) {
        Intrinsics.checkNotNullParameter(str, Constants.ScionAnalytics.MessageType.DATA_MESSAGE);
        return Encryptor.decrypt(str, this.encryptionKey);
    }
}
