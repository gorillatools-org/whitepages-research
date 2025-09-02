package net.minidev.json.parser;

import com.facebook.react.uimanager.drawable.InsetBoxShadowDrawableKt;
import com.facebook.react.uimanager.drawable.OutsetBoxShadowDrawableKt;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.CommonStatusCodes;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import net.minidev.json.writer.JsonReader;
import net.minidev.json.writer.JsonReaderI;
import okhttp3.internal.ws.WebSocketProtocol;

abstract class JSONParserBase {
    protected static boolean[] stopAll;
    protected static boolean[] stopArray;
    protected static boolean[] stopKey;
    protected static boolean[] stopValue;
    protected static boolean[] stopX;
    protected final boolean acceptLeadinZero;
    protected final boolean acceptNaN;
    protected final boolean acceptNonQuote;
    protected final boolean acceptSimpleQuote;
    protected final boolean acceptUselessComma;
    JsonReader base;
    protected char c;
    protected final boolean checkTaillingData;
    protected final boolean checkTaillingSpace;
    protected final boolean ignoreControlChar;
    private String lastKey;
    protected int pos;
    protected final boolean reject127;
    protected final MSB sb = new MSB(15);
    protected final boolean useHiPrecisionFloat;
    protected final boolean useIntegerStorage;
    protected Object xo;
    protected String xs;

    /* access modifiers changed from: protected */
    public abstract void read();

    /* access modifiers changed from: protected */
    public abstract void readNQString(boolean[] zArr);

    /* access modifiers changed from: protected */
    public abstract void readNoEnd();

    /* access modifiers changed from: protected */
    public abstract Object readNumber(boolean[] zArr);

    /* access modifiers changed from: package-private */
    public abstract void readS();

    /* access modifiers changed from: protected */
    public abstract void readString();

    static {
        boolean[] zArr = new boolean[WebSocketProtocol.PAYLOAD_SHORT];
        stopAll = zArr;
        boolean[] zArr2 = new boolean[WebSocketProtocol.PAYLOAD_SHORT];
        stopArray = zArr2;
        boolean[] zArr3 = new boolean[WebSocketProtocol.PAYLOAD_SHORT];
        stopKey = zArr3;
        boolean[] zArr4 = new boolean[WebSocketProtocol.PAYLOAD_SHORT];
        stopValue = zArr4;
        boolean[] zArr5 = new boolean[WebSocketProtocol.PAYLOAD_SHORT];
        stopX = zArr5;
        zArr3[26] = true;
        zArr3[58] = true;
        zArr4[26] = true;
        zArr4[125] = true;
        zArr4[44] = true;
        zArr2[26] = true;
        zArr2[93] = true;
        zArr2[44] = true;
        zArr5[26] = true;
        zArr[58] = true;
        zArr[44] = true;
        zArr[26] = true;
        zArr[125] = true;
        zArr[93] = true;
    }

    public JSONParserBase(int i) {
        boolean z = false;
        this.acceptNaN = (i & 4) > 0;
        this.acceptNonQuote = (i & 2) > 0;
        this.acceptSimpleQuote = (i & 1) > 0;
        this.ignoreControlChar = (i & 8) > 0;
        this.useIntegerStorage = (i & 16) > 0;
        this.acceptLeadinZero = (i & 32) > 0;
        this.acceptUselessComma = (i & 64) > 0;
        this.useHiPrecisionFloat = (i & 128) > 0;
        this.checkTaillingData = (i & 768) != 768;
        this.checkTaillingSpace = (i & 512) == 0;
        this.reject127 = (i & 1024) > 0 ? true : z;
    }

    public void checkControleChar() {
        if (!this.ignoreControlChar) {
            int length = this.xs.length();
            for (int i = 0; i < length; i++) {
                char charAt = this.xs.charAt(i);
                if (charAt >= 0) {
                    if (charAt <= 31) {
                        throw new ParseException(this.pos + i, 0, Character.valueOf(charAt));
                    } else if (charAt == 127 && this.reject127) {
                        throw new ParseException(this.pos + i, 0, Character.valueOf(charAt));
                    }
                }
            }
        }
    }

    public void checkLeadinZero() {
        int length = this.xs.length();
        if (length != 1) {
            if (length != 2) {
                char charAt = this.xs.charAt(0);
                char charAt2 = this.xs.charAt(1);
                if (charAt == '-') {
                    char charAt3 = this.xs.charAt(2);
                    if (charAt2 == '0' && charAt3 >= '0' && charAt3 <= '9') {
                        throw new ParseException(this.pos, 6, this.xs);
                    }
                } else if (charAt == '0' && charAt2 >= '0' && charAt2 <= '9') {
                    throw new ParseException(this.pos, 6, this.xs);
                }
            } else if (this.xs.equals("00")) {
                throw new ParseException(this.pos, 6, this.xs);
            }
        }
    }

    /* access modifiers changed from: protected */
    public Number extractFloat() {
        if (!this.acceptLeadinZero) {
            checkLeadinZero();
        }
        if (!this.useHiPrecisionFloat) {
            return Float.valueOf(Float.parseFloat(this.xs));
        }
        if (this.xs.length() > 18) {
            return new BigDecimal(this.xs);
        }
        return Double.valueOf(Double.parseDouble(this.xs));
    }

    /* access modifiers changed from: protected */
    public Object parse(JsonReaderI jsonReaderI) {
        this.pos = -1;
        try {
            read();
            Object readFirst = readFirst(jsonReaderI);
            if (this.checkTaillingData) {
                if (!this.checkTaillingSpace) {
                    skipSpace();
                }
                if (this.c != 26) {
                    throw new ParseException(this.pos - 1, 1, Character.valueOf(this.c));
                }
            }
            this.xs = null;
            this.xo = null;
            return readFirst;
        } catch (IOException e) {
            throw new ParseException(this.pos, e);
        }
    }

    /* access modifiers changed from: protected */
    public Number parseNumber(String str) {
        int i;
        int i2;
        int length = str.length();
        boolean z = false;
        if (str.charAt(0) == '-') {
            if (this.acceptLeadinZero || length < 3 || str.charAt(1) != '0') {
                i = 20;
                i2 = 1;
            } else {
                throw new ParseException(this.pos, 6, str);
            }
        } else if (this.acceptLeadinZero || length < 2 || str.charAt(0) != '0') {
            i = 19;
            i2 = 0;
        } else {
            throw new ParseException(this.pos, 6, str);
        }
        int i3 = i2;
        if (length >= i) {
            if (length > i) {
                return new BigInteger(str, 10);
            }
            length--;
            z = true;
        }
        long j = 0;
        while (i2 < length) {
            j = (j * 10) + ((long) ('0' - str.charAt(i2)));
            i2++;
        }
        if (z) {
            int i4 = (j > -922337203685477580L ? 1 : (j == -922337203685477580L ? 0 : -1));
            if (i4 <= 0 && (i4 < 0 || (i3 == 0 ? str.charAt(i2) > '7' : str.charAt(i2) > '8'))) {
                return new BigInteger(str, 10);
            }
            j = (j * 10) + ((long) ('0' - str.charAt(i2)));
        }
        if (i3 == 0) {
            long j2 = -j;
            if (!this.useIntegerStorage || j2 > 2147483647L) {
                return Long.valueOf(j2);
            }
            return Integer.valueOf((int) j2);
        } else if (!this.useIntegerStorage || j < -2147483648L) {
            return Long.valueOf(j);
        } else {
            return Integer.valueOf((int) j);
        }
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0043, code lost:
        if (r4 == ':') goto L_0x0074;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0047, code lost:
        if (r4 == ']') goto L_0x0057;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x004b, code lost:
        if (r4 == '}') goto L_0x0074;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0057, code lost:
        if (r1 == false) goto L_0x006c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x005b, code lost:
        if (r7.acceptUselessComma == false) goto L_0x005e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x006b, code lost:
        throw new net.minidev.json.parser.ParseException(r7.pos, 0, java.lang.Character.valueOf(r7.c));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x006c, code lost:
        read();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x0073, code lost:
        return r8.convert(r0);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object readArray(net.minidev.json.writer.JsonReaderI r8) {
        /*
            r7 = this;
            java.lang.Object r0 = r8.createArray()
            char r1 = r7.c
            r2 = 91
            if (r1 != r2) goto L_0x00ad
            r7.read()
            char r1 = r7.c
            r2 = 44
            r3 = 0
            if (r1 != r2) goto L_0x0027
            boolean r1 = r7.acceptUselessComma
            if (r1 == 0) goto L_0x0019
            goto L_0x0027
        L_0x0019:
            net.minidev.json.parser.ParseException r8 = new net.minidev.json.parser.ParseException
            int r0 = r7.pos
            char r1 = r7.c
            java.lang.Character r1 = java.lang.Character.valueOf(r1)
            r8.<init>(r0, r3, r1)
            throw r8
        L_0x0027:
            r1 = r3
        L_0x0028:
            char r4 = r7.c
            r5 = 9
            if (r4 == r5) goto L_0x00a8
            r5 = 10
            if (r4 == r5) goto L_0x00a8
            r5 = 13
            if (r4 == r5) goto L_0x00a8
            r5 = 26
            r6 = 1
            if (r4 == r5) goto L_0x009c
            r5 = 32
            if (r4 == r5) goto L_0x00a8
            if (r4 == r2) goto L_0x0082
            r5 = 58
            if (r4 == r5) goto L_0x0074
            r5 = 93
            if (r4 == r5) goto L_0x0057
            r1 = 125(0x7d, float:1.75E-43)
            if (r4 == r1) goto L_0x0074
            boolean[] r1 = stopArray
            java.lang.Object r1 = r7.readMain(r8, r1)
            r8.addValue(r0, r1)
            goto L_0x0027
        L_0x0057:
            if (r1 == 0) goto L_0x006c
            boolean r1 = r7.acceptUselessComma
            if (r1 == 0) goto L_0x005e
            goto L_0x006c
        L_0x005e:
            net.minidev.json.parser.ParseException r8 = new net.minidev.json.parser.ParseException
            int r0 = r7.pos
            char r1 = r7.c
            java.lang.Character r1 = java.lang.Character.valueOf(r1)
            r8.<init>(r0, r3, r1)
            throw r8
        L_0x006c:
            r7.read()
            java.lang.Object r8 = r8.convert(r0)
            return r8
        L_0x0074:
            net.minidev.json.parser.ParseException r8 = new net.minidev.json.parser.ParseException
            int r0 = r7.pos
            char r1 = r7.c
            java.lang.Character r1 = java.lang.Character.valueOf(r1)
            r8.<init>(r0, r3, r1)
            throw r8
        L_0x0082:
            if (r1 == 0) goto L_0x0097
            boolean r1 = r7.acceptUselessComma
            if (r1 == 0) goto L_0x0089
            goto L_0x0097
        L_0x0089:
            net.minidev.json.parser.ParseException r8 = new net.minidev.json.parser.ParseException
            int r0 = r7.pos
            char r1 = r7.c
            java.lang.Character r1 = java.lang.Character.valueOf(r1)
            r8.<init>(r0, r3, r1)
            throw r8
        L_0x0097:
            r7.read()
            r1 = r6
            goto L_0x0028
        L_0x009c:
            net.minidev.json.parser.ParseException r8 = new net.minidev.json.parser.ParseException
            int r0 = r7.pos
            int r0 = r0 - r6
            r1 = 3
            java.lang.String r2 = "EOF"
            r8.<init>(r0, r1, r2)
            throw r8
        L_0x00a8:
            r7.read()
            goto L_0x0028
        L_0x00ad:
            java.lang.RuntimeException r8 = new java.lang.RuntimeException
            java.lang.String r0 = "Internal Error"
            r8.<init>(r0)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: net.minidev.json.parser.JSONParserBase.readArray(net.minidev.json.writer.JsonReaderI):java.lang.Object");
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x00ba, code lost:
        throw new net.minidev.json.parser.ParseException(r3.pos, 0, java.lang.Character.valueOf(r3.c));
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object readFirst(net.minidev.json.writer.JsonReaderI r4) {
        /*
            r3 = this;
        L_0x0000:
            char r0 = r3.c
            r1 = 9
            if (r0 == r1) goto L_0x0114
            r1 = 10
            if (r0 == r1) goto L_0x0114
            r1 = 1
            switch(r0) {
                case 13: goto L_0x0114;
                case 32: goto L_0x0114;
                case 34: goto L_0x010a;
                case 39: goto L_0x010a;
                case 45: goto L_0x00fd;
                case 78: goto L_0x00c0;
                case 91: goto L_0x00bb;
                case 93: goto L_0x00ac;
                case 102: goto L_0x0081;
                case 110: goto L_0x005b;
                case 116: goto L_0x0030;
                case 123: goto L_0x002b;
                case 125: goto L_0x00ac;
                default: goto L_0x000e;
            }
        L_0x000e:
            switch(r0) {
                case 48: goto L_0x00fd;
                case 49: goto L_0x00fd;
                case 50: goto L_0x00fd;
                case 51: goto L_0x00fd;
                case 52: goto L_0x00fd;
                case 53: goto L_0x00fd;
                case 54: goto L_0x00fd;
                case 55: goto L_0x00fd;
                case 56: goto L_0x00fd;
                case 57: goto L_0x00fd;
                case 58: goto L_0x00ac;
                default: goto L_0x0011;
            }
        L_0x0011:
            boolean[] r0 = stopX
            r3.readNQString(r0)
            boolean r0 = r3.acceptNonQuote
            if (r0 == 0) goto L_0x0021
            java.lang.String r0 = r3.xs
            java.lang.Object r4 = r4.convert(r0)
            return r4
        L_0x0021:
            net.minidev.json.parser.ParseException r4 = new net.minidev.json.parser.ParseException
            int r0 = r3.pos
            java.lang.String r2 = r3.xs
            r4.<init>(r0, r1, r2)
            throw r4
        L_0x002b:
            java.lang.Object r4 = r3.readObject(r4)
            return r4
        L_0x0030:
            boolean[] r0 = stopX
            r3.readNQString(r0)
            java.lang.String r0 = "true"
            java.lang.String r2 = r3.xs
            boolean r0 = r0.equals(r2)
            if (r0 == 0) goto L_0x0046
            java.lang.Boolean r0 = java.lang.Boolean.TRUE
            java.lang.Object r4 = r4.convert(r0)
            return r4
        L_0x0046:
            boolean r0 = r3.acceptNonQuote
            if (r0 == 0) goto L_0x0051
            java.lang.String r0 = r3.xs
            java.lang.Object r4 = r4.convert(r0)
            return r4
        L_0x0051:
            net.minidev.json.parser.ParseException r4 = new net.minidev.json.parser.ParseException
            int r0 = r3.pos
            java.lang.String r2 = r3.xs
            r4.<init>(r0, r1, r2)
            throw r4
        L_0x005b:
            boolean[] r0 = stopX
            r3.readNQString(r0)
            java.lang.String r0 = "null"
            java.lang.String r2 = r3.xs
            boolean r0 = r0.equals(r2)
            if (r0 == 0) goto L_0x006c
            r4 = 0
            return r4
        L_0x006c:
            boolean r0 = r3.acceptNonQuote
            if (r0 == 0) goto L_0x0077
            java.lang.String r0 = r3.xs
            java.lang.Object r4 = r4.convert(r0)
            return r4
        L_0x0077:
            net.minidev.json.parser.ParseException r4 = new net.minidev.json.parser.ParseException
            int r0 = r3.pos
            java.lang.String r2 = r3.xs
            r4.<init>(r0, r1, r2)
            throw r4
        L_0x0081:
            boolean[] r0 = stopX
            r3.readNQString(r0)
            java.lang.String r0 = "false"
            java.lang.String r2 = r3.xs
            boolean r0 = r0.equals(r2)
            if (r0 == 0) goto L_0x0097
            java.lang.Boolean r0 = java.lang.Boolean.FALSE
            java.lang.Object r4 = r4.convert(r0)
            return r4
        L_0x0097:
            boolean r0 = r3.acceptNonQuote
            if (r0 == 0) goto L_0x00a2
            java.lang.String r0 = r3.xs
            java.lang.Object r4 = r4.convert(r0)
            return r4
        L_0x00a2:
            net.minidev.json.parser.ParseException r4 = new net.minidev.json.parser.ParseException
            int r0 = r3.pos
            java.lang.String r2 = r3.xs
            r4.<init>(r0, r1, r2)
            throw r4
        L_0x00ac:
            net.minidev.json.parser.ParseException r4 = new net.minidev.json.parser.ParseException
            int r0 = r3.pos
            char r1 = r3.c
            java.lang.Character r1 = java.lang.Character.valueOf(r1)
            r2 = 0
            r4.<init>(r0, r2, r1)
            throw r4
        L_0x00bb:
            java.lang.Object r4 = r3.readArray(r4)
            return r4
        L_0x00c0:
            boolean[] r0 = stopX
            r3.readNQString(r0)
            boolean r0 = r3.acceptNaN
            if (r0 == 0) goto L_0x00f3
            java.lang.String r0 = "NaN"
            java.lang.String r2 = r3.xs
            boolean r0 = r0.equals(r2)
            if (r0 == 0) goto L_0x00de
            r0 = 2143289344(0x7fc00000, float:NaN)
            java.lang.Float r0 = java.lang.Float.valueOf(r0)
            java.lang.Object r4 = r4.convert(r0)
            return r4
        L_0x00de:
            boolean r0 = r3.acceptNonQuote
            if (r0 == 0) goto L_0x00e9
            java.lang.String r0 = r3.xs
            java.lang.Object r4 = r4.convert(r0)
            return r4
        L_0x00e9:
            net.minidev.json.parser.ParseException r4 = new net.minidev.json.parser.ParseException
            int r0 = r3.pos
            java.lang.String r2 = r3.xs
            r4.<init>(r0, r1, r2)
            throw r4
        L_0x00f3:
            net.minidev.json.parser.ParseException r4 = new net.minidev.json.parser.ParseException
            int r0 = r3.pos
            java.lang.String r2 = r3.xs
            r4.<init>(r0, r1, r2)
            throw r4
        L_0x00fd:
            boolean[] r0 = stopX
            java.lang.Object r0 = r3.readNumber(r0)
            r3.xo = r0
            java.lang.Object r4 = r4.convert(r0)
            return r4
        L_0x010a:
            r3.readString()
            java.lang.String r0 = r3.xs
            java.lang.Object r4 = r4.convert(r0)
            return r4
        L_0x0114:
            r3.read()
            goto L_0x0000
        */
        throw new UnsupportedOperationException("Method not decompiled: net.minidev.json.parser.JSONParserBase.readFirst(net.minidev.json.writer.JsonReaderI):java.lang.Object");
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x00a0, code lost:
        throw new net.minidev.json.parser.ParseException(r2.pos, 0, java.lang.Character.valueOf(r2.c));
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object readMain(net.minidev.json.writer.JsonReaderI r3, boolean[] r4) {
        /*
            r2 = this;
        L_0x0000:
            char r0 = r2.c
            r1 = 9
            if (r0 == r1) goto L_0x00ea
            r1 = 10
            if (r0 == r1) goto L_0x00ea
            r1 = 1
            switch(r0) {
                case 13: goto L_0x00ea;
                case 32: goto L_0x00ea;
                case 34: goto L_0x00e4;
                case 39: goto L_0x00e4;
                case 45: goto L_0x00df;
                case 78: goto L_0x00ac;
                case 91: goto L_0x00a1;
                case 93: goto L_0x0092;
                case 102: goto L_0x0071;
                case 110: goto L_0x0051;
                case 116: goto L_0x0030;
                case 123: goto L_0x0025;
                case 125: goto L_0x0092;
                default: goto L_0x000e;
            }
        L_0x000e:
            switch(r0) {
                case 48: goto L_0x00df;
                case 49: goto L_0x00df;
                case 50: goto L_0x00df;
                case 51: goto L_0x00df;
                case 52: goto L_0x00df;
                case 53: goto L_0x00df;
                case 54: goto L_0x00df;
                case 55: goto L_0x00df;
                case 56: goto L_0x00df;
                case 57: goto L_0x00df;
                case 58: goto L_0x0092;
                default: goto L_0x0011;
            }
        L_0x0011:
            r2.readNQString(r4)
            boolean r3 = r2.acceptNonQuote
            if (r3 == 0) goto L_0x001b
            java.lang.String r3 = r2.xs
            return r3
        L_0x001b:
            net.minidev.json.parser.ParseException r3 = new net.minidev.json.parser.ParseException
            int r4 = r2.pos
            java.lang.String r0 = r2.xs
            r3.<init>(r4, r1, r0)
            throw r3
        L_0x0025:
            java.lang.String r4 = r2.lastKey
            net.minidev.json.writer.JsonReaderI r3 = r3.startObject(r4)
            java.lang.Object r3 = r2.readObject(r3)
            return r3
        L_0x0030:
            r2.readNQString(r4)
            java.lang.String r3 = "true"
            java.lang.String r4 = r2.xs
            boolean r3 = r3.equals(r4)
            if (r3 == 0) goto L_0x0040
            java.lang.Boolean r3 = java.lang.Boolean.TRUE
            return r3
        L_0x0040:
            boolean r3 = r2.acceptNonQuote
            if (r3 == 0) goto L_0x0047
            java.lang.String r3 = r2.xs
            return r3
        L_0x0047:
            net.minidev.json.parser.ParseException r3 = new net.minidev.json.parser.ParseException
            int r4 = r2.pos
            java.lang.String r0 = r2.xs
            r3.<init>(r4, r1, r0)
            throw r3
        L_0x0051:
            r2.readNQString(r4)
            java.lang.String r3 = "null"
            java.lang.String r4 = r2.xs
            boolean r3 = r3.equals(r4)
            if (r3 == 0) goto L_0x0060
            r3 = 0
            return r3
        L_0x0060:
            boolean r3 = r2.acceptNonQuote
            if (r3 == 0) goto L_0x0067
            java.lang.String r3 = r2.xs
            return r3
        L_0x0067:
            net.minidev.json.parser.ParseException r3 = new net.minidev.json.parser.ParseException
            int r4 = r2.pos
            java.lang.String r0 = r2.xs
            r3.<init>(r4, r1, r0)
            throw r3
        L_0x0071:
            r2.readNQString(r4)
            java.lang.String r3 = "false"
            java.lang.String r4 = r2.xs
            boolean r3 = r3.equals(r4)
            if (r3 == 0) goto L_0x0081
            java.lang.Boolean r3 = java.lang.Boolean.FALSE
            return r3
        L_0x0081:
            boolean r3 = r2.acceptNonQuote
            if (r3 == 0) goto L_0x0088
            java.lang.String r3 = r2.xs
            return r3
        L_0x0088:
            net.minidev.json.parser.ParseException r3 = new net.minidev.json.parser.ParseException
            int r4 = r2.pos
            java.lang.String r0 = r2.xs
            r3.<init>(r4, r1, r0)
            throw r3
        L_0x0092:
            net.minidev.json.parser.ParseException r3 = new net.minidev.json.parser.ParseException
            int r4 = r2.pos
            char r0 = r2.c
            java.lang.Character r0 = java.lang.Character.valueOf(r0)
            r1 = 0
            r3.<init>(r4, r1, r0)
            throw r3
        L_0x00a1:
            java.lang.String r4 = r2.lastKey
            net.minidev.json.writer.JsonReaderI r3 = r3.startArray(r4)
            java.lang.Object r3 = r2.readArray(r3)
            return r3
        L_0x00ac:
            r2.readNQString(r4)
            boolean r3 = r2.acceptNaN
            if (r3 == 0) goto L_0x00d5
            java.lang.String r3 = "NaN"
            java.lang.String r4 = r2.xs
            boolean r3 = r3.equals(r4)
            if (r3 == 0) goto L_0x00c4
            r3 = 2143289344(0x7fc00000, float:NaN)
            java.lang.Float r3 = java.lang.Float.valueOf(r3)
            return r3
        L_0x00c4:
            boolean r3 = r2.acceptNonQuote
            if (r3 == 0) goto L_0x00cb
            java.lang.String r3 = r2.xs
            return r3
        L_0x00cb:
            net.minidev.json.parser.ParseException r3 = new net.minidev.json.parser.ParseException
            int r4 = r2.pos
            java.lang.String r0 = r2.xs
            r3.<init>(r4, r1, r0)
            throw r3
        L_0x00d5:
            net.minidev.json.parser.ParseException r3 = new net.minidev.json.parser.ParseException
            int r4 = r2.pos
            java.lang.String r0 = r2.xs
            r3.<init>(r4, r1, r0)
            throw r3
        L_0x00df:
            java.lang.Object r3 = r2.readNumber(r4)
            return r3
        L_0x00e4:
            r2.readString()
            java.lang.String r3 = r2.xs
            return r3
        L_0x00ea:
            r2.read()
            goto L_0x0000
        */
        throw new UnsupportedOperationException("Method not decompiled: net.minidev.json.parser.JSONParserBase.readMain(net.minidev.json.writer.JsonReaderI, boolean[]):java.lang.Object");
    }

    /* access modifiers changed from: protected */
    public Object readObject(JsonReaderI jsonReaderI) {
        if (this.c == '{') {
            Object createObject = jsonReaderI.createObject();
            boolean z = false;
            while (true) {
                read();
                char c2 = this.c;
                if (!(c2 == 9 || c2 == 10 || c2 == 13 || c2 == ' ')) {
                    if (c2 != ',') {
                        if (c2 != ':' && c2 != '[' && c2 != ']' && c2 != '{') {
                            if (c2 != '}') {
                                if (c2 == '\"' || c2 == '\'') {
                                    readString();
                                } else {
                                    readNQString(stopKey);
                                    if (!this.acceptNonQuote) {
                                        throw new ParseException(this.pos, 1, this.xs);
                                    }
                                }
                                String str = this.xs;
                                skipSpace();
                                char c3 = this.c;
                                if (c3 == ':') {
                                    readNoEnd();
                                    this.lastKey = str;
                                    jsonReaderI.setValue(createObject, str, readMain(jsonReaderI, stopValue));
                                    this.lastKey = null;
                                    skipSpace();
                                    char c4 = this.c;
                                    if (c4 == '}') {
                                        read();
                                        return jsonReaderI.convert(createObject);
                                    } else if (c4 == 26) {
                                        throw new ParseException(this.pos - 1, 3, (Object) null);
                                    } else if (c4 != ',') {
                                        throw new ParseException(this.pos - 1, 1, Character.valueOf(this.c));
                                    }
                                } else if (c3 == 26) {
                                    throw new ParseException(this.pos - 1, 3, (Object) null);
                                } else {
                                    throw new ParseException(this.pos - 1, 0, Character.valueOf(this.c));
                                }
                            } else if (!z || this.acceptUselessComma) {
                                read();
                                return jsonReaderI.convert(createObject);
                            } else {
                                throw new ParseException(this.pos, 0, Character.valueOf(this.c));
                            }
                        }
                    } else if (z && !this.acceptUselessComma) {
                        throw new ParseException(this.pos, 0, Character.valueOf(this.c));
                    }
                    z = true;
                }
            }
            throw new ParseException(this.pos, 0, Character.valueOf(this.c));
        }
        throw new RuntimeException("Internal Error");
    }

    /* access modifiers changed from: protected */
    public void readString2() {
        char c2 = this.c;
        while (true) {
            read();
            char c3 = this.c;
            if (c3 == '\"' || c3 == '\'') {
                if (c2 == c3) {
                    read();
                    this.xs = this.sb.toString();
                    return;
                }
                this.sb.append(c3);
            } else if (c3 != '\\') {
                if (c3 != 127) {
                    switch (c3) {
                        case 0:
                        case 1:
                        case 2:
                        case 3:
                        case 4:
                        case 5:
                        case 6:
                        case 7:
                        case 8:
                        case 9:
                        case 10:
                        case 11:
                        case 12:
                        case 13:
                        case 14:
                        case 15:
                        case 16:
                        case 17:
                        case 18:
                        case 19:
                        case 20:
                        case CommonStatusCodes.RECONNECTION_TIMED_OUT_DURING_UPDATE:
                        case 22:
                        case ConnectionResult.API_DISABLED:
                        case ConnectionResult.API_DISABLED_FOR_CONNECTION:
                        case 25:
                        case 27:
                        case OutsetBoxShadowDrawableKt.MIN_OUTSET_BOX_SHADOW_SDK_VERSION:
                        case InsetBoxShadowDrawableKt.MIN_INSET_BOX_SHADOW_SDK_VERSION:
                        case 30:
                        case 31:
                            if (this.ignoreControlChar) {
                                continue;
                            } else {
                                throw new ParseException(this.pos, 0, Character.valueOf(this.c));
                            }
                        case 26:
                            throw new ParseException(this.pos - 1, 3, (Object) null);
                    }
                } else if (this.ignoreControlChar) {
                    continue;
                } else if (this.reject127) {
                    throw new ParseException(this.pos, 0, Character.valueOf(this.c));
                }
                this.sb.append(c3);
            } else {
                read();
                char c4 = this.c;
                if (c4 == '\"') {
                    this.sb.append('\"');
                } else if (c4 == '\'') {
                    this.sb.append('\'');
                } else if (c4 == '/') {
                    this.sb.append('/');
                } else if (c4 == '\\') {
                    this.sb.append('\\');
                } else if (c4 == 'b') {
                    this.sb.append(8);
                } else if (c4 == 'f') {
                    this.sb.append(12);
                } else if (c4 == 'n') {
                    this.sb.append(10);
                } else if (c4 == 'r') {
                    this.sb.append(13);
                } else if (c4 == 'x') {
                    this.sb.append(readUnicode(2));
                } else if (c4 == 't') {
                    this.sb.append(9);
                } else if (c4 == 'u') {
                    this.sb.append(readUnicode(4));
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public char readUnicode(int i) {
        int i2;
        int i3 = 0;
        for (int i4 = 0; i4 < i; i4++) {
            int i5 = i3 * 16;
            read();
            char c2 = this.c;
            if (c2 <= '9' && c2 >= '0') {
                i2 = c2 - '0';
            } else if (c2 <= 'F' && c2 >= 'A') {
                i2 = c2 - '7';
            } else if (c2 >= 'a' && c2 <= 'f') {
                i2 = c2 - 'W';
            } else if (c2 == 26) {
                throw new ParseException(this.pos, 3, "EOF");
            } else {
                throw new ParseException(this.pos, 4, Character.valueOf(this.c));
            }
            i3 = i5 + i2;
        }
        return (char) i3;
    }

    /* access modifiers changed from: protected */
    public void skipDigits() {
        while (true) {
            char c2 = this.c;
            if (c2 >= '0' && c2 <= '9') {
                readS();
            } else {
                return;
            }
        }
    }

    /* access modifiers changed from: protected */
    public void skipNQString(boolean[] zArr) {
        while (true) {
            char c2 = this.c;
            if (c2 == 26) {
                return;
            }
            if (c2 < 0 || c2 >= '~' || !zArr[c2]) {
                readS();
            } else {
                return;
            }
        }
    }

    /* access modifiers changed from: protected */
    public void skipSpace() {
        while (true) {
            char c2 = this.c;
            if (c2 <= ' ' && c2 != 26) {
                readS();
            } else {
                return;
            }
        }
    }

    public static class MSB {
        char[] b;
        int p = -1;

        public MSB(int i) {
            this.b = new char[i];
        }

        public void append(char c) {
            int i = this.p + 1;
            this.p = i;
            char[] cArr = this.b;
            if (cArr.length <= i) {
                char[] cArr2 = new char[((cArr.length * 2) + 1)];
                System.arraycopy(cArr, 0, cArr2, 0, cArr.length);
                this.b = cArr2;
            }
            this.b[this.p] = c;
        }

        public String toString() {
            return new String(this.b, 0, this.p + 1);
        }

        public void clear() {
            this.p = -1;
        }
    }
}
