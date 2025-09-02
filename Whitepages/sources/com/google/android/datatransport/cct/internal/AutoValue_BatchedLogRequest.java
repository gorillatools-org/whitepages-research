package com.google.android.datatransport.cct.internal;

import java.util.List;

final class AutoValue_BatchedLogRequest extends BatchedLogRequest {
    private final List logRequests;

    AutoValue_BatchedLogRequest(List list) {
        if (list != null) {
            this.logRequests = list;
            return;
        }
        throw new NullPointerException("Null logRequests");
    }

    public List getLogRequests() {
        return this.logRequests;
    }

    public String toString() {
        return "BatchedLogRequest{logRequests=" + this.logRequests + "}";
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof BatchedLogRequest) {
            return this.logRequests.equals(((BatchedLogRequest) obj).getLogRequests());
        }
        return false;
    }

    public int hashCode() {
        return this.logRequests.hashCode() ^ 1000003;
    }
}
