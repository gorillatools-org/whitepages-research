package androidx.work.impl.workers;

import androidx.work.Logger;
import androidx.work.impl.model.SystemIdInfo;
import androidx.work.impl.model.SystemIdInfoDao;
import androidx.work.impl.model.WorkNameDao;
import androidx.work.impl.model.WorkSpec;
import androidx.work.impl.model.WorkSpecKt;
import androidx.work.impl.model.WorkTagDao;
import java.util.Iterator;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

public abstract class DiagnosticsWorkerKt {
    /* access modifiers changed from: private */
    public static final String TAG;

    static {
        String tagWithPrefix = Logger.tagWithPrefix("DiagnosticsWrkr");
        Intrinsics.checkNotNullExpressionValue(tagWithPrefix, "tagWithPrefix(\"DiagnosticsWrkr\")");
        TAG = tagWithPrefix;
    }

    /* access modifiers changed from: private */
    public static final String workSpecRows(WorkNameDao workNameDao, WorkTagDao workTagDao, SystemIdInfoDao systemIdInfoDao, List list) {
        StringBuilder sb = new StringBuilder();
        sb.append("\n Id \t Class Name\t " + "Job Id" + "\t State\t Unique Name\t Tags\t");
        Iterator it = list.iterator();
        while (it.hasNext()) {
            WorkSpec workSpec = (WorkSpec) it.next();
            SystemIdInfo systemIdInfo = systemIdInfoDao.getSystemIdInfo(WorkSpecKt.generationalId(workSpec));
            sb.append(workSpecRow(workSpec, CollectionsKt.joinToString$default(workNameDao.getNamesForWorkSpecId(workSpec.id), ",", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 62, (Object) null), systemIdInfo != null ? Integer.valueOf(systemIdInfo.systemId) : null, CollectionsKt.joinToString$default(workTagDao.getTagsForWorkSpecId(workSpec.id), ",", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 62, (Object) null)));
        }
        String sb2 = sb.toString();
        Intrinsics.checkNotNullExpressionValue(sb2, "StringBuilder().apply(builderAction).toString()");
        return sb2;
    }

    private static final String workSpecRow(WorkSpec workSpec, String str, Integer num, String str2) {
        return 10 + workSpec.id + "\t " + workSpec.workerClassName + "\t " + num + "\t " + workSpec.state.name() + "\t " + str + "\t " + str2 + 9;
    }
}
