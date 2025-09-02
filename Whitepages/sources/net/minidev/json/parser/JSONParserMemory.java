package net.minidev.json.parser;

abstract class JSONParserMemory extends JSONParserBase {
    protected int len;

    /* access modifiers changed from: protected */
    public abstract void extractString(int i, int i2);

    /* access modifiers changed from: protected */
    public abstract void extractStringTrim(int i, int i2);

    /* access modifiers changed from: protected */
    public abstract int indexOf(char c, int i);

    public JSONParserMemory(int i) {
        super(i);
    }

    /* access modifiers changed from: protected */
    public void readNQString(boolean[] zArr) {
        int i = this.pos;
        skipNQString(zArr);
        extractStringTrim(i, this.pos);
    }

    /* access modifiers changed from: protected */
    public Object readNumber(boolean[] zArr) {
        int i = this.pos;
        read();
        skipDigits();
        char c = this.c;
        if (c == '.' || c == 'E' || c == 'e') {
            if (c == '.') {
                read();
                skipDigits();
            }
            char c2 = this.c;
            if (c2 == 'E' || c2 == 'e') {
                this.sb.append('E');
                read();
                char c3 = this.c;
                if (c3 == '+' || c3 == '-' || (c3 >= '0' && c3 <= '9')) {
                    this.sb.append(c3);
                    read();
                    skipDigits();
                    skipSpace();
                    char c4 = this.c;
                    if (c4 < 0 || c4 >= '~' || zArr[c4] || c4 == 26) {
                        extractStringTrim(i, this.pos);
                        return extractFloat();
                    }
                    skipNQString(zArr);
                    extractStringTrim(i, this.pos);
                    if (this.acceptNonQuote) {
                        return this.xs;
                    }
                    throw new ParseException(this.pos, 1, this.xs);
                }
                skipNQString(zArr);
                extractStringTrim(i, this.pos);
                if (this.acceptNonQuote) {
                    if (!this.acceptLeadinZero) {
                        checkLeadinZero();
                    }
                    return this.xs;
                }
                throw new ParseException(this.pos, 1, this.xs);
            }
            skipSpace();
            char c5 = this.c;
            if (c5 < 0 || c5 >= '~' || zArr[c5] || c5 == 26) {
                extractStringTrim(i, this.pos);
                return extractFloat();
            }
            skipNQString(zArr);
            extractStringTrim(i, this.pos);
            if (this.acceptNonQuote) {
                return this.xs;
            }
            throw new ParseException(this.pos, 1, this.xs);
        }
        skipSpace();
        char c6 = this.c;
        if (c6 < 0 || c6 >= '~' || zArr[c6] || c6 == 26) {
            extractStringTrim(i, this.pos);
            return parseNumber(this.xs);
        }
        skipNQString(zArr);
        extractStringTrim(i, this.pos);
        if (this.acceptNonQuote) {
            return this.xs;
        }
        throw new ParseException(this.pos, 1, this.xs);
    }

    /* access modifiers changed from: protected */
    public void readString() {
        if (this.acceptSimpleQuote || this.c != '\'') {
            int indexOf = indexOf(this.c, this.pos + 1);
            if (indexOf != -1) {
                extractString(this.pos + 1, indexOf);
                if (this.xs.indexOf(92) == -1) {
                    checkControleChar();
                    this.pos = indexOf;
                    read();
                    return;
                }
                this.sb.clear();
                readString2();
                return;
            }
            throw new ParseException(this.len, 3, (Object) null);
        } else if (this.acceptNonQuote) {
            readNQString(JSONParserBase.stopAll);
        } else {
            throw new ParseException(this.pos, 0, Character.valueOf(this.c));
        }
    }
}
