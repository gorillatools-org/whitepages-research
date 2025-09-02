package com.nimbusds.jose.crypto;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.jca.JCAContext;
import com.nimbusds.jose.util.Base64URL;
import java.security.InvalidKeyException;
import java.security.Signature;
import java.security.SignatureException;
import java.security.interfaces.RSAPublicKey;
import java.util.Set;

public class RSASSAVerifier extends RSASSAProvider implements JWSVerifier {
    private final CriticalHeaderParamsDeferral critPolicy;
    private final RSAPublicKey publicKey;

    public /* bridge */ /* synthetic */ JCAContext getJCAContext() {
        return super.getJCAContext();
    }

    public RSASSAVerifier(RSAPublicKey rSAPublicKey) {
        this(rSAPublicKey, (Set) null);
    }

    public RSASSAVerifier(RSAPublicKey rSAPublicKey, Set set) {
        CriticalHeaderParamsDeferral criticalHeaderParamsDeferral = new CriticalHeaderParamsDeferral();
        this.critPolicy = criticalHeaderParamsDeferral;
        if (rSAPublicKey != null) {
            this.publicKey = rSAPublicKey;
            criticalHeaderParamsDeferral.setDeferredCriticalHeaderParams(set);
            return;
        }
        throw new IllegalArgumentException("The public RSA key must not be null");
    }

    public boolean verify(JWSHeader jWSHeader, byte[] bArr, Base64URL base64URL) {
        if (!this.critPolicy.headerPasses(jWSHeader)) {
            return false;
        }
        Signature signerAndVerifier = RSASSA.getSignerAndVerifier(jWSHeader.getAlgorithm(), getJCAContext().getProvider());
        try {
            signerAndVerifier.initVerify(this.publicKey);
            try {
                signerAndVerifier.update(bArr);
                return signerAndVerifier.verify(base64URL.decode());
            } catch (SignatureException unused) {
                return false;
            }
        } catch (InvalidKeyException e) {
            throw new JOSEException("Invalid public RSA key: " + e.getMessage(), e);
        }
    }
}
