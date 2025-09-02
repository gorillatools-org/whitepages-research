package com.nimbusds.jwt;

import com.nimbusds.jose.JOSEObject;
import com.nimbusds.jose.JWSObject;
import com.nimbusds.jose.util.Base64URL;
import java.io.Serializable;
import java.text.ParseException;
import net.minidev.json.JSONObject;

public class SignedJWT extends JWSObject implements Serializable {
    private static final long serialVersionUID = 1;

    public SignedJWT(Base64URL base64URL, Base64URL base64URL2, Base64URL base64URL3) {
        super(base64URL, base64URL2, base64URL3);
    }

    public JWTClaimsSet getJWTClaimsSet() {
        JSONObject jSONObject = getPayload().toJSONObject();
        if (jSONObject != null) {
            return JWTClaimsSet.parse(jSONObject);
        }
        throw new ParseException("Payload of JWS object is not a valid JSON object", 0);
    }

    public static SignedJWT parse(String str) {
        Base64URL[] split = JOSEObject.split(str);
        if (split.length == 3) {
            return new SignedJWT(split[0], split[1], split[2]);
        }
        throw new ParseException("Unexpected number of Base64URL parts, must be three", 0);
    }
}
