package com.salesforce.marketingcloud;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@MCKeep
public final class InitializationStatus {
    public static final b Companion = new b((DefaultConstructorMarker) null);
    private final boolean encryptionChanged;
    private final List<String> initializedComponents;
    private final boolean isUsable;
    private final boolean locationsError;
    private final boolean messagingPermissionError;
    private final String playServicesMessage;
    private final int playServicesStatus;
    private final boolean proximityError;
    private final boolean sslProviderEnablementError;
    private final Status status;
    private final boolean storageError;
    private final Throwable unrecoverableException;

    @MCKeep
    public enum Status {
        SUCCESS,
        COMPLETED_WITH_DEGRADED_FUNCTIONALITY,
        FAILED;

        static {
            Status[] $values;
            $ENTRIES = EnumEntriesKt.enumEntries($values);
        }

        public static EnumEntries getEntries() {
            return $ENTRIES;
        }
    }

    public static final class a {
        private Throwable a;
        private boolean b;
        private boolean c;
        private boolean d;
        private boolean e;
        private boolean f;
        private String g;
        private int h = -1;
        private boolean i;
        private final List<String> j = new ArrayList();

        public final void a(d dVar) {
            Intrinsics.checkNotNullParameter(dVar, "component");
            List<String> list = this.j;
            String componentName = dVar.componentName();
            Intrinsics.checkNotNullExpressionValue(componentName, "componentName(...)");
            list.add(componentName);
        }

        public final boolean b() {
            return this.a == null;
        }

        public final void c(boolean z) {
            this.e = z;
        }

        public final void d(boolean z) {
            this.i = z;
        }

        public final void e(boolean z) {
            this.f = z;
        }

        public final void f(boolean z) {
            this.d = z;
        }

        public final void a(String str) {
            if (str != null) {
                String str2 = this.g;
                if (str2 != null) {
                    str = str2 + ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE + str;
                }
                this.g = str;
            }
        }

        public final void b(boolean z) {
            this.b = z;
        }

        public final InitializationStatus a() {
            Status status = b() ? (this.b || this.d || this.e || this.i || this.f) ? Status.COMPLETED_WITH_DEGRADED_FUNCTIONALITY : Status.SUCCESS : Status.FAILED;
            Throwable th = this.a;
            boolean z = this.b;
            int i2 = this.h;
            String str = this.g;
            boolean z2 = this.c;
            boolean z3 = this.d;
            boolean z4 = this.i;
            boolean z5 = this.e;
            boolean z6 = this.f;
            String[] strArr = (String[]) this.j.toArray(new String[0]);
            return new InitializationStatus(status, th, z, i2, str, z2, z3, z4, z5, z6, CollectionsKt.listOf(Arrays.copyOf(strArr, strArr.length)), false, b.u, (DefaultConstructorMarker) null);
        }

        public final void a(boolean z) {
            this.c = z;
        }

        public final void a(int i2) {
            this.h = i2;
        }

        public final void a(Throwable th) {
            Intrinsics.checkNotNullParameter(th, "throwable");
            this.a = th;
        }
    }

    public static final class b {
        private b() {
        }

        public final InitializationStatus a() {
            a b = b();
            b.a((Throwable) new IllegalStateException("Amazon devices are not supported"));
            return b.a();
        }

        public final a b() {
            return new a();
        }

        public /* synthetic */ b(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public InitializationStatus(Status status2, Throwable th, boolean z, int i, String str, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, List<String> list, boolean z7) {
        Intrinsics.checkNotNullParameter(status2, "status");
        Intrinsics.checkNotNullParameter(list, "initializedComponents");
        this.status = status2;
        this.unrecoverableException = th;
        this.locationsError = z;
        this.playServicesStatus = i;
        this.playServicesMessage = str;
        this.encryptionChanged = z2;
        this.storageError = z3;
        this.proximityError = z4;
        this.messagingPermissionError = z5;
        this.sslProviderEnablementError = z6;
        this.initializedComponents = list;
        this.isUsable = z7;
    }

    /* renamed from: -deprecated_encryptionChanged  reason: not valid java name */
    public final boolean m602deprecated_encryptionChanged() {
        return this.encryptionChanged;
    }

    /* renamed from: -deprecated_initializedComponents  reason: not valid java name */
    public final List<String> m603deprecated_initializedComponents() {
        return this.initializedComponents;
    }

    /* renamed from: -deprecated_locationsError  reason: not valid java name */
    public final boolean m604deprecated_locationsError() {
        return this.locationsError;
    }

    /* renamed from: -deprecated_messagingPermissionError  reason: not valid java name */
    public final boolean m605deprecated_messagingPermissionError() {
        return this.messagingPermissionError;
    }

    /* renamed from: -deprecated_playServicesMessage  reason: not valid java name */
    public final String m606deprecated_playServicesMessage() {
        return this.playServicesMessage;
    }

    /* renamed from: -deprecated_playServicesStatus  reason: not valid java name */
    public final int m607deprecated_playServicesStatus() {
        return this.playServicesStatus;
    }

    /* renamed from: -deprecated_proximityError  reason: not valid java name */
    public final boolean m608deprecated_proximityError() {
        return this.proximityError;
    }

    /* renamed from: -deprecated_sslProviderEnablementError  reason: not valid java name */
    public final boolean m609deprecated_sslProviderEnablementError() {
        return this.sslProviderEnablementError;
    }

    /* renamed from: -deprecated_status  reason: not valid java name */
    public final Status m610deprecated_status() {
        return this.status;
    }

    /* renamed from: -deprecated_storageError  reason: not valid java name */
    public final boolean m611deprecated_storageError() {
        return this.storageError;
    }

    /* renamed from: -deprecated_unrecoverableException  reason: not valid java name */
    public final Throwable m612deprecated_unrecoverableException() {
        return this.unrecoverableException;
    }

    public final boolean encryptionChanged() {
        return this.encryptionChanged;
    }

    public final List<String> initializedComponents() {
        return this.initializedComponents;
    }

    public final boolean isUsable() {
        return this.isUsable;
    }

    public final boolean locationsError() {
        return this.locationsError;
    }

    public final boolean messagingPermissionError() {
        return this.messagingPermissionError;
    }

    public final String playServicesMessage() {
        return this.playServicesMessage;
    }

    public final int playServicesStatus() {
        return this.playServicesStatus;
    }

    public final boolean proximityError() {
        return this.proximityError;
    }

    public final boolean sslProviderEnablementError() {
        return this.sslProviderEnablementError;
    }

    public final Status status() {
        return this.status;
    }

    public final boolean storageError() {
        return this.storageError;
    }

    public String toString() {
        Status status2 = this.status;
        Throwable th = this.unrecoverableException;
        boolean z = this.locationsError;
        int i = this.playServicesStatus;
        String str = this.playServicesMessage;
        boolean z2 = this.encryptionChanged;
        boolean z3 = this.storageError;
        boolean z4 = this.proximityError;
        boolean z5 = this.messagingPermissionError;
        boolean z6 = this.sslProviderEnablementError;
        List<String> list = this.initializedComponents;
        boolean z7 = this.isUsable;
        return "InitializationStatus(status=" + status2 + ", unrecoverableException=" + th + ", locationsError=" + z + ", playServicesStatus=" + i + ", playServicesMessage=" + str + ", encryptionChanged=" + z2 + ", storageError=" + z3 + ", proximityError=" + z4 + ", messagingPermissionError=" + z5 + ", sslProviderEnablementError=" + z6 + ", initializedComponents=" + list + ", isUsable=" + z7 + ")";
    }

    public final Throwable unrecoverableException() {
        return this.unrecoverableException;
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public /* synthetic */ InitializationStatus(com.salesforce.marketingcloud.InitializationStatus.Status r15, java.lang.Throwable r16, boolean r17, int r18, java.lang.String r19, boolean r20, boolean r21, boolean r22, boolean r23, boolean r24, java.util.List r25, boolean r26, int r27, kotlin.jvm.internal.DefaultConstructorMarker r28) {
        /*
            r14 = this;
            r0 = r27
            r0 = r0 & 2048(0x800, float:2.87E-42)
            if (r0 == 0) goto L_0x0010
            com.salesforce.marketingcloud.InitializationStatus$Status r0 = com.salesforce.marketingcloud.InitializationStatus.Status.FAILED
            r2 = r15
            if (r2 == r0) goto L_0x000e
            r0 = 1
        L_0x000c:
            r13 = r0
            goto L_0x0013
        L_0x000e:
            r0 = 0
            goto L_0x000c
        L_0x0010:
            r2 = r15
            r13 = r26
        L_0x0013:
            r1 = r14
            r2 = r15
            r3 = r16
            r4 = r17
            r5 = r18
            r6 = r19
            r7 = r20
            r8 = r21
            r9 = r22
            r10 = r23
            r11 = r24
            r12 = r25
            r1.<init>(r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.salesforce.marketingcloud.InitializationStatus.<init>(com.salesforce.marketingcloud.InitializationStatus$Status, java.lang.Throwable, boolean, int, java.lang.String, boolean, boolean, boolean, boolean, boolean, java.util.List, boolean, int, kotlin.jvm.internal.DefaultConstructorMarker):void");
    }
}
