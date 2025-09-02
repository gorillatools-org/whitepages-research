package com.facebook;

import android.content.Intent;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.facebook.internal.Utility;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class ProfileManager {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    public static volatile ProfileManager instance;
    private Profile currentProfileField;
    private final LocalBroadcastManager localBroadcastManager;
    private final ProfileCache profileCache;

    public ProfileManager(LocalBroadcastManager localBroadcastManager2, ProfileCache profileCache2) {
        Intrinsics.checkNotNullParameter(localBroadcastManager2, "localBroadcastManager");
        Intrinsics.checkNotNullParameter(profileCache2, "profileCache");
        this.localBroadcastManager = localBroadcastManager2;
        this.profileCache = profileCache2;
    }

    public final Profile getCurrentProfile() {
        return this.currentProfileField;
    }

    public final void setCurrentProfile(Profile profile) {
        setCurrentProfile(profile, true);
    }

    public final boolean loadCurrentProfile() {
        Profile load = this.profileCache.load();
        if (load == null) {
            return false;
        }
        setCurrentProfile(load, false);
        return true;
    }

    private final void setCurrentProfile(Profile profile, boolean z) {
        Profile profile2 = this.currentProfileField;
        this.currentProfileField = profile;
        if (z) {
            if (profile != null) {
                this.profileCache.save(profile);
            } else {
                this.profileCache.clear();
            }
        }
        if (!Utility.areObjectsEqual(profile2, profile)) {
            sendCurrentProfileChangedBroadcast(profile2, profile);
        }
    }

    private final void sendCurrentProfileChangedBroadcast(Profile profile, Profile profile2) {
        Intent intent = new Intent("com.facebook.sdk.ACTION_CURRENT_PROFILE_CHANGED");
        intent.putExtra("com.facebook.sdk.EXTRA_OLD_PROFILE", profile);
        intent.putExtra("com.facebook.sdk.EXTRA_NEW_PROFILE", profile2);
        this.localBroadcastManager.sendBroadcast(intent);
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final synchronized ProfileManager getInstance() {
            ProfileManager access$getInstance$cp;
            try {
                if (ProfileManager.instance == null) {
                    LocalBroadcastManager instance = LocalBroadcastManager.getInstance(FacebookSdk.getApplicationContext());
                    Intrinsics.checkNotNullExpressionValue(instance, "getInstance(applicationContext)");
                    ProfileManager.instance = new ProfileManager(instance, new ProfileCache());
                }
                access$getInstance$cp = ProfileManager.instance;
                if (access$getInstance$cp == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("instance");
                    access$getInstance$cp = null;
                }
            } catch (Throwable th) {
                while (true) {
                    throw th;
                }
            }
            return access$getInstance$cp;
        }
    }
}
