package com.google.firebase.remoteconfig.interop;

import com.google.firebase.remoteconfig.interop.rollouts.RolloutsStateSubscriber;

public interface FirebaseRemoteConfigInterop {
    void registerRolloutsStateSubscriber(String str, RolloutsStateSubscriber rolloutsStateSubscriber);
}
