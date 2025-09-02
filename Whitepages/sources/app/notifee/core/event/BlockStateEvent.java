package app.notifee.core.event;

import android.os.Bundle;
import app.notifee.core.KeepForSdk;
import app.notifee.core.interfaces.MethodCallResult;

@KeepForSdk
public class BlockStateEvent {
    @KeepForSdk
    public static final int TYPE_APP_BLOCKED = 4;
    @KeepForSdk
    public static final int TYPE_CHANNEL_BLOCKED = 5;
    @KeepForSdk
    public static final int TYPE_CHANNEL_GROUP_BLOCKED = 6;
    public int a;
    public boolean b;
    public Bundle c;
    public MethodCallResult d;
    public boolean e = false;

    public BlockStateEvent(int i, Bundle bundle, boolean z, MethodCallResult methodCallResult) {
        this.a = i;
        this.d = methodCallResult;
        this.b = z;
        this.c = bundle;
    }

    @KeepForSdk
    public Bundle getChannelOrGroupBundle() {
        return this.c;
    }

    @KeepForSdk
    public int getType() {
        return this.a;
    }

    @KeepForSdk
    public boolean isBlocked() {
        return this.b;
    }

    @KeepForSdk
    public void setCompletionResult() {
        if (!this.e) {
            this.e = true;
            this.d.onComplete((Exception) null, null);
        }
    }
}
