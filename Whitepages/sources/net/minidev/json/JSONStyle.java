package net.minidev.json;

import net.minidev.json.JStylerObj;

public class JSONStyle {
    public static final JSONStyle LT_COMPRESS = new JSONStyle(2);
    public static final JSONStyle MAX_COMPRESS = new JSONStyle(-1);
    public static final JSONStyle NO_COMPRESS = new JSONStyle(0);
    private boolean _ignore_null;
    private boolean _protect4Web;
    private boolean _protectKeys;
    private boolean _protectValues;
    private JStylerObj.StringProtector esc;
    private JStylerObj.MustProtect mpKey;
    private JStylerObj.MustProtect mpValue;

    public void arrayObjectEnd(Appendable appendable) {
    }

    public void arrayfirstObject(Appendable appendable) {
    }

    public void objectElmStop(Appendable appendable) {
    }

    public void objectFirstStart(Appendable appendable) {
    }

    public JSONStyle(int i) {
        JStylerObj.MustProtect mustProtect;
        boolean z = false;
        boolean z2 = (i & 1) == 0;
        this._protectKeys = z2;
        boolean z3 = (i & 4) == 0;
        this._protectValues = z3;
        boolean z4 = (i & 2) == 0;
        this._protect4Web = z4;
        this._ignore_null = (i & 16) > 0 ? true : z;
        if ((i & 8) > 0) {
            mustProtect = JStylerObj.MP_AGGRESIVE;
        } else {
            mustProtect = JStylerObj.MP_SIMPLE;
        }
        if (z3) {
            this.mpValue = JStylerObj.MP_TRUE;
        } else {
            this.mpValue = mustProtect;
        }
        if (z2) {
            this.mpKey = JStylerObj.MP_TRUE;
        } else {
            this.mpKey = mustProtect;
        }
        if (z4) {
            this.esc = JStylerObj.ESCAPE4Web;
        } else {
            this.esc = JStylerObj.ESCAPE_LT;
        }
    }

    public boolean ignoreNull() {
        return this._ignore_null;
    }

    public boolean mustProtectKey(String str) {
        return this.mpKey.mustBeProtect(str);
    }

    public boolean mustProtectValue(String str) {
        return this.mpValue.mustBeProtect(str);
    }

    public void writeString(Appendable appendable, String str) {
        if (!mustProtectValue(str)) {
            appendable.append(str);
            return;
        }
        appendable.append('\"');
        JSONValue.escape(str, appendable, this);
        appendable.append('\"');
    }

    public void escape(String str, Appendable appendable) {
        this.esc.escape(str, appendable);
    }

    public void objectStart(Appendable appendable) {
        appendable.append('{');
    }

    public void objectStop(Appendable appendable) {
        appendable.append('}');
    }

    public void objectNext(Appendable appendable) {
        appendable.append(',');
    }

    public void objectEndOfKey(Appendable appendable) {
        appendable.append(':');
    }

    public void arrayStart(Appendable appendable) {
        appendable.append('[');
    }

    public void arrayStop(Appendable appendable) {
        appendable.append(']');
    }

    public void arrayNextElm(Appendable appendable) {
        appendable.append(',');
    }
}
