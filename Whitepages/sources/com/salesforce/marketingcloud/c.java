package com.salesforce.marketingcloud;

import android.annotation.SuppressLint;
import android.app.Service;
import android.app.job.JobInfo;
import android.app.job.JobParameters;
import android.app.job.JobScheduler;
import android.app.job.JobServiceEngine;
import android.app.job.JobWorkItem;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.IBinder;
import android.os.PowerManager;
import java.util.ArrayList;
import java.util.HashMap;

abstract class c extends Service {
    static final String h = g.a("JobIntentService");
    static final Object i = new Object();
    static final HashMap<ComponentName, h> j = new HashMap<>();
    b a;
    h b;
    a c;
    boolean d;
    boolean e;
    boolean f;
    final ArrayList<d> g = null;

    @SuppressLint({"StaticFieldLeak"})
    final class a extends AsyncTask<Void, Void, Void> {
        a() {
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public Void doInBackground(Void... voidArr) {
            e a2;
            try {
                g.a(c.h, "Starting to dequeue work...", new Object[0]);
                while (!isCancelled() && (a2 = c.this.a()) != null) {
                    String str = c.h;
                    g.a(str, "Processing next work: %s", a2);
                    c.this.a(a2.b());
                    g.a(str, "Completing work: %s", a2);
                    a2.a();
                }
                g.a(c.h, "Done processing work!", new Object[0]);
                return null;
            } catch (Exception e) {
                g.b(c.h, e, "Exception thrown by JobIntentService", new Object[0]);
                return null;
            }
        }

        /* access modifiers changed from: protected */
        /* renamed from: b */
        public void onPostExecute(Void voidR) {
            c.this.e();
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public void onCancelled(Void voidR) {
            c.this.e();
        }
    }

    interface b {
        e a();

        IBinder b();
    }

    /* renamed from: com.salesforce.marketingcloud.c$c  reason: collision with other inner class name */
    static final class C0009c extends h {
        private final Context d;
        private final PowerManager.WakeLock e;
        private final PowerManager.WakeLock f;
        boolean g;
        boolean h;

        C0009c(Context context, ComponentName componentName) {
            super(componentName);
            this.d = context.getApplicationContext();
            PowerManager powerManager = (PowerManager) context.getSystemService("power");
            PowerManager.WakeLock newWakeLock = powerManager.newWakeLock(1, componentName.getClassName() + ":launch");
            this.e = newWakeLock;
            newWakeLock.setReferenceCounted(false);
            PowerManager.WakeLock newWakeLock2 = powerManager.newWakeLock(1, componentName.getClassName() + ":run");
            this.f = newWakeLock2;
            newWakeLock2.setReferenceCounted(false);
        }

        /* access modifiers changed from: package-private */
        public void a(Intent intent) {
            Intent intent2 = new Intent(intent);
            intent2.setComponent(this.a);
            g.a(c.h, "Starting service for work: %s", intent);
            if (this.d.startService(intent2) != null) {
                synchronized (this) {
                    try {
                        if (!this.g) {
                            this.g = true;
                            if (!this.h) {
                                this.e.acquire(60000);
                            }
                        }
                    } catch (Throwable th) {
                        throw th;
                    }
                }
            }
        }

        public void b() {
            synchronized (this) {
                try {
                    if (!this.h) {
                        this.h = true;
                        this.f.acquire(600000);
                        this.e.release();
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }

        public void c() {
            synchronized (this) {
                this.g = false;
            }
        }

        public void a() {
            synchronized (this) {
                try {
                    if (this.h) {
                        if (this.g) {
                            this.e.acquire(60000);
                        }
                        this.h = false;
                        this.f.release();
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
    }

    final class d implements e {
        final Intent a;
        final int b;

        d(Intent intent, int i) {
            this.a = intent;
            this.b = i;
        }

        public void a() {
            g.a(c.h, "Stopping self: #%d", Integer.valueOf(this.b));
            c.this.stopSelf(this.b);
        }

        public Intent b() {
            return this.a;
        }
    }

    interface e {
        void a();

        Intent b();
    }

    static final class f extends JobServiceEngine implements b {
        static final String d = g.a("JobServiceEngineImpl");
        final c a;
        final Object b = new Object();
        JobParameters c;

        final class a implements e {
            final JobWorkItem a;

            a(JobWorkItem jobWorkItem) {
                this.a = jobWorkItem;
            }

            public void a() {
                synchronized (f.this.b) {
                    try {
                        JobParameters jobParameters = f.this.c;
                        if (jobParameters != null) {
                            jobParameters.completeWork(this.a);
                        }
                    } catch (Throwable th) {
                        throw th;
                    }
                }
            }

            public Intent b() {
                return this.a.getIntent();
            }
        }

        f(c cVar) {
            super(cVar);
            this.a = cVar;
        }

        /* JADX WARNING: Code restructure failed: missing block: B:11:0x0011, code lost:
            if (r1 == null) goto L_0x0026;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:12:0x0013, code lost:
            r1.getIntent().setExtrasClassLoader(r3.a.getClassLoader());
         */
        /* JADX WARNING: Code restructure failed: missing block: B:13:0x0025, code lost:
            return new com.salesforce.marketingcloud.c.f.a(r3, r1);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:14:0x0026, code lost:
            return null;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public com.salesforce.marketingcloud.c.e a() {
            /*
                r3 = this;
                java.lang.Object r0 = r3.b
                monitor-enter(r0)
                android.app.job.JobParameters r1 = r3.c     // Catch:{ all -> 0x000a }
                r2 = 0
                if (r1 != 0) goto L_0x000c
                monitor-exit(r0)     // Catch:{ all -> 0x000a }
                return r2
            L_0x000a:
                r1 = move-exception
                goto L_0x0027
            L_0x000c:
                android.app.job.JobWorkItem r1 = r1.dequeueWork()     // Catch:{ all -> 0x000a }
                monitor-exit(r0)     // Catch:{ all -> 0x000a }
                if (r1 == 0) goto L_0x0026
                android.content.Intent r0 = r1.getIntent()
                com.salesforce.marketingcloud.c r2 = r3.a
                java.lang.ClassLoader r2 = r2.getClassLoader()
                r0.setExtrasClassLoader(r2)
                com.salesforce.marketingcloud.c$f$a r0 = new com.salesforce.marketingcloud.c$f$a
                r0.<init>(r1)
                return r0
            L_0x0026:
                return r2
            L_0x0027:
                monitor-exit(r0)     // Catch:{ all -> 0x000a }
                throw r1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.salesforce.marketingcloud.c.f.a():com.salesforce.marketingcloud.c$e");
        }

        public IBinder b() {
            return getBinder();
        }

        public boolean onStartJob(JobParameters jobParameters) {
            g.a(d, "onStartJob: %s", jobParameters);
            this.c = jobParameters;
            this.a.a(false);
            return true;
        }

        public boolean onStopJob(JobParameters jobParameters) {
            g.a(d, "onStartJob: %s", jobParameters);
            boolean b2 = this.a.b();
            synchronized (this.b) {
                this.c = null;
            }
            return b2;
        }
    }

    static final class g extends h {
        private final JobInfo d;
        private final JobScheduler e;

        g(Context context, ComponentName componentName, int i) {
            super(componentName);
            a(i);
            this.d = new JobInfo.Builder(i, this.a).setOverrideDeadline(0).build();
            this.e = (JobScheduler) context.getApplicationContext().getSystemService("jobscheduler");
        }

        /* access modifiers changed from: package-private */
        public void a(Intent intent) {
            g.a(c.h, "Enqueueing work: %s", intent);
            try {
                this.e.enqueue(this.d, new JobWorkItem(intent));
            } catch (Exception e2) {
                g.b(c.h, e2, "Unable to enqueue %s for work %s", Integer.valueOf(this.c), intent);
            }
        }
    }

    static abstract class h {
        final ComponentName a;
        boolean b;
        int c;

        h(ComponentName componentName) {
            this.a = componentName;
        }

        public void a() {
        }

        /* access modifiers changed from: package-private */
        public void a(int i) {
            if (!this.b) {
                this.b = true;
                this.c = i;
            } else if (this.c != i) {
                throw new IllegalArgumentException("Given job ID " + i + " is different than previous " + this.c);
            }
        }

        /* access modifiers changed from: package-private */
        public abstract void a(Intent intent);

        public void b() {
        }

        public void c() {
        }
    }

    /* access modifiers changed from: package-private */
    public e a() {
        b bVar = this.a;
        if (bVar != null) {
            return bVar.a();
        }
        synchronized (this.g) {
            try {
                if (this.g.size() <= 0) {
                    return null;
                }
                e remove = this.g.remove(0);
                return remove;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    /* access modifiers changed from: protected */
    public abstract void a(Intent intent);

    /* access modifiers changed from: package-private */
    public boolean b() {
        a aVar = this.c;
        if (aVar != null) {
            aVar.cancel(this.d);
        }
        this.e = true;
        return d();
    }

    public boolean c() {
        return this.e;
    }

    public boolean d() {
        return true;
    }

    /* access modifiers changed from: package-private */
    public void e() {
        ArrayList<d> arrayList = this.g;
        if (arrayList != null) {
            synchronized (arrayList) {
                try {
                    this.c = null;
                    ArrayList<d> arrayList2 = this.g;
                    if (arrayList2 != null && arrayList2.size() > 0) {
                        a(false);
                    } else if (!this.f) {
                        this.b.a();
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
    }

    public IBinder onBind(Intent intent) {
        b bVar = this.a;
        if (bVar == null) {
            return null;
        }
        IBinder b2 = bVar.b();
        g.a(h, "Returning engine: %s", b2);
        return b2;
    }

    public void onCreate() {
        super.onCreate();
        g.a(h, "CREATING: %s", this);
        this.a = new f(this);
        this.b = null;
    }

    public void onDestroy() {
        ArrayList<d> arrayList = this.g;
        if (arrayList != null) {
            synchronized (arrayList) {
                this.f = true;
                this.b.a();
            }
        }
        super.onDestroy();
    }

    public int onStartCommand(Intent intent, int i2, int i3) {
        if (this.g != null) {
            this.b.c();
            g.a(h, "Received compat start command #%d: %s", Integer.valueOf(i3), intent);
            synchronized (this.g) {
                ArrayList<d> arrayList = this.g;
                if (intent == null) {
                    intent = new Intent();
                }
                arrayList.add(new d(intent, i3));
                a(true);
            }
            return 3;
        }
        g.a(h, "Ignoring start command: %s", intent);
        return 2;
    }

    public static void a(Context context, ComponentName componentName, int i2, Intent intent) {
        if (intent != null) {
            synchronized (i) {
                h a2 = a(context, componentName, true, i2);
                a2.a(i2);
                a2.a(intent);
            }
            return;
        }
        throw new IllegalArgumentException("work must not be null");
    }

    public void b(boolean z) {
        this.d = z;
    }

    public static void a(Context context, Class cls, int i2, Intent intent) {
        a(context, new ComponentName(context, cls), i2, intent);
    }

    /* access modifiers changed from: package-private */
    public void a(boolean z) {
        if (this.c == null) {
            this.c = new a();
            h hVar = this.b;
            if (hVar != null && z) {
                hVar.b();
            }
            g.a(h, "Starting processor: %s", this.c);
            this.c.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
        }
    }

    static h a(Context context, ComponentName componentName, boolean z, int i2) {
        HashMap<ComponentName, h> hashMap = j;
        h hVar = hashMap.get(componentName);
        if (hVar != null) {
            return hVar;
        }
        if (z) {
            g gVar = new g(context, componentName, i2);
            hashMap.put(componentName, gVar);
            return gVar;
        }
        throw new IllegalArgumentException("Can't be here without a job id");
    }
}
