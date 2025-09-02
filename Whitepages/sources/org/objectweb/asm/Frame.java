package org.objectweb.asm;

final class Frame {
    static final int[] a;
    Label b;
    int[] c;
    int[] d;
    private int[] e;
    private int[] f;
    private int g;
    private int h;
    private int[] i;

    static {
        _clinit_();
        int[] iArr = new int[202];
        for (int i2 = 0; i2 < 202; i2++) {
            iArr[i2] = "EFFFFFFFFGGFFFGGFFFEEFGFGFEEEEEEEEEEEEEEEEEEEEDEDEDDDDDCDCDEEEEEEEEEEEEEEEEEEEEBABABBBBDCFFFGGGEDCDCDCDCDCDCDCDCDCDCEEEEDDDDDDDCDCDCEFEFDDEEFFDEDEEEBDDBBDDDDDDCCCCCCCCEFEDDDCDCDEEEEEEEEEEFEEEEEEDDEEDDEE".charAt(i2) - 'E';
        }
        a = iArr;
    }

    Frame() {
    }

    static /* synthetic */ void _clinit_() {
    }

    private int a() {
        int i2 = this.g;
        if (i2 > 0) {
            int[] iArr = this.f;
            int i3 = i2 - 1;
            this.g = i3;
            return iArr[i3];
        }
        Label label = this.b;
        int i4 = label.f - 1;
        label.f = i4;
        return (-i4) | 50331648;
    }

    private int a(int i2) {
        int[] iArr = this.e;
        if (iArr == null || i2 >= iArr.length) {
            return i2 | 33554432;
        }
        int i3 = iArr[i2];
        if (i3 != 0) {
            return i3;
        }
        int i4 = i2 | 33554432;
        iArr[i2] = i4;
        return i4;
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x004d A[LOOP:0: B:8:0x0022->B:19:0x004d, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x004c A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private int a(org.objectweb.asm.ClassWriter r7, int r8) {
        /*
            r6 = this;
            r0 = 16777222(0x1000006, float:2.3509904E-38)
            r1 = 24117248(0x1700000, float:4.4081038E-38)
            if (r8 != r0) goto L_0x000f
            java.lang.String r0 = r7.I
        L_0x0009:
            int r7 = r7.c((java.lang.String) r0)
            r7 = r7 | r1
            goto L_0x0021
        L_0x000f:
            r0 = -1048576(0xfffffffffff00000, float:NaN)
            r0 = r0 & r8
            r2 = 25165824(0x1800000, float:4.7019774E-38)
            if (r0 != r2) goto L_0x0050
            org.objectweb.asm.Item[] r0 = r7.H
            r2 = 1048575(0xfffff, float:1.469367E-39)
            r2 = r2 & r8
            r0 = r0[r2]
            java.lang.String r0 = r0.g
            goto L_0x0009
        L_0x0021:
            r0 = 0
        L_0x0022:
            int r1 = r6.h
            if (r0 >= r1) goto L_0x0050
            int[] r1 = r6.i
            r1 = r1[r0]
            r2 = -268435456(0xfffffffff0000000, float:-1.58456325E29)
            r2 = r2 & r1
            r3 = 251658240(0xf000000, float:6.3108872E-30)
            r3 = r3 & r1
            r4 = 33554432(0x2000000, float:9.403955E-38)
            r5 = 8388607(0x7fffff, float:1.1754942E-38)
            if (r3 != r4) goto L_0x003e
            int[] r3 = r6.c
            r1 = r1 & r5
            r1 = r3[r1]
        L_0x003c:
            int r1 = r1 + r2
            goto L_0x004a
        L_0x003e:
            r4 = 50331648(0x3000000, float:3.761582E-37)
            if (r3 != r4) goto L_0x004a
            int[] r3 = r6.d
            int r4 = r3.length
            r1 = r1 & r5
            int r4 = r4 - r1
            r1 = r3[r4]
            goto L_0x003c
        L_0x004a:
            if (r8 != r1) goto L_0x004d
            return r7
        L_0x004d:
            int r0 = r0 + 1
            goto L_0x0022
        L_0x0050:
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: org.objectweb.asm.Frame.a(org.objectweb.asm.ClassWriter, int):int");
    }

    private void a(int i2, int i3) {
        if (this.e == null) {
            this.e = new int[10];
        }
        int length = this.e.length;
        if (i2 >= length) {
            int[] iArr = new int[Math.max(i2 + 1, length * 2)];
            System.arraycopy(this.e, 0, iArr, 0, length);
            this.e = iArr;
        }
        this.e[i2] = i3;
    }

    private void a(String str) {
        char charAt = str.charAt(0);
        if (charAt == '(') {
            c((Type.getArgumentsAndReturnSizes(str) >> 2) - 1);
        } else if (charAt == 'J' || charAt == 'D') {
            c(2);
        } else {
            c(1);
        }
    }

    private void a(ClassWriter classWriter, String str) {
        int b2 = b(classWriter, str);
        if (b2 != 0) {
            b(b2);
            if (b2 == 16777220 || b2 == 16777219) {
                b(16777216);
            }
        }
    }

    private static boolean a(ClassWriter classWriter, int i2, int[] iArr, int i3) {
        int min;
        int i4 = iArr[i3];
        if (i4 == i2) {
            return false;
        }
        if ((268435455 & i2) == 16777221) {
            if (i4 == 16777221) {
                return false;
            }
            i2 = 16777221;
        }
        if (i4 == 0) {
            iArr[i3] = i2;
            return true;
        }
        int i5 = i4 & 267386880;
        int i6 = 16777216;
        int i7 = -268435456;
        if (i5 == 24117248 || (i4 & -268435456) != 0) {
            if (i2 == 16777221) {
                return false;
            }
            if ((i2 & -1048576) != (-1048576 & i4)) {
                int i8 = i2 & 267386880;
                if (i8 == 24117248 || (i2 & -268435456) != 0) {
                    int i9 = i2 & -268435456;
                    int i10 = ((i9 == 0 || i8 == 24117248) ? 0 : -268435456) + i9;
                    int i11 = i4 & -268435456;
                    if (i11 == 0 || i5 == 24117248) {
                        i7 = 0;
                    }
                    min = Math.min(i10, i7 + i11);
                }
            } else if (i5 == 24117248) {
                i6 = (i2 & -268435456) | 24117248 | classWriter.a(i2 & 1048575, 1048575 & i4);
            } else {
                min = (i4 & -268435456) - 268435456;
            }
            i6 = min | 24117248 | classWriter.c("java/lang/Object");
        } else if (i4 == 16777221) {
            if ((i2 & 267386880) != 24117248 && (i2 & -268435456) == 0) {
                i2 = 16777216;
            }
            i6 = i2;
        }
        if (i4 == i6) {
            return false;
        }
        iArr[i3] = i6;
        return true;
    }

    private static int b(ClassWriter classWriter, String str) {
        int indexOf = str.charAt(0) == '(' ? str.indexOf(41) + 1 : 0;
        char charAt = str.charAt(indexOf);
        int i2 = 16777218;
        if (charAt == 'F') {
            return 16777218;
        }
        if (charAt == 'L') {
            return classWriter.c(str.substring(indexOf + 1, str.length() - 1)) | 24117248;
        }
        if (charAt != 'S') {
            if (charAt == 'V') {
                return 0;
            }
            if (!(charAt == 'Z' || charAt == 'I')) {
                if (charAt == 'J') {
                    return 16777220;
                }
                switch (charAt) {
                    case 'B':
                    case 'C':
                        break;
                    case 'D':
                        return 16777219;
                    default:
                        int i3 = indexOf + 1;
                        while (str.charAt(i3) == '[') {
                            i3++;
                        }
                        char charAt2 = str.charAt(i3);
                        if (charAt2 != 'F') {
                            if (charAt2 == 'S') {
                                i2 = 16777228;
                            } else if (charAt2 == 'Z') {
                                i2 = 16777225;
                            } else if (charAt2 == 'I') {
                                i2 = 16777217;
                            } else if (charAt2 != 'J') {
                                switch (charAt2) {
                                    case 'B':
                                        i2 = 16777226;
                                        break;
                                    case 'C':
                                        i2 = 16777227;
                                        break;
                                    case 'D':
                                        i2 = 16777219;
                                        break;
                                    default:
                                        i2 = classWriter.c(str.substring(i3 + 1, str.length() - 1)) | 24117248;
                                        break;
                                }
                            } else {
                                i2 = 16777220;
                            }
                        }
                        return ((i3 - indexOf) << 28) | i2;
                }
            }
        }
        return 16777217;
    }

    private void b(int i2) {
        if (this.f == null) {
            this.f = new int[10];
        }
        int length = this.f.length;
        int i3 = this.g;
        if (i3 >= length) {
            int[] iArr = new int[Math.max(i3 + 1, length * 2)];
            System.arraycopy(this.f, 0, iArr, 0, length);
            this.f = iArr;
        }
        int[] iArr2 = this.f;
        int i4 = this.g;
        int i5 = i4 + 1;
        this.g = i5;
        iArr2[i4] = i2;
        Label label = this.b;
        int i6 = label.f + i5;
        if (i6 > label.g) {
            label.g = i6;
        }
    }

    private void c(int i2) {
        int i3 = this.g;
        if (i3 >= i2) {
            this.g = i3 - i2;
            return;
        }
        this.b.f -= i2 - i3;
        this.g = 0;
    }

    private void d(int i2) {
        if (this.i == null) {
            this.i = new int[2];
        }
        int length = this.i.length;
        int i3 = this.h;
        if (i3 >= length) {
            int[] iArr = new int[Math.max(i3 + 1, length * 2)];
            System.arraycopy(this.i, 0, iArr, 0, length);
            this.i = iArr;
        }
        int[] iArr2 = this.i;
        int i4 = this.h;
        this.h = i4 + 1;
        iArr2[i4] = i2;
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:111:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:112:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:115:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:116:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:117:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:125:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:126:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x003d, code lost:
        a(r3, r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x004b, code lost:
        if (r1.charAt(0) == '[') goto L_0x003d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x004e, code lost:
        r1 = r3.c(r1) | 24117248;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0053, code lost:
        b(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0079, code lost:
        r1 = r1 | r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x00d0, code lost:
        r1 = r4.i;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x00f4, code lost:
        b(16777217);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x0106, code lost:
        b(16777219);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x0109, code lost:
        b(16777216);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x0111, code lost:
        b(16777218);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x0119, code lost:
        b(16777220);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x012a, code lost:
        c(2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:0x0133, code lost:
        c(2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:67:0x0161, code lost:
        b(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:68:0x0164, code lost:
        b(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:70:0x0178, code lost:
        b(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:72:0x0187, code lost:
        b(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:86:0x01da, code lost:
        a(r1, r2 | 8388608);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:87:0x01df, code lost:
        a(r1, 16777216);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:96:0x0208, code lost:
        c(2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:97:0x020d, code lost:
        c(2);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(int r17, int r18, org.objectweb.asm.ClassWriter r19, org.objectweb.asm.Item r20) {
        /*
            r16 = this;
            r0 = r16
            r1 = r17
            r2 = r18
            r3 = r19
            r4 = r20
            r5 = 198(0xc6, float:2.77E-43)
            r6 = 1
            if (r1 == r5) goto L_0x0236
            r5 = 199(0xc7, float:2.79E-43)
            if (r1 == r5) goto L_0x0236
            r5 = 16777218(0x1000002, float:2.3509893E-38)
            r7 = 24117248(0x1700000, float:4.4081038E-38)
            r8 = 16777219(0x1000003, float:2.3509895E-38)
            r9 = 16777217(0x1000001, float:2.350989E-38)
            r10 = 16777220(0x1000004, float:2.3509898E-38)
            r11 = 16777216(0x1000000, float:2.3509887E-38)
            switch(r1) {
                case 0: goto L_0x0239;
                case 1: goto L_0x0231;
                case 2: goto L_0x00f4;
                case 3: goto L_0x00f4;
                case 4: goto L_0x00f4;
                case 5: goto L_0x00f4;
                case 6: goto L_0x00f4;
                case 7: goto L_0x00f4;
                case 8: goto L_0x00f4;
                case 9: goto L_0x0119;
                case 10: goto L_0x0119;
                case 11: goto L_0x0111;
                case 12: goto L_0x0111;
                case 13: goto L_0x0111;
                case 14: goto L_0x0106;
                case 15: goto L_0x0106;
                case 16: goto L_0x00f4;
                case 17: goto L_0x00f4;
                case 18: goto L_0x0218;
                default: goto L_0x0026;
            }
        L_0x0026:
            switch(r1) {
                case 21: goto L_0x00f4;
                case 22: goto L_0x0119;
                case 23: goto L_0x0111;
                case 24: goto L_0x0106;
                case 25: goto L_0x0212;
                default: goto L_0x0029;
            }
        L_0x0029:
            r12 = 8388608(0x800000, float:1.17549435E-38)
            r13 = 251658240(0xf000000, float:6.3108872E-30)
            switch(r1) {
                case 46: goto L_0x0133;
                case 47: goto L_0x00f9;
                case 48: goto L_0x012a;
                case 49: goto L_0x0100;
                case 50: goto L_0x01fc;
                case 51: goto L_0x0133;
                case 52: goto L_0x0133;
                case 53: goto L_0x0133;
                case 54: goto L_0x01e3;
                case 55: goto L_0x01ba;
                case 56: goto L_0x01e3;
                case 57: goto L_0x01ba;
                case 58: goto L_0x01e3;
                default: goto L_0x0030;
            }
        L_0x0030:
            r12 = 3
            r13 = 91
            r15 = 0
            r14 = 4
            switch(r1) {
                case 79: goto L_0x01b5;
                case 80: goto L_0x01b0;
                case 81: goto L_0x01b5;
                case 82: goto L_0x01b0;
                case 83: goto L_0x01b5;
                case 84: goto L_0x01b5;
                case 85: goto L_0x01b5;
                case 86: goto L_0x01b5;
                case 87: goto L_0x0236;
                case 88: goto L_0x01aa;
                case 89: goto L_0x01a1;
                case 90: goto L_0x0198;
                case 91: goto L_0x018b;
                case 92: goto L_0x017c;
                case 93: goto L_0x0169;
                case 94: goto L_0x0148;
                case 95: goto L_0x0138;
                case 96: goto L_0x0133;
                case 97: goto L_0x012f;
                case 98: goto L_0x012a;
                case 99: goto L_0x0126;
                case 100: goto L_0x0133;
                case 101: goto L_0x012f;
                case 102: goto L_0x012a;
                case 103: goto L_0x0126;
                case 104: goto L_0x0133;
                case 105: goto L_0x012f;
                case 106: goto L_0x012a;
                case 107: goto L_0x0126;
                case 108: goto L_0x0133;
                case 109: goto L_0x012f;
                case 110: goto L_0x012a;
                case 111: goto L_0x0126;
                case 112: goto L_0x0133;
                case 113: goto L_0x012f;
                case 114: goto L_0x012a;
                case 115: goto L_0x0126;
                case 116: goto L_0x0239;
                case 117: goto L_0x0239;
                case 118: goto L_0x0239;
                case 119: goto L_0x0239;
                case 120: goto L_0x0133;
                case 121: goto L_0x0122;
                case 122: goto L_0x0133;
                case 123: goto L_0x0122;
                case 124: goto L_0x0133;
                case 125: goto L_0x0122;
                case 126: goto L_0x0133;
                case 127: goto L_0x012f;
                case 128: goto L_0x0133;
                case 129: goto L_0x012f;
                case 130: goto L_0x0133;
                case 131: goto L_0x012f;
                case 132: goto L_0x011d;
                case 133: goto L_0x0116;
                case 134: goto L_0x010e;
                case 135: goto L_0x0103;
                case 136: goto L_0x0133;
                case 137: goto L_0x012a;
                case 138: goto L_0x0100;
                case 139: goto L_0x00fc;
                case 140: goto L_0x0116;
                case 141: goto L_0x0103;
                case 142: goto L_0x0133;
                case 143: goto L_0x00f9;
                case 144: goto L_0x012a;
                case 145: goto L_0x0239;
                case 146: goto L_0x0239;
                case 147: goto L_0x0239;
                case 148: goto L_0x00f1;
                case 149: goto L_0x0133;
                case 150: goto L_0x0133;
                case 151: goto L_0x00f1;
                case 152: goto L_0x00f1;
                case 153: goto L_0x0236;
                case 154: goto L_0x0236;
                case 155: goto L_0x0236;
                case 156: goto L_0x0236;
                case 157: goto L_0x0236;
                case 158: goto L_0x0236;
                case 159: goto L_0x01aa;
                case 160: goto L_0x01aa;
                case 161: goto L_0x01aa;
                case 162: goto L_0x01aa;
                case 163: goto L_0x01aa;
                case 164: goto L_0x01aa;
                case 165: goto L_0x01aa;
                case 166: goto L_0x01aa;
                case 167: goto L_0x0239;
                case 168: goto L_0x00e9;
                case 169: goto L_0x00e9;
                case 170: goto L_0x0236;
                case 171: goto L_0x0236;
                case 172: goto L_0x0236;
                case 173: goto L_0x01aa;
                case 174: goto L_0x0236;
                case 175: goto L_0x01aa;
                case 176: goto L_0x0236;
                case 177: goto L_0x0239;
                case 178: goto L_0x00d0;
                case 179: goto L_0x00e2;
                case 180: goto L_0x00de;
                case 181: goto L_0x00d4;
                case 182: goto L_0x00b2;
                case 183: goto L_0x00b2;
                case 184: goto L_0x00b2;
                case 185: goto L_0x00b2;
                case 186: goto L_0x00aa;
                case 187: goto L_0x00a1;
                case 188: goto L_0x007b;
                case 189: goto L_0x0058;
                case 190: goto L_0x00fc;
                case 191: goto L_0x0236;
                case 192: goto L_0x0042;
                case 193: goto L_0x00fc;
                case 194: goto L_0x0236;
                case 195: goto L_0x0236;
                default: goto L_0x0038;
            }
        L_0x0038:
            r0.c(r2)
            java.lang.String r1 = r4.g
        L_0x003d:
            r0.a((org.objectweb.asm.ClassWriter) r3, (java.lang.String) r1)
            goto L_0x0239
        L_0x0042:
            java.lang.String r1 = r4.g
            r16.a()
            char r2 = r1.charAt(r15)
            if (r2 != r13) goto L_0x004e
            goto L_0x003d
        L_0x004e:
            int r1 = r3.c((java.lang.String) r1)
            r1 = r1 | r7
        L_0x0053:
            r0.b(r1)
            goto L_0x0239
        L_0x0058:
            java.lang.String r1 = r4.g
            r16.a()
            char r2 = r1.charAt(r15)
            if (r2 != r13) goto L_0x0073
            java.lang.StringBuffer r2 = new java.lang.StringBuffer
            r2.<init>()
            r2.append(r13)
            r2.append(r1)
            java.lang.String r1 = r2.toString()
            goto L_0x003d
        L_0x0073:
            r2 = 292552704(0x11700000, float:1.8932662E-28)
            int r1 = r3.c((java.lang.String) r1)
        L_0x0079:
            r1 = r1 | r2
            goto L_0x0053
        L_0x007b:
            r16.a()
            switch(r2) {
                case 4: goto L_0x009d;
                case 5: goto L_0x0099;
                case 6: goto L_0x0095;
                case 7: goto L_0x0091;
                case 8: goto L_0x008d;
                case 9: goto L_0x0089;
                case 10: goto L_0x0085;
                default: goto L_0x0081;
            }
        L_0x0081:
            r1 = 285212676(0x11000004, float:1.0097424E-28)
            goto L_0x0053
        L_0x0085:
            r1 = 285212673(0x11000001, float:1.0097421E-28)
            goto L_0x0053
        L_0x0089:
            r1 = 285212684(0x1100000c, float:1.0097434E-28)
            goto L_0x0053
        L_0x008d:
            r1 = 285212682(0x1100000a, float:1.0097432E-28)
            goto L_0x0053
        L_0x0091:
            r1 = 285212675(0x11000003, float:1.0097423E-28)
            goto L_0x0053
        L_0x0095:
            r1 = 285212674(0x11000002, float:1.0097422E-28)
            goto L_0x0053
        L_0x0099:
            r1 = 285212683(0x1100000b, float:1.0097433E-28)
            goto L_0x0053
        L_0x009d:
            r1 = 285212681(0x11000009, float:1.009743E-28)
            goto L_0x0053
        L_0x00a1:
            java.lang.String r1 = r4.g
            int r1 = r3.a((java.lang.String) r1, (int) r2)
            r2 = 25165824(0x1800000, float:4.7019774E-38)
            goto L_0x0079
        L_0x00aa:
            java.lang.String r1 = r4.h
            r0.a((java.lang.String) r1)
            java.lang.String r1 = r4.h
            goto L_0x003d
        L_0x00b2:
            java.lang.String r2 = r4.i
            r0.a((java.lang.String) r2)
            r2 = 184(0xb8, float:2.58E-43)
            if (r1 == r2) goto L_0x00d0
            int r2 = r16.a()
            r5 = 183(0xb7, float:2.56E-43)
            if (r1 != r5) goto L_0x00d0
            java.lang.String r1 = r4.h
            char r1 = r1.charAt(r15)
            r5 = 60
            if (r1 != r5) goto L_0x00d0
            r0.d(r2)
        L_0x00d0:
            java.lang.String r1 = r4.i
            goto L_0x003d
        L_0x00d4:
            java.lang.String r1 = r4.i
            r0.a((java.lang.String) r1)
            r16.a()
            goto L_0x0239
        L_0x00de:
            r0.c(r6)
            goto L_0x00d0
        L_0x00e2:
            java.lang.String r1 = r4.i
            r0.a((java.lang.String) r1)
            goto L_0x0239
        L_0x00e9:
            java.lang.RuntimeException r1 = new java.lang.RuntimeException
            java.lang.String r2 = "JSR/RET are not supported with computeFrames option"
            r1.<init>(r2)
            throw r1
        L_0x00f1:
            r0.c(r14)
        L_0x00f4:
            r0.b(r9)
            goto L_0x0239
        L_0x00f9:
            r1 = 2
            goto L_0x020d
        L_0x00fc:
            r0.c(r6)
            goto L_0x00f4
        L_0x0100:
            r1 = 2
            goto L_0x0208
        L_0x0103:
            r0.c(r6)
        L_0x0106:
            r0.b(r8)
        L_0x0109:
            r0.b(r11)
            goto L_0x0239
        L_0x010e:
            r0.c(r6)
        L_0x0111:
            r0.b(r5)
            goto L_0x0239
        L_0x0116:
            r0.c(r6)
        L_0x0119:
            r0.b(r10)
            goto L_0x0109
        L_0x011d:
            r0.a((int) r2, (int) r9)
            goto L_0x0239
        L_0x0122:
            r0.c(r12)
            goto L_0x0119
        L_0x0126:
            r0.c(r14)
            goto L_0x0106
        L_0x012a:
            r1 = 2
            r0.c(r1)
            goto L_0x0111
        L_0x012f:
            r0.c(r14)
            goto L_0x0119
        L_0x0133:
            r1 = 2
            r0.c(r1)
            goto L_0x00f4
        L_0x0138:
            int r1 = r16.a()
            int r2 = r16.a()
            r0.b(r1)
            r0.b(r2)
            goto L_0x0239
        L_0x0148:
            int r1 = r16.a()
            int r2 = r16.a()
            int r3 = r16.a()
            int r4 = r16.a()
            r0.b(r2)
            r0.b(r1)
            r0.b(r4)
        L_0x0161:
            r0.b(r3)
        L_0x0164:
            r0.b(r2)
            goto L_0x0053
        L_0x0169:
            int r1 = r16.a()
            int r2 = r16.a()
            int r3 = r16.a()
            r0.b(r2)
        L_0x0178:
            r0.b(r1)
            goto L_0x0161
        L_0x017c:
            int r1 = r16.a()
            int r2 = r16.a()
            r0.b(r2)
        L_0x0187:
            r0.b(r1)
            goto L_0x0164
        L_0x018b:
            int r1 = r16.a()
            int r2 = r16.a()
            int r3 = r16.a()
            goto L_0x0178
        L_0x0198:
            int r1 = r16.a()
            int r2 = r16.a()
            goto L_0x0187
        L_0x01a1:
            int r1 = r16.a()
            r0.b(r1)
            goto L_0x0053
        L_0x01aa:
            r1 = 2
            r0.c(r1)
            goto L_0x0239
        L_0x01b0:
            r0.c(r14)
            goto L_0x0239
        L_0x01b5:
            r0.c(r12)
            goto L_0x0239
        L_0x01ba:
            r0.c(r6)
            int r1 = r16.a()
            r0.a((int) r2, (int) r1)
            int r1 = r2 + 1
            r0.a((int) r1, (int) r11)
            if (r2 <= 0) goto L_0x0239
            int r1 = r2 + -1
            int r2 = r0.a((int) r1)
            if (r2 == r10) goto L_0x01df
            if (r2 != r8) goto L_0x01d6
            goto L_0x01df
        L_0x01d6:
            r3 = r2 & r13
            if (r3 == r11) goto L_0x0239
        L_0x01da:
            r2 = r2 | r12
            r0.a((int) r1, (int) r2)
            goto L_0x0239
        L_0x01df:
            r0.a((int) r1, (int) r11)
            goto L_0x0239
        L_0x01e3:
            int r1 = r16.a()
            r0.a((int) r2, (int) r1)
            if (r2 <= 0) goto L_0x0239
            int r1 = r2 + -1
            int r2 = r0.a((int) r1)
            if (r2 == r10) goto L_0x01df
            if (r2 != r8) goto L_0x01f7
            goto L_0x01df
        L_0x01f7:
            r3 = r2 & r13
            if (r3 == r11) goto L_0x0239
            goto L_0x01da
        L_0x01fc:
            r0.c(r6)
            int r1 = r16.a()
            r2 = -268435456(0xfffffffff0000000, float:-1.58456325E29)
            int r1 = r1 + r2
            goto L_0x0053
        L_0x0208:
            r0.c(r1)
            goto L_0x0106
        L_0x020d:
            r0.c(r1)
            goto L_0x0119
        L_0x0212:
            int r1 = r0.a((int) r2)
            goto L_0x0053
        L_0x0218:
            int r1 = r4.b
            r2 = 16
            if (r1 == r2) goto L_0x022d
            switch(r1) {
                case 3: goto L_0x00f4;
                case 4: goto L_0x0111;
                case 5: goto L_0x0119;
                case 6: goto L_0x0106;
                case 7: goto L_0x0229;
                case 8: goto L_0x0225;
                default: goto L_0x0221;
            }
        L_0x0221:
            java.lang.String r1 = "java/lang/invoke/MethodHandle"
            goto L_0x004e
        L_0x0225:
            java.lang.String r1 = "java/lang/String"
            goto L_0x004e
        L_0x0229:
            java.lang.String r1 = "java/lang/Class"
            goto L_0x004e
        L_0x022d:
            java.lang.String r1 = "java/lang/invoke/MethodType"
            goto L_0x004e
        L_0x0231:
            r1 = 16777221(0x1000005, float:2.35099E-38)
            goto L_0x0053
        L_0x0236:
            r0.c(r6)
        L_0x0239:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.objectweb.asm.Frame.a(int, int, org.objectweb.asm.ClassWriter, org.objectweb.asm.Item):void");
    }

    /* access modifiers changed from: package-private */
    public void a(ClassWriter classWriter, int i2, Type[] typeArr, int i3) {
        int i4;
        int i5;
        int[] iArr = new int[i3];
        this.c = iArr;
        this.d = new int[0];
        if ((i2 & 8) == 0) {
            i4 = 1;
            if ((i2 & 524288) == 0) {
                iArr[0] = classWriter.c(classWriter.I) | 24117248;
            } else {
                iArr[0] = 16777222;
            }
        } else {
            i4 = 0;
        }
        for (Type descriptor : typeArr) {
            int b2 = b(classWriter, descriptor.getDescriptor());
            int[] iArr2 = this.c;
            int i6 = i4 + 1;
            iArr2[i4] = b2;
            if (b2 == 16777220 || b2 == 16777219) {
                i5 = i4 + 2;
                iArr2[i6] = 16777216;
            } else {
                i5 = i6;
            }
        }
        while (i4 < i3) {
            this.c[i4] = 16777216;
            i4++;
        }
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:70:0x0107  */
    /* JADX WARNING: Removed duplicated region for block: B:82:0x010b A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean a(org.objectweb.asm.ClassWriter r19, org.objectweb.asm.Frame r20, int r21) {
        /*
            r18 = this;
            r0 = r18
            r1 = r19
            r2 = r20
            r3 = r21
            int[] r4 = r0.c
            int r4 = r4.length
            int[] r5 = r0.d
            int r5 = r5.length
            int[] r6 = r2.c
            if (r6 != 0) goto L_0x0018
            int[] r6 = new int[r4]
            r2.c = r6
            r6 = 1
            goto L_0x0019
        L_0x0018:
            r6 = 0
        L_0x0019:
            r9 = 0
        L_0x001a:
            r11 = 16777220(0x1000004, float:2.3509898E-38)
            r12 = 8388608(0x800000, float:1.17549435E-38)
            r13 = 33554432(0x2000000, float:9.403955E-38)
            r14 = 251658240(0xf000000, float:6.3108872E-30)
            r15 = -268435456(0xfffffffff0000000, float:-1.58456325E29)
            r16 = 8388607(0x7fffff, float:1.1754942E-38)
            r8 = 16777216(0x1000000, float:2.3509887E-38)
            if (r9 >= r4) goto L_0x0079
            int[] r7 = r0.e
            if (r7 == 0) goto L_0x0063
            int r10 = r7.length
            if (r9 >= r10) goto L_0x0063
            r7 = r7[r9]
            if (r7 != 0) goto L_0x003c
            int[] r7 = r0.c
            r8 = r7[r9]
            goto L_0x0067
        L_0x003c:
            r10 = r7 & r15
            r14 = r14 & r7
            if (r14 != r8) goto L_0x0043
            r8 = r7
            goto L_0x0067
        L_0x0043:
            if (r14 != r13) goto L_0x004d
            int[] r13 = r0.c
            r14 = r7 & r16
            r13 = r13[r14]
        L_0x004b:
            int r10 = r10 + r13
            goto L_0x0056
        L_0x004d:
            int[] r13 = r0.d
            r14 = r7 & r16
            int r14 = r5 - r14
            r13 = r13[r14]
            goto L_0x004b
        L_0x0056:
            r7 = r7 & r12
            if (r7 == 0) goto L_0x0061
            if (r10 == r11) goto L_0x0067
            r7 = 16777219(0x1000003, float:2.3509895E-38)
            if (r10 != r7) goto L_0x0061
            goto L_0x0067
        L_0x0061:
            r8 = r10
            goto L_0x0067
        L_0x0063:
            int[] r7 = r0.c
            r8 = r7[r9]
        L_0x0067:
            int[] r7 = r0.i
            if (r7 == 0) goto L_0x006f
            int r8 = r0.a((org.objectweb.asm.ClassWriter) r1, (int) r8)
        L_0x006f:
            int[] r7 = r2.c
            boolean r7 = a((org.objectweb.asm.ClassWriter) r1, (int) r8, (int[]) r7, (int) r9)
            r6 = r6 | r7
            int r9 = r9 + 1
            goto L_0x001a
        L_0x0079:
            if (r3 <= 0) goto L_0x00a1
            r5 = 0
        L_0x007c:
            if (r5 >= r4) goto L_0x008c
            int[] r7 = r0.c
            r7 = r7[r5]
            int[] r8 = r2.c
            boolean r7 = a((org.objectweb.asm.ClassWriter) r1, (int) r7, (int[]) r8, (int) r5)
            r6 = r6 | r7
            int r5 = r5 + 1
            goto L_0x007c
        L_0x008c:
            int[] r4 = r2.d
            if (r4 != 0) goto L_0x0097
            r4 = 1
            int[] r5 = new int[r4]
            r2.d = r5
            r7 = r4
            goto L_0x0098
        L_0x0097:
            r7 = r6
        L_0x0098:
            int[] r2 = r2.d
            r9 = 0
            boolean r1 = a((org.objectweb.asm.ClassWriter) r1, (int) r3, (int[]) r2, (int) r9)
            r1 = r1 | r7
            return r1
        L_0x00a1:
            r4 = 1
            r9 = 0
            int[] r3 = r0.d
            int r3 = r3.length
            org.objectweb.asm.Label r7 = r0.b
            int r7 = r7.f
            int r3 = r3 + r7
            int[] r7 = r2.d
            if (r7 != 0) goto L_0x00b8
            int r6 = r0.g
            int r6 = r6 + r3
            int[] r6 = new int[r6]
            r2.d = r6
            r7 = r4
            goto L_0x00b9
        L_0x00b8:
            r7 = r6
        L_0x00b9:
            r4 = r9
        L_0x00ba:
            if (r4 >= r3) goto L_0x00d2
            int[] r6 = r0.d
            r6 = r6[r4]
            int[] r10 = r0.i
            if (r10 == 0) goto L_0x00c8
            int r6 = r0.a((org.objectweb.asm.ClassWriter) r1, (int) r6)
        L_0x00c8:
            int[] r10 = r2.d
            boolean r6 = a((org.objectweb.asm.ClassWriter) r1, (int) r6, (int[]) r10, (int) r4)
            r7 = r7 | r6
            int r4 = r4 + 1
            goto L_0x00ba
        L_0x00d2:
            int r4 = r0.g
            if (r9 >= r4) goto L_0x0117
            int[] r4 = r0.f
            r4 = r4[r9]
            r6 = r4 & r15
            r10 = r4 & r14
            if (r10 != r8) goto L_0x00e5
            r6 = r4
        L_0x00e1:
            r4 = 16777219(0x1000003, float:2.3509895E-38)
            goto L_0x0103
        L_0x00e5:
            if (r10 != r13) goto L_0x00ef
            int[] r10 = r0.c
            r17 = r4 & r16
            r10 = r10[r17]
        L_0x00ed:
            int r6 = r6 + r10
            goto L_0x00f8
        L_0x00ef:
            int[] r10 = r0.d
            r17 = r4 & r16
            int r17 = r5 - r17
            r10 = r10[r17]
            goto L_0x00ed
        L_0x00f8:
            r4 = r4 & r12
            if (r4 == 0) goto L_0x00e1
            r4 = 16777219(0x1000003, float:2.3509895E-38)
            if (r6 == r11) goto L_0x0102
            if (r6 != r4) goto L_0x0103
        L_0x0102:
            r6 = r8
        L_0x0103:
            int[] r10 = r0.i
            if (r10 == 0) goto L_0x010b
            int r6 = r0.a((org.objectweb.asm.ClassWriter) r1, (int) r6)
        L_0x010b:
            int[] r10 = r2.d
            int r4 = r3 + r9
            boolean r4 = a((org.objectweb.asm.ClassWriter) r1, (int) r6, (int[]) r10, (int) r4)
            r7 = r7 | r4
            int r9 = r9 + 1
            goto L_0x00d2
        L_0x0117:
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: org.objectweb.asm.Frame.a(org.objectweb.asm.ClassWriter, org.objectweb.asm.Frame, int):boolean");
    }
}
