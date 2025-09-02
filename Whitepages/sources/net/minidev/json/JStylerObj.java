package net.minidev.json;

import com.facebook.hermes.intl.Constants;
import java.io.IOException;

abstract class JStylerObj {
    public static final Escape4Web ESCAPE4Web = new Escape4Web((Escape4Web) null);
    public static final EscapeLT ESCAPE_LT = new EscapeLT((EscapeLT) null);
    public static final MPAgressive MP_AGGRESIVE = new MPAgressive((MPAgressive) null);
    public static final MPSimple MP_SIMPLE = new MPSimple((MPSimple) null);
    public static final MPTrue MP_TRUE = new MPTrue((MPTrue) null);

    public interface MustProtect {
        boolean mustBeProtect(String str);
    }

    public interface StringProtector {
        void escape(String str, Appendable appendable);
    }

    public static boolean isSpace(char c) {
        return c == 13 || c == 10 || c == 9 || c == ' ';
    }

    public static boolean isSpecial(char c) {
        return c == '{' || c == '[' || c == ',' || c == '}' || c == ']' || c == ':' || c == '\'' || c == '\"';
    }

    public static boolean isSpecialChar(char c) {
        return c == 8 || c == 12 || c == 10;
    }

    public static boolean isSpecialClose(char c) {
        return c == '}' || c == ']' || c == ',' || c == ':';
    }

    public static boolean isUnicode(char c) {
        if (c >= 0 && c <= 31) {
            return true;
        }
        if (c < 127 || c > 159) {
            return c >= 8192 && c <= 8447;
        }
        return true;
    }

    private static class MPTrue implements MustProtect {
        public boolean mustBeProtect(String str) {
            return true;
        }

        private MPTrue() {
        }

        /* synthetic */ MPTrue(MPTrue mPTrue) {
            this();
        }
    }

    private static class MPSimple implements MustProtect {
        private MPSimple() {
        }

        /* synthetic */ MPSimple(MPSimple mPSimple) {
            this();
        }

        public boolean mustBeProtect(String str) {
            if (str == null) {
                return false;
            }
            int length = str.length();
            if (length == 0 || str.trim() != str) {
                return true;
            }
            char charAt = str.charAt(0);
            if ((charAt >= '0' && charAt <= '9') || charAt == '-') {
                return true;
            }
            for (int i = 0; i < length; i++) {
                char charAt2 = str.charAt(i);
                if (JStylerObj.isSpace(charAt2) || JStylerObj.isSpecial(charAt2) || JStylerObj.isSpecialChar(charAt2) || JStylerObj.isUnicode(charAt2)) {
                    return true;
                }
            }
            return JStylerObj.isKeyword(str);
        }
    }

    private static class MPAgressive implements MustProtect {
        private MPAgressive() {
        }

        /* synthetic */ MPAgressive(MPAgressive mPAgressive) {
            this();
        }

        /* JADX WARNING: Code restructure failed: missing block: B:31:0x0054, code lost:
            if (r3 == '.') goto L_0x0056;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:32:0x0056, code lost:
            r7 = r7 + 1;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:33:0x0058, code lost:
            if (r7 < r1) goto L_0x005b;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:34:0x005b, code lost:
            r3 = r11.charAt(r7);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:35:0x005f, code lost:
            if (r3 < '0') goto L_0x0063;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:36:0x0061, code lost:
            if (r3 <= '9') goto L_0x0056;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:37:0x0063, code lost:
            if (r7 != r1) goto L_0x0066;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:38:0x0065, code lost:
            return true;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:40:0x0068, code lost:
            if (r3 == 'E') goto L_0x006e;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:42:0x006c, code lost:
            if (r3 != 'e') goto L_0x0085;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:43:0x006e, code lost:
            r3 = r7 + 1;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:44:0x0070, code lost:
            if (r3 != r1) goto L_0x0073;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:45:0x0072, code lost:
            return false;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:46:0x0073, code lost:
            r8 = r11.charAt(r3);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:47:0x0079, code lost:
            if (r8 == '+') goto L_0x0080;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:48:0x007b, code lost:
            if (r8 != '-') goto L_0x007e;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:49:0x007e, code lost:
            r7 = r3;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:50:0x0080, code lost:
            r7 = r7 + 2;
            r11.charAt(r7);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:51:0x0085, code lost:
            if (r7 != r1) goto L_0x0088;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:52:0x0087, code lost:
            return false;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:53:0x0088, code lost:
            if (r7 < r1) goto L_0x008b;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:54:0x008b, code lost:
            r3 = r11.charAt(r7);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:55:0x008f, code lost:
            if (r3 < '0') goto L_0x0097;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:56:0x0091, code lost:
            if (r3 <= '9') goto L_0x0094;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:57:0x0094, code lost:
            r7 = r7 + 1;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:58:0x0097, code lost:
            if (r7 != r1) goto L_0x009a;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:59:0x0099, code lost:
            return true;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean mustBeProtect(java.lang.String r11) {
            /*
                r10 = this;
                r0 = 0
                if (r11 != 0) goto L_0x0004
                return r0
            L_0x0004:
                int r1 = r11.length()
                r2 = 1
                if (r1 != 0) goto L_0x000c
                return r2
            L_0x000c:
                java.lang.String r3 = r11.trim()
                if (r3 == r11) goto L_0x0013
                return r2
            L_0x0013:
                char r3 = r11.charAt(r0)
                boolean r4 = net.minidev.json.JStylerObj.isSpecial(r3)
                if (r4 != 0) goto L_0x00b0
                boolean r3 = net.minidev.json.JStylerObj.isUnicode(r3)
                if (r3 == 0) goto L_0x0025
                goto L_0x00b0
            L_0x0025:
                r3 = r2
            L_0x0026:
                if (r3 < r1) goto L_0x009b
                boolean r3 = net.minidev.json.JStylerObj.isKeyword(r11)
                if (r3 == 0) goto L_0x002f
                return r2
            L_0x002f:
                char r3 = r11.charAt(r0)
                r4 = 45
                r5 = 57
                r6 = 48
                if (r3 < r6) goto L_0x003d
                if (r3 <= r5) goto L_0x003f
            L_0x003d:
                if (r3 != r4) goto L_0x009a
            L_0x003f:
                r7 = r2
            L_0x0040:
                if (r7 < r1) goto L_0x0043
                goto L_0x004f
            L_0x0043:
                char r3 = r11.charAt(r7)
                if (r3 < r6) goto L_0x004f
                if (r3 <= r5) goto L_0x004c
                goto L_0x004f
            L_0x004c:
                int r7 = r7 + 1
                goto L_0x0040
            L_0x004f:
                if (r7 != r1) goto L_0x0052
                return r2
            L_0x0052:
                r8 = 46
                if (r3 != r8) goto L_0x0058
            L_0x0056:
                int r7 = r7 + 1
            L_0x0058:
                if (r7 < r1) goto L_0x005b
                goto L_0x0063
            L_0x005b:
                char r3 = r11.charAt(r7)
                if (r3 < r6) goto L_0x0063
                if (r3 <= r5) goto L_0x0056
            L_0x0063:
                if (r7 != r1) goto L_0x0066
                return r2
            L_0x0066:
                r8 = 69
                if (r3 == r8) goto L_0x006e
                r8 = 101(0x65, float:1.42E-43)
                if (r3 != r8) goto L_0x0085
            L_0x006e:
                int r3 = r7 + 1
                if (r3 != r1) goto L_0x0073
                return r0
            L_0x0073:
                char r8 = r11.charAt(r3)
                r9 = 43
                if (r8 == r9) goto L_0x0080
                if (r8 != r4) goto L_0x007e
                goto L_0x0080
            L_0x007e:
                r7 = r3
                goto L_0x0085
            L_0x0080:
                int r7 = r7 + 2
                r11.charAt(r7)
            L_0x0085:
                if (r7 != r1) goto L_0x0088
                return r0
            L_0x0088:
                if (r7 < r1) goto L_0x008b
                goto L_0x0097
            L_0x008b:
                char r3 = r11.charAt(r7)
                if (r3 < r6) goto L_0x0097
                if (r3 <= r5) goto L_0x0094
                goto L_0x0097
            L_0x0094:
                int r7 = r7 + 1
                goto L_0x0088
            L_0x0097:
                if (r7 != r1) goto L_0x009a
                return r2
            L_0x009a:
                return r0
            L_0x009b:
                char r4 = r11.charAt(r3)
                boolean r5 = net.minidev.json.JStylerObj.isSpecialClose(r4)
                if (r5 != 0) goto L_0x00b0
                boolean r4 = net.minidev.json.JStylerObj.isUnicode(r4)
                if (r4 == 0) goto L_0x00ac
                goto L_0x00b0
            L_0x00ac:
                int r3 = r3 + 1
                goto L_0x0026
            L_0x00b0:
                return r2
            */
            throw new UnsupportedOperationException("Method not decompiled: net.minidev.json.JStylerObj.MPAgressive.mustBeProtect(java.lang.String):boolean");
        }
    }

    public static boolean isKeyword(String str) {
        if (str.length() < 3) {
            return false;
        }
        char charAt = str.charAt(0);
        if (charAt == 'n') {
            return str.equals("null");
        }
        if (charAt == 't') {
            return str.equals("true");
        }
        if (charAt == 'f') {
            return str.equals(Constants.CASEFIRST_FALSE);
        }
        if (charAt == 'N') {
            return str.equals("NaN");
        }
        return false;
    }

    private static class EscapeLT implements StringProtector {
        private EscapeLT() {
        }

        /* synthetic */ EscapeLT(EscapeLT escapeLT) {
            this();
        }

        public void escape(String str, Appendable appendable) {
            try {
                int length = str.length();
                for (int i = 0; i < length; i++) {
                    char charAt = str.charAt(i);
                    if (charAt == 12) {
                        appendable.append("\\f");
                    } else if (charAt == 13) {
                        appendable.append("\\r");
                    } else if (charAt == '\"') {
                        appendable.append("\\\"");
                    } else if (charAt != '\\') {
                        switch (charAt) {
                            case 8:
                                appendable.append("\\b");
                                break;
                            case 9:
                                appendable.append("\\t");
                                break;
                            case 10:
                                appendable.append("\\n");
                                break;
                            default:
                                if ((charAt >= 0 && charAt <= 31) || ((charAt >= 127 && charAt <= 159) || (charAt >= 8192 && charAt <= 8447))) {
                                    appendable.append("\\u");
                                    appendable.append("0123456789ABCDEF".charAt((charAt >> 12) & 15));
                                    appendable.append("0123456789ABCDEF".charAt((charAt >> 8) & 15));
                                    appendable.append("0123456789ABCDEF".charAt((charAt >> 4) & 15));
                                    appendable.append("0123456789ABCDEF".charAt(charAt & 15));
                                    break;
                                } else {
                                    appendable.append(charAt);
                                    break;
                                }
                        }
                    } else {
                        appendable.append("\\\\");
                    }
                }
            } catch (IOException unused) {
                throw new RuntimeException("Impossible Exeption");
            }
        }
    }

    private static class Escape4Web implements StringProtector {
        private Escape4Web() {
        }

        /* synthetic */ Escape4Web(Escape4Web escape4Web) {
            this();
        }

        public void escape(String str, Appendable appendable) {
            try {
                int length = str.length();
                for (int i = 0; i < length; i++) {
                    char charAt = str.charAt(i);
                    if (charAt == 12) {
                        appendable.append("\\f");
                    } else if (charAt == 13) {
                        appendable.append("\\r");
                    } else if (charAt == '\"') {
                        appendable.append("\\\"");
                    } else if (charAt == '/') {
                        appendable.append("\\/");
                    } else if (charAt != '\\') {
                        switch (charAt) {
                            case 8:
                                appendable.append("\\b");
                                break;
                            case 9:
                                appendable.append("\\t");
                                break;
                            case 10:
                                appendable.append("\\n");
                                break;
                            default:
                                if ((charAt >= 0 && charAt <= 31) || ((charAt >= 127 && charAt <= 159) || (charAt >= 8192 && charAt <= 8447))) {
                                    appendable.append("\\u");
                                    appendable.append("0123456789ABCDEF".charAt((charAt >> 12) & 15));
                                    appendable.append("0123456789ABCDEF".charAt((charAt >> 8) & 15));
                                    appendable.append("0123456789ABCDEF".charAt((charAt >> 4) & 15));
                                    appendable.append("0123456789ABCDEF".charAt(charAt & 15));
                                    break;
                                } else {
                                    appendable.append(charAt);
                                    break;
                                }
                                break;
                        }
                    } else {
                        appendable.append("\\\\");
                    }
                }
            } catch (IOException unused) {
                throw new RuntimeException("Impossible Error");
            }
        }
    }
}
