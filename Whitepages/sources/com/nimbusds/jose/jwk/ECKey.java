package com.nimbusds.jose.jwk;

import com.nimbusds.jose.Algorithm;
import com.nimbusds.jose.crypto.utils.ECChecks;
import com.nimbusds.jose.util.Base64URL;
import com.nimbusds.jose.util.JSONObjectUtils;
import java.net.URI;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import net.minidev.json.JSONObject;

public final class ECKey extends JWK {
    public static final Set SUPPORTED_CURVES = Collections.unmodifiableSet(new HashSet(Arrays.asList(new Curve[]{Curve.P_256, Curve.P_384, Curve.P_521})));
    private static final long serialVersionUID = 1;
    private final Curve crv;
    private final Base64URL d;
    private final PrivateKey privateKey;
    private final Base64URL x;
    private final Base64URL y;

    private static void ensurePublicCoordinatesOnCurve(Curve curve, Base64URL base64URL, Base64URL base64URL2) {
        if (!SUPPORTED_CURVES.contains(curve)) {
            throw new IllegalArgumentException("Unknown / unsupported curve: " + curve);
        } else if (!ECChecks.isPointOnCurve(base64URL.decodeToBigInteger(), base64URL2.decodeToBigInteger(), curve.toECParameterSpec())) {
            throw new IllegalArgumentException("Invalid EC JWK: The 'x' and 'y' public coordinates are not on the " + curve + " curve");
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ECKey(Curve curve, Base64URL base64URL, Base64URL base64URL2, KeyUse keyUse, Set set, Algorithm algorithm, String str, URI uri, Base64URL base64URL3, Base64URL base64URL4, List list, KeyStore keyStore) {
        super(KeyType.EC, keyUse, set, algorithm, str, uri, base64URL3, base64URL4, list, keyStore);
        Curve curve2 = curve;
        Base64URL base64URL5 = base64URL;
        Base64URL base64URL6 = base64URL2;
        if (curve2 != null) {
            this.crv = curve2;
            if (base64URL5 != null) {
                this.x = base64URL5;
                if (base64URL6 != null) {
                    this.y = base64URL6;
                    ensurePublicCoordinatesOnCurve(curve, base64URL, base64URL2);
                    this.d = null;
                    this.privateKey = null;
                    return;
                }
                throw new IllegalArgumentException("The 'y' coordinate must not be null");
            }
            throw new IllegalArgumentException("The 'x' coordinate must not be null");
        }
        throw new IllegalArgumentException("The curve must not be null");
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ECKey(Curve curve, Base64URL base64URL, Base64URL base64URL2, Base64URL base64URL3, KeyUse keyUse, Set set, Algorithm algorithm, String str, URI uri, Base64URL base64URL4, Base64URL base64URL5, List list, KeyStore keyStore) {
        super(KeyType.EC, keyUse, set, algorithm, str, uri, base64URL4, base64URL5, list, keyStore);
        Curve curve2 = curve;
        Base64URL base64URL6 = base64URL;
        Base64URL base64URL7 = base64URL2;
        Base64URL base64URL8 = base64URL3;
        if (curve2 != null) {
            this.crv = curve2;
            if (base64URL6 != null) {
                this.x = base64URL6;
                if (base64URL7 != null) {
                    this.y = base64URL7;
                    ensurePublicCoordinatesOnCurve(curve, base64URL, base64URL2);
                    if (base64URL8 != null) {
                        this.d = base64URL8;
                        this.privateKey = null;
                        return;
                    }
                    throw new IllegalArgumentException("The 'd' coordinate must not be null");
                }
                throw new IllegalArgumentException("The 'y' coordinate must not be null");
            }
            throw new IllegalArgumentException("The 'x' coordinate must not be null");
        }
        throw new IllegalArgumentException("The curve must not be null");
    }

    public JSONObject toJSONObject() {
        JSONObject jSONObject = super.toJSONObject();
        jSONObject.put("crv", this.crv.toString());
        jSONObject.put("x", this.x.toString());
        jSONObject.put("y", this.y.toString());
        Base64URL base64URL = this.d;
        if (base64URL != null) {
            jSONObject.put("d", base64URL.toString());
        }
        return jSONObject;
    }

    public static ECKey parse(JSONObject jSONObject) {
        JSONObject jSONObject2 = jSONObject;
        Curve parse = Curve.parse(JSONObjectUtils.getString(jSONObject2, "crv"));
        Base64URL base64URL = new Base64URL(JSONObjectUtils.getString(jSONObject2, "x"));
        Base64URL base64URL2 = new Base64URL(JSONObjectUtils.getString(jSONObject2, "y"));
        if (JWKMetadata.parseKeyType(jSONObject) == KeyType.EC) {
            Base64URL base64URL3 = jSONObject2.get("d") != null ? new Base64URL(JSONObjectUtils.getString(jSONObject2, "d")) : null;
            if (base64URL3 != null) {
                return new ECKey(parse, base64URL, base64URL2, base64URL3, JWKMetadata.parseKeyUse(jSONObject), JWKMetadata.parseKeyOperations(jSONObject), JWKMetadata.parseAlgorithm(jSONObject), JWKMetadata.parseKeyID(jSONObject), JWKMetadata.parseX509CertURL(jSONObject), JWKMetadata.parseX509CertThumbprint(jSONObject), JWKMetadata.parseX509CertSHA256Thumbprint(jSONObject), JWKMetadata.parseX509CertChain(jSONObject), (KeyStore) null);
            }
            try {
                return new ECKey(parse, base64URL, base64URL2, JWKMetadata.parseKeyUse(jSONObject), JWKMetadata.parseKeyOperations(jSONObject), JWKMetadata.parseAlgorithm(jSONObject), JWKMetadata.parseKeyID(jSONObject), JWKMetadata.parseX509CertURL(jSONObject), JWKMetadata.parseX509CertThumbprint(jSONObject), JWKMetadata.parseX509CertSHA256Thumbprint(jSONObject), JWKMetadata.parseX509CertChain(jSONObject), (KeyStore) null);
            } catch (IllegalArgumentException e) {
                throw new ParseException(e.getMessage(), 0);
            }
        } else {
            throw new ParseException("The key type \"kty\" must be EC", 0);
        }
    }
}
