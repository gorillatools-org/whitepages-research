package com.nimbusds.jose.jwk;

import com.nimbusds.jose.Algorithm;
import com.nimbusds.jose.util.Base64URL;
import com.nimbusds.jose.util.JSONObjectUtils;
import java.net.URI;
import java.security.KeyStore;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import net.minidev.json.JSONObject;

public class OctetKeyPair extends JWK {
    public static final Set SUPPORTED_CURVES = Collections.unmodifiableSet(new HashSet(Arrays.asList(new Curve[]{Curve.Ed25519, Curve.Ed448, Curve.X25519, Curve.X448})));
    private static final long serialVersionUID = 1;
    private final Curve crv;
    private final Base64URL d;
    private final Base64URL x;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public OctetKeyPair(Curve curve, Base64URL base64URL, KeyUse keyUse, Set set, Algorithm algorithm, String str, URI uri, Base64URL base64URL2, Base64URL base64URL3, List list, KeyStore keyStore) {
        super(KeyType.OKP, keyUse, set, algorithm, str, uri, base64URL2, base64URL3, list, keyStore);
        Curve curve2 = curve;
        Base64URL base64URL4 = base64URL;
        if (curve2 == null) {
            throw new IllegalArgumentException("The curve must not be null");
        } else if (SUPPORTED_CURVES.contains(curve)) {
            this.crv = curve2;
            if (base64URL4 != null) {
                this.x = base64URL4;
                this.d = null;
                return;
            }
            throw new IllegalArgumentException("The 'x' parameter must not be null");
        } else {
            throw new IllegalArgumentException("Unknown / unsupported curve: " + curve);
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public OctetKeyPair(Curve curve, Base64URL base64URL, Base64URL base64URL2, KeyUse keyUse, Set set, Algorithm algorithm, String str, URI uri, Base64URL base64URL3, Base64URL base64URL4, List list, KeyStore keyStore) {
        super(KeyType.OKP, keyUse, set, algorithm, str, uri, base64URL3, base64URL4, list, keyStore);
        Curve curve2 = curve;
        Base64URL base64URL5 = base64URL;
        Base64URL base64URL6 = base64URL2;
        if (curve2 == null) {
            throw new IllegalArgumentException("The curve must not be null");
        } else if (SUPPORTED_CURVES.contains(curve2)) {
            this.crv = curve2;
            if (base64URL5 != null) {
                this.x = base64URL5;
                if (base64URL6 != null) {
                    this.d = base64URL6;
                    return;
                }
                throw new IllegalArgumentException("The 'd' parameter must not be null");
            }
            throw new IllegalArgumentException("The 'x' parameter must not be null");
        } else {
            throw new IllegalArgumentException("Unknown / unsupported curve: " + curve2);
        }
    }

    public JSONObject toJSONObject() {
        JSONObject jSONObject = super.toJSONObject();
        jSONObject.put("crv", this.crv.toString());
        jSONObject.put("x", this.x.toString());
        Base64URL base64URL = this.d;
        if (base64URL != null) {
            jSONObject.put("d", base64URL.toString());
        }
        return jSONObject;
    }

    public static OctetKeyPair parse(JSONObject jSONObject) {
        Curve parse = Curve.parse(JSONObjectUtils.getString(jSONObject, "crv"));
        Base64URL base64URL = new Base64URL(JSONObjectUtils.getString(jSONObject, "x"));
        if (JWKMetadata.parseKeyType(jSONObject) == KeyType.OKP) {
            Base64URL base64URL2 = jSONObject.get("d") != null ? new Base64URL(JSONObjectUtils.getString(jSONObject, "d")) : null;
            if (base64URL2 != null) {
                return new OctetKeyPair(parse, base64URL, base64URL2, JWKMetadata.parseKeyUse(jSONObject), JWKMetadata.parseKeyOperations(jSONObject), JWKMetadata.parseAlgorithm(jSONObject), JWKMetadata.parseKeyID(jSONObject), JWKMetadata.parseX509CertURL(jSONObject), JWKMetadata.parseX509CertThumbprint(jSONObject), JWKMetadata.parseX509CertSHA256Thumbprint(jSONObject), JWKMetadata.parseX509CertChain(jSONObject), (KeyStore) null);
            }
            try {
                return new OctetKeyPair(parse, base64URL, JWKMetadata.parseKeyUse(jSONObject), JWKMetadata.parseKeyOperations(jSONObject), JWKMetadata.parseAlgorithm(jSONObject), JWKMetadata.parseKeyID(jSONObject), JWKMetadata.parseX509CertURL(jSONObject), JWKMetadata.parseX509CertThumbprint(jSONObject), JWKMetadata.parseX509CertSHA256Thumbprint(jSONObject), JWKMetadata.parseX509CertChain(jSONObject), (KeyStore) null);
            } catch (IllegalArgumentException e) {
                throw new ParseException(e.getMessage(), 0);
            }
        } else {
            throw new ParseException("The key type \"kty\" must be OKP", 0);
        }
    }
}
