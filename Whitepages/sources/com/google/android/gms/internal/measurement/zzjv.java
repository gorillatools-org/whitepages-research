package com.google.android.gms.internal.measurement;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.StrictMode;
import android.util.Log;
import androidx.collection.SimpleArrayMap;
import com.google.common.base.Optional;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public final class zzjv {
    private static volatile Optional zza;

    private zzjv() {
    }

    public static Optional zza(Context context) {
        Optional optional;
        StrictMode.ThreadPolicy allowThreadDiskReads;
        Optional optional2;
        BufferedReader bufferedReader;
        Optional optional3 = zza;
        if (optional3 == null) {
            synchronized (zzjv.class) {
                try {
                    optional3 = zza;
                    if (optional3 == null) {
                        String str = Build.TYPE;
                        String str2 = Build.TAGS;
                        int i = zzjx.zza;
                        if (!str.equals("eng")) {
                            if (str.equals("userdebug")) {
                            }
                            optional = Optional.absent();
                            zza = optional;
                            optional3 = optional;
                        }
                        if (!str2.contains("dev-keys")) {
                            if (str2.contains("test-keys")) {
                            }
                            optional = Optional.absent();
                            zza = optional;
                            optional3 = optional;
                        }
                        if (zzji.zzc() && !context.isDeviceProtectedStorage()) {
                            context = context.createDeviceProtectedStorageContext();
                        }
                        allowThreadDiskReads = StrictMode.allowThreadDiskReads();
                        StrictMode.allowThreadDiskWrites();
                        try {
                            File file = new File(context.getDir("phenotype_hermetic", 0), "overrides.txt");
                            optional2 = file.exists() ? Optional.of(file) : Optional.absent();
                        } catch (RuntimeException e) {
                            Log.e("HermeticFileOverrides", "no data dir", e);
                            optional2 = Optional.absent();
                        }
                        if (optional2.isPresent()) {
                            File file2 = (File) optional2.get();
                            try {
                                bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file2)));
                                SimpleArrayMap simpleArrayMap = new SimpleArrayMap();
                                HashMap hashMap = new HashMap();
                                while (true) {
                                    String readLine = bufferedReader.readLine();
                                    if (readLine == null) {
                                        break;
                                    }
                                    String[] split = readLine.split(" ", 3);
                                    if (split.length != 3) {
                                        Log.e("HermeticFileOverrides", "Invalid: " + readLine);
                                    } else {
                                        String str3 = new String(split[0]);
                                        String decode = Uri.decode(new String(split[1]));
                                        String str4 = (String) hashMap.get(split[2]);
                                        if (str4 == null) {
                                            String str5 = new String(split[2]);
                                            str4 = Uri.decode(str5);
                                            if (str4.length() < 1024 || str4 == str5) {
                                                hashMap.put(str5, str4);
                                            }
                                        }
                                        SimpleArrayMap simpleArrayMap2 = (SimpleArrayMap) simpleArrayMap.get(str3);
                                        if (simpleArrayMap2 == null) {
                                            simpleArrayMap2 = new SimpleArrayMap();
                                            simpleArrayMap.put(str3, simpleArrayMap2);
                                        }
                                        simpleArrayMap2.put(decode, str4);
                                    }
                                }
                                String obj = file2.toString();
                                String packageName = context.getPackageName();
                                Log.w("HermeticFileOverrides", "Parsed " + obj + " for Android package " + packageName);
                                zzjo zzjo = new zzjo(simpleArrayMap);
                                bufferedReader.close();
                                optional = Optional.of(zzjo);
                            } catch (IOException e2) {
                                throw new RuntimeException(e2);
                            } catch (Throwable th) {
                                th.addSuppressed(th);
                            }
                        } else {
                            optional = Optional.absent();
                        }
                        StrictMode.setThreadPolicy(allowThreadDiskReads);
                        zza = optional;
                        optional3 = optional;
                    }
                } catch (Throwable th2) {
                    throw th2;
                }
            }
        }
        return optional3;
        throw th;
    }
}
