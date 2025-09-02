package com.salesforce.marketingcloud.sfmcsdk;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.salesforce.marketingcloud.sfmcsdk.components.behaviors.BehaviorType;
import com.salesforce.marketingcloud.sfmcsdk.components.logging.SFMCSdkLogger;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;
import kotlin.text.StringsKt;

public final class SFMCSdkReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        BehaviorType fromString;
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(intent, "intent");
        String action = intent.getAction();
        if (action == null) {
            action = "";
        }
        String str = action;
        if (!StringsKt.isBlank(str)) {
            String replaceFirst$default = StringsKt.replaceFirst$default(str, context.getApplicationContext().getPackageName() + new Regex("."), "", false, 4, (Object) null);
            SFMCSdkLogger.INSTANCE.d("~$SFMCSdkReceiver", new SFMCSdkReceiver$onReceive$1(replaceFirst$default));
            if (Intrinsics.areEqual((Object) replaceFirst$default, (Object) "android.intent.action.MY_PACKAGE_REPLACED") && (fromString = BehaviorType.Companion.fromString(replaceFirst$default)) != null) {
                SFMCSdkJobIntentService.Companion.enqueueSystemBehavior(context, fromString, intent.getExtras());
            }
        }
    }
}
