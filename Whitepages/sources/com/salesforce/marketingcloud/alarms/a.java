package com.salesforce.marketingcloud.alarms;

import android.annotation.SuppressLint;
import com.salesforce.marketingcloud.sfmcsdk.components.http.NetworkManager;

@SuppressLint({"UnknownNullness"})
public abstract class a {
    private final String a;
    private final long b;
    private final double c;
    private final long d;
    private final String e;
    private final int f;
    private final boolean g;

    /* renamed from: com.salesforce.marketingcloud.alarms.a$a  reason: collision with other inner class name */
    public enum C0001a {
        REGISTRATION(909100, (int) null) {
            /* access modifiers changed from: protected */
            public a b() {
                return new i(c());
            }
        },
        ET_ANALYTICS(909102, (int) null) {
            /* access modifiers changed from: protected */
            public a b() {
                return new b(c());
            }
        },
        FETCH_REGION_MESSAGES_DAILY(909111, (int) null) {
            /* access modifiers changed from: protected */
            public a b() {
                return new f(c());
            }
        },
        FETCH_PUSH_TOKEN(909108, (int) null) {
            /* access modifiers changed from: protected */
            public a b() {
                return new h(c());
            }
        },
        UPDATE_INBOX_MESSAGE_STATUS(909110, (int) null) {
            /* access modifiers changed from: protected */
            public a b() {
                return new j(c());
            }
        },
        SYNC(909112, (int) null) {
            /* access modifiers changed from: protected */
            public a b() {
                return new g(c());
            }
        },
        IAM_IMAGE_BATCH(909113, (int) null) {
            /* access modifiers changed from: protected */
            public a b() {
                return new e(c());
            }
        },
        DEVICE_STATS(909114, (int) null) {
            /* access modifiers changed from: protected */
            public a b() {
                return new c(c());
            }
        },
        EVENTS(909115, (int) null) {
            /* access modifiers changed from: protected */
            public a b() {
                return new d(c());
            }
        };
        
        private final int a;

        /* renamed from: com.salesforce.marketingcloud.alarms.a$a$j */
        static class j {
            static final int a = 909115;
            static final int b = 909114;
            static final int c = 909113;
            static final int d = 909112;
            static final int e = 909110;
            static final int f = 909109;
            static final int g = 909108;
            static final int h = 909111;
            static final int i = 909102;
            static final int j = 909101;
            static final int k = 909100;

            j() {
            }
        }

        private C0001a(int i2) {
            this.a = i2;
        }

        /* access modifiers changed from: protected */
        @Deprecated
        public boolean a(com.salesforce.marketingcloud.storage.j jVar) {
            return true;
        }

        /* access modifiers changed from: protected */
        public abstract a b();

        public int c() {
            return this.a;
        }
    }

    private static final class b extends a {
        b(int i) {
            this(i, "et_etanalytic_alarm_created_date", "et_etanalytic_next_alarm_interval", 60000, 2.0d, NetworkManager.MAX_SERVER_RETRY, true);
        }

        private b(int i, String str, String str2, long j, double d, long j2, boolean z) {
            super(i, str, str2, j, d, j2, z);
        }
    }

    private static final class c extends a {
        c(int i) {
            super(i, "et_device_stats_alarm_created_date", "et_device_stats_alarm_interval", 60000, 2.0d, NetworkManager.MAX_SERVER_RETRY, false);
        }
    }

    private static final class d extends a {
        d(int i) {
            super(i, "et_events_alarm_created_date", "et_events_alarm_interval", 60000, 2.0d, NetworkManager.MAX_SERVER_RETRY, false);
        }
    }

    private static final class e extends a {
        e(int i) {
            super(i, "et_iam_image_cache_route_alarm_created_date", "et_iam_image_cache_route_alarm_interval", 60000, 2.0d, NetworkManager.MAX_SERVER_RETRY, false);
        }
    }

    private static final class f extends a {
        f(int i) {
            this(i, "et_fetch_background_beacon_messages_alarm_created_date", "et_fetch_background_beacon_messages_next_alarm_interval", NetworkManager.MAX_SERVER_RETRY, 1.0d, NetworkManager.MAX_SERVER_RETRY, false);
        }

        private f(int i, String str, String str2, long j, double d, long j2, boolean z) {
            super(i, str, str2, j, d, j2, z);
        }
    }

    private static final class g extends a {
        g(int i) {
            super(i, "et_sync_route_alarm_created_date", "et_sync_route_alarm_interval", 60000, 2.0d, NetworkManager.MAX_SERVER_RETRY, false);
        }
    }

    private static final class h extends a {
        h(int i) {
            this(i, "et_register_for_remote_notifications_alarm_created_date", "et_register_for_remote_notifications_next_alarm_interval", 60000, 2.0d, NetworkManager.MAX_SERVER_RETRY, false);
        }

        private h(int i, String str, String str2, long j, double d, long j2, boolean z) {
            super(i, str, str2, j, d, j2, z);
        }
    }

    private static final class i extends a {
        i(int i) {
            this(i, "et_registration_alarm_created_date", "et_registration_next_alarm_interval", 60000, 2.0d, NetworkManager.MAX_SERVER_RETRY, false);
        }

        private i(int i, String str, String str2, long j, double d, long j2, boolean z) {
            super(i, str, str2, j, d, j2, z);
        }
    }

    private static final class j extends a {
        j(int i) {
            this(i, "et_update_inbox_message_status_alarm_created_date", "et_update_inbox_message_status_next_alarm_interval", 60000, 2.0d, NetworkManager.MAX_SERVER_RETRY, true);
        }

        private j(int i, String str, String str2, long j, double d, long j2, boolean z) {
            super(i, str, str2, j, d, j2, z);
        }
    }

    a(int i2, String str, String str2, long j2, double d2, long j3, boolean z) {
        this.f = i2;
        this.e = str;
        this.a = str2;
        this.b = j2;
        this.c = d2;
        this.d = j3;
        this.g = z;
    }

    /* access modifiers changed from: package-private */
    public final String a() {
        return this.e;
    }

    /* access modifiers changed from: package-private */
    public final int b() {
        return this.f;
    }

    /* access modifiers changed from: package-private */
    public final String c() {
        return this.a;
    }

    /* access modifiers changed from: package-private */
    public final long d() {
        return this.b;
    }

    /* access modifiers changed from: package-private */
    public final double e() {
        return this.c;
    }

    /* access modifiers changed from: package-private */
    public final long f() {
        return this.d;
    }

    /* access modifiers changed from: package-private */
    public final boolean g() {
        return this.g;
    }
}
