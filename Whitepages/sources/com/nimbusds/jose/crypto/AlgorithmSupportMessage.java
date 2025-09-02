package com.nimbusds.jose.crypto;

import com.nimbusds.jose.JWSAlgorithm;
import java.util.Collection;

abstract class AlgorithmSupportMessage {
    private static String itemize(Collection collection) {
        StringBuilder sb = new StringBuilder();
        Object[] array = collection.toArray();
        for (int i = 0; i < array.length; i++) {
            if (i != 0) {
                if (i < array.length - 1) {
                    sb.append(", ");
                } else if (i == array.length - 1) {
                    sb.append(" or ");
                }
            }
            sb.append(array[i].toString());
        }
        return sb.toString();
    }

    public static String unsupportedJWSAlgorithm(JWSAlgorithm jWSAlgorithm, Collection collection) {
        return "Unsupported JWS algorithm " + jWSAlgorithm + ", must be " + itemize(collection);
    }
}
