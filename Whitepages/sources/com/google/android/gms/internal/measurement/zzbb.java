package com.google.android.gms.internal.measurement;

import java.util.Arrays;
import java.util.Iterator;

public final class zzbb {
    /* JADX WARNING: type inference failed for: r0v120, types: [com.google.android.gms.internal.measurement.zzap] */
    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:100:0x01f8, code lost:
        r4 = r24.zzc();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:101:0x01fc, code lost:
        r2 = r24.zzc();
        r6 = new com.google.android.gms.internal.measurement.zzae();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:102:0x020a, code lost:
        if (r26.size() <= 1) goto L_0x0272;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:103:0x020c, code lost:
        r1 = java.lang.Math.max(0, (int) com.google.android.gms.internal.measurement.zzh.zza(r3.zzb((com.google.android.gms.internal.measurement.zzap) r0.get(1)).zzh().doubleValue()));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:104:0x0228, code lost:
        if (r1 <= 0) goto L_0x0244;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:105:0x022a, code lost:
        r7 = r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:107:0x0231, code lost:
        if (r7 >= java.lang.Math.min(r2, r4 + r1)) goto L_0x0244;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:108:0x0233, code lost:
        r6.zzq(r6.zzc(), r5.zze(r4));
        r5.zzp(r4);
        r7 = r7 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:110:0x0249, code lost:
        if (r26.size() <= 2) goto L_0x0285;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:111:0x024b, code lost:
        r7 = 2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:113:0x0250, code lost:
        if (r7 >= r26.size()) goto L_0x0285;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:114:0x0252, code lost:
        r1 = r3.zzb((com.google.android.gms.internal.measurement.zzap) r0.get(r7));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:115:0x025e, code lost:
        if ((r1 instanceof com.google.android.gms.internal.measurement.zzag) != false) goto L_0x026a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:116:0x0260, code lost:
        r5.zzo((r4 + r7) - 2, r1);
        r7 = r7 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:118:0x0271, code lost:
        throw new java.lang.IllegalArgumentException("Failed to parse elements to add");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:119:0x0272, code lost:
        if (r4 >= r2) goto L_0x0285;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:120:0x0274, code lost:
        r6.zzq(r6.zzc(), r5.zze(r4));
        r5.zzq(r4, (com.google.android.gms.internal.measurement.zzap) null);
        r4 = r4 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:122:0x0288, code lost:
        r5 = r24;
        r3 = r25;
        r0 = r26;
        com.google.android.gms.internal.measurement.zzh.zzj(com.facebook.hermes.intl.Constants.SORT, 1, r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:123:0x0297, code lost:
        if (r24.zzc() < 2) goto L_0x043a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:124:0x0299, code lost:
        r2 = r24.zzm();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:125:0x02a1, code lost:
        if (r26.isEmpty() != false) goto L_0x02be;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:126:0x02a3, code lost:
        r0 = r3.zzb((com.google.android.gms.internal.measurement.zzap) r0.get(0));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:127:0x02b0, code lost:
        if ((r0 instanceof com.google.android.gms.internal.measurement.zzai) == false) goto L_0x02b6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:128:0x02b2, code lost:
        r1 = r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:130:0x02bd, code lost:
        throw new java.lang.IllegalArgumentException("Comparator should be a method");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:131:0x02be, code lost:
        java.util.Collections.sort(r2, new com.google.android.gms.internal.measurement.zzba(r1, r3));
        r24.zzn();
        r0 = r2.iterator();
        r7 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:133:0x02d2, code lost:
        if (r0.hasNext() == false) goto L_0x043a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:134:0x02d4, code lost:
        r5.zzq(r7, (com.google.android.gms.internal.measurement.zzap) r0.next());
        r7 = r7 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:135:0x02e2, code lost:
        r5 = r24;
        r3 = r25;
        r0 = r26;
        com.google.android.gms.internal.measurement.zzh.zzh("some", 1, r0);
        r0 = r3.zzb((com.google.android.gms.internal.measurement.zzap) r0.get(0));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:136:0x02f9, code lost:
        if ((r0 instanceof com.google.android.gms.internal.measurement.zzai) == false) goto L_0x0355;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:138:0x02ff, code lost:
        if (r24.zzc() != 0) goto L_0x0305;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:140:0x0305, code lost:
        r0 = (com.google.android.gms.internal.measurement.zzai) r0;
        r1 = r24.zzk();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:142:0x030f, code lost:
        if (r1.hasNext() == false) goto L_0x0351;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:143:0x0311, code lost:
        r2 = ((java.lang.Integer) r1.next()).intValue();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:144:0x031f, code lost:
        if (r5.zzs(r2) == false) goto L_0x030b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:146:0x034b, code lost:
        if (r0.zza(r3, java.util.Arrays.asList(new com.google.android.gms.internal.measurement.zzap[]{r5.zze(r2), new com.google.android.gms.internal.measurement.zzah(java.lang.Double.valueOf((double) r2)), r5})).zzg().booleanValue() == false) goto L_0x030b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:150:0x035a, code lost:
        throw new java.lang.IllegalArgumentException("Callback should be a method");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:151:0x035b, code lost:
        r5 = r24;
        r0 = r26;
        r3 = r25;
        com.google.android.gms.internal.measurement.zzh.zzj("slice", 2, r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:152:0x036a, code lost:
        if (r26.isEmpty() == false) goto L_0x0372;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:154:0x0372, code lost:
        r1 = (double) r24.zzc();
        r6 = com.google.android.gms.internal.measurement.zzh.zza(r3.zzb((com.google.android.gms.internal.measurement.zzap) r0.get(0)).zzh().doubleValue());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:155:0x0392, code lost:
        if (r6 >= 0.0d) goto L_0x039a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:156:0x0394, code lost:
        r6 = java.lang.Math.max(r6 + r1, 0.0d);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:157:0x039a, code lost:
        r6 = java.lang.Math.min(r6, r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:159:0x03a3, code lost:
        if (r26.size() != 2) goto L_0x03cc;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:160:0x03a5, code lost:
        r3 = com.google.android.gms.internal.measurement.zzh.zza(r3.zzb((com.google.android.gms.internal.measurement.zzap) r0.get(1)).zzh().doubleValue());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:161:0x03c0, code lost:
        if (r3 >= 0.0d) goto L_0x03c8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:162:0x03c2, code lost:
        r1 = java.lang.Math.max(r1 + r3, 0.0d);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:163:0x03c8, code lost:
        r1 = java.lang.Math.min(r1, r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:164:0x03cc, code lost:
        r0 = new com.google.android.gms.internal.measurement.zzae();
        r3 = (int) r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:166:0x03d5, code lost:
        if (((double) r3) >= r1) goto L_0x079d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:167:0x03d7, code lost:
        r0.zzq(r0.zzc(), r5.zze(r3));
        r3 = r3 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:168:0x03e5, code lost:
        r5 = r24;
        com.google.android.gms.internal.measurement.zzh.zzh("shift", 0, r26);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:169:0x03f1, code lost:
        if (r24.zzc() != 0) goto L_0x03f7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:171:0x03f7, code lost:
        r0 = r5.zze(0);
        r5.zzp(0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:172:0x0400, code lost:
        r5 = r24;
        com.google.android.gms.internal.measurement.zzh.zzh(r21, 0, r26);
        r0 = r24.zzc();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:173:0x040e, code lost:
        if (r0 == 0) goto L_0x043a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:174:0x0410, code lost:
        r7 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:176:0x0414, code lost:
        if (r7 >= (r0 / 2)) goto L_0x043a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:178:0x041a, code lost:
        if (r5.zzs(r7) == false) goto L_0x0436;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:179:0x041c, code lost:
        r2 = r5.zze(r7);
        r5.zzq(r7, (com.google.android.gms.internal.measurement.zzap) null);
        r3 = (r0 - 1) - r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:180:0x042a, code lost:
        if (r5.zzs(r3) == false) goto L_0x0433;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:181:0x042c, code lost:
        r5.zzq(r7, r5.zze(r3));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:182:0x0433, code lost:
        r5.zzq(r3, r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:183:0x0436, code lost:
        r7 = r7 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:186:0x0448, code lost:
        return zzc(r24, r25, r26, false);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:188:0x0454, code lost:
        return zzc(r24, r25, r26, true);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:189:0x0455, code lost:
        r5 = r24;
        r3 = r25;
        r0 = r26;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:190:0x045f, code lost:
        if (r26.isEmpty() != false) goto L_0x047d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:191:0x0461, code lost:
        r0 = r26.iterator();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:193:0x0469, code lost:
        if (r0.hasNext() == false) goto L_0x047d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:194:0x046b, code lost:
        r5.zzq(r24.zzc(), r3.zzb((com.google.android.gms.internal.measurement.zzap) r0.next()));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:196:0x048b, code lost:
        return new com.google.android.gms.internal.measurement.zzah(java.lang.Double.valueOf((double) r24.zzc()));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:197:0x048c, code lost:
        r5 = r24;
        com.google.android.gms.internal.measurement.zzh.zzh("pop", 0, r26);
        r0 = r24.zzc();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:198:0x0498, code lost:
        if (r0 != 0) goto L_0x049e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:200:0x049e, code lost:
        r0 = r0 - 1;
        r1 = r5.zze(r0);
        r5.zzp(r0);
        r1 = r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:202:0x04aa, code lost:
        r5 = r24;
        r3 = r25;
        r0 = r26;
        com.google.android.gms.internal.measurement.zzh.zzh("map", 1, r0);
        r0 = r3.zzb((com.google.android.gms.internal.measurement.zzap) r0.get(0));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:203:0x04c1, code lost:
        if ((r0 instanceof com.google.android.gms.internal.measurement.zzao) == false) goto L_0x04d8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:205:0x04c7, code lost:
        if (r24.zzc() != 0) goto L_0x04d0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:209:0x04dd, code lost:
        throw new java.lang.IllegalArgumentException("Callback should be a method");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:210:0x04de, code lost:
        r5 = r24;
        r3 = r25;
        r0 = r26;
        com.google.android.gms.internal.measurement.zzh.zzj("lastIndexOf", 2, r0);
        r1 = com.google.android.gms.internal.measurement.zzap.zzf;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:211:0x04ee, code lost:
        if (r26.isEmpty() != false) goto L_0x04fb;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:212:0x04f0, code lost:
        r1 = r3.zzb((com.google.android.gms.internal.measurement.zzap) r0.get(0));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:213:0x04fb, code lost:
        r2 = r24.zzc() - 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:214:0x0506, code lost:
        if (r26.size() <= 1) goto L_0x0542;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:215:0x0508, code lost:
        r0 = r3.zzb((com.google.android.gms.internal.measurement.zzap) r0.get(1));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:216:0x051e, code lost:
        if (java.lang.Double.isNaN(r0.zzh().doubleValue()) == false) goto L_0x052a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:217:0x0520, code lost:
        r2 = (double) (r24.zzc() - 1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:218:0x0527, code lost:
        r6 = 0.0d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:219:0x052a, code lost:
        r2 = com.google.android.gms.internal.measurement.zzh.zza(r0.zzh().doubleValue());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:221:0x0539, code lost:
        if (r2 >= 0.0d) goto L_0x0545;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:222:0x053b, code lost:
        r2 = r2 + ((double) r24.zzc());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:223:0x0542, code lost:
        r6 = 0.0d;
        r2 = (double) r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:225:0x0547, code lost:
        if (r2 >= r6) goto L_0x0554;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:227:0x0554, code lost:
        r0 = (int) java.lang.Math.min((double) r24.zzc(), r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:228:0x055e, code lost:
        if (r0 < 0) goto L_0x0580;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:230:0x0564, code lost:
        if (r5.zzs(r0) == false) goto L_0x057d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:232:0x056e, code lost:
        if (com.google.android.gms.internal.measurement.zzh.zzl(r5.zze(r0), r1) == false) goto L_0x057d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:234:0x057d, code lost:
        r0 = r0 - 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:236:0x058b, code lost:
        r5 = r24;
        r3 = r25;
        r0 = r26;
        r1 = r16;
        com.google.android.gms.internal.measurement.zzh.zzj("join", 1, r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:237:0x059b, code lost:
        if (r24.zzc() != 0) goto L_0x05a1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:240:0x05a5, code lost:
        if (r26.isEmpty() != false) goto L_0x05c3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:241:0x05a7, code lost:
        r0 = r3.zzb((com.google.android.gms.internal.measurement.zzap) r0.get(0));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:242:0x05b4, code lost:
        if ((r0 instanceof com.google.android.gms.internal.measurement.zzan) != false) goto L_0x05c0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:244:0x05b8, code lost:
        if ((r0 instanceof com.google.android.gms.internal.measurement.zzau) == false) goto L_0x05bb;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:245:0x05bb, code lost:
        r4 = r0.zzi();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:246:0x05c0, code lost:
        r4 = "";
     */
    /* JADX WARNING: Code restructure failed: missing block: B:247:0x05c3, code lost:
        r4 = r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:249:0x05cf, code lost:
        r5 = r24;
        r3 = r25;
        r0 = r26;
        com.google.android.gms.internal.measurement.zzh.zzj(r22, 2, r0);
        r1 = com.google.android.gms.internal.measurement.zzap.zzf;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:250:0x05e1, code lost:
        if (r26.isEmpty() != false) goto L_0x05ee;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:251:0x05e3, code lost:
        r1 = r3.zzb((com.google.android.gms.internal.measurement.zzap) r0.get(0));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:253:0x05f3, code lost:
        if (r26.size() <= 1) goto L_0x062c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:254:0x05f5, code lost:
        r2 = com.google.android.gms.internal.measurement.zzh.zza(r3.zzb((com.google.android.gms.internal.measurement.zzap) r0.get(1)).zzh().doubleValue());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:255:0x0612, code lost:
        if (r2 < ((double) r24.zzc())) goto L_0x061f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:258:0x0623, code lost:
        if (r2 >= 0.0d) goto L_0x062f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:259:0x0625, code lost:
        r2 = r2 + ((double) r24.zzc());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:260:0x062c, code lost:
        r2 = 0.0d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:261:0x062f, code lost:
        r0 = r24.zzk();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:263:0x0637, code lost:
        if (r0.hasNext() == false) goto L_0x065d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:264:0x0639, code lost:
        r4 = ((java.lang.Integer) r0.next()).intValue();
        r6 = (double) r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:265:0x0646, code lost:
        if (r6 < r2) goto L_0x0633;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:267:0x0650, code lost:
        if (com.google.android.gms.internal.measurement.zzh.zzl(r5.zze(r4), r1) == false) goto L_0x0633;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:270:0x0668, code lost:
        r5 = r24;
        r3 = r25;
        r0 = r26;
        com.google.android.gms.internal.measurement.zzh.zzh("forEach", 1, r0);
        r0 = r3.zzb((com.google.android.gms.internal.measurement.zzap) r0.get(0));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:271:0x067f, code lost:
        if ((r0 instanceof com.google.android.gms.internal.measurement.zzao) == false) goto L_0x0694;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:273:0x0685, code lost:
        if (r24.zzb() != 0) goto L_0x068b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:275:0x068b, code lost:
        zzb(r5, r3, (com.google.android.gms.internal.measurement.zzao) r0, (java.lang.Boolean) null, (java.lang.Boolean) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:277:0x0699, code lost:
        throw new java.lang.IllegalArgumentException("Callback should be a method");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:278:0x069a, code lost:
        r5 = r24;
        r3 = r25;
        r0 = r26;
        com.google.android.gms.internal.measurement.zzh.zzh(com.facebook.react.uimanager.ViewProps.FILTER, 1, r0);
        r0 = r3.zzb((com.google.android.gms.internal.measurement.zzap) r0.get(0));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:279:0x06b1, code lost:
        if ((r0 instanceof com.google.android.gms.internal.measurement.zzao) == false) goto L_0x06f3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:281:0x06b7, code lost:
        if (r24.zzb() != 0) goto L_0x06c0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:283:0x06c0, code lost:
        r2 = (com.google.android.gms.internal.measurement.zzae) r24.zzd();
        r0 = zzb(r5, r3, (com.google.android.gms.internal.measurement.zzao) r0, (java.lang.Boolean) null, java.lang.Boolean.TRUE);
        r1 = new com.google.android.gms.internal.measurement.zzae();
        r0 = r0.zzk();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:285:0x06db, code lost:
        if (r0.hasNext() == false) goto L_0x04a7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:286:0x06dd, code lost:
        r1.zzq(r1.zzc(), r2.zze(((java.lang.Integer) r0.next()).intValue()));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:288:0x06f8, code lost:
        throw new java.lang.IllegalArgumentException("Callback should be a method");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:289:0x06f9, code lost:
        r1 = r24;
        r3 = r25;
        r0 = r26;
        com.google.android.gms.internal.measurement.zzh.zzh("every", 1, r0);
        r0 = r3.zzb((com.google.android.gms.internal.measurement.zzap) r0.get(0));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:290:0x0710, code lost:
        if ((r0 instanceof com.google.android.gms.internal.measurement.zzao) == false) goto L_0x0736;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:292:0x0716, code lost:
        if (r24.zzc() != 0) goto L_0x071c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:295:0x072e, code lost:
        if (zzb(r1, r3, (com.google.android.gms.internal.measurement.zzao) r0, java.lang.Boolean.FALSE, java.lang.Boolean.TRUE).zzc() == r24.zzc()) goto L_0x0733;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:299:0x073b, code lost:
        throw new java.lang.IllegalArgumentException("Callback should be a method");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0033, code lost:
        r6 = r18;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:300:0x073c, code lost:
        r1 = r24;
        r3 = r25;
        r0 = r26;
        r1 = (com.google.android.gms.internal.measurement.zzae) r24.zzd();
        r1 = r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:301:0x074c, code lost:
        if (r26.isEmpty() != false) goto L_0x04a7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:302:0x074e, code lost:
        r0 = r26.iterator();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:304:0x0756, code lost:
        if (r0.hasNext() == false) goto L_0x04a7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:305:0x0758, code lost:
        r2 = r3.zzb((com.google.android.gms.internal.measurement.zzap) r0.next());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:306:0x0764, code lost:
        if ((r2 instanceof com.google.android.gms.internal.measurement.zzag) != false) goto L_0x0795;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:307:0x0766, code lost:
        r4 = r1.zzc();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:308:0x076c, code lost:
        if ((r2 instanceof com.google.android.gms.internal.measurement.zzae) == false) goto L_0x0791;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:309:0x076e, code lost:
        r2 = (com.google.android.gms.internal.measurement.zzae) r2;
        r5 = r2.zzk();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:311:0x0778, code lost:
        if (r5.hasNext() == false) goto L_0x0752;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:312:0x077a, code lost:
        r6 = (java.lang.Integer) r5.next();
        r1.zzq(r6.intValue() + r4, r2.zze(r6.intValue()));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:313:0x0791, code lost:
        r1.zzq(r4, r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:315:0x079c, code lost:
        throw new java.lang.IllegalStateException("Failed evaluation of arguments");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:344:0x04a7, code lost:
        r1 = r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:347:0x04a7, code lost:
        r1 = r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:350:?, code lost:
        return new com.google.android.gms.internal.measurement.zzae();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:351:?, code lost:
        return r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:352:?, code lost:
        return com.google.android.gms.internal.measurement.zzap.zzl;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:353:?, code lost:
        return com.google.android.gms.internal.measurement.zzap.zzk;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:354:?, code lost:
        return com.google.android.gms.internal.measurement.zzap.zzl;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:355:?, code lost:
        return r24.zzd();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:356:?, code lost:
        return com.google.android.gms.internal.measurement.zzap.zzf;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:357:?, code lost:
        return r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:358:?, code lost:
        return r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:359:?, code lost:
        return com.google.android.gms.internal.measurement.zzap.zzf;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:360:?, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:361:?, code lost:
        return new com.google.android.gms.internal.measurement.zzae();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:362:?, code lost:
        return zzb(r5, r3, (com.google.android.gms.internal.measurement.zzao) r0, (java.lang.Boolean) null, (java.lang.Boolean) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:363:?, code lost:
        return new com.google.android.gms.internal.measurement.zzah(java.lang.Double.valueOf(-1.0d));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:364:?, code lost:
        return new com.google.android.gms.internal.measurement.zzah(java.lang.Double.valueOf((double) r0));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:365:?, code lost:
        return new com.google.android.gms.internal.measurement.zzah(java.lang.Double.valueOf(-1.0d));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:366:?, code lost:
        return com.google.android.gms.internal.measurement.zzap.zzm;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:367:?, code lost:
        return new com.google.android.gms.internal.measurement.zzat(r5.zzj(r4));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:368:?, code lost:
        return new com.google.android.gms.internal.measurement.zzah(java.lang.Double.valueOf(-1.0d));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:369:?, code lost:
        return new com.google.android.gms.internal.measurement.zzah(java.lang.Double.valueOf(r6));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:370:?, code lost:
        return new com.google.android.gms.internal.measurement.zzah(java.lang.Double.valueOf(-1.0d));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:371:?, code lost:
        return com.google.android.gms.internal.measurement.zzap.zzf;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:372:?, code lost:
        return com.google.android.gms.internal.measurement.zzap.zzf;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:373:?, code lost:
        return new com.google.android.gms.internal.measurement.zzae();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:374:?, code lost:
        return com.google.android.gms.internal.measurement.zzap.zzk;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:375:?, code lost:
        return com.google.android.gms.internal.measurement.zzap.zzl;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:376:?, code lost:
        return com.google.android.gms.internal.measurement.zzap.zzk;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:377:?, code lost:
        return r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:0x00ff, code lost:
        r0 = 65535;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x0101, code lost:
        r21 = "reverse";
        r22 = "indexOf";
        r1 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:0x010a, code lost:
        switch(r0) {
            case 0: goto L_0x073c;
            case 1: goto L_0x06f9;
            case 2: goto L_0x069a;
            case 3: goto L_0x0668;
            case 4: goto L_0x05cf;
            case 5: goto L_0x058b;
            case 6: goto L_0x04de;
            case 7: goto L_0x04aa;
            case 8: goto L_0x048c;
            case 9: goto L_0x0455;
            case 10: goto L_0x0449;
            case 11: goto L_0x043d;
            case 12: goto L_0x0400;
            case 13: goto L_0x03e5;
            case 14: goto L_0x035b;
            case 15: goto L_0x02e2;
            case 16: goto L_0x0288;
            case 17: goto L_0x01bb;
            case 18: goto L_0x01a7;
            case 19: goto L_0x0115;
            default: goto L_0x010d;
        };
     */
    /* JADX WARNING: Code restructure failed: missing block: B:68:0x0114, code lost:
        throw new java.lang.IllegalArgumentException("Command not supported");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x003e, code lost:
        r6 = r18;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:70:0x0119, code lost:
        if (r26.isEmpty() != false) goto L_0x0196;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:71:0x011b, code lost:
        r0 = new com.google.android.gms.internal.measurement.zzae();
        r1 = r26.iterator();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:73:0x0128, code lost:
        if (r1.hasNext() == false) goto L_0x014a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:74:0x012a, code lost:
        r2 = r25.zzb((com.google.android.gms.internal.measurement.zzap) r1.next());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:75:0x0138, code lost:
        if ((r2 instanceof com.google.android.gms.internal.measurement.zzag) != false) goto L_0x0142;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:76:0x013a, code lost:
        r0.zzq(r0.zzc(), r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:78:0x0149, code lost:
        throw new java.lang.IllegalStateException("Argument evaluation failed");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:79:0x014a, code lost:
        r1 = r0.zzc();
        r2 = r24.zzk();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:81:0x0156, code lost:
        if (r2.hasNext() == false) goto L_0x0171;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:82:0x0158, code lost:
        r3 = (java.lang.Integer) r2.next();
        r0.zzq(r3.intValue() + r1, r24.zze(r3.intValue()));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:83:0x0171, code lost:
        r5 = r24;
        r24.zzn();
        r1 = r0.zzk();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:85:0x017e, code lost:
        if (r1.hasNext() == false) goto L_0x0198;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:86:0x0180, code lost:
        r2 = (java.lang.Integer) r1.next();
        r5.zzq(r2.intValue(), r0.zze(r2.intValue()));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:87:0x0196, code lost:
        r5 = r24;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:89:0x01a6, code lost:
        return new com.google.android.gms.internal.measurement.zzah(java.lang.Double.valueOf((double) r24.zzc()));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:90:0x01a7, code lost:
        com.google.android.gms.internal.measurement.zzh.zzh(r6, 0, r26);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:91:0x01ba, code lost:
        return new com.google.android.gms.internal.measurement.zzat(r24.zzj(r16));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:92:0x01bb, code lost:
        r5 = r24;
        r3 = r25;
        r0 = r26;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:93:0x01c6, code lost:
        if (r26.isEmpty() == false) goto L_0x01cf;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:95:0x01cf, code lost:
        r4 = (int) com.google.android.gms.internal.measurement.zzh.zza(r3.zzb((com.google.android.gms.internal.measurement.zzap) r0.get(0)).zzh().doubleValue());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:96:0x01e6, code lost:
        if (r4 >= 0) goto L_0x01f2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:97:0x01e8, code lost:
        r4 = java.lang.Math.max(0, r4 + r24.zzc());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:99:0x01f6, code lost:
        if (r4 <= r24.zzc()) goto L_0x01fc;
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.google.android.gms.internal.measurement.zzap zza(java.lang.String r23, com.google.android.gms.internal.measurement.zzae r24, com.google.android.gms.internal.measurement.zzg r25, java.util.List r26) {
        /*
            r0 = r23
            r1 = r24
            r2 = r25
            r3 = r26
            java.lang.String r4 = ","
            int r6 = r23.hashCode()
            java.lang.String r7 = "toString"
            java.lang.String r8 = "filter"
            java.lang.String r9 = "forEach"
            java.lang.String r10 = "lastIndexOf"
            java.lang.String r11 = "map"
            java.lang.String r12 = "pop"
            java.lang.String r13 = "join"
            java.lang.String r14 = "some"
            java.lang.String r15 = "sort"
            java.lang.String r5 = "every"
            r16 = r4
            java.lang.String r4 = "shift"
            java.lang.String r3 = "slice"
            java.lang.String r1 = "reverse"
            java.lang.String r2 = "indexOf"
            r17 = -1
            r18 = r7
            switch(r6) {
                case -1776922004: goto L_0x00f4;
                case -1354795244: goto L_0x00e8;
                case -1274492040: goto L_0x00de;
                case -934873754: goto L_0x00d2;
                case -895859076: goto L_0x00c6;
                case -678635926: goto L_0x00bc;
                case -467511597: goto L_0x00b4;
                case -277637751: goto L_0x00a9;
                case 107868: goto L_0x00a1;
                case 111185: goto L_0x0098;
                case 3267882: goto L_0x0090;
                case 3452698: goto L_0x0085;
                case 3536116: goto L_0x007c;
                case 3536286: goto L_0x0073;
                case 96891675: goto L_0x0068;
                case 109407362: goto L_0x005f;
                case 109526418: goto L_0x0056;
                case 965561430: goto L_0x004b;
                case 1099846370: goto L_0x0042;
                case 1943291465: goto L_0x0037;
                default: goto L_0x0033;
            }
        L_0x0033:
            r6 = r18
            goto L_0x00ff
        L_0x0037:
            boolean r0 = r0.equals(r2)
            if (r0 == 0) goto L_0x0033
            r0 = 4
        L_0x003e:
            r6 = r18
            goto L_0x0101
        L_0x0042:
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x0033
            r0 = 12
            goto L_0x003e
        L_0x004b:
            java.lang.String r6 = "reduceRight"
            boolean r0 = r0.equals(r6)
            if (r0 == 0) goto L_0x0033
            r0 = 11
            goto L_0x003e
        L_0x0056:
            boolean r0 = r0.equals(r3)
            if (r0 == 0) goto L_0x0033
            r0 = 14
            goto L_0x003e
        L_0x005f:
            boolean r0 = r0.equals(r4)
            if (r0 == 0) goto L_0x0033
            r0 = 13
            goto L_0x003e
        L_0x0068:
            boolean r0 = r0.equals(r5)
            if (r0 == 0) goto L_0x0033
            r6 = r18
            r0 = 1
            goto L_0x0101
        L_0x0073:
            boolean r0 = r0.equals(r15)
            if (r0 == 0) goto L_0x0033
            r0 = 16
            goto L_0x003e
        L_0x007c:
            boolean r0 = r0.equals(r14)
            if (r0 == 0) goto L_0x0033
            r0 = 15
            goto L_0x003e
        L_0x0085:
            java.lang.String r6 = "push"
            boolean r0 = r0.equals(r6)
            if (r0 == 0) goto L_0x0033
            r0 = 9
            goto L_0x003e
        L_0x0090:
            boolean r0 = r0.equals(r13)
            if (r0 == 0) goto L_0x0033
            r0 = 5
            goto L_0x003e
        L_0x0098:
            boolean r0 = r0.equals(r12)
            if (r0 == 0) goto L_0x0033
            r0 = 8
            goto L_0x003e
        L_0x00a1:
            boolean r0 = r0.equals(r11)
            if (r0 == 0) goto L_0x0033
            r0 = 7
            goto L_0x003e
        L_0x00a9:
            java.lang.String r6 = "unshift"
            boolean r0 = r0.equals(r6)
            if (r0 == 0) goto L_0x0033
            r0 = 19
            goto L_0x003e
        L_0x00b4:
            boolean r0 = r0.equals(r10)
            if (r0 == 0) goto L_0x0033
            r0 = 6
            goto L_0x003e
        L_0x00bc:
            boolean r0 = r0.equals(r9)
            if (r0 == 0) goto L_0x0033
            r6 = r18
            r0 = 3
            goto L_0x0101
        L_0x00c6:
            java.lang.String r6 = "splice"
            boolean r0 = r0.equals(r6)
            if (r0 == 0) goto L_0x0033
            r0 = 17
            goto L_0x003e
        L_0x00d2:
            java.lang.String r6 = "reduce"
            boolean r0 = r0.equals(r6)
            if (r0 == 0) goto L_0x0033
            r0 = 10
            goto L_0x003e
        L_0x00de:
            boolean r0 = r0.equals(r8)
            if (r0 == 0) goto L_0x0033
            r6 = r18
            r0 = 2
            goto L_0x0101
        L_0x00e8:
            java.lang.String r6 = "concat"
            boolean r0 = r0.equals(r6)
            if (r0 == 0) goto L_0x0033
            r6 = r18
            r0 = 0
            goto L_0x0101
        L_0x00f4:
            r6 = r18
            boolean r0 = r0.equals(r6)
            if (r0 == 0) goto L_0x00ff
            r0 = 18
            goto L_0x0101
        L_0x00ff:
            r0 = r17
        L_0x0101:
            r19 = -4616189618054758400(0xbff0000000000000, double:-1.0)
            java.lang.String r7 = "Callback should be a method"
            r21 = r1
            r22 = r2
            r1 = 0
            switch(r0) {
                case 0: goto L_0x073c;
                case 1: goto L_0x06f9;
                case 2: goto L_0x069a;
                case 3: goto L_0x0668;
                case 4: goto L_0x05cf;
                case 5: goto L_0x058b;
                case 6: goto L_0x04de;
                case 7: goto L_0x04aa;
                case 8: goto L_0x048c;
                case 9: goto L_0x0455;
                case 10: goto L_0x0449;
                case 11: goto L_0x043d;
                case 12: goto L_0x0400;
                case 13: goto L_0x03e5;
                case 14: goto L_0x035b;
                case 15: goto L_0x02e2;
                case 16: goto L_0x0288;
                case 17: goto L_0x01bb;
                case 18: goto L_0x01a7;
                case 19: goto L_0x0115;
                default: goto L_0x010d;
            }
        L_0x010d:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = "Command not supported"
            r0.<init>(r1)
            throw r0
        L_0x0115:
            boolean r0 = r26.isEmpty()
            if (r0 != 0) goto L_0x0196
            com.google.android.gms.internal.measurement.zzae r0 = new com.google.android.gms.internal.measurement.zzae
            r0.<init>()
            java.util.Iterator r1 = r26.iterator()
        L_0x0124:
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto L_0x014a
            java.lang.Object r2 = r1.next()
            com.google.android.gms.internal.measurement.zzap r2 = (com.google.android.gms.internal.measurement.zzap) r2
            r3 = r25
            com.google.android.gms.internal.measurement.zzap r2 = r3.zzb(r2)
            boolean r4 = r2 instanceof com.google.android.gms.internal.measurement.zzag
            if (r4 != 0) goto L_0x0142
            int r4 = r0.zzc()
            r0.zzq(r4, r2)
            goto L_0x0124
        L_0x0142:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "Argument evaluation failed"
            r0.<init>(r1)
            throw r0
        L_0x014a:
            int r1 = r0.zzc()
            java.util.Iterator r2 = r24.zzk()
        L_0x0152:
            boolean r3 = r2.hasNext()
            if (r3 == 0) goto L_0x0171
            java.lang.Object r3 = r2.next()
            java.lang.Integer r3 = (java.lang.Integer) r3
            int r4 = r3.intValue()
            int r4 = r4 + r1
            int r3 = r3.intValue()
            r5 = r24
            com.google.android.gms.internal.measurement.zzap r3 = r5.zze(r3)
            r0.zzq(r4, r3)
            goto L_0x0152
        L_0x0171:
            r5 = r24
            r24.zzn()
            java.util.Iterator r1 = r0.zzk()
        L_0x017a:
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto L_0x0198
            java.lang.Object r2 = r1.next()
            java.lang.Integer r2 = (java.lang.Integer) r2
            int r3 = r2.intValue()
            int r2 = r2.intValue()
            com.google.android.gms.internal.measurement.zzap r2 = r0.zze(r2)
            r5.zzq(r3, r2)
            goto L_0x017a
        L_0x0196:
            r5 = r24
        L_0x0198:
            com.google.android.gms.internal.measurement.zzah r0 = new com.google.android.gms.internal.measurement.zzah
            int r1 = r24.zzc()
            double r1 = (double) r1
            java.lang.Double r1 = java.lang.Double.valueOf(r1)
            r0.<init>(r1)
            return r0
        L_0x01a7:
            r5 = r24
            r0 = r26
            r2 = 0
            com.google.android.gms.internal.measurement.zzh.zzh(r6, r2, r0)
            com.google.android.gms.internal.measurement.zzat r0 = new com.google.android.gms.internal.measurement.zzat
            r1 = r16
            java.lang.String r1 = r5.zzj(r1)
            r0.<init>(r1)
            return r0
        L_0x01bb:
            r5 = r24
            r3 = r25
            r0 = r26
            r2 = 0
            boolean r4 = r26.isEmpty()
            if (r4 == 0) goto L_0x01cf
            com.google.android.gms.internal.measurement.zzae r0 = new com.google.android.gms.internal.measurement.zzae
            r0.<init>()
            goto L_0x079d
        L_0x01cf:
            java.lang.Object r4 = r0.get(r2)
            com.google.android.gms.internal.measurement.zzap r4 = (com.google.android.gms.internal.measurement.zzap) r4
            com.google.android.gms.internal.measurement.zzap r4 = r3.zzb(r4)
            java.lang.Double r4 = r4.zzh()
            double r6 = r4.doubleValue()
            double r6 = com.google.android.gms.internal.measurement.zzh.zza(r6)
            int r4 = (int) r6
            if (r4 >= 0) goto L_0x01f2
            int r6 = r24.zzc()
            int r4 = r4 + r6
            int r4 = java.lang.Math.max(r2, r4)
            goto L_0x01fc
        L_0x01f2:
            int r2 = r24.zzc()
            if (r4 <= r2) goto L_0x01fc
            int r4 = r24.zzc()
        L_0x01fc:
            int r2 = r24.zzc()
            com.google.android.gms.internal.measurement.zzae r6 = new com.google.android.gms.internal.measurement.zzae
            r6.<init>()
            int r7 = r26.size()
            r8 = 1
            if (r7 <= r8) goto L_0x0272
            java.lang.Object r1 = r0.get(r8)
            com.google.android.gms.internal.measurement.zzap r1 = (com.google.android.gms.internal.measurement.zzap) r1
            com.google.android.gms.internal.measurement.zzap r1 = r3.zzb(r1)
            java.lang.Double r1 = r1.zzh()
            double r7 = r1.doubleValue()
            double r7 = com.google.android.gms.internal.measurement.zzh.zza(r7)
            int r1 = (int) r7
            r7 = 0
            int r1 = java.lang.Math.max(r7, r1)
            if (r1 <= 0) goto L_0x0244
            r7 = r4
        L_0x022b:
            int r8 = r4 + r1
            int r8 = java.lang.Math.min(r2, r8)
            if (r7 >= r8) goto L_0x0244
            com.google.android.gms.internal.measurement.zzap r8 = r5.zze(r4)
            int r9 = r6.zzc()
            r6.zzq(r9, r8)
            r5.zzp(r4)
            r8 = 1
            int r7 = r7 + r8
            goto L_0x022b
        L_0x0244:
            int r1 = r26.size()
            r2 = 2
            if (r1 <= r2) goto L_0x0285
            r7 = 2
        L_0x024c:
            int r1 = r26.size()
            if (r7 >= r1) goto L_0x0285
            java.lang.Object r1 = r0.get(r7)
            com.google.android.gms.internal.measurement.zzap r1 = (com.google.android.gms.internal.measurement.zzap) r1
            com.google.android.gms.internal.measurement.zzap r1 = r3.zzb(r1)
            boolean r2 = r1 instanceof com.google.android.gms.internal.measurement.zzag
            if (r2 != 0) goto L_0x026a
            int r2 = r4 + r7
            int r2 = r2 + -2
            r5.zzo(r2, r1)
            r1 = 1
            int r7 = r7 + r1
            goto L_0x024c
        L_0x026a:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = "Failed to parse elements to add"
            r0.<init>(r1)
            throw r0
        L_0x0272:
            if (r4 >= r2) goto L_0x0285
            com.google.android.gms.internal.measurement.zzap r0 = r5.zze(r4)
            int r3 = r6.zzc()
            r6.zzq(r3, r0)
            r5.zzq(r4, r1)
            r7 = 1
            int r4 = r4 + r7
            goto L_0x0272
        L_0x0285:
            r0 = r6
            goto L_0x079d
        L_0x0288:
            r5 = r24
            r3 = r25
            r0 = r26
            r7 = 1
            com.google.android.gms.internal.measurement.zzh.zzj(r15, r7, r0)
            int r2 = r24.zzc()
            r4 = 2
            if (r2 < r4) goto L_0x043a
            java.util.List r2 = r24.zzm()
            boolean r4 = r26.isEmpty()
            if (r4 != 0) goto L_0x02be
            r4 = 0
            java.lang.Object r0 = r0.get(r4)
            com.google.android.gms.internal.measurement.zzap r0 = (com.google.android.gms.internal.measurement.zzap) r0
            com.google.android.gms.internal.measurement.zzap r0 = r3.zzb(r0)
            boolean r1 = r0 instanceof com.google.android.gms.internal.measurement.zzai
            if (r1 == 0) goto L_0x02b6
            r1 = r0
            com.google.android.gms.internal.measurement.zzai r1 = (com.google.android.gms.internal.measurement.zzai) r1
            goto L_0x02be
        L_0x02b6:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = "Comparator should be a method"
            r0.<init>(r1)
            throw r0
        L_0x02be:
            com.google.android.gms.internal.measurement.zzba r0 = new com.google.android.gms.internal.measurement.zzba
            r0.<init>(r1, r3)
            java.util.Collections.sort(r2, r0)
            r24.zzn()
            java.util.Iterator r0 = r2.iterator()
            r7 = 0
        L_0x02ce:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x043a
            java.lang.Object r1 = r0.next()
            com.google.android.gms.internal.measurement.zzap r1 = (com.google.android.gms.internal.measurement.zzap) r1
            r2 = 1
            int r3 = r7 + 1
            r5.zzq(r7, r1)
            r7 = r3
            goto L_0x02ce
        L_0x02e2:
            r5 = r24
            r3 = r25
            r0 = r26
            r2 = 1
            com.google.android.gms.internal.measurement.zzh.zzh(r14, r2, r0)
            r1 = 0
            java.lang.Object r0 = r0.get(r1)
            com.google.android.gms.internal.measurement.zzap r0 = (com.google.android.gms.internal.measurement.zzap) r0
            com.google.android.gms.internal.measurement.zzap r0 = r3.zzb(r0)
            boolean r1 = r0 instanceof com.google.android.gms.internal.measurement.zzai
            if (r1 == 0) goto L_0x0355
            int r1 = r24.zzc()
            if (r1 != 0) goto L_0x0305
            com.google.android.gms.internal.measurement.zzap r0 = com.google.android.gms.internal.measurement.zzap.zzl
            goto L_0x079d
        L_0x0305:
            com.google.android.gms.internal.measurement.zzai r0 = (com.google.android.gms.internal.measurement.zzai) r0
            java.util.Iterator r1 = r24.zzk()
        L_0x030b:
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto L_0x0351
            java.lang.Object r2 = r1.next()
            java.lang.Integer r2 = (java.lang.Integer) r2
            int r2 = r2.intValue()
            boolean r4 = r5.zzs(r2)
            if (r4 == 0) goto L_0x030b
            com.google.android.gms.internal.measurement.zzap r4 = r5.zze(r2)
            double r6 = (double) r2
            com.google.android.gms.internal.measurement.zzah r2 = new com.google.android.gms.internal.measurement.zzah
            java.lang.Double r6 = java.lang.Double.valueOf(r6)
            r2.<init>(r6)
            r6 = 3
            com.google.android.gms.internal.measurement.zzap[] r7 = new com.google.android.gms.internal.measurement.zzap[r6]
            r8 = 0
            r7[r8] = r4
            r4 = 1
            r7[r4] = r2
            r2 = 2
            r7[r2] = r5
            java.util.List r2 = java.util.Arrays.asList(r7)
            com.google.android.gms.internal.measurement.zzap r2 = r0.zza(r3, r2)
            java.lang.Boolean r2 = r2.zzg()
            boolean r2 = r2.booleanValue()
            if (r2 == 0) goto L_0x030b
            com.google.android.gms.internal.measurement.zzap r0 = com.google.android.gms.internal.measurement.zzap.zzk
            goto L_0x079d
        L_0x0351:
            com.google.android.gms.internal.measurement.zzap r0 = com.google.android.gms.internal.measurement.zzap.zzl
            goto L_0x079d
        L_0x0355:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            r0.<init>(r7)
            throw r0
        L_0x035b:
            r5 = r24
            r0 = r26
            r1 = r3
            r2 = 2
            r3 = r25
            com.google.android.gms.internal.measurement.zzh.zzj(r1, r2, r0)
            boolean r1 = r26.isEmpty()
            if (r1 == 0) goto L_0x0372
            com.google.android.gms.internal.measurement.zzap r0 = r24.zzd()
            goto L_0x079d
        L_0x0372:
            int r1 = r24.zzc()
            double r1 = (double) r1
            r4 = 0
            java.lang.Object r4 = r0.get(r4)
            com.google.android.gms.internal.measurement.zzap r4 = (com.google.android.gms.internal.measurement.zzap) r4
            com.google.android.gms.internal.measurement.zzap r4 = r3.zzb(r4)
            java.lang.Double r4 = r4.zzh()
            double r6 = r4.doubleValue()
            double r6 = com.google.android.gms.internal.measurement.zzh.zza(r6)
            r8 = 0
            int r4 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
            if (r4 >= 0) goto L_0x039a
            double r6 = r6 + r1
            double r6 = java.lang.Math.max(r6, r8)
            goto L_0x039e
        L_0x039a:
            double r6 = java.lang.Math.min(r6, r1)
        L_0x039e:
            int r4 = r26.size()
            r8 = 2
            if (r4 != r8) goto L_0x03cc
            r4 = 1
            java.lang.Object r0 = r0.get(r4)
            com.google.android.gms.internal.measurement.zzap r0 = (com.google.android.gms.internal.measurement.zzap) r0
            com.google.android.gms.internal.measurement.zzap r0 = r3.zzb(r0)
            java.lang.Double r0 = r0.zzh()
            double r3 = r0.doubleValue()
            double r3 = com.google.android.gms.internal.measurement.zzh.zza(r3)
            r8 = 0
            int r0 = (r3 > r8 ? 1 : (r3 == r8 ? 0 : -1))
            if (r0 >= 0) goto L_0x03c8
            double r1 = r1 + r3
            double r1 = java.lang.Math.max(r1, r8)
            goto L_0x03cc
        L_0x03c8:
            double r1 = java.lang.Math.min(r1, r3)
        L_0x03cc:
            com.google.android.gms.internal.measurement.zzae r0 = new com.google.android.gms.internal.measurement.zzae
            r0.<init>()
            int r3 = (int) r6
        L_0x03d2:
            double r6 = (double) r3
            int r4 = (r6 > r1 ? 1 : (r6 == r1 ? 0 : -1))
            if (r4 >= 0) goto L_0x079d
            com.google.android.gms.internal.measurement.zzap r4 = r5.zze(r3)
            int r6 = r0.zzc()
            r0.zzq(r6, r4)
            r4 = 1
            int r3 = r3 + r4
            goto L_0x03d2
        L_0x03e5:
            r5 = r24
            r0 = r26
            r2 = 0
            com.google.android.gms.internal.measurement.zzh.zzh(r4, r2, r0)
            int r0 = r24.zzc()
            if (r0 != 0) goto L_0x03f7
            com.google.android.gms.internal.measurement.zzap r0 = com.google.android.gms.internal.measurement.zzap.zzf
            goto L_0x079d
        L_0x03f7:
            com.google.android.gms.internal.measurement.zzap r0 = r5.zze(r2)
            r5.zzp(r2)
            goto L_0x079d
        L_0x0400:
            r5 = r24
            r0 = r26
            r3 = r21
            r2 = 0
            com.google.android.gms.internal.measurement.zzh.zzh(r3, r2, r0)
            int r0 = r24.zzc()
            if (r0 == 0) goto L_0x043a
            r2 = 2
            r7 = 0
        L_0x0412:
            int r3 = r0 / 2
            if (r7 >= r3) goto L_0x043a
            boolean r2 = r5.zzs(r7)
            if (r2 == 0) goto L_0x0436
            com.google.android.gms.internal.measurement.zzap r2 = r5.zze(r7)
            r5.zzq(r7, r1)
            int r3 = r0 + -1
            int r3 = r3 - r7
            boolean r4 = r5.zzs(r3)
            if (r4 == 0) goto L_0x0433
            com.google.android.gms.internal.measurement.zzap r4 = r5.zze(r3)
            r5.zzq(r7, r4)
        L_0x0433:
            r5.zzq(r3, r2)
        L_0x0436:
            r2 = 1
            int r7 = r7 + r2
            r2 = 2
            goto L_0x0412
        L_0x043a:
            r0 = r5
            goto L_0x079d
        L_0x043d:
            r5 = r24
            r3 = r25
            r0 = r26
            r1 = 0
            com.google.android.gms.internal.measurement.zzap r0 = zzc(r5, r3, r0, r1)
            return r0
        L_0x0449:
            r5 = r24
            r3 = r25
            r0 = r26
            r2 = 1
            com.google.android.gms.internal.measurement.zzap r0 = zzc(r5, r3, r0, r2)
            return r0
        L_0x0455:
            r5 = r24
            r3 = r25
            r0 = r26
            boolean r1 = r26.isEmpty()
            if (r1 != 0) goto L_0x047d
            java.util.Iterator r0 = r26.iterator()
        L_0x0465:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x047d
            java.lang.Object r1 = r0.next()
            com.google.android.gms.internal.measurement.zzap r1 = (com.google.android.gms.internal.measurement.zzap) r1
            com.google.android.gms.internal.measurement.zzap r1 = r3.zzb(r1)
            int r2 = r24.zzc()
            r5.zzq(r2, r1)
            goto L_0x0465
        L_0x047d:
            com.google.android.gms.internal.measurement.zzah r0 = new com.google.android.gms.internal.measurement.zzah
            int r1 = r24.zzc()
            double r1 = (double) r1
            java.lang.Double r1 = java.lang.Double.valueOf(r1)
            r0.<init>(r1)
            return r0
        L_0x048c:
            r5 = r24
            r0 = r26
            r2 = 0
            com.google.android.gms.internal.measurement.zzh.zzh(r12, r2, r0)
            int r0 = r24.zzc()
            if (r0 != 0) goto L_0x049e
            com.google.android.gms.internal.measurement.zzap r0 = com.google.android.gms.internal.measurement.zzap.zzf
            goto L_0x079d
        L_0x049e:
            int r0 = r0 + -1
            com.google.android.gms.internal.measurement.zzap r1 = r5.zze(r0)
            r5.zzp(r0)
        L_0x04a7:
            r0 = r1
            goto L_0x079d
        L_0x04aa:
            r5 = r24
            r3 = r25
            r0 = r26
            r2 = 0
            r4 = 1
            com.google.android.gms.internal.measurement.zzh.zzh(r11, r4, r0)
            java.lang.Object r0 = r0.get(r2)
            com.google.android.gms.internal.measurement.zzap r0 = (com.google.android.gms.internal.measurement.zzap) r0
            com.google.android.gms.internal.measurement.zzap r0 = r3.zzb(r0)
            boolean r2 = r0 instanceof com.google.android.gms.internal.measurement.zzao
            if (r2 == 0) goto L_0x04d8
            int r2 = r24.zzc()
            if (r2 != 0) goto L_0x04d0
            com.google.android.gms.internal.measurement.zzae r0 = new com.google.android.gms.internal.measurement.zzae
            r0.<init>()
            goto L_0x079d
        L_0x04d0:
            com.google.android.gms.internal.measurement.zzao r0 = (com.google.android.gms.internal.measurement.zzao) r0
            com.google.android.gms.internal.measurement.zzae r0 = zzb(r5, r3, r0, r1, r1)
            goto L_0x079d
        L_0x04d8:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            r0.<init>(r7)
            throw r0
        L_0x04de:
            r5 = r24
            r3 = r25
            r0 = r26
            r1 = 2
            com.google.android.gms.internal.measurement.zzh.zzj(r10, r1, r0)
            com.google.android.gms.internal.measurement.zzap r1 = com.google.android.gms.internal.measurement.zzap.zzf
            boolean r2 = r26.isEmpty()
            if (r2 != 0) goto L_0x04fb
            r2 = 0
            java.lang.Object r1 = r0.get(r2)
            com.google.android.gms.internal.measurement.zzap r1 = (com.google.android.gms.internal.measurement.zzap) r1
            com.google.android.gms.internal.measurement.zzap r1 = r3.zzb(r1)
        L_0x04fb:
            int r2 = r24.zzc()
            int r2 = r2 + -1
            int r4 = r26.size()
            r6 = 1
            if (r4 <= r6) goto L_0x0542
            java.lang.Object r0 = r0.get(r6)
            com.google.android.gms.internal.measurement.zzap r0 = (com.google.android.gms.internal.measurement.zzap) r0
            com.google.android.gms.internal.measurement.zzap r0 = r3.zzb(r0)
            java.lang.Double r2 = r0.zzh()
            double r2 = r2.doubleValue()
            boolean r2 = java.lang.Double.isNaN(r2)
            if (r2 == 0) goto L_0x052a
            int r0 = r24.zzc()
            int r0 = r0 + -1
            double r2 = (double) r0
        L_0x0527:
            r6 = 0
            goto L_0x0537
        L_0x052a:
            java.lang.Double r0 = r0.zzh()
            double r2 = r0.doubleValue()
            double r2 = com.google.android.gms.internal.measurement.zzh.zza(r2)
            goto L_0x0527
        L_0x0537:
            int r0 = (r2 > r6 ? 1 : (r2 == r6 ? 0 : -1))
            if (r0 >= 0) goto L_0x0545
            int r0 = r24.zzc()
            double r8 = (double) r0
            double r2 = r2 + r8
            goto L_0x0545
        L_0x0542:
            r6 = 0
            double r2 = (double) r2
        L_0x0545:
            int r0 = (r2 > r6 ? 1 : (r2 == r6 ? 0 : -1))
            if (r0 >= 0) goto L_0x0554
            com.google.android.gms.internal.measurement.zzah r0 = new com.google.android.gms.internal.measurement.zzah
            java.lang.Double r1 = java.lang.Double.valueOf(r19)
            r0.<init>(r1)
            goto L_0x079d
        L_0x0554:
            int r0 = r24.zzc()
            double r6 = (double) r0
            double r2 = java.lang.Math.min(r6, r2)
            int r0 = (int) r2
        L_0x055e:
            if (r0 < 0) goto L_0x0580
            boolean r2 = r5.zzs(r0)
            if (r2 == 0) goto L_0x057d
            com.google.android.gms.internal.measurement.zzap r2 = r5.zze(r0)
            boolean r2 = com.google.android.gms.internal.measurement.zzh.zzl(r2, r1)
            if (r2 == 0) goto L_0x057d
            double r0 = (double) r0
            com.google.android.gms.internal.measurement.zzah r2 = new com.google.android.gms.internal.measurement.zzah
            java.lang.Double r0 = java.lang.Double.valueOf(r0)
            r2.<init>(r0)
            r0 = r2
            goto L_0x079d
        L_0x057d:
            int r0 = r0 + -1
            goto L_0x055e
        L_0x0580:
            com.google.android.gms.internal.measurement.zzah r0 = new com.google.android.gms.internal.measurement.zzah
            java.lang.Double r1 = java.lang.Double.valueOf(r19)
            r0.<init>(r1)
            goto L_0x079d
        L_0x058b:
            r5 = r24
            r3 = r25
            r0 = r26
            r1 = r16
            r2 = 1
            com.google.android.gms.internal.measurement.zzh.zzj(r13, r2, r0)
            int r2 = r24.zzc()
            if (r2 != 0) goto L_0x05a1
            com.google.android.gms.internal.measurement.zzap r0 = com.google.android.gms.internal.measurement.zzap.zzm
            goto L_0x079d
        L_0x05a1:
            boolean r2 = r26.isEmpty()
            if (r2 != 0) goto L_0x05c3
            r2 = 0
            java.lang.Object r0 = r0.get(r2)
            com.google.android.gms.internal.measurement.zzap r0 = (com.google.android.gms.internal.measurement.zzap) r0
            com.google.android.gms.internal.measurement.zzap r0 = r3.zzb(r0)
            boolean r1 = r0 instanceof com.google.android.gms.internal.measurement.zzan
            if (r1 != 0) goto L_0x05c0
            boolean r1 = r0 instanceof com.google.android.gms.internal.measurement.zzau
            if (r1 == 0) goto L_0x05bb
            goto L_0x05c0
        L_0x05bb:
            java.lang.String r4 = r0.zzi()
            goto L_0x05c4
        L_0x05c0:
            java.lang.String r4 = ""
            goto L_0x05c4
        L_0x05c3:
            r4 = r1
        L_0x05c4:
            com.google.android.gms.internal.measurement.zzat r0 = new com.google.android.gms.internal.measurement.zzat
            java.lang.String r1 = r5.zzj(r4)
            r0.<init>(r1)
            goto L_0x079d
        L_0x05cf:
            r5 = r24
            r3 = r25
            r0 = r26
            r1 = r22
            r2 = 2
            com.google.android.gms.internal.measurement.zzh.zzj(r1, r2, r0)
            com.google.android.gms.internal.measurement.zzap r1 = com.google.android.gms.internal.measurement.zzap.zzf
            boolean r2 = r26.isEmpty()
            if (r2 != 0) goto L_0x05ee
            r2 = 0
            java.lang.Object r1 = r0.get(r2)
            com.google.android.gms.internal.measurement.zzap r1 = (com.google.android.gms.internal.measurement.zzap) r1
            com.google.android.gms.internal.measurement.zzap r1 = r3.zzb(r1)
        L_0x05ee:
            int r2 = r26.size()
            r4 = 1
            if (r2 <= r4) goto L_0x062c
            java.lang.Object r0 = r0.get(r4)
            com.google.android.gms.internal.measurement.zzap r0 = (com.google.android.gms.internal.measurement.zzap) r0
            com.google.android.gms.internal.measurement.zzap r0 = r3.zzb(r0)
            java.lang.Double r0 = r0.zzh()
            double r2 = r0.doubleValue()
            double r2 = com.google.android.gms.internal.measurement.zzh.zza(r2)
            int r0 = r24.zzc()
            double r6 = (double) r0
            int r0 = (r2 > r6 ? 1 : (r2 == r6 ? 0 : -1))
            if (r0 < 0) goto L_0x061f
            com.google.android.gms.internal.measurement.zzah r0 = new com.google.android.gms.internal.measurement.zzah
            java.lang.Double r1 = java.lang.Double.valueOf(r19)
            r0.<init>(r1)
            goto L_0x079d
        L_0x061f:
            r6 = 0
            int r0 = (r2 > r6 ? 1 : (r2 == r6 ? 0 : -1))
            if (r0 >= 0) goto L_0x062f
            int r0 = r24.zzc()
            double r6 = (double) r0
            double r2 = r2 + r6
            goto L_0x062f
        L_0x062c:
            r6 = 0
            r2 = r6
        L_0x062f:
            java.util.Iterator r0 = r24.zzk()
        L_0x0633:
            boolean r4 = r0.hasNext()
            if (r4 == 0) goto L_0x065d
            java.lang.Object r4 = r0.next()
            java.lang.Integer r4 = (java.lang.Integer) r4
            int r4 = r4.intValue()
            double r6 = (double) r4
            int r8 = (r6 > r2 ? 1 : (r6 == r2 ? 0 : -1))
            if (r8 < 0) goto L_0x0633
            com.google.android.gms.internal.measurement.zzap r4 = r5.zze(r4)
            boolean r4 = com.google.android.gms.internal.measurement.zzh.zzl(r4, r1)
            if (r4 == 0) goto L_0x0633
            com.google.android.gms.internal.measurement.zzah r0 = new com.google.android.gms.internal.measurement.zzah
            java.lang.Double r1 = java.lang.Double.valueOf(r6)
            r0.<init>(r1)
            goto L_0x079d
        L_0x065d:
            com.google.android.gms.internal.measurement.zzah r0 = new com.google.android.gms.internal.measurement.zzah
            java.lang.Double r1 = java.lang.Double.valueOf(r19)
            r0.<init>(r1)
            goto L_0x079d
        L_0x0668:
            r5 = r24
            r3 = r25
            r0 = r26
            r2 = 1
            com.google.android.gms.internal.measurement.zzh.zzh(r9, r2, r0)
            r2 = 0
            java.lang.Object r0 = r0.get(r2)
            com.google.android.gms.internal.measurement.zzap r0 = (com.google.android.gms.internal.measurement.zzap) r0
            com.google.android.gms.internal.measurement.zzap r0 = r3.zzb(r0)
            boolean r2 = r0 instanceof com.google.android.gms.internal.measurement.zzao
            if (r2 == 0) goto L_0x0694
            int r2 = r24.zzb()
            if (r2 != 0) goto L_0x068b
            com.google.android.gms.internal.measurement.zzap r0 = com.google.android.gms.internal.measurement.zzap.zzf
            goto L_0x079d
        L_0x068b:
            com.google.android.gms.internal.measurement.zzao r0 = (com.google.android.gms.internal.measurement.zzao) r0
            zzb(r5, r3, r0, r1, r1)
            com.google.android.gms.internal.measurement.zzap r0 = com.google.android.gms.internal.measurement.zzap.zzf
            goto L_0x079d
        L_0x0694:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            r0.<init>(r7)
            throw r0
        L_0x069a:
            r5 = r24
            r3 = r25
            r0 = r26
            r2 = 1
            com.google.android.gms.internal.measurement.zzh.zzh(r8, r2, r0)
            r2 = 0
            java.lang.Object r0 = r0.get(r2)
            com.google.android.gms.internal.measurement.zzap r0 = (com.google.android.gms.internal.measurement.zzap) r0
            com.google.android.gms.internal.measurement.zzap r0 = r3.zzb(r0)
            boolean r2 = r0 instanceof com.google.android.gms.internal.measurement.zzao
            if (r2 == 0) goto L_0x06f3
            int r2 = r24.zzb()
            if (r2 != 0) goto L_0x06c0
            com.google.android.gms.internal.measurement.zzae r0 = new com.google.android.gms.internal.measurement.zzae
            r0.<init>()
            goto L_0x079d
        L_0x06c0:
            com.google.android.gms.internal.measurement.zzap r2 = r24.zzd()
            com.google.android.gms.internal.measurement.zzae r2 = (com.google.android.gms.internal.measurement.zzae) r2
            com.google.android.gms.internal.measurement.zzao r0 = (com.google.android.gms.internal.measurement.zzao) r0
            java.lang.Boolean r4 = java.lang.Boolean.TRUE
            com.google.android.gms.internal.measurement.zzae r0 = zzb(r5, r3, r0, r1, r4)
            com.google.android.gms.internal.measurement.zzae r1 = new com.google.android.gms.internal.measurement.zzae
            r1.<init>()
            java.util.Iterator r0 = r0.zzk()
        L_0x06d7:
            boolean r3 = r0.hasNext()
            if (r3 == 0) goto L_0x04a7
            java.lang.Object r3 = r0.next()
            java.lang.Integer r3 = (java.lang.Integer) r3
            int r3 = r3.intValue()
            com.google.android.gms.internal.measurement.zzap r3 = r2.zze(r3)
            int r4 = r1.zzc()
            r1.zzq(r4, r3)
            goto L_0x06d7
        L_0x06f3:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            r0.<init>(r7)
            throw r0
        L_0x06f9:
            r1 = r24
            r3 = r25
            r0 = r26
            r2 = 1
            com.google.android.gms.internal.measurement.zzh.zzh(r5, r2, r0)
            r2 = 0
            java.lang.Object r0 = r0.get(r2)
            com.google.android.gms.internal.measurement.zzap r0 = (com.google.android.gms.internal.measurement.zzap) r0
            com.google.android.gms.internal.measurement.zzap r0 = r3.zzb(r0)
            boolean r2 = r0 instanceof com.google.android.gms.internal.measurement.zzao
            if (r2 == 0) goto L_0x0736
            int r2 = r24.zzc()
            if (r2 != 0) goto L_0x071c
            com.google.android.gms.internal.measurement.zzap r0 = com.google.android.gms.internal.measurement.zzap.zzk
            goto L_0x079d
        L_0x071c:
            com.google.android.gms.internal.measurement.zzao r0 = (com.google.android.gms.internal.measurement.zzao) r0
            java.lang.Boolean r2 = java.lang.Boolean.FALSE
            java.lang.Boolean r4 = java.lang.Boolean.TRUE
            com.google.android.gms.internal.measurement.zzae r0 = zzb(r1, r3, r0, r2, r4)
            int r0 = r0.zzc()
            int r1 = r24.zzc()
            if (r0 == r1) goto L_0x0733
            com.google.android.gms.internal.measurement.zzap r0 = com.google.android.gms.internal.measurement.zzap.zzl
            goto L_0x079d
        L_0x0733:
            com.google.android.gms.internal.measurement.zzap r0 = com.google.android.gms.internal.measurement.zzap.zzk
            goto L_0x079d
        L_0x0736:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            r0.<init>(r7)
            throw r0
        L_0x073c:
            r1 = r24
            r3 = r25
            r0 = r26
            com.google.android.gms.internal.measurement.zzap r1 = r24.zzd()
            com.google.android.gms.internal.measurement.zzae r1 = (com.google.android.gms.internal.measurement.zzae) r1
            boolean r2 = r26.isEmpty()
            if (r2 != 0) goto L_0x04a7
            java.util.Iterator r0 = r26.iterator()
        L_0x0752:
            boolean r2 = r0.hasNext()
            if (r2 == 0) goto L_0x04a7
            java.lang.Object r2 = r0.next()
            com.google.android.gms.internal.measurement.zzap r2 = (com.google.android.gms.internal.measurement.zzap) r2
            com.google.android.gms.internal.measurement.zzap r2 = r3.zzb(r2)
            boolean r4 = r2 instanceof com.google.android.gms.internal.measurement.zzag
            if (r4 != 0) goto L_0x0795
            int r4 = r1.zzc()
            boolean r5 = r2 instanceof com.google.android.gms.internal.measurement.zzae
            if (r5 == 0) goto L_0x0791
            com.google.android.gms.internal.measurement.zzae r2 = (com.google.android.gms.internal.measurement.zzae) r2
            java.util.Iterator r5 = r2.zzk()
        L_0x0774:
            boolean r6 = r5.hasNext()
            if (r6 == 0) goto L_0x0752
            java.lang.Object r6 = r5.next()
            java.lang.Integer r6 = (java.lang.Integer) r6
            int r7 = r6.intValue()
            int r7 = r7 + r4
            int r6 = r6.intValue()
            com.google.android.gms.internal.measurement.zzap r6 = r2.zze(r6)
            r1.zzq(r7, r6)
            goto L_0x0774
        L_0x0791:
            r1.zzq(r4, r2)
            goto L_0x0752
        L_0x0795:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "Failed evaluation of arguments"
            r0.<init>(r1)
            throw r0
        L_0x079d:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzbb.zza(java.lang.String, com.google.android.gms.internal.measurement.zzae, com.google.android.gms.internal.measurement.zzg, java.util.List):com.google.android.gms.internal.measurement.zzap");
    }

    private static zzae zzb(zzae zzae, zzg zzg, zzai zzai, Boolean bool, Boolean bool2) {
        zzae zzae2 = new zzae();
        Iterator zzk = zzae.zzk();
        while (zzk.hasNext()) {
            int intValue = ((Integer) zzk.next()).intValue();
            if (zzae.zzs(intValue)) {
                zzap zza = zzai.zza(zzg, Arrays.asList(new zzap[]{zzae.zze(intValue), new zzah(Double.valueOf((double) intValue)), zzae}));
                if (zza.zzg().equals(bool)) {
                    break;
                } else if (bool2 == null || zza.zzg().equals(bool2)) {
                    zzae2.zzq(intValue, zza);
                }
            }
        }
        return zzae2;
    }

    /* JADX WARNING: Removed duplicated region for block: B:25:0x005f  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0094 A[RETURN] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static com.google.android.gms.internal.measurement.zzap zzc(com.google.android.gms.internal.measurement.zzae r10, com.google.android.gms.internal.measurement.zzg r11, java.util.List r12, boolean r13) {
        /*
            r0 = -1
            java.lang.String r1 = "reduce"
            r2 = 1
            com.google.android.gms.internal.measurement.zzh.zzi(r1, r2, r12)
            r3 = 2
            com.google.android.gms.internal.measurement.zzh.zzj(r1, r3, r12)
            r1 = 0
            java.lang.Object r4 = r12.get(r1)
            com.google.android.gms.internal.measurement.zzap r4 = (com.google.android.gms.internal.measurement.zzap) r4
            com.google.android.gms.internal.measurement.zzap r4 = r11.zzb(r4)
            boolean r5 = r4 instanceof com.google.android.gms.internal.measurement.zzai
            if (r5 == 0) goto L_0x009d
            int r5 = r12.size()
            if (r5 != r3) goto L_0x0037
            java.lang.Object r12 = r12.get(r2)
            com.google.android.gms.internal.measurement.zzap r12 = (com.google.android.gms.internal.measurement.zzap) r12
            com.google.android.gms.internal.measurement.zzap r12 = r11.zzb(r12)
            boolean r5 = r12 instanceof com.google.android.gms.internal.measurement.zzag
            if (r5 != 0) goto L_0x002f
            goto L_0x003e
        L_0x002f:
            java.lang.IllegalArgumentException r10 = new java.lang.IllegalArgumentException
            java.lang.String r11 = "Failed to parse initial value"
            r10.<init>(r11)
            throw r10
        L_0x0037:
            int r12 = r10.zzc()
            if (r12 == 0) goto L_0x0095
            r12 = 0
        L_0x003e:
            com.google.android.gms.internal.measurement.zzai r4 = (com.google.android.gms.internal.measurement.zzai) r4
            int r5 = r10.zzc()
            if (r13 == 0) goto L_0x0048
            r6 = r1
            goto L_0x004a
        L_0x0048:
            int r6 = r5 + -1
        L_0x004a:
            if (r13 == 0) goto L_0x004e
            int r5 = r5 + r0
            goto L_0x004f
        L_0x004e:
            r5 = r1
        L_0x004f:
            if (r2 == r13) goto L_0x0052
            goto L_0x0053
        L_0x0052:
            r0 = r2
        L_0x0053:
            if (r12 != 0) goto L_0x005a
            com.google.android.gms.internal.measurement.zzap r12 = r10.zze(r6)
        L_0x0059:
            int r6 = r6 + r0
        L_0x005a:
            int r13 = r5 - r6
            int r13 = r13 * r0
            if (r13 < 0) goto L_0x0094
            boolean r13 = r10.zzs(r6)
            if (r13 == 0) goto L_0x0059
            com.google.android.gms.internal.measurement.zzap r13 = r10.zze(r6)
            double r7 = (double) r6
            com.google.android.gms.internal.measurement.zzah r9 = new com.google.android.gms.internal.measurement.zzah
            java.lang.Double r7 = java.lang.Double.valueOf(r7)
            r9.<init>(r7)
            r7 = 4
            com.google.android.gms.internal.measurement.zzap[] r7 = new com.google.android.gms.internal.measurement.zzap[r7]
            r7[r1] = r12
            r7[r2] = r13
            r7[r3] = r9
            r12 = 3
            r7[r12] = r10
            java.util.List r12 = java.util.Arrays.asList(r7)
            com.google.android.gms.internal.measurement.zzap r12 = r4.zza(r11, r12)
            boolean r13 = r12 instanceof com.google.android.gms.internal.measurement.zzag
            if (r13 != 0) goto L_0x008c
            goto L_0x0059
        L_0x008c:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r11 = "Reduce operation failed"
            r10.<init>(r11)
            throw r10
        L_0x0094:
            return r12
        L_0x0095:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r11 = "Empty array with no initial value error"
            r10.<init>(r11)
            throw r10
        L_0x009d:
            java.lang.IllegalArgumentException r10 = new java.lang.IllegalArgumentException
            java.lang.String r11 = "Callback should be a method"
            r10.<init>(r11)
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzbb.zzc(com.google.android.gms.internal.measurement.zzae, com.google.android.gms.internal.measurement.zzg, java.util.List, boolean):com.google.android.gms.internal.measurement.zzap");
    }
}
