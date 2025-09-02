package com.google.firebase.remoteconfig.interop.rollouts;

import java.util.Set;

final class AutoValue_RolloutsState extends RolloutsState {
    private final Set<RolloutAssignment> rolloutAssignments;

    AutoValue_RolloutsState(Set<RolloutAssignment> set) {
        if (set != null) {
            this.rolloutAssignments = set;
            return;
        }
        throw new NullPointerException("Null rolloutAssignments");
    }

    public Set<RolloutAssignment> getRolloutAssignments() {
        return this.rolloutAssignments;
    }

    public String toString() {
        return "RolloutsState{rolloutAssignments=" + this.rolloutAssignments + "}";
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof RolloutsState) {
            return this.rolloutAssignments.equals(((RolloutsState) obj).getRolloutAssignments());
        }
        return false;
    }

    public int hashCode() {
        return this.rolloutAssignments.hashCode() ^ 1000003;
    }
}
