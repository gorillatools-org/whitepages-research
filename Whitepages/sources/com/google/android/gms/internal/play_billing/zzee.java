package com.google.android.gms.internal.play_billing;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

final class zzee {
    private static final char[] zza;

    static {
        char[] cArr = new char[80];
        zza = cArr;
        Arrays.fill(cArr, ' ');
    }

    static String zza(zzec zzec, String str) {
        StringBuilder sb = new StringBuilder();
        sb.append("# ");
        sb.append(str);
        zzd(zzec, sb, 0);
        return sb.toString();
    }

    static void zzb(StringBuilder sb, int i, String str, Object obj) {
        if (obj instanceof List) {
            for (Object zzb : (List) obj) {
                zzb(sb, i, str, zzb);
            }
        } else if (obj instanceof Map) {
            for (Map.Entry zzb2 : ((Map) obj).entrySet()) {
                zzb(sb, i, str, zzb2);
            }
        } else {
            sb.append(10);
            zzc(i, sb);
            if (!str.isEmpty()) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append(Character.toLowerCase(str.charAt(0)));
                for (int i2 = 1; i2 < str.length(); i2++) {
                    char charAt = str.charAt(i2);
                    if (Character.isUpperCase(charAt)) {
                        sb2.append("_");
                    }
                    sb2.append(Character.toLowerCase(charAt));
                }
                str = sb2.toString();
            }
            sb.append(str);
            if (obj instanceof String) {
                sb.append(": \"");
                sb.append(zzfd.zza(new zzbn(((String) obj).getBytes(zzda.zzb))));
                sb.append('\"');
            } else if (obj instanceof zzbq) {
                sb.append(": \"");
                sb.append(zzfd.zza((zzbq) obj));
                sb.append('\"');
            } else if (obj instanceof zzcs) {
                sb.append(" {");
                zzd((zzcs) obj, sb, i + 2);
                sb.append(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
                zzc(i, sb);
                sb.append("}");
            } else if (obj instanceof Map.Entry) {
                int i3 = i + 2;
                sb.append(" {");
                Map.Entry entry = (Map.Entry) obj;
                zzb(sb, i3, "key", entry.getKey());
                zzb(sb, i3, "value", entry.getValue());
                sb.append(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
                zzc(i, sb);
                sb.append("}");
            } else {
                sb.append(": ");
                sb.append(obj);
            }
        }
    }

    private static void zzc(int i, StringBuilder sb) {
        while (i > 0) {
            int i2 = 80;
            if (i <= 80) {
                i2 = i;
            }
            sb.append(zza, 0, i2);
            i -= i2;
        }
    }

    private static void zzd(zzec zzec, StringBuilder sb, int i) {
        int i2;
        boolean z;
        Method method;
        Method method2;
        zzec zzec2 = zzec;
        StringBuilder sb2 = sb;
        int i3 = i;
        HashSet hashSet = new HashSet();
        HashMap hashMap = new HashMap();
        TreeMap treeMap = new TreeMap();
        Method[] declaredMethods = zzec.getClass().getDeclaredMethods();
        int length = declaredMethods.length;
        int i4 = 0;
        while (true) {
            i2 = 3;
            if (i4 >= length) {
                break;
            }
            Method method3 = declaredMethods[i4];
            if (!Modifier.isStatic(method3.getModifiers()) && method3.getName().length() >= 3) {
                if (method3.getName().startsWith("set")) {
                    hashSet.add(method3.getName());
                } else if (Modifier.isPublic(method3.getModifiers()) && method3.getParameterTypes().length == 0) {
                    if (method3.getName().startsWith("has")) {
                        hashMap.put(method3.getName(), method3);
                    } else if (method3.getName().startsWith("get")) {
                        treeMap.put(method3.getName(), method3);
                    }
                }
            }
            i4++;
        }
        for (Map.Entry entry : treeMap.entrySet()) {
            String substring = ((String) entry.getKey()).substring(i2);
            if (substring.endsWith("List") && !substring.endsWith("OrBuilderList") && !substring.equals("List") && (method2 = (Method) entry.getValue()) != null && method2.getReturnType().equals(List.class)) {
                zzb(sb2, i3, substring.substring(0, substring.length() - 4), zzcs.zzp(method2, zzec2, new Object[0]));
            } else if (substring.endsWith("Map") && !substring.equals("Map") && (method = (Method) entry.getValue()) != null && method.getReturnType().equals(Map.class) && !method.isAnnotationPresent(Deprecated.class) && Modifier.isPublic(method.getModifiers())) {
                zzb(sb2, i3, substring.substring(0, substring.length() - 3), zzcs.zzp(method, zzec2, new Object[0]));
            } else if (hashSet.contains("set".concat(substring)) && (!substring.endsWith("Bytes") || !treeMap.containsKey("get".concat(String.valueOf(substring.substring(0, substring.length() - 5)))))) {
                Method method4 = (Method) entry.getValue();
                Method method5 = (Method) hashMap.get("has".concat(substring));
                if (method4 != null) {
                    Object zzp = zzcs.zzp(method4, zzec2, new Object[0]);
                    if (method5 == null) {
                        if (zzp instanceof Boolean) {
                            if (!((Boolean) zzp).booleanValue()) {
                            }
                        } else if (zzp instanceof Integer) {
                            if (((Integer) zzp).intValue() == 0) {
                            }
                        } else if (zzp instanceof Float) {
                            if (Float.floatToRawIntBits(((Float) zzp).floatValue()) == 0) {
                            }
                        } else if (!(zzp instanceof Double)) {
                            if (zzp instanceof String) {
                                z = zzp.equals("");
                            } else if (zzp instanceof zzbq) {
                                z = zzp.equals(zzbq.zzb);
                            } else if (zzp instanceof zzec) {
                                if (zzp == ((zzec) zzp).zzh()) {
                                }
                            } else if ((zzp instanceof Enum) && ((Enum) zzp).ordinal() == 0) {
                            }
                            if (z) {
                            }
                        } else if (Double.doubleToRawLongBits(((Double) zzp).doubleValue()) == 0) {
                        }
                    } else if (!((Boolean) zzcs.zzp(method5, zzec2, new Object[0])).booleanValue()) {
                    }
                    zzb(sb2, i3, substring, zzp);
                }
            }
            i2 = 3;
        }
        if (zzec2 instanceof zzco) {
            Iterator zzf = ((zzco) zzec2).zzb.zzf();
            if (zzf.hasNext()) {
                zzcp zzcp = (zzcp) ((Map.Entry) zzf.next()).getKey();
                throw null;
            }
        }
        zzfg zzfg = ((zzcs) zzec2).zzc;
        if (zzfg != null) {
            zzfg.zzi(sb2, i3);
        }
    }
}
