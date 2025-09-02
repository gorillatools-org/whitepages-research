package androidx.activity.result.contract;

import android.content.Context;
import android.content.Intent;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.core.content.ContextCompat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

public final class ActivityResultContracts$RequestMultiplePermissions extends ActivityResultContract {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final Intent createIntent$activity_release(String[] strArr) {
            Intrinsics.checkNotNullParameter(strArr, "input");
            Intent putExtra = new Intent("androidx.activity.result.contract.action.REQUEST_PERMISSIONS").putExtra("androidx.activity.result.contract.extra.PERMISSIONS", strArr);
            Intrinsics.checkNotNullExpressionValue(putExtra, "Intent(ACTION_REQUEST_PE…EXTRA_PERMISSIONS, input)");
            return putExtra;
        }
    }

    public Intent createIntent(Context context, String[] strArr) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(strArr, "input");
        return Companion.createIntent$activity_release(strArr);
    }

    public ActivityResultContract.SynchronousResult getSynchronousResult(Context context, String[] strArr) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(strArr, "input");
        if (strArr.length == 0) {
            return new ActivityResultContract.SynchronousResult(MapsKt.emptyMap());
        }
        for (String checkSelfPermission : strArr) {
            if (ContextCompat.checkSelfPermission(context, checkSelfPermission) != 0) {
                return null;
            }
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(strArr.length), 16));
        for (String str : strArr) {
            Pair pair = TuplesKt.to(str, Boolean.TRUE);
            linkedHashMap.put(pair.getFirst(), pair.getSecond());
        }
        return new ActivityResultContract.SynchronousResult(linkedHashMap);
    }

    public Map parseResult(int i, Intent intent) {
        if (i != -1) {
            return MapsKt.emptyMap();
        }
        if (intent == null) {
            return MapsKt.emptyMap();
        }
        String[] stringArrayExtra = intent.getStringArrayExtra("androidx.activity.result.contract.extra.PERMISSIONS");
        int[] intArrayExtra = intent.getIntArrayExtra("androidx.activity.result.contract.extra.PERMISSION_GRANT_RESULTS");
        if (intArrayExtra == null || stringArrayExtra == null) {
            return MapsKt.emptyMap();
        }
        ArrayList arrayList = new ArrayList(intArrayExtra.length);
        int length = intArrayExtra.length;
        for (int i2 = 0; i2 < length; i2++) {
            arrayList.add(Boolean.valueOf(intArrayExtra[i2] == 0));
        }
        return MapsKt.toMap((Iterable) CollectionsKt.zip(ArraysKt.filterNotNull(stringArrayExtra), arrayList));
    }
}
