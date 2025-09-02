package com.nimbusds.jose.jwk;

import com.nimbusds.jose.Algorithm;
import com.nimbusds.jose.util.Base64URL;
import com.nimbusds.jose.util.JSONObjectUtils;
import com.nimbusds.jose.util.X509CertChainUtils;
import java.net.URI;
import java.util.List;
import java.util.Set;
import net.minidev.json.JSONObject;

abstract class JWKMetadata {
    static KeyType parseKeyType(JSONObject jSONObject) {
        return KeyType.parse(JSONObjectUtils.getString(jSONObject, "kty"));
    }

    static KeyUse parseKeyUse(JSONObject jSONObject) {
        if (jSONObject.containsKey("use")) {
            return KeyUse.parse(JSONObjectUtils.getString(jSONObject, "use"));
        }
        return null;
    }

    static Set parseKeyOperations(JSONObject jSONObject) {
        if (jSONObject.containsKey("key_ops")) {
            return KeyOperation.parse(JSONObjectUtils.getStringList(jSONObject, "key_ops"));
        }
        return null;
    }

    static Algorithm parseAlgorithm(JSONObject jSONObject) {
        if (jSONObject.containsKey("alg")) {
            return new Algorithm(JSONObjectUtils.getString(jSONObject, "alg"));
        }
        return null;
    }

    static String parseKeyID(JSONObject jSONObject) {
        if (jSONObject.containsKey("kid")) {
            return JSONObjectUtils.getString(jSONObject, "kid");
        }
        return null;
    }

    static URI parseX509CertURL(JSONObject jSONObject) {
        if (jSONObject.containsKey("x5u")) {
            return JSONObjectUtils.getURI(jSONObject, "x5u");
        }
        return null;
    }

    static Base64URL parseX509CertThumbprint(JSONObject jSONObject) {
        if (jSONObject.containsKey("x5t")) {
            return new Base64URL(JSONObjectUtils.getString(jSONObject, "x5t"));
        }
        return null;
    }

    static Base64URL parseX509CertSHA256Thumbprint(JSONObject jSONObject) {
        if (jSONObject.containsKey("x5t#S256")) {
            return new Base64URL(JSONObjectUtils.getString(jSONObject, "x5t#S256"));
        }
        return null;
    }

    static List parseX509CertChain(JSONObject jSONObject) {
        if (jSONObject.containsKey("x5c")) {
            return X509CertChainUtils.parseX509CertChain(JSONObjectUtils.getJSONArray(jSONObject, "x5c"));
        }
        return null;
    }
}
