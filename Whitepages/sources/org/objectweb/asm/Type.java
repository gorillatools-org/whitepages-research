package org.objectweb.asm;

import java.lang.reflect.Method;

public class Type {
    public static final Type BOOLEAN_TYPE = new Type(1, (char[]) null, 1509950721, 1);
    public static final Type BYTE_TYPE = new Type(3, (char[]) null, 1107297537, 1);
    public static final Type CHAR_TYPE = new Type(2, (char[]) null, 1124075009, 1);
    public static final Type DOUBLE_TYPE = new Type(8, (char[]) null, 1141048066, 1);
    public static final Type FLOAT_TYPE = new Type(6, (char[]) null, 1174536705, 1);
    public static final Type INT_TYPE = new Type(5, (char[]) null, 1224736769, 1);
    public static final Type LONG_TYPE = new Type(7, (char[]) null, 1241579778, 1);
    public static final Type SHORT_TYPE = new Type(4, (char[]) null, 1392510721, 1);
    public static final Type VOID_TYPE = new Type(0, (char[]) null, 1443168256, 1);
    private final int a;
    private final char[] b;
    private final int c;
    private final int d;

    static {
        _clinit_();
    }

    private Type(int i, char[] cArr, int i2, int i3) {
        this.a = i;
        this.b = cArr;
        this.c = i2;
        this.d = i3;
    }

    static /* synthetic */ void _clinit_() {
    }

    private static Type a(char[] cArr, int i) {
        char c2;
        char c3 = cArr[i];
        if (c3 == 'F') {
            return FLOAT_TYPE;
        }
        if (c3 == 'L') {
            int i2 = 1;
            while (cArr[i + i2] != ';') {
                i2++;
            }
            return new Type(10, cArr, i + 1, i2 - 1);
        } else if (c3 == 'S') {
            return SHORT_TYPE;
        } else {
            if (c3 == 'V') {
                return VOID_TYPE;
            }
            if (c3 == 'I') {
                return INT_TYPE;
            }
            if (c3 == 'J') {
                return LONG_TYPE;
            }
            if (c3 == 'Z') {
                return BOOLEAN_TYPE;
            }
            if (c3 != '[') {
                switch (c3) {
                    case 'B':
                        return BYTE_TYPE;
                    case 'C':
                        return CHAR_TYPE;
                    case 'D':
                        return DOUBLE_TYPE;
                    default:
                        return new Type(11, cArr, i, cArr.length - i);
                }
            } else {
                int i3 = 1;
                while (true) {
                    c2 = cArr[i + i3];
                    if (c2 != '[') {
                        break;
                    }
                    i3++;
                }
                if (c2 == 'L') {
                    do {
                        i3++;
                    } while (cArr[i + i3] != ';');
                }
                return new Type(9, cArr, i, i3 + 1);
            }
        }
    }

    private void a(StringBuffer stringBuffer) {
        char c2;
        char[] cArr = this.b;
        if (cArr == null) {
            c2 = (char) ((this.c & -16777216) >>> 24);
        } else if (this.a == 10) {
            stringBuffer.append('L');
            stringBuffer.append(this.b, this.c, this.d);
            c2 = ';';
        } else {
            stringBuffer.append(cArr, this.c, this.d);
            return;
        }
        stringBuffer.append(c2);
    }

    private static void a(StringBuffer stringBuffer, Class<?> cls) {
        while (!cls.isPrimitive()) {
            if (cls.isArray()) {
                stringBuffer.append('[');
                cls = cls.getComponentType();
            } else {
                stringBuffer.append('L');
                String name = cls.getName();
                int length = name.length();
                for (int i = 0; i < length; i++) {
                    char charAt = name.charAt(i);
                    if (charAt == '.') {
                        charAt = '/';
                    }
                    stringBuffer.append(charAt);
                }
                stringBuffer.append(';');
                return;
            }
        }
        stringBuffer.append(cls == Integer.TYPE ? 'I' : cls == Void.TYPE ? 'V' : cls == Boolean.TYPE ? 'Z' : cls == Byte.TYPE ? 'B' : cls == Character.TYPE ? 'C' : cls == Short.TYPE ? 'S' : cls == Double.TYPE ? 'D' : cls == Float.TYPE ? 'F' : 'J');
    }

    public static Type[] getArgumentTypes(String str) {
        char[] charArray = str.toCharArray();
        int i = 1;
        int i2 = 1;
        int i3 = 0;
        while (true) {
            int i4 = i2 + 1;
            char c2 = charArray[i2];
            if (c2 == ')') {
                break;
            } else if (c2 == 'L') {
                while (true) {
                    i2 = i4 + 1;
                    if (charArray[i4] == ';') {
                        break;
                    }
                    i4 = i2;
                }
                i3++;
            } else {
                if (c2 != '[') {
                    i3++;
                }
                i2 = i4;
            }
        }
        Type[] typeArr = new Type[i3];
        int i5 = 0;
        while (charArray[i] != ')') {
            Type a2 = a(charArray, i);
            typeArr[i5] = a2;
            i += a2.d + (a2.a == 10 ? 2 : 0);
            i5++;
        }
        return typeArr;
    }

    public static int getArgumentsAndReturnSizes(String str) {
        int i;
        char charAt;
        int i2 = 1;
        int i3 = 1;
        int i4 = 1;
        while (true) {
            i = i3 + 1;
            char charAt2 = str.charAt(i3);
            if (charAt2 == ')') {
                break;
            } else if (charAt2 == 'L') {
                while (true) {
                    i3 = i + 1;
                    if (str.charAt(i) == ';') {
                        break;
                    }
                    i = i3;
                }
                i4++;
            } else {
                if (charAt2 == '[') {
                    while (true) {
                        charAt = str.charAt(i);
                        if (charAt != '[') {
                            break;
                        }
                        i++;
                    }
                    if (charAt == 'D' || charAt == 'J') {
                        i4--;
                    }
                } else {
                    i4 = (charAt2 == 'D' || charAt2 == 'J') ? i4 + 2 : i4 + 1;
                }
                i3 = i;
            }
        }
        char charAt3 = str.charAt(i);
        int i5 = i4 << 2;
        if (charAt3 == 'V') {
            i2 = 0;
        } else if (charAt3 == 'D' || charAt3 == 'J') {
            i2 = 2;
        }
        return i5 | i2;
    }

    public static String getDescriptor(Class cls) {
        StringBuffer stringBuffer = new StringBuffer();
        a(stringBuffer, cls);
        return stringBuffer.toString();
    }

    public static String getInternalName(Class cls) {
        return cls.getName().replace('.', '/');
    }

    public static String getMethodDescriptor(Method method) {
        Class[] parameterTypes = method.getParameterTypes();
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append('(');
        for (Class a2 : parameterTypes) {
            a(stringBuffer, a2);
        }
        stringBuffer.append(')');
        a(stringBuffer, (Class) method.getReturnType());
        return stringBuffer.toString();
    }

    public static Type getMethodType(String str) {
        return a(str.toCharArray(), 0);
    }

    public static Type getObjectType(String str) {
        char[] charArray = str.toCharArray();
        return new Type(charArray[0] == '[' ? 9 : 10, charArray, 0, charArray.length);
    }

    public static Type getType(Class cls) {
        return cls.isPrimitive() ? cls == Integer.TYPE ? INT_TYPE : cls == Void.TYPE ? VOID_TYPE : cls == Boolean.TYPE ? BOOLEAN_TYPE : cls == Byte.TYPE ? BYTE_TYPE : cls == Character.TYPE ? CHAR_TYPE : cls == Short.TYPE ? SHORT_TYPE : cls == Double.TYPE ? DOUBLE_TYPE : cls == Float.TYPE ? FLOAT_TYPE : LONG_TYPE : getType(getDescriptor(cls));
    }

    public static Type getType(String str) {
        return a(str.toCharArray(), 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Type)) {
            return false;
        }
        Type type = (Type) obj;
        int i = this.a;
        if (i != type.a) {
            return false;
        }
        if (i >= 9) {
            int i2 = this.d;
            if (i2 != type.d) {
                return false;
            }
            int i3 = this.c;
            int i4 = type.c;
            int i5 = i2 + i3;
            while (i3 < i5) {
                if (this.b[i3] != type.b[i4]) {
                    return false;
                }
                i3++;
                i4++;
            }
        }
        return true;
    }

    public String getDescriptor() {
        StringBuffer stringBuffer = new StringBuffer();
        a(stringBuffer);
        return stringBuffer.toString();
    }

    public String getInternalName() {
        return new String(this.b, this.c, this.d);
    }

    public int getSort() {
        return this.a;
    }

    public int hashCode() {
        int i = this.a;
        int i2 = i * 13;
        if (i >= 9) {
            int i3 = this.c;
            int i4 = this.d + i3;
            while (i3 < i4) {
                i2 = (i2 + this.b[i3]) * 17;
                i3++;
            }
        }
        return i2;
    }

    public String toString() {
        return getDescriptor();
    }
}
