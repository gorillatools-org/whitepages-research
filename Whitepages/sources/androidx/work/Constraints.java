package androidx.work;

import android.net.Uri;
import java.util.Set;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class Constraints {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final Constraints NONE = new Constraints((NetworkType) null, false, false, false, false, 0, 0, (Set) null, 255, (DefaultConstructorMarker) null);
    private final long contentTriggerMaxDelayMillis;
    private final long contentTriggerUpdateDelayMillis;
    private final Set contentUriTriggers;
    private final NetworkType requiredNetworkType;
    private final boolean requiresBatteryNotLow;
    private final boolean requiresCharging;
    private final boolean requiresDeviceIdle;
    private final boolean requiresStorageNotLow;

    public Constraints(NetworkType networkType, boolean z, boolean z2, boolean z3, boolean z4, long j, long j2, Set set) {
        Intrinsics.checkNotNullParameter(networkType, "requiredNetworkType");
        Intrinsics.checkNotNullParameter(set, "contentUriTriggers");
        this.requiredNetworkType = networkType;
        this.requiresCharging = z;
        this.requiresDeviceIdle = z2;
        this.requiresBatteryNotLow = z3;
        this.requiresStorageNotLow = z4;
        this.contentTriggerUpdateDelayMillis = j;
        this.contentTriggerMaxDelayMillis = j2;
        this.contentUriTriggers = set;
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public /* synthetic */ Constraints(androidx.work.NetworkType r12, boolean r13, boolean r14, boolean r15, boolean r16, long r17, long r19, java.util.Set r21, int r22, kotlin.jvm.internal.DefaultConstructorMarker r23) {
        /*
            r11 = this;
            r0 = r22
            r1 = r0 & 1
            if (r1 == 0) goto L_0x0009
            androidx.work.NetworkType r1 = androidx.work.NetworkType.NOT_REQUIRED
            goto L_0x000a
        L_0x0009:
            r1 = r12
        L_0x000a:
            r2 = r0 & 2
            r3 = 0
            if (r2 == 0) goto L_0x0011
            r2 = r3
            goto L_0x0012
        L_0x0011:
            r2 = r13
        L_0x0012:
            r4 = r0 & 4
            if (r4 == 0) goto L_0x0018
            r4 = r3
            goto L_0x0019
        L_0x0018:
            r4 = r14
        L_0x0019:
            r5 = r0 & 8
            if (r5 == 0) goto L_0x001f
            r5 = r3
            goto L_0x0020
        L_0x001f:
            r5 = r15
        L_0x0020:
            r6 = r0 & 16
            if (r6 == 0) goto L_0x0025
            goto L_0x0027
        L_0x0025:
            r3 = r16
        L_0x0027:
            r6 = r0 & 32
            r7 = -1
            if (r6 == 0) goto L_0x002f
            r9 = r7
            goto L_0x0031
        L_0x002f:
            r9 = r17
        L_0x0031:
            r6 = r0 & 64
            if (r6 == 0) goto L_0x0036
            goto L_0x0038
        L_0x0036:
            r7 = r19
        L_0x0038:
            r0 = r0 & 128(0x80, float:1.794E-43)
            if (r0 == 0) goto L_0x0041
            java.util.Set r0 = kotlin.collections.SetsKt.emptySet()
            goto L_0x0043
        L_0x0041:
            r0 = r21
        L_0x0043:
            r12 = r11
            r13 = r1
            r14 = r2
            r15 = r4
            r16 = r5
            r17 = r3
            r18 = r9
            r20 = r7
            r22 = r0
            r12.<init>(r13, r14, r15, r16, r17, r18, r20, r22)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.work.Constraints.<init>(androidx.work.NetworkType, boolean, boolean, boolean, boolean, long, long, java.util.Set, int, kotlin.jvm.internal.DefaultConstructorMarker):void");
    }

    public final NetworkType getRequiredNetworkType() {
        return this.requiredNetworkType;
    }

    public final long getContentTriggerUpdateDelayMillis() {
        return this.contentTriggerUpdateDelayMillis;
    }

    public final long getContentTriggerMaxDelayMillis() {
        return this.contentTriggerMaxDelayMillis;
    }

    public final Set getContentUriTriggers() {
        return this.contentUriTriggers;
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public Constraints(androidx.work.Constraints r13) {
        /*
            r12 = this;
            java.lang.String r0 = "other"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r13, r0)
            boolean r3 = r13.requiresCharging
            boolean r4 = r13.requiresDeviceIdle
            androidx.work.NetworkType r2 = r13.requiredNetworkType
            boolean r5 = r13.requiresBatteryNotLow
            boolean r6 = r13.requiresStorageNotLow
            java.util.Set r11 = r13.contentUriTriggers
            long r7 = r13.contentTriggerUpdateDelayMillis
            long r9 = r13.contentTriggerMaxDelayMillis
            r1 = r12
            r1.<init>(r2, r3, r4, r5, r6, r7, r9, r11)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.work.Constraints.<init>(androidx.work.Constraints):void");
    }

    public final boolean requiresCharging() {
        return this.requiresCharging;
    }

    public final boolean requiresDeviceIdle() {
        return this.requiresDeviceIdle;
    }

    public final boolean requiresBatteryNotLow() {
        return this.requiresBatteryNotLow;
    }

    public final boolean requiresStorageNotLow() {
        return this.requiresStorageNotLow;
    }

    public final boolean hasContentUriTriggers() {
        return !this.contentUriTriggers.isEmpty();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!Intrinsics.areEqual((Object) Constraints.class, (Object) obj.getClass())) {
            return false;
        }
        Constraints constraints = (Constraints) obj;
        if (this.requiresCharging == constraints.requiresCharging && this.requiresDeviceIdle == constraints.requiresDeviceIdle && this.requiresBatteryNotLow == constraints.requiresBatteryNotLow && this.requiresStorageNotLow == constraints.requiresStorageNotLow && this.contentTriggerUpdateDelayMillis == constraints.contentTriggerUpdateDelayMillis && this.contentTriggerMaxDelayMillis == constraints.contentTriggerMaxDelayMillis && this.requiredNetworkType == constraints.requiredNetworkType) {
            return Intrinsics.areEqual((Object) this.contentUriTriggers, (Object) constraints.contentUriTriggers);
        }
        return false;
    }

    public int hashCode() {
        long j = this.contentTriggerUpdateDelayMillis;
        long j2 = this.contentTriggerMaxDelayMillis;
        return (((((((((((((this.requiredNetworkType.hashCode() * 31) + (this.requiresCharging ? 1 : 0)) * 31) + (this.requiresDeviceIdle ? 1 : 0)) * 31) + (this.requiresBatteryNotLow ? 1 : 0)) * 31) + (this.requiresStorageNotLow ? 1 : 0)) * 31) + ((int) (j ^ (j >>> 32)))) * 31) + ((int) (j2 ^ (j2 >>> 32)))) * 31) + this.contentUriTriggers.hashCode();
    }

    public static final class ContentUriTrigger {
        private final boolean isTriggeredForDescendants;
        private final Uri uri;

        public ContentUriTrigger(Uri uri2, boolean z) {
            Intrinsics.checkNotNullParameter(uri2, "uri");
            this.uri = uri2;
            this.isTriggeredForDescendants = z;
        }

        public final Uri getUri() {
            return this.uri;
        }

        public final boolean isTriggeredForDescendants() {
            return this.isTriggeredForDescendants;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!Intrinsics.areEqual((Object) ContentUriTrigger.class, (Object) obj != null ? obj.getClass() : null)) {
                return false;
            }
            Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type androidx.work.Constraints.ContentUriTrigger");
            ContentUriTrigger contentUriTrigger = (ContentUriTrigger) obj;
            return Intrinsics.areEqual((Object) this.uri, (Object) contentUriTrigger.uri) && this.isTriggeredForDescendants == contentUriTrigger.isTriggeredForDescendants;
        }

        public int hashCode() {
            return (this.uri.hashCode() * 31) + Boolean.hashCode(this.isTriggeredForDescendants);
        }
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
