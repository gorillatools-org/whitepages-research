package com.google.firebase.remoteconfig.internal.rollouts;

import android.util.Log;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigException;
import com.google.firebase.remoteconfig.internal.ConfigCacheClient;
import com.google.firebase.remoteconfig.internal.ConfigContainer;
import com.google.firebase.remoteconfig.interop.rollouts.RolloutsState;
import com.google.firebase.remoteconfig.interop.rollouts.RolloutsStateSubscriber;
import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;

public class RolloutsStateSubscriptionsHandler {
    private ConfigCacheClient activatedConfigsCache;
    private Executor executor;
    private RolloutsStateFactory rolloutsStateFactory;
    private Set<RolloutsStateSubscriber> subscribers = Collections.newSetFromMap(new ConcurrentHashMap());

    public RolloutsStateSubscriptionsHandler(ConfigCacheClient configCacheClient, RolloutsStateFactory rolloutsStateFactory2, Executor executor2) {
        this.activatedConfigsCache = configCacheClient;
        this.rolloutsStateFactory = rolloutsStateFactory2;
        this.executor = executor2;
    }

    public void registerRolloutsStateSubscriber(RolloutsStateSubscriber rolloutsStateSubscriber) {
        this.subscribers.add(rolloutsStateSubscriber);
        Task<ConfigContainer> task = this.activatedConfigsCache.get();
        task.addOnSuccessListener(this.executor, (OnSuccessListener<? super ConfigContainer>) new RolloutsStateSubscriptionsHandler$$ExternalSyntheticLambda0(this, task, rolloutsStateSubscriber));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$registerRolloutsStateSubscriber$1(Task task, RolloutsStateSubscriber rolloutsStateSubscriber, ConfigContainer configContainer) {
        try {
            ConfigContainer configContainer2 = (ConfigContainer) task.getResult();
            if (configContainer2 != null) {
                this.executor.execute(new RolloutsStateSubscriptionsHandler$$ExternalSyntheticLambda1(rolloutsStateSubscriber, this.rolloutsStateFactory.getActiveRolloutsState(configContainer2)));
            }
        } catch (FirebaseRemoteConfigException e) {
            Log.w(FirebaseRemoteConfig.TAG, "Exception publishing RolloutsState to subscriber. Continuing to listen for changes.", e);
        }
    }

    public void publishActiveRolloutsState(ConfigContainer configContainer) {
        try {
            RolloutsState activeRolloutsState = this.rolloutsStateFactory.getActiveRolloutsState(configContainer);
            for (RolloutsStateSubscriber rolloutsStateSubscriptionsHandler$$ExternalSyntheticLambda2 : this.subscribers) {
                this.executor.execute(new RolloutsStateSubscriptionsHandler$$ExternalSyntheticLambda2(rolloutsStateSubscriptionsHandler$$ExternalSyntheticLambda2, activeRolloutsState));
            }
        } catch (FirebaseRemoteConfigException e) {
            Log.w(FirebaseRemoteConfig.TAG, "Exception publishing RolloutsState to subscribers. Continuing to listen for changes.", e);
        }
    }
}
