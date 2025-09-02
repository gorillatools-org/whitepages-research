package io.branch.referral;

import android.content.Context;
import android.text.TextUtils;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;

abstract class BranchPreinstall {
    public static void getPreinstallSystemData(Branch branch, Context context) {
        if (branch != null) {
            String checkForBranchPreinstallInSystem = checkForBranchPreinstallInSystem();
            if (!TextUtils.isEmpty(checkForBranchPreinstallInSystem)) {
                readBranchFile(checkForBranchPreinstallInSystem, branch, context);
            }
        }
    }

    private static String checkForBranchPreinstallInSystem() {
        try {
            return (String) Class.forName("android.os.SystemProperties").getMethod("get", new Class[]{String.class}).invoke((Object) null, new Object[]{"io.branch.preinstall.apps.path"});
        } catch (Exception unused) {
            return null;
        }
    }

    private static void readBranchFile(final String str, final Branch branch, final Context context) {
        new Thread(new Runnable() {
            public void run() {
                try {
                    StringBuilder sb = new StringBuilder();
                    BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(str)));
                    while (true) {
                        String readLine = bufferedReader.readLine();
                        if (readLine == null) {
                            break;
                        }
                        sb.append(readLine);
                    }
                    bufferedReader.close();
                    JSONObject jSONObject = new JSONObject(sb.toString().trim());
                    if (!TextUtils.isEmpty(jSONObject.toString())) {
                        BranchPreinstall.getBranchFileContent(jSONObject, branch, context);
                        return;
                    }
                    throw new FileNotFoundException();
                } catch (FileNotFoundException e) {
                    BranchLogger.d(e.getMessage());
                } catch (IOException e2) {
                    BranchLogger.d(e2.getMessage());
                } catch (JSONException e3) {
                    BranchLogger.d(e3.getMessage());
                }
            }
        }).start();
    }

    public static void getBranchFileContent(JSONObject jSONObject, Branch branch, Context context) {
        Iterator<String> keys = jSONObject.keys();
        while (keys.hasNext()) {
            String next = keys.next();
            try {
                if (next.equals("apps") && (jSONObject.get(next) instanceof JSONObject) && jSONObject.getJSONObject(next).get(SystemObserver.getPackageName(context)) != null) {
                    JSONObject jSONObject2 = jSONObject.getJSONObject(next).getJSONObject(SystemObserver.getPackageName(context));
                    Iterator<String> keys2 = jSONObject2.keys();
                    while (keys2.hasNext()) {
                        String next2 = keys2.next();
                        Defines$PreinstallKey defines$PreinstallKey = Defines$PreinstallKey.campaign;
                        if (!next2.equals(defines$PreinstallKey.getKey()) || !TextUtils.isEmpty(PrefHelper.getInstance(context).getInstallMetaData(defines$PreinstallKey.getKey()))) {
                            Defines$PreinstallKey defines$PreinstallKey2 = Defines$PreinstallKey.partner;
                            if (!next2.equals(defines$PreinstallKey2.getKey()) || !TextUtils.isEmpty(PrefHelper.getInstance(context).getInstallMetaData(defines$PreinstallKey2.getKey()))) {
                                branch.setRequestMetadata(next2, jSONObject2.get(next2).toString());
                            } else {
                                branch.setPreinstallPartner(jSONObject2.get(next2).toString());
                            }
                        } else {
                            branch.setPreinstallCampaign(jSONObject2.get(next2).toString());
                        }
                    }
                }
            } catch (JSONException e) {
                BranchLogger.d(e.getMessage());
            }
        }
    }

    public static void setBranchPreInstallGoogleReferrer(Context context, HashMap hashMap) {
        Branch instance = Branch.getInstance();
        PrefHelper instance2 = PrefHelper.getInstance(context);
        if (TextUtils.isEmpty(instance2.getInstallMetaData(Defines$PreinstallKey.partner.getKey())) && TextUtils.isEmpty(instance2.getInstallMetaData(Defines$PreinstallKey.campaign.getKey()))) {
            Defines$Jsonkey defines$Jsonkey = Defines$Jsonkey.UTMCampaign;
            if (!TextUtils.isEmpty((CharSequence) hashMap.get(defines$Jsonkey.getKey()))) {
                instance.setPreinstallCampaign((String) hashMap.get(defines$Jsonkey.getKey()));
            }
            Defines$Jsonkey defines$Jsonkey2 = Defines$Jsonkey.UTMMedium;
            if (!TextUtils.isEmpty((CharSequence) hashMap.get(defines$Jsonkey2.getKey()))) {
                instance.setPreinstallPartner((String) hashMap.get(defines$Jsonkey2.getKey()));
            }
        }
    }
}
