package com.nimbusds.jose.jwk;

import com.nimbusds.jose.util.Base64URL;
import com.nimbusds.jose.util.JSONObjectUtils;
import java.io.Serializable;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;

public final class RSAKey extends JWK {
    private static final long serialVersionUID = 1;
    private final Base64URL d;
    private final Base64URL dp;
    private final Base64URL dq;
    private final Base64URL e;

    /* renamed from: n  reason: collision with root package name */
    private final Base64URL f13n;
    private final List oth;
    private final Base64URL p;
    private final PrivateKey privateKey;
    private final Base64URL q;
    private final Base64URL qi;

    public static class OtherPrimesInfo implements Serializable {
        private static final long serialVersionUID = 1;
        /* access modifiers changed from: private */
        public final Base64URL d;
        /* access modifiers changed from: private */
        public final Base64URL r;
        /* access modifiers changed from: private */
        public final Base64URL t;

        public OtherPrimesInfo(Base64URL base64URL, Base64URL base64URL2, Base64URL base64URL3) {
            if (base64URL != null) {
                this.r = base64URL;
                if (base64URL2 != null) {
                    this.d = base64URL2;
                    if (base64URL3 != null) {
                        this.t = base64URL3;
                        return;
                    }
                    throw new IllegalArgumentException("The factor CRT coefficient must not be null");
                }
                throw new IllegalArgumentException("The factor CRT exponent must not be null");
            }
            throw new IllegalArgumentException("The prime factor must not be null");
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0066 A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0083 A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x00a2  */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x00c8  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public RSAKey(com.nimbusds.jose.util.Base64URL r17, com.nimbusds.jose.util.Base64URL r18, com.nimbusds.jose.util.Base64URL r19, com.nimbusds.jose.util.Base64URL r20, com.nimbusds.jose.util.Base64URL r21, com.nimbusds.jose.util.Base64URL r22, com.nimbusds.jose.util.Base64URL r23, com.nimbusds.jose.util.Base64URL r24, java.util.List r25, java.security.PrivateKey r26, com.nimbusds.jose.jwk.KeyUse r27, java.util.Set r28, com.nimbusds.jose.Algorithm r29, java.lang.String r30, java.net.URI r31, com.nimbusds.jose.util.Base64URL r32, com.nimbusds.jose.util.Base64URL r33, java.util.List r34, java.security.KeyStore r35) {
        /*
            r16 = this;
            r11 = r16
            r12 = r17
            r13 = r18
            r14 = r20
            r15 = r21
            com.nimbusds.jose.jwk.KeyType r1 = com.nimbusds.jose.jwk.KeyType.RSA
            r0 = r16
            r2 = r27
            r3 = r28
            r4 = r29
            r5 = r30
            r6 = r31
            r7 = r32
            r8 = r33
            r9 = r34
            r10 = r35
            r0.<init>(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10)
            if (r12 == 0) goto L_0x00d8
            r11.f13n = r12
            if (r13 == 0) goto L_0x00d0
            r11.e = r13
            r0 = r19
            r11.d = r0
            if (r14 == 0) goto L_0x005e
            if (r15 == 0) goto L_0x005e
            r0 = r22
            r1 = r23
            if (r0 == 0) goto L_0x005b
            r2 = r24
            if (r1 == 0) goto L_0x0063
            if (r2 == 0) goto L_0x0063
            r11.p = r14
            r11.q = r15
            r11.dp = r0
            r11.dq = r1
            r11.qi = r2
            if (r25 == 0) goto L_0x0054
            java.util.List r0 = java.util.Collections.unmodifiableList(r25)
            r11.oth = r0
        L_0x0051:
            r0 = r26
            goto L_0x009d
        L_0x0054:
            java.util.List r0 = java.util.Collections.emptyList()
            r11.oth = r0
            goto L_0x0051
        L_0x005b:
            r2 = r24
            goto L_0x0063
        L_0x005e:
            r0 = r22
            r1 = r23
            goto L_0x005b
        L_0x0063:
            r3 = 0
            if (r14 != 0) goto L_0x0081
            if (r15 != 0) goto L_0x0081
            if (r0 != 0) goto L_0x0081
            if (r1 != 0) goto L_0x0081
            if (r2 != 0) goto L_0x0081
            if (r25 != 0) goto L_0x0081
            r11.p = r3
            r11.q = r3
            r11.dp = r3
            r11.dq = r3
            r11.qi = r3
            java.util.List r0 = java.util.Collections.emptyList()
            r11.oth = r0
            goto L_0x0051
        L_0x0081:
            if (r14 != 0) goto L_0x00a0
            if (r15 != 0) goto L_0x00a0
            if (r0 != 0) goto L_0x00a0
            if (r1 != 0) goto L_0x00a0
            if (r2 == 0) goto L_0x008c
            goto L_0x00a0
        L_0x008c:
            r11.p = r3
            r11.q = r3
            r11.dp = r3
            r11.dq = r3
            r11.qi = r3
            java.util.List r0 = java.util.Collections.emptyList()
            r11.oth = r0
            goto L_0x0051
        L_0x009d:
            r11.privateKey = r0
            return
        L_0x00a0:
            if (r14 == 0) goto L_0x00c8
            if (r15 == 0) goto L_0x00c0
            if (r0 == 0) goto L_0x00b8
            if (r1 != 0) goto L_0x00b0
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = "Incomplete second private (CRT) representation: The second factor CRT exponent must not be null"
            r0.<init>(r1)
            throw r0
        L_0x00b0:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = "Incomplete second private (CRT) representation: The first CRT coefficient must not be null"
            r0.<init>(r1)
            throw r0
        L_0x00b8:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = "Incomplete second private (CRT) representation: The first factor CRT exponent must not be null"
            r0.<init>(r1)
            throw r0
        L_0x00c0:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = "Incomplete second private (CRT) representation: The second prime factor must not be null"
            r0.<init>(r1)
            throw r0
        L_0x00c8:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = "Incomplete second private (CRT) representation: The first prime factor must not be null"
            r0.<init>(r1)
            throw r0
        L_0x00d0:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = "The public exponent value must not be null"
            r0.<init>(r1)
            throw r0
        L_0x00d8:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = "The modulus value must not be null"
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.nimbusds.jose.jwk.RSAKey.<init>(com.nimbusds.jose.util.Base64URL, com.nimbusds.jose.util.Base64URL, com.nimbusds.jose.util.Base64URL, com.nimbusds.jose.util.Base64URL, com.nimbusds.jose.util.Base64URL, com.nimbusds.jose.util.Base64URL, com.nimbusds.jose.util.Base64URL, com.nimbusds.jose.util.Base64URL, java.util.List, java.security.PrivateKey, com.nimbusds.jose.jwk.KeyUse, java.util.Set, com.nimbusds.jose.Algorithm, java.lang.String, java.net.URI, com.nimbusds.jose.util.Base64URL, com.nimbusds.jose.util.Base64URL, java.util.List, java.security.KeyStore):void");
    }

    public JSONObject toJSONObject() {
        JSONObject jSONObject = super.toJSONObject();
        jSONObject.put("n", this.f13n.toString());
        jSONObject.put("e", this.e.toString());
        Base64URL base64URL = this.d;
        if (base64URL != null) {
            jSONObject.put("d", base64URL.toString());
        }
        Base64URL base64URL2 = this.p;
        if (base64URL2 != null) {
            jSONObject.put("p", base64URL2.toString());
        }
        Base64URL base64URL3 = this.q;
        if (base64URL3 != null) {
            jSONObject.put("q", base64URL3.toString());
        }
        Base64URL base64URL4 = this.dp;
        if (base64URL4 != null) {
            jSONObject.put("dp", base64URL4.toString());
        }
        Base64URL base64URL5 = this.dq;
        if (base64URL5 != null) {
            jSONObject.put("dq", base64URL5.toString());
        }
        Base64URL base64URL6 = this.qi;
        if (base64URL6 != null) {
            jSONObject.put("qi", base64URL6.toString());
        }
        List list = this.oth;
        if (list != null && !list.isEmpty()) {
            JSONArray jSONArray = new JSONArray();
            for (OtherPrimesInfo otherPrimesInfo : this.oth) {
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("r", otherPrimesInfo.r.toString());
                jSONObject2.put("d", otherPrimesInfo.d.toString());
                jSONObject2.put("t", otherPrimesInfo.t.toString());
                jSONArray.add(jSONObject2);
            }
            jSONObject.put("oth", jSONArray);
        }
        return jSONObject;
    }

    public static RSAKey parse(JSONObject jSONObject) {
        ArrayList arrayList;
        JSONObject jSONObject2 = jSONObject;
        Base64URL base64URL = new Base64URL(JSONObjectUtils.getString(jSONObject2, "n"));
        Base64URL base64URL2 = new Base64URL(JSONObjectUtils.getString(jSONObject2, "e"));
        if (KeyType.parse(JSONObjectUtils.getString(jSONObject2, "kty")) == KeyType.RSA) {
            Base64URL base64URL3 = jSONObject2.containsKey("d") ? new Base64URL(JSONObjectUtils.getString(jSONObject2, "d")) : null;
            Base64URL base64URL4 = jSONObject2.containsKey("p") ? new Base64URL(JSONObjectUtils.getString(jSONObject2, "p")) : null;
            Base64URL base64URL5 = jSONObject2.containsKey("q") ? new Base64URL(JSONObjectUtils.getString(jSONObject2, "q")) : null;
            Base64URL base64URL6 = jSONObject2.containsKey("dp") ? new Base64URL(JSONObjectUtils.getString(jSONObject2, "dp")) : null;
            Base64URL base64URL7 = jSONObject2.containsKey("dq") ? new Base64URL(JSONObjectUtils.getString(jSONObject2, "dq")) : null;
            Base64URL base64URL8 = jSONObject2.containsKey("qi") ? new Base64URL(JSONObjectUtils.getString(jSONObject2, "qi")) : null;
            if (jSONObject2.containsKey("oth")) {
                JSONArray jSONArray = JSONObjectUtils.getJSONArray(jSONObject2, "oth");
                ArrayList arrayList2 = new ArrayList(jSONArray.size());
                Iterator it = jSONArray.iterator();
                while (it.hasNext()) {
                    Object next = it.next();
                    if (next instanceof JSONObject) {
                        JSONObject jSONObject3 = (JSONObject) next;
                        arrayList2.add(new OtherPrimesInfo(new Base64URL(JSONObjectUtils.getString(jSONObject3, "r")), new Base64URL(JSONObjectUtils.getString(jSONObject3, "dq")), new Base64URL(JSONObjectUtils.getString(jSONObject3, "t"))));
                    }
                    JSONObject jSONObject4 = jSONObject;
                }
                arrayList = arrayList2;
            } else {
                arrayList = null;
            }
            try {
                return new RSAKey(base64URL, base64URL2, base64URL3, base64URL4, base64URL5, base64URL6, base64URL7, base64URL8, arrayList, (PrivateKey) null, JWKMetadata.parseKeyUse(jSONObject), JWKMetadata.parseKeyOperations(jSONObject), JWKMetadata.parseAlgorithm(jSONObject), JWKMetadata.parseKeyID(jSONObject), JWKMetadata.parseX509CertURL(jSONObject), JWKMetadata.parseX509CertThumbprint(jSONObject), JWKMetadata.parseX509CertSHA256Thumbprint(jSONObject), JWKMetadata.parseX509CertChain(jSONObject), (KeyStore) null);
            } catch (IllegalArgumentException e2) {
                throw new ParseException(e2.getMessage(), 0);
            }
        } else {
            throw new ParseException("The key type \"kty\" must be RSA", 0);
        }
    }
}
