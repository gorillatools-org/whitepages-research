package com.nimbusds.jose.util;

import java.text.ParseException;
import java.util.LinkedList;
import java.util.List;
import net.minidev.json.JSONArray;

public abstract class X509CertChainUtils {
    public static List parseX509CertChain(JSONArray jSONArray) {
        LinkedList linkedList = new LinkedList();
        int i = 0;
        while (i < jSONArray.size()) {
            Object obj = jSONArray.get(i);
            if (obj == null) {
                throw new ParseException("The X.509 certificate at position " + i + " must not be null", 0);
            } else if (obj instanceof String) {
                linkedList.add(new Base64((String) obj));
                i++;
            } else {
                throw new ParseException("The X.509 certificate at position " + i + " must be encoded as a Base64 string", 0);
            }
        }
        return linkedList;
    }
}
