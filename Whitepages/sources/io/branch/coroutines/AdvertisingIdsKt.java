package io.branch.coroutines;

import android.content.Context;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.Dispatchers;

public abstract class AdvertisingIdsKt {
    public static final Object getGoogleAdvertisingInfoObject(Context context, Continuation continuation) {
        return BuildersKt.withContext(Dispatchers.getDefault(), new AdvertisingIdsKt$getGoogleAdvertisingInfoObject$2(context, (Continuation) null), continuation);
    }

    public static final Object getHuaweiAdvertisingInfoObject(Context context, Continuation continuation) {
        return BuildersKt.withContext(Dispatchers.getDefault(), new AdvertisingIdsKt$getHuaweiAdvertisingInfoObject$2(context, (Continuation) null), continuation);
    }

    public static final Object getAmazonFireAdvertisingInfoObject(Context context, Continuation continuation) {
        return BuildersKt.withContext(Dispatchers.getDefault(), new AdvertisingIdsKt$getAmazonFireAdvertisingInfoObject$2(context, (Continuation) null), continuation);
    }
}
