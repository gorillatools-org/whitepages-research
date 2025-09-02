package com.google.firebase.crashlytics.internal;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.os.Process;
import com.google.firebase.crashlytics.internal.model.CrashlyticsReport;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

public final class ProcessDetailsProvider {
    public static final ProcessDetailsProvider INSTANCE = new ProcessDetailsProvider();

    public final CrashlyticsReport.Session.Event.Application.ProcessDetails buildProcessDetails(String str) {
        Intrinsics.checkNotNullParameter(str, "processName");
        return buildProcessDetails$default(this, str, 0, 0, false, 14, (Object) null);
    }

    public final CrashlyticsReport.Session.Event.Application.ProcessDetails buildProcessDetails(String str, int i) {
        Intrinsics.checkNotNullParameter(str, "processName");
        return buildProcessDetails$default(this, str, i, 0, false, 12, (Object) null);
    }

    public final CrashlyticsReport.Session.Event.Application.ProcessDetails buildProcessDetails(String str, int i, int i2) {
        Intrinsics.checkNotNullParameter(str, "processName");
        return buildProcessDetails$default(this, str, i, i2, false, 8, (Object) null);
    }

    private ProcessDetailsProvider() {
    }

    public final List<CrashlyticsReport.Session.Event.Application.ProcessDetails> getAppProcessDetails(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        int i = context.getApplicationInfo().uid;
        String str = context.getApplicationInfo().processName;
        Object systemService = context.getSystemService("activity");
        List<ActivityManager.RunningAppProcessInfo> list = null;
        ActivityManager activityManager = systemService instanceof ActivityManager ? (ActivityManager) systemService : null;
        if (activityManager != null) {
            list = activityManager.getRunningAppProcesses();
        }
        if (list == null) {
            list = CollectionsKt.emptyList();
        }
        ArrayList<ActivityManager.RunningAppProcessInfo> arrayList = new ArrayList<>();
        for (Object next : CollectionsKt.filterNotNull(list)) {
            if (((ActivityManager.RunningAppProcessInfo) next).uid == i) {
                arrayList.add(next);
            }
        }
        ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList, 10));
        for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : arrayList) {
            arrayList2.add(CrashlyticsReport.Session.Event.Application.ProcessDetails.builder().setProcessName(runningAppProcessInfo.processName).setPid(runningAppProcessInfo.pid).setImportance(runningAppProcessInfo.importance).setDefaultProcess(Intrinsics.areEqual((Object) runningAppProcessInfo.processName, (Object) str)).build());
        }
        return arrayList2;
    }

    public final CrashlyticsReport.Session.Event.Application.ProcessDetails getCurrentProcessDetails(Context context) {
        Object obj;
        Intrinsics.checkNotNullParameter(context, "context");
        int myPid = Process.myPid();
        Iterator it = getAppProcessDetails(context).iterator();
        while (true) {
            if (!it.hasNext()) {
                obj = null;
                break;
            }
            obj = it.next();
            if (((CrashlyticsReport.Session.Event.Application.ProcessDetails) obj).getPid() == myPid) {
                break;
            }
        }
        CrashlyticsReport.Session.Event.Application.ProcessDetails processDetails = (CrashlyticsReport.Session.Event.Application.ProcessDetails) obj;
        if (processDetails != null) {
            return processDetails;
        }
        return buildProcessDetails$default(this, getProcessName(), myPid, 0, false, 12, (Object) null);
    }

    public static /* synthetic */ CrashlyticsReport.Session.Event.Application.ProcessDetails buildProcessDetails$default(ProcessDetailsProvider processDetailsProvider, String str, int i, int i2, boolean z, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = 0;
        }
        if ((i3 & 8) != 0) {
            z = false;
        }
        return processDetailsProvider.buildProcessDetails(str, i, i2, z);
    }

    public final CrashlyticsReport.Session.Event.Application.ProcessDetails buildProcessDetails(String str, int i, int i2, boolean z) {
        Intrinsics.checkNotNullParameter(str, "processName");
        CrashlyticsReport.Session.Event.Application.ProcessDetails build = CrashlyticsReport.Session.Event.Application.ProcessDetails.builder().setProcessName(str).setPid(i).setImportance(i2).setDefaultProcess(z).build();
        Intrinsics.checkNotNullExpressionValue(build, "builder()\n      .setProc…ltProcess)\n      .build()");
        return build;
    }

    private final String getProcessName() {
        String m;
        int i = Build.VERSION.SDK_INT;
        if (i > 33) {
            String m2 = Process.myProcessName();
            Intrinsics.checkNotNullExpressionValue(m2, "{\n      Process.myProcessName()\n    }");
            return m2;
        } else if (i < 28 || (m = Application.getProcessName()) == null) {
            return "";
        } else {
            return m;
        }
    }
}
