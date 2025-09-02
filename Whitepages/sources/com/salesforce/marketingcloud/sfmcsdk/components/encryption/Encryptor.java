package com.salesforce.marketingcloud.sfmcsdk.components.encryption;

import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

class Encryptor {
    private static final String AES_CBC_CIPHER = "AES/CBC/PKCS5Padding";
    private static final String AES_GCM_CIPHER = "AES/GCM/NoPadding";
    private static final String BOUNCY_CASTLE = "BC";
    private static final String MAC_TRANSFORMATION = "HmacSHA256";
    private static final String RSA_PKCS1 = "RSA/ECB/PKCS1Padding";
    private static final String SHA1PRNG = "SHA1PRNG";
    private static final String TAG = "Encryptor";

    Encryptor() {
    }

    private static Cipher getEncryptingCipher(byte[] bArr, byte[] bArr2) throws InvalidAlgorithmParameterException, InvalidKeyException {
        Cipher bestCipher = getBestCipher(AES_GCM_CIPHER);
        SecretKeySpec secretKeySpec = new SecretKeySpec(bArr, bestCipher.getAlgorithm());
        IvParameterSpec ivParameterSpec = new IvParameterSpec(bArr2);
        bestCipher.init(1, secretKeySpec, ivParameterSpec);
        bestCipher.updateAAD(ivParameterSpec.getIV());
        return bestCipher;
    }

    private static Cipher getDecryptingCipher(byte[] bArr, byte[] bArr2) throws InvalidAlgorithmParameterException, InvalidKeyException {
        Cipher bestCipher = getBestCipher(AES_GCM_CIPHER);
        SecretKeySpec secretKeySpec = new SecretKeySpec(bArr, bestCipher.getAlgorithm());
        IvParameterSpec ivParameterSpec = new IvParameterSpec(bArr2);
        bestCipher.init(2, secretKeySpec, ivParameterSpec);
        bestCipher.updateAAD(ivParameterSpec.getIV());
        return bestCipher;
    }

    private static Cipher getLegacyDecryptingCipher(byte[] bArr, byte[] bArr2) throws InvalidAlgorithmParameterException, InvalidKeyException {
        Cipher bestCipher = getBestCipher(AES_CBC_CIPHER);
        bestCipher.init(2, new SecretKeySpec(bArr, bestCipher.getAlgorithm()), new IvParameterSpec(bArr2));
        return bestCipher;
    }

    public static String legacyDecrypt(String str, String str2) {
        return decrypt(str, str2, new byte[16]);
    }

    public static String decrypt(String str, String str2) {
        return decrypt(str, str2, new byte[12]);
    }

    public static String legacyDecrypt(byte[] bArr, String str) {
        return decrypt(bArr, str, new byte[16]);
    }

    public static String decrypt(byte[] bArr, String str) {
        return decrypt(bArr, str, new byte[12]);
    }

    public static String decrypt(String str, String str2, byte[] bArr) {
        return (TextUtils.isEmpty(str2) || str == null) ? str : decrypt(str.getBytes(), str2, bArr);
    }

    public static String decrypt(byte[] bArr, String str, byte[] bArr2) {
        if (!TextUtils.isEmpty(str)) {
            try {
                byte[] decode = Base64.decode(str, 2);
                byte[] decode2 = Base64.decode(bArr, 2);
                byte[] decrypt = decrypt(decode2, decode2.length, decode, bArr2);
                return new String(decrypt, 0, decrypt.length, StandardCharsets.UTF_8);
            } catch (Exception e) {
                Log.w(TAG, "Error during decryption", e);
                return null;
            }
        } else if (bArr != null) {
            return new String(bArr, StandardCharsets.UTF_8);
        } else {
            return null;
        }
    }

    public static String encrypt(String str, String str2) {
        try {
            return encrypt(str, str2, generateInitVector());
        } catch (Exception e) {
            Log.w(TAG, "Error during encryption", e);
            return null;
        }
    }

    public static String encrypt(String str, String str2, byte[] bArr) {
        if (TextUtils.isEmpty(str2) || str == null) {
            return str;
        }
        byte[] encryptBytes = encryptBytes(str, str2, bArr);
        if (encryptBytes == null) {
            return null;
        }
        try {
            return new String(encryptBytes, StandardCharsets.US_ASCII);
        } catch (Exception e) {
            Log.w(TAG, "Error during encryption", e);
            return null;
        }
    }

    public static byte[] encryptBytes(String str, String str2) {
        try {
            return encryptBytes(str, str2, generateInitVector());
        } catch (Exception e) {
            Log.w(TAG, "Error during encryption", e);
            return null;
        }
    }

    public static byte[] encryptBytes(String str, String str2, byte[] bArr) {
        if (!TextUtils.isEmpty(str2)) {
            try {
                return Base64.encode(encrypt(str.getBytes(StandardCharsets.UTF_8), Base64.decode(str2, 2), bArr), 2);
            } catch (Exception e) {
                Log.w(TAG, "Error during encryption", e);
                return null;
            }
        } else if (str == null) {
            return null;
        } else {
            return str.getBytes();
        }
    }

    public static String encryptWithRSA(PublicKey publicKey, String str) {
        byte[] encryptWithRSABytes = encryptWithRSABytes(publicKey, str);
        if (encryptWithRSABytes != null) {
            return Base64.encodeToString(encryptWithRSABytes, 3);
        }
        return null;
    }

    public static byte[] encryptWithRSABytes(PublicKey publicKey, String str) {
        return encryptWithPublicKey(publicKey, str, RSA_PKCS1);
    }

    public static String decryptWithRSA(PrivateKey privateKey, String str) {
        byte[] decryptWithRSABytes = decryptWithRSABytes(privateKey, str);
        if (decryptWithRSABytes != null) {
            try {
                return new String(decryptWithRSABytes, 0, decryptWithRSABytes.length, StandardCharsets.UTF_8);
            } catch (Exception e) {
                Log.e(TAG, "Error during asymmetric decryption using RSA", e);
            }
        }
        return null;
    }

    public static byte[] decryptWithRSABytes(PrivateKey privateKey, String str) {
        return decryptWithPrivateKey(privateKey, str, RSA_PKCS1);
    }

    public static String decryptBytes(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        try {
            byte[] doFinal = getLegacyDecryptingCipher(bArr2, bArr3).doFinal(bArr, 0, bArr.length);
            return new String(doFinal, 0, doFinal.length, StandardCharsets.UTF_8);
        } catch (Exception e) {
            Log.e(TAG, "Error during symmetric decryption using AES", e);
            return null;
        }
    }

    private static byte[] encryptWithPublicKey(PublicKey publicKey, String str, String str2) {
        if (publicKey != null && !TextUtils.isEmpty(str)) {
            try {
                Cipher instance = Cipher.getInstance(str2);
                instance.init(1, publicKey);
                return instance.doFinal(str.getBytes());
            } catch (Exception e) {
                Log.e(TAG, "Error during asymmetric encryption", e);
            }
        }
        return null;
    }

    private static byte[] decryptWithPrivateKey(PrivateKey privateKey, String str, String str2) {
        if (privateKey != null && !TextUtils.isEmpty(str)) {
            try {
                Cipher instance = Cipher.getInstance(str2);
                instance.init(2, privateKey);
                return instance.doFinal(Base64.decode(str.getBytes(), 3));
            } catch (Exception e) {
                Log.e(TAG, "Error during asymmetric decryption", e);
            }
        }
        return null;
    }

    public static byte[] generateInitVector() throws NoSuchAlgorithmException {
        byte[] bArr = new byte[12];
        SecureRandom.getInstance(SHA1PRNG).nextBytes(bArr);
        return bArr;
    }

    private static byte[] encrypt(byte[] bArr, byte[] bArr2, byte[] bArr3) throws GeneralSecurityException {
        byte[] doFinal = getEncryptingCipher(bArr2, bArr3).doFinal(bArr);
        byte[] bArr4 = new byte[(bArr3.length + doFinal.length)];
        System.arraycopy(bArr3, 0, bArr4, 0, bArr3.length);
        System.arraycopy(doFinal, 0, bArr4, bArr3.length, doFinal.length);
        return bArr4;
    }

    private static byte[] decrypt(byte[] bArr, int i, byte[] bArr2, byte[] bArr3) throws GeneralSecurityException {
        Cipher cipher;
        System.arraycopy(bArr, 0, bArr3, 0, bArr3.length);
        int length = i - bArr3.length;
        byte[] bArr4 = new byte[length];
        System.arraycopy(bArr, bArr3.length, bArr4, 0, length);
        if (bArr3.length == 12) {
            cipher = getDecryptingCipher(bArr2, bArr3);
        } else {
            cipher = getLegacyDecryptingCipher(bArr2, bArr3);
        }
        return cipher.doFinal(bArr4, 0, length);
    }

    private static Cipher getBestCipher(String str) {
        try {
            if (AES_GCM_CIPHER.equals(str)) {
                return Cipher.getInstance(str);
            }
            return Cipher.getInstance(str, getLegacyEncryptionProvider());
        } catch (Exception e) {
            Log.e(TAG, "No cipher transformation available", e);
            return null;
        }
    }

    private static String getLegacyEncryptionProvider() {
        return BOUNCY_CASTLE;
    }
}
