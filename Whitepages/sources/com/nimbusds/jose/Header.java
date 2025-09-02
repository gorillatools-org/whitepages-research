package com.nimbusds.jose;

import com.google.android.gms.fido.u2f.api.common.ClientData;
import com.nimbusds.jose.util.Base64URL;
import com.nimbusds.jose.util.JSONObjectUtils;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import net.minidev.json.JSONObject;

public abstract class Header implements Serializable {
    private static final Map EMPTY_CUSTOM_PARAMS = Collections.unmodifiableMap(new HashMap());
    private static final long serialVersionUID = 1;
    private final Algorithm alg;
    private final Set crit;
    private final String cty;
    private final Map customParams;
    private final Base64URL parsedBase64URL;
    private final JOSEObjectType typ;

    protected Header(Algorithm algorithm, JOSEObjectType jOSEObjectType, String str, Set set, Map map, Base64URL base64URL) {
        if (algorithm != null) {
            this.alg = algorithm;
            this.typ = jOSEObjectType;
            this.cty = str;
            if (set != null) {
                this.crit = Collections.unmodifiableSet(new HashSet(set));
            } else {
                this.crit = null;
            }
            if (map != null) {
                this.customParams = Collections.unmodifiableMap(new HashMap(map));
            } else {
                this.customParams = EMPTY_CUSTOM_PARAMS;
            }
            this.parsedBase64URL = base64URL;
            return;
        }
        throw new IllegalArgumentException("The algorithm \"alg\" header parameter must not be null");
    }

    public Algorithm getAlgorithm() {
        return this.alg;
    }

    public Set getCriticalParams() {
        return this.crit;
    }

    public JSONObject toJSONObject() {
        JSONObject jSONObject = new JSONObject(this.customParams);
        jSONObject.put("alg", this.alg.toString());
        JOSEObjectType jOSEObjectType = this.typ;
        if (jOSEObjectType != null) {
            jSONObject.put(ClientData.KEY_TYPE, jOSEObjectType.toString());
        }
        String str = this.cty;
        if (str != null) {
            jSONObject.put("cty", str);
        }
        Set set = this.crit;
        if (set != null && !set.isEmpty()) {
            jSONObject.put("crit", new ArrayList(this.crit));
        }
        return jSONObject;
    }

    public String toString() {
        return toJSONObject().toString();
    }

    public static Algorithm parseAlgorithm(JSONObject jSONObject) {
        String string = JSONObjectUtils.getString(jSONObject, "alg");
        Algorithm algorithm = Algorithm.NONE;
        if (string.equals(algorithm.getName())) {
            return algorithm;
        }
        if (jSONObject.containsKey("enc")) {
            return JWEAlgorithm.parse(string);
        }
        return JWSAlgorithm.parse(string);
    }
}
