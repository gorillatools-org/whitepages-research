package com.salesforce.marketingcloud;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.salesforce.marketingcloud.behaviors.a;

@SuppressLint({"UnknownNullness"})
public class MCReceiver extends BroadcastReceiver {
    public static final String a = "com.salesforce.marketingcloud.WAKE_FOR_ALARM";
    private static final String b = "alarmName";
    private static final String c = g.a("MCReceiver");

    public static Intent a(Context context, String str) {
        Intent intent = new Intent(context, MCReceiver.class);
        return intent.setAction(context.getApplicationContext().getPackageName() + "." + "com.salesforce.marketingcloud.WAKE_FOR_ALARM").putExtra(b, str);
    }

    public final void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (TextUtils.isEmpty(action)) {
            g.a(c, "Action was empty %s", intent.toString());
            return;
        }
        String replaceFirst = action.replaceFirst(context.getApplicationContext().getPackageName() + ".", "");
        g.d(c, "onReceive with action: %s", replaceFirst);
        replaceFirst.hashCode();
        char c2 = 65535;
        switch (replaceFirst.hashCode()) {
            case -1076576821:
                if (replaceFirst.equals("android.intent.action.AIRPLANE_MODE")) {
                    c2 = 0;
                    break;
                }
                break;
            case 487459773:
                if (replaceFirst.equals("com.salesforce.marketingcloud.WAKE_FOR_ALARM")) {
                    c2 = 1;
                    break;
                }
                break;
            case 502473491:
                if (replaceFirst.equals("android.intent.action.TIMEZONE_CHANGED")) {
                    c2 = 2;
                    break;
                }
                break;
            case 798292259:
                if (replaceFirst.equals("android.intent.action.BOOT_COMPLETED")) {
                    c2 = 3;
                    break;
                }
                break;
            case 1737074039:
                if (replaceFirst.equals("android.intent.action.MY_PACKAGE_REPLACED")) {
                    c2 = 4;
                    break;
                }
                break;
            case 1947666138:
                if (replaceFirst.equals("android.intent.action.ACTION_SHUTDOWN")) {
                    c2 = 5;
                    break;
                }
                break;
        }
        switch (c2) {
            case 0:
            case 2:
            case 3:
            case 4:
            case 5:
                a a2 = a.a(replaceFirst);
                if (a2 != null) {
                    MCService.a(context, a2, intent.getExtras());
                    return;
                }
                return;
            case 1:
                MCService.a(context, intent.getStringExtra(b));
                return;
            default:
                return;
        }
    }
}
