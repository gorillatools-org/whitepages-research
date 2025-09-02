package com.facebook.react.fabric;

import com.salesforce.marketingcloud.config.a;
import kotlin.jvm.internal.Intrinsics;

public final class SynchronousEvent {
    private final String eventName;
    private final int surfaceId;
    private final int viewTag;

    public static /* synthetic */ SynchronousEvent copy$default(SynchronousEvent synchronousEvent, int i, int i2, String str, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = synchronousEvent.surfaceId;
        }
        if ((i3 & 2) != 0) {
            i2 = synchronousEvent.viewTag;
        }
        if ((i3 & 4) != 0) {
            str = synchronousEvent.eventName;
        }
        return synchronousEvent.copy(i, i2, str);
    }

    public final int component1() {
        return this.surfaceId;
    }

    public final int component2() {
        return this.viewTag;
    }

    public final String component3() {
        return this.eventName;
    }

    public final SynchronousEvent copy(int i, int i2, String str) {
        Intrinsics.checkNotNullParameter(str, a.h);
        return new SynchronousEvent(i, i2, str);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SynchronousEvent)) {
            return false;
        }
        SynchronousEvent synchronousEvent = (SynchronousEvent) obj;
        return this.surfaceId == synchronousEvent.surfaceId && this.viewTag == synchronousEvent.viewTag && Intrinsics.areEqual((Object) this.eventName, (Object) synchronousEvent.eventName);
    }

    public int hashCode() {
        return (((Integer.hashCode(this.surfaceId) * 31) + Integer.hashCode(this.viewTag)) * 31) + this.eventName.hashCode();
    }

    public String toString() {
        int i = this.surfaceId;
        int i2 = this.viewTag;
        String str = this.eventName;
        return "SynchronousEvent(surfaceId=" + i + ", viewTag=" + i2 + ", eventName=" + str + ")";
    }

    public SynchronousEvent(int i, int i2, String str) {
        Intrinsics.checkNotNullParameter(str, a.h);
        this.surfaceId = i;
        this.viewTag = i2;
        this.eventName = str;
    }

    public final int getSurfaceId() {
        return this.surfaceId;
    }

    public final int getViewTag() {
        return this.viewTag;
    }

    public final String getEventName() {
        return this.eventName;
    }
}
