package com.google.android.gms.internal.measurement;

import androidx.customview.widget.ExploreByTouchHelper;
import com.google.firebase.crashlytics.internal.metadata.UserMetadata;
import com.salesforce.marketingcloud.b;
import java.util.List;
import okhttp3.internal.http2.Http2;

public final class zzhx extends zzmd implements zzni {
    /* access modifiers changed from: private */
    public static final zzhx zzb;
    private long zzA;
    private int zzB;
    private String zzC = "";
    private String zzD = "";
    private boolean zzE;
    /* access modifiers changed from: private */
    public zzmj zzF = zzmd.zzcn();
    private String zzG = "";
    private int zzH;
    private int zzI;
    private int zzJ;
    private String zzK = "";
    private long zzL;
    private long zzM;
    private String zzN = "";
    private String zzO = "";
    private int zzP;
    private String zzQ = "";
    private zzia zzR;
    private zzmh zzS = zzmd.zzck();
    private long zzT;
    private long zzU;
    private String zzV = "";
    private String zzW = "";
    private int zzX;
    private boolean zzY;
    private String zzZ = "";
    private boolean zzaa;
    private zzhs zzab;
    private String zzac = "";
    private zzmj zzad = zzmd.zzcn();
    private String zzae = "";
    private long zzaf;
    private boolean zzag;
    private String zzah = "";
    private boolean zzai;
    private String zzaj = "";
    private int zzak;
    private String zzal = "";
    private zzhg zzam;
    private int zzan;
    private zzhc zzao;
    private String zzap = "";
    private zzim zzaq;
    private long zzar;
    private String zzas = "";
    private int zzd;
    private int zze;
    private int zzf;
    /* access modifiers changed from: private */
    public zzmj zzg = zzmd.zzcn();
    private zzmj zzh = zzmd.zzcn();
    private long zzi;
    private long zzj;
    private long zzk;
    private long zzl;
    private long zzm;
    private String zzn = "";
    private String zzo = "";
    private String zzp = "";
    private String zzq = "";
    private int zzr;
    private String zzs = "";
    private String zzt = "";
    private String zzu = "";
    private long zzv;
    private long zzw;
    private String zzx = "";
    private boolean zzy;
    private String zzz = "";

    static {
        zzhx zzhx = new zzhx();
        zzb = zzhx;
        zzmd.zzct(zzhx.class, zzhx);
    }

    private zzhx() {
    }

    public static zzhw zzA(zzhx zzhx) {
        zzlz zzcg = zzb.zzcg();
        zzcg.zzaY(zzhx);
        return (zzhw) zzcg;
    }

    static /* synthetic */ void zzZ(zzhx zzhx, Iterable iterable) {
        zzmj zzmj = zzhx.zzF;
        if (!zzmj.zzc()) {
            zzhx.zzF = zzmd.zzco(zzmj);
        }
        zzko.zzcc(iterable, zzhx.zzF);
    }

    static /* synthetic */ void zzaA(zzhx zzhx, String str) {
        str.getClass();
        zzhx.zzd |= b.u;
        zzhx.zzs = str;
    }

    static /* synthetic */ void zzaB(zzhx zzhx, String str) {
        str.getClass();
        zzhx.zzd |= UserMetadata.MAX_INTERNAL_KEY_SIZE;
        zzhx.zzu = str;
    }

    static /* synthetic */ void zzaC(zzhx zzhx, int i) {
        zzhx.zzd |= 33554432;
        zzhx.zzH = i;
    }

    static /* synthetic */ void zzaD(zzhx zzhx, zzhg zzhg) {
        zzhg.getClass();
        zzhx.zzam = zzhg;
        zzhx.zze |= 4194304;
    }

    static /* synthetic */ void zzaE(zzhx zzhx, long j) {
        zzhx.zze |= 134217728;
        zzhx.zzar = j;
    }

    static /* synthetic */ void zzaF(zzhx zzhx, int i) {
        zzhx.zzd |= 1048576;
        zzhx.zzB = i;
    }

    static /* synthetic */ void zzaG(zzhx zzhx, long j) {
        zzhx.zze |= 32;
        zzhx.zzU = j;
    }

    static /* synthetic */ void zzaH(zzhx zzhx, long j) {
        zzhx.zzd |= 536870912;
        zzhx.zzL = j;
    }

    static /* synthetic */ void zzaI(zzhx zzhx, String str) {
        zzhx.zze |= 131072;
        zzhx.zzah = str;
    }

    static /* synthetic */ void zzaJ(zzhx zzhx, String str) {
        zzhx.zze |= 128;
        zzhx.zzW = str;
    }

    static /* synthetic */ void zzaK(zzhx zzhx, String str) {
        str.getClass();
        zzhx.zze |= 524288;
        zzhx.zzaj = str;
    }

    static /* synthetic */ void zzaL(zzhx zzhx, int i) {
        zzhx.zze |= 8388608;
        zzhx.zzan = i;
    }

    static /* synthetic */ void zzaM(zzhx zzhx, long j) {
        zzhx.zzd |= 524288;
        zzhx.zzA = j;
    }

    static /* synthetic */ void zzaN(zzhx zzhx, String str) {
        str.getClass();
        zzhx.zzd |= 256;
        zzhx.zzp = str;
    }

    static /* synthetic */ void zzaO(zzhx zzhx, String str) {
        str.getClass();
        zzhx.zzd |= ExploreByTouchHelper.INVALID_ID;
        zzhx.zzN = str;
    }

    static /* synthetic */ void zzaP(zzhx zzhx, long j) {
        zzhx.zze |= 16;
        zzhx.zzT = j;
    }

    static /* synthetic */ void zzaQ(zzhx zzhx, boolean z) {
        zzhx.zze |= 65536;
        zzhx.zzag = z;
    }

    static /* synthetic */ void zzaR(zzhx zzhx, long j) {
        zzhx.zzd |= 8;
        zzhx.zzk = j;
    }

    static /* synthetic */ void zzaS(zzhx zzhx, String str) {
        str.getClass();
        zzhx.zze |= Http2.INITIAL_MAX_FRAME_SIZE;
        zzhx.zzae = str;
    }

    static /* synthetic */ void zzaT(zzhx zzhx, int i, zzhm zzhm) {
        zzhm.getClass();
        zzhx.zzcx();
        zzhx.zzg.set(i, zzhm);
    }

    static /* synthetic */ void zzaU(zzhx zzhx, String str) {
        zzhx.zze |= 268435456;
        zzhx.zzas = "";
    }

    static /* synthetic */ void zzaV(zzhx zzhx, String str) {
        str.getClass();
        zzhx.zzd |= 16777216;
        zzhx.zzG = str;
    }

    static /* synthetic */ void zzaW(zzhx zzhx, String str) {
        str.getClass();
        zzhx.zzd |= 4194304;
        zzhx.zzD = str;
    }

    static /* synthetic */ void zzaX(zzhx zzhx, long j) {
        zzhx.zzd |= Http2.INITIAL_MAX_FRAME_SIZE;
        zzhx.zzv = j;
    }

    static /* synthetic */ void zzaY(zzhx zzhx, String str) {
        zzhx.zzd |= 2097152;
        zzhx.zzC = str;
    }

    static /* synthetic */ void zzaZ(zzhx zzhx, boolean z) {
        zzhx.zze |= 262144;
        zzhx.zzai = z;
    }

    static /* synthetic */ void zzaa(zzhx zzhx, Iterable iterable) {
        zzhx.zzcx();
        zzko.zzcc(iterable, zzhx.zzg);
    }

    static /* synthetic */ void zzab(zzhx zzhx, Iterable iterable) {
        zzmh zzmh = zzhx.zzS;
        if (!zzmh.zzc()) {
            int size = zzmh.size();
            zzhx.zzS = zzmh.zzg(size + size);
        }
        zzko.zzcc(iterable, zzhx.zzS);
    }

    static /* synthetic */ void zzac(zzhx zzhx, Iterable iterable) {
        zzmj zzmj = zzhx.zzad;
        if (!zzmj.zzc()) {
            zzhx.zzad = zzmd.zzco(zzmj);
        }
        zzko.zzcc(iterable, zzhx.zzad);
    }

    static /* synthetic */ void zzad(zzhx zzhx, Iterable iterable) {
        zzhx.zzcy();
        zzko.zzcc(iterable, zzhx.zzh);
    }

    static /* synthetic */ void zzae(zzhx zzhx, zzhm zzhm) {
        zzhm.getClass();
        zzhx.zzcx();
        zzhx.zzg.add(zzhm);
    }

    static /* synthetic */ void zzaf(zzhx zzhx, zzio zzio) {
        zzio.getClass();
        zzhx.zzcy();
        zzhx.zzh.add(zzio);
    }

    static /* synthetic */ void zzag(zzhx zzhx) {
        zzhx.zzd &= -262145;
        zzhx.zzz = zzb.zzz;
    }

    static /* synthetic */ void zzai(zzhx zzhx) {
        zzhx.zzd &= -257;
        zzhx.zzp = zzb.zzp;
    }

    static /* synthetic */ void zzaj(zzhx zzhx) {
        zzhx.zzd &= Integer.MAX_VALUE;
        zzhx.zzN = zzb.zzN;
    }

    static /* synthetic */ void zzal(zzhx zzhx) {
        zzhx.zzd &= -2097153;
        zzhx.zzC = zzb.zzC;
    }

    static /* synthetic */ void zzam(zzhx zzhx) {
        zzhx.zzd &= -131073;
        zzhx.zzy = false;
    }

    static /* synthetic */ void zzan(zzhx zzhx) {
        zzhx.zzd &= -33;
        zzhx.zzm = 0;
    }

    static /* synthetic */ void zzao(zzhx zzhx) {
        zzhx.zzd &= -17;
        zzhx.zzl = 0;
    }

    static /* synthetic */ void zzap(zzhx zzhx) {
        zzhx.zzd &= -65537;
        zzhx.zzx = zzb.zzx;
    }

    static /* synthetic */ void zzaq(zzhx zzhx) {
        zzhx.zze &= -8193;
        zzhx.zzac = zzb.zzac;
    }

    static /* synthetic */ void zzar(zzhx zzhx) {
        zzhx.zzd &= -268435457;
        zzhx.zzK = zzb.zzK;
    }

    static /* synthetic */ void zzas(zzhx zzhx) {
        zzhx.zzd &= -3;
        zzhx.zzi = 0;
    }

    static /* synthetic */ void zzat(zzhx zzhx, int i) {
        zzhx.zzcx();
        zzhx.zzg.remove(i);
    }

    static /* synthetic */ void zzau(zzhx zzhx, int i) {
        zzhx.zzcy();
        zzhx.zzh.remove(i);
    }

    static /* synthetic */ void zzav(zzhx zzhx, zzhc zzhc) {
        zzhc.getClass();
        zzhx.zzao = zzhc;
        zzhx.zze |= 16777216;
    }

    static /* synthetic */ void zzaw(zzhx zzhx, int i) {
        zzhx.zze |= 1048576;
        zzhx.zzak = i;
    }

    static /* synthetic */ void zzax(zzhx zzhx, String str) {
        str.getClass();
        zzhx.zze |= 4;
        zzhx.zzQ = str;
    }

    static /* synthetic */ void zzay(zzhx zzhx, String str) {
        str.getClass();
        zzhx.zzd |= b.v;
        zzhx.zzt = str;
    }

    static /* synthetic */ void zzaz(zzhx zzhx, String str) {
        str.getClass();
        zzhx.zzd |= 262144;
        zzhx.zzz = str;
    }

    static /* synthetic */ void zzba(zzhx zzhx, boolean z) {
        zzhx.zzd |= 131072;
        zzhx.zzy = z;
    }

    static /* synthetic */ void zzbb(zzhx zzhx, String str) {
        str.getClass();
        zzhx.zzd |= 128;
        zzhx.zzo = str;
    }

    static /* synthetic */ void zzbc(zzhx zzhx, String str) {
        zzhx.zzd |= 64;
        zzhx.zzn = "android";
    }

    static /* synthetic */ void zzbd(zzhx zzhx, zzia zzia) {
        zzia.getClass();
        zzhx.zzR = zzia;
        zzhx.zze |= 8;
    }

    static /* synthetic */ void zzbe(zzhx zzhx, long j) {
        zzhx.zzd |= 32;
        zzhx.zzm = j;
    }

    static /* synthetic */ void zzbf(zzhx zzhx, long j) {
        zzhx.zzd |= 16;
        zzhx.zzl = j;
    }

    static /* synthetic */ void zzbg(zzhx zzhx, int i) {
        zzhx.zzd |= 1;
        zzhx.zzf = 1;
    }

    static /* synthetic */ void zzbh(zzhx zzhx, String str) {
        str.getClass();
        zzhx.zzd |= 65536;
        zzhx.zzx = str;
    }

    static /* synthetic */ void zzbi(zzhx zzhx, int i) {
        zzhx.zze |= 2;
        zzhx.zzP = i;
    }

    static /* synthetic */ void zzbj(zzhx zzhx, boolean z) {
        zzhx.zzd |= 8388608;
        zzhx.zzE = z;
    }

    static /* synthetic */ void zzbk(zzhx zzhx, String str) {
        str.getClass();
        zzhx.zze |= UserMetadata.MAX_INTERNAL_KEY_SIZE;
        zzhx.zzac = str;
    }

    static /* synthetic */ void zzbl(zzhx zzhx, zzim zzim) {
        zzhx.zzaq = zzim;
        zzhx.zze |= 67108864;
    }

    static /* synthetic */ void zzbm(zzhx zzhx, long j) {
        zzhx.zzd |= 4;
        zzhx.zzj = j;
    }

    static /* synthetic */ void zzbn(zzhx zzhx, long j) {
        zzhx.zze |= 32768;
        zzhx.zzaf = j;
    }

    static /* synthetic */ void zzbo(zzhx zzhx, int i) {
        zzhx.zzd |= 1024;
        zzhx.zzr = i;
    }

    static /* synthetic */ void zzbp(zzhx zzhx, long j) {
        zzhx.zzd |= 2;
        zzhx.zzi = j;
    }

    static /* synthetic */ void zzbq(zzhx zzhx, long j) {
        zzhx.zzd |= 32768;
        zzhx.zzw = 119002;
    }

    static /* synthetic */ void zzbr(zzhx zzhx, int i, zzio zzio) {
        zzio.getClass();
        zzhx.zzcy();
        zzhx.zzh.set(i, zzio);
    }

    static /* synthetic */ void zzbs(zzhx zzhx, String str) {
        str.getClass();
        zzhx.zzd |= 512;
        zzhx.zzq = str;
    }

    private final void zzcx() {
        zzmj zzmj = this.zzg;
        if (!zzmj.zzc()) {
            this.zzg = zzmd.zzco(zzmj);
        }
    }

    private final void zzcy() {
        zzmj zzmj = this.zzh;
        if (!zzmj.zzc()) {
            this.zzh = zzmd.zzco(zzmj);
        }
    }

    public static zzhw zzz() {
        return (zzhw) zzb.zzcg();
    }

    public final zzim zzC() {
        zzim zzim = this.zzaq;
        return zzim == null ? zzim.zzd() : zzim;
    }

    public final zzio zzD(int i) {
        return (zzio) this.zzh.get(i);
    }

    public final String zzE() {
        return this.zzQ;
    }

    public final String zzF() {
        return this.zzt;
    }

    public final String zzG() {
        return this.zzz;
    }

    public final String zzH() {
        return this.zzs;
    }

    public final String zzI() {
        return this.zzu;
    }

    public final String zzJ() {
        return this.zzah;
    }

    public final String zzK() {
        return this.zzW;
    }

    public final String zzL() {
        return this.zzaj;
    }

    public final String zzM() {
        return this.zzp;
    }

    public final String zzN() {
        return this.zzN;
    }

    public final String zzO() {
        return this.zzG;
    }

    public final String zzP() {
        return this.zzD;
    }

    public final String zzQ() {
        return this.zzC;
    }

    public final String zzR() {
        return this.zzo;
    }

    public final String zzS() {
        return this.zzn;
    }

    public final String zzT() {
        return this.zzx;
    }

    public final String zzU() {
        return this.zzac;
    }

    public final String zzV() {
        return this.zzq;
    }

    public final List zzW() {
        return this.zzF;
    }

    public final List zzX() {
        return this.zzg;
    }

    public final List zzY() {
        return this.zzh;
    }

    public final int zza() {
        return this.zzak;
    }

    public final int zzb() {
        return this.zzH;
    }

    public final boolean zzbA() {
        return (this.zze & 134217728) != 0;
    }

    public final boolean zzbB() {
        return (this.zzd & 1048576) != 0;
    }

    public final boolean zzbC() {
        return (this.zzd & 536870912) != 0;
    }

    public final boolean zzbD() {
        return (this.zze & 131072) != 0;
    }

    public final boolean zzbE() {
        return (this.zze & 128) != 0;
    }

    public final boolean zzbF() {
        return (this.zze & 524288) != 0;
    }

    public final boolean zzbG() {
        return (this.zze & 8388608) != 0;
    }

    public final boolean zzbH() {
        return (this.zzd & 524288) != 0;
    }

    public final boolean zzbI() {
        return (this.zzd & ExploreByTouchHelper.INVALID_ID) != 0;
    }

    public final boolean zzbJ() {
        return (this.zze & 16) != 0;
    }

    public final boolean zzbK() {
        return (this.zzd & 8) != 0;
    }

    public final boolean zzbL() {
        return (this.zzd & Http2.INITIAL_MAX_FRAME_SIZE) != 0;
    }

    public final boolean zzbM() {
        return (this.zze & 262144) != 0;
    }

    public final boolean zzbN() {
        return (this.zzd & 131072) != 0;
    }

    public final boolean zzbO() {
        return (this.zzd & 32) != 0;
    }

    public final boolean zzbP() {
        return (this.zzd & 16) != 0;
    }

    public final boolean zzbQ() {
        return (this.zzd & 1) != 0;
    }

    public final boolean zzbR() {
        return (this.zze & 2) != 0;
    }

    public final boolean zzbS() {
        return (this.zzd & 8388608) != 0;
    }

    public final boolean zzbT() {
        return (this.zze & UserMetadata.MAX_INTERNAL_KEY_SIZE) != 0;
    }

    public final boolean zzbU() {
        return (this.zze & 67108864) != 0;
    }

    public final boolean zzbV() {
        return (this.zzd & 4) != 0;
    }

    public final boolean zzbW() {
        return (this.zze & 32768) != 0;
    }

    public final boolean zzbX() {
        return (this.zzd & 1024) != 0;
    }

    public final boolean zzbY() {
        return (this.zzd & 2) != 0;
    }

    public final boolean zzbZ() {
        return (this.zzd & 32768) != 0;
    }

    public final boolean zzbt() {
        return this.zzag;
    }

    public final boolean zzbu() {
        return this.zzai;
    }

    public final boolean zzbv() {
        return this.zzy;
    }

    public final boolean zzbw() {
        return this.zzE;
    }

    public final boolean zzbx() {
        return (this.zze & 16777216) != 0;
    }

    public final boolean zzby() {
        return (this.zzd & 33554432) != 0;
    }

    public final boolean zzbz() {
        return (this.zze & 4194304) != 0;
    }

    public final int zzc() {
        return this.zzB;
    }

    public final int zzd() {
        return this.zzan;
    }

    public final int zze() {
        return this.zzg.size();
    }

    public final int zzf() {
        return this.zzf;
    }

    public final int zzg() {
        return this.zzP;
    }

    public final int zzh() {
        return this.zzr;
    }

    public final int zzi() {
        return this.zzh.size();
    }

    public final long zzj() {
        return this.zzar;
    }

    public final long zzk() {
        return this.zzL;
    }

    /* access modifiers changed from: protected */
    public final Object zzl(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzmd.zzcq(zzb, "\u0004B\u0000\u0002\u0001SB\u0000\u0005\u0000\u0001င\u0000\u0002\u001b\u0003\u001b\u0004ဂ\u0001\u0005ဂ\u0002\u0006ဂ\u0003\u0007ဂ\u0005\bဈ\u0006\tဈ\u0007\nဈ\b\u000bဈ\t\fင\n\rဈ\u000b\u000eဈ\f\u0010ဈ\r\u0011ဂ\u000e\u0012ဂ\u000f\u0013ဈ\u0010\u0014ဇ\u0011\u0015ဈ\u0012\u0016ဂ\u0013\u0017င\u0014\u0018ဈ\u0015\u0019ဈ\u0016\u001aဂ\u0004\u001cဇ\u0017\u001d\u001b\u001eဈ\u0018\u001fင\u0019 င\u001a!င\u001b\"ဈ\u001c#ဂ\u001d$ဂ\u001e%ဈ\u001f&ဈ 'င!)ဈ\",ဉ#-\u001d.ဂ$/ဂ%2ဈ&4ဈ'5᠌(7ဇ)9ဈ*:ဇ+;ဉ,?ဈ-@\u001aAဈ.Cဂ/Dဇ0Gဈ1Hဇ2Iဈ3Jင4Kဈ5Lဉ6Mင7Oဉ8Pဈ9Qဉ:Rဂ;Sဈ<", new Object[]{"zzd", "zze", "zzf", "zzg", zzhm.class, "zzh", zzio.class, "zzi", "zzj", "zzk", "zzm", "zzn", "zzo", "zzp", "zzq", "zzr", "zzs", "zzt", "zzu", "zzv", "zzw", "zzx", "zzy", "zzz", "zzA", "zzB", "zzC", "zzD", "zzl", "zzE", "zzF", zzhi.class, "zzG", "zzH", "zzI", "zzJ", "zzK", "zzL", "zzM", "zzN", "zzO", "zzP", "zzQ", "zzR", "zzS", "zzT", "zzU", "zzV", "zzW", "zzX", zzha.zza, "zzY", "zzZ", "zzaa", "zzab", "zzac", "zzad", "zzae", "zzaf", "zzag", "zzah", "zzai", "zzaj", "zzak", "zzal", "zzam", "zzan", "zzao", "zzap", "zzaq", "zzar", "zzas"});
        } else if (i2 == 3) {
            return new zzhx();
        } else {
            if (i2 == 4) {
                return new zzhw((zzip) null);
            }
            if (i2 == 5) {
                return zzb;
            }
            throw null;
        }
    }

    public final long zzm() {
        return this.zzA;
    }

    public final long zzn() {
        return this.zzT;
    }

    public final long zzo() {
        return this.zzk;
    }

    public final long zzp() {
        return this.zzv;
    }

    public final long zzq() {
        return this.zzm;
    }

    public final long zzr() {
        return this.zzl;
    }

    public final long zzs() {
        return this.zzj;
    }

    public final long zzt() {
        return this.zzaf;
    }

    public final long zzu() {
        return this.zzi;
    }

    public final long zzv() {
        return this.zzw;
    }

    public final zzhc zzw() {
        zzhc zzhc = this.zzao;
        return zzhc == null ? zzhc.zze() : zzhc;
    }

    public final zzhg zzx() {
        zzhg zzhg = this.zzam;
        return zzhg == null ? zzhg.zzc() : zzhg;
    }

    public final zzhm zzy(int i) {
        return (zzhm) this.zzg.get(i);
    }
}
