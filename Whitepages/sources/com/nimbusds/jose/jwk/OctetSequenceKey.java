package com.nimbusds.jose.jwk;

import com.nimbusds.jose.Algorithm;
import com.nimbusds.jose.util.Base64URL;
import com.nimbusds.jose.util.JSONObjectUtils;
import java.net.URI;
import java.security.KeyStore;
import java.text.ParseException;
import java.util.List;
import java.util.Set;
import net.minidev.json.JSONObject;

public final class OctetSequenceKey extends JWK {
    private static final long serialVersionUID = 1;
    private final Base64URL k;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public OctetSequenceKey(Base64URL base64URL, KeyUse keyUse, Set set, Algorithm algorithm, String str, URI uri, Base64URL base64URL2, Base64URL base64URL3, List list, KeyStore keyStore) {
        super(KeyType.OCT, keyUse, set, algorithm, str, uri, base64URL2, base64URL3, list, keyStore);
        Base64URL base64URL4 = base64URL;
        if (base64URL4 != null) {
            this.k = base64URL4;
        } else {
            throw new IllegalArgumentException("The key value must not be null");
        }
    }

    public JSONObject toJSONObject() {
        JSONObject jSONObject = super.toJSONObject();
        jSONObject.put("k", this.k.toString());
        return jSONObject;
    }

    public static OctetSequenceKey parse(JSONObject jSONObject) {
        Base64URL base64URL = new Base64URL(JSONObjectUtils.getString(jSONObject, "k"));
        if (JWKMetadata.parseKeyType(jSONObject) == KeyType.OCT) {
            return new OctetSequenceKey(base64URL, JWKMetadata.parseKeyUse(jSONObject), JWKMetadata.parseKeyOperations(jSONObject), JWKMetadata.parseAlgorithm(jSONObject), JWKMetadata.parseKeyID(jSONObject), JWKMetadata.parseX509CertURL(jSONObject), JWKMetadata.parseX509CertThumbprint(jSONObject), JWKMetadata.parseX509CertSHA256Thumbprint(jSONObject), JWKMetadata.parseX509CertChain(jSONObject), (KeyStore) null);
        }
        throw new ParseException("The key type \"kty\" must be oct", 0);
    }
}
