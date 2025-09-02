package com.google.android.gms.internal.measurement;

import java.util.Iterator;

public final class zzat implements Iterable, zzap {
    /* access modifiers changed from: private */
    public final String zza;

    public zzat(String str) {
        if (str != null) {
            this.zza = str;
            return;
        }
        throw new IllegalArgumentException("StringValue cannot be null.");
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzat)) {
            return false;
        }
        return this.zza.equals(((zzat) obj).zza);
    }

    public final int hashCode() {
        return this.zza.hashCode();
    }

    public final Iterator iterator() {
        return new zzas(this);
    }

    public final String toString() {
        return "\"" + this.zza + "\"";
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:100:0x0182, code lost:
        com.google.android.gms.internal.measurement.zzh.zzh("toUpperCase", 0, r22);
        r6 = r19;
        r1 = new com.google.android.gms.internal.measurement.zzat(r6.zza.trim());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:101:0x0195, code lost:
        r3 = r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:102:0x0199, code lost:
        r6 = r19;
        com.google.android.gms.internal.measurement.zzh.zzh("toUpperCase", 0, r22);
        r1 = new com.google.android.gms.internal.measurement.zzat(r6.zza.toUpperCase(java.util.Locale.ENGLISH));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:103:0x01af, code lost:
        r6 = r19;
        com.google.android.gms.internal.measurement.zzh.zzh("toString", 0, r22);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:104:0x01b9, code lost:
        r6 = r19;
        com.google.android.gms.internal.measurement.zzh.zzh("toLowerCase", 0, r22);
        r1 = new com.google.android.gms.internal.measurement.zzat(r6.zza.toLowerCase(java.util.Locale.ENGLISH));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:105:0x01cf, code lost:
        r6 = r19;
        com.google.android.gms.internal.measurement.zzh.zzh("toLocaleLowerCase", 0, r22);
        r1 = new com.google.android.gms.internal.measurement.zzat(r6.zza.toLowerCase());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:106:0x01e3, code lost:
        r6 = r19;
        com.google.android.gms.internal.measurement.zzh.zzh(r11, 0, r22);
        r1 = new com.google.android.gms.internal.measurement.zzat(r6.zza.toUpperCase());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:107:0x01f7, code lost:
        r6 = r19;
        r0 = r22;
        com.google.android.gms.internal.measurement.zzh.zzj("substring", 2, r0);
        r2 = r6.zza;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:108:0x0206, code lost:
        if (r22.isEmpty() != false) goto L_0x0222;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:109:0x0208, code lost:
        r1 = r21;
        r3 = (int) com.google.android.gms.internal.measurement.zzh.zza(r1.zzb((com.google.android.gms.internal.measurement.zzap) r0.get(0)).zzh().doubleValue());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:110:0x0222, code lost:
        r1 = r21;
        r3 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:112:0x022a, code lost:
        if (r22.size() <= 1) goto L_0x0245;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:113:0x022c, code lost:
        r0 = (int) com.google.android.gms.internal.measurement.zzh.zza(r1.zzb((com.google.android.gms.internal.measurement.zzap) r0.get(1)).zzh().doubleValue());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:115:0x0245, code lost:
        r0 = r2.length();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:116:0x024a, code lost:
        r3 = java.lang.Math.min(java.lang.Math.max(r3, 0), r2.length());
        r0 = java.lang.Math.min(java.lang.Math.max(r0, 0), r2.length());
        r1 = new com.google.android.gms.internal.measurement.zzat(r2.substring(java.lang.Math.min(r3, r0), java.lang.Math.max(r3, r0)));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:117:0x0275, code lost:
        r6 = r19;
        r1 = r21;
        r0 = r22;
        com.google.android.gms.internal.measurement.zzh.zzj("split", 2, r0);
        r2 = r6.zza;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:118:0x0285, code lost:
        if (r2.length() != 0) goto L_0x029a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:119:0x0287, code lost:
        r0 = new com.google.android.gms.internal.measurement.zzae(java.util.Arrays.asList(new com.google.android.gms.internal.measurement.zzap[]{r6}));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:120:0x0296, code lost:
        r3 = r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:121:0x029a, code lost:
        r4 = new java.util.ArrayList();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:122:0x02a4, code lost:
        if (r22.isEmpty() == false) goto L_0x02ab;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:123:0x02a6, code lost:
        r4.add(r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:124:0x02ab, code lost:
        r3 = r1.zzb((com.google.android.gms.internal.measurement.zzap) r0.get(0)).zzi();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:125:0x02be, code lost:
        if (r22.size() <= 1) goto L_0x02d7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:126:0x02c0, code lost:
        r0 = com.google.android.gms.internal.measurement.zzh.zzd(r1.zzb((com.google.android.gms.internal.measurement.zzap) r0.get(1)).zzh().doubleValue());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:127:0x02d7, code lost:
        r0 = 2147483647L;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:129:0x02de, code lost:
        if (r0 != 0) goto L_0x02e6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:130:0x02e0, code lost:
        r0 = new com.google.android.gms.internal.measurement.zzae();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:131:0x02e6, code lost:
        r2 = r2.split(java.util.regex.Pattern.quote(r3), ((int) r0) + 1);
        r5 = r2.length;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:132:0x02f6, code lost:
        if (r3.isEmpty() == false) goto L_0x030e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:133:0x02f8, code lost:
        if (r5 <= 0) goto L_0x030e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:134:0x02fa, code lost:
        r8 = r2[0].isEmpty();
        r3 = -1;
        r7 = r5 - 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:135:0x030a, code lost:
        if (r2[r7].isEmpty() != false) goto L_0x0311;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:136:0x030c, code lost:
        r7 = r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:137:0x030e, code lost:
        r3 = -1;
        r7 = r5;
        r8 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:139:0x0314, code lost:
        if (((long) r5) <= r0) goto L_0x0317;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:140:0x0316, code lost:
        r7 = r7 + r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:141:0x0317, code lost:
        if (r8 >= r7) goto L_0x0326;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:142:0x0319, code lost:
        r4.add(new com.google.android.gms.internal.measurement.zzat(r2[r8]));
        r8 = r8 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:143:0x0326, code lost:
        r0 = new com.google.android.gms.internal.measurement.zzae(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:144:0x032d, code lost:
        r6 = r19;
        r1 = r21;
        r0 = r22;
        com.google.android.gms.internal.measurement.zzh.zzj("slice", 2, r0);
        r4 = r6.zza;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:145:0x033d, code lost:
        if (r22.isEmpty() != false) goto L_0x0353;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:146:0x033f, code lost:
        r7 = r1.zzb((com.google.android.gms.internal.measurement.zzap) r0.get(0)).zzh().doubleValue();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:147:0x0353, code lost:
        r7 = 0.0d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:148:0x0354, code lost:
        r7 = com.google.android.gms.internal.measurement.zzh.zza(r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:149:0x035a, code lost:
        if (r7 >= 0.0d) goto L_0x0367;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:150:0x035c, code lost:
        r7 = java.lang.Math.max(((double) r4.length()) + r7, 0.0d);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:151:0x0367, code lost:
        r7 = java.lang.Math.min(r7, (double) r4.length());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:153:0x0375, code lost:
        if (r22.size() <= 1) goto L_0x038a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:154:0x0377, code lost:
        r0 = r1.zzb((com.google.android.gms.internal.measurement.zzap) r0.get(1)).zzh().doubleValue();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:155:0x038a, code lost:
        r0 = (double) r4.length();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:156:0x038f, code lost:
        r0 = com.google.android.gms.internal.measurement.zzh.zza(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:157:0x0395, code lost:
        if (r0 >= 0.0d) goto L_0x03a2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:158:0x0397, code lost:
        r0 = java.lang.Math.max(((double) r4.length()) + r0, 0.0d);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:159:0x03a2, code lost:
        r0 = java.lang.Math.min(r0, (double) r4.length());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:160:0x03ab, code lost:
        r2 = (int) r7;
        r1 = new com.google.android.gms.internal.measurement.zzat(r4.substring(r2, java.lang.Math.max(0, ((int) r0) - r2) + r2));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:161:0x03bf, code lost:
        r6 = r19;
        r1 = r21;
        r0 = r22;
        com.google.android.gms.internal.measurement.zzh.zzj("search", 1, r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:162:0x03ce, code lost:
        if (r22.isEmpty() != false) goto L_0x03de;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:163:0x03d0, code lost:
        r16 = r1.zzb((com.google.android.gms.internal.measurement.zzap) r0.get(0)).zzi();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:164:0x03de, code lost:
        r0 = java.util.regex.Pattern.compile(r16).matcher(r6.zza);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:165:0x03ec, code lost:
        if (r0.find() == false) goto L_0x03fe;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:166:0x03ee, code lost:
        r1 = new com.google.android.gms.internal.measurement.zzah(java.lang.Double.valueOf((double) r0.start()));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:167:0x03fe, code lost:
        r0 = new com.google.android.gms.internal.measurement.zzah(java.lang.Double.valueOf(-1.0d));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:168:0x040b, code lost:
        r6 = r19;
        r1 = r21;
        r0 = r22;
        com.google.android.gms.internal.measurement.zzh.zzj(r17, 2, r0);
        r2 = com.google.android.gms.internal.measurement.zzap.zzf;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:169:0x041d, code lost:
        if (r22.isEmpty() != false) goto L_0x043f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:170:0x041f, code lost:
        r16 = r1.zzb((com.google.android.gms.internal.measurement.zzap) r0.get(0)).zzi();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:171:0x0433, code lost:
        if (r22.size() <= 1) goto L_0x043f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:172:0x0435, code lost:
        r2 = r1.zzb((com.google.android.gms.internal.measurement.zzap) r0.get(1));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:173:0x043f, code lost:
        r0 = r16;
        r3 = r6.zza;
        r4 = r3.indexOf(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:174:0x0447, code lost:
        if (r4 < 0) goto L_0x0625;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:176:0x044b, code lost:
        if ((r2 instanceof com.google.android.gms.internal.measurement.zzai) == false) goto L_0x0473;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:177:0x044d, code lost:
        r8 = 0;
        r2 = ((com.google.android.gms.internal.measurement.zzai) r2).zza(r1, java.util.Arrays.asList(new com.google.android.gms.internal.measurement.zzap[]{new com.google.android.gms.internal.measurement.zzat(r0), new com.google.android.gms.internal.measurement.zzah(java.lang.Double.valueOf((double) r4)), r6}));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:178:0x0473, code lost:
        r8 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:179:0x0474, code lost:
        r1 = new com.google.android.gms.internal.measurement.zzat(r3.substring(r8, r4) + r2.zzi() + r3.substring(r4 + r0.length()));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:180:0x049e, code lost:
        r6 = r19;
        r1 = r21;
        r0 = r22;
        com.google.android.gms.internal.measurement.zzh.zzj("match", 1, r0);
        r2 = r6.zza;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:181:0x04af, code lost:
        if (r22.size() > 0) goto L_0x04b4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:182:0x04b1, code lost:
        r0 = "";
     */
    /* JADX WARNING: Code restructure failed: missing block: B:183:0x04b4, code lost:
        r0 = r1.zzb((com.google.android.gms.internal.measurement.zzap) r0.get(0)).zzi();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:184:0x04c3, code lost:
        r0 = java.util.regex.Pattern.compile(r0).matcher(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:185:0x04cf, code lost:
        if (r0.find() == false) goto L_0x04eb;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:186:0x04d1, code lost:
        r1 = new com.google.android.gms.internal.measurement.zzae(java.util.Arrays.asList(new com.google.android.gms.internal.measurement.zzap[]{new com.google.android.gms.internal.measurement.zzat(r0.group())}));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:187:0x04eb, code lost:
        r0 = com.google.android.gms.internal.measurement.zzap.zzg;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:188:0x04ef, code lost:
        r6 = r19;
        r1 = r21;
        r0 = r22;
        com.google.android.gms.internal.measurement.zzh.zzj(r18, 2, r0);
        r2 = r6.zza;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:189:0x0502, code lost:
        if (r22.size() > 0) goto L_0x0507;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:190:0x0504, code lost:
        r3 = r16;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:191:0x0507, code lost:
        r16 = r1.zzb((com.google.android.gms.internal.measurement.zzap) r0.get(0)).zzi();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:193:0x051a, code lost:
        if (r22.size() >= 2) goto L_0x051f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:194:0x051c, code lost:
        r0 = Double.NaN;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:195:0x051f, code lost:
        r0 = r1.zzb((com.google.android.gms.internal.measurement.zzap) r0.get(1)).zzh().doubleValue();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:197:0x0536, code lost:
        if (java.lang.Double.isNaN(r0) == false) goto L_0x053b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:198:0x0538, code lost:
        r0 = Double.POSITIVE_INFINITY;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:199:0x053b, code lost:
        r0 = com.google.android.gms.internal.measurement.zzh.zza(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:200:0x053f, code lost:
        r1 = new com.google.android.gms.internal.measurement.zzah(java.lang.Double.valueOf((double) r2.lastIndexOf(r3, (int) r0)));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:201:0x0550, code lost:
        r6 = r19;
        r1 = r21;
        r0 = r22;
        com.google.android.gms.internal.measurement.zzh.zzj("indexOf", 2, r0);
        r5 = r6.zza;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:202:0x0560, code lost:
        if (r22.size() > 0) goto L_0x0565;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:203:0x0562, code lost:
        r7 = r16;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:204:0x0565, code lost:
        r16 = r1.zzb((com.google.android.gms.internal.measurement.zzap) r0.get(0)).zzi();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:206:0x0579, code lost:
        if (r22.size() >= 2) goto L_0x057c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:207:0x057c, code lost:
        r2 = r1.zzb((com.google.android.gms.internal.measurement.zzap) r0.get(1)).zzh().doubleValue();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:208:0x058f, code lost:
        r1 = new com.google.android.gms.internal.measurement.zzah(java.lang.Double.valueOf((double) r5.indexOf(r7, (int) com.google.android.gms.internal.measurement.zzh.zza(r2))));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:209:0x05a4, code lost:
        r6 = r19;
        r0 = r22;
        com.google.android.gms.internal.measurement.zzh.zzh(r12, 1, r0);
        r2 = r6.zza;
        r0 = r21.zzb((com.google.android.gms.internal.measurement.zzap) r0.get(0));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:210:0x05c5, code lost:
        if ("length".equals(r0.zzi()) == false) goto L_0x05cb;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:211:0x05c7, code lost:
        r0 = com.google.android.gms.internal.measurement.zzap.zzk;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:212:0x05cb, code lost:
        r0 = r0.zzh().doubleValue();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:213:0x05d9, code lost:
        if (r0 != java.lang.Math.floor(r0)) goto L_0x05e8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:214:0x05db, code lost:
        r0 = (int) r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:215:0x05dc, code lost:
        if (r0 < 0) goto L_0x05e8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:217:0x05e2, code lost:
        if (r0 >= r2.length()) goto L_0x05e8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:218:0x05e4, code lost:
        r0 = com.google.android.gms.internal.measurement.zzap.zzk;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:219:0x05e8, code lost:
        r0 = com.google.android.gms.internal.measurement.zzap.zzl;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:220:0x05ec, code lost:
        r6 = r19;
        r1 = r21;
        r0 = r22;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:221:0x05f6, code lost:
        if (r22.isEmpty() != false) goto L_0x0625;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:222:0x05f8, code lost:
        r3 = new java.lang.StringBuilder(r6.zza);
        r8 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:224:0x0604, code lost:
        if (r8 >= r22.size()) goto L_0x061a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:225:0x0606, code lost:
        r3.append(r1.zzb((com.google.android.gms.internal.measurement.zzap) r0.get(r8)).zzi());
        r8 = r8 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:226:0x061a, code lost:
        r1 = new com.google.android.gms.internal.measurement.zzat(r3.toString());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:227:0x0625, code lost:
        r3 = r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:228:0x0627, code lost:
        r3 = r19;
        r1 = r21;
        r0 = r22;
        com.google.android.gms.internal.measurement.zzh.zzj(r6, 1, r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:229:0x0635, code lost:
        if (r22.isEmpty() != false) goto L_0x0650;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:230:0x0637, code lost:
        r8 = (int) com.google.android.gms.internal.measurement.zzh.zza(r1.zzb((com.google.android.gms.internal.measurement.zzap) r0.get(0)).zzh().doubleValue());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:231:0x0650, code lost:
        r8 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:232:0x0652, code lost:
        r0 = r3.zza;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:233:0x0654, code lost:
        if (r8 < 0) goto L_0x066b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:235:0x065a, code lost:
        if (r8 < r0.length()) goto L_0x065d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:241:?, code lost:
        return com.google.android.gms.internal.measurement.zzap.zzm;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:242:?, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:243:?, code lost:
        return r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:244:?, code lost:
        return r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:245:?, code lost:
        return new com.google.android.gms.internal.measurement.zzat(java.lang.String.valueOf(r0.charAt(r8)));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x00bc, code lost:
        r6 = r16;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x00be, code lost:
        r12 = r18;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x00d6, code lost:
        r6 = r16;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x00d8, code lost:
        r12 = r18;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:95:0x016e, code lost:
        r1 = 65535;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:96:0x016f, code lost:
        r16 = "undefined";
        r17 = "replace";
        r18 = "lastIndexOf";
        r2 = 0.0d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:97:0x0177, code lost:
        switch(r1) {
            case 0: goto L_0x0627;
            case 1: goto L_0x05ec;
            case 2: goto L_0x05a4;
            case 3: goto L_0x0550;
            case 4: goto L_0x04ef;
            case 5: goto L_0x049e;
            case 6: goto L_0x040b;
            case 7: goto L_0x03bf;
            case 8: goto L_0x032d;
            case 9: goto L_0x0275;
            case 10: goto L_0x01f7;
            case 11: goto L_0x01e3;
            case 12: goto L_0x01cf;
            case 13: goto L_0x01b9;
            case 14: goto L_0x01af;
            case 15: goto L_0x0199;
            case 16: goto L_0x0182;
            default: goto L_0x017a;
        };
     */
    /* JADX WARNING: Code restructure failed: missing block: B:99:0x0181, code lost:
        throw new java.lang.IllegalArgumentException("Command not supported");
     */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x00c2  */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x00cf  */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x00dc  */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x00e5  */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x00ee  */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x00f7  */
    /* JADX WARNING: Removed duplicated region for block: B:62:0x00ff  */
    /* JADX WARNING: Removed duplicated region for block: B:65:0x0108  */
    /* JADX WARNING: Removed duplicated region for block: B:68:0x0111  */
    /* JADX WARNING: Removed duplicated region for block: B:71:0x0119  */
    /* JADX WARNING: Removed duplicated region for block: B:74:0x0122  */
    /* JADX WARNING: Removed duplicated region for block: B:77:0x012a  */
    /* JADX WARNING: Removed duplicated region for block: B:80:0x0133  */
    /* JADX WARNING: Removed duplicated region for block: B:83:0x013f  */
    /* JADX WARNING: Removed duplicated region for block: B:86:0x014b  */
    /* JADX WARNING: Removed duplicated region for block: B:89:0x0156  */
    /* JADX WARNING: Removed duplicated region for block: B:92:0x0162  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.internal.measurement.zzap zzcz(java.lang.String r20, com.google.android.gms.internal.measurement.zzg r21, java.util.List r22) {
        /*
            r19 = this;
            r0 = r19
            r1 = r20
            r2 = r21
            r3 = r22
            java.lang.String r6 = "trim"
            java.lang.String r10 = "charAt"
            boolean r11 = r10.equals(r1)
            java.lang.String r12 = "concat"
            java.lang.String r13 = "toLocaleUpperCase"
            java.lang.String r14 = "toString"
            java.lang.String r15 = "toLocaleLowerCase"
            java.lang.String r4 = "toLowerCase"
            java.lang.String r7 = "substring"
            java.lang.String r9 = "split"
            java.lang.String r5 = "slice"
            java.lang.String r8 = "search"
            java.lang.String r2 = "replace"
            java.lang.String r0 = "match"
            java.lang.String r3 = "lastIndexOf"
            r16 = r10
            java.lang.String r10 = "indexOf"
            r17 = r6
            java.lang.String r6 = "hasOwnProperty"
            r18 = r13
            java.lang.String r13 = "toUpperCase"
            if (r11 != 0) goto L_0x00b2
            boolean r11 = r12.equals(r1)
            if (r11 != 0) goto L_0x00b2
            boolean r11 = r6.equals(r1)
            if (r11 != 0) goto L_0x00b2
            boolean r11 = r10.equals(r1)
            if (r11 != 0) goto L_0x00b2
            boolean r11 = r3.equals(r1)
            if (r11 != 0) goto L_0x00b2
            boolean r11 = r0.equals(r1)
            if (r11 != 0) goto L_0x00b2
            boolean r11 = r2.equals(r1)
            if (r11 != 0) goto L_0x00b2
            boolean r11 = r8.equals(r1)
            if (r11 != 0) goto L_0x00b2
            boolean r11 = r5.equals(r1)
            if (r11 != 0) goto L_0x00b2
            boolean r11 = r9.equals(r1)
            if (r11 != 0) goto L_0x00b2
            boolean r11 = r7.equals(r1)
            if (r11 != 0) goto L_0x00b2
            boolean r11 = r4.equals(r1)
            if (r11 != 0) goto L_0x00b2
            boolean r11 = r15.equals(r1)
            if (r11 != 0) goto L_0x00b2
            boolean r11 = r14.equals(r1)
            if (r11 != 0) goto L_0x00b2
            boolean r11 = r13.equals(r1)
            if (r11 != 0) goto L_0x00b2
            r11 = r18
            boolean r18 = r11.equals(r1)
            if (r18 != 0) goto L_0x00ad
            r18 = r6
            r6 = r17
            boolean r17 = r6.equals(r1)
            if (r17 == 0) goto L_0x009d
            goto L_0x00b5
        L_0x009d:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.Object[] r1 = new java.lang.Object[]{r20}
            java.lang.String r2 = "%s is not a String function"
            java.lang.String r1 = java.lang.String.format(r2, r1)
            r0.<init>(r1)
            throw r0
        L_0x00ad:
            r18 = r6
            r6 = r17
            goto L_0x00b5
        L_0x00b2:
            r11 = r18
            goto L_0x00ad
        L_0x00b5:
            int r17 = r20.hashCode()
            switch(r17) {
                case -1789698943: goto L_0x0162;
                case -1776922004: goto L_0x0156;
                case -1464939364: goto L_0x014b;
                case -1361633751: goto L_0x013f;
                case -1354795244: goto L_0x0133;
                case -1137582698: goto L_0x012a;
                case -906336856: goto L_0x0122;
                case -726908483: goto L_0x0119;
                case -467511597: goto L_0x0111;
                case -399551817: goto L_0x0108;
                case 3568674: goto L_0x00ff;
                case 103668165: goto L_0x00f7;
                case 109526418: goto L_0x00ee;
                case 109648666: goto L_0x00e5;
                case 530542161: goto L_0x00dc;
                case 1094496948: goto L_0x00cf;
                case 1943291465: goto L_0x00c2;
                default: goto L_0x00bc;
            }
        L_0x00bc:
            r6 = r16
        L_0x00be:
            r12 = r18
            goto L_0x016e
        L_0x00c2:
            boolean r1 = r1.equals(r10)
            if (r1 == 0) goto L_0x00bc
            r6 = r16
            r12 = r18
            r1 = 3
            goto L_0x016f
        L_0x00cf:
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L_0x00bc
            r1 = 6
        L_0x00d6:
            r6 = r16
        L_0x00d8:
            r12 = r18
            goto L_0x016f
        L_0x00dc:
            boolean r1 = r1.equals(r7)
            if (r1 == 0) goto L_0x00bc
            r1 = 10
            goto L_0x00d6
        L_0x00e5:
            boolean r1 = r1.equals(r9)
            if (r1 == 0) goto L_0x00bc
            r1 = 9
            goto L_0x00d6
        L_0x00ee:
            boolean r1 = r1.equals(r5)
            if (r1 == 0) goto L_0x00bc
            r1 = 8
            goto L_0x00d6
        L_0x00f7:
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L_0x00bc
            r1 = 5
            goto L_0x00d6
        L_0x00ff:
            boolean r1 = r1.equals(r6)
            if (r1 == 0) goto L_0x00bc
            r1 = 16
            goto L_0x00d6
        L_0x0108:
            boolean r1 = r1.equals(r13)
            if (r1 == 0) goto L_0x00bc
            r1 = 15
            goto L_0x00d6
        L_0x0111:
            boolean r1 = r1.equals(r3)
            if (r1 == 0) goto L_0x00bc
            r1 = 4
            goto L_0x00d6
        L_0x0119:
            boolean r1 = r1.equals(r11)
            if (r1 == 0) goto L_0x00bc
            r1 = 11
            goto L_0x00d6
        L_0x0122:
            boolean r1 = r1.equals(r8)
            if (r1 == 0) goto L_0x00bc
            r1 = 7
            goto L_0x00d6
        L_0x012a:
            boolean r1 = r1.equals(r4)
            if (r1 == 0) goto L_0x00bc
            r1 = 13
            goto L_0x00d6
        L_0x0133:
            boolean r1 = r1.equals(r12)
            if (r1 == 0) goto L_0x00bc
            r6 = r16
            r12 = r18
            r1 = 1
            goto L_0x016f
        L_0x013f:
            r6 = r16
            boolean r1 = r1.equals(r6)
            if (r1 == 0) goto L_0x00be
            r12 = r18
            r1 = 0
            goto L_0x016f
        L_0x014b:
            r6 = r16
            boolean r1 = r1.equals(r15)
            if (r1 == 0) goto L_0x00be
            r1 = 12
            goto L_0x00d8
        L_0x0156:
            r6 = r16
            boolean r1 = r1.equals(r14)
            if (r1 == 0) goto L_0x00be
            r1 = 14
            goto L_0x00d8
        L_0x0162:
            r6 = r16
            r12 = r18
            boolean r1 = r1.equals(r12)
            if (r1 == 0) goto L_0x016e
            r1 = 2
            goto L_0x016f
        L_0x016e:
            r1 = -1
        L_0x016f:
            java.lang.String r16 = "undefined"
            r17 = r2
            r18 = r3
            r2 = 0
            switch(r1) {
                case 0: goto L_0x0627;
                case 1: goto L_0x05ec;
                case 2: goto L_0x05a4;
                case 3: goto L_0x0550;
                case 4: goto L_0x04ef;
                case 5: goto L_0x049e;
                case 6: goto L_0x040b;
                case 7: goto L_0x03bf;
                case 8: goto L_0x032d;
                case 9: goto L_0x0275;
                case 10: goto L_0x01f7;
                case 11: goto L_0x01e3;
                case 12: goto L_0x01cf;
                case 13: goto L_0x01b9;
                case 14: goto L_0x01af;
                case 15: goto L_0x0199;
                case 16: goto L_0x0182;
                default: goto L_0x017a;
            }
        L_0x017a:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = "Command not supported"
            r0.<init>(r1)
            throw r0
        L_0x0182:
            r0 = r22
            r1 = 0
            com.google.android.gms.internal.measurement.zzh.zzh(r13, r1, r0)
            r6 = r19
            java.lang.String r0 = r6.zza
            com.google.android.gms.internal.measurement.zzat r1 = new com.google.android.gms.internal.measurement.zzat
            java.lang.String r0 = r0.trim()
            r1.<init>(r0)
        L_0x0195:
            r3 = r6
            r6 = r1
            goto L_0x066d
        L_0x0199:
            r1 = 0
            r6 = r19
            r0 = r22
            com.google.android.gms.internal.measurement.zzh.zzh(r13, r1, r0)
            java.lang.String r0 = r6.zza
            com.google.android.gms.internal.measurement.zzat r1 = new com.google.android.gms.internal.measurement.zzat
            java.util.Locale r2 = java.util.Locale.ENGLISH
            java.lang.String r0 = r0.toUpperCase(r2)
            r1.<init>(r0)
            goto L_0x0195
        L_0x01af:
            r1 = 0
            r6 = r19
            r0 = r22
            com.google.android.gms.internal.measurement.zzh.zzh(r14, r1, r0)
            goto L_0x0625
        L_0x01b9:
            r1 = 0
            r6 = r19
            r0 = r22
            com.google.android.gms.internal.measurement.zzh.zzh(r4, r1, r0)
            java.lang.String r0 = r6.zza
            com.google.android.gms.internal.measurement.zzat r1 = new com.google.android.gms.internal.measurement.zzat
            java.util.Locale r2 = java.util.Locale.ENGLISH
            java.lang.String r0 = r0.toLowerCase(r2)
            r1.<init>(r0)
            goto L_0x0195
        L_0x01cf:
            r1 = 0
            r6 = r19
            r0 = r22
            com.google.android.gms.internal.measurement.zzh.zzh(r15, r1, r0)
            java.lang.String r0 = r6.zza
            com.google.android.gms.internal.measurement.zzat r1 = new com.google.android.gms.internal.measurement.zzat
            java.lang.String r0 = r0.toLowerCase()
            r1.<init>(r0)
            goto L_0x0195
        L_0x01e3:
            r1 = 0
            r6 = r19
            r0 = r22
            com.google.android.gms.internal.measurement.zzh.zzh(r11, r1, r0)
            java.lang.String r0 = r6.zza
            com.google.android.gms.internal.measurement.zzat r1 = new com.google.android.gms.internal.measurement.zzat
            java.lang.String r0 = r0.toUpperCase()
            r1.<init>(r0)
            goto L_0x0195
        L_0x01f7:
            r6 = r19
            r0 = r22
            r1 = 0
            r2 = 2
            com.google.android.gms.internal.measurement.zzh.zzj(r7, r2, r0)
            java.lang.String r2 = r6.zza
            boolean r3 = r22.isEmpty()
            if (r3 != 0) goto L_0x0222
            java.lang.Object r3 = r0.get(r1)
            com.google.android.gms.internal.measurement.zzap r3 = (com.google.android.gms.internal.measurement.zzap) r3
            r1 = r21
            com.google.android.gms.internal.measurement.zzap r3 = r1.zzb(r3)
            java.lang.Double r3 = r3.zzh()
            double r3 = r3.doubleValue()
            double r3 = com.google.android.gms.internal.measurement.zzh.zza(r3)
            int r3 = (int) r3
            goto L_0x0225
        L_0x0222:
            r1 = r21
            r3 = 0
        L_0x0225:
            int r4 = r22.size()
            r5 = 1
            if (r4 <= r5) goto L_0x0245
            java.lang.Object r0 = r0.get(r5)
            com.google.android.gms.internal.measurement.zzap r0 = (com.google.android.gms.internal.measurement.zzap) r0
            com.google.android.gms.internal.measurement.zzap r0 = r1.zzb(r0)
            java.lang.Double r0 = r0.zzh()
            double r0 = r0.doubleValue()
            double r0 = com.google.android.gms.internal.measurement.zzh.zza(r0)
            int r0 = (int) r0
        L_0x0243:
            r1 = 0
            goto L_0x024a
        L_0x0245:
            int r0 = r2.length()
            goto L_0x0243
        L_0x024a:
            int r3 = java.lang.Math.max(r3, r1)
            int r4 = r2.length()
            int r3 = java.lang.Math.min(r3, r4)
            int r0 = java.lang.Math.max(r0, r1)
            int r1 = r2.length()
            int r0 = java.lang.Math.min(r0, r1)
            com.google.android.gms.internal.measurement.zzat r1 = new com.google.android.gms.internal.measurement.zzat
            int r4 = java.lang.Math.min(r3, r0)
            int r0 = java.lang.Math.max(r3, r0)
            java.lang.String r0 = r2.substring(r4, r0)
            r1.<init>(r0)
            goto L_0x0195
        L_0x0275:
            r6 = r19
            r1 = r21
            r0 = r22
            r2 = 2
            com.google.android.gms.internal.measurement.zzh.zzj(r9, r2, r0)
            java.lang.String r2 = r6.zza
            int r3 = r2.length()
            if (r3 != 0) goto L_0x029a
            com.google.android.gms.internal.measurement.zzae r0 = new com.google.android.gms.internal.measurement.zzae
            r1 = 1
            com.google.android.gms.internal.measurement.zzap[] r1 = new com.google.android.gms.internal.measurement.zzap[r1]
            r3 = 0
            r1[r3] = r6
            java.util.List r1 = java.util.Arrays.asList(r1)
            r0.<init>(r1)
        L_0x0296:
            r3 = r6
            r6 = r0
            goto L_0x066d
        L_0x029a:
            r3 = 0
            java.util.ArrayList r4 = new java.util.ArrayList
            r4.<init>()
            boolean r5 = r22.isEmpty()
            if (r5 == 0) goto L_0x02ab
            r4.add(r6)
            goto L_0x0326
        L_0x02ab:
            java.lang.Object r5 = r0.get(r3)
            com.google.android.gms.internal.measurement.zzap r5 = (com.google.android.gms.internal.measurement.zzap) r5
            com.google.android.gms.internal.measurement.zzap r3 = r1.zzb(r5)
            java.lang.String r3 = r3.zzi()
            int r5 = r22.size()
            r7 = 1
            if (r5 <= r7) goto L_0x02d7
            java.lang.Object r0 = r0.get(r7)
            com.google.android.gms.internal.measurement.zzap r0 = (com.google.android.gms.internal.measurement.zzap) r0
            com.google.android.gms.internal.measurement.zzap r0 = r1.zzb(r0)
            java.lang.Double r0 = r0.zzh()
            double r0 = r0.doubleValue()
            long r0 = com.google.android.gms.internal.measurement.zzh.zzd(r0)
            goto L_0x02da
        L_0x02d7:
            r0 = 2147483647(0x7fffffff, double:1.060997895E-314)
        L_0x02da:
            r7 = 0
            int r5 = (r0 > r7 ? 1 : (r0 == r7 ? 0 : -1))
            if (r5 != 0) goto L_0x02e6
            com.google.android.gms.internal.measurement.zzae r0 = new com.google.android.gms.internal.measurement.zzae
            r0.<init>()
            goto L_0x0296
        L_0x02e6:
            java.lang.String r5 = java.util.regex.Pattern.quote(r3)
            int r7 = (int) r0
            r8 = 1
            int r7 = r7 + r8
            java.lang.String[] r2 = r2.split(r5, r7)
            int r5 = r2.length
            boolean r3 = r3.isEmpty()
            if (r3 == 0) goto L_0x030e
            if (r5 <= 0) goto L_0x030e
            r3 = 0
            r3 = r2[r3]
            boolean r8 = r3.isEmpty()
            r3 = -1
            int r7 = r5 + -1
            r9 = r2[r7]
            boolean r9 = r9.isEmpty()
            if (r9 != 0) goto L_0x0311
            r7 = r5
            goto L_0x0311
        L_0x030e:
            r3 = -1
            r7 = r5
            r8 = 0
        L_0x0311:
            long r9 = (long) r5
            int r0 = (r9 > r0 ? 1 : (r9 == r0 ? 0 : -1))
            if (r0 <= 0) goto L_0x0317
            int r7 = r7 + r3
        L_0x0317:
            if (r8 >= r7) goto L_0x0326
            com.google.android.gms.internal.measurement.zzat r0 = new com.google.android.gms.internal.measurement.zzat
            r1 = r2[r8]
            r0.<init>(r1)
            r4.add(r0)
            r0 = 1
            int r8 = r8 + r0
            goto L_0x0317
        L_0x0326:
            com.google.android.gms.internal.measurement.zzae r0 = new com.google.android.gms.internal.measurement.zzae
            r0.<init>(r4)
            goto L_0x0296
        L_0x032d:
            r6 = r19
            r1 = r21
            r0 = r22
            r4 = 2
            com.google.android.gms.internal.measurement.zzh.zzj(r5, r4, r0)
            java.lang.String r4 = r6.zza
            boolean r5 = r22.isEmpty()
            if (r5 != 0) goto L_0x0353
            r5 = 0
            java.lang.Object r7 = r0.get(r5)
            com.google.android.gms.internal.measurement.zzap r7 = (com.google.android.gms.internal.measurement.zzap) r7
            com.google.android.gms.internal.measurement.zzap r5 = r1.zzb(r7)
            java.lang.Double r5 = r5.zzh()
            double r7 = r5.doubleValue()
            goto L_0x0354
        L_0x0353:
            r7 = r2
        L_0x0354:
            double r7 = com.google.android.gms.internal.measurement.zzh.zza(r7)
            int r5 = (r7 > r2 ? 1 : (r7 == r2 ? 0 : -1))
            if (r5 >= 0) goto L_0x0367
            int r5 = r4.length()
            double r9 = (double) r5
            double r9 = r9 + r7
            double r7 = java.lang.Math.max(r9, r2)
            goto L_0x0370
        L_0x0367:
            int r5 = r4.length()
            double r9 = (double) r5
            double r7 = java.lang.Math.min(r7, r9)
        L_0x0370:
            int r5 = r22.size()
            r9 = 1
            if (r5 <= r9) goto L_0x038a
            java.lang.Object r0 = r0.get(r9)
            com.google.android.gms.internal.measurement.zzap r0 = (com.google.android.gms.internal.measurement.zzap) r0
            com.google.android.gms.internal.measurement.zzap r0 = r1.zzb(r0)
            java.lang.Double r0 = r0.zzh()
            double r0 = r0.doubleValue()
            goto L_0x038f
        L_0x038a:
            int r0 = r4.length()
            double r0 = (double) r0
        L_0x038f:
            double r0 = com.google.android.gms.internal.measurement.zzh.zza(r0)
            int r5 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r5 >= 0) goto L_0x03a2
            int r5 = r4.length()
            double r9 = (double) r5
            double r9 = r9 + r0
            double r0 = java.lang.Math.max(r9, r2)
            goto L_0x03ab
        L_0x03a2:
            int r2 = r4.length()
            double r2 = (double) r2
            double r0 = java.lang.Math.min(r0, r2)
        L_0x03ab:
            int r2 = (int) r7
            int r0 = (int) r0
            int r0 = r0 - r2
            r3 = 0
            int r0 = java.lang.Math.max(r3, r0)
            int r0 = r0 + r2
            com.google.android.gms.internal.measurement.zzat r1 = new com.google.android.gms.internal.measurement.zzat
            java.lang.String r0 = r4.substring(r2, r0)
            r1.<init>(r0)
            goto L_0x0195
        L_0x03bf:
            r6 = r19
            r1 = r21
            r0 = r22
            r2 = 1
            r3 = 0
            com.google.android.gms.internal.measurement.zzh.zzj(r8, r2, r0)
            boolean r2 = r22.isEmpty()
            if (r2 != 0) goto L_0x03de
            java.lang.Object r0 = r0.get(r3)
            com.google.android.gms.internal.measurement.zzap r0 = (com.google.android.gms.internal.measurement.zzap) r0
            com.google.android.gms.internal.measurement.zzap r0 = r1.zzb(r0)
            java.lang.String r16 = r0.zzi()
        L_0x03de:
            java.lang.String r0 = r6.zza
            java.util.regex.Pattern r1 = java.util.regex.Pattern.compile(r16)
            java.util.regex.Matcher r0 = r1.matcher(r0)
            boolean r1 = r0.find()
            if (r1 == 0) goto L_0x03fe
            com.google.android.gms.internal.measurement.zzah r1 = new com.google.android.gms.internal.measurement.zzah
            int r0 = r0.start()
            double r2 = (double) r0
            java.lang.Double r0 = java.lang.Double.valueOf(r2)
            r1.<init>(r0)
            goto L_0x0195
        L_0x03fe:
            com.google.android.gms.internal.measurement.zzah r0 = new com.google.android.gms.internal.measurement.zzah
            r1 = -4616189618054758400(0xbff0000000000000, double:-1.0)
            java.lang.Double r1 = java.lang.Double.valueOf(r1)
            r0.<init>(r1)
            goto L_0x0296
        L_0x040b:
            r3 = 2
            r6 = r19
            r1 = r21
            r0 = r22
            r2 = r17
            com.google.android.gms.internal.measurement.zzh.zzj(r2, r3, r0)
            com.google.android.gms.internal.measurement.zzap r2 = com.google.android.gms.internal.measurement.zzap.zzf
            boolean r3 = r22.isEmpty()
            if (r3 != 0) goto L_0x043f
            r3 = 0
            java.lang.Object r4 = r0.get(r3)
            com.google.android.gms.internal.measurement.zzap r4 = (com.google.android.gms.internal.measurement.zzap) r4
            com.google.android.gms.internal.measurement.zzap r3 = r1.zzb(r4)
            java.lang.String r16 = r3.zzi()
            int r3 = r22.size()
            r4 = 1
            if (r3 <= r4) goto L_0x043f
            java.lang.Object r0 = r0.get(r4)
            com.google.android.gms.internal.measurement.zzap r0 = (com.google.android.gms.internal.measurement.zzap) r0
            com.google.android.gms.internal.measurement.zzap r2 = r1.zzb(r0)
        L_0x043f:
            r0 = r16
            java.lang.String r3 = r6.zza
            int r4 = r3.indexOf(r0)
            if (r4 < 0) goto L_0x0625
            boolean r5 = r2 instanceof com.google.android.gms.internal.measurement.zzai
            if (r5 == 0) goto L_0x0473
            com.google.android.gms.internal.measurement.zzai r2 = (com.google.android.gms.internal.measurement.zzai) r2
            com.google.android.gms.internal.measurement.zzat r5 = new com.google.android.gms.internal.measurement.zzat
            r5.<init>(r0)
            double r7 = (double) r4
            com.google.android.gms.internal.measurement.zzah r9 = new com.google.android.gms.internal.measurement.zzah
            java.lang.Double r7 = java.lang.Double.valueOf(r7)
            r9.<init>(r7)
            r7 = 3
            com.google.android.gms.internal.measurement.zzap[] r7 = new com.google.android.gms.internal.measurement.zzap[r7]
            r8 = 0
            r7[r8] = r5
            r5 = 1
            r7[r5] = r9
            r5 = 2
            r7[r5] = r6
            java.util.List r5 = java.util.Arrays.asList(r7)
            com.google.android.gms.internal.measurement.zzap r2 = r2.zza(r1, r5)
            goto L_0x0474
        L_0x0473:
            r8 = 0
        L_0x0474:
            com.google.android.gms.internal.measurement.zzat r1 = new com.google.android.gms.internal.measurement.zzat
            java.lang.String r5 = r3.substring(r8, r4)
            java.lang.String r2 = r2.zzi()
            int r0 = r0.length()
            int r4 = r4 + r0
            java.lang.String r0 = r3.substring(r4)
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            r3.append(r5)
            r3.append(r2)
            r3.append(r0)
            java.lang.String r0 = r3.toString()
            r1.<init>(r0)
            goto L_0x0195
        L_0x049e:
            r6 = r19
            r1 = r21
            r2 = r0
            r3 = 1
            r0 = r22
            com.google.android.gms.internal.measurement.zzh.zzj(r2, r3, r0)
            java.lang.String r2 = r6.zza
            int r3 = r22.size()
            if (r3 > 0) goto L_0x04b4
            java.lang.String r0 = ""
            goto L_0x04c3
        L_0x04b4:
            r3 = 0
            java.lang.Object r0 = r0.get(r3)
            com.google.android.gms.internal.measurement.zzap r0 = (com.google.android.gms.internal.measurement.zzap) r0
            com.google.android.gms.internal.measurement.zzap r0 = r1.zzb(r0)
            java.lang.String r0 = r0.zzi()
        L_0x04c3:
            java.util.regex.Pattern r0 = java.util.regex.Pattern.compile(r0)
            java.util.regex.Matcher r0 = r0.matcher(r2)
            boolean r1 = r0.find()
            if (r1 == 0) goto L_0x04eb
            com.google.android.gms.internal.measurement.zzae r1 = new com.google.android.gms.internal.measurement.zzae
            com.google.android.gms.internal.measurement.zzat r2 = new com.google.android.gms.internal.measurement.zzat
            java.lang.String r0 = r0.group()
            r2.<init>(r0)
            r0 = 1
            com.google.android.gms.internal.measurement.zzap[] r0 = new com.google.android.gms.internal.measurement.zzap[r0]
            r3 = 0
            r0[r3] = r2
            java.util.List r0 = java.util.Arrays.asList(r0)
            r1.<init>(r0)
            goto L_0x0195
        L_0x04eb:
            com.google.android.gms.internal.measurement.zzap r0 = com.google.android.gms.internal.measurement.zzap.zzg
            goto L_0x0296
        L_0x04ef:
            r6 = r19
            r1 = r21
            r0 = r22
            r2 = r18
            r3 = 0
            r4 = 2
            com.google.android.gms.internal.measurement.zzh.zzj(r2, r4, r0)
            java.lang.String r2 = r6.zza
            int r5 = r22.size()
            if (r5 > 0) goto L_0x0507
        L_0x0504:
            r3 = r16
            goto L_0x0516
        L_0x0507:
            java.lang.Object r3 = r0.get(r3)
            com.google.android.gms.internal.measurement.zzap r3 = (com.google.android.gms.internal.measurement.zzap) r3
            com.google.android.gms.internal.measurement.zzap r3 = r1.zzb(r3)
            java.lang.String r16 = r3.zzi()
            goto L_0x0504
        L_0x0516:
            int r5 = r22.size()
            if (r5 >= r4) goto L_0x051f
            r0 = 9221120237041090560(0x7ff8000000000000, double:NaN)
            goto L_0x0532
        L_0x051f:
            r4 = 1
            java.lang.Object r0 = r0.get(r4)
            com.google.android.gms.internal.measurement.zzap r0 = (com.google.android.gms.internal.measurement.zzap) r0
            com.google.android.gms.internal.measurement.zzap r0 = r1.zzb(r0)
            java.lang.Double r0 = r0.zzh()
            double r0 = r0.doubleValue()
        L_0x0532:
            boolean r4 = java.lang.Double.isNaN(r0)
            if (r4 == 0) goto L_0x053b
            r0 = 9218868437227405312(0x7ff0000000000000, double:Infinity)
            goto L_0x053f
        L_0x053b:
            double r0 = com.google.android.gms.internal.measurement.zzh.zza(r0)
        L_0x053f:
            int r0 = (int) r0
            com.google.android.gms.internal.measurement.zzah r1 = new com.google.android.gms.internal.measurement.zzah
            int r0 = r2.lastIndexOf(r3, r0)
            double r2 = (double) r0
            java.lang.Double r0 = java.lang.Double.valueOf(r2)
            r1.<init>(r0)
            goto L_0x0195
        L_0x0550:
            r6 = r19
            r1 = r21
            r0 = r22
            r4 = 2
            com.google.android.gms.internal.measurement.zzh.zzj(r10, r4, r0)
            java.lang.String r5 = r6.zza
            int r7 = r22.size()
            if (r7 > 0) goto L_0x0565
        L_0x0562:
            r7 = r16
            goto L_0x0575
        L_0x0565:
            r7 = 0
            java.lang.Object r7 = r0.get(r7)
            com.google.android.gms.internal.measurement.zzap r7 = (com.google.android.gms.internal.measurement.zzap) r7
            com.google.android.gms.internal.measurement.zzap r7 = r1.zzb(r7)
            java.lang.String r16 = r7.zzi()
            goto L_0x0562
        L_0x0575:
            int r8 = r22.size()
            if (r8 >= r4) goto L_0x057c
            goto L_0x058f
        L_0x057c:
            r2 = 1
            java.lang.Object r0 = r0.get(r2)
            com.google.android.gms.internal.measurement.zzap r0 = (com.google.android.gms.internal.measurement.zzap) r0
            com.google.android.gms.internal.measurement.zzap r0 = r1.zzb(r0)
            java.lang.Double r0 = r0.zzh()
            double r2 = r0.doubleValue()
        L_0x058f:
            double r0 = com.google.android.gms.internal.measurement.zzh.zza(r2)
            int r0 = (int) r0
            com.google.android.gms.internal.measurement.zzah r1 = new com.google.android.gms.internal.measurement.zzah
            int r0 = r5.indexOf(r7, r0)
            double r2 = (double) r0
            java.lang.Double r0 = java.lang.Double.valueOf(r2)
            r1.<init>(r0)
            goto L_0x0195
        L_0x05a4:
            r6 = r19
            r1 = r21
            r0 = r22
            r2 = 1
            com.google.android.gms.internal.measurement.zzh.zzh(r12, r2, r0)
            java.lang.String r2 = r6.zza
            r3 = 0
            java.lang.Object r0 = r0.get(r3)
            com.google.android.gms.internal.measurement.zzap r0 = (com.google.android.gms.internal.measurement.zzap) r0
            com.google.android.gms.internal.measurement.zzap r0 = r1.zzb(r0)
            java.lang.String r1 = r0.zzi()
            java.lang.String r3 = "length"
            boolean r1 = r3.equals(r1)
            if (r1 == 0) goto L_0x05cb
            com.google.android.gms.internal.measurement.zzap r0 = com.google.android.gms.internal.measurement.zzap.zzk
            goto L_0x0296
        L_0x05cb:
            java.lang.Double r0 = r0.zzh()
            double r0 = r0.doubleValue()
            double r3 = java.lang.Math.floor(r0)
            int r3 = (r0 > r3 ? 1 : (r0 == r3 ? 0 : -1))
            if (r3 != 0) goto L_0x05e8
            int r0 = (int) r0
            if (r0 < 0) goto L_0x05e8
            int r1 = r2.length()
            if (r0 >= r1) goto L_0x05e8
            com.google.android.gms.internal.measurement.zzap r0 = com.google.android.gms.internal.measurement.zzap.zzk
            goto L_0x0296
        L_0x05e8:
            com.google.android.gms.internal.measurement.zzap r0 = com.google.android.gms.internal.measurement.zzap.zzl
            goto L_0x0296
        L_0x05ec:
            r6 = r19
            r1 = r21
            r0 = r22
            boolean r2 = r22.isEmpty()
            if (r2 != 0) goto L_0x0625
            java.lang.String r2 = r6.zza
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>(r2)
            r8 = 0
        L_0x0600:
            int r2 = r22.size()
            if (r8 >= r2) goto L_0x061a
            java.lang.Object r2 = r0.get(r8)
            com.google.android.gms.internal.measurement.zzap r2 = (com.google.android.gms.internal.measurement.zzap) r2
            com.google.android.gms.internal.measurement.zzap r2 = r1.zzb(r2)
            java.lang.String r2 = r2.zzi()
            r3.append(r2)
            r2 = 1
            int r8 = r8 + r2
            goto L_0x0600
        L_0x061a:
            java.lang.String r0 = r3.toString()
            com.google.android.gms.internal.measurement.zzat r1 = new com.google.android.gms.internal.measurement.zzat
            r1.<init>(r0)
            goto L_0x0195
        L_0x0625:
            r3 = r6
            goto L_0x066d
        L_0x0627:
            r3 = r19
            r1 = r21
            r0 = r22
            r2 = 1
            com.google.android.gms.internal.measurement.zzh.zzj(r6, r2, r0)
            boolean r2 = r22.isEmpty()
            if (r2 != 0) goto L_0x0650
            r2 = 0
            java.lang.Object r0 = r0.get(r2)
            com.google.android.gms.internal.measurement.zzap r0 = (com.google.android.gms.internal.measurement.zzap) r0
            com.google.android.gms.internal.measurement.zzap r0 = r1.zzb(r0)
            java.lang.Double r0 = r0.zzh()
            double r0 = r0.doubleValue()
            double r0 = com.google.android.gms.internal.measurement.zzh.zza(r0)
            int r8 = (int) r0
            goto L_0x0652
        L_0x0650:
            r2 = 0
            r8 = r2
        L_0x0652:
            java.lang.String r0 = r3.zza
            if (r8 < 0) goto L_0x066b
            int r1 = r0.length()
            if (r8 < r1) goto L_0x065d
            goto L_0x066b
        L_0x065d:
            com.google.android.gms.internal.measurement.zzat r6 = new com.google.android.gms.internal.measurement.zzat
            char r0 = r0.charAt(r8)
            java.lang.String r0 = java.lang.String.valueOf(r0)
            r6.<init>(r0)
            goto L_0x066d
        L_0x066b:
            com.google.android.gms.internal.measurement.zzap r6 = com.google.android.gms.internal.measurement.zzap.zzm
        L_0x066d:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzat.zzcz(java.lang.String, com.google.android.gms.internal.measurement.zzg, java.util.List):com.google.android.gms.internal.measurement.zzap");
    }

    public final zzap zzd() {
        return new zzat(this.zza);
    }

    public final Boolean zzg() {
        return Boolean.valueOf(!this.zza.isEmpty());
    }

    public final Double zzh() {
        String str = this.zza;
        if (str.isEmpty()) {
            return Double.valueOf(0.0d);
        }
        try {
            return Double.valueOf(str);
        } catch (NumberFormatException unused) {
            return Double.valueOf(Double.NaN);
        }
    }

    public final String zzi() {
        return this.zza;
    }

    public final Iterator zzl() {
        return new zzar(this);
    }
}
