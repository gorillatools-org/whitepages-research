package com.nimbusds.jwt;

import com.nimbusds.jose.util.DateUtils;
import com.nimbusds.jose.util.JSONObjectUtils;
import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;

public final class JWTClaimsSet implements Serializable {
    private static final Set REGISTERED_CLAIM_NAMES;
    private static final long serialVersionUID = 1;
    private final Map claims;

    static {
        HashSet hashSet = new HashSet();
        hashSet.add("iss");
        hashSet.add("sub");
        hashSet.add("aud");
        hashSet.add("exp");
        hashSet.add("nbf");
        hashSet.add("iat");
        hashSet.add("jti");
        REGISTERED_CLAIM_NAMES = Collections.unmodifiableSet(hashSet);
    }

    public static class Builder {
        private final Map claims = new LinkedHashMap();

        public Builder issuer(String str) {
            this.claims.put("iss", str);
            return this;
        }

        public Builder subject(String str) {
            this.claims.put("sub", str);
            return this;
        }

        public Builder audience(List list) {
            this.claims.put("aud", list);
            return this;
        }

        public Builder expirationTime(Date date) {
            this.claims.put("exp", date);
            return this;
        }

        public Builder notBeforeTime(Date date) {
            this.claims.put("nbf", date);
            return this;
        }

        public Builder issueTime(Date date) {
            this.claims.put("iat", date);
            return this;
        }

        public Builder jwtID(String str) {
            this.claims.put("jti", str);
            return this;
        }

        public Builder claim(String str, Object obj) {
            this.claims.put(str, obj);
            return this;
        }

        public JWTClaimsSet build() {
            return new JWTClaimsSet(this.claims, (JWTClaimsSet) null);
        }
    }

    private JWTClaimsSet(Map map) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        this.claims = linkedHashMap;
        linkedHashMap.putAll(map);
    }

    /* synthetic */ JWTClaimsSet(Map map, JWTClaimsSet jWTClaimsSet) {
        this(map);
    }

    public List getAudience() {
        Object claim = getClaim("aud");
        if (claim instanceof String) {
            return Collections.singletonList((String) claim);
        }
        try {
            List stringListClaim = getStringListClaim("aud");
            return stringListClaim != null ? Collections.unmodifiableList(stringListClaim) : Collections.emptyList();
        } catch (ParseException unused) {
            return Collections.emptyList();
        }
    }

    public Object getClaim(String str) {
        return this.claims.get(str);
    }

    public String[] getStringArrayClaim(String str) {
        if (getClaim(str) == null) {
            return null;
        }
        try {
            List list = (List) getClaim(str);
            int size = list.size();
            String[] strArr = new String[size];
            int i = 0;
            while (i < size) {
                try {
                    strArr[i] = (String) list.get(i);
                    i++;
                } catch (ClassCastException unused) {
                    throw new ParseException("The \"" + str + "\" claim is not a list / JSON array of strings", 0);
                }
            }
            return strArr;
        } catch (ClassCastException unused2) {
            throw new ParseException("The \"" + str + "\" claim is not a list / JSON array", 0);
        }
    }

    public List getStringListClaim(String str) {
        String[] stringArrayClaim = getStringArrayClaim(str);
        if (stringArrayClaim == null) {
            return null;
        }
        return Collections.unmodifiableList(Arrays.asList(stringArrayClaim));
    }

    public Map getClaims() {
        return Collections.unmodifiableMap(this.claims);
    }

    public JSONObject toJSONObject() {
        JSONObject jSONObject = new JSONObject();
        for (Map.Entry entry : this.claims.entrySet()) {
            if (entry.getValue() instanceof Date) {
                jSONObject.put((String) entry.getKey(), Long.valueOf(DateUtils.toSecondsSinceEpoch((Date) entry.getValue())));
            } else if ("aud".equals(entry.getKey())) {
                List audience = getAudience();
                if (audience != null && !audience.isEmpty()) {
                    if (audience.size() == 1) {
                        jSONObject.put("aud", audience.get(0));
                    } else {
                        JSONArray jSONArray = new JSONArray();
                        jSONArray.addAll(audience);
                        jSONObject.put("aud", jSONArray);
                    }
                }
            } else if (entry.getValue() != null) {
                jSONObject.put((String) entry.getKey(), entry.getValue());
            }
        }
        return jSONObject;
    }

    public String toString() {
        return toJSONObject().toJSONString();
    }

    public static JWTClaimsSet parse(JSONObject jSONObject) {
        Builder builder = new Builder();
        for (String str : jSONObject.keySet()) {
            if (str.equals("iss")) {
                builder.issuer(JSONObjectUtils.getString(jSONObject, "iss"));
            } else if (str.equals("sub")) {
                builder.subject(JSONObjectUtils.getString(jSONObject, "sub"));
            } else if (str.equals("aud")) {
                Object obj = jSONObject.get("aud");
                if (obj instanceof String) {
                    ArrayList arrayList = new ArrayList();
                    arrayList.add(JSONObjectUtils.getString(jSONObject, "aud"));
                    builder.audience(arrayList);
                } else if (obj instanceof List) {
                    builder.audience(JSONObjectUtils.getStringList(jSONObject, "aud"));
                }
            } else if (str.equals("exp")) {
                builder.expirationTime(new Date(JSONObjectUtils.getLong(jSONObject, "exp") * 1000));
            } else if (str.equals("nbf")) {
                builder.notBeforeTime(new Date(JSONObjectUtils.getLong(jSONObject, "nbf") * 1000));
            } else if (str.equals("iat")) {
                builder.issueTime(new Date(JSONObjectUtils.getLong(jSONObject, "iat") * 1000));
            } else if (str.equals("jti")) {
                builder.jwtID(JSONObjectUtils.getString(jSONObject, "jti"));
            } else {
                builder.claim(str, jSONObject.get(str));
            }
        }
        return builder.build();
    }
}
