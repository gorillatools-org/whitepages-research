package com.nimbusds.jose.jwk;

import com.nimbusds.jose.Algorithm;
import com.nimbusds.jose.util.Base64URL;
import com.nimbusds.jose.util.JSONObjectUtils;
import java.io.Serializable;
import java.net.URI;
import java.security.KeyStore;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import net.minidev.json.JSONAware;
import net.minidev.json.JSONObject;

public abstract class JWK implements JSONAware, Serializable {
    private static final long serialVersionUID = 1;
    private final Algorithm alg;
    private final KeyStore keyStore;
    private final String kid;
    private final KeyType kty;
    private final Set ops;
    private final KeyUse use;
    private final List x5c;
    private final Base64URL x5t;
    private Base64URL x5t256;
    private final URI x5u;

    protected JWK(KeyType keyType, KeyUse keyUse, Set set, Algorithm algorithm, String str, URI uri, Base64URL base64URL, Base64URL base64URL2, List list, KeyStore keyStore2) {
        if (keyType != null) {
            this.kty = keyType;
            if (KeyUseAndOpsConsistency.areConsistent(keyUse, set)) {
                this.use = keyUse;
                this.ops = set;
                this.alg = algorithm;
                this.kid = str;
                this.x5u = uri;
                this.x5t = base64URL;
                this.x5t256 = base64URL2;
                this.x5c = list;
                this.keyStore = keyStore2;
                return;
            }
            throw new IllegalArgumentException("The key use \"use\" and key options \"key_opts\" parameters are not consistent, see RFC 7517, section 4.3");
        }
        throw new IllegalArgumentException("The key type \"kty\" parameter must not be null");
    }

    public JSONObject toJSONObject() {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("kty", this.kty.getValue());
        KeyUse keyUse = this.use;
        if (keyUse != null) {
            jSONObject.put("use", keyUse.identifier());
        }
        if (this.ops != null) {
            ArrayList arrayList = new ArrayList(this.ops.size());
            for (KeyOperation identifier : this.ops) {
                arrayList.add(identifier.identifier());
            }
            jSONObject.put("key_ops", arrayList);
        }
        Algorithm algorithm = this.alg;
        if (algorithm != null) {
            jSONObject.put("alg", algorithm.getName());
        }
        String str = this.kid;
        if (str != null) {
            jSONObject.put("kid", str);
        }
        URI uri = this.x5u;
        if (uri != null) {
            jSONObject.put("x5u", uri.toString());
        }
        Base64URL base64URL = this.x5t;
        if (base64URL != null) {
            jSONObject.put("x5t", base64URL.toString());
        }
        Base64URL base64URL2 = this.x5t256;
        if (base64URL2 != null) {
            jSONObject.put("x5t#S256", base64URL2.toString());
        }
        List list = this.x5c;
        if (list != null) {
            jSONObject.put("x5c", list);
        }
        return jSONObject;
    }

    public String toJSONString() {
        return toJSONObject().toString();
    }

    public String toString() {
        return toJSONObject().toString();
    }

    public static JWK parse(JSONObject jSONObject) {
        KeyType parse = KeyType.parse(JSONObjectUtils.getString(jSONObject, "kty"));
        if (parse == KeyType.EC) {
            return ECKey.parse(jSONObject);
        }
        if (parse == KeyType.RSA) {
            return RSAKey.parse(jSONObject);
        }
        if (parse == KeyType.OCT) {
            return OctetSequenceKey.parse(jSONObject);
        }
        if (parse == KeyType.OKP) {
            return OctetKeyPair.parse(jSONObject);
        }
        throw new ParseException("Unsupported key type \"kty\" parameter: " + parse, 0);
    }
}
