package com.google.zxing.datamatrix.encoder;

import com.google.zxing.Dimension;
import java.util.Arrays;

public abstract class HighLevelEncoder {
    static boolean isDigit(char c) {
        return c >= '0' && c <= '9';
    }

    static boolean isExtendedASCII(char c) {
        return c >= 128 && c <= 255;
    }

    private static boolean isNativeC40(char c) {
        if (c == ' ') {
            return true;
        }
        if (c < '0' || c > '9') {
            return c >= 'A' && c <= 'Z';
        }
        return true;
    }

    private static boolean isNativeEDIFACT(char c) {
        return c >= ' ' && c <= '^';
    }

    private static boolean isNativeText(char c) {
        if (c == ' ') {
            return true;
        }
        if (c < '0' || c > '9') {
            return c >= 'a' && c <= 'z';
        }
        return true;
    }

    private static boolean isSpecialB256(char c) {
        return false;
    }

    private static boolean isX12TermSep(char c) {
        return c == 13 || c == '*' || c == '>';
    }

    private static char randomize253State(char c, int i) {
        int i2 = c + ((i * 149) % 253) + 1;
        if (i2 > 254) {
            i2 -= 254;
        }
        return (char) i2;
    }

    public static String encodeHighLevel(String str, SymbolShapeHint symbolShapeHint, Dimension dimension, Dimension dimension2) {
        int i = 0;
        Encoder[] encoderArr = {new ASCIIEncoder(), new C40Encoder(), new TextEncoder(), new X12Encoder(), new EdifactEncoder(), new Base256Encoder()};
        EncoderContext encoderContext = new EncoderContext(str);
        encoderContext.setSymbolShape(symbolShapeHint);
        encoderContext.setSizeConstraints(dimension, dimension2);
        if (str.startsWith("[)>\u001e05\u001d") && str.endsWith("\u001e\u0004")) {
            encoderContext.writeCodeword(236);
            encoderContext.setSkipAtEnd(2);
            encoderContext.pos += 7;
        } else if (str.startsWith("[)>\u001e06\u001d") && str.endsWith("\u001e\u0004")) {
            encoderContext.writeCodeword(237);
            encoderContext.setSkipAtEnd(2);
            encoderContext.pos += 7;
        }
        while (encoderContext.hasMoreCharacters()) {
            encoderArr[i].encode(encoderContext);
            if (encoderContext.getNewEncoding() >= 0) {
                i = encoderContext.getNewEncoding();
                encoderContext.resetEncoderSignal();
            }
        }
        int codewordCount = encoderContext.getCodewordCount();
        encoderContext.updateSymbolInfo();
        int dataCapacity = encoderContext.getSymbolInfo().getDataCapacity();
        if (!(codewordCount >= dataCapacity || i == 0 || i == 5 || i == 4)) {
            encoderContext.writeCodeword(254);
        }
        StringBuilder codewords = encoderContext.getCodewords();
        if (codewords.length() < dataCapacity) {
            codewords.append(129);
        }
        while (codewords.length() < dataCapacity) {
            codewords.append(randomize253State(129, codewords.length() + 1));
        }
        return encoderContext.getCodewords().toString();
    }

    static int lookAheadTest(CharSequence charSequence, int i, int i2) {
        float[] fArr;
        int i3;
        int i4;
        CharSequence charSequence2 = charSequence;
        int i5 = i;
        if (i5 >= charSequence.length()) {
            return i2;
        }
        float f = 2.0f;
        int i6 = 6;
        int i7 = 4;
        int i8 = 3;
        if (i2 == 0) {
            fArr = new float[]{0.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.25f};
        } else {
            fArr = new float[]{1.0f, 2.0f, 2.0f, 2.0f, 2.0f, 2.25f};
            fArr[i2] = 0.0f;
        }
        int i9 = 0;
        while (true) {
            int i10 = i5 + i9;
            if (i10 == charSequence.length()) {
                byte[] bArr = new byte[i6];
                int[] iArr = new int[i6];
                int findMinimums = findMinimums(fArr, iArr, Integer.MAX_VALUE, bArr);
                int minimumCount = getMinimumCount(bArr);
                if (iArr[0] == findMinimums) {
                    return 0;
                }
                if (minimumCount == 1 && bArr[5] > 0) {
                    return 5;
                }
                if (minimumCount == 1 && bArr[i7] > 0) {
                    return i7;
                }
                if (minimumCount == 1 && bArr[2] > 0) {
                    return 2;
                }
                if (minimumCount != 1 || bArr[i8] <= 0) {
                    return 1;
                }
                return i8;
            }
            char charAt = charSequence2.charAt(i10);
            i9++;
            if (isDigit(charAt)) {
                fArr[0] = fArr[0] + 0.5f;
            } else if (isExtendedASCII(charAt)) {
                float ceil = (float) Math.ceil((double) fArr[0]);
                fArr[0] = ceil;
                fArr[0] = ceil + f;
            } else {
                float ceil2 = (float) Math.ceil((double) fArr[0]);
                fArr[0] = ceil2;
                fArr[0] = ceil2 + 1.0f;
            }
            if (isNativeC40(charAt)) {
                fArr[1] = fArr[1] + 0.6666667f;
            } else if (isExtendedASCII(charAt)) {
                fArr[1] = fArr[1] + 2.6666667f;
            } else {
                fArr[1] = fArr[1] + 1.3333334f;
            }
            if (isNativeText(charAt)) {
                fArr[2] = fArr[2] + 0.6666667f;
            } else if (isExtendedASCII(charAt)) {
                fArr[2] = fArr[2] + 2.6666667f;
            } else {
                fArr[2] = fArr[2] + 1.3333334f;
            }
            if (isNativeX12(charAt)) {
                fArr[3] = fArr[3] + 0.6666667f;
            } else if (isExtendedASCII(charAt)) {
                fArr[3] = fArr[3] + 4.3333335f;
            } else {
                fArr[3] = fArr[3] + 3.3333333f;
            }
            if (isNativeEDIFACT(charAt)) {
                fArr[4] = fArr[4] + 0.75f;
            } else if (isExtendedASCII(charAt)) {
                fArr[4] = fArr[4] + 4.25f;
            } else {
                fArr[4] = fArr[4] + 3.25f;
            }
            if (isSpecialB256(charAt)) {
                fArr[5] = fArr[5] + 4.0f;
            } else {
                fArr[5] = fArr[5] + 1.0f;
            }
            if (i9 >= 4) {
                int[] iArr2 = new int[i6];
                byte[] bArr2 = new byte[i6];
                findMinimums(fArr, iArr2, Integer.MAX_VALUE, bArr2);
                int minimumCount2 = getMinimumCount(bArr2);
                int i11 = iArr2[0];
                int i12 = iArr2[5];
                if (i11 >= i12 || i11 >= iArr2[1] || i11 >= iArr2[2] || i11 >= iArr2[3] || i11 >= iArr2[4]) {
                    if (i12 < i11) {
                        break;
                    }
                    byte b = bArr2[1];
                    byte b2 = bArr2[2];
                    byte b3 = bArr2[3];
                    byte b4 = bArr2[4];
                    if (b + b2 + b3 + b4 == 0) {
                        break;
                    } else if (minimumCount2 == 1 && b4 > 0) {
                        return 4;
                    } else {
                        if (minimumCount2 == 1 && b2 > 0) {
                            return 2;
                        }
                        if (minimumCount2 == 1 && b3 > 0) {
                            return 3;
                        }
                        int i13 = iArr2[1];
                        if (i13 + 1 >= i11 || i13 + 1 >= i12) {
                            i4 = 3;
                            i3 = 4;
                            i7 = i3;
                            i6 = 6;
                            i8 = i4;
                            f = 2.0f;
                        } else {
                            i3 = 4;
                            if (i13 + 1 < iArr2[4] && i13 + 1 < iArr2[2]) {
                                int i14 = iArr2[3];
                                if (i13 < i14) {
                                    return 1;
                                }
                                if (i13 == i14) {
                                    int i15 = i5 + i9 + 1;
                                    while (i15 < charSequence.length()) {
                                        char charAt2 = charSequence2.charAt(i15);
                                        if (!isX12TermSep(charAt2)) {
                                            if (!isNativeX12(charAt2)) {
                                                break;
                                            }
                                            i15++;
                                        } else {
                                            return 3;
                                        }
                                    }
                                    return 1;
                                }
                            }
                        }
                    }
                } else {
                    return 0;
                }
            } else {
                i3 = 4;
            }
            i4 = 3;
            i7 = i3;
            i6 = 6;
            i8 = i4;
            f = 2.0f;
        }
        return 5;
    }

    private static int findMinimums(float[] fArr, int[] iArr, int i, byte[] bArr) {
        Arrays.fill(bArr, (byte) 0);
        for (int i2 = 0; i2 < 6; i2++) {
            int ceil = (int) Math.ceil((double) fArr[i2]);
            iArr[i2] = ceil;
            if (i > ceil) {
                Arrays.fill(bArr, (byte) 0);
                i = ceil;
            }
            if (i == ceil) {
                bArr[i2] = (byte) (bArr[i2] + 1);
            }
        }
        return i;
    }

    private static int getMinimumCount(byte[] bArr) {
        int i = 0;
        for (int i2 = 0; i2 < 6; i2++) {
            i += bArr[i2];
        }
        return i;
    }

    private static boolean isNativeX12(char c) {
        if (isX12TermSep(c) || c == ' ') {
            return true;
        }
        if (c < '0' || c > '9') {
            return c >= 'A' && c <= 'Z';
        }
        return true;
    }

    public static int determineConsecutiveDigitCount(CharSequence charSequence, int i) {
        int length = charSequence.length();
        int i2 = 0;
        if (i < length) {
            char charAt = charSequence.charAt(i);
            while (isDigit(charAt) && i < length) {
                i2++;
                i++;
                if (i < length) {
                    charAt = charSequence.charAt(i);
                }
            }
        }
        return i2;
    }

    static void illegalCharacter(char c) {
        String hexString = Integer.toHexString(c);
        throw new IllegalArgumentException("Illegal character: " + c + " (0x" + ("0000".substring(0, 4 - hexString.length()) + hexString) + ')');
    }
}
