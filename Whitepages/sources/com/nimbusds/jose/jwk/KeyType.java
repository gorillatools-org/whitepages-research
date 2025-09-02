package com.nimbusds.jose.jwk;

import com.nimbusds.jose.Requirement;
import java.io.Serializable;
import net.minidev.json.JSONAware;
import net.minidev.json.JSONObject;

public final class KeyType implements JSONAware, Serializable {
    public static final KeyType EC = new KeyType("EC", Requirement.RECOMMENDED);
    public static final KeyType OCT;
    public static final KeyType OKP;
    public static final KeyType RSA = new KeyType("RSA", Requirement.REQUIRED);
    private static final long serialVersionUID = 1;
    private final Requirement requirement;
    private final String value;

    static {
        Requirement requirement2 = Requirement.OPTIONAL;
        OCT = new KeyType("oct", requirement2);
        OKP = new KeyType("OKP", requirement2);
    }

    public KeyType(String str, Requirement requirement2) {
        if (str != null) {
            this.value = str;
            this.requirement = requirement2;
            return;
        }
        throw new IllegalArgumentException("The key type value must not be null");
    }

    public String getValue() {
        return this.value;
    }

    public int hashCode() {
        return this.value.hashCode();
    }

    public boolean equals(Object obj) {
        return obj != null && (obj instanceof KeyType) && toString().equals(obj.toString());
    }

    public String toString() {
        return this.value;
    }

    public String toJSONString() {
        return "\"" + JSONObject.escape(this.value) + '\"';
    }

    public static KeyType parse(String str) {
        KeyType keyType = EC;
        if (str.equals(keyType.getValue())) {
            return keyType;
        }
        KeyType keyType2 = RSA;
        if (str.equals(keyType2.getValue())) {
            return keyType2;
        }
        KeyType keyType3 = OCT;
        if (str.equals(keyType3.getValue())) {
            return keyType3;
        }
        KeyType keyType4 = OKP;
        if (str.equals(keyType4.getValue())) {
            return keyType4;
        }
        return new KeyType(str, (Requirement) null);
    }
}
