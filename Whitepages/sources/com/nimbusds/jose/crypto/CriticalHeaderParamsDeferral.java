package com.nimbusds.jose.crypto;

import com.nimbusds.jose.Header;
import java.util.Collections;
import java.util.Set;

class CriticalHeaderParamsDeferral {
    private Set deferredParams = Collections.emptySet();

    CriticalHeaderParamsDeferral() {
    }

    public void setDeferredCriticalHeaderParams(Set set) {
        if (set == null) {
            this.deferredParams = Collections.emptySet();
        } else {
            this.deferredParams = set;
        }
    }

    public boolean headerPasses(Header header) {
        Set criticalParams = header.getCriticalParams();
        if (criticalParams == null || criticalParams.isEmpty()) {
            return true;
        }
        Set set = this.deferredParams;
        if (set == null || !set.containsAll(criticalParams)) {
            return false;
        }
        return true;
    }
}
