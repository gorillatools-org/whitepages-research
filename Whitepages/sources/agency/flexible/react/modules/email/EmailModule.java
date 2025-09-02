package agency.flexible.react.modules.email;

import android.content.Intent;
import android.content.pm.LabeledIntent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableNativeArray;
import java.util.ArrayList;
import java.util.List;

public class EmailModule extends ReactContextBaseJavaModule {
    public EmailModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }

    public String getName() {
        return "Email";
    }

    @ReactMethod
    public void getEmailClients(Promise promise) {
        List<ResolveInfo> queryIntentActivities = getCurrentActivity().getPackageManager().queryIntentActivities(new Intent("android.intent.action.VIEW", Uri.parse("mailto:")), 0);
        if (queryIntentActivities.size() > 0) {
            WritableNativeArray writableNativeArray = new WritableNativeArray();
            for (ResolveInfo resolveInfo : queryIntentActivities) {
                writableNativeArray.pushString(resolveInfo.activityInfo.packageName);
            }
            promise.resolve(writableNativeArray);
            return;
        }
        promise.reject("NoEmailAppsAvailable", "No email apps available");
    }

    @ReactMethod
    public void open(String str, boolean z, Promise promise) {
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("mailto:"));
        PackageManager packageManager = getCurrentActivity().getPackageManager();
        List<ResolveInfo> queryIntentActivities = packageManager.queryIntentActivities(intent, 0);
        if (queryIntentActivities.size() > 0) {
            Intent createLaunchIntent = createLaunchIntent(queryIntentActivities.get(0), z);
            if (createLaunchIntent != null) {
                Intent createChooser = Intent.createChooser(createLaunchIntent, str);
                ArrayList arrayList = new ArrayList();
                for (int i = 1; i < queryIntentActivities.size(); i++) {
                    ResolveInfo resolveInfo = queryIntentActivities.get(i);
                    String str2 = resolveInfo.activityInfo.packageName;
                    Intent createLaunchIntent2 = createLaunchIntent(resolveInfo, z);
                    if (createLaunchIntent2 != null) {
                        arrayList.add(new LabeledIntent(createLaunchIntent2, str2, resolveInfo.loadLabel(packageManager), resolveInfo.icon));
                    }
                }
                createChooser.putExtra("android.intent.extra.INITIAL_INTENTS", (LabeledIntent[]) arrayList.toArray(new LabeledIntent[arrayList.size()]));
                setNewTaskFlag(createChooser, z);
                getCurrentActivity().startActivity(createChooser);
            }
            promise.resolve(Boolean.TRUE);
            return;
        }
        promise.reject("NoEmailAppsAvailable", "No email apps available");
    }

    @ReactMethod
    public void composeWith(String str, String str2, String str3, String str4, String str5, String str6, String str7, Promise promise) {
        Intent intent = new Intent("android.intent.action.SENDTO");
        intent.setPackage(str);
        String str8 = "mailto:" + Uri.encode(str3) + "?subject=" + Uri.encode(str4) + "&body=" + Uri.encode(str5);
        if (str6 != null) {
            str8 = str8 + "&cc=" + Uri.encode(str6);
        }
        if (str7 != null) {
            str8 = str8 + "&bcc=" + Uri.encode(str7);
        }
        intent.setData(Uri.parse(str8));
        intent.setFlags(268435456);
        if (intent.resolveActivity(getCurrentActivity().getPackageManager()) != null) {
            getCurrentActivity().startActivity(intent);
            promise.resolve("Success");
            return;
        }
        promise.reject("AppNotFound", "Application not found");
    }

    @ReactMethod
    public void compose(String str, String str2, String str3, String str4, String str5, String str6) {
        Intent intent = new Intent("android.intent.action.SENDTO");
        String str7 = "mailto:" + Uri.encode(str2) + "?subject=" + Uri.encode(str3) + "&body=" + Uri.encode(str4);
        if (str5 != null) {
            str7 = str7 + "&cc=" + Uri.encode(str5);
        }
        if (str6 != null) {
            str7 = str7 + "&bcc=" + Uri.encode(str6);
        }
        intent.setData(Uri.parse(str7));
        Intent createChooser = Intent.createChooser(intent, str);
        createChooser.setFlags(268435456);
        getReactApplicationContext().startActivity(createChooser);
    }

    private Intent createLaunchIntent(ResolveInfo resolveInfo, boolean z) {
        Intent launchIntentForPackage = getCurrentActivity().getPackageManager().getLaunchIntentForPackage(resolveInfo.activityInfo.packageName);
        if (launchIntentForPackage != null) {
            setNewTaskFlag(launchIntentForPackage, z);
        }
        return launchIntentForPackage;
    }

    private void setNewTaskFlag(Intent intent, boolean z) {
        if (z) {
            intent.addFlags(268435456);
        } else {
            intent.setFlags(intent.getFlags() & -268435457);
        }
    }
}
