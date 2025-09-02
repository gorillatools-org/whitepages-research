package com.nimbusds.jose.jwk;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

abstract class KeyUseAndOpsConsistency {
    static Map MAP;

    static {
        HashMap hashMap = new HashMap();
        hashMap.put(KeyUse.SIGNATURE, new HashSet(Arrays.asList(new KeyOperation[]{KeyOperation.SIGN, KeyOperation.VERIFY})));
        hashMap.put(KeyUse.ENCRYPTION, new HashSet(Arrays.asList(new KeyOperation[]{KeyOperation.ENCRYPT, KeyOperation.DECRYPT, KeyOperation.WRAP_KEY, KeyOperation.UNWRAP_KEY})));
        MAP = Collections.unmodifiableMap(hashMap);
    }

    static boolean areConsistent(KeyUse keyUse, Set set) {
        if (keyUse == null || set == null) {
            return true;
        }
        return ((Set) MAP.get(keyUse)).containsAll(set);
    }
}
