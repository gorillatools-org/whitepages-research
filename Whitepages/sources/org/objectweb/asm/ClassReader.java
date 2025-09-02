package org.objectweb.asm;

public class ClassReader {
    private final int[] a;
    public final byte[] b;
    private final String[] c;
    private final int d;
    public final int header;

    public ClassReader(byte[] bArr) {
        this(bArr, 0, bArr.length);
    }

    public ClassReader(byte[] bArr, int i, int i2) {
        this.b = bArr;
        if (readShort(i + 6) <= 52) {
            int[] iArr = new int[readUnsignedShort(i + 8)];
            this.a = iArr;
            int length = iArr.length;
            this.c = new String[length];
            int i3 = i + 10;
            int i4 = 0;
            int i5 = 1;
            while (i5 < length) {
                int i6 = i3 + 1;
                this.a[i5] = i6;
                byte b2 = bArr[i3];
                int i7 = 3;
                if (b2 == 1) {
                    i7 = 3 + readUnsignedShort(i6);
                    if (i7 > i4) {
                        i4 = i7;
                    }
                } else if (b2 != 15) {
                    if (!(b2 == 18 || b2 == 3 || b2 == 4)) {
                        if (b2 != 5 && b2 != 6) {
                            switch (b2) {
                                case 9:
                                case 10:
                                case 11:
                                case 12:
                                    break;
                            }
                        } else {
                            i5++;
                            i7 = 9;
                        }
                    }
                    i7 = 5;
                } else {
                    i7 = 4;
                }
                i3 += i7;
                i5++;
            }
            this.d = i4;
            this.header = i3;
            return;
        }
        throw new IllegalArgumentException();
    }

    private int a() {
        int i = this.header;
        int readUnsignedShort = i + 8 + (readUnsignedShort(i + 6) * 2);
        for (int readUnsignedShort2 = readUnsignedShort(readUnsignedShort); readUnsignedShort2 > 0; readUnsignedShort2--) {
            for (int readUnsignedShort3 = readUnsignedShort(readUnsignedShort + 8); readUnsignedShort3 > 0; readUnsignedShort3--) {
                readUnsignedShort += readInt(readUnsignedShort + 12) + 6;
            }
            readUnsignedShort += 8;
        }
        int i2 = readUnsignedShort + 2;
        for (int readUnsignedShort4 = readUnsignedShort(i2); readUnsignedShort4 > 0; readUnsignedShort4--) {
            for (int readUnsignedShort5 = readUnsignedShort(i2 + 8); readUnsignedShort5 > 0; readUnsignedShort5--) {
                i2 += readInt(i2 + 12) + 6;
            }
            i2 += 8;
        }
        return i2 + 2;
    }

    private int a(int i, boolean z, boolean z2, Context context) {
        int i2;
        int i3;
        int i4;
        char[] cArr = context.c;
        Label[] labelArr = context.h;
        if (z) {
            int i5 = i + 1;
            i2 = this.b[i] & 255;
            i3 = i5;
        } else {
            context.o = -1;
            i3 = i;
            i2 = 255;
        }
        int i6 = 0;
        context.r = 0;
        if (i2 < 64) {
            context.p = 3;
            context.t = 0;
        } else if (i2 < 128) {
            i2 -= 64;
            i3 = a(context.u, 0, i3, cArr, labelArr);
            context.p = 4;
            context.t = 1;
        } else {
            int readUnsignedShort = readUnsignedShort(i3);
            int i7 = i3 + 2;
            if (i2 == 247) {
                i4 = a(context.u, 0, i7, cArr, labelArr);
                context.p = 4;
                context.t = 1;
            } else {
                if (i2 < 248 || i2 >= 251) {
                    if (i2 != 251) {
                        if (i2 >= 255) {
                            context.p = 0;
                            int readUnsignedShort2 = readUnsignedShort(i7);
                            int i8 = i3 + 4;
                            context.r = readUnsignedShort2;
                            context.q = readUnsignedShort2;
                            int i9 = 0;
                            while (readUnsignedShort2 > 0) {
                                i8 = a(context.s, i9, i8, cArr, labelArr);
                                readUnsignedShort2--;
                                i9++;
                            }
                            int readUnsignedShort3 = readUnsignedShort(i8);
                            int i10 = i8 + 2;
                            context.t = readUnsignedShort3;
                            while (true) {
                                int i11 = i6;
                                if (readUnsignedShort3 <= 0) {
                                    break;
                                }
                                i6 = i11 + 1;
                                i10 = a(context.u, i11, i4, cArr, labelArr);
                                readUnsignedShort3--;
                            }
                        } else {
                            int i12 = i2 - 251;
                            int i13 = z2 ? context.q : 0;
                            i4 = i7;
                            int i14 = i12;
                            while (i14 > 0) {
                                i4 = a(context.s, i13, i4, cArr, labelArr);
                                i14--;
                                i13++;
                            }
                            context.p = 1;
                            context.r = i12;
                            context.q += i12;
                            context.t = 0;
                        }
                    } else {
                        context.p = 3;
                    }
                } else {
                    context.p = 2;
                    int i15 = 251 - i2;
                    context.r = i15;
                    context.q -= i15;
                }
                context.t = 0;
                i4 = i7;
            }
            i2 = readUnsignedShort;
        }
        int i16 = context.o + i2 + 1;
        context.o = i16;
        readLabel(i16, labelArr);
        return i3;
    }

    private int a(int i, char[] cArr, String str, AnnotationVisitor annotationVisitor) {
        Object readConst;
        Object sh;
        int i2 = 0;
        if (annotationVisitor == null) {
            byte b2 = this.b[i] & 255;
            return b2 != 64 ? b2 != 91 ? b2 != 101 ? i + 3 : i + 5 : a(i + 1, cArr, false, (AnnotationVisitor) null) : a(i + 3, cArr, true, (AnnotationVisitor) null);
        }
        int i3 = i + 1;
        byte b3 = this.b[i] & 255;
        if (b3 == 64) {
            return a(i + 3, cArr, true, annotationVisitor.visitAnnotation(str, readUTF8(i3, cArr)));
        }
        if (b3 != 70) {
            if (b3 != 83) {
                if (b3 == 99) {
                    readConst = Type.getType(readUTF8(i3, cArr));
                } else if (b3 == 101) {
                    annotationVisitor.visitEnum(str, readUTF8(i3, cArr), readUTF8(i + 3, cArr));
                    return i + 5;
                } else if (b3 == 115) {
                    readConst = readUTF8(i3, cArr);
                } else if (!(b3 == 73 || b3 == 74)) {
                    if (b3 == 90) {
                        readConst = readInt(this.a[readUnsignedShort(i3)]) == 0 ? Boolean.FALSE : Boolean.TRUE;
                    } else if (b3 != 91) {
                        switch (b3) {
                            case 66:
                                sh = new Byte((byte) readInt(this.a[readUnsignedShort(i3)]));
                                break;
                            case 67:
                                sh = new Character((char) readInt(this.a[readUnsignedShort(i3)]));
                                break;
                            case 68:
                                break;
                            default:
                                return i3;
                        }
                    } else {
                        int readUnsignedShort = readUnsignedShort(i3);
                        int i4 = i + 3;
                        if (readUnsignedShort == 0) {
                            return a(i + 1, cArr, false, annotationVisitor.visitArray(str));
                        }
                        int i5 = i + 4;
                        byte b4 = this.b[i4] & 255;
                        if (b4 == 70) {
                            float[] fArr = new float[readUnsignedShort];
                            while (i2 < readUnsignedShort) {
                                fArr[i2] = Float.intBitsToFloat(readInt(this.a[readUnsignedShort(i5)]));
                                i5 += 3;
                                i2++;
                            }
                            annotationVisitor.visit(str, fArr);
                        } else if (b4 == 83) {
                            short[] sArr = new short[readUnsignedShort];
                            while (i2 < readUnsignedShort) {
                                sArr[i2] = (short) readInt(this.a[readUnsignedShort(i5)]);
                                i5 += 3;
                                i2++;
                            }
                            annotationVisitor.visit(str, sArr);
                        } else if (b4 == 90) {
                            boolean[] zArr = new boolean[readUnsignedShort];
                            for (int i6 = 0; i6 < readUnsignedShort; i6++) {
                                zArr[i6] = readInt(this.a[readUnsignedShort(i5)]) != 0;
                                i5 += 3;
                            }
                            annotationVisitor.visit(str, zArr);
                        } else if (b4 == 73) {
                            int[] iArr = new int[readUnsignedShort];
                            while (i2 < readUnsignedShort) {
                                iArr[i2] = readInt(this.a[readUnsignedShort(i5)]);
                                i5 += 3;
                                i2++;
                            }
                            annotationVisitor.visit(str, iArr);
                        } else if (b4 != 74) {
                            switch (b4) {
                                case 66:
                                    byte[] bArr = new byte[readUnsignedShort];
                                    while (i2 < readUnsignedShort) {
                                        bArr[i2] = (byte) readInt(this.a[readUnsignedShort(i5)]);
                                        i5 += 3;
                                        i2++;
                                    }
                                    annotationVisitor.visit(str, bArr);
                                    break;
                                case 67:
                                    char[] cArr2 = new char[readUnsignedShort];
                                    while (i2 < readUnsignedShort) {
                                        cArr2[i2] = (char) readInt(this.a[readUnsignedShort(i5)]);
                                        i5 += 3;
                                        i2++;
                                    }
                                    annotationVisitor.visit(str, cArr2);
                                    break;
                                case 68:
                                    double[] dArr = new double[readUnsignedShort];
                                    while (i2 < readUnsignedShort) {
                                        dArr[i2] = Double.longBitsToDouble(readLong(this.a[readUnsignedShort(i5)]));
                                        i5 += 3;
                                        i2++;
                                    }
                                    annotationVisitor.visit(str, dArr);
                                    break;
                                default:
                                    return a(i + 1, cArr, false, annotationVisitor.visitArray(str));
                            }
                        } else {
                            long[] jArr = new long[readUnsignedShort];
                            while (i2 < readUnsignedShort) {
                                jArr[i2] = readLong(this.a[readUnsignedShort(i5)]);
                                i5 += 3;
                                i2++;
                            }
                            annotationVisitor.visit(str, jArr);
                        }
                        return i5 - 1;
                    }
                }
                annotationVisitor.visit(str, readConst);
                return i + 3;
            }
            sh = new Short((short) readInt(this.a[readUnsignedShort(i3)]));
            annotationVisitor.visit(str, sh);
            return i + 3;
        }
        readConst = readConst(readUnsignedShort(i3), cArr);
        annotationVisitor.visit(str, readConst);
        return i + 3;
    }

    private int a(int i, char[] cArr, boolean z, AnnotationVisitor annotationVisitor) {
        int readUnsignedShort = readUnsignedShort(i);
        int i2 = i + 2;
        if (z) {
            while (readUnsignedShort > 0) {
                i2 = a(i2 + 2, cArr, readUTF8(i2, cArr), annotationVisitor);
                readUnsignedShort--;
            }
        } else {
            while (readUnsignedShort > 0) {
                i2 = a(i2, cArr, (String) null, annotationVisitor);
                readUnsignedShort--;
            }
        }
        if (annotationVisitor != null) {
            annotationVisitor.visitEnd();
        }
        return i2;
    }

    private int a(ClassVisitor classVisitor, Context context, int i) {
        int i2;
        Context context2 = context;
        int i3 = i;
        char[] cArr = context2.c;
        int readUnsignedShort = readUnsignedShort(i3);
        String readUTF8 = readUTF8(i3 + 2, cArr);
        String readUTF82 = readUTF8(i3 + 4, cArr);
        int i4 = i3 + 6;
        int i5 = i4;
        int i6 = readUnsignedShort;
        int readUnsignedShort2 = readUnsignedShort(i4);
        int i7 = 0;
        int i8 = 0;
        int i9 = 0;
        int i10 = 0;
        Attribute attribute = null;
        String str = null;
        Object obj = null;
        while (readUnsignedShort2 > 0) {
            String readUTF83 = readUTF8(i5 + 2, cArr);
            if ("ConstantValue".equals(readUTF83)) {
                int readUnsignedShort3 = readUnsignedShort(i5 + 8);
                obj = readUnsignedShort3 == 0 ? null : readConst(readUnsignedShort3, cArr);
            } else if ("Signature".equals(readUTF83)) {
                str = readUTF8(i5 + 8, cArr);
            } else {
                if ("Deprecated".equals(readUTF83)) {
                    i2 = 131072;
                } else if ("Synthetic".equals(readUTF83)) {
                    i2 = 266240;
                } else if ("RuntimeVisibleAnnotations".equals(readUTF83)) {
                    i10 = i5 + 8;
                } else if ("RuntimeVisibleTypeAnnotations".equals(readUTF83)) {
                    i8 = i5 + 8;
                } else if ("RuntimeInvisibleAnnotations".equals(readUTF83)) {
                    i9 = i5 + 8;
                } else if ("RuntimeInvisibleTypeAnnotations".equals(readUTF83)) {
                    i7 = i5 + 8;
                } else {
                    Attribute attribute2 = attribute;
                    int i11 = i7;
                    int i12 = i8;
                    int i13 = i9;
                    int i14 = i10;
                    attribute = a(context2.a, readUTF83, i5 + 8, readInt(i5 + 4), cArr, -1, (Label[]) null);
                    Attribute attribute3 = attribute2;
                    if (attribute != null) {
                        attribute.a = attribute3;
                        i9 = i13;
                    } else {
                        i9 = i13;
                        attribute = attribute3;
                    }
                    i10 = i14;
                    i7 = i11;
                    i8 = i12;
                }
                i6 |= i2;
            }
            i5 += readInt(i5 + 4) + 6;
            readUnsignedShort2--;
            context2 = context;
        }
        Attribute attribute4 = attribute;
        int i15 = i7;
        int i16 = i8;
        int i17 = i9;
        int i18 = i10;
        int i19 = i5 + 2;
        FieldVisitor visitField = classVisitor.visitField(i6, readUTF8, readUTF82, str, obj);
        if (visitField == null) {
            return i19;
        }
        if (i18 != 0) {
            int i20 = i18 + 2;
            for (int readUnsignedShort4 = readUnsignedShort(i18); readUnsignedShort4 > 0; readUnsignedShort4--) {
                i20 = a(i20 + 2, cArr, true, visitField.visitAnnotation(readUTF8(i20, cArr), true));
            }
        }
        if (i17 != 0) {
            int i21 = i17;
            int i22 = i21 + 2;
            for (int readUnsignedShort5 = readUnsignedShort(i21); readUnsignedShort5 > 0; readUnsignedShort5--) {
                i22 = a(i22 + 2, cArr, true, visitField.visitAnnotation(readUTF8(i22, cArr), false));
            }
        }
        int i23 = i16;
        if (i23 != 0) {
            int i24 = i23 + 2;
            for (int readUnsignedShort6 = readUnsignedShort(i23); readUnsignedShort6 > 0; readUnsignedShort6--) {
                Context context3 = context;
                int a2 = a(context3, i24);
                i24 = a(a2 + 2, cArr, true, visitField.visitTypeAnnotation(context3.i, context3.j, readUTF8(a2, cArr), true));
            }
        }
        Context context4 = context;
        int i25 = i15;
        if (i25 != 0) {
            int i26 = i25 + 2;
            for (int readUnsignedShort7 = readUnsignedShort(i25); readUnsignedShort7 > 0; readUnsignedShort7--) {
                int a3 = a(context4, i26);
                i26 = a(a3 + 2, cArr, true, visitField.visitTypeAnnotation(context4.i, context4.j, readUTF8(a3, cArr), false));
            }
        }
        while (attribute4 != null) {
            Attribute attribute5 = attribute4.a;
            attribute4.a = null;
            visitField.visitAttribute(attribute4);
            attribute4 = attribute5;
        }
        visitField.visitEnd();
        return i19;
    }

    /* JADX WARNING: Removed duplicated region for block: B:21:0x0082  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0084  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private int a(org.objectweb.asm.Context r9, int r10) {
        /*
            r8 = this;
            int r0 = r8.readInt(r10)
            int r1 = r0 >>> 24
            r2 = 1
            if (r1 == 0) goto L_0x0075
            if (r1 == r2) goto L_0x0075
            r3 = 64
            r4 = -16777216(0xffffffffff000000, float:-1.7014118E38)
            if (r1 == r3) goto L_0x002f
            r3 = 65
            if (r1 == r3) goto L_0x002f
            switch(r1) {
                case 19: goto L_0x002c;
                case 20: goto L_0x002c;
                case 21: goto L_0x002c;
                case 22: goto L_0x0075;
                default: goto L_0x0018;
            }
        L_0x0018:
            switch(r1) {
                case 71: goto L_0x0025;
                case 72: goto L_0x0025;
                case 73: goto L_0x0025;
                case 74: goto L_0x0025;
                case 75: goto L_0x0025;
                default: goto L_0x001b;
            }
        L_0x001b:
            r3 = 67
            if (r1 >= r3) goto L_0x0021
            r4 = -256(0xffffffffffffff00, float:NaN)
        L_0x0021:
            r0 = r0 & r4
            int r10 = r10 + 3
            goto L_0x007a
        L_0x0025:
            r1 = -16776961(0xffffffffff0000ff, float:-1.7014636E38)
            r0 = r0 & r1
            int r10 = r10 + 4
            goto L_0x007a
        L_0x002c:
            r0 = r0 & r4
            int r10 = r10 + r2
            goto L_0x007a
        L_0x002f:
            r0 = r0 & r4
            int r1 = r10 + 1
            int r1 = r8.readUnsignedShort(r1)
            org.objectweb.asm.Label[] r3 = new org.objectweb.asm.Label[r1]
            r9.l = r3
            org.objectweb.asm.Label[] r3 = new org.objectweb.asm.Label[r1]
            r9.m = r3
            int[] r3 = new int[r1]
            r9.f46n = r3
            int r10 = r10 + 3
            r3 = 0
        L_0x0045:
            if (r3 >= r1) goto L_0x007a
            int r4 = r8.readUnsignedShort(r10)
            int r5 = r10 + 2
            int r5 = r8.readUnsignedShort(r5)
            org.objectweb.asm.Label[] r6 = r9.l
            org.objectweb.asm.Label[] r7 = r9.h
            org.objectweb.asm.Label r7 = r8.readLabel(r4, r7)
            r6[r3] = r7
            org.objectweb.asm.Label[] r6 = r9.m
            int r4 = r4 + r5
            org.objectweb.asm.Label[] r5 = r9.h
            org.objectweb.asm.Label r4 = r8.readLabel(r4, r5)
            r6[r3] = r4
            int[] r4 = r9.f46n
            int r5 = r10 + 4
            int r5 = r8.readUnsignedShort(r5)
            r4[r3] = r5
            int r10 = r10 + 6
            int r3 = r3 + 1
            goto L_0x0045
        L_0x0075:
            r1 = -65536(0xffffffffffff0000, float:NaN)
            r0 = r0 & r1
            int r10 = r10 + 2
        L_0x007a:
            int r1 = r8.readByte(r10)
            r9.i = r0
            if (r1 != 0) goto L_0x0084
            r0 = 0
            goto L_0x008b
        L_0x0084:
            org.objectweb.asm.TypePath r0 = new org.objectweb.asm.TypePath
            byte[] r3 = r8.b
            r0.<init>(r3, r10)
        L_0x008b:
            r9.j = r0
            int r10 = r10 + r2
            int r1 = r1 * 2
            int r10 = r10 + r1
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: org.objectweb.asm.ClassReader.a(org.objectweb.asm.Context, int):int");
    }

    private int a(Object[] objArr, int i, int i2, char[] cArr, Label[] labelArr) {
        int i3 = i2 + 1;
        switch (this.b[i2] & 255) {
            case 0:
                objArr[i] = Opcodes.TOP;
                return i3;
            case 1:
                objArr[i] = Opcodes.INTEGER;
                return i3;
            case 2:
                objArr[i] = Opcodes.FLOAT;
                return i3;
            case 3:
                objArr[i] = Opcodes.DOUBLE;
                return i3;
            case 4:
                objArr[i] = Opcodes.LONG;
                return i3;
            case 5:
                objArr[i] = Opcodes.NULL;
                return i3;
            case 6:
                objArr[i] = Opcodes.UNINITIALIZED_THIS;
                return i3;
            case 7:
                objArr[i] = readClass(i3, cArr);
                break;
            default:
                objArr[i] = readLabel(readUnsignedShort(i3), labelArr);
                break;
        }
        return i2 + 3;
    }

    private String a(int i, int i2, char[] cArr) {
        byte b2;
        int i3 = i2 + i;
        byte[] bArr = this.b;
        int i4 = 0;
        boolean z = false;
        char c2 = 0;
        while (i < i3) {
            int i5 = i + 1;
            byte b3 = bArr[i];
            if (z) {
                if (z) {
                    cArr[i4] = (char) ((b3 & 63) | (c2 << 6));
                    i4++;
                    z = false;
                } else if (z) {
                    b2 = (b3 & 63) | (c2 << 6);
                }
                i = i5;
            } else {
                byte b4 = b3 & 255;
                if (b4 < 128) {
                    cArr[i4] = (char) b4;
                    i4++;
                } else if (b4 >= 224 || b4 <= 191) {
                    c2 = (char) (b3 & 15);
                    z = true;
                } else {
                    b2 = b3 & 31;
                }
                i = i5;
            }
            c2 = (char) b2;
            z = true;
            i = i5;
        }
        return new String(cArr, 0, i4);
    }

    private Attribute a(Attribute[] attributeArr, String str, int i, int i2, char[] cArr, int i3, Label[] labelArr) {
        Attribute[] attributeArr2 = attributeArr;
        String str2 = str;
        for (int i4 = 0; i4 < attributeArr2.length; i4++) {
            if (attributeArr2[i4].type.equals(str)) {
                return attributeArr2[i4].read(this, i, i2, cArr, i3, labelArr);
            }
        }
        return new Attribute(str).read(this, i, i2, (char[]) null, -1, (Label[]) null);
    }

    private void a(Context context) {
        int i;
        int i2;
        String str = context.g;
        Object[] objArr = context.s;
        int i3 = 0;
        if ((context.e & 8) == 0) {
            if ("<init>".equals(context.f)) {
                objArr[0] = Opcodes.UNINITIALIZED_THIS;
            } else {
                objArr[0] = readClass(this.header + 2, context.c);
            }
            i3 = 1;
        }
        int i4 = 1;
        while (true) {
            int i5 = i4 + 1;
            char charAt = str.charAt(i4);
            if (charAt == 'F') {
                i2 = i + 1;
                objArr[i] = Opcodes.FLOAT;
            } else if (charAt != 'L') {
                if (!(charAt == 'S' || charAt == 'I')) {
                    if (charAt == 'J') {
                        i2 = i + 1;
                        objArr[i] = Opcodes.LONG;
                    } else if (charAt != 'Z') {
                        if (charAt != '[') {
                            switch (charAt) {
                                case 'B':
                                case 'C':
                                    break;
                                case 'D':
                                    i2 = i + 1;
                                    objArr[i] = Opcodes.DOUBLE;
                                    break;
                                default:
                                    context.q = i;
                                    return;
                            }
                        } else {
                            while (str.charAt(i5) == '[') {
                                i5++;
                            }
                            if (str.charAt(i5) == 'L') {
                                do {
                                    i5++;
                                } while (str.charAt(i5) != ';');
                            }
                            int i6 = i5 + 1;
                            objArr[i] = str.substring(i4, i6);
                            i4 = i6;
                            i++;
                        }
                    }
                }
                i2 = i + 1;
                objArr[i] = Opcodes.INTEGER;
            } else {
                int i7 = i5;
                while (str.charAt(i7) != ';') {
                    i7++;
                }
                objArr[i] = str.substring(i5, i7);
                i++;
                i4 = i7 + 1;
            }
            i = i2;
            i4 = i5;
        }
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:171:0x04ed, code lost:
        r46 = r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:172:0x04ef, code lost:
        r36 = r11;
        r0 = r17;
        r2 = r29;
        r11 = r38;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:177:0x051c, code lost:
        r0 = r17;
        r2 = r29;
        r11 = r38;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:188:0x05b1, code lost:
        r1 = r32 + 3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x00ad, code lost:
        r0 = r0 + 5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:192:0x05e9, code lost:
        r1 = r32 + 5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:209:0x06a1, code lost:
        r1 = r32 + 3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x00bb, code lost:
        r0 = r0 + 3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:215:0x06d7, code lost:
        r1 = r32 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:217:0x06eb, code lost:
        r1 = r32 + 2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:221:0x071a, code lost:
        if (r11 == null) goto L_0x0758;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:223:0x071d, code lost:
        if (r0 >= r11.length) goto L_0x0758;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:224:0x071f, code lost:
        if (r2 > r8) goto L_0x0758;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:225:0x0721, code lost:
        if (r2 != r8) goto L_0x073b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:226:0x0723, code lost:
        r2 = a(r14, r11[r0]);
        a(r2 + 2, r13, true, r10.visitInsnAnnotation(r14.i, r14.j, readUTF8(r2, r13), true));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:227:0x073b, code lost:
        r0 = r0 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:228:0x073e, code lost:
        if (r0 >= r11.length) goto L_0x0755;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:230:0x0748, code lost:
        if (readByte(r11[r0]) >= 67) goto L_0x074b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:231:0x074b, code lost:
        r2 = readUnsignedShort(r11[r0] + 1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:232:0x0755, code lost:
        r2 = r33;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:233:0x0758, code lost:
        r3 = r19;
        r4 = r30;
        r6 = r37;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:234:0x075e, code lost:
        if (r6 == null) goto L_0x07b7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:236:0x0761, code lost:
        if (r3 >= r6.length) goto L_0x07b7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:237:0x0763, code lost:
        if (r4 > r8) goto L_0x07b7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:238:0x0765, code lost:
        if (r4 != r8) goto L_0x0787;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:239:0x0767, code lost:
        r4 = a(r14, r6[r3]);
        r17 = r0;
        r19 = r1;
        r29 = r8;
        a(r4 + 2, r13, true, r10.visitInsnAnnotation(r14.i, r14.j, readUTF8(r4, r13), false));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:240:0x0787, code lost:
        r17 = r0;
        r19 = r1;
        r29 = r8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:241:0x078e, code lost:
        r3 = r3 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:242:0x0791, code lost:
        if (r3 >= r6.length) goto L_0x07ab;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:244:0x079b, code lost:
        if (readByte(r6[r3]) >= 67) goto L_0x07a0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:246:0x07a0, code lost:
        r4 = readUnsignedShort(r6[r3] + 1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:248:0x07ae, code lost:
        r4 = r33;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:249:0x07b0, code lost:
        r0 = r17;
        r1 = r19;
        r8 = r29;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void a(org.objectweb.asm.MethodVisitor r44, org.objectweb.asm.Context r45, int r46) {
        /*
            r43 = this;
            r7 = r43
            r15 = r44
            r14 = r45
            r8 = r46
            byte[] r9 = r7.b
            char[] r13 = r14.c
            int r12 = r7.readUnsignedShort(r8)
            int r0 = r8 + 2
            int r11 = r7.readUnsignedShort(r0)
            int r0 = r8 + 4
            int r10 = r7.readInt(r0)
            int r16 = r8 + 8
            int r6 = r16 + r10
            int r0 = r10 + 2
            org.objectweb.asm.Label[] r5 = new org.objectweb.asm.Label[r0]
            r14.h = r5
            int r0 = r10 + 1
            r7.readLabel(r0, r5)
            r0 = r16
        L_0x002d:
            r4 = 132(0x84, float:1.85E-43)
            r3 = 1
            if (r0 >= r6) goto L_0x00c7
            int r1 = r0 - r16
            byte r2 = r9[r0]
            r2 = r2 & 255(0xff, float:3.57E-43)
            byte[] r17 = org.objectweb.asm.ClassWriter.a
            byte r2 = r17[r2]
            switch(r2) {
                case 0: goto L_0x00c3;
                case 1: goto L_0x00bf;
                case 2: goto L_0x00bb;
                case 3: goto L_0x00bf;
                case 4: goto L_0x00c3;
                case 5: goto L_0x00bb;
                case 6: goto L_0x00bb;
                case 7: goto L_0x00ad;
                case 8: goto L_0x00ad;
                case 9: goto L_0x00b1;
                case 10: goto L_0x00a3;
                case 11: goto L_0x00bf;
                case 12: goto L_0x00bb;
                case 13: goto L_0x00bb;
                case 14: goto L_0x0074;
                case 15: goto L_0x004d;
                case 16: goto L_0x003f;
                case 17: goto L_0x0042;
                default: goto L_0x003f;
            }
        L_0x003f:
            int r0 = r0 + 4
            goto L_0x002d
        L_0x0042:
            int r1 = r0 + 1
            byte r1 = r9[r1]
            r1 = r1 & 255(0xff, float:3.57E-43)
            if (r1 != r4) goto L_0x003f
            int r0 = r0 + 6
            goto L_0x002d
        L_0x004d:
            int r0 = r0 + 4
            r2 = r1 & 3
            int r0 = r0 - r2
            int r2 = r7.readInt(r0)
            int r2 = r2 + r1
            r7.readLabel(r2, r5)
            int r2 = r0 + 4
            int r2 = r7.readInt(r2)
        L_0x0060:
            if (r2 <= 0) goto L_0x0071
            int r3 = r0 + 12
            int r3 = r7.readInt(r3)
            int r3 = r3 + r1
            r7.readLabel(r3, r5)
            int r0 = r0 + 8
            int r2 = r2 + -1
            goto L_0x0060
        L_0x0071:
            int r0 = r0 + 8
            goto L_0x002d
        L_0x0074:
            int r0 = r0 + 4
            r2 = r1 & 3
            int r0 = r0 - r2
            int r2 = r7.readInt(r0)
            int r2 = r2 + r1
            r7.readLabel(r2, r5)
            int r2 = r0 + 8
            int r2 = r7.readInt(r2)
            int r4 = r0 + 4
            int r4 = r7.readInt(r4)
            int r2 = r2 - r4
            int r2 = r2 + r3
        L_0x008f:
            if (r2 <= 0) goto L_0x00a0
            int r3 = r0 + 12
            int r3 = r7.readInt(r3)
            int r3 = r3 + r1
            r7.readLabel(r3, r5)
            int r0 = r0 + 4
            int r2 = r2 + -1
            goto L_0x008f
        L_0x00a0:
            int r0 = r0 + 12
            goto L_0x002d
        L_0x00a3:
            int r2 = r0 + 1
            int r2 = r7.readInt(r2)
            int r1 = r1 + r2
            r7.readLabel(r1, r5)
        L_0x00ad:
            int r0 = r0 + 5
            goto L_0x002d
        L_0x00b1:
            int r2 = r0 + 1
            short r2 = r7.readShort(r2)
            int r1 = r1 + r2
            r7.readLabel(r1, r5)
        L_0x00bb:
            int r0 = r0 + 3
            goto L_0x002d
        L_0x00bf:
            int r0 = r0 + 2
            goto L_0x002d
        L_0x00c3:
            int r0 = r0 + 1
            goto L_0x002d
        L_0x00c7:
            int r1 = r7.readUnsignedShort(r0)
        L_0x00cb:
            if (r1 <= 0) goto L_0x0106
            int r2 = r0 + 2
            int r2 = r7.readUnsignedShort(r2)
            org.objectweb.asm.Label r2 = r7.readLabel(r2, r5)
            int r4 = r0 + 4
            int r4 = r7.readUnsignedShort(r4)
            org.objectweb.asm.Label r4 = r7.readLabel(r4, r5)
            int r3 = r0 + 6
            int r3 = r7.readUnsignedShort(r3)
            org.objectweb.asm.Label r3 = r7.readLabel(r3, r5)
            r19 = r6
            int[] r6 = r7.a
            int r0 = r0 + 8
            int r20 = r7.readUnsignedShort(r0)
            r6 = r6[r20]
            java.lang.String r6 = r7.readUTF8(r6, r13)
            r15.visitTryCatchBlock(r2, r4, r3, r6)
            int r1 = r1 + -1
            r6 = r19
            r3 = 1
            r4 = 132(0x84, float:1.85E-43)
            goto L_0x00cb
        L_0x0106:
            r19 = r6
            int r0 = r0 + 2
            int r1 = r14.b
            r6 = 8
            r1 = r1 & r6
            if (r1 == 0) goto L_0x0113
            r3 = 1
            goto L_0x0114
        L_0x0113:
            r3 = 0
        L_0x0114:
            int r1 = r7.readUnsignedShort(r0)
            r21 = r0
            r22 = r1
            r0 = 0
            r1 = 0
            r8 = 1
            r23 = 0
            r24 = 0
            r25 = 0
            r26 = 0
            r27 = 0
            r28 = 0
            r29 = -1
            r30 = -1
        L_0x012f:
            r6 = 67
            if (r22 <= 0) goto L_0x0368
            int r2 = r21 + 2
            java.lang.String r2 = r7.readUTF8(r2, r13)
            java.lang.String r4 = "LocalVariableTable"
            boolean r4 = r4.equals(r2)
            if (r4 == 0) goto L_0x01ad
            int r2 = r14.b
            r2 = r2 & 2
            if (r2 != 0) goto L_0x01a6
            int r2 = r21 + 8
            int r4 = r7.readUnsignedShort(r2)
            r6 = r21
        L_0x014f:
            if (r4 <= 0) goto L_0x0194
            r34 = r0
            int r0 = r6 + 10
            r35 = r1
            int r1 = r7.readUnsignedShort(r0)
            r26 = r5[r1]
            if (r26 != 0) goto L_0x0170
            r26 = r0
            org.objectweb.asm.Label r0 = r7.readLabel(r1, r5)
            r36 = r2
            int r2 = r0.a
            r18 = 1
            r2 = r2 | 1
            r0.a = r2
            goto L_0x0174
        L_0x0170:
            r26 = r0
            r36 = r2
        L_0x0174:
            int r6 = r6 + 12
            int r0 = r7.readUnsignedShort(r6)
            int r1 = r1 + r0
            r0 = r5[r1]
            if (r0 != 0) goto L_0x0189
            org.objectweb.asm.Label r0 = r7.readLabel(r1, r5)
            int r1 = r0.a
            r2 = 1
            r1 = r1 | r2
            r0.a = r1
        L_0x0189:
            int r4 = r4 + -1
            r6 = r26
            r0 = r34
            r1 = r35
            r2 = r36
            goto L_0x014f
        L_0x0194:
            r34 = r0
            r35 = r1
            r36 = r2
            r15 = r3
            r20 = r5
            r17 = r10
            r39 = r19
            r26 = r36
        L_0x01a3:
            r10 = -1
            goto L_0x0351
        L_0x01a6:
            r34 = r0
            r35 = r1
        L_0x01aa:
            r4 = 0
            r6 = 1
            goto L_0x01bd
        L_0x01ad:
            r34 = r0
            r35 = r1
            java.lang.String r0 = "LocalVariableTypeTable"
            boolean r0 = r0.equals(r2)
            if (r0 == 0) goto L_0x01c9
            int r0 = r21 + 8
            r25 = r0
        L_0x01bd:
            r15 = r3
        L_0x01be:
            r20 = r5
            r17 = r10
            r39 = r19
            r0 = r34
        L_0x01c6:
            r1 = r35
            goto L_0x01a3
        L_0x01c9:
            java.lang.String r0 = "LineNumberTable"
            boolean r0 = r0.equals(r2)
            if (r0 == 0) goto L_0x0218
            int r0 = r14.b
            r0 = r0 & 2
            if (r0 != 0) goto L_0x01aa
            int r0 = r21 + 8
            int r0 = r7.readUnsignedShort(r0)
            r1 = r21
        L_0x01df:
            if (r0 <= 0) goto L_0x01aa
            int r2 = r1 + 10
            int r2 = r7.readUnsignedShort(r2)
            r4 = r5[r2]
            if (r4 != 0) goto L_0x01f7
            org.objectweb.asm.Label r4 = r7.readLabel(r2, r5)
            int r6 = r4.a
            r18 = 1
            r6 = r6 | 1
            r4.a = r6
        L_0x01f7:
            r2 = r5[r2]
        L_0x01f9:
            int r4 = r2.b
            if (r4 <= 0) goto L_0x020b
            org.objectweb.asm.Label r4 = r2.k
            if (r4 != 0) goto L_0x0208
            org.objectweb.asm.Label r4 = new org.objectweb.asm.Label
            r4.<init>()
            r2.k = r4
        L_0x0208:
            org.objectweb.asm.Label r2 = r2.k
            goto L_0x01f9
        L_0x020b:
            int r4 = r1 + 12
            int r4 = r7.readUnsignedShort(r4)
            r2.b = r4
            int r1 = r1 + 4
            int r0 = r0 + -1
            goto L_0x01df
        L_0x0218:
            java.lang.String r0 = "RuntimeVisibleTypeAnnotations"
            boolean r0 = r0.equals(r2)
            if (r0 == 0) goto L_0x024c
            int r0 = r21 + 8
            r1 = 1
            int[] r0 = r7.a((org.objectweb.asm.MethodVisitor) r15, (org.objectweb.asm.Context) r14, (int) r0, (boolean) r1)
            int r2 = r0.length
            if (r2 == 0) goto L_0x023c
            r2 = 0
            r4 = r0[r2]
            int r4 = r7.readByte(r4)
            if (r4 >= r6) goto L_0x0234
            goto L_0x023c
        L_0x0234:
            r4 = r0[r2]
            int r4 = r4 + r1
            int r1 = r7.readUnsignedShort(r4)
            goto L_0x023d
        L_0x023c:
            r1 = -1
        L_0x023d:
            r29 = r1
            r15 = r3
            r20 = r5
            r17 = r10
            r39 = r19
            r10 = -1
            r1 = r0
            r0 = r34
            goto L_0x0351
        L_0x024c:
            java.lang.String r0 = "RuntimeInvisibleTypeAnnotations"
            boolean r0 = r0.equals(r2)
            if (r0 == 0) goto L_0x027d
            int r0 = r21 + 8
            r4 = 0
            int[] r0 = r7.a((org.objectweb.asm.MethodVisitor) r15, (org.objectweb.asm.Context) r14, (int) r0, (boolean) r4)
            int r1 = r0.length
            if (r1 == 0) goto L_0x0266
            r1 = r0[r4]
            int r1 = r7.readByte(r1)
            if (r1 >= r6) goto L_0x0268
        L_0x0266:
            r6 = 1
            goto L_0x0271
        L_0x0268:
            r1 = r0[r4]
            r6 = 1
            int r1 = r1 + r6
            int r1 = r7.readUnsignedShort(r1)
            goto L_0x0272
        L_0x0271:
            r1 = -1
        L_0x0272:
            r30 = r1
            r15 = r3
            r20 = r5
            r17 = r10
            r39 = r19
            goto L_0x01c6
        L_0x027d:
            r4 = 0
            r6 = 1
            java.lang.String r0 = "StackMapTable"
            boolean r0 = r0.equals(r2)
            if (r0 == 0) goto L_0x02a3
            int r0 = r14.b
            r0 = r0 & 4
            if (r0 != 0) goto L_0x01bd
            int r0 = r21 + 10
            int r1 = r21 + 4
            int r1 = r7.readInt(r1)
            int r2 = r21 + 8
            int r2 = r7.readUnsignedShort(r2)
            r23 = r0
            r24 = r1
            r28 = r2
            goto L_0x01bd
        L_0x02a3:
            java.lang.String r0 = "StackMap"
            boolean r0 = r0.equals(r2)
            if (r0 == 0) goto L_0x02c9
            int r0 = r14.b
            r0 = r0 & 4
            if (r0 != 0) goto L_0x01bd
            int r0 = r21 + 10
            int r1 = r21 + 4
            int r1 = r7.readInt(r1)
            int r2 = r21 + 8
            int r2 = r7.readUnsignedShort(r2)
            r23 = r0
            r24 = r1
            r28 = r2
            r15 = r3
            r8 = r4
            goto L_0x01be
        L_0x02c9:
            r1 = r4
        L_0x02ca:
            org.objectweb.asm.Attribute[] r0 = r14.a
            int r4 = r0.length
            if (r1 >= r4) goto L_0x033b
            r0 = r0[r1]
            java.lang.String r0 = r0.type
            boolean r0 = r0.equals(r2)
            if (r0 == 0) goto L_0x030e
            org.objectweb.asm.Attribute[] r0 = r14.a
            r0 = r0[r1]
            int r4 = r21 + 8
            int r6 = r21 + 4
            int r6 = r7.readInt(r6)
            r37 = r34
            r34 = r8
            r8 = r27
            r27 = r1
            r38 = r35
            r1 = r43
            r32 = r2
            r15 = 0
            r2 = r4
            r15 = r3
            r4 = 1
            r3 = r6
            r17 = r10
            r6 = 0
            r10 = -1
            r4 = r13
            r20 = r5
            r5 = r46
            r39 = r19
            r6 = r20
            org.objectweb.asm.Attribute r0 = r0.read(r1, r2, r3, r4, r5, r6)
            if (r0 == 0) goto L_0x0322
            r0.a = r8
            goto L_0x0323
        L_0x030e:
            r32 = r2
            r15 = r3
            r20 = r5
            r17 = r10
            r39 = r19
            r37 = r34
            r38 = r35
            r10 = -1
            r34 = r8
            r8 = r27
            r27 = r1
        L_0x0322:
            r0 = r8
        L_0x0323:
            int r1 = r27 + 1
            r27 = r0
            r3 = r15
            r10 = r17
            r5 = r20
            r2 = r32
            r8 = r34
            r34 = r37
            r35 = r38
            r19 = r39
            r4 = 0
            r6 = 1
            r15 = r44
            goto L_0x02ca
        L_0x033b:
            r15 = r3
            r20 = r5
            r17 = r10
            r39 = r19
            r37 = r34
            r38 = r35
            r10 = -1
            r34 = r8
            r8 = r27
            r8 = r34
            r0 = r37
            r1 = r38
        L_0x0351:
            int r2 = r21 + 4
            int r2 = r7.readInt(r2)
            int r2 = r2 + 6
            int r21 = r21 + r2
            int r22 = r22 + -1
            r3 = r15
            r10 = r17
            r5 = r20
            r19 = r39
            r15 = r44
            goto L_0x012f
        L_0x0368:
            r37 = r0
            r38 = r1
            r15 = r3
            r20 = r5
            r34 = r8
            r17 = r10
            r39 = r19
            r10 = -1
            if (r23 == 0) goto L_0x03cf
            r14.o = r10
            r8 = 0
            r14.p = r8
            r14.q = r8
            r14.r = r8
            r14.t = r8
            java.lang.Object[] r0 = new java.lang.Object[r11]
            r14.s = r0
            java.lang.Object[] r0 = new java.lang.Object[r12]
            r14.u = r0
            if (r15 == 0) goto L_0x0390
            r7.a(r14)
        L_0x0390:
            r0 = r23
        L_0x0392:
            int r1 = r23 + r24
            int r1 = r1 + -2
            if (r0 >= r1) goto L_0x03c7
            byte r1 = r9[r0]
            r5 = 8
            if (r1 != r5) goto L_0x03bd
            int r1 = r0 + 1
            int r1 = r7.readUnsignedShort(r1)
            if (r1 < 0) goto L_0x03bd
            r4 = r17
            if (r1 >= r4) goto L_0x03ba
            int r2 = r16 + r1
            byte r2 = r9[r2]
            r2 = r2 & 255(0xff, float:3.57E-43)
            r3 = 187(0xbb, float:2.62E-43)
            if (r2 != r3) goto L_0x03ba
            r3 = r20
            r7.readLabel(r1, r3)
            goto L_0x03c0
        L_0x03ba:
            r3 = r20
            goto L_0x03c0
        L_0x03bd:
            r4 = r17
            goto L_0x03ba
        L_0x03c0:
            int r0 = r0 + 1
            r20 = r3
            r17 = r4
            goto L_0x0392
        L_0x03c7:
            r4 = r17
            r3 = r20
            r5 = 8
            r2 = r14
            goto L_0x03d7
        L_0x03cf:
            r4 = r17
            r3 = r20
            r5 = 8
            r8 = 0
            r2 = 0
        L_0x03d7:
            r17 = r8
            r19 = r17
            r1 = r16
        L_0x03dd:
            r0 = r39
            if (r1 >= r0) goto L_0x07e3
            int r8 = r1 - r16
            r5 = r3[r8]
            if (r5 == 0) goto L_0x040a
            org.objectweb.asm.Label r6 = r5.k
            r10 = 0
            r5.k = r10
            r10 = r44
            r10.visitLabel(r5)
            r39 = r0
            int r0 = r14.b
            r0 = r0 & 2
            if (r0 != 0) goto L_0x040e
            int r0 = r5.b
            if (r0 <= 0) goto L_0x040e
            r10.visitLineNumber(r0, r5)
        L_0x0400:
            if (r6 == 0) goto L_0x040e
            int r0 = r6.b
            r10.visitLineNumber(r0, r5)
            org.objectweb.asm.Label r6 = r6.k
            goto L_0x0400
        L_0x040a:
            r10 = r44
            r39 = r0
        L_0x040e:
            r6 = r2
        L_0x040f:
            if (r6 == 0) goto L_0x04bd
            int r0 = r6.o
            r5 = -1
            if (r0 == r8) goto L_0x042f
            if (r0 != r5) goto L_0x0419
            goto L_0x042f
        L_0x0419:
            r32 = r1
            r31 = r3
            r18 = r4
            r33 = r5
            r22 = r11
            r35 = r12
            r12 = r23
            r11 = r34
            r24 = r39
            r23 = 8
            goto L_0x04d1
        L_0x042f:
            if (r0 == r5) goto L_0x0480
            if (r34 == 0) goto L_0x0435
            if (r15 == 0) goto L_0x0448
        L_0x0435:
            r32 = r1
            r31 = r3
            r18 = r4
            r33 = r5
            r22 = r11
            r35 = r12
            r12 = r23
            r24 = r39
            r23 = 8
            goto L_0x0471
        L_0x0448:
            int r2 = r6.p
            int r0 = r6.r
            java.lang.Object[] r5 = r6.s
            r18 = r4
            int r4 = r6.t
            r22 = r11
            java.lang.Object[] r11 = r6.u
            r31 = r0
            r24 = r39
            r0 = r44
            r32 = r1
            r1 = r2
            r2 = r31
            r31 = r3
            r3 = r5
            r35 = r12
            r12 = r23
            r23 = 8
            r33 = -1
            r5 = r11
            r0.visitFrame(r1, r2, r3, r4, r5)
            goto L_0x0492
        L_0x0471:
            int r2 = r6.q
            java.lang.Object[] r3 = r6.s
            int r4 = r6.t
            java.lang.Object[] r5 = r6.u
            r1 = -1
            r0 = r44
            r0.visitFrame(r1, r2, r3, r4, r5)
            goto L_0x0492
        L_0x0480:
            r32 = r1
            r31 = r3
            r18 = r4
            r33 = r5
            r22 = r11
            r35 = r12
            r12 = r23
            r24 = r39
            r23 = 8
        L_0x0492:
            if (r28 <= 0) goto L_0x04ac
            r11 = r34
            int r0 = r7.a((int) r12, (boolean) r11, (boolean) r15, (org.objectweb.asm.Context) r6)
            int r28 = r28 + -1
            r23 = r0
            r4 = r18
            r11 = r22
            r39 = r24
            r3 = r31
            r1 = r32
            r12 = r35
            goto L_0x040f
        L_0x04ac:
            r23 = r12
            r4 = r18
            r11 = r22
            r39 = r24
            r3 = r31
            r1 = r32
            r12 = r35
            r6 = 0
            goto L_0x040f
        L_0x04bd:
            r32 = r1
            r31 = r3
            r18 = r4
            r22 = r11
            r35 = r12
            r12 = r23
            r11 = r34
            r24 = r39
            r23 = 8
            r33 = -1
        L_0x04d1:
            byte r0 = r9[r32]
            r5 = r0 & 255(0xff, float:3.57E-43)
            byte[] r0 = org.objectweb.asm.ClassWriter.a
            byte r0 = r0[r5]
            switch(r0) {
                case 0: goto L_0x070f;
                case 1: goto L_0x0700;
                case 2: goto L_0x06ef;
                case 3: goto L_0x06db;
                case 4: goto L_0x06b6;
                case 5: goto L_0x06a5;
                case 6: goto L_0x0651;
                case 7: goto L_0x0651;
                case 8: goto L_0x05fd;
                case 9: goto L_0x05ed;
                case 10: goto L_0x05d8;
                case 11: goto L_0x05c5;
                case 12: goto L_0x05b5;
                case 13: goto L_0x05a2;
                case 14: goto L_0x0569;
                case 15: goto L_0x052e;
                case 16: goto L_0x04dc;
                case 17: goto L_0x04fb;
                default: goto L_0x04dc;
            }
        L_0x04dc:
            int r1 = r32 + 1
            java.lang.String r0 = r7.readClass(r1, r13)
            int r1 = r32 + 3
            byte r1 = r9[r1]
            r1 = r1 & 255(0xff, float:3.57E-43)
            r10.visitMultiANewArrayInsn(r0, r1)
            int r1 = r32 + 4
        L_0x04ed:
            r46 = r6
        L_0x04ef:
            r36 = r11
            r0 = r17
            r2 = r29
            r11 = r38
            r34 = 132(0x84, float:1.85E-43)
            goto L_0x071a
        L_0x04fb:
            int r1 = r32 + 1
            byte r0 = r9[r1]
            r0 = r0 & 255(0xff, float:3.57E-43)
            r4 = 132(0x84, float:1.85E-43)
            int r1 = r32 + 2
            if (r0 != r4) goto L_0x0524
            int r0 = r7.readUnsignedShort(r1)
            int r1 = r32 + 4
            short r1 = r7.readShort(r1)
            r10.visitIincInsn(r0, r1)
            int r1 = r32 + 6
        L_0x0516:
            r34 = r4
            r46 = r6
            r36 = r11
        L_0x051c:
            r0 = r17
            r2 = r29
            r11 = r38
            goto L_0x071a
        L_0x0524:
            int r1 = r7.readUnsignedShort(r1)
            r10.visitVarInsn(r0, r1)
            int r1 = r32 + 4
            goto L_0x0516
        L_0x052e:
            r4 = 132(0x84, float:1.85E-43)
            int r1 = r32 + 4
            r0 = r8 & 3
            int r1 = r1 - r0
            int r0 = r7.readInt(r1)
            int r0 = r0 + r8
            int r2 = r1 + 4
            int r2 = r7.readInt(r2)
            int[] r3 = new int[r2]
            org.objectweb.asm.Label[] r5 = new org.objectweb.asm.Label[r2]
            int r1 = r1 + 8
            r4 = 0
        L_0x0547:
            if (r4 >= r2) goto L_0x0563
            int r32 = r7.readInt(r1)
            r3[r4] = r32
            r46 = r2
            int r2 = r1 + 4
            int r2 = r7.readInt(r2)
            int r2 = r2 + r8
            r2 = r31[r2]
            r5[r4] = r2
            int r1 = r1 + 8
            int r4 = r4 + 1
            r2 = r46
            goto L_0x0547
        L_0x0563:
            r0 = r31[r0]
            r10.visitLookupSwitchInsn(r0, r3, r5)
            goto L_0x04ed
        L_0x0569:
            int r1 = r32 + 4
            r0 = r8 & 3
            int r1 = r1 - r0
            int r0 = r7.readInt(r1)
            int r0 = r0 + r8
            int r2 = r1 + 4
            int r2 = r7.readInt(r2)
            int r3 = r1 + 8
            int r3 = r7.readInt(r3)
            int r4 = r3 - r2
            r5 = 1
            int r4 = r4 + r5
            org.objectweb.asm.Label[] r5 = new org.objectweb.asm.Label[r4]
            int r1 = r1 + 12
            r46 = r6
            r6 = 0
        L_0x058a:
            if (r6 >= r4) goto L_0x059b
            int r32 = r7.readInt(r1)
            int r32 = r8 + r32
            r32 = r31[r32]
            r5[r6] = r32
            int r1 = r1 + 4
            int r6 = r6 + 1
            goto L_0x058a
        L_0x059b:
            r0 = r31[r0]
            r10.visitTableSwitchInsn(r2, r3, r0, r5)
            goto L_0x04ef
        L_0x05a2:
            r46 = r6
            int r1 = r32 + 1
            byte r0 = r9[r1]
            r0 = r0 & 255(0xff, float:3.57E-43)
            int r1 = r32 + 2
            byte r1 = r9[r1]
            r10.visitIincInsn(r0, r1)
        L_0x05b1:
            int r1 = r32 + 3
            goto L_0x04ef
        L_0x05b5:
            r46 = r6
            int r1 = r32 + 1
            int r0 = r7.readUnsignedShort(r1)
            java.lang.Object r0 = r7.readConst(r0, r13)
            r10.visitLdcInsn(r0)
            goto L_0x05b1
        L_0x05c5:
            r46 = r6
            int r1 = r32 + 1
            byte r0 = r9[r1]
            r0 = r0 & 255(0xff, float:3.57E-43)
            java.lang.Object r0 = r7.readConst(r0, r13)
            r10.visitLdcInsn(r0)
            int r1 = r32 + 2
            goto L_0x04ef
        L_0x05d8:
            r46 = r6
            r6 = 1
            int r5 = r5 + -33
            int r1 = r32 + 1
            int r0 = r7.readInt(r1)
            int r0 = r0 + r8
            r0 = r31[r0]
            r10.visitJumpInsn(r5, r0)
        L_0x05e9:
            int r1 = r32 + 5
            goto L_0x04ef
        L_0x05ed:
            r46 = r6
            r6 = 1
            int r1 = r32 + 1
            short r0 = r7.readShort(r1)
            int r0 = r0 + r8
            r0 = r31[r0]
            r10.visitJumpInsn(r5, r0)
            goto L_0x05b1
        L_0x05fd:
            r46 = r6
            r6 = 1
            int[] r0 = r7.a
            int r1 = r32 + 1
            int r1 = r7.readUnsignedShort(r1)
            r0 = r0[r1]
            int[] r1 = r14.d
            int r2 = r7.readUnsignedShort(r0)
            r1 = r1[r2]
            int r2 = r7.readUnsignedShort(r1)
            java.lang.Object r2 = r7.readConst(r2, r13)
            org.objectweb.asm.Handle r2 = (org.objectweb.asm.Handle) r2
            int r3 = r1 + 2
            int r3 = r7.readUnsignedShort(r3)
            java.lang.Object[] r4 = new java.lang.Object[r3]
            int r1 = r1 + 4
            r5 = 0
        L_0x0627:
            if (r5 >= r3) goto L_0x0639
            int r6 = r7.readUnsignedShort(r1)
            java.lang.Object r6 = r7.readConst(r6, r13)
            r4[r5] = r6
            int r1 = r1 + 2
            int r5 = r5 + 1
            r6 = 1
            goto L_0x0627
        L_0x0639:
            int[] r1 = r7.a
            int r0 = r0 + 2
            int r0 = r7.readUnsignedShort(r0)
            r0 = r1[r0]
            java.lang.String r1 = r7.readUTF8(r0, r13)
            int r0 = r0 + 2
            java.lang.String r0 = r7.readUTF8(r0, r13)
            r10.visitInvokeDynamicInsn(r1, r0, r2, r4)
            goto L_0x05e9
        L_0x0651:
            r46 = r6
            int[] r0 = r7.a
            int r1 = r32 + 1
            int r1 = r7.readUnsignedShort(r1)
            r0 = r0[r1]
            int r1 = r0 + -1
            byte r1 = r9[r1]
            r2 = 11
            if (r1 != r2) goto L_0x0667
            r6 = 1
            goto L_0x0668
        L_0x0667:
            r6 = 0
        L_0x0668:
            java.lang.String r2 = r7.readClass(r0, r13)
            int[] r1 = r7.a
            int r0 = r0 + 2
            int r0 = r7.readUnsignedShort(r0)
            r0 = r1[r0]
            java.lang.String r3 = r7.readUTF8(r0, r13)
            int r0 = r0 + 2
            java.lang.String r4 = r7.readUTF8(r0, r13)
            r0 = 182(0xb6, float:2.55E-43)
            if (r5 >= r0) goto L_0x068d
            r10.visitFieldInsn(r5, r2, r3, r4)
            r36 = r11
            r34 = 132(0x84, float:1.85E-43)
            r11 = r5
            goto L_0x0699
        L_0x068d:
            r0 = r44
            r1 = r5
            r34 = 132(0x84, float:1.85E-43)
            r36 = r11
            r11 = r5
            r5 = r6
            r0.visitMethodInsn(r1, r2, r3, r4, r5)
        L_0x0699:
            r0 = 185(0xb9, float:2.59E-43)
            if (r11 != r0) goto L_0x06a1
            int r1 = r32 + 5
            goto L_0x051c
        L_0x06a1:
            int r1 = r32 + 3
            goto L_0x051c
        L_0x06a5:
            r46 = r6
            r36 = r11
            r34 = 132(0x84, float:1.85E-43)
            r11 = r5
            int r1 = r32 + 1
            java.lang.String r0 = r7.readClass(r1, r13)
            r10.visitTypeInsn(r11, r0)
            goto L_0x06a1
        L_0x06b6:
            r46 = r6
            r36 = r11
            r34 = 132(0x84, float:1.85E-43)
            r11 = r5
            r0 = 54
            if (r11 <= r0) goto L_0x06cc
            int r5 = r11 + -59
            int r1 = r5 >> 2
            int r1 = r1 + r0
            r0 = r5 & 3
            r10.visitVarInsn(r1, r0)
            goto L_0x06d7
        L_0x06cc:
            int r5 = r11 + -26
            int r0 = r5 >> 2
            int r0 = r0 + 21
            r1 = r5 & 3
            r10.visitVarInsn(r0, r1)
        L_0x06d7:
            int r1 = r32 + 1
            goto L_0x051c
        L_0x06db:
            r46 = r6
            r36 = r11
            r34 = 132(0x84, float:1.85E-43)
            r11 = r5
            int r1 = r32 + 1
            byte r0 = r9[r1]
            r0 = r0 & 255(0xff, float:3.57E-43)
            r10.visitVarInsn(r11, r0)
        L_0x06eb:
            int r1 = r32 + 2
            goto L_0x051c
        L_0x06ef:
            r46 = r6
            r36 = r11
            r34 = 132(0x84, float:1.85E-43)
            r11 = r5
            int r1 = r32 + 1
            short r0 = r7.readShort(r1)
            r10.visitIntInsn(r11, r0)
            goto L_0x06a1
        L_0x0700:
            r46 = r6
            r36 = r11
            r34 = 132(0x84, float:1.85E-43)
            r11 = r5
            int r1 = r32 + 1
            byte r0 = r9[r1]
            r10.visitIntInsn(r11, r0)
            goto L_0x06eb
        L_0x070f:
            r46 = r6
            r36 = r11
            r34 = 132(0x84, float:1.85E-43)
            r11 = r5
            r10.visitInsn(r11)
            goto L_0x06d7
        L_0x071a:
            if (r11 == 0) goto L_0x0758
            int r3 = r11.length
            if (r0 >= r3) goto L_0x0758
            if (r2 > r8) goto L_0x0758
            if (r2 != r8) goto L_0x073b
            r2 = r11[r0]
            int r2 = r7.a(r14, r2)
            int r3 = r2 + 2
            int r4 = r14.i
            org.objectweb.asm.TypePath r5 = r14.j
            java.lang.String r2 = r7.readUTF8(r2, r13)
            r6 = 1
            org.objectweb.asm.AnnotationVisitor r2 = r10.visitInsnAnnotation(r4, r5, r2, r6)
            r7.a((int) r3, (char[]) r13, (boolean) r6, (org.objectweb.asm.AnnotationVisitor) r2)
        L_0x073b:
            int r0 = r0 + 1
            int r2 = r11.length
            if (r0 >= r2) goto L_0x0755
            r2 = r11[r0]
            int r2 = r7.readByte(r2)
            r3 = 67
            if (r2 >= r3) goto L_0x074b
            goto L_0x0755
        L_0x074b:
            r2 = r11[r0]
            r3 = 1
            int r2 = r2 + r3
            int r4 = r7.readUnsignedShort(r2)
            r2 = r4
            goto L_0x071a
        L_0x0755:
            r2 = r33
            goto L_0x071a
        L_0x0758:
            r3 = r19
            r4 = r30
            r6 = r37
        L_0x075e:
            if (r6 == 0) goto L_0x07b7
            int r5 = r6.length
            if (r3 >= r5) goto L_0x07b7
            if (r4 > r8) goto L_0x07b7
            if (r4 != r8) goto L_0x0787
            r4 = r6[r3]
            int r4 = r7.a(r14, r4)
            int r5 = r4 + 2
            r17 = r0
            int r0 = r14.i
            r19 = r1
            org.objectweb.asm.TypePath r1 = r14.j
            java.lang.String r4 = r7.readUTF8(r4, r13)
            r29 = r8
            r8 = 0
            org.objectweb.asm.AnnotationVisitor r0 = r10.visitInsnAnnotation(r0, r1, r4, r8)
            r1 = 1
            r7.a((int) r5, (char[]) r13, (boolean) r1, (org.objectweb.asm.AnnotationVisitor) r0)
            goto L_0x078e
        L_0x0787:
            r17 = r0
            r19 = r1
            r29 = r8
            r8 = 0
        L_0x078e:
            int r3 = r3 + 1
            int r0 = r6.length
            if (r3 >= r0) goto L_0x07ab
            r0 = r6[r3]
            int r0 = r7.readByte(r0)
            r1 = 67
            if (r0 >= r1) goto L_0x07a0
        L_0x079d:
            r20 = 1
            goto L_0x07ae
        L_0x07a0:
            r0 = r6[r3]
            r20 = 1
            int r0 = r0 + 1
            int r4 = r7.readUnsignedShort(r0)
            goto L_0x07b0
        L_0x07ab:
            r1 = 67
            goto L_0x079d
        L_0x07ae:
            r4 = r33
        L_0x07b0:
            r0 = r17
            r1 = r19
            r8 = r29
            goto L_0x075e
        L_0x07b7:
            r17 = r0
            r19 = r1
            r1 = 67
            r8 = 0
            r20 = 1
            r29 = r2
            r30 = r4
            r37 = r6
            r38 = r11
            r4 = r18
            r11 = r22
            r5 = r23
            r39 = r24
            r10 = r33
            r34 = r36
            r2 = r46
            r6 = r1
            r23 = r12
            r1 = r19
            r12 = r35
            r19 = r3
            r3 = r31
            goto L_0x03dd
        L_0x07e3:
            r10 = r44
            r31 = r3
            r18 = r4
            r22 = r11
            r35 = r12
            r6 = r37
            r11 = r38
            r20 = 1
            r0 = r31[r18]
            if (r0 == 0) goto L_0x07fa
            r10.visitLabel(r0)
        L_0x07fa:
            int r0 = r14.b
            r0 = r0 & 2
            if (r0 != 0) goto L_0x0899
            if (r26 == 0) goto L_0x0899
            r0 = r25
            if (r0 == 0) goto L_0x0831
            int r25 = r0 + 2
            int r0 = r7.readUnsignedShort(r0)
            int r0 = r0 * 3
            int[] r2 = new int[r0]
            r1 = r25
        L_0x0812:
            if (r0 <= 0) goto L_0x082f
            int r3 = r0 + -1
            int r4 = r1 + 6
            r2[r3] = r4
            int r3 = r0 + -2
            int r4 = r1 + 8
            int r4 = r7.readUnsignedShort(r4)
            r2[r3] = r4
            int r0 = r0 + -3
            int r3 = r7.readUnsignedShort(r1)
            r2[r0] = r3
            int r1 = r1 + 10
            goto L_0x0812
        L_0x082f:
            r9 = r2
            goto L_0x0832
        L_0x0831:
            r9 = 0
        L_0x0832:
            int r0 = r26 + 2
            r1 = r26
            int r1 = r7.readUnsignedShort(r1)
            r12 = r0
            r15 = r1
        L_0x083c:
            if (r15 <= 0) goto L_0x0899
            int r0 = r7.readUnsignedShort(r12)
            int r1 = r12 + 2
            int r1 = r7.readUnsignedShort(r1)
            int r2 = r12 + 8
            int r5 = r7.readUnsignedShort(r2)
            if (r9 == 0) goto L_0x086b
            r4 = r8
        L_0x0851:
            int r2 = r9.length
            if (r4 >= r2) goto L_0x086b
            r2 = r9[r4]
            if (r2 != r0) goto L_0x0868
            int r2 = r4 + 1
            r2 = r9[r2]
            if (r2 != r5) goto L_0x0868
            int r4 = r4 + 2
            r2 = r9[r4]
            java.lang.String r2 = r7.readUTF8(r2, r13)
            r3 = r2
            goto L_0x086c
        L_0x0868:
            int r4 = r4 + 3
            goto L_0x0851
        L_0x086b:
            r3 = 0
        L_0x086c:
            int r2 = r12 + 4
            java.lang.String r2 = r7.readUTF8(r2, r13)
            int r4 = r12 + 6
            java.lang.String r4 = r7.readUTF8(r4, r13)
            r16 = r31[r0]
            int r0 = r0 + r1
            r17 = r31[r0]
            r0 = r44
            r1 = r2
            r2 = r4
            r4 = r16
            r16 = r5
            r5 = r17
            r40 = r6
            r10 = r20
            r6 = r16
            r0.visitLocalVariable(r1, r2, r3, r4, r5, r6)
            int r12 = r12 + 10
            int r15 = r15 + -1
            r6 = r40
            r10 = r44
            goto L_0x083c
        L_0x0899:
            r40 = r6
            r10 = r20
            r0 = 32
            if (r11 == 0) goto L_0x0900
            r4 = r8
        L_0x08a2:
            int r1 = r11.length
            if (r4 >= r1) goto L_0x0900
            r1 = r11[r4]
            int r1 = r7.readByte(r1)
            int r1 = r1 >> r10
            if (r1 != r0) goto L_0x08e3
            r1 = r11[r4]
            int r1 = r7.a(r14, r1)
            int r2 = r1 + 2
            int r9 = r14.i
            org.objectweb.asm.TypePath r3 = r14.j
            org.objectweb.asm.Label[] r5 = r14.l
            org.objectweb.asm.Label[] r12 = r14.m
            int[] r6 = r14.f46n
            java.lang.String r1 = r7.readUTF8(r1, r13)
            r15 = 1
            r16 = r8
            r8 = r44
            r0 = r10
            r10 = r3
            r17 = r11
            r3 = r22
            r11 = r5
            r5 = r35
            r41 = r13
            r13 = r6
            r6 = r14
            r14 = r1
            r1 = r44
            org.objectweb.asm.AnnotationVisitor r8 = r8.visitLocalVariableAnnotation(r9, r10, r11, r12, r13, r14, r15)
            r15 = r41
            r7.a((int) r2, (char[]) r15, (boolean) r0, (org.objectweb.asm.AnnotationVisitor) r8)
            goto L_0x08f0
        L_0x08e3:
            r1 = r44
            r16 = r8
            r0 = r10
            r17 = r11
            r15 = r13
            r6 = r14
            r3 = r22
            r5 = r35
        L_0x08f0:
            int r4 = r4 + 1
            r10 = r0
            r22 = r3
            r35 = r5
            r14 = r6
            r13 = r15
            r8 = r16
            r11 = r17
            r0 = 32
            goto L_0x08a2
        L_0x0900:
            r1 = r44
            r16 = r8
            r0 = r10
            r15 = r13
            r6 = r14
            r3 = r22
            r5 = r35
            r2 = r40
            if (r2 == 0) goto L_0x095a
            r4 = r16
        L_0x0911:
            int r8 = r2.length
            if (r4 >= r8) goto L_0x095a
            r8 = r2[r4]
            int r8 = r7.readByte(r8)
            int r8 = r8 >> r0
            r14 = 32
            if (r8 != r14) goto L_0x094f
            r8 = r2[r4]
            int r8 = r7.a(r6, r8)
            int r13 = r8 + 2
            int r9 = r6.i
            org.objectweb.asm.TypePath r10 = r6.j
            org.objectweb.asm.Label[] r11 = r6.l
            org.objectweb.asm.Label[] r12 = r6.m
            int[] r14 = r6.f46n
            java.lang.String r16 = r7.readUTF8(r8, r15)
            r17 = 0
            r8 = r44
            r42 = r13
            r13 = r14
            r18 = 32
            r14 = r16
            r37 = r2
            r2 = r15
            r15 = r17
            org.objectweb.asm.AnnotationVisitor r8 = r8.visitLocalVariableAnnotation(r9, r10, r11, r12, r13, r14, r15)
            r9 = r42
            r7.a((int) r9, (char[]) r2, (boolean) r0, (org.objectweb.asm.AnnotationVisitor) r8)
            goto L_0x0954
        L_0x094f:
            r37 = r2
            r18 = r14
            r2 = r15
        L_0x0954:
            int r4 = r4 + 1
            r15 = r2
            r2 = r37
            goto L_0x0911
        L_0x095a:
            r0 = r27
        L_0x095c:
            if (r0 == 0) goto L_0x0968
            org.objectweb.asm.Attribute r2 = r0.a
            r4 = 0
            r0.a = r4
            r1.visitAttribute(r0)
            r0 = r2
            goto L_0x095c
        L_0x0968:
            r1.visitMaxs(r5, r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.objectweb.asm.ClassReader.a(org.objectweb.asm.MethodVisitor, org.objectweb.asm.Context, int):void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x0060  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x007d  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private int[] a(org.objectweb.asm.MethodVisitor r12, org.objectweb.asm.Context r13, int r14, boolean r15) {
        /*
            r11 = this;
            char[] r0 = r13.c
            int r1 = r11.readUnsignedShort(r14)
            int[] r2 = new int[r1]
            int r14 = r14 + 2
            r3 = 0
        L_0x000b:
            if (r3 >= r1) goto L_0x0089
            r2[r3] = r14
            int r4 = r11.readInt(r14)
            int r5 = r4 >>> 24
            r6 = 1
            if (r5 == 0) goto L_0x0055
            if (r5 == r6) goto L_0x0055
            r7 = 64
            if (r5 == r7) goto L_0x0031
            r7 = 65
            if (r5 == r7) goto L_0x0031
            switch(r5) {
                case 19: goto L_0x002e;
                case 20: goto L_0x002e;
                case 21: goto L_0x002e;
                case 22: goto L_0x0055;
                default: goto L_0x0025;
            }
        L_0x0025:
            switch(r5) {
                case 71: goto L_0x002b;
                case 72: goto L_0x002b;
                case 73: goto L_0x002b;
                case 74: goto L_0x002b;
                case 75: goto L_0x002b;
                default: goto L_0x0028;
            }
        L_0x0028:
            int r14 = r14 + 3
            goto L_0x0057
        L_0x002b:
            int r14 = r14 + 4
            goto L_0x0057
        L_0x002e:
            int r14 = r14 + 1
            goto L_0x0057
        L_0x0031:
            int r7 = r14 + 1
            int r7 = r11.readUnsignedShort(r7)
        L_0x0037:
            if (r7 <= 0) goto L_0x0028
            int r8 = r14 + 3
            int r8 = r11.readUnsignedShort(r8)
            int r9 = r14 + 5
            int r9 = r11.readUnsignedShort(r9)
            org.objectweb.asm.Label[] r10 = r13.h
            r11.readLabel(r8, r10)
            int r8 = r8 + r9
            org.objectweb.asm.Label[] r9 = r13.h
            r11.readLabel(r8, r9)
            int r14 = r14 + 6
            int r7 = r7 + -1
            goto L_0x0037
        L_0x0055:
            int r14 = r14 + 2
        L_0x0057:
            int r7 = r11.readByte(r14)
            r8 = 66
            r9 = 0
            if (r5 != r8) goto L_0x007d
            if (r7 != 0) goto L_0x0063
            goto L_0x006a
        L_0x0063:
            org.objectweb.asm.TypePath r9 = new org.objectweb.asm.TypePath
            byte[] r5 = r11.b
            r9.<init>(r5, r14)
        L_0x006a:
            int r7 = r7 * 2
            int r7 = r7 + r6
            int r14 = r14 + r7
            int r5 = r14 + 2
            java.lang.String r14 = r11.readUTF8(r14, r0)
            org.objectweb.asm.AnnotationVisitor r14 = r12.visitTryCatchAnnotation(r4, r9, r14, r15)
            int r14 = r11.a((int) r5, (char[]) r0, (boolean) r6, (org.objectweb.asm.AnnotationVisitor) r14)
            goto L_0x0086
        L_0x007d:
            int r14 = r14 + 3
            int r7 = r7 * 2
            int r14 = r14 + r7
            int r14 = r11.a((int) r14, (char[]) r0, (boolean) r6, (org.objectweb.asm.AnnotationVisitor) r9)
        L_0x0086:
            int r3 = r3 + 1
            goto L_0x000b
        L_0x0089:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: org.objectweb.asm.ClassReader.a(org.objectweb.asm.MethodVisitor, org.objectweb.asm.Context, int, boolean):int[]");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:67:0x01af, code lost:
        if (r1.j == 0) goto L_0x01d0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private int b(org.objectweb.asm.ClassVisitor r32, org.objectweb.asm.Context r33, int r34) {
        /*
            r31 = this;
            r8 = r31
            r9 = r33
            r0 = r34
            char[] r10 = r9.c
            int r1 = r8.readUnsignedShort(r0)
            r9.e = r1
            int r1 = r0 + 2
            java.lang.String r1 = r8.readUTF8(r1, r10)
            r9.f = r1
            int r1 = r0 + 4
            java.lang.String r1 = r8.readUTF8(r1, r10)
            r9.g = r1
            int r11 = r0 + 6
            int r0 = r8.readUnsignedShort(r11)
            r14 = r0
            r15 = r11
            r0 = 0
            r1 = 0
            r2 = 0
            r3 = 0
            r4 = 0
            r5 = 0
            r6 = 0
            r7 = 0
            r12 = 0
            r16 = 0
            r17 = 0
            r18 = 0
            r19 = 0
        L_0x0037:
            if (r14 <= 0) goto L_0x0174
            int r13 = r15 + 2
            java.lang.String r13 = r8.readUTF8(r13, r10)
            r21 = r0
            java.lang.String r0 = "Code"
            boolean r0 = r0.equals(r13)
            if (r0 == 0) goto L_0x0068
            int r0 = r9.b
            r13 = 1
            r0 = r0 & r13
            if (r0 != 0) goto L_0x0057
            int r0 = r15 + 8
            r19 = r0
        L_0x0053:
            r0 = r21
            goto L_0x0167
        L_0x0057:
            r27 = r1
            r28 = r2
            r13 = r3
            r29 = r4
            r30 = r5
            r22 = r7
        L_0x0062:
            r26 = r21
            r21 = r6
            goto L_0x0158
        L_0x0068:
            java.lang.String r0 = "Exceptions"
            boolean r0 = r0.equals(r13)
            if (r0 == 0) goto L_0x0090
            int r0 = r15 + 8
            int r0 = r8.readUnsignedShort(r0)
            java.lang.String[] r6 = new java.lang.String[r0]
            int r13 = r15 + 10
            r20 = r1
            r1 = r13
            r13 = 0
        L_0x007e:
            if (r13 >= r0) goto L_0x008b
            java.lang.String r17 = r8.readClass(r1, r10)
            r6[r13] = r17
            int r1 = r1 + 2
            int r13 = r13 + 1
            goto L_0x007e
        L_0x008b:
            r17 = r1
        L_0x008d:
            r1 = r20
            goto L_0x0053
        L_0x0090:
            r20 = r1
            java.lang.String r0 = "Signature"
            boolean r0 = r0.equals(r13)
            if (r0 == 0) goto L_0x00a1
            int r0 = r15 + 8
            java.lang.String r7 = r8.readUTF8(r0, r10)
            goto L_0x008d
        L_0x00a1:
            java.lang.String r0 = "Deprecated"
            boolean r0 = r0.equals(r13)
            if (r0 == 0) goto L_0x00bc
            int r0 = r9.e
            r1 = 131072(0x20000, float:1.83671E-40)
        L_0x00ad:
            r0 = r0 | r1
            r9.e = r0
            r28 = r2
            r13 = r3
            r29 = r4
            r30 = r5
            r22 = r7
            r27 = r20
            goto L_0x0062
        L_0x00bc:
            java.lang.String r0 = "RuntimeVisibleAnnotations"
            boolean r0 = r0.equals(r13)
            if (r0 == 0) goto L_0x00c7
            int r5 = r15 + 8
            goto L_0x008d
        L_0x00c7:
            java.lang.String r0 = "RuntimeVisibleTypeAnnotations"
            boolean r0 = r0.equals(r13)
            if (r0 == 0) goto L_0x00d2
            int r2 = r15 + 8
            goto L_0x008d
        L_0x00d2:
            java.lang.String r0 = "AnnotationDefault"
            boolean r0 = r0.equals(r13)
            if (r0 == 0) goto L_0x00dd
            int r3 = r15 + 8
            goto L_0x008d
        L_0x00dd:
            java.lang.String r0 = "Synthetic"
            boolean r0 = r0.equals(r13)
            if (r0 == 0) goto L_0x00eb
            int r0 = r9.e
            r1 = 266240(0x41000, float:3.73082E-40)
            goto L_0x00ad
        L_0x00eb:
            java.lang.String r0 = "RuntimeInvisibleAnnotations"
            boolean r0 = r0.equals(r13)
            if (r0 == 0) goto L_0x00f6
            int r4 = r15 + 8
            goto L_0x008d
        L_0x00f6:
            java.lang.String r0 = "RuntimeInvisibleTypeAnnotations"
            boolean r0 = r0.equals(r13)
            if (r0 == 0) goto L_0x0102
            int r1 = r15 + 8
            goto L_0x0053
        L_0x0102:
            java.lang.String r0 = "RuntimeVisibleParameterAnnotations"
            boolean r0 = r0.equals(r13)
            if (r0 == 0) goto L_0x010d
            int r18 = r15 + 8
            goto L_0x008d
        L_0x010d:
            java.lang.String r0 = "RuntimeInvisibleParameterAnnotations"
            boolean r0 = r0.equals(r13)
            if (r0 == 0) goto L_0x011a
            int r0 = r15 + 8
            r1 = r20
            goto L_0x0167
        L_0x011a:
            java.lang.String r0 = "MethodParameters"
            boolean r0 = r0.equals(r13)
            if (r0 == 0) goto L_0x0126
            int r16 = r15 + 8
            goto L_0x008d
        L_0x0126:
            org.objectweb.asm.Attribute[] r1 = r9.a
            int r22 = r15 + 8
            int r0 = r15 + 4
            int r23 = r8.readInt(r0)
            r24 = -1
            r25 = 0
            r26 = r21
            r0 = r31
            r27 = r20
            r28 = r2
            r2 = r13
            r13 = r3
            r3 = r22
            r29 = r4
            r4 = r23
            r30 = r5
            r5 = r10
            r21 = r6
            r6 = r24
            r22 = r7
            r7 = r25
            org.objectweb.asm.Attribute r0 = r0.a(r1, r2, r3, r4, r5, r6, r7)
            if (r0 == 0) goto L_0x0158
            r0.a = r12
            r12 = r0
        L_0x0158:
            r3 = r13
            r6 = r21
            r7 = r22
            r0 = r26
            r1 = r27
            r2 = r28
            r4 = r29
            r5 = r30
        L_0x0167:
            int r13 = r15 + 4
            int r13 = r8.readInt(r13)
            int r13 = r13 + 6
            int r15 = r15 + r13
            int r14 = r14 + -1
            goto L_0x0037
        L_0x0174:
            r26 = r0
            r27 = r1
            r28 = r2
            r13 = r3
            r29 = r4
            r30 = r5
            r21 = r6
            r22 = r7
            int r15 = r15 + 2
            int r1 = r9.e
            java.lang.String r2 = r9.f
            java.lang.String r3 = r9.g
            r0 = r32
            r4 = r22
            r5 = r21
            org.objectweb.asm.MethodVisitor r0 = r0.visitMethod(r1, r2, r3, r4, r5)
            if (r0 != 0) goto L_0x0198
            return r15
        L_0x0198:
            boolean r1 = r0 instanceof org.objectweb.asm.MethodWriter
            if (r1 == 0) goto L_0x01d7
            r1 = r0
            org.objectweb.asm.MethodWriter r1 = (org.objectweb.asm.MethodWriter) r1
            org.objectweb.asm.ClassWriter r2 = r1.b
            org.objectweb.asm.ClassReader r2 = r2.M
            if (r2 != r8) goto L_0x01d7
            java.lang.String r2 = r1.g
            r7 = r22
            if (r7 != r2) goto L_0x01d7
            if (r21 != 0) goto L_0x01b2
            int r2 = r1.j
            if (r2 != 0) goto L_0x01d7
            goto L_0x01d0
        L_0x01b2:
            r6 = r21
            int r2 = r6.length
            int r3 = r1.j
            if (r2 != r3) goto L_0x01d7
            int r2 = r6.length
            r3 = 1
            int r2 = r2 - r3
        L_0x01bc:
            if (r2 < 0) goto L_0x01d0
            int r3 = r17 + -2
            int[] r4 = r1.k
            r4 = r4[r2]
            int r5 = r8.readUnsignedShort(r3)
            if (r4 == r5) goto L_0x01cb
            goto L_0x01d7
        L_0x01cb:
            int r2 = r2 + -1
            r17 = r3
            goto L_0x01bc
        L_0x01d0:
            r1.h = r11
            int r0 = r15 - r11
            r1.i = r0
            return r15
        L_0x01d7:
            if (r16 == 0) goto L_0x01f8
            byte[] r1 = r8.b
            byte r1 = r1[r16]
            r1 = r1 & 255(0xff, float:3.57E-43)
            r2 = 1
            int r16 = r16 + 1
            r2 = r16
        L_0x01e4:
            if (r1 <= 0) goto L_0x01f8
            java.lang.String r3 = r8.readUTF8(r2, r10)
            int r4 = r2 + 2
            int r4 = r8.readUnsignedShort(r4)
            r0.visitParameter(r3, r4)
            int r1 = r1 + -1
            int r2 = r2 + 4
            goto L_0x01e4
        L_0x01f8:
            if (r13 == 0) goto L_0x0207
            org.objectweb.asm.AnnotationVisitor r1 = r0.visitAnnotationDefault()
            r2 = 0
            r8.a((int) r13, (char[]) r10, (java.lang.String) r2, (org.objectweb.asm.AnnotationVisitor) r1)
            if (r1 == 0) goto L_0x0207
            r1.visitEnd()
        L_0x0207:
            r5 = r30
            if (r5 == 0) goto L_0x0225
            int r1 = r8.readUnsignedShort(r5)
            int r5 = r5 + 2
        L_0x0211:
            if (r1 <= 0) goto L_0x0225
            int r2 = r5 + 2
            java.lang.String r3 = r8.readUTF8(r5, r10)
            r4 = 1
            org.objectweb.asm.AnnotationVisitor r3 = r0.visitAnnotation(r3, r4)
            int r5 = r8.a((int) r2, (char[]) r10, (boolean) r4, (org.objectweb.asm.AnnotationVisitor) r3)
            int r1 = r1 + -1
            goto L_0x0211
        L_0x0225:
            r4 = r29
            if (r4 == 0) goto L_0x0245
            int r1 = r8.readUnsignedShort(r4)
            int r4 = r4 + 2
        L_0x022f:
            if (r1 <= 0) goto L_0x0245
            int r2 = r4 + 2
            java.lang.String r3 = r8.readUTF8(r4, r10)
            r4 = 0
            org.objectweb.asm.AnnotationVisitor r3 = r0.visitAnnotation(r3, r4)
            r4 = 1
            int r2 = r8.a((int) r2, (char[]) r10, (boolean) r4, (org.objectweb.asm.AnnotationVisitor) r3)
            int r1 = r1 + -1
            r4 = r2
            goto L_0x022f
        L_0x0245:
            r2 = r28
            if (r2 == 0) goto L_0x026b
            int r1 = r8.readUnsignedShort(r2)
            int r2 = r2 + 2
        L_0x024f:
            if (r1 <= 0) goto L_0x026b
            int r2 = r8.a(r9, r2)
            int r3 = r2 + 2
            int r4 = r9.i
            org.objectweb.asm.TypePath r5 = r9.j
            java.lang.String r2 = r8.readUTF8(r2, r10)
            r6 = 1
            org.objectweb.asm.AnnotationVisitor r2 = r0.visitTypeAnnotation(r4, r5, r2, r6)
            int r2 = r8.a((int) r3, (char[]) r10, (boolean) r6, (org.objectweb.asm.AnnotationVisitor) r2)
            int r1 = r1 + -1
            goto L_0x024f
        L_0x026b:
            r1 = r27
            if (r1 == 0) goto L_0x0292
            int r2 = r8.readUnsignedShort(r1)
            int r1 = r1 + 2
        L_0x0275:
            if (r2 <= 0) goto L_0x0292
            int r1 = r8.a(r9, r1)
            int r3 = r1 + 2
            int r4 = r9.i
            org.objectweb.asm.TypePath r5 = r9.j
            java.lang.String r1 = r8.readUTF8(r1, r10)
            r6 = 0
            org.objectweb.asm.AnnotationVisitor r1 = r0.visitTypeAnnotation(r4, r5, r1, r6)
            r4 = 1
            int r1 = r8.a((int) r3, (char[]) r10, (boolean) r4, (org.objectweb.asm.AnnotationVisitor) r1)
            int r2 = r2 + -1
            goto L_0x0275
        L_0x0292:
            r4 = 1
            r1 = r18
            if (r1 == 0) goto L_0x029a
            r8.b(r0, r9, r1, r4)
        L_0x029a:
            r1 = r26
            if (r1 == 0) goto L_0x02a2
            r2 = 0
            r8.b(r0, r9, r1, r2)
        L_0x02a2:
            if (r12 == 0) goto L_0x02ae
            org.objectweb.asm.Attribute r1 = r12.a
            r2 = 0
            r12.a = r2
            r0.visitAttribute(r12)
            r12 = r1
            goto L_0x02a2
        L_0x02ae:
            r12 = r19
            if (r12 == 0) goto L_0x02b8
            r0.visitCode()
            r8.a((org.objectweb.asm.MethodVisitor) r0, (org.objectweb.asm.Context) r9, (int) r12)
        L_0x02b8:
            r0.visitEnd()
            return r15
        */
        throw new UnsupportedOperationException("Method not decompiled: org.objectweb.asm.ClassReader.b(org.objectweb.asm.ClassVisitor, org.objectweb.asm.Context, int):int");
    }

    private void b(MethodVisitor methodVisitor, Context context, int i, boolean z) {
        int i2 = i + 1;
        byte b2 = this.b[i] & 255;
        int length = Type.getArgumentTypes(context.g).length - b2;
        int i3 = 0;
        while (i3 < length) {
            AnnotationVisitor visitParameterAnnotation = methodVisitor.visitParameterAnnotation(i3, "Ljava/lang/Synthetic;", false);
            if (visitParameterAnnotation != null) {
                visitParameterAnnotation.visitEnd();
            }
            i3++;
        }
        char[] cArr = context.c;
        while (i3 < b2 + length) {
            i2 += 2;
            for (int readUnsignedShort = readUnsignedShort(i2); readUnsignedShort > 0; readUnsignedShort--) {
                i2 = a(i2 + 2, cArr, true, methodVisitor.visitParameterAnnotation(i3, readUTF8(i2, cArr), z));
            }
            i3++;
        }
    }

    public void accept(ClassVisitor classVisitor, int i) {
        accept(classVisitor, new Attribute[0], i);
    }

    public void accept(ClassVisitor classVisitor, Attribute[] attributeArr, int i) {
        String[] strArr;
        String str;
        String str2;
        String str3;
        int i2;
        Attribute attribute;
        int i3;
        ClassVisitor classVisitor2 = classVisitor;
        int i4 = i;
        int i5 = this.header;
        char[] cArr = new char[this.d];
        Context context = new Context();
        context.a = attributeArr;
        context.b = i4;
        context.c = cArr;
        int readUnsignedShort = readUnsignedShort(i5);
        String readClass = readClass(i5 + 2, cArr);
        String readClass2 = readClass(i5 + 4, cArr);
        int readUnsignedShort2 = readUnsignedShort(i5 + 6);
        String[] strArr2 = new String[readUnsignedShort2];
        int i6 = i5 + 8;
        for (int i7 = 0; i7 < readUnsignedShort2; i7++) {
            strArr2[i7] = readClass(i6, cArr);
            i6 += 2;
        }
        int a2 = a();
        int i8 = a2;
        int i9 = readUnsignedShort;
        int readUnsignedShort3 = readUnsignedShort(a2);
        int i10 = readUnsignedShort2;
        int i11 = 0;
        String str4 = null;
        String str5 = null;
        String str6 = null;
        String str7 = null;
        String str8 = null;
        String str9 = null;
        int i12 = 0;
        int i13 = 0;
        int i14 = 0;
        int i15 = 0;
        Attribute attribute2 = null;
        while (readUnsignedShort3 > 0) {
            String readUTF8 = readUTF8(i8 + 2, cArr);
            if ("SourceFile".equals(readUTF8)) {
                str6 = readUTF8(i8 + 8, cArr);
            } else if ("InnerClasses".equals(readUTF8)) {
                i15 = i8 + 8;
            } else if ("EnclosingMethod".equals(readUTF8)) {
                String readClass3 = readClass(i8 + 8, cArr);
                int readUnsignedShort4 = readUnsignedShort(i8 + 10);
                if (readUnsignedShort4 != 0) {
                    str9 = readUTF8(this.a[readUnsignedShort4], cArr);
                    str4 = readUTF8(this.a[readUnsignedShort4] + 2, cArr);
                }
                str8 = readClass3;
            } else if ("Signature".equals(readUTF8)) {
                str7 = readUTF8(i8 + 8, cArr);
            } else if ("RuntimeVisibleAnnotations".equals(readUTF8)) {
                i11 = i8 + 8;
            } else if ("RuntimeVisibleTypeAnnotations".equals(readUTF8)) {
                i13 = i8 + 8;
            } else {
                if ("Deprecated".equals(readUTF8)) {
                    i3 = 131072;
                } else if ("Synthetic".equals(readUTF8)) {
                    i3 = 266240;
                } else if ("SourceDebugExtension".equals(readUTF8)) {
                    int readInt = readInt(i8 + 4);
                    str5 = a(i8 + 8, readInt, new char[readInt]);
                } else if ("RuntimeInvisibleAnnotations".equals(readUTF8)) {
                    i12 = i8 + 8;
                } else if ("RuntimeInvisibleTypeAnnotations".equals(readUTF8)) {
                    i14 = i8 + 8;
                } else {
                    if ("BootstrapMethods".equals(readUTF8)) {
                        int readUnsignedShort5 = readUnsignedShort(i8 + 8);
                        int[] iArr = new int[readUnsignedShort5];
                        int i16 = i8 + 10;
                        int i17 = 0;
                        while (i17 < readUnsignedShort5) {
                            iArr[i17] = i16;
                            i16 += (readUnsignedShort(i16 + 2) + 2) << 1;
                            i17++;
                            i11 = i11;
                        }
                        context.d = iArr;
                        str3 = str4;
                        str2 = str5;
                        str = str6;
                        strArr = strArr2;
                        attribute = attribute2;
                        i2 = i11;
                        attribute2 = attribute;
                    } else {
                        i2 = i11;
                        str3 = str4;
                        str2 = str5;
                        str = str6;
                        strArr = strArr2;
                        Attribute a3 = a(attributeArr, readUTF8, i8 + 8, readInt(i8 + 4), cArr, -1, (Label[]) null);
                        attribute = attribute2;
                        if (a3 != null) {
                            a3.a = attribute;
                            attribute2 = a3;
                        }
                        attribute2 = attribute;
                    }
                    i11 = i2;
                    str4 = str3;
                    str5 = str2;
                    str6 = str;
                    i8 += readInt(i8 + 4) + 6;
                    readUnsignedShort3--;
                    Attribute[] attributeArr2 = attributeArr;
                    strArr2 = strArr;
                }
                i9 |= i3;
            }
            strArr = strArr2;
            i8 += readInt(i8 + 4) + 6;
            readUnsignedShort3--;
            Attribute[] attributeArr22 = attributeArr;
            strArr2 = strArr;
        }
        int i18 = i11;
        String str10 = str4;
        String str11 = str5;
        String str12 = str6;
        String[] strArr3 = strArr2;
        Attribute attribute3 = attribute2;
        classVisitor.visit(readInt(this.a[1] - 7), i9, readClass, str7, readClass2, strArr2);
        if ((i4 & 2) == 0) {
            String str13 = str12;
            String str14 = str11;
            if (!(str13 == null && str14 == null)) {
                classVisitor2.visitSource(str13, str14);
            }
        }
        String str15 = str8;
        if (str15 != null) {
            classVisitor2.visitOuterClass(str15, str9, str10);
        }
        int i19 = i18;
        if (i19 != 0) {
            int i20 = i19 + 2;
            for (int readUnsignedShort6 = readUnsignedShort(i19); readUnsignedShort6 > 0; readUnsignedShort6--) {
                i20 = a(i20 + 2, cArr, true, classVisitor2.visitAnnotation(readUTF8(i20, cArr), true));
            }
        }
        int i21 = i12;
        if (i21 != 0) {
            int i22 = i21 + 2;
            for (int readUnsignedShort7 = readUnsignedShort(i21); readUnsignedShort7 > 0; readUnsignedShort7--) {
                i22 = a(i22 + 2, cArr, true, classVisitor2.visitAnnotation(readUTF8(i22, cArr), false));
            }
        }
        int i23 = i13;
        if (i23 != 0) {
            int i24 = i23 + 2;
            for (int readUnsignedShort8 = readUnsignedShort(i23); readUnsignedShort8 > 0; readUnsignedShort8--) {
                int a4 = a(context, i24);
                i24 = a(a4 + 2, cArr, true, classVisitor2.visitTypeAnnotation(context.i, context.j, readUTF8(a4, cArr), true));
            }
        }
        int i25 = i14;
        if (i25 != 0) {
            int i26 = i25 + 2;
            for (int readUnsignedShort9 = readUnsignedShort(i25); readUnsignedShort9 > 0; readUnsignedShort9--) {
                int a5 = a(context, i26);
                i26 = a(a5 + 2, cArr, true, classVisitor2.visitTypeAnnotation(context.i, context.j, readUTF8(a5, cArr), false));
            }
        }
        while (attribute3 != null) {
            Attribute attribute4 = attribute3.a;
            attribute3.a = null;
            classVisitor2.visitAttribute(attribute3);
            attribute3 = attribute4;
        }
        int i27 = i15;
        if (i27 != 0) {
            int i28 = i27 + 2;
            for (int readUnsignedShort10 = readUnsignedShort(i27); readUnsignedShort10 > 0; readUnsignedShort10--) {
                classVisitor2.visitInnerClass(readClass(i28, cArr), readClass(i28 + 2, cArr), readUTF8(i28 + 4, cArr), readUnsignedShort(i28 + 6));
                i28 += 8;
            }
        }
        int i29 = this.header + 10 + (i10 * 2);
        for (int readUnsignedShort11 = readUnsignedShort(i29 - 2); readUnsignedShort11 > 0; readUnsignedShort11--) {
            i29 = a(classVisitor2, context, i29);
        }
        int i30 = i29 + 2;
        for (int readUnsignedShort12 = readUnsignedShort(i29); readUnsignedShort12 > 0; readUnsignedShort12--) {
            i30 = b(classVisitor2, context, i30);
        }
        classVisitor.visitEnd();
    }

    public int readByte(int i) {
        return this.b[i] & 255;
    }

    public String readClass(int i, char[] cArr) {
        return readUTF8(this.a[readUnsignedShort(i)], cArr);
    }

    public Object readConst(int i, char[] cArr) {
        int i2 = this.a[i];
        byte b2 = this.b[i2 - 1];
        if (b2 == 16) {
            return Type.getMethodType(readUTF8(i2, cArr));
        }
        switch (b2) {
            case 3:
                return new Integer(readInt(i2));
            case 4:
                return new Float(Float.intBitsToFloat(readInt(i2)));
            case 5:
                return new Long(readLong(i2));
            case 6:
                return new Double(Double.longBitsToDouble(readLong(i2)));
            case 7:
                return Type.getObjectType(readUTF8(i2, cArr));
            case 8:
                return readUTF8(i2, cArr);
            default:
                int readByte = readByte(i2);
                int[] iArr = this.a;
                int i3 = iArr[readUnsignedShort(i2 + 1)];
                String readClass = readClass(i3, cArr);
                int i4 = iArr[readUnsignedShort(i3 + 2)];
                return new Handle(readByte, readClass, readUTF8(i4, cArr), readUTF8(i4 + 2, cArr));
        }
    }

    public int readInt(int i) {
        byte[] bArr = this.b;
        return (bArr[i + 3] & 255) | ((bArr[i] & 255) << 24) | ((bArr[i + 1] & 255) << 16) | ((bArr[i + 2] & 255) << 8);
    }

    /* access modifiers changed from: protected */
    public Label readLabel(int i, Label[] labelArr) {
        if (labelArr[i] == null) {
            labelArr[i] = new Label();
        }
        return labelArr[i];
    }

    public long readLong(int i) {
        return (((long) readInt(i)) << 32) | (((long) readInt(i + 4)) & 4294967295L);
    }

    public short readShort(int i) {
        byte[] bArr = this.b;
        return (short) ((bArr[i + 1] & 255) | ((bArr[i] & 255) << 8));
    }

    public String readUTF8(int i, char[] cArr) {
        int readUnsignedShort = readUnsignedShort(i);
        if (i == 0 || readUnsignedShort == 0) {
            return null;
        }
        String[] strArr = this.c;
        String str = strArr[readUnsignedShort];
        if (str != null) {
            return str;
        }
        int i2 = this.a[readUnsignedShort];
        String a2 = a(i2 + 2, readUnsignedShort(i2), cArr);
        strArr[readUnsignedShort] = a2;
        return a2;
    }

    public int readUnsignedShort(int i) {
        byte[] bArr = this.b;
        return (bArr[i + 1] & 255) | ((bArr[i] & 255) << 8);
    }
}
