package com.nimbusds.jose.util;

public class Base64URL extends Base64 {
    public Base64URL(String str) {
        super(str);
    }

    public boolean equals(Object obj) {
        return obj != null && (obj instanceof Base64URL) && toString().equals(obj.toString());
    }
}
