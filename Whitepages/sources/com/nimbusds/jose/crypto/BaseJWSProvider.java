package com.nimbusds.jose.crypto;

import com.nimbusds.jose.jca.JCAContext;
import java.util.Collections;
import java.util.Set;

abstract class BaseJWSProvider {
    private final Set algs;
    private final JCAContext jcaContext = new JCAContext();

    public BaseJWSProvider(Set set) {
        if (set != null) {
            this.algs = Collections.unmodifiableSet(set);
            return;
        }
        throw new IllegalArgumentException("The supported JWS algorithm set must not be null");
    }

    public JCAContext getJCAContext() {
        return this.jcaContext;
    }
}
