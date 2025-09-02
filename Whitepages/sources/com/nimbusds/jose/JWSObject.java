package com.nimbusds.jose;

import com.nimbusds.jose.util.Base64URL;
import com.nimbusds.jose.util.StandardCharset;
import java.text.ParseException;

public abstract class JWSObject extends JOSEObject {
    private static final long serialVersionUID = 1;
    private final JWSHeader header;
    private Base64URL signature;
    private final String signingInputString;
    private State state;

    public enum State {
        UNSIGNED,
        SIGNED,
        VERIFIED
    }

    public JWSObject(Base64URL base64URL, Base64URL base64URL2, Base64URL base64URL3) {
        if (base64URL != null) {
            try {
                this.header = JWSHeader.parse(base64URL);
                if (base64URL2 != null) {
                    setPayload(new Payload(base64URL2));
                    this.signingInputString = composeSigningInput(base64URL, base64URL2);
                    if (base64URL3 != null) {
                        this.signature = base64URL3;
                        this.state = State.SIGNED;
                        setParsedParts(base64URL, base64URL2, base64URL3);
                        return;
                    }
                    throw new IllegalArgumentException("The third part must not be null");
                }
                throw new IllegalArgumentException("The second part must not be null");
            } catch (ParseException e) {
                throw new ParseException("Invalid JWS header: " + e.getMessage(), 0);
            }
        } else {
            throw new IllegalArgumentException("The first part must not be null");
        }
    }

    public JWSHeader getHeader() {
        return this.header;
    }

    private static String composeSigningInput(Base64URL base64URL, Base64URL base64URL2) {
        return String.valueOf(base64URL.toString()) + '.' + base64URL2.toString();
    }

    public byte[] getSigningInput() {
        return this.signingInputString.getBytes(StandardCharset.UTF_8);
    }

    public Base64URL getSignature() {
        return this.signature;
    }

    private void ensureSignedOrVerifiedState() {
        State state2 = this.state;
        if (state2 != State.SIGNED && state2 != State.VERIFIED) {
            throw new IllegalStateException("The JWS object must be in a signed or verified state");
        }
    }

    public synchronized boolean verify(JWSVerifier jWSVerifier) {
        boolean verify;
        ensureSignedOrVerifiedState();
        try {
            verify = jWSVerifier.verify(getHeader(), getSigningInput(), getSignature());
            if (verify) {
                this.state = State.VERIFIED;
            }
        } catch (JOSEException e) {
            throw e;
        } catch (Exception e2) {
            throw new JOSEException(e2.getMessage(), e2);
        }
        return verify;
    }

    public String serialize() {
        ensureSignedOrVerifiedState();
        return String.valueOf(this.signingInputString) + '.' + this.signature.toString();
    }
}
