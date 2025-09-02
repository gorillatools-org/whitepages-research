package com.facebook.internal;

import android.content.Intent;
import android.support.v4.media.session.MediaControllerCompat$MediaControllerImplApi21$ExtraBinderRequestResultReceiver$$ExternalSyntheticThrowCCEIfNotNull0;
import com.facebook.CallbackManager;
import com.facebook.FacebookSdk;
import java.util.HashMap;
import java.util.Map;
import kotlin.jvm.internal.DefaultConstructorMarker;

public final class CallbackManagerImpl implements CallbackManager {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    public static final Map staticCallbacks = new HashMap();
    private final Map callbacks = new HashMap();

    public interface Callback {
    }

    public boolean onActivityResult(int i, int i2, Intent intent) {
        MediaControllerCompat$MediaControllerImplApi21$ExtraBinderRequestResultReceiver$$ExternalSyntheticThrowCCEIfNotNull0.m(this.callbacks.get(Integer.valueOf(i)));
        return Companion.runStaticCallback(i, i2, intent);
    }

    public enum RequestCodeOffset {
        Login(0),
        Share(1),
        Message(2),
        Like(3),
        GameRequest(4),
        AppGroupCreate(5),
        AppGroupJoin(6),
        AppInvite(7),
        DeviceShare(8),
        GamingFriendFinder(9),
        GamingGroupIntegration(10),
        Referral(11),
        GamingContextCreate(12),
        GamingContextSwitch(13),
        GamingContextChoose(14),
        TournamentShareDialog(15),
        TournamentJoinDialog(16);
        
        private final int offset;

        private RequestCodeOffset(int i) {
            this.offset = i;
        }

        public final int toRequestCode() {
            return FacebookSdk.getCallbackRequestCodeOffset() + this.offset;
        }
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        private final synchronized Callback getStaticCallback(int i) {
            MediaControllerCompat$MediaControllerImplApi21$ExtraBinderRequestResultReceiver$$ExternalSyntheticThrowCCEIfNotNull0.m(CallbackManagerImpl.staticCallbacks.get(Integer.valueOf(i)));
            return null;
        }

        /* access modifiers changed from: private */
        public final boolean runStaticCallback(int i, int i2, Intent intent) {
            getStaticCallback(i);
            return false;
        }
    }
}
