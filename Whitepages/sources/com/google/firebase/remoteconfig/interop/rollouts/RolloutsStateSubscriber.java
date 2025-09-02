package com.google.firebase.remoteconfig.interop.rollouts;

public interface RolloutsStateSubscriber {
    void onRolloutsStateChanged(RolloutsState rolloutsState);
}
