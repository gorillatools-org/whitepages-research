package com.google.android.gms.measurement.internal;

import android.annotation.TargetApi;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.measurement.zzaa;
import com.google.android.gms.internal.measurement.zzfj;
import com.google.android.gms.internal.measurement.zzfl;
import com.google.android.gms.internal.measurement.zzfp;
import com.google.android.gms.internal.measurement.zzfr;
import com.google.android.gms.internal.measurement.zzfv;
import com.google.android.gms.internal.measurement.zzhc;
import com.google.android.gms.internal.measurement.zzhg;
import com.google.android.gms.internal.measurement.zzhi;
import com.google.android.gms.internal.measurement.zzhk;
import com.google.android.gms.internal.measurement.zzhl;
import com.google.android.gms.internal.measurement.zzhm;
import com.google.android.gms.internal.measurement.zzhp;
import com.google.android.gms.internal.measurement.zzhq;
import com.google.android.gms.internal.measurement.zzhv;
import com.google.android.gms.internal.measurement.zzhw;
import com.google.android.gms.internal.measurement.zzhx;
import com.google.android.gms.internal.measurement.zzic;
import com.google.android.gms.internal.measurement.zzie;
import com.google.android.gms.internal.measurement.zzim;
import com.google.android.gms.internal.measurement.zzin;
import com.google.android.gms.internal.measurement.zzio;
import com.google.android.gms.internal.measurement.zzlp;
import com.google.android.gms.internal.measurement.zzmm;
import com.google.android.gms.internal.measurement.zzng;
import com.google.android.gms.internal.measurement.zzqr;
import com.google.android.gms.internal.measurement.zzrd;
import com.salesforce.marketingcloud.storage.db.k;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.zip.GZIPOutputStream;

public final class zzqa extends zzpg {
    zzqa(zzpv zzpv) {
        super(zzpv);
    }

    static boolean zzA(String str) {
        return str != null && str.matches("([+-])?([0-9]+\\.?[0-9]*|[0-9]*\\.?[0-9]+)") && str.length() <= 310;
    }

    static Bundle[] zzC(List list) {
        ArrayList arrayList = new ArrayList();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            zzhq zzhq = (zzhq) it.next();
            if (zzhq != null) {
                Bundle bundle = new Bundle();
                for (zzhq zzhq2 : zzhq.zzi()) {
                    if (zzhq2.zzy()) {
                        bundle.putString(zzhq2.zzg(), zzhq2.zzh());
                    } else if (zzhq2.zzw()) {
                        bundle.putLong(zzhq2.zzg(), zzhq2.zzd());
                    } else if (zzhq2.zzu()) {
                        bundle.putDouble(zzhq2.zzg(), zzhq2.zza());
                    }
                }
                if (!bundle.isEmpty()) {
                    arrayList.add(bundle);
                }
            }
        }
        return (Bundle[]) arrayList.toArray(new Bundle[arrayList.size()]);
    }

    static final void zzD(zzhl zzhl, String str, Object obj) {
        List zzp = zzhl.zzp();
        int i = 0;
        while (true) {
            if (i >= zzp.size()) {
                i = -1;
                break;
            } else if (str.equals(((zzhq) zzp.get(i)).zzg())) {
                break;
            } else {
                i++;
            }
        }
        zzhp zze = zzhq.zze();
        zze.zzj(str);
        zze.zzi(((Long) obj).longValue());
        if (i >= 0) {
            zzhl.zzj(i, zze);
        } else {
            zzhl.zze(zze);
        }
    }

    static final boolean zzE(zzbh zzbh, zzr zzr) {
        Preconditions.checkNotNull(zzbh);
        Preconditions.checkNotNull(zzr);
        return !TextUtils.isEmpty(zzr.zzb) || !TextUtils.isEmpty(zzr.zzp);
    }

    static final Bundle zzF(List list) {
        Bundle bundle = new Bundle();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            zzhq zzhq = (zzhq) it.next();
            String zzg = zzhq.zzg();
            if (zzhq.zzu()) {
                bundle.putDouble(zzg, zzhq.zza());
            } else if (zzhq.zzv()) {
                bundle.putFloat(zzg, zzhq.zzb());
            } else if (zzhq.zzy()) {
                bundle.putString(zzg, zzhq.zzh());
            } else if (zzhq.zzw()) {
                bundle.putLong(zzg, zzhq.zzd());
            }
        }
        return bundle;
    }

    static final zzhq zzG(zzhm zzhm, String str) {
        for (zzhq zzhq : zzhm.zzi()) {
            if (zzhq.zzg().equals(str)) {
                return zzhq;
            }
        }
        return null;
    }

    static final Object zzH(zzhm zzhm, String str) {
        zzhq zzG = zzG(zzhm, str);
        if (zzG == null) {
            return null;
        }
        if (zzG.zzy()) {
            return zzG.zzh();
        }
        if (zzG.zzw()) {
            return Long.valueOf(zzG.zzd());
        }
        if (zzG.zzu()) {
            return Double.valueOf(zzG.zza());
        }
        if (zzG.zzc() > 0) {
            return zzC(zzG.zzi());
        }
        return null;
    }

    static final Object zzI(zzhm zzhm, String str, Object obj) {
        Object zzH = zzH(zzhm, str);
        return zzH == null ? obj : zzH;
    }

    private final void zzJ(StringBuilder sb, int i, List list) {
        if (list != null) {
            int i2 = i + 1;
            Iterator it = list.iterator();
            while (it.hasNext()) {
                zzhq zzhq = (zzhq) it.next();
                if (zzhq != null) {
                    zzL(sb, i2);
                    sb.append("param {\n");
                    Double d = null;
                    zzQ(sb, i2, "name", zzhq.zzx() ? this.zzu.zzj().zze(zzhq.zzg()) : null);
                    zzQ(sb, i2, "string_value", zzhq.zzy() ? zzhq.zzh() : null);
                    zzQ(sb, i2, "int_value", zzhq.zzw() ? Long.valueOf(zzhq.zzd()) : null);
                    if (zzhq.zzu()) {
                        d = Double.valueOf(zzhq.zza());
                    }
                    zzQ(sb, i2, "double_value", d);
                    if (zzhq.zzc() > 0) {
                        zzJ(sb, i2, zzhq.zzi());
                    }
                    zzL(sb, i2);
                    sb.append("}\n");
                }
            }
        }
    }

    private final void zzK(StringBuilder sb, int i, zzfl zzfl) {
        String str;
        if (zzfl != null) {
            zzL(sb, i);
            sb.append("filter {\n");
            if (zzfl.zzh()) {
                zzQ(sb, i, "complement", Boolean.valueOf(zzfl.zzg()));
            }
            if (zzfl.zzj()) {
                zzQ(sb, i, "param_name", this.zzu.zzj().zze(zzfl.zze()));
            }
            if (zzfl.zzk()) {
                int i2 = i + 1;
                zzfv zzd = zzfl.zzd();
                if (zzd != null) {
                    zzL(sb, i2);
                    sb.append("string_filter {\n");
                    if (zzd.zzi()) {
                        switch (zzd.zzj()) {
                            case 1:
                                str = "UNKNOWN_MATCH_TYPE";
                                break;
                            case 2:
                                str = "REGEXP";
                                break;
                            case 3:
                                str = "BEGINS_WITH";
                                break;
                            case 4:
                                str = "ENDS_WITH";
                                break;
                            case 5:
                                str = "PARTIAL";
                                break;
                            case 6:
                                str = "EXACT";
                                break;
                            default:
                                str = "IN_LIST";
                                break;
                        }
                        zzQ(sb, i2, "match_type", str);
                    }
                    if (zzd.zzh()) {
                        zzQ(sb, i2, "expression", zzd.zzd());
                    }
                    if (zzd.zzg()) {
                        zzQ(sb, i2, "case_sensitive", Boolean.valueOf(zzd.zzf()));
                    }
                    if (zzd.zza() > 0) {
                        zzL(sb, i + 2);
                        sb.append("expression_list {\n");
                        for (String append : zzd.zze()) {
                            zzL(sb, i + 3);
                            sb.append(append);
                            sb.append(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
                        }
                        sb.append("}\n");
                    }
                    zzL(sb, i2);
                    sb.append("}\n");
                }
            }
            if (zzfl.zzi()) {
                zzR(sb, i + 1, "number_filter", zzfl.zzc());
            }
            zzL(sb, i);
            sb.append("}\n");
        }
    }

    private static final void zzL(StringBuilder sb, int i) {
        for (int i2 = 0; i2 < i; i2++) {
            sb.append("  ");
        }
    }

    private static final void zzM(Uri.Builder builder, String str, String str2, Set set) {
        if (!set.contains(str) && !TextUtils.isEmpty(str2)) {
            builder.appendQueryParameter(str, str2);
        }
    }

    private static final String zzN(boolean z, boolean z2, boolean z3) {
        StringBuilder sb = new StringBuilder();
        if (z) {
            sb.append("Dynamic ");
        }
        if (z2) {
            sb.append("Sequence ");
        }
        if (z3) {
            sb.append("Session-Scoped ");
        }
        return sb.toString();
    }

    private static final void zzO(Uri.Builder builder, String[] strArr, Bundle bundle, Set set) {
        for (String split : strArr) {
            String[] split2 = split.split(",");
            String str = split2[0];
            String str2 = split2[split2.length - 1];
            String string = bundle.getString(str);
            if (string != null) {
                zzM(builder, str2, string, set);
            }
        }
    }

    private static final void zzP(StringBuilder sb, int i, String str, zzic zzic) {
        if (zzic != null) {
            zzL(sb, 3);
            sb.append(str);
            sb.append(" {\n");
            if (zzic.zzb() != 0) {
                zzL(sb, 4);
                sb.append("results: ");
                int i2 = 0;
                for (Long l : zzic.zzi()) {
                    int i3 = i2 + 1;
                    if (i2 != 0) {
                        sb.append(", ");
                    }
                    sb.append(l);
                    i2 = i3;
                }
                sb.append(10);
            }
            if (zzic.zzd() != 0) {
                zzL(sb, 4);
                sb.append("status: ");
                int i4 = 0;
                for (Long l2 : zzic.zzk()) {
                    int i5 = i4 + 1;
                    if (i4 != 0) {
                        sb.append(", ");
                    }
                    sb.append(l2);
                    i4 = i5;
                }
                sb.append(10);
            }
            if (zzic.zza() != 0) {
                zzL(sb, 4);
                sb.append("dynamic_filter_timestamps: {");
                int i6 = 0;
                for (zzhk zzhk : zzic.zzh()) {
                    int i7 = i6 + 1;
                    if (i6 != 0) {
                        sb.append(", ");
                    }
                    sb.append(zzhk.zzh() ? Integer.valueOf(zzhk.zza()) : null);
                    sb.append(":");
                    sb.append(zzhk.zzg() ? Long.valueOf(zzhk.zzb()) : null);
                    i6 = i7;
                }
                sb.append("}\n");
            }
            if (zzic.zzc() != 0) {
                zzL(sb, 4);
                sb.append("sequence_filter_timestamps: {");
                int i8 = 0;
                for (zzie zzie : zzic.zzj()) {
                    int i9 = i8 + 1;
                    if (i8 != 0) {
                        sb.append(", ");
                    }
                    sb.append(zzie.zzi() ? Integer.valueOf(zzie.zzb()) : null);
                    sb.append(": [");
                    int i10 = 0;
                    for (Long longValue : zzie.zzf()) {
                        long longValue2 = longValue.longValue();
                        int i11 = i10 + 1;
                        if (i10 != 0) {
                            sb.append(", ");
                        }
                        sb.append(longValue2);
                        i10 = i11;
                    }
                    sb.append("]");
                    i8 = i9;
                }
                sb.append("}\n");
            }
            zzL(sb, 3);
            sb.append("}\n");
        }
    }

    private static final void zzQ(StringBuilder sb, int i, String str, Object obj) {
        if (obj != null) {
            zzL(sb, i + 1);
            sb.append(str);
            sb.append(": ");
            sb.append(obj);
            sb.append(10);
        }
    }

    private static final void zzR(StringBuilder sb, int i, String str, zzfp zzfp) {
        if (zzfp != null) {
            zzL(sb, i);
            sb.append(str);
            sb.append(" {\n");
            if (zzfp.zzg()) {
                int zzm = zzfp.zzm();
                zzQ(sb, i, "comparison_type", zzm != 1 ? zzm != 2 ? zzm != 3 ? zzm != 4 ? "BETWEEN" : "EQUAL" : "GREATER_THAN" : "LESS_THAN" : "UNKNOWN_COMPARISON_TYPE");
            }
            if (zzfp.zzi()) {
                zzQ(sb, i, "match_as_float", Boolean.valueOf(zzfp.zzf()));
            }
            if (zzfp.zzh()) {
                zzQ(sb, i, "comparison_value", zzfp.zzc());
            }
            if (zzfp.zzk()) {
                zzQ(sb, i, "min_comparison_value", zzfp.zze());
            }
            if (zzfp.zzj()) {
                zzQ(sb, i, "max_comparison_value", zzfp.zzd());
            }
            zzL(sb, i);
            sb.append("}\n");
        }
    }

    static int zza(zzhw zzhw, String str) {
        for (int i = 0; i < zzhw.zzd(); i++) {
            if (str.equals(zzhw.zzaE(i).zzg())) {
                return i;
            }
        }
        return -1;
    }

    static zzng zzp(zzng zzng, byte[] bArr) throws zzmm {
        zzlp zza = zzlp.zza();
        if (zza != null) {
            return zzng.zzaV(bArr, zza);
        }
        return zzng.zzaU(bArr);
    }

    static List zzu(BitSet bitSet) {
        int length = (bitSet.length() + 63) / 64;
        ArrayList arrayList = new ArrayList(length);
        for (int i = 0; i < length; i++) {
            long j = 0;
            for (int i2 = 0; i2 < 64; i2++) {
                int i3 = (i * 64) + i2;
                if (i3 >= bitSet.length()) {
                    break;
                }
                if (bitSet.get(i3)) {
                    j |= 1 << i2;
                }
            }
            arrayList.add(Long.valueOf(j));
        }
        return arrayList;
    }

    static boolean zzy(List list, int i) {
        if (i >= list.size() * 64) {
            return false;
        }
        return ((1 << (i % 64)) & ((Long) list.get(i / 64)).longValue()) != 0;
    }

    /* access modifiers changed from: package-private */
    public final byte[] zzB(byte[] bArr) throws IOException {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
            gZIPOutputStream.write(bArr);
            gZIPOutputStream.close();
            byteArrayOutputStream.close();
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            this.zzu.zzaW().zze().zzb("Failed to gzip content", e);
            throw e;
        }
    }

    /* access modifiers changed from: protected */
    public final boolean zzb() {
        return false;
    }

    /* access modifiers changed from: package-private */
    public final long zzd(String str) {
        if (TextUtils.isEmpty(str)) {
            return 0;
        }
        return zzf(str.getBytes(Charset.forName("UTF-8")));
    }

    /* access modifiers changed from: package-private */
    public final long zzf(byte[] bArr) {
        Preconditions.checkNotNull(bArr);
        zzio zzio = this.zzu;
        zzio.zzw().zzg();
        MessageDigest zzI = zzqf.zzI();
        if (zzI != null) {
            return zzqf.zzr(zzI.digest(bArr));
        }
        zzio.zzaW().zze().zza("Failed to get MD5");
        return 0;
    }

    /* access modifiers changed from: package-private */
    public final Bundle zzh(Map map, boolean z) {
        Bundle bundle = new Bundle();
        for (String str : map.keySet()) {
            Object obj = map.get(str);
            if (obj == null) {
                bundle.putString(str, (String) null);
            } else if (obj instanceof Long) {
                bundle.putLong(str, ((Long) obj).longValue());
            } else if (obj instanceof Double) {
                bundle.putDouble(str, ((Double) obj).doubleValue());
            } else if (!(obj instanceof ArrayList)) {
                bundle.putString(str, obj.toString());
            } else if (z) {
                ArrayList arrayList = (ArrayList) obj;
                ArrayList arrayList2 = new ArrayList();
                int size = arrayList.size();
                for (int i = 0; i < size; i++) {
                    arrayList2.add(zzh((Map) arrayList.get(i), false));
                }
                bundle.putParcelableArray(str, (Parcelable[]) arrayList2.toArray(new Parcelable[0]));
            }
        }
        return bundle;
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x002d, code lost:
        r1.recycle();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0030, code lost:
        throw r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0018, code lost:
        r5 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:?, code lost:
        r4.zzu.zzaW().zze().zza("Failed to load parcelable from buffer");
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:8:0x001a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final android.os.Parcelable zzi(byte[] r5, android.os.Parcelable.Creator r6) {
        /*
            r4 = this;
            r0 = 0
            if (r5 != 0) goto L_0x0004
            return r0
        L_0x0004:
            android.os.Parcel r1 = android.os.Parcel.obtain()
            int r2 = r5.length     // Catch:{ ParseException -> 0x001a }
            r3 = 0
            r1.unmarshall(r5, r3, r2)     // Catch:{ ParseException -> 0x001a }
            r1.setDataPosition(r3)     // Catch:{ ParseException -> 0x001a }
            java.lang.Object r5 = r6.createFromParcel(r1)     // Catch:{ ParseException -> 0x001a }
            android.os.Parcelable r5 = (android.os.Parcelable) r5     // Catch:{ ParseException -> 0x001a }
            r0 = r5
            goto L_0x0029
        L_0x0018:
            r5 = move-exception
            goto L_0x002d
        L_0x001a:
            com.google.android.gms.measurement.internal.zzio r5 = r4.zzu     // Catch:{ all -> 0x0018 }
            com.google.android.gms.measurement.internal.zzhe r5 = r5.zzaW()     // Catch:{ all -> 0x0018 }
            com.google.android.gms.measurement.internal.zzhc r5 = r5.zze()     // Catch:{ all -> 0x0018 }
            java.lang.String r6 = "Failed to load parcelable from buffer"
            r5.zza(r6)     // Catch:{ all -> 0x0018 }
        L_0x0029:
            r1.recycle()
            return r0
        L_0x002d:
            r1.recycle()
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzqa.zzi(byte[], android.os.Parcelable$Creator):android.os.Parcelable");
    }

    /* access modifiers changed from: package-private */
    public final zzbh zzj(zzaa zzaa) {
        String str;
        Object obj;
        Bundle zzh = zzh(zzaa.zzf(), true);
        if (!zzh.containsKey("_o") || (obj = zzh.get("_o")) == null) {
            str = "app";
        } else {
            str = obj.toString();
        }
        String str2 = str;
        String zzb = zzjy.zzb(zzaa.zze());
        if (zzb == null) {
            zzb = zzaa.zze();
        }
        return new zzbh(zzb, new zzbf(zzh), str2, zzaa.zza());
    }

    /* access modifiers changed from: package-private */
    @TargetApi(30)
    public final zzov zzl(String str, zzhw zzhw, zzhl zzhl, String str2) {
        int indexOf;
        String str3 = str;
        zzqr.zzb();
        zzio zzio = this.zzu;
        if (!zzio.zzf().zzx(str3, zzgi.zzaV)) {
            return null;
        }
        long currentTimeMillis = zzio.zzaU().currentTimeMillis();
        String[] split = zzio.zzf().zzr(str3, zzgi.zzat).split(",");
        int length = split.length;
        HashSet hashSet = new HashSet(length);
        int i = 0;
        while (i < length) {
            String str4 = split[i];
            Objects.requireNonNull(str4);
            if (hashSet.add(str4)) {
                i++;
            } else {
                throw new IllegalArgumentException("duplicate element: ".concat(str4));
            }
        }
        Set unmodifiableSet = Collections.unmodifiableSet(hashSet);
        zzpv zzpv = this.zzg;
        zzpi zzy = zzpv.zzy();
        String zzm = zzy.zzg.zzr().zzm(str3);
        Uri.Builder builder = new Uri.Builder();
        zzio zzio2 = zzy.zzu;
        builder.scheme(zzio2.zzf().zzr(str3, zzgi.zzam));
        if (!TextUtils.isEmpty(zzm)) {
            builder.authority(zzm + "." + zzio2.zzf().zzr(str3, zzgi.zzan));
        } else {
            builder.authority(zzio2.zzf().zzr(str3, zzgi.zzan));
        }
        builder.path(zzio2.zzf().zzr(str3, zzgi.zzao));
        zzM(builder, "gmp_app_id", zzhw.zzaJ(), unmodifiableSet);
        zzio.zzf().zzj();
        zzM(builder, "gmp_version", String.valueOf(119002), unmodifiableSet);
        String zzaG = zzhw.zzaG();
        zzam zzf = zzio.zzf();
        zzgg zzgg = zzgi.zzaY;
        if (zzf.zzx(str3, zzgg) && zzpv.zzr().zzA(str3)) {
            zzaG = "";
        }
        zzM(builder, "app_instance_id", zzaG, unmodifiableSet);
        zzM(builder, "rdid", zzhw.zzaL(), unmodifiableSet);
        zzM(builder, "bundle_id", zzhw.zzaF(), unmodifiableSet);
        String zzo = zzhl.zzo();
        String zza = zzjy.zza(zzo);
        if (true != TextUtils.isEmpty(zza)) {
            zzo = zza;
        }
        zzM(builder, "app_event_name", zzo, unmodifiableSet);
        zzM(builder, k.a.q, String.valueOf(zzhw.zzb()), unmodifiableSet);
        String zzaK = zzhw.zzaK();
        if (zzio.zzf().zzx(str3, zzgg) && zzpv.zzr().zzE(str3) && !TextUtils.isEmpty(zzaK) && (indexOf = zzaK.indexOf(".")) != -1) {
            zzaK = zzaK.substring(0, indexOf);
        }
        zzM(builder, "os_version", zzaK, unmodifiableSet);
        zzM(builder, "timestamp", String.valueOf(zzhl.zzc()), unmodifiableSet);
        String str5 = "1";
        if (zzhw.zzaP()) {
            zzM(builder, "lat", str5, unmodifiableSet);
        }
        zzM(builder, "privacy_sandbox_version", String.valueOf(zzhw.zza()), unmodifiableSet);
        zzM(builder, "trigger_uri_source", str5, unmodifiableSet);
        zzM(builder, "trigger_uri_timestamp", String.valueOf(currentTimeMillis), unmodifiableSet);
        zzM(builder, "request_uuid", str2, unmodifiableSet);
        List<zzhq> zzp = zzhl.zzp();
        Bundle bundle = new Bundle();
        for (zzhq zzhq : zzp) {
            String zzg = zzhq.zzg();
            if (zzhq.zzu()) {
                bundle.putString(zzg, String.valueOf(zzhq.zza()));
            } else if (zzhq.zzv()) {
                bundle.putString(zzg, String.valueOf(zzhq.zzb()));
            } else if (zzhq.zzy()) {
                bundle.putString(zzg, zzhq.zzh());
            } else if (zzhq.zzw()) {
                bundle.putString(zzg, String.valueOf(zzhq.zzd()));
            }
        }
        zzO(builder, zzio.zzf().zzr(str3, zzgi.zzas).split("\\|"), bundle, unmodifiableSet);
        List<zzio> zzaN = zzhw.zzaN();
        Bundle bundle2 = new Bundle();
        for (zzio zzio3 : zzaN) {
            String zzg2 = zzio3.zzg();
            if (zzio3.zzr()) {
                bundle2.putString(zzg2, String.valueOf(zzio3.zza()));
            } else if (zzio3.zzs()) {
                bundle2.putString(zzg2, String.valueOf(zzio3.zzb()));
            } else if (zzio3.zzv()) {
                bundle2.putString(zzg2, zzio3.zzh());
            } else if (zzio3.zzt()) {
                bundle2.putString(zzg2, String.valueOf(zzio3.zzc()));
            }
        }
        zzO(builder, zzio.zzf().zzr(str3, zzgi.zzar).split("\\|"), bundle2, unmodifiableSet);
        if (true != zzhw.zzaO()) {
            str5 = "0";
        }
        zzM(builder, "dma", str5, unmodifiableSet);
        if (!zzhw.zzaI().isEmpty()) {
            zzM(builder, "dma_cps", zzhw.zzaI(), unmodifiableSet);
        }
        if (zzhw.zzaQ()) {
            zzhc zzg3 = zzhw.zzg();
            if (!zzg3.zzh().isEmpty()) {
                zzM(builder, "dl_gclid", zzg3.zzh(), unmodifiableSet);
            }
            if (!zzg3.zzg().isEmpty()) {
                zzM(builder, "dl_gbraid", zzg3.zzg(), unmodifiableSet);
            }
            if (!zzg3.zzf().isEmpty()) {
                zzM(builder, "dl_gs", zzg3.zzf(), unmodifiableSet);
            }
            if (zzg3.zza() > 0) {
                zzM(builder, "dl_ss_ts", String.valueOf(zzg3.zza()), unmodifiableSet);
            }
            if (!zzg3.zzk().isEmpty()) {
                zzM(builder, "mr_gclid", zzg3.zzk(), unmodifiableSet);
            }
            if (!zzg3.zzj().isEmpty()) {
                zzM(builder, "mr_gbraid", zzg3.zzj(), unmodifiableSet);
            }
            if (!zzg3.zzi().isEmpty()) {
                zzM(builder, "mr_gs", zzg3.zzi(), unmodifiableSet);
            }
            if (zzg3.zzb() > 0) {
                zzM(builder, "mr_click_ts", String.valueOf(zzg3.zzb()), unmodifiableSet);
            }
        }
        return new zzov(builder.build().toString(), currentTimeMillis, 1);
    }

    /* access modifiers changed from: package-private */
    public final zzhm zzm(zzbc zzbc) {
        zzhl zze = zzhm.zze();
        zze.zzl(zzbc.zze);
        zzbf zzbf = zzbc.zzf;
        zzbe zzbe = new zzbe(zzbf);
        while (zzbe.hasNext()) {
            String zza = zzbe.next();
            zzhp zze2 = zzhq.zze();
            zze2.zzj(zza);
            Object zzf = zzbf.zzf(zza);
            Preconditions.checkNotNull(zzf);
            zzw(zze2, zzf);
            zze.zze(zze2);
        }
        String str = zzbc.zzc;
        if (!TextUtils.isEmpty(str) && zzbf.zzf("_o") == null) {
            zzhp zze3 = zzhq.zze();
            zze3.zzj("_o");
            zze3.zzk(str);
            zze.zzf((zzhq) zze3.zzba());
        }
        return (zzhm) zze.zzba();
    }

    /* access modifiers changed from: package-private */
    public final String zzq(zzhv zzhv) {
        String str;
        String str2;
        zzhg zzx;
        if (zzhv == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("\nbatch {\n");
        if (zzhv.zzq()) {
            zzQ(sb, 0, "upload_subdomain", zzhv.zzg());
        }
        if (zzhv.zzp()) {
            zzQ(sb, 0, "sgtm_join_id", zzhv.zzf());
        }
        for (zzhx zzhx : zzhv.zzh()) {
            if (zzhx != null) {
                zzL(sb, 1);
                sb.append("bundle {\n");
                if (zzhx.zzbQ()) {
                    zzQ(sb, 1, "protocol_version", Integer.valueOf(zzhx.zzf()));
                }
                zzrd.zzb();
                zzio zzio = this.zzu;
                if (zzio.zzf().zzx(zzhx.zzF(), zzgi.zzaL) && zzhx.zzbT()) {
                    zzQ(sb, 1, "session_stitching_token", zzhx.zzU());
                }
                zzQ(sb, 1, k.a.b, zzhx.zzS());
                if (zzhx.zzbL()) {
                    zzQ(sb, 1, "gmp_version", Long.valueOf(zzhx.zzp()));
                }
                if (zzhx.zzbZ()) {
                    zzQ(sb, 1, "uploading_gmp_version", Long.valueOf(zzhx.zzv()));
                }
                if (zzhx.zzbJ()) {
                    zzQ(sb, 1, "dynamite_version", Long.valueOf(zzhx.zzn()));
                }
                if (zzhx.zzbC()) {
                    zzQ(sb, 1, "config_version", Long.valueOf(zzhx.zzk()));
                }
                zzQ(sb, 1, "gmp_app_id", zzhx.zzP());
                zzQ(sb, 1, "admob_app_id", zzhx.zzE());
                zzQ(sb, 1, "app_id", zzhx.zzF());
                zzQ(sb, 1, k.a.q, zzhx.zzI());
                if (zzhx.zzby()) {
                    zzQ(sb, 1, "app_version_major", Integer.valueOf(zzhx.zzb()));
                }
                zzQ(sb, 1, "firebase_instance_id", zzhx.zzO());
                if (zzhx.zzbH()) {
                    zzQ(sb, 1, "dev_cert_hash", Long.valueOf(zzhx.zzm()));
                }
                zzQ(sb, 1, "app_store", zzhx.zzH());
                if (zzhx.zzbY()) {
                    zzQ(sb, 1, "upload_timestamp_millis", Long.valueOf(zzhx.zzu()));
                }
                if (zzhx.zzbV()) {
                    zzQ(sb, 1, "start_timestamp_millis", Long.valueOf(zzhx.zzs()));
                }
                if (zzhx.zzbK()) {
                    zzQ(sb, 1, "end_timestamp_millis", Long.valueOf(zzhx.zzo()));
                }
                if (zzhx.zzbP()) {
                    zzQ(sb, 1, "previous_bundle_start_timestamp_millis", Long.valueOf(zzhx.zzr()));
                }
                if (zzhx.zzbO()) {
                    zzQ(sb, 1, "previous_bundle_end_timestamp_millis", Long.valueOf(zzhx.zzq()));
                }
                zzQ(sb, 1, "app_instance_id", zzhx.zzG());
                zzQ(sb, 1, "resettable_device_id", zzhx.zzT());
                zzQ(sb, 1, "ds_id", zzhx.zzN());
                if (zzhx.zzbN()) {
                    zzQ(sb, 1, "limited_ad_tracking", Boolean.valueOf(zzhx.zzbv()));
                }
                zzQ(sb, 1, "os_version", zzhx.zzR());
                zzQ(sb, 1, "device_model", zzhx.zzM());
                zzQ(sb, 1, "user_default_language", zzhx.zzV());
                if (zzhx.zzbX()) {
                    zzQ(sb, 1, "time_zone_offset_minutes", Integer.valueOf(zzhx.zzh()));
                }
                if (zzhx.zzbB()) {
                    zzQ(sb, 1, "bundle_sequential_index", Integer.valueOf(zzhx.zzc()));
                }
                if (zzhx.zzbG()) {
                    zzQ(sb, 1, "delivery_index", Integer.valueOf(zzhx.zzd()));
                }
                if (zzhx.zzbS()) {
                    zzQ(sb, 1, "service_upload", Boolean.valueOf(zzhx.zzbw()));
                }
                zzQ(sb, 1, "health_monitor", zzhx.zzQ());
                if (zzhx.zzbR()) {
                    zzQ(sb, 1, "retry_counter", Integer.valueOf(zzhx.zzg()));
                }
                if (zzhx.zzbE()) {
                    zzQ(sb, 1, "consent_signals", zzhx.zzK());
                }
                if (zzhx.zzbM()) {
                    zzQ(sb, 1, "is_dma_region", Boolean.valueOf(zzhx.zzbu()));
                }
                if (zzhx.zzbF()) {
                    zzQ(sb, 1, "core_platform_services", zzhx.zzL());
                }
                if (zzhx.zzbD()) {
                    zzQ(sb, 1, "consent_diagnostics", zzhx.zzJ());
                }
                if (zzhx.zzbW()) {
                    zzQ(sb, 1, "target_os_version", Long.valueOf(zzhx.zzt()));
                }
                zzqr.zzb();
                if (zzio.zzf().zzx(zzhx.zzF(), zzgi.zzaV)) {
                    zzQ(sb, 1, "ad_services_version", Integer.valueOf(zzhx.zza()));
                    if (zzhx.zzbz() && (zzx = zzhx.zzx()) != null) {
                        zzL(sb, 2);
                        sb.append("attribution_eligibility_status {\n");
                        zzQ(sb, 2, "eligible", Boolean.valueOf(zzx.zzn()));
                        zzQ(sb, 2, "no_access_adservices_attribution_permission", Boolean.valueOf(zzx.zzp()));
                        zzQ(sb, 2, "pre_r", Boolean.valueOf(zzx.zzq()));
                        zzQ(sb, 2, "r_extensions_too_old", Boolean.valueOf(zzx.zzr()));
                        zzQ(sb, 2, "adservices_extension_too_old", Boolean.valueOf(zzx.zzm()));
                        zzQ(sb, 2, "ad_storage_not_allowed", Boolean.valueOf(zzx.zzk()));
                        zzQ(sb, 2, "measurement_manager_disabled", Boolean.valueOf(zzx.zzo()));
                        zzL(sb, 2);
                        sb.append("}\n");
                    }
                }
                if (zzhx.zzbx()) {
                    zzhc zzw = zzhx.zzw();
                    zzL(sb, 2);
                    sb.append("ad_campaign_info {\n");
                    if (zzw.zzC()) {
                        zzQ(sb, 2, "deep_link_gclid", zzw.zzh());
                    }
                    if (zzw.zzB()) {
                        zzQ(sb, 2, "deep_link_gbraid", zzw.zzg());
                    }
                    if (zzw.zzA()) {
                        zzQ(sb, 2, "deep_link_gad_source", zzw.zzf());
                    }
                    if (zzw.zzD()) {
                        zzQ(sb, 2, "deep_link_session_millis", Long.valueOf(zzw.zza()));
                    }
                    if (zzw.zzH()) {
                        zzQ(sb, 2, "market_referrer_gclid", zzw.zzk());
                    }
                    if (zzw.zzG()) {
                        zzQ(sb, 2, "market_referrer_gbraid", zzw.zzj());
                    }
                    if (zzw.zzF()) {
                        zzQ(sb, 2, "market_referrer_gad_source", zzw.zzi());
                    }
                    if (zzw.zzE()) {
                        zzQ(sb, 2, "market_referrer_click_millis", Long.valueOf(zzw.zzb()));
                    }
                    zzL(sb, 2);
                    sb.append("}\n");
                }
                if (zzhx.zzbA()) {
                    zzQ(sb, 1, "batching_timestamp_millis", Long.valueOf(zzhx.zzj()));
                }
                if (zzhx.zzbU()) {
                    zzim zzC = zzhx.zzC();
                    zzL(sb, 2);
                    sb.append("sgtm_diagnostics {\n");
                    int zzg = zzC.zzg();
                    if (zzg != 1) {
                        str = zzg != 2 ? zzg != 3 ? zzg != 4 ? "SDK_SERVICE_UPLOAD" : "PACKAGE_SERVICE_UPLOAD" : "SDK_CLIENT_UPLOAD" : "GA_UPLOAD";
                    } else {
                        str = "UPLOAD_TYPE_UNKNOWN";
                    }
                    zzQ(sb, 2, "upload_type", str);
                    zzQ(sb, 2, "client_upload_eligibility", zzC.zzb().name());
                    int zzf = zzC.zzf();
                    if (zzf != 1) {
                        str2 = zzf != 2 ? zzf != 3 ? zzf != 4 ? zzf != 5 ? "NON_PLAY_MISSING_SGTM_SERVER_URL" : "MISSING_SGTM_PROXY_INFO" : "MISSING_SGTM_SETTINGS" : "NOT_IN_ROLLOUT" : "SERVICE_UPLOAD_ELIGIBLE";
                    } else {
                        str2 = "SERVICE_UPLOAD_ELIGIBILITY_UNKNOWN";
                    }
                    zzQ(sb, 2, "service_upload_eligibility", str2);
                    zzL(sb, 2);
                    sb.append("}\n");
                }
                List<zzio> zzY = zzhx.zzY();
                if (zzY != null) {
                    for (zzio zzio2 : zzY) {
                        if (zzio2 != null) {
                            zzL(sb, 2);
                            sb.append("user_property {\n");
                            Double d = null;
                            zzQ(sb, 2, "set_timestamp_millis", zzio2.zzu() ? Long.valueOf(zzio2.zzd()) : null);
                            zzQ(sb, 2, "name", zzio.zzj().zzf(zzio2.zzg()));
                            zzQ(sb, 2, "string_value", zzio2.zzh());
                            zzQ(sb, 2, "int_value", zzio2.zzt() ? Long.valueOf(zzio2.zzc()) : null);
                            if (zzio2.zzr()) {
                                d = Double.valueOf(zzio2.zza());
                            }
                            zzQ(sb, 2, "double_value", d);
                            zzL(sb, 2);
                            sb.append("}\n");
                        }
                    }
                }
                List<zzhi> zzW = zzhx.zzW();
                if (zzW != null) {
                    for (zzhi zzhi : zzW) {
                        if (zzhi != null) {
                            zzL(sb, 2);
                            sb.append("audience_membership {\n");
                            if (zzhi.zzk()) {
                                zzQ(sb, 2, "audience_id", Integer.valueOf(zzhi.zza()));
                            }
                            if (zzhi.zzm()) {
                                zzQ(sb, 2, "new_audience", Boolean.valueOf(zzhi.zzj()));
                            }
                            zzP(sb, 2, "current_data", zzhi.zzd());
                            if (zzhi.zzn()) {
                                zzP(sb, 2, "previous_data", zzhi.zze());
                            }
                            zzL(sb, 2);
                            sb.append("}\n");
                        }
                    }
                }
                List<zzhm> zzX = zzhx.zzX();
                if (zzX != null) {
                    for (zzhm zzhm : zzX) {
                        if (zzhm != null) {
                            zzL(sb, 2);
                            sb.append("event {\n");
                            zzQ(sb, 2, "name", zzio.zzj().zzd(zzhm.zzh()));
                            if (zzhm.zzu()) {
                                zzQ(sb, 2, "timestamp_millis", Long.valueOf(zzhm.zzd()));
                            }
                            if (zzhm.zzt()) {
                                zzQ(sb, 2, "previous_timestamp_millis", Long.valueOf(zzhm.zzc()));
                            }
                            if (zzhm.zzs()) {
                                zzQ(sb, 2, "count", Integer.valueOf(zzhm.zza()));
                            }
                            if (zzhm.zzb() != 0) {
                                zzJ(sb, 2, zzhm.zzi());
                            }
                            zzL(sb, 2);
                            sb.append("}\n");
                        }
                    }
                }
                zzL(sb, 1);
                sb.append("}\n");
            }
        }
        sb.append("} // End-of-batch\n");
        return sb.toString();
    }

    /* access modifiers changed from: package-private */
    public final String zzr(zzfj zzfj) {
        if (zzfj == null) {
            return "null";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("\nevent_filter {\n");
        if (zzfj.zzp()) {
            zzQ(sb, 0, "filter_id", Integer.valueOf(zzfj.zzb()));
        }
        zzQ(sb, 0, "event_name", this.zzu.zzj().zzd(zzfj.zzg()));
        String zzN = zzN(zzfj.zzk(), zzfj.zzm(), zzfj.zzn());
        if (!zzN.isEmpty()) {
            zzQ(sb, 0, "filter_type", zzN);
        }
        if (zzfj.zzo()) {
            zzR(sb, 1, "event_count_filter", zzfj.zzf());
        }
        if (zzfj.zza() > 0) {
            sb.append("  filters {\n");
            for (zzfl zzK : zzfj.zzh()) {
                zzK(sb, 2, zzK);
            }
        }
        zzL(sb, 1);
        sb.append("}\n}\n");
        return sb.toString();
    }

    /* access modifiers changed from: package-private */
    public final String zzs(zzfr zzfr) {
        if (zzfr == null) {
            return "null";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("\nproperty_filter {\n");
        if (zzfr.zzj()) {
            zzQ(sb, 0, "filter_id", Integer.valueOf(zzfr.zza()));
        }
        zzQ(sb, 0, "property_name", this.zzu.zzj().zzf(zzfr.zze()));
        String zzN = zzN(zzfr.zzg(), zzfr.zzh(), zzfr.zzi());
        if (!zzN.isEmpty()) {
            zzQ(sb, 0, "filter_type", zzN);
        }
        zzK(sb, 1, zzfr.zzb());
        sb.append("}\n");
        return sb.toString();
    }

    /* access modifiers changed from: package-private */
    public final List zzt(List list, List list2) {
        int i;
        ArrayList arrayList = new ArrayList(list);
        Iterator it = list2.iterator();
        while (it.hasNext()) {
            Integer num = (Integer) it.next();
            if (num.intValue() < 0) {
                this.zzu.zzaW().zzk().zzb("Ignoring negative bit index to be cleared", num);
            } else {
                int intValue = num.intValue() / 64;
                if (intValue >= arrayList.size()) {
                    this.zzu.zzaW().zzk().zzc("Ignoring bit index greater than bitSet size", num, Integer.valueOf(arrayList.size()));
                } else {
                    arrayList.set(intValue, Long.valueOf(((Long) arrayList.get(intValue)).longValue() & (~(1 << (num.intValue() % 64)))));
                }
            }
        }
        int size = arrayList.size();
        int size2 = arrayList.size() - 1;
        while (true) {
            int i2 = size2;
            i = size;
            size = i2;
            if (size >= 0 && ((Long) arrayList.get(size)).longValue() == 0) {
                size2 = size - 1;
            }
        }
        return arrayList.subList(0, i);
    }

    /* access modifiers changed from: package-private */
    public final Map zzv(Bundle bundle, boolean z) {
        HashMap hashMap = new HashMap();
        for (String next : bundle.keySet()) {
            Object obj = bundle.get(next);
            boolean z2 = obj instanceof Parcelable[];
            if (z2 || (obj instanceof ArrayList) || (obj instanceof Bundle)) {
                if (z) {
                    ArrayList arrayList = new ArrayList();
                    if (z2) {
                        for (Parcelable parcelable : (Parcelable[]) obj) {
                            if (parcelable instanceof Bundle) {
                                arrayList.add(zzv((Bundle) parcelable, false));
                            }
                        }
                    } else if (obj instanceof ArrayList) {
                        ArrayList arrayList2 = (ArrayList) obj;
                        int size = arrayList2.size();
                        for (int i = 0; i < size; i++) {
                            Object obj2 = arrayList2.get(i);
                            if (obj2 instanceof Bundle) {
                                arrayList.add(zzv((Bundle) obj2, false));
                            }
                        }
                    } else if (obj instanceof Bundle) {
                        arrayList.add(zzv((Bundle) obj, false));
                    }
                    hashMap.put(next, arrayList);
                }
            } else if (obj != null) {
                hashMap.put(next, obj);
            }
        }
        return hashMap;
    }

    /* access modifiers changed from: package-private */
    public final void zzw(zzhp zzhp, Object obj) {
        Preconditions.checkNotNull(obj);
        zzhp.zzg();
        zzhp.zze();
        zzhp.zzd();
        zzhp.zzf();
        if (obj instanceof String) {
            zzhp.zzk((String) obj);
        } else if (obj instanceof Long) {
            zzhp.zzi(((Long) obj).longValue());
        } else if (obj instanceof Double) {
            zzhp.zzh(((Double) obj).doubleValue());
        } else if (obj instanceof Bundle[]) {
            ArrayList arrayList = new ArrayList();
            for (Bundle bundle : (Bundle[]) obj) {
                if (bundle != null) {
                    zzhp zze = zzhq.zze();
                    for (String next : bundle.keySet()) {
                        zzhp zze2 = zzhq.zze();
                        zze2.zzj(next);
                        Object obj2 = bundle.get(next);
                        if (obj2 instanceof Long) {
                            zze2.zzi(((Long) obj2).longValue());
                        } else if (obj2 instanceof String) {
                            zze2.zzk((String) obj2);
                        } else if (obj2 instanceof Double) {
                            zze2.zzh(((Double) obj2).doubleValue());
                        }
                        zze.zzc(zze2);
                    }
                    if (zze.zza() > 0) {
                        arrayList.add((zzhq) zze.zzba());
                    }
                }
            }
            zzhp.zzb(arrayList);
        } else {
            this.zzu.zzaW().zze().zzb("Ignoring invalid (type) event param value", obj);
        }
    }

    /* access modifiers changed from: package-private */
    public final void zzx(zzin zzin, Object obj) {
        Preconditions.checkNotNull(obj);
        zzin.zzc();
        zzin.zzb();
        zzin.zza();
        if (obj instanceof String) {
            zzin.zzh((String) obj);
        } else if (obj instanceof Long) {
            zzin.zze(((Long) obj).longValue());
        } else if (obj instanceof Double) {
            zzin.zzd(((Double) obj).doubleValue());
        } else {
            this.zzu.zzaW().zze().zzb("Ignoring invalid (type) user attribute value", obj);
        }
    }

    /* access modifiers changed from: package-private */
    public final boolean zzz(long j, long j2) {
        return j == 0 || j2 <= 0 || Math.abs(this.zzu.zzaU().currentTimeMillis() - j) > j2;
    }
}
