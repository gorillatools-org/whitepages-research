package com.nimbusds.jose.util;

import java.util.Arrays;

abstract class Base64Codec {
    private static final char[] CA;
    private static final char[] CA_URL_SAFE = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-_".toCharArray();
    private static final int[] IA;
    private static final int[] IA_URL_SAFE = new int[256];

    static {
        char[] charArray = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".toCharArray();
        CA = charArray;
        int[] iArr = new int[256];
        IA = iArr;
        Arrays.fill(iArr, -1);
        int length = charArray.length;
        for (int i = 0; i < length; i++) {
            IA[CA[i]] = i;
        }
        IA[61] = 0;
        Arrays.fill(IA_URL_SAFE, -1);
        int length2 = CA_URL_SAFE.length;
        for (int i2 = 0; i2 < length2; i2++) {
            IA_URL_SAFE[CA_URL_SAFE[i2]] = i2;
        }
        IA_URL_SAFE[61] = 0;
    }

    public static String normalizeEncodedString(String str) {
        int length = str.length();
        int countIllegalChars = (length - countIllegalChars(str)) % 4;
        int i = countIllegalChars == 0 ? 0 : 4 - countIllegalChars;
        char[] cArr = new char[(length + i)];
        str.getChars(0, length, cArr, 0);
        for (int i2 = 0; i2 < i; i2++) {
            cArr[length + i2] = '=';
        }
        for (int i3 = 0; i3 < length; i3++) {
            char c = cArr[i3];
            if (c == '_') {
                cArr[i3] = '/';
            } else if (c == '-') {
                cArr[i3] = '+';
            }
        }
        return new String(cArr);
    }

    public static int countIllegalChars(String str) {
        int i = 0;
        for (int i2 = 0; i2 < str.length(); i2++) {
            char charAt = str.charAt(i2);
            if (IA[charAt] == -1 && IA_URL_SAFE[charAt] == -1) {
                i++;
            }
        }
        return i;
    }

    public static byte[] decode(String str) {
        if (str == null || str.isEmpty()) {
            return new byte[0];
        }
        String normalizeEncodedString = normalizeEncodedString(str);
        int length = normalizeEncodedString.length();
        int countIllegalChars = length - countIllegalChars(normalizeEncodedString);
        if (countIllegalChars % 4 != 0) {
            return new byte[0];
        }
        int i = 0;
        while (length > 1) {
            length--;
            if (IA[normalizeEncodedString.charAt(length)] > 0) {
                break;
            } else if (normalizeEncodedString.charAt(length) == '=') {
                i++;
            }
        }
        int i2 = ((countIllegalChars * 6) >> 3) - i;
        byte[] bArr = new byte[i2];
        int i3 = 0;
        int i4 = 0;
        while (i3 < i2) {
            int i5 = 0;
            int i6 = 0;
            while (i5 < 4) {
                int i7 = i4 + 1;
                int i8 = IA[normalizeEncodedString.charAt(i4)];
                if (i8 >= 0) {
                    i6 |= i8 << (18 - (i5 * 6));
                } else {
                    i5--;
                }
                i5++;
                i4 = i7;
            }
            int i9 = i3 + 1;
            bArr[i3] = (byte) (i6 >> 16);
            if (i9 < i2) {
                int i10 = i3 + 2;
                bArr[i9] = (byte) (i6 >> 8);
                if (i10 < i2) {
                    i3 += 3;
                    bArr[i10] = (byte) i6;
                } else {
                    i3 = i10;
                }
            } else {
                i3 = i9;
            }
        }
        return bArr;
    }
}
